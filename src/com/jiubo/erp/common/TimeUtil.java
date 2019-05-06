package com.jiubo.erp.common;


import org.apache.commons.lang.StringUtils;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {

    public static ThreadLocal<Long> local = new ThreadLocal<Long>();
    public final static String _NULLSTR = "";
    public final static String YYYY = "yyyy";
    public final static String MM = "MM";
    public final static String DD = "DD";
    public final static String YYYYMM = "yyyyMM";
    public final static String YYYYMMDD = "yyyyMMdd";
    public final static String YYYY$MM$DD = "yyyy/MM/dd";
    public final static String YYYY_MM_DD = "yyyy-MM-dd";
    public final static String YYYY_M_D = "yyyy-M-d";
    public final static String YYYY$M$D = "yyyy/M/d";
    public final static String YYYY_M_DD = "yyyy-M-dd";
    public final static String YYYY$M$DD = "yyyy/M/dd";
    public final static String YYYY_MM_D = "yyyy-MM-d";
    public final static String YYYY$MM$D = "yyyy/MM/d";

    private static SimpleDateFormat sdf_YYYY = new SimpleDateFormat(YYYY);
    private static SimpleDateFormat sdf_MM= new SimpleDateFormat(MM);
    private static SimpleDateFormat sdf_YYYYMM = new SimpleDateFormat(YYYYMM);
    private static SimpleDateFormat sdf_DD= new SimpleDateFormat(DD);
    private static SimpleDateFormat sdf_YYYYMMDD = new SimpleDateFormat(YYYYMMDD);
    private static SimpleDateFormat sdf_YYYY$M$D = new SimpleDateFormat(YYYY$M$D);
    private static SimpleDateFormat sdf_YYYY_M_D = new SimpleDateFormat(YYYY_M_D);
    private static SimpleDateFormat sdf_YYYY$M$DD = new SimpleDateFormat(YYYY$M$DD);
    private static SimpleDateFormat sdf_YYYY_M_DD = new SimpleDateFormat(YYYY_M_DD);
    private static SimpleDateFormat sdf_YYYY$MM$D = new SimpleDateFormat(YYYY$MM$D);
    private static SimpleDateFormat sdf_YYYY_MM_D = new SimpleDateFormat(YYYY_MM_D);
    private static SimpleDateFormat sdf_YYYY$MM$DD = new SimpleDateFormat(YYYY$MM$DD);
    private static SimpleDateFormat sdf_YYYY_MM_DD = new SimpleDateFormat(YYYY_MM_DD);

    private static SimpleDateFormat sdf_YYYY_MM_DD_HH_MM_SS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat sdf_YYYYMMDDHHMMSSSSS = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    private static SimpleDateFormat sdf_YYYYMMDDHHMMSS = new SimpleDateFormat("yyyyMMddHHmmss");
    private static SimpleDateFormat sdf_YYMMDDHHMMSS = new SimpleDateFormat("yyMMddHHmmss");
    private static SimpleDateFormat sdf_YYYY_MM_DD_HH_MM_SS_SSS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    private static SimpleDateFormat sdf_YYYY$MM$DD$HH$MM$SS = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private static SimpleDateFormat sdf_YYYY$MM$DD$HH$MM$SS$SSS = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");


    private static SimpleDateFormat sdf_YYYYMM01 = new SimpleDateFormat("yyyyMM01");

    private static SimpleDateFormat sdf_HH_MM_SS = new SimpleDateFormat("HH:mm:ss");

    //静态日历对象
    private static Calendar calendar = Calendar.getInstance();

    //日期单位
    public final static int UNIT_YEAR = Calendar.YEAR;
    public final static int UNIT_MONTH = Calendar.MONTH;
    public final static int UNIT_DAY = Calendar.DATE;
    public final static int UNIT_HOUR = Calendar.HOUR;
    public final static int UNIT_MINUTE = Calendar.MINUTE;
    public final static int UNIT_SECOND = Calendar.SECOND;



    /**
     * 返回日期增减
     * */
    public synchronized static int DateDiffDays(Date begDate,Date endDate){

        //得到两个日期相差的天数
        Date date1 = null;
        Date date2 = null;

        int days = 0;

        //容错处理
        if(begDate == null || endDate == null) return days;

        try {
            date1 = parseDateYYYYMMDD(getDateYYYYMMDD(begDate));
            date2 = parseDateYYYYMMDD(getDateYYYYMMDD(endDate));
            days = (int) ((date2.getTime() - date1.getTime()) / (24*3600*1000));
        } catch (ParseException e) {
            //内部异常，概率极低，不处理
        }

        return days;

    }


    /**
     * 返回日期增减
     * */
    public static synchronized Date dateAdd(Date targetDate,int unit,int num){
        if(targetDate == null) return targetDate;
        calendar.setTime(targetDate);
        calendar.add(unit, num);
        return calendar.getTime();
    }

    /*
     * 获取年月字符串
     * */
    public static synchronized String getYearMonthStr(Date date) throws ParseException {

        return  date==null?_NULLSTR:sdf_YYYYMM.format(date);

    }

    /*
     * 获取月字符串
     * */
    public static synchronized String getMonthStr(Date date) throws ParseException {

        return  date==null?_NULLSTR:sdf_MM.format(date);

    }

    /*
   * 获取年月字符串
   * */
    public static synchronized String getDayStr(Date date) throws ParseException {
        if(date==null) return _NULLSTR;
        calendar.setTime(date);
        return String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
    }


    public static synchronized String getHourStr(Date date) throws ParseException{
        if(date==null) return _NULLSTR;
        calendar.setTime(date);
        return String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
    }

    /**
     * 返回月日字符串
     * @param date
     * @param concat 月日字符串格式,如5.1 or 5-1
     * @return
     * @throws Exception
     *
     */
    public static  synchronized String getMMDDStr(Date date,String concat)throws Exception{
        if(date==null) return _NULLSTR;
        calendar.setTime(date);
        concat = concat == null ? _NULLSTR : concat;
        return (calendar.get(Calendar.MONTH) + 1)+concat+calendar.get(Calendar.DAY_OF_MONTH);
    }
    /*
     * 获取年字符串
     * */
    public static synchronized String getYearStr(Date date) throws ParseException {

        return  date==null?_NULLSTR:sdf_YYYY.format(date);

    }


    /*
    * 获取季度
    * */
    public static synchronized int getQuarter(Date date) throws ParseException {
        int season = 0;
        if(date==null) return season;
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH);
        switch (month) {
            case Calendar.JANUARY:
            case Calendar.FEBRUARY:
            case Calendar.MARCH:
                season = 1;
                break;
            case Calendar.APRIL:
            case Calendar.MAY:
            case Calendar.JUNE:
                season = 2;
                break;
            case Calendar.JULY:
            case Calendar.AUGUST:
            case Calendar.SEPTEMBER:
                season = 3;
                break;
            case Calendar.OCTOBER:
            case Calendar.NOVEMBER:
            case Calendar.DECEMBER:
                season = 4;
                break;
            default:
                break;
        }
        return season;

    }

    /*
    * 获取月的第一天
    * */
    public static synchronized Date getFirstDayOfMonth(Date date) throws ParseException {

        return  date==null?null:sdf_YYYYMMDD.parse(sdf_YYYYMM01.format(date));

    }

    /*
     * 获取月的最后一天,注意时间是0点
     * */
    public static synchronized Date getLastDayOfMonth(Date date) throws ParseException {
        if(date==null) return null;
        Date time = dateAdd(getFirstDayOfMonth(dateAdd(date, UNIT_MONTH, 1)), UNIT_DAY, -1);
        return  sdf_YYYYMMDD.parse(sdf_YYYYMMDD.format(time));
    }


    public static synchronized Date parseDateYYYYMMDDHHMMSSSSS(String date) throws ParseException {

        java.util.Date time = (date==null?null:sdf_YYYYMMDDHHMMSSSSS.parse(date));

        return time;

    }


    public static synchronized String getDateYYYYMMDDHHMMSSSSS(Date date) {

        return date==null?_NULLSTR:sdf_YYYYMMDDHHMMSSSSS.format(date);

    }

    public static synchronized Date parseDateYYYY$MM$DD$HH$MM$SS$SSS(String date) throws ParseException {

        java.util.Date time = (date==null?null:sdf_YYYY$MM$DD$HH$MM$SS$SSS.parse(date));

        return time;

    }


    public static synchronized String getDateYYYY$MM$DD$HH$MM$SS$SSS(Date date) {

        return date==null?_NULLSTR:sdf_YYYY$MM$DD$HH$MM$SS$SSS.format(date);

    }

    public static synchronized Date parseDateYYYY$MM$DD$HH$MM$SS(String date) throws ParseException {

        java.util.Date time = (date==null?null:sdf_YYYY$MM$DD$HH$MM$SS.parse(date));

        return time;

    }

    public static synchronized String getDateYYYY$MM$DD$HH$MM$SS(Date date) {

        return date==null?_NULLSTR:sdf_YYYY$MM$DD$HH$MM$SS.format(date);

    }

    public static synchronized Date parseDateYYYYMMDDHHMMSS(String date) throws ParseException {

        java.util.Date time =  (date==null?null:sdf_YYYYMMDDHHMMSS.parse(date));

        return time;

    }


    public static synchronized String getDateYYYYMMDDHHMMSS(Date date) {

        return date==null?_NULLSTR:sdf_YYYYMMDDHHMMSS.format(date);

    }


    /*
    *
    * yyyy-MM-dd HH:mm:ss.SSS
    * */
    public static synchronized Date parseDateYYYY_MM_DD_HH_MM_SS_SSS(String date) throws ParseException {

        java.util.Date time = (date==null?null:sdf_YYYY_MM_DD_HH_MM_SS_SSS.parse(date));

        return time;

    }

    /*
    * yyyyMMdd
    * */
    public static synchronized String getDateYYYY_MM_DD_HH_MM_SS_SSS(Date date) {

        return date==null?_NULLSTR:sdf_YYYY_MM_DD_HH_MM_SS_SSS.format(date);

    }

    /*
    *
    * yyyyMMdd
    * */
    public static synchronized Date parseDateYYYYMMDD(String date) throws ParseException {

        java.util.Date time = (date==null?null:sdf_YYYYMMDD.parse(date));

        return time;

    }

    /*
    * yyyy-MM-dd HH:mm:ss.SSS
    * */
    public static synchronized String getDateYYYYMMDD(Date date) {

        return date==null?_NULLSTR:sdf_YYYYMMDD.format(date);

    }


    /*
    *
    * yyyy/MM/dd
    * */
    public static synchronized Date parseDateYYYY$MM$DD(String date) throws ParseException {

        java.util.Date time = (date==null?null:sdf_YYYY$MM$DD.parse(date));

        return time;

    }

    /*
    * yyyy/MM/dd
    * */
    public static synchronized String getDateYYYY$MM$DD(Date date) {

        return date==null?_NULLSTR:sdf_YYYY$MM$DD.format(date);

    }

    /*
*
* yyyy/MM/dd
* */
    public static synchronized Date parseDateYYYY_MM_DD(String date) throws ParseException {

        java.util.Date time = (date==null?null:sdf_YYYY_MM_DD.parse(date));

        return time;

    }

    /*
    * yyyy/MM/dd
    * */
    public static synchronized String getDateYYYY_MM_DD(Date date) {

        return date==null?_NULLSTR:sdf_YYYY_MM_DD.format(date);

    }


    /*
    *
    * yyyy/MM/dd
    * */
    public static synchronized Date parseAnyDate(String date) throws ParseException {

        java.util.Date time = null;

        if(StringUtils.isBlank(date)) return null;

        if(date.length() == 8){
            try{
                time = sdf_YYYY_M_D.parse(date);
            }catch (Exception e1){
                try {
                    time = sdf_YYYY$M$D.parse(date);
                }catch (Exception e2){
                    time = sdf_YYYYMMDD.parse(date);
                }
            }
        }else if(date.length() == 9){
            try{
                time = sdf_YYYY_M_DD.parse(date);
            }catch (Exception e1){
                try {
                    time = sdf_YYYY$M$DD.parse(date);
                }catch (Exception e2){
                    try {
                        time = sdf_YYYY_MM_D.parse(date);
                    }catch (Exception e3){
                        time = sdf_YYYY$M$D.parse(date);
                    }
                }
            }
        }else if(date.length() <= 10){
            try{
                time = sdf_YYYY_MM_DD.parse(date);
            }catch (Exception e1){
                try {
                    time = sdf_YYYY$MM$DD.parse(date);
                }catch (Exception e2){
                    time = sdf_YYYYMMDD.parse(date);
                }
            }
        }else  if(date.length() == 14){
            time = sdf_YYYYMMDDHHMMSS.parse(date);
        }else  if(date.length() == 17){
            time = sdf_YYYYMMDDHHMMSSSSS.parse(date);
        }else  if(date.length() == 19){
            try {
                time = sdf_YYYY$MM$DD$HH$MM$SS.parse(date);
            }catch (Exception e1){
                time = sdf_YYYY_MM_DD_HH_MM_SS.parse(date);
            }
        }else if(date.length() > 19){
            try{
                time = sdf_YYYY_MM_DD_HH_MM_SS_SSS.parse(date);
            }catch (Exception e1){
                time = sdf_YYYY$MM$DD$HH$MM$SS$SSS.parse(date);
            }
        }else {
            throw new RuntimeException("不支持的时间格式！");
        }

        return time;

    }

    public static synchronized Date parseDateYYYY_MM_DD_HH_MM_SS(String date) throws ParseException {

        java.util.Date time = (date==null?null:sdf_YYYY_MM_DD_HH_MM_SS.parse(date));

        return time;

    }


    /*
    * yyyyMMdd
    * */
    public static synchronized String getDateYYYY_MM_DD_HH_MM_SS(Date date) {

        return date==null?_NULLSTR:sdf_YYYY_MM_DD_HH_MM_SS.format(date);

    }

    /*
    * yyyyMMdd
    * */
    public static synchronized String getDateHH_MM_SS(Date date) {

        return date==null?_NULLSTR:sdf_HH_MM_SS.format(date);

    }




    /**
     * 返回当前时间指定天数后的时间 可为负数
     * @param date
     * @param day
     * @return
     */
    public static Date getNextDay(Date date,int day) {
        if(date==null) return null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, +day);
        date = calendar.getTime();
        return date;
    }


    /*
    * 显示时间值：如耗时多少小时多少分钟...
    * */
    public static  String getTimeCHStr(long timeMillis) {

        StringBuffer timeStr = new StringBuffer();

        if(timeMillis >= 1000 * 60 * 60 * 24){
            timeStr.append(timeMillis/(1000 * 60 * 60 * 24)).append("天")
                    .append(TimeUtil.getTimeCHStr(timeMillis - (timeMillis/(1000 * 60 * 60 * 24)) * 1000 * 60 * 60 * 24));
        }else if(timeMillis >= 1000 * 60 * 60){
            timeStr.append(timeMillis/(1000 * 60 * 60)).append("小时")
                    .append(TimeUtil.getTimeCHStr(timeMillis - (timeMillis/(1000 * 60 * 60)) * 1000 * 60 * 60));
        }else if(timeMillis >= 1000 * 60){
            timeStr.append(timeMillis/(1000 * 60)).append("分钟")
                    .append(TimeUtil.getTimeCHStr(timeMillis - (timeMillis/(1000 * 60)) * 1000 * 60));
        }else if(timeMillis >= 1000){
            timeStr.append(timeMillis/1000).append("秒")
                    .append(TimeUtil.getTimeCHStr(timeMillis - (timeMillis/1000) * 1000));
        }else if(timeMillis >= 0){
            timeStr.append(timeMillis).append("毫秒");
        }else{
            timeStr.append(timeMillis).append("错误！");
        }

        return timeStr.toString();

    }

    //字符串转date
    public static Date StrToDate(String str,String type) {
        SimpleDateFormat format = new SimpleDateFormat(type);
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    /**
     * 日期转换成字符串
     * @param date
     * @return str
     */
    public static String DateToStr(Date date,String type) {
        SimpleDateFormat format = new SimpleDateFormat(type);
        String str = format.format(date);
        return str;
    }

    /**
     * date2比date1多的天数
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDays(Date date1,Date date2)
    {
        if(date1==null || date2==null) return 0;
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1= cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if(year1 != year2) //同一年
        {
            int timeDistance = 0 ;
            for(int i = year1 ; i < year2 ; i ++)
            {
                if(i%4==0 && i%100!=0 || i%400==0) //闰年
                {
                    timeDistance += 366;
                }
                else //不是闰年
                {
                    timeDistance += 365;
                }
            }

            return timeDistance + (day2-day1) ;
        }
        else //不同年
        {
            return day2-day1;
        }
    }

    /**
     * 计算2个时间相差的月数
     * @param date1
     * @param date2
     * @param pattern
     *      时间格式
     * @param endDate
     *      是否包含结束日期
     * @return
     * @throws Exception
     */
    public static int countMonths(String date1,String date2,String pattern,boolean endDate)throws Exception{
        int count = 0;
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        c1.setTime(sdf.parse(date1));
        c2.setTime(sdf.parse(date2));

        int year = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);

        //开始日期若小月结束日期
        if(year < 0){
            year =- year;
            count = year * 12 + c1.get(Calendar.MONTH) - c2.get(Calendar.MONTH);
        }else{
            count = year * 12 + c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
        }
        return endDate ? count + 1 : count;
    }

    public static int countYears(String date1,String date2,String pattern,boolean endDate)throws Exception{
        int count = 0;
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        c1.setTime(sdf.parse(date1));
        c2.setTime(sdf.parse(date2));
        count = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
        return endDate ? count + 1 : count;
    }

    //查询指定日期是否为周末
    public static boolean isWeekend(Date date) throws Exception{
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if(cal.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY||cal.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY){
            return true;
        }
        else return false;
    }

    //验证字符串是否为合法的日期
    public static boolean isValidDate(String str) {
        boolean convertSuccess=true;
        // 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            // e.printStackTrace();
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            convertSuccess=false;
        }
        return convertSuccess;
    }


    /*
    * 获取上期时间天数
    * */
    public static int getBeforeDaysForLastTerm(Date begin,Date end) throws ParseException {

        begin = TimeUtil.parseDateYYYY$MM$DD(TimeUtil.getDateYYYY$MM$DD(begin));
        end = TimeUtil.parseDateYYYY$MM$DD(TimeUtil.getDateYYYY$MM$DD(end));

        if(begin.getTime() > end.getTime()) return 7;
        Date tempDate = dateAdd(begin, UNIT_DAY, 6);

        if(tempDate.getTime() > end.getTime()) return 7;

        tempDate = dateAdd(begin,UNIT_DAY,30);

        if(tempDate.getTime() > end.getTime())
            return  differentDays(dateAdd(begin, UNIT_MONTH, -1),begin);

        return differentDays(dateAdd(begin, UNIT_YEAR, -1),begin);

    }

    public static  void start() {

        local.set(System.currentTimeMillis());

    }


    public static String stop(){

        return getTimeCHStr(System.currentTimeMillis() - local.get());


    }


    public static synchronized String getDateYYMMDDHHMMSS(Date date) {

        return date==null?_NULLSTR:sdf_YYMMDDHHMMSS.format(date);

    }


    /**
     * @Description:
     * @param: stype 1.起始时间 2.结束日期
     * @return:
     * @author Gaodongdong
     * @createDate: 2018-11-29 16:06
     * @modifyDate: 2018-11-29 16:06
     */
    public static synchronized Object parseReportDate(String dateStr,int stype) throws Exception {

        Object retObj = null;
        int length = dateStr.trim().length();

        switch (length){
            case 4:
                retObj = stype == 1?Long.parseLong(dateStr):Long.parseLong(dateStr)+1;
                break;
            case 7:
                dateStr = dateStr.concat("-01");
                retObj = Long.parseLong(TimeUtil.getYearMonthStr(stype == 1?TimeUtil.parseAnyDate(dateStr):TimeUtil.dateAdd(TimeUtil.parseAnyDate(dateStr),TimeUtil.UNIT_MONTH,1)));
                break;
            case 10:
                retObj = stype == 1?TimeUtil.parseAnyDate(dateStr):TimeUtil.dateAdd(TimeUtil.parseAnyDate(dateStr),TimeUtil.UNIT_DAY,1);
                break;
           default:
               retObj = stype == 1?TimeUtil.parseAnyDate(dateStr):TimeUtil.dateAdd(TimeUtil.parseAnyDate(dateStr),TimeUtil.UNIT_DAY,1);
            break;
        }
       return retObj;
    }
    
    
    /**
     * @Description:入参年份有多少天
     * @param:
     * @return: 
     * @author Gaodongdong
     * @createDate: 2018-12-25 19:06
     * @modifyDate: 2018-12-25 19:06
     */
    public synchronized static int getYearDays(String year) throws Exception {
        if(StringUtils.isBlank(year) || year.length() != 4) throw new MessageException("传入年参数异常！");
        return Integer.parseInt(getDayStr(dateAdd(parseDateYYYYMMDD(year.concat("0301")), UNIT_DAY, -1))) + 337;
    }

    /**
     * @Description:入参月份有多少天
     * @param:
     * @return:
     * @author Gaodongdong
     * @createDate: 2018-12-25 19:06
     * @modifyDate: 2018-12-25 19:06
     */
    public synchronized static int getYearMonthDays(String yearMonth) throws Exception {
        if(StringUtils.isBlank(yearMonth) || yearMonth.length() != 6) throw new MessageException("传入年月参数异常！");
        return Integer.parseInt(getDayStr(dateAdd(dateAdd(parseAnyDate(yearMonth.concat("01")), UNIT_MONTH, 1), UNIT_DAY, -1)));
    }

    /**
     * @Description:日期是当年的第几天
     * @param:
     * @return:
     * @author Gaodongdong
     * @createDate: 2018-12-27 13:57
     * @modifyDate: 2018-12-27 13:57
     */
    public synchronized static int getYearDayNumber(Date date) throws Exception {
        int yearMonth = Integer.parseInt(TimeUtil.getYearMonthStr(date));
        int yearMonthBeg = Integer.parseInt(TimeUtil.getYearStr(date).concat("01"));
        int days = 0;
        for(int i = yearMonthBeg;i< yearMonth;i++){
            days += getYearMonthDays(String.valueOf(i));
        }
        days += Integer.parseInt(getDayStr(date));
        return  days;
    }


}