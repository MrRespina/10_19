package com.ji.http003;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import com.ji.http001.JiHttpClient;

public class AirMain2 {

	static Doc[] d = new Doc[25];

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		getInfo();

		for (int i = 0; i < 25; i++) {

			try {
				writeFile(d[i]);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}

		}

	}

	public static void getInfo() {

		try {
			String url = "http://openapi.seoul.go.kr:8088/70646b63596a69683437716f794e49/xml/RealtimeCityAir/1/25/";
			InputStream is = JiHttpClient.download(url);

			XmlPullParserFactory xppf = XmlPullParserFactory.newInstance();
			XmlPullParser xpp = xppf.newPullParser();
			xpp.setInput(is, "UTF-8");
			String[] store = { "MSRDT", "MSRSTE_NM", "PM10", "PM25", "O3", "IDEX_MVL" };

			int type = xpp.getEventType();
			String tagName = null;
			String[] keys = new String[6];
			String[] values = new String[6];
			int count = 0;

			for (int i = 0; i < 25; i++) {

				d[i] = new Doc();

			}

			while (type != XmlPullParser.END_DOCUMENT) {

				if (type == XmlPullParser.START_TAG) { // <>

					tagName = xpp.getName();

				} else if (type == XmlPullParser.TEXT) {

					for (int i = 0; i < 6; i++) {

						if (tagName.equals(store[i])) {

							keys[i] = tagName;
							values[i] = xpp.getText();
							d[count].setKeys(keys[i], values[i]);

						}

					}

					if (d[count].immo() == true) {
						
						//Doc d 클래스에 정보 제대로 들어갔는지 console 확인용!
						d[count].printInfo();
						
						if(count < 24) {
							count++;
						}
					
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

	public static void writeFile(Doc d) throws IOException {

		String res = "C:/Users/sdedu/Desktop/source.txt";
		FileOutputStream out = new FileOutputStream(res, true);
		OutputStreamWriter outW = new OutputStreamWriter(out, "UTF-8");
		BufferedWriter bw = new BufferedWriter(outW);

		String year = d.date.substring(0, 4);
		String month = d.date.substring(4, 6);
		String day = d.date.substring(6, 8);
		String time = d.date.substring(8,10);
		String minute = d.date.substring(10,12);
		String result = (year + "년 " + month + "월 " + day + "일 " +time+ "시 "+minute+"분 기준");

		try { // 상단 경로 부분에 true를 사용해야 append가 적용 // true를 사용하지 않으면 append를 해도 덮어쓰기
			bw.append("날짜 : " + result + "\n");
			bw.append("위치 : " + d.gu + "\n");
			bw.append("미세먼지 : " + d.pm10 + "(㎍/㎥)\n");
			bw.append("초미세먼지 : " + d.pm25 + "(㎍/㎥)\n");
			bw.append("오존 : " + d.o3 + "(ppm)\n");
			bw.append("통합대기환경지수 : " + d.mvl + "\n");
			bw.append("======================\n");
			bw.flush();
			System.out.println(d.gu+" 입력 완료 !");

		} catch (IOException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {

		} catch (Exception e) {
			bw.close();
			e.printStackTrace();
		}

	}

}
