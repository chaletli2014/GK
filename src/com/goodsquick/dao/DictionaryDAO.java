package com.goodsquick.dao;

import java.util.List;

import com.goodsquick.model.GoodsDictionary;
import com.goodsquick.model.GoodsDictionaryType;

public interface DictionaryDAO {

	public List<GoodsDictionaryType> getAllDictionaryType() throws Exception;
	
	public List<GoodsDictionary> getAllDictionary() throws Exception;
	
	public List<GoodsDictionary> getDictionaryByType(String type) throws Exception;
	
	public boolean checkIfDictionaryTypeCodeOrNameExists(String typeCode, String name) throws Exception;
	
	public boolean checkIfDictionaryExists(String typeCode, String name) throws Exception;
	
	public void saveDictionary(GoodsDictionary dictionary) throws Exception;
	
	public void updateDictionary(GoodsDictionary dictionary) throws Exception;
	
	public void deleteDictionary(GoodsDictionary dictionary) throws Exception;
	
	public void saveDictionaryType(GoodsDictionaryType dictionaryType) throws Exception;
	
	public void updateDictionaryType(GoodsDictionaryType dictionaryType) throws Exception;
	
	public void deleteDictionaryType(GoodsDictionaryType dictionaryType) throws Exception;
}
