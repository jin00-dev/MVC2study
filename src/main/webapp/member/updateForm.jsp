<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
	// 회원가입 정보가 잘 입력여부 체크
	function check(){
		var id = document.fr.id.value;
		var pw = document.fr.pw.value;
		var email = document.fr.email.value;
		
		//alert(id);
		if(id == ""){
			alert(" 아이디를 입력하세요! ");
			document.fr.id.focus();
			return false;
		}
		// 비밀번호
		if(pw == ""){
			alert(" 비밀번호를 입력하세요! ");
			document.fr.pw.focus();
			return false;
		}
		
		// 이메일
		if(email == ""){
			alert(" 이메일을 입력하세요! ");
			document.fr.email.focus();
			return false;
		}
		
		
	}
  
	
</script>

</head>
<body>
	<h1>updateForm.jsp (MVC)</h1>
	<h2> ITWILL - 회원정보 수정  </h2>
	
	<c:if test="${empty sessionScope.id }">
		<c:redirect url="./MemberLogin.me"/>
	</c:if>	
		
	<%
		 // 로그인한 사용자만 접근
		 // 사용자의 정보(DB)를 화면에 출력		
	%>	
	
	<fieldset>
	  <form action="./MemberUpdatePro.me" method="post" name="fr" onsubmit="return check();">
	    아이디 : <input type="text" name="id" value="${dto.id }" readonly><br>
	    비밀번호 : <input type="password" name="pw" placeholder="비밀번호를 입력하세요!"><br>
	    이름 : <input type="text" name="name" value="${dto.name }"><br>
	    나이 : <input type="text" name="age" value="${dto.age }"><br>
	    성별 : <input type="radio" name="gender" value="남" 
	    		<c:if test="${dto.gender.equals('남') }">	
			    	checked
			    </c:if>
			    >남 
	    	   <input type="radio" name="gender" value="여" 
    	       <c:if test="${dto.gender.equals('여') }">	
			    	checked
			    </c:if>
	    	   >여<br>
	    이메일 : <input type="email" name="email" value="${dto.email }" readonly><hr>	   
	  
	    <input type="submit" value="회원정보 수정">
	  </form>	
	</fieldset>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
</body>
</html>