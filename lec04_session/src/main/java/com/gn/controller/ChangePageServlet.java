package com.gn.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/changePage")
public class ChangePageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ChangePageServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 화면전환하는 기능만
		RequestDispatcher view 
//			= request.getRequestDispatcher("/views/countPage.jsp");
			= getServletContext().getRequestDispatcher("/views/countPage.jsp");
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
