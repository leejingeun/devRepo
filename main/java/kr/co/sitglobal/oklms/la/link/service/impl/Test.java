package kr.co.sitglobal.oklms.la.link.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import kr.co.sitglobal.oklms.comm.util.CommonUtil;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String traningDate = CommonUtil.getLaterDay("2017.07.29","yyyy.MM.dd", 7);

		System.out.println(traningDate);
		
	}

}