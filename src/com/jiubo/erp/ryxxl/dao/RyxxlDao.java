package com.jiubo.erp.ryxxl.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jiubo.erp.ryxxl.bean.DepartmentAttendanceBean;
import com.jiubo.erp.ryxxl.bean.OutEmpBean;
import com.jiubo.erp.ryxxl.bean.ZpxgpgBean;

public interface RyxxlDao {
	
	//查询工龄，学历，年龄，性别报表
	public Map<String,Object> queryZzryReport(@Param("date") String date,@Param("flag")String flag);
	
	//查询学历
	public Map<String,Object> queryEducation(@Param("date") String date,@Param("flag")String flag);
	
	//查询岗位报表
	public List<Map<String,Object>> queryPositionReport(@Param("date") String date,@Param("flag")String flag);
	
	//查询离职员工工龄
	public Map<String,Object> queryOutEmpWork(@Param("begDate") String begDate,@Param("endDate") String endDate,@Param("deptId") String deptId,@Param("posId")String[] posId);
	
	//查询离职员工学历
	public Map<String,Object> queryOutEmpEducation(@Param("begDate") String begDate,@Param("endDate") String endDate,@Param("deptId") String deptId,@Param("posId")String[] posId);

	//查询不同岗位类型离职人数 
	public List<Map<String,Object>> queryOutEmpPositionType(@Param("begDate") String begDate,@Param("endDate") String endDate);
	
	//查询重点岗位
	public List<Map<String,Object>> queryPositionDataIsPoint();
	
	//查询具体岗位离职人数
	public List<Map<String,Object>> queryOutEmpCount(@Param("id")String id,@Param("begDate") String begDate,@Param("endDate") String endDate);
	
	//查询所有岗位离职人数
	public List<OutEmpBean> queryOutEmpAllCount(@Param("begDate") String begDate,@Param("endDate") String endDate);
	
	//查询每个部门离职人数
	public List<OutEmpBean> queryOutEmpByDept(@Param("begDate") String begDate,@Param("endDate") String endDate);

	//查询异动
	public List<OutEmpBean> queryOutEmpChangeByDept(@Param("begDate") String begDate,@Param("endDate") String endDate);

	//查询月初人数，月末人数，本月入职人数
	public List<OutEmpBean> queryEmpCount(@Param("begDate") String begDate,@Param("endDate") String endDate);
	
	//查询每个部门岗位缺编人数，月初人数，计划招聘数，到面人数，合格人数，入职人数，月末人数
	public List<ZpxgpgBean> queryRecruitByDeptPosId(@Param("begDate") String begDate,@Param("endDate") String endDate);

	//查询合格人数，面试人数，入职人数
	public List<ZpxgpgBean> queryRecruitChannel(@Param("begDate") String begDate,@Param("endDate") String endDate);
	
	//查询每个部门员工的打卡天数
	public List<OutEmpBean> queryDeptEmpClock(@Param("deptId") String deptId,@Param("begDate") String begDate,@Param("isEntry")String isEntry,@Param("isQuit")String isQuit);

	//查询每个部门未打卡天数
	public List<DepartmentAttendanceBean> queryDepartmentAttendance(@Param("deptId") String deptId,@Param("begDate") String begDate);
	
	//查询每个部门正常休假天数
	public List<OutEmpBean> queryDeptVacation(@Param("deptId") String deptId,@Param("begDate") String begDate,@Param("isEntry")String isEntry,@Param("isQuit")String isQuit);
	
	//修改或添加部门未打卡信息
	public void updateAddDeptAttendance(DepartmentAttendanceBean departmentAttendanceBean);
}