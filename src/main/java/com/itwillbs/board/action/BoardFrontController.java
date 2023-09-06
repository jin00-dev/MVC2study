package com.itwillbs.board.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.util.Action;
import com.itwillbs.util.ActionForward;

public class BoardFrontController extends HttpServlet {
	
	protected void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" C : BoardFrontController-doProcess() 호출");
		
		////////////////////1. 가상주소 계산////////////////////////////////
		System.out.println("\n\n -----------1. 가상주소 계산 시작 ---------------");
		String requestURI = request.getRequestURI();
		System.out.println(" C : requestURI : "+requestURI);
		String CTXPath = request.getContextPath();
		System.out.println(" C : CTXPath : "+CTXPath);
		String command = requestURI.substring(CTXPath.length());
		System.out.println(" C : command : "+command);		
		System.out.println(" -----------1. 가상주소 계산 끝 ---------------");
		////////////////////1. 가상주소 계산////////////////////////////////
		
		////////////////////2. 가상주소 비교////////////////////////////////
		System.out.println("\n\n -----------2. 가상주소 비교 시작 ---------------");
		Action action = null;
		ActionForward forward = null;
		
		if(command.equals("/BoardWrite.bo")) {
			System.out.println(" C : /BoardWrite.bo 호출 ");
			System.out.println(" C : 패턴 1 - DB사용X,페이지 이동 ");
			
			forward = new ActionForward();
			forward.setPath("./board/boardWrite.jsp");
			forward.setRedirect(false);			
		}
		else if(command.equals("/BoardWriteAction.bo")) {
			System.out.println(" C : /BoardWriteAction.bo 호출 ");
			System.out.println(" C : 패턴 2 - DB사용 O, 페이지 이동");
			
			// BoardWriteAction() 객체
			action = new BoardWriteAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/BoardList.bo")) {
			System.out.println(" C : /BoardList.bo 호출 ");
			System.out.println(" C : 패턴3 - DB사용O, 페이지 출력");
			
			// BoardListAction
			action = new BoardListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}		
		}
		else if(command.equals("/BoardContent.bo")) {
			System.out.println(" C : /BoardContent.bo 호출");
			System.out.println(" C : 패턴3 - 디비 사용O,페이지 출력 ");
			
			// BoardContentAction 객체 
			action = new BoardContentAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (command.equals("/BoardUpdate.bo")) {
			System.out.println(" C : /BoardUpdate.bo 호출");
			System.out.println(" C : 패턴3 - 디비사용 O, 페이지 출력");
			
			//BoardUpdateAction 
			action = new BoardUpdateAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/BoardUpdatePro.bo")) {
			System.out.println(" C : /BoardUpdatePro.bo 호출");
			System.out.println(" C : 패턴2 - DB 사용 O, 페이지 이동 ");
			
			// BoardUpdateProAction 객체 
			action = new BoardUpdateProAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (command.equals("/BoardDelete.bo")) {
			System.out.println(" C : /BoardDelete.bo 호출");
			System.out.println(" C : 패턴1 - DB사용 X, 페이지 이동");
			
			forward = new ActionForward(); 
			forward.setPath("./board/boardDelete.jsp");
			forward.setRedirect(false);
		}
		else if(command.equals("/BoardDeleteAction.bo")) {
			System.out.println(" C : /BoardDeleteAction.bo 호출");
			System.out.println(" C : 패턴2 - DB사용, 페이지 이동");
			
			// BoardDeleteAcrion() 객체 
			action = new BoardDeleteAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (command.equals("/BoardReWrite.bo")) {
			System.out.println(" C : /BoardReWrite.bo 호출");
			System.out.println(" C : 패턴1 - DB사용 X,페이지이동");
			
			forward = new ActionForward();
			forward.setPath("./board/boardReWrite.jsp");
			forward.setRedirect(false);
		}
		else if (command.equals("/BoardReWriteAction.bo")) {
			System.out.println(" C : /BoardReWriteAction.bo 호출");
			System.out.println(" C : 패턴2 - DB 사용O , 페이지 이동 ");
			
			// BoardReWriteAction 객체 생성
			action = new BoardReWriteAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (command.equals("/BoardFileWrite.bo")) {
			System.out.println(" C : /BoardFileWrite.bo 호출");
			System.out.println(" C : 패턴1 - DB사용 X, 페이지 이동");
			
			forward = new ActionForward();
			forward.setPath("./board/boardFileWrite.jsp");
			forward.setRedirect(false);
			
		}
		else if (command.equals("/BoardFileWriteAction.bo")) {
			System.out.println(" C : /BoardFileWriteAction.bo 호출");
			System.out.println(" C : 패턴2 - DB사용 O, 페이지 이동");
			
			// /BoardFileWriteAction 객체
			action = new BoardFileWriteAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (command.equals("/BoardFileDown.bo")) {
			System.out.println(" C : /BoardFileDown.bo 호출");
			System.out.println(" C : 패턴3 - DB사용O , 페이지출력");
			
			// BoardFileDownAction() 객체 
//			action = new BoardFileDownAction();
			action = new BoardFileDownAction2();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		}
		
		System.out.println(" -----------2. 가상주소 비교 끝 ---------------");
		////////////////////2. 가상주소 비교////////////////////////////////
		
		////////////////////3. 가상주소 페이지이동 ////////////////////////////////
		System.out.println("\n\n -----------3. 가상주소 페이지이동 시작 ---------------");
		if(forward != null) {
			if(forward.isRedirect()) {
				System.out.println(" C : 주소 ="+forward.getPath()+",방식 : sendRedirect()");
				response.sendRedirect(forward.getPath());
			}else {
				System.out.println(" C : 주소 ="+forward.getPath()+",방식 : forward(request, response)");
				RequestDispatcher dis 
				   = request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
			}		
		}
		System.out.println(" -----------3. 가상주소 페이지이동 끝 ---------------");
		////////////////////3. 가상주소 페이지이동 ////////////////////////////////
		
		
		System.out.println("\n\n -----------컨트롤러 끝 ---------------");
	}
	
	
	//alt shift s + v
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\n\n -----------컨트롤러 시작 ---------------");
		System.out.println(" C : BoardFrontController-doGet() 호출");
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\n\n -----------컨트롤러 시작 ---------------");
		System.out.println(" C : BoardFrontController-doPost() 호출");
		doProcess(request, response);
	}
	

	
	

}
