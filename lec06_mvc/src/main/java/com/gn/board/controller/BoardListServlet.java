package com.gn.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gn.board.service.BoardService;
import com.gn.board.vo.Board;

@WebServlet("/boardList")
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String boardTitle = request.getParameter("board_title");
		 String nowPage = request.getParameter("nowPage"); // nowPage 받아오기
		
  		Board option = new Board();
		if(nowPage != null) {
			option.setNowPage(Integer.parseInt(nowPage));
		}
		option.setBoardTitle(boardTitle);
		
		int totalData = new BoardService().selectBoardCount(option);
		option.setTotalData(totalData); //이코드가 동작하면서 Paging클래스의 calcpaging함수가 동작함
		
		List<Board> resultList = new BoardService().selectBoardList(option);
		
		RequestDispatcher view = request.getRequestDispatcher("/views/board/list.jsp");
		request.setAttribute("resultList", resultList);
		request.setAttribute("paging", option); //list쪽으로 보내기
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
