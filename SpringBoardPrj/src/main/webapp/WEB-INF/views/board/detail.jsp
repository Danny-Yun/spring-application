<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<style>
	#modDiv {
		width: 300px;
		height: 100px;
		background-color: green;
		position: absolute;
		top: 50%;
		left: 50%;
		margin-top: -50px;
		margin-left: -150px;
		padding: 10px;
		z-index: 1000;
	}
</style>
<meta charset="UTF-8">
<title>게시물 상세페이지</title>
<!-- 부트스트랩 -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" 
	rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" 
	crossorigin="anonymous">
<!-- 제이쿼리 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
	<div class="col-md-10">
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
					<td class="col-md-10" colspan="4">${board.b_title }</td>
				</tr>
				<tr>	
					<td scope="row" colspan="4">
						<textarea class="form-control-plaintext col-md-12" rows="20px" cols="60px" readonly>${board.b_content }</textarea>
					</td>
				</tr>
			</table>
			<form class="col-md-2 offset-md-4" style='display: inline' action="/board/modify/${board.b_no }" method="post">
				<input class="btn btn-outline-success" type="submit" value="수정하기" />
			</form>
		
			<form class="col-md-2" style='display: inline' action="/board/remove" method="post" id="remove">
				<input type="hidden" value="${board.b_no }" name="b_no" />
			</form>
			<button class="btn btn-outline-success" value="삭제하기" onclick="remove()">삭제하기</button>
			<script> 
				function remove() {
					if(confirm("정말로 삭제하시겠습니까?")) {
						let choice = document.getElementById("remove");
						choice.submit();
					} else {
						location.href="/board/get/${board.b_no}";
					}
				}
			</script>
			
			<button class="btn btn-outline-success col-md-3" type="button" onclick="location.href='/board/list'">리스트로 돌아가기</button>
		</div>
	</div>
	<!--  댓글 -->
	<ul id="replies">
	
	</ul>
	
	<div>
		<div>
			REPLYER <input type="text" name="replyer" id="newReplyWriter" />
		</div>
		<div>
			REPLY <input type="text" name="reply" id="newReply" />
		</div>
		<button id="replyAddBtn">ADD REPLY</button>
	</div>
	
	<!-- 모달 -->
	<div id="modDiv" style="display:none;">
		<div class="modal-title"></div>
		<div>
			<input type="text" id="replytext" />
		</div>
		<div>
			<button type="button" id="replyModBtn">Modify</button>		
			<button type="button" id="replyDelBtn">Delete</button>		
			<button type="button" id="closeBtn">Close</button>		
		</div>
	</div>
	
	<script>
		var b_no = ${board.b_no};
		
		// 댓글 조회
		function getAllList() {
			$.getJSON("/replies/all/" + b_no, function(data) {
				var timestamp = this.updateDate;
				var date = new Date(timestamp);
				console.log(date);
				var formattedTime = "게시일 : " + date.getFullYear()  // 년도 추출
								  	+ "/" + (date.getMonth() + 1)  // 0월부터 시작하기 때문에
								  	+ "/" + date.getDate()		// 날짜 
								  	+ "/" + date.getHours()     // 시간
								  	+ ":" + date.getMinutes()   // 분 
								  	+ ":" + date.getSeconds()   // 초
				var str = "";
				
				$(data).each(function() {
					str += "<div class='replyLi' data-r_no='" + this.r_no + "'><strong>@"
						+ this.replyer + "</strong> - " + this.updateDate + "<br>"
						+ "<div class='reply'>" + this.reply + "</div>"
						+ "<button type='button' class='btn btn-info'> 수정/삭제 </button>"
						+ "</div>";
				});
				
				// #replies인 ul태그 내부에 str을 끼워넣음
				$("#replies").html(str);
			});
		}
		getAllList();
		
		// 댓글 작성
		$("#replyAddBtn").on("click", function(){
			
			var replyer = $("#newReplyWriter").val();
			var reply = $("#newReply").val();
			console.log(replyer);
			console.log(reply);
			
			$.ajax({
				type : 'post',
				url : '/replies',
				headers : {
					"Content-Type" : "application/json",
					"X-HTTP-Method-Override" : "POST"
				},
				dataType : 'text',
				data : JSON.stringify({
					b_no : b_no,
					replyer : replyer,
					reply : reply
				}),
				success : function(result) {
					if(result == 'SUCCESS'){
						alert("등록되었습니다.");
						getAllList();
						// 댓글 쓰고 나서 다시 새롭게 갱신된 목록을 넣어주도록 전체 댓글 목록 다시 조회
						$("#newReplyWriter").val("");
						$("#newReply").val("");
					}
				}
			});
		});
		
		// 모달 위임
		$("#replies").on("click", ".replyLi button", function(){
			var replyLi = $(this).parent();
			
			var r_no = replyLi.attr("data-r_no");
			var reply = $(this).siblings(".reply").text();  
			
			// 클릭한 버튼에 해당하는 댓글번호 + 본문이 얻어지나 디버깅
			console.log(r_no + ":" + reply);
			$(".modal-title").html(r_no);
			$("#replytext").val(reply);
			$("#modDiv").show("slow");
		});
		
		// 댓글 삭제
		$("#replyDelBtn").on("click", function(){
			var r_no = $(".modal-title").html();
			
			$.ajax({
				type : 'delete',
				url : '/replies/' + r_no,
				success : function(result) {
					if(result == 'SUCCESS') {
						alert(r_no + "번 글이 삭제되었습니다.");
						$("#modDiv").hide("slow");
						getAllList();
					}
				}
			})
		});
		
		// 댓글 수정
		$("#replyModBtn").on("click", function() {
			var r_no = $(".modal-title").html();
			var reply = $("#replytext").val();
			
			$.ajax({
				type : 'patch',
				url : '/replies/' + r_no,
				headers : {
					"Content-Type" : "application/json",
					"X-HTTP-Method-Override" : "PATCH"
				},
				dataType : 'text',
				data : JSON.stringify({reply:reply}),
				success : function(result){
					if(result === 'SUCCESS') {
						alert(r_no + "번 댓글이 수정되었습니다.");
						$("#modDiv").hide("slow");
						getAllList();
					}
				}
			})
		});
		
		// 수정/삭제 모달 닫기 버튼
		$("#closeBtn").on("click", function() {
			$("#modDiv").hide("slow");
		});
		
	</script>
</body>
</html>