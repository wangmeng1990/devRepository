package com.ark.hngxt.product.util;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;



/**
 * 日期获取相关方法
 * 
 *
 */
public class DateUtils {

	/**
	 * 获取本周第一天零点
	 * 
	 * @return
	 */
	public static LocalDateTime getWeekFirstDay() {
		return LocalDateTime.of(
				LocalDate.now()
						.with(TemporalAdjusters.ofDateAdjuster(localDate -> localDate
								.minusDays(localDate.getDayOfWeek().getValue() - DayOfWeek.MONDAY.getValue()))),
				LocalTime.MIN);
	}

	/**
	 * 获取本周最后一天59分59秒
	 * 
	 * @return
	 */
	public static LocalDateTime getWeekLastDay() {
		return LocalDateTime.of(
				LocalDate.now()
						.with(TemporalAdjusters.ofDateAdjuster(localDate -> localDate
								.plusDays(DayOfWeek.SUNDAY.getValue() - localDate.getDayOfWeek().getValue()))),
				LocalTime.MAX);
	}

	/**
	 * 获取本月第一天零点
	 * 
	 * @return
	 */
	public static LocalDateTime getMonthFirstDay() {
		return LocalDateTime.of(LocalDate.now().with(TemporalAdjusters.firstDayOfMonth()), LocalTime.MIN);
	}

	/**
	 * 获取本月最后一天59分59秒
	 * 
	 * @return
	 */
	public static LocalDateTime getMonthLastDay() {
		return LocalDateTime.of(LocalDate.now().with(TemporalAdjusters.lastDayOfMonth()), LocalTime.MAX);
	}

	/**
	 * 获取本季度第一天零点
	 * 
	 * @return
	 */
	public static LocalDateTime getQuarterFirstDay() {
		LocalDateTime result = getMonthFirstDay();
		return result.withMonth(result.getMonth().firstMonthOfQuarter().getValue());
	}

	/**
	 * 获取本季度最后一天59分59秒
	 * 
	 * @return
	 */
	public static LocalDateTime getQuarterLastDay() {
		LocalDateTime result = getMonthLastDay();
		return result.withMonth(result.getMonth().firstMonthOfQuarter().getValue() + 2).with(TemporalAdjusters.lastDayOfMonth());
	}

	/**
	 * 获取今年第一天零点
	 * 
	 * @return
	 */
	public static LocalDateTime getYearFirstDay() {
		return LocalDateTime.of(LocalDate.now().with(TemporalAdjusters.firstDayOfYear()), LocalTime.MIN);
	}

	/**
	 * 获取今年最后一天59分59秒
	 * 
	 * @return
	 */
	public static LocalDateTime getYearLastDay() {
		return LocalDateTime.of(LocalDate.now().with(TemporalAdjusters.lastDayOfYear()), LocalTime.MAX);
	}
	/**
	 * Date 格式化转化为 String
	 * @param date
	 * @param format
	 * @return
	 */
	public static String data2StringForamt(Date date,String format) {
		if(null==date) {
			return "";
		}
		if(StringUtils.isBlank(format)) {
			format="yyyy-MM-dd HH:mm:ss";
		}
		return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern(format));
	}
	/**
	 * Date 转化为 String 
	 * yyyy-MM-dd HH:mm:ss
	 * @param date
	 * @return
	 */
	public static String data2String(Date date) {
		if(null==date) {
			return "";
		}
		return data2StringForamt(date,null);
	}

	/**
	 *	获取格式化 2个日期距离展示
	 *
 	 * @param createDate
	 * @param nowDate
	 * @return
	 */
	public static String getDatePoor(Date createDate, Date nowDate) {

		long nd = 1000 * 24 * 60 * 60;
		long nh = 1000 * 60 * 60;
		long nm = 1000 * 60;
		SimpleDateFormat formatYear=new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
		SimpleDateFormat format=new SimpleDateFormat("MM月dd日 HH:mm");
		 //年份
		Calendar cal = Calendar.getInstance();
		cal.setTime(createDate);
		//把日期往后增加一天.整数往后推,负数往前移动
		cal.add(Calendar.DATE,3);
		if(cal.getTime().after(nowDate)){
				// 3天前
			long diff = nowDate.getTime() - createDate.getTime();
			// 计算差多少天
			long day = diff / nd;
			if(day>=1){
				return "3天前";
			}else{
				// 计算差多少小时
				long hour = diff % nd / nh;
				if(hour>=1){
					return hour+"小时前";
				}else{
					// 计算差多少分钟
					long min = diff % nd % nh / nm;
					if(min>=10){
						return min+"分钟前";
					}else{
						return "刚刚";
					}
				}
			}
		}else{
			int createYear = cal.get(Calendar.YEAR);
			cal.setTime(nowDate);
			int nowYear = cal.get(Calendar.YEAR);
			if(createYear == nowYear){
				//3天 -今年内
				return format.format(createDate);
			}else{
				// 去年及更早
				return formatYear.format(createDate);
			}
		}
	}

	/**string转Date
	 * @param formatDate
	 * @param format
	 * @return
	 */
	public static Date stringToDateFormat(String formatDate,String format) {
		if(StringUtils.isBlank(format)) {
			format="yyyy-MM-dd HH:mm:ss";
		}
        Date date = Date.from(LocalDateTime.parse(formatDate, DateTimeFormatter.ofPattern(format)).atZone(ZoneId.systemDefault()).toInstant());
		return date;
		
	}
	
	/**string转Date
	 * @param formatDate
	 * @param format
	 * @return
	 */
	public static Date stringToDate(String formatDate) {
		return stringToDateFormat(formatDate, null);
	}
}
