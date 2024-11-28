package publicDataTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import publicDataTest.controller.LandPriceRegisterManager;
import publicDataTest.model.LandPriceVO;

public class PublicDataApiTest {
static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		
		int m = 0;
		
		System.out.println("1. s 2. i 3. d");
		m=sc.nextInt();
		try {
		switch (m) {
		case 1: {
			LandPriceRegisterManager.totalSelectManager();
		}
		case 2: {
			LandPriceRegisterManager.LandPriceInsertManager();
		}
		case 3: {
			LandPriceRegisterManager.LandPriceDeleteManager();
		}
		}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	public static ArrayList<LandPriceVO> apiDataLoad() {

		// 1. url 주소를 적는다.
		ArrayList<LandPriceVO> lpList = new ArrayList<LandPriceVO>();
		StringBuilder urlBuilder = null;
		try {
			urlBuilder = new StringBuilder("https://apis.data.go.kr/1613000/BusSttnInfoInqireService/getSttnNoList");
			urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8")
					+ "=R1aXUY1vkohw4omiMvjBE9MfSgRp8Osgl4Fs1VnOabPAFRMl0Nj0oSBMQ3V5h5Dv2zqjcBuzpDmm5e77Y0hkkg%3D%3D");
			urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("10", "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("_type", "UTF-8") + "=" + URLEncoder.encode("xml", "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("cityCode", "UTF-8") + "=" + URLEncoder.encode("25", "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("nodeNm", "UTF-8") + "=" + URLEncoder.encode("전통시장", "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("&nodeNo", "UTF-8") + "=" + URLEncoder.encode("44810", "UTF-8"));

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 2. 공공데이터 connection 객체 생성
		URL url = null;
		HttpURLConnection conn = null;
		try {
			url = new URL(urlBuilder.toString());
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 서버에서 응답을 줄대느   3가지 방식(start line : 상태코드 , message header : 서버상태정보 , message
		// body : html 코드 , 데이터코드)
		// 3. 요청 전송 ,응답처리(200~300 : 정상 , 403 : 권한설정, 404: 페이지 찾기 힘듬 , 405: 인증안됨 ,500:
		// 서버에서 예외처리)
		BufferedReader br = null;
		try {
			int responseCode = conn.getResponseCode();
			if (responseCode >= 200 && responseCode <= 300) {
				InputStream is = conn.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				br = new BufferedReader(isr);

			} else {
				InputStream is = conn.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				br = new BufferedReader(isr);
			}
			// message body 데이터를 가져오자
			Document doc = parseXML(conn.getInputStream());
			// document 객체속에서 item 테그명을 찾는다 . (태그객체 = 노드)
			NodeList parentNode = doc.getElementsByTagName("item");
			// 객체를 가져오는데 디비에서 가져오느게 아니라 공공데이터 서버에서 네트우크로 가져오는것.
			
			// parentNode 객체에서 자식 객체 가져오기
			for (int i = 0; i < parentNode.getLength(); i++) {
				Node item = parentNode.item(i);
				LandPriceVO lvo = new LandPriceVO();
				// 자식의 이름을 검색해서 거기에 해당하는 데이터를 가져와 셋
				// Node 에는 <div 속성> context(내용,자식노드)</div>
				for (Node node = item.getFirstChild(); node != null; node=node.getNextSibling()) {
					switch (node.getNodeName()) {
					case "gpslati":
						lvo.setGpslati(Double.parseDouble(node.getTextContent()));
						break;
					case "gpslong":
						lvo.setGpslong(Double.parseDouble(node.getTextContent()));
						break;
					case "nodeid":
						lvo.setNodeid(node.getTextContent());
						break;
					case "nodenm":
						lvo.setNodenm(node.getTextContent());
						break;
					case "nodeno":
						lvo.setNodeno(Integer.parseInt(node.getTextContent()));
						break;
					}
				}
				lpList.add(lvo);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(br!=null) {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(conn!= null) {
			conn.disconnect();
		}
		return lpList;
	}

	// 입력에 해당되는 객체를 주면 Document 변환해서 리턴한다.
	private static Document parseXML(InputStream stream) {
		DocumentBuilderFactory objDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder objDocumentBuilder = null;
		Document doc = null;
		try {
			objDocumentBuilder = objDocumentBuilderFactory.newDocumentBuilder();
			doc = objDocumentBuilder.parse(stream);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) { // Simple API for XML e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return doc;
	}

}
