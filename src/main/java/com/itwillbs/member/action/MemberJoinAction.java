package com.itwillbs.member.action;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.member.db.MemberDAO;
import com.itwillbs.member.db.MemberDTO;
import com.itwillbs.util.Action;
import com.itwillbs.util.ActionForward;

//MemberJoinAction - 회원가입에 필요한 동작 처리 
// insertPro.jsp 페이지의 역할 수행 

// 1. 정보 전달 받기 (+한글 인코딩)
// 2. 객체 정보를 사용해서 파라메터 저장 
// 3. DAO객체 생성 - 회원가입 메서드
// 4. 페이지 이동 (로그인 페이지) =>컨트롤러를 타고 움직일것..! = forward가 있어야 함 

// Jsp 내장 객체를 사용 할 수 없음 => 기본 자바 객체(POJO)이기 때문 
// Controller => Model(MemberJoinAction) 호출하는 형태 => 작업처리 (DB관련처리) => 티켓전달 =>Controller로 돌아감 
// 항상 ActionForward를 만들기 위해서..? 사용..? 

public class MemberJoinAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("M : MemberJoinAction_execute() 호출");
//		request.setCharacterEncoding("UTF-8");
		//(+한글 인코딩)
		// 1. 정보 전달 받기 
		// 2. 객체 정보를 사용해서 파라메터 저장 
//			String id = request.getParameter("id");
//			System.out.println("M : id = "+ id);
		// MemberDTO 객체 생성 
		MemberDTO dto = new MemberDTO();
		dto.setId(request.getParameter("id"));
		dto.setPw(request.getParameter("pw"));
		dto.setName(request.getParameter("name"));
		dto.setGender(request.getParameter("gender"));
		dto.setEmail(request.getParameter("email"));
		dto.setAge(Integer.parseInt(request.getParameter("age")));
		dto.setRegdate(new Date(System.currentTimeMillis()));
		
		System.out.println("M : "+dto);

				
		// 3. DAO객체 생성 - 회원가입 메서드
		MemberDAO dao = new MemberDAO();
		dao.insertMember(dto);
	
		// 4. 페이지 이동 (로그인 페이지)
		// => 페이지 이동은 무조건 Controller에서! 
		// => 페이지 이동 정보만 저장 (티켓생성)
		ActionForward forward = new ActionForward(); // 기차 티켓~
		forward.setPath("./MemberLogin.me");
		forward.setRedirect(true);// 이동 방식 결정! 
		
		System.out.println("M : "+ forward);
		System.out.println("M : 실제 페이지 이동 X 페이지이동에 필요한 티켓만 생성");
		
		return forward; // 티켓 전달 
	}
}
