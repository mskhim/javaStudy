package com.kh.subjectMVCProject.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.kh.subjectMVCProject.model.TraineeVO;


public class TraineeDAO {
	public static final String TRAINEE_SELECT_SQL = "SELECT * FROM STUDENT";
	public static final String TRAINEE_SELECT_SORT_SQL = "SELECT * FROM STUDENT ORDER BY RANK";
	public static final String TRAINEE_INSERT_SQL = "INSERT INTO STUDENT(NO, NAME, KOR, ENG, MAT) VALUES(STUDENT3_NO_SEQ.NEXTVAL, ?, ?, ?, ?)";
	public static final String TRAINEE_UPATE_SQL = "UPDATE STUDENT SET NAME = ?, KOR = ?, ENG = ?, MAT = ? WHERE NO = ? ";
	public static final String TRAINEE_DELETE_SQl = "DELETE FROM STUDENT WHERE NO = ?";
	public static final String TRAINEE_CALL_RANK_PROC = "{call STUDENT3_RANK_PROC()}";
	
	
	//StudentVO를 받아서 db에 삽입
	public static boolean sDBInsert(TraineeVO trvo) throws SQLException {
		// Conection
		Connection con = null;
		CallableStatement cstmt = null;
		PreparedStatement pstmt = null;
		// 1 Load, 2. connect
		con = DBUtility.dbCon();
		// 3.statement
		pstmt = con.prepareStatement(TRAINEE_INSERT_SQL);
//		pstmt.setString(1, trvo.getName());
		int result1 = pstmt.executeUpdate();
		cstmt = con.prepareCall(TRAINEE_CALL_RANK_PROC);
		int result2 = cstmt.executeUpdate();
		DBUtility.dbClose(con, pstmt);
		return (result1!=0 && result2 !=0)?true:false;
	}
	//테이블 전체를 ArrayList에 저장
	public static ArrayList<TraineeVO> StudentSelecttrvo() throws SQLException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<TraineeVO> studentList = new ArrayList<TraineeVO>();
		con = DBUtility.dbCon();
		stmt = con.prepareCall("{call STUDENT3_RANK_PROC()}");
		stmt = con.createStatement();
		rs = stmt.executeQuery(TRAINEE_SELECT_SQL);

		while (rs.next()) {
			int no = rs.getInt("NO");
			String name = rs.getString("NAME");
			int kor = rs.getInt("KOR");
			int eng = rs.getInt("ENG");
			int mat = rs.getInt("MAT");
			int total = rs.getInt("TOTAL");
			int ave = rs.getInt("AVE");
			int rank = rs.getInt("RANK");

			TraineeVO stu = new TraineeVO();
			studentList.add(stu);
		}
//		stuListPrint(stuList);
		DBUtility.dbClose(con, rs, stmt);
		return studentList;
	}
	//TraineeVO를 받아서 db 업데이트
	public static boolean sDBUpdate(TraineeVO trvo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		con = DBUtility.dbCon();
		pstmt = con.prepareStatement(TRAINEE_UPATE_SQL);
//		pstmt.setString(1, trvo.getName());
		pstmt.setInt(5, trvo.getNo());
		
		int result1 = pstmt.executeUpdate();
		pstmt = con.prepareCall(TRAINEE_CALL_RANK_PROC);
		int result2 = pstmt.executeUpdate();
		DBUtility.dbClose(con, pstmt);
		return (result1!=0 && result2 !=0)?true:false;
	}
	//student no를 받아서 db 삭제
	public static boolean sDBDelete(TraineeVO trvo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		con = DBUtility.dbCon();
		pstmt = con.prepareStatement(TRAINEE_DELETE_SQl);
		pstmt.setInt(1, trvo.getNo());
		int result = pstmt.executeUpdate();
		DBUtility.dbClose(con, pstmt);
		return (result!=0)?true:false;
	}
	//student를 db를 정렬
	public static ArrayList<TraineeVO> studentSorttrvo() throws SQLException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<TraineeVO> stuList = new ArrayList<TraineeVO>();

		con = DBUtility.dbCon();
		stmt = con.createStatement();
		rs = stmt.executeQuery(TRAINEE_SELECT_SORT_SQL);
		while (rs.next()) {
			int no = rs.getInt("NO");
			String name = rs.getString("NAME");
			int kor = rs.getInt("KOR");
			int eng = rs.getInt("ENG");
			int mat = rs.getInt("MAT");
			int total = rs.getInt("TOTAL");
			int ave = rs.getInt("AVE");
			int rank = rs.getInt("RANK");
			TraineeVO stu = new TraineeVO();
			stuList.add(stu);
		}
		DBUtility.dbClose(con,rs,stmt);
		return stuList;

	}
	
}

