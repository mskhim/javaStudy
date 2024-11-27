package com.kh.theaterProject.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.kh.theaterProject.model.CinemaVO;

public class CinemaDAO {
	private final String SELECT_SQL = "SELECT * FROM Cinema ORDER BY NO";
	private final String SELECT_BY_NO_SQL = "SELECT * FROM Cinema WHERE NO = TO_CHAR(?,'FM00')";
	private final String SELECT_SORT_SQL = "SELECT * FROM Cinema ORDER BY RUNNINGTIME desc";
	private final String INSERT_SQL = "INSERT INTO Cinema(NO, NAME, RUNNINGTIME) "
			+ "VALUES(to_char((select nvl(max(no),0)+1 from Cinema),'FM00'), ?, ?)";
	private final String UPDATE_SQL = "UPDATE Cinema SET NAME = ?, RUNNINGTIME = ? WHERE NO = TO_CHAR(?,'FM00') ";
	private final String DELETE_SQL = "DELETE FROM Cinema WHERE NO = TO_CHAR(?,'FM00')";
	private final String CALL_PROC_SQL = "{CALL CINEMA_STATUS_PROCEDURE()}";//status가 null이 아닌 상영 정보에 해당 영화가 없으면, status를 null로 바꿔주고 영화가 상영중이면 0으로 바꿔주는 프로시저 .
	
	// CinemaVO를 받아서 db에 insert 후 성공여부 true false 반환
	public boolean insertDB(CinemaVO cnvo) throws SQLException {
		// Conection
		Connection con = null;
		PreparedStatement pstmt = null;
		// 1 Load, 2. connect
		con = DBUtility.dbCon();
		// 3.statement
		pstmt = con.prepareStatement(INSERT_SQL);
		pstmt.setString(1, cnvo.getName());
		pstmt.setInt(2, cnvo.getRunningtime());
		int result1 = pstmt.executeUpdate();
		DBUtility.dbClose(con, pstmt);
		return (result1 != 0) ? true : false;
	}


	// CinemaVO를 받아서 db에 update 후 성공여부 true false 반환
	public boolean updateDB(CinemaVO cnvo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		con = DBUtility.dbCon();
		pstmt = con.prepareStatement(UPDATE_SQL);
		pstmt.setString(1, cnvo.getName());
		pstmt.setInt(2, cnvo.getRunningtime());
		pstmt.setString(3, cnvo.getNo());
		int result1 = pstmt.executeUpdate();
		DBUtility.dbClose(con, pstmt);
		return (result1 != 0) ? true : false;
	}

	// CinemaVO를 받아서 db에 delete 후 성공여부 true false 반환
	public boolean deleteDB(CinemaVO cnvo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		con = DBUtility.dbCon();
		pstmt = con.prepareStatement(DELETE_SQL);
		pstmt.setString(1, cnvo.getNo());
		int result = pstmt.executeUpdate();
		DBUtility.dbClose(con, pstmt);
		return (result != 0) ? true : false;
	}

	// 테이블 전체를 List에 저장 후 반환
	public ArrayList<CinemaVO> retrunList() throws SQLException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<CinemaVO> CinemaList = new ArrayList<CinemaVO>();
		con = DBUtility.dbCon();
		stmt = con.createStatement();
		CallableStatement cstmt = null;
		cstmt = con.prepareCall(CALL_PROC_SQL);
		cstmt.executeUpdate();
		rs = stmt.executeQuery(SELECT_SQL);
		while (rs.next()) {
			String no = rs.getString("NO");
			String name = rs.getString("NAME");
			int runningtime = rs.getInt("RUNNINGTIME");
			String status = rs.getString("status");
			CinemaVO cnvo = new CinemaVO(no, name, runningtime, status);
			CinemaList.add(cnvo);
		}
//		stuListPrint(stuList);
		DBUtility.dbClose(con, rs, stmt, cstmt);
		return CinemaList;
	}

	// 테이블을 러닝타임순으로 정렬후 List 반환
	public ArrayList<CinemaVO> returnSortList() throws SQLException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<CinemaVO> CinemaList = new ArrayList<CinemaVO>();
		con = DBUtility.dbCon();
		stmt = con.createStatement();
		CallableStatement cstmt = null;
		cstmt = con.prepareCall(CALL_PROC_SQL);
		cstmt.executeUpdate();
		rs = stmt.executeQuery(SELECT_SORT_SQL);
		while (rs.next()) {
			String no = rs.getString("NO");
			String name = rs.getString("NAME");
			int runningtime = rs.getInt("RUNNINGTIME");
			String status = rs.getString("status");
			CinemaVO cnvo = new CinemaVO(no, name, runningtime, status);
			CinemaList.add(cnvo);
		}
//		stuListPrint(stuList);
		DBUtility.dbClose(con, rs, stmt, cstmt);
		return CinemaList;
	}

	// no가 들어있는 CusotomerVO를 받아서 그에 해당하는 db의 CinemaVO를 반환
	public CinemaVO returncnvo(CinemaVO cnvo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		con = DBUtility.dbCon();
		CallableStatement cstmt = null;
		cstmt = con.prepareCall(CALL_PROC_SQL);
		cstmt.executeUpdate();
		pstmt = con.prepareStatement(SELECT_BY_NO_SQL);
		pstmt.setString(1, cnvo.getNo());
		rs = pstmt.executeQuery();
		while (rs.next()) {
			String no = rs.getString("NO");
			String name = rs.getString("NAME");
			int runningtime = rs.getInt("RUNNINGTIME");
			String status = rs.getString("status");
			cnvo = new CinemaVO(no, name, runningtime, status);
		}
//		stuListPrint(stuList);
		DBUtility.dbClose(con, rs, pstmt, cstmt);
		return cnvo;
	}

}
