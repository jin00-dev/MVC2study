package com.itwillbs.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.board.db.BoardDAO;
import com.itwillbs.board.db.BoardDTO;
import com.itwillbs.util.Action;
import com.itwillbs.util.ActionForward;
import com.itwillbs.util.JSMethod;

public class BoardDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println(" M : BoardDeleteAction_execute 호출");
		
		
		// 전달정보 저장 (bno, pass, pageNum)
			BoardDTO dto = new BoardDTO();
			
			dto.setBno(Integer.parseInt(request.getParameter("bno")));
			dto.setPass(request.getParameter("pass"));
		
			String pageNum = request.getParameter("pageNum");

		// DAO - 글 삭제 메서드() 호출
			
			BoardDAO dao = new BoardDAO();
			int result = dao.deleteBoard(dto);
		// 처리 결과에 따른 페이지 이동 
			
			if (result == 0) {
				JSMethod.alertBack(response, "비밀번호 오류");
				return null; 
			}
			if (result == -1) {
				JSMethod.alertBack(response, "글 정보 없음");
				return null; 
			}
			// result == 1 => 글 정보 수정했다. 
				JSMethod.alertLocation(response, "삭제완료", "./BoardList.bo?pageNum="+pageNum);
		
		return null;
	}

}
