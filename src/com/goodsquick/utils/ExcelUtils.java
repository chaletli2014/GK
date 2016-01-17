package com.goodsquick.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.goodsquick.model.GoodsHouseDevice;
import com.goodsquick.model.GoodsOrdinaryHouse;


public class ExcelUtils {
    
    private static Logger logger = Logger.getLogger(ExcelUtils.class);
    
    public static List<GoodsOrdinaryHouse> getHouseFromFile(String filePath,List<String> headers) throws Exception{
        List<GoodsOrdinaryHouse> ordinaryHouses = new ArrayList<GoodsOrdinaryHouse>();
        FileInputStream is = null;
        try{
            is = new FileInputStream(filePath);
            Workbook book = createCommonWorkbook(is);
            Sheet sheet = book.getSheetAt(0);
            
            Map<String, Integer> headerColumn = new HashMap<String, Integer>();
            
            Row row = sheet.getRow(sheet.getFirstRowNum());
            for( int i = row.getFirstCellNum(); i < row.getPhysicalNumberOfCells(); i++ ){
                if( headers.contains(row.getCell(i).toString()) ){
                    headerColumn.put(row.getCell(i).toString(), i);
                }
            }
            
            //get the data
            for( int i = sheet.getFirstRowNum() + 1; i < sheet.getPhysicalNumberOfRows(); i++ ){
                row = sheet.getRow(i);
                
                if( null == row 
                		|| null == row.getCell(headerColumn.get(headers.get(0)))){
                	break;
                }
                
                String buildingName = "";
                Cell buildingNameCell = row.getCell(headerColumn.get(headers.get(0)));
                if( null != buildingNameCell ){
                	buildingName = buildingNameCell.toString();
                }
                
                String company = "";
                Cell companyCell = row.getCell(headerColumn.get(headers.get(1)));
                if( null != companyCell ){
                	company = companyCell.toString();
                }
                
                String location = "";
                Cell locationCell = row.getCell(headerColumn.get(headers.get(2)));
                if( null != locationCell ){
                	location = locationCell.toString();
                }
                
                String propertyName = "";
                Cell propertyNameCell = row.getCell(headerColumn.get(headers.get(3)));
                if( null != propertyNameCell ){
                	propertyName = propertyNameCell.toString();
                }
                
                String startYearMonth = "";
                Cell startYearMonthCell = row.getCell(headerColumn.get(headers.get(4)));
                if( null != startYearMonthCell ){
                	startYearMonth = startYearMonthCell.toString();
                }
                
                int startYear = 0;
                int startMonth = 0;
                if( !"".equalsIgnoreCase(startYearMonth) ){
                	String[] yearMonth = startYearMonth.split("年");
                	if( yearMonth.length > 0 ){
                		startYear = Integer.parseInt(startYearMonth.split("年")[0]);
                	}
                	if( yearMonth.length > 1 ){
                		startYearMonth = startYearMonth.split("年")[1];
                		if( !"".equalsIgnoreCase(startYearMonth) ){
                			String[] months = startYearMonth.split("月");
                			if( months.length > 0 ){
                				startMonth = Integer.parseInt(startYearMonth.split("月")[0]);
                			}
                		}
                	}
                }
                
                String floorSpaceStr = "";
                Cell floorSpaceCell = row.getCell(headerColumn.get(headers.get(5)));
                if( null != floorSpaceCell ){
                	floorSpaceStr = floorSpaceCell.toString();
                }
                
                double floorSpace = 0;
                if( !"".equalsIgnoreCase(floorSpaceStr) ){
                	floorSpace = Double.parseDouble(floorSpaceStr.substring(0, floorSpaceStr.length()-2));
                }
                
                String brandName = "";
                Cell brandNameCell = row.getCell(headerColumn.get(headers.get(6)));
                if( null != brandNameCell ){
                	brandName = brandNameCell.toString();
                }
                
                String deviceNumStr = "";
                Cell deviceNumCell = row.getCell(headerColumn.get(headers.get(7)));
                deviceNumCell.setCellType(Cell.CELL_TYPE_STRING);
                if( null != deviceNumCell ){
                	deviceNumStr = deviceNumCell.toString();
                }
                int deviceNum = 0;
                if( !"".equalsIgnoreCase(deviceNumStr) ){
                	deviceNum = Integer.parseInt(deviceNumStr);
                }
                
                if( null != buildingName && !"".equalsIgnoreCase(buildingName) ){
                	GoodsOrdinaryHouse ordinaryHouse = new GoodsOrdinaryHouse();
                	ordinaryHouse.setBuildingName(buildingName);
                	ordinaryHouse.setCompany(company);
                	ordinaryHouse.setLocation(location);
                	ordinaryHouse.setPropertyName(propertyName);
                	ordinaryHouse.setStartYear(startYear);
                	ordinaryHouse.setStartMonth(startMonth);
                	ordinaryHouse.setFloorSpace(floorSpace);
                	
//                	List<GoodsHouseDevice> deviceList = new ArrayList<GoodsHouseDevice>();
//                	GoodsHouseDevice houseDevice = new GoodsHouseDevice();
//                	houseDevice.setBrandName(brandName);
//                	houseDevice.setDeviceNum(deviceNum);
//                	houseDevice.setDeviceType("lift_system");
//                	deviceList.add(houseDevice);
//                	
//                	ordinaryHouse.setHouseDevices(deviceList);
                    
                	ordinaryHouses.add(ordinaryHouse);
                }
            }
            
        }catch(Exception e){
            logger.error("fail to get ordinaryHouses from the excel file.",e);
            throw new Exception(e.getMessage());
        }finally{
            if(null!=is){
                is.close();
            }
        }
        
        return ordinaryHouses;
    }
    
    public static void saveHouseInfos2ExcelFromMap(List<Map<String,String>> houseInfos, String fileName){
    	FileOutputStream fOut = null;
        try{
        	fOut = new FileOutputStream(fileName);
            
        	HSSFWorkbook workbook = new HSSFWorkbook();
        	workbook.createSheet("HouseInfo");
        	HSSFSheet sheet = workbook.getSheetAt(0);
            
            int currentRowNum = 0;
            HSSFRow row = sheet.createRow(currentRowNum++);
            row.createCell(0, XSSFCell.CELL_TYPE_STRING).setCellValue("大楼名称");
            row.createCell(1, XSSFCell.CELL_TYPE_STRING).setCellValue("开发商");
            row.createCell(2, XSSFCell.CELL_TYPE_STRING).setCellValue("大楼地址");
            row.createCell(3, XSSFCell.CELL_TYPE_STRING).setCellValue("物业管理公司");
            row.createCell(4, XSSFCell.CELL_TYPE_STRING).setCellValue("竣工年月");
            row.createCell(5, XSSFCell.CELL_TYPE_STRING).setCellValue("总建筑面积");
            row.createCell(6, XSSFCell.CELL_TYPE_STRING).setCellValue("电梯品牌");
            row.createCell(7, XSSFCell.CELL_TYPE_STRING).setCellValue("电梯数");
            for( Map<String,String> house : houseInfos ){
            	row = sheet.createRow(currentRowNum++);
            	row.createCell(0, XSSFCell.CELL_TYPE_STRING).setCellValue(house.get("不动产名称"));
            	row.createCell(1, XSSFCell.CELL_TYPE_STRING).setCellValue(house.get("开发商"));
            	row.createCell(2, XSSFCell.CELL_TYPE_STRING).setCellValue(house.get("地址"));
            	row.createCell(3, XSSFCell.CELL_TYPE_STRING).setCellValue(house.get("物业管理公司"));
            	row.createCell(4, XSSFCell.CELL_TYPE_STRING).setCellValue(house.get("竣工年月"));
            	row.createCell(5, XSSFCell.CELL_TYPE_STRING).setCellValue(house.get("总建筑面积"));
            	row.createCell(6, XSSFCell.CELL_TYPE_STRING).setCellValue(house.get("电梯品牌"));
            	
            	int liftNum = 0;
            	try{
            		String passengerLift = house.get("客梯数");
            		String goodsLift = house.get("货梯数");
            		liftNum = Integer.parseInt(passengerLift.substring(0, passengerLift.length()-1))
            				+ Integer.parseInt(goodsLift.substring(0, goodsLift.length()-1));
            	}catch(Exception e){
            	}
            	row.createCell(7, XSSFCell.CELL_TYPE_STRING).setCellValue(liftNum);
            }
            
            workbook.write(fOut);
        }catch(Exception e){
        	e.printStackTrace();
        	logger.error("保存到excel失败,",e);
        }
    }
    
    public static Workbook createCommonWorkbook(InputStream inp) throws IOException, InvalidFormatException { 
        if (!inp.markSupported()) {
            inp = new PushbackInputStream(inp,8);
        } 
        if (POIFSFileSystem.hasPOIFSHeader(inp)) { 
            return new HSSFWorkbook(inp); 
        } 
        if (POIXMLDocument.hasOOXMLHeader(inp)) { 
            return new XSSFWorkbook(OPCPackage.open(inp)); 
        } 
        throw new IOException("不能解析的excel版本"); 
    }
    
    public static void main(String[] args){
    }
}
