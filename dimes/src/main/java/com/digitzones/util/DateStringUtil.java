package com.digitzones.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 日期字符串转换工具类
 * @author Administrator
 */
public final class DateStringUtil {
	private String pattern ;
	public String getPattern() {
		return pattern;
	}
	public void setPattern(String pattern) {
		this.pattern = pattern;
		sdf.applyPattern(pattern);
	}
	private SimpleDateFormat sdf ;
	public DateStringUtil(){
		this("yyyy-MM-dd");
	}
	public DateStringUtil(String pattern){
		this.pattern = pattern;
		sdf = new SimpleDateFormat(this.pattern);
	}
	/**
	 * 字符串转日期
	 * @param dateString
	 * @return
	 * @throws ParseException
	 */
	public Date string2Date(String dateString){
		try {
			return sdf.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
			System.err.println("日期格式不合法,可通过调用getPattern方法查看日期格式!");
		}
		return null;
	}
	/**
	 * 日期转字符串
	 * @param date
	 * @return
	 */
	public String date2String(Date date){
		return sdf.format(date);
	}
	/**
	 * 日期转换到日
	 * @param date
	 * @return
	 */
	public String date2DayOfMonth(Date date){
		String pat = this.pattern;
		setPattern("dd");
		String day = sdf.format(date);
		setPattern(pat);
		return day;
	}
	/**
	 * 日期转换到年月
	 * @param date
	 * @return
	 */
	public String date2Month(Date date){
		String pat = this.pattern;
		setPattern("yyyy-MM");
		String month = sdf.format(date);
		setPattern(pat);
		return month;
	}
	/**
	 * 根据当前年月产生一个月的日期对象
	 * @param currentMonth yyyy-MM格式的字符串
	 * @return List<Date>
	 */
	public List<Date> generateOneMonthDay(String currentMonth){
		List<Date> dates = new ArrayList<Date>();
		String pat = this.pattern;
		setPattern("yyyy-MM");
		try {
			
			Calendar c = Calendar.getInstance();
			Date date = sdf.parse(currentMonth);
			c.setTime(date);
			int month = c.get(Calendar.MONTH);
			while(true){
				int newMonth = c.get(Calendar.MONTH);
				if(newMonth!=month){
					break;
				}
				dates.add(c.getTime());
				c.add(Calendar.DAY_OF_MONTH, 1);
			}
			setPattern(pat);
		} catch (ParseException e) {
			System.err.println("日期格式不正确，需要yyyy-MM格式字符串,但得到的字符串为：" + currentMonth);
		}
		return dates;
	}
	
	public static void main(String[] args) {
		List<Date> dates = new DateStringUtil().generateOneMonthDay("2016-02");
		System.out.println(dates);
	}
}
