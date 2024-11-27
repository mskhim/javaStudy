package myProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class dbTest {

	public static void main(String[] args) {
		//객체참조변수 선언
				Connection con = null;
				Statement stmt = null;
				ResultSet rs = null;
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
					} catch (SQLException e) {
						System.out.println("2.오류");
					}
				//3. 쿼리문 statement
					try {
						stmt = con.createStatement();
					} catch (SQLException e) {
						System.out.println("3.오류");
					}
				//4. result set
					try {
						stmt.executeUpdate("create table test(no number(5), name varchar2(5))");
					} catch (SQLException e) {
						System.out.println("4.오류");
					}
				//4-1 데이터 저장
					try {
						while(rs.next()) {
							int id =rs.getInt("DEPARTMENT_ID");
							String name =rs.getString("DEPARTMENT_NAME");
							System.out.println(id+name);						
						}
					} catch (SQLException e) {
						System.out.println("5.오류");
					}
					
				//4-2 데이터 출력
				
				//5. 닫기

	}

}
