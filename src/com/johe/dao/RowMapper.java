package com.johe.dao;

import java.sql.ResultSet;

/**
 * T���͵���ӳ����
 * @author Administrator
 *
 * @param <T>
 */
public interface RowMapper<T> {
	
	/**
	 * ��������ĵ�ǰ��ӳ��ΪJava����
	 * �ò�������������ڵ��α�ָ����ָ��ǰ�У�����ʵ���ڲ������ƶ��α꣡����
	 * @param rs 
	 * @return
	 */
	public T toObject(ResultSet rs)throws Exception;

}
