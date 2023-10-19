package com.ji.http005;

import java.util.Scanner;

public class EarthquakeMain2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner k = new Scanner(System.in);
		ParseModule pm = new ParseModule();
		System.out.print("검색할 지진옥외대피소의 갯수를 입력해주세요 ! : ");
		int num = k.nextInt();

		try {

			pm.start(num);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		k.close();
		
	}

}
