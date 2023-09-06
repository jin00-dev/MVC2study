package com.itwillbs.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.util.Action;
import com.itwillbs.util.ActionForward;

public class MemberLogoutAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : MemberLogoutAction_execute() 호출 ");
		
		// 로그아웃 => 세션 초기화 
		HttpSession session = request.getSession();
		
		session.invalidate();
		
		// 메인페이지 이동 (JS)
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('정상적으로 로그아웃 되었습니다.');");
		out.println("location.href = './Main.me';");
		out.println("</script>");
		
		// 컨트롤러에서의 이동은 수행하지 X
		System.out.println("M : JS 페이지 이동, 컨트롤러 페이지 이동 X "); // 컨트롤러로 가기는 함...
		return null;
	}

}
