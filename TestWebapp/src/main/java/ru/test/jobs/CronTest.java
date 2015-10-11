package ru.test.jobs;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.quartz.CronExpression;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CronTest {
	
	public static void main(String[] args) {
		Date date;
	    CronExpression exp;
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	    // Run every 10 minutes and 30 seconds in the year 2002
	    String a = "30 */10 * * * ? 2002";      
	    // Run every 10 minutes and 30 seconds of any year
	    String b = "0 0,30 12,13,14 * * ? *"; 
	    int i = 0;
	    Map<Date, Integer> map = new HashMap<>(); 
	    try {
	        date = new Date();
	        exp = new CronExpression(b);
	        
	    	while (date != null){
		        date = exp.getNextValidTimeAfter(date);
		        System.out.println(date.getTime() + " - " + sdf.format(date));       // Tue Nov 04 19:20:30 PST 2014
		        ++i;
		        map.put(date, new Integer(i));
	    	}
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
	    System.out.println(i);
	}
}
