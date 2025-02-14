package com.gn.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import static com.gn.common.sql.JDBCTemplate.close;
import com.gn.member.vo.Member;

public class MemberDao {
	
	// createMember매소드 만들기
	// 매개변수로 Connection과 Member받아서 DB에 INSERT(member_id,member_pw,member_name)해주기
	// ResultSet 아니고 executeUpdate 임
	// 그 후 결과를 int로 반환하기
	
	
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
	
	
}
