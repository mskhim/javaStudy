package com.kh.theaterProject.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.kh.theaterProject.model.BookingVO;
import com.kh.theaterProject.model.CustomerVO;
import com.kh.theaterProject.model.PlayingVO;
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
		PlayingVO pvo = new PlayingVO();
		CustomerVO cvo = new CustomerVO();
		pvo.setNo(bvo.getPlaying_no());
		cvo.setNo(bvo.getCustomer_no());
		ArrayList<SeatsVO> svoList = new ArrayList<SeatsVO>();
		svoList = returnListByPVO(pvo);
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
					data.setCustomerNo(cvo.getNo());
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
	public void updateCancleManager(CustomerVO cvo, PlayingVO pvo) throws SQLException {
		SeatsDAO sDAO = new SeatsDAO();
		ArrayList<SeatsVO> svoList = new ArrayList<SeatsVO>();
		svoList = returnListByPVO(pvo);
			int listCount=0;
			for(SeatsVO data : svoList) {
				if(data.getCustomerNo()!=null&&data.getCustomerNo().equals(cvo.getNo())) {
					data.setCustomerNo(null);
					svoList.set(listCount, data);
				}
				listCount++;
			}
			sDAO.updateBookingDB(svoList);
			SeatsPrint.printAllByList(svoList);
		}
	
	//PlayingVO를 받아서 해당 상영정보에 일치하는 SeatsVO 전체를 ArrayList에 담아서 반환.
	public ArrayList<SeatsVO> returnListByPVO(PlayingVO pvo) throws SQLException {
		SeatsDAO seatsDAO = new SeatsDAO();
		ArrayList<SeatsVO> svoList = new ArrayList<SeatsVO>();
		PlayingDAO pDAO = new PlayingDAO();
		SeatsVO svo = new SeatsVO();
		pvo= pDAO.returnpvo(pvo);
		svo.setPlayingNo(pvo.getNo());// hall정보도 받아서 hall의 col 값도 뽑아와야함.
		for (SeatsVO data : seatsDAO.returnList(svo)) {
			svoList.add(data);
		}
		return svoList;
	}
	

}
