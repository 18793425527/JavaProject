package com.briup.web.listener;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.briup.bean.Book;
import com.briup.bean.Category;
import com.briup.service.impl.IBookServiceIMP;
import com.briup.service.impl.ICategotyServiceIMP;
/**
 * 
 * 	监听器 
 * 	实现数据进容器
 * @author cxt
 * 
 * */

public class ListenerBook implements ServletContextListener {
	private ICategotyServiceIMP categotyServiceIMP = new ICategotyServiceIMP();
	private IBookServiceIMP bookServiceIMP = new IBookServiceIMP();
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// 拿到最大容器
		ServletContext context = sce.getServletContext();
		
		List<Category> categories = categotyServiceIMP.findAllCategories();
		context.setAttribute("category", categories);
		
		List<Book> findAllBooks = bookServiceIMP.findAllBooks();
		context.setAttribute("bookes", findAllBooks);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

	

}
