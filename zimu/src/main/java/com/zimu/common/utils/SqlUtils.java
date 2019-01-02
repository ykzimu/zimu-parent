package com.zimu.common.utils;

import java.sql.*;

public class SqlUtils {

	// 定义MySQL数据库的连接地址
	private static final String URL = "jdbc:mysql://www.qinbeixian.com:3306/zimu";
	private static final String USERNAME = "yangkun";
	private static final String PASSWORD = "yksyc316497";
	private static Connection conn = null;

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void selectByField(String tableName) {
		try {

			ResultSetMetaData metaData = getMetaData(tableName);
			int colCount = metaData.getColumnCount();
			StringBuffer buffer = new StringBuffer();
			buffer.append(
					"\t<select id=\"selectByField\" parameterType=\"java.util.Map\" resultMap=\"BaseResultMap\">\n");
			buffer.append("\t\tselect\n");
			buffer.append("\t\t<include refid=\"Base_Column_List\" />\n");
			buffer.append("\t\tfrom " + tableName + "\n");
			buffer.append("\t\twhere 1 = 1\n");
			for (int i = 1; i <= colCount; i++) {
				String colName = metaData.getColumnName(i);
				int colType = metaData.getColumnType(i);
				JDBCType type = JDBCType.valueOf(colType);
				String upname = toUpperCase(colName);
				buffer.append("\t\t<if test=\"" + upname + " != null\">\n");
				buffer.append("\t\t\tand " + colName + " = #{" + upname + ",jdbcType=" + type.getName() + "}\n");
				buffer.append("\t\t</if>\n");
			}
			buffer.append("\t</select>\n");
			System.out.println(buffer.toString());

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void selectByIds(String tableName) {

		StringBuffer buffer = new StringBuffer();
		buffer.append("\t<select id=\"selectByIds\" resultMap=\"BaseResultMap\">\n");
		buffer.append("\t\tselect\n");
		buffer.append("\t\t<include refid=\"Base_Column_List\" />\n");
		buffer.append("\t\tfrom " + tableName + "\n");
		buffer.append("\t\twhere id in\n");
		buffer.append(
				"\t\t<foreach collection=\"list\" index=\"index\" item=\"item\" open=\"(\" separator=\",\" close=\")\">\n");
		buffer.append("\t\t\t#{item}\n");
		buffer.append("\t\t</foreach>\n");
		buffer.append("\t</select>\n");
		System.out.println(buffer.toString());

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

	private static String toUpperCase(String name) {
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

}
