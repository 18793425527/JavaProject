package com.briup.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.briup.bean.Customer;
import com.briup.bean.Order;
import com.briup.bean.ShopCar;
import com.briup.service.ICustomerService;
import com.briup.service.impl.ICustomerServiceIMP;
import com.briup.service.impl.IOrderServiceIMP;
import com.briup.service.impl.IShopCarServiceIMP;

/**
 * 登录的实现
 * 
 * @author Caoxt
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ICustomerService service = new ICustomerServiceIMP();
	IShopCarServiceIMP shopCarServiceIMP = new IShopCarServiceIMP();
//	private IOrderServiceIMP orderServiceIMP = new IOrderServiceIMP();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		try {
			Customer customer = service.findByUsername(username, password);
			HttpSession session = request.getSession();
			session.setAttribute("customer", customer);
			
			
			// 登录成功后加入购物车
			List<ShopCar> cars = shopCarServiceIMP.findByCustomer(customer);
			session.setAttribute("cars", cars);

			// 页面响应
			response.sendRedirect("/estore/index.jsp");
			// request.getRequestDispatcher("index.jsp").forward(request, response);

		} catch (Exception e) {
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}

	}
}
