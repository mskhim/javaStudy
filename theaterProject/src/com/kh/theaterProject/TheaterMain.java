package com.kh.theaterProject;

import java.util.Scanner;

import com.kh.theaterProject.view.MANAGE_MENU_CHOICE;
import com.kh.theaterProject.view.MANAGE_SUB_MENU_CHOICE;
import com.kh.theaterProject.view.MENU_CHOICE;
import com.kh.theaterProject.view.MenuViewer;

public class TheaterMain {

	public static Scanner sc = new Scanner(System.in);
	//메인
	public static void main(String[] args) {
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
			case MENU_CHOICE.BOOKING:
				bookingMenu();
				break;
			case MENU_CHOICE.CHECKBOOKING:
				checkBookingMenu();
				break;
			case MENU_CHOICE.PRINTPLAYING:
				printPlayingMenu();
				break;
			case MENU_CHOICE.REGISTER:
				registerMenu();
				break;
			case MENU_CHOICE.MANAGERMODE:
				managerModeMenu();
				break;
			case MENU_CHOICE.END:
				System.out.println("프로그램을 종료합니다.");
				exitFlag = true;
			default:
				System.out.println("해당 메뉴 번호만 입력하세요.");

			}
		}

	}
	//관리자 모드 메뉴
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
			case MANAGE_MENU_CHOICE.END:
				System.out.println("이전메뉴료 돌아갑니다.");
				exitFlag = true;
			default:
				System.out.println("해당 메뉴 번호만 입력하세요.");

			}
		}
	}
	//관리자 모드의 상영작 메뉴
	private static void managePlayingMenu() {
		int no = 0;
		boolean exitFlag = false;
		while (!exitFlag) {
			MenuViewer.managePlayingMenuView();
			try {
				no = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.println("숫자를 입력해주세요.");
			}
			switch (no) {
			case MANAGE_SUB_MENU_CHOICE.SELECT:
				manageCustomerMenu();
				break;
			case MANAGE_SUB_MENU_CHOICE.INSERT:
				manageHallMenu();
				break;
			case MANAGE_SUB_MENU_CHOICE.UPDATE:
				manageCinemaMenu();
				break;
			case MANAGE_SUB_MENU_CHOICE.DELETE:
				managePlayingMenu();
				break;
			case MANAGE_SUB_MENU_CHOICE.END:
				System.out.println("이전메뉴로 돌아갑니다.");
				exitFlag = true;
			default:
				System.out.println("해당 메뉴 번호만 입력하세요.");

			}
		}
		
	}
	//관리자 모드의 영화 메뉴
	private static void manageCinemaMenu() {
		int no = 0;
		boolean exitFlag = false;
		while (!exitFlag) {
			MenuViewer.manageCinemaMenuView();
			try {
				no = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.println("숫자를 입력해주세요.");
			}
			switch (no) {
			case MANAGE_SUB_MENU_CHOICE.SELECT:
				manageCustomerMenu();
				break;
			case MANAGE_SUB_MENU_CHOICE.INSERT:
				manageHallMenu();
				break;
			case MANAGE_SUB_MENU_CHOICE.UPDATE:
				manageCinemaMenu();
				break;
			case MANAGE_SUB_MENU_CHOICE.DELETE:
				managePlayingMenu();
				break;
			case MANAGE_SUB_MENU_CHOICE.END:
				System.out.println("이전메뉴로 돌아갑니다.");
				exitFlag = true;
			default:
				System.out.println("해당 메뉴 번호만 입력하세요.");

			}
		}
		
	}
	//관리자 모드의 상영관 메뉴
	private static void manageHallMenu() {
		int no = 0;
		boolean exitFlag = false;
		while (!exitFlag) {
			MenuViewer.manageHallMenuView();
			try {
				no = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.println("숫자를 입력해주세요.");
			}
			switch (no) {
			case MANAGE_SUB_MENU_CHOICE.SELECT:
				manageCustomerMenu();
				break;
			case MANAGE_SUB_MENU_CHOICE.INSERT:
				manageHallMenu();
				break;
			case MANAGE_SUB_MENU_CHOICE.UPDATE:
				manageCinemaMenu();
				break;
			case MANAGE_SUB_MENU_CHOICE.DELETE:
				managePlayingMenu();
				break;
			case MANAGE_SUB_MENU_CHOICE.END:
				System.out.println("이전메뉴로 돌아갑니다.");
				exitFlag = true;
			default:
				System.out.println("해당 메뉴 번호만 입력하세요.");

			}
		}
		
	}
	//관리자 모드의 고객 메뉴
	private static void manageCustomerMenu() {
		int no = 0;
		boolean exitFlag = false;
		while (!exitFlag) {
			MenuViewer.manageCustomerMenuView();
			try {
				no = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.println("숫자를 입력해주세요.");
			}
			switch (no) {
			case MANAGE_SUB_MENU_CHOICE.SELECT:
				manageCustomerMenu();
				break;
			case MANAGE_SUB_MENU_CHOICE.INSERT:
				manageHallMenu();
				break;
			case MANAGE_SUB_MENU_CHOICE.UPDATE:
				manageCinemaMenu();
				break;
			case MANAGE_SUB_MENU_CHOICE.DELETE:
				managePlayingMenu();
				break;
			case MANAGE_SUB_MENU_CHOICE.END:
				System.out.println("이전메뉴로 돌아갑니다.");
				exitFlag = true;
			default:
				System.out.println("해당 메뉴 번호만 입력하세요.");

			}
		}
	}
	//회원가입 메뉴
	private static void registerMenu() {
		// TODO Auto-generated method stub

	}
	//현재상영작 출력 매뉴
	private static void printPlayingMenu() {
		// TODO Auto-generated method stub

	}
	//예약확인 메뉴
	private static void checkBookingMenu() {
		// TODO Auto-generated method stub

	}
	//예약 메뉴
	private static void bookingMenu() {

	}

}
