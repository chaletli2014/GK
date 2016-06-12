package com.goodsquick.utils;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.cxf.common.util.CollectionUtils;

public class GoodsQuickStringUtils {

	public static String getInParameterByList(List<String> args ){
		StringBuilder result = new StringBuilder("");
		if( CollectionUtils.isEmpty(args) ){
			return "";
		}else{
			for( String arg : args ){
				result.append("'").append(arg).append("',");
			}
		}
		return result.substring(0, result.length()-1);
	}
	
	public static String getInParameterByLongList(List<Long> args ){
		StringBuilder result = new StringBuilder("");
		if( CollectionUtils.isEmpty(args) ){
			return "";
		}else{
			for( Long arg : args ){
				result.append("'").append(arg).append("',");
			}
		}
		return result.substring(0, result.length()-1);
	}
	
	public static String getInParameterByStr(String args,String splitChar){
		StringBuilder result = new StringBuilder("");
		if( StringUtils.isBlank(args) ){
			return "";
		}else{
			String[] argArray = args.split(splitChar);
			for( String arg : argArray ){
				result.append("'").append(arg).append("',");
			}
		}
		return result.substring(0, result.length()-1);
	}
}
