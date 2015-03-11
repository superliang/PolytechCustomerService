package com.jandar.polytech.customerservice.util;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.logging.Logger;

/**
 * Description: 与日期相关的函数
 */
public class DateUtil {

	static Logger logger = Logger.getLogger(DateUtil.class.getName());

	private static String arrWeekdays[] = { "星期日", "星期一", "星期二", "星期三", "星期四",
			"星期五", "星期六" };

	/**
	 * 构造函数
	 * 
	 */
	public DateUtil() {

	}

	/**
	 * <BR>
	 * 说明：返回与指定日期对应参数的值 <BR>
	 * 实例：getDatePart(new Date(),"y"); <BR>
	 * 参数列表：
	 * 
	 * <pre>
	 * 参数	说明
	 * y	年
	 * m	月
	 * d	日
	 * dm	一月中的第几天
	 * dy	一年中的第几天
	 * h	时
	 * M	分
	 * s	秒
	 * S	毫秒
	 * w	星期
	 * wim	一个月中的周数(相对第一天)week_of_in_month
	 * wm	一个月中的第几周(实际周数)week_of_month
	 * wy	一年中的第几周week_of_year
	 * </pre>
	 * 
	 * @param dateValue
	 * @param interval
	 * @return int
	 */
	public static int getDatePart(Date dateValue, String interval) {

		Calendar c = Calendar.getInstance();
		c.setTime(dateValue);

		if (interval.equalsIgnoreCase("y"))
			return c.get(Calendar.YEAR);
		if (interval.equals("M"))
			return c.get(Calendar.MONTH);
		if (interval.equalsIgnoreCase("d"))
			return c.get(Calendar.DATE);
		if (interval.equalsIgnoreCase("dm"))
			return c.get(Calendar.DAY_OF_MONTH);
		if (interval.equalsIgnoreCase("dy"))
			return c.get(Calendar.DAY_OF_YEAR);
		// 12小时时间
		if (interval.equals("h"))
			return c.get(Calendar.HOUR);
		// 24小时时间
		if (interval.equals("H"))
			return c.get(Calendar.HOUR_OF_DAY);
		if (interval.equals("m"))
			return c.get(Calendar.MINUTE);
		if (interval.equals("s"))
			return c.get(Calendar.SECOND);
		if (interval.equals("S"))
			return c.get(Calendar.MILLISECOND);
		if (interval.equalsIgnoreCase("w"))
			return c.get(Calendar.DAY_OF_WEEK);
		if (interval.equalsIgnoreCase("wim"))
			return c.get(Calendar.DAY_OF_WEEK_IN_MONTH);
		if (interval.equalsIgnoreCase("wm"))
			return c.get(Calendar.WEEK_OF_MONTH);
		if (interval.equalsIgnoreCase("wy"))
			return c.get(Calendar.WEEK_OF_YEAR);

		return -1;
	}

	/**
	 * <BR>
	 * 说明：返回与指定日期对应参数的值 <BR>
	 * 实例：getDatePart("2003-10-11", "yyyy-MM-dd", "M");
	 * 
	 * @param strDateValue
	 * @param pattern
	 * @param interval
	 * @return int
	 */
	public static int getDatePart(String strDateValue, String pattern,
			String interval) throws ParseException {
		if (strDateValue == null || strDateValue.equals("")) {
			strDateValue = "1900-01-01";
		}
		SimpleDateFormat sdfFormat = new SimpleDateFormat(pattern,Locale.getDefault());
		Date dateValue = sdfFormat.parse(strDateValue);
		return getDatePart(dateValue, interval);
	}

	/**
	 * <BR>
	 * 说明：返回与指定日期对应参数相差多少的日期 <BR>
	 * 实例：getDateAdd(new Date(),1,"y"); <BR>
	 * 参数列表：
	 * 
	 * <pre>
	 * 参数	说明
	 * y	年
	 * M	月
	 * d	日
	 * h	时
	 * m	分
	 * s	秒
	 * S	毫秒
	 * </pre>
	 * 
	 * @param dateValue
	 * @param intDiff
	 * @param interval
	 * @return Date
	 */
	public static Date getDateAdd(Date dateValue, int intDiff, String interval) {
		Calendar c = Calendar.getInstance();
		c.setTime(dateValue);

		if (interval.equalsIgnoreCase("y"))
			c.add(Calendar.YEAR, intDiff);
		if (interval.equals("M"))
			c.add(Calendar.MONTH, intDiff);
		if (interval.equalsIgnoreCase("d"))
			c.add(Calendar.DATE, intDiff);
		if (interval.equalsIgnoreCase("h"))
			c.add(Calendar.HOUR, intDiff);
		if (interval.equals("m"))
			c.add(Calendar.MINUTE, intDiff);
		if (interval.equals("s"))
			c.add(Calendar.SECOND, intDiff);
		if (interval.equals("S"))
			c.add(Calendar.MILLISECOND, intDiff);

		return c.getTime();
	}

	/**
	 * <BR>
	 * 说明：取得当前月份的前一个月 <BR>
	 * 实例：getLastMonth();
	 * 
	 * @return String
	 */
	public static String getPreviousMonth() {
		return getAmountMonth(new Date(), -1);
	}

	/**
	 * <BR>
	 * 说明：取得当前月份的前一个月 <BR>
	 * 实例：getLastMonth();
	 * 
	 * @return String
	 */
	public static String getNextMonth() {
		return getAmountMonth(new Date(), 1);
	}

	/**
	 * <BR>
	 * 说明：取得当前月份的amount月月份 如 amount=-1 为前一个月 amount=1 为下一个月 <BR>
	 * 实例：getLastMonth();
	 * 
	 * @return String
	 */
	public static String getAmountMonth(int amount) {
		return getAmountMonth(new Date(), amount);
	}

	/**
	 * <BR>
	 * 说明：取得指定月份的上月月份 <BR>
	 * 实例：getLastMonth(new Date());
	 * 
	 * @param dateValue
	 * @return String
	 */
	public static String getAmountMonth(Date dateValue, int amount) {

		Calendar c = Calendar.getInstance();

		c.setTime(dateValue);
		c.add(Calendar.MONTH, amount);

		return new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault()).format(c.getTime());
	}

	/*
	 * <BR>说明：取得当前的日期 <BR>实例：getCurrentDate("yyyy-MM-dd");或者
	 * getCurrentDate("yyyy-MM-dd HH:mm:ss"); @param pattern 日期的格式，例如：yyyy-mm-dd
	 * 或者 yyyy-mm-dd HH:MM:ss @return String
	 */
	public static String getCurrentDate(String pattern) {
		return getFormatDateTime(new Date(), pattern);
	}

	/*
	 * <BR>说明：取得当天是星期几 <BR>实例：getCurrentWeekDay(); @return String
	 */
	public static String getCurrentWeekDay() {
		return arrWeekdays[getDatePart(new Date(), "w") - 1];
	}

	/*
	 * <BR>说明：取得指定日期是星期几 <BR>实例：getCurrentWeekDay(new Date()); @param Date
	 * dateValue @return String
	 */
	public static String getWeekDay(Date dateValue) {
		return arrWeekdays[getDatePart(dateValue, "w") - 1];
	}

	/*
	 * <BR>说明：取得指定日期是星期几 <BR>实例：getCurrentWeekDay(); @param String dateValue
	 * 
	 * @param String pattern @return String
	 */
	public static String getWeekDay(String dateValue, String pattern)
			throws ParseException {
		return arrWeekdays[getDatePart(dateValue, pattern, "w") - 1];
	}

	/**
	 * <BR>
	 * 说明：取得指定日期指定格式的日期字符串 <BR>
	 * 实例：getFormatDateTime(new Date(), "yyyy-MM-dd"); 或者 getFormatDateTime(new
	 * Date(), "yyyy-MM-dd HH:mm:ss");
	 * 
	 * @param dateValue
	 * @param strFormat
	 * @return String
	 */
	public static String getFormatDateTime(Date dateValue, String strFormat) {
		return new SimpleDateFormat(strFormat,Locale.getDefault()).format(dateValue);
	}

	/**
	 * <BR>
	 * 说明：将指定格式的日期字符串转换为另一种格式的字符串 <BR>
	 * 实例：getFormatDateTime("20021011", "yyyyMMdd", "yyyy-MM-dd");
	 * 
	 * @param strDateValue
	 * @param strFormat1
	 * @param strFormat2
	 * @return String
	 */
	public static String getFormatDateTime(String strDateValue,
			String strFormat1, String strFormat2) throws ParseException {
		if (strDateValue == null || strDateValue.equals("")) {
			strDateValue = "19000101";
		}
		return getFormatDateTime(
				new SimpleDateFormat(strFormat1,Locale.getDefault()).parse(strDateValue),
				strFormat2);
	}

	/**
	 * 将字符串转化为日期
	 * 
	 * @param strDate
	 * @return Date
	 * @throws ParseException
	 */
	public static Date changeStringToDate(String strDate) throws ParseException {
		if (strDate == null || strDate.equals("")) {
			return null;
		}

		return DateFormat.getDateInstance(DateFormat.MEDIUM).parse(strDate);
	}

	/**
	 * <BR>
	 * 说明：取得两个日期相差的天数 <BR>
	 * 实例：getDateDiff(date1, date2);
	 * 
	 * @param date1
	 * @param date2
	 * @return int
	 */
	public static int getDateDiff(Date date1, Date date2) {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(date1);
		Calendar c2 = Calendar.getInstance();
		c2.setTime(date2);
		Long lngDiff = ((c1.getTime().getTime() - c2.getTime().getTime()) / (24 * 3600 * 1000));
		return (lngDiff.intValue());
	}

	/**
	 * <BR>
	 * 说明：取得两个日期相差的毫秒数 <BR>
	 * 实例：getDateDiff(date1, date2);
	 * 
	 * @param date1
	 * @param date2
	 * @return long
	 */
	public static long getDateDiffLong(Date date1, Date date2) {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(date1);
		Calendar c2 = Calendar.getInstance();
		c2.setTime(date2);
		return (c1.getTime().getTime() - c2.getTime().getTime());
	}

	/**
	 * <BR>
	 * 说明：取得两个日期相差的天数 <BR>
	 * 实例：getDateDiff(new Date(),"2003-10-1","yyyy-MM-dd");
	 * 
	 * @param date1
	 * @param date2
	 * @param pattern
	 * @return int
	 */
	public static int getDateDiff(Date date1, String date2, String pattern)
			throws ParseException {
		if (date2 == null || date2.equals("")) {
			date2 = "1900/01/01";
		}
		SimpleDateFormat sdfFormat = new SimpleDateFormat(pattern,Locale.getDefault());
		return getDateDiff(date1, sdfFormat.parse(date2));
	}

	/**
	 * <BR>
	 * 说明：取得两个日期相差的天数 <BR>
	 * 实例：getDateDiff("2003-10-1", "2003-9-1", "yyyy-MM-dd");
	 * 
	 * @param date1
	 * @param date2
	 * @param pattern
	 * @return int
	 */
	public static int getDateDiff(String date1, String date2, String pattern)
			throws ParseException {
		if (date1 == null || date2 == null || date1.equals("")
				|| date2.equals("")) {
			date1 = "1900-01-01";
			date2 = "1900-01-01";
		}
		SimpleDateFormat sdfFormat = new SimpleDateFormat(pattern,Locale.getDefault());
		return getDateDiff(sdfFormat.parse(date1), sdfFormat.parse(date2));
	}

	/**
	 * <BR>
	 * 说明：取得两个日期相差的天数 <BR>
	 * 实例：getDateDiff("2003/10/1", "yyyy/MM/dd", "2003-09-01", "yyyy-MM-dd");
	 * 
	 * @param date1
	 * @param pattern1
	 * @param date2
	 * @param pattern2
	 * @return int
	 */
	public static int getDateDiff(String date1, String pattern1, String date2,
			String pattern2) throws ParseException {
		if (date1 == null || date2 == null || date1.equals("")
				|| date2.equals("")) {
			date1 = "1900/01/01";
			date2 = "1900/01/01";
		}
		SimpleDateFormat sdfFormat1 = new SimpleDateFormat(pattern1,Locale.getDefault());
		SimpleDateFormat sdfFormat2 = new SimpleDateFormat(pattern2,Locale.getDefault());
		return getDateDiff(sdfFormat1.parse(date1), sdfFormat2.parse(date2));
	}

	/**
	 * 说明：取得当前日子的前几天或后几天 实例：getDateAfer(10,"yyyy-MM-dd") 取得后十天的日期
	 * 
	 * @param inter
	 * @return String
	 */
	public static String getDateAfter(long inter, String format) {
		if (format == null || format.equals("")) {
			format = "1900-01-01";
		}
		Date d1 = new Date();
		d1.setTime(d1.getTime() + inter * 24 * 3600 * 1000);
		return getFormatDateTime(d1, format);
	}

	public static String getDateAfter(String date1, String date1format,
			long inter, String format) throws ParseException {
		SimpleDateFormat sdfFormat = new SimpleDateFormat(date1format,Locale.getDefault());
		Date dateValue = sdfFormat.parse(date1);
		dateValue.setTime(dateValue.getTime() + inter * 24 * 3600 * 1000);
		return getFormatDateTime(dateValue, format);
	}

	/**
	 * 说明：把 yyyy-MM-dd格式转化为 yyyyMMdd格式 实例：
	 */
	public static String getDateNoSpace(String data) {
		if (data == null || data.length() < 10)
			return "";
		data = data.substring(0, 4) + data.substring(5, 7)
				+ data.substring(8, 10);
		return data;
	}

	/**
	 * <BR>
	 * 说明：把从数据库中取得的日期形式转换成制定的日期形式 <BR>
	 * 实例：getDataFromDatabase("2005-09-07 15:41:30.000","Date")；
	 * 
	 * @param datetime
	 * @param model
	 *            ("DateTime" : return "yyyy-MM-dd HH:mm:ss"形式 "Date" : return
	 *            "yyyy-MM-dd" 形式)
	 * @return String (yyyy-MM-dd HH:mm:ss | yyyy-MM-dd)
	 */
	public static String getStdDateTime(String datetime, String model) {

		if (datetime == null || datetime.equals("")) {
			return "";
		}

		int position = datetime.indexOf(".");
		String dateTimeStr = "1900-01-01";
		String realDateTime;
		boolean more;

		if (position >= 0) {
			char[] dateArray = new char[19];

			datetime.getChars(0, position, dateArray, 0);
			realDateTime = new String(dateArray);
		} else {
			realDateTime = datetime;
		}

		more = realDateTime.regionMatches(true, 0, dateTimeStr, 0,
				dateTimeStr.length());

		if (more) {
			realDateTime = "";
		} else if (model.equalsIgnoreCase("Date")) {
			realDateTime = realDateTime.substring(0, 10);
		}

		return realDateTime;
	}

	/**
	 * <BR>
	 * 说明：从数据库中取得的任意日期形式 <BR>
	 * 实例：getFmtDateTime("%y-%m-%d %H:%m:%s","2005-01-01 10:01:01")； 参数 说明 y 两位年
	 * Y 四位年 m 月 d 日 H 时 M 分 s 秒
	 * 
	 * </pre>
	 * 
	 * @param pattern
	 * @param strDateValue
	 * @return String ("2005-01-01 10:01:01")
	 */
	public static String getFmtDateTime(String pattern, String strDateValue) {

		if (strDateValue == null || strDateValue.equals("")) {
			return "";
		}

		int dateTimeValue;
		String dateTime;

		SimpleDateFormat sdfFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.getDefault());

		try {
			Date dateValue = sdfFormat.parse(strDateValue);

			if (pattern.indexOf("Y") != -1) {
				pattern = pattern.replaceAll("%Y",
						String.valueOf(getDatePart(dateValue, "Y")));
			} else if (pattern.indexOf("y") != -1) {
				dateTimeValue = getDatePart(dateValue, "Y");
				dateTime = String.valueOf(dateTimeValue);
				pattern = pattern.replaceAll("%y", dateTime.substring(2, 4));
			}

			if (pattern.indexOf("M") != -1) {
				dateTimeValue = getDatePart(dateValue, "M") + 1;
				dateTime = String.valueOf(dateTimeValue);

				if (dateTimeValue < 10) {
					pattern = pattern.replaceAll("%M", "0".concat(dateTime));
				} else {
					pattern = pattern.replaceAll("%M", dateTime);
				}
			}

			if (pattern.indexOf("d") != -1) {
				dateTimeValue = getDatePart(dateValue, "d");
				dateTime = String.valueOf(dateTimeValue);

				if (dateTimeValue < 10) {
					pattern = pattern.replaceAll("%d", "0".concat(dateTime));
				} else {
					pattern = pattern.replaceAll("%d", dateTime);
				}
			}

			if (pattern.indexOf("H") != -1) {
				dateTimeValue = getDatePart(dateValue, "H");
				dateTime = String.valueOf(dateTimeValue);

				if (dateTimeValue < 10) {
					pattern = pattern.replaceAll("%H", "0".concat(dateTime));
				} else {
					pattern = pattern.replaceAll("%H", dateTime);
				}
			}

			if (pattern.indexOf("m") != -1) {
				dateTimeValue = getDatePart(dateValue, "m");
				dateTime = String.valueOf(dateTimeValue);

				if (dateTimeValue < 10) {
					pattern = pattern.replaceAll("%m", "0".concat(dateTime));
				} else {
					pattern = pattern.replaceAll("%m", dateTime);
				}
			}

			if (pattern.indexOf("s") != -1) {
				dateTimeValue = getDatePart(dateValue, "s");
				dateTime = String.valueOf(dateTimeValue);

				if (dateTimeValue < 10) {
					pattern = pattern.replaceAll("%s", "0".concat(dateTime));
				} else {
					pattern = pattern.replaceAll("%s", dateTime);
				}
			}

		} catch (ParseException pe) {
			logger.info("输入日期的字符不合格" + pe);
		}

		return pattern;
	}

	/**
	 * <BR>
	 * 说明：获取两个指定日期的差值 <BR>
	 * 实例：getDateDifference("2003-08-15 17:15:30", "2003-09-15 17:16:40")；
	 * 
	 * @param dateBegin
	 * @param dateEnd
	 * @return long
	 */
	public static long getDateDifference(String dateBegin, String dateEnd) {
		if (dateBegin == null || dateEnd == null || dateBegin.equals("")
				|| dateEnd.equals("")) {
			dateBegin = "1900-01-01 00:00:00";
			dateEnd = "1900-01-01 00:00:00";
		}
		long dateTimeDiff = 987654321;
		int dateBeginLen = dateBegin.length();
		int dateEndLen = dateEnd.length();
		long l2 = 0;
		long l1 = 0;
		SimpleDateFormat formatter;
		ParsePosition pos;
		Date date;

		try {
			if (dateBeginLen == 19 || dateBeginLen == 21) {
				formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.getDefault());
			} else if (dateBeginLen == 16) {
				formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm",Locale.getDefault());
			} else {
				return dateTimeDiff;
			}

			pos = new ParsePosition(0);
			date = formatter.parse(dateBegin, pos);
			l1 = date.getTime();
		} catch (NullPointerException npe) {

			logger.info("空指针异常" + npe);
		} catch (IllegalArgumentException iae) {
			logger.info("日期格式错误" + iae);
		}

		try {
			if (dateEndLen == 19 || dateEndLen == 21) {
				System.out.println("Test1");
				formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.getDefault());
			} else if (dateEndLen == 16) {
				formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm",Locale.getDefault());
			} else {
				return dateTimeDiff;
			}

			pos = new ParsePosition(0);
			date = formatter.parse(dateEnd, pos);
			l2 = date.getTime();
		} catch (NullPointerException npe) {
			logger.info("空指针异常" + npe);
		} catch (IllegalArgumentException iae) {
			logger.info("日期格式错误" + iae);
		}

		dateTimeDiff = (l2 - l1) / 1000;

		return dateTimeDiff;
	}

	/**
	 * <BR>
	 * 说明：获取两个指定日期的秒数差 <BR>
	 * 实例：getDateDifference("2003-08-15 17:15:30", "2003-09-15 17:16:40")；
	 * 
	 * @param dateBegin
	 * @param dateEnd
	 * @return long
	 */
	public static long getDateTimeDiffSec(String dateBegin, String dateEnd) {
		if (dateBegin == null || dateEnd == null || dateBegin.equals("")
				|| dateEnd.equals("")) {
			dateBegin = "1900-01-01 00:00:00";
			dateEnd = "1900-01-01 00:00:00";
		}
		return getDateDifference(dateBegin, dateEnd);
	}

	/**
	 * <BR>
	 * 说明：获取两个指定日期的分钟差 <BR>
	 * 实例：getDateDifference("2003-08-15 17:15:30", "2003-09-15 17:16:40")；
	 * 
	 * @param dateBegin
	 * @param dateEnd
	 * @return long
	 */
	public static long getDateTimeDifMin(String dateBegin, String dateEnd) {
		if (dateBegin == null || dateEnd == null || dateBegin.equals("")
				|| dateEnd.equals("")) {
			dateBegin = "1900-01-01 00:00:00";
			dateEnd = "1900-01-01 00:00:00";
		}
		return getDateDifference(dateBegin, dateEnd) / 60;
	}

	/**
	 * <BR>
	 * 说明：获取两个指定日期的小时差 <BR>
	 * 实例：getDateDifference("2003-08-15 17:15:30", "2003-09-15 17:16:40")；
	 * 
	 * @param dateBegin
	 * @param dateEnd
	 * @return long
	 */
	public static long getDateTimeDiffHor(String dateBegin, String dateEnd) {
		if (dateBegin == null || dateEnd == null || dateBegin.equals("")
				|| dateEnd.equals("")) {
			dateBegin = "1900-01-01 00:00:00";
			dateEnd = "1900-01-01 00:00:00";
		}
		return getDateDifference(dateBegin, dateEnd) / 3600;
	}

	/**
	 * 通过long型数据获取天-小时:分:秒 形式
	 * 
	 * @param l
	 * @return (1天10:10:10)
	 */
	private static String getDayHourMinSec(long l) {

		String hour;// 剩余小时数
		String minute;// 剩余分钟数
		String second;// 剩余秒数
		boolean sign = true;

		if (l < 0) {
			sign = false;// 记录该时间为负时间
			l = -1 * l;// 把负值转变为正值
		}

		long dayNum = l / 86400;// 天数
		long hourNum = l / 3600 % 24;// 小时数
		long minuteNum = l / 60 % 60;// 分钟数
		long secondNum = l % 60;// 秒数

		if (hourNum < 10) {
			hour = "0" + String.valueOf(hourNum);
		} else {
			hour = String.valueOf(hourNum);
		}

		if (minuteNum < 10) {
			minute = "0" + String.valueOf(minuteNum);
		} else {
			minute = String.valueOf(minuteNum);
		}

		if (secondNum < 10) {
			second = "0" + String.valueOf(secondNum);
		} else {
			second = String.valueOf(secondNum);
		}

		if (dayNum == 0 && sign) {
			return hour + ":" + minute + ":" + second;
		} else if (dayNum == 0 && !sign) {
			return "-" + hour + ":" + minute + ":" + second;
		} else if (sign) {
			return String.valueOf(dayNum) + "天" + hour + ":" + minute + ":"
					+ second;
		} else {
			return "-" + String.valueOf(dayNum) + "天" + hour + ":" + minute
					+ ":" + second;
		}
	}

	/**
	 * <BR>
	 * 说明：获取两个日期时间差 <BR>
	 * 实例：getDateTimeDiff1("2004-10-04 8:00:00", "2004-10-04 9:00:00")；
	 * 
	 * @param dateBegin
	 * @param dateEnd
	 * @return String (day天HH-mm-ss)
	 */
	public static String getDateTimeDiff1(String dateBegin, String dateEnd) {
		if (dateBegin == null || dateBegin.equals("") || dateEnd == null
				|| dateEnd.equals("")) {
			return "";
		} else {
			return getDayHourMinSec(getDateDifference(dateBegin, dateEnd));
		}
	}

	/**
	 * <BR>
	 * 说明：获取当前日期与指定日期的差值 <BR>
	 * 实例：getDateTimeDiff2("2004-10-04 8:00:00")；
	 * 
	 * @param dateTime
	 * @return String (day天HH-mm-ss)
	 */

	public static String getDateTimeDiff2(String dateTime) {
		return (dateTime == null || dateTime.equals("")) ? ""
				: getDayHourMinSec(getDateDifference(dateTime,
						getCurrentDate("yyyy-MM-dd HH:mm:ss")));
	}

	/**
	 * <BR>
	 * 说明：获取两个指定日期的差值 <BR>
	 * 实例：getDateDifference("2003-08-15 17:15:30", "2003-09-15 17:16:40", true)；
	 * 
	 * @param dateBegin
	 * @param dateEnd
	 * @param order
	 *            (两个时间相减的顺序:true->第二个时间-第一个时间 false第一个时间-第二个时间)
	 * @return long
	 */
	public static long getDateDifference(String dateBegin, String dateEnd,
			boolean order) {

		if (dateBegin == null || dateBegin.equals("") || dateEnd == null
				|| dateEnd.equals("")) {
			return 0;
		}

		long timeDifference = 0;

		try {
			SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss",Locale.getDefault());
			ParsePosition pos = new ParsePosition(0);
			ParsePosition pos1 = new ParsePosition(0);
			Date dt1 = formatter.parse(dateBegin, pos);
			Date dt2 = formatter.parse(dateEnd, pos1);

			timeDifference = order ? (dt2.getTime() - dt1.getTime()) / 1000
					: (dt1.getTime() - dt2.getTime()) / 1000;
		} catch (NullPointerException npe) {
			logger.info("出现空指针异常错误" + npe);
		} catch (IllegalArgumentException iae) {
			logger.info("参数格式错误" + iae);
		}

		return timeDifference;
	}

	/**
	 * <BR>
	 * 说明：通过指定日期和当前日期的差值返回指定类型的时间日期 <BR>
	 * 实例：getDateDifference("2003-08-15 17:15:30", true)；
	 * 
	 * @param dateBegin
	 * @param order
	 *            (两个时间相减的顺序: true->第二个时间-第一个时间 false第一个时间-第二个时间)
	 * @return String (day天HH-mm-ss)
	 */
	public static String getDateDifference(String dateBegin, boolean order) {

		if (dateBegin == null || dateBegin.equals("")) {
			return "";
		}

		String hour;// 剩余小时数
		String minute;// 剩余分钟数
		String second;// 剩余秒数
		boolean sign = true;
		long l = getDateDifference(dateBegin,
				getCurrentDate("yyyy-MM-dd HH:mm:ss"), order);

		if (l < 0) {
			sign = false;// 记录该时间为负时间
			l = -1 * l;// 把负值转变为正值
		}

		long dayNum = l / 86400;// 天数
		long hourNum = l / 3600 % 24;// 小时数
		long minuteNum = l / 60 % 60;// 分钟数
		long secondNum = l % 60;// 秒数

		if (hourNum < 10) {
			hour = "0" + String.valueOf(hourNum);
		} else {
			hour = String.valueOf(hourNum);
		}

		if (minuteNum < 10) {
			minute = "0" + String.valueOf(minuteNum);
		} else {
			minute = String.valueOf(minuteNum);
		}

		if (secondNum < 10) {
			second = "0" + String.valueOf(secondNum);
		} else {
			second = String.valueOf(secondNum);
		}

		if (dayNum == 0 && sign) {
			return hour + ":" + minute + ":" + second;
		} else if (dayNum == 0 && !sign) {
			return "-" + hour + ":" + minute + ":" + second;
		} else if (sign) {
			return String.valueOf(dayNum) + "天" + hour + ":" + minute + ":"
					+ second;
		} else {
			return "-" + String.valueOf(dayNum) + "天" + hour + ":" + minute
					+ ":" + second;
		}
	}

	/**
	 * <BR>
	 * 说明：获取下某几个月的日期 <BR>
	 * 实例：getSomeMonthDate(2, "2001-11-30");
	 * 
	 * @param monthNum
	 * @param date
	 * @return String (yyyy-MM-dd)
	 */
	public static String getSomeMonthDate(int monthNum, String date) {
		if (date == null || date.equals("")) {
			date = "1900-01-01";
		}
		DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
		Calendar c = Calendar.getInstance();

		try {
			c.setTime(dateFormat.parse(date));
		} catch (ParseException pe) {
			logger.info("输入日期的格式不合格" + pe);
		}

		c.add(Calendar.MONTH, monthNum);

		return dateFormat.format(c.getTime());
	}

	/**
	 * <BR>
	 * 说明：返回传入时间-当前时间+分钟数，如为负数，前面加'-' <BR>
	 * 实例：getTimeDiff1("2003-08-15 17:15:30", "15", false)；
	 * 
	 * @param strTime
	 * @param timeDiff
	 * @param order
	 *            (两个时间相减的顺序:true 第二个时间-第一个时间 false 第一个时间-第二个时间)
	 * @return long
	 */
	public static String getTimeDiff1(String strTime, long timeDiff,
			boolean order) {

		if (strTime == null || strTime.equals("")) {
			return "";
		}

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.getDefault());
		ParsePosition pos = new ParsePosition(0);
		Date dt = formatter.parse(strTime, pos);

		dt.setTime(dt.getTime() + timeDiff * 60 * 1000);

		return getDateDifference(getFormatDateTime(dt, "yyyy-MM-dd HH:mm:ss"),
				order);
	}

	/**
	 * <BR>
	 * 说明：返回当天24时-当前时间的差值 <BR>
	 * 实例：getTimeDiff2(false)；
	 * 
	 * @param order
	 *            (两个时间相减的顺序:true->第二个时间-第一个时间 false第一个时间-第二个时间)
	 * @return long
	 */

	public static String getTimeDiff2(boolean order) {

		String secondDayTime = getDateAfter(1, "yyyy-MM-dd") + " 00:00:00";

		return getDateDifference(secondDayTime, order).substring(1);
	}

	/**
	 * 通过给定的日期形式和日期类型取得与其对应的java.util.Date类型
	 * 
	 * @param strDateTime
	 *            日期形式
	 * @return Date
	 */
	public static Date getSpecifyDate(String strDateTime) {
		if (strDateTime == null || strDateTime.equals("")) {
			return null;
		}

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.getDefault());
		ParsePosition pos = new ParsePosition(0);

		return formatter.parse(strDateTime, pos);
	}

	/**
	 * <BR>
	 * 说明: 获取日期加上指定分钟数后的新日期 <BR>
	 * 实例：getTimeDiff3("2003-08-15 17:15:30", 15)；
	 * 
	 * @param strDateTime
	 * @param minute
	 * @return String
	 */
	public static String getTimeDiff3(String strDateTime, long minute) {
		if (strDateTime == null || strDateTime.equals("")) {
			strDateTime = "1900-01-01 00:00:00";
		}
		Date dt = getSpecifyDate(strDateTime);

		dt.setTime(dt.getTime() + minute * 60 * 1000);

		return getFormatDateTime(dt, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * <BR>
	 * 说明: 获取日期加上指定天数后的新日期 <BR>
	 * 实例：getTimeDiff3("2003-08-15 17:15:30", 15)；
	 * 
	 * @param strDateTime
	 * @param days
	 * @return String
	 */
	public static String getTimeDiff4(String strDateTime, int days) {
		if (strDateTime == null || strDateTime.equals("")) {
			strDateTime = "1900-01-01";
		}
		Date dt = getSpecifyDate(strDateTime);

		dt.setTime(dt.getTime() + days * 24 * 60 * 60 * 1000);

		return getFormatDateTime(dt, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * <BR>
	 * 说明: 获取日期加上指定小时数后的新日期 <BR>
	 * 实例：getTimeDiff5("2003-08-15 17:15:30", 15)；
	 * 
	 * @param strDateTime
	 * @param hours
	 * @return String
	 */
	public static String getTimeDiff5(String strDateTime, int hours) {
		if (strDateTime == null || strDateTime.equals("")) {
			strDateTime = "1900-01-01 00:00:00";
		}
		Date dt = getSpecifyDate(strDateTime);

		dt.setTime(dt.getTime() + hours * 60 * 60 * 1000);

		return getFormatDateTime(dt, "yyyy-MM-dd HH:mm:ss");
	}

	public static String getLastMonday(String pattern) {

		Calendar c = Calendar.getInstance();

		c.setTime(new Date());
		c.add(Calendar.DATE, (c.get(Calendar.DAY_OF_WEEK) + 5) % 7 * (-1));

		return getFormatDateTime(c.getTime(), pattern);
	}

	/**
	 * @param yearMonth
	 *            ="2010-02"
	 * @return
	 */
	public static int getMonthdays(String yearMonth) {
		int year = Integer.valueOf(yearMonth.substring(0, 4));
		int month = Integer.valueOf(yearMonth.substring(5, 7));
		int days = 0;
		if (month != 2) {
			switch (month) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				days = 31;
				break;
			case 4:
			case 6:
			case 9:
			case 11:
				days = 30;

			}
		} else {

			if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
				days = 29;
			else
				days = 28;

		}
		return days;
	}

	/**
	 * <BR>
	 * 说明：取得两个日期相差的毫秒数 <BR>
	 * 实例：getDateDiff(date1, date2);
	 * 
	 * @param date1
	 * @param date2
	 * @return long
	 */
	public static long getLongDateDiff(Date date1, Date date2) {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(date1);
		Calendar c2 = Calendar.getInstance();
		c2.setTime(date2);
		Long lngDiff = c1.getTime().getTime() - c2.getTime().getTime();
		return lngDiff;
	}

	/**
	 * 字符串转换为日期
	 * 
	 * @param dateStr
	 * @param format
	 * @return
	 */
	public static Date stringToDate(String dateStr, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format,Locale.getDefault());
		if(!"".equals(dateStr)){
			try {
				return sdf.parse(dateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 将字符串转换为格式为yyyy-MM-dd日期
	 * 
	 * @param dateStr
	 * @return
	 */
	public static Date stringToDate(String dateStr) {
		return stringToDate(dateStr, "yyyy-MM-dd");
	}

	/**
	 * 根据格式返回当前时间的日期格式
	 * 
	 * @param format
	 *            格式
	 * @return String
	 */
	public static String getNowTime(String format) {
		return new SimpleDateFormat(format,Locale.getDefault()).format(new Date()).toString();
	}

	// 根据当前日期返回本周的7个日期 d[0]到d[6]
	public static String[] getWeekDay() {
		Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault());
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY - 1);
		c.setTime(date);
		String[] d = new String[7];
		for (int i = 0; i < 7; i++) {
			c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + i);
			d[i] = dateformat.format(c.getTime());
		}
		return d;
	}

	/**
	 * 把传入的一个Date对象增加(24*60*60-1)*1000)毫秒 即00:00:00增加至本天的最后一秒23:59:59
	 * 注意：不适用于新建的当前时间的Date对象,因其时间不是00:00:00
	 * 
	 * @return Date
	 */
	public static Date changeDateToEnd(Date dateArgs) {
		Date resultDate = new Date();
		resultDate.setTime(dateArgs.getTime() + ((24 * 60 * 60 - 1) * 1000));
		return resultDate;
	}

	/**
	 * 简单获取系统时间字符格串
	 * 
	 * @param format
	 *            easyDateFormat("dd MMMMM yyyy") easyDateFormat("yyyyMMdd")
	 *            easyDateFormat("dd.MM.yy") easyDateFormat("MM/dd/yy")
	 *            easyDateFormat("yyyy.MM.dd G 'at' hh:mm:ss z")
	 *            easyDateFormat("EEE, MMM d, ''yy") easyDateFormat("h:mm a")
	 *            easyDateFormat("H:mm:ss:SSS") easyDateFormat("K:mm a,z")
	 *            easyDateFormat("yyyy.MMMMM.dd GGG hh:mm aaa")
	 * @return
	 */
	public static String easyDateFormat(String format) {
		Date today = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(format,Locale.getDefault());
		String datenewformat = formatter.format(today);
		return datenewformat;
	}

	public static String easyDateFormat(String format, Object object) {
		SimpleDateFormat formatter = new SimpleDateFormat(format,Locale.getDefault());
		String datenewformat = formatter.format(object);
		return datenewformat;
	}

	/**
	 * 简单获取系统时间字符格串
	 * 
	 * @param format
	 *            easyDateFormat("dd MMMMM yyyy") easyDateFormat("yyyyMMdd")
	 *            easyDateFormat("dd.MM.yy") easyDateFormat("MM/dd/yy")
	 *            easyDateFormat("yyyy.MM.dd G 'at' hh:mm:ss z")
	 *            easyDateFormat("EEE, MMM d, ''yy") easyDateFormat("h:mm a")
	 *            easyDateFormat("H:mm:ss:SSS") easyDateFormat("K:mm a,z")
	 *            easyDateFormat("yyyy.MMMMM.dd GGG hh:mm aaa")
	 * @return
	 */
	public static String easyDateFormat(String format, String date) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(format,Locale.getDefault());
			Date d = formatter.parse(date);
			String datenewformat = formatter.format(d);
			return datenewformat;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 获取时间
	 * 
	 * @param arg0
	 *            信息参数
	 * @return boolean true | false
	 */
	public static Date getDate() {
		Date d = new Date();
		return d;
	}

	/**
	 * 获取日期格式
	 * 
	 * @param date
	 * @return String
	 */
	public static String getDate(Date date) {
		return new java.text.SimpleDateFormat("yyyy-MM-dd",Locale.getDefault()).format(date);
	}

	public static BigDecimal getDateBigDecimal(String str) {
		if (str == null) {
			return null;
		} else {
			str = str.replace("-", "");
			return new BigDecimal(str);
		}
	}

	/**
	 * 根据数据库类型,返回日期sql语句 easyDateFormat("dd MMMMM yyyy")
	 * easyDateFormat("yyyyMMdd") easyDateFormat("dd.MM.yy")
	 * easyDateFormat("MM/dd/yy") easyDateFormat("yyyy.MM.dd G 'at' hh:mm:ss z")
	 * easyDateFormat("EEE, MMM d, ''yy") easyDateFormat("h:mm a")
	 * easyDateFormat("H:mm:ss:SSS") easyDateFormat("K:mm a,z")
	 * easyDateFormat("yyyy.MMMMM.dd GGG hh:mm aaa")
	 */
	public static String getDbDateByDate(Date date, String format) {

		SimpleDateFormat dateFormat = new SimpleDateFormat(format,Locale.getDefault());

		return dateFormat.format(date);
	}

	/**
	 * 得到oracle的sql语句date比较
	 * 
	 * @param date
	 * @param formatter
	 * @return
	 */
	public static String getOracleDbDate(Date date, String formatter) {
		String dbDate = "";
		SimpleDateFormat dateFormat = new SimpleDateFormat(formatter,Locale.getDefault());
		if (date == null) {
			date = new Date();
		}
		String strDate = dateFormat.format(date);
		if (formatter.indexOf("HH:mm:ss") != -1
				|| formatter.indexOf("hh:mm:ss") != -1) {

			formatter = formatter.replace(":mm:ss", "24:mi:ss");
		}
		dbDate = "to_date('" + strDate + "','" + formatter + "')";
		return dbDate;
	}

	/**
	 * 传入一个指定的Date对象，使用"yyyy-MM-dd HH:mm:ss"格式化并返回
	 * 
	 * @param date
	 * @return
	 */
	public static String getDbDateByDate(Date date) {

		String format = "yyyy-MM-dd HH:mm:ss";

		SimpleDateFormat dateFormat = new SimpleDateFormat(format,Locale.getDefault());

		return dateFormat.format(date);
	}

	/**
	 * 获取日期格式
	 * 
	 * @param date
	 * @return String
	 */
	public static String getDatetime(Date date) {
		return new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.getDefault())
				.format(date);
	}

	// 根据当前时间返回近期时间 d[0]当前日期， d[1]n天后的日期
	public static String[] getRecentDay(int n) {
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault());
		String[] d = new String[2];
		Calendar now = Calendar.getInstance();
		d[0] = dateformat.format(now.getTime());
		now.add(Calendar.DATE, n);
		d[1] = dateformat.format(now.getTime());
		return d;
	}

	@SuppressWarnings("deprecation")
	public static Date getEasyDate() {
		Date d = new Date();
		d.setHours(0);
		d.setMinutes(0);
		d.setSeconds(0);
		return d;
	}

	/**
	 * 根据long得到date
	 * 
	 * @param timeLong
	 * @return
	 */
	public static Date getDateByLong(long timeLong) {
		Date d = new java.util.Date(timeLong);
		return new java.sql.Date(d.getTime());
	}

	/**
	 * 处理成这一天的开始时间如2009-06-06 23:59:59
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static String getDateEnd(String date) {
		try {
			Date d = changeStringToDate(date);
			if (d != null)
				return easyDateFormat("yyyy-MM-dd", d) + " 23:59:59";
			else
				return "";
		} catch (Exception e) {
			return "";
		}

	}

	/**
	 * 处理成这一天的结束时间如2009-06-06 23:59:59
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static String getDateEnd(Date d) {
		try {
			if (d != null)
				return easyDateFormat("yyyy-MM-dd", d) + " 23:59:59";
			else
				return "";
		} catch (Exception e) {
			return "";
		}

	}

	/**
	 * 处理成这一天的开始时间如2009-06-06 00:00:00
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static String getDateStart(String date) {
		try {
			Date d = changeStringToDate(date);
			if (d != null)
				return easyDateFormat("yyyy-MM-dd", d) + " 00:00:00";
			else
				return "";
		} catch (Exception e) {
			return "";
		}

	}

	/**
	 * 处理成这一天的开始时间如2009-06-06 00:00:00
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static String getDateStart(Date d) {
		try {

			if (d != null)
				return easyDateFormat("yyyy-MM-dd", d) + " 00:00:00";
			else
				return "";
		} catch (Exception e) {
			return "";
		}

	}

	/**
	 * 比较两个date的大小
	 * 
	 * @param date1
	 * @param date2
	 * @return date1早于date2 返回true
	 */
	public static boolean date1SmallThendate2(Date date1, Date date2) {

		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();

		c1.setTime(date1);
		c2.setTime(date2);

		return c1.before(c2);
	}

}
