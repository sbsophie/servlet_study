package com.gn.session;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/createSession")
public class CreateSessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CreateSessionServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session 
			= request.getSession();
//			= request.getSession(true); 위 코드와 같음
		if(session.isNew() || session.getAttribute("member_id") == null) {
			session.setAttribute("member_id", "user01");
			session.setMaxInactiveInterval(10);  // 30분이 기본 룰(60*10
		}
		response.sendRedirect("/");
		
//		if(session.isNew()) {
//			// true면 세로운 세션 객체 생성된거임
//			
//		}else {
//			// false면 기존의 세션 객체가 반환된거임
//			if(session.getAttribute("member_id") ==  null) {
//				
//			}
//		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
