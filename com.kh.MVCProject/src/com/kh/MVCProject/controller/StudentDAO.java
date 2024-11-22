package com.kh.MVCProject.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.kh.MVCProject.model.StudentVO;
import com.kh.MVCProject.view.StudentInterface;

public class StudentDAO {
	public static String callCalProc = "CALL STU_CAL_PROC()";
	public static String selectSqlGarbage = "SELECT * FROM STUDENT_GAR order by deletetime desc";
	public static String selectSqlOdByCode = "SELECT * FROM STUDENT2 order by code";
	public static String selectSqlOdByName = "SELECT * FROM STUDENT2 order by upper(name)";
	public static String selectSqlOdByBirth = "SELECT * FROM STUDENT2 order by TO_DATE(birth,'YYYY/MM/DD')";
	public static String selectSqlOdByGrade = "SELECT * FROM STUDENT2 order by total desc";
	public static String selectSqlByCode = "SELECT * FROM STUDENT2 where code = ?";
	public static String insertSql = "insert into student2(code, name, birth, KOR, MATH, ENG) values((select nvl(max(code),0)+1 from student2),?,?,?,?,?)";
	public static String insertSqlRandom = "insert into student2(code,name,birth,kor,math,eng) values((select nvl(max(code),0)+1 from student2),dbms_random.string('U',3),round(dbms_random.value(1980,2000),0)||'/'||round(dbms_random.value(1,12),0)||'/'||round(dbms_random.value(1,30),0),round(dbms_random.value(1,100),0),round(dbms_random.value(1,100),0),round(dbms_random.value(1,100),0))";
	public static String updateSql = "update student2 set name=?,birth=?,kor=?,math=?,eng=? where code=?";
	public static String deleteSql = "delete from student2 where code = ?";
	public static String deleteSqlGarbage = "delete from STUDENT_GAR";
	
	
	// 번호를 받고 번호에 맞는 조건으로 order by 하여 arrayList에 저장(1. code 2. name 3. birth 4. grade)
	public static ArrayList<StudentVO> returnListOrderNum(int num) throws SQLException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		con = DBUtility.dbCon();
		stmt = con.createStatement();
		stmt.executeUpdate(callCalProc);
		String sQuery= null;
		switch (num) {
		case StudentInterface.CODE: {
			sQuery=selectSqlOdByCode;
			break;
		}
		case StudentInterface.NAME: {
			sQuery=selectSqlOdByName;
			break;
		}
		case StudentInterface.BIRTH: {
			sQuery=selectSqlOdByBirth;
			break;
		}
		case StudentInterface.GRADE: {
			sQuery=selectSqlOdByGrade;
			break;
		}
		}
		rs= stmt.executeQuery(sQuery);
		ArrayList<StudentVO> sList = new ArrayList<StudentVO>();
		while (rs.next()) {
			int code = rs.getInt("CODE");
			String name = rs.getString("NAME");
			String birth = rs.getString("BIRTH");
			int kor = rs.getInt("KOR");
			int math = rs.getInt("MATH");
			int eng = rs.getInt("ENG");
			int total = rs.getInt("TOTAL");
			double avg = rs.getDouble("AVG");
			int rank = rs.getInt("rank");
			sList.add(new StudentVO(code, name, birth, kor, math, eng, total, avg, rank));
		}
		DBUtility.dbClose(con, stmt, rs);
		return sList;
	}

	//student2 db에서 svo를 받은후 해당 svo 코드에 맞는 정보를 StudentVO로 반환
	public static StudentVO returnStudentVOByCode(StudentVO svo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int findCode = svo.getCode();
		con = DBUtility.dbCon();
		pstmt = con.prepareStatement(selectSqlByCode);
		pstmt.setInt(1, findCode);
		rs = pstmt.executeQuery();
		while(rs.next()) {
			int code = rs.getInt("CODE");
			String name = rs.getString("NAME");
			String birth = rs.getString("BIRTH");
			int kor = rs.getInt("KOR");
			int math = rs.getInt("MATH");
			int eng = rs.getInt("ENG");
			int total = rs.getInt("TOTAL");
			double avg = rs.getDouble("AVG");
			int rank = rs.getInt("rank");
			svo = new StudentVO(code, name, birth, kor, math, eng, total, avg, rank);
		}
			DBUtility.dbClose(con, pstmt, rs);
			return svo;
	}

	// student2 db로 svo를 받은 후에 insert  후 결과를  boolean으로 반환
	public static boolean insertStudentDB(StudentVO svo) throws SQLException {
		Connection con = null;
		Statement stmt = null;
		con = DBUtility.dbCon();
		stmt = con.createStatement();
		PreparedStatement pstmt = con.prepareStatement(insertSql);
		pstmt.setString(1, svo.getName());
		pstmt.setString(2, svo.getBirth());
		pstmt.setInt(3, svo.getKor());
		pstmt.setInt(4, svo.getMath());
		pstmt.setInt(5, svo.getEng());
		int rs1 = pstmt.executeUpdate();
		stmt.executeUpdate(callCalProc);
		DBUtility.dbClose(con, stmt);
		return (rs1 != 0) ? true : false;
	}

	// student2 db를 svo를 받은후 해당 svo코드의 db를 update 후 결과를 boolean 반환
	public static boolean updateStudentDB(StudentVO svo) throws SQLException {
			int rs1=0;
		Connection con = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		con = DBUtility.dbCon();
			stmt = con.createStatement();
			pstmt = con.prepareStatement(updateSql);
			pstmt.setString(1, svo.getName());
			pstmt.setString(2, svo.getBirth());
			pstmt.setInt(3, svo.getKor());
			pstmt.setInt(4, svo.getMath());
			pstmt.setInt(5, svo.getEng());
			pstmt.setInt(6, svo.getCode());
			rs1 = pstmt.executeUpdate();
			stmt.executeUpdate(callCalProc);
			DBUtility.dbClose(con, stmt,pstmt);
			return (rs1 != 0) ? true : false;

	}

	// student2 db를 svo를 받은후 해당 svo 코드의 db를 delete 후 결과를 boolean으로 반환
	public static boolean deleteStudentDB(StudentVO svo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		Statement stmt = null;
		int findCode=svo.getCode();
		con = DBUtility.dbCon();
		stmt = con.createStatement();
		pstmt = con.prepareStatement(deleteSql);
		pstmt.setInt(1, findCode);
		int rs1= pstmt.executeUpdate();
		stmt.executeUpdate(callCalProc);
		DBUtility.dbClose(con, pstmt, stmt);;
		return (rs1 != 0) ? true : false;
	}

	//student2 db 에 학생정보를 자동으로 insert 해주고 결과를 boolean으로 반환
	public static boolean insertStudentDBRandom() throws SQLException {
		Connection con = null;
		Statement stmt = null;
		con = DBUtility.dbCon();
		stmt = con.createStatement();
		int rs1 = stmt.executeUpdate(insertSqlRandom);
		stmt.executeUpdate(callCalProc);
		DBUtility.dbClose(con, stmt);
		return (rs1 != 0) ? true : false;

	}

	//student_gar deletetime desc순으로 orderby 해서 arrayList에 저장후 반환
	public static ArrayList<StudentVO>  returnListGarbage() throws SQLException {
		Connection con = null;
		Statement stmt = null;
		con=DBUtility.dbCon();
		stmt=con.createStatement();
		ResultSet rs = stmt.executeQuery(selectSqlGarbage);
		ArrayList<StudentVO> sList = new ArrayList<StudentVO>();
		while (rs.next()) {
			int code = rs.getInt("CODE");
			String name = rs.getString("NAME");
			String birth = rs.getString("BIRTH");
			int kor = rs.getInt("KOR");
			int math = rs.getInt("MATH");
			int eng = rs.getInt("ENG");
			int total = rs.getInt("TOTAL");
			double avg = rs.getDouble("AVG");
			int rank = rs.getInt("rank");
			sList.add(new StudentVO(code, name, birth, kor, math, eng, total, avg, rank));
		}
		DBUtility.dbClose(con, stmt, rs);
		return sList;
		}
	
	//student_gar 을 전체삭제해주고 결과를 boolean으로 반환 
	public static boolean deleteStudentGarbageDB() throws SQLException {
		Connection con = null;
		Statement stmt = null;
		con = DBUtility.dbCon();
		stmt = con.createStatement();
		int rs1= stmt.executeUpdate(deleteSqlGarbage);
		DBUtility.dbClose(con, stmt);;
		return (rs1 != 0) ? true : false;
	}
}
