<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
	// id, pw, email입력 체크 
	function check (){
		var id = document.fr.id.value;
		var pw = document.fr.pw.value;
		var email = document.fr.email.value;
		
		 if (id == "") {
			 alert("id를 입력하세요.");
			 document.fr.id.focus();
			 return false;
			 
		 }
		 if (pw == "") {
			 alert("비밀번호를 입력하세요.");
			 document.fr.pw.focus();
			 return false;
			 
		 }
		 if (email == "") {
			 alert("이메일을 입력하세요.");
			 document.fr.email.focus();
			 return false;
			 
		 }
	}
</script>
<body>

	<h1>insertFrom.jsp(MVC)</h1>
	<h2>ITWILL 회원가입 (정보입력 -> 다음페이지(처리) 전달)</h2>
	
	<fieldset>
	<form action="./MemberJoinAction.me" method="post" name ="fr" onsubmit="return check();">
		아이디 : <input type="text" name ="id"> <br>
		비밀번호 : <input type="password" name = "pw" > <br>
		이름 : <input type="text" name = "name"> <br>
		나이 : <input type="text" name = "age"> <br>
		성별 : <input type="radio" name = "gender" value="여">여 
			   <input type="radio" name = "gender" value="남">남 <br>
		이메일 : <input type="email" name = "email"> <br>
		
	<input type="submit" value="회원가입">
	
	
	
	</form>
	</fieldset>

</body>
</html>