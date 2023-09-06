package com.itwillbs.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.member.db.MemberDAO;
import com.itwillbs.util.Action;
import com.itwillbs.util.ActionForward;

public class MemberDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println(" M : MemberDeleteAction_execute 호출");
		
		// 세션제어 
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if(id == null) {
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}
		
		// 전달된 정보 저장 (pw) 
		String pw = request.getParameter("pw");
		
		// DAO객체 - memberDelete(id, pw);
		MemberDAO dao = new MemberDAO();
		int result = dao.memberDelete(id,pw);
		
		// 처리결과에 따른 페이지 이동 (-1,0,1)(JS)
				if(result == -1) {
					response.setContentType("text/html; charset=utf-8");
					PrintWriter out = response.getWriter();
					out.print("<script>");
					out.print(" alert('회원정보 없음!'); ");
					out.print(" history.back(); ");
					out.print("</script>");
					out.close();
					
					return null; //js이동 수행시 컨트롤러 이동X			
				}else if(result == 0) {
					response.setContentType("text/html; charset=utf-8");
					PrintWriter out = response.getWriter();
					out.print("<script>");
					out.print(" alert('비밀번호 오류!'); ");
					out.print(" history.back(); ");
					out.print("</script>");
					out.close();
					
					return null; //js이동 수행시 컨트롤러 이동X			
				}else { // result == 1
					session.invalidate();
					response.setContentType("text/html; charset=utf-8");
					PrintWriter out = response.getWriter();
					out.print("<script>");
					out.print(" alert('삭제 완료'); ");
					out.print(" location.href='./Main.me'; ");
					out.print("</script>");
					out.close();
				}		
				
				return null;
		
	}

}
