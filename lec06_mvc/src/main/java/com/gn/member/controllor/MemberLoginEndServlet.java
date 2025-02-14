package com.gn.member.controllor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gn.member.service.MemberService;
import com.gn.member.vo.Member;

// 1. 별명과 url모두 작성
// 2. EncryptFilter에 대상으로 추가

//@WebServlet("/memberLoginEnd")
@WebServlet(name= "memberLoginEndServlet", urlPatterns="/memberLoginEnd")
public class MemberLoginEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MemberLoginEndServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("member_id");
		String pw = request.getParameter("member_pw");
		
		// 3. 전달받은 아이디와 비밀번호가 일치하는 회원 정보(번호,아이디,비밀번호,이름) 조회
		Member m = new MemberService().loginMember(id,pw);
		System.out.println(m);
		
		response.setContentType("application/json; charset=utf-8");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
