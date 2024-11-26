package com.kh.theaterProject.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.kh.theaterProject.model.BookingVO;
import com.kh.theaterProject.model.SeatsVO;
import com.kh.theaterProject.view.SeatsPrint;

public class SeatsRegisterManager {
	public static Scanner sc = new Scanner(System.in);

	// 출력
	public void selectManager() throws SQLException {
		
		
	}
	
	// seats를 업데이트 하는데 CustomerVO와, PALYINGVO를 받아서 customerNO, playingNo, amount, 사용할 예정임.
	public void updateBookingManager(BookingVO bvo) throws SQLException {
		SeatsDAO sDAO = new SeatsDAO();
		ArrayList<SeatsVO> svoList = new ArrayList<SeatsVO>();
		svoList = returnListByBVO(bvo);
		SeatsPrint.printAllByList(svoList);
		System.out.println("현재 예매 현황입니다. 예매할 좌석 번호를 순서대로 입력해주세요.");
		int count = 0;
		while(count < bvo.getAmount()) {
			Boolean exitFlag = false;
			System.out.print(">>");
			String seat = sc.nextLine();
			int listCount=0;
			for(SeatsVO data : svoList) {
				if((data.getX()+data.getY()+"").equals(seat)&&data.getCustomerNo()==null) {
					data.setCustomerNo(bvo.getCustomer_no());
					svoList.set(listCount, data);
					exitFlag=true;
				}
				listCount++;
			}
			if(exitFlag) {
				System.out.println("예매가능한 좌석입니다.");
			count++;}
			else {
				System.out.println("예매할수 없는 좌석입니다.");
			}
		}
		sDAO.updateBookingDB(svoList);
	}

	// 예매 취소시 좌석 변경
	public void updateCancleManager(BookingVO bvo) throws SQLException {
		SeatsDAO sDAO = new SeatsDAO();
		ArrayList<SeatsVO> svoList = new ArrayList<SeatsVO>();
		svoList = returnListByBVO(bvo);
			int listCount=0;
			for(SeatsVO data : svoList) {
				if(data.getCustomerNo()!=null&&data.getCustomerNo().equals(bvo.getCustomer_no())) {
					data.setCustomerNo(null);
					svoList.set(listCount, data);
				}
				listCount++;
			}
			sDAO.updateBookingDB(svoList);
			SeatsPrint.printAllByList(svoList);
		}
	
	//PlayingVO를 받아서 해당 상영정보에 일치하는 SeatsVO 전체를 ArrayList에 담아서 반환.
	public ArrayList<SeatsVO> returnListByBVO(BookingVO bvo) throws SQLException {
		SeatsDAO seatsDAO = new SeatsDAO();
		ArrayList<SeatsVO> svoList = new ArrayList<SeatsVO>();
		SeatsVO svo = new SeatsVO();
		svo.setPlayingNo(bvo.getPlaying_no());
		for (SeatsVO data : seatsDAO.returnList(svo)) {
			svoList.add(data);
		}
		return svoList;
	}
	

}
