<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>list.jsp(MVC)</h1>

	<h1>관리자 메뉴 : 회원정보 출력</h1>
	<%
	// 세션정보(id) 가져와서 관리자 여부 판단
	%>
	<c:if test="${empty sessionScope.id || !id.equals('admin') }">
		<c:redirect url = "./MemberLogin.me"/>
	</c:if>
	
<%-- 	${memberList } --%>
	
	<table border="1">
	       <tr>
	         <td>아이디</td>
	         <td>비밀번호</td>
	         <td>이름</td>
	         <td>나이</td>
	         <td>이메일</td>
	         <td>성별</td>
	         <td>회원가입일</td>
	       </tr>
	       
	       <c:forEach var="dto" items="${memberList }">
	       	<tr>
	         <td>${dto.id }</td>
	         <td>${dto.pw }</td>
	         <td>${dto.name }</td>
	         <td>${dto.age }</td>
	         <td>${dto.email }</td>
	         <td>${dto.gender }</td>
	         <td>${dto.regdate }</td>
	       </tr>
	       </c:forEach>
    </table>

    <h2><a href="./Main.me">메인페이지로</a></h2>

</body>
</html>