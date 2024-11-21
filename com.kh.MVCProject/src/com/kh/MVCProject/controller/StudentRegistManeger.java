package com.kh.MVCProject.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.kh.MVCProject.model.StudentVO;
import com.kh.MVCProject.view.StudentPrint;

public class StudentRegistManeger {
	public static Scanner sc = new Scanner(System.in);

	// 코드순으로 정렬된 전체를 프린트
	public static void printOdByCode() throws SQLException {
		ArrayList<StudentVO> svo = StudentDAO.returnListOrderNum(1);
		StudentPrint.printStudent(svo);
	}
	// 학생을 insert하는 함수
	public static void studentInsert2() throws SQLException {
		StudentVO svo = writeStudentVO();
		boolean Flag = StudentDAO.insertStudentDB(svo);
		System.out.println((Flag == true) ? "입력 성공" : "입력 실패");
	}
	// 학생을 update하는 함수
	public static void studentUpdate2() throws SQLException {
		System.out.println("학생정보를 수정합니다. 수정할 학생의 코드를 입력해주세요.");
		System.out.print(">>");
		// 코드 번호를 넣으면 올바른 코드가 나올때까지 반복해서 반환해주는함수
		int code = returnCode();
		System.out.println("====================================수정할 학생정보==================================");
		StudentPrint.printStudentByCode(code);
		System.out.println("=================================================================================");
		StudentVO svo = writeStudentVO();
		// 수정전의 학생정보를 코드를 받아서 출력
		StudentPrint.printStudentByCode(code);
		System.out.println("▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲수정전▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲");
		System.out.println("▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼수정후▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼");
		// 수정할 학생의 점보를 svo에 담아서 업데이트
		StudentDAO.updateStudentDB(svo);
		// 바뀐 정보를 출력
		StudentPrint.printStudentByCode(code);
	}
	// 학생을 delete 하는 함수

	// 학생을 delete하는 함수
	public static void studentDelete() throws SQLException {
		System.out.println("학생정보를 삭제합니다. 삭제할 학생의 코드를 입력해주세요.");
		System.out.print(">>");
		int code = returnCode();
		StudentVO svo = new StudentVO();
		svo.setCode(code);
		System.out.println("====================================삭제할 학생정보==================================");
		StudentPrint.printStudentByCode(code);
		System.out.println("=================================================================================");
		boolean flag = StudentDAO.deleteStudentDB(svo);
		System.out.println((flag == true) ? "삭제 완료" : "삭제 실패");
	}
	// 입력한 순서로 정렬해주는 함수(1.code,2.name,3.birth,4.grade)

	// 받은 조건대로 정렬해서 출력해주는 함수
	public static void studentRankUp() throws SQLException {
		System.out.println("정렬 기준을 선택해주세요.");
		int num = 0;
		while (num == 0) {
			System.out.println("1.code 2.name 3.birth 4.grade(순위표시)");
			System.out.print(">>");
			try {
				num = Integer.parseInt(sc.nextLine());
				if (num == 1 || num == 2 || num == 3 || num == 4) {
				} else {
					num = 0;
					System.out.println("잘못된 입력입니다. 1~4 사이의 수를 입력해주세요.");
				}
			} catch (Exception e) {
				System.out.println("잘못된 입력입니다. 1~4 사이의 수를 입력해주세요.");
			}
		}
		System.out.println("출력중입니다...");
		StudentPrint.printStudent(StudentDAO.returnListOrderNum(num));
	}
	// 랜덤으로 insert 해주는 함수

	// 학생을 랜덤 조건으로 insert해주는 함수
	public static void studentInsertRandom() throws SQLException {
		boolean flag = StudentDAO.insertStudentDBRandom();
		System.out.println((flag == true) ? "추가 성공" : "추가 실패");
	}
	// 학생 찾아서 출력해주는 함수

	// 학생을 찾아서 출력해주는 함수
	public static void studentFind() throws SQLException {
		System.out.println("학생정보를 조회합니다. 조회할 학생의 코드를 입력해주세요.");
		System.out.print(">>");
		int code = returnCode();
		System.out.println("====================================조회한 학생정보==================================");
		StudentPrint.printStudentByCode(code);
		System.out.println("=================================================================================");
	}
	// 휴지통 관리 함수

	// 휴지통 기능 함수
	public static void studentGarbage() throws SQLException {
		ArrayList<StudentVO> svo = StudentDAO.returnListGarbage();
		StudentPrint.printStudent(svo);
		System.out.println("휴지통은 최근삭제한 순서대로 표시됩니다. del 입력시 휴지통 비우기");
		
		String num = sc.nextLine();
		if (num.equals("del")) {
			boolean flag = StudentDAO.deleteStudentGarbageDB();
			System.out.println((flag == true) ? ("휴지통 비우기 완료") : ("휴지통 비우기 실패"));
		}
	}
	// 코드 알맞게 반환해주는 함수

	// 추가기능 함수들

	// db에 맞는코드를 반환할때까지 반복해서 code값을 반환해주는함수
	public static int returnCode() throws SQLException {
		int findCode = 0;
		while (findCode == 0) {
			try {
				findCode = Integer.parseInt(sc.nextLine());
				boolean checkFlag = false;
				ArrayList<StudentVO> sList = StudentDAO.returnListOrderNum(1);
				for (StudentVO data : sList) {
					if (data.getCode() == findCode) {
						checkFlag = true;
					}
				}
				if (checkFlag == false) {
					System.out.println("존재하지 않는 학생입니다. 확인하고 다시 입력해주세요.");
					System.out.print(">>");
					findCode = 0;
				}

			} catch (Exception e) {
				System.out.println("숫자를 입력해주세요.");
				System.out.print(">>");
			}
		}
		return findCode;
	}

	// studentVO를 작성해주는함수
	private static StudentVO writeStudentVO() {
		System.out.print("학생 이름 : ");
		String name = sc.nextLine();
		System.out.print("학생 생일(yyyy/mm/dd 형태로 입력해주세요) : ");
		String birth = getDateInput();
		System.out.print("학생 국어 : ");
		int KOR = gradePattern();
		System.out.print("학생 수학 : ");
		int MATH = gradePattern();
		System.out.print("학생 영어 : ");
		int ENG = gradePattern();
		StudentVO svo = new StudentVO(0, name, birth, KOR, MATH, ENG);
		return svo;
	}
	// 날짜 패턴 매치 함수
	public static String getDateInput() {
	    String datePattern = "^\\d{4}/(0?[1-9]|1[0-2])/\\d{2}$"; // MM은 1~12, 앞에 0은 선택사항
	    String inputDate = null;

	    while (true) {
	        inputDate = sc.nextLine().trim();
	        // 패턴 매칭
	        if (Pattern.matches(datePattern, inputDate)) {
	            // 입력이 패턴에 맞는 경우 리턴
	            return inputDate;
	        } else {
	            System.out.println("잘못된 형식입니다. 다시 입력하세요.");
	            System.out.print(">>");
	        }
	    }
	}
	// 점수 패턴 맞춰주는 함수
	private static int gradePattern() {
		int grade = 0;
		while (grade == 0) {
			try {
				grade = Integer.parseInt(sc.nextLine());
				if (grade < 0 || grade > 100) {
					grade = 0;
					System.out.println("1~100 사이의 정수를 입력해주세요.");
					System.out.print(">>");
				}

			} catch (Exception e) {
				System.out.println("1~100 사이의 정수를 입력해주세요.");
				System.out.print(">>");
			}
		}
		return grade;
	}


}
