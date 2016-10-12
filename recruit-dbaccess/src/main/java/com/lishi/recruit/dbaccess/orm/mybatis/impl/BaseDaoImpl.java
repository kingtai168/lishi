package com.lishi.recruit.dbaccess.orm.mybatis.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.util.Assert;

import com.lishi.recruit.basecomponent.exception.DaoException;
import com.lishi.recruit.basecomponent.utils.BeanUtils;
import com.lishi.recruit.basecomponent.utils.ReflectionUtils;
import com.lishi.recruit.dbaccess.orm.mybatis.IBaseDao;
import com.lishi.recruit.dbaccess.orm.mybatis.constants.MapperId;
import com.lishi.recruit.dbaccess.orm.mybatis.constants.Message;

public class BaseDaoImpl<T> implements IBaseDao<T> {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	private final String SQLNAME_SEPARATOR = ".";
	public String sqlName;
	
	@Autowired(required = true)
	public SqlSession sqlSessionTemplate;
	
	public void setSqlSessionTemplate(SqlSession sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	@Override
	@Deprecated
	public void create(T t) throws Exception {
		// TODO
//		sqlSessionTemplateASS.insert("create", sqlGenerator.sql_create(t, currentColumnFieldNames));
	}

	@Override
	@Deprecated
	public void createOfBatch(List<T> tList) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(T entity) throws Exception {
		Assert.notNull(entity);
		try {
			/*if (StringUtils.isBlank(entity.getId()))
				entity.setId(generateId());*/
			sqlSessionTemplate.insert(getSqlName(MapperId.SQL_INSERT), entity);
		} catch (Exception e) {
			throw new DaoException(String.format(Message.INSERT_FAILED, getSqlName(MapperId.SQL_INSERT)), e);
		}
		
	}

	@Override
	public void saveOfBatch(List<T> entityList) throws Exception {
		if (entityList == null || entityList.isEmpty())
			return;
		for (T entity : entityList) {
			this.save(entity);
		}
	}
	
	@Override
	public Integer delete(T entity) {
		Assert.notNull(entity);
		try {
			
			BeanMap map = BeanMap.create(entity);
			map.setBean(entity);
			
			Map<String, Object> params = BeanUtils.toMap(entity);
			return sqlSessionTemplate.delete(getSqlName(MapperId.SQL_DELETE), params);
		} catch (Exception e) {
			throw new DaoException(String.format(Message.DELETE_FAILED, getSqlName(MapperId.SQL_DELETE)), e);
		}
	}

	@Override
	public Integer deleteById(String id) throws Exception {
		Assert.notNull(id);
		try {
			return sqlSessionTemplate.delete(getSqlName(MapperId.SQL_DELETE_BY_ID), id);
		} catch (Exception e) {
			throw new DaoException(String.format(Message.DELETE_BYID_FAILED, getSqlName(MapperId.SQL_DELETE_BY_ID)), e);
		}
	}
	
	@Override
	@Deprecated
	public Integer deleteOfBatch(List<String> ids) throws Exception {
		Assert.notNull(ids);
		//TODO:
		return null;
		
	}
	
	@Override
	public Integer deleteAll() throws Exception {
		try {
			return sqlSessionTemplate.delete(getSqlName(MapperId.SQL_DELETE));
		} catch (Exception e) {
			throw new DaoException(String.format(Message.DELETE_ALL_FAILED, getSqlName(MapperId.SQL_DELETE)), e);
		}
	}

	@Override
	public T findOneById(String id) throws Exception {
		Assert.notNull(id);
		try {
			return sqlSessionTemplate.selectOne(getSqlName(MapperId.SQL_SELECT_BY_ID), id);
		} catch (Exception e) {
			throw new DaoException(String.format(Message.SELECT_BYID_FAILED, getSqlName(MapperId.SQL_SELECT_BY_ID)), e);
		}
	}
	
	@Override
	public List<T> findList(List<T> entites) throws Exception {
		Assert.notNull(entites);
		try {
			Map<String, Object> params = BeanUtils.toMap(entites);
			return sqlSessionTemplate.selectList(getSqlName(MapperId.SQL_SELECT), params);
		} catch (Exception e) {
			throw new DaoException(String.format(Message.SELECT_LIST_FAILED,getSqlName(MapperId.SQL_SELECT)), e);
		}
	}

	@Override
	public List<T> findAll() throws Exception {
		try {
			return sqlSessionTemplate.selectList(getSqlName(MapperId.SQL_SELECT));
		} catch (Exception e) {
			throw new DaoException(String.format(Message.DELETE_ALL_FAILED, getSqlName(MapperId.SQL_SELECT)), e);
		}
	}

	@Override
	public Integer update(T entity) throws Exception {
		Assert.notNull(entity);
		try {
			return sqlSessionTemplate.update(getSqlName(MapperId.SQL_UPDATE_BY_ID), entity);
		} catch (Exception e) {
			throw new DaoException(String.format(Message.UPDATE_BYID_FAILED, getSqlName(MapperId.SQL_UPDATE_BY_ID)), e);
		}
		
	}

	@Override
	public void updateOfBatch(List<T> entities) throws Exception {
		if (entities == null || entities.isEmpty())
			return;
		for (T entity : entities) {
			this.updateByIdSelective(entity);
		}
		
	}

	@Override
	public Integer updateByIdSelective(T entity) throws Exception {
		Assert.notNull(entity);
		try {
			return sqlSessionTemplate.update(getSqlName(MapperId.SQL_UPDATE_BY_ID_SELECTIVE), entity);
		} catch (Exception e) {
			throw new DaoException(String.format(Message.UPDATE_SELECTIVE_FAILED, getSqlName(MapperId.SQL_UPDATE_BY_ID_SELECTIVE)),e);
		}
	}
	

	
	public String getSqlName(String sqlName) {
		return getDefaultSqlNamespace(this.getClass()) + SQLNAME_SEPARATOR + sqlName;
	}

	public void setSqlName(String sqlName) {
		this.sqlName = sqlName;
	}

	@SuppressWarnings("rawtypes")
	protected static String getDefaultSqlNamespace(Class clazz) {
		Class<?> genericClass = ReflectionUtils.getSuperGenericType(clazz);
		return genericClass == null ? null : genericClass.getName();
	}

	@Override
	public Long findAllCount() throws Exception  {
		try {
			return sqlSessionTemplate.selectOne(getSqlName(MapperId.SQL_SELECT_COUNT));
		} catch (Exception e) {
			throw new DaoException(String.format(Message.SELECT_COUNT_FAILED, getSqlName(MapperId.SQL_SELECT_COUNT)), e);
		}
	}

	@Override
	public Long findCount(T query) throws Exception  {
		try {
			Map<String, Object> params = BeanUtils.toMap(query);
			return sqlSessionTemplate.selectOne(getSqlName(MapperId.SQL_SELECT_COUNT), params);
		} catch (Exception e) {
			throw new DaoException(String.format(Message.SELECT_COUNT_FAILED, getSqlName(MapperId.SQL_SELECT_COUNT)), e);
		}
	}

}
