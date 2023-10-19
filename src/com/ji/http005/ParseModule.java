package com.ji.http005;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.ji.http001.JiHttpClient;

public class ParseModule {

	String url = "http://openapi.seoul.go.kr:8088/70646b63596a69683437716f794e49/json/TlEtqkP/1/";
	Scanner k = new Scanner(System.in);
	String sd_nm;
	String sgg_nm;
	String equp_nm;
	String loc_sfpr_a;
	String xcord;
	String ycord;

	public ParseModule() {

	}

	public void start(int num) throws Exception {

		url = url + num;
		System.out.println("URL : " + url);

		for(int i=0;i<num;i++) {
			
			parsing(i);
			Shelter s = makeShelter(sd_nm, sgg_nm, equp_nm, loc_sfpr_a, xcord, ycord);
			s.writeFile();
			
		}
		

	}

	public void parsing(int i) throws Exception {

		InputStream is = JiHttpClient.download(url);
		String str = JiHttpClient.convert(is, "UTF-8");

		JSONParser jp = new JSONParser();
		JSONObject jo = (JSONObject) jp.parse(str);

		JSONObject jotl = (JSONObject) jo.get("TlEtqkP");

		JSONArray ja = (JSONArray) jotl.get("row");
		JSONObject jo2 = (JSONObject) ja.get(i);

		sd_nm = (String) jo2.get("SD_NM");
		sgg_nm = (String) jo2.get("SGG_NM");
		equp_nm = (String) jo2.get("EQUP_NM");
		loc_sfpr_a = (String) jo2.get("LOC_SFPR_A");
		xcord = (String) jo2.get("XCORD");
		ycord = (String) jo2.get("YCORD");

	}

	public Shelter makeShelter(String sd_nm, String sgg_nm, String equp_nm, String loc_sfpr_a, String xcord,
			String ycord) {

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
