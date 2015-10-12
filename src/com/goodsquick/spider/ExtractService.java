package com.goodsquick.spider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

public class ExtractService {

	/** 
     * @param rule 
     * @return 
     */  
    public static List<LinkTypeData> extract(SpiderRule rule){  
        // 进行对rule的必要校验  
        validateRule(rule);  
  
        List<LinkTypeData> datas = new ArrayList<LinkTypeData>();  
        LinkTypeData data = null;  
        try  
        {  
            /** 
             * 解析rule 
             */  
            String url = rule.getUrl();  
            String[] params = rule.getParams();  
            String[] values = rule.getValues();  
            String resultTagName = rule.getResultTagName();  
            int type = rule.getType();  
            int requestType = rule.getRequestMoethod();  
  
            Connection conn = Jsoup.connect(url);  
            // 设置查询参数  
  
            if (params != null)  
            {  
                for (int i = 0; i < params.length; i++)  
                {  
                    conn.data(params[i], values[i]);  
                }  
            }  
  
            // 设置请求类型  
            Document doc = null;  
            switch (requestType)  
            {  
            case SpiderRule.GET:  
                doc = conn.timeout(100000).get();  
                break;  
            case SpiderRule.POST:  
                doc = conn.timeout(100000).post();  
                break;  
            }  
  
            //处理返回数据  
            Elements results = new Elements();  
            switch (type)  
            {  
            case SpiderRule.CLASS:  
                results = doc.getElementsByClass(resultTagName);  
                break;  
            case SpiderRule.ID:  
                Element result = doc.getElementById(resultTagName);  
                results.add(result);  
                break;  
            case SpiderRule.SELECTION:  
                results = doc.select(resultTagName);  
                break;  
            default:  
                //当resultTagName为空时默认去body标签  
                if (StringUtils.isEmpty(resultTagName))  
                {  
                    results = doc.getElementsByTag("body");  
                }  
            }  
  
            for (Element result : results)  
            {  
                Elements links = result.getElementsByTag("a");  
  
                for (Element link : links)  
                {  
                    //必要的筛选  
                    String linkHref = link.attr("href");  
                    String linkText = link.text();  
  
                    data = new LinkTypeData();  
                    data.setLinkHref(linkHref);  
                    data.setLinkText(linkText);  
  
                    datas.add(data);  
                }  
            }  
  
        } catch (IOException e)  
        {  
            e.printStackTrace();  
        }  
  
        return datas;  
    }  
    
    /**
     * 
     * @param rule
     * @return
     */
    public static List<Map<String,String>> getHouseInfo(SpiderRule rule){  
        // 进行对rule的必要校验  
        validateRule(rule);
        List<Map<String,String>> allHouseInfo = new ArrayList<Map<String,String>>();
        try{
            /** 
             * 解析rule 
             */  
            String url = rule.getUrl();  
            String resultTagName = rule.getResultTagName();  
            int type = rule.getType();  
            int requestType = rule.getRequestMoethod();
            
            for( int i = rule.getStartNum(); i <= rule.getEndNum(); i++ ){
            	Map<String, String> infoMap = new HashMap<String,String>();
            	String fullUrl = new StringBuilder(300).append(url).append(i).toString();
            	try{
            		Connection conn = Jsoup.connect(fullUrl);
                    Document doc = null;  
                    switch (requestType){
        	            case SpiderRule.GET:  
        	                doc = conn.timeout(100000).get();  
        	                break;  
        	            case SpiderRule.POST:  
        	                doc = conn.timeout(100000).post();  
        	                break;
                    }
          
                    //处理返回数据  
                    Elements results = new Elements();  
                    switch (type)  
                    {  
                    case SpiderRule.CLASS:  
                        results = doc.getElementsByClass(resultTagName);  
                        break;  
                    case SpiderRule.ID:  
                        Element result = doc.getElementById(resultTagName);  
                        results.add(result);  
                        break;  
                    case SpiderRule.SELECTION:  
                        results = doc.select(resultTagName);  
                        break;  
                    default:  
                        //当resultTagName为空时默认去body标签  
                        if (StringUtils.isEmpty(resultTagName)){  
                            results = doc.getElementsByTag("body");  
                        }  
                    }
                    
                    Elements h1s = doc.getElementsByClass("l-title");
                    
                    for(Element h1 : h1s){
                    	infoMap.put("不动产名称", h1.html());
                    }
                    
                    String infoName = "";
                    boolean getAddressAready = false;
                    for (Element result : results){
                        Elements ps = result.getElementsByTag("p");
          
                        for (Element p : ps){
                        	if( !getAddressAready ){
                        		List<Node> pNodes = p.childNodes();
                        		boolean truePNode = false;
                        		for( Node node : pNodes ){
                        			if( node.toString().contains("地&nbsp;址") ){
                        				infoName = "地址";
                        				truePNode = true;
                        			}
                        		}
                        		if( truePNode && pNodes.size() > 1){
                        			infoMap.put(infoName, pNodes.get(1).toString());
                        		}
                        		getAddressAready = true;
                        	}
                        	
                        	Elements spans = p.getElementsByTag("span");
                        	for( Element span : spans ){
                        		List<Node> nodes = span.childNodes();
                        		boolean trueNode = false;
                        		for( Node node : nodes ){
                        			if( node.toString().contains("客梯数") ){
                        				infoName = "客梯数";
                        				trueNode = true;
                        			}
                        			if( node.toString().contains("货梯数") ){
                        				infoName = "货梯数";
                        				trueNode = true;
                        			}
                        			if( node.toString().contains("电梯品牌") ){
                        				infoName = "电梯品牌";
                        				trueNode = true;
                        			}
                        			if( node.toString().contains("开发商") ){
                        				infoName = "开发商";
                        				trueNode = true;
                        			}
                        			if( node.toString().contains("物业管理公司") ){
                        				infoName = "物业管理公司";
                        				trueNode = true;
                        			}
                        			if( node.toString().contains("竣工年月") ){
                        				infoName = "竣工年月";
                        				trueNode = true;
                        			}
                        			if( node.toString().contains("总建筑面积") ){
                        				infoName = "总建筑面积";
                        				trueNode = true;
                        			}
                        		}
                        		if( trueNode && nodes.size() > 1){
                        			infoMap.put(infoName, nodes.get(1).toString());
                        		}
                        	}
                        }  
                    }
                System.out.println("[infoMap]"+infoMap);
                allHouseInfo.add(infoMap);
            	}catch(Exception e){
            		System.out.println("地址["+fullUrl+"]获取信息错误,"+e.getMessage());
            	}
            }
        } catch (Exception e){
            e.printStackTrace();  
        }
        return allHouseInfo;  
    }  
  
    /** 
     * 对传入的参数进行必要的校验 
     */  
    private static void validateRule(SpiderRule rule)  
    {  
        String url = rule.getUrl();  
        if (StringUtils.isEmpty(url))  
        {  
            throw new RuleException("url不能为空！");  
        }  
        if (!url.startsWith("http://"))  
        {  
            throw new RuleException("url的格式不正确！");  
        }  
  
        if (rule.getParams() != null && rule.getValues() != null)  
        {  
            if (rule.getParams().length != rule.getValues().length)  
            {  
                throw new RuleException("参数的键值对个数不匹配！");  
            }  
        }  
  
    } 
}
