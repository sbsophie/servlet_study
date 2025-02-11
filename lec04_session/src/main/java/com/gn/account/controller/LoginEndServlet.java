package com.gn.account.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gn.account.vo.Account;

@WebServlet("/loginEnd")
public class LoginEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public LoginEndServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accountId = request.getParameter("account_id");
		String accountPw = request.getParameter("account_pw");
		String rememberId = request.getParameter("remember_id");
		
		System.out.println("아이디 : "+accountId
				+"\n비밀번호 : "+accountPw
				+"\n아이디 저장 유무 : "+rememberId);
		
		// 1. 아이디,비밀번호 정보가 일치하는 데이터 있는지 확인하기
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Account account = null;
		try {
			// SELECT 
			// 기준 아이디와 비밀번호 일치해야함(LIKE 아님)
			// 전체 정보를 조회해서 account 객체에 담아주면 됨
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://127.0.0.1:3306/login_project";
			String user = "scott";
			String pw = "tiger";
			conn = DriverManager.getConnection(url,user,pw);
			
			// sql문 뒤에 띄어쓰기 되어 있는지 항상 확인하기~!!!!!!!!
			String sql = "SELECT * "
					+ "FROM account "
					+ "WHERE account_id = ? AND account_pw = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, accountId);
			pstmt.setString(2, accountPw);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				account = new Account();
				account.setAccountNo(rs.getInt("account_no"));
				account.setAccountId(rs.getString("account_id"));
				account.setAccountPw(rs.getString("account_pw"));
				account.setAccountName(rs.getString("account_name"));
			}		
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		if(account != null) {
			// 2-1 O :만약에 일치하는 사용자가 있으면 사용자의 정보(번호,아이디,비밀번호,이름) 담고 있는 객체 Session에 저장하기
			HttpSession session = request.getSession();
			if(session.isNew() || session.getAttribute("account") == null) {
				session.setAttribute("account", account);
				session.setMaxInactiveInterval(10); //30분(60*30)
			}
			// if 부분 -> 사용자가 아이디 정보를 저장하고 싶으면 Cookie에 아이디 저장하기
			// else 부분 -> 저장 체크박스 해지했을때 쿠키 정보 삭제하는 코드
			if(rememberId != null) {
				Cookie cookie
					= new Cookie("remember_id",account.getAccountId());
				cookie.setMaxAge(60*60*24*7); //7일 (아이디 정보는 보통 7일 가지고 있음)
				response.addCookie(cookie);
			}else {
				Cookie cookie
					= new Cookie("remember_id","");
				cookie.setMaxAge(0); 
				response.addCookie(cookie);
			}
			// 		사용자가 아이디 정보를 저장하고 싶지 않으면 
			// 		홈화면으로 이동해서 로그인한 사용자 정보를 노출하기
			response.sendRedirect("/");
//	================ 여기까지가 로그인 성공했을때의 로직 끝 ===============
		}else {
			// 2-2 X :로그인 페이지 다시 요청하기
			response.sendRedirect("/login");
		}
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}

}
