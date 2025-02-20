<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>목록 출력</title>
</head>
<body>
	<table border="1">
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>내용</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${empty resultList}">
					<tr>
						<td colspan="3">게시글이 없습니다.</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach var="b" items="${resultList}">
						<tr>
							<td>${b.boardNo}</td>
							<td>${b.boardTitle }</td>
							<td>${b.boardContent }</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
			
			<!-- resultList가 비어있으면 게시글이 없습니다.
			그렇지 않으면 출력해주기(번호,제목,내용)  -->
		</tbody>
	</table>



</body>
</html>