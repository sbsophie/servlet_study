package com.gn.member.vo;

import java.sql.Connection;

import com.gn.member.dao.MemberDao;
import static com.gn.common.sql.JDBCTemplate.close;
import static com.gn.common.sql.JDBCTemplate.getConnection;

public class MemberService {

	// createMember 메소드
	// Member를 매개변수로 받아서 Connection객체 생성
	// MemberDao에게 Connection과 Member 전달
	// int 반환
	
	private MemberDao memberDao = new MemberDao();
	
	public int createMember(Member m) {
		Connection conn = getConnection();
		int result = memberDao.createMember(m, conn);
		close(conn);
		return result;
	}
	
}
