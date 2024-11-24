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
	private final String SELECT_BY_CODE_SQL = "SELECT * FROM BOOKING_PLAYING_CINEMA_HALL_JOIN WHERE CODE = ?";
	private final String SELECT_BY_CUSTOMER_SQL = "SELECT * FROM BOOKING_PLAYING_CINEMA_HALL_JOIN WHERE CUSTOMER_NO = ?";
	private final String SELECT_LAST_SQL = "SELECT * FROM (select * from BOOKING_PLAYING_CINEMA_HALL_JOIN order by booking_date desc) where rownum=1";
	private final String SELECT_SORT_SQL = "SELECT * FROM BOOKING_PLAYING_CINEMA_HALL_JOIN ORDER BY BOOKING_DATE";
	private final String INSERT_SQL = "INSERT INTO Booking(NO,PLAYING_NO, CUSTOMER_NO, AMOUNT, BOOKING_DATE) "
			+ "VALUES(to_char((select nvl(max(no),0)+1 from Booking),'FM00000'), TO_CHAR(?,'FM000'), TO_CHAR(?,'FM00000'), ?,SYSDATE)";
	private final String UPDATE_SQL = "UPDATE Booking SET PLAYING_NO = TO_CHAR(?,'FM000'), CUSTOMER_NO = TO_CHAR(?,'FM00000'), AMOUNT = ? WHERE NO = TO_CHAR(?,'FM00000') ";
	private final String DELETE_SQL = "DELETE FROM Booking WHERE NO = TO_CHAR(?,'FM00000')";

	// BookingVO를 받아서 db에 insert 후 성공여부 true false 반환
	public boolean insertDB(BookingVO bvo) throws SQLException {
		// Conection
		Connection con = null;
		PreparedStatement pstmt = null;
		// 1 Load, 2. connect
		con = DBUtility.dbCon();
		// 3.statement
		pstmt = con.prepareStatement(INSERT_SQL);
		pstmt.setString(1, bvo.getPlaying_no());
		pstmt.setString(2, bvo.getCustomer_no());
		pstmt.setInt(3, bvo.getAmount());
		int result1 = pstmt.executeUpdate();
		DBUtility.dbClose(con, pstmt);
		return (result1 != 0) ? true : false;
	}

	// BookingVO를 받아서 db에 update 후 성공여부 true false 반환
	public boolean updateDB(BookingVO bvo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		con = DBUtility.dbCon();
		pstmt = con.prepareStatement(UPDATE_SQL);
		pstmt.setString(1, bvo.getPlaying_no());
		pstmt.setString(2, bvo.getCustomer_no());
		pstmt.setInt(3, bvo.getAmount());
		pstmt.setString(4, bvo.getNo());
		int result1 = pstmt.executeUpdate();
		DBUtility.dbClose(con, pstmt);
		return (result1 != 0) ? true : false;
	}

	// BookingVO를 받아서 db에 delete 후 성공여부 true false 반환
	public boolean deleteDB(BookingVO bvo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		con = DBUtility.dbCon();
		pstmt = con.prepareStatement(DELETE_SQL);
		pstmt.setString(1, bvo.getNo());
		int result = pstmt.executeUpdate();
		DBUtility.dbClose(con, pstmt);
		return (result != 0) ? true : false;
	}

	// 테이블 전체를 List에 저장 후 반환
	public ArrayList<BookingVO> returnList() throws SQLException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<BookingVO> BookingList = new ArrayList<BookingVO>();
		con = DBUtility.dbCon();
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
			String cName = rs.getString("NAME");
			String hName = rs.getString("HNO");
			Timestamp startTime = rs.getTimestamp("STARTTIME");
			String status = rs.getString("STATUS");
			BookingVO bvo = new BookingVO(no, playingNo, customerNo, code, amount, price, bookingDate,cName,hName,startTime,status);
			BookingList.add(bvo);
		}
//		stuListPrint(stuList);
		DBUtility.dbClose(con, rs, stmt);
		return BookingList;
	}

	// 테이블을 러닝타임순으로 정렬후 List 반환
	public ArrayList<BookingVO> returnSortList() throws SQLException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<BookingVO> BookingList = new ArrayList<BookingVO>();
		con = DBUtility.dbCon();
		stmt = con.createStatement();
		rs = stmt.executeQuery(SELECT_SORT_SQL);
		while (rs.next()) {
			String no = rs.getString("NO");
			String playingNo = rs.getString("PLAYING_NO");
			String customerNo = rs.getString("CUSTOMER_NO");
			String code = rs.getString("CODE");
			int amount = rs.getInt("AMOUNT");
			int price = rs.getInt("PRICE");
			Timestamp bookingDate = rs.getTimestamp("BOOKING_DATE");
			String cName = rs.getString("NAME");
			String hName = rs.getString("HNO");
			Timestamp startTime = rs.getTimestamp("STARTTIME");
			String status = rs.getString("STATUS");
			BookingVO bvo = new BookingVO(no, playingNo, customerNo, code, amount, price, bookingDate,cName,hName,startTime,status);
			BookingList.add(bvo);
		}
//		stuListPrint(stuList);
		DBUtility.dbClose(con, rs, stmt);
		return BookingList;
	}

	// code가 들어있는 BookingVO를 받아서 그에 해당하는 db의 BookingVO를 반환
	public BookingVO returnCodebvo(BookingVO bvo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
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
			String cName = rs.getString("NAME");
			String hName = rs.getString("HNO");
			Timestamp startTime = rs.getTimestamp("STARTTIME");
			String status = rs.getString("STATUS");
			bvo = new BookingVO(no, playingNo, customerNo, code, amount, price, bookingDate,cName,hName,startTime,status);
		}
//			stuListPrint(stuList);
		DBUtility.dbClose(con, rs, pstmt);
		return bvo;
	}

	// Customer_no가 들어있는 BookingVO를 받아서 그에 해당하는 db의 BookingVO를 반환
	public ArrayList<BookingVO> returnCustomerList(BookingVO bvo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
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
			String cName = rs.getString("NAME");
			String hName = rs.getString("HNO");
			Timestamp startTime = rs.getTimestamp("STARTTIME");
			String status = rs.getString("STATUS");
			bvo = new BookingVO(no, playingNo, customerNo, code, amount, price, bookingDate,cName,hName,startTime,status);
			BookingList.add(bvo);
		}
//				stuListPrint(stuList);
		DBUtility.dbClose(con, rs, pstmt);
		return BookingList;
	}

	// 가장 최근 입력된 bvo를 리턴
	public BookingVO returnLastbvo(BookingVO bvo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		con = DBUtility.dbCon();
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
			String cName = rs.getString("NAME");
			String hName = rs.getString("HNO");
			Timestamp startTime = rs.getTimestamp("STARTTIME");
			String status = rs.getString("STATUS");
			bvo = new BookingVO(no, playingNo, customerNo, code, amount, price, bookingDate,cName,hName,startTime,status);
		}
//			stuListPrint(stuList);
		DBUtility.dbClose(con, rs, pstmt);
		return bvo;
	}

}
