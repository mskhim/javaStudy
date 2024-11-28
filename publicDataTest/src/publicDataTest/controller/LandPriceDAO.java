package publicDataTest.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import publicDataTest.model.LandPriceVO;

public class LandPriceDAO {
	public static final String LANDPRICE_SELECT_SQL = "SELECT * FROM LANDPRICE";
	public static final String LANDPRICE_CHECK_SELECT_SQL = "SELECT COUNT(*) AS COUNT FROM LANDPRICE WHERE NODENO = ?";
	public static final String LANDPRICE_INSERT_SQL = "INSERT INTO LANDPRICE VALUES(?, ?, ?, ?, ?)";
	public static final String LANDPRICE_UPDATE_SQL = "UPDATE LANDPRICE SET GPSLATI = ?, GPSLONG=?,NODEID=?,NODENM=? WHERE NODENO=?";
	public static final String LANDPRICE_DELETE_SQl = "DELETE FROM LANDPRICE WHERE NODENO = ?";

	// LandPriceVO를 받아서 db에 삽입
	public static boolean DBInsert(LandPriceVO lpvo) {
		// Conection
		Connection con = null;
		PreparedStatement pstmt = null;
		int result1 = 0;
		// 1 Load, 2. connect
		con = DBUtility.dbCon();
		// 3.statement
		try {
			pstmt = con.prepareStatement(LANDPRICE_INSERT_SQL);
			pstmt.setInt(1, lpvo.getNodeno());
			pstmt.setDouble(2, lpvo.getGpslati());
			pstmt.setDouble(3, lpvo.getGpslong());
			pstmt.setString(4, lpvo.getNodeid());
			pstmt.setString(5, lpvo.getNodenm());
			result1 = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtility.dbClose(con, pstmt);
		return (result1 != 0) ? true : false;
	}

	// 테이블 전체를 ArrayList에 저장
	public static ArrayList<LandPriceVO> LandPriceSelect() throws SQLException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<LandPriceVO> lList = new ArrayList<LandPriceVO>();
		con = DBUtility.dbCon();
		stmt = con.createStatement();
		rs = stmt.executeQuery(LANDPRICE_SELECT_SQL);
		while (rs.next()) {
			double gpslati = rs.getDouble("GPSLATI");
			double gpslong = rs.getDouble("GPSLONG");
			String nodeid = rs.getString("NODEID");
			String nodenm = rs.getString("NODENM");
			int nodeno = rs.getInt("NODENO");
			LandPriceVO stu = new LandPriceVO(gpslati, gpslong, nodeid, nodenm, nodeno);
			lList.add(stu);
		}
//		stuListPrint(stuList);
		DBUtility.dbClose(con, rs, stmt);
		return lList;
	}

	//
//	// LandPriceVO를 받아서 db 업데이트
//	public static boolean DBUpdate(LandPriceVO lpvo) throws SQLException {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		con = DBUtility.dbCon();
//		pstmt = con.prepareStatement(LANDPRICE_UPDATE_SQL);
//		pstmt.setDouble(1, lpvo.getGpslati());
//		pstmt.setDouble(2, lpvo.getGpslong());
//		pstmt.setString(1, lpvo.getNodeid());
//		pstmt.setString(1, lpvo.getNodenm());
//		pstmt.setInt(1, lpvo.getNodeno());
//		int result1 = pstmt.executeUpdate();
//		DBUtility.dbClose(con, pstmt);
//		return (result1 != 0) ? true : false;
//	}
//
	// LANDPRICE no를 받아서 db 삭제
	public static boolean DBDelete(LandPriceVO lpvo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		con = DBUtility.dbCon();
		pstmt = con.prepareStatement(LANDPRICE_DELETE_SQl);
		pstmt.setInt(1, lpvo.getNodeno());
		int result = pstmt.executeUpdate();
		DBUtility.dbClose(con, pstmt);
		return (result != 0) ? true : false;
	}

	// 테이블 전체를 ArrayList에 저장
	public static int LandPriceCheckNodeNo(LandPriceVO lpvo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int rt = 0;
		con = DBUtility.dbCon();
		try {
			pstmt = con.prepareStatement(LANDPRICE_CHECK_SELECT_SQL);
			pstmt.setInt(1, lpvo.getNodeno());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				rt = rs.getInt("COUNT");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtility.dbClose(con, rs, pstmt);
		return rt;
	}

}
