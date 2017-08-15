/**
 * 
 */
package com.johe.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

/**
 * @author Lenovo
 *
 */
public class DBwrapper {
	private Connection con;
	/**
	 * @return the con
	 */
	private ArrayList<PreparedStatement> psts = new ArrayList<PreparedStatement>();
	private ArrayList<ResultSet> rts = new ArrayList<ResultSet>();
	private static String jdbcName;
	private static String url;
	private static String user;
	private static String passwd;
	
	/**
	 * 连接数据库，每个对象初始化不同的连接，满足多线程
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws FileNotFoundException 
	 */
	private DBwrapper() throws SQLException, ClassNotFoundException, FileNotFoundException, IOException{
		if(jdbcName == null)
		getProperty_properties();
		Class.forName(jdbcName);
		con = DriverManager.getConnection(url,user,passwd);
	}
	/**
	 * 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 * @return返回该对象实例
	 */
	public static DBwrapper getInstance() throws ClassNotFoundException, SQLException, IOException{
		return new DBwrapper();
	}
	/**
	 * 关闭该连接
	 */
	public void close(){
		for (ResultSet resultSet : rts) {
			try {
				resultSet.close();
			} catch (Exception e) {
			}
		}
		for (PreparedStatement preparedStatement : psts) {
			try {
				preparedStatement.close();
			} catch (Exception e) {
			}
		}
		rts.clear();
		psts.clear();
		try {
			con.close();
		} catch (SQLException e) {
		}
	}
	/**
	 * 查询
	 * @param sql
	 * @param args
	 * @return
	 * @throws SQLException
	 */
	public ResultSet excuQuery(String sql,Object[] args) throws SQLException{
		PreparedStatement pst;
		pst = con.prepareStatement(sql);
		psts.add(pst);
		ResultSet result = null;
		for(int i=0;i<args.length;i++){
			pst.setObject(i+1, args[i]);
		}
		result =  pst.executeQuery();
		rts.add(result);
		return result;
	}
	/**
	 * 更新
	 * @param sql
	 * @param args
	 * @return
	 * @throws SQLException
	 */
	public int excuUpdate(String sql,Object[] args) throws SQLException{
		PreparedStatement pst;
		pst = con.prepareStatement(sql);
		psts.add(pst);
		for(int i=0;i<args.length;i++){
			if(args[i] instanceof java.util.Date) {
				args[i] = new java.sql.Date(((java.util.Date)args[i]).getTime());
			}
			pst.setObject(i+1, args[i]);
		}
		return pst.executeUpdate();
	}
	/**
	 *获取连接
	 * @return
	 */
	public Connection getCon() {
		return con;
	}
	/**
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private static void getProperty_properties() throws FileNotFoundException, IOException{
		File file = new File("f:/property.properties");
		Properties properties = new Properties();
		properties.load(new FileInputStream(file));
		System.out.println("jdbc:"+(jdbcName=properties.getProperty("jdbcName")));
		System.out.println("url:"+(url=properties.getProperty("url")));
		System.out.println("user:"+(user=properties.getProperty("user")));
		System.out.println("passwd:"+(passwd=properties.getProperty("passwd")));
	}
}
