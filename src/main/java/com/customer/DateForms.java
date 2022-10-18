package com.customer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateForms {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String string = "January 2nd, 2010";
		String string2 = string.replace("nd", "");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH);
		LocalDate date = LocalDate.parse(string2, formatter);
		System.out.println(date); // 2010-01-02
	}

}
