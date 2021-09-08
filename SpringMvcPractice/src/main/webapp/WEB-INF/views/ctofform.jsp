<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- ctofform을 이용해 섭씨온도를 입력하고 제출버튼을 누르면 결과값이 나오는 로직 -->
	<form action="/ctof" method="post">
		<input type="text" name="c" placeholder="섭씨 온도를 입력하세요" /> 
		<input type="submit" value="확인" />
	</form>
</body>
</html>