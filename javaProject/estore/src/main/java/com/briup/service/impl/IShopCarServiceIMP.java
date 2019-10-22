package com.briup.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.briup.bean.Book;
import com.briup.bean.Customer;
import com.briup.bean.ShopCar;
import com.briup.dao.BookDao;
import com.briup.dao.ShopCarDao;
import com.briup.service.IShopCarService;
import com.briup.util.MySqlSessionFactory;

/**
 * IShopCarService 实现类
 * 
 * @author Caoxt
 */
public class IShopCarServiceIMP implements IShopCarService {
	private SqlSession session = MySqlSessionFactory.openSession(true);
	private ShopCarDao dao = session.getMapper(ShopCarDao.class);
	private BookDao bookDao = session.getMapper(BookDao.class);

	@Override
	public List<ShopCar> findByCustomer(Customer customer) {
		List<ShopCar> list = dao.findByCustomer(customer);
		session.clearCache();
		return list;
	}

	@Override
	public void saveShopCar(ShopCar shopCar) {
//		dao.saveShopCar(shopCar);
	}

	@Override
	public void saveShopCar(List<ShopCar> cars, Integer bookId, Customer customer) {
		// 遍历购物车集合，如果书已存在，num++；否则插入
		for (ShopCar shopCar : cars) {
			if (shopCar.getBook().getId() == bookId) {
				Integer num = shopCar.getNum();
				shopCar.setNum(++num);
				dao.updateShopCar(shopCar);
				return;
			}
		}
		Book book = bookDao.findBookById(bookId);
		dao.saveShopCar(new ShopCar(customer, book, 1));
	}

}
