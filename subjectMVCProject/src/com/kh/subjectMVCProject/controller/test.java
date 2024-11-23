package com.kh.subjectMVCProject.controller;

import java.sql.Connection;

public class test {

	public static void main(String[] args) {
		Connection con = DBUtility.dbCon();
		if (con != null) {
			System.out.println("접속성공");
		} else {
			System.out.println("접속 실패");
		}

	}

}
