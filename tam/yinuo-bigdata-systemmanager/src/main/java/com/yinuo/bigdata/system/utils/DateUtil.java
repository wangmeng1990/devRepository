package com.yinuo.bigdata.system.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * @Title 日期工具类
 * @author ：jias
 * @date ：2020/2/11 10:31
 */
public class DateUtil {

	// 日期格式，年份，例如：2004，2008
	public static final String DATE_FORMAT_YYYY = "yyyy";

	// 日期格式，年份和月份，例如：200707，200808
	public static final String DATE_FORMAT_YYYYMM = "yyyyMM";

	// 日期格式，年份和月份，例如：200707，200808
	public static final String DATE_FORMAT_YYYY_MM = "yyyy-MM";

	// 日期格式，年月日，例如：20050630，20080808
	public static final String DATE_FORMAT_YYYYMMDD = "yyyyMMdd";

	// 日期格式，年月日，用横杠分开，例如：2006-12-25，2008-08-08
	public static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";

	// 日期格式，年月日时分秒，例如：20001230120000，20080808200808
	public static final String DATE_TIME_FORMAT_YYYYMMDDHHMISS = "yyyyMMddHHmmss";

	// 日期格式，年月日时分秒，例如：20001230120000，20080808200808
	public static final String DATE_TIME_FORMAT_YYYYMMDDHHMISSSSS = "yyyyMMddHHmmssSSS";

	// 日期格式，年月日时分秒，年月日用横杠分开，时分秒用冒号分开，
	// 例如：2005-05-10 23：20：00，2008-08-08 20:08:08
	public static final String DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS = "yyyy-MM-dd HH:mm:ss";

	public static final String DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI = "yyyy-MM-dd HH:mm";

	public static final String TIME_FORMAT_HH_MI_SS = "HH:mm:ss";
	/**
	 * 年月日时分秒
	 */
	private static SimpleDateFormat dateTimeFormat = new SimpleDateFormat(DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS);
	/**
	 * 年月日
	 */
	private static SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD);

	/**
	 * 根据指定的格式获取时间
	 * 
	 * @param format
	 * @return String
	 * @author 周玲斌
	 * @date 2013-8-21
	 */
	public static String getDateByFormat(String format) {
		Calendar today = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat(format);
		return formater.format(today.getTime());
	}

	/**
	 * 获得当前日期字符串，格式"yyyy-MM-dd HH:mm:ss"
	 * 
	 * @return String
	 * @author 周玲斌
	 * @date 2013-7-11
	 */
	public static String getNow() {
		Calendar today = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat(DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS);
		return formater.format(today.getTime());
	}

	/**
	 * 获取当前日期
	 * 
	 * @return String
	 * @author 周玲斌
	 * @date 2013-7-23
	 */
	public static String getDate() {
		Calendar today = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD);
		return formater.format(today.getTime());
	}
	
	/**
	 * 根据当前日期获得所在周的日期区间（周一和周日日期）
	 * @param date
	 * @return 贾松
	 */
	public static String getTimeInterval(Date date) {  
		 //格式化日期     
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();  
        cal.setTime(date);  
        // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了  
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天  
        if (1 == dayWeek) {  
           cal.add(Calendar.DAY_OF_MONTH, -1);  
        }  
        // System.out.println("要计算日期为:" + sdf.format(cal.getTime())); // 输出要计算日期  
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一  
        cal.setFirstDayOfWeek(Calendar.MONDAY);  
        // 获得当前日期是一个星期的第几天  
        int day = cal.get(Calendar.DAY_OF_WEEK);  
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值  
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);  
        String imptimeBegin = sdf.format(cal.getTime());  
        // System.out.println("所在周星期一的日期：" + imptimeBegin);  
        cal.add(Calendar.DATE, 6);  
        String imptimeEnd = sdf.format(cal.getTime());  
        // System.out.println("所在周星期日的日期：" + imptimeEnd);  
        return imptimeBegin + "," + imptimeEnd;  
   }
	
	/**
	 * 根据当前日期获得上周的日期区间（上周周一和周日日期）
	 * @return 贾松
	 */
	public static String getLastTimeInterval() { 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar1 = Calendar.getInstance();  
        Calendar calendar2 = Calendar.getInstance();  
        int dayOfWeek = calendar1.get(Calendar.DAY_OF_WEEK) - 1;  
        int offset1 = 1 - dayOfWeek;  
        int offset2 = 7 - dayOfWeek;  
        calendar1.add(Calendar.DATE, offset1 - 7);  
        calendar2.add(Calendar.DATE, offset2 - 7);  
        // System.out.println(sdf.format(calendar1.getTime()));// last Monday  
        String lastBeginDate = sdf.format(calendar1.getTime());  
        // System.out.println(sdf.format(calendar2.getTime()));// last Sunday  
        String lastEndDate = sdf.format(calendar2.getTime());  
        return lastBeginDate + "," + lastEndDate;  
   }

	/**
	 * 字符串转换为日期
	 * 
	 * @param strDate
	 *            字符串类型的日期
	 * @param format
	 *            转换格式
	 * @return
	 * @Author 周玲斌
	 * @version: 2017年4月25日 下午4:10:51
	 */
	public static Date strToDate(String strDate, String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = dateFormat.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 日期转换为字符串
	 * 
	 * @param date
	 * @param format
	 * @return
	 * @Author 周玲斌
	 * @version: 2017年4月25日 下午4:12:22
	 */
	public static String dateToStr(Date date, String format) {
		if(date==null){
			return "";
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}

	/**
	 * 得到当天的最后时间,today是字符串类型"yyyy-mm-dd", 返回是日期类型"yyyy-mm-dd 23:59:59"
	 * 
	 * @param today
	 * @return
	 * @Author 周玲斌
	 * @version: 2017年4月25日 下午4:12:48
	 */
	public static Date getTodayLastTime(String today) {
		String todayLastTime = today + " 23:59:59";
		return strToDate(todayLastTime, DateUtil.DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS);
	}
	
	/**
	  *    获取当天0点，today是字符串类型"yyyy-mm-dd", 返回是日期类型"yyyy-mm-dd 00:00:00"
	 * @param today
	 * @return
	 */
	public static Date getTodaycreateTime(String today) {
		String todayLastTime = today + " 00:00:00";
		return strToDate(todayLastTime, DateUtil.DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS);
	}
	

	/**
	 * 比较大小
	 * 
	 * @param source
	 *            yyyy-MM-dd HH:mm:ss
	 * @param target
	 *            yyyy-MM-dd HH:mm:ss
	 * @return 当 source < target = true, else false
	 * @throws ParseException
	 *             boolean
	 * @创建作者:周玲斌
	 * @创建时间:2015年11月18日 下午6:19:34
	 */
	public static boolean compareTime(String source, String target) {
		boolean bool = false;
		try {
			Date s = dateTimeFormat.parse(source);
			Date t = dateTimeFormat.parse(target);
			bool = s.before(t);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bool;
	}

	/**
	 * 比较日期大小
	 * 
	 * @param source
	 *            yyyy-MM-dd
	 * @param target
	 *            yyyy-MM-dd
	 * @return 当 source < target = true, else false
	 */
	public static boolean compareDate(String source, String target) {
		boolean bool = false;
		try {
			Date s = dateFormat.parse(source);
			Date t = dateFormat.parse(target);
			bool = s.before(t);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bool;
	}

	/**
	 * 求2个时间段之间的间隔
	 * 
	 * @param bdate yyyy-MM-dd HH:mm:ss
	 * @param edate  yyyy-MM-dd HH:mm:ss
	 *            edate = "" or null时间， edate的值为目前时间
	 * @param type
	 *            type = 1:2时间段间的天数,type = 2:2时间段间的小时数,type = 3:2时间段间的分钟数,type =
	 *            4:2时间段间的秒钟数
	 * @return
	 */
	public static long getDateLong(String bdate, String edate, int type) {
		if (bdate == null || bdate.equals(""))
			return 0L;
		long result = 0L;
		try {
			dateTimeFormat.parse(bdate);
			long bt = dateTimeFormat.getCalendar().getTimeInMillis();
			long et = 0L;
			if (edate != null && !edate.equals("")) {
				dateTimeFormat.parse(edate);
				et = dateTimeFormat.getCalendar().getTimeInMillis();
			} else {
				et = Calendar.getInstance().getTimeInMillis();// 当天
			}
			switch (type) {
			case 1:// 天数
				result = ((et - bt) / (1000 * 60 * 60 * 24));
				break;
			case 2:// 小时数
				result = ((et - bt) / (1000 * 60 * 60));
				break;
			case 3:// 分钟数
				result = ((et - bt) / (1000 * 60));
				break;
			case 4:// 秒钟数
				result = ((et - bt) / (1000));
				break;
			}
		} catch (ParseException e) {
			e.printStackTrace();
			return 0L;
		}

		return result;
	}

	/**
	 * 获取两个日期之间相隔的天数
	 * 
	 * @param bdate
	 *            yyyy-mm-dd
	 * @param edate
	 *            yyyy-mm-dd
	 * @return long edate-bdate
	 * @创建作者:周玲斌
	 * @创建时间:2016年1月8日 下午2:27:59
	 */
	public static long countDays(String bdate, String edate) {
		if (bdate == null || bdate.equals(""))
			return 0L;
		long result = 0L;
		try {
			dateFormat.parse(bdate);
			long bt = dateFormat.getCalendar().getTimeInMillis();
			long et = 0L;
			if (edate != null && !edate.equals("")) {
				dateFormat.parse(edate);
				et = dateFormat.getCalendar().getTimeInMillis();
			} else {
				et = Calendar.getInstance().getTimeInMillis();// 当天
			}
			result = ((et - bt) / (1000 * 60 * 60 * 24));
		} catch (ParseException e) {
			e.printStackTrace();
			return 0L;
		}
		return result;
	}

	/**
	 * 计算两个日期相隔的月数
	 * 
	 * @param date1
	 *            开始日期 yyyy-MM-dd
	 * @param date2
	 *            结束日期 yyyy-MM-dd
	 * @return 月份数 date2-date1
	 */
	public static int countMonths(String date1, String date2) {
		try {
			Calendar c1 = Calendar.getInstance();
			Calendar c2 = Calendar.getInstance();

			c1.setTime(dateFormat.parse(date1));
			c2.setTime(dateFormat.parse(date2));

			int year = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);

			if (year < 0) {// date1 大于 date2
				year = -year;
				int month = year * 12 + c1.get(Calendar.MONTH) - c2.get(Calendar.MONTH);
				int day = c1.get(Calendar.DATE) - c2.get(Calendar.DATE);
				if (day < 0) {
					month--;
				}
				return -month;
			}
			int month = year * 12 + c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
			int day = c2.get(Calendar.DATE) - c1.get(Calendar.DATE);
			if (day < 0) {
				month--;
			}
			return month;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * 时间运算， 如某时间经过多少分或多少天后的时间 某时间前几分或几小时或几天的时间
	 * 
	 * @param time
	 *            yyyy-MM-dd HH:mm:ss
	 * @param calcuate
	 *            计算数
	 * @param type
	 *            计算类型，0:分为单位;1:时为单位;2天为单位;3月为单位
	 * @param addOrdec
	 *            运算类型 1:加 ; 2:减
	 * @return 返回的日期格式是
	 */
	public static String nextDateTime(String time, int calcuate, int type, int addOrdec) {
		Calendar now_Time = Calendar.getInstance();
		DateFormat df = new SimpleDateFormat(DateUtil.DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS);
		try {
			now_Time.setTime(df.parse(time));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (addOrdec == 2)
			calcuate = 0 - calcuate;
		switch (type) {
		case 0:
			now_Time.add(Calendar.MINUTE, calcuate);
			break;
		case 1:
			now_Time.add(Calendar.HOUR_OF_DAY, calcuate);
			break;
		case 2:
			now_Time.add(Calendar.DATE, calcuate);
			break;
		case 3:
			now_Time.add(Calendar.MONTH, calcuate);
			break;
		}
		SimpleDateFormat sdNowDate = new SimpleDateFormat(DateUtil.DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS);
		return sdNowDate.format(now_Time.getTime());
	}

	/**
	 * 日期运算
	 *
	 * @param time
	 *            yyyy-MM-dd
	 * @param calcuate
	 *            计算数
	 * @param type
	 *            计算类型，0天为单位;1月为单位;2年为单位;
	 * @param addOrdec
	 *            运算类型 1:加 ; 2:减
	 * @return 返回的日期格式是 yyyy-MM-dd
	 */
	public static String nextDate(String time, int calcuate, int type, int addOrdec) {
		Calendar now_Time = Calendar.getInstance();
		DateFormat df = new SimpleDateFormat(DateUtil.DATE_FORMAT_YYYY_MM_DD);
		try {
			now_Time.setTime(df.parse(time));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (addOrdec == 2)
			calcuate = 0 - calcuate;
		switch (type) {
		case 0:
			now_Time.add(Calendar.DATE, calcuate);
			break;
		case 1:
			now_Time.add(Calendar.MONTH, calcuate);
			break;
		case 2:
			now_Time.add(Calendar.YEAR, calcuate);
			break;
		}
		SimpleDateFormat sdNowDate = new SimpleDateFormat(DateUtil.DATE_FORMAT_YYYY_MM_DD);
		return sdNowDate.format(now_Time.getTime());
	}
	
	/**
	 * 计算 time 的下n天
	 * 
	 * @param time
	 *            yyyy-MM-dd
	 * @return yyyy-MM-dd
	 */
	public static String nextDay(String time, int nday) {
		Calendar now_Time = Calendar.getInstance();
		DateFormat df = new SimpleDateFormat(DateUtil.DATE_FORMAT_YYYY_MM_DD);
		try {
			now_Time.setTime(df.parse(time));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		now_Time.add(Calendar.DATE, nday);
		SimpleDateFormat sdNowDate = new SimpleDateFormat(DateUtil.DATE_FORMAT_YYYY_MM_DD);
		return sdNowDate.format(now_Time.getTime());
	}

	/**
	 * 日期运算
	 * 
	 * @param dateStr
	 *            yyyy-MM-dd
	 * @param calcuate
	 *            计算数
	 * @param addOrdec
	 *            运算类型 1:加 ; 2:减
	 * @return yyyy-MM-dd
	 */
	public static String nextDay(String dateStr, int calcuate, int addOrdec) {
		Calendar nowDate = Calendar.getInstance();
		DateFormat df = new SimpleDateFormat(DateUtil.DATE_FORMAT_YYYY_MM_DD);
		try {
			nowDate.setTime(df.parse(dateStr));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (addOrdec == 2)
			calcuate = 0 - calcuate;
		nowDate.add(Calendar.DATE, calcuate);
		SimpleDateFormat sdNowDate = new SimpleDateFormat(DateUtil.DATE_FORMAT_YYYY_MM_DD);
		return sdNowDate.format(nowDate.getTime());
	}

	public static String nextDays(String dateStr, int calcuate, int addOrdec) {
		Calendar nowDate = Calendar.getInstance();
		DateFormat df = new SimpleDateFormat(DateUtil.DATE_FORMAT_YYYYMMDD);
		try {
			nowDate.setTime(df.parse(dateStr));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (addOrdec == 2)
			calcuate = 0 - calcuate;
		nowDate.add(Calendar.DATE, calcuate);
		SimpleDateFormat sdNowDate = new SimpleDateFormat(DateUtil.DATE_FORMAT_YYYYMMDD);
		return sdNowDate.format(nowDate.getTime());
	}

	/**
	 * 月份运算
	 * 
	 * @param dateStr
	 *            yyyy-MM
	 * @param calcuate
	 *            计算数
	 * @param addOrdec
	 *            运算类型 1:加 ; 2:减
	 * @return
	 */
	public static String nextMonth(String dateStr, int calcuate, int addOrdec) {
		Calendar nowDate = Calendar.getInstance();
		DateFormat df = new SimpleDateFormat(DateUtil.DATE_FORMAT_YYYY_MM);
		try {
			nowDate.setTime(df.parse(dateStr));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (addOrdec == 2)
			calcuate = 0 - calcuate;
		nowDate.add(Calendar.MONTH, calcuate);
		SimpleDateFormat sdNowDate = new SimpleDateFormat(DateUtil.DATE_FORMAT_YYYY_MM);
		return sdNowDate.format(nowDate.getTime());
	}

	/**
	 * 比较tg时间是否在strat到end时间段之间
	 * 
	 * @param strat
	 * @param end
	 * @param tg
	 * @return 是返回true，否则 false
	 */
	public static boolean cmpDate(Date strat, Date end, Date tg) {
		if (tg == null)
			return false;
		long tl = tg.getTime();
		return tl >= strat.getTime() && tl <= end.getTime();
	}

	/**
	 * 获取指定时间相差的时间
	 * 
	 * @param date
	 *            指定时间
	 * @param bdCount
	 *            相差天数
	 * @return
	 */
	public static String getDateString(Date date, int bdCount) {
		Calendar c = Calendar.getInstance();
		c.setTime(date); // 设置当前日期
		c.add(Calendar.DATE, bdCount); // 日期分钟加1,Calendar.DATE(天),Calendar.HOUR(小时)
		return DateUtil.dateToStr(c.getTime(), DateUtil.DATE_FORMAT_YYYY_MM_DD); // 结果
	}

	/**
	 * 计算两个时间相差的天时分秒
	 * 
	 * @param startTime
	 * @param endTime
	 * @param format
	 * @param type
	 *            0-天时分秒，1-天时分，2-天时，3-天
	 * @return Long
	 */
	public static String dateDiff(String startTime, String endTime, String format, int type) {
		// 按照传入的格式生成一个simpledateformate对象
		SimpleDateFormat sd = new SimpleDateFormat(format);
		long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
		long nh = 1000 * 60 * 60;// 一小时的毫秒数
		long nm = 1000 * 60;// 一分钟的毫秒数
		long ns = 1000;// 一秒钟的毫秒数
		long diff;
		long day = 0;
		long hour = 0;
		long min = 0;
		long sec = 0;
		String result = "";
		// 获得两个时间的毫秒时间差异
		try {
			diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
			day = diff / nd;// 计算差多少天
			hour = diff % nd / nh + day * 24;// 计算差多少小时
			min = diff % nd % nh / nm + day * 24 * 60;// 计算差多少分钟
			sec = diff % nd % nh % nm / ns;// 计算差多少秒
			if (type == 1) {
				result = day + " 天 " + (hour - day * 24) + " 时 " + (min - day * 24 * 60) + " 分 ";
			} else if (type == 2) {
				result = day + " 天 " + (hour - day * 24) + " 时 ";
			} else if (type == 3) {
				result = day + " 天 ";
			} else {
				result = day + " 天 " + (hour - day * 24) + " 时 " + (min - day * 24 * 60) + " 分 " + sec + " 秒 ";
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 判断两个日期是否为同一周
	 * 
	 * @param date1
	 * @param date2
	 * @return boolean
	 * @创建作者:周玲斌
	 * @创建时间:2016年1月8日 下午2:06:33
	 */
	public static boolean isSameDate(String date1, String date2) {
		boolean bool = false;
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date d1 = null;
			Date d2 = null;
			try {
				d1 = format.parse(date1);
				d2 = format.parse(date2);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Calendar cal1 = Calendar.getInstance();
			Calendar cal2 = Calendar.getInstance();
			cal1.setTime(d1);
			cal2.setTime(d2);
			int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
			// subYear==0,说明是同一年
			if (subYear == 0) {
				if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
					return true;
			}
			// 例子:cal1是"2005-1-1"，cal2是"2004-12-25"
			// java对"2004-12-25"处理成第52周
			// "2004-12-26"它处理成了第1周，和"2005-1-1"相同了
			// 大家可以查一下自己的日历
			// 处理的比较好
			// 说明:java的一月用"0"标识，那么12月用"11"
			else if (subYear == 1 && cal2.get(Calendar.MONTH) == 11) {
				if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
					bool = true;

			}
			// 例子:cal1是"2004-12-31"，cal2是"2005-1-1"
			else if (subYear == -1 && cal1.get(Calendar.MONTH) == 11) {
				if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
					bool = true;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bool;
	}

	/**
	 * 获取今天是星期几 星期天=0...星期六=6
	 *
	 * @param dateStr
	 *            yyyy-MM-dd
	 * @return
	 */
	public static int todayIsWeek(String dateStr) {
		int week = 0;
		try {
			Date date = strToDate(dateStr, DateUtil.DATE_FORMAT_YYYY_MM_DD);
			if (date == null)
				date = new Date();
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			week = c.get(c.DAY_OF_WEEK) - 1;
		} catch (Exception e) {
			e.printStackTrace();
			;
		}
		return week;
	}

	/**
	 * 根据日期获取周几
	 * 
	 * @param dateStr
	 *            yyyy-MM-dd
	 * @return String
	 * @创建作者:周玲斌
	 * @创建时间:2016年1月8日 下午2:09:49
	 */
	public static int getWeekByNum(String dateStr) {
		int week = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(dateStr);
			int[] weekDaysCode = { 7, 1, 2, 3, 4, 5, 6 };
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
			week = weekDaysCode[intWeek];
		} catch (Exception e) {
			e.printStackTrace();
		}
		return week;
	}

	/**
	 * 根据日期获取星期几
	 * 
	 * @param dateStr
	 *            yyyy-MM-dd
	 * @return String
	 * @创建作者:周玲斌
	 * @创建时间:2016年1月8日 下午2:13:33
	 */
	public static String getWeek(String dateStr) {
		String str = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(dateStr);
			String[] weeks = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
			if (week_index < 0) {
				week_index = 0;
			}
			str = weeks[week_index];
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * 获取一段时间内的所有日期
	 * 
	 * @param dateStr
	 * @param beforeDays
	 * @return List<String>
	 * @创建作者:周玲斌
	 * @创建时间:2016年1月8日 下午2:21:12
	 */
	public static List<String> getDatePeriod(String dateStr, int beforeDays) {
		List<String> datePeriodList = new ArrayList<String>();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(dateStr);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			int inputDayOfYear = cal.get(Calendar.DAY_OF_YEAR);
			for (int i = beforeDays - 1; i >= 0; i--) {
				cal.set(Calendar.DAY_OF_YEAR, inputDayOfYear - i);
				datePeriodList.add(dateFormat.format(cal.getTime()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return datePeriodList;
	}

	/**
	 * 获取时间段内的每一天
	 * 
	 * @param begDate
	 * @param endDate
	 * @return List<String>
	 * @创建作者:周玲斌
	 * @创建时间:2016年1月8日 下午2:24:28
	 */
	public static List<String> getEveryDay(String begDate, String endDate) {
		List<String> datePeriodList = new ArrayList<String>();

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		try {
			Date dateOne = dateFormat.parse(begDate);
			Date dateTwo = dateFormat.parse(nextDay(endDate, 1));

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(dateOne);

			while (calendar.getTime().before(dateTwo)) {
				datePeriodList.add(dateFormat.format(calendar.getTime()));
				calendar.add(Calendar.DAY_OF_MONTH, 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return datePeriodList;
	}

	/**
	 * 计算年龄
	 * 
	 * @param birth
	 *            yyyy-MM-dd
	 * @param end
	 *            结束日期
	 * @return
	 * @Author 周玲斌
	 * @version: 2017年5月2日 上午11:28:35
	 */
	public static int calcAge(String birth, String end) {
		int age = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date birthDate = sdf.parse(birth);
			Date nowDate = sdf.parse(end);
			// Date nowDate= new Date();
			Calendar flightCal = Calendar.getInstance();
			flightCal.setTime(nowDate);
			Calendar birthCal = Calendar.getInstance();
			birthCal.setTime(birthDate);

			int y = flightCal.get(Calendar.YEAR) - birthCal.get(Calendar.YEAR);
			int m = flightCal.get(Calendar.MONTH) - birthCal.get(Calendar.MONTH);
			int d = flightCal.get(Calendar.DATE) - birthCal.get(Calendar.DATE);

			if (m < 0) {
				y--;
			}
			if (m == 0 && d < 0) {
				y--;
			}
			// if(y<0){
			// throw new RuntimeException("您老还没出生");
			// }
			age = y;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return age;
	}

	/**
	 * 根据身份证号码获取出生日期
	 * 
	 * @param idCode
	 *            身份证号码
	 * @return
	 * @Author 周玲斌
	 * @version: 2017年5月2日 上午11:29:08
	 */
	public static String getBirthDate(String idCode) {
		String birthDate = "";
		try {
			int leh = idCode.length();
			if (leh != 18) {
				System.out.println("身份证号码的格式不正确");
			} else {
				birthDate = idCode.substring(6, 10) + "-" + idCode.substring(10, 12) + "-" + idCode.substring(12, 14);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return birthDate;
	}

	/**
	 * 根据身份证号码获取性别
	 * 
	 * @param idCode
	 *            身份证号码
	 * @return
	 * @Author 周玲斌
	 * @version: 2017年5月2日 上午11:29:46
	 */
	/*
	 * public static int getSex(String idCode) { int sex = 0; try { int leh =
	 * idCode.length(); if (leh != 18) { System.out.println("身份证号码的格式不正确"); } else {
	 * // 根据身份证号码识别性别 String str = idCode.substring(idCode.length() - 2,
	 * idCode.length() - 1); if (!StringUtils.isEmpty(str)) { int m =
	 * Integer.parseInt(str); if (m % 2 == 0) {// 女性 sex = 1902; } else {// 男性 sex =
	 * 1901; } } } } catch (Exception e) { e.printStackTrace(); } return sex; }
	 */
	
	/**
	 * 根据传入的日期获取所在月份所有日期
	 * 
	 * @param strDate yyyy-MM-dd
	 * @return
	 * @Author 周玲斌
	 * @version: 2017年7月13日 下午5:01:05
	 */
	public static List<String> getAllDaysMonth(String strDate) {
		List<String> lst = new ArrayList<String>();
		Date d = strToDate(strDate, DateUtil.DATE_FORMAT_YYYY_MM_DD);
		Date date = getMonthStart(d);
		Date monthEnd = getMonthEnd(d);
		while (!date.after(monthEnd)) {
			lst.add(dateFormat.format(date));
			date = getNext(date);
		}
		return lst;
	}

	/**
	 * 获取当前日期所属月份的第一天
	 * 
	 * @param date
	 * @return
	 * @Author 周玲斌
	 * @version: 2017年7月13日 下午4:56:31
	 */
	private static Date getMonthStart(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int index = calendar.get(Calendar.DAY_OF_MONTH);
		calendar.add(Calendar.DATE, (1 - index));
		return calendar.getTime();
	}

	/**
	 * 获取当前日期所属月份的最后一天
	 * 
	 * @param date
	 * @return
	 * @Author 周玲斌
	 * @version: 2017年7月13日 下午4:57:02
	 */
	private static Date getMonthEnd(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		int index = calendar.get(Calendar.DAY_OF_MONTH);
		calendar.add(Calendar.DATE, (-index));
		return calendar.getTime();
	}

	/**
	 * 获取一个月的最后一天
	 * @param monthStr yyyy-MM
	 * @return  yyyy-MM-dd
	 * @Author 周玲斌
	 * @version: 2017年7月15日 上午9:13:00
	 */
	public static String getLastDate(String monthStr){
		String lastDate="";
		try {
			Calendar calendar=new GregorianCalendar();  
	        SimpleDateFormat mf=new SimpleDateFormat(DateUtil.DATE_FORMAT_YYYY_MM);  
	        Date date=mf.parse(monthStr);  
	        calendar.setTime(date);  
	        calendar.roll(calendar.DATE, -1);//api解释roll()：向指定日历字段添加指定（有符号的）时间量，不更改更大的字段  
	        lastDate=dateFormat.format(calendar.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
        return lastDate;
	}
	
	/**
	 * 获取当前日期的下一天
	 * 
	 * @param date
	 * @return
	 * @Author 周玲斌
	 * @version: 2017年7月13日 下午4:57:16
	 */
	private static Date getNext(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, 1);
		return calendar.getTime();
	}

	/**
	 * 根据日期来获取一周的第一天
	 * @param strDate
	 * @return 
	 * @Author 周玲斌
	 * @version: 2017年7月13日 下午5:06:18
	 */
	public static String getWeekStartDay(String strDate) {
		Date d = strToDate(strDate, DateUtil.DATE_FORMAT_YYYY_MM_DD);
		Calendar c = Calendar.getInstance();
		List<String> lst = new ArrayList<String>();
		c.setTime(d);
		setToFirstDay(c);
		for (int i = 0; i < 7; i++) {
			String day = printDay(c);
			lst.add(day);
			c.add(Calendar.DATE, 1);
		}
		return lst.get(0);
	}

	/**
	 * 根据日期来获取一周的最后一天
	 * @param strDate
	 * @return 
	 * @Author 周玲斌
	 * @version: 2017年7月13日 下午5:07:01
	 */
	public static String getWeekEndtDay(String strDate) {
		Date d = strToDate(strDate, DateUtil.DATE_FORMAT_YYYY_MM_DD);
		Calendar c = Calendar.getInstance();
		List<String> lst = new ArrayList<String>();
		c.setTime(d);
		setToFirstDay(c);
		for (int i = 0; i < 7; i++) {
			String day = printDay(c);
			lst.add(day);
			c.add(Calendar.DATE, 1);
		}
		return lst.get(6);
	}

	/**
	 * 根据日期来获取其所在周的每一天
	 * @param strDate
	 * @return 
	 * @Author 周玲斌
	 * @version: 2017年7月13日 下午5:07:31
	 */
	public static List<String> getAllweekDays(String strDate) {
		Date d = strToDate(strDate, DateUtil.DATE_FORMAT_YYYY_MM_DD);
		Calendar c = Calendar.getInstance();
		List<String> lst = new ArrayList<String>();
		c.setTime(d);
		setToFirstDay(c);
		for (int i = 0; i < 7; i++) {
			String day = printDay(c);
			lst.add(day);
			c.add(Calendar.DATE, 1);
		}
		return lst;
	}

	/**
	 * 获取每一周的第一天
	 * @param calendar 
	 * @Author 周玲斌
	 * @version: 2017年7月13日 下午5:07:56
	 */
	private static void setToFirstDay(Calendar calendar) {
		while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
			calendar.add(Calendar.DATE, -1);
		}
	}

	/**
	 * 打印日期
	 * @param calendar
	 * @return 
	 * @Author 周玲斌
	 * @version: 2017年7月13日 下午5:08:09
	 */
	private static String printDay(Calendar calendar) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(calendar.getTime());
	}

	public static void main(String[] a) {
		// System.out.println(countMonths("2017-01-15", getDate(),
		// DATE_FORMAT_YYYY_MM_DD));

//		List<String> all = getAllDaysMonth(DateUtil.getNow());
//		for (String string : all) {
//			System.out.println(string);
//		}
		
//		System.out.println(getLastDate("2017-02"));
		
		// 当前日期前10天的日期
		 List<String> dateList= getEveryDay("2017-08-01", getDate());
		 for(String date:dateList){
		 System.out.println(date);
		 }

		// long time=getDateLong(DateUtil.getNow(),"2016-07-07 10:10:00",3);
		// System.out.println(time+"");

		// String time = "20:32:22";
		// String date = DateUtil.dateTimeToStr(new Date(),
		// DateUtil.DATE_FORMAT_YYYY_MM_DD);
		// long initialDelay = DateUtil.getDateLong(date+" "+time, null,
		// 3)*60;//当天是否需要执行
		// if(initialDelay < 0){//指定执行时间在当天中已过去
		// //求出离第二天次执行的时间间隔
		// initialDelay = DateUtil.getDateLong(DateUtil.dateTimeToStr(new
		// Date(), DateUtil.DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS),
		// DateUtil.nextDate(date+" "+time,1), 1);
		// }
		// long period = 24*60*60;//间隔
		// System.out.println((initialDelay));
		// System.out.println((DateUtil.todayIsWeek(DateUtil.strToDate("2012-07-16",
		// DateUtil.DATE_FORMAT_YYYY_MM_DD))));
		// System.out.println((DateUtil.todayIsWeek(DateUtil.strToDate("2012-07-20",
		// DateUtil.DATE_FORMAT_YYYY_MM_DD))));
		// System.out.println((DateUtil.todayIsWeek(DateUtil.strToDate("2012-07-22",
		// DateUtil.DATE_FORMAT_YYYY_MM_DD))));

		// System.out.println(DateUtil.nextDate(DateUtil.dateTimeToStr(new
		// Date(), DateUtil.DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS),
		// 10,0,2));
		// Date s = DateUtil.strToDate("2012-07-20 08:30:00",
		// DateUtil.DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS);
		// Date e = DateUtil.strToDate("2012-07-20 18:00:00",
		// DateUtil.DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS);
		// Date t = DateUtil.strToDate("2012-07-20 18:00:00",
		// DateUtil.DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS);
		// long l = System.currentTimeMillis();
		// System.out.println(System.currentTimeMillis());
		// if(DateUtil.cmpDate(s, e, t)){
		// System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~");
		// }
		// System.out.println(System.currentTimeMillis()-l);
	}

}
