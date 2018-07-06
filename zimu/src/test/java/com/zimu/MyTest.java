package com.zimu;

import java.math.BigDecimal;

public class MyTest {

	public static void main(String[] args) {
		String x=new BigDecimal(7).multiply(new BigDecimal(100)).divide(new BigDecimal(55),2,BigDecimal.ROUND_HALF_UP).toString();
		System.out.println(x);

	}
}
