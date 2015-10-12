package com.goodsquick.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.goodsquick.dao.GoodsChartDAO;
import com.goodsquick.model.GoodsChartObj1;
import com.goodsquick.model.GoodsChartObj2;
import com.goodsquick.model.GoodsChartObj3;

@Service("reportService")
public class ReportServiceImpl implements ReportService {

	@Autowired
	@Qualifier("goodsChartDAO")
	private GoodsChartDAO goodsChartDAO;
	
	@Override
	public List<GoodsChartObj1> getHouseVolumePerYear() throws Exception {
		try{
			return goodsChartDAO.getHouseVolumePerYear();
		} catch(EmptyResultDataAccessException erd){
            return Collections.emptyList();
        } catch(Exception e){
            return Collections.emptyList();
        }
	}

	@Override
	public List<GoodsChartObj1> getliftArea() throws Exception {
		try{
			List<GoodsChartObj1> liftNums = goodsChartDAO.getliftArea();
			double totalNum = 0;
			for( GoodsChartObj1 liftNum : liftNums ){
				totalNum = totalNum + liftNum.getyValue();
			}
			
			for( GoodsChartObj1 liftNum : liftNums ){
				BigDecimal b = new BigDecimal(liftNum.getyValue()/totalNum);
				liftNum.setyValue2(b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue()*100);
			}
			return liftNums;
		} catch(EmptyResultDataAccessException erd){
            return Collections.emptyList();
        } catch(Exception e){
            return Collections.emptyList();
        }
	}

	@Override
	public List<GoodsChartObj1> getliftBrandDetail(String city) throws Exception {
		try{
			List<GoodsChartObj1> liftNums = goodsChartDAO.getliftBrandDetail(city);
			double totalNum = 0;
			for( GoodsChartObj1 liftNum : liftNums ){
				totalNum = totalNum + liftNum.getyValue();
			}
			
			for( GoodsChartObj1 liftNum : liftNums ){
				BigDecimal b = new BigDecimal(liftNum.getyValue()/totalNum);
				liftNum.setyValue2(b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue()*100);
			}
			return liftNums;
		} catch(EmptyResultDataAccessException erd){
            return Collections.emptyList();
        } catch(Exception e){
            return Collections.emptyList();
        }
	}

	@Override
	public List<GoodsChartObj3> getliftSalesDetail() throws Exception {
		try{
			List<GoodsChartObj3> result = new ArrayList<GoodsChartObj3>();
			List<GoodsChartObj2> salesDetails = goodsChartDAO.getliftSalesDetail();
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			int lastYear = cal.get(Calendar.YEAR);
			for( int i = 1; i <= 10; i++ ){
				GoodsChartObj3 yearObj = new GoodsChartObj3();
				String year = String.valueOf(lastYear-i);
				yearObj.setxName(year);
				for( GoodsChartObj2 salesDetail : salesDetails ){
					if( salesDetail.getxName().equalsIgnoreCase(year) ){
					}
				}
			}
			return result;
		} catch(EmptyResultDataAccessException erd){
            return Collections.emptyList();
        } catch(Exception e){
            return Collections.emptyList();
        }
	}
}
