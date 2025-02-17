package com.gn.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.simple.JSONObject;

import com.gn.board.service.BoardService;
import com.gn.board.vo.Attach;
import com.gn.board.vo.Board;

@WebServlet("/boardCreateEnd")
public class BoardCreateEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BoardCreateEndServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 요청시에 전달된 데이터를 담을 바구니를 만들어 주기
		Board b = new Board();
		Attach a = new Attach();
		
		// 2. 파일업로드와 관련된 코드 : 파일을 업로드할 경로를 설정해주기
		String path ="C:\\upload\\board"; //윈도우 체계에서는 역슬래쉬를 써야하고 자바에서는 문자열로써 사용하려면 두개를 넣어야함
		File dir = new File(path);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		
		// 3. 파일을 업로드할 저장공간의 정보를 셋팅해주기
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(dir);
		// byte -> KB -> MB -> GB ->TB
		// 1024 -> 1KB * 1024 -> 1MB *10
		factory.setSizeThreshold(1024*1024*10); //경로의 파일을 넣어줄떄 어느정도 크기까지 넣어줄지 설정하는 코드
		
		// 4. 요청을 통해 전달된 데이터를 읽어오기
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			List<FileItem> items = upload.parseRequest(request);
			for(int i = 0; i < items.size(); i++) {
				FileItem fileItem = items.get(i);				
				if(fileItem.isFormField()) {
					// (1) 파일이 아닌 폼 내부 요소
					//System.out.println(fileItem.getFieldName());
					switch(fileItem.getFieldName()) {
						case "board_title" :
							b.setBoardTitle(fileItem.getString("utf-8"));
							break;
						case "board_content" :
							b.setBoardContent(fileItem.getString("utf-8"));
							break;
						case "board_writer" : 
							b.setBoardWriter(Integer.parseInt(fileItem.getString("utf-8")));
							break;
					}
				}else {
					// (2) 파일 형태의 폼 요소
					// System.out.println(fileItem.getName());
					if(fileItem.getSize() > 0) {
						String oriName = fileItem.getName();
						int idx = oriName.lastIndexOf(".");
						String ext = oriName.substring(idx);
						
						String uuid = UUID.randomUUID().toString().replace("-", "");
						String newName = uuid+ext;
						
						File uploadFile = new File(dir,newName);
						fileItem.write(uploadFile);
						
						// 메타데이터에 저장
						a.setOriName(oriName);
						a.setNewName(newName);
						//C:\\upload\\board\\새로운이름.확장자
						a.setAttachPath(path+"\\"+newName); 
					}
				}
			}
			// 1. 바구니에 데이터가 들어있는지 확인하기
			System.out.println(b);
			System.out.println(a);
			// 2. 지정한 경로에 파일 업로드 되었는지 확인하기
			int result = new BoardService().createBoard(b,a);
			
			//RequestDispatcher view
			//	= request.getRequestDispatcher("/views/board/create_fail.jsp");
			JSONObject obj = new JSONObject();
			if(result > 0) {
			//	view = request.getRequestDispatcher("/views/board/create_success.jsp");
				obj.put("res_code","200");
				obj.put("res_msg","정상적으로 게시글 등록되었습니다.");
			}else {
				obj.put("res_code","500");
				obj.put("res_msg","게시글 등록 중 오류가 발생하였습니다.");
				String deletePath = a.getAttachPath();
				File deleteFile = new File(deletePath);
				if(deleteFile.exists()) {
					deleteFile.delete();
				}
			}
			response.setContentType("application/json; charset=utf-8");
			response.getWriter().print(obj);
			//view.forward(request, response);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
