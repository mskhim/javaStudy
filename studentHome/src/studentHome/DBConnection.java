package studentHome;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
public static Connection dbCon() {
	Connection con = null;
	

	//1. jdbc driver load, connection
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521/xe","HR","hr");
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
	
}
