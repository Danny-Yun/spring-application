<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>BMI 테스트</h3>
	<h6>당신의 키와 몸무게를 적어주세요.</h6>
	<br>
	<form action="/bmi" method="post">
		<input type="text" name="height" placeholder="키를 입력하세요(소수점X)" />
		<input type="text" name="weight" placeholder="몸무게를 입력하세요(소수점X)" />
		<input type="submit" value="확인" />
	</form>
</body>
</html>