package com.jiubo.erp.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class TestClass {

	public static void main(String[] args) throws Exception{
	/*	String startTime = "2017-01-02 08:30:00";
		String endTime = "2017-01-02 17:30:00";
//		Date begDate = TimeUtil.parseAnyDate(startTime);
//		Date endDate = TimeUtil.parseAnyDate(endTime);
//		int days = TimeUtil.DateDiffDays(begDate, endDate);
//		double hour = TimeUtil.DateDiffHours(begDate, endDate);
//		double hour = TimeUtil.getHourHex(begDate);
//		System.out.println("hour:"+hour);
//		System.out.println(TimeUtil.parseAnyDate("2018-05"));
		
		int a = 13;
		int b = 20;
		System.out.println(a / (double)b);
		
//		List<Integer> list = new ArrayList<Integer>();
//		for(int i = 0;i < 10;i++) {
//			list.add(i);
//		}
		List<Integer> list = Arrays.asList(5,2,4,9,1,-2,0,-6,2,1,7,4,3,2);
		Collections.sort(list, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				
				return o2 - o1;
			}
		});
		System.out.println("list:"+list);
		*/
		/*
		 * int[] arr = {1,0,0 ,1,0,0 ,1,0,0 ,1,0,0 ,3,0,0 ,1,0,0 ,2,0,0 ,1,0,0 ,0,1,0
		 * ,1,0,0 ,1,0,0 ,3,0,0 ,6,0,0 ,0,1,0 ,1,0,0 ,1,0,0 ,3,0,0 ,0,1,0}; int sum =0;
		 * for(int i : arr) { sum += i; } System.out.println("sum:"+sum);
		 * 
		 * int a = 6; int b = 0; System.out.println(b == 0 ? 0 : a / b);
		 * 
		 * double d = 10; System.out.println(DoubleUtil.roundByScale(Double.valueOf(29)
		 * / Double.valueOf(10) * 100, 2));
		 * System.out.println(DoubleUtil.roundByScale(29 / d * 100, 2));
		 */
//		test();
		
		float x = 1, y = 2,  z = 3;
		//y += z-- / ++x;
		y = z-- / ++x;
		System.out.println(y);

	}
	
	public static void test() throws Exception {
		String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String url = "jdbc:sqlserver://172.16.1.5:1433;databaseName=ZZZ_ERP_20190516";
		String userName = "sa";
		String password = "Jiubo@200906";
		String sql = "SELECT * FROM T_DEPARTMENT_ATTENDANCE  WHERE ID = NULL ";
		Class.forName(driver);
		Connection conn =  DriverManager.getConnection(url, userName, password);
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		int i = rs.getMetaData().getColumnCount();
		while(rs.next()) {
			for(int x = 1;x < i; x++) {
				System.out.println(rs.getString(x));
			}
		}
	}
}
/*
 Date date = new Date();
		SimpleDateFormat dateFm = new SimpleDateFormat("EEEE");
		String currSun = dateFm.format(date);
		System.out.println(currSun);
 */
