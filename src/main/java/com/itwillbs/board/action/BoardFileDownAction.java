package com.itwillbs.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.util.Action;
import com.itwillbs.util.ActionForward;

public class BoardFileDownAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println(" M : BoardFileDownAction_execute() 호출");
		
		
		// 파일 다운로드 => 페이지 출력 
		
		// 첨부파일의 이름을 저장 
		String fileName = request.getParameter("fileName");
		System.out.println(" M : 다운로드 할 파일 명 : " + fileName);
		
		// request 영역에 저장 
		request.setAttribute("fileName", fileName);
			
		// 연결된 view 페이지로 이동 (출력) fileDownload.jsp
		ActionForward forward = new ActionForward();
		forward.setPath("./board/fileDownload.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
