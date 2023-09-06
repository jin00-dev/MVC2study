package com.itwillbs.util;

// ActionForward : 페이지 이동에 필요한 정보를 저장하는 객체 
// Ex : 기차표! 

public class ActionForward {
	
	private String path ; // 이동 할 페이지 주소 
	private boolean isRedirect ; // 이동 방식 저장 
	// 이동방식 - true : sendRedirect()   
	//		    - false : forward()       => 우리가 한 약속!
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public boolean isRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	@Override
	public String toString() {
		return "ActionForward(기차표) [path(목적지)=" + path + ", isRedirect(이동방법)=" + isRedirect + "]";
	}
	
}
