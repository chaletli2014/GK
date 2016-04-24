package com.goodsquick.utils;

import java.util.List;

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
}
