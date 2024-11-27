package com.kh.subjectMVCProject;

import java.sql.SQLException;
import java.util.Scanner;

import com.kh.subjectMVCProject.controller.LessonRegisterManager;
import com.kh.subjectMVCProject.controller.StudentRegisterManager;
import com.kh.subjectMVCProject.controller.SubjectRegisterManager;
import com.kh.subjectMVCProject.controller.TraineeRegisterManager;
import com.kh.subjectMVCProject.view.MENU_CHOICE;
import com.kh.subjectMVCProject.view.MenuViewer;
import com.kh.subjectMVCProject.view.SUBMENU_CHOICE;
import com.kh.subjectMVCProject.view.SUBJECT_CHOICE;

public class SubjectMain {
	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int no=0;
		while (true) {
			try {
				MenuViewer.mainMenuView();
				no = Integer.parseInt(sc.nextLine());

				switch (no) {
				case MENU_CHOICE.SUBJECT:
                	subjectMenu();
					break;
				case MENU_CHOICE.STUDENT:
                	studentMenu();
					break;
				case MENU_CHOICE.LESSON:
                	lessonMenu();
					break;
				case MENU_CHOICE.TRAINEE:
                	traineeMenu();
					break;
				case MENU_CHOICE.EXIT:
					System.out.println("프로그램을 종료합니다.");
					return;
				default:
					System.out.println("해당 메뉴 번호만 입력하세요.");
				}
			} catch (Exception e) {
				System.out.println("\n입력에 오류가 있습니다.\n프로그램을 다시 시작하세요.");
				return;

			}
		}

	}
	//학생 정보 매뉴
	private static void studentMenu() throws SQLException {
	     int no;

	        // StudentRegisterManager studnetManager = new StudentRegisterManager();
	     	boolean exitFlag = false;
	        MenuViewer.studentMenuView();
	        no = Integer.parseInt(sc.nextLine());
	        StudentRegisterManager srm = new StudentRegisterManager(); 
	        while(!exitFlag) {
	        switch (no) {
	        case SUBMENU_CHOICE.INSERT:
	            System.out.println("");
	            srm.studentInsertManager();
	            break;
	        case SUBMENU_CHOICE.UPDATE:
	            System.out.println("");
	            srm.studentUpdateManager();
	            break;
	        case SUBMENU_CHOICE.LIST:
	            System.out.println("");
	            srm.totalSelectManager();
	            break;
	        case SUBMENU_CHOICE.MAIN:
	        	exitFlag=true;
	        	break;
	        default:
	            System.out.println("해당 메뉴 번호만 입력하세요.");
	        }
	        }
		
	}
	
	//학과 정보 매뉴
	private static void subjectMenu() throws SQLException {
	     int no;

	        // StudentRegisterManager studnetManager = new StudentRegisterManager();
	     	boolean exitFlag = false;
	        MenuViewer.subjectMenuView();
	        no = Integer.parseInt(sc.nextLine());
	        SubjectRegisterManager srm = new SubjectRegisterManager(); 
	        while(!exitFlag) {
	        switch (no) {
	        case SUBMENU_CHOICE.INSERT:
	            System.out.println("");
	            srm.studentInsertManager();
	            break;
	        case SUBMENU_CHOICE.UPDATE:
	            System.out.println("");
	            srm.studentUpdateManager();
	            break;
	        case SUBMENU_CHOICE.LIST:
	            System.out.println("");
	            srm.totalSelectManager();
	            break;
	        case SUBMENU_CHOICE.MAIN:
	        	exitFlag=true;
	        	break;
	        default:
	            System.out.println("해당 메뉴 번호만 입력하세요.");
	        }
	        }
		
	}

	//과목 정보 매뉴
	private static void lessonMenu() throws SQLException {
	     int no;

	        // StudentRegisterManager studnetManager = new StudentRegisterManager();
	     	boolean exitFlag = false;
	        MenuViewer.lessonMenuView();
	        no = Integer.parseInt(sc.nextLine());
	        LessonRegisterManager lrm = new LessonRegisterManager(); 
	        while(!exitFlag) {
	        switch (no) {
	        case SUBMENU_CHOICE.INSERT:
	            System.out.println("");
	            lrm.studentInsertManager();
	            break;
	        case SUBMENU_CHOICE.UPDATE:
	            System.out.println("");
	            lrm.studentUpdateManager();
	            break;
	        case SUBMENU_CHOICE.LIST:
	            System.out.println("");
	            lrm.totalSelectManager();
	            break;
	        case SUBMENU_CHOICE.MAIN:
	        	exitFlag=true;
	        	break;
	        default:
	            System.out.println("해당 메뉴 번호만 입력하세요.");
	        }
	        }
		
	}
	
	//수강신청 정보 매뉴
	private static void traineeMenu() throws SQLException {
	     int no;

	        // StudentRegisterManager studnetManager = new StudentRegisterManager();
	     	boolean exitFlag = false;
	        MenuViewer.traineeMenuView();
	        no = Integer.parseInt(sc.nextLine());
	        TraineeRegisterManager trm = new TraineeRegisterManager(); 
	        while(!exitFlag) {
	        switch (no) {
	        case SUBMENU_CHOICE.INSERT:
	            System.out.println("");
	            trm.studentInsertManager();
	            break;
	        case SUBMENU_CHOICE.UPDATE:
	            System.out.println("");
	            trm.studentUpdateManager();
	            break;
	        case SUBMENU_CHOICE.LIST:
	            System.out.println("");
	            trm.totalSelectManager();
	            break;
	        case SUBMENU_CHOICE.MAIN:
	        	exitFlag=true;
	        	break;
	        default:
	            System.out.println("해당 메뉴 번호만 입력하세요.");
	        }
	        }
		
	}

}
