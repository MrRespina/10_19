package com.ji.http003;

import java.io.InputStream;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import com.ji.http001.JiHttpClient;

//	data.go.kr		data.seoul.go.kr		dev.naver.com		dev.kakao.com
//	Parsing > kxm12.jar (Android에는 내장)
//	http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1150064000

//	XML
//	형태 : <x>yyy</x>
//	x : 태그명		START_DOCUMENT
//	<x> : 시작태그		START_TAG
//	yyy : text		TEXT
//	</x> : 종료 태그	END_TAG
//					END_DOCUMENT

public class WeatherMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/*
		 * try { InputStream is = JiHttpClient.download(
		 * "http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1150064000"); String result =
		 * JiHttpClient.convert(is, "UTF-8"); //xml은 UTF-8 System.out.println(result);
		 * 
		 * }catch (Exception e) { // TODO: handle exception e.printStackTrace(); }
		 */

		try {
			String url = "https://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1150064000";
			InputStream is = JiHttpClient.download(url);

			XmlPullParserFactory xppf = XmlPullParserFactory.newInstance();
			XmlPullParser xpp = xppf.newPullParser();
			xpp.setInput(is, "UTF-8");

			int type = xpp.getEventType();
			String tagName = null;

			while (type != XmlPullParser.END_DOCUMENT) {

				if (type == XmlPullParser.START_TAG) { // <>

					tagName = xpp.getName();

				} else if (type == XmlPullParser.TEXT) {

					if (tagName.equals("hour")) {

						System.out.printf("시간 : %s\n", xpp.getText());

					} else if (tagName.equals("temp")) {

						System.out.printf("온도 : %s\n", xpp.getText());

					} else if (tagName.equals("wfKor")) {

						System.out.printf("날씨 : %s\n", xpp.getText());

					} else if (tagName.equals("wdKor")) {

						System.out.printf("풍향 : %s\n", xpp.getText());
						System.out.println("===============");

					}

				} else if (type == XmlPullParser.END_TAG) { // </>

					tagName = "";	//xml에서는 띄어쓰기를 텍스트로 인식하는 경우가 있음.
					
				}

				xpp.next();	// 다음 Data로 넘어감.
				type = xpp.getEventType();	// 다음 TAG 값을 가짐.
			}

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

	}

}
