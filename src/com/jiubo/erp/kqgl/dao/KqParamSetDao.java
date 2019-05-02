package com.jiubo.erp.kqgl.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jiubo.erp.kqgl.bean.AttRuleTypeBean;
import com.jiubo.erp.kqgl.bean.AttShiftGroupBean;
import com.jiubo.erp.kqgl.bean.AttShiftScheduleBean;
import com.jiubo.erp.kqgl.vo.Vacation;

public interface KqParamSetDao {
	
	//查询假期种类
	public List<Vacation> queryVacation();
	
	//修改假期种类
	public int updateVacation(@Param("vacation")Vacation vacation);
	
	//删除假期种类
	public int deleteVacation(int id);
	
	//添加假期种类
	public int addVacation(@Param("vacation") Vacation vacation);
	
	//查询考勤规则
	public List<AttRuleTypeBean> queryAttRuleType();
	
	//添加考勤规则
	public int addAttRuleType(@Param("attRuleType")AttRuleTypeBean attRuleType);
	
	//删除考勤规则
	public void deleteAttRuleType(int id);
	
	//修改考勤规则
	public void updateAttRuleType(@Param("attRuleType")AttRuleTypeBean attRuleType);
	
	//查询班次
	public List<AttShiftScheduleBean> queryAttShiftSchedule();
	
	//添加班次
	public void addAttShiftSchedule(@Param("attShiftSchedule")AttShiftScheduleBean attShiftSchedule);
	
	//删除班次
	public void deleteAttShiftSchedule(int id);
	
	//修改班次
	public void updateAttShiftSchedule(@Param("attShiftSchedule")AttShiftScheduleBean attShiftSchedule);
	
	//查询班组
	public List<AttShiftGroupBean> queryAttShiftGroup();
	
	//添加班组
	public void addAttShiftGroup(@Param("attShiftGroupBean")AttShiftGroupBean attShiftGroupBean);
	
	//删除班组
	public void deleteAttShiftGroup(int id);
	
	//修改班组
	public void updateAttShiftGroup(@Param("attShiftGroupBean")AttShiftGroupBean attShiftGroupBean);
}
