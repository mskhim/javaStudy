package com.kh.theaterProject.view;

import java.sql.SQLException;

import com.kh.theaterProject.controller.HallDAO;
import com.kh.theaterProject.model.HallVO;

public class HallPrint {

	// 전체 고객 리스트를 출력요청
	public static void printAll() throws SQLException {
		HallDAO cineDAO = new HallDAO();
		System.out.println(HallVO.getHeader());
		for (HallVO data : cineDAO.returnList()) {
			System.out.println(data.toString());
		}
	}

	// 전체 예매순 정렬 고객 리스트를 출력요청
	public static void printSortAll() throws SQLException {
		HallDAO cineDAO = new HallDAO();
		System.out.println(HallVO.getHeader());
		for (HallVO data : cineDAO.returnList()) {
			System.out.println(data.toString());
		}
	}

	// no가들어있는 hvo를 받아서 그 hvo를 출력
	public static void printByCode(HallVO hvo) throws SQLException {
		HallDAO cineDAO = new HallDAO();
		System.out.println(HallVO.getHeader());
		System.out.println(cineDAO.returnhvo(hvo).toString());

	}

}
