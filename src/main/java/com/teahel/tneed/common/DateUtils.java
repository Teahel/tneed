package com.teahel.tneed.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**格式化时间
 * @version 1.0
 * @author： L.T.J
 * @date： 2020-12-21
 */

public class DateUtils {
    public static final String YYYY = "yyyy";

    public static final String YYYY_MM = "yyyy-MM";

    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    /**
     * 获取当前时间
     *
     * @return 当前时间
     */
    public static LocalDateTime now() {
        return LocalDateTime.now();
    }

    /**
     * LocalDateTime -> String
     *
     * @param time    时间
     * @param pattern 时间封装格式
     * @return 转后的时间封装格式
     */
    public static String dateFormat(LocalDateTime time, String pattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return dateTimeFormatter.format(time);
    }

    /**
     * LocalDateTime -> String
     *
     * @param time 时间
     * @return 转后的时间封装格式
     */
    public static String dateFormat(LocalDateTime time) {
        return dateFormat(time, YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * String -> LocalDateTime
     *
     * @param time    时间
     * @param pattern 时间格式
     * @return LocalDateTime格式时间
     */
    public static LocalDateTime parse(String time, String pattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(time, dateTimeFormatter);
    }

    /**
     * String -> LocalDateTime YYYY_MM_DD格式
     *
     * @param time 时间
     * @return LocalDateTime格式时间
     */
    public static LocalDateTime parse(String time) {
        return parse(time, YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 时间加减（分）
     * 负数减
     *
     * @param dateTime 需要操做的时间
     * @param minute   分钟
     * @return 操作后的时间
     */
    public static LocalDateTime addDateMinutes(LocalDateTime dateTime, int minute) {
        return dateTime.plusMinutes(minute);
    }
}
