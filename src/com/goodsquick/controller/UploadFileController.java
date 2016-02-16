package com.goodsquick.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.goodsquick.model.GoodsOrdinaryHouse;
import com.goodsquick.service.OrdinaryHouseService;
import com.goodsquick.utils.ExcelUtils;
import com.goodsquick.utils.GoodsQuickAttributes;
import com.goodsquick.utils.GoodsQuickUtils;

@Controller
public class UploadFileController {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	@Qualifier("ordinaryHouseService")
	private OrdinaryHouseService ordinaryHouseService;

	@RequestMapping("/uploadSource")
    public ModelAndView uploadSource(HttpServletRequest request){
        ModelAndView view = new ModelAndView();
        try {
        	view.addObject("opened", ",system,");
			view.addObject("actived", ",uploadSource,");
		} catch (Exception e) {
			
		}
        
        view.setViewName("sys/uploadSource");
        return view;
    }

	@RequestMapping("/uploadHouseFile")
    public String uploadHouseFile(HttpServletRequest request){
    	try{
            List<String> houseInfoHeaders = new ArrayList<String>();
            houseInfoHeaders.add("大楼名称");//tbl_goods_ordinary_house.building_name
            houseInfoHeaders.add("开发商");//tbl_goods_ordinary_house.company
            houseInfoHeaders.add("大楼地址");//tbl_goods_ordinary_house.location
            houseInfoHeaders.add("物业管理公司");//tbl_goods_ordinary_house.property_name
            houseInfoHeaders.add("竣工年月");//tbl_goods_ordinary_house.start_year / start_month
            houseInfoHeaders.add("总建筑面积");//tbl_goods_ordinary_house.floor_space
            houseInfoHeaders.add("电梯品牌");//tbl_goods_house_device.brand_name
            houseInfoHeaders.add("电梯数");//tbl_goods_house_device.device_num
            
            long begin = System.currentTimeMillis();
            List<GoodsOrdinaryHouse> allInfos = ExcelUtils.getHouseFromFile(GoodsQuickUtils.loadFile(request), houseInfoHeaders);
            long end = System.currentTimeMillis();
            logger.info("all item size is " + allInfos.size() + ", spend time " + (end - begin) + " ms");
            
            ordinaryHouseService.saveOrdinaryHousesFromFile(allInfos);
            
            request.getSession().setAttribute(GoodsQuickAttributes.UPLOAD_FILE_MESSAGE, GoodsQuickAttributes.RETURNED_MESSAGE_0);
        }catch(Exception e){
            logger.error("fail to upload the file,",e);
            request.getSession().setAttribute(GoodsQuickAttributes.UPLOAD_FILE_MESSAGE, (null==e.getMessage()||"".equalsIgnoreCase(e.getMessage()))?GoodsQuickAttributes.RETURNED_MESSAGE_1:e.getMessage());
        }
        request.getSession().setAttribute(GoodsQuickAttributes.MESSAGE_AREA_ID, "uploadHouse_div");
        return "redirect:uploadSource";
    }

	@ResponseBody
	@RequestMapping("/uploadSourceFile")
	public Map<String, Object> uploadSourceFile(HttpServletRequest request){
		Map<String, Object> categoryMap = new HashMap<String, Object>();
		return categoryMap;
	}
	
	/**
	 * 上传不动产资料
	 * @param request
	 * @return Map<String, Object>
	 */
	@ResponseBody
	@RequestMapping("/uploadHouseSourceFile")
	public Map<String, Object> uploadHouseSourceFile(HttpServletRequest request){
		Map<String, Object> categoryMap = new HashMap<String, Object>();
		String repositoryCode = (String)request.getSession().getAttribute(GoodsQuickAttributes.WEB_SESSION_REPOSITORY_CODE);
		String sourceFile12 = request.getParameter("sourceFile");
		String uploadFile = loadFile(request);
		
		File sourceFile = new File(uploadFile);
		
		String fileName = uploadFile.substring(uploadFile.lastIndexOf('\\'));
		
		File targetPath = new File(GoodsQuickAttributes.UPLOAD_FILE_PATH+repositoryCode);
		File targetFile = new File(GoodsQuickAttributes.UPLOAD_FILE_PATH+repositoryCode+"/"+fileName);
		
		if( !targetPath.exists() ){
			targetPath.mkdirs();
		}
		
		try {
			FileUtils.copyFile(sourceFile, targetFile);
		} catch (Exception e) {
			logger.error("file not found when upload house source file");
		}
		
		return categoryMap;
	}
	
	private String loadFile(HttpServletRequest request){
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
    			logger.info(item.getFieldName());
    			logger.info(item.getString());
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
    		logger.error("fail to load the file");
    	}
    	logger.info(String.format("loadFile... the file name is %s", fileName));
    	return savePath+fileName;
    }
}
