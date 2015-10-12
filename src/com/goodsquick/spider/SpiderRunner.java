package com.goodsquick.spider;

import java.util.List;
import java.util.Map;

import com.goodsquick.model.GoodsOrdinaryHouse;
import com.goodsquick.utils.ExcelUtils;

public class SpiderRunner {

	@org.junit.Test
    public void getDatasByClass(){
		/**
		 * http://hz.xzl.anjuke.com/loupan/canshu/ 35000, 35410
		 * http://sh.xzl.anjuke.com/loupan/canshu/
		 * http://bj.xzl.anjuke.com/loupan/canshu/12215 13215
		 */
        SpiderRule rule = new SpiderRule("http://bj.xzl.anjuke.com/loupan/canshu/",12215, 13215,
                "cent_l_wlzc", SpiderRule.CLASS, SpiderRule.POST);
        List<Map<String,String>> houseInfos = ExtractService.getHouseInfo(rule);
        ExcelUtils.saveHouseInfos2ExcelFromMap(houseInfos, "D:/houseInfo-bj.xlsx");
//        printHouse(extracts);
    }
	
    @org.junit.Test  
    public void getDatasByCssQuery()  
    {  
        SpiderRule rule = new SpiderRule("http://www.11315.com/search",  
                new String[] { "name" }, new String[] { "兴网" },  
                "div.g-mn div.con-model", SpiderRule.SELECTION, SpiderRule.GET);  
        List<LinkTypeData> extracts = ExtractService.extract(rule);  
//        printf(extracts);  
    }
  
    public void printf(List<LinkTypeData> datas)  
    {  
        for (LinkTypeData data : datas)  
        {  
            System.out.println(data.getLinkText());  
            System.out.println(data.getLinkHref());  
            System.out.println("***********************************");  
        }  
  
    }
    
    public void printHouse( List<Map<String,String>> datas){
    	System.out.println(datas);
    }
}
