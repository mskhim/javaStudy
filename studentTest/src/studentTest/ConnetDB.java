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
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Employees> empList = new ArrayList<Employees>();
		//1. jdbc driver load
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("1. 드라이버 적재 성공");
		} catch (ClassNotFoundException e) {
			System.out.println("1. 드라이버 적재 실패"+e.toString());
		}
		//2. connection
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521/xe","HR","hr");
			System.out.println("2. 오라클 접속 성공");
		} catch (SQLException e) {
			System.out.println("2. 오라클 접속 실패");
			e.printStackTrace();
		}
		//3. 쿼리문 statement
		try {
			stmt= con.createStatement();
			System.out.println("3. statement 객체 생성 성공");
		} catch (SQLException e) {
			System.out.println("3. statement 객체 생성 실패"+e.toString());
		}
		//4. result set
		try {
				rs = stmt.executeQuery("SELECT * FROM EMPLOYEES ORDER BY SALARY");
				System.out.println("4. result set 객체 생성 성공");
		} catch (SQLException e) {
			System.out.println("4. result set 객체 생성 실패");
			e.printStackTrace();
		}
		//4-1 데이터 저장
		try {
			while(rs.next()) {
				int Id=rs.getInt("EMPLOYEE_ID");
				String Name = rs.getString("FIRST_NAME");
				int Salary=rs.getInt("SALARY");
				Employees employee= new Employees(Id, Name, Salary);
				empList.add(employee);
			}
		} catch (SQLException e) {
		}
		
		//4-2 데이터 출력
		empListPrint(empList);
		
		//5. 닫기
		if (con!=null) {
			try {
				con.close();
				rs.close();
				System.out.println("5. 오라클 커넥션 닫기");
			} catch (SQLException e) {
				System.out.println("5. conclose 실패"+e.toString());
			}
		}
		if (stmt!=null) {
			try {
				stmt.close();
				System.out.println("5. 오라클 커넥션 닫기");
			} catch (SQLException e) {
				System.out.println("5. conclose 실패"+e.toString());
			}
		}
		if (rs!=null) {
			try {
				rs.close();
				System.out.println("5. 오라클 커넥션 닫기");
			} catch (SQLException e) {
				System.out.println("5. conclose 실패"+e.toString());
			}
		}
	}

	private static void empListPrint(ArrayList<Employees> empList) {
		for(Employees e:empList) {
			System.out.println(e.toString());
		}
		
	}

}
