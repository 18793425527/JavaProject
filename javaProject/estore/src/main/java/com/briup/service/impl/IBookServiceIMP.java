package com.briup.service.impl;

import java.util.List;

import com.briup.bean.Book;
import com.briup.dao.BookDao;
import com.briup.service.IBookService;
import com.briup.util.BriupUtil;

/**
 * IBookService 实现类
 * 
 * @author Caoxt
 */
public class IBookServiceIMP implements IBookService {
	private BookDao dao = BriupUtil.getMapper(BookDao.class);

	@Override
	public List<Book> findAllBooks() {
		List<Book> list = dao.findAllBooks();
		return list;
	}

	@Override
	public Book findBookById(Integer id) {
		Book book = dao.findBookById(id);
		return book;
	}

}
