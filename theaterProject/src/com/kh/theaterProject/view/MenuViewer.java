package com.kh.theaterProject.view;

import com.kh.theaterProject.model.CustomerVO;

public class MenuViewer {

	    // 메인 메뉴
	    public static void mainMenuView() {
	        System.out.println("\n환영합니다.");
	        System.out.println("---------------------------------------------------------");
	        System.out.println("| 1. 로그인 | 2. 현재 상영작 확인하기 | 3. 회원가입 | 4. 프로그램 종료 |");
	        System.out.println("---------------------------------------------------------");
	        System.out.println("메뉴를 선택해주세요");
	        System.out.print(">> ");
	    }

	    // 관리자 모드 메뉴
	    public static void manageMenuView() {
	        System.out.println("\n관리자 모드입니다.");
	        System.out.println("-----------------------------------------------------------------------------------------------");
	        System.out.println("| 1. 고객정보 관리 | 2. 상영관 관리 | 3. 영화 목록 관리 | 4. 상영정보 관리 | 5. 예매현황 관리 | 6. 로그아웃 | 7. 종료 |");
	        System.out.println("-----------------------------------------------------------------------------------------------");
	        System.out.println("메뉴를 선택해주세요");
	        System.out.print(">> ");
	    }

	    // 관리자모드의 고객 데이터 확인 메뉴
	    public static void manageBookingMenuView() {
	    	System.out.println("\n예매 데이터 관리화면입니다.");
	    	System.out.println("----------------------------------------------------------------------------------------");
	    	System.out.println("| 1. 예매 데이터 출력 | 2. 예매 데이터 생성 | 3. 데이터 수정 | 4. 데이터 삭제 | 5. 데이터 찾기 | 6. 뒤로가기 |");
	    	System.out.println("----------------------------------------------------------------------------------------");
	    	System.out.println("메뉴를 선택해주세요");
	    	System.out.print(">> ");
	    }
	    
	    // 관리자모드의 고객 데이터 확인 메뉴
	    public static void manageCustomerMenuView() {
	        System.out.println("\n고객 데이터 관리화면입니다.");
	        System.out.println("---------------------------------------------------------------------------------------------------");
	        System.out.println("| 1. 고객 데이터 출력 | 2. 더미 데이터 생성 | 3. 데이터 수정 | 4. 데이터 삭제 | 5. 데이터 찾기 | 6. 권한설정 | 7. 뒤로가기 |");
	        System.out.println("---------------------------------------------------------------------------------------------------");
	        System.out.println("메뉴를 선택해주세요");
	        System.out.print(">> ");
	    }

	    // 관리자모드의 상영관 데이터 확인 메뉴
	    public static void manageHallMenuView() {
	        System.out.println("\n상영관 데이터 관리화면입니다.");
	        System.out.println("-------------------------------------------------------------------------------------");
	        System.out.println("| 1. 상영관 데이터 출력 | 2. 데이터 생성 | 3. 데이터 수정 | 4. 데이터 삭제 | 5. 데이터 찾기 | 6. 뒤로가기 |");
	        System.out.println("-------------------------------------------------------------------------------------");
	        System.out.println("메뉴를 선택해주세요");
	        System.out.print(">> ");
	    }

	    // 관리자모드의 영화 데이터 확인 메뉴
	    public static void manageCinemaMenuView() {
	        System.out.println("\n영화 데이터 관리화면입니다.");
	        System.out.println("------------------------------------------------------------------------------------");
	        System.out.println("| 1. 영화 데이터 출력 | 2. 데이터 생성 | 3. 데이터 수정 | 4. 데이터 삭제 | 5. 데이터 찾기 | 6. 뒤로가기 |");
	        System.out.println("------------------------------------------------------------------------------------");
	        System.out.println("메뉴를 선택해주세요");
	        System.out.print(">> ");
	    }

	    // 관리자모드의 상영작 데이터 확인 메뉴
	    public static void managePlayingMenuView() {
	        System.out.println("\n상영작 데이터 관리화면입니다.");
	        System.out.println("-------------------------------------------------------------------------------------------");
	        System.out.println("| 1. 상영작 데이터 출력 | 2. 데이터 생성 | 3. 데이터 수정 | 4. 데이터 삭제 | 5. 데이터 찾기 | 6. 뒤로가기 |");
	        System.out.println("-------------------------------------------------------------------------------------------");
	        System.out.println("메뉴를 선택해주세요");
	        System.out.print(">> ");
	    }
	    
	    // 로그인 이후 메뉴
	    public static void afterLoginMenuView(CustomerVO cvo) {
	    	System.out.println("\n"+cvo.getName()+"님 환영합니다. 메뉴를 선택해주세요.");
	    	System.out.println("-----------------------------------------------------------------------------");
	    	System.out.println("| 1. 예매하기 | 2. 나의 예매내역 | 3. 마이페이지 | 4. 현재 상영작 확인 | 5. 로그아웃 | 6. 종료 |");
	    	System.out.println("-----------------------------------------------------------------------------");
	    	System.out.println("메뉴를 선택해주세요");
	    	System.out.print(">> ");
	    }

		public static void myPageMenuView() {
			System.out.println("---------------------------");
			System.out.println("| 1. 내정보 수정하기 | 2. 나가기 |");
			System.out.println("---------------------------");
			System.out.print(">>");
		}

		public static void myPageBookingMenuView() {
			System.out.println("-----------------------------------------");
			System.out.println("| 1. 예매 취소하기 | 2. 예매 변경하기 | 3. 나가기 |");
			System.out.println("-----------------------------------------");
			System.out.print(">>");
			
		}
	
	    //전체 출력
	    
}

