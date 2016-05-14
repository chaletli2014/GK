package com.goodsquick.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GoodsDateUtil {

	private static String DATE_FORMAT_YYYYmmDD = "yyyyMMdd";
	private static String DATE_FORMAT_YYYY_mm_DD = "yyyy-MM-dd";
	
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
	
	public static void main(String[] args){
		System.out.println(getStringFormat(new Date()));
	}
}
