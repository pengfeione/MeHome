package com.mehome.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by renhui on 2017/3/3.
 */
public class DateUtils {
	public static Log log = LogFactory.getLog(DateUtils.class);
    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static SimpleDateFormat simpleDateFormat_Date= new SimpleDateFormat("yyyy-MM-dd");
    public static Calendar getTodayZero(){
        Calendar calendar =  Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        return calendar;
    }
    public static Calendar dayOffset(Calendar date,int dayOffset){
        Calendar calendar = Calendar.getInstance();
        date.add(Calendar.DAY_OF_YEAR,dayOffset);
        return calendar;
    }
    public static Date strToDate(String str){
		try {
			return simpleDateFormat.parse(str);
		} catch (ParseException e) {
			log.error("字符串转化为时间出错:"+e);
		}
		return null;
		
	}
	
	public static String dateToStr(Date date){
		return simpleDateFormat.format(date);
	}
	
	public static String dateToDateStr(Date date){
		return simpleDateFormat_Date.format(date);
	}
	
	public static Date getDayEnd(Date currentDate,String dateType,Integer num) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(currentDate);
		if(("month").equals(dateType)){
			cal.add(Calendar.MONTH, num);
		}
		if(("day").equals(dateType)){
			cal.add(Calendar.DATE, num);
		}
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), 23, 59, 59);
		cal.set(Calendar.MILLISECOND, 59);
		return cal.getTime();
	}
	
	public static Date getToDayStart(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
				cal.get(Calendar.DATE), 0, 0, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	
	public static Long getSeconds(Date date){
		return date.getTime()/1000;
	}
	public static void main(String[] args) {
		Date date=getDayEnd(new Date(), "day", 1);
		System.out.println(DateUtils.dateToStr(date));
	}
}
