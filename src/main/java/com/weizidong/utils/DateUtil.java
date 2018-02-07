package com.weizidong.utils;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * 日期操作工具类
 * 
 * @author WeiZiDong
 *
 */
public class DateUtil {

	/** 格式: yyyy-MM-dd HH:mm:ss **/
	public static final String P_TIMESTAMP = "yyyy-MM-dd HH:mm:ss";

	/** 格式: yyyy-MM-dd **/
	public static final String P_DATE = "yyyy-MM-dd";

	/** 格式: yyyyMMdd **/
	public static final String PDATE = "yyyyMMdd";

	/** 格式: yyyyMMdd **/
	public static final String PDATE2 = "yyyy/MM/dd";

	/** 格式: yyyyMMddHHmmss **/
	public static final String PTIMESTAMP = "yyyyMMddHHmmss";

	/** 格式: yyyyMMddHHmm **/
	public static final String PDATETIME = "yyyyMMddHHmm";

	/** 格式: yyyy-MM-dd HH:mm **/
	public static final String P_DATETIME = "yyyy-MM-dd HH:mm";
	/** 格式: HH:mm **/
	public static final String P_TIME = "HH:mm";

	public static javax.xml.datatype.XMLGregorianCalendar getXMLGregorianCalendarByDate(Date date) throws Exception {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		javax.xml.datatype.DatatypeFactory dtf = javax.xml.datatype.DatatypeFactory.newInstance();
		return dtf.newXMLGregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1,
				calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE),
				calendar.get(Calendar.SECOND), calendar.get(Calendar.MILLISECOND),
				calendar.get(Calendar.ZONE_OFFSET) / (1000 * 60));

	}

	public static javax.xml.datatype.XMLGregorianCalendar getXMLGregorianCalendarByString(String stringdate)
			throws Exception {

		java.util.Date date = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
		date = simpleDateFormat.parse(stringdate);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		javax.xml.datatype.DatatypeFactory dtf = javax.xml.datatype.DatatypeFactory.newInstance();
		return dtf.newXMLGregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1,
				calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE),
				calendar.get(Calendar.SECOND), calendar.get(Calendar.MILLISECOND),
				calendar.get(Calendar.ZONE_OFFSET) / (1000 * 60));

	}

	public static Calendar datetoCalendar(Date date) {

		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c;

	}

	/**
	 * 
	 * string-date 格式为yyyy-MM-dd
	 */
	public static Date stringTodate(String stringdate) {
		try {
			Date date = new SimpleDateFormat(P_DATE).parse(stringdate);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * string-date 格式为partten
	 */
	public static Date stringTodate(String stringdate, String partten) {
		try {
			DateFormat format = new SimpleDateFormat(partten);
			Date date = format.parse(stringdate);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * string-date 格式为sourcePartten,转换成targetPartter的字符串
	 */
	public static String stringToDateStr(String stringdate, String sourcePartten, String targetPartter) {
		try {
			Date date = stringTodate(stringdate, sourcePartten);
			return dateToString(date, targetPartter);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Date stringTodatetype(String stringdate) {
		try {
			Date date = new SimpleDateFormat(P_DATETIME).parse(stringdate);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Date stringToDateTime(String dateStr) {
		try {
			Date date = new SimpleDateFormat(P_DATETIME).parse(dateStr);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String dateToString(Date date) {

		SimpleDateFormat df = new SimpleDateFormat(P_DATE);

		try {
			String time = df.format(date);
			return time;
		} catch (Exception e) {
			return null;
		}
	}

	public static String dateToString(Date date, String partten) {

		SimpleDateFormat df = new SimpleDateFormat(partten);

		try {
			String time = df.format(date);
			return time;
		} catch (Exception e) {
			return null;
		}
	}

	public static String dateFormat(Date date) {

		SimpleDateFormat df = new SimpleDateFormat(P_TIMESTAMP);

		try {
			String time = df.format(date);
			return time;
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * 
	 * 计算两个时间之间的天数
	 */
	public static int getDaysBetween(Calendar d1, Calendar d2) {
		if (d1.after(d2)) {
			java.util.Calendar swap = d1;
			d1 = d2;
			d2 = swap;
		}
		int days = d2.get(Calendar.DAY_OF_YEAR) - d1.get(Calendar.DAY_OF_YEAR);
		int y2 = d2.get(Calendar.YEAR);
		if (d1.get(Calendar.YEAR) != y2) {
			d1 = (Calendar) d1.clone();
			do {
				days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);// 得到当年的实际天数
				d1.add(Calendar.YEAR, 1);
			} while (d1.get(Calendar.YEAR) != y2);
		}
		return days;
	}

	public static int getDaysBetween(Date date1, Date date2) {
		Calendar d1 = Calendar.getInstance();
		d1.setTime(date1);
		Calendar d2 = Calendar.getInstance();
		d2.setTime(date2);

		if (d1.after(d2)) {
			java.util.Calendar swap = d1;
			d1 = d2;
			d2 = swap;
		}
		int days = d2.get(Calendar.DAY_OF_YEAR) - d1.get(Calendar.DAY_OF_YEAR);
		int y2 = d2.get(Calendar.YEAR);
		if (d1.get(Calendar.YEAR) != y2) {
			d1 = (Calendar) d1.clone();
			do {
				days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);// 得到当年的实际天数
				d1.add(Calendar.YEAR, 1);
			} while (d1.get(Calendar.YEAR) != y2);
		}
		return days;
	}

	/**
	 * 
	 * 计算两个时间之间的天数差
	 */
	public static int getDaysBetweenPoor(Date date1, Date date2) {
		Calendar d1 = Calendar.getInstance();
		d1.setTime(date1);
		Calendar d2 = Calendar.getInstance();
		d2.setTime(date2);

		int days = d2.get(Calendar.DAY_OF_YEAR) - d1.get(Calendar.DAY_OF_YEAR);
		int y2 = d2.get(Calendar.YEAR);
		if (d1.get(Calendar.YEAR) != y2) {
			d1 = (Calendar) d1.clone();
			do {
				days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);// 得到当年的实际天数
				d1.add(Calendar.YEAR, 1);
			} while (d1.get(Calendar.YEAR) <= y2);
		}
		return days;
	}

	/**
	 * 计算某个时间之后的多少天
	 * 
	 */
	public static Date getAfterMonth(Date date, int months) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + months);
		return calendar.getTime();
	}

	/**
	 * 3. * 计算两个日期之间相差的月数 4. *
	 * 
	 * @param date1
	 *            5. *
	 * @param date2
	 *            6. *
	 * @return 7.
	 */
	public static int getMonths(Date date1, Date date2) {
		int iMonth = 0;
		int flag = 0;
		try {
			Calendar objCalendarDate1 = Calendar.getInstance();
			objCalendarDate1.setTime(date1);

			Calendar objCalendarDate2 = Calendar.getInstance();
			objCalendarDate2.setTime(date2);

			if (objCalendarDate2.equals(objCalendarDate1))
				return 0;
			if (objCalendarDate1.after(objCalendarDate2)) {
				Calendar temp = objCalendarDate1;
				objCalendarDate1 = objCalendarDate2;
				objCalendarDate2 = temp;
			}
			if (objCalendarDate2.get(Calendar.DAY_OF_MONTH) < objCalendarDate1.get(Calendar.DAY_OF_MONTH))
				flag = 1;

			if (objCalendarDate2.get(Calendar.YEAR) > objCalendarDate1.get(Calendar.YEAR))
				iMonth = ((objCalendarDate2.get(Calendar.YEAR) - objCalendarDate1.get(Calendar.YEAR)) * 12
						+ objCalendarDate2.get(Calendar.MONTH) - flag) - objCalendarDate1.get(Calendar.MONTH);
			else
				iMonth = objCalendarDate2.get(Calendar.MONTH) - objCalendarDate1.get(Calendar.MONTH) - flag;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return iMonth;
	}

	/**
	 * 计算某个时间之前的多少天
	 * 
	 */
	public static Date getBeforeDate(Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - days);
		return calendar.getTime();
	}

	/**
	 * 计算某个时间之前的的多少月的日期
	 * 
	 * @param date
	 * @param months
	 * @return
	 */
	public static Date getBeforeMonth(Date date, int months) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - months);
		return calendar.getTime();
	}

	/**
	 * 计算某个时间之后的多少天
	 * 
	 */
	public static Date getAfterDate(Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + days);
		return calendar.getTime();
	}

	/**
	 * 计算某一天在一年中的那一周
	 * 
	 */
	public static int getWeekNoForYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		int weekno = cal.get(Calendar.WEEK_OF_YEAR);
		return weekno;
	}

	/**
	 * 
	 * 获取某一天在某周中的开始日期
	 */
	public static Date getWeekOfDate(Date dt, int weekbegin) {
		Integer[] weekDays = { 0, 1, 2, 3, 4, 5, 6 };
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);

		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;

		if (w < 0)
			w = 0;

		int d = weekDays[w];
		if (weekbegin == 1) {
			if (d == 0) {
				dt = DateUtil.getBeforeDate(dt, 7);
			}
			if (d == 2) {
				dt = DateUtil.getBeforeDate(dt, 1);
			}
			if (d == 3) {
				dt = DateUtil.getBeforeDate(dt, 2);
			}
			if (d == 4) {
				dt = DateUtil.getBeforeDate(dt, 3);
			}
			if (d == 5) {
				dt = DateUtil.getBeforeDate(dt, 4);
			}
			if (d == 6) {
				dt = DateUtil.getBeforeDate(dt, 5);
			}
		} else {
			if (d == 1) {
				dt = DateUtil.getBeforeDate(dt, 1);
			}
			if (d == 2) {
				dt = DateUtil.getBeforeDate(dt, 2);
			}
			if (d == 3) {
				dt = DateUtil.getBeforeDate(dt, 3);
			}
			if (d == 4) {
				dt = DateUtil.getBeforeDate(dt, 4);
			}
			if (d == 5) {
				dt = DateUtil.getBeforeDate(dt, 5);
			}
			if (d == 6) {
				dt = DateUtil.getBeforeDate(dt, 6);
			}
		}

		return dt;

	}

	/**
	 * 获取当前日期是星期几<br>
	 */
	public static Integer getWeekOfDate(Date dt) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w <= 0)
			w = 7;
		return w;
	}

	/**
	 * 获取当前日期是星期几<br>
	 * 
	 * @param dt
	 * @return 当前日期是星期几
	 */
	public static String getWeekOfDate(Calendar cal) {
		String[] weekDays = { "日", "一", "二", "三", "四", "五", "六" };
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		return weekDays[w];
	}

	/**
	 * 
	 * 获取某一天在某月中的开始日期
	 */
	public static Date getMonthOfDate(Date dt) {
		Integer[] weekDays = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24,
				25, 26, 27, 28, 29, 30, 31 };
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);

		int w = cal.get(Calendar.DAY_OF_MONTH) - 1;
		if (w < 0)
			w = 0;

		int wi = weekDays[w];
		if (wi == 2) {
			dt = DateUtil.getAfterDate(dt, 1);
		}
		// if(wi==2){
		// dt = DateUtils.getAfterDate(dt, 1);
		// }
		if (wi == 3) {
			dt = DateUtil.getBeforeDate(dt, 2);
		}
		if (wi == 4) {
			dt = DateUtil.getBeforeDate(dt, 3);
		}
		if (wi == 5) {
			dt = DateUtil.getBeforeDate(dt, 4);
		}
		if (wi == 6) {
			dt = DateUtil.getBeforeDate(dt, 5);
		}
		if (wi == 7) {
			dt = DateUtil.getBeforeDate(dt, 6);
		}
		if (wi == 8) {
			dt = DateUtil.getBeforeDate(dt, 7);
		}
		if (wi == 9) {
			dt = DateUtil.getBeforeDate(dt, 8);
		}
		if (wi == 10) {
			dt = DateUtil.getBeforeDate(dt, 9);
		}
		if (wi == 11) {
			dt = DateUtil.getBeforeDate(dt, 10);
		}
		if (wi == 12) {
			dt = DateUtil.getBeforeDate(dt, 11);
		}
		if (wi == 13) {
			dt = DateUtil.getBeforeDate(dt, 12);
		}
		if (wi == 14) {
			dt = DateUtil.getBeforeDate(dt, 13);
		}
		if (wi == 15) {
			dt = DateUtil.getBeforeDate(dt, 14);
		}
		if (wi == 16) {
			dt = DateUtil.getBeforeDate(dt, 15);
		}
		if (wi == 17) {
			dt = DateUtil.getBeforeDate(dt, 16);
		}
		if (wi == 18) {
			dt = DateUtil.getBeforeDate(dt, 17);
		}
		if (wi == 19) {
			dt = DateUtil.getBeforeDate(dt, 18);
		}
		if (wi == 20) {
			dt = DateUtil.getBeforeDate(dt, 19);
		}
		if (wi == 21) {
			dt = DateUtil.getBeforeDate(dt, 20);
		}
		if (wi == 22) {
			dt = DateUtil.getBeforeDate(dt, 21);
		}
		if (wi == 23) {
			dt = DateUtil.getBeforeDate(dt, 22);
		}
		if (wi == 24) {
			dt = DateUtil.getBeforeDate(dt, 23);
		}
		if (wi == 25) {
			dt = DateUtil.getBeforeDate(dt, 24);
		}
		if (wi == 26) {
			dt = DateUtil.getBeforeDate(dt, 25);
		}
		if (wi == 27) {
			dt = DateUtil.getBeforeDate(dt, 26);
		}
		if (wi == 28) {
			dt = DateUtil.getBeforeDate(dt, 27);
		}
		if (wi == 29) {
			dt = DateUtil.getBeforeDate(dt, 28);
		}
		if (wi == 30) {
			dt = DateUtil.getBeforeDate(dt, 29);
		}
		if (wi == 31) {
			dt = DateUtil.getBeforeDate(dt, 30);
		}

		return dt;

	}

	/**
	 * 获取某一天在某季度中的第一天
	 */
	@SuppressWarnings("deprecation")
	public static Date getQuarterOfDate(Date dt) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, dt.getYear());
		cal.set(Calendar.MONTH, dt.getMonth());
		cal.set(Calendar.DAY_OF_MONTH, dt.getDay());
		int weekno = cal.get(Calendar.MONTH) + 1;
		if (weekno == 1 || weekno == 2 || weekno == 3) {
			dt = DateUtil.stringTodate(String.valueOf((dt.getYear() + 1900)) + "-01" + "-01");
		}
		if (weekno == 4 || weekno == 5 || weekno == 6) {
			dt = DateUtil.stringTodate(String.valueOf((dt.getYear() + 1900)) + "-04" + "-01");
		}
		if (weekno == 7 || weekno == 8 || weekno == 9) {
			dt = DateUtil.stringTodate(String.valueOf((dt.getYear() + 1900)) + "-07" + "-01");
		}
		if (weekno == 10 || weekno == 11 || weekno == 12) {
			dt = DateUtil.stringTodate(String.valueOf((dt.getYear() + 1900)) + "-10" + "-01");
		}

		return dt;
	}

	/**
	 * 计算2个日期月份差
	 * 
	 * @param begin
	 * @param end
	 * @return
	 * @throws Exception
	 */
	public static long judge(String date1, String date2) {
		Calendar cal = Calendar.getInstance();
		String[] s1 = date1.split("-");
		String year1 = s1[0];
		String month1 = s1[1];
		String day1 = s1[2];
		cal.set(Integer.parseInt(year1), Integer.parseInt(month1), Integer.parseInt(day1));
		long time1 = cal.getTimeInMillis();

		String[] s2 = date2.split("-");
		String year2 = s2[0];
		String month2 = s2[1];
		String day2 = s2[2];
		cal.set(Integer.parseInt(year2), Integer.parseInt(month2), Integer.parseInt(day2));
		long time2 = cal.getTimeInMillis();

		long distance = Math.abs((time1 - time2) / (1000 * 60 * 60 * 24 * 30));// 一个月按30天算

		if (distance == 0) {
			return 0;
		} else {
			return distance;
		}
	}

	/**
	 * 取出某个时间所在周的第一天的日期数据
	 * 
	 * @param date
	 *            某个时间
	 * @param firstDayOfWeek
	 *            一周从哪天开始,0:星期天，1：星期1。。。
	 * @return
	 */
	public static Date getFirstDayOfWeek(Date date, int firstDayOfWeek) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.setFirstDayOfWeek(firstDayOfWeek + 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return (cal.getTime());
	}

	/**
	 * 某周的最后一天时间
	 * 
	 * @param date
	 * @param firstDayOfWeek
	 *            0:星期天，1：星期1。。。
	 * @return
	 */
	public static Date getEndDayOfWeek(Date date, int firstDayOfWeek) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.setFirstDayOfWeek(firstDayOfWeek + 1);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return (cal.getTime());
	}

	public static String getSystemTime() {
		SimpleDateFormat theDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
		return theDate.format(new Date());
	}

	/**
	 * 取出某个时间所在的月份的第一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstDayOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return (cal.getTime());
	}

	/**
	 * 返回指定日期的月的最后一天
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static Date getLastDayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
		calendar.roll(Calendar.DATE, -1);
		return calendar.getTime();
	}

	/**
	 * 返回指定年月的月的最后一天
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static Date getLastDayOfMonth(Integer year, Integer month) {
		Calendar calendar = Calendar.getInstance();
		if (year == null) {
			year = calendar.get(Calendar.YEAR);
		}
		if (month == null) {
			month = calendar.get(Calendar.MONTH);
		}
		calendar.set(year, month, 1);
		calendar.roll(Calendar.DATE, -1);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}

	/**
	 * 取出某时间所在的季的第一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstDayOfQuarter(Date date) {
		date = getDayStartTime(date.getTime());
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int month = cal.get(Calendar.MONTH);
		int firstMonth;
		if (month <= 2) {
			firstMonth = 0;
		} else if (month > 2 && month <= 5) {
			firstMonth = 3;
		} else if (month > 5 && month <= 8) {
			firstMonth = 6;
		} else {
			firstMonth = 9;
		}

		cal.set(Calendar.MONTH, firstMonth);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return (cal.getTime());
	}

	public static String getEnglishDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return String.format(Locale.US, "%tb", date) + " " + cal.get(Calendar.DAY_OF_MONTH);
	}

	public static String getEnglishDate(Calendar cal) {
		return String.format(Locale.US, "%tb", cal.getTime()) + " " + cal.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 根据模板生成月、日、时、分字符串
	 * 
	 * @param date
	 * @param template
	 *            模板，如{0}月{1}日，{2}点{3}分
	 * @return
	 */
	public static String formatTemplateDate(Date date, String template) {
		String val = "";
		try {
			if (null == date) {
				date = new Date();
			}
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			val = MessageFormat.format(template, calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH),
					calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return val;
	}

	public static String formatDate(Date date, String pattern) {
		SimpleDateFormat theDate = new SimpleDateFormat(pattern);
		if (date != null) {
			return theDate.format(date);
		} else {
			return null;
		}
	}

	public static Calendar parseDate(String dateStr, String pattern) {
		DateFormat format1 = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			date = format1.parse(dateStr);
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			return c;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Date parseToDate(String dateStr, String pattern) {
		Date date = null;
		try {
			SimpleDateFormat format = new SimpleDateFormat(pattern);
			date = format.parse(dateStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 时间格式转换
	 * 
	 * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
	 */
	public static String getFormatDate(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(date);
		return dateString;
	}

	/**
	 * 返回指定日期的季的最后一天
	 * 
	 * @param year
	 * @param quarter
	 * @return
	 */
	public static Date getLastDayOfQuarter(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		return getLastDayOfQuarter(cal.get(Calendar.YEAR), getQuarterOfYear(date));
	}

	/**
	 * 返回指定年季的季的最后一天
	 * 
	 * @param year
	 * @param quarter
	 * @return
	 */
	public static Date getLastDayOfQuarter(Integer year, Integer quarter) {
		Calendar calendar = Calendar.getInstance();
		Integer month = new Integer(0);
		if (quarter == 1) {
			month = 3 - 1;
		} else if (quarter == 2) {
			month = 6 - 1;
		} else if (quarter == 3) {
			month = 9 - 1;
		} else if (quarter == 4) {
			month = 12 - 1;
		} else {
			month = calendar.get(Calendar.MONTH);
		}
		return getLastDayOfMonth(year, month);
	}

	/**
	 * 返回指定日期的季度
	 * 
	 * @param date
	 * @return
	 */
	public static int getQuarterOfYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) / 3 + 1;
	}

	/**
	 * 计算年龄
	 * 
	 * @param birthDay
	 * @return
	 */
	public static int getAge(Date birthDay) {
		Date currentDate = new Date();
		if (currentDate.after(birthDay)) {
			Calendar birth = Calendar.getInstance();
			birth.setTime(birthDay);
			Calendar cal = Calendar.getInstance();
			cal.setTime(currentDate);
			cal.set(Calendar.MONTH, birth.get(Calendar.MONTH));
			cal.set(Calendar.DAY_OF_MONTH, birth.get(Calendar.DAY_OF_MONTH));
			int birthYear = birth.get(Calendar.YEAR);
			int currentYear = cal.get(Calendar.YEAR);
			int age = currentYear - birthYear;
			if (currentDate.before(cal.getTime())) {
				age = age - 1;
			}
			return (age);
		} else {
			Calendar birth = Calendar.getInstance();
			birth.setTime(birthDay);
			Calendar cal = Calendar.getInstance();
			cal.setTime(currentDate);
			cal.set(Calendar.MONTH, birth.get(Calendar.MONTH));
			cal.set(Calendar.DAY_OF_MONTH, birth.get(Calendar.DAY_OF_MONTH));
			int birthYear = birth.get(Calendar.YEAR);
			int currentYear = cal.get(Calendar.YEAR);
			int age = currentYear - birthYear;
			if (currentDate.after(cal.getTime())) {
				age = age + 1;
			}
			return (age);
		}
	}

	/**
	 * 获得本天的开始时间，即2012-01-01 00:00:00
	 * 
	 * @return
	 */
	public static Date getCurrentDayStartTime() {
		Date now = new Date();
		SimpleDateFormat shortSdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			String str = shortSdf.format(now);
			now = shortSdf.parse(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return now;
	}

	/**
	 * 获得本天的结束时间，即2012-01-01 23:59:59
	 * 
	 * @return
	 */

	public static Date getCurrentDayEndTime() {
		Date now = new Date();
		SimpleDateFormat shortSdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat longSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			String str = shortSdf.format(now) + " 23:59:59";
			now = longSdf.parse(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return now;
	}

	/**
	 * 获得某天的开始时间，即2012-01-01 00:00:00
	 * 
	 * @return
	 */
	public static Date getDayStartTime(long startTime) {
		Date now = new Date(startTime);
		SimpleDateFormat shortSdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			now = shortSdf.parse(shortSdf.format(now));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return now;
	}

	/**
	 * 获得某天的结束时间，即2012-01-01 23:59:59
	 * 
	 * @return
	 */
	public static Date getDayEndTime(long endTime) {
		Date now = new Date(endTime);
		SimpleDateFormat shortSdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat longSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			now = longSdf.parse(shortSdf.format(now) + " 23:59:59");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return now;
	}

	/**
	 * 计算某年某月的月份天数
	 * 
	 * @param date
	 * @return
	 */
	public static int getCurMonthDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int day = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		return day;
	}

	public static Date getBeginDate(Date date1) {
		Calendar cal = Calendar.getInstance();

		cal.setTime(date1);

		cal.set(11, cal.getActualMinimum(11));

		cal.set(12, cal.getActualMinimum(12));

		cal.set(13, cal.getActualMinimum(13));

		cal.set(14, cal.getActualMinimum(14));

		return cal.getTime();
	}

	public static Date getEndDate(Date date1) {
		Calendar cal = Calendar.getInstance();

		cal.setTime(date1);

		cal.set(11, cal.getActualMaximum(11));

		cal.set(12, cal.getActualMaximum(12));

		cal.set(13, cal.getActualMaximum(13));

		cal.set(14, cal.getActualMaximum(14));

		return cal.getTime();
	}

	public static Date getFirstDateOfWeek(Date date, int firstDayOfWeek) {
		Calendar cal = Calendar.getInstance();

		cal.setTime(date);

		cal.setFirstDayOfWeek(firstDayOfWeek);

		cal.set(7, firstDayOfWeek);

		return getBeginDate(cal.getTime());
	}

	public static Date getLastDateOfWeek(Date date, int firstDayOfWeek) {
		Calendar cal = Calendar.getInstance();

		cal.setTime(date);

		cal.setFirstDayOfWeek(firstDayOfWeek);
		if (firstDayOfWeek == 1) {
			cal.set(7, 7);
		} else {
			cal.set(7, 1);
		}
		return getEndDate(cal.getTime());
	}

	/**
	 * 
	 * @param created
	 *            时间戳
	 * @param created2
	 *            时间戳
	 * @return
	 */
	public static long getMinuteBetween(long created, long created2) {
		long differ = Math.abs(created - created2);
		long differMinute = differ / (1000 * 60);
		return differMinute;
	}

	public static int getYear(Date date) {
		Calendar cal = new GregorianCalendar();
		if (date != null) {
			cal.setTime(date);
		}
		int year = cal.get(Calendar.YEAR);
		return year;
	}

	public static int getMonth(Date date) {
		Calendar cal = new GregorianCalendar();
		if (date != null) {
			cal.setTime(date);
		}
		int month = cal.get(Calendar.MONTH) + 1;
		return month;
	}

	public static int getDay(Date date) {
		Calendar cal = new GregorianCalendar();
		if (date != null) {
			cal.setTime(date);
		}
		int day = cal.get(Calendar.DAY_OF_MONTH);
		return day;
	}

	/**
	 * 前一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getPreviousDay(Date date) {
		Date result = null;

		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			int curMonthday = calendar.get(Calendar.DAY_OF_MONTH);
			curMonthday = curMonthday - 1;
			calendar.set(Calendar.DAY_OF_MONTH, curMonthday);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);

			result = calendar.getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 前X天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getPreviousDay(Date date, int X) {
		Date result = null;

		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			int curMonthday = calendar.get(Calendar.DAY_OF_MONTH);
			curMonthday = curMonthday - X;
			calendar.set(Calendar.DAY_OF_MONTH, curMonthday);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);

			result = calendar.getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 下一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getNextDay(Date date) {
		Date result = null;

		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			int curMonthday = calendar.get(Calendar.DAY_OF_MONTH);
			curMonthday = curMonthday + 1;
			calendar.set(Calendar.DAY_OF_MONTH, curMonthday);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);

			result = calendar.getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 获取所在周的星期一
	 * 
	 * @param date
	 * @return
	 */
	public static Date getMonday(Date date) {
		Date result = null;

		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			int curWeekday = calendar.get(Calendar.DAY_OF_WEEK) - 1;
			if (curWeekday == 0) {
				curWeekday = 7;
			}
			int curMonthday = calendar.get(Calendar.DAY_OF_MONTH);
			calendar.set(Calendar.DAY_OF_MONTH, curMonthday - curWeekday + 1);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);

			result = calendar.getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 获取所在周的星期天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getSunday(Date date) {
		Date result = null;

		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			int curWeekday = calendar.get(Calendar.DAY_OF_WEEK) - 1;
			if (curWeekday == 0) {
				curWeekday = 7;
			}
			int curMonthday = calendar.get(Calendar.DAY_OF_MONTH);
			calendar.set(Calendar.DAY_OF_MONTH, curMonthday + 7 - curWeekday);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);

			result = calendar.getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 计算两个日期之间相差的天数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int daysBetween(Date date1, Date date2) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date1);

		long time1 = cal.getTimeInMillis();
		cal.setTime(date2);

		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 判断给定的时间是否是在今天以前
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isBeforeToday(Date date) {

		Calendar current = Calendar.getInstance();
		current.setTime(date);

		Calendar today = Calendar.getInstance(); // 今天

		today.set(Calendar.HOUR_OF_DAY, 0);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);

		if (current.before(today)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 计算两个时间相差的秒数
	 */
	public static int calLastedTime(Date endDate,Date startDate) {
		long a = endDate.getTime();
		long b = startDate.getTime();
		int c = (int) ((a - b) / 1000);
		return c;
	}
	
	/**
	 * 上月最后一天
	 */
	public static String getlastDayofMonth(){
	    String lastDayofMonth;
	    Calendar calendar = Calendar.getInstance();
	    calendar.set(Calendar.DAY_OF_MONTH, 1); 
	    calendar.add(Calendar.DATE, -1);
	    lastDayofMonth = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
	    return lastDayofMonth+" 23:59:59";
	}
	/**
	 * 上月第一天
	 */
	public static String getfirstDayofMonth(){
	    String firstDayofMonth;
	    Calendar calendar = Calendar.getInstance();
	    calendar.add(Calendar.MONTH, -1);
	    calendar.set(Calendar.DAY_OF_MONTH, 1);
	    firstDayofMonth = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
	    return firstDayofMonth+" 00:00:00";
	}
	/**
	 * 上周最后一天
	 */
	public static String getSunday() {
	    Calendar cal = Calendar.getInstance();
	    cal.setFirstDayOfWeek(Calendar.MONDAY);//将每周第一天设为星期一，默认是星期天
	    cal.add(Calendar.DATE, -1*7);
	    cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
	    String sunday = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
	    return sunday+" 23:59:59";
	}
	/**
	 * 上周第一天
	 */
	public static String getMonday() {
	    Calendar cal = Calendar.getInstance();
	    cal.setFirstDayOfWeek(Calendar.MONDAY);//将每周第一天设为星期一，默认是星期天
	    cal.add(Calendar.DATE, -1*7);
	    cal.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
	    String monday = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
	    return monday+" 00:00:00";
	}
}
