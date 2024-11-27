package com.kh.MVCProject.view;

import java.sql.SQLException;
import java.util.ArrayList;

import com.kh.MVCProject.controller.StudentDAO;
import com.kh.MVCProject.model.StudentVO;

public class StudentPrint {
	// 0. 메뉴 프린트 함수
	public static void printMenu() {
		System.out.println("1.출력 2.추가 3.수정 4.삭제 5.항목별 정렬 6.자동생성 7.찾기 8.나가기 9.휴지통");
		System.out.print(">>");

	}

	// ArrayList<StudentVO> 를 받으면 출력해주는 출력함수
	public static void printStudent(ArrayList<StudentVO> sList) throws SQLException {
		System.out.println(StudentVO.getHeader());
		for (StudentVO data : sList) {
			System.out.println(data);
		}
	}


	// code 를 받으면 해당 코드에 대한 학생정보를 출력해주는 함수
	public static void printStudentByCode(StudentVO svo) throws SQLException {
		svo = StudentDAO.returnStudentVOByCode(svo);
		System.out.println(StudentVO.getHeader());
		System.out.println(svo.toString());
	}
}