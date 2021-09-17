<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 등록</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" 
	rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" 
	crossorigin="anonymous">
</head>
<body>
	<div class="col-md-10">
		<div class="col-md-8 offset-md-1">
			<form action="/board/register" method="post">
				<table class="content table table-bordered border border-success">
					<tr>
						<td><input type="text" class="form-control" name="b_title" placeholder="제목을 입력해주세요" required="required"></td>
					</tr>
					<tr>
						<td><textarea cols="70" rows="20" name="b_content" required="required"></textarea></td>
					</tr>
					<tr>
						<td><input type="text" class="form-control-plaintext col-md-12" name="b_writer" ></td>
					</tr>
				</table>
				<p>
					<button type="button" class="btn btn-outline-success col-md-2 offset-md-4" onclick="location.href='/board/list'">취소</button>&nbsp;
					<input type="submit" class="btn btn-outline-success col-md-2" value="등록">
				</p>		
			</form>
		</div>
	</div>
</body>
</html>