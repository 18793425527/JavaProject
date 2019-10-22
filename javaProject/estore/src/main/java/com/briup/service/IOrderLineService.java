package com.briup.service;

import java.util.List;

import com.briup.bean.Order;
import com.briup.bean.OrderLine;

public interface IOrderLineService {
	
	List<Order> findAllOrders();

	void insertOrderLine(OrderLine orderLine);
}
