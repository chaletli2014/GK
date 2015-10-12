package com.goodsquick.webservice;

public class HelloService {
	
	/**
     *  
     */  
    public String sayHello() {  
        return "Hello";  
    }  
  
    /** 
     * @param name 
     * @return 返回加上名称的欢迎词 
     */  
    public String sayHelloToPerson(String name) {  
        if (name == null || name.equals("")) {  
            name = "nobody";  
        }  
        return "Hello " + name;  
    }
}
