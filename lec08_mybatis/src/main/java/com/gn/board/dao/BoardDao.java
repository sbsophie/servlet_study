package com.gn.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.gn.board.vo.Board;

public class BoardDao {
	public List<Board> selectBoardList(SqlSession session, Board option){
		// 매개변수에는 mapper의 namespace.쿼리문의 id 쓰기
		return session.selectList("boardMapper.boardList",option);
	}

	public Board selectBoardOne(SqlSession session, int boardNo) {
		return session.selectOne("boardMapper.boardOne",boardNo);
	}

	public Board selectBoardTwo(SqlSession session, Map<String, String> paramMap) {
		return session.selectOne("boardMapper.boardTwo",paramMap);
	}

	public Board selectBoardThree(SqlSession session, Board option) {
		return session.selectOne("boardMapper.boardThree",option);
	}

	public int updateBoard(SqlSession session, Board board) {
		return session.update("boardMapper.boardUpdate",board);
	}

	public int deleteBoard(SqlSession session, Board b) {
		return session.delete("boardMapper.boardDelete",b);
	}

	public int insertBoard(SqlSession session, Board board) {
		System.out.println("실행전 : "+board);
		int result = session.insert("boardMapper.insertBoard",board);
		System.out.println("실행후 : "+board);
		result = board.getBoardNo();
		return result;
	}

	public int insertMany(SqlSession session, List<Board> list) {
		return session.insert("boardMapper.insertMany",list);
	}
}
