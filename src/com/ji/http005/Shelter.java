package com.ji.http005;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Shelter {

	String sd_nm;
	String sgg_nm;
	String equp_nm;
	String loc_sfpr_a;
	String xcord;
	String ycord;

	public void setKeys(String key, String value) {

		if (key.equals("SD_NM")) {
			sd_nm = value;
		} else if (key.equals("SGG_NM")) {
			sgg_nm = value;
		} else if (key.equals("EQUP_NM")) {
			equp_nm = value;
		} else if (key.equals("LOC_SFPR_A")) {
			loc_sfpr_a = value;
		} else if (key.equals("XCORD")) {
			xcord = value;
		} else if (key.equals("YCORD")) {
			ycord = value;
		}

	}
	
	public void printInfo() {
		
		System.out.println(sd_nm);
		System.out.println(sgg_nm);
		System.out.println(equp_nm);
		System.out.println(loc_sfpr_a);
		System.out.println(xcord);
		System.out.println(ycord);
		
	}

	public boolean immo() {

		if (sd_nm != null & sgg_nm != null & equp_nm != null & loc_sfpr_a != null & xcord != null & ycord != null) {
			return true;
		} else {
			return false;
		}

	}
	
	public void writeFile() throws IOException {

		String res = "C:/Users/sdedu/Desktop/source.txt";
		FileOutputStream out = new FileOutputStream(res, true);
		OutputStreamWriter outW = new OutputStreamWriter(out, "UTF-8");
		BufferedWriter bw = new BufferedWriter(outW);
		
			// 상단 경로 부분에 true를 사용해야 append가 적용 // true를 사용하지 않으면 append를 해도 덮어쓰기
		
			bw.append("시 : " + equp_nm + "\n");
			bw.append("군(구) : " + sgg_nm + "\n");
			bw.append("대피소 이름 : " + equp_nm + "\n");
			bw.append("상세 위치 : " + loc_sfpr_a + "\n");
			bw.append("경도 : " + xcord + "\n");
			bw.append("위도 : " + ycord + "\n");
			bw.append("======================\n");
			bw.flush();
			
			System.out.println(equp_nm+" 입력 완료 !");
			bw.close();

	}

}
