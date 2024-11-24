package com.kh.theaterProject.controller;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import com.kh.theaterProject.model.PlayingVO;
import com.kh.theaterProject.view.CinemaPrint;
import com.kh.theaterProject.view.HallPrint;
import com.kh.theaterProject.view.PlayingPrint;

public class PlayingRegisterManager {
	public static Scanner sc = new Scanner(System.in);

	// 출력
	public void selectManager() throws SQLException {
		PlayingPrint.printAll();
	}

	// 삭제
	public void deleteManager() throws SQLException {
		System.out.println("삭제할 상영정보 번호를 입력해주세요.");
		PlayingDAO cineDAO = new PlayingDAO();
		PlayingVO pvo = returnRightNo();
		System.out.println("============================삭제대상 상영정보============================");
		PlayingPrint.printByCode(pvo);
		System.out.println("=================================================================");
		boolean flag = cineDAO.deleteDB(pvo);
		System.out.println((flag) ? "삭제성공" : "삭제실패");

	}

	// 상영 종료된 영화 삭제해주는

	// 업데이트
	public void updateManager() throws SQLException {
		PlayingDAO cineDAO = new PlayingDAO();
		System.out.print("수정할 상영정보의 번호를 입력하세요 : ");
		PlayingVO pvo = returnRightNo();
		System.out.println("============================수정대상 상영정보===============================");
		PlayingPrint.printByCode(pvo);
		System.out.println("====================================================================");
		System.out.println("새로운 상영관 번호를 입력해주세요.");
		System.out.print(">>");
		HallPrint.printAll();
		String hallNo = HallRegisterManager.returnRightNo().getNo();
		System.out.println("새로운 영화 번호를 입력해주세요.");
		System.out.print(">>");
		CinemaPrint.printAll();
		String cinemaNo = CinemaRegisterManager.returnRightNo().getNo();
		System.out.println("새로운 상영 시작시간을 입력해주세요.(yyyy/MM/dd HH:mi 형식으로 작성,x 입력시 기존값 사용)");
		System.out.print(">>");
		Timestamp tsp = null;
		boolean timeFlag = false;
		while (!timeFlag) {
			String stsp = sc.nextLine();
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
				java.util.Date jdate = sdf.parse(stsp);
				tsp = new Timestamp(jdate.getTime());
				timeFlag = true;
			} catch (Exception e) {
				tsp = pvo.getStarttime();
				timeFlag = true;
			}
		}
		pvo = new PlayingVO(pvo.getNo(), hallNo, cinemaNo, tsp);
		Boolean Flag = cineDAO.updateDB(pvo);
		System.out.println((Flag) ? "수정성공" : "수정실패");
	}

	// 입력
	public void insertManager() throws SQLException {

		PlayingDAO cineDAO = new PlayingDAO();
		System.out.println("새로운 상영관 번호를 입력해주세요.");
		System.out.print(">>");
		HallPrint.printAll();
		String hallNo = HallRegisterManager.returnRightNo().getNo();
		System.out.println("새로운 영화 번호를 입력해주세요.");
		System.out.print(">>");
		CinemaPrint.printAll();
		String cinemaNo = CinemaRegisterManager.returnRightNo().getNo();
		System.out.println("새로운 상영 시작시간을 입력해주세요.(yyyy/MM/dd HH:mi 형식으로 작성)");
		System.out.print(">>");
		boolean timeFlag = false;
		Timestamp tsp = null;
		while (!timeFlag) {
			String stsp = sc.nextLine();
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
				java.util.Date jdate = sdf.parse(stsp);
				tsp = new Timestamp(jdate.getTime());
				timeFlag = true;
			} catch (Exception e) {
				System.out.println("잘못된 입력입니다. 형식에 맞춰서 입력해주세요.");
				System.out.print(">>");
			}
		}
		PlayingVO pvo = new PlayingVO(null, hallNo, cinemaNo, tsp);
		boolean flag = cineDAO.insertDB(pvo);
		System.out.println((flag) ? "입력성공" : "입력실패");
	}

	// 찾기
	public void findManager() throws SQLException {
		PlayingDAO cusDAO = new PlayingDAO();
		PlayingVO pvo = new PlayingVO();
		System.out.println("찾으려는 상영정보의 번호 입력해주세요.");
		System.out.print(">>");
		String no = sc.nextLine();
		pvo.setNo(no);
		pvo = cusDAO.returnpvo(pvo);
		if (pvo.getNo() != null) {
			System.out.println("============================찾으신상영정보===============================");
			PlayingPrint.printByCode(pvo);
			System.out.println("==================================================================");
		} else {
			System.out.println("존재하지 않는 상영정보입니다.");
		}

	}

	// 해당 클래스 내부에서만 사용할 함수들
	// 실행하면 적합한 no가 나올떄까지 반복해서 올바른 PlayingVO를 반환해주는 함수
	public static PlayingVO returnRightNo() throws SQLException {
		boolean exitFlag = false;
		PlayingVO pvo = new PlayingVO();
		PlayingDAO cineDAO = new PlayingDAO();
		while (!exitFlag) {
			System.out.print(">>");
			try {
				int no = Integer.parseInt(sc.nextLine());
				String sNo = String.valueOf(no);
				pvo.setNo(sNo);
				pvo = cineDAO.returnpvo(pvo);
				if (pvo.getHall_no() != null) {
					exitFlag = true;

				} else {
					System.out.println("존재하지 않는 상영정보입니다.");
					System.out.print("재입력 >>");
				}

			} catch (Exception e) {
				System.out.println("숫자를 입력해주세요.");
				System.out.print("재입력 >>");
			}
		}
		return pvo;
	}

}
