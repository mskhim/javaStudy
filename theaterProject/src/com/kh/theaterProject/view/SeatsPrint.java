package com.kh.theaterProject.view;

import java.sql.SQLException;
import java.util.ArrayList;

import com.kh.theaterProject.controller.HallDAO;
import com.kh.theaterProject.model.HallVO;
import com.kh.theaterProject.model.SeatsVO;

public class SeatsPrint {

	// 전체 좌석 정보를 출력
	public static void printAllByList(ArrayList<SeatsVO> svoList) throws SQLException {
		HallDAO hDAO = new HallDAO();
		HallVO hvo = new HallVO();
		hvo.setNo(svoList.get(1).getHallNo());
		hvo = hDAO.returnhvo(hvo);
		System.out.println("=========================예매 현황=========================");
		int count = 0;
		for (SeatsVO data : svoList) {
			System.out.print("|" + data + "|");
			count++;
			if (count % hvo.getCol() == 0) {
				System.out.println();
			}

		}
	}


	
}
