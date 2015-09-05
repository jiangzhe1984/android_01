/**
 * Created on 2005-3-25
 *
 * 提供获取系统时间的方法

 */
package com.example.jiangz.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author JiangZ
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Kalendar {


	public static Date getParse(String date){
		Date pareDate = null;
		try {
			pareDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return pareDate;
	}


	public static String getFormat(Date date){
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}

	//获得当前系统日期
	public static String getDate() {
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	}

	public static String getDate(int year, int month, int day) {
		GregorianCalendar calendar = new GregorianCalendar(year, month - 1, day);
		return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
	}

	public static String getDateTime(){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}

	public static String getChineseDate() {
		return new SimpleDateFormat("yyyy年M月d日").format(new Date());
	}

	//获得当前系统时间
	public static String getTime() {
		return new SimpleDateFormat("HH:mm:ss").format(new Date());
	}
	
	//获得当前系统时间,返回一个String[2],分别封装小时数和分钟数

	public static String[] getTimeForHourMinute() {
		String[] time = new String[2];
		String timeStr = new SimpleDateFormat("HH:mm:ss").format(new Date());
		String[] temp = timeStr.split(":");
		time[0] = temp[0];
		time[1] = temp[1];
		return time;
	}

	//获得当前系统时间的星期日期

	public static String getWeekday() {
		return new SimpleDateFormat("E").format(new Date());
	}

	public static String getWeekday(int year, int month, int day) {
		GregorianCalendar calendar = new GregorianCalendar(year, month - 1, day);
		return new SimpleDateFormat("E").format(calendar.getTime());
	}

	//获得当前系统时间的年月

	public static String getYearAndMonth() {
		return new SimpleDateFormat("yyyy-MM").format(new Date());
	}

	public static String getYearAndMonth(int year, int month, int day) {
		GregorianCalendar calendar = new GregorianCalendar(year, month - 1, day);
		return new SimpleDateFormat("yyyy-MM").format(calendar.getTime());
	}

	//获得当前系统时间是一年中的第几天
	public static String getDateInYear() {
		return new SimpleDateFormat("DDD").format(new Date());
	}

	public static String getDateInYear(int year, int month, int day) {
		GregorianCalendar calendar = new GregorianCalendar(year, month - 1, day);
		return new SimpleDateFormat("DDD").format(calendar.getTime());
	}

	//获得当前系统时间是一年中的第几周
	public static String getWeekInYear() {
		return new SimpleDateFormat("ww").format(new Date());
	}

	public static String getWeekInYear(int year, int month, int day) {
		GregorianCalendar calendar = new GregorianCalendar(year, month - 1, day);
		return new SimpleDateFormat("ww").format(calendar.getTime());
	}

	//获得当前系统时间是一个月中的第几周

	public static String getWeekInMonth() {
		return new SimpleDateFormat("WW").format(new Date());
	}

	public static String getWeekInMonth(int year, int month, int day) {
		GregorianCalendar calendar = new GregorianCalendar(year, month - 1, day);
		return new SimpleDateFormat("WW").format(calendar.getTime());
	}

	//获得n天以前的日期
	public static String getDateByBefore(int beforeNum) {
		Calendar now = Calendar.getInstance();
		now.add(Calendar.DAY_OF_YEAR, -1 * beforeNum);
		return new SimpleDateFormat("yyyy-MM-dd").format(now.getTime());
	}

	public static String getDateByBefore(
		int year,
		int month,
		int day,
		int beforeNum) {
		GregorianCalendar calendar = new GregorianCalendar(year, month - 1, day);
		calendar.add(GregorianCalendar.DAY_OF_YEAR, -1 * beforeNum);
		return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
	}
	
	/**
	 * 获得n年前今天的日期

	 * @param beforeyears
	 * @return
	 */
	public static String getDateBeforeYears(int beforeyears){
		Calendar now = Calendar.getInstance();
		
		now.add(GregorianCalendar.YEAR, -1 * beforeyears);
		return new SimpleDateFormat("yyyy-MM-dd").format(now.getTime());
	}

	/**
	 * 得到指定日期前指定天数的日期
	 * @param dateString
	 * @param beforeNum
	 * @return
	 */
	public static String getDateByBefore(String dateString, int beforeNum) {

		Calendar calendar = Calendar.getInstance();

		try {
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);

			calendar.setTime(date);
		} catch (Exception e) {
			return "";
		}

		calendar.add(GregorianCalendar.DAY_OF_YEAR, -1 * beforeNum);

		return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
	}

	/**
	 * 得到指定日期后指定天数的日期
	 * @param dateString
	 * @param afterNum
	 * @return
	 */
	public static String getDateByAfter(String dateString, int afterNum) {

		Calendar calendar = Calendar.getInstance();

		try {
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);

			calendar.setTime(date);
		} catch (Exception e) {
			return "";
		}

		calendar.add(GregorianCalendar.DAY_OF_YEAR, afterNum);

		return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
	}

	//获得n天以前的日期的星期日期

	public static String getWeekdayByBefore(int beforeNum) {
		Calendar now = Calendar.getInstance();
		now.add(Calendar.DAY_OF_YEAR, -1 * beforeNum);
		return new SimpleDateFormat("E").format(now.getTime());
	}

	public static String getWeekdayByBefore(
		int year,
		int month,
		int day,
		int beforeNum) {
		GregorianCalendar calendar = new GregorianCalendar(year, month - 1, day);
		calendar.add(GregorianCalendar.DAY_OF_YEAR, -1 * beforeNum);
		return new SimpleDateFormat("E").format(calendar.getTime());
	}

	//获得n天以后的日期
	public static String getDateByAfter(int afterNum) {
		Calendar now = Calendar.getInstance();
		now.add(Calendar.DAY_OF_YEAR, afterNum);
		return new SimpleDateFormat("yyyy-MM-dd").format(now.getTime());
	}

	public static String getDateByAfter(
		int year,
		int month,
		int day,
		int afterNum) {
		GregorianCalendar calendar = new GregorianCalendar(year, month - 1, day);
		calendar.add(GregorianCalendar.DAY_OF_YEAR, afterNum);
		return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
	}

	//获得n天以后的日期的星期日期

	public static String getWeekdayByAfter(int afterNum) {
		Calendar now = Calendar.getInstance();
		now.add(Calendar.DAY_OF_YEAR, afterNum);
		return new SimpleDateFormat("E").format(now.getTime());
	}

	public static String getWeekdayByAfter(
		int year,
		int month,
		int day,
		int afterNum) {
		GregorianCalendar calendar = new GregorianCalendar(year, month - 1, day);
		calendar.add(GregorianCalendar.DAY_OF_YEAR, afterNum);
		return new SimpleDateFormat("E").format(calendar.getTime());
	}

	//获得当前系统日期所处周的周末日期（周六）

	public static String getDateOfWeekend() {
		Calendar now = Calendar.getInstance();
		now.add(Calendar.DAY_OF_YEAR, 7 - now.get(Calendar.DAY_OF_WEEK));
		return new SimpleDateFormat("yyyy-MM-dd").format(now.getTime());
	}

	public static String getDateOfWeekend(int year, int month, int day) {
		GregorianCalendar calendar = new GregorianCalendar(year, month - 1, day);
		calendar.add(
			GregorianCalendar.DAY_OF_YEAR,
			7 - calendar.get(GregorianCalendar.DAY_OF_WEEK));
		return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
	}

	//获得当前系统日期所处周的周起始日期（周日）
	public static String getDateOfWeekstart() {
		Calendar now = Calendar.getInstance();
		now.add(Calendar.DAY_OF_YEAR, 1 - now.get(Calendar.DAY_OF_WEEK));
		return new SimpleDateFormat("yyyy-MM-dd").format(now.getTime());
	}

	public static String getDateOfWeekstart(int year, int month, int day) {
		GregorianCalendar calendar = new GregorianCalendar(year, month - 1, day);
		calendar.add(
			GregorianCalendar.DAY_OF_YEAR,
			1 - calendar.get(GregorianCalendar.DAY_OF_WEEK));
		return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
	}

	//获得当前系统日期所处月的天数

	public static int getDateOfMonthend() {
		Calendar now = Calendar.getInstance();
		return now.getActualMaximum(Calendar.DATE);
	}

	public static int getDateOfMonthend(int year, int month, int day) {
		GregorianCalendar calendar = new GregorianCalendar(year, month - 1, day);
		return calendar.getActualMaximum(GregorianCalendar.DATE);
	}

	/**
	 * 在参数日期的时间上减去相应时间（按分钟减去）
	 * @param ：要查询的日期，格式为"yyyy-MM-dd HH:mm:ss"
	 * @param minute  要减去的时间
	 * @return：boolean
	 */
	public static String getDateBefore(String timeString, int minute) {
		long min = 0;
		try {
			Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(timeString);
			min = date.getTime();

		} catch (Exception e) {
		}
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(
			new Date(min - minute * 60 * 1000));
	}

	/**
	 * 判断传入的日期是否大于当前系统时间额定的时间
	 * @param ：要查询的日期，格式为"yyyy-MM-dd HH:mm:ss"
	 * @return：boolean
	 */
	public static boolean IsOverTime(String timeString, int rating) {
		try {
			Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(timeString);
			Date now =
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(
					new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

			long min = date.getTime();
			long nowmin = now.getTime();

			if (nowmin - min > rating * 60 * 1000)
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		}
	}

	public static String switchDateFormat(String dateStr) {
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
		} catch (Exception e) {
		}
		return new SimpleDateFormat("yyyy年M月d日").format(date);
	}

	public static String switchDateFormat(
		String firstFormat,
		String secondFormat,
		String dateStr) {
		Date date = null;
		try {
			date = new SimpleDateFormat(firstFormat).parse(dateStr);
		} catch (Exception e) {
		}
		return new SimpleDateFormat(secondFormat).format(date);
	}

	/**
	 * 得到指定的日期是星期几

	 * @param dateString：要查询的日期，格式为"yyyy-MM-dd"
	 * @return：日期的星期
	 */
	public static String getWeekdayByDateString(String dateString) {
		String weekday = "";

		try {
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);

			weekday = new SimpleDateFormat("E").format(date);
		} catch (Exception e) {

		}

		return weekday;
	}

	/**
	 * 得到指定的日期是星期几

	 * @param date：要查询的日期

	 * @return：日期的星期
	 */
	public static String getWeekdayByDate(Date date) {
		return new SimpleDateFormat("E").format(date);
	}

	/**
	 * 将当前系统时间转换成直至Millisecond的样式

	 * @return
	 */
	public static String getDateTimeZone() {
		return new SimpleDateFormat("yyyyMMddHHmmssS").format(new Date());
	}

    /**
     * 获得当前系统年份
     * @return
    */
    public static String getYear() {
       return new SimpleDateFormat("yyyy").format(new Date());
    }
    
    /**
     * 获得给定年龄对应的出生年份

     * @param age 年龄
     * @return
     * @throws Exception
     */
    public static String getYearByAge(int age)throws Exception{
    	String year = getYear();
    	
    	int yearNum = Integer.parseInt(year);
    	yearNum = yearNum - age;
    	year = Integer.toString(yearNum);
    	
    	return year;
    }
    
	/**
	 * 获得当前系统季度的起始日期和结束日期
	 * @return
	 */    
	public static String[] getNowQuarter() {
	   String[] record = new String[4];
	   String startDateStr = "";
	   String endDateStr = ""; 
	   String quarter = "";	
	   Date date = new Date();
	   
	   String year =  new SimpleDateFormat("yyyy").format(date);	
	   String month = new SimpleDateFormat("MM").format(date);
	   int monthInteger = Integer.parseInt(month);
	   
	   if(monthInteger>=1 && monthInteger<=3) {
			startDateStr = "-01-01";
			endDateStr = "-03-31";
			quarter = "1";
	   }
	   if(monthInteger>=4 && monthInteger<=6) {
			startDateStr = "-04-01";
			endDateStr = "-06-30";
			quarter = "2";
	   }
	   if(monthInteger>=7 && monthInteger<=9) {
			startDateStr = "-07-01";
			endDateStr = "-09-30";
			quarter = "3";
	   }
	   if(monthInteger>=9 && monthInteger<=12) {
			startDateStr = "-10-01";
			endDateStr = "-12-31";
			quarter = "4";
	   }
	   
	   record[0] = year+startDateStr;
	   record[1] = year+endDateStr;
	   record[2] = quarter;
	   record[3] = year+"年第"+quarter+"季度";
	   
//	   System.out.println(record[0]+"   "+record[1]+"   "+record[2]);
	   	   
	   return record;
	   
	}   
	
	/**
	 * 获得下一个季度的起始日期和结束日期（以页面传入时间为准）
	 * @return
	 */    
	public static String[] getDownQuarter(String date, String quarter) throws Exception {
		String[] record = new String[4];
		String startDateStr = "";
		String endDateStr = "";
		int monthInteger = Integer.parseInt(quarter);
		
		String year =  new SimpleDateFormat("yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(date));
		int yearInteger = Integer.parseInt(year);
		
		if(monthInteger == 1) {
			startDateStr = year+"-04-01";
			endDateStr = year+"-06-30";
			quarter = "2";			
		}
		if(monthInteger == 2) {
			startDateStr = year+"-07-01";
			endDateStr = year+"-09-30";
			quarter = "3";		
		}
		if(monthInteger == 3) {
			startDateStr = year+"-10-01";
			endDateStr = year+"-12-31";
			quarter = "4";	
		}
		if(monthInteger == 4) {
			startDateStr = (yearInteger+1)+"-01-01";
			endDateStr = (yearInteger+1)+"-03-31";
			quarter = "1";
			year = Integer.toString(yearInteger+1);
		}
		
		record[0] = startDateStr;
		record[1] = endDateStr;
		record[2] = quarter;
		record[3] = year+"年第"+quarter+"季度"; 
		
//		System.out.println(record[0]+"   "+record[1]+"   "+record[2]);
		
		return record;		
			
	}
	
	/**
	 * 获得下一个季度的起始日期和结束日期（以页面传入时间为准）
	 * @return
	 */    
	public static String[] getUpQuarter(String date, String quarter) throws Exception {
		String[] record = new String[4];
		String startDateStr = "";
		String endDateStr = "";
		int monthInteger = Integer.parseInt(quarter);
		
		String year =  new SimpleDateFormat("yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(date));
		int yearInteger = Integer.parseInt(year);
		
		if(monthInteger == 1) {
			startDateStr = (yearInteger-1)+"-10-01";
			endDateStr = (yearInteger-1)+"-12-31";
			quarter = "4";
			year = Integer.toString(yearInteger-1);	
		}
		if(monthInteger == 2) {
			startDateStr = year+"-01-01";
			endDateStr = year+"-03-31";
			quarter = "1";	
		}
		if(monthInteger == 3) {
			startDateStr = year+"-04-01";
			endDateStr = year+"-06-30";
			quarter = "2";	
		}
		if(monthInteger == 4) {
			startDateStr = year+"-07-01";
			endDateStr = year+"-09-30";
			quarter = "3";
		}
		
		record[0] = startDateStr;
		record[1] = endDateStr;
		record[2] = quarter;
		record[3] = year+"年第"+quarter+"季度";
		
//		System.out.println(record[0]+"   "+record[1]+"   "+record[2]);
		
		return record;		
			
	}		 

	/**
	 * 考试时间专用方法，当考生第一次翻开试卷页面是，当时的服务器系统时间应该
	 * 记录到HttpSession 其对应参数firsttime ；所以如果考生是第一次考试，那么

	 * 传递的HttpSession中参数应该和当时的服务器系统时间一致；系统将按照考试
	 * 分钟长度转换成毫秒形式返回；如果在考试当中考生刷新页面，则HttpSession
	 * 中的时间作为firsttime 当时的系统时间作为secondtime传入，系统将减去所损失
	 * 的时间转换成毫秒传入
	 * @param firsttime
	 * @param secondtime
	 * @param m 考试时间（多少分钟）
	 * @return：long
	 */
	public static int getExamTime(String firsttime, String secondtime, int m) {
		long record = 0;
		try {
			Date first = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(firsttime);
			Date second =
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(secondtime);
			Date now =
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(
					new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

			long m_first = first.getTime();
			long m_second = second.getTime();

			if (m_first == m_second)
				record = m * 60 * 1000;
			else
				record = m * 60 * 1000 - (now.getTime() - first.getTime());
		} catch (Exception e) {
		}
		return (int) record / (60 * 1000);
	}
	
	/**
	 * 将日期由"yyyy-MM-dd HH"转换为"yyyyMMddHH"格式
	 * @return String
	 */
	public static String switchDateStr(String date) {
		String record = "";
		try {
			Date first = new SimpleDateFormat("yyyy-MM-dd HH").parse(date);
			record = new SimpleDateFormat("yyyyMMddHH").format(first);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return record;
	}
	
	/**
	 * 将日期由"yyyy-MM-dd HH:mm:ss.S"转换为"yyyy-MM-dd HH:mm:ss"格式
	 * @return String
	 */
	public static String switchDateStrForWorkList(String date) {
		String record = "";
		try {
			Date first = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(date);
			record = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(first);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return record;
	}
	
	/**
	 * java实现：计算年龄。参数：出生日期、欲计算的生效日期。没过生日，则减一。

	 *
	 */
	public static int computeIssueAge(String sBirthDate) throws Exception{
		   
		SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
		   
		Calendar birthCalendar = Calendar.getInstance();
		Calendar effectiveCalendar = Calendar.getInstance();
		   
		Date birthDate = formatter1.parse(sBirthDate);
		birthCalendar.setTime(birthDate);
		   
		Date effectiveDate = new Date();
		effectiveCalendar.setTime(effectiveDate);
		   
		int age = effectiveCalendar.get(Calendar.YEAR)-birthCalendar.get(Calendar.YEAR);
		if (birthCalendar.get(Calendar.MONTH)>effectiveCalendar.get(Calendar.MONTH)){
			age=age-1;
		}else if (birthCalendar.get(Calendar.MONTH)==effectiveCalendar.get(Calendar.MONTH)){
			if (birthCalendar.get(Calendar.DAY_OF_MONTH)>effectiveCalendar.get(Calendar.DAY_OF_MONTH)){
				age=age-1;
			}
		}
		return age;
	}
	
	public static String uniteDateTime(String date, String time) {
		return date + " " + time;
	}

//	public static void main(String[] args) throws Exception {
//		System.out.println(Kalendar.getDate());
//		System.out.println(Kalendar.getDate(2005,3,28));
//		System.out.println(Kalendar.getTime());
//		System.out.println(Kalendar.getWeekday());
//		System.out.println(Kalendar.getWeekday(2005,3,28));
//		System.out.println(Kalendar.getDateInYear());
//		System.out.println(Kalendar.getDateInYear(2005,3,28));
//		System.out.println(Kalendar.getWeekInYear());
//		System.out.println(Kalendar.getWeekInYear(2005,3,28));
//		System.out.println(Kalendar.getWeekInMonth());
//		System.out.println(Kalendar.getWeekInMonth(2005,3,28));
//		System.out.println(Kalendar.getDateByBefore(2));
//		System.out.println(Kalendar.getDateByBefore(2005,3,28,2));
//		System.out.println(Kalendar.getWeekdayByBefore(2));
//		System.out.println(Kalendar.getWeekdayByBefore(2005,3,28,2));
//		System.out.println(Kalendar.getDateByAfter(2));
//		System.out.println(Kalendar.getDateByAfter(2005,6,18,100));
//		System.out.println(Kalendar.getWeekdayByAfter(2));
//		System.out.println(Kalendar.getWeekdayByAfter(2005,3,28,2));
//		System.out.println(Kalendar.getDateOfWeekend());
//		System.out.println(Kalendar.getDateOfWeekend(2005,3,28));
//		System.out.println(Kalendar.getDateOfWeekstart());
//		System.out.println(Kalendar.getDateOfWeekstart(2005,1,1));
//		System.out.println(Kalendar.getYearAndMonth());
//		System.out.println(Kalendar.getYearAndMonth(2005,3,28));
//		System.out.println(Kalendar.getDateOfMonthend());
//		System.out.println(Kalendar.getDateOfMonthend(2005,2,28));
//		System.out.println(Kalendar.getWeekdayByDateString("2005-03-31"));
//		System.out.println(Kalendar.IsOverTime("2005-04-01 11:44:32",1));
//		System.out.println(Kalendar.getDateTimeZone());
//		System.out.println(new SimpleDateFormat("yyyy年M月d日").format(new Date()));
//		System.out.println(Kalendar.switchDateFormat("2005-11-17"));
//		System.out.println(Kalendar.switchDateFormat("yyyy-MM-dd","yyyy年M月d日","2005-11-17"));
//		Kalendar.getNowQuarter();
//		Kalendar.getDownQuarter("2005-12-01","4");
//		Kalendar.getUpQuarter("2005-01-01","1");
//		String[] time = Kalendar.getTimeForHourMinute();
//		System.out.println(time[0]+"----"+time[1]);
//		System.out.println(Kalendar.switchDateStr("2005-01-01 06"));
//	}

}
