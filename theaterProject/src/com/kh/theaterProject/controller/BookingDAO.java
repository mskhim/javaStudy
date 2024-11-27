package com.kh.theaterProject.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.kh.theaterProject.model.BookingVO;

public class BookingDAO {
	private final String SELECT_SQL = "SELECT * FROM BOOKING_PLAYING_CINEMA_HALL_JOIN ORDER BY NO";
	private final String SELECT_SEATS_BY_CUS_SQL = "SELECT S.PLAYING_NO, S.X, S.Y ,S.CUSTOMER_NO FROM SEATS S JOIN BOOKING B ON B.CUSTOMER_NO=S.CUSTOMER_NO AND B.NO =S.BOOKING_NO WHERE S.CUSTOMER_NO = TO_CHAR(?,'FM00000') AND B.NO = TO_CHAR(?,'FM00000') order by X,Y";
	private final String SELECT_STATUS_NULL_SQL = "SELECT * FROM BOOKING_PLAYING_CINEMA_HALL_JOIN WHERE STATUS IS NULL ORDER BY NO";
	private final String SELECT_BY_CODE_SQL = "SELECT * FROM BOOKING_PLAYING_CINEMA_HALL_JOIN WHERE CODE = ?";
	private final String SELECT_BY_CUSTOMER_SQL = "SELECT * FROM BOOKING_PLAYING_CINEMA_HALL_JOIN WHERE CUSTOMER_NO = ? ";
	private final String SELECT_LAST_SQL = "SELECT * FROM (select * from BOOKING_PLAYING_CINEMA_HALL_JOIN order by booking_date desc) where rownum=1";
	private final String INSERT_SQL = "INSERT INTO Booking(NO,PLAYING_NO, CUSTOMER_NO, AMOUNT, BOOKING_DATE) "
			+ "VALUES(to_char((select nvl(max(no),0)+1 from Booking),'FM00000'), TO_CHAR(?,'FM000'), TO_CHAR(?,'FM00000'), ?,SYSDATE)";
	private final String UPDATE_SQL = "UPDATE Booking SET PLAYING_NO = TO_CHAR(?,'FM000'), CUSTOMER_NO = TO_CHAR(?,'FM00000'), AMOUNT = ? WHERE NO = TO_CHAR(?,'FM00000') ";
	private final String DELETE_SQL = "DELETE FROM Booking WHERE NO = TO_CHAR(?,'FM00000')";

	// BookingVO를 받아서 db에 insert 후 성공여부 true false 반환
	public boolean insertDB(BookingVO bvo) {
		// Conection
		Connection con = null;
		PreparedStatement pstmt = null;
		int result1 = 0;
		// 1 Load, 2. connect
		con = DBUtility.dbCon();
		// 3.statement
		try {
			pstmt = con.prepareStatement(INSERT_SQL);
			pstmt.setString(1, bvo.getPlaying_no());
			pstmt.setString(2, bvo.getCustomer_no());
			pstmt.setInt(3, bvo.getAmount());
			result1 = pstmt.executeUpdate();
			pstmt = con.prepareStatement(SELECT_LAST_SQL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtility.dbClose(con, pstmt);
		return (result1 != 0) ? true : false;
	}

	// BookingVO를 받아서 db에 update 후 성공여부 true false 반환
	public boolean updateDB(BookingVO bvo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result1 = 0;
		con = DBUtility.dbCon();
		try {
			pstmt = con.prepareStatement(UPDATE_SQL);
			pstmt.setString(1, bvo.getPlaying_no());
			pstmt.setString(2, bvo.getCustomer_no());
			pstmt.setInt(3, bvo.getAmount());
			pstmt.setString(4, bvo.getNo());
			result1 = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtility.dbClose(con, pstmt);
		return (result1 != 0) ? true : false;
	}

	// BookingVO를 받아서 db에 delete 후 성공여부 true false 반환
	public boolean deleteDB(BookingVO bvo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;
		con = DBUtility.dbCon();
		try {
			pstmt = con.prepareStatement(DELETE_SQL);
			pstmt.setString(1, bvo.getNo());
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtility.dbClose(con, pstmt);
		return (result != 0) ? true : false;
	}

	// 테이블 전체를 List에 저장 후 반환
	public ArrayList<BookingVO> returnList() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		PreparedStatement pstmt = null;
		ArrayList<BookingVO> BookingList = new ArrayList<BookingVO>();
		con = DBUtility.dbCon();
		try {
			
			stmt = con.createStatement();
			rs = stmt.executeQuery(SELECT_SQL);
			while (rs.next()) {
				String no = rs.getString("NO");
				String playingNo = rs.getString("PLAYING_NO");
				String customerNo = rs.getString("CUSTOMER_NO");
				String code = rs.getString("CODE");
				int amount = rs.getInt("AMOUNT");
				int price = rs.getInt("PRICE");
				Timestamp bookingDate = rs.getTimestamp("BOOKING_DATE");
				String cName = rs.getString("CINENAME");
				String hName = rs.getString("HNO");
				Timestamp startTime = rs.getTimestamp("STARTTIME");
				String status = rs.getString("STATUS");
				String cusName = rs.getString("CUSNAME");
				BookingVO bvo = new BookingVO(no, playingNo, customerNo, code, amount, price, bookingDate, cName, hName,
						startTime, status, cusName);
				ArrayList<String> seatList = new ArrayList<String>();
				pstmt = con.prepareStatement(SELECT_SEATS_BY_CUS_SQL);
				pstmt.setString(1, bvo.getCustomer_no());
				pstmt.setString(2, bvo.getNo());
				rs2 = pstmt.executeQuery();
				while (rs2.next()) {
					String X = rs2.getString("X");
					String Y = rs2.getString("Y");
					seatList.add(X + Y);
				}
				bvo.setSeatList(seatList);
				BookingList.add(bvo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtility.dbClose(con, rs, stmt, pstmt);
		return BookingList;
	}

	// status가 null인 테이블 전체를 List에 저장 후 반환
	public ArrayList<BookingVO> returnNullList() throws SQLException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		PreparedStatement pstmt = null;
		ArrayList<BookingVO> BookingList = new ArrayList<BookingVO>();
		con = DBUtility.dbCon();
		stmt = con.createStatement();
		rs = stmt.executeQuery(SELECT_STATUS_NULL_SQL);
		while (rs.next()) {
			String no = rs.getString("NO");
			String playingNo = rs.getString("PLAYING_NO");
			String customerNo = rs.getString("CUSTOMER_NO");
			String code = rs.getString("CODE");
			int amount = rs.getInt("AMOUNT");
			int price = rs.getInt("PRICE");
			Timestamp bookingDate = rs.getTimestamp("BOOKING_DATE");
			String cName = rs.getString("CINENAME");
			String hName = rs.getString("HNO");
			Timestamp startTime = rs.getTimestamp("STARTTIME");
			String status = rs.getString("STATUS");
			String cusName = rs.getString("CUSNAME");
			BookingVO bvo = new BookingVO(no, playingNo, customerNo, code, amount, price, bookingDate, cName, hName,
					startTime, status, cusName);
			ArrayList<String> seatList = new ArrayList<String>();
			pstmt = con.prepareStatement(SELECT_SEATS_BY_CUS_SQL);
			pstmt.setString(1, bvo.getCustomer_no());
			pstmt.setString(2, bvo.getNo());
			rs2 = pstmt.executeQuery();
			while (rs2.next()) {
				String X = rs2.getString("X");
				String Y = rs2.getString("Y");
				seatList.add(X + Y);
			}
			bvo.setSeatList(seatList);
			BookingList.add(bvo);

		}
//			stuListPrint(stuList);
		DBUtility.dbClose(con, rs, stmt, pstmt);
		return BookingList;
	}

	// code가 들어있는 BookingVO를 받아서 그에 해당하는 db의 BookingVO를 반환
	public BookingVO returnByCodebvo(BookingVO bvo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		con = DBUtility.dbCon();
		pstmt = con.prepareStatement(SELECT_BY_CODE_SQL);
		pstmt.setString(1, bvo.getCode());
		rs = pstmt.executeQuery();
		while (rs.next()) {
			String no = rs.getString("NO");
			String playingNo = rs.getString("PLAYING_NO");
			String customerNo = rs.getString("CUSTOMER_NO");
			String code = rs.getString("CODE");
			int amount = rs.getInt("AMOUNT");
			int price = rs.getInt("PRICE");
			Timestamp bookingDate = rs.getTimestamp("BOOKING_DATE");
			String cName = rs.getString("CINENAME");
			String hName = rs.getString("HNO");
			Timestamp startTime = rs.getTimestamp("STARTTIME");
			String status = rs.getString("STATUS");
			String cusName = rs.getString("CUSNAME");
			bvo = new BookingVO(no, playingNo, customerNo, code, amount, price, bookingDate, cName, hName, startTime,
					status, cusName);
		}
		ArrayList<String> seatList = new ArrayList<String>();
		pstmt = con.prepareStatement(SELECT_SEATS_BY_CUS_SQL);
		pstmt.setString(1, bvo.getCustomer_no());
		pstmt.setString(2, bvo.getNo());
		rs2 = pstmt.executeQuery();
		while (rs2.next()) {
			String X = rs2.getString("X");
			String Y = rs2.getString("Y");
			seatList.add(X + Y);
		}
		bvo.setSeatList(seatList);

		DBUtility.dbClose(con, rs, pstmt);
		return bvo;
	}

	// BookingVO를 받아서 그에 해당하는 seatsList를 bvo에 넣어서 반환 db의 BookingVO를 반환
	public BookingVO returnSeatsbvo(BookingVO bvo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<String> seatList = new ArrayList<String>();
		con = DBUtility.dbCon();
		pstmt = con.prepareStatement(SELECT_SEATS_BY_CUS_SQL);
		pstmt.setString(1, bvo.getCustomer_no());
		pstmt.setString(2, bvo.getNo());
		rs = pstmt.executeQuery();
		while (rs.next()) {
			String X = rs.getString("X");
			String Y = rs.getString("Y");
			seatList.add(X + Y);
		}
		bvo.setSeatList(seatList);
		DBUtility.dbClose(con, rs, pstmt);
		return bvo;
	}

	// Customer_no가 들어있는 BookingVO를 받아서 그에 해당하는 db의 BookingVO를 반환
	public ArrayList<BookingVO> returnCustomerList(BookingVO bvo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		ArrayList<BookingVO> BookingList = new ArrayList<BookingVO>();
		con = DBUtility.dbCon();
		pstmt = con.prepareStatement(SELECT_BY_CUSTOMER_SQL);
		pstmt.setString(1, bvo.getCustomer_no());
		rs = pstmt.executeQuery();
		while (rs.next()) {
			String no = rs.getString("NO");
			String playingNo = rs.getString("PLAYING_NO");
			String customerNo = rs.getString("CUSTOMER_NO");
			String code = rs.getString("CODE");
			int amount = rs.getInt("AMOUNT");
			int price = rs.getInt("PRICE");
			Timestamp bookingDate = rs.getTimestamp("BOOKING_DATE");
			String cName = rs.getString("CINENAME");
			String hName = rs.getString("HNO");
			Timestamp startTime = rs.getTimestamp("STARTTIME");
			String status = rs.getString("STATUS");
			String cusName = rs.getString("CUSNAME");
			bvo = new BookingVO(no, playingNo, customerNo, code, amount, price, bookingDate, cName, hName, startTime,
					status, cusName);
			ArrayList<String> seatList = new ArrayList<String>();
			pstmt2 = con.prepareStatement(SELECT_SEATS_BY_CUS_SQL);
			pstmt2.setString(1, bvo.getCustomer_no());
			pstmt2.setString(2, bvo.getNo());
			rs2 = pstmt2.executeQuery();
			while (rs2.next()) {
				String X = rs2.getString("X");
				String Y = rs2.getString("Y");
				seatList.add(X + Y);
			}
			bvo.setSeatList(seatList);
			BookingList.add(bvo);
		}
		DBUtility.dbClose(con, rs, pstmt, pstmt2);
		return BookingList;
	}

	// 가장 최근 입력된 bvo를 리턴
	public BookingVO returnLastbvo(BookingVO bvo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		con = DBUtility.dbCon();
		try {
			pstmt = con.prepareStatement(SELECT_LAST_SQL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String no = rs.getString("NO");
				String playingNo = rs.getString("PLAYING_NO");
				String customerNo = rs.getString("CUSTOMER_NO");
				String code = rs.getString("CODE");
				int amount = rs.getInt("AMOUNT");
				int price = rs.getInt("PRICE");
				Timestamp bookingDate = rs.getTimestamp("BOOKING_DATE");
				String cName = rs.getString("CINENAME");
				String hName = rs.getString("HNO");
				Timestamp startTime = rs.getTimestamp("STARTTIME");
				String status = rs.getString("STATUS");
				String cusName = rs.getString("CUSNAME");
				bvo = new BookingVO(no, playingNo, customerNo, code, amount, price, bookingDate, cName, hName,
						startTime, status, cusName);
			}
			ArrayList<String> seatList = new ArrayList<String>();
			pstmt = con.prepareStatement(SELECT_SEATS_BY_CUS_SQL);
			pstmt.setString(1, bvo.getCustomer_no());
			pstmt.setString(2, bvo.getNo());
			rs2 = pstmt.executeQuery();
			while (rs2.next()) {
				String X = rs2.getString("X");
				String Y = rs2.getString("Y");
				seatList.add(X + Y);
				bvo.setSeatList(seatList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		DBUtility.dbClose(con, rs, pstmt);
		return bvo;
	}
}
