package studentHome;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class studentMain implements StudentNumInt {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws SQLException {

		boolean exitFlag = false;
		System.out.println("매뉴 번호를 입력해주세요.");
		while (!exitFlag) {
			printMenu();
			int mNum = Integer.parseInt(sc.nextLine());
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
			case 7:
				autoStu();
				break;
			case FIN:
				exitFlag = true;
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + mNum);
			}

		}

	}

	// 자동추가
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

	// 항목별 정렬
	private static void rankStu() throws SQLException {
		System.out.println("정렬 기준을 선택해주세요.");
		System.out.println("1.code 2.name 3.birth 4.grade(순위표시)");
		int sNum = Integer.parseInt(sc.nextLine());

		System.out.println("출력중입니다...");
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<student> sList = new ArrayList<student>();
		con = DBConnection.dbCon();
		stmt = con.createStatement();
		stmt.executeUpdate("CALL STU_CAL_PROC()");
		String query = "select code, name, birth, kor,math,eng,total,avg,rank from student2 ";
		switch (sNum) {
		case 1: {
			rs = stmt.executeQuery(query + "order by code");
			break;
		}
		case 2: {
			rs = stmt.executeQuery(query + "order by upper(name)");
			break;
		}
		case 3: {
			rs = stmt.executeQuery(query + "order by TO_DATE(birth,'YYYY/MM/DD')");
			break;
		}
		case 4: {
			rs = stmt.executeQuery(query + "order by total desc");
			break;
		}
		}
		printRs(rs, sList);

		DBConnection.dbClose(con, stmt, rs);

	}

	// resultSet을 받아서 전체 출력하는 함수
	private static void printRs(ResultSet rs, ArrayList<student> sList) throws SQLException {
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
			System.out.println(data.toString());
		}

	}

	// 학생 정보 삭제
	private static void deleteStu() throws SQLException {
		System.out.println("학생정보를 삭제합니다. 삭제할 학생의 코드를 입력해주세요.");
		int code = Integer.parseInt(sc.nextLine());
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		con = DBConnection.dbCon();
		stmt = con.createStatement();
		rs = stmt.executeQuery("select * from student2 where code=" + code);

		int rs1 = stmt.executeUpdate("delete from student2 where code=" + code);
		System.out.println((rs1 != 0) ? "삭제 완료" : "삭제 실패");
		DBConnection.dbClose(con, stmt, rs);

	}

	// 학생 정보 수정
	private static void updateStu() throws SQLException {
		System.out.println("학생정보를 수정합니다. 수정할 학생의 코드를 입력해주세요.");
		int code = Integer.parseInt(sc.nextLine());
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
		student st = new student(code, name, birth, KOR, MATH, ENG);
		int rs = stmt.executeUpdate(
				"update student2 set name='" + st.getName() + "',birth='" + st.getBirth() + "',KOR='" + st.getKor()
						+ "',MATH='" + st.getMath() + "',ENG='" + st.getEng() + "' where code=" + st.getCode());
		stmt.executeUpdate("CALL STU_CAL_PROC()");
		System.out.println((rs != 0) ? "수정 완료" : "수정 실패");
		DBConnection.dbClose(con, stmt);
	}

	// 학생 추가
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
		System.out.println("1");
		int rs = stmt.executeUpdate(
				"insert into student2(code, name, birth, KOR, MATH, ENG) values((select nvl(max(code),0)+1 from student2),'"
						+ st.getName() + "','" + st.getBirth() + "'," + st.getKor() + "," + st.getMath() + ","
						+ st.getEng() + ")");
		System.out.println((rs != 0) ? "추가 완료" : "추가 실패");
		stmt.executeUpdate("CALL STU_CAL_PROC()");
		DBConnection.dbClose(con, stmt);

	}

	// 전체 출력해주는 함수
	private static void printStu() throws SQLException {
		System.out.println("출력중입니다...");
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<student> sList = new ArrayList<student>();
		con = DBConnection.dbCon();
		stmt = con.createStatement();
		stmt.executeUpdate("CALL STU_CAL_PROC()");
		rs = stmt.executeQuery("SELECT * FROM STUDENT2 order by code");
		printRs(rs, sList);

		DBConnection.dbClose(con, stmt, rs);
	}

	// 메뉴 프린트 함수
	private static void printMenu() {
		System.out.println("1.출력 2.추가 3.수정 4.삭제 5.항목별 정렬 6.나가기 7.자동생성");

	}

}
