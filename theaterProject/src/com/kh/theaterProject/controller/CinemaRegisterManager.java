package com.kh.theaterProject.controller;

import java.sql.SQLException;
import java.util.Scanner;

import com.kh.theaterProject.model.CinemaVO;
import com.kh.theaterProject.view.CinemaPrint;

public class CinemaRegisterManager {
	public static Scanner sc = new Scanner(System.in);

	public void selectManager() throws SQLException {
		CinemaPrint.printAll();
	}

	// 삭제
	public void deleteManager() throws SQLException {
		System.out.println("삭제할 영화 번호를 입력해주세요.");
		CinemaDAO cineDAO = new CinemaDAO();
		CinemaVO cnvo = returnRightNo();
		System.out.println("============================삭제대상 영화============================");
		CinemaPrint.printByCode(cnvo);
		System.out.println("=================================================================");
		boolean flag = cineDAO.deleteDB(cnvo);
		System.out.println((flag) ? "삭제성공" : "삭제실패");

	}

	// 업데이트
	public void updateManager() throws SQLException {
		CinemaDAO cineDAO = new CinemaDAO();
		System.out.print("수정할 영화의 번호를 입력하세요 : ");
		CinemaVO cnvo = returnRightNo();
		System.out.println("============================수정대상 영화===============================");
		CinemaPrint.printByCode(cnvo);
		System.out.println("====================================================================");
		System.out.println("기존값 사용시 x 입력");
		System.out.println("이름을 입력해 주세요.");
		System.out.print(">>");
		String name = sc.nextLine();
		if (name.equals("x")) {
			name = cnvo.getName();
		}
		System.out.println("러닝타임를 입력해주세요.(분으로 입력 2시간 -> 120)");
		System.out.print(">>");
		int runningTime = 0;
		try {
			runningTime = Integer.parseInt(sc.nextLine());
		} catch (Exception e) {
			runningTime = cnvo.getRunningtime();
		}
		cnvo = new CinemaVO(cnvo.getNo(), name, runningTime);
		Boolean Flag = cineDAO.updateDB(cnvo);
		System.out.println((Flag) ? "수정성공" : "수정실패");
	}

	// 입력
	public void insertManager() throws SQLException {

		CinemaDAO cineDAO = new CinemaDAO();
		System.out.println("이름을 입력해 주세요.");
		System.out.print(">>");
		String name = sc.nextLine();
		System.out.println("러닝타임를 입력해주세요.(분으로 입력 2시간 -> 120)");
		System.out.print(">>");
		int runningTime = 0;
		while (runningTime == 0) {
			try {
				runningTime = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.println("숫자를 입력해주세요.");
				System.out.print("재입력 >>");
				runningTime = 0;
			}
		}
		CinemaVO cnvo = new CinemaVO(null, name, runningTime);
		boolean flag = cineDAO.insertDB(cnvo);
		System.out.println((flag) ? "입력성공" : "입력실패");
	}

	// 찾기
	public void findManager() throws SQLException {
		CinemaDAO cusDAO = new CinemaDAO();
		CinemaVO cnvo = new CinemaVO();
		System.out.println("찾으려는 영화의 번호 입력해주세요.");
		System.out.print(">>");
		String no = sc.nextLine();
		cnvo.setNo(no);
		cnvo = cusDAO.returncnvo(cnvo);
		if (cnvo.getNo() != null) {
			System.out.println("============================찾으신영화===============================");
			CinemaPrint.printByCode(cnvo);
			System.out.println("==================================================================");
		} else {
			System.out.println("존재하지 않는 영화입니다.");
		}

	}

	// 해당 클래스 내부에서만 사용할 함수들
	// 실행하면 존재하는 cinemano 가 나올떄까지 반복해서 올바른 CinemaVO를 반환해주는 함수
	public static CinemaVO returnRightNo() throws SQLException {
		boolean exitFlag = false;
		CinemaVO cnvo = new CinemaVO();
		CinemaDAO cineDAO = new CinemaDAO();
		while (!exitFlag) {
			System.out.print(">>");
			try {
				int no = Integer.parseInt(sc.nextLine());
				String sNo = String.valueOf(no);
				cnvo.setNo(sNo);
				cnvo = cineDAO.returncnvo(cnvo);
				if (cnvo.getName()!=null) {
					exitFlag = true;

				} else {
					System.out.println("존재하지 않는 영화입니다.");
					System.out.print("재입력 >>");
				}

			} catch (Exception e) {
				System.out.println("숫자를 입력해주세요.");
				System.out.print("재입력 >>");
			}
		}
		return cnvo;
	}

}
