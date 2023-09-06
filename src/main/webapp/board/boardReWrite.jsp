<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<li><a href="./index.html">HOME</a></li>
	<li><a href="./company/welcome.html">COMPANY</a></li>
	<li><a href="#">SOLUTIONS</a></li>
	<li><a href="./center/notice.html">CUSTOMER CENTER</a></li>
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
<h1>/board/boardReWrite.jsp</h1>
<h2> 게시판 답글쓰기페이지 </h2>

<!-- Content페이지에서 전달해 준 정보(답글) 저장 후 전달 / DB 전달 => hidden / DB 전달 X => 주소줄-->
<form action="./BoardReWriteAction.bo?pageNum=${param.pageNum }" method="post">

<input type="hidden" name="bno" value="${param.bno }">
<input type="hidden" name="re_ref" value="${param.re_ref }">
<input type="hidden" name="re_lev" value="${param.re_lev }">
<input type="hidden" name="re_seq" value="${param.re_seq }">

<table id="notice">
	<tr>
	    <th class="ttitle" colspan="3"> 아이티윌 게시판 </th>
    </tr>
	<tr>
		<td > 작성자 : </td>
	    <td colspan="2">
	    	<input type="text" id="wInput" name="name">
	    </td>
    </tr>
    <tr>
		<td> 비밀번호 : </td>
	    <td colspan="2">
	    	<input type="password" id="wInput" name="pass">
	    </td>
    </tr>
    <tr>
		<td> 제 목 : </td>
	    <td colspan="2">
	   		<input type="text" id="wInput" name="subject" value="[답글]">
	    </td>
    </tr>
    <tr>
		<td> 내 용 : </td>
	    <td colspan="2">
	    	<textarea rows="" cols="" id="wInput" name="content"></textarea>
	    </td>
    </tr>
</table>

	<div id="table_search">
		<input type="submit" value="글쓰기" class="btn">
	</div>
	<div class="clear"></div>
	<div id="page_control">
	
	</div>
</form>

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