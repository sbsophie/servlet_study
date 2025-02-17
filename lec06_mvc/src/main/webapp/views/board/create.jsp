<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html> 
<head>
<meta charset="UTF-8">
<title>게시글 등록</title>
<link href='<%=request.getContextPath()%>/resources/css/board/create.css' 
rel="stylesheet" type="text/css">
<script src="<%=request.getContextPath()%>/resources/js/jquery-3.7.1.js"></script>
</head>
<body>
	<%@ include file="/views/include/header.jsp" %>
	<%@ include file="/views/include/nav.jsp" %>
	<section>
		<div id="section_wrap">
			<div class="word">
				<h3>게시글 등록</h3>
			</div><br>
			<div class="create_board_form">
				<form action='/boardCreateEnd' name="create_board_form" method="post" enctype="multipart/form-data">	
					<input type="text" name="board_title" placeholder="제목을 입력하세요."> <br>
					<input type="text" name="board_content" placeholder="내용을 입력하세요."><br>
					<input type="hidden" name="board_writer" value="<%=m.getMemberNo()%>">
					<input type="file" name="board_file" accept=".png,.jpg,.jpeg"><br>
					<input type="button" value="등록" onclick="createBoardForm();"> 
					<input type="reset" value="취소">
				</form>
			</div>
		</div>
	</section>
	<script type="text/javascript">
		const createBoardForm = function() {
			let form = document.create_board_form;	
			if(!form.board_title.value){
				alert("제목을 입력하세요.");
				form.board_title.focus();
			}else if(!form.board_content.value){
				alert("내용을 입력하세요.");
				form.board_content.focus();
			}else if (!form.board_file.value) { //이미지 파일이 입력되지 않았을때
				alert('이미지 파일을 선택하세요.');
				form.board_file.focus();	
			}else if(form.board_file.value){  //이미지 파일이 입력되었을때
				const val = form.board_file.value;
				const idx = val.lastIndexOf('.');
				const type = val.substring(idx+1, val.length); //시작점부터 몇번째까지 잘라온건지
				if(type == 'jsp' || type == 'png' || type == 'jpeg'){
					//form.submit();
					//console.log(val);
					const sendData = new FormData(form);
					$.ajax({
						url:'/boardCreateEnd',
						type:'post',
						enctype:'multipart/form-data',
						cache:false, //내가 지나간 흔적이 남는거(고의가 아님),쿠기는 고의로 남기는것
						async:false, //동기,비동기
						contentType:false,
						processData:false,
						data:sendData,
						dataType:'JSON',
						success:function(data){
							alert(data.res_msg);
							if(data.res_code == 200){
								location.href="/boardList";
							}
						}
						
					})
				}else{
					alert('이미지 파일만 선택할 수 있습니다.');
					form.board_file.value = ''; // 잘못입력한 값을 비워주는 코드
				}
			}
		}
	</script>
</body>
</html>