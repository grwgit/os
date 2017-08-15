package com.johe.dao;

import java.sql.ResultSet;
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 * 本PreSQL类的对象内部封装了一个PreparedStatement对象
 * 执行任何方法时都使用的是内部封装的PreparedStatement对象
 * @author Administrator
 *
 */
public interface PreSQL {
	
	/**
	 * 执行查询sql，返回ResultSet
	 * @param sql
	 * @param args
	 * @return
	 * @throws DaoException
	 */
	public ResultSet query(Object... args ) throws DaoException;
	
	
	/**
	 * 执行更新sql，返回影响行数
	 * @param sql
	 * @param args
	 * @return
	 * @throws DaoException
	 */
	public int update(Object... args )  throws DaoException;
	
	
	/**
	 * 
	 * @param mapper
	 * @param sql
	 * @param args
	 * @return
	 * @throws DaoException
	 */
	public <T> List<T> queryList(RowMapper<T> mapper,Object... args ) throws DaoException;
	
	/**
	 * 
	 * @param mapper
	 * @param sql
	 * @param args
	 * @return
	 * @throws DaoException
	 */
	public <T> T queryUnique(RowMapper<T> mapper,Object... args ) throws DaoException;
	
	/**
	 * 执行单列的select sql，查询获取一个String对象
	 * @param sql
	 * @param args
	 * @return
	 * @throws DaoException
	 */
	public String queryString(Object... args )throws DaoException;
	
	/**
	 * 执行单列的select sql，查询获取String对象的List集合
	 * @param sql
	 * @param args
	 * @return
	 * @throws DaoException
	 */
	public List<String> queryStringList(Object... args ) throws DaoException;
	
	public Integer queryInteger(Object... args ) throws DaoException;
	
	public List<Integer> queryIntegerList(Object... args ) throws DaoException;
	
	public Long queryLong(Object... args ) throws DaoException;
	
	public List<Long> queryLongList(Object... args ) throws DaoException;
	
	
	public Float queryFloat(Object... args ) throws DaoException;
	
	public List<Float> queryFloatList(Object... args ) throws DaoException;
	
	public Double queryDouble(Object... args ) throws DaoException;
	
	public List<Double> queryDoubleList(Object... args ) throws DaoException;
	
	public Date queryDate(Object... args ) throws DaoException;
	
	public List<Date> queryDateList(Object... args ) throws DaoException;
	
	public Map<String,Object> queryMap(Object... args ) throws DaoException;
	
	public List<Map<String,Object>> queryMapList(Object... args ) throws DaoException;
	
	/**
	 * 获取一行数据
	 * @param sql
	 * @param args
	 * @return
	 * @throws DaoException
	 */
	public Object[] queryArray(Object... args ) throws DaoException;
	
	public List<Object[]> queryArrayList(Object... args ) throws DaoException;
	
	public <T> T queryUniqueValue(Class<T> valueClass,Object... args);
	
	public <T> List<T> queryValueList(Class<T> valueClass,Object... args);

	public <T> T queryBean(Class<T> beanClass,Object... args);
	
	public <T> List<T> queryBeanList(Class<T> beanClass,Object... args);
	
}
