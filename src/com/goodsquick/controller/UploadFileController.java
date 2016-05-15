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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.goodsquick.model.GoodsHouseFile;
import com.goodsquick.model.GoodsOrdinaryHouse;
import com.goodsquick.model.GoodsProductObj;
import com.goodsquick.model.GoodsProductSource;
import com.goodsquick.model.WebUserInfo;
import com.goodsquick.service.GoodsSourceFileService;
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
	
	@Autowired
	@Qualifier("goodsSourceFileService")
	private GoodsSourceFileService goodsSourceFileService;

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
	public Map<String, Object> uploadHouseSourceFile(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request){
		Map<String, Object> categoryMap = new HashMap<String, Object>();
		// 文件是否为空
		if (file.getSize() == 0 || file.isEmpty()) {
			categoryMap.put("resultMsg", "上传文件为空");
			return categoryMap;
		}
		// 文件是否过大
		if (file.getSize() > 10240000) {
			categoryMap.put("resultMsg", "上传文件过大");
			return categoryMap;
		}
		
		String repositoryCode = (String)request.getSession().getAttribute(GoodsQuickAttributes.WEB_SESSION_REPOSITORY_CODE);
		WebUserInfo currentUser = (WebUserInfo)request.getSession().getAttribute(GoodsQuickAttributes.WEB_LOGIN_USER);
		String targetPathStr = request.getSession().getServletContext().getRealPath("/")+GoodsQuickAttributes.UPLOAD_FILE_PATH+repositoryCode;
		String downloadPath = request.getSession().getServletContext().getContextPath()+GoodsQuickAttributes.UPLOAD_FILE_PATH+repositoryCode;
		try{
			File targetPath = new File(targetPathStr);
			String ori_fileName = file.getOriginalFilename();
			
			if( !targetPath.exists() ){
				targetPath.mkdirs();
			}
			
			String targetFileStr = targetPathStr +"/"+ ori_fileName;
			String downloadFile = downloadPath + "/"+ ori_fileName;
			File targetFile = new File(targetFileStr);
			
			GoodsHouseFile houseFile = new GoodsHouseFile();
			houseFile.setFileName(ori_fileName.substring(0,ori_fileName.lastIndexOf('.')));
			houseFile.setFileType(ori_fileName.substring(ori_fileName.lastIndexOf('.')+1));
			houseFile.setFilePath(downloadFile);
			houseFile.setRepositoryCode(repositoryCode);
			houseFile.setTargetFile(targetFile);
			houseFile.setIsMain("0");
			houseFile.setSourceFile(file);
			
			goodsSourceFileService.saveOrUpdateGoodsHouseFile(houseFile, currentUser);
			categoryMap.put("existsFiles", goodsSourceFileService.getGoodsHouseFileByRepositoryCode(repositoryCode));
			
		}catch(Exception e){
			logger.error("fail to upload the file,",e);
			categoryMap.put("resultMsg", "上传文件失败："+e.getMessage());
		}
		return categoryMap;
	}
	/**
	 * 上传产品资料
	 * @param request
	 * @return Map<String, Object>
	 */
	@ResponseBody
	@RequestMapping("/uploadProductSource")
	public Map<String, Object> uploadProductSource(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request){
		Map<String, Object> categoryMap = new HashMap<String, Object>();
		// 文件是否为空
		if (file.getSize() == 0 || file.isEmpty()) {
			categoryMap.put("resultMsg", "上传文件为空");
			return categoryMap;
		}
		// 文件是否过大
		if (file.getSize() > 10240000) {
			categoryMap.put("resultMsg", "上传文件过大");
			return categoryMap;
		}
		
		try{
			String repositoryCode = (String)request.getSession().getAttribute(GoodsQuickAttributes.WEB_SESSION_REPOSITORY_CODE);
			WebUserInfo currentUser = (WebUserInfo)request.getSession().getAttribute(GoodsQuickAttributes.WEB_LOGIN_USER);
			GoodsProductObj currentProductObj = (GoodsProductObj)request.getSession().getAttribute("currentProductObj");
			if( null == currentProductObj ){
				categoryMap.put("resultMsg", "请先保存产品再上传资料文件");
			}else{
				long goodsProductObjId = currentProductObj.getId();
				String targetPathStr = request.getSession().getServletContext().getRealPath("/")+GoodsQuickAttributes.UPLOAD_FILE_PATH+repositoryCode+"/"+goodsProductObjId;
				String downloadPath = request.getSession().getServletContext().getContextPath()+GoodsQuickAttributes.UPLOAD_FILE_PATH+repositoryCode+"/"+goodsProductObjId;
				
				File targetPath = new File(targetPathStr);
				String ori_fileName = file.getOriginalFilename();
				
				if( !targetPath.exists() ){
					targetPath.mkdirs();
				}
				
				String targetFileStr = targetPathStr +"/"+ ori_fileName;
				String downloadFile = downloadPath + "/"+ ori_fileName;
				File targetFile = new File(targetFileStr);
				
				GoodsProductSource productSource = new GoodsProductSource();
				productSource.setFileName(ori_fileName.substring(0,ori_fileName.lastIndexOf('.')));
				productSource.setFileType(ori_fileName.substring(ori_fileName.lastIndexOf('.')+1));
				productSource.setFilePath(downloadFile);
				productSource.setRepositoryCode(repositoryCode);
				productSource.setGoodsProductObjId(goodsProductObjId);
				productSource.setTargetFile(targetFile);
				productSource.setIsMain("0");
				productSource.setSourceFile(file);
				
				goodsSourceFileService.saveOrUpdateGoodsProductSource(productSource, currentUser);
				categoryMap.put("existsFiles", goodsSourceFileService.getGoodsProductSourceByRCAndProductId(repositoryCode, goodsProductObjId));
			}
		}catch(Exception e){
			logger.error("fail to upload the product source,",e);
			categoryMap.put("resultMsg", "上传文件失败："+e.getMessage());
		}
		return categoryMap;
	}
}
