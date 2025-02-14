package com.gn.member.service;

import java.sql.Connection;

import com.gn.member.dao.MemberDao;
import com.gn.member.vo.Member;

import static com.gn.common.sql.JDBCTemplate.close;
import static com.gn.common.sql.JDBCTemplate.getConnection;

public class MemberService {

	// createMember 메소드
	// Member를 매개변수로 받아서 Connection객체 생성
	// MemberDao에게 Connection과 Member 전달
	// int 반환
	
	private MemberDao memberDao = new MemberDao();
	
	// 회원가입 코드
	public int createMember(Member m) {
		Connection conn = getConnection();
		int result = memberDao.createMember(m, conn);
		close(conn);
		return result;
	}

	// 로그인 코드
	public Member loginMember(String id,String pw) {
		Connection conn = getConnection();
		Member m = memberDao.loginMember(id,pw,conn);
		close(conn);
		return m;
	}
	
	// 계정수정 코드
	public int updateMember(Member m) {
		Connection conn = getConnection();
		int result = memberDao.updateMember(m, conn);
		close(conn);
		return result;
	}
	
	// 계정 재설정 코드
	public Member selectMember(Member m) {
		Connection conn = getConnection();
		Member member = memberDao.selectMember(m ,conn);
		close(conn);
		return member;
	}
	
}
