package com.zimu.common.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class JfSqlUtils {

	// 定义MySQL数据库的连接地址
	private static final String URL = "jdbc:mysql://devdb.jointforce.com:3306/jf_test_portal?useInformationSchema=true";
	private static final String USERNAME = "jftest";
	private static final String PASSWORD = "Dfv3eqYhn532F243";
	private static Connection conn = null;

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("rawtypes")
	public static String xml(String tableName) throws SQLException {
		tableName = tableName.toLowerCase();
		ResultSetMetaData metaData = getMetaData(tableName);
		int colCount = metaData.getColumnCount();
		String sql = getSql(metaData, tableName);
		StringBuffer buffer = new StringBuffer();
		buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		buffer.append(
				"<!DOCTYPE model-mapping PUBLIC \"-//ResourceOne/Model Mapping DTD 5.0//EN\" \"http://www.chinasofti.com/model-mapping-5.0.dtd\">\n\n");
		buffer.append("<model-mapping>\n");
		buffer.append("\t<class name=\"\" table=\"" + tableName + "\">\n");
		buffer.append("\t\t<comment>" + getTableRemarks(tableName) + "</comment>\n");
		buffer.append("\t\t<sql id=\"list\" type=\"default\"><![CDATA[" + sql + "]]></sql>\n");

		Map<String, String> remarks = getRemarks(tableName);
		for (int i = 1; i <= colCount; i++) {
			String colName = metaData.getColumnName(i).toLowerCase();
			int colType = metaData.getColumnType(i);
			// JDBCType type = JDBCType.valueOf(colType);
			Class clazz = JDBCTypesUtils.jdbcTypeToJavaType(colType);
			String upname = toUpperCase(colName);

			if (i == 1) {
				buffer.append("\t\t<id name=\"" + upname + "\" type=\"" + clazz.getName() + "\">\n");
				buffer.append("\t\t\t<column name=\"" + colName + "\" not-null=\"true\">\n");
				buffer.append("\t\t\t\t<comment>" + remarks.get(colName) + "</comment>\n");
				buffer.append("\t\t\t</column>\n");
				buffer.append("\t\t\t<generator class=\"native\"/>\n");
				buffer.append("\t\t</id>\n");
			} else {
				buffer.append("\t\t<property name=\"" + upname + "\" type=\"" + clazz.getName() + "\">\n");
				buffer.append("\t\t\t<column name=\"" + colName + "\">\n");
				buffer.append("\t\t\t\t<comment>" + remarks.get(colName) + "</comment>\n");
				buffer.append("\t\t\t</column>\n");
				buffer.append("\t\t</property>\n");
			}

		}

		buffer.append("\t</class>\n");
		buffer.append("</model-mapping>\n");

		return buffer.toString();
	}

	private static String getSql(ResultSetMetaData metaData, String tableName) throws SQLException {
		StringBuffer buffer = new StringBuffer();
		int colCount = metaData.getColumnCount();
		for (int i = 1; i <= colCount; i++) {
			String colName = metaData.getColumnName(i).toLowerCase();
			buffer.append(colName);
			if (i != colCount) {
				buffer.append(", ");
			}
		}
		String sql = "SELECT " + buffer.toString() + " FROM " + tableName + " WHERE 1=1";
		return sql;
	}

	private static ResultSetMetaData getMetaData(String tableName) {
		ResultSetMetaData metaData = null;
		try {
			String sql = "select * from " + tableName + " where 1=0";
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			metaData = resultSet.getMetaData();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return metaData;
	}

	private static Map<String, String> getRemarks(String tableName) {
		Map<String, String> map = new HashMap<>();
		try {
			ResultSet rs = conn.getMetaData().getColumns(null, null, tableName.toUpperCase(), "%");
			while (rs.next()) {
				String colName = rs.getString("COLUMN_NAME").toLowerCase();
				String remarks = rs.getString("REMARKS");
				if (remarks == null || remarks.equals("")) {
					remarks = colName;
				}
				map.put(colName, remarks);
			}
		} catch (SQLException e) {

		}

		return map;
	}

	private static String getTableRemarks(String tableName) {
		try {
			ResultSet resultSet = conn.getMetaData().getTables(null, null, tableName, new String[] { "TABLE" });
			resultSet.next();
			return resultSet.getString("REMARKS");
		} catch (SQLException e) {
			return "";
		}
	}

	private static String toUpperCase(String name) {
		name = name.toLowerCase();
		StringBuffer buffer = new StringBuffer();
		String[] names = name.split("_");
		for (int i = 0; i < names.length; i++) {
			if (i == 0) {
				buffer.append(names[i]);
			} else {
				buffer.append(names[i].substring(0, 1).toUpperCase() + names[i].substring(1));
			}
		}

		return buffer.toString();
	}

	public static void main(String[] args) {
		try {

			String xx = xml("jf_biz_orders");
			System.out.println(xx);

		} catch (Exception e) {

		}
	}

}
