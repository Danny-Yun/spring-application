<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>당신의 정보은 아래와 같습니다.</h3>
	<ul>
		<li>이름 : ${vo.name }</li>
		<li>나이 : ${vo.age }</li>
		<li>전공 : ${vo.major }</li>
		<li>학년 : ${vo.grade }</li>
		<li>주소지 : ${vo.address }</li>
	</ul>
</body>
</html>