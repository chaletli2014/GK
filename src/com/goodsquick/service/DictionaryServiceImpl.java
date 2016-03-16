package com.goodsquick.service;

import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.goodsquick.dao.DictionaryDAO;
import com.goodsquick.model.GoodsDictionary;
import com.goodsquick.model.GoodsDictionaryType;

@Service("dictionaryService")
public class DictionaryServiceImpl implements DictionaryService {

    Logger logger = Logger.getLogger(DictionaryServiceImpl.class);
    
	@Autowired
	@Qualifier("dictionaryDAO")
	private DictionaryDAO dictionaryDAO;
	
	@Override
	public List<GoodsDictionaryType> getAllDictionaryType() throws Exception {
		try{
			return dictionaryDAO.getAllDictionaryType();
		} catch(EmptyResultDataAccessException erd){
            return Collections.emptyList();
        } catch(Exception e){
            logger.error("fail to get the dictionary types,",e);
            return Collections.emptyList();
        }
	}

	@Override
	public List<GoodsDictionary> getAllDictionary() throws Exception {
		try{
			return dictionaryDAO.getAllDictionary();
		} catch(EmptyResultDataAccessException erd){
            return Collections.emptyList();
        } catch(Exception e){
            logger.error("fail to get the dictionary,",e);
            return Collections.emptyList();
        }
	}

	@Override
	public List<GoodsDictionary> getDictionaryByType(String type)
			throws Exception {
		try{
			return dictionaryDAO.getDictionaryByType(type);
		} catch(EmptyResultDataAccessException erd){
            return Collections.emptyList();
        } catch(Exception e){
            logger.error(String.format("fail to get the dictionary by type %s,",type),e);
            return Collections.emptyList();
        }
	}
	
	@Override
	public List<GoodsDictionary> getDictionaryByTypeLike(String type)
			throws Exception {
		try{
			return dictionaryDAO.getDictionaryByTypeLike(type);
		} catch(EmptyResultDataAccessException erd){
			return Collections.emptyList();
		} catch(Exception e){
			logger.error(String.format("fail to get the dictionary by type %s,",type),e);
			return Collections.emptyList();
		}
	}

	@Override
	public boolean checkIfDictionaryTypeCodeOrNameExists(String typeCode, String name)
			throws Exception {
		return dictionaryDAO.checkIfDictionaryTypeCodeOrNameExists(typeCode, name);
	}

	@Override
	public boolean checkIfDictionaryExists(String typeCode, String name)
			throws Exception {
		return dictionaryDAO.checkIfDictionaryExists(typeCode, name);
	}

	@Override
	public void saveDictionary(GoodsDictionary dictionary) throws Exception {
		dictionaryDAO.saveDictionary(dictionary);
	}

	@Override
	public void updateDictionary(GoodsDictionary dictionary) throws Exception {
		dictionaryDAO.updateDictionary(dictionary);
	}

	@Override
	public void deleteDictionary(GoodsDictionary dictionary) throws Exception {
		dictionaryDAO.deleteDictionary(dictionary);
	}

	@Override
	public void saveDictionaryType(GoodsDictionaryType dictionaryType)
			throws Exception {
		dictionaryDAO.saveDictionaryType(dictionaryType);
	}

	@Override
	public void updateDictionaryType(GoodsDictionaryType dictionaryType)
			throws Exception {
		dictionaryDAO.updateDictionaryType(dictionaryType);

	}

	@Override
	public void deleteDictionaryType(GoodsDictionaryType dictionaryType)
			throws Exception {
		dictionaryDAO.deleteDictionaryType(dictionaryType);
	}

	@Override
	public GoodsDictionary getDictionaryByCode(String code, String typeCode) throws Exception {
		try{
			return dictionaryDAO.getDictionaryByCode(code, typeCode);
		} catch(EmptyResultDataAccessException erd){
            return new GoodsDictionary();
        } catch(Exception e){
            logger.error("fail to get the dictionary,",e);
            return new GoodsDictionary();
        }
	}

}
