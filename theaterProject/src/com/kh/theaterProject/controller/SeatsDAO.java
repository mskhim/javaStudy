package com.kh.theaterProject.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.kh.theaterProject.model.SeatsVO;

public class SeatsDAO {
	private final String SELECT_BY_CUSTOMER_NO_SQL = "select * from SEATS  WHERE CUSTOMER_NO = TO_CHAR(?,'FM00000') order by PLAYING_NO,X,Y";
	private final String SELECT_BY_PLAYING_NO_SQL = "select * from SEATS  WHERE PLAYING_NO = TO_CHAR(?,'FM000') order by PLAYING_NO,X,Y";
	private final String UPDATE_SQL = "UPDATE SEATS SET CUSTOMER_NO = ?, BOOKING_NO = ? WHERE PLAYING_NO = TO_CHAR(?,'FM000') AND X=? AND Y=TO_CHAR(?,'FM00') ";

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
				pstmt.setString(2, svo.getBookingNo());
				pstmt.setString(3, svo.getPlayingNo());
				pstmt.setString(4, svo.getX());
				pstmt.setString(5, svo.getY());
				result1 = pstmt.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtility.dbClose(con, pstmt);
		return (result1 != 0) ? true : false;
	}

	// svo를 받아서 해당 Playing_no를 이용해 테이블 전체를 List에 저장 후 반환
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
			String bookingNo = rs.getString("BOOKING_NO");
			
			svo = new SeatsVO(playingNo, hallNo, x, y, customerNo,bookingNo);
			SeatsList.add(svo);
		}
//		stuListPrint(stuList);
		DBUtility.dbClose(con, rs, pstmt);
		return SeatsList;
	}

	// CustomerNo, Booking_NO가 들어있는 SeatsVO를 받아서 그에 해당하는 db의 SeatsVO를 반환
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
			String bookingNo = rs.getString("BOOKING_NO");
			svo = new SeatsVO(playingNo, hallNo, x, y, customerNo,bookingNo);
		}
//		stuListPrint(stuList);
		DBUtility.dbClose(con, rs, pstmt);
		return svo;
	}

}
