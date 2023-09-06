package com.itwillbs.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {

	// 메서드 선언 => 인터페이스 안에 있는 메서드는 모두 추상 메서드 
	// public,abstract 생략 가능 , 보통 public 만 써준다.  
	public ActionForward execute(HttpServletRequest request,
								 HttpServletResponse response) throws Exception ; 
	
}
