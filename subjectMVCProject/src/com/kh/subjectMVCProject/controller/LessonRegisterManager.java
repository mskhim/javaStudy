package com.kh.subjectMVCProject.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.kh.subjectMVCProject.model.StudentVO;


public class LessonRegisterManager {
	public static Scanner sc = new Scanner(System.in); 
	
	// 삭제
	public static void studentDeleteManager() throws SQLException {
		System.out.print("삭제할 학생 번호를 입력하세요: ");
		int no = Integer.parseInt(sc.nextLine());
		StudentVO svo = new StudentVO();
		svo.setNo(no);
		boolean flag = StudentDAO.sDBDelete(svo);
		System.out.println((flag == true) ? "삭제성공" : "삭제실패");

	}
	
	// 업데이트
	public static void studentUpdateManager() throws SQLException {

		System.out.print("수정할 학생의 번호를 입력하세요: ");
		int no = Integer.parseInt(sc.nextLine());
		System.out.print("새로운 이름을 입력하세요: ");
		String name = sc.nextLine();
		System.out.print("새로운 국어 점수를 입력하세요: ");
		int kor = Integer.parseInt(sc.nextLine());
		System.out.print("새로운 영어 점수를 입력하세요: ");
		int eng = Integer.parseInt(sc.nextLine());
		System.out.print("새로운 수학 점수를 입력하세요: ");
		int mat = Integer.parseInt(sc.nextLine());
		StudentVO st = new StudentVO();
		Boolean Flag = StudentDAO.sDBUpdate(st);
		System.out.println((Flag != true) ? "수정성공" : "수정실패");
	}

	// 전체 학생 리스트를 출력요청
	public static void totalSelectManager() throws SQLException {
		ArrayList<StudentVO> studentList = new ArrayList<StudentVO>();
		studentList = StudentDAO.StudentSelectSVO();
		printStudentList(studentList);
	}
	
	// 정렬된 학생 리스트를 출력요청
	public static void sortSelectManager() throws SQLException {
		ArrayList<StudentVO> studentList = new ArrayList<StudentVO>();
		studentList = StudentDAO.studentSortSVO();
		printStudentList(studentList);
	}
	
	// 전체 학생 리스트를 출력
	public static void printStudentList(ArrayList<StudentVO> studentList) {
		System.out.println("============================================");
		for (StudentVO data : studentList) {
			System.out.println(data);
		}
		System.out.println("============================================");

	}
	
	// 입력
	public static void studentInsertManager() throws SQLException {
		// 3.statement
		System.out.print("학생 이름을 입력하세요: ");
		String name = sc.nextLine();
		System.out.print("국어 점수를 입력하세요: ");
		int kor = Integer.parseInt(sc.nextLine());
		System.out.print("영어 점수를 입력하세요: ");
		int eng = Integer.parseInt(sc.nextLine());
		System.out.print("수학 점수를 입력하세요: ");
		int mat = Integer.parseInt(sc.nextLine());
		StudentVO studentVO = new StudentVO();
		boolean sucFlag=StudentDAO.sDBInsert(studentVO);
		System.out.println((sucFlag!=true)?"입력 성공":"입력 실패");
	}
 	}
