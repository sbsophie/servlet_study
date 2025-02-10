<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int visitCount = 1;
	Cookie ck = new Cookie("visit_count",String.valueOf(visitCount));
	ck.setMaxAge(60*60*24);
	
	response.addCookie(ck);
	
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>화면</title>
</head>
<body>
	
	<p>
	당신은 이 페이지를 
	<strong>0</strong>번 방문했습니다.


</p>
</body>
</html>