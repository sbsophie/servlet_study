package com.gn.member.controllor;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/* history 기록 남기기
 * 클래스명 : MemberCreateServlet 
 * 주요기능 : 회원가입 화명으로 전환
 * 개발자 : 박수빈
 * 개발일자 : 25-02-13
 */
@WebServlet("/memberCreate")
public class MemberCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberCreateServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view
			= request.getRequestDispatcher("/views/member/create.jsp");
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
