package com.ji.http005;

import java.io.InputStream;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.ji.http001.JiHttpClient;

public class ParseModule {

	String url = "http://openapi.seoul.go.kr:8088/70646b63596a69683437716f794e49/json/TlEtqkP/1/";
	Scanner k = new Scanner(System.in);
	private String sd_nm;
	private String sgg_nm;
	private String equp_nm;
	private String loc_sfpr_a;
	private String xcord;
	private String ycord;
	InputStream is;
	String str ;

	public ParseModule() {

	}

	public void start(int num) throws Exception {

		// 만들어놓은 HTTP 통신(JiHttpClient)에 url을 넣어줌.	
		url = url + num;
		System.out.println("URL : " + url);
		
		is = JiHttpClient.download(url);
		str = JiHttpClient.convert(is, "UTF-8");	

		for(int i=0;i<num;i++) {
			
			parsing(i);
			Shelter s = makeShelter(getSd_nm(), getSgg_nm(), getEqup_nm(), getLoc_sfpr_a(), getXcord(), getYcord());
			s.writeFile();
			
		}
		
		k.close();
		
	}	

	public String getSd_nm() {
		return sd_nm;
	}

	public void setSd_nm(String sd_nm) {
		this.sd_nm = sd_nm;
	}

	public String getSgg_nm() {
		return sgg_nm;
	}

	public void setSgg_nm(String sgg_nm) {
		this.sgg_nm = sgg_nm;
	}

	public String getLoc_sfpr_a() {
		return loc_sfpr_a;
	}

	public void setLoc_sfpr_a(String loc_sfpr_a) {
		this.loc_sfpr_a = loc_sfpr_a;
	}

	public String getXcord() {
		return xcord;
	}

	public void setXcord(String xcord) {
		this.xcord = xcord;
	}

	public String getYcord() {
		return ycord;
	}

	public void setYcord(String ycord) {
		this.ycord = ycord;
	}

	public String getEqup_nm() {
		return equp_nm;
	}

	public void setEqup_nm(String equp_nm) {
		this.equp_nm = equp_nm;
	}

	public void parsing(int i) throws Exception {
		
		// JSONParser를 사용해서 str 문항을 오브젝트로 파싱함
		JSONParser jp = new JSONParser();
		JSONObject jo = (JSONObject) jp.parse(str);

		// 최상위 오브젝트 TlEtqkP에 접근하는 객체.
		JSONObject jotl = (JSONObject) jo.get("TlEtqkP");

		// TlEtqkP 하위 Array "row"를 사용하기 위해 접근하는 객체.(i번째 번. 상위 start문에서 JSON 오브젝트가 여러번 여러개 생성되면서 )
		JSONArray ja = (JSONArray) jotl.get("row");
		JSONObject jo2 = (JSONObject) ja.get(i);

		// setter로 객체 속성에 추가해줌.
		setSd_nm((String) jo2.get("SD_NM"));
		setSgg_nm((String) jo2.get("SGG_NM"));
		setEqup_nm((String) jo2.get("EQUP_NM"));
		setLoc_sfpr_a((String) jo2.get("LOC_SFPR_A"));
		setXcord((String) jo2.get("XCORD"));
		setYcord((String) jo2.get("YCORD"));

	}

	public Shelter makeShelter(String sd_nm, String sgg_nm, String equp_nm, String loc_sfpr_a, String xcord,
			String ycord) {

		//Shelter 객체를 생성해서 value에 key를 세팅해줌.
		
		Shelter s = new Shelter();

		s.setKeys("SD_NM", sd_nm);
		s.setKeys("SGG_NM", sgg_nm);
		s.setKeys("EQUP_NM", equp_nm);
		s.setKeys("LOC_SFPR_A", loc_sfpr_a);
		s.setKeys("XCORD", xcord);
		s.setKeys("YCORD", ycord);

		return s;

	}

}
