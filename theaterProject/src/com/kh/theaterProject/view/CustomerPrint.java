package com.kh.theaterProject.view;

import java.sql.SQLException;

import com.kh.theaterProject.controller.CustomerDAO;
import com.kh.theaterProject.model.CustomerVO;

public class CustomerPrint {

	// 전체 고객 리스트를 출력요청
	public static void printAll() throws SQLException {
		CustomerDAO cusDAO = new CustomerDAO();
		System.out.println(CustomerVO.getHeader()); 
		for (CustomerVO data : cusDAO.retrunList()) {
			System.out.println(data.toString());
		}
	}

	// 전체 예매순 정렬 고객 리스트를 출력요청
	public static void printSortAll() throws SQLException {
		CustomerDAO cusDAO = new CustomerDAO();
		System.out.println(CustomerVO.getHeader()); 
		for (CustomerVO data : cusDAO.returnSortList()) {
			System.out.println(data.toString());
		}
	}
	
	// no가들어있는 cvo를 받아서 그 cvo를 출력
	public static void printByNo(CustomerVO cvo) throws SQLException {
		CustomerDAO cusDAO = new CustomerDAO();
		System.out.println(CustomerVO.getHeader()); 
		System.out.println(cusDAO.returncvoByCode(cvo).toString());
		
	}

}
