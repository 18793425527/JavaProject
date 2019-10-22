package com.briup.dao;


import java.sql.Timestamp;
import java.util.List;

import com.briup.bean.Customer;
import com.briup.bean.Order;

public interface OrderDao {
	
	// 查询出购物车,做页面展示
	List<Order> findAllOrders();
	
	// 显示用户
	List<Order> findByCustomer(Customer customer);
	
	// 保存
	void saveOrder(Order order);
	
	// 根据订单生成时间去查询订单id，查询订单id用于关联 相关的订单项
	Integer findByDate(Timestamp date);
}
