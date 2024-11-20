package studentTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ConnetDB {

	public static void main(String[] args) {
		//객체참조변수 선언
		Connection con = null;
		ResultSet rs = null;
		Statement stmt = null;
		ArrayList<Employees> empList = new ArrayList<Employees>();
		
		con=DBConnection.dbCon();
		//3. 쿼리문 statement
		try {
			stmt= con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM EMPLOYEES ORDER BY SALARY");
			
			while(rs.next()) {
				int Id=rs.getInt("EMPLOYEE_ID");
				String Name = rs.getString("FIRST_NAME");
				int Salary=rs.getInt("SALARY");
				Employees employee= new Employees(Id, Name, Salary);
				empList.add(employee);
		}} catch (SQLException e) {
		
		
	}
		//4-2 데이터 출력
		empListPrint(empList);
		DBConnection.dbClose(con, stmt, rs);
	}
	private static void empListPrint(ArrayList<Employees> empList) {
		for(Employees e:empList) {
			System.out.println(e.toString());
		}
		
	}

}
