package com.itwillbs.board.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 * BoardDAO (Data Access Object) : 데이터(DB) 처리하는 객체 
 * => DB관련된 모든 처리를 수행
 */
public class BoardDAO {
	// 공용변수 선언
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = "";

	// 메서드 선언 -> 수행하는 동작 
	
	// 디비 연결 메서드 - getConnect()
//	public Connection getConnect() throws Exception {
//		System.out.println(" DAO : getConnect() 실행 ");
//		System.out.println(" DAO : 1,2단계를 한번에 처리 -> 연결정보(con) 생성");
//		// 디비연결정보
//		final String DRIVER = "com.mysql.cj.jdbc.Driver";
//		final String DBURL = "jdbc:mysql://localhost:3306/jspdb";
//		final String DBID = "root";
//		final String DBPW = "1234";
//		
//		// 1. 드라이버 로드
//		Class.forName(DRIVER);
//		System.out.println(" 드라이버 로드 성공! ");
//		// 2. 디비연결
//		con = DriverManager.getConnection(DBURL, DBID, DBPW);
//		System.out.println(" 디비연결 성공!! ");
//		System.out.println("con : "+con);
//		return con;
//	}	
	
	public Connection getConnect() throws Exception {
		System.out.println(" DAO : getConnect() 실행 ");
		System.out.println(" DAO : 1,2단계를 한번에 처리 -> 연결정보(con) 생성");
		System.out.println(" DAO : 커넥션풀(CP)을 사용 ");
		
		// 디비 연결정보를 가져오기 (/META-INF/context.xml)
		
		// 프로젝트 정보 초기화
		Context initCTX = new InitialContext();

		// conxtext.xml 파일(jdbc/jsp6 이름) 접근 -> DataSource형태로 변경 
		DataSource ds = (DataSource) initCTX.lookup("java:comp/env/jdbc/mvc6");
		
		// 연결정보 객체를 사용해서 디비 연결
		con = ds.getConnection();
		
		return con;
	}	
	// 디비 연결 메서드 - getConnect()
	
	
	// 디비 자원해제 메서드 - closeDB()
	// 디비 작업시 필수 동작
	public void closeDB() {
		try {
			if(rs != null) {
				rs.close();
			}
			if(pstmt != null) {
				pstmt.close();
			}
			if(con != null) {
				con.close();
			}
			System.out.println(" DAO : 자원해제 완료 ");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// 디비 자원해제 메서드 - closeDB()
	
	
	
	// 글쓰기 - insertBoard();
	public void insertBoard(BoardDTO bb) throws Exception  {
		// 글번호 저장하는 변수
		int bno = 0;
		
		// 사용자가 입력한 데이터를 DB에 저장
		// System.out.println(" 전달정보 : "+bb);
		System.out.println(" DAO : insertBoard(bb) 호출-시작 ");
		
		/*
		 * // 1. 드라이버 로드 Class.forName(DRIVER); System.out.println(" 드라이버 로드 성공! "); //
		 * 2. 디비연결 Connection con = DriverManager.getConnection(DBURL, DBID, DBPW);
		 * System.out.println(" 디비연결 성공!! ");
		 */
		// 1.2. 디비연결
		con = getConnect();		
		
		// 3. SQL 구문 작성 & pstmt 객체 
		// 글번호(bno) 계산하기 
		sql = "select max(bno) from itwill_board";
		pstmt = con.prepareStatement(sql);
		// 4. SQL 구문 실행
		rs = pstmt.executeQuery();
		// 5. 데이터 처리
		if(rs.next()) { //select문의 실행결과 커서가 있을때  게시판 작성된 글이 있음
			System.out.println(" @@@@@@@@@@ 게시판 글 있음 @@@@@@@");
			//bno = rs.getInt("bno"); (x)
			// rs.getInt() => 실행결과가 SQL NULL일때, 0을 리턴
			//bno = rs.getInt("max(bno)") + 1;
			//  1번 인덱스 컬럼의 값을 가져오기
			bno = rs.getInt(1) + 1;
		}
//		else { // 게시판 작성된 글이 없음
//			System.out.println(" @@@@@@@@@@ 게시판 글 없음 @@@@@@@");
//			bno = 1;
//		}
		System.out.println(" DAO : 글번호 - "+bno);
		
		// 글쓰기 동작 처리 (insert)
		// 3. sql 작성 & pstmt 객체
		sql = "insert into itwill_board (bno,name,pass,subject,content,readcount,"
				+ "re_ref,re_lev,re_seq,date,ip,file) "
				+ "values(?,?,?,?,?,?,?,?,?,now(),?,?)";
		pstmt = con.prepareStatement(sql);
		// ???
		pstmt.setInt(1, bno);
		pstmt.setString(2, bb.getName());
		pstmt.setString(3, bb.getPass());
		pstmt.setString(4, bb.getSubject());
		pstmt.setString(5, bb.getContent());
		
		pstmt.setInt(6, 0); //모든글 생성시 조회수는 0
		pstmt.setInt(7, bno); // ref = bno (그룹번호, 일반글의 번호와 동일)
		pstmt.setInt(8, 0);   // lev = 0 (답글 들여쓰기, 일반글은 항상 0)
		pstmt.setInt(9, 0);   // seq = 0 (답글 순서, 일반글은 항상 0)
		
		pstmt.setString(10, bb.getIp());
		pstmt.setString(11, bb.getFile());
		
		// 4. sql 실행
		pstmt.executeUpdate();
		System.out.println(" 글쓰기 완료! ");
		
		closeDB();
		
		System.out.println(" DAO : insertBoard(bb) 호출-끝 ");	
	}
	// 글쓰기 - insertBoard();
	
	
	// 글 리스트조회 - boardList()
	public ArrayList boardList() throws Exception {
		ArrayList boardList = new ArrayList();
//		List boardList = new ArrayList();
		
		System.out.println(" DAO : boardList() 실행");
		
		/*
		 * // 1. 드라이버로드 Class.forName(DRIVER); System.out.println(" 드라이버 로드 성공! "); //
		 * 2. 디비연결 Connection con = DriverManager.getConnection(DBURL, DBID, DBPW);
		 * System.out.println(" 디비연결 성공! ");
		 */
		// 1.2. 디비 연결
		con = getConnect();		
		
		// 3. sql 구문 (select)& pstmt 객체
		sql = "select * from itwill_board";
		pstmt = con.prepareStatement(sql);
		// 4. sql 실행		
		rs = pstmt.executeQuery();
		// 5. 데이터처리
		//    rs(select문의 결과) -> 글 하나의 정보를 저장 객체 -> ArrayList 저장
		//                               BoardBean
		while(rs.next()) {
			// rs -> BoardBean 저장
			BoardDTO bb = new BoardDTO();
			
			bb.setBno(rs.getInt("bno"));
			bb.setContent(rs.getString("content"));
			bb.setDate(rs.getTimestamp("date"));
			bb.setFile(rs.getString("file"));
			bb.setIp(rs.getString("ip"));
			bb.setName(rs.getString("name"));
			bb.setPass(rs.getString("pass"));
			bb.setRe_lev(rs.getInt("re_lev"));
			bb.setRe_ref(rs.getInt("re_ref"));
			bb.setRe_seq(rs.getInt("re_seq"));
			bb.setReadcount(rs.getInt("readcount"));
			bb.setSubject(rs.getString("subject"));
			
			// BoardBean -> ArrayList 한칸에 저장
			boardList.add(bb);
			
		} //while
		
		System.out.println(" 게시판 목록 조회 성공! ");
		System.out.println(boardList.size());
		
		System.out.println(" DAO : boardList() 실행");
		return boardList;
	}
	// 글 리스트조회 - boardList()
	 
	// 글의 개수계산 - getBoardCount()
	public int getBoardCount() {
		System.out.println(" DAO : getBoardCount() 호출 ");
		System.out.println(" DAO : 실행목적 : 글의 개수(int) 리턴");
		int result = 0;
		
		try {
			// 1.2. 디비연결
			con = getConnect();
			// 3. SQL 작성(select) & pstmt 객체
			sql = "select count(*) from itwill_board";
			pstmt = con.prepareStatement(sql);
			// 4. SQL 실행
			rs = pstmt.executeQuery();
			// 5. 데이터 처리
			if(rs.next()) {
				//result = rs.getInt(1);
				result = rs.getInt("count(*)");
			}
			System.out.println(" DAO : 글의 개수 조회 완료! ");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
			/*
			 * try { // 작업처리가 완료후(성공,실패) 자원해제 if( con != null ) { con.close(); } if( pstmt
			 * != null ) { pstmt.close(); } if( rs != null ) { rs.close(); } } catch
			 * (SQLException e) { e.printStackTrace(); }
			 */
		}
		
		
		return result;
	}
	// 글의 개수계산 - getBoardCount()
	
	
	// 글 리스트 정보 가져오기(페이징처리) - getBoardListPage(startRow,pageSize)
	public List<BoardDTO> getBoardListPage(int startRow,int pageSize){
		System.out.println(" DAO : getBoardListPage(startRow,pageSize) 호출 ");
		
		// 글 리스트 저장하는 객체
		List<BoardDTO> boardList = new ArrayList<BoardDTO>();
		
		try {
			// 1.2. 디비연결 (커넥션풀)
			con = getConnect();
			// 3. sql 구문 작성 & pstmt 객체
			// 게시판 글 리스트 원하는 만큼만  조회
			//   limit 시작위치,개수 
			//      : 위치(인덱스)에서 개수만큼 데이터를 짤라서 가져오기 
			//            정렬  re_ref(그룹번호) 내림차순
			//                  re_seq(답글순서) 오름차순
			sql ="select * from itwill_board "
					+ "order by re_ref desc, re_seq asc "
					+ "limit ?,?";
			pstmt = con.prepareStatement(sql);
			// ????
			pstmt.setInt(1, startRow-1); // (시작위치-1)  startRow 1 
			pstmt.setInt(2, pageSize); //  개수 pageSize 10
			// 4. sql 실행
			rs = pstmt.executeQuery();
			// 5. 데이터처리 (rs -> BoardBean -> List)
			while(rs.next()) {
				// rs -> BoardBean
				BoardDTO bb = new BoardDTO();
				
				bb.setBno(rs.getInt("bno"));
				bb.setContent(rs.getString("content"));
				bb.setDate(rs.getTimestamp("date"));
				bb.setFile(rs.getString("file"));
				bb.setIp(rs.getString("ip"));
				bb.setName(rs.getString("name"));
				bb.setPass(rs.getString("pass"));
				bb.setRe_lev(rs.getInt("re_lev"));
				bb.setRe_ref(rs.getInt("re_ref"));
				bb.setRe_seq(rs.getInt("re_seq"));
				bb.setReadcount(rs.getInt("readcount"));
				bb.setSubject(rs.getString("subject"));
				
				// BoardBean -> List 
				boardList.add(bb);
				
			}// while
			
			System.out.println(" DAO : (페이징처리된) 글 리스트를 저장");
			System.out.println(" DAO : 리스트 사이즈 : "+boardList.size());
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return boardList;
	}	
	// 글 리스트 정보 가져오기(페이징처리) - getBoardListPage(startRow,pageSize)
	
	// 글 조회수 1증가 - updateReadCount(bno)
	public void updateReadCount(int bno) {
		System.out.println(" DAO : 글 조회수 1증가 - updateReadCount(bno) 호출 ");
		try {
			// 1.2. 디비연결
			con = getConnect();
			// 3. sql 구문 & pstmt 객체 
			//  sql -> 기존의 조회수 + 1(update)
			sql = "update itwill_board set readcount = readcount + 1 "
					+ "where bno = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bno);
			
			// 4. sql 실행
			pstmt.executeUpdate();
			
			System.out.println(" DAO : 글 조회수 1증가 ");			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}		
	}	
	// 글 조회수 1증가 - updateReadCount(bno)
	
	// 글정보 (1개) 가져오기 - getBoard(bno)
	public BoardDTO getBoard(int bno) {
		System.out.println(" DAO : 글정보 (1개) 가져오기 - getBoard(bno) 호출 ");
		BoardDTO bb = null;
		try {
			// 1.2. 디비연결
			con = getConnect();
			// 3. sql 작성(select) & pstmt객체
			// sql -> bno에 해당하는 글정보만 조회
			sql = "select * from itwill_board "
					+ " where bno=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bno);
			// 4. sql 실행
			rs = pstmt.executeQuery();
			// 5. 데이터 처리 (rs -> 객체(BoardBean))
			if(rs.next()) {
				bb = new BoardDTO();
				
				// rs -> bb 저장
				bb.setBno(rs.getInt("bno"));
				bb.setContent(rs.getString("content"));
				bb.setDate(rs.getTimestamp("date"));
				bb.setFile(rs.getString("file"));
				bb.setIp(rs.getString("ip"));
				bb.setName(rs.getString("name"));
				bb.setPass(rs.getString("pass"));
				bb.setRe_lev(rs.getInt("re_lev"));
				bb.setRe_ref(rs.getInt("re_ref"));
				bb.setRe_seq(rs.getInt("re_seq"));
				bb.setReadcount(rs.getInt("readcount"));
				bb.setSubject(rs.getString("subject"));
			} // if
			System.out.println(" DAO : "+bno+"번 글정보 저장완료!");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		//글정보 리턴
		return bb;
	}	
	// 글정보 (1개) 가져오기 - getBoard(bno)
	
	
	// 글 정보 수정하기 - updateBoard(bb)
	public int updateBoard(BoardDTO bb) {
		int result = -1;  //  -1(에러-계정)  0(에러-비밀번호)  1(정상)
		
		try {
			// 1.2. 디비연결
			con = getConnect();
			// 3. sql 구문작성(기존의 게시판 글 여부확인) & pstmt 객체
			sql = "select pass from itwill_board where bno=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bb.getBno());
			// 4. sql 실행		
			rs = pstmt.executeQuery();
			// 5. 데이터 처리 
			if(rs.next()) {
				if(bb.getPass().equals(rs.getString("pass"))) {
					// 3. sql 구문 실행(update)
					sql = "update itwill_board set name=?,subject=?,content=? "
							+ "where bno=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, bb.getName());
					pstmt.setString(2, bb.getSubject());
					pstmt.setString(3, bb.getContent());
					pstmt.setInt(4, bb.getBno());
					// 4. sql문 실행
					pstmt.executeUpdate();
					System.out.println(" DAO : 글 정보 수정완료! ");
					
					result = 1;
				}else {
					result = 0;	
				}
			}else {
				result = -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return result;
	}
	// 글 정보 수정하기 - updateBoard(bb)
	
	
	// 글 정보 삭제하기 - deleteBoard(bb)
	public int deleteBoard(BoardDTO bb) {
		int result = -1;
		
		try {
			// 1.2. 디비연결
			con = getConnect();
			// 3. sql 구문작성(기존의 게시판 글 여부확인) & pstmt 객체
			sql = "select pass from itwill_board where bno=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bb.getBno());
			// 4. sql 실행		
			rs = pstmt.executeQuery();
			// 5. 데이터 처리 
			if(rs.next()) {
				if(bb.getPass().equals(rs.getString("pass"))) {
					// 3. sql 구문 실행(delete)
					sql = "delete from itwill_board where bno=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, bb.getBno());
					// 4. sql문 실행
					pstmt.executeUpdate();
					System.out.println(" DAO : 글 정보 삭제완료! ");
					
					result = 1;
				}else {
					result = 0;	
				}
			}else {
				result = -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return result;
	}
	
	// 글 정보 삭제하기 - deleteBoard(bb)
	
	
	// 답글 쓰기 - reInsertBoard(bb)
	public void reInsertBoard(BoardDTO bb) {
		int bno = 0;
		try {
			// 1.2.  디비연결
			con = getConnect();
			/*******************1) bno 계산*********************/
			// 3. sql 구문 작성 & pstmt 객체
			sql = "select max(bno) from itwill_board";
			pstmt = con.prepareStatement(sql);
			// 4. sql 구문 실행
			rs = pstmt.executeQuery();
			// 5. 데이터 처리 
			if(rs.next()) {
				bno = rs.getInt(1)+1;
			}
			
			System.out.println("1) bno 계산 완료");
			/*******************2) 답글정보(seq) 재배치(수정) ******************/
			// 3. sql 구문 작성 & pstmt 객체
			//  같은그룹(re_ref), 순서값(re_seq) => 
			// 같은그룹에 있는 값중에서  re_seq기존의 데이터 보다 큰값이 있는지 
			sql = "update itwill_board set re_seq=re_seq+1 "
					+ "where re_ref=? and re_seq>?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bb.getRe_ref());
			pstmt.setInt(2, bb.getRe_seq());
			// 4. sql 구문 실행
			int result = pstmt.executeUpdate();
			
			if(result >= 1) {
				System.out.println(" DAO : 답글 순서 재배치! (seq+1) ");
			}
			/*******************3) 글 작성(insert)************************/
			// 3. sql 구문 작성 & pstmt 객체
			sql = "insert into itwill_board (bno,name,pass,subject,content,readcount,"
					+ "re_ref,re_lev,re_seq,date,ip,file) "
					+ "values(?,?,?,?,?,?,?,?,?,now(),?,?)";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, bno);
			pstmt.setString(2, bb.getName());
			pstmt.setString(3, bb.getPass());
			pstmt.setString(4, bb.getSubject());
			pstmt.setString(5, bb.getContent());
			pstmt.setInt(6, bb.getReadcount());
			pstmt.setInt(7, bb.getRe_ref()); // 원글 ref값
			pstmt.setInt(8, bb.getRe_lev()+1); // 원글 lev+1
			pstmt.setInt(9, bb.getRe_seq()+1); // 원글 seq+1
			pstmt.setString(10, bb.getIp());
			pstmt.setString(11, bb.getFile());
			// 4. sql 구문 실행
			pstmt.executeUpdate();
			
			System.out.println(" DAO : 답글 작성완료! ");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
	}	
	// 답글 쓰기 - reInsertBoard(bb)
	
	

} // DAO
