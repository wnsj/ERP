package com.jiubo.erp.kqgl.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiubo.erp.kqgl.dao.KqParamSetDao;
import com.jiubo.erp.kqgl.service.KqParamSetService;
import com.jiubo.erp.kqgl.vo.AttRuleType;
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
	public void updateVacation(Vacation vacation) throws Exception {
		kqParamSetDao.updateVacation(vacation);
	}

	@Override
	public void addVacation(Vacation vacation) {
		kqParamSetDao.addVacation(vacation);
	}

	@Override
	public void deleteVacation(int id) throws Exception {
		kqParamSetDao.deleteVacation(id);
	}

	@Override
	public List<AttRuleType> queryAttRuleType() {
		return kqParamSetDao.queryAttRuleType();
	}

	@Override
	public void addAttRuleType(AttRuleType attRuleType) {
		kqParamSetDao.addAttRuleType(attRuleType);
	}

	@Override
	public void deleteAttRuleType(int id) {
		kqParamSetDao.deleteAttRuleType(id);
	}

	@Override
	public void updateAttRuleType(AttRuleType attRuleType) {
		kqParamSetDao.updateAttRuleType(attRuleType);
	}

	
	
}
