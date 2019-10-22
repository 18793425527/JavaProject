package com.briup.web.servlet;

/**
 * 	接收加入购物车数据
 * @author Caoxt
 * @version 时间：2019年10月20日上午12:03:58
 */
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.briup.bean.Customer;
import com.briup.bean.ShopCar;
import com.briup.service.impl.IShopCarServiceIMP;

@WebServlet("/AddShopCarServlet")
public class AddShopCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IShopCarServiceIMP shopCarServiceIMP = new IShopCarServiceIMP();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer bid = Integer.parseInt(request.getParameter("bookId"));

		HttpSession session = request.getSession();
		List<ShopCar> cars = (List<ShopCar>) session.getAttribute("cars");

		Customer cus = (Customer) session.getAttribute("customer");

		shopCarServiceIMP.saveShopCar(cars, bid, cus);
		
		response.sendRedirect("/estore/viewBook.jsp");
	}
}
