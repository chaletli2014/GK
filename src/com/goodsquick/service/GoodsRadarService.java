package com.goodsquick.service;

import java.util.List;

import com.goodsquick.model.GoodsCompanyInfo;

public interface GoodsRadarService {
	/**
	 * 根据物库编码获取电梯品牌，然后获取服务商名称
	 * @param repositoryCode 物库编码
	 * @return List<GoodsCompanyInfo> 服务商集合
	 * @throws Exception
	 */
	public List<GoodsCompanyInfo> getCompanyInfoByRepositoryCode(String repositoryCode) throws Exception;
}
