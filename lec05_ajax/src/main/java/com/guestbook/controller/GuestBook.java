package com.guestbook.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

@WebServlet("/guestBook")
public class GuestBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	// 기능 servlet
    public GuestBook() {
        super();
    }

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String textName = request.getParameter("guestName");
		String textMsg = request.getParameter("guestMessage");
		
		JSONObject js = new JSONObject();
		js.put("guestName",textName);
		js.put("guestMessage",textMsg);
		
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().print(js);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
