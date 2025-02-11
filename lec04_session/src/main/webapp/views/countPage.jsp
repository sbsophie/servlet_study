<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>조회수 확인</title>
</head>
<body>
	<%
		// 조회수를 담을 변수를 숫자형태로 만들어줘야함
		int visitCount = 0;
		// 쿠키 가져오기 -> 쿠키 존재여부를 확인해야함
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
			// 해당 페이지의 접근 이력이 있는 상태
			for(Cookie c : cookies){
				if("visit_count".equals(c.getName())){
					visitCount = Integer.parseInt(c.getValue());
				}
			}
		}
		// 조회수 증가
		visitCount++;
		// 쿠키 생성 및 갱신
		Cookie visitCookie = new Cookie("visit_count",String.valueOf(visitCount));
		visitCookie.setMaxAge(60*60*24);
		response.addCookie(visitCookie);
	%>
	
	<p>
		당신은 이 페이지를 
		<strong><%=visitCount %></strong>번 방문했습니다.
	</p>
	
</body>
</html>