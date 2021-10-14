<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
	.uploadResult {
		width:100%;
		background-color: gray;
	}
	.uploadResult ul {
		display: flex;
		flex-flow: row;
		justify-content: center;
		align-items: center;
	}
	.uploadResult ul li {
		list-style: none;
		padding: 10px;
	}
	.uploadResult ul li img {
		width: 20px
	}
</style>
<meta charset="UTF-8">
<title>Ajax 파일 업로드 폼</title>
<!-- 제이쿼리 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
	<h1>Upload with Ajax</h1>
	
	<div class="uploadDiv">
		<input type="file" name="uploadFile" multiple />
	</div>
	
	<div class='uploadResult'>
		<ul>
			<!-- 업로드된 파일들이 들어갈 자리 -->
		</ul>
	</div>
	
	<button id="uploadBtn">Upload</button>
	
	<script>
		$(document).ready(function() {
			
			// 정규 표현식으로 파일 확장자, 용량 거르기
			let regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
			let maxSize = 5242880;  // 5MB
			
			function checkExtension(fileName, fileSize) {
				if(fileSize >= maxSize) {
					alert("파일 사이즈 초과");
					return false;
				}
				
				if(regex.test(fileName)) {
					alert("해당 종류의 파일은 업로드할 수 없습니다.");
					return false;
				}
				return true;
			}
			
			var cloneObj = $(".uploadDiv").clone();
			
			$('#uploadBtn').on("click", function(e) {
				
				let formData = new FormData();
				
				let inputFile = $("input[name='uploadFile']");
				
				let files = inputFile[0].files;
				console.log(files);
				
				// 파일데이터를 폼에 넣기
				for(let i = 0; i < files.length; i++) {
					// 폼에 넣기 전에 검사부터
					if(!checkExtension(files[i].name, files[i].size)) {
						return false;
					}
					
					formData.append("uploadFile", files[i]);
				}
				
				$.ajax({
					url: '/uploadAjaxAction',
					processData: false,
					contentType: false,
					data : formData,
					type : 'POST',
					dataType:'json',
					success: function(result){
						alert("Uploaded");
						console.log(result);
						
						showUploadedFile(result);
						$(".uploadDiv").html(cloneObj.html());
					}
				}); // END Ajax
			});
			
			let uploadResult = $(".uploadResult ul");
			function showUploadedFile(uploadResultArr) {
				var str = "";
				
				$(uploadResultArr).each(function(i, obj){
					
					if(!obj.image) {
						str += "<li><img src='/resources/clip.png'>" + obj.fileName + "</li>";
					} else {
						/* str += "<li>" + obj.fileName + "</li>"; */
						// 파일 이름 + 썸네일 보여주기 위해 썸네일 주소 요청하게 만들기
						var fileCallPath = encodeURIComponent(obj.uploadPath + "/s_" + 
																obj.uuid + "_" + obj.fileName);
						str += "<li><img src='/display?fileName="+fileCallPath+"'></li>";
					}
				});
				uploadResult.append(str);
			}
			
		});
	</script>
</body>
</html>