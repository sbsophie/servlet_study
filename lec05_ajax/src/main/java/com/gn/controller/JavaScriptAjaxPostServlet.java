package com.gn.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/jsAjaxPost")
public class JavaScriptAjaxPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public JavaScriptAjaxPostServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("userName");
		response.setContentType("text/html; charset=utf-8");
		response.getWriter().append("<h1>"+userName+"님이 만든 post 방식 ajax응답</h1>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
