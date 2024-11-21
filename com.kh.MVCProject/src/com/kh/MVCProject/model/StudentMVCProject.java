package com.kh.MVCProject.model;

import java.sql.SQLException;
import java.util.Scanner;

import com.kh.MVCProject.controller.StudentRegistManeger;
import com.kh.MVCProject.view.StudentInterface;
import com.kh.MVCProject.view.StudentPrint;

public class StudentMVCProject implements StudentInterface {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws SQLException {
		int mNum = 0;
		boolean exitFlag = false;
		System.out.println("매뉴 번호를 입력해주세요.");
		while (!exitFlag) {
			StudentPrint.printMenu();
			try {
				 mNum=Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.println("숫자를 입력해주세요.");
			}
			switch (mNum) {
			case PRINT:
			StudentRegistManeger.printOdByCode();
				break;
			case INSERT:
				StudentRegistManeger.studentInsert2();
				break;
			case UPDATE:
				StudentRegistManeger.studentUpdate2();
				break;
			case DELETE:
				StudentRegistManeger.studentDelete();
				break;
			case RANK:
				StudentRegistManeger.studentRankUp();
				break;
			case RAND:
			StudentRegistManeger.studentInsertRandom();
				break;
			case FIND:
				StudentRegistManeger.studentFind(); 
				break;
			case FIN:
				exitFlag = true;
				System.out.println("-종료-");
				break;
			case GARBAGE:
				StudentRegistManeger.studentGarbage();
				break;
			default:
				break;
			}

		}

	}
	
}
