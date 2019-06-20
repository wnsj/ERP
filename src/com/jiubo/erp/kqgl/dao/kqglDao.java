package com.jiubo.erp.kqgl.dao;

import java.util.List;

import com.jiubo.erp.kqgl.bean.AttLeaveTypeBean;
import com.jiubo.erp.kqgl.bean.AttRuleTypeBean;
import com.jiubo.erp.kqgl.bean.AttShiftScheduleBean;
import com.jiubo.erp.kqgl.bean.PersonalKQBean;
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
	
	//考勤报表
	List<KqInfoResult> kqTableInfoList(KqInfoResult kr);
	
	//考勤报表统计
	List<KqInfoResult> kqTableCountList(KqInfoResult kr);
	
	List<KqInfoResult> selectKIGList(KqInfoResult kResult);
	
}
