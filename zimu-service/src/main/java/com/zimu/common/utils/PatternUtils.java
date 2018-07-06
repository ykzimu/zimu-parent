package com.zimu.common.utils;

import org.apache.commons.lang.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternUtils {

	// yyyy-MM-dd
	public static final String DATE_REGEX = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)";

	// 手机号
	public static final String MOBILE_REGEX = "^1\\d{10}";
	// 邮件
	public static final String EMAIL_REGEX = "";
	// 用户名
	public static final String USERNAME_REGEX = "";

	/**
	 * 正则校验工具
	 * 
	 * @param str
	 * @param regex
	 * @return boolean
	 */
	public static Boolean regexMatches(String str, String regex) {

		// 若为空，则不进行校验
		if (StringUtils.isBlank(str)) {
			return false;
		}

		// 创建 Pattern 对象
		Pattern r = Pattern.compile(regex);
		// 创建 matcher 对象
		Matcher m = r.matcher(str);
		return m.matches();
	}
}
