package com.ji.http004;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.ji.http001.JiHttpClient;

// AJAX - JavaScript에서 XML/JSON 파싱
//		> XML은 많이 안쓰임.
//		> JS에 친화된 형태?

// JSON	(JavaScriot Object Notation)
//		DB에 있는 데이터를 JavaScript 형태로 표현한 것.
//

// Java 배열 : {1,2,3}
// JS 배열 : [1,2,3]

// Java 객체 : Dog d = new Dog();
//				d.getName("a");

// JS 객체 : { name : "a", age : 3 }

//	https://api.openweathermap.org/data/2.5/weather?q=seoul&appid=8a6cd099fcff98185b8b37a3f69227b1
//	protocol//hostaddress/folder1/folder2/.../filename?변수명=값&변수명=값...
//	요청 파라미터 : client가 서버에게 공개적으로 전달할 정보. 한글,특문 다이렉트 x >> URL Incoding 해야함.
//	API key : 8a6cd099fcff98185b8b37a3f69227b1

public class WeatherMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			Scanner s = new Scanner(System.in);
			System.out.print("검색할 도시 입력 : ");
			String cityName = s.nextLine();

			cityName = URLEncoder.encode(cityName, "UTF-8");

			String address = "https://api.openweathermap.org/data/2.5/weather";
			address += "?q=" + cityName;
			address += "&units=metric";
			address += "&appid=8a6cd099fcff98185b8b37a3f69227b1";

			System.out.println("URL : " + address);
			InputStream is = JiHttpClient.download(address);
			String str = JiHttpClient.convert(is, "UTF-8");

			JSONParser jp = new JSONParser();
			JSONObject jo = (JSONObject) jp.parse(str);

			JSONObject sys = (JSONObject) jo.get("sys");
			String country = (String) sys.get("country");
			System.out.println(country+" 나라의 "+jo.get("name"));
			
			//	description
			//	전체를 감싸는 객체 속에 weather 라는 배열 안의 0번째 객체
			JSONArray ja = (JSONArray) jo.get("weather");
			JSONObject jo2 = (JSONObject) ja.get(0);
			String description = (String) jo2.get("description");
			System.out.println("날씨는 "+description);
			
			// temp
			JSONObject mainO = (JSONObject) jo.get("main");
			
			Double temp = (Double) mainO.get("temp");					
			Double feels_like = (Double) mainO.get("feels_like");
			Double temp_min = (Double) mainO.get("temp_min");
			Double temp_max = (Double) mainO.get("temp_max");
			long pressure = (long) mainO.get("pressure");
			long humidity = (long) mainO.get("humidity");
			
			System.out.println("온도는 "+temp+"°C");
			System.out.println("체감 온도는 "+feels_like+"°C");
			System.out.println("최저 기온은 "+temp_min+"°C");
			System.out.println("최고 기온은 "+temp_max+"°C");
			System.out.println("기압은 "+pressure+"hPa");
			System.out.println("습도는 "+humidity+"%");
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
