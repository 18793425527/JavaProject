package com.briup.web.servlet;
/**
*  @author Caoxt
*  @version 时间：2019年10月20日下午12:10:00
*/

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.briup.bean.Book;
import com.briup.service.impl.IBookServiceIMP;

@WebServlet("/ToViewBookServlet")
public class ToViewBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer bookId = Integer.parseInt(request.getParameter("bookId"));
		IBookServiceIMP bookServiceIMP = new IBookServiceIMP();
		Book book = bookServiceIMP.findBookById(bookId);

		HttpSession session = request.getSession();
		session.setAttribute("toViewBook", book);
		response.sendRedirect("/estore/viewBook.jsp");
	}
}

