package com.gn.board.service;

import static com.gn.common.sql.SqlSessionTemplate.getSqlSession;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.gn.board.dao.BoardDao;
import com.gn.board.vo.Board;

public class BoardService {
	
	public List<Board> selectBoardList(Board option){
		SqlSession session = getSqlSession();
		List<Board> resultList = new BoardDao().selectBoardList(session,option);
		session.close();
		return resultList;
	}

	public Board selectBoardOne(int boardNo) {
		SqlSession session = getSqlSession();
		Board board = new BoardDao().selectBoardOne(session,boardNo);
		session.close();
		return board;
	}

	public Board selectBoardTwo(Map<String, String> paramMap) {
		SqlSession session = getSqlSession();
		Board board = new BoardDao().selectBoardTwo(session,paramMap);
		session.close();
		return board;
	}

	public Board selectBoardThree(Board option) {
		SqlSession session = getSqlSession();
		Board board = new BoardDao().selectBoardThree(session,option);
		session.close();
		return board;
	}

	public int updateBoard(Board board) {
		SqlSession session = getSqlSession();
		int result = new BoardDao().updateBoard(session,board);
		//session.commit(true);
		//session.rollback();
		session.close();
		return result;
	}

	public int deleteBoard(Board b) {
		SqlSession session = getSqlSession();
		int result = new BoardDao().deleteBoard(session,b);
		session.close();
		return result;
	}

	public int insertBoard(Board board) {
		SqlSession session = getSqlSession();
		int result = new BoardDao().insertBoard(session,board);
		session.close();
		return result;
	}

	public int insertMany(List<Board> list) {
		SqlSession session = getSqlSession();
		int result = new BoardDao().insertMany(session,list);
		session.close();
		return result;
	}
}
