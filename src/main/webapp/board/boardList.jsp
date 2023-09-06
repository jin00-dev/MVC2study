<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="./css/default.css" rel="stylesheet" type="text/css">
<link href="./css/subpage.css" rel="stylesheet" type="text/css">
<!--[if lt IE 9]>
<script src="http://ie7-js.googlecode.com/svn/version/2.1(beta4)/IE9.js" type="text/javascript"></script>
<script src="http://ie7-js.googlecode.com/svn/version/2.1(beta4)/ie7-squish.js" type="text/javascript"></script>
<script src="http://html5shim.googlecode.com/svn/trunk/html5.js" type="text/javascript"></script>
<![endif]-->
<!--[if IE 6]>
 <script src="../script/DD_belatedPNG_0.0.8a.js"></script>
 <script>
   /* EXAMPLE */
   DD_belatedPNG.fix('#wrap');
   DD_belatedPNG.fix('#main_img');   

 </script>
 <![endif]-->
</head>
<body>
<div id="wrap">
<!-- 헤더들어가는 곳 -->
<header>
<div id="login"><a href="./member/login.html">login</a> | <a href="../member/join.html">join</a></div>
<div class="clear"></div>
<!-- 로고들어가는 곳 -->
<div id="logo"><img src="./images/logo.gif" width="265" height="62" alt="Fun Web"></div>
<!-- 로고들어가는 곳 -->
<nav id="top_menu">
<ul>
	<li><a href="../index.html">HOME</a></li>
	<li><a href="../company/welcome.html">COMPANY</a></li>
	<li><a href="#">SOLUTIONS</a></li>
	<li><a href="../center/notice.html">CUSTOMER CENTER</a></li>
	<li><a href="#">CONTACT US</a></li>
</ul>
</nav>
</header>
<!-- 헤더들어가는 곳 -->

<!-- 본문들어가는 곳 -->
<!-- 메인이미지 -->
<div id="sub_img_center"></div>
<!-- 메인이미지 -->

<!-- 왼쪽메뉴 -->
  <jsp:include page="../inc/left_subMenu.jsp"/>
<!-- 왼쪽메뉴 -->

<!-- 게시판 -->
<article>
<%--  ${boardList } --%>
<h1>Notice</h1>
<table id="notice">
<tr><th class="tno">No.</th>
    <th class="ttitle">Title</th>
    <th class="twrite">Writer</th>
    <th class="tdate">Date</th>
    <th class="tread">Read</th></tr>
    
    <c:forEach var="dto" items="${boardList }">
		<tr>
			<td>${dto.bno }</td>
			<td class="left">
			
			<c:if test="${dto.re_lev > 0 }">
			<img src="./images/board/level.gif" width="${dto.re_lev * 10 }">
			<img src="./images/board/re.gif">
			</c:if>
			
				<a href="./BoardContent.bo?bno=${dto.bno }&pageNum=${pageNum}">${dto.subject }</a>
			<c:if test="${!empty dto.file }">
				<img src="./images/board/save.png" width="20px">
			</c:if>
			</td>
		    <td>${dto.name }</td>
		    <td>
		      <fmt:formatDate value="${dto.date }" pattern="MM-dd"/>  
		    </td>
		    <td>${dto.readcount }</td>
	    </tr>
    </c:forEach>
    
</table>
<!-- <div id="table_search"> -->
<!-- <input type="text" name="search" class="input_box"> -->
<!-- <input type="button" value="search" class="btn"> -->
<!-- </div> -->
<div class="clear"></div>
<div id="page_control">
	
	<c:if test="${startPage > pageBlock }">
		<a href="./BoardList.bo?pageNum=${startPage-pageBlock }">Prev</a>
	</c:if>
	
	<c:forEach var="i" begin="${startPage }" end="${endPage }" step="1">
		<a href="./BoardList.bo?pageNum=${i}">${i }</a>
	</c:forEach>
	
	<c:if test="${endPage < pageCount }">
		<a href="./BoardList.bo?pageNum=${startPage+pageBlock }">Next</a>
	</c:if>
	
</div>
</article>
<!-- 게시판 -->
<!-- 본문들어가는 곳 -->
<div class="clear"></div>
<!-- 푸터들어가는 곳 -->
<footer>
<hr>
<div id="copy">All contents Copyright 2011 FunWeb 2011 FunWeb 
Inc. all rights reserved<br>
Contact mail:funweb@funwebbiz.com Tel +82 64 123 4315
Fax +82 64 123 4321</div>
<div id="social"><img src="./images/facebook.gif" width="33" 
height="33" alt="Facebook">
<img src="./images/twitter.gif" width="34" height="34"
alt="Twitter"></div>
</footer>
<!-- 푸터들어가는 곳 -->
</div>
</body>
</html>