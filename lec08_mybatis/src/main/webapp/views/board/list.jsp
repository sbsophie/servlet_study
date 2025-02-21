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
	<form action="<c:url value='/boardList'/>" id="searchFrm">
		<fieldset>
			<legend>검색하기</legend>
			<input type="text" name="board_title" placeholder="제목">
			<input type="text" name="board_content" placeholder="내용">
			<input type="text" name="member_name" placeholder="작성자">
			<input type="submit" value="조회">
		</fieldset>
		<fieldset>
			<legend>정렬하기</legend>
			<select name="order_type" id="order_type">
				<option value="-1">선택</option>
				<option value="1">최신순</option>
				<option value="2">오래된순</option>
			</select>
		</fieldset>
	</form>
	<script>
		const orderType = document.getElementById('order_type');
		orderType.onchange = function(){
			document.getElementById('searchFrm').submit();
		}
	</script>
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