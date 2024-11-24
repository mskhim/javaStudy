package com.kh.theaterProject.view;

import java.sql.SQLException;

import com.kh.theaterProject.controller.PlayingDAO;
import com.kh.theaterProject.model.PlayingVO;

public class PlayingPrint {

	// 전체 고객 리스트를 출력요청
	public static void printAll() throws SQLException {
		PlayingDAO cineDAO = new PlayingDAO();
		for (PlayingVO data : cineDAO.retrunList()) {
			System.out.println(data.toString());
		}
	}

	// 전체 예매순 정렬 고객 리스트를 출력요청
	public static void printSortAll() throws SQLException {
		PlayingDAO cineDAO = new PlayingDAO();
		for (PlayingVO data : cineDAO.returnSortList()) {
			System.out.println(data.toString());
		}
	}

	// no가들어있는 pvo를 받아서 그 pvo를 출력
	public static void printByCode(PlayingVO pvo) throws SQLException {
		PlayingDAO cineDAO = new PlayingDAO();
		System.out.println(cineDAO.returnpvo(pvo).toString());

	}

}
