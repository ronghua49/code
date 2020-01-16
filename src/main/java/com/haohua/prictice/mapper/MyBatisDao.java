package com.haohua.prictice.mapper;


import java.io.Serializable;
import java.util.List;
import java.util.Map;


/**
 * MyBatis基础dao
 * 

 * @author gsh456

 * @date 2018年4月19日
 */
public interface MyBatisDao<PK extends Serializable, T> {
	
	public abstract int add(T paramT);

	public abstract int update(T paramT);
	
	public T findById(Long id);
    /**
     * 查询实体对象
     * @param map	通用查询对象
     * @return				分页结果
     */
    List<T> query(Map<String, Object> map);
    
    Map<String,Object> summary(Map<String, Object> map);
}
