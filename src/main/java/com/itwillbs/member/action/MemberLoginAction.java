package com.itwillbs.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.member.db.MemberDAO;
import com.itwillbs.member.db.MemberDTO;
import com.itwillbs.util.Action;
import com.itwillbs.util.ActionForward;

public class MemberLoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : MemberLoginAction_execute() 호출");
		
		// Pro.jsp 페이지의 동작 수행
		
		// 0. 한글처리 인코딩
//		request.setCharacterEncoding("UTF-8");
		// 1. 전달정보 저장 (ID,PW)
//		MemberDTO dto = new MemberDTO();
//		dto.setId(request.getParameter("id"));
//		dto.setPw(request.getParameter("pw"));
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		// 2. DAO 객체 생성 - userCheck(id,pw);
		MemberDAO dao = new MemberDAO();
		int result = dao.userCheck(id,pw);
		System.out.println("M : 로그인 결과 ("+result+")");
		
		// 3. DB에서 전달 된 정보에 따른 페이지 이동  
		if (result == -1) {
			// 비회원 -> 페이지 이동 (JS)  순수 자바코드 내부에서는 HTML,JSP코드 불가, 하지만 되게 만들거야
			response.setContentType("text/html; charset=UTF-8"); // 서블릿과 JSP페이지 상단에서 봤어 
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('회원정보 없음');");
			out.println("history.back();");
			out.println("</script>");
			
			out.close();
			return null; //의미 : null 값을 리턴한다. + 함수의 종류..? 
			// if가 맞다면 아래 내용을 실행 할 필요가 없음 + 다시 이동 할 필요 없음 
			// 컨트롤러의 페이지 이동을 막음 
			
			
		}else if (result == 0) {
			//비밀번호 오류 -> 페이지 이동 (JS) 
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호 오류');");
			out.println("history.back();");
			out.println("</script>");
			
			out.close();
			return null;
			
		}else {
			// result == 1 
			// 로그인 성공 -> 페이지 이동(forward) 
			HttpSession session = request.getSession();
			session.setAttribute("id", id);
			
			// 페이지 이동 (forward)
			ActionForward forward = new ActionForward();
			forward.setPath("./Main.me");
			forward.setRedirect(true);
			
			System.out.println("M : " +forward);
			
			return forward; // 컨트롤러에서 움직일 수 있게 해주려고! 
		}//else
	}//execute
	
	

}
