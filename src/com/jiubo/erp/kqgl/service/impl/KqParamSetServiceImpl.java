package com.jiubo.erp.kqgl.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiubo.erp.common.MessageException;
import com.jiubo.erp.common.Position;
import com.jiubo.erp.kqgl.bean.AttRuleTypeBean;
import com.jiubo.erp.kqgl.bean.AttShiftGroupBean;
import com.jiubo.erp.kqgl.bean.AttShiftScheduleBean;
import com.jiubo.erp.kqgl.dao.KqParamSetDao;
import com.jiubo.erp.kqgl.service.KqParamSetService;
import com.jiubo.erp.kqgl.vo.Vacation;
import com.jiubo.erp.rygl.bean.DepartmentBean;

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

	@Override
	public List<Map<String, Object>> queryDepartment() throws MessageException {
		return kqParamSetDao.queryDepartment();
	}

	@Override
	public void addDepartment(DepartmentBean departmentBean) throws MessageException {
		if(StringUtils.isBlank(departmentBean.getName()))throw new MessageException("部门名称不能为空!");
		if(StringUtils.isBlank(departmentBean.getParentID()))departmentBean.setParentID("0");
		if(StringUtils.isBlank(departmentBean.getOrderNum()))departmentBean.setOrderNum("0");
		kqParamSetDao.addDepartment(departmentBean);
	}

	@Override
	public void deleteDepartment(int id) throws MessageException {
		if(id <= 0)throw new MessageException("部门id不能为空！");
		kqParamSetDao.deleteDepartment(id);
	}

	@Override
	public void updateDepartment(DepartmentBean departmentBean) throws MessageException {
		if(StringUtils.isBlank(departmentBean.getName()) || StringUtils.isBlank(departmentBean.getID()))throw new MessageException("部门名或部门id为空！");
		kqParamSetDao.updateDepartment(departmentBean);
	}

	@Override
	public List<Position> queryPosition() throws MessageException {
		return kqParamSetDao.queryPosition();
	}

	@Override
	public void addPosition(Position position) throws MessageException {
//		if(StringUtils.isBlank(position.))
		
	}

	@Override
	public void updatePosition(Position position) throws MessageException {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
