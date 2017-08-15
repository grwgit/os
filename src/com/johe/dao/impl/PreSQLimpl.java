/**
 * 
 */
package com.johe.dao.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;

import com.johe.dao.*;

/**
 * @author Lenovo
 *
 */
public class PreSQLimpl implements PreSQL{

	private PreparedStatement pst;

	private List<ResultSet> resultSets = new ArrayList<ResultSet>();
//	private static File file;
//	private static ConfigurationSource source;
	private static Logger log;

	public PreSQLimpl(PreparedStatement pst) {
		this.pst = pst;
//		file = new File("log4j2.xml");
//		try {
//			source = new ConfigurationSource(new FileInputStream(file),file);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//		Configurator.initialize(null,source);
		log = (Logger) LogManager.getLogger(PreSQLimpl.class.getName());
	}
	
	@Override
	public ResultSet query(Object... args) throws DaoException {
		outLog(args);
		try{
			for(int i=0;i<args.length;i++){
				pst.setObject(i+1, args[i]);
			}
			ResultSet rt = pst.executeQuery();
			resultSets.add(rt);
			return rt;
		}catch(Exception e){
			
		}
		return null;
	}

	@Override
	public int update(Object... args) throws DaoException {
		outLog(args);
		try {
			for(int i=0;i<args.length;i++){
				pst.setObject(i+1, args[i]);
			}
			return pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public <T> List<T> queryList(RowMapper<T> mapper, Object... args) throws DaoException {
		outLog(args);
		try{
			for(int i=0;i<args.length;i++){
				pst.setObject(i+1, args[i]);
			}
			ResultSet rt = pst.executeQuery();
			resultSets.add(rt);
			List<T> list = new ArrayList<T>();
			while(rt.next()){
				list.add(mapper.toObject(rt));
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public <T> T queryUnique(RowMapper<T> mapper, Object... args) throws DaoException {
		outLog(args);
		try{
			for(int i=0;i<args.length;i++){
				pst.setObject(i+1, args[i]);
			}
			ResultSet rt = pst.executeQuery();
			resultSets.add(rt);
			if(rt.next()){
				return mapper.toObject(rt);
			}
		}catch(Exception e){
			
		}
		return null;
	}

	@Override
	public String queryString(Object... args) throws DaoException {
		return queryUniqueValue(String.class, args);
	}

	@Override
	public List<String> queryStringList(Object... args) throws DaoException {
		return queryValueList(String.class, args);
	}

	@Override
	public Integer queryInteger(Object... args) throws DaoException {
		return (Integer)queryUniqueValue(Integer.class, args);
	}

	@Override
	public List<Integer> queryIntegerList(Object... args) throws DaoException {
		return queryValueList(Integer.class, args);
	}

	
	@Override
	public Long queryLong(Object... args) throws DaoException {
		return queryUniqueValue(Long.class, args);
	}

	@Override
	public List<Long> queryLongList(Object... args) throws DaoException {
		return queryValueList(Long.class, args);
	}

	@Override
	public Float queryFloat(Object... args) throws DaoException {
		return queryUniqueValue(Float.class, args);
	}

	@Override
	public List<Float> queryFloatList(Object... args) throws DaoException {
		return queryValueList(Float.class, args);
	}

	@Override
	public Double queryDouble(Object... args) throws DaoException {
		return queryUniqueValue(Double.class, args);
	}

	@Override
	public List<Double> queryDoubleList(Object... args) throws DaoException {
		return queryValueList(Double.class, args);
	}

	@Override
	public Date queryDate(Object... args) throws DaoException {
		return queryUniqueValue(Date.class, args);
	}

	@Override
	public List<Date> queryDateList(Object... args) throws DaoException {
		return queryValueList(Date.class, args);
	}

	@Override
	public Map<String, Object> queryMap(Object... args) throws DaoException {
		outLog(args);
		try{
			for(int i=0;i<args.length;i++){
				pst.setObject(i+1, args[i]);
			}
			ResultSet rt = pst.executeQuery();
			
			RowMapper<Map<String, Object>> rowMapper = new RowMapper<Map<String,Object>>() {
				Map<String, Object> map = new HashMap<>();
				@Override
				public Map<String, Object> toObject(ResultSet rs) throws Exception {
					ResultSetMetaData rsmd = rt.getMetaData();
					for(int i=0;i<rsmd.getColumnCount();i++)
						map.put(rsmd.getColumnLabel(1), rt.getObject(1));
					return map;
				}
			};
			if(rt.next()){
				return rowMapper.toObject(rt);
			}
		}catch(Exception e){
			
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> queryMapList(Object... args) throws DaoException {
		outLog(args);
		try{
			List<Map<String, Object>> list = new ArrayList<>();
			for(int i=0;i<args.length;i++){
				pst.setObject(i+1, args[i]);
			}
			ResultSet rt = pst.executeQuery();
			RowMapper<Map<String, Object>> rowMapper = new RowMapper<Map<String,Object>>() {
				@Override
				public Map toObject(ResultSet rs) throws Exception {
					Map<String, Object> map = new HashMap<>();
					ResultSetMetaData rsmd = rt.getMetaData();
					for(int i=0;i<rsmd.getColumnCount();i++){
						map.put(rsmd.getColumnLabel(i+1), rt.getObject(i+1));
					}
					return map;
				}
			};
			
			if(rt.next()){
				list.add(rowMapper.toObject(rt));
			}
			return list;
		}catch(Exception e){
			
		}
		return null;
	}

	@Override
	public Object[] queryArray(Object... args) throws DaoException {
		outLog(args);
		try{
			for(int i=0;i<args.length;i++){
				pst.setObject(i+1, args[i]);
			}
			ResultSet rt = pst.executeQuery();
			RowMapper<Object[]> rowMapper = new RowMapper<Object[]>() {

				@Override
				public Object[] toObject(ResultSet rs) throws Exception {
					ResultSetMetaData rsmd = rt.getMetaData();
					Object[] obj = new Object[rsmd.getColumnCount()];
					for(int i=0;i<rsmd.getColumnCount();i++){
						obj[i]=rt.getObject(i+1);
					}
					return obj;
				}
			};
			if(rt.next()){
				return rowMapper.toObject(rt);
			}
		}catch(Exception e){
			
		}
		return null;
	}

	@Override
	public List<Object[]> queryArrayList(Object... args) throws DaoException {
		outLog(args);
		List<Object[]> list = new ArrayList<Object[]>();
		try{
			for(int i=0;i<args.length;i++){
				pst.setObject(i+1, args[i]);
			}
			ResultSet rt = pst.executeQuery();
			
			RowMapper<Object[]> rowMapper = new RowMapper<Object[]>() {

				@Override
				public Object[] toObject(ResultSet rs) throws Exception {
					ResultSetMetaData rsmd = rt.getMetaData();
					Object[] obj = new Object[rsmd.getColumnCount()];
					for(int i=0;i<rsmd.getColumnCount();i++){
						obj[i]=rt.getObject(i+1);
					}
					return obj;
				}
			};
			while(rt.next()){
				list.add(rowMapper.toObject(rt));
			}
		}catch(Exception e){
			
		}
		return null;
	}

	
	@Override
	public <T> T queryUniqueValue(Class<T> valueClass, Object... args) {
		outLog(args);
		try{
			for(int i=0;i<args.length;i++){
				pst.setObject(i+1, args[i]);
			}
			ResultSet rt = pst.executeQuery();
			resultSets.add(rt);
			if(rt.next()){
				if(valueClass==String.class)
					return (T) rt.getString(1);
				else if(valueClass==int.class)
					return (T) new Integer(rt.getInt(1));
				else if(valueClass==Integer.class){
					BigDecimal num = rt.getBigDecimal(1);
					if(num==null)return null;
					return (T) new Integer(num.intValue());
				}
				else if(valueClass==double.class)
					return (T) new Double(rt.getDouble(1));
				else if(valueClass==Double.class){
					BigDecimal num = rt.getBigDecimal(1);
					if(num==null)return null;
					return (T) new Double(num.doubleValue());
				}
				else if(valueClass==float.class)
					return (T) new Float(rt.getFloat(1));
				else if(valueClass==Float.class){
					BigDecimal num = rt.getBigDecimal(1);
					if(num==null)return null;
					return (T) new Float(num.floatValue());
				}
				else if(valueClass==long.class)
					return (T) new Long(rt.getLong(1));
				else if(valueClass==Long.class){
					BigDecimal num = rt.getBigDecimal(1);
					if(num==null)return null;
					return (T) new Long(num.longValue());
				}
				else if(valueClass==Date.class){
					Object obj = rt.getObject(1);
					if(obj instanceof Date){
						return (T) rt.getTimestamp(1); 
					}else if(obj instanceof String){
						DateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
						return (T) fm.parse((String) obj);
					}
					throw new DaoException("不支持的日期格式！");
				}
				return null;
			}
		}catch(Exception e){
			
		}
		return null;
	}

	@Override
	public <T> List<T> queryValueList(Class<T> valueClass, Object... args) {
		outLog(args);
		List<T> list = new ArrayList<T>();
		try{
			for(int i=0;i<args.length;i++)
				pst.setObject(i+1, args[i]);
			ResultSet rt = pst.executeQuery();
			resultSets.add(rt);
			RowMapper<T> rowMapper = new RowMapper<T>() {
				
				@Override
				public T toObject(ResultSet rs) throws Exception {
						ResultSetMetaData rsdm = rt.getMetaData();
						return setValue(rt);
				}
				
				private T setValue(ResultSet rt) throws Exception {
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					if(valueClass==String.class){
						return (T) rt.getString(1);
					}else if(valueClass==int.class){
						return (T)new Integer(rt.getInt(1));
					}else if(valueClass==Integer.class){
						BigDecimal num = rt.getBigDecimal(1);
						if(num==null)	return null;
						return (T) new Integer(num.intValue());
					}else if(valueClass==double.class){
						return (T) new Double(rt.getDouble(1));
					}else if(valueClass==Double.class){
						BigDecimal num = rt.getBigDecimal(1);
						if(num==null)	return null;
						return (T) new Double(num.doubleValue());
					}else if(valueClass==float.class){
						return (T) new Float(rt.getFloat(1));
					}else if(valueClass==Float.class){
						BigDecimal num = rt.getBigDecimal(1);
						if(num==null)	return null;
						return (T) new Float(num.floatValue());
					}else if(valueClass==long.class){
						return (T) new Long(rt.getLong(1));
					}else if(valueClass==Long.class){
						BigDecimal num = rt.getBigDecimal(1);
						if(num==null)	return null;
						return (T) new Long(num.longValue());
					}else if(valueClass==Date.class){
						Object date = rt.getObject(1);
						if(date instanceof Date)
							return (T) rt.getTimestamp(1);
						else if(date instanceof String)
							return (T) df.parse((String)date);
						throw new DaoException("不支持的日期格式");
					}
					return null;
				}
			};
			while(rt.next()){
				list.add(rowMapper.toObject(rt));
			}
			return list;
		}catch(Exception e){
			
		}
		return null;
	}

	@Override
	public <T> T queryBean(Class<T> beanClass, Object... args) {
		outLog(args);
		try{
			for(int i=0;i<args.length;i++){
				pst.setObject(i+1, args[i]);
			}
			ResultSet rt = pst.executeQuery();
			ResultSetMetaData rsmd = rt.getMetaData();
			/**
			 * 获取列标
			 */
			String[] colLable = new String[rsmd.getColumnCount()];
			for(int i=0;i<colLable.length;i++){
				colLable[i]=rsmd.getColumnLabel(i+1);
			}
			RowMapper<T> rowMapper = new RowMapper<T>() {

				@Override
				public T toObject(ResultSet rs) throws Exception {
					T t = beanClass.newInstance();
					Method method[] = beanClass.getMethods();
					List<Method> method2 = new ArrayList<Method>();
					
					for(int i=0;i<method.length;i++){
						if(method[i].getName().startsWith("set")
								&&method[i].getParameterCount()==1
								&&method.length>3
								&&Character.isUpperCase(method[i].getName().charAt(3))){
							method2.add(method[i]);
						}
					}
					for (Method md : method2) {
						String string = md.getName().substring(3);
						for (String string2 : colLable) {
							if(string.equalsIgnoreCase(string2))
								md.invoke(t, setValue(rs, string2, md.getParameterTypes()[0]));
						}
					}
					return t;
				}

				private Object setValue(ResultSet rs,String colLab,Class type) throws Exception {
					if(type==String.class)
						return rs.getString(colLab);
					else if(type==int.class||type==Integer.class)
						return rs.getInt(colLab);
					else if(type==Date.class)
						return rs.getDate(colLab);
					else if(type==Float.class||type==float.class)
						return rs.getFloat(colLab);	
					else if(type==long.class||type==Long.class)
						return rs.getLong(1);
					else if(type==Double.class||type==double.class)
						return rs.getDouble(colLab);
					else 
						return null;
				}
			};
			
			if(rt.next())
				return rowMapper.toObject(rt);
		}catch(Exception e){
			
		}
		return null;
	}

	@Override
	public <T> List<T> queryBeanList(Class<T> beanClass, Object... args) {
		outLog(args);
		List<T> list = new ArrayList<T>();
		try{
			for(int i=0;i<args.length;i++){
				pst.setObject(i+1, args[i]);
			}
			ResultSet rt = pst.executeQuery();
			ResultSetMetaData rsmd = rt.getMetaData();
			String[] colLable = new String[rsmd.getColumnCount()];
			for (int i=0;i<colLable.length;i++){
				colLable[i]=rsmd.getColumnLabel(i+1);
			}
			
			RowMapper<T> rowMapper = new RowMapper<T>() {
	
				@Override
				public T toObject(ResultSet rs) throws Exception {
					T t = beanClass.newInstance();
					Method method[] = beanClass.getMethods();
					List<Method> method2 = new ArrayList<Method>();
					
					for(int i=0;i<method.length;i++){
						if(method[i].getName().startsWith("set")
								&&method[i].getParameterCount()==1
								&&method.length>3
								&&Character.isUpperCase(method[i].getName().charAt(3))){
							method2.add(method[i]);
						}
					}
					for (Method md : method2) {
						String string = md.getName().substring(3);
						for (String string2 : colLable) {
							if(string.equalsIgnoreCase(string2))
								md.invoke(t, setValue(rs, string2, md.getParameterTypes()[0]));
						}
					}
					return t;
				}
	
				private Object setValue(ResultSet rs,String colLab,Class type) throws Exception {
					if(type==String.class)
						return rs.getString(colLab);
					else if(type==int.class||type==Integer.class)
						return rs.getInt(colLab);
					else if(type==Date.class)
						return rs.getDate(colLab);
					else if(type==Float.class||type==float.class)
						return rs.getFloat(colLab);		
					else if(type==long.class||type==Long.class)
						return rs.getLong(1);
					else if(type==Double.class||type==double.class)
						return rs.getDouble(colLab);
					else 
						return null;
				}
			};
			
			while(rt.next()){
				list.add(rowMapper.toObject(rt));
			}
			return list;
		}catch(Exception e){
			
		}
		return null;
	}
	
	/**
	 * @return the resultSets
	 */
	public List<ResultSet> getResultSets() {
		return resultSets;
	}

	/**
	 * @return the pst
	 */
	public PreparedStatement getPst() {
		return pst;
	}
	
	private void outLog(Object...objects){
		String str = "";
		for (Object object : objects) {
			str+=object;
		}
		log.debug(str);
	}
}
