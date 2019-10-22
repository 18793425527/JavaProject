package com.briup.service.impl;

import java.util.List;

import com.briup.bean.Customer;
import com.briup.bean.Order;
import com.briup.bean.ShopCar;
import com.briup.dao.OrderDao;
import com.briup.service.IOrderService;
import com.briup.util.BriupUtil;

/**
 * 当用户登录后，才有购物车功能(新建一个用户购物车，向order表中插入数据) <br>
 * 当用户登录系统后，应该从数据库中把当前用户的购物车查询出来
 * 
 * @author Caoxt
 */
public class IOrderServiceIMP implements IOrderService {
	private OrderDao dao = BriupUtil.getMapper(OrderDao.class);

	@Override
	public void saveOrder(Order order, List<ShopCar> cars) {
		
	}

	@Override
	public List<Order> findByCustomer(Customer customer) {
		List<Order> list = dao.findByCustomer(customer);
		return list;
	}

}
