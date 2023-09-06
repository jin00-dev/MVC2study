<%@page import="java.net.URLEncoder"%>
<%@page import="java.time.chrono.MinguoChronology"%>
<%@page import="java.io.FileInputStream"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>fileDownload.jsp(MVC)</h1>
	
	<%
		// 다운로드 할 파일의 이름 정보 가져오기 
// 		String fileName = request.getParameter("file_name");
		String fileName = (String)request.getAttribute("fileName");
	
		//다운로드 위치  == 업로드 위치 
		String savePath = "upload";
		
		//실제 위치정보 
// 		request.getRealPath(path); 이거 말고 다른거 써볼게! 
		ServletContext CTX = getServletContext(); //Context = Project / 프로젝트 정보를 불러오는, 저장하는 것
		String downloadPath = CTX.getRealPath(savePath);
			
		System.out.println("다운로드 위치 : " + downloadPath);
		
		String filePath = downloadPath + "\\" + fileName;  // 이건 뭐지 
		
		System.out.println("다운로드 할 파일의 위치 : "+ filePath);
		
		////////////////////////////////////////////////////////////////////
		
		// 파일 다운로드 설정 
		
		// 파일 다운로드 방법 => byte 타입 (숫자로 변경해서 저장)/ char 타입 (문자로 변경해서 저장)..? 
								// 데이터 => byte로 들고옴...? 
		
		// 파일을 한번에 많이 읽고 사용가능한 배열 => 버퍼 
		byte [] b = new byte [4096]; // 4KB씩 데이터 읽어오는(저장하는) 배열
		
		// 파일 정보를 읽어서 사용 가능하도록 하는 객체 
		FileInputStream fis = new FileInputStream(filePath); // 파일 위치로 가서 더블클릭해서 열어놓은 상태 
		
		// 다운로드 할 파일의 마임타입을 설정 (마임타입..?)
		String MimeType = getServletContext().getMimeType(filePath);
		System.out.println("MineType : " + MimeType);
		
		if (MimeType == null) {
			MimeType = "application/octet-stream";
			// => 잘 알려지지 않은 이진파일 
		}
		
		// 응답정보 데이터 처리를 수행 
		response.setContentType(MimeType); //해당 파일의 형식을 미리 읽을 수 있도록! 준비~ 
		
		/////////////////////////////////////////////////////////////////////////
		// 파일 인코딩 
		
		// 사용자의 브라우저 정보 체크 
		String agent = request.getHeader("User-Agent");
		
		//ie 브라우저여부를 체크 (브라우저 정보 안에 MSIE,Trident 있으면 true)
		boolean ieBrowser
			= (agent.indexOf("MSIE") > -1) || (agent.indexOf("Trident") > -1); 
		// 사용자의 브라우저 정보에 MSIE라는 정보가 들어있는지, 그 값이 -1 보다 큰지 
		// = index가 0이다 = true 
		
		if (ieBrowser){ // ie브라우저 일때 
			fileName = URLEncoder.encode(fileName,"UTF-8").replaceAll("\\+", "%20"); 
			//%20 = UTF-8 에서의 공백을 의미함 
		}else{ // ie브라우저가 아닐 때 
			fileName = new String (fileName.getBytes("UTF-8"),"iso-8859-1");
		}
		// 이렇게 해야 한글이 깨지지 않음 
		
		// 아래가 왜 필요해?? => 모든 파일의 형태를 일반화 시킴 / 전부 다운로드 형태가 됨! 
		response.setHeader("Content-Disposition", "attachment; filename="+fileName);
		
		// 기존의 JSP-out내장객체와의 충돌을 해결 
		out.clear();
		out = pageContext.pushBody();
		
		// 다운로드 시작 
		ServletOutputStream out2 = response.getOutputStream(); // 정보를 내보낼 수 있게 하는 것
		
		int data = 0;
		while ((data = fis.read(b,0,b.length)) != -1){ // -1 => file의 끝 / file이 끝나기 전 까지 계속
			out2.write(b,0,data);
		}
		
		out2.flush(); 
		out2.close();
		fis.close();
		
	
	
	%>

</body>
</html>