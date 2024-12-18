package com.kh.theaterProject.controller;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Scanner;

import com.kh.theaterProject.model.CustomerVO;
import com.kh.theaterProject.view.CustomerPrint;

public class CustomerRegisterManager {
	public static Scanner sc = new Scanner(System.in);

	// 삭제
	public void deleteManager() throws SQLException {
		System.out.println("삭제할 고객 번호를 입력해주세요.");
		CustomerDAO cusDAO = new CustomerDAO();
		CustomerVO cvo = returnRightNo();
		System.out.println("============================삭제대상 고객============================");
		CustomerPrint.printByNo(cvo);
		System.out.println("=================================================================");
		boolean flag = cusDAO.deleteDB(cvo);
		System.out.println((flag) ? "삭제성공" : "삭제실패");

	}

	// 내정보 업데이트
	public void updateMyDateManager(CustomerVO cvo) throws SQLException {
		CustomerDAO cusDAO = new CustomerDAO();
		System.out.println("기존값 사용시 x 입력");
		System.out.println("이름을 입력해 주세요.");
		System.out.print(">>");
		String name = sc.nextLine();
		if (name.equals("x")) {
			name = cvo.getName();
		}

		System.out.println("비밀번호를 입력해주세요.");
		System.out.print(">>");
		String pwd = sc.nextLine();
		if (pwd.equals("x")) {
			pwd = cvo.getPwd();
		}
		System.out.println("생년월일을 입력해주세요. (yyyy-mm-dd생략하려면 x 입력 기존에 날짜가 없을경우 입력해야 수정가능)");
		System.out.print(">>");
		boolean exitFlag = false;
		Date birth = null;
		while (!exitFlag) {
			String sBirth = sc.nextLine();
			if (sBirth.equals("x")) {
				birth = cvo.getBirth();
				exitFlag = true;
			} else {
				try {
					birth = Date.valueOf(sBirth);
					exitFlag = true;
				} catch (Exception e) {
					System.out.println("날짜형식이 아닙니다.");
					System.out.print("재입력 >>");
				}
			}
		}
		System.out.println("핸드폰번호를 입력해주세요.");
		System.out.print(">>");
		String phone = sc.nextLine();
		if (phone.equals("x")) {
			phone = cvo.getPhone();
		}
		cvo = new CustomerVO(cvo.getNo(), name, cvo.getId(), pwd, birth, phone, birth);
		Boolean Flag = cusDAO.updateDB(cvo);
		System.out.println((Flag) ? "수정성공" : "수정실패");
	}

	// 업데이트
	public void updateManager() throws SQLException {
		CustomerDAO cusDAO = new CustomerDAO();
		System.out.print("수정할 고객의 고객 번호를 입력하세요 : ");
		CustomerVO cvo = returnRightNo();
		System.out.println("============================수정할대상 고객============================");
		CustomerPrint.printByNo(cvo);
		System.out.println("===================================================================");
		System.out.println("기존값 사용시 x 입력");
		System.out.println("이름을 입력해 주세요.");
		System.out.print(">>");
		String name = sc.nextLine();
		if (name.equals("x")) {
			name = cvo.getName();
		}

		System.out.println("비밀번호를 입력해주세요.");
		System.out.print(">>");
		String pwd = sc.nextLine();
		if (pwd.equals("x")) {
			pwd = cvo.getPwd();
		}
		System.out.println("생년월일을 입력해주세요. (yyyy-mm-dd생략하려면 x 입력 기존에 날짜가 없을경우 입력해야 수정가능)");
		System.out.print(">>");
		boolean exitFlag = false;
		Date birth = null;
		while (!exitFlag) {
			String sBirth = sc.nextLine();
			if (sBirth.equals("x")) {
				birth = cvo.getBirth();
				exitFlag = true;
			} else {
				try {
					birth = Date.valueOf(sBirth);
					exitFlag = true;
				} catch (Exception e) {
					System.out.println("날짜형식이 아닙니다.");
					System.out.print("재입력 >>");
				}
			}
		}
		System.out.println("핸드폰번호를 입력해주세요.");
		System.out.print(">>");
		String phone = sc.nextLine();
		if (phone.equals("x")) {
			phone = cvo.getPhone();
		}
		cvo = new CustomerVO(cvo.getNo(), name, cvo.getId(), pwd, birth, phone, birth);
		Boolean Flag = cusDAO.updateDB(cvo);
		System.out.println((Flag) ? "수정성공" : "수정실패");
	}

	// 관리자용 업데이트, 권한 부여기능 추가
	public void updateAdminManager() throws SQLException {
		CustomerDAO cusDAO = new CustomerDAO();
		System.out.print("권한을 부여할 고객의 고객 번호를 입력하세요 : ");
		CustomerVO cvo = returnRightNo();
		System.out.println("============================수정할대상 고객============================");
		CustomerPrint.printAdminByNo(cvo);
		System.out.println("===================================================================");
		System.out.println("권한을 입력해주세요. (admin or 아무값이나.)");
		System.out.print(">>");
		String right = sc.nextLine();
		cvo.setRight(right);
		Boolean Flag = cusDAO.updateAdiminDB(cvo);
		System.out.println((Flag) ? "권한 부여 성공" : "실패");
	}

	// 출력
	public void selectManager() throws SQLException {
		CustomerPrint.printAll();
	}

	// 관리자용
	public void selectAdminManager() throws SQLException {
		CustomerPrint.printAdminAll();
	}

	// 찾기
	public void findManager() throws SQLException {
		CustomerDAO cusDAO = new CustomerDAO();
		CustomerVO cvo = new CustomerVO();
		System.out.println("찾으려는 ID를 입력해주세요.");
		System.out.print(">>");
		String id = sc.nextLine();
		cvo.setId(id);
		cvo = cusDAO.returncvoById(cvo);
		if (cvo.getNo() != null) {
			System.out.println("============================찾으신대상 고객============================");
			CustomerPrint.printByNo(cvo);
			System.out.println("===================================================================");
		} else {
			System.out.println("존재하지 않는 아이디입니다.");
		}

	}

	// 입력
	public void insertManager() throws SQLException {

		CustomerDAO cusDAO = new CustomerDAO();
		System.out.println("이름을 입력해 주세요.");
		System.out.print(">>");
		String name = sc.nextLine();
		System.out.println("ID를 입력해주세요.");
		System.out.print(">>");
		String id = sc.nextLine();
		System.out.println("비밀번호를 입력해주세요.");
		System.out.print(">>");
		String pwd = sc.nextLine();
		System.out.println("생년월일을 입력해주세요. (yyyy-mm-dd생략하려면 x 입력)");
		System.out.print(">>");
		boolean exitFlag = false;
		Date birth = null;
		while (!exitFlag) {
			String sBirth = sc.nextLine();
			if (sBirth.equals("x")) {
				birth = null;
			} else {
				try {
					birth = Date.valueOf(sBirth);
					exitFlag = true;
				} catch (Exception e) {
					System.out.println("날짜형식이 아닙니다.");
					System.out.print("재입력 >>");
				}
			}
		}
		System.out.println("핸드폰번호를 입력해주세요. (생략하려면 x 입력)");
		System.out.print(">>");
		String phone = sc.nextLine();
		if (phone.equals("x")) {
			phone = null;
		}
		CustomerVO cvo = new CustomerVO(null, name, id, pwd, birth, phone, null);
		boolean flag = cusDAO.insertDB(cvo);
		System.out.println((flag) ? "입력성공" : "입력실패");
	}

	// 랜덤 입력
	public void insertRandomManager() throws SQLException {
		CustomerDAO cusDAO = new CustomerDAO();
		boolean flag = cusDAO.insertRandomDB();
		System.out.println((flag) ? "입력성공" : "입력실패");
	}

	// 회원가입
	public void insertRegistManager() throws SQLException {

		CustomerDAO cusDAO = new CustomerDAO();
		CustomerVO cvo = new CustomerVO();
		System.out.println("이름을 입력해 주세요.");
		System.out.print(">>");
		String name = sc.nextLine();

		// 아이디 중복체크
		boolean idFlag = false;
		String id = null;
		System.out.println("ID를 입력해주세요.");
		while (!idFlag) {
			System.out.print(">>");
			id = sc.nextLine();
			cvo.setId(id);
			if (cusDAO.returncvoById(cvo).getNo() == null) {
				idFlag = true;
			} else {
				System.out.println("중복된 아이디입니다.");
				System.out.print("재입력 >>");
			}

		}
		System.out.println("사용가능한 아이디 입니다.");
		String pwd = "1";
		String pwd2 = "2";
		while (!pwd.equals(pwd2)) {
			System.out.println("비밀번호를 입력해주세요.");
			System.out.print(">>");
			pwd = sc.nextLine();
			System.out.println("비밀번호를 확인해주세요.");
			System.out.print(">>");
			pwd2 = sc.nextLine();
			System.out.println((pwd.equals(pwd2)) ? "비밀번호 확인 성공" : "비민번호 확인 실패");
		}

		System.out.println("생년월일을 입력해주세요. (yyyy-mm-dd생략하려면 x 입력)");
		System.out.print(">>");
		boolean birthFlag = false;
		Date birth = null;
		while (!birthFlag) {
			String sBirth = sc.nextLine();
			if (sBirth.equals("x")) {
				birth = null;
				birthFlag = true;
			} else {
				try {
					birth = Date.valueOf(sBirth);
					birthFlag = true;
				} catch (Exception e) {
					System.out.println("날짜형식이 아닙니다.");
					System.out.print("재입력 >>");
				}
			}
		}
		System.out.println("핸드폰번호를 입력해주세요. (생략하려면 x 입력)");
		System.out.print(">>");
		String phone = sc.nextLine();
		if (phone.equals("x")) {
			phone = null;
		}
		cvo = new CustomerVO(null, name, id, pwd, birth, phone, null);
		boolean flag = cusDAO.insertDB(cvo);
		System.out.println((flag) ? "입력성공" : "입력실패");
	}

	// 로그인기능
	public CustomerVO loginManager() throws SQLException {
		CustomerVO cvo = new CustomerVO();
		CustomerRegisterManager crm = new CustomerRegisterManager();
		System.out.print("아이디를 입력해주세요 : ");
		String id = sc.nextLine();
		System.out.println();
		System.out.print("비밀번호를 입력해주세요 : ");
		String pwd = sc.nextLine();
		cvo.setId(id);
		cvo.setPwd(pwd);
		cvo = crm.returnLogin(cvo);
		if (cvo.getName() == null) {
			System.out.println("\n로그인 실패");
			return cvo;
		} else {

			System.out.println("\n환영합니다." + cvo.getName() + "고객님.");
			return cvo;
		}
	}

	// 로그인 기능. 아이디, 비밀번호 확인후 해당하는 cvo 반환해준다. 없으면 빈cvo를 반환
	public CustomerVO returnLogin(CustomerVO cvo) throws SQLException {
		CustomerDAO cusDAO = new CustomerDAO();
		CustomerVO cvo2 = cusDAO.returncvoById(cvo);
		if (cvo2.getName() == null) {
			return cvo;
		} else {
			if (cvo2.getPwd().equals(cvo.getPwd())) {
				return cvo2;
			} else {
				return cvo;
			}
		}
	}

	// 해당 클래스 내부에서만 사용할 함수들
	// 실행하면 존재하는 customer no가 나올떄까지 반복해서 올바른 CustomerVO를 반환해주는 함수
	public static CustomerVO returnRightNo() throws SQLException {
		boolean exitFlag = false;
		CustomerVO cvo = new CustomerVO();
		CustomerDAO cusDAO = new CustomerDAO();
		while (!exitFlag) {
			System.out.print(">>");
			try {
				int no = Integer.parseInt(sc.nextLine());
				String sNo = String.valueOf(no);
				cvo.setNo(sNo);
				cvo = cusDAO.returncvoByNo(cvo);
				if (cvo.getName() != null) {
					exitFlag = true;

				} else {
					System.out.println("존재하지 않는 고객입니다.");
					System.out.print("재입력 >>");
				}

			} catch (Exception e) {
				System.out.println("숫자를 입력해주세요.");
				System.out.print("재입력 >>");
			}
		}
		return cvo;
	}

}
