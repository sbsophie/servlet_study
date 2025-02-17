package com.gn.board.dao;

import static com.gn.common.sql.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.gn.board.vo.Attach;
import com.gn.board.vo.Board;

public class BoardDao {
	
	public List<Board> selectBoardList(Connection conn){
		// 게시글 번호 조회(board_no), 게시글 제목(board_title), 게시글 내용(board_content),
		// 게시글 작성자(board_writer) -> 게시글 작성자의 id 또는 닉네임 정보를 알려줘야함
		// 게시글 등록일(reg_date), 게시글 수정일(mod_date)
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Board> list = new ArrayList<Board>();
		try {
			String sql = "SELECT * "
					+ "FROM `board` b "
					+ "JOIN `member` m ON b.board_writer = m.member_no ";
						
			pstmt = conn.prepareStatement(sql);
			rs= pstmt.executeQuery();
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
			while(rs.next()) {
				Board board = new Board();
				board.setBoardNo(rs.getInt("board_no"));
				board.setBoardTitle(rs.getString("board_title"));
				board.setBoardContent(rs.getString("board_content"));
				board.setBoardWriter(rs.getInt("board_writer"));
				board.setMemberName(rs.getString("member_name"));
				board.setRegDate(rs.getTimestamp("reg_date").toLocalDateTime());
				//sdf.format(rs.getDate("reg_date"));
				board.setModDate(rs.getTimestamp("mod_date").toLocalDateTime());
				list.add(board);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
		
	}
	
	public int insertBoard(Board b, Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int boardNo = 0;
		try {
			String sql = "INSERT INTO `board`(board_title ,board_content ,board_writer) "
					+ "VALUES(?,?,?)";
			// (1) 매개변수 추가
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, b.getBoardTitle());
			pstmt.setString(2, b.getBoardContent());
			pstmt.setInt(3, b.getBoardWriter());
			
			boardNo = pstmt.executeUpdate();
			// (2) 생성된 키 반환
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				boardNo = rs.getInt(1);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return boardNo;
	}
	
	public int insertAttach(Attach a, Connection conn) {
		// 1. board_no ,ori_name ,new_name ,attach_path insert해주기
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int attachNo = 0;
		try{
			String sql = "INSERT INTO `attach`(board_no ,ori_name ,new_name ,attach_path) "
					+ "VALUE(?,?,?,?)";
			pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, a.getBoardNo());
			pstmt.setString(2, a.getOriName());
			pstmt.setString(3, a.getNewName());
			pstmt.setString(4, a.getAttachPath());
			
			attachNo = pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				attachNo = rs.getInt(1);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return attachNo;
	
	}
}
