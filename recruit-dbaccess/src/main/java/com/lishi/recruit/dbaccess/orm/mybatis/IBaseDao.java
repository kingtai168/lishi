package com.lishi.recruit.dbaccess.orm.mybatis;

import java.util.List;

public interface IBaseDao<T> {
	
	/**
     * 新增(不会将序列生成的ID,注入)
     * 效率较save(T t)高
     * @param t
     */
    public void create(T t) throws Exception;
     
    /**
     * 批量新增(不会将序列生成的ID,注入)
     * 效率较saveOfBatch(List<T> entities)高
     * @param entities
     */
    public void createOfBatch(List<T> entities) throws Exception;
     
    /**
     * 新增(会将序列生成的ID,注入)
     * @param t
     */
    public void save(T t) throws Exception;
     
    /**
     * 批量新增(会将序列生成的ID,注入)
     * @param entities
     */
    public void saveOfBatch(List<T> entities) throws Exception;

	/**
	 * 删除对象
	 * @param entity 要删除的实体对象，不能为null
	 * @return int 受影响结果数
	 * @date 2014年3月3日下午5:47:47
	 */
	public Integer delete(T entity);
    
    /**
     * 根据ID进行删除
     * @param id
     */
    public Integer deleteById(String id) throws Exception;
    

     
    /**
     * 根据ids进行批量删除
     * @param ids
     */
    public Integer deleteOfBatch(List<String> ids) throws Exception;
     
    /**
     * 删除全部
     * @throws Exception
     */
    public Integer deleteAll() throws Exception;
    
    /**
     * 逻辑删除
     */
    //TODO:
     
    /**
     * 更新,字段为空，则不进行更新
     * @param t
     */
    public Integer update(T t) throws Exception;
     
    /**
     * 批量更新
     * @param entities
     */
    public void updateOfBatch(List<T> entities) throws Exception;
     
    /**
     * 根据ID获取对象
     * @param id
     * @return
     */
    public T findOneById(String id) throws Exception;
    
    /**
     * 获取所有的对象
     * @return
     */
    public List<T> findList(List<T> entites) throws Exception;
     
    /**
     * 获取所有的对象
     * @return
     */
    public List<T> findAll() throws Exception;
     
    
	/**
	 * 获取总记录数
	 * @return long 记录总数
	 * @throws Exception 
	 */
	public Long findAllCount() throws Exception;

	/**
	 * 查询记录数
	 * @param query 查询对象，如果为null，则查询对象总数
	 * @return long 记录总数
	 * @throws Exception 
	 */
	public Long findCount(T query) throws Exception;
    
	/**
	 * 更新对象中已设置的字段，未设置的字段不更新
	 * @param entity 要更新的实体对象，不能为null，切ID必须不为null
	 * @return 
	 */
	public Integer updateByIdSelective(T entity) throws Exception;
}
