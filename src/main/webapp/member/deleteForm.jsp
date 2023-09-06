<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>deleteForm.jsp(MVC)</h1>
	<h2> 회원정보 삭제(탈퇴)</h2>
	
	<%
		//로그인 성공했을 때만 실행 > 안되었을 때는 로그인 하고 오게 만듦 
	%>
	
	<c:if test="${empty sessionScope.id }">
		<c:redirect url = "./MemberLogin.me"/>
	</c:if>
	
	
	<fieldset>
	<form action="./MemberDeleteAction.me" method="post">
		
	비밀번호 : <input type="password" name="pw">
	<input type="submit" value="탈퇴하기">
	
	</form>
	
	
	</fieldset>
	
	
	

</body>
</html>