<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>info.jsp(MVC)</h1>
<h2>로그인 한 회원의 정보를 출력</h2>

<!-- 1) 세션에 저장된 로그인 정보 처리 -->
		<c:if test="${empty sessionScope.id }">
			<c:redirect url="./MemberLogin.me"/>
		</c:if>
		
		<%		 
		 // 2) DB접근 - 사용자의 모든정보를 조회(select)
		 // => Action페이지 처리완료=> 결과전달(request영역)
		 // 3) DB에서 가져온정보를 출력
		%>
		<hr>
		아이디 : ${requestScope.dto.id } <br>
		비밀번호 :  ${dto.pw } <br>
		이름 :  ${dto.name }<br>
		성별 :  ${dto.gender }<br>
		이메일 :  ${dto.email }<br>
		나이 :  ${dto.age }<br>
		회원가입일 :  ${dto.regdate }<br>
		
		<hr>
		
		<h2><a href="./Main.me">메인페이지로 이동하기</a></h2>
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

</body>
</html>