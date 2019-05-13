package com.jiubo.erp.kqgl.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

public interface KqService {
	    //查询考勤人员基础信息列表
		List<KqInfoResult> selectKqInfoList(KqInfoResult kResult);
		//查询考勤人员班次信息列表
		List<KqInfoResult> searchKqInfoList(KqInfoResult kResult);
		//查询个人班次列表
		List<ClassTime> selectClassTimeList(ClassTime ct);
		//查询打卡信息
		List<PunchRecord> selectPunchRecordList(PunchRecord pRecord);
		
		//查询部门列表
		List<DepartKQ> selectDepartKqInfoList();
		//搜索部门考勤
		List<DepartKQ> searchDepartKqInfoList(DepartKQ departKQ);
		
		//考勤报表
		List<KqInfoResult> kqTableInfoList(KqInfoResult kr);
}
