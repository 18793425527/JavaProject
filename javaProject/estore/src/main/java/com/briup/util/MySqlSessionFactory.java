package com.briup.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * 
 * MyBatis工具类
 * 		用于或者SqlSession对象
 * @author 60509
 *
 */
public class MySqlSessionFactory {
	
	
	//在本类中，我们需要通过对MyBatis配置文件进行读取
	//根据其中的信息连接数据库，并获取SqlSession对象
	
	public static void main(String[] args) {
		
	}
	
	public static SqlSessionFactory getSqlSessionFactory() {
		
		try {
			//输入流
			InputStream is = Resources.getResourceAsStream(
					"mybatis-config.xml");
			
			//创建工厂类Builder
			SqlSessionFactoryBuilder builder = 
					new SqlSessionFactoryBuilder();
			
			//builder根据配置文件的输入流对工厂类对象进行构建
			SqlSessionFactory factory = builder.build(is);
			
			return factory;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/*
	 * 
	 * 获取到SqlSession工厂之后，我们就可以获得SqlSession对象
	 * 这个过程同样可以进行封装
	 */
	public static SqlSession openSession(boolean flag) {
		
		/*SqlSessionFactory factory = 
				getSqlSessionFactory();
		
		SqlSession session = factory.openSession(flag);
		return session;*/
		
		return getSqlSessionFactory().openSession(flag);
	}
	
	public static SqlSession openSession() {
		return openSession(false);
	}
	
	
	
}



