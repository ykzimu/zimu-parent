package com.zimu;

import com.zimu.design.abstractfactory.AbstractFactory;
import com.zimu.design.abstractfactory.AmdFactory;
import com.zimu.design.abstractfactory.ComputerEngineer;
import com.zimu.design.abstractfactory.IntelFactory;
import com.zimu.design.adapter.*;
import com.zimu.design.bridge.*;
import com.zimu.design.builder.Person;
import com.zimu.design.composite.MarketBranch;
import com.zimu.design.composite.MarketJoin;
import com.zimu.design.decorator.ConcreteComponent;
import com.zimu.design.decorator.ConcreteDecoratorA;
import com.zimu.design.decorator.ConcreteDecoratorB;
import com.zimu.design.decorator.DecoratorComponent;
import com.zimu.design.facade.ModuleFacade;
import com.zimu.design.factorymethod.*;
import com.zimu.design.flyweight.ConnectionPool;
import com.zimu.design.prototype.Prototype;
import com.zimu.design.prototype.PrototypePhone;
import com.zimu.design.proxy.*;
import com.zimu.design.singleton.Singleton;
import com.zimu.design.singleton.Singleton3;
import com.zimu.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Slf4j
public class DesignTest {

    /**
     * 1、工厂方法模式（Factory Method Pattern）
     * 1.1：普通工厂模式
     */
    @Test
    public void test1a() {
        NormalSendFactory sendFactory = new NormalSendFactory();
        Sender sender = sendFactory.produce("sms");
        sender.send();
        sender = sendFactory.produce("mail");
        sender.send();
    }


    /**
     * 1、工厂方法模式（Factory Method Pattern）
     * 1.2：多个工厂方法模式
     */
    @Test
    public void test1b() {
        MoreSendFactory sendFactory = new MoreSendFactory();
        Sender sender = sendFactory.produceSms();
        sender.send();
        sender = sendFactory.produceMail();
        sender.send();
    }

    /**
     * 1、工厂方法模式（Factory Method Pattern）
     * 1.3：静态工厂方法模式
     */
    @Test
    public void test1c() {
        Sender sender = StaticSendFactory.produceSms();
        sender.send();
        sender = StaticSendFactory.produceMail();
        sender.send();
    }

    /**
     * 1、工厂方法模式（Factory Method Pattern）
     * 针对“工厂方法模式”的“开闭原则”进行改进的一种方式
     */
    @Test
    public void test1() {
        Provider provider = new SendMailFactory();
        Sender sender = provider.produce();
        sender.send();

        provider = new SendSmsFactory();
        sender = provider.produce();
        sender.send();
    }

    /**
     * 2、抽象工厂模式（Abstract Factory Pattern）
     */
    @Test
    public void test2() {
        //创建装机工程师对象
        ComputerEngineer cf = new ComputerEngineer();
        //客户选择并创建需要使用的产品对象
        AbstractFactory af = new IntelFactory();
        //告诉装机工程师自己选择的产品，让装机工程师组装电脑
        cf.makeComputer(af);

        af = new AmdFactory();
        //告诉装机工程师自己选择的产品，让装机工程师组装电脑
        cf.makeComputer(af);
    }

    /**
     * 3、建造者模式（Builder Pattern）
     */
    @Test
    public void test3() {
        Person.PersonBuilder builder = Person.builder();
        Person person = builder.setName("yangkun")//
            .setAge(30)//
            .setSex(1)//
            .setHeight(Float.parseFloat("1.76"))//
            .setWeight(Float.parseFloat("80"))//
            .build();

        System.out.println(person);
    }


    /**
     * 4、单例模式(Singleton Pattern)
     */
    @Test
    public void test4() {

        long startTime = System.currentTimeMillis();

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> {
                Singleton singleton = Singleton3.getInstance();
            });
        }


        executorService.shutdown();
        while (true) {//等待所有任务都执行结束
            if (executorService.isTerminated()) {//所有的子线程都结束了
                break;
            }
        }

        System.out.println("共耗时:" + (System.currentTimeMillis() - startTime));


   /*     Singleton singleton1 = Singleton1.getInstance();//线程不安全 不能使用

        Singleton singleton2 = Singleton2.getInstance();//线程安全 非懒加载 不建议使用

        Singleton singleton3 = Singleton3.getInstance();//线程安全 但是影响性能 不建议使用

        Singleton singleton4 = Singleton4.getInstance();//线程不安全 双重校验锁其实线程并不安全，不能使用

        Singleton singleton5 = Singleton5.getInstance();//线程安全 懒加载 建议使用

        Singleton singleton6 = Singleton6.getInstance();//线程安全 懒加载 建议使用
    */

    }

    /**
     * 5、原型模式	Prototype Pattern
     */
    @Test
    public void test5() {

        //简单形式
        Prototype prototype1 = new Prototype();
        PrototypePhone prototypePhone1 = new PrototypePhone();
        prototypePhone1.setPhoneNumber(13262575259L);
        prototypePhone1.setPhoneType("IOS");
        prototype1.setName("杨坤");
        prototype1.setPrototypePhone(prototypePhone1);


        Prototype prototype2 = prototype1.clone();
        Prototype prototype3 = prototype1.deepClone();
        System.out.println("浅拷贝：" + (prototype1 == prototype2));
        System.out.println("深拷贝：" + (prototype1 == prototype3));


        PrototypePhone prototypePhone2 = prototype2.getPrototypePhone();
        PrototypePhone prototypePhone3 = prototype3.getPrototypePhone();
        System.out.println("浅拷贝引用：" + (prototypePhone1 == prototypePhone2));
        System.out.println("深拷贝引用：" + (prototypePhone1 == prototypePhone3));
    }

    /**
     * 6、适配器模式	Adapter Pattern
     */
    @Test
    public void test6() {

        //类的适配器模式
        Targetable target = new Adapter();
        target.method1();
        target.method2();

        //对象的适配器模式
        Source source = new Source();
        target = new Wrapper(source);
        target.method1();
        target.method2();


        //接口的适配器模式
        Sourceable source1 = new SourceSub1();
        Sourceable source2 = new SourceSub2();
        source1.method1();
        source1.method2();
        source2.method1();
        source2.method2();
    }

    /**
     * 7、桥梁模式/桥接模式	Bridge Pattern
     */
    @Test
    public void test7() {
        Bridge bridge = new MyBridge();

        /*调用第一个对象*/
        Animal animal = new Cat();
        bridge.setAnimal(animal);
        bridge.eat();

        /*调用第二个对象*/
        Animal animal2 = new Dog();
        bridge.setAnimal(animal2);
        bridge.eat();
    }

    /**
     * 8、组合模式	Composite Pattern
     */
    @Test
    public void test8() {
        MarketBranch rootBranch = new MarketBranch("总店");
        MarketBranch qhdBranch = new MarketBranch("秦皇岛分店");
        MarketJoin hgqJoin = new MarketJoin("秦皇岛分店一海港区加盟店");
        MarketJoin btlJoin = new MarketJoin("秦皇岛分店二白塔岭加盟店");

        qhdBranch.add(hgqJoin);
        qhdBranch.add(btlJoin);
        rootBranch.add(qhdBranch);
        rootBranch.PayByCard();
    }

    /**
     * 9、装饰模式	Decorator Pattern
     */
    @Test
    public void test9() {
        DecoratorComponent decoratorComponent = new ConcreteComponent();
        DecoratorComponent decoratorA = new ConcreteDecoratorA(decoratorComponent);
        DecoratorComponent decoratorB = new ConcreteDecoratorB(decoratorComponent);
        decoratorA.sampleOperation();
        decoratorB.sampleOperation();
    }

    /**
     * 10、享元模式	Flyweight Pattern
     */
    @Test
    public void test10() {

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        connectionPool.setDriverClassName("com.mysql.cj.jdbc.Driver");
        connectionPool.setUrl("jdbc:mysql://mysql.qinbeixian.com:3306/zimu?useUnicode=true&characterEncoding=UTF-8&useSSL=false");
        connectionPool.setUsername("yangkun");
        connectionPool.setPassword("yksyc316497");
        connectionPool.setPoolSize(10);
        connectionPool.init();


        ExecutorService executorService = Executors.newFixedThreadPool(6);
        for (int i = 0; i < 6; i++) {
            executorService.execute(() -> {
                ConnectionPool pool = ConnectionPool.getInstance();
                Connection connection = pool.getConnection();
                connection = pool.getConnection();
                connection = pool.getConnection();
                connection = pool.getConnection();
                connection = pool.getConnection();
                //System.out.println(Thread.currentThread().getId());
                // pool.release();
            });
        }


        executorService.shutdown();
        while (true) {//等待所有任务都执行结束
            if (executorService.isTerminated()) {//所有的子线程都结束了
                break;
            }
        }

        System.out.println(connectionPool.getPool().size());


    }

    /**
     * 11、门面模式/外观模式	Facade Pattern
     * 所有模块统一由门面对外提供服务
     */
    @Test
    public void test11() {
        ModuleFacade moduleFacade = new ModuleFacade();
        moduleFacade.a1();
        moduleFacade.b1();
        moduleFacade.c1();
    }

    /**
     * 12、代理模式	Proxy pattern
     */
    @Test
    public void test12() {

        //静态代理
        RealObject realObject = new RealObject();
        ProxyObject proxyObject = new ProxyObject(realObject);
        proxyObject.operation();

        //动态代理
        MapperProxy<UserEntity> mapperProxy = new MapperProxy<>();
        UserMapper userMapper = (UserMapper) Proxy.newProxyInstance(mapperProxy.getClass().getClassLoader(), new Class[]{UserMapper.class}, new MapperInvocationHandler<>(mapperProxy));
        UserEntity entity = userMapper.findOne(1L);
        System.out.println(entity);
    }


}
