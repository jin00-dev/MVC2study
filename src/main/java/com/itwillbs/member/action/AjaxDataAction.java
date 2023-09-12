package com.itwillbs.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.util.Action;
import com.itwillbs.util.ActionForward;

public class AjaxDataAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(" M : AjaxDataAction_execute 호출");
		
		// Ajax 호출 시 처리된 결과 (data형태)
		// => 화면에 값을 출력하는 결과와 동일한 형태
		
		String data = "ITWILL";
		
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().print(data);
		
		
		
		return null;
	}

}
