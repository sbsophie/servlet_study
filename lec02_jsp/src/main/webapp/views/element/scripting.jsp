<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스크립팅 요소 연습</title>
</head>
<body>
	<%-- Scriptlet : 일반적인 자바 코드 --%>
	<%
		int sum = 0;
		for(int i=0; i <= 10; i++){
			sum += i;
		}
		// System.out.println(sum);
		out.println("총합(1) : "+sum);
	%>
	<br>
	<%-- Expression : (화면에)출력하는 용도로 사용 --%>
	<%= "총합(2) : "+sum %>
	<%-- Declaration : 선언할때 사용 --%>
	<%! int visitCount = 0; %>  <%--변수선언/ 아래는 메소드 선언--%>
	<%! 
		public int sumNum(int a, int b){	
			return a+b;
		}		
	%>
	<% visitCount++; %>
	<br>
	<%= "조회수 : "+visitCount %>
	<br>
	<%= "더하기 : "+sumNum(3,4) %>
	
	
</body>
</html>