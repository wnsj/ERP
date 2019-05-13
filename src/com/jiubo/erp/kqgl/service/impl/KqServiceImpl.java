package com.jiubo.erp.kqgl.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiubo.erp.kqgl.bean.AttLeaveTypeBean;
import com.jiubo.erp.kqgl.bean.AttRuleTypeBean;
import com.jiubo.erp.kqgl.bean.AttShiftGroupBean;
import com.jiubo.erp.kqgl.bean.AttShiftScheduleBean;
import com.jiubo.erp.kqgl.bean.PersonalKQBean;
import com.jiubo.erp.kqgl.bean.PositionDataBean;
import com.jiubo.erp.kqgl.bean.PositionTypeBean;
import com.jiubo.erp.kqgl.dao.kqglDao;
import com.jiubo.erp.kqgl.service.KqService;
import com.jiubo.erp.kqgl.vo.ClassTime;
import com.jiubo.erp.kqgl.vo.DepartKQ;
import com.jiubo.erp.kqgl.vo.KqInfoResult;
import com.jiubo.erp.kqgl.vo.PositionResultInfo;
import com.jiubo.erp.kqgl.vo.PunchRecord;
import com.jiubo.erp.kqgl.vo.ScheduleQueryResult;
import com.jiubo.erp.rygl.bean.DepartmentBean;
@Service
@Transactional
public class KqServiceImpl implements KqService {
	
	@Autowired
	private kqglDao dao;
	
	@Override
	public List<KqInfoResult> selectKqInfoList(KqInfoResult kResult){
		List<KqInfoResult> kqInfoList = this.dao.selectKqInfoList(kResult);
		return kqInfoList;
	}
	
	@Override
	public List<KqInfoResult> searchKqInfoList(KqInfoResult kResult){
		List<KqInfoResult> kqInfoList = this.dao.searchKqInfoList(kResult);
		return kqInfoList;
	}
	
	@Override
	public List<ClassTime> selectClassTimeList(ClassTime ct){
		return this.dao.selectClassTimeList(ct);
	}
	
	@Override
	public List<DepartKQ> selectDepartKqInfoList() {
		// TODO Auto-generated method stub
		return this.dao.selectDepartKqInfoList();
	}

	@Override
	public List<DepartKQ> searchDepartKqInfoList(DepartKQ departKQ) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<PunchRecord> selectPunchRecordList(PunchRecord pRecord){
		
		return dao.selectPunchRecordList(pRecord);
	}

	/* (non-Javadoc)
	 * @see com.jiubo.erp.kqgl.service.KqService#kqTableInfoList(com.jiubo.erp.kqgl.bean.PersonalKQBean)
	 */
	@Override
	public List<KqInfoResult> kqTableInfoList(KqInfoResult kr) {
		// TODO Auto-generated method stub
		return this.dao.kqTableInfoList(kr);
	}

	
}
