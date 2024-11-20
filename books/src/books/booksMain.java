package books;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class booksMain {
 static	Scanner sc= new Scanner(System.in);
	public static void main(String[] args) throws SQLException {
		boolean flag=false;
		while(!flag) {
		printMenu();
		int num = Integer.parseInt(sc.nextLine());
		
		switch (num) {
		case 1:
			booksPrint();
		 break;
		case 2: 
			booksInsert();
			 break;
		case 3: 
			booksUpdate();
			 break;
		case 4: 
			booksDelete();
			 break;
		
		case 5: 
			flag=true;
			break;
			
		default: break;
		
		}
		}
		// TODO Auto-generated method stub

	}

	
	private static void booksDelete() throws SQLException {
		// TODO Auto-generated method stub
		Connection con = null;
		Statement stmt = null;
		
		con = DBConnection.dbCon();
		stmt=con.createStatement();
		System.out.println("삭제를 원하는 번호를 입력해주세요");
		int delNo= sc.nextInt();
		sc.nextLine();
		int result=stmt.executeUpdate("DELETE FROM BOOKS WHERE ID="+delNo);
		System.out.println((result!=0)?"삭제성공":"삭제실패");
		DBConnection.dbClose(con, stmt);
	}


	private static void booksUpdate() throws SQLException {
		Connection con = null;
		Statement stmt = null;
		
		con = DBConnection.dbCon();
		stmt=con.createStatement();
		System.out.println("수정을 원하는 번호를 입력해주세요");
		int upNum = Integer.parseInt(sc.nextLine());
		System.out.print("책 제목을 입력해주세요");
		String name= sc.nextLine();
		System.out.print("저자를 입력해주세요");
		String pub= sc.nextLine();
		System.out.print("연도를 입력해주세요");
		String year= sc.nextLine();
		System.out.print("가격을 입력해주세요");
		int pri = Integer.parseInt(sc.nextLine());
		System.out.println("수정중입니다.");
		books nb= new books(0, name, pub, year, pri);
		int result=stmt.executeUpdate("UPDATE books set Title='"+nb.getTitle()+"',Publisher='"+nb.getPublisher()+"',Year='"+nb.getYear()+"',Price="+nb.getPrice()+" where id="+upNum);
		System.out.println((result!=0)?"수정성공":"수정실패");
		DBConnection.dbClose(con, stmt);
		
	}


	private static void booksInsert() throws SQLException {
		// TODO Auto-generated method stubConnection con = null;
		Connection con = null;
		Statement stmt = null;
		System.out.print("책 제목을 입력해주세요");
		String name= sc.nextLine();
		System.out.print("저자를 입력해주세요");
		String pub= sc.nextLine();
		System.out.print("연도를 입력해주세요");
		String year= sc.nextLine();
		System.out.print("가격을 입력해주세요");
		int pri= sc.nextInt();
		sc.nextLine();
		books nb= new books(0, name, pub, year, pri);
		con = DBConnection.dbCon();
		stmt=con.createStatement();
		int result=stmt.executeUpdate("INSERT INTO books VALUES (bookS_id_seq.nextval, '"+nb.getTitle()+"', '"+nb.getPublisher()+"', '"+nb.getYear()+"', "+nb.getPrice()+")");
		System.out.println((result!=0)?"입력성공":"입력실패");
		DBConnection.dbClose(con, stmt);
	}


	private static void booksPrint() throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("출력중입니다....");
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<books> bList= new ArrayList<books>();
		con = DBConnection.dbCon();
			stmt=con.createStatement();
			rs=stmt.executeQuery("select * from books");
			
		while(rs.next()) {
			int id= rs.getInt("ID");
			String title= rs.getString("TITLE");
			String publisher= rs.getString("PUBLISHER");
			int year= rs.getInt("YEAR");
			String price= rs.getString("PRICE");
			
			bList.add(new books(id, title, publisher, price, year));
		}
		bListPrint(bList);
		DBConnection.dbClose(con, stmt, rs);
	}


	private static void printMenu() {
		System.out.println("1. 출력 2. 추가 3. 수정 4. 삭제 5. 나가기");
		
	}
	private static void bListPrint(ArrayList<books> bList) {
		for(books data :bList) {
			System.out.println(data.toString());
		}
		
	}

}
