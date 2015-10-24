package com.goodsquick.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.goodsquick.model.Category;
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
}
