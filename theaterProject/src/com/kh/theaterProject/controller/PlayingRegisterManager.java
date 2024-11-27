package com.kh.theaterProject.controller;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import com.kh.theaterProject.model.BookingVO;
import com.kh.theaterProject.model.PlayingVO;
import com.kh.theaterProject.view.CinemaPrint;
import com.kh.theaterProject.view.HallPrint;
import com.kh.theaterProject.view.PlayingPrint;
import com.kh.theaterProject.view.SeatsPrint;

public class PlayingRegisterManager {
	public static Scanner sc = new Scanner(System.in);

	// 출력
	public void selectManager() throws SQLException {
		PlayingPrint.printAll();
	}
	//satus가 null인 영화들 삭제
	public void deleteNullManager() throws SQLException {
		PlayingDAO bDAO = new PlayingDAO();
		PlayingPrint.printAllNull();
	System.out.println("해당 정보들을 삭제합니다.(y/n)");
	String text= sc.nextLine();
	if(text.equals("y")) {
		bDAO.deleteDBnull();
		System.out.println("삭제가 완료되었습니다.");
	}
	}
	
	// 삭제
	public void deleteManager() throws SQLException {
		System.out.println("삭제할 상영정보 번호를 입력해주세요.");
		PlayingDAO palyDAO = new PlayingDAO();
		PlayingVO pvo = returnRightNo();
		System.out.println("============================삭제대상 상영정보============================");
		PlayingPrint.printByCode(pvo);
		System.out.println("=================================================================");
		boolean flag = palyDAO.deleteDB(pvo);
		System.out.println((flag) ? "삭제성공" : "삭제실패");

	}

	// 업데이트
	public void updateManager() throws SQLException {
		PlayingDAO palyDAO = new PlayingDAO();
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
		String palymaNo = CinemaRegisterManager.returnRightNo().getNo();
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
		pvo = new PlayingVO(pvo.getNo(), hallNo, palymaNo, tsp);
		Boolean Flag = palyDAO.updateDB(pvo);
		System.out.println((Flag) ? "수정성공" : "수정실패");
	}

	// 입력
	public void insertManager() throws SQLException {

		PlayingDAO palyDAO = new PlayingDAO();
		System.out.println("새로운 상영관 번호를 입력해주세요.");
		System.out.print(">>");
		HallPrint.printAll();
		String hallNo = HallRegisterManager.returnRightNo().getNo();
		System.out.println("새로운 영화 번호를 입력해주세요.");
		System.out.print(">>");
		CinemaPrint.printAll();
		String palymaNo = CinemaRegisterManager.returnRightNo().getNo();
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
		PlayingVO pvo = new PlayingVO(null, hallNo, palymaNo, tsp);
		boolean flag = palyDAO.insertDB(pvo);
		System.out.println((flag) ? "입력성공" : "입력실패");
	}

	// 찾기
	public void findManager() throws SQLException {
		SeatsRegisterManager srm = new SeatsRegisterManager();
		PlayingDAO cusDAO = new PlayingDAO();
		PlayingVO pvo = new PlayingVO();
		BookingVO bvo = new BookingVO();//seats출력을 위해서 필요.  
		System.out.println("찾으려는 상영정보의 번호 입력해주세요.");
		System.out.print(">>");
		String no = sc.nextLine();
		pvo.setNo(no);
		pvo = cusDAO.returnpvo(pvo);
		if (pvo.getNo() != null) {
			System.out.println("============================찾으신상영정보===============================");
			PlayingPrint.printByCode(pvo);
			System.out.println("====================================================================");
			bvo.setPlaying_no(pvo.getNo());
			SeatsPrint.printAllByList(srm.returnListByBVO(bvo));
		} else {
			System.out.println("존재하지 않는 상영정보입니다.");
		}

	}

	// 해당 클래스 내부에서만 사용할 함수들
	// 실행하면 적합한 no가 나올떄까지 반복해서 올바른 PlayingVO를 반환해주는 함수
	public static PlayingVO returnRightNo() throws SQLException {
		boolean exitFlag = false;
		PlayingVO pvo = new PlayingVO();
		PlayingDAO palyDAO = new PlayingDAO();
		while (!exitFlag) {
			System.out.print(">>");
			try {
				int no = Integer.parseInt(sc.nextLine());
				String sNo = String.valueOf(no);
				pvo.setNo(sNo);
				pvo = palyDAO.returnpvo(pvo);
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
