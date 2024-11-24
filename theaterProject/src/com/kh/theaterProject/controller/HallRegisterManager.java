package com.kh.theaterProject.controller;

import java.sql.SQLException;
import java.util.Scanner;

import com.kh.theaterProject.model.HallVO;
import com.kh.theaterProject.view.HallPrint;

public class HallRegisterManager {
	public static Scanner sc = new Scanner(System.in);

	public void selectManager() throws SQLException {
		HallPrint.printAll();
	}

	// 삭제
	public void deleteManager() throws SQLException {
		System.out.println("삭제할 상영관 번호를 입력해주세요.");
		HallDAO hallDAO = new HallDAO();
		HallVO hvo = returnRightNo();
		System.out.println("============================삭제대상 상영관============================");
		HallPrint.printByCode(hvo);
		System.out.println("=================================================================");
		boolean flag = hallDAO.deleteDB(hvo);
		System.out.println((flag) ? "삭제성공" : "삭제실패");

	}

	// 업데이트
	public void updateManager() throws SQLException {
		HallDAO hallDAO = new HallDAO();
		System.out.print("수정할 상영관의 번호를 입력하세요 : ");
		HallVO hvo = returnRightNo();
		System.out.println("============================수정대상 상영관===============================");
		HallPrint.printByCode(hvo);
		System.out.println("====================================================================");
		System.out.println("기존값 사용시 x 입력");
		System.out.println("좌석수를 입력해주세요.");
		System.out.print(">>");
		int seats = 0;
		try {
			seats = Integer.parseInt(sc.nextLine());
		} catch (Exception e) {
			seats = hvo.getPrice();
		}
		System.out.println("상영요금을 입력해주세요.");
		System.out.print(">>");
		int price = 0;
		try {
			price = Integer.parseInt(sc.nextLine());
		} catch (Exception e) {
			price = hvo.getPrice();
		}
		hvo = new HallVO(hvo.getNo(), seats, price);
		Boolean Flag = hallDAO.updateDB(hvo);
		System.out.println((Flag) ? "수정성공" : "수정실패");
	}

	// 입력
	public void insertManager() throws SQLException {

		HallDAO hallDAO = new HallDAO();
		int seats = 0;
		int price = 0;
		System.out.println("좌석수를 입력해주세요.");
		while(seats==0) {
		System.out.print(">>");
		try {
			seats = Integer.parseInt(sc.nextLine());
		} catch (Exception e) {
			System.out.println("숫자를 입력해주세요");
			System.out.print("재입력 >>");
			seats=0;
		}
		System.out.println("상영요금을 입력해주세요.");
		System.out.print(">>");
		}
		while(price==0) {
		try {
			price = Integer.parseInt(sc.nextLine());
		} catch (Exception e) {
			System.out.println("숫자를 입력해주세요");
			System.out.print("재입력 >>");
			price=0;
		}
		}
		HallVO hvo = new HallVO(null, seats, price);
		boolean flag = hallDAO.insertDB(hvo);
		System.out.println((flag) ? "입력성공" : "입력실패");
	}

	// 찾기
	public void findManager() throws SQLException {
		HallDAO cusDAO = new HallDAO();
		HallVO hvo = new HallVO();
		System.out.println("찾으려는 상영관의 번호 입력해주세요.");
		System.out.print(">>");
		String no = sc.nextLine();
		hvo.setNo(no);
		hvo = cusDAO.returnhvo(hvo);
		if (hvo.getNo() != null) {
			System.out.println("============================찾으신상영관===============================");
			HallPrint.printByCode(hvo);
			System.out.println("==================================================================");
		} else {
			System.out.println("존재하지 않는 상영관입니다.");
		}

	}

	// 해당 클래스 내부에서만 사용할 함수들
	// 실행하면 존재하는 상영관 no가 나올떄까지 반복해서 올바른 HallVO를 반환해주는 함수
	public static HallVO returnRightNo() throws SQLException {
		boolean exitFlag = false;
		HallVO hvo = new HallVO();
		HallDAO hallDAO = new HallDAO();
		while (!exitFlag) {
			System.out.print(">>");
			try {
				int no = Integer.parseInt(sc.nextLine());
				String sNo = String.valueOf(no);
				hvo.setNo(sNo);
				hvo = hallDAO.returnhvo(hvo);
				if (hvo.getSeats()!=0) {
					exitFlag = true;

				} else {
					System.out.println("존재하지 않는 상영관입니다.");
					System.out.print("재입력 >>");
				}

			} catch (Exception e) {
				System.out.println("숫자를 입력해주세요.");
				System.out.print("재입력 >>");
			}
		}
		return hvo;
	}

}
