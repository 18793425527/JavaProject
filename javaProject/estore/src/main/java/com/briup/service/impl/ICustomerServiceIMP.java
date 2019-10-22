package com.briup.service.impl;

import com.briup.bean.Customer;
import com.briup.dao.CustomerDao;
import com.briup.service.ICustomerService;
import com.briup.util.BriupUtil;

public class ICustomerServiceIMP implements ICustomerService {
	CustomerDao dao = BriupUtil.getMapper(CustomerDao.class);

	/**
	 * 查询用户名,密码是否正确 首先判断前台传过来的是用户名、邮箱还是手机号
	 * 
	 * @author Caoxt
	 * @throws Exception
	 */
	@Override
	public Customer findByUsername(String username, String password) throws Exception {
		Customer customer = null;
//		String ph = "^((13[0-9])|(15[^4,\\D])|(17[0-9])|(18[0,5-9]))\\d{8}$";
		String ph = "^([0-9]+)$";
		String em = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";

		if (username.matches(em)) {
			customer = dao.findByUserEmail(username);
			judgeU(customer, password, "邮箱");
		} else if (username.matches(ph)) {
			customer = dao.findByUserPhone(username);
			judgeU(customer, password, "手机号");
		} else {
			customer = dao.findByUsername(username);
			judgeU(customer, password, "用户名");
		}
		return customer;
	}

	/**
	 * 判断用户输入是否正确
	 * 
	 * @throws Exception
	 */
	public void judgeU(Customer customer, String password, String str) throws Exception {
		if (customer == null) {
			throw new Exception("您输入的" + str + "不存在！");
		}
		if (!customer.getPassword().equals(password)) {
			throw new Exception("用户密码错误！");
		}
	}

	@Override
	public void saveCustomer(Customer customer) throws Exception {
		// 先去查询用户名是否存在
		Customer fruitCustomer = dao.findByUsername(customer.getUsername());
		Customer fruitPhone = dao.findByUserPhone(customer.getPhone());
		Customer fruitEmail = dao.findByUserEmail(customer.getEmail());

		// 判断用户名
		if (fruitCustomer != null) {
			throw new Exception("您输入的用户名已经存在！");
		}

		// 判断电话
		if (fruitPhone != null) {
			throw new Exception("您输入的电话已经存在！");
		}

		// 判断邮箱
		if (fruitEmail != null) {
			throw new Exception("您输入的邮箱已经存在！");
		}

		// 保存数据 注册
		dao.saveCustomer(customer);
	}

	@Override
	public void updateCustomer(Customer customer) {
		// 拿到修改后的信息
		dao.updateCustomer(customer);
	}

}
