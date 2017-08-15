package com.johe.dao;

import java.sql.ResultSet;

/**
 * T类型的行映射器
 * @author Administrator
 *
 * @param <T>
 */
public interface RowMapper<T> {
	
	/**
	 * 将结果集的当前行映射为Java对象
	 * 该参数结果集对象内的游标指针已指向当前行，方法实现内部不得移动游标！！！
	 * @param rs 
	 * @return
	 */
	public T toObject(ResultSet rs)throws Exception;

}
