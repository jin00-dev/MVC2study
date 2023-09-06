package com.itwillbs.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

// JS 사용해서 페이지 이동 하는 동작을 정의 
// - alert + 뒤로 가기 메서드  
// - alert + 특정 주소 이동 메서드


public class JSMethod {
	
	public static void alertBack(HttpServletResponse response, String msg) {
		response.setContentType("text/html; charset=UTF-8");
		
		try {
			PrintWriter out = response.getWriter();
			String jsScript 
			= "<script>"
			+ "alert('"+msg+"');"
			+ "history.back();"
			+ "</script>";
		out.print(jsScript);
		out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void alertLocation(HttpServletResponse response, String msg, String url) {
		response.setContentType("text/html; charset=UTF-8");
		try {
			PrintWriter out = response.getWriter();
			String jsScript 
			= "<script>"
			+ "alert('"+msg+"');"
			+ "location.href='"+url+"';"
			+ "</script>";
		out.print(jsScript);
		out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
