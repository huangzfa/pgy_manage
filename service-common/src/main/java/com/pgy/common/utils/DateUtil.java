package com.pgy.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;

/**
 * 日期工具类
 * 
 * @author huangzhongfa
 * 
 */
public class DateUtil {
	/**
	 * yyyy-MM-dd HH:mm:ss public static Pattern datePattern = Pattern.compile(
	 * "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$"
	 * );
	 */
	/**
	 * HH:mm
	 */
	public static Pattern HHmmPattern = Pattern.compile("^(?:[01]\\d|2[0-3])(?::[0-5]\\d)$");
	/**
	 * HH:mm:ss
	 */
	public static Pattern HHmmssPattern = Pattern.compile("^(?:[01]\\d|2[0-3])(?::[0-5]\\d){2}$");
	/**
	 * yyyy-MM-dd
	 */
	public static Pattern yyyy_MM_ddPattern = Pattern.compile(
			"^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$");
	/**
	 * yyyy[-/.]MM[-/.]dd
	 */
	public static Pattern yyyyMMddPattern = Pattern.compile(
			"^(?:(?!0000)[0-9]{4}([-/.]?)(?:(?:0?[1-9]|1[0-2])([-/.]?)(?:0?[1-9]|1[0-9]|2[0-8])|(?:0?[13-9]|1[0-2])([-/.]?)(?:29|30)|(?:0?[13578]|1[02])([-/.]?)31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)([-/.]?)0?2([-/.]?)29)$");
	/**
	 * yyyy-MM-dd HH:mm:ss
	 */
	public static Pattern yyyy_MM_ddHHmmssPattern = Pattern.compile(
			"^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)\\s+([01][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]$");

	public static final String format_yyyy_MM_ddHHmmss = "yyyy-MM-dd HH:mm:ss";
	public static final String format_yyyy_zh_MM_ddHHmmss = "yyyy年MM月dd日 HH:mm:ss";
	public static final String format_yyyy_MM_ddHHmm = "yyyy-MM-dd HH:mm";
	public static final String format_MM_ddHHmm = "MM-dd HH:mm";
	public static final String format_yyyy_MM_dd = "yyyy-MM-dd";
	public static final String format_yyyyMMdd = "yyyyMMdd";
	public static final String parse_yyyyMMddHHmmss = "yyyyMMddHHmmss";
	public static final String format_yyyyMM = "yyyyMM";
	public static final String format_yyyyMMddHHmmss = "yyyyMMdd HH:mm:ss";
	public static final String format_HHmmss = "HH:mm:ss";
	public static final String format_HHmm = "HH:mm";

	private static ThreadLocal<SimpleDateFormat> yyyy_MM_ddHHmmss = new ThreadLocal<SimpleDateFormat>();
	private static ThreadLocal<SimpleDateFormat> yyyy_zh_MM_ddHHmmss = new ThreadLocal<SimpleDateFormat>();
	private static ThreadLocal<SimpleDateFormat> yyyy_MM_ddHHmm = new ThreadLocal<SimpleDateFormat>();
	private static ThreadLocal<SimpleDateFormat> MM_ddHHmm = new ThreadLocal<SimpleDateFormat>();
	private static ThreadLocal<SimpleDateFormat> yyyy_MM_dd = new ThreadLocal<SimpleDateFormat>();
	private static ThreadLocal<SimpleDateFormat> yyyyMMdd = new ThreadLocal<SimpleDateFormat>();
	private static ThreadLocal<SimpleDateFormat> yyyyMM = new ThreadLocal<SimpleDateFormat>();
	private static ThreadLocal<SimpleDateFormat> yyyyMMddHHmmss = new ThreadLocal<SimpleDateFormat>();
	private static ThreadLocal<SimpleDateFormat> HHmmss = new ThreadLocal<SimpleDateFormat>();
	private static ThreadLocal<SimpleDateFormat> HHmm = new ThreadLocal<SimpleDateFormat>();

	/** 以下时间都是基于毫秒的 */
	public static final int MS = 1000;// 毫秒
	public static final int SEC_MS = 1 * MS;// 1秒=1000毫秒
	public static final int SEC = 1;// 1秒
	public static final int MIN_MS = 60 * SEC_MS;// 1分钟
	public static final int MIN = 60;// 1分钟=60秒
	public static final int QR_MS = 15 * MIN_MS;// 一刻钟
	public static final int QR = 15 * MIN;// 一刻钟
	public static final int HALF_H_MS = 30 * MIN_MS;// 半小时
	public static final int HALF_H = 30 * MIN;// 半小时
	public static final int H_MS = 60 * MIN_MS;// 一小时
	public static final int H = 60 * MIN;// 一小时
	public static final int DAY_MS = 24 * H_MS;// 一天

	public static SimpleDateFormat getyyyy_MM_ddHHmmss() {
		SimpleDateFormat sdf = yyyy_MM_ddHHmmss.get();
		if (sdf == null) {
			sdf = new SimpleDateFormat(format_yyyy_MM_ddHHmmss);
			yyyy_MM_ddHHmmss.set(sdf);
		}
		return sdf;
	}

	public static SimpleDateFormat getyyyy_zh_MM_ddHHmmss() {
		SimpleDateFormat sdf = yyyy_zh_MM_ddHHmmss.get();
		if (sdf == null) {
			sdf = new SimpleDateFormat(format_yyyy_zh_MM_ddHHmmss);
			yyyy_zh_MM_ddHHmmss.set(sdf);
		}
		return sdf;
	}

	public static SimpleDateFormat getyyyy_MM_ddHHmm() {
		SimpleDateFormat sdf = yyyy_MM_ddHHmm.get();
		if (sdf == null) {
			sdf = new SimpleDateFormat(format_yyyy_MM_ddHHmm);
			yyyy_MM_ddHHmm.set(sdf);
		}
		return sdf;
	}

	public static SimpleDateFormat getMM_ddHHmm() {
		SimpleDateFormat sdf = MM_ddHHmm.get();
		if (sdf == null) {
			sdf = new SimpleDateFormat(format_MM_ddHHmm);
			MM_ddHHmm.set(sdf);
		}
		return sdf;
	}

	public static SimpleDateFormat getyyyy_MM_dd() {
		SimpleDateFormat sdf = yyyy_MM_dd.get();
		if (sdf == null) {
			sdf = new SimpleDateFormat(format_yyyy_MM_dd);
			yyyy_MM_dd.set(sdf);
		}
		return sdf;
	}

	public static SimpleDateFormat getyyyyMMdd() {
		SimpleDateFormat sdf = yyyyMMdd.get();
		if (sdf == null) {
			sdf = new SimpleDateFormat(format_yyyyMMdd);
			yyyyMMdd.set(sdf);
		}
		return sdf;
	}

	public static SimpleDateFormat getyyyyMM() {
		SimpleDateFormat sdf = yyyyMM.get();
		if (sdf == null) {
			sdf = new SimpleDateFormat(format_yyyyMM);
			yyyyMM.set(sdf);
		}
		return sdf;
	}

	public static SimpleDateFormat getyyyyMMddHHmmss() {
		SimpleDateFormat sdf = yyyyMMddHHmmss.get();
		if (sdf == null) {
			sdf = new SimpleDateFormat(format_yyyyMMddHHmmss);
			yyyyMMddHHmmss.set(sdf);
		}
		return sdf;
	}

	public static SimpleDateFormat getHHmmss() {
		SimpleDateFormat sdf = HHmmss.get();
		if (sdf == null) {
			sdf = new SimpleDateFormat(format_HHmmss);
			HHmmss.set(sdf);
		}
		return sdf;
	}

	public static SimpleDateFormat getHHmm() {
		SimpleDateFormat sdf = HHmm.get();
		if (sdf == null) {
			sdf = new SimpleDateFormat(format_HHmm);
			HHmm.set(sdf);
		}
		return sdf;
	}

	public static Date parse_yyyy_MM_ddHHmmss(String ds) throws ParseException {
		return getyyyy_MM_ddHHmmss().parse(ds);
	}

	public static Date parse_yyyy_MM_ddHHmm(String ds) throws ParseException {
		return getyyyy_MM_ddHHmm().parse(ds);
	}

	public static Date parse_MM_ddHHmm(String ds) throws ParseException {
		return getMM_ddHHmm().parse(ds);
	}

	public static Date parse_yyyy_MM_dd(String ds) throws ParseException {
		return getyyyy_MM_dd().parse(ds);
	}

	public static Date parse_yyyyMMdd(String ds) throws ParseException {
		return getyyyyMMdd().parse(ds);
	}

	public static Date parse_yyyyMM(String ds) throws ParseException {
		return getyyyyMM().parse(ds);
	}

	public static Date parse_yyyyMMddHHmmss(String ds) throws ParseException {
		return getyyyyMMddHHmmss().parse(ds);
	}

	public static Date parse_HHmmss(String ds) throws ParseException {
		return getHHmmss().parse(ds);
	}

	public static String format_yyyy_MM_ddHHmmss(Date d) {
		return getyyyy_MM_ddHHmmss().format(d);
	}

	public static String format_yyyy_zh_MM_ddHHmmss(Date d) {
		return getyyyy_zh_MM_ddHHmmss().format(d);
	}

	public static String format_yyyy_MM_ddHHmm(Date d) {
		return getyyyy_MM_ddHHmm().format(d);
	}

	public static String format_MM_ddHHmm(Date d) {
		return getMM_ddHHmm().format(d);
	}

	public static String format_yyyy_MM_dd(Date d) {
		return getyyyy_MM_dd().format(d);
	}

	public static String format_yyyyMMdd(Date d) {
		return getyyyyMMdd().format(d);
	}

	public static String format_yyyyMM(Date d) {
		return getyyyyMM().format(d);
	}

	public static String format_yyyyMMddHHmmss(Date d) {
		return getyyyyMMddHHmmss().format(d);
	}

	public static String format_HHmmss(Date d) {
		return getHHmmss().format(d);
	}

	public static String format_HHmm(Date d) {
		return getHHmm().format(d);
	}

	public static Date parseByFormat(String dateStr, String format) throws ParseException {
		return new SimpleDateFormat(format).parse(dateStr);
	}
	public static String getTimeStr(Date time) {
		long date = time.getTime();
		String str = Long.toString(date / 1000);
		return str;
	}
	// 获取时分秒
	public static String getSFM(long ms) {
		int sec = (int) (ms / 1000);
		int ySec = sec % 60;
		int min = sec / 60;
		int yMin = min % 60;
		int hour = min / 60;
		String hourStr = hour + "";
		if (hour < 10) {
			hourStr = "0" + hour;
		}
		String minStr = yMin + "";
		if (yMin < 10) {
			minStr = "0" + yMin;
		}
		String secStr = ySec + "";
		if (ySec < 10) {
			secStr = "0" + ySec;
		}
		return hourStr + ":" + minStr + ":" + secStr;
	}

	// 分转小时分
	public static String getFM(int min) {
		int yMin = min % 60;
		int hour = min / 60;
		if (hour == 0) {
			return yMin + "分钟";
		}
		if (hour > 0 && yMin == 0) {
			return hour + "小时";
		}
		return hour + "小时" + yMin + "分钟";
	}

	// 秒转小时分
	public static String getFM_BySec(long sec) {
		long ySec = sec % 60;
		long min = sec / 60;
		if (ySec > 0) {
			min = min + 1;
		}
		long yMin = min % 60;
		long hour = min / 60;
		if (hour == 0) {
			return yMin + "分钟";
		}
		if (hour > 0 && yMin == 0) {
			return hour + "小时";
		}
		return hour + "小时" + yMin + "分钟";
	}

	/**
	 * 毫秒转小时风分钟 占道停车
	 * 
	 * @param ms
	 * @return
	 */
	public static String getSFM_ZDTC(long ms) {
		long sec = ms / 1000;// 停车时间 秒
		if (sec == 0) {
			sec = 1;
		}
		long ySec = sec % 60;
		long min = sec / 60;// 停车时间分
		if (ySec > 0) {
			min = min + 1;
		}
		if (min == 0) {
			min = 1;
		}
		long yMin = min % 60;
		long hour = min / 60;
		if (hour == 0) {
			return yMin + "分钟";
		}
		if (hour > 0 && yMin == 0) {
			return hour + "小时";
		}
		return hour + "小时" + yMin + "分钟";
	}

	// 秒毫秒
	public static String getSMS(long ms) {
		int ms_y = (int) (ms % MS);
		int sec = (int) (ms / MS);
		return sec + "秒" + ms_y + "毫秒";
	}

	/**
	 * 判断日期是否是今天
	 * 
	 * @param target
	 *            需要校验的时间
	 * @return true:是今天;false：不是今天
	 * @throws ParseException
	 *             日期转换异常
	 */
	public static boolean isToday(Date target) throws ParseException {
		return format_yyyy_MM_dd(target).equals(format_yyyy_MM_dd(new Date()));
	}

	/**
	 * 判断两个日期是否同一天
	 * 
	 * @param s
	 * @param e
	 * @return
	 * @throws ParseException
	 */
	public static boolean isSameDay(Date s, Date e) throws ParseException {
		return format_yyyy_MM_dd(s).equals(format_yyyy_MM_dd(e));
	}

	/**
	 * 获得时间差 精确到分钟
	 * 
	 * @param sd
	 *            开始时间
	 * @param ed
	 *            结束时间
	 * @return 时间差值多少秒
	 */
	public static long getTimeDiffByMin(Date sd, Date ed) {
		return getTimeDiffByUnit(sd, ed, MIN_MS);
	}

	/**
	 * 获得时间差 精确到秒
	 * 
	 * @param sd
	 *            开始时间
	 * @param ed
	 *            结束时间
	 * @return 时间差值多少秒
	 */
	public static long getTimeDiffBySec(Date sd, Date ed) {
		return getTimeDiffByUnit(sd, ed, SEC_MS);
	}

	public static long getTimeDiffBySec(String sd, String ed) throws ParseException {
		return getTimeDiffBySec(parse_yyyy_MM_ddHHmmss(sd), parse_yyyy_MM_ddHHmmss(ed));
	}

	/**
	 * 获得时间差
	 * 
	 * @param sd
	 *            开始时间
	 * @param ed
	 *            结束时间
	 * @param unit
	 *            单位
	 * @return 根据单位获得的时间差值
	 */
	public static long getTimeDiffByUnit(Date sd, Date ed, int unit) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(sd);
		long s = cal1.getTimeInMillis() / unit;
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(ed);
		long e = cal2.getTimeInMillis() / unit;
		cal1 = null;
		cal2 = null;
		return e - s;
	}

	public static long getTimeInMillis(Date d) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		Long s = cal.getTimeInMillis();
		cal = null;
		return s;
	}

	/**
	 * 分钟转成秒
	 * 
	 * @param m
	 *            多少分钟
	 * @return 多少秒
	 */
	public static long getSecByMin(int m) {
		return m * MIN;
	}

	/**
	 * 小时转成秒
	 * 
	 * @param h
	 *            多少小时
	 * @return 多少秒
	 */
	public static long getSecByHour(int h) {
		return h * H;
	}

	/**
	 * 小时转分钟
	 * 
	 * @param h
	 * @return
	 */
	public static int getMinByHour(int h) {
		return h * 60;
	}

	/**
	 * 分钟转毫秒
	 * 
	 * @param m
	 * @return
	 */
	public static long getMsByMin(int m) {
		return m * MIN_MS;
	}

	/**
	 * 秒转分，不省略余数时，分+1
	 * 
	 * @param sec
	 * @param isOmitRemainder
	 *            是否省略余数，true是，false否
	 * @return
	 */
	public static long getMinBySec(long sec, boolean isOmitRemainder) {
		long min = sec / 60;
		long ySec = sec % 60;
		if (!isOmitRemainder && ySec > 0) {
			min = min + 1;
		}
		if (min == 0) {
			min = 1;
		}
		return min;
	}

	/**
	 * 匹配日期格式yyyy-MM-dd HH:mm:ss
	 * 
	 * @param dateStr
	 *            日期字符串
	 * @return ture:有效的日期格式；false:无效的日期格式
	 */
	public static boolean matche_yyyy_MM_ddHHmmss(String dateStr) {
		return yyyy_MM_ddHHmmssPattern.matcher(dateStr).matches();
	}

	/**
	 * 匹配日期格式yyyy-MM-dd
	 * 
	 * @param dateStr
	 *            日期字符串
	 * @return ture:有效的日期格式；false:无效的日期格式
	 */
	public static boolean matche_yyyy_MM_dd(String dateStr) {
		return yyyy_MM_ddPattern.matcher(dateStr).matches();
	}

	public static boolean matche_yyyyMMdd(String dateStr) {
		return yyyyMMddPattern.matcher(dateStr).matches();
	}

	/**
	 * 匹配日期格式HH:mm
	 * 
	 * @param dateStr
	 *            日期字符串
	 * @return ture:有效的日期格式；false:无效的日期格式
	 */
	public static boolean matche_HHmm(String dateStr) {
		return HHmmPattern.matcher(dateStr).matches();
	}

	/**
	 * 获取当前时间基础上增加多少分钟的时间
	 * 
	 * @param min
	 * @return
	 */
	public static long getAddDateByMinAfter(int min) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.MINUTE, min);// 24小时制
		return cal.getTimeInMillis();
	}

	/**
	 * 基于date增加天数
	 * 
	 * @param date
	 *            需要增加天数的日期
	 * @param day
	 *            要增加的天数
	 * @return
	 */
	public static Date addDay(Date date, int day) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, day);
		return cal.getTime();
	}

	public static Date addMonth(Date date, int month) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, month);
		return cal.getTime();
	}

	/**
	 * 把HH:mm格式时间转换成多少分钟
	 * 
	 * @param HHmm
	 * @return
	 * @throws DateException
	 */
	public static int getMinByHHmm(String HHmm) throws Exception {
		if (!matche_HHmm(HHmm)) {
			throw new Exception(HHmm + " 时间格式不正确");
		}
		String[] st = HHmm.split(":");
		int stH = Integer.parseInt(st[0]);
		int stM = Integer.parseInt(st[1]);
		return stH * 60 + stM;
	}

	/**
	 * 当天的开始时间00:00:00
	 * 
	 * @return
	 * @throws ParseException
	 */
	public static String getTodayBeginTimeStr() {
		return format_yyyy_MM_dd(new Date()) + " 00:00:00";
	}

	public static Date getTodayBeginTime() throws ParseException {
		return parse_yyyy_MM_ddHHmmss(format_yyyy_MM_dd(new Date()) + " 00:00:00");
	}

	/**
	 * 当天的结束时间23:59:59
	 * 
	 * @return
	 * @throws ParseException
	 */
	public static String getTodayEndTimeStr() {
		return format_yyyy_MM_dd(new Date()) + " 23:59:59";
	}

	public static Date getTodayEndTime() throws ParseException {
		return parse_yyyy_MM_ddHHmmss(format_yyyy_MM_dd(new Date()) + " 23:59:59");
	}

	public static String[] weeks = new String[] { "周一", "周二", "周三", "周四", "周五", "周六", "周日" };

	/**
	 * 获取指定日期是星期几
	 * 
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public static String dayForWeek(Date date) throws Exception {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int dayForWeek = 0;
		if (c.get(Calendar.DAY_OF_WEEK) == 1) {
			dayForWeek = 7;
		} else {
			dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
		}
		return weeks[dayForWeek - 1];
	}

	/*
	 * 基于date增加分钟
	 * 
	 * @param date 需要增加天数的日期
	 * 
	 * @param day 要增加的分钟
	 * 
	 * @return
	 */
	public static Date addMinute(Date date, int minute) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, minute);
		return cal.getTime();
	}

	/*
	 * 基于date增加分钟
	 * 
	 * @param date 需要增加天数的日期
	 * 
	 * @param day 要增加的秒
	 * 
	 * @return
	 */
	public static Date addSecond(Date date, int second) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.SECOND, second);
		return cal.getTime();
	}

	/**
	 * 判断时间是否在一个时间区间内
	 * 
	 * @param sd_start_HHmm
	 * @param sd_end_HHmm
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static boolean isInTimeInterval(String sd_start_HHmm, String sd_end_HHmm, Date date) throws ParseException {
		String yyyy_MM_dd = DateUtil.format_yyyy_MM_dd(date);
		Date sfsdStart = DateUtil.parse_yyyy_MM_ddHHmm(yyyy_MM_dd + " " + sd_start_HHmm);
		Date sfsdEnd = DateUtil.parse_yyyy_MM_ddHHmm(yyyy_MM_dd + " " + sd_end_HHmm);
		return isInTimeInterval(sfsdStart, sfsdEnd, date);
	}

	public static boolean isInTimeInterval(Date sfsdStart, Date sfsdEnd, Date date) throws ParseException {
		long dl = date.getTime();
		if (dl >= sfsdStart.getTime() && dl <= sfsdEnd.getTime()) {
			return true;
		}
		return false;
	}

	public static boolean isInTimeInterval(Date sfsdStart, Date sfsdEnd, Date inDate, Date outDate)
			throws ParseException {
		long startTime = sfsdStart.getTime();
		long endTime = sfsdEnd.getTime();
		long inTime = inDate.getTime();
		long outTime = outDate.getTime();
		if (inTime >= startTime && inTime <= endTime) {
			return true;
		}
		if (outTime >= startTime && outTime <= endTime) {
			return true;
		}
		if (inTime < startTime && outTime > endTime) {
			return true;
		}
		return false;
	}

	/**
	 * 获取月份的最后一天的最后时间
	 */
	public static Date getMonthMaxTime(String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DATE, calendar.getMaximum(Calendar.DATE));
		return calendar.getTime();
	}

	public static Date lastDayOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.roll(Calendar.DAY_OF_MONTH, -1);
		return cal.getTime();
	}

	public static String getToday() {
		return format_yyyy_MM_dd(new Date());
	}

	/**
	 * 剩余时间-n天n小时
	 * 
	 * @param minuend
	 * @param subtrahend
	 * @return
	 */
	public static String getRemainTimeStr(Date minuend, Date subtrahend) {
		String result = "";
		long cha = minuend.getTime() - subtrahend.getTime();
		if (cha < 0) {
			cha = Math.abs(cha);
		}
		int day = (int) (cha / DAY_MS);
		if (day > 0) {
			result += day + "天";
		}
		long hourCha = cha % DAY_MS;
		if (hourCha > 0) {
			int hour = (int) (hourCha / H_MS);
			if (hour > 0) {
				result += hour + "小时";
			}
		}
		return result;
	}

	/**
	 * 获得当前日期
	 * 
	 * @return
	 */
	public static Date getNow() {
		Calendar cal = Calendar.getInstance();
		Date currDate = cal.getTime();
		return currDate;
	}

	/**
	 * @Title: compareDate
	 * @Description: (日期比较，如果s>=e 返回true 否则返回false)
	 * @param s
	 * @param e
	 * @return boolean
	 */
	public static boolean compareDate(Date s, Date e) {
		return s.getTime() >= e.getTime();
	}

	/**
	 * Add specified number of days to the given date.
	 *
	 * @param date date
	 * @param days Int number of days to add
	 * @return revised date
	 */
	public static Date addDays(final Date date, int days) {
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, days);

		return new Date(cal.getTime().getTime());
	}

	/**
	 * Format date as given date format.
	 *
	 * @param timeStamp 毫秒级别时间戳
	 * @return 格式化后的日期字符串
	 */
	public static String formatDate(long timeStamp, String format) {
		Date date = new Date(timeStamp);
		return new SimpleDateFormat(format).format(date);
	}

	public static void main(String[] args) {
		System.out.println(DateUtil.getFM_BySec(100));
	}
}
