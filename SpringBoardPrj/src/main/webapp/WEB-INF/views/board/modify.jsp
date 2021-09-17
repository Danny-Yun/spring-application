<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" 
	rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" 
	crossorigin="anonymous">
</head>
<body>
	<div class="col-md-10">
		<form action="/board/modify" method="post">
			<div class="col-md-10 offset-md-1">
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
						<td class="col-md-10" colspan="4">
							<input type="text" class="form-control" width="60px" name="b_title"
								value="${board.b_title }" required="required" />
						</td>
					</tr>
					<tr>
						<td colspan="4"><textarea class="col-md-12" cols="60px" rows="20px" name="b_content"
								required="required">${board.b_content }</textarea></td>
					</tr>
				</table>
			</div>
			<div class="col-md-10 offset-md-1">
				<div class="row">
					<p>
						<button class="btn btn-outline-success col-md-2 offset-md-2" type="button"
							onclick="location.href='/board/get/${board.b_no }'">취소</button>&nbsp;
						<input class="btn btn-outline-success col-md-2" type="reset" value="초기화">&nbsp;
						<input class="btn btn-outline-success col-md-2" type="submit" value="저장">&nbsp;
					</p>
					<!-- hidden 태그를 이용해 나머지 요소들도 다 첨부 -->
					<input type="hidden" name="b_no" value="${board.b_no}" />
					<input type="hidden" name="b_regdate" value="${board.b_regdate}" />
					<input type="hidden" name="b_updatedate" value="${board.b_updatedate}" />
					<input type="hidden" name="b_writer" value="${board.b_writer}" />
				</div>
			</div>
		</form>
	</div>
</body>
</html>