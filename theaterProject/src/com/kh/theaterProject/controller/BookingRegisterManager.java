package com.kh.theaterProject.controller;

import java.sql.SQLException;
import java.util.Scanner;

import com.kh.theaterProject.model.BookingVO;
import com.kh.theaterProject.model.CustomerVO;
import com.kh.theaterProject.view.BookingPrint;
import com.kh.theaterProject.view.CustomerPrint;
import com.kh.theaterProject.view.PlayingPrint;

public class BookingRegisterManager {
	public static Scanner sc = new Scanner(System.in);
	
	//로그인기능
	public CustomerVO loginManager() throws SQLException {
		CustomerVO cvo = new CustomerVO();
		CustomerRegisterManager crm = new CustomerRegisterManager();
		System.out.print("아이디를 입력해주세요 : ");
		String id = sc.nextLine();
		System.out.println();
		System.out.print("비밀번호를 입력해주세요 : ");
		String pwd = sc.nextLine();
		cvo.setId(id);
		cvo.setPwd(pwd);
		cvo = crm.returnLogin(cvo);
		if(cvo.getName()==null) {
			System.out.println("로그인 실패");
			return cvo;
		}else {
			System.out.println("환영합니다."+cvo.getName()+"고객님.");
			return cvo;
		}
	}
	
	//예매기능
	public void bookingManager(CustomerVO cvo) throws SQLException {
			System.out.println("환영합니다."+cvo.getName()+"고객님.");
			BookingDAO bookDAO = new BookingDAO();
			PlayingPrint.printAll();
			System.out.println("상영정보를 입력해주세요.");
			System.out.print(">>");
			String playingNo = PlayingRegisterManager.returnRightNo().getNo();
			String customerNo = cvo.getNo();
			System.out.println("합계 인원을 작성해주세요.");
			System.out.print(">>");
			int amount = Integer.parseInt(sc.nextLine());
			BookingVO bvo = new BookingVO(null, playingNo, customerNo, amount, null);
			boolean flag = bookDAO.insertDB(bvo);
			bvo=bookDAO.returnLastbvo(bvo);
			System.out.println((flag) ? "예매가 완료되었습니다. 예매 코드는"+bvo.getCode()+"입니다." : "예매에 실패하였습니다.");
	}
	
	//예매조회
	public void bookingInquiryManager(CustomerVO cvo) throws SQLException {
		BookingVO bvo = new BookingVO();
			System.out.println("고객님의 현재 예매 정보 입니다.");
			bvo.setCustomer_no(cvo.getNo());
			BookingPrint.printByCustomer(bvo);
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
		System.out.println("============================삭제대상 예매정보============================");
		BookingPrint.printByCode(bvo);
		System.out.println("====================================================================");
		boolean flag = bookDAO.deleteDB(bvo);
		System.out.println((flag) ? "삭제성공" : "삭제실패");

	}

	// 예매취소
	public void deleteCancleManager() throws SQLException {
		System.out.println("취소할 예매코드를 입력해주세요.");
		BookingDAO bookDAO = new BookingDAO();
		BookingVO bvo = returnRightCode();
		System.out.println("============================취소할 예매정보=============================");
		BookingPrint.printByCode(bvo);
		System.out.println("====================================================================");
		bvo = new BookingVO(bvo.getNo(), bvo.getPlaying_no(), bvo.getCustomer_no(), bvo.getCode(), 0, bvo.getPrice(), null);
		bookDAO.updateDB(bvo);
		boolean flag = bookDAO.deleteDB(bvo);
		System.out.println((flag) ? "삭제성공" : "삭제실패");

	}
	// 상영 종료된 영화 삭제해주는

	// 업데이트
	public void updateManager() throws SQLException {
		BookingDAO bookDAO = new BookingDAO();
		System.out.print("변경할 예매정보의 코드를 입력하세요 : ");
		BookingVO bvo = returnRightCode();
		System.out.println("============================변경대상 예매정보===============================");
		BookingPrint.printByCode(bvo);
		System.out.println("====================================================================");
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
			System.out.println("============================찾으신상영정보===============================");
			BookingPrint.printByCode(bvo);
			System.out.println("==================================================================");
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
