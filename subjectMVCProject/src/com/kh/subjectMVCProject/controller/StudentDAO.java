package com.kh.subjectMVCProject.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.kh.subjectMVCProject.model.StudentVO;


public class StudentDAO {
	public static final String STUDENT_SELECT_SQL = "SELECT * FROM STUDENT";
	public static final String STUDENT_SELECT_SORT_SQL = "SELECT * FROM STUDENT ORDER BY RANK";
	public static final String STUDENT_INSERT_SQL = "INSERT INTO STUDENT(NO, NAME, KOR, ENG, MAT) VALUES(STUDENT3_NO_SEQ.NEXTVAL, ?, ?, ?, ?)";
	public static final String STUDENT_UPATE_SQL = "UPDATE STUDENT SET NAME = ?, KOR = ?, ENG = ?, MAT = ? WHERE NO = ? ";
	public static final String STUDENT_DELETE_SQl = "DELETE FROM STUDENT WHERE NO = ?";
	public static final String STUDENT_CALL_RANK_PROC = "{call STUDENT3_RANK_PROC()}";
	
	
	//StudentVO를 받아서 db에 삽입
	public static boolean sDBInsert(StudentVO svo) throws SQLException {
		// Conection
		Connection con = null;
		CallableStatement cstmt = null;
		PreparedStatement pstmt = null;
		// 1 Load, 2. connect
		con = DBUtility.dbCon();
		// 3.statement
		pstmt = con.prepareStatement(STUDENT_INSERT_SQL);
		pstmt.setString(1, svo.getName());
		int result1 = pstmt.executeUpdate();
		cstmt = con.prepareCall(STUDENT_CALL_RANK_PROC);
		int result2 = cstmt.executeUpdate();
		DBUtility.dbClose(con, pstmt);
		return (result1!=0 && result2 !=0)?true:false;
	}
	//테이블 전체를 ArrayList에 저장
	public static ArrayList<StudentVO> StudentSelectSVO() throws SQLException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<StudentVO> studentList = new ArrayList<StudentVO>();
		con = DBUtility.dbCon();
		stmt = con.prepareCall("{call STUDENT3_RANK_PROC()}");
		stmt = con.createStatement();
		rs = stmt.executeQuery(STUDENT_SELECT_SQL);

		while (rs.next()) {
			int no = rs.getInt("NO");
			String name = rs.getString("NAME");
			int kor = rs.getInt("KOR");
			int eng = rs.getInt("ENG");
			int mat = rs.getInt("MAT");
			int total = rs.getInt("TOTAL");
			int ave = rs.getInt("AVE");
			int rank = rs.getInt("RANK");

			StudentVO stu = new StudentVO();
			studentList.add(stu);
		}
//		stuListPrint(stuList);
		DBUtility.dbClose(con, rs, stmt);
		return studentList;
	}
	//StudentVO를 받아서 db 업데이트
	public static boolean sDBUpdate(StudentVO svo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		con = DBUtility.dbCon();
		pstmt = con.prepareStatement(STUDENT_UPATE_SQL);
		pstmt.setString(1, svo.getName());
		pstmt.setInt(5, svo.getNo());
		
		int result1 = pstmt.executeUpdate();
		pstmt = con.prepareCall(STUDENT_CALL_RANK_PROC);
		int result2 = pstmt.executeUpdate();
		DBUtility.dbClose(con, pstmt);
		return (result1!=0 && result2 !=0)?true:false;
	}
	//student no를 받아서 db 삭제
	public static boolean sDBDelete(StudentVO svo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		con = DBUtility.dbCon();
		pstmt = con.prepareStatement(STUDENT_DELETE_SQl);
		pstmt.setInt(1, svo.getNo());
		int result = pstmt.executeUpdate();
		DBUtility.dbClose(con, pstmt);
		return (result!=0)?true:false;
	}
	//student를 db를 정렬
	public static ArrayList<StudentVO> studentSortSVO() throws SQLException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<StudentVO> stuList = new ArrayList<StudentVO>();

		con = DBUtility.dbCon();
		stmt = con.createStatement();
		rs = stmt.executeQuery(STUDENT_SELECT_SORT_SQL);
		while (rs.next()) {
			int no = rs.getInt("NO");
			String name = rs.getString("NAME");
			int kor = rs.getInt("KOR");
			int eng = rs.getInt("ENG");
			int mat = rs.getInt("MAT");
			int total = rs.getInt("TOTAL");
			int ave = rs.getInt("AVE");
			int rank = rs.getInt("RANK");
			StudentVO stu = new StudentVO();
			stuList.add(stu);
		}
		DBUtility.dbClose(con,rs,stmt);
		return stuList;

	}
	
}

