package com.johe.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class Utils {

	private static final String PRE_SET="set";
	private static final String PRE_GET="get";
	private static final String DATE_FORMAT="yyyy-MM-dd";
	
	public static void fillBean(HttpServletRequest request,Object bean) throws Exception {
		Enumeration<String> e = request.getParameterNames();
//		Field[] fields = bean.getClass().getFields();
		Method[] methods = bean.getClass().getMethods();
		List<Method> method_set = new ArrayList<>(); 
		for(Method method:methods) {
			if(method.getName().startsWith(PRE_SET)&&method.getParameterCount()==1&method.getName().length()>3&&
					Character.isUpperCase(method.getName().charAt(3))) {
				method_set.add(method);
			}
		}
		DateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		while(e.hasMoreElements()) {
			String str = e.nextElement();
//			System.out.println(str);
			for(int i=0;i<method_set.size();i++) {
				Method meth = method_set.get(i);
//				System.err.println(method_set.get(i).getName());
				if(str.equalsIgnoreCase(meth.getName().substring(3))) {	//具有公共属性
//					System.out.println(i);
					String param_str = request.getParameter(str);
					Object param = "";
					if(meth.getParameterTypes()[0]==String.class) {
						param = param_str;
					}else if(meth.getParameterTypes()[0]==Integer.class) {
						param = new Integer(Integer.parseInt(param_str));
					}else if(meth.getParameterTypes()[0]==Float.class) {
						param = new Float(Float.parseFloat(param_str)) ;
					}else if(meth.getParameterTypes()[0]==Double.class) {
						param = new Double(Double.parseDouble(param_str)) ;
					}else if(meth.getParameterTypes()[0]==Long.class) {
						param = new Long(Long.parseLong(param_str)) ;
					}else if(meth.getParameterTypes()[0]==Date.class) {
						param = sdf.parse(param_str);
					}
					meth.invoke(bean, param);
				}
			}
		}
	}
	public static void copyBean(Object fromBean,Object toBean) throws Exception {
		Method[] methods_from = fromBean.getClass().getMethods();
		Method[] methods_to = toBean.getClass().getMethods();
		List<Method> me_from = new ArrayList<>();
		List<Method> me_to = new ArrayList<>();
		//tobean的set方法
		for(Method method:methods_to) {
			if(method.getName().startsWith(PRE_SET)&&method.getParameterCount()==1&method.getName().length()>3&&
					Character.isUpperCase(method.getName().charAt(3))) {
				me_to.add(method);
			}
		}
		System.err.println(me_to.toString());
		//frombean的get方法
		for(Method method:methods_from) {
			if(method.getName().startsWith(PRE_GET)&&method.getParameterCount()==0&method.getName().length()>3&&
					Character.isUpperCase(method.getName().charAt(3))) {
				me_from.add(method);
			}
		}
//		System.err.println(me_from.size());
		//对于属性相同的两个bean执行tobean的set方法,参数为frombean的get方法
		for(Method method:me_to) {
			for(Method method2:me_from) {
				if(method.getName().substring(3).equalsIgnoreCase(method2.getName().substring(3))) {
					method.invoke(toBean, method2.invoke(fromBean));
				}
			}
		}
		
		
		
		
		
		
		
		
		//筛选setAttribute方法
		/*for(Method method:methods_from) {
			if(method.getName().startsWith(PRE_SET)&&method.getParameterCount()==1&method.getName().length()>3&&
					Character.isUpperCase(method.getName().charAt(3))) {
				me_from.add(method);
			}
		}
		
		//得到公有方法
		
		for(Method method:me_from) {
			for(Method method2:me_to) {
				if(method.getName().equals(method2)) {
					me_to_all.add(method2);
				}
			}
		}
		//拷贝
		for(Method method:me_to_all) {
			method.invoke(toBean, args)
		}*/
		
	}
}
