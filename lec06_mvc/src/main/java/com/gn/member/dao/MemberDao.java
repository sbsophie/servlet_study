package com.gn.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static com.gn.common.sql.JDBCTemplate.close;
import com.gn.member.vo.Member;

public class MemberDao {
	
	// createMember매소드 만들기
	// 매개변수로 Connection과 Member받아서 DB에 INSERT(member_id,member_pw,member_name)해주기
	// ResultSet 아니고 executeUpdate 임
	// 그 후 결과를 int로 반환하기
	
	// 회원가입 코드
	public int createMember(Member m,Connection conn) {
		PreparedStatement pstmt = null;
		int result = 0;
		try{
			String sql = "INSERT INTO member(member_id,member_pw,member_name) "
						+"VALUES(?,?,?) ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getMemberId());
			pstmt.setString(2, m.getMemberPw());
			pstmt.setString(3, m.getMemberName());
			result = pstmt.executeUpdate();
					
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	// 로그인 코드
	public Member loginMember(String id, String pw, Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Member m = null;
		
		try {
			String sql = "SELECT * FROM member WHERE member_id= ? AND member_pw= ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				m = new Member();
				m.setMemberNo(rs.getInt("member_no"));
				m.setMemberId(rs.getString("member_id"));
				m.setMemberPw(rs.getString("member_pw"));
				m.setMemberName(rs.getString("member_name"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return m;
	}
	
	
}
