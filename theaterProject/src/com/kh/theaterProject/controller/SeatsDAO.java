package com.kh.theaterProject.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.kh.theaterProject.model.SeatsVO;

public class SeatsDAO {
	private final String SELECT_BY_CUSTOMER_NO_SQL = "select * from SEATS  WHERE CUSTOMER_NO = TO_CHAR(?,'FM00000') ";
	private final String SELECT_BY_PLAYING_NO_SQL = "select * from SEATS  WHERE PLAYING_NO = TO_CHAR(?,'FM000') ";
	private final String INSERT_SQL = "INSERT INTO SEATS(PLAYING_NO,HALL_NO,X,Y) VALUES(TO_CHAR(?,'FM00'),?, TO_CHAR(?,'FM00'))";
	private final String UPDATE_SQL = "UPDATE SEATS SET CUSTOMER_NO = ? WHERE PLAYING_NO = TO_CHAR(?,'FM000') AND X=? AND Y=TO_CHAR(?,'FM00') ";
//	private final String UPDATE_BOOKING_SQL = "UPDATE SEATS SET STATUS = ? WHERE PLAYING_NO = TO_CHAR(?,'FM000') CUSTOMER_NO = TO_CHAR(?,'FM00000') ";
	private final String DELETE_SQL = "DELETE FROM SEATS ";

	// SeatsVOList를 받아서 db에 전체 insert 후 성공여부 true false 반환--현상태에는 필요없음. PLAYING 따라서
	// 자동 입력, 삭제
	public boolean insertDB(ArrayList<SeatsVO> svoList) throws SQLException {
		// Conection
		Connection con = null;
		PreparedStatement pstmt = null;
		// 1 Load, 2. connect
		con = DBUtility.dbCon();
		// 3.statement
		int result1 = 0;
		for (SeatsVO svo : svoList) {
			pstmt = con.prepareStatement(INSERT_SQL);
			pstmt.setString(1, svo.getHallNo());
			pstmt.setString(2, svo.getX());
			pstmt.setString(3, svo.getY());
			pstmt.setString(4, svo.getCustomerNo());
			result1 = pstmt.executeUpdate();
		}
		DBUtility.dbClose(con, pstmt);
		return (result1 != 0) ? true : false;
	}

	// SeatsVOList를 받아서 db에 status를 업데이트 성공여부 true false 반환
	public boolean updateBookingDB(ArrayList<SeatsVO> svoList) {
		Connection con = null;
		PreparedStatement pstmt = null;
		con = DBUtility.dbCon();
		int result1 = 0;
		try {
			
			for (SeatsVO svo : svoList) {
				pstmt = con.prepareStatement(UPDATE_SQL);
				pstmt.setString(1, svo.getCustomerNo());
				pstmt.setString(2, svo.getPlayingNo());
				pstmt.setString(3, svo.getX());
				pstmt.setString(4, svo.getY());
				result1 = pstmt.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtility.dbClose(con, pstmt);
		return (result1 != 0) ? true : false;
	}

	
	// SeatsVOList를 받아서 db에 status를 업데이트 성공여부 true false 반환
	public boolean updateBooking123DB(ArrayList<SeatsVO> svoList) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		con = DBUtility.dbCon();
		int result1 = 0;
		for (SeatsVO svo : svoList) {
			pstmt = con.prepareStatement(UPDATE_SQL);
			pstmt.setString(1, svo.getCustomerNo());
			pstmt.setString(2, svo.getHallNo());
			pstmt.setString(3, svo.getX());
			pstmt.setString(4, svo.getY());
			result1 = pstmt.executeUpdate();
		}
		DBUtility.dbClose(con, pstmt);
		return (result1 != 0) ? true : false;
	}

	// SeatsVOList를 받아서 전체삭제후 새로운 정보를 insert전체
	public boolean deleteDB(SeatsVO svo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		con = DBUtility.dbCon();
		pstmt = con.prepareStatement(DELETE_SQL);
		int result = pstmt.executeUpdate();
		DBUtility.dbClose(con, pstmt);
		return (result != 0) ? true : false;
	}

	// 테이블 전체를 List에 저장 후 반환
	public ArrayList<SeatsVO> returnList(SeatsVO svo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<SeatsVO> SeatsList = new ArrayList<SeatsVO>();
		con = DBUtility.dbCon();
		pstmt = con.prepareStatement(SELECT_BY_PLAYING_NO_SQL);
		pstmt.setString(1, svo.getPlayingNo());
		rs = pstmt.executeQuery();
		while (rs.next()) {
			String playingNo = rs.getString("PLAYING_NO");
			String hallNo = rs.getString("HALL_NO");
			String x = rs.getString("X");
			String y = rs.getString("Y");
			String customerNo = rs.getString("CUSTOMER_NO");
			svo = new SeatsVO(playingNo, hallNo, x, y, customerNo);
			SeatsList.add(svo);
		}
//		stuListPrint(stuList);
		DBUtility.dbClose(con, rs, pstmt);
		return SeatsList;
	}

	// CustomerNo가 들어있는 SeatsVO를 받아서 그에 해당하는 db의 SeatsVO를 반환
	public SeatsVO returnsvo(SeatsVO svo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		con = DBUtility.dbCon();
		pstmt = con.prepareStatement(SELECT_BY_CUSTOMER_NO_SQL);
		pstmt.setString(1, svo.getCustomerNo());
		rs = pstmt.executeQuery();
		while (rs.next()) {
			String playingNo = rs.getString("PLAYING_NO");
			String hallNo = rs.getString("HALL_NO");
			String x = rs.getString("X");
			String y = rs.getString("Y");
			String customerNo = rs.getString("CUSTOMER_NO");
			svo = new SeatsVO(playingNo, hallNo, x, y, customerNo);
		}
//		stuListPrint(stuList);
		DBUtility.dbClose(con, rs, pstmt);
		return svo;
	}

}
