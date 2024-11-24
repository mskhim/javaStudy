package com.kh.theaterProject.controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.kh.theaterProject.model.CustomerVO;

public class CustomerDAO {
	private final String SELECT_SQL = "SELECT * FROM CUSTOMER ORDER BY NO";
	private final String SELECT_BY_NO_SQL = "SELECT * FROM CUSTOMER WHERE NO = TO_CHAR(?,'FM00000')";
	private final String SELECT_COUNT_BY_ID_SQL = "SELECT * FROM CUSTOMER WHERE ID = ?";
	private final String SELECT_SORT_SQL = "SELECT * FROM CUSTOMER ORDER BY BOOKCOUNT desc";
	private final String INSERT_SQL = "INSERT INTO CUSTOMER(NO, NAME, ID, PWD, BIRTH,PHONE,REGISTDATE) "
			+ "VALUES(to_char((select nvl(max(no),0)+1 from CUSTOMER),'FM00000'), ?, ?, ?, ?,?,SYSDATE)";
	private final String INSERT_RANDOM_SQL = "insert into CUSTOMER(no, NAME, ID, PWD, REGISTDATE) "
			+ "VALUES(to_char((select nvl(max(no),0)+1 from CUSTOMER),'FM00000'),DBMS_RANDOM.string('U',5),DBMS_RANDOM.string('U',10),DBMS_RANDOM.string('U',10),sysdate)";
	private final String UPDATE_SQL = "UPDATE customer SET NAME = ?, ID = ?, PWD = ?, BIRTH = ?, PHONE = ? WHERE NO = ? ";
	private final String DELETE_SQL = "DELETE FROM customer WHERE NO = TO_CHAR(?,'FM00000')";

	// CustomerVO를 받아서 db에 insert 후 성공여부 true false 반환
	public boolean insertDB(CustomerVO cvo) throws SQLException {
		// Conection
		Connection con = null;
		PreparedStatement pstmt = null;
		// 1 Load, 2. connect
		con = DBUtility.dbCon();
		// 3.statement
		pstmt = con.prepareStatement(INSERT_SQL);
		pstmt.setString(1, cvo.getName());
		pstmt.setString(2, cvo.getId());
		pstmt.setString(3, cvo.getPwd());
		pstmt.setDate(4, (cvo.getBirth()));
		pstmt.setString(5, cvo.getPhone());

		int result1 = pstmt.executeUpdate();
		DBUtility.dbClose(con, pstmt);
		return (result1 != 0) ? true : false;
	}

	// 랜덤으로 insert 후 성공여부 true false 반환
	public boolean insertRandomDB() throws SQLException {
		// Conection
		Connection con = null;
		PreparedStatement pstmt = null;
		// 1 Load, 2. connect
		con = DBUtility.dbCon();
		// 3.statement
		pstmt = con.prepareStatement(INSERT_RANDOM_SQL);
		int result1 = pstmt.executeUpdate();
		DBUtility.dbClose(con, pstmt);
		return (result1 != 0) ? true : false;
	}

	// CustomerVO를 받아서 db에 update 후 성공여부 true false 반환
	public boolean updateDB(CustomerVO cvo) throws SQLException  {
		Connection con = null;
		PreparedStatement pstmt = null;
		con = DBUtility.dbCon();
		int result1=0;
			pstmt = con.prepareStatement(UPDATE_SQL);
			pstmt.setString(1, cvo.getName());
			pstmt.setString(2, cvo.getId());
			pstmt.setString(3, cvo.getPwd());
			pstmt.setDate(4, cvo.getBirth());
			pstmt.setString(5, cvo.getPhone());
			pstmt.setString(6, cvo.getNo());
			 result1= pstmt.executeUpdate();
			DBUtility.dbClose(con, pstmt);
			
		return (result1 != 0) ? true : false;
	}

	// CustomerVO를 받아서 db에 delete 후 성공여부 true false 반환
	public boolean deleteDB(CustomerVO cvo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		con = DBUtility.dbCon();
		pstmt = con.prepareStatement(DELETE_SQL);
		pstmt.setString(1, cvo.getNo());
		int result = pstmt.executeUpdate();
		DBUtility.dbClose(con, pstmt);
		return (result != 0) ? true : false;
	}

	// 테이블 전체를 List에 저장 후 반환
	public ArrayList<CustomerVO> retrunList() throws SQLException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<CustomerVO> customerList = new ArrayList<CustomerVO>();
		con = DBUtility.dbCon();
		stmt = con.createStatement();

		rs = stmt.executeQuery(SELECT_SQL);
		while (rs.next()) {
			String no = rs.getString("NO");
			String name = rs.getString("NAME");
			String id = rs.getString("ID");
			String pwd = rs.getString("PWD");
			Date Birth = rs.getDate("BIRTH");
			String phone = rs.getString("PHONE");
			int bookCount = rs.getInt("BOOKCOUNT");
			Date registDate = rs.getDate("REGISTDATE");
			CustomerVO cvo = new CustomerVO(no, name, id, pwd, Birth, phone, bookCount, registDate);
			customerList.add(cvo);
		}
//		stuListPrint(stuList);
		DBUtility.dbClose(con, rs, stmt);
		return customerList;
	}

	// 테이블을 예약많은순으로 정렬후 List 반환
	public ArrayList<CustomerVO> returnSortList() throws SQLException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<CustomerVO> customerList = new ArrayList<CustomerVO>();
		con = DBUtility.dbCon();
		stmt = con.createStatement();

		rs = stmt.executeQuery(SELECT_SORT_SQL);
		while (rs.next()) {
			String no = rs.getString("NO");
			String name = rs.getString("NAME");
			String id = rs.getString("ID");
			String pwd = rs.getString("PWD");
			Date Birth = rs.getDate("BIRTH");
			String phone = rs.getString("PHONE");
			int bookCount = rs.getInt("BOOKCOUNT");
			Date registDate = rs.getDate("REGISTDATE");
			CustomerVO cvo = new CustomerVO(no, name, id, pwd, Birth, phone, bookCount, registDate);
			customerList.add(cvo);
		}
//		stuListPrint(stuList);
		DBUtility.dbClose(con, rs, stmt);
		return customerList;
	}

	// no가 들어있는 CusotomerVO를 받아서 그에 해당하는 db의 CustomerVO를 반환
	public CustomerVO returncvoByCode(CustomerVO cvo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		con = DBUtility.dbCon();
		pstmt = con.prepareStatement(SELECT_BY_NO_SQL);
		pstmt.setString(1, cvo.getNo());
		rs = pstmt.executeQuery();
		while (rs.next()) {
			String no = rs.getString("NO");
			String name = rs.getString("NAME");
			String id = rs.getString("ID");
			String pwd = rs.getString("PWD");
			Date Birth = rs.getDate("BIRTH");
			String phone = rs.getString("PHONE");
			int bookCount = rs.getInt("BOOKCOUNT");
			Date registDate = rs.getDate("REGISTDATE");
			cvo = new CustomerVO(no, name, id, pwd, Birth, phone, bookCount, registDate);
		}
//		stuListPrint(stuList);
		DBUtility.dbClose(con, rs, pstmt);
		return cvo;
	}

	// id가 들어있는 CusotomerVO를 받아서 찾아서 반환
	public CustomerVO returncvoById(CustomerVO cvo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		con = DBUtility.dbCon();
		pstmt = con.prepareStatement(SELECT_COUNT_BY_ID_SQL);
		pstmt.setString(1, cvo.getId());
		rs = pstmt.executeQuery();
		while (rs.next()) {
			String no = rs.getString("NO");
			String name = rs.getString("NAME");
			String id = rs.getString("ID");
			String pwd = rs.getString("PWD");
			Date Birth = rs.getDate("BIRTH");
			String phone = rs.getString("PHONE");
			int bookCount = rs.getInt("BOOKCOUNT");
			Date registDate = rs.getDate("REGISTDATE");
			cvo = new CustomerVO(no, name, id, pwd, Birth, phone, bookCount, registDate);
		}
//			stuListPrint(stuList);
		DBUtility.dbClose(con, rs, pstmt);
		return cvo;
	}

}
