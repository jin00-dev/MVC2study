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
<h1>/board/boardContent.jsp</h1>
<h2> 게시판 본문내용 </h2>

<table id="notice">
	<tr>
	    <th class="ttitle" colspan="4"> 아이티윌 게시판 </th>
    </tr>
	<tr>
		<td > 작성자 : </td>
	    <td > ${dto.name } </td>
	    <td> 조회수 : </td>
	    <td> ${dto.readcount } </td>
    </tr>
    <tr>
		<td> 글번호 : </td>
	    <td >
	    	${dto.bno }
	    </td>
	    <td> 작성일 : </td>
	    <td> ${dto.date } </td>
    </tr>
    <tr>
		<td> 제 목 : </td>
	    <td colspan="3">
	   		${dto.subject }
	    </td>
    </tr>
    <tr>
		<td> 내 용 : </td>
	    <td colspan="3">
	    	${dto.content }
	    </td>
    </tr>
    <tr>
    	<td>첨부파일</td>
    	<td colspan="3">
<%--     		<a href="./upload/${dto.file }" download>${dto.file }</a> 편하지만 안전하지 않은 방법 --%>
    		<a href="./BoardFileDown.bo?fileName=${dto.file }" >${dto.file }</a>
    	</td>
    	
    </tr>
</table>

	<div id="table_search">
		<input type="button" value="수정하기" class="btn" 
			   onclick="location.href = './BoardUpdate.bo?bno=${dto.bno}&pageNum=${param.pageNum }';"
		>
		<input type="button" value="삭제하기" class="btn"
			   onclick="location.href = './BoardDelete.bo?bno=${dto.bno}&pageNum=${param.pageNum }';"
		>
		<input type="button" value="답글쓰기" class="btn"
			   onclick="location.href = './BoardReWrite.bo?bno=${dto.bno}&pageNum=${param.pageNum }&re_ref=${dto.re_ref }&re_lev=${dto.re_lev }&re_seq=${dto.re_seq }';"
		>
		<input type="button" value="목록이동" class="btn"
			   onclick="location.href = './BoardList.bo?pageNum=${param.pageNum}';"
		> <!-- Action에서 파라메터로 불러왔으니 param.pageNum으로 불러와야 함 -->
	</div>
	<div class="clear"></div>
	<div id="page_control">
	
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