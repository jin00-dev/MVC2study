package com.itwillbs.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.member.db.MemberDAO;
import com.itwillbs.member.db.MemberDTO;
import com.itwillbs.util.Action;
import com.itwillbs.util.ActionForward;

public class MemberUpdateAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		System.out.println(" M : MemberUpdateAction_execute() 호출 ");
		
		// 세션제어 
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if(id == null) {
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}
		
		// DAO 객체 - 회원정보 가져오기
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = dao.getMemberInfo(id);
		
		// 회원정보 출력 -> 정보 전달 (request 영역에 저장)
		request.setAttribute("dto", dto);
		//request.setAttribute("dto", dao.getMemberInfo(id));
		
		// 연결된 뷰페이지 이동티켓 (./member/updateForm.jsp)	
		forward.setPath("./member/updateForm.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
