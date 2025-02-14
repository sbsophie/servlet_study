<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="<%=request.getContextPath() %>/resources/css/include/nav.css" rel="stylesheet" type="text/css">
    
<nav>
	<div id="nav_wrap">
		<div class="menu">
			<ul>
				<li>
					<a href="/memberLogin">로그인</a>
				</li>
				<!-- 1. MemberLoginServlet
					2. /views/member/login.jsp (이동하고자 하는곳)
					3. /resources/css/member/login.css (css넣고자 하는곳) -->
				<li>
					<a href="/memberCreate">회원가입</a>
				</li>
			</ul>
		</div>
	</div>
</nav>	 