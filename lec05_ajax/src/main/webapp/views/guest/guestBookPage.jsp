<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록</title>
<script src="<%=request.getContextPath()%>/resources/jquery-3.7.1.js"></script>
</head>
<body>
	<h2>방명록</h2>
    <input type="text" id="guest_name" placeholder="이름"><br><br>
    <textarea id="guest_message" placeholder="메시지">
    
    </textarea>
    <button id="submit_btn">등록</button>

    <h3>📝 남긴 메시지</h3>
    <ul id="guestbook_list">
    
    </ul>
    <script>
    	$(function(){
    		$("#submit_btn").click(function(){
    			const guestName = $("#guest_name").val();
    			const guestMessage = $("#guest_message").val();
    			console.log(guestMessage);
    			$.ajax({
    				url:"/guestBook",
    				type:"post",
    				contextType:"application/x-www-form-urlencoded; charset=UTF-8",
    				data:{
    					guestName:guestName,
    					guestMessage:guestMessage
    				},
    				dataType:"JSON",
    				success:function(data){
    					$("#guestbook_list").append("<li>"+data.guestName+":"+data.guestMessage+"</li>");
    					console.log(data);
    				},
    				error:function(){
    					alert("응답 오류");
    				}
    			})
    		})
    	})
    </script>
</body>
</html>