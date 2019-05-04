package com.jiubo.erp.kqgl.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.jiubo.erp.common.MessageException;
import com.jiubo.erp.common.Position;
import com.jiubo.erp.kqgl.bean.AttRuleTypeBean;
import com.jiubo.erp.kqgl.bean.AttShiftGroupBean;
import com.jiubo.erp.kqgl.bean.AttShiftScheduleBean;
import com.jiubo.erp.kqgl.bean.PositionDataBean;
import com.jiubo.erp.kqgl.bean.PositionTypeBean;
import com.jiubo.erp.kqgl.dao.KqParamSetDao;
import com.jiubo.erp.kqgl.service.KqParamSetService;
import com.jiubo.erp.kqgl.vo.Vacation;
import com.jiubo.erp.rygl.bean.DepartmentBean;
import com.jiubo.erp.rygl.bean.EmployeeBasicBean;

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
	public List<PositionTypeBean> queryPositionType() throws MessageException {
		return kqParamSetDao.queryPositionType();
	}

	@Override
	public void addPositionType(PositionTypeBean positionTypeBean) throws MessageException {
		kqParamSetDao.addPositionType(positionTypeBean);
	}

	@Override
	public void deletePositionType(int id) throws MessageException {
		kqParamSetDao.deletePositionType(id);
	}

	@Override
	public void updatePositionType(PositionTypeBean positionTypeBean) throws MessageException {
		kqParamSetDao.updatePositionType(positionTypeBean);
	}

	@Override
	public List<Map<String,Object>> queryPositionData() throws MessageException {
		return kqParamSetDao.queryPositionData();
	}

	@Override
	public void addPositionData(PositionDataBean positionDataBean) throws MessageException {
		if(StringUtils.isBlank(positionDataBean.getPosition_Name()))
			throw new MessageException("职位名为空！");
		kqParamSetDao.addPositionData(positionDataBean);
	}

	@Override
	public void updatePositionData(PositionDataBean positionDataBean) throws MessageException {
		if(StringUtils.isBlank(positionDataBean.getPosition_ID()) || StringUtils.isBlank(positionDataBean.getPosition_Name()))
			throw new MessageException("职位id或职位名为空！");
		kqParamSetDao.updatePositionData(positionDataBean);
	}

	@Override
	public List<Map<String,Object>> queryDepartmentEmployee() {
		
		List<Map<String,Object>> oneList = new ArrayList<Map<String,Object>>();
		List<DepartmentBean> departmentList = queryDepartmentByPId("0");
		for(DepartmentBean departmentBean : departmentList) {
			//第一层
			Map<String,Object> beanMap = new HashMap<String,Object>();
			beanMap.put("depId", departmentBean.getID());
			beanMap.put("depName", departmentBean.getName());
			beanMap.put("depParentID",departmentBean.getParentID());
			beanMap.put("depOrderNum", departmentBean.getOrderNum());
			beanMap.put("employees",queryEmployeeBasic(departmentBean.getID(),"1"));//部门下的员工
			
			List<Map<String,Object>> twoList = new ArrayList<Map<String,Object>>();
			List<DepartmentBean> sonList = queryDepartmentByPId(departmentBean.getID());
			for(DepartmentBean bean : sonList) {
				//第二层
				Map<String,Object> twoMap = new HashMap<String,Object>();
				twoMap.put("depId", bean.getID());
				twoMap.put("depName", bean.getName());
				twoMap.put("depParentID",bean.getParentID());
				twoMap.put("depOrderNum", bean.getOrderNum());
				twoMap.put("employees",queryEmployeeBasic(departmentBean.getID(),"1"));//部门下的员工
				twoList.add(twoMap);
				
				List<Map<String,Object>> threeList = new ArrayList<Map<String,Object>>();
				List<DepartmentBean> sList = queryDepartmentByPId(bean.getID());
				for(DepartmentBean depBean : sList) {
					//第三层
					Map<String,Object> threeMap = new HashMap<String,Object>();
					threeMap.put("depId", depBean.getID());
					threeMap.put("depName", depBean.getName());
					threeMap.put("depParentID",depBean.getParentID());
					threeMap.put("depOrderNum", depBean.getOrderNum());
					threeMap.put("employees",queryEmployeeBasic(departmentBean.getID(),"1"));//部门下的员工
				}
				beanMap.put("sonDep", threeList);//子部门
			}
			
			beanMap.put("sonDep", twoList);//子部门
			oneList.add(beanMap);
		}
		return oneList;
	}
	
	public List<DepartmentBean> queryDepartmentByPId(String pId){
		return kqParamSetDao.queryDepartmentByPId(pId);
	}
	
	public List<EmployeeBasicBean> queryEmployeeBasic(String departmentId,String state){
		return kqParamSetDao.queryEmployeeBasic(departmentId, state);
	}

}
