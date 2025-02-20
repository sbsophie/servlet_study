	<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html> 
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link href='<%=request.getContextPath()%>/resources/css/board/list.css' rel="stylesheet" type="text/css">
<link href='<%=request.getContextPath()%>/resources/css/include/paging.css' rel="stylesheet" type="text/css">
<%-- <script src="<%=request.getContextPath()%>/resources/js/jquery-3.7.1.js"></script> --%>
<script src="<c:url value='/resources/js/jquery-3.7.1.js'/>"></script>
</head>
<body>
	<%@ include file="/views/include/header.jsp" %>
	<%@ include file="/views/include/nav.jsp" %>
	<%Board paging = (Board)request.getAttribute("paging"); %>
	<section>
		<div id="section_wrap">
			<div class="search">
				<form action="/boardList" name="search_board_form" method="get">
					<input type="text" name="board_title" placeholder="검색하고자 하는 게시글 제목을 입력하세요."
					value="<%=paging.getBoardTitle() == null ? "" : paging.getBoardTitle() %>">
					
					<input type="submit" value="검색">
				</form>	
			</div>
			<div class="word">
				<h3>게시글 목록</h3>
			</div><br>
			<div class="board_list">
				<table>
					<colgroup>
						<col width="10%">
						<col width="50%">
						<col width="20%">
						<col width="20%">
					</colgroup>
					<thead>
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>작성자</th>
							<th>작성일시</th>
						</tr>
					</thead>
					<tbody>
<%-- 						<%
							List<Board> list = (List<Board>)request.getAttribute("resultList");
							DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yy-MM-dd HH:mm"); %> --%>
						<%@ page import="com.gn.board.vo.Board, java.util.*, java.time.format.*" %>
						<c:choose>
							<c:when test="${not empty resultList }">
								<c:forEach var="board" items="${resultList}" varStatus="vs">
									<tr data-board-no = "${board.boardNo }">
										<td>${((paging.nowPage-1)*paging.numPerPage)+vs.index+1 }</td>
										<td>${board.boardTitle }</td>
										<td>${board.memberName }</td>
										<%-- <td>${board.regDate }</td> --%>
										<fmt:parseDate value="${board.regDate }" pattern="yyyy-MM-dd'T'HH:mm:ss" var="strRegDate"/>
										<td>
											<fmt:formatDate value="${strRegDate }" pattern="yyyy-MM-dd HH:mm"/>
										</td>
									</tr>
								</c:forEach>	
							</c:when>
							<c:otherwise>
								<tr>
									<td colspan="4">조회된 데이터가 없습니다.</td>
								</tr>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</div>
		</div>
	</section>	
<%-- 	<% 
	if(paging != null) {%>
	<div class="center">
		<div class="pagination">
			<%if(paging.isPrev()){ %>
				<a href="/boardList?nowPage=<%=(paging.getPageBarStart()-1)%>&board_title=<%=paging.getBoardTitle() == null ? "" : paging.getBoardTitle()%>">&laquo;</a>  <!-- 부호<<(왼쪽 꺽쇠 2개)를 나타냄 -->
			<%} %>
			<%for(int i = paging.getPageBarStart(); i <= paging.getPageBarEnd(); i++){ %>
				<a href="/boardList?nowPage=<%=i%>&board_title=<%=paging.getBoardTitle() == null ? "" : paging.getBoardTitle()%>"><%=i%></a>
			<%} %>
			<%if(paging.isNext()){ %>
				<a href="/boardList?nowPage=<%=(paging.getPageBarEnd()+1)%>&board_title=<%=paging.getBoardTitle() == null ? "" : paging.getBoardTitle()%>">&raquo;</a>  <!-- 부호>>(오른쪽 꺽쇠 2개)를 나타냄 -->
			<%} %> --%>
<%-- 	<%} %> --%>
			<c:choose>
				<c:when test="${not empty paging}">
				<div class="center">
					<div class="pagination">
						<c:if test="${paging.prev}">
							<c:url var="testUrl" value="/boardList">
								<c:param name="nowPage" value="${paging.pageBarStart-1 }"/>
								<c:param name="board_title" value="${paging.boardTitle }"/>
							</c:url>
							<a href="${testUrl }">&laquo;</a>											
						</c:if>
						<c:forEach var="i" begin="${paging.pageBarStart}" end="${paging.pageBarEnd}">
							<a href="/boardList?nowPage=${i}&board_title=${empty paging.boardTitle ? '' : paging.boardTitle}">${i}</a>
						</c:forEach>
						<c:if test="${paging.next}">
							<a href="/boardList?nowPage=${paging.pageBarEnd+1}&board_title=${empty paging.boardTitle ? '' : paging.boardTitle}">&raquo;</a>
						</c:if>
					</div>
				</div>
				</c:when>
			</c:choose>
	<script>
		$('.board_list tbody tr').on('click',function(){
			const boardNo = $(this).data('board-no');
			location.href='/boardDetail?board_no='+boardNo;
		});
	</script>
</body>
</html>