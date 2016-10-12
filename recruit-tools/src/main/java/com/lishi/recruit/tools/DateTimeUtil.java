package com.lishi.recruit.tools;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtil {

	  private static final String FORMAT_STR_COMMON = "yyyy-MM-dd HH:mm:ss";
	  
	  private static final String FORMAT_STR_SHORT = "yyyy-MM-dd HH:mm";
	  
	  private static final String FORMAT_STR_LONG = "yyyy-MM-dd HH:mm:ss:SSS";
	  
	  private static final String FORMAT_STR_SIMP = "yyyy-MM-dd";
	  
	  private static final String FORMAT_STR_UNIN = "yyyyMMddHHmmss";
	  
	  private static final String FORMAT_STR_UNINLONG = "yyyyMMddHHmmssSSS";
	  
	  private static final String FORMAT_STR_UNIN_YMD = "yyyyMMdd";
	  
	  /**
	   * 取当前时间
	   * yyyy-MM-dd HH:mm:ss
	   * @return
	   */
	  public static String getCurrentTime()
	  {
	    Date date = new Date();
	    SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_STR_COMMON);
	    return sdf.format(date);
	  }
	  /**
	   * 把时间格式转换成字符串
	   * yyyy-MM-dd HH:mm:ss
	   * @return
	   */
	  public static String convertTime(Date date)
	  {
		  SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_STR_COMMON);
		  return sdf.format(date);
	  }
	  
	  /**
	   * 
	   * <p>转换成短时间格式</p>
	   * @Author 张太
	   * @param  
	   * @return String
	   * @since  2015年1月23日 下午6:06:29
	   */
	  public static String convertShortTime(Date date)
	  {
	      SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_STR_SHORT);
	      return sdf.format(date);
	  }
	  
	  /**
	   * 取当前日期
	   * yyyy-MM-dd
	   * @return
	   */
	  public static String getCurrentDate()
	  {
		  Date date = new Date();
		  SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_STR_SIMP);
		  return sdf.format(date);
	  }
	  
	  /**
	   * 获取当前有豪秒时间
	   * @return
	   */
	  public static String getTimeMillis()
	  {
		  Date date = new Date();
		  SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_STR_LONG);
		  return sdf.format(date);
	  }
	  
	  /**
	   * 转换有豪秒时间
	   * @return
	   */
	  public static String convertTimeMillis(Date date)
	  {
	    SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_STR_LONG);
	    return sdf.format(date);
	  }
	  
	  /**
	   * 获取时间格式没有"-"
	   * 年月日时分秒
	   * @return
	   */
	  public static String getymdhmsCurrentTimeString()
	  {
	    Date date = new Date();
	    SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_STR_UNIN);
	    return sdf.format(date);
	  }
	  
	  /**
	   * 获取当前有豪秒时间无"-"
	   * 年月日时分秒豪秒
	   * @return
	   */
	  public static String getCurrentTimeMillis()
	  {
	    Date date = new Date();
	    SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_STR_UNINLONG);
	    return sdf.format(date);
	  }
	  
	  /**
	   * 把豪秒时间转成字符串时音
	   * @param time
	   * @return
	   */
	  public static String convertTimeMillis(Long time)
	  {
		    SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_STR_UNINLONG);
		    Calendar calendar = Calendar.getInstance();
		    calendar.setTimeInMillis(time);
		    return sdf.format(calendar.getTime());
	  }
	  
	  /**
	   * 
	   * <p>根据时间字付串转成y-m-d h:m格式</p>
	   * @Author 张太
	   * @param  
	   * @return Date
	   * @since  2015年1月23日 下午6:21:09
	   */
	  public static Date strToDateLong(String strDate) {
	      SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_STR_SHORT);
	      ParsePosition pos = new ParsePosition(0);
	      Date strtodate = formatter.parse(strDate, pos);
	      return strtodate;
	   }
	  
	  /**
	   * 
	   * <p>根据时间字付串转成y-m-d h:m:s格式</p>
	   * @Author 张太
	   * @param  
	   * @return Date 
	   * @since  2015年1月23日 下午6:21:09
	   */
	  public static Date strToDate(String strDate) {
	      SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_STR_COMMON);
	      ParsePosition pos = new ParsePosition(0);
	      Date strtodate = formatter.parse(strDate, pos);
	      return strtodate;
	  }
	  
	  /**
	   * 
	   * <p>根据时间获取yyyymmdd格式时间</p>
	   * @Author 张太
	   * @param  
	   * @return Long
	   * @since  2015年5月6日 上午11:35:48
	   */
     public static String getymdCurrentTimeString(Date date)
     {      
          SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_STR_UNIN_YMD);
          return sdf.format(date);
     }

	  public static void main(String[] args)
	  {
	      System.out.println(strToDate("2014-12-02 12:12:46"));
	      System.out.println(getymdCurrentTimeString(new Date()));
	      System.out.println(System.currentTimeMillis());
//	    System.out.println(getymdhmsCurrentTimeString());
//	    System.out.println(getymdhmsCurrentTimeString());
//	      System.out.println(convertShortTime(strToDateLong("2015-01-23 17:05:11.0")));
	      
	  }
}
