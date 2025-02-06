package com.gn.practice.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/order")
public class PizzaController extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public PizzaController() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String userName = req.getParameter("user_name");
		String userPhone = req.getParameter("user_phone");
		String userEmail = req.getParameter("user_email");
		String size = req.getParameter("size");
		String[] adds = req.getParameterValues("add");
		String deliveryTime = req.getParameter("delivery_time");
		
		// 1. size 기준
		// small : 15900원 / medium : 21000원 / large : 27900원
		int price = 0;
		switch(size) {
		case "1" : price = 15900;break;
		case "2" : price = 21000;break;
		case "3" : price = 27900;break;
		}
		// 2. 토핑 추가
		// 새우 + 2000원 / 고구마,감자,파인애플 +1000원
		System.out.println("이름 : "+userName);  //콘솔창에 나타남
		System.out.println("전화번호 : "+userPhone);
		System.out.println("이메일 : "+userEmail);
		System.out.println("피자 사이즈 : "+size);
		if(adds != null) {
			for(String s : adds) {
				System.out.println("토핑 : "+s);
				if(s.equals("1")) price += 2000;
				else price += 1000;
			}
		}
		System.out.println("희망 배송 시간 : "+deliveryTime);

		//응답 코드 넣어주기 (화면에 보내주기)
		RequestDispatcher view = req.getRequestDispatcher("views/pizzaPayment.jsp");
		req.setAttribute("name", userName);
		req.setAttribute("phone", userPhone);
		req.setAttribute("email", userEmail);
		req.setAttribute("size", size);
		req.setAttribute("toppings", adds);
		req.setAttribute("price", price);
		req.setAttribute("time", deliveryTime);
		view.forward(req, resp);
		
		}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}

	
	
}
