package com.zimu.common.utils;

import java.text.ParseException;
import java.util.Date;

/**
 * @author yangkun
 * @ClassName: DateUtils
 * @Description: 日期工具类
 * @date 2016年4月26日 下午3:49:40
 */

public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    public enum FormatEnum {

        /**
         * yyyyMMdd
         */
        FORMAT_YMD1("yyyyMMdd"),

        /**
         * yyyy/MM/dd
         */
        FORMAT_YMD2("yyyy/MM/dd"),

        /**
         * yyyy-MM-dd
         */
        FORMAT_YMD3("yyyy-MM-dd"),

        /**
         * yyyyMMddHHmmss
         */
        FORMAT_YMDHMS1("yyyyMMddHHmmss"),

        /**
         * yyyy/MM/dd HH:mm:ss
         */
        FORMAT_YMDHMS2("yyyy/MM/dd HH:mm:ss"),

        /**
         * yyyy-MM-dd HH:mm:ss
         */
        FORMAT_YMDHMS3("yyyy-MM-dd HH:mm:ss");

        private String format;

        private FormatEnum(String format) {
            this.format = format;
        }

        public String getFormat() {
            return this.format;
        }

    }

    /**
     * string转换成date
     */
    public static Date strToDate(String strDate) {
        return strToDate(strDate, FormatEnum.FORMAT_YMD3.getFormat());
    }

    /**
     * string转换成date
     */
    public static Date strToDate(String strDate, FormatEnum format) {
        return strToDate(strDate, format.getFormat());
    }

    /**
     * string转换成date
     */
    public static Date strToDate(String strDate, String format) {

        try {
            return parseDate(strDate, format);
        } catch (ParseException e) {
            return null;
        }

    }

    /**
     * date转换成string
     */
    public static String dateToStr(Date date) {
        return dateToStr(date, FormatEnum.FORMAT_YMD3.getFormat());
    }

    /**
     * date转换成string
     */
    public static String dateToStr(Date date, FormatEnum format) {
        return dateToStr(date, format.getFormat());
    }

    /**
     * date转换成string
     */
    public static String dateToStr(Date date, String format) {
        return org.apache.commons.lang3.time.DateFormatUtils.format(date, format);
    }
}
