package com.itwillbs.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.board.db.BoardDAO;
import com.itwillbs.board.db.BoardDTO;
import com.itwillbs.util.Action;
import com.itwillbs.util.ActionForward;

public class BoardUpdateAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : BoardUpdateAction_execute() 호출");
		
		// 전달 정보 저장 (bno, pageNum)  
			int bno = Integer.parseInt(request.getParameter("bno"));
			String pageNum = request.getParameter("pageNum");
		
		// DAO - 특정 글 정보 조회 
			BoardDAO dao = new BoardDAO();
			BoardDTO dto = dao.getBoard(bno);
			
		// request 영역 저장 
			request.setAttribute("dto", dto);
			request.setAttribute("pageNum", pageNum);
			
		// 페이지 이동 
			ActionForward forward = new ActionForward(); 
			forward.setPath("./board/boardUpdate.jsp");
			forward.setRedirect(false);
			
		return forward;
	}
}
