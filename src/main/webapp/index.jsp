<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1> index.jsp </h1>
	Model2 에서 실행 가능한 유일한 JSP 페이지 
	Model2 프로젝트의 시작지점 
	index.jsp -> MemberFrontController 연결 
	
	<%
// 		response.sendRedirect("./MemberJoin.me");
// 		response.sendRedirect("./MemberLogin.me");
// 		response.sendRedirect("./Main.me");
// 		response.sendRedirect("./BoardWrite.bo");
		response.sendRedirect("./BoardList.bo");
	%>


</body>
</html>