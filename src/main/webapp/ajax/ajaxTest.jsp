<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
	<script type="text/javascript">
	$(document).ready(function(){
		// 버튼 1 클릭시 ajax로 데이터 가져오기
		$("#btn1").click(function(){
			$.ajax({
				url : "./AjaxData.me", // MVC2 에서는 가상주소 써야 함 
				success : function(data){
					alert("성공");
					$("body").text(data);
				}
				
			});//ajax
		});//click		
		
		
	}); //JQuery 끝 

</script>
</head>
<body>

	<h1>ajaxTest.jsp</h1>
	
	<input type="button" id ="btn1" value="버튼 1 ">

	<div id = "div1"></div>
	
</body>
</html>