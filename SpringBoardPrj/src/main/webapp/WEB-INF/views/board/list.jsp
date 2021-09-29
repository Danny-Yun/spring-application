<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 리스트</title>
<!-- 부트스트랩 -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" 
	rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" 
	crossorigin="anonymous">
<!-- 제이쿼리 -->
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
</head>
<body>
	<div class="col-md-10">
		<div class="col-md-5 offset-md-8">
			<form style='display:inline' action="/board/list" method="get">
				<input type="text" name="keyword" placeholder="검색어" value="${keyword}"/>
				<input class="btn btn-success" type="submit" value="검색" />
				<select name="searchType">
					<option value="n"
					<c:out value="${btnMaker.cri.searchType == null ? 'selected' :'' }"/>>
						-
					</option>
					<option value="t"
					<c:out value="${btnMaker.cri.searchType eq 't' ? 'selected' :'' }"/>>
						제목
					</option>
					<option value="c"
					<c:out value="${btnMaker.cri.searchType eq 'c' ? 'selected' :'' }"/>>
						본문
					</option>
					<option value="w"
					<c:out value="${btnMaker.cri.searchType eq 'w' ? 'selected' :'' }"/>>
						글쓴이
					</option>
					<option value="tc"
					<c:out value="${btnMaker.cri.searchType eq 'tc' ? 'selected' :'' }"/>>
						제목+본문
					</option>
					<option value="cw"
					<c:out value="${btnMaker.cri.searchType eq 'cw' ? 'selected' :'' }"/>>
						본문+글쓴이
					</option>
					<option value="tcw"
					<c:out value="${btnMaker.cri.searchType eq 'tcw' ? 'selected' :'' }"/>>
						제목+본문+글쓴이
					</option>
				</select>
			</form>
		</div>
		<div class="col-md-10 offset-md-1">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>글 번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>등록일</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="list" items="${list }">
						<tr>
							<td>${list.b_no }</td>
							<td><a href='/board/get/${list.b_no }?pageNum=${btnMaker.cri.pageNum}&searchType=${btnMaker.cri.searchType}&keyword=${btnMaker.cri.keyword}'>${list.b_title }</a></td>
							<td>${list.b_writer }</td>
							<td>${list.b_regdate }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			
			<!-- 페이지네이션 버튼 위치 -->
			<nav aria-label="Page navigation example">
			  <ul class="pagination justify-content-center">
			    <!-- Prev버튼  -->
			    <c:if test="${btnMaker.prev }">
			      	<li class="page-item">
			      		<a class="page-link" 
		      		href="/board/list?pageNum=${btnMaker.startPage - 1}&searchType=${btnMaker.cri.searchType}&keyword=${btnMaker.cri.keyword}">Prev</a>
			      	</li>
			    </c:if>
				
				<!-- 번호 버튼 -->
				<!-- begin, end속성을 이용해서 startPage부터 endPage까지의 숫자들이 버튼으로 나열되게끔 -->
				<c:forEach begin="${btnMaker.startPage }" end="${btnMaker.endPage }" var="pageNum">
					<li class="page-item ${btnMaker.cri.pageNum == pageNum ? 'active' : '' }">
			      		<a class="page-link" 
		      		href="/board/list?pageNum=${pageNum}&searchType=${btnMaker.cri.searchType}&keyword=${btnMaker.cri.keyword}">${pageNum}</a>
			      	</li>
				</c:forEach>
					
				<!-- Next버튼 -->
			    <c:if test="${btnMaker.next }">
			      	<li class="page-item">
			      		<a class="page-link" 
		      		href="/board/list?pageNum=${btnMaker.endPage + 1}&searchType=${btnMaker.cri.searchType}&keyword=${btnMaker.cri.keyword}">Next</a>
			      	</li>
			    </c:if>
			  </ul>
			</nav>
			
			<button type="button" class="btn btn-outline-success" onclick="location.href='/board/register'">새 글 쓰기</button>&nbsp;
			
			<script>
				// 컨트롤러에서 success라는 이름으로 날린 자료가 들어오는지 확인
				// 그냥 list페이지 접근시엔 success를 날려주지 않아서 아무것도 들어오지 않고
				// remove 로직의 결과로 넘어왔을때만 데이터가 전달됨
				
				let result = "${success}";
				console.log(result);
				let bno = "${b_no}"
				
				if(result === "removeOK") {
					alert(bno + "번 글이 정상적으로 삭제되었습니다.");
				} else if(result === "registerOK") {
					alert(bno + "번 글이 정상적으로 등록되었습니다.")
				}
			</script>
		</div>
	</div>
</body>
</html>