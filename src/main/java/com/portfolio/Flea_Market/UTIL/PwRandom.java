package com.portfolio.Flea_Market.UTIL;

import java.util.Random;

public class PwRandom {

	private int size;
	private boolean lowerCheck;
	
	Random rand = new Random();
	StringBuffer sb = new StringBuffer();
	
	public String getKey(boolean lowerCheck, int size) {
		this.lowerCheck = lowerCheck;
		this.size = size;
		
		return getPwRandom();
	}
	
	
	public String getPwRandom() {
		
		int num = 0;
		
		do {
			
			num = rand.nextInt(75) + 48;
			if ((num >= 48 && num <= 57) || (num >= 65 && num <= 90) || (num >= 97 && num <= 122)) {
				sb.append((char) num);
			} else {
				continue;
			}
			
		} while (sb.length() < size);
		
		if (lowerCheck) {
			return sb.toString().toLowerCase();
		}
		
		return sb.toString();
	}
	
}
