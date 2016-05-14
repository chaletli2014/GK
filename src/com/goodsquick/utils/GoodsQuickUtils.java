package com.goodsquick.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class GoodsQuickUtils {

	public static String loadFile(HttpServletRequest request){
    	String savePath =request.getSession().getServletContext().getRealPath("/")+"uploadfiles\\";  
    	String fileName = "";
    	try{
    		request.setCharacterEncoding("UTF-8");
    		File f1 = new File(savePath);
    		if (!f1.exists()) {
    			f1.mkdirs();  
    		}  
    		DiskFileItemFactory fac = new DiskFileItemFactory();
    		ServletFileUpload upload = new ServletFileUpload(fac);
    		
    		upload.setHeaderEncoding("utf-8");  
    		List fileList = null;
    		try {  
    			fileList = upload.parseRequest(request);  
    		} catch (FileUploadException ex) {
    			ex.printStackTrace();
    		}
    		
    		FileItem item=(FileItem)fileList.get(0);
    		
    		if( item.isFormField() ){
    		}else{
    			fileName = item.getName();
    			if( fileName.indexOf("\\") > 0 ){
    			    fileName = fileName.substring(fileName.lastIndexOf("\\")+1, fileName.length());
    			}
    			FileOutputStream fos = new FileOutputStream(savePath+fileName);
    			InputStream in = item.getInputStream();
    			byte buffer[] = new byte[1024];
    			int len = 0;
    			while((len=in.read(buffer))>0){
    				fos.write(buffer,0,len);
    			}
    			in.close();
    			fos.close();
    		}
    	}catch(Exception e){
    	}
    	return savePath+fileName;
    }
	
	public static Integer parseIntegerFromString(String input){
		try{
			return Integer.valueOf(input);
		}catch(NumberFormatException e){
			return 0;
		}
	}
	
	public static Double parseDoubleFromString(String input){
		try{
			return Double.valueOf(input);
		}catch(NumberFormatException e){
			return 0.00;
		}
	}
}
