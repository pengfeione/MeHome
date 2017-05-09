package com.mehome.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by renhui on 2017/3/3.
 */
public class DateUtils {
	public static Log log = LogFactory.getLog(DateUtils.class);
    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
}
