package com.goodsquick.utils;

import java.util.ArrayList;
import java.util.List;

import com.goodsquick.model.CategoryJsonObj;

public class GoodsCollectionUtils {
	
	/**
	 * 
	 * @param list1 source
	 * @param list2 target
	 * @return
	 * @throws Exception
	 */
	public static List<CategoryJsonObj> getDifferentCategoryList(List<CategoryJsonObj> list1, List<CategoryJsonObj> list2) throws Exception{
		List<CategoryJsonObj> differentList = new ArrayList<CategoryJsonObj>();
		
		if( list1 == null || list1.isEmpty() ){
			return list2;
		}
		if( list2 == null || list2.isEmpty() ){
			return list1;
		}
			
		/**
		 * list1 为DB中持久化的值
		 * list2是页面中获取的值
		 */
		
		for( CategoryJsonObj dbObj : list1 ){
			boolean isExists = false;
			for( CategoryJsonObj pageObj : list2 ){
				if( pageObj.getId().equals(dbObj.getId())){
					isExists = true;
					if( !pageObj.getName().equalsIgnoreCase(dbObj.getName()) ){
						pageObj.setStatus("u");
						differentList.add(pageObj);
					}
					break;
				}
			}
			
			if( !isExists ){
				dbObj.setStatus("d");
				differentList.add(dbObj);
			}
		}
		
		for( CategoryJsonObj pageObj : list2 ){
			boolean isExists = false;
			for( CategoryJsonObj dbObj : list1 ){
				if( pageObj.getId().equals(dbObj.getId())){
					isExists = true;
					break;
				}
			}
			
			if( !isExists ){
				pageObj.setStatus("a");
				differentList.add(pageObj);
			}
		}
		
		
		return differentList;
	}

}
