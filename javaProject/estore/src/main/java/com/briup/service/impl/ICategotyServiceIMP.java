package com.briup.service.impl;

import java.util.List;

import com.briup.bean.Category;
import com.briup.dao.CategoryDao;
import com.briup.service.ICategotyService;
import com.briup.util.BriupUtil;

public class ICategotyServiceIMP implements ICategotyService {

	@Override
	public List<Category> findAllCategories() {
		CategoryDao dao = BriupUtil.getMapper(CategoryDao.class);
		List<Category> list = dao.findAllCategories();
		return list;
	}

}
