package com.jiubo.erp.kqgl.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jiubo.erp.kqgl.vo.AttRuleType;
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
	public List<AttRuleType> queryAttRuleType();
	
	//添加考勤规则
	public int addAttRuleType(@Param("attRuleType")AttRuleType attRuleType);
	
	//删除考勤规则
	public void deleteAttRuleType(int id);
	
	//修改考勤规则
	public void updateAttRuleType(@Param("attRuleType")AttRuleType attRuleType);
}
