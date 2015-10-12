package com.goodsquick.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.goodsquick.utils.DataBean;

public class BaseDAOImpl {
	@Autowired
	@Qualifier("dataBean")
	protected DataBean dataBean;
}
