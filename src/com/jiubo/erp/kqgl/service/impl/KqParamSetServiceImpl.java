package com.jiubo.erp.kqgl.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiubo.erp.common.MessageException;
import com.jiubo.erp.kqgl.bean.AttRuleTypeBean;
import com.jiubo.erp.kqgl.bean.AttShiftGroupBean;
import com.jiubo.erp.kqgl.bean.AttShiftScheduleBean;
import com.jiubo.erp.kqgl.dao.KqParamSetDao;
import com.jiubo.erp.kqgl.service.KqParamSetService;
import com.jiubo.erp.kqgl.vo.Vacation;

@Service
public class KqParamSetServiceImpl implements KqParamSetService{
	
	@Autowired
	private KqParamSetDao kqParamSetDao;
	
	@Override
	public List<Vacation> queryVacation() {
		return kqParamSetDao.queryVacation();
	}

	@Override
	public void updateVacation(Vacation vacation) throws MessageException {
		kqParamSetDao.updateVacation(vacation);
	}

	@Override
	public void addVacation(Vacation vacation) {
		kqParamSetDao.addVacation(vacation);
	}

	@Override
	public void deleteVacation(int id) throws MessageException {
		kqParamSetDao.deleteVacation(id);
	}

	@Override
	public List<AttRuleTypeBean> queryAttRuleType() {
		return kqParamSetDao.queryAttRuleType();
	}

	@Override
	public void addAttRuleType(AttRuleTypeBean attRuleType) {
		kqParamSetDao.addAttRuleType(attRuleType);
	}

	@Override
	public void deleteAttRuleType(int id) {
		kqParamSetDao.deleteAttRuleType(id);
	}

	@Override
	public void updateAttRuleType(AttRuleTypeBean attRuleType) {
		kqParamSetDao.updateAttRuleType(attRuleType);
	}

	@Override
	public List<AttShiftScheduleBean> queryAttShiftSchedule() {
		return kqParamSetDao.queryAttShiftSchedule();
	}

	@Override
	public void addAttShiftSchedule(AttShiftScheduleBean attShiftSchedule) {
		kqParamSetDao.addAttShiftSchedule(attShiftSchedule);
	}

	@Override
	public void deleteAttShiftSchedule(int id) {
		kqParamSetDao.deleteAttShiftSchedule(id);
	}

	@Override
	public void updateAttShiftSchedule(AttShiftScheduleBean attShiftSchedule) {
		kqParamSetDao.updateAttShiftSchedule(attShiftSchedule);
	}

	@Override
	public List<AttShiftGroupBean> queryAttShiftGroup() {
		return kqParamSetDao.queryAttShiftGroup();
	}

	@Override
	public void addAttShiftGroup(AttShiftGroupBean attShiftGroupBean) {
		kqParamSetDao.addAttShiftGroup(attShiftGroupBean);
	}

	@Override
	public void deleteAttShiftGroup(int id) {
		kqParamSetDao.deleteAttShiftGroup(id);
	}

	@Override
	public void updateAttShiftGroup(AttShiftGroupBean attShiftGroupBean) {
		kqParamSetDao.updateAttShiftGroup(attShiftGroupBean);
	}

}
