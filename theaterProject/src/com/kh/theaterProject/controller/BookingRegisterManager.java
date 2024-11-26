package com.kh.theaterProject.controller;

import java.sql.SQLException;
import java.util.Scanner;

import com.kh.theaterProject.model.BookingVO;
import com.kh.theaterProject.model.CustomerVO;
import com.kh.theaterProject.model.PlayingVO;
import com.kh.theaterProject.view.BookingPrint;
import com.kh.theaterProject.view.CustomerPrint;
import com.kh.theaterProject.view.PlayingPrint;

public class BookingRegisterManager {
	public static Scanner sc = new Scanner(System.in);
	

	
	//예매기능-- 이용시 CustomerVO를 넣어서 입력
	public void bookingManager(CustomerVO cvo) throws SQLException {
		SeatsRegisterManager srm = new SeatsRegisterManager();
			System.out.println("환영합니다. "+cvo.getName()+"고객님.");
			BookingDAO bookDAO = new BookingDAO();
			PlayingPrint.printAll();//상영정보 전체 출력
			System.out.println("상영정보를 입력해주세요.");
			PlayingVO pvo = PlayingRegisterManager.returnRightNo();//유효한 상영정보가 나올때까지 반복한뒤 해당 pvo를저장
			System.out.println("합계 인원을 작성해주세요.");
			System.out.print(">>");
			int amount = Integer.parseInt(sc.nextLine());
			BookingVO bvo = new BookingVO(null, pvo.getNo(), cvo.getNo(), amount, null);//bvo에 정보 추가
			srm.updateBookingManager(bvo);//bvo에 추가된 정보로 좌석을 고른다.
			boolean flag = bookDAO.insertDB(bvo);//Bookingtable에 정보 추가
			bvo=bookDAO.returnLastbvo(bvo);//마지막에 추가한 bvo를 반환
			System.out.println((flag) ? "예매가 완료되었습니다. 예매 코드는"+bvo.getCode()+"입니다." : "예매에 실패하였습니다.");
	}
	
	//예매조회
	public void bookingInquiryManager(CustomerVO cvo) throws SQLException {
		BookingVO bvo = new BookingVO();
			System.out.println("고객님의 현재 예매 정보 입니다.");
			System.out.println("=======================================================================================================================================================================");
			bvo.setCustomer_no(cvo.getNo());
			BookingPrint.printByCustomer(bvo);
			System.out.println("=======================================================================================================================================================================");
	}
	
	// 출력
	public void selectManager() throws SQLException {
		BookingPrint.printAll();
	}

	// 삭제
	public void deleteManager() throws SQLException {
		System.out.println("삭제할 예매코드를 입력해주세요.");
		BookingDAO bookDAO = new BookingDAO();
		BookingVO bvo = returnRightCode();
		System.out.println("=======================================================================================================================================================================");
		BookingPrint.printByCode(bvo);
		System.out.println("=======================================================================================================================================================================");
		boolean flag = bookDAO.deleteDB(bvo);
		System.out.println((flag) ? "삭제성공" : "삭제실패");

	}

	// 예매취소
	public void deleteCancleManager() throws SQLException {
		System.out.println("취소할 예매코드를 입력해주세요.");
		CustomerVO cvo = new CustomerVO();
		PlayingVO pvo = new PlayingVO();
		BookingDAO bookDAO = new BookingDAO();
		SeatsRegisterManager srm = new SeatsRegisterManager();
		BookingVO bvo = returnRightCode();
		System.out.println("=======================================================================================================================================================================");
		BookingPrint.printByCode(bvo);
		System.out.println("=======================================================================================================================================================================");
		bvo = new BookingVO(bvo.getNo(), bvo.getPlaying_no(), bvo.getCustomer_no(), bvo.getCode(), 0, bvo.getPrice(), null);
		bookDAO.updateDB(bvo);
		cvo.setNo(bvo.getCustomer_no());
		pvo.setNo(bvo.getPlaying_no());
		srm.updateCancleManager(cvo, pvo);
		boolean flag = bookDAO.deleteDB(bvo);
		System.out.println((flag) ? "취소되었습니다." : "취소실패");

	}
	
	// 업데이트
	public void updateManager() throws SQLException {
		BookingDAO bookDAO = new BookingDAO();
		System.out.print("변경할 예매정보의 코드를 입력하세요 : ");
		BookingVO bvo = returnRightCode();
		System.out.println("=======================================================================================================================================================================");
		BookingPrint.printByCode(bvo);
		System.out.println("=======================================================================================================================================================================");
		
		PlayingPrint.printAll();
		System.out.println("새로운 상영정보를 입력해주세요.");
		System.out.print(">>");
		String playingNo = PlayingRegisterManager.returnRightNo().getNo();
		System.out.println("새로운 인원을 입력해주세요.");
		System.out.print(">>");
		int amount = Integer.parseInt(sc.nextLine());
		bvo = new BookingVO(bvo.getNo(), playingNo, bvo.getCustomer_no(), bvo.getCode(), amount, bvo.getPrice(), null);
		
		Boolean Flag = bookDAO.updateDB(bvo);
		System.out.println((Flag) ? "수정성공" : "수정실패");
	}

	// 입력
	public void insertManager() throws SQLException {
		SeatsRegisterManager srm = new SeatsRegisterManager();
		BookingDAO bookDAO = new BookingDAO();
		System.out.println("새로운 상영정보를 입력해주세요.");
		System.out.print(">>");
		PlayingPrint.printAll();
		String playingNo = PlayingRegisterManager.returnRightNo().getNo();
		System.out.println("새로운 고객 번호를 입력해주세요.");
		System.out.print(">>");
		CustomerPrint.printAll();
		String customerNo = CustomerRegisterManager.returnRightNo().getNo();
		System.out.println("합계 인원을 작성해주세요.");
		System.out.print(">>");
		int amount = Integer.parseInt(sc.nextLine());
		BookingVO bvo = new BookingVO(null, playingNo, customerNo, amount, null);
		srm.updateBookingManager(bvo);
		boolean flag = bookDAO.insertDB(bvo);
		System.out.println((flag) ? "입력성공" : "입력실패");
	}

	// 찾기
	public void findManager() throws SQLException {
		BookingDAO cusDAO = new BookingDAO();
		BookingVO bvo = new BookingVO();
		System.out.println("찾으려는 예매정보 코드를 입력해주세요.");
		System.out.print(">>");
		String code = sc.nextLine();
		bvo.setCode(code);
		bvo = cusDAO.returnCodebvo(bvo);
		if (bvo.getNo() != null) {
			System.out.println("=======================================================================================================================================================================");
			BookingPrint.printByCode(bvo);
			System.out.println("=======================================================================================================================================================================");
		} else {
			System.out.println("존재하지 않는 상영정보입니다.");
		}

	}

	// 해당 클래스 내부에서만 사용할 함수들
	// 실행하면 적합한 no가 나올떄까지 반복해서 올바른 BookingVO를 반환해주는 함수
	private BookingVO returnRightCode() throws SQLException {
		boolean exitFlag = false;
		BookingVO bvo = new BookingVO();
		BookingDAO bookDAO = new BookingDAO();
		while (!exitFlag) {
			System.out.print(">>");

			String code = sc.nextLine();
			bvo.setCode(code);
			bvo = bookDAO.returnCodebvo(bvo);
			if (bvo.getPlaying_no() != null) {
				exitFlag = true;

			} else {
				System.out.println("존재하지 않는 예매정보입니다.");
				System.out.print("재입력 >>");
			}

		}
		return bvo;
	}

}
