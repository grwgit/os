/**
 * 
 */
package com.johe.dao.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;
import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
public class DaoImpl implements Dao{
	private DBwrapper dWrapper;
//	private static File file;
//	private static ConfigurationSource source;
	private static Logger log = (Logger) LogManager.getLogger(DaoImpl.class);
	private List<PreSQLimpl> list = new ArrayList<PreSQLimpl>();
	
	public DaoImpl() throws DaoException{
		super();
//		file = new File("log4j2.xml");
		try {
			dWrapper = DBwrapper.getInstance();
//			source = new ConfigurationSource(new FileInputStream(file),file);
//			Configurator.initialize(null,source);
		} catch (ClassNotFoundException | SQLException | IOException e) {
			throw new DaoException("数据库配置信息错误或数据库连接错误！",e);
		}
	}
	/**
	 * 查询sql并打印结果
	 */
	@Override
	public ResultSet query(String sql, Object...args) {
		outLog(sql,args);
		try {
			return dWrapper.excuQuery(sql, args);
		} catch (Exception e) {
			throw new DaoException("查询出错！",e);
//			log.error();;
		}
	}
	/**
	 * 增删改sql,返回更新行数
	 */
	@Override
	public int update(String sql, Object... args)  {
		outLog(sql,args);
		try {
			return dWrapper.excuUpdate(sql,args);
		} catch (SQLException e) {
			throw new DaoException("更新出错！",e);
		}
	}
	/**
	 * 关闭数据库连接,resultSet和pst
	 */
	@Override
	public void close() {
		if(list!=null){
			try {
				for (PreSQLimpl preSQL : list) {
					for (ResultSet resultSet : preSQL.getResultSets()) {
						resultSet.close();
					}
					preSQL.getResultSets().clear();
					preSQL.getPst().close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		dWrapper.close();
	}
	@Override
	public <T> List<T> queryList(RowMapper<T> mapper, String sql, Object... args) throws DaoException {
		outLog(sql,args);
		ResultSet rt = query(sql, args);
		ArrayList<T> lists= new ArrayList<T>();
		try {
			while(rt.next()){
				lists.add(mapper.toObject(rt));
			}
		} catch (Exception e) {
			throw new DaoException("结果集查询出错",e);
		}
		return lists;
	}
	@Override
	public <T> T queryUnique(RowMapper<T> mapper, String sql, Object... args) throws DaoException {
		outLog(sql,args);
		T t = null;
		ResultSet rt = query(sql, args);
		try {
			if(rt.next()){
				t=mapper.toObject(rt);
			}
		} catch (Exception e) {
			throw new DaoException("结果集查询出错",e);
		}
		return t;
	}
	
	@Override
	public String queryString(String sql, Object... args)  {
		log.info(queryUniqueValue(String.class, sql, args));
		return queryUniqueValue(String.class, sql, args);
	}
	
	@Override
	public List<String> queryStringList(String sql, Object... args) throws DaoException {
		return queryValueList(String.class, sql, args);
	}
	
	@Override
	public Integer queryInteger(String sql, Object... args) throws DaoException {
		return (Integer)queryUniqueValue(Integer.class, sql, args);
	}
	
	@Override
	public List<Integer> queryIntegerList(String sql, Object... args) throws DaoException {
		return queryValueList(Integer.class, sql, args);
	}
	
	@Override
	public Long queryLong(String sql, Object... args) throws DaoException {
		return queryUniqueValue(Long.class, sql, args);
	}
	
	@Override
	public List<Long> queryLongList(String sql, Object... args) throws DaoException {
		return queryValueList(Long.class, sql, args);
	}
	
	@Override
	public Float queryFloat(String sql, Object... args) throws DaoException {
		return queryUniqueValue(Float.class, sql, args);
	}
	
	@Override
	public List<Float> queryFloatList(String sql, Object... args) throws DaoException {
		return queryValueList(Float.class, sql, args);
	}

	@Override
	public Double queryDouble(String sql, Object... args) throws DaoException {
		return queryUniqueValue(Double.class, sql, args);
	}
	
	@Override
	public List<Double> queryDoubleList(String sql, Object... args) throws DaoException {
		return queryValueList(Double.class, sql, args);
	}

	@Override
	public Date queryDate(String sql, Object... args) throws DaoException {
		return queryUniqueValue(Date.class, sql, args);
	}

	@Override
	public List<Date> queryDateList(String sql, Object... args) throws DaoException {
		return queryValueList(Date.class, sql, args);
	}
	
	@Override
	public Map<String, Object> queryMap(String sql, Object... args) throws DaoException {
		return queryUniqueValue(Map.class, sql, args);
	}
	
	@Override
	public List<Map<String, Object>> queryMapList(String sql, Object... args) throws DaoException {
		outLog(sql,args);
		ResultSet rt = query(sql, args);
		List<Map<String,Object>> list_map = new ArrayList<Map<String,Object>>();
		try {
			ResultSetMetaData rsmd = rt.getMetaData();
			while(rt.next()){
				Map<String, Object> map = new HashMap<>();
				for(int i=1;i<=rsmd.getColumnCount();i++){
					map.put(rsmd.getColumnLabel(i), rt.getObject(i));
				}
				list_map.add(map);
			}
			return list_map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Object[] queryArray(String sql, Object... args) throws DaoException {
		outLog(sql,args);
		ResultSet rt = query(sql, args);
		try {
			ResultSetMetaData rtsd = rt.getMetaData();
			Object[] obj = new Object[rtsd.getColumnCount()];
			if(rt.next())
				for(int i=0;i<rtsd.getColumnCount();i++){
					obj[i]=rt.getObject(i+1);
				}
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<Object[]> queryArrayList(String sql, Object... args) throws DaoException {
		outLog(sql,args);
		ResultSet rt = query(sql, args);
		List<Object[]> list_array = new ArrayList<Object[]>();
		try {
			ResultSetMetaData rtsd = rt.getMetaData();
			while(rt.next()){
				Object[] obj = new Object[rtsd.getColumnCount()];
				for(int i=0;i<rtsd.getColumnCount();i++)
				obj[i]=rt.getObject(i+1);
				list_array.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list_array;
	}
	
	@Override
	public <T> T queryUniqueValue(Class<T> valueClass, String sql, Object... args) {
		outLog(sql,args);
		T t = null;
		ResultSet rt = query(sql, args);
		
		RowMapper<T> rowMapper = new RowMapper<T>(){
			@Override
			public T toObject(ResultSet rs) throws Exception {
				try{
					ResultSetMetaData rtsd=rt.getMetaData();
					Object[] o = new Object[rtsd.getColumnCount()];
					for(int i=0;i<rtsd.getColumnCount();i++)
					o[i]=rt.getObject(i+1);
					if(valueClass==Integer.class||valueClass==int.class){
						/**
						 * 为null
						 * 为数字（整形，浮点型）
						 * 为字符
						 * 其他
						 */
						if(o[0]==null&&valueClass==int.class){
							throw new DaoException("int 不能为空");
						}else if(o==null&&valueClass==Integer.class){
							return null;
						}else if(o[0] instanceof Number){
							return (T) new Integer(((Number)o[0]).intValue());
						}else if(o[0] instanceof String){
							try{
								Integer.parseInt((String)o[0]);
								return (T)new Integer(Integer.valueOf((String)o[0]).intValue());
							}catch(NumberFormatException nft){
								throw new DaoException("String不能转换为int");
							}
						}
						throw new DaoException("不能转为Integer类型");
					}else if(valueClass==String.class){
						if(o[0] instanceof Number){
							return (T) (String)o[0];
						}else if(o[0] instanceof String){
							return (T)o[0];
						}
						throw new DaoException("不能转为string");
					}else if(valueClass==Long.class||valueClass==long.class){
						if(o[0]==null&&valueClass==long.class){
							throw new DaoException("long 不能为空");
						}else if(o[0]==null&&valueClass==Long.class){
							return null;
						}else if(o[0] instanceof Number){
							return (T) new Long(((Number)o[0]).longValue());
						}else if (o[0] instanceof String){
							try{
								Long.parseLong((String)o[0]);
								return (T)new Long(Long.valueOf((String)o[0]).longValue());
							}catch(Exception e){
								throw new DaoException("不能转为long型");
							}
						}
						throw new DaoException("不能转为long型");
					}else if(valueClass==Float.class||valueClass==float.class){
						if(o[0]==null&&valueClass==float.class){
							throw new DaoException("float不能为空");
						}else if(o[0]==null&&valueClass==Float.class){
							return null;
						}else if(o[0] instanceof Number){
							return (T) new Float(((Number)o[0]).floatValue());
						}else if(o[0] instanceof String){
							try{
								Float.parseFloat((String)o[0]);
								return (T) new Float(((Number)o[0]).floatValue());
							}catch(Exception e){
								throw new DaoException("不能转为float型");
							}
						}
						throw new DaoException("不能转为float型");
					}else if(valueClass==Double.class||valueClass==double.class){
						if(o[0]==null&&valueClass==double.class){
							throw new DaoException("double 不能为空");
						}else if(o[0]==null&&valueClass==Double.class){
							return null;
						}else if(o[0] instanceof Number){
							return (T) new Double(((Number)o[0]).doubleValue());
						}else if(o[0] instanceof String){
							try{
								Double.parseDouble((String)o[0]);
								return (T) new Double(((Number)o[0]).doubleValue());
							}catch(Exception e){
								throw new DaoException("不能转为double类型");
							}
						}
						throw new DaoException("不能转为double类型");
					}else if(valueClass==Date.class){
						if(o[0] instanceof Date&&o[0]!=null){
							return (T) o[0];
						}else if(o[0] instanceof String){
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
							return (T) new Date((long)sdf.parse((String)o[0]).getTime());
						}else if(o[0] instanceof Date&&o[0]==null){
							return null;
						}
						throw new DaoException("不能转为日期类型");
					}else if(valueClass==Map.class){
						Map<String, Object> map = new HashMap<>();
						for(int i=1;i<=rtsd.getColumnCount();i++){
							if(rtsd.getColumnLabel(i) != null)
							map.put(rtsd.getColumnLabel(i), o[i-1]);
							else{
								throw new DaoException("列标空，键不能空");
							}
						}
						if(map==null)
							return null;
						return (T) map;
					}else if(valueClass==Object[].class){
						if(o==null)
							return null;
						return (T) o;
					}
					return t;
				}catch(Exception e){
					throw new DaoException("获取元对象失败");
				}
			}
		};
		try{
			if(rt.next()){
				return rowMapper.toObject(rt);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return t;
	}

	@Override
	public <T> List<T> queryValueList(Class<T> valueClass, String sql, Object... args) {
		outLog(sql,args);
		List<T> list = new ArrayList<T>();
		ResultSet rt = query(sql, args);
		try{
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
	public <T> T queryBean(Class<T> beanClass, String sql, Object... args) {
		outLog(sql,args);
		ResultSet rt = query(sql, args);
		try{
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
					else if(type==Double.class||type==double.class)
						return rs.getDouble(colLab);
					else if(type==Long.class||type==long.class)
						return rs.getLong(colLab);
					else 
						return null;
				}
			};
			if(rt.next())
				return rowMapper.toObject(rt);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public <T> List<T> queryBeanList(Class<T> beanClass, String sql, Object... args) {
		outLog(sql,args);
		List<T> list = new ArrayList<T>();
		ResultSet rt = query(sql, args);
		try{
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
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获得该sql的预编译类的对象
	 */
	@Override
	public PreSQL  preSql(String sql) {
		try {
			log.debug(sql);
			PreSQLimpl pSql;
			PreparedStatement pst;
			pst = dWrapper.getCon().prepareStatement(sql);
			pSql = new PreSQLimpl(pst);
			list.add(pSql);
			return pSql;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/*@Override
	public PreSQL preSql(String sql) {
		try {
			PreparedStatement pst = dWrapper.getCon().prepareStatement(sql);
			return new PreSQL() {
				
				@Override
				public int update(Object... args) throws DaoException {
					
					return pst.executeUpdate();
				}
				
				@Override
				public <T> List<T> queryValueList(Class<T> valueClass, Object... args) {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public <T> T queryUniqueValue(Class<T> valueClass, Object... args) {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public <T> T queryUnique(RowMapper<T> mapper, Object... args) throws DaoException {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public List<String> queryStringList(Object... args) throws DaoException {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public String queryString(Object... args) throws DaoException {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public List<Map<String, Object>> queryMapList(Object... args) throws DaoException {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public Map<String, Object> queryMap(Object... args) throws DaoException {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public List<Long> queryLongList(Object... args) throws DaoException {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public Long queryLong(Object... args) throws DaoException {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public <T> List<T> queryList(RowMapper<T> mapper, Object... args) throws DaoException {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public List<Integer> queryIntegerList(Object... args) throws DaoException {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public Integer queryInteger(Object... args) throws DaoException {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public List<Float> queryFloatList(Object... args) throws DaoException {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public Float queryFloat(Object... args) throws DaoException {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public List<Double> queryDoubleList(Object... args) throws DaoException {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public Double queryDouble(Object... args) throws DaoException {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public List<Date> queryDateList(Object... args) throws DaoException {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public Date queryDate(Object... args) throws DaoException {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public <T> List<T> queryBeanList(Class<T> beanClass, Object... args) {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public <T> T queryBean(Class<T> beanClass, Object... args) {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public List<Object[]> queryArrayList(Object... args) throws DaoException {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public Object[] queryArray(Object... args) throws DaoException {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public ResultSet query(Object... args) throws DaoException {
					// TODO Auto-generated method stub
					return null;
				}
			};
		} catch (SQLException e) {
			throw new DaoException("创建预编译语句出错",e);
		}
		
	}*/
	
	private void outLog(String sql,Object...objects){
		String str = sql+"	";
		for (Object object : objects) {
			str+=object;
		}
		log.debug(str);
		log.error("mytest");
	}
}
