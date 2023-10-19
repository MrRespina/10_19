package com.ji.http003;

public class Doc {

	String date;
	String gu;
	String pm10;
	String pm25;
	String o3;
	String mvl;
	
	public Doc() {

	}

	public void setKeys(String key,String value) {

		if (key.equals("MSRDT")) {
			date = value;
		} else if (key.equals("MSRSTE_NM")) {
			gu = value;
		} else if (key.equals("PM10")) {
			pm10 = value;
		} else if (key.equals("PM25")) {
			pm25 = value;
		} else if (key.equals("O3")) {
			o3 = value;
		} else if (key.equals("IDEX_MVL")) {
			mvl = value;
		}

	}
	
	public void printInfo() {
		
		System.out.println(date);
		System.out.println(gu);
		System.out.println(pm10);
		System.out.println(pm25);
		System.out.println(o3);
		System.out.println(mvl);
		
	}
	
	public boolean immo() {
		
		if(date!=null&gu!=null&pm10!=null&pm25!=null&o3!=null&mvl!=null) {
			return true;
		}else {
			return false;
		}
		
		
		
	}
	

}
