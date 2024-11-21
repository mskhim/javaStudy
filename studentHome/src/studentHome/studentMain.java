package studentHome;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class studentMain implements StudentNumInt {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws SQLException {
		int mNum = 0;
		boolean exitFlag = false;
		System.out.println("매뉴 번호를 입력해주세요.");
		while (!exitFlag) {
			printMenu();
			try {
				 mNum=Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.println("숫자를 입력해주세요.");
			}
			switch (mNum) {
			case PRINT:
				printStu();
				break;
			case INSERT:
				insertStu();
				break;
			case UPDATE:
				updateStu();
				break;
			case DELETE:
				deleteStu();
				break;
			case RANK:
				rankStu();
				break;
			case RAND:
				autoStu();
				break;
			case FIND:
				find();
				break;
			case FIN:
				exitFlag = true;
				break;
			case GARBAGE:
				garbage();
				break;
			default:
				break;
			}

		}

	}

	//1. 전체 출력해주는 함수
	private static void printStu() throws SQLException {
		System.out.println("출력중입니다...");
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		con = DBConnection.dbCon();
		stmt = con.createStatement();
		stmt.executeUpdate("CALL STU_CAL_PROC()");
		rs = stmt.executeQuery("SELECT * FROM STUDENT2 order by code");
		printRs(rs);

		DBConnection.dbClose(con, stmt, rs);
	}

	//2. 학생 추가
	private static void insertStu() throws SQLException {
			System.out.println("학생을 추가합니다. 정보를 입력해주세요.");
			System.out.print("학생 이름 : ");
			String name = sc.nextLine();
			System.out.print("학생 생일(yyyy/mm/dd 형태로 입력해주세요) : ");
			String birth = sc.nextLine();
			System.out.print("학생 국어 : ");
			int KOR = Integer.parseInt(sc.nextLine());
			System.out.print("학생 수학 : ");
			int MATH = Integer.parseInt(sc.nextLine());
			System.out.print("학생 영어 : ");
			int ENG = Integer.parseInt(sc.nextLine());
			Connection con = null;
			Statement stmt = null;
			con = DBConnection.dbCon();
			stmt = con.createStatement();
			student st = new student(0, name, birth, KOR, MATH, ENG);
			PreparedStatement pstmt = con.prepareStatement("insert into student2(code, name, birth, KOR, MATH, ENG) "
					+ "values((select nvl(max(code),0)+1 from student2),?,?,?,?,?)");
			pstmt.setString(1, st.getName());
			pstmt.setString(2, st.getBirth());
			pstmt.setInt(3, st.getKor());
			pstmt.setInt(4, st.getMath());
			pstmt.setInt(5, st.getEng());
			int rs = pstmt.executeUpdate();
			System.out.println((rs != 0) ? "추가 완료" : "추가 실패");
			stmt.executeUpdate("CALL STU_CAL_PROC()");
			DBConnection.dbClose(con, stmt);

		}
	
	// 3. 학생 정보 수정
	private static void updateStu() throws SQLException {
			System.out.println("학생정보를 수정합니다. 수정할 학생의 코드를 입력해주세요.");
			System.out.print(">>");
			int code = Integer.parseInt(sc.nextLine());
			System.out.println("해당 코드의 학생 >>>>>>>>>>>> ");
			findStudent(code);
			System.out.print("수정할 학생 이름 : ");
			String name = sc.nextLine();
			System.out.print("수정할 학생 생일(yyyy/mm/dd 형태로 입력해주세요) : ");
			String birth = sc.nextLine();
			System.out.print("수정할 학생 국어 : ");
			int kor = Integer.parseInt(sc.nextLine());
			System.out.print("수정할 학생 수학 : ");
			int math = Integer.parseInt(sc.nextLine());
			System.out.print("수정할 학생 영어 : ");
			int eng = Integer.parseInt(sc.nextLine());
			Connection con = null;
			Statement stmt = null;
			PreparedStatement pstmt = null;
			con = DBConnection.dbCon();
			stmt = con.createStatement();

			//수정전 학생정보 출력해줌
			//수정된 학생 찾아서 출력해줌
			findStudent(code);
			System.out.println("수정전▲▲▲▲▲▲▲▲▲");
			System.out.println("수정후▼▼▼▼▼▼▼▼▼");
			//pstmt 작성
			pstmt = con.prepareStatement("update student2 set name=?,birth=?,kor=?,math=?,eng=? where code=?");
			student st = new student(code, name, birth, kor, math, eng);
			pstmt.setString(1, st.getName());
			pstmt.setString(2, st.getBirth());
			pstmt.setInt(3, st.getkor());
			pstmt.setInt(4, st.getMath());
			pstmt.setInt(5, st.getEng());
			pstmt.setInt(6, st.getCode());
			int rsb = pstmt.executeUpdate();
			//정렬
			stmt.executeUpdate("CALL STU_CAL_PROC()");
			//수정된 학생 찾아서 출력해줌
			findStudent(code);
			System.out.println((rsb != 0) ? "수정 완료" : "수정 실패");
			DBConnection.dbClose(con, stmt);
		}

	// 4. 학생 정보 삭제
	private static void deleteStu() throws SQLException {
			System.out.println("학생정보를 삭제합니다. 삭제할 학생의 코드를 입력해주세요.");
			System.out.print(">>");
			int code = Integer.parseInt(sc.nextLine());
			Connection con = null;
			Statement stmt = null;
			ResultSet rs = null;
			con = DBConnection.dbCon();
			stmt = con.createStatement();
			findStudent(code);
			rs = stmt.executeQuery("select * from student2 where code=" + code);
			int rs1 = stmt.executeUpdate("delete from student2 where code=" + code);
			System.out.println((rs1 != 0) ? "삭제 완료" : "삭제 실패");
			DBConnection.dbClose(con, stmt, rs);

		}

	// 5. 항목별 정렬
	private static void rankStu() throws SQLException {
		System.out.println("정렬 기준을 선택해주세요.");
		System.out.println("1.code 2.name 3.birth 4.grade(순위표시)");
		System.out.print(">>");
		int sNum = Integer.parseInt(sc.nextLine());
		System.out.println("출력중입니다...");
		Connection con = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		con = DBConnection.dbCon();
		stmt = con.createStatement();
		//평균, 랭크 없을지도 모르니 프로시저 실행
		stmt.executeUpdate("CALL STU_CAL_PROC()");
		String order = null;
		switch (sNum) {
		case CODE: {
			order = " code";
			break;
		}
		case NAME: {
			order = " upper(name)";
			break;
		}
		case BIRTH: {
			order = " TO_DATE(birth,'YYYY/MM/DD')";
			break;
		}
		case GRADE: {
			order = " total desc";
			break;
		}
		}
		rs = stmt.executeQuery("select * from student2 order by"+order);
		printRs(rs);
		DBConnection.dbClose(con, stmt,pstmt, rs);

	}

	// 6. 자동추가
	private static void autoStu() throws SQLException {
		Connection con = null;
		Statement stmt = null;
		con = DBConnection.dbCon();
		stmt = con.createStatement();
		int rs = stmt.executeUpdate(
		"insert into student2(code,name,birth,kor,math,eng) values((select nvl(max(code),0)+1 from student2),dbms_random.string('U',3),round(dbms_random.value(1980,2000),0)||'/'||round(dbms_random.value(1,12),0)||'/'||round(dbms_random.value(1,30),0),round(dbms_random.value(1,100),0),round(dbms_random.value(1,100),0),round(dbms_random.value(1,100),0))");
		
		System.out.println((rs != 0) ? "추가 완료" : "추가 실패");
		stmt.executeUpdate("CALL STU_CAL_PROC()");
		DBConnection.dbClose(con, stmt);

	}

	// 7. 찾기
	private static void find() throws SQLException {
		System.out.println("찾을 학생의 코드를 입력해주세요");
		System.out.print(">>");
		int code = Integer.parseInt(sc.nextLine());
		findStudent(code);
	}
	
	//9. 휴지통
	private static void  garbage() throws SQLException {
		Connection con = null;
		Statement stmt = null;
		int rsb=0;
		con=DBConnection.dbCon();
		stmt=con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM STUDENT_GAR order by deletetime desc");
		printRs(rs);
		System.out.println("휴지통은 최근삭제한 순서대로 표시됩니다. 0 입력시 휴지통 비우기");
		int num =Integer.parseInt(sc.nextLine());
		if (num==0) {
			rsb = stmt.executeUpdate("delete from STUDENT_GAR");
			System.out.println((rsb!=0)?("휴지통 비우기 완료"):("휴지통 비우기 실패"));
		}
	}
	
	//0. 메뉴 프린트 함수
	private static void printMenu() {
		System.out.println("1.출력 2.추가 3.수정 4.삭제 5.항목별 정렬 6.자동생성 7.찾기 8.나가기 9.휴지통");
		System.out.print(">>");

	}

	
	// resultSet을 받아서 전체 출력하는 함수
	private static void printRs(ResultSet rs) throws SQLException {
		ArrayList<student> sList = new ArrayList<student>();
		System.out.println(student.getHeader());
		while (rs.next()) {
			int code = rs.getInt("CODE");
			String name = rs.getString("NAME");
			String birth = rs.getString("BIRTH");
			int kor = rs.getInt("KOR");
			int math = rs.getInt("MATH");
			int eng = rs.getInt("ENG");
			int total = rs.getInt("TOTAL");
			int avg = rs.getInt("AVG");
			int rank = rs.getInt("rank");
			sList.add(new student(code, name, birth, kor, math, eng, total, avg, rank));
		}
		for (student data : sList) {
			System.out.println(data);
		}

	}

	
	// resultSet을 받아서 원하는 코드번호의 학생을 출력
	private static void findStudent(int findCode) throws SQLException {
		Connection con = null;
		Statement stmt = null;
		con = DBConnection.dbCon();
		ResultSet rs = null;
		PreparedStatement pstmt=con.prepareStatement("select * from student2 where code=?");
		pstmt.setInt(1, findCode);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			int code = rs.getInt("CODE");
			String name = rs.getString("NAME");
			String birth = rs.getString("BIRTH");
			int kor = rs.getInt("KOR");
			int math = rs.getInt("MATH");
			int eng = rs.getInt("ENG");
			int total = rs.getInt("TOTAL");
			int avg = rs.getInt("AVG");
			int rank = rs.getInt("rank");
			System.out.println(student.getHeader());
			student st= new student(code, name, birth, kor, math, eng, total, avg, rank);
			System.out.println(st.toString());
		}
	}
	

	
}
