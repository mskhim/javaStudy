package com.kh.theaterProject.view;

import java.sql.SQLException;

import com.kh.theaterProject.controller.BookingDAO;
import com.kh.theaterProject.model.BookingVO;

public class BookingPrint {

	// 전체 고객 리스트를 출력요청
	public static void printAll() throws SQLException {
		BookingDAO cineDAO = new BookingDAO();
		System.out.println(BookingVO.getHeader());
		for (BookingVO data : cineDAO.returnList()) {
			System.out.println(data.toString());
		}
	}

	// 전체 예매순 정렬 고객 리스트를 출력요청
	public static void printSortAll() throws SQLException {
		BookingDAO cineDAO = new BookingDAO();
		System.out.println(BookingVO.getHeader());
		for (BookingVO data : cineDAO.returnSortList()) {
			System.out.println(data.toString());
		}
	}

	// code가들어있는 bvo를 받아서 그 bvo를 출력
	public static void printByCode(BookingVO bvo) throws SQLException {
		BookingDAO cineDAO = new BookingDAO();
		System.out.println(BookingVO.getHeader());
		System.out.println(cineDAO.returnCodebvo(bvo).toString());

	}

	// customerNO가들어있는 bvo를 받아서 그 bvo를 출력
	public static void printByCustomer(BookingVO bvo) throws SQLException {
		BookingDAO cineDAO = new BookingDAO();
		System.out.println(BookingVO.getHeader());
		for (BookingVO data : cineDAO.returnCustomerList(bvo)) {
			System.out.println(data.toString());
		}

	}

}
