package com.kh.theaterProject.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.kh.theaterProject.model.PlayingVO;

public class PlayingDAO {
	private final String SELECT_SQL = "SELECT NO, HALL_NO,CINEMA_NO, TO_CHAR(STARTTIME,'MM/DD HH24:MI') AS STARTTIMESTAMP ,REMAIN,STATUS FROM PLAYING";
	private final String SELECT_BY_NO_SQL = "SELECT NO, HALL_NO,CINEMA_NO, TO_CHAR(STARTTIME,'MM/DD HH24:MI') AS STARTTIMESTAMP ,REMAIN,STATUS FROM Playing WHERE NO = TO_CHAR(?,'FM000')";
	private final String SELECT_SORT_SQL = "SELECT NO, HALL_NO,CINEMA_NO, TO_CHAR(STARTTIME,'MM/DD HH24:MI') AS STARTTIMESTAMP ,REMAIN,STATUS FROM Playing ORDER BY date";
	private final String INSERT_SQL = "INSERT INTO Playing(NO,HALL_NO, CINEMA_NO, STARTTIME) "
			+ "VALUES(to_char((select nvl(max(no),0)+1 from Playing),'FM00'), ?, ?, ?)";
	private final String UPDATE_SQL = "UPDATE Playing SET HALL_NO = ?, CINEMA_NO = ?, STARTTIME = ? WHERE NO = ? ";
	private final String DELETE_SQL = "DELETE FROM Playing WHERE NO = TO_CHAR(?,'FM000')";
	private final String CALL_PROC_SQL = "CALL PLAYING_STATUS_PROCEDURE()";

	// PlayingVO를 받아서 db에 insert 후 성공여부 true false 반환
	public boolean insertDB(PlayingVO pvo) throws SQLException {
		// Conection
		Connection con = null;
		PreparedStatement pstmt = null;
		// 1 Load, 2. connect
		con = DBUtility.dbCon();
		// 3.statement
		pstmt = con.prepareStatement(INSERT_SQL);
		pstmt.setString(1, pvo.getHall_no());
		pstmt.setString(2, pvo.getCinema_no());
		pstmt.setTimestamp(3, pvo.getStarttime());
		int result1 = pstmt.executeUpdate();
		DBUtility.dbClose(con, pstmt);
		return (result1 != 0) ? true : false;
	}

	// PlayingVO를 받아서 db에 update 후 성공여부 true false 반환
	public boolean updateDB(PlayingVO pvo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		con = DBUtility.dbCon();
		pstmt = con.prepareStatement(UPDATE_SQL);
		pstmt.setString(1, pvo.getHall_no());
		pstmt.setString(2, pvo.getCinema_no());
		pstmt.setTimestamp(3, pvo.getStarttime());
		int result1 = pstmt.executeUpdate();
		DBUtility.dbClose(con, pstmt);
		return (result1 != 0) ? true : false;
	}

	// PlayingVO를 받아서 db에 delete 후 성공여부 true false 반환
	public boolean deleteDB(PlayingVO pvo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		con = DBUtility.dbCon();
		pstmt = con.prepareStatement(DELETE_SQL);
		pstmt.setString(1, pvo.getNo());
		int result = pstmt.executeUpdate();
		DBUtility.dbClose(con, pstmt);
		return (result != 0) ? true : false;
	}

	// 테이블 전체를 List에 저장 후 반환
	public ArrayList<PlayingVO> retrunList() throws SQLException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		CallableStatement cstmt = null;
		ArrayList<PlayingVO> PlayingList = new ArrayList<PlayingVO>();
		con = DBUtility.dbCon();
		stmt = con.createStatement();
		cstmt = con.prepareCall(CALL_PROC_SQL);
		rs = stmt.executeQuery(SELECT_SQL);
		while (rs.next()) {
			String no = rs.getString("NO");
			String hallNo = rs.getString("HALL_NO");
			String cinemaNo = rs.getString("CINEMA_NO");
			Timestamp startTime = rs.getTimestamp("STARTTIMESTAMP");
			int remain = rs.getInt("REMAIN");
			String status = rs.getString("STATUS");
			PlayingVO pvo = new PlayingVO(no, hallNo, cinemaNo, startTime, remain, status);
			PlayingList.add(pvo);
		}
//		stuListPrint(stuList);
		DBUtility.dbClose(con, rs, stmt, cstmt);
		return PlayingList;
	}

	// 테이블을 러닝타임순으로 정렬후 List 반환
	public ArrayList<PlayingVO> returnSortList() throws SQLException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		CallableStatement cstmt = null;
		ArrayList<PlayingVO> PlayingList = new ArrayList<PlayingVO>();
		con = DBUtility.dbCon();
		stmt = con.createStatement();
		cstmt = con.prepareCall(CALL_PROC_SQL);
		rs = stmt.executeQuery(SELECT_SORT_SQL);
		while (rs.next()) {
			String no = rs.getString("NO");
			String hallNo = rs.getString("HALL_NO");
			String cinemaNo = rs.getString("CINEMA_NO");
			Timestamp startTime = rs.getTimestamp("STARTTIMESTAMP");
			int remain = rs.getInt("REMAIN");
			String status = rs.getString("STATUS");
			PlayingVO pvo = new PlayingVO(no, hallNo, cinemaNo, startTime, remain, status);
			PlayingList.add(pvo);
		}
//		stuListPrint(stuList);
		DBUtility.dbClose(con, rs, stmt, cstmt);
		return PlayingList;
	}

	// no가 들어있는 PlayingVO를 받아서 그에 해당하는 db의 PlayingVO를 반환
	public PlayingVO returnpvo(PlayingVO pvo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CallableStatement cstmt = null;
		con = DBUtility.dbCon();
		cstmt = con.prepareCall(CALL_PROC_SQL);
		pstmt = con.prepareStatement(SELECT_BY_NO_SQL);
		pstmt.setString(1, pvo.getNo());
		rs = pstmt.executeQuery();
		while (rs.next()) {
			String no = rs.getString("NO");
			String hallNo = rs.getString("HALL_NO");
			String cinemaNo = rs.getString("CINEMA_NO");
			Timestamp startTime = rs.getTimestamp("STARTTIMESTAMP");
			int remain = rs.getInt("REMAIN");
			String status = rs.getString("STATUS");
			pvo = new PlayingVO(no, hallNo, cinemaNo, startTime, remain, status);
		}
//		stuListPrint(stuList);
		DBUtility.dbClose(con, rs, pstmt,cstmt);
		return pvo;
	}

}
