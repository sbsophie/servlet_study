package com.gn.board.service;

import static com.gn.common.sql.JDBCTemplate.close;
import static com.gn.common.sql.JDBCTemplate.commit;
import static com.gn.common.sql.JDBCTemplate.getConnection;
import static com.gn.common.sql.JDBCTemplate.rollback; //문제가 생겼을때 해결해주는게 rollback

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.gn.board.dao.BoardDao;
import com.gn.board.vo.Attach;
import com.gn.board.vo.Board;

public class BoardService {
	
	public List<Board> selectBoardList(){
		Connection conn = getConnection();
		List<Board> resultList = new ArrayList<Board>();
		resultList = new BoardDao().selectBoardList(conn);
		return resultList;
	}
	
	public int createBoard(Board b, Attach a) {
		Connection conn = getConnection();
		int result = 0;
		// 트랜젝션 곁들이기
		try {
			// 오토인크리먼트를 꺼주기
			conn.setAutoCommit(false);
			int boardNo = new BoardDao().insertBoard(b,conn);
			//반환받은 board를 attach바구니에 넣어주기
			a.setBoardNo(boardNo);
			int attachNo = new BoardDao().insertAttach(a,conn);
			
			if(boardNo != 0 && attachNo != 0) {
				result = 1;
				commit(conn);
			}else {
				rollback(conn);
			}
			
		}catch(Exception e) {
			rollback(conn);
			e.printStackTrace();
		}
		
		close(conn);
		return result;
	}
}
