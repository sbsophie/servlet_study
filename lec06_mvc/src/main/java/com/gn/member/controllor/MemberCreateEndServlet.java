package com.gn.member.controllor;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gn.member.vo.Member;
import com.gn.member.vo.MemberService;

@WebServlet(name="memberCreateEndServlet", urlPatterns="/memberCreateEnd")
public class MemberCreateEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MemberCreateEndServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("member_id");
		String pw = request.getParameter("member_pw");
		String name = request.getParameter("member_name");
		
		Member m = new Member();
		m.setMemberId(id);
		m.setMemberPw(pw);
		m.setMemberName(name);
		
		// Service에 데이터 전달
		int result = new MemberService().createMember(m);
		
		RequestDispatcher view
			= request.getRequestDispatcher("/views/member/create_fail.jsp");
		if(result > 0) {
			view = request.getRequestDispatcher("/views/member/create_success.jsp");
		}
		view.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
