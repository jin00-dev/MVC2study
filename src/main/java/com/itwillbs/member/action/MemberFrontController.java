package com.itwillbs.member.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.util.Action;
import com.itwillbs.util.ActionForward;

// 컨트롤러의 동작 수행 : model(java) - view (Jsp) 연결
// 실행 : http://localhost:8088/JspMVC6/member 
// 실행 : http://localhost:8088/JspMVC6/*.me  

public class MemberFrontController extends HttpServlet {

	// 쉬프트 + 알트 + S -> V 
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\n C : MemberFrontController - doProcess() 호출");
		System.out.println("C : Get/Post 방식 상관없이 모든 주소 처리");
		
		/****************1. 가상주소 계산 ****************/
		System.out.println("\n C : 1. 가상주소 계산 - 시작 ");
		// 가상주소 : http://localhost:8088/JspMVC6/MemberJoin.me
		//주소정보 (URI)
		String requestURI = request.getRequestURI();	
		System.out.println("requestURI : " + requestURI);
		// 프로젝트 명 가져오기! 
		String CTXPath = request.getContextPath();
		System.out.println("CTXPath : " + CTXPath );
		// 최종! 계산된 가상주소 (URI - 프로젝트 명) 
		String command = requestURI.substring(CTXPath.length());
		System.out.println("command : "+ command);
		System.out.println("\n C : 1. 가상주소 계산 - 끝 ");
		/****************1. 가상주소 계산 ****************/

		/****************2. 가상주소 매핑 ****************/
		System.out.println("\n C : 2. 가상주소 매핑 - 시작 ");
		
		Action action = null; 
		ActionForward forward = null;
		//회원가입 - 패턴 1 
		if (command.equals("/MemberJoin.me")) {
			System.out.println(" C : /MemberJoin.me 매핑 ");
			System.out.println(" C : 패턴 1 - DB사용 X, 화면이동");
			
			// 이동할 티켓만 만들어 놓은 것!  / 페이지 이동에 필요한 정보를 저장하는 객체
			forward = new ActionForward();
			forward.setPath("./member/insertForm.jsp");
			forward.setRedirect(false);
		}
		//회원가입 처리 - 패턴 2  
		else if (command.equals("/MemberJoinAction.me")){
			System.out.println("C : MemberJoinAction.me 매핑");
			System.out.println("C : 패턴 2 - DB사용 O, 화면 이동");
			// 객체 생성 (MemberjoinAction불러오기) + 예외처리 
//			MemberJoinAction joinAction = new MemberJoinAction();
			action = new MemberJoinAction();
			try {
//				forward = joinAction.execute(request, response);
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		else if (command.equals("/MemberLogin.me")) {
			// 로그인 처리 
			System.out.println("C : /MemberLogin.me 호출");
			System.out.println("C : 패턴1 - 디비사용X, 화면 이동");
			
			forward = new ActionForward();
			forward.setPath("./member/loginForm.jsp");
			forward.setRedirect(false);
		}
		else if (command.equals("/MemberLoginAction.me")) {
			// 로그인 처리 
			System.out.println("C : /MemberLoginAction.me 호출");
			System.out.println("C : 패턴2 - 디비 사용O , 화면 이동 O");
			
			action = new MemberLoginAction();
			try {
			 	forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		else if (command.equals("/Main.me")) {
			//메인페이지 이동
			System.out.println("C : /Main.me 호출");
			System.out.println("C : 패턴1 - DB사용X , 화면 이동");
			
			forward = new ActionForward();
			forward.setPath("./member/main.jsp");
			forward.setRedirect(false);
		}
		else if (command.equals("/MemberLogout.me")) {
			// 로그아웃 처리 
			System.out.println("C : /MemberLogout.me 호출");
			System.out.println("C : 패턴2 - 처리동작O, 화면 이동");
			// 왜 패턴 2번이지? DB사용 = 처리동작이 있는지 없는지!
			
			// MemberLogoutAction 객체 
			action = new MemberLogoutAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/MemberInfo.me")) {
			System.out.println("C : /MemberInfo.me 호출");
			System.out.println("C : 패턴3 - 처리동작(DB)O, 화면 출력");
			
			//MemberInfoAction 객체 
			action = new MemberInfoAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/MemberUpdate.me")) {
			System.out.println(" C : /MemberUpdate.me 호출 ");
			System.out.println(" C : 패턴3 - DB사용 o,화면출력 ");
			
			// MemberUpdateAction() 객체 생성
			action = new MemberUpdateAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}
		else if(command.equals("/MemberUpdatePro.me")) {
			System.out.println(" C : /MemberUpdatePro.me 호출 ");
			System.out.println(" C : 패턴2 - 데이터처리(DB사용)O, 페이지이동 ");
			
			// MemberUpdateProAction() 객체 
			action = new MemberUpdateProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (command.equals("/MemberDelete.me")) {
			System.out.println("C : /MemberDelete.me 호출");
			System.out.println("C : 패턴1 - DB 사용 X, 페이지 이동");
			
			forward = new ActionForward();
			forward.setPath("./member/deleteForm.jsp");
			forward.setRedirect(false);
		}
		else if (command.equals("/MemberDeleteAction.me")) {
			System.out.println("C : /MemberDeleteAction.me 호출");
			System.out.println("C : 패턴2 - DB사용 O, 페이지 이동");

			// MemberDeleteAction() 객체 생성
			action = new MemberDeleteAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/MemberList.me")) {
			System.out.println(" C : /MemberList.me 호출");
			System.out.println(" C :  패턴3 - DB사용 O, 페이지 출력");
			
			// MemberListAction() 객체 
			action = new MemberListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("\n C : 2. 가상주소 매핑 - 끝 ");
		/****************2. 가상주소 매핑 ****************/

		/****************3. 가상주소 이동 ****************/
		System.out.println("\n C : 3. 가상주소 이동 - 시작 ");
		if (forward != null) {
			if(forward.isRedirect()) {
				System.out.println("C : "+ forward.getPath()+"주소로 방식 :" +forward.isRedirect());
				response.sendRedirect(forward.getPath());
			}else {
				System.out.println("C : "+ forward.getPath()+"주소로 방식 :" +forward.isRedirect());
				RequestDispatcher dis = 
						request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
			}
		}
		
		
		System.out.println("\n C : 3. 가상주소 이동 - 끝 ");
		/****************3. 가상주소 이동 ****************/
		System.out.println("\n\n------------------컨트롤러 끝---------------------");
		
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\n\n------------------컨트롤러 시작---------------------");
		System.out.println("C : MemberFrontController - doGet() 호출");
		doProcess(request, response);
	
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\n\n------------------컨트롤러 시작---------------------");
		System.out.println("C : MemberFrontController - doPost() 호출");
		doProcess(request, response);
		
	}
	
}
