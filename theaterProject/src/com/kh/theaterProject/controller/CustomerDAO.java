package com.kh.theaterProject.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.kh.theaterProject.model.CustomerVO;


public class CustomerDAO {
	public static final String CUSTOMER_SELECT_SQL = "SELECT * FROM CUSTOMER";
	public static final String CUSTOMER_SELECT_SORT_SQL = "SELECT * FROM CUSTOMER ORDER BY BOOKCOUNT";
	public static final String CUSTOMER_INSERT_SQL = "INSERT INTO CUSTOMER(NO, NAME, ID, PWD, BIRTH,PHONE,REGISTDATE) VALUES((SELECT NVL(MAX(NO),0)+1 FROM CUSTOMER), ?, ?, ?, ?,?,?)";
	public static final String CUSTOMER_UPATE_SQL = "UPDATE STUDENT SET NAME = ?, ID = ?, PWD = ?, BIRTH = ? PHONE = ? WHERE NO = ? ";
	public static final String CUSTOMER_DELETE_SQl = "DELETE FROM STUDENT WHERE NO = ?";
	public static final String CUSTOMER_CALL_RANK_PROC = "{call STUDENT3_RANK_PROC()}";
	
	
	//CustomerVO를 받아서 db에 삽입
	public static boolean sDBInsert(CustomerVO cvo) throws SQLException {
		// Conection
		Connection con = null;
		CallableStatement cstmt = null;
		PreparedStatement pstmt = null;
		// 1 Load, 2. connect
		con = DBUtility.dbCon();
		// 3.statement
		pstmt = con.prepareStatement(CUSTOMER_INSERT_SQL);
		pstmt.setString(1, cvo.getName());
		int result1 = pstmt.executeUpdate();
		cstmt = con.prepareCall(CUSTOMER_CALL_RANK_PROC);
		int result2 = cstmt.executeUpdate();
		DBUtility.dbClose(con, pstmt);
		return (result1!=0 && result2 !=0)?true:false;
	}
	//테이블 전체를 ArrayList에 저장
	public static ArrayList<CustomerVO> StudentSelectcvo() throws SQLException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<CustomerVO> studentList = new ArrayList<CustomerVO>();
		con = DBUtility.dbCon();
		stmt = con.prepareCall("{call STUDENT3_RANK_PROC()}");
		stmt = con.createStatement();
		rs = stmt.executeQuery(CUSTOMER_SELECT_SQL);

		while (rs.next()) {
			int no = rs.getInt("NO");
			String name = rs.getString("NAME");
			int kor = rs.getInt("KOR");
			int eng = rs.getInt("ENG");
			int mat = rs.getInt("MAT");
			int total = rs.getInt("TOTAL");
			int ave = rs.getInt("AVE");
			int rank = rs.getInt("RANK");

			CustomerVO stu = new CustomerVO();
			studentList.add(stu);
		}
//		stuListPrint(stuList);
		DBUtility.dbClose(con, rs, stmt);
		return studentList;
	}
	//CustomerVO를 받아서 db 업데이트
	public static boolean sDBUpdate(CustomerVO cvo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		con = DBUtility.dbCon();
		pstmt = con.prepareStatement(CUSTOMER_UPATE_SQL);
		pstmt.setString(1, cvo.getName());
		pstmt.setInt(5, cvo.getNo());
		
		int result1 = pstmt.executeUpdate();
		pstmt = con.prepareCall(CUSTOMER_CALL_RANK_PROC);
		int result2 = pstmt.executeUpdate();
		DBUtility.dbClose(con, pstmt);
		return (result1!=0 && result2 !=0)?true:false;
	}
	//student no를 받아서 db 삭제
	public static boolean sDBDelete(CustomerVO cvo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		con = DBUtility.dbCon();
		pstmt = con.prepareStatement(CUSTOMER_DELETE_SQl);
		pstmt.setInt(1, cvo.getNo());
		int result = pstmt.executeUpdate();
		DBUtility.dbClose(con, pstmt);
		return (result!=0)?true:false;
	}
	//student를 db를 정렬
	public static ArrayList<CustomerVO> studentSortcvo() throws SQLException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<CustomerVO> stuList = new ArrayList<CustomerVO>();

		con = DBUtility.dbCon();
		stmt = con.createStatement();
		rs = stmt.executeQuery(CUSTOMER_SELECT_SORT_SQL);
		while (rs.next()) {
			int no = rs.getInt("NO");
			String name = rs.getString("NAME");
			int kor = rs.getInt("KOR");
			int eng = rs.getInt("ENG");
			int mat = rs.getInt("MAT");
			int total = rs.getInt("TOTAL");
			int ave = rs.getInt("AVE");
			int rank = rs.getInt("RANK");
			CustomerVO stu = new CustomerVO();
			stuList.add(stu);
		}
		DBUtility.dbClose(con,rs,stmt);
		return stuList;

	}
	
}

