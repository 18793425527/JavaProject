package com.briup.bean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import com.briup.bean.Customer;

public class Order {
	private Integer id;
	private Double total;			// 总计
	private Timestamp orderDate;	// 时间
	private Customer customer;      // 用户
									// 物品：
	private List<OrderLine> lines = new ArrayList<OrderLine>();
	
	public Order() {}
	
	
	public Order(Integer id) {
		super();
		this.id = id;
	}


	public Order(Double total, Timestamp orderDate, Customer customer) {
		super();
		this.total = total;
		this.orderDate = orderDate;
		this.customer = customer;
	}


	public Timestamp getOrderDate() {
		return orderDate;
	}


	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}


	public Order(Integer id, Double total, Timestamp orderDate, Customer customer, List<OrderLine> lines) {
		super();
		this.id = id;
		this.total = total;
		this.orderDate = orderDate;
		this.customer = customer;
		this.lines = lines;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}



	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public List<OrderLine> getLines() {
		return lines;
	}

	public void setLines(List<OrderLine> lines) {
		this.lines = lines;
	}



	@Override
	public String toString() {
		return "Order [id=" + id + ", total=" + total + ", orderDate=" + orderDate + ", customer=" + customer
				+ ", lines=" + lines + "]";
	}

}
