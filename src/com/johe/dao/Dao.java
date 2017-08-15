package com.johe.dao;

import java.sql.ResultSet;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface Dao {
	
	/**
	 * ִ�в�ѯsql������ResultSet
	 * @param sql
	 * @param args
	 * @return
	 * @throws DaoException
	 */
	public ResultSet query(String sql,Object... args ) throws DaoException;
	
	/**
	 * ִ�и���sql������Ӱ������
	 * @param sql
	 * @param args
	 * @return
	 * @throws DaoException
	 */
	public int update(String sql,Object... args )  throws DaoException;
	
	
	/**
	 * 
	 * @param mapper
	 * @param sql
	 * @param args
	 * @return
	 * @throws DaoException
	 */
	public <T> List<T> queryList(RowMapper<T> mapper,String sql,Object... args ) throws DaoException;
	
	/**
	 * 
	 * @param mapper
	 * @param sql
	 * @param args
	 * @return
	 * @throws DaoException
	 */
	public <T> T queryUnique(RowMapper<T> mapper,String sql,Object... args ) throws DaoException;
	
	/**
	 * ִ�е��е�select sql����ѯ��ȡһ��String����
	 * @param sql
	 * @param args
	 * @return
	 * @throws DaoException
	 */
	public String queryString(String sql,Object... args )throws DaoException;
	
	/**
	 * ִ�е��е�select sql����ѯ��ȡString�����List����
	 * @param sql
	 * @param args
	 * @return
	 * @throws DaoException
	 */
	public List<String> queryStringList(String sql,Object... args ) throws DaoException;
	
	public Integer queryInteger(String sql,Object... args ) throws DaoException;
	
	public List<Integer> queryIntegerList(String sql,Object... args ) throws DaoException;
	
	public Long queryLong(String sql,Object... args ) throws DaoException;
	
	public List<Long> queryLongList(String sql,Object... args ) throws DaoException;
	
	
	public Float queryFloat(String sql,Object... args ) throws DaoException;
	
	public List<Float> queryFloatList(String sql,Object... args ) throws DaoException;
	
	public Double queryDouble(String sql,Object... args ) throws DaoException;
	
	public List<Double> queryDoubleList(String sql,Object... args ) throws DaoException;
	
	public Date queryDate(String sql,Object... args ) throws DaoException;
	
	public List<Date> queryDateList(String sql,Object... args ) throws DaoException;
	
	public Map<String,Object> queryMap(String sql,Object... args ) throws DaoException;
	
	public List<Map<String,Object>> queryMapList(String sql,Object... args ) throws DaoException;
	
	/**
	 * ��ȡһ������
	 * @param sql
	 * @param args
	 * @return
	 * @throws DaoException
	 */
	public Object[] queryArray(String sql,Object... args ) throws DaoException;
	
	public List<Object[]> queryArrayList(String sql,Object... args ) throws DaoException;
	
	public <T> T queryUniqueValue(Class<T> valueClass,String sql,Object... args);
	
	public <T> List<T> queryValueList(Class<T> valueClass,String sql,Object... args);

	public <T> T queryBean(Class<T> beanClass,String sql,Object... args);
	
	public <T> List<T> queryBeanList(Class<T> beanClass,String sql,Object... args);
	
	public PreSQL preSql(String sql);
	/**
	 * �ر���Դ
	 */
	public void close();

}
