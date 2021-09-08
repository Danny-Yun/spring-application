<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>현재 보고 계신 페이지는 ${page }페이지 입니다.</h2>
	
	<!-- jstl을 활용하여 page가 100미만이면 하단에 h2태그를 이용해서 "초반부입니다", 
		100이상 200미만이면 "중반부입니다", 200이상이면 "후반부입니다."라는 문장을 출력하도록  -->
	<c:choose>
		<c:when test="${page < 100 }">초반부입니다.</c:when>
		<c:when test="${page >= 100 && page < 200 }">중반부입니다.</c:when>
		<c:otherwise>후반부입니다.</c:otherwise>
	</c:choose>
</body>
</html>