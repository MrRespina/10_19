package com.ji.http005;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import com.ji.http001.JiHttpClient;

public class EarthquakeMain1 {
	
	static Shelter[] s = new Shelter[1000];
	static int num = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		start();
		
		for (int i = 0; i < num; i++) {

			try {
				s[i].writeFile();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}

		}
		

	}
	
	public static void start() {
		
		String url = "http://openapi.seoul.go.kr:8088/70646b63596a69683437716f794e49/xml/TlEtqkP/1/";
		Scanner k = new Scanner(System.in);	
		
		try {
			
			System.out.print("검색할 지진옥외대피소의 갯수를 입력해주세요 : ");
			num = k.nextInt();
			
			url = url + num;
			System.out.println(url);
			
			InputStream is = JiHttpClient.download(url);
			XmlPullParserFactory xppf = XmlPullParserFactory.newInstance();
			XmlPullParser xpp = xppf.newPullParser();
			xpp.setInput(is, "UTF-8");
			
			String[] store = { "SD_NM", "SGG_NM", "EQUP_NM", "LOC_SFPR_A", "XCORD", "YCORD" };

			int type = xpp.getEventType();
			String tagName = null;
			
			String[] keys = new String[6];
			String[] values = new String[6];
			
			int count = 0;

			for (int i = 0; i < num; i++) {

				s[i] = new Shelter();

			}

			while (type != XmlPullParser.END_DOCUMENT) {

				if (type == XmlPullParser.START_TAG) { // <>

					tagName = xpp.getName();

				} else if (type == XmlPullParser.TEXT) {

					for (int i = 0; i < 6; i++) {

						if (tagName.equals(store[i])) {

							keys[i] = tagName;
							values[i] = xpp.getText();
							s[count].setKeys(keys[i], values[i]);

						}

					}
					
					if (s[count].immo() == true) {
						
						//Shelter s 클래스에 정보 제대로 들어갔는지 console 확인용!
						s[count].printInfo();
						
						//받은 num의 숫자 -1 (MAX) 만큼 반복해서 Shelter 객체 생성.
						if(count < (num-1)) {
							count++;
						}
					
					}

				} else if (type == XmlPullParser.END_TAG) { // </>

					tagName = ""; // xml에서는 띄어쓰기를 텍스트로 인식하는 경우가 있음.

				}

				xpp.next(); // 다음 Data로 넘어감.
				type = xpp.getEventType(); // 다음 TAG 값을 가짐.

			}
			
			k.close();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	
		
	}


}
