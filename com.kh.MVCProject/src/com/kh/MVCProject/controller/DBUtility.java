package com.kh.MVCProject.controller;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBUtility {
	
	public static Connection dbCon() {
		//db.properties 에서 id, pw 가져오기
		String filePath = "D:\\javaStudy\\com.kh.MVCProject\\src\\db.properties";
		Properties pt= new Properties();
		try {
			pt.load(new FileReader(filePath));
		} catch (Exception e) {
			e.printStackTrace();
		}
		String id=(pt.getProperty("id"));
		String pw=(pt.getProperty("pw"));
		String url=(pt.getProperty("url"));
		Connection con = null;
		//1. jdbc driver load, connection
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url,id,pw);
		} catch (ClassNotFoundException e) {
			System.out.println(e.toString());
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		return con;
	}

	public static void dbClose(Connection con, Statement stmt,Statement stmt2, ResultSet rs) {
		if (con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println(e.toString()); 
			}
		}
		if (stmt!=null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				System.out.println(e.toString());
			}
		}if (stmt2!=null) {
			try {
				stmt2.close();
			} catch (SQLException e) {
				System.out.println(e.toString());
			}
		}
		if (rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				System.out.println(e.toString());
			}
		}
	}
	public static void dbClose(Connection con, Statement stmt, ResultSet rs) {
		if (con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println(e.toString()); 
			}
		}
		if (stmt!=null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				System.out.println(e.toString());
			}
		}
		
		if (rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				System.out.println(e.toString());
			}
		}
	}

	public static void dbClose(Connection con, Statement stmt) {
		if (con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println(e.toString()); 
			}
		}
		if (stmt!=null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				System.out.println(e.toString());
			}
		}
		
		
	}
	public static void dbClose(Connection con, Statement stmt, Statement stmt2) {
		if (con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println(e.toString()); 
			}
		}
		if (stmt!=null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				System.out.println(e.toString());
			}
		}
		if (stmt2!=null) {
			try {
				stmt2.close();
			} catch (SQLException e) {
				System.out.println(e.toString());
			}
		}
		
	}
	
}
