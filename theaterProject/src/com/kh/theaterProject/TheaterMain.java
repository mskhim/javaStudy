package com.kh.theaterProject;

import java.sql.SQLException;
import java.util.Scanner;

import com.kh.theaterProject.controller.BookingRegisterManager;
import com.kh.theaterProject.controller.CinemaRegisterManager;
import com.kh.theaterProject.controller.CustomerRegisterManager;
import com.kh.theaterProject.controller.HallRegisterManager;
import com.kh.theaterProject.controller.PlayingRegisterManager;
import com.kh.theaterProject.model.CustomerVO;
import com.kh.theaterProject.view.MANAGE_MENU_CHOICE;
import com.kh.theaterProject.view.MANAGE_SUB_MENU_CHOICE;
import com.kh.theaterProject.view.MENU_BOOKING_INQUERY;
import com.kh.theaterProject.view.MENU_CHOICE;
import com.kh.theaterProject.view.MenuViewer;
import com.kh.theaterProject.view.PlayingPrint;

public class TheaterMain {
	public static Scanner sc = new Scanner(System.in);
	
	// 메인
	public static void main(String[] args) throws SQLException {
		int no = 0;
		CustomerRegisterManager crm = new CustomerRegisterManager();
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
				loginMenu(); 
				break;
			case MENU_CHOICE.PRINTPLAYING:
				PlayingPrint.printAll();
				break;
			case MENU_CHOICE.REGISTER:
				crm.insertRegistManager();
				break;
			case MENU_CHOICE.MANAGERMODE:
				managerModeMenu();
				break;
			case MENU_CHOICE.END:
				System.out.println("프로그램을 종료합니다.");
				exitFlag = true;
				break;
			default:
				System.out.println("해당 메뉴 번호만 입력하세요.");

			}
		}

	}

	// 관리자 모드 메뉴
	private static void managerModeMenu() {
		int no = 0;
		boolean exitFlag = false;
		while (!exitFlag) {
			MenuViewer.manageMenuView();
			try {
				no = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.println("숫자를 입력해주세요.");
			}
			switch (no) {
			case MANAGE_MENU_CHOICE.CUSTOMER:
				manageCustomerMenu();
				break;
			case MANAGE_MENU_CHOICE.HALL:
				manageHallMenu();
				break;
			case MANAGE_MENU_CHOICE.CINEMA:
				manageCinemaMenu();
				break;
			case MANAGE_MENU_CHOICE.PLAYING:
				managePlayingMenu();
				break;
			case MANAGE_MENU_CHOICE.BOOKING:
				manageBookingMenu();
				break;
			case MANAGE_MENU_CHOICE.END:
				System.out.println("이전메뉴료 돌아갑니다.");
				exitFlag = true;
				break;
			default:
				System.out.println("해당 메뉴 번호만 입력하세요.");

			}
		}
	}

	// 관리자 모드의 예매 메뉴
	private static void manageBookingMenu() {
		BookingRegisterManager brm = new  BookingRegisterManager();
		int no = 0;
		boolean exitFlag = false;
		while (!exitFlag) {
			MenuViewer.manageBookingMenuView();
			try {
				no = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.println("숫자를 입력해주세요.");
			}
			try {
			switch (no) {
			
			case MANAGE_SUB_MENU_CHOICE.SELECT:
					brm.selectManager();
				break;
			case MANAGE_SUB_MENU_CHOICE.INSERT:
				brm.insertManager();
				break;
			case MANAGE_SUB_MENU_CHOICE.UPDATE:
				brm.updateManager();
				break;
			case MANAGE_SUB_MENU_CHOICE.DELETE:
				brm.deleteManager();
				break;
			case MANAGE_SUB_MENU_CHOICE.END:
				System.out.println("이전메뉴로 돌아갑니다.");
				exitFlag = true;
				break;
			default:
				System.out.println("해당 메뉴 번호만 입력하세요.");

			}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	// 관리자 모드의 상영작 메뉴
	private static void managePlayingMenu() {
		int no = 0;
		boolean exitFlag = false;
		PlayingRegisterManager prm = new PlayingRegisterManager();
		while (!exitFlag) {
			MenuViewer.managePlayingMenuView();
			try {
				no = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.println("숫자를 입력해주세요.");
			}
			try {
				switch (no) {
				case MANAGE_SUB_MENU_CHOICE.SELECT:
					prm.selectManager();
					break;
				case MANAGE_SUB_MENU_CHOICE.INSERT:
					prm.insertManager();
					break;
				case MANAGE_SUB_MENU_CHOICE.UPDATE:
					prm.updateManager();
					break;
				case MANAGE_SUB_MENU_CHOICE.DELETE:
					prm.deleteManager();
					break;
				case MANAGE_SUB_MENU_CHOICE.FIND:
					prm.findManager();
					break;
				case MANAGE_SUB_MENU_CHOICE.END:
					System.out.println("이전메뉴로 돌아갑니다.");
					exitFlag = true;
					break;
				default:
					System.out.println("해당 메뉴 번호만 입력하세요.");

				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	// 관리자 모드의 영화 메뉴
	private static void manageCinemaMenu() {
		int no = 0;
		CinemaRegisterManager cnrm = new CinemaRegisterManager();
		boolean exitFlag = false;
		try {
			while (!exitFlag) {
				MenuViewer.manageCinemaMenuView();
				try {
					no = Integer.parseInt(sc.nextLine());
				} catch (Exception e) {
					System.out.println("숫자를 입력해주세요.");
				}
				switch (no) {
				case MANAGE_SUB_MENU_CHOICE.SELECT:
					cnrm.selectManager();
					break;
				case MANAGE_SUB_MENU_CHOICE.INSERT:
					cnrm.insertManager();
					break;
				case MANAGE_SUB_MENU_CHOICE.UPDATE:
					cnrm.updateManager();
					break;
				case MANAGE_SUB_MENU_CHOICE.DELETE:
					cnrm.deleteManager();
					break;
				case MANAGE_SUB_MENU_CHOICE.FIND:
					cnrm.findManager();
					break;
				case MANAGE_SUB_MENU_CHOICE.END:
					System.out.println("이전메뉴로 돌아갑니다.");
					exitFlag = true;
					break;
				default:
					System.out.println("해당 메뉴 번호만 입력하세요.");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// 관리자 모드의 상영관 메뉴
	private static void manageHallMenu() {
		int no = 0;
		HallRegisterManager hrm = new HallRegisterManager();
		boolean exitFlag = false;
		while (!exitFlag) {
			MenuViewer.manageHallMenuView();
			try {
				no = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.println("숫자를 입력해주세요.");
			}
			try {
				switch (no) {
				case MANAGE_SUB_MENU_CHOICE.SELECT:
					hrm.selectManager();
					break;
				case MANAGE_SUB_MENU_CHOICE.INSERT:
					hrm.insertManager();
					break;
				case MANAGE_SUB_MENU_CHOICE.UPDATE:
					hrm.updateManager();
					break;
				case MANAGE_SUB_MENU_CHOICE.DELETE:
					hrm.deleteManager();
					break;
				case MANAGE_SUB_MENU_CHOICE.FIND:
					hrm.findManager();
					break;
				case MANAGE_SUB_MENU_CHOICE.END:
					System.out.println("이전메뉴로 돌아갑니다.");
					exitFlag = true;
					break;
				default:
					System.out.println("해당 메뉴 번호만 입력하세요.");

				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	// 관리자 모드의 고객 메뉴
	private static void manageCustomerMenu() {
		int no = 0;
		boolean exitFlag = false;
		CustomerRegisterManager crm = new CustomerRegisterManager();
		while (!exitFlag) {
			MenuViewer.manageCustomerMenuView();
			try {
				no = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.println("숫자를 입력해주세요.");
			}
			try {
				switch (no) {
				case MANAGE_SUB_MENU_CHOICE.SELECT:
					crm.selectManager();
					break;
				case MANAGE_SUB_MENU_CHOICE.INSERT:
					crm.insertRandomManager();
					break;
				case MANAGE_SUB_MENU_CHOICE.UPDATE:
					crm.updateManager();
					break;
				case MANAGE_SUB_MENU_CHOICE.DELETE:
					crm.deleteManager();
					break;
				case MANAGE_SUB_MENU_CHOICE.FIND:
					crm.findManager();
					break;
				case MANAGE_SUB_MENU_CHOICE.END:
					System.out.println("이전메뉴로 돌아갑니다.");
					exitFlag = true;
					break;
				default:
					System.out.println("해당 메뉴 번호만 입력하세요.");

				}

			} catch (Exception e) {
			}
		}
	}

	// 예매확인 메뉴
	private static void bookingInqueryMenu() {
		BookingRegisterManager brm = new BookingRegisterManager();
		int no = 0;
		boolean exitFlag = false;
		
		while (!exitFlag) {
			try {
				System.out.println("1. 예매 취소하기 2. 예매 변경하기 3. 나가기");
				no = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.println("숫자를 입력해주세요.");
			}
			try {
				switch (no) {
				case MENU_BOOKING_INQUERY.CANCLE:
					brm.deleteCancleManager();
					break;
				case MENU_BOOKING_INQUERY.UPDATE:
					brm.updateManager();
					break;
				case MENU_BOOKING_INQUERY.END:
					System.out.println("이전메뉴로 돌아갑니다.");
					exitFlag = true;
					break;
				default:
					System.out.println("해당 메뉴 번호만 입력하세요.");
					break;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 로그인 메뉴
	private static void loginMenu() throws SQLException {
		BookingRegisterManager brm = new BookingRegisterManager();
		CustomerVO cvo = brm.loginManager();
		if(cvo.getName()==null) {
			return;
		}else {
			boolean exitFlag = false;
			int num =0 ;
			while(!exitFlag) {
			System.out.println("1. 예매하기 2. 예매 조회,변경  3. 나가기");
			try {
				num = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.println("숫자를 입력해주세요");
				System.out.print(">>");
			}
			switch (num) {
			case 1: {
			brm.bookingManager(cvo);	
			break;
			}
			case 2: {
			brm.bookingInquiryManager(cvo);
				bookingInqueryMenu();
				break;
			}
			case 3: {
				exitFlag = true;
				break;
			}
			default:
				System.out.println("입력한 메뉴 번호를 확인해주세요");
				System.out.print(">>");
			}
			}
			
		}
	}
}


