package publicDataTest.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import publicDataTest.PublicDataApiTest;
import publicDataTest.model.LandPriceVO;

public class LandPriceRegisterManager {
	public static Scanner sc = new Scanner(System.in);

	// 삭제
	public static void LandPriceDeleteManager() throws SQLException {
		System.out.print("삭제할 학생 번호를 입력하세요: ");
		int no = Integer.parseInt(sc.nextLine());
		LandPriceVO svo = new LandPriceVO();
		svo.setNodeno(no);
		boolean flag = LandPriceDAO.DBDelete(svo);
		System.out.println((flag == true) ? "삭제성공" : "삭제실패");

	}
//
//	// 업데이트
//	public static void LandPriceUpdateManager() throws SQLException {
//
//		System.out.print("수정할 학생의 번호를 입력하세요: ");
//		int no = Integer.parseInt(sc.nextLine());
//		System.out.print("새로운 이름을 입력하세요: ");
//		String name = sc.nextLine();
//		System.out.print("새로운 국어 점수를 입력하세요: ");
//		int kor = Integer.parseInt(sc.nextLine());
//		System.out.print("새로운 영어 점수를 입력하세요: ");
//		int eng = Integer.parseInt(sc.nextLine());
//		System.out.print("새로운 수학 점수를 입력하세요: ");
//		int mat = Integer.parseInt(sc.nextLine());
//		LandPriceVO st = new LandPriceVO();
//		Boolean Flag = LandPriceDAO.DBUpdate(st);
//		System.out.println((Flag != true) ? "수정성공" : "수정실패");
//	}
//
	// 전체 학생 리스트를 출력요청
	public static void totalSelectManager() throws SQLException {
		ArrayList<LandPriceVO> LandPriceList = new ArrayList<LandPriceVO>();
		LandPriceList = LandPriceDAO.LandPriceSelect();
		printLandPriceList(LandPriceList);
	}
//
//	// 정렬된 학생 리스트를 출력요청
//	public static void sortSelectManager() throws SQLException {
//		ArrayList<LandPriceVO> LandPriceList = new ArrayList<LandPriceVO>();
//		LandPriceList = LandPriceDAO.LandPriceSortSVO();
//		printLandPriceList(LandPriceList);
//	}

	// 전체 학생 리스트를 출력
	public static void printLandPriceList(ArrayList<LandPriceVO> LandPriceList) {
		System.out.println("============================================");
		for (LandPriceVO data : LandPriceList) {
			System.out.println(data);
		}
		System.out.println("============================================");

	}

	// 입력
	public static void LandPriceInsertManager() throws SQLException {
		boolean sucFlag = false;
		for(LandPriceVO data: PublicDataApiTest.apiDataLoad()) {
			if(LandPriceDAO.LandPriceCheckNodeNo(data)==0) {
			sucFlag = LandPriceDAO.DBInsert(data);}
		}
		System.out.println((sucFlag != true) ? "입력 성공" : "입력 실패");
	}
}
