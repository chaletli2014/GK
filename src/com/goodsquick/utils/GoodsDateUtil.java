package com.goodsquick.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GoodsDateUtil {

	private static String DATE_FORMAT_YYYYmmDD = "yyyyMMdd";
	private static String DATE_FORMAT_YYYY_mm_DD = "yyyy-MM-dd";
	private static String DATE_FORMAT_HH_mm_ss = "HH:mm:ss";
	
	public static String getStringFormat(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_YYYYmmDD);
		return sdf.format(date);
	}
	
	public static Date getDateFormat(String dateStr){
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_YYYY_mm_DD);
		try {
			return sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 判断参数日期是否是当天
	 * @param date
	 * @return Date
	 */
	public static boolean isSameDay(Date date){
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_YYYY_mm_DD);
		try {
			return sdf.parse(sdf.format(date)).equals(sdf.parse(sdf.format(now)));
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static void main(String[] args){
		System.out.println(getStringFormat(new Date()));
	}
}
