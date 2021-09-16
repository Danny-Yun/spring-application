<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 상세페이지</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" 
	rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" 
	crossorigin="anonymous">
</head>
<body>
	<table class="content table table-bordered border border-success">
		<tr>	
			<td class="col-md-2"><strong>글 번호</strong></td>
			<td class="col-md-4">${board.b_no }</td>
			<td class="col-md-2"><strong>등록일</strong></td>
			<td class="col-md-4">${board.b_regdate }</td>
		</tr>
		<tr>	
			<th class="col-md-2"><strong>글쓴이</strong></th>
			<td class="col-md-4">${board.b_writer }</td>
			<td class="col-md-2"><strong>최종 수정일</strong></td>
			<td class="col-md-4" colspan="4">${board.b_updatedate }</td>
		</tr>
		<tr>	
			<th class="col-md-2"><strong>제목</strong></th>
			<td class="col-md-10" colspan="4">${board.b_title }</td>
		</tr>
		<tr>	
			<td scope="row" colspan="4">
				<textarea class="form-control-plaintext col-md-12" rows="20px" cols="60px" readonly>${board.b_content }</textarea>
			</td>
		</tr>
	</table>
	<button type="button" class="btn btn-outline-success col-md-2 offset-md-4" onclick="location.href='/board/list'">리스트로 돌아가기</button>&nbsp;
</body>
</html>