package com.itwillbs.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.board.db.BoardDAO;
import com.itwillbs.board.db.BoardDTO;
import com.itwillbs.util.Action;
import com.itwillbs.util.ActionForward;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class BoardFileWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : BoardFileWriteAction_execute() 호출");
		
		// 업로드 할 폴더 생성 - upload (가상경로)
		// 실제 파일이 업로드 되는 경로 
		String realPath = request.getRealPath("/upload");
		System.out.println(" M : realPath : " + realPath); // 실제 워크스페이스의 경로와는 다름
		
		// 파일 크기 제한 할 수 있음 
		int maxSize = 10 * 1024 * 1024;// 파일 용량 제한하는 이유~ 서버도 컴퓨터니까~ 
		
		// 1) 파일 업로드 수행 -> MultipartRequest 객체 생성 
		MultipartRequest multi 
			= new MultipartRequest( 
					request, 
					realPath,
					maxSize,
					"UTF-8",
					new DefaultFileRenamePolicy()
					);  
		
		System.out.println(" M : 파일업로드 성공");
		
		// 2) 전달정보 (파라메터) 저장 (DTO)
			BoardDTO dto = new BoardDTO(); 
			
			// enctype로 변경해서 reqest.getparmater 불가
			dto.setName(multi.getParameter("name"));
			dto.setPass(multi.getParameter("pass"));
			dto.setSubject(multi.getParameter("subject"));
			dto.setContent(multi.getParameter("content"));
			dto.setFile(multi.getFilesystemName("file")); // 파일정보 받아올 때는 다름!! 확인! 
			
			dto.setIp(request.getRemoteAddr());
		
		// DAO 객체 - 일반 글쓰기 메서드 호출 ( + 파일에 대한 처리 되어있음) 
			BoardDAO dao = new BoardDAO(); 
			dao.insertBoard(dto);
			
		// 페이지 이동 (./BoardList.bo) 
		ActionForward forward = new ActionForward(); 
		forward.setPath("./BoardList.bo");
		forward.setRedirect(true);
		
		return forward;
	}

}
