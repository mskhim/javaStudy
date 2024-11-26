package com.kh.theaterProject.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.kh.theaterProject.model.HallVO;

public class HallDAO {
	private final String SELECT_SQL = "SELECT * FROM Hall ORDER BY NO";
	private final String SELECT_BY_NO_SQL = "SELECT * FROM Hall WHERE NO = TO_CHAR(?,'FM00')";
//	private final String SELECT_SORT_SQL = "SELECT * FROM Hall ORDER BY PRICE desc";
	private final String INSERT_SQL = "INSERT INTO Hall(NO, PRICE, ROWX,COLY) "
			+ "VALUES(to_char((select nvl(max(no),0)+1 from Hall),'FM00'), ?, ?, ?)";
	private final String UPDATE_SQL = "UPDATE Hall SET PRICE = ?, ROWX = ?, COLY = ? WHERE NO = TO_CHAR(?,'FM00') ";
	private final String DELETE_SQL = "DELETE FROM Hall WHERE NO = TO_CHAR(?,'FM00')";

	// HallVO를 받아서 db에 insert 후 성공여부 true false 반환
	public boolean insertDB(HallVO hvo) throws SQLException {
		// Conection
		Connection con = null;
		PreparedStatement pstmt = null;
		// 1 Load, 2. connect
		con = DBUtility.dbCon();
		// 3.statement
		pstmt = con.prepareStatement(INSERT_SQL);
		pstmt.setInt(1, hvo.getPrice());
		pstmt.setInt(2, hvo.getRow());
		pstmt.setInt(3, hvo.getCol());
		int result1 = pstmt.executeUpdate();
		DBUtility.dbClose(con, pstmt);
		return (result1 != 0) ? true : false;
	}


	// HallVO를 받아서 db에 update 후 성공여부 true false 반환
	public boolean updateDB(HallVO hvo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		con = DBUtility.dbCon();
		pstmt = con.prepareStatement(UPDATE_SQL);
		pstmt.setInt(1, hvo.getPrice());
		pstmt.setInt(2, hvo.getRow());
		pstmt.setInt(3, hvo.getCol());
		pstmt.setString(4, hvo.getNo());
		int result1 = pstmt.executeUpdate();
		DBUtility.dbClose(con, pstmt);
		return (result1 != 0) ? true : false;
	}

	// HallVO를 받아서 db에 delete 후 성공여부 true false 반환
	public boolean deleteDB(HallVO hvo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		con = DBUtility.dbCon();
		pstmt = con.prepareStatement(DELETE_SQL);
		pstmt.setString(1, hvo.getNo());
		int result = pstmt.executeUpdate();
		DBUtility.dbClose(con, pstmt);
		return (result != 0) ? true : false;
	}

	// 테이블 전체를 List에 저장 후 반환
	public ArrayList<HallVO> returnList() throws SQLException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<HallVO> HallList = new ArrayList<HallVO>();
		con = DBUtility.dbCon();
		stmt = con.createStatement();

		rs = stmt.executeQuery(SELECT_SQL);
		while (rs.next()) {
			String no = rs.getString("NO");
			int seats = rs.getInt("SEATS");
			int price = rs.getInt("PRICE");
			int row = rs.getInt("ROWX");
			int col = rs.getInt("COLY");
			HallVO hvo = new HallVO(no, seats, price,row,col);
			HallList.add(hvo);
		}
//		stuListPrint(stuList);
		DBUtility.dbClose(con, rs, stmt);
		return HallList;
	}

	

	// no가 들어있는 CusotomerVO를 받아서 그에 해당하는 db의 HallVO를 반환
	public HallVO returnhvo(HallVO hvo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		con = DBUtility.dbCon();
		pstmt = con.prepareStatement(SELECT_BY_NO_SQL);
		pstmt.setString(1, hvo.getNo());
		rs = pstmt.executeQuery();
		while (rs.next()) {
			String no = rs.getString("NO");
			int seats = rs.getInt("SEATS");
			int price = rs.getInt("PRICE");
			int row = rs.getInt("ROWX");
			int col = rs.getInt("COLY");
			hvo = new HallVO(no, seats, price,row,col);
		}
//		stuListPrint(stuList);
		DBUtility.dbClose(con, rs, pstmt);
		return hvo;
	}

}
