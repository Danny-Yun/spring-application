<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
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
					success: function(result){
						alert("Uploaded");
					}
				}); // END Ajax
			});
			
		});
	</script>
</body>
</html>