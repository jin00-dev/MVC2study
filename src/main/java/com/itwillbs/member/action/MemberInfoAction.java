package com.itwillbs.member.action;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.member.db.MemberDAO;
import com.itwillbs.member.db.MemberDTO;
import com.itwillbs.util.Action;
import com.itwillbs.util.ActionForward;

public class MemberInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : MemberInfoAcrion.me 호출 ");
		
		// 세션정보 확인 
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if (id == null) {
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			
			return forward;
			
		}
		// MemberDAO 객체 - getMemberInfo();
		MemberDAO dao = new MemberDAO(); 
		MemberDTO dto = dao.getMemberInfo(id);
		// DB에서 가져온 정보를 view 페이지로 전달 
		// => request 영역에 정보 저장
		request.setAttribute("dto", dto);
		
		// 페이지 이동 (forward)
		forward.setPath("./member/info.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
