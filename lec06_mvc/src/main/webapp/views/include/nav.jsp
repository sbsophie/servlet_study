<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.gn.member.vo.Member" %>
<% Member m = (Member)session.getAttribute("member"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link href="<%=request.getContextPath() %>/resources/css/include/nav.css" rel="stylesheet" type="text/css">
    
<nav>
	<div id="nav_wrap">
		<div class="menu">
			<ul>
				<li>
					<a href="/boardList">게시판</a>
				</li>
				<c:choose>
					<c:when test="${empty member}">
						<li>
							<a href="/memberLogin">로그인</a>
						</li>
						<!-- 1. MemberLoginServlet
							2. /views/member/login.jsp (이동하고자 하는곳)
							3. /resources/css/member/login.css (css넣고자 하는곳) -->
						<li>
							<a href="/memberCreate">회원가입</a>
						</li>
					</c:when>
					<c:otherwise>
						<li>
							<a href="/boardCreate">게시글 등록</a>
						</li>
						<li>
							<a href="/memberLogout">로그아웃</a>
						</li>
						<li>
							<a href="/memberUpdate">계정수정</a>
						</li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</div>
</nav>	 