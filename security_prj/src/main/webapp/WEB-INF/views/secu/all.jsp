<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>all 주소</h1>          
	
	<sec:authorize access="isAnonymous()">
		<!-- 로그인 안한(익명) 사용자인 경우 -->
		<a href="/customLogin">로그인</a>
	</sec:authorize>
	<sec:authorize access="isAuthenticated()">
		<!-- 로그인 한(인증된) 사용자인 경우 -->
		<h3><sec:authentication property="principal.member.userName" />님 환영환영</h3>
		
		<!-- 특정 사용자만 보이도록 -->
		<!-- 아래와 같이 var속성을 지정하면 property의 정보를 var변수명에 저장한다. -->
		<sec:authentication property="principal" var="secuInfo" />
		<c:if test="${secuInfo.member.userName eq '운영자25' }">
			너어무 반값읍니다. <br/>
		</c:if>
		
		<a href="/customLogout">로그아웃</a>
	</sec:authorize>
	
</body>
</html>