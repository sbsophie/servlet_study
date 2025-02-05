package com.gn.study.controller.send;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/joinMember")
public class GetSendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public GetSendServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. text 타입
		String userName = request.getParameter("userName");
		System.out.println("이름 : "+userName);
		
		// 2. radio 타입
		String userGender = request.getParameter("userGender");
//		성별 정보가 1 일때 남성, 그 외에는 여성
		if(userGender.equals('1')) {
			System.out.println("성별 : "+"남성");
		}else {
			System.out.println("성별 : "+"여성");
		}

		// 3. number 타입
		int userAge = Integer.parseInt(request.getParameter("userAge"));
		System.out.println("나이 : "+userAge);
		
		// 4. checkBox 타입
		String[] hobby = request.getParameterValues("hobby");
//		취미 정보를 ,로 연결해서 출력되도록하고 취미정보 없으면 취미 없음으로 출력
		Map<String,String> map = new HashMap<String,String>();
		map.put("1", "야구");
		map.put("2", "농구");
		map.put("3", "축구");
		
		if(hobby != null) {
			String[] arr = new String[hobby.length];
			System.arraycopy(hobby, 0, arr, 0, hobby.length);
			for(int i=0; i<arr.length; i++) {
				arr[i] = map.get(arr[i]);
			}
			System.out.println("취미 : "+String.join(",", arr));					
		}else {
			System.out.println("취미 없음");
		}
		
		// 1. 출력할 문서의 형태를 선언하는 방법
		response.setContentType("text/html; charset=UTF-8");
		// 2. 터널(스트림) 뚫어주는 방법
		PrintWriter out = response.getWriter();
		// 3. 자바가 가지고 있는걸 프론트 엔드쪽으로 옮겨주기
		// 스트림을 통해 HTML 구문을 한줄씩 출력
		out.println("<html lang='en'>");
		out.println("<head>");
		out.println("<meta charset='UTF-8'>");
		out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
		out.println("<title>회원가입 결과화면</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>"+userName+"님, 환영합니다</h1>");
		out.println("<h2>앞으로도 자주 와주실꺼죠?</h2>");
		out.println("<a href='/'>홈페이지로 이동</a>");
		out.println("</body>");
		out.println("</html>");
		// 4. 터널 문닫기
		out.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
