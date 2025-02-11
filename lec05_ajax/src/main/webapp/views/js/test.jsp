<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JS 방식</title>
</head>
<body>
	<input type="text" id="user_name">
	<input type="button" value="제출" onclick="jsGetTest();">
	<input type="button" value="제출(2)" onclick="jsPostTest();">
	<div id="result_div">
		
	</div>
	<script>
		// post 방식
		const jsPostTest = function(){
			// 1. 객체 생성하기
			const xhr = new XMLHttpRequest();
			// 2. open() 메소드를 호출하기
			xhr.open("post","/jsAjaxPost");
			// 3. 서버 응답 처리함수를 생성하기
			xhr.onreadystatechange = function(){
				if(xhr.readyState == 4 && xhr.status == 200){
					document.getElementById("result_div").innerHTML 
						+= xhr.responseText;
				}
			}
			// 4. content-Type을 설정해주는 코드 필요
			xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
			// 5. send() 함수를 설정해준다.
			const userName = document.getElementById('user_name').value;
			xhr.send("userName="+userName)
		}
		// get 방식
		const jsGetTest = function(){
			// 1. XMLHttpRequest 객체 생성
			const xhr = new XMLHttpRequest();
			// 2. open() 매소드 호출
			// 방식,주소,동기/비동기 를 매개변수에 넣기
			const userName = document.getElementById('user_name').value;
			xhr.open("get","/jsAjaxGet?userName="+userName);	// 안쓰면 비동기 방식으로 들어감
			// 3. onreadystatechange 서버 응답처리 함수를 생성
			xhr.onreadystatechange = function(){
				if(xhr.readyState == 4 && xhr.status == 200){
					const result = xhr.responseText;
					document.getElementById("result_div").innerHTML += result;
				}
			}
			// 4. 요청 보내기
			xhr.send();
		}
	</script>
</body>
</html>