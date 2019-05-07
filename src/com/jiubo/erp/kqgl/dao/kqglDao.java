package com.jiubo.erp.kqgl.dao;

import java.util.List;

import com.jiubo.erp.kqgl.bean.AttLeaveTypeBean;
import com.jiubo.erp.kqgl.bean.AttRuleTypeBean;
import com.jiubo.erp.kqgl.bean.AttShiftScheduleBean;
import com.jiubo.erp.kqgl.bean.AttShiftGroupBean;
import com.jiubo.erp.kqgl.bean.PositionDataBean;
import com.jiubo.erp.kqgl.bean.PositionTypeBean;
import com.jiubo.erp.kqgl.vo.ClassTime;
import com.jiubo.erp.kqgl.vo.DepartKQ;
import com.jiubo.erp.kqgl.vo.KqInfoResult;
import com.jiubo.erp.kqgl.vo.PositionResultInfo;
import com.jiubo.erp.kqgl.vo.PunchRecord;
import com.jiubo.erp.kqgl.vo.ScheduleQueryResult;
import com.jiubo.erp.rygl.bean.DepartmentBean;

public interface kqglDao {
	//加载考勤基础信息列表
	List<KqInfoResult> selectKqInfoList(KqInfoResult kResult);
	//搜索考勤信息列表
	List<KqInfoResult> searchKqInfoList(KqInfoResult kResult);
	//查询班次列表
	List<ClassTime> selectClassTimeList(ClassTime ct);
	
	//查询考勤信息
	List<PunchRecord> selectPunchRecordList(PunchRecord pRecord);
	
	//查询所有部门
	List<DepartKQ> selectDepartKqInfoList();
	//搜索部门考勤
	List<DepartKQ> searchDepartKqInfoList(DepartKQ departKQ);
	
	//加载岗位类型列表
	List<PositionTypeBean> selectPositionTypeList();
	//修改
	int updatePositionType(PositionTypeBean bean);
	//删除
	int DeletePositionType(PositionTypeBean bean);
	//新增
	int addPositionType(PositionTypeBean bean);
	//职务信息列表加载
	List<PositionResultInfo> selectPositionInfo();
	//添加职位信息
	int addPositionInfo(PositionDataBean input);
	//加载班组列表
	List<AttShiftGroupBean> selectAttshiftGroupList();
	//添加班组
	int addAttShiftGroup(AttShiftGroupBean input);
	//加载假期种类列表
	List<AttLeaveTypeBean> selectAttLeaveTypelist();
	//添加假期种类
	int addAttLeaveType(AttLeaveTypeBean bean);
    //加载部门列表
	List<DepartmentBean> selectDepartMentInfo();
	//新增部门
    int addDeapartment(DepartmentBean input);
    //加载排班规则信息
    List<ScheduleQueryResult> selectScheduleResult();
    //新增排班规则
    int addAttShiftSchedule(AttShiftScheduleBean ShiftSchedule);
    //查询出勤规则列表
    List<AttRuleTypeBean> selectRultTypeInfo();
    //新增出勤规则
    int addRuleTypeInfo(AttRuleTypeBean bean); 
}
