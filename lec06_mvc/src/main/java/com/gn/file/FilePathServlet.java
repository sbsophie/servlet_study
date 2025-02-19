package com.gn.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gn.board.service.BoardService;
import com.gn.board.vo.Attach;

@WebServlet("/filePath")
public class FilePathServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public FilePathServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 읽어올 파일명 전달받아오는 코드
		int attachNo = Integer.parseInt(request.getParameter("attach_no"));
		Attach a = new BoardService().selectAttachOne(attachNo);
		
		// 2. 해당 파일명이 비어있는지 확인하는 코드
		String filePath = a.getAttachPath();
		if(filePath == null || filePath.trim().equals("")) {
			// 사용자가 자의적으로 수정하려고 하면 400오류(잘못된 요청이 발생했을경우 이 오류) 발생시키기
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		// 3. 파일 경로에 파일이 존재하는지 확인하는 코드
		File file = new File(filePath);
		if(!file.exists()) {
			// 사용자가 자의적으로 수정하려고 하면 404오류(요청한 파일을 찾을 수 없을경우 발생하는 오류) 발생시키기
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		
		// 4. MIME 타입 감지(모든 형태의 파일이 가능하도록 작업)
		String mimeType = getServletContext().getMimeType(filePath);
		if(mimeType == null) {
			mimeType = "application/octet-stream"; //모든 타입의 파일을 다운로드 받을 수 있는것
		}
		response.setContentType(mimeType);
		
		//5. 파일을 읽어(In)들여서(바깥쪽에서 Java쪽으로) 클라이언트에 전송(Out)
		try(FileInputStream fis = new FileInputStream(file);
				OutputStream out = response.getOutputStream()) {
			byte[] buffer = new byte[1024];
			int byteRead;
			while((byteRead = fis.read(buffer)) != -1) {
				out.write(buffer,0,byteRead);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
