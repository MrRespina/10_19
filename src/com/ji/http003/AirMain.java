package com.ji.http003;

import java.io.InputStream;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import com.ji.http001.JiHttpClient;

public class AirMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			String url = "http://openapi.seoul.go.kr:8088/70646b63596a69683437716f794e49/xml/RealtimeCityAir/1/3/";
			InputStream is = JiHttpClient.download(url);
			String arr[] = { "MSRSTE_NM", "PM10", "PM25", "O3", "NO2", "CO", "SO2", "IDEX_NM", "IDEX_MVL" };

			XmlPullParserFactory xppf = XmlPullParserFactory.newInstance();
			XmlPullParser xpp = xppf.newPullParser();
			xpp.setInput(is, "UTF-8");

			int type = xpp.getEventType();
			String tagName = null;

			while (type != XmlPullParser.END_DOCUMENT) {

				if (type == XmlPullParser.START_TAG) { // <>

					tagName = xpp.getName();

				} else if (type == XmlPullParser.TEXT) {

					/*
					 * for (int i = 0; i < 9; i++) {
					 * 
					 * if (tagName.equals(arr[i])) {
					 * 
					 * System.out.println(tagName + " : " + xpp.getText());
					 * 
					 * }
					 * 
					 * }
					 */
					
					  
					  if (tagName.equals("MSRSTE_NM")) {
					  
					  System.out.printf("측정 구 : %s\n", xpp.getText());
					  
					  } else if (tagName.equals("PM10")) {
					  
					  System.out.printf("미세먼지 : %s(㎍/㎥)\n", xpp.getText());
					  
					  } else if (tagName.equals("PM25")) {
					  
					  System.out.printf("초미세먼지 : %s(㎍/㎥)\n", xpp.getText());
					  
					  } else if (tagName.equals("O3")) {
					  
					  System.out.printf("오존 : %s(ppm)\n", xpp.getText());
					  
					  } else if (tagName.equals("NO2")) {
					  
					  System.out.printf("이산화질소 : %s(ppm)\n", xpp.getText());
					  
					  } else if (tagName.equals("CO")) {
					  
					  System.out.printf("일산화탄소 : %s(ppm)\n", xpp.getText());
					  
					  } else if (tagName.equals("SO2")) {
					  
					  System.out.printf("아황산가스 : %s(ppm)\n", xpp.getText());
					  
					  }else if (tagName.equals("IDEX_NM")) {
					  
					  System.out.printf("통합대기환경등급 : %s\n", xpp.getText());
					  
					  }else if (tagName.equals("IDEX_MVL")) {
					  
					  System.out.printf("통합대기환경지수 : %s\n", xpp.getText());
					  System.out.println("===============");
					  
					  }
					 

				} else if (type == XmlPullParser.END_TAG) { // </>

					tagName = ""; // xml에서는 띄어쓰기를 텍스트로 인식하는 경우가 있음.

				}

				xpp.next(); // 다음 Data로 넘어감.
				type = xpp.getEventType(); // 다음 TAG 값을 가짐.
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
