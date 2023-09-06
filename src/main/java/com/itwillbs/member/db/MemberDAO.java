package com.itwillbs.member.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	
	// 공통으로 사용되는 변수 선언 
	private Connection con = null; 
	private PreparedStatement pstmt = null;
	private ResultSet rs = null; 
	private String sql = "";
	
	// 디비 연결 메서드 - getCon () 
	private Connection getCon() throws Exception {
		// 1. Context 객체 정보 생성 
		Context initCTX = new InitialContext(); // 업캐스팅 
		// 2. 필요한 정보 (DB 연결정보) 가져오기 
		DataSource ds = (DataSource)initCTX.lookup("java:comp/env/jdbc/mvc6"); 
		// 초기화한 프로젝트 정보 중 jdbc/mvc6 이라는 이름의 정보를 들고와라 
		// 3. 디비 연결 
		con = ds.getConnection();
		System.out.println("DAO : 디비 연결 성공" + con);
		return con;
	}
	// 디비 연결 메서드 - getCon () 
	
	//디비 자원해제 메서드 - closeDB() 
	public void closeDB() {
		// 디비 동작의 역순으로 자원 해제 
		if (rs != null)
			try {
				if (rs != null)rs.close();
				if (pstmt != null)pstmt.close();
				if (con != null)con.close();
				
				System.out.println("DAO : 자원해제 완료");
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	//디비 자원해제 메서드 - closeDB() 

	//회원가입 처리하는 메서드 - insertMember() 

	public void insertMember(MemberDTO dto) {
		
		// 1.2. 디비 연결
		try {
			con  = getCon();
			// 3. sql 작성 + pstmt 객체
			sql = "insert into itwill_member(id,pw,name,age,gender,email,regdate)"
					+ " values(?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPw());
			pstmt.setString(3, dto.getName());
			pstmt.setInt(4, dto.getAge());
			pstmt.setString(5, dto.getGender());
			pstmt.setString(6, dto.getEmail());
			pstmt.setDate(7, dto.getRegdate());
			
			// 4. sql 실행 
			pstmt.executeUpdate();
			
			System.out.println("DAO : 회원가입 성공");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
	}
	//회원가입 처리하는 메서드 - insertMember() 

	//로그인 정보 체크 - userCheck(id,pw)
		public int userCheck(String id, String pw) {
			// 디비연결
			int result = 0; 
				try {
					con = getCon();
					// sql 작성 (select)& pstmt 객체
					sql = "select pw from itwill_member where id=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, id);
					// sql 실행 
					rs = pstmt.executeQuery();
					// 데이터 처리 
					if (rs.next()) {
						// 회원
						if(pw.equals(rs.getString("pw"))) {
							// 본인 
							result = 1;
						}else {
							result = 0; 
							// 본인이 아니다.
						}	
					}else {
						result = -1; 
						// 비회원
					}	
					
					System.out.println("DAO : 로그인 체크 완료 (" + result + ")");
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					closeDB();
				}
		
			return result;
		}
	
	//로그인 정보 체크 - userCheck(id,pw)
	
	//회원정보 조회 - getMemberInfo(id)
		public MemberDTO getMemberInfo(String id) {
			MemberDTO dto = null; 
			// 디비 연결
			try {
				con = getCon();
				// sql 작성 , pstmt 객체 
				sql = "select * from itwill_member where id = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				// sql 실행
				rs = pstmt.executeQuery();
				// 데이터 처리
				if (rs.next()) {
					dto = new MemberDTO();
					
					dto.setId(rs.getString("id"));
					dto.setPw(rs.getString("pw"));
					dto.setName(rs.getString("name"));
					dto.setEmail(rs.getString("email"));
					dto.setGender(rs.getString("gander"));
					dto.setAge(rs.getInt("age"));
					dto.setRegdate(rs.getDate("regdate"));
				}
				System.out.println("DAO : 회원정보 조회 완료");
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				closeDB();
			}

			return dto; 
		}
		
	//회원정보 조회 - getMemberInfo(id)

		// 회원정보 수정 - memberUpdate(dto)
		public int memberUpdate(MemberDTO dto) {
			int result = -1;
			
			try {
				//1.2. 디비연결
				con = getCon();
				//3. sql 작성(select) & pstmt 객체
				sql = "select pw from itwill_member where id=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, dto.getId());
				//4. sql 실행
				rs = pstmt.executeQuery();
				//5. 데이터 처리
				if(rs.next()) {
					// 회원
					if(dto.getPw().equals(rs.getString("pw"))) {
						// 비밀번호 O
						//3. sql 작성(update) & pstmt 객체
						sql = "update itwill_member set name=?,age=?,gender=? where id=?";
						pstmt = con.prepareStatement(sql);
						pstmt.setString(1, dto.getName());
						pstmt.setInt(2, dto.getAge());
						pstmt.setString(3, dto.getGender());
						pstmt.setString(4, dto.getId());
						
						//4. sql 실행
						result = pstmt.executeUpdate();
						// result = 1;
					}else {
						// 비밀번호 X  0
						result = 0;
					}
				}else {
					// 비회원 -1
					result = -1;
				}
				
				System.out.println(" DAO : 정보수정완료 "+result);
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				closeDB();
			}
			
			return result;
		}	
		// 회원정보 수정 - memberUpdate(dto)
		
		// 회원정보 삭제 - memberDelete (id,pw)
			
		public int memberDelete(String id,String pw) {
			int result = -1; 
			
			try {
				//1.2. 디비연결
				con = getCon();
				//3. sql 작성(select) & pstmt 객체
				sql = "select pw from itwill_member where id=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				//4. sql 실행
				rs = pstmt.executeQuery();
				//5. 데이터 처리
				if(rs.next()) {
					// 회원
					if(pw.equals(rs.getString("pw"))) {
						// 비밀번호 O
						//3. sql 작성(delete) & pstmt 객체
						sql = "delete from itwill_member where id=?";
						pstmt = con.prepareStatement(sql);
						pstmt.setString(1, id);
						
						//4. sql 실행
						result = pstmt.executeUpdate();
						// result = 1;
					}else {
						// 비밀번호 X  0
						result = 0;
					}
				}else {
					// 비회원 -1
					result = -1;
				}
				
				System.out.println(" DAO : 정보수정완료 "+result);
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				closeDB();
			}
			
			return result;
		}
		
		
		// 회원정보 삭제 - memberDelete (id,pw)
		
		// 회원 목록 (관리자) - getMemberList() 
		public ArrayList<MemberDTO> getMemberList(){
			
			ArrayList<MemberDTO> memberList = new ArrayList<MemberDTO>(); //배열준비
			
				try {
					// 1. DB 연결 
					con = getCon();
					// 2. sql 작성(select), pstmt 객체 
					sql = "select * from itwill_member";
					pstmt = con.prepareStatement(sql);
					// 3. sql 실행 
					rs = pstmt.executeQuery();
					// 4. 데이터 처리
					while(rs.next()) {
						// rs -> DTO 저장 -> List(ArrayList)
						MemberDTO dto = new MemberDTO();
						
						dto.setId(rs.getString("id"));
						dto.setPw(rs.getString("pw"));
						dto.setEmail(rs.getString("email"));
						dto.setName(rs.getString("name"));
						dto.setGender(rs.getString("gender"));
						dto.setAge(rs.getInt("age"));
						dto.setRegdate(rs.getDate("regdate"));
						
						// DTO 정보 -> List 저장 
						memberList.add(dto);
					}// while 
					
				System.out.println(" DAO : 회원 목록 저장 완료 ");
					
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					closeDB();
				}
			
			
			return memberList; 
		}
		
		// 회원 목록 (관리자) - getMemberList() 
	
	
	
	
}
