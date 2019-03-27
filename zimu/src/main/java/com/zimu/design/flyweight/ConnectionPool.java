package com.zimu.design.flyweight;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.NamedThreadLocal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Data
@Slf4j
public class ConnectionPool {

    private ThreadLocal<Connection> connectionThreadLocal = new NamedThreadLocal<>("connectionThreadLocal");

    private List<Connection> pool = Collections.synchronizedList(new LinkedList<>());

    /* 公有属性 */
    private String url;
    private String username;
    private String password;
    private String driverClassName;

    private int poolSize;

    private volatile Boolean initFlag = false;

    /* 构造方法，做一些初始化工作 */
    private ConnectionPool() {

    }

    /* 返回连接到连接池 */
    public void release() {
        Connection conn = connectionThreadLocal.get();
        if (conn != null) {
            connectionThreadLocal.remove();
            pool.add(conn);
        }
    }

    /* 返回连接池中的一个数据库连接 */
    public Connection getConnection() {

        if (!initFlag) {
            synchronized (this) {
                init();
            }
        }

        Connection connection = connectionThreadLocal.get();
        if (connection != null) {
            return connection;
        }

        if (!pool.isEmpty()) {
            Connection conn = pool.get(0);
            connectionThreadLocal.set(conn);
            pool.remove(conn);
            return conn;
        } else {
            return null;
        }
    }

    public synchronized void init() {
        if (initFlag) {
            return;
        }
        for (int i = 0; i < poolSize; i++) {
            try {
                Class.forName(driverClassName);
                Connection conn = DriverManager.getConnection(url, username, password);
                pool.add(conn);
            } catch (Exception e) {
                log.error("", e);
            }
        }
        initFlag = true;
    }

    public static ConnectionPool getInstance() {
        return ConnectionPoolHoloder.INSTANCE;
    }

    private static class ConnectionPoolHoloder {
        private static final ConnectionPool INSTANCE = new ConnectionPool();
    }
}
