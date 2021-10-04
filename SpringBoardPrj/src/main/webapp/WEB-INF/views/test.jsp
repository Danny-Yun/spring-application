<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<title>ajax테스트</title>
</head>
<body>
	<h2>Ajax 테스트</h2>
	
	<div>
		<div>
			REPLYER <input type="text" name="replyer" id="newReplyWriter" />
		</div>
		<div>
			REPLY <input type="text" name="reply" id="newReply" />
		</div>
		<button id="replyAddBtn">ADD REPLY</button>
	</div>
	
	<ul id="replies">
	
	</ul>
	
	<!-- 모달 요소는 바로 안 보이기 때문에 어디 넣든 상관없지만, 보통 html요소들끼리 놨을 때
			제일 아래쪽에 작성하는 경우가 일반적이다. -->
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
	
	<!-- jquery -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<!-- 코드는 위에서 아래로 실행되므로 ajax스크립트 태그는 맨 아래에 -->
	<script type="text/javascript">
		var b_no = 50;
		
		function getAllList() {
			$.getJSON("/replies/all/" + b_no, function(data) {
				
				console.log(data.length);
				
				// str 변수 내부에 문자형태로 html 코드를 작성함
				var str = "";
				
				// $(data).each()는 향상된 for문처럼 내부데이터 하나하나를 반복한다. 
				// 내부 this는 댓글 하나하나이다. 
				$(data).each(function() {
						str += "<li data-r_no='" + this.r_no + "' class = 'replyLi'>"
							+ this.r_no + ":" + this.reply
							+ "<button> 수정/삭제 </button></li>";
					});
				
				// #replies인 ul태그 내부에 str을 끼워넣음
				$("#replies").html(str);
			});
		}
		getAllList();
	
		var b_no = 51;
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
					}
				}
			});
		});
		
		// 이벤트 위임 
		// 내가 현재 이벤트를 걸려는 집단(button)을 포함하면서 범위가 제일 좁은 #replies를 시작조건으로 잡는다.
		// .on("click", "목적지 태그까지 요소들", function(){실행문}) 과 같이 위임시엔 파라미터가 3개 들어간다. 
		$("#replies").on("click", ".replyLi button", function(){
			// this는 최하위태그인 button, button의 부모면 결국 .replyLi
			var reply = $(this).parent();
			
			// .attr("속성명") 을 하면 해당 속성의 값을 얻는다. 
			var r_no = reply.attr("data-r_no");
			var reply = reply.text();  // li태그 글씨만 얻기
			
			// 클릭한 버튼에 해당하는 댓글번호 + 본문이 얻어지나 디버깅
			console.log(r_no + ":" + reply);
			$(".modal-title").html(r_no);
			$("#replytext").val(reply);
			$("#modDiv").show("slow");
			
		});
	</script>
</body>
</html>