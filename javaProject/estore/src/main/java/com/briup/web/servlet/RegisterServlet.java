package com.briup.web.servlet;

/**
 * 注册页面逻辑处理
 * @author caoxiaotao
 * */
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.briup.bean.Customer;
import com.briup.service.ICustomerService;
import com.briup.service.impl.ICustomerServiceIMP;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ICustomerService service = new ICustomerServiceIMP();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String zip = request.getParameter("zip");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		Customer customer = new Customer(username, password, zip, address, phone, email);
		try {
			service.saveCustomer(customer);
			// 向login.jsp发送“恭喜注册成功！”字样
			request.setAttribute("msg", "恭喜注册成功！");
			response.sendRedirect("/estore/login.jsp");
			
		} catch (Exception e) {
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("register.jsp").forward(request, response);

			// 10秒后文字消失
		}

	}
}
