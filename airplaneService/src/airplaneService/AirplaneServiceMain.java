package airplaneService;

import java.sql.SQLException;
import java.util.Scanner;

import airplaneService.view.CUSTOMER_LOGIN_CHOICE;
import airplaneService.view.FLIGHT_SEARCH_MENU_CHOICE;
import airplaneService.view.MANAGER_LOGIN_CHOICE;
import airplaneService.view.MENU_CHOICE;
import airplaneService.view.MYBOOKING__MENU_CHOICE;
import airplaneService.view.MYPAGE_MENU_CHOICE;
import airplaneService.view.MenuViewer;

public class AirplaneServiceMain {
	public static Scanner sc = new Scanner(System.in);
	// 메인
	public static void main(String[] args) throws SQLException {
		int no = 0;
		boolean exitFlag = false;
		while (!exitFlag) {
			MenuViewer.mainMenuView();
			try {
				no = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.println("숫자를 입력해주세요.");
			}
			switch (no) {
			case MENU_CHOICE.LOGIN:
				int i =Integer.parseInt(sc.nextLine()); 
				if(i==1) {//로그인 기능 제작, 로그인후 customerVO 반환받아서 관리자,일반고객 구분
				exitFlag=CustomerLoginMenu();//일반고객 로그인메뉴
				}else {
				exitFlag=managerLoginMenu();//관리자 로그인매뉴
				}
				break;
			case MENU_CHOICE.PRINTFLIGHT:
				flightSearchMenu(); // 운항정보 출력후, 해당 운항정보의남은 좌석 확인 또는 나가기
				break;
			case MENU_CHOICE.REGISTER:
				registerMenu(); //회원가입, 
				break;
			case MENU_CHOICE.END:
				System.out.println("프로그램을 종료합니다.");
				exitFlag = true;
				break;
			}
		}

	}
	//회원가입 메뉴
	private static void registerMenu() {
		
	}
	
	
	private static boolean managerLoginMenu() {
		int no = 0;
		while (true) {
			MenuViewer.managerLoginMenuView();
			try {
				no = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.println("숫자를 입력해주세요.");
			}
			switch (no) {
			case MANAGER_LOGIN_CHOICE.CUSTOMER:

				MenuViewer.manageCustomerMenuView();
				break;
			case MANAGER_LOGIN_CHOICE.PLANE:
				MenuViewer.managePlaneMenuView();
				break;
			case MANAGER_LOGIN_CHOICE.COUNTRY:
				MenuViewer.manageCountryMenuView();
				break;
			case MANAGER_LOGIN_CHOICE.FLIGHT:
				MenuViewer.manageFlightMenuView();
				break;
			case MANAGER_LOGIN_CHOICE.BOOKING:
				MenuViewer.manageBookingMenuView();
				break;
			case MANAGER_LOGIN_CHOICE.LOGOUT:
				return false;
			case MANAGER_LOGIN_CHOICE.END:
				System.out.println("프로그램을 종료합니다.");
				return true;
			}
		}
		
	}
	
	private static boolean CustomerLoginMenu() {
		int no = 0;
		while (true) {
			MenuViewer.CustomerLoginMenuView();
			try {
				no = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.println("숫자를 입력해주세요.");
			}
			switch (no) {
			case CUSTOMER_LOGIN_CHOICE.BOOKING:
				break;
			case CUSTOMER_LOGIN_CHOICE.MYBOOKING:
				myBookingMenu();
				break;
			case CUSTOMER_LOGIN_CHOICE.MYPAGE:
				myPageMenu();
				break;
			case CUSTOMER_LOGIN_CHOICE.FLIGHT:
				flightSearchMenu();
				break;
			case CUSTOMER_LOGIN_CHOICE.LOGOUT:
				return false;
			case MANAGER_LOGIN_CHOICE.END:
				System.out.println("프로그램을 종료합니다.");
				return true;
			}
		}
		
	}
	private static void myBookingMenu() {
		int no = 0;
		boolean exitFlag = false;
		while (!exitFlag) {
			MenuViewer.myBookingMenuView();
			try {
				no = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.println("숫자를 입력해주세요.");
			}
			switch (no) {
			case MYBOOKING__MENU_CHOICE.CANCLE:
				break;
			case MYBOOKING__MENU_CHOICE.UPDATE:
				break;
			case MYBOOKING__MENU_CHOICE.END:
				System.out.println("프로그램을 종료합니다.");
				exitFlag = true;
				break;
			}
		}
		
	}
	private static void myPageMenu() {
		int no = 0;
		boolean exitFlag = false;
		while (!exitFlag) {
			MenuViewer.myPageMenuView();
			try {
				no = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.println("숫자를 입력해주세요.");
			}
			switch (no) {
			case MYPAGE_MENU_CHOICE.UPDATE:
				break;
			case MYPAGE_MENU_CHOICE.END:
				System.out.println("프로그램을 종료합니다.");
				exitFlag = true;
				break;
			}
		}
		
	}
	private static void flightSearchMenu() {
		int no = 0;
		boolean exitFlag = false;
		while (!exitFlag) {
			MenuViewer.flightSearchMenuView();
			try {
				no = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.println("숫자를 입력해주세요.");
			}
			switch (no) {
			case FLIGHT_SEARCH_MENU_CHOICE.SEATS:
				break;
			case FLIGHT_SEARCH_MENU_CHOICE.SEARCH:
				break;
			case FLIGHT_SEARCH_MENU_CHOICE.END:
				System.out.println("프로그램을 종료합니다.");
				exitFlag = true;
				break;
			}
		}
		
	}


}
