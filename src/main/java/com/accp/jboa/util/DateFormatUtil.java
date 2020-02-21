package com.accp.jboa.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName: 时间转换小工具
 * @Description: TODO 
 * @author: wy
 * @date: 2018年6月19日 下午10:12:01
 * @version: V1.0
 */
public class DateFormatUtil {
	
	/**
	 * @Title: formatStringToDate
	 * @Description: 将字符串转换为Date类型
	 * @param str
	 * @return
	 * Date
	 */
	public static Date formatStringToDate(String str,String pattern) {
		if(str == null || "".equals(str)) return null;
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			date = sdf.parse(str);
		} catch (ParseException e) {
			throw new RuntimeException("时间转换失败",e);
		}
		return date;
	}
	
	/**
	 * @Title: formatDateToString
	 * @Description: 将Date类型转换为字符串
	 * @param date
	 * @param pattern 字符串格式
	 * @return
	 * String
	 */
	public static String formatDateToString(Date date,String pattern) {
		if(date == null) return null;
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

}
