package com.kh.theaterProject.view;

import java.sql.SQLException;

import com.kh.theaterProject.controller.CinemaDAO;
import com.kh.theaterProject.model.CinemaVO;

public class CinemaPrint {

	// 전체 고객 리스트를 출력요청
	public static void printAll() throws SQLException {
		CinemaDAO cineDAO = new CinemaDAO();
		for (CinemaVO data : cineDAO.retrunList()) {
			System.out.println(data.toString());
		}
	}

	// 전체 예매순 정렬 고객 리스트를 출력요청
	public static void printSortAll() throws SQLException {
		CinemaDAO cineDAO = new CinemaDAO();
		for (CinemaVO data : cineDAO.returnSortList()) {
			System.out.println(data.toString());
		}
	}
	
	// no가들어있는 cnvo를 받아서 그 cnvo를 출력
	public static void printByCode(CinemaVO cnvo) throws SQLException {
		CinemaDAO cineDAO = new CinemaDAO();
		System.out.println(cineDAO.returncnvo(cnvo).toString());
		
	}

}
