package com.jiubo.erp.wzbg.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiubo.erp.common.MessageException;
import com.jiubo.erp.common.TimeUtil;

import com.jiubo.erp.wzbg.bean.ApprovalBaoBeiBean;
import com.jiubo.erp.wzbg.bean.LeavePrepareBean;
import com.jiubo.erp.wzbg.dao.WzbgDao;
import com.jiubo.erp.wzbg.service.WzbgService;
import com.jiubo.erp.wzbg.vo.AccWithApprovalLeaveAuth;
import com.jiubo.erp.wzbg.vo.DeptWithEmp;

/**
 * @version: V1.0
 * @author: DingDong
 * @className: 
 * @description: 无纸化办公业务实现层
 * @data: 2019-06-29
 **/
@Service
@Transactional
public class WzbgServiceImpl implements WzbgService {
	
	private final static Logger logger  = LoggerFactory.getLogger(WzbgServiceImpl.class);
	
	@Autowired
	private WzbgDao wzbgDao;
	
	/**
	 * @author: DingDong
	 * @date: 2019年7月1日
	 * @version: V1.0
	 */
	@Override
	public List<DeptWithEmp> queryEmpInfoByDept(String id) throws MessageException {
		List<DeptWithEmp> list = wzbgDao.queryEmpInfoByDept(id);
		return list;
	}
	
	/**
	 * @Description: 添加请假报备
	 * @param  leavePrepareBean(请假报备实体)
	 * @return 
	 * @author: DingDong
	 * @date: 2019年06月29日
	 * @version: V1.0
	 */
	@Override
	public void addLeavePrepareBean(LeavePrepareBean leavePrepareBean) throws MessageException {
		logger.info("---------------开始执行addLeavePrepareBean方法-------------------");
		if(!StringUtils.isBlank(leavePrepareBean.getType())) {
			logger.info("---------------开始假期类型-------------------");
			switch (leavePrepareBean.getType()) {
				case "1":
					leavePrepareBean.setType("病假");
					break;
				case "2":
					leavePrepareBean.setType("事假");
					break;
				case "3":
					leavePrepareBean.setType("婚嫁");
					break;
				case "4":
					leavePrepareBean.setType("产检");
					break;
				case "5":
					leavePrepareBean.setType("产假");
					break;
				case "6":
					leavePrepareBean.setType("哺乳假");
					break;
				case "7":
					leavePrepareBean.setType("丧假");
					break;
				case "8":
					leavePrepareBean.setType("倒休");
					break;
				case "9":
					leavePrepareBean.setType("其他");
					break;
			}
		}
		if(!StringUtils.isBlank(leavePrepareBean.getStartTime())) {
			logger.info("---------------开始转换startTme格式-------------------");
			String startTime = TimeUtil.convertDateT(leavePrepareBean.getStartTime());
			leavePrepareBean.setStartTime(startTime);
		}
		if(!StringUtils.isBlank(leavePrepareBean.getEndTime())) {
			logger.info("---------------开始转换endTime格式-------------------");
			String endTime = TimeUtil.convertDateT(leavePrepareBean.getEndTime());
			leavePrepareBean.setEndTime(endTime);
		}
		wzbgDao.addLeavePrepare(leavePrepareBean);
	}
	
	/**
	 * @Description: 查询请假报备审批权限账户信息
	 * @param  
	 * @return 
	 * @author: DingDong
	 * @date: 2019年07月2日
	 * @version: V1.0
	 */
	@Override
	public List<AccWithApprovalLeaveAuth> queryApprovalAuthAccount() throws MessageException {
		logger.info("---------------开始执行queryApprovalAuthAccount方法-------------------");
		// 查询审批报备
		ApprovalBaoBeiBean approval = wzbgDao.queryApprovalAuth();
		// 创建Map用于封装集合数据
		List<AccWithApprovalLeaveAuth> accList = new ArrayList<AccWithApprovalLeaveAuth>();
		// 获取报备表的审批职位id并去掉首尾符号
		String str = approval.getCheckPositionID().substring(1, approval.getCheckPositionID().length()-1);
		// 截取字符串
		String[] pidStr = str.split("\\|");
		for(String pid:pidStr) {
			List<AccWithApprovalLeaveAuth> list = wzbgDao.queryAuthAccount(pid);
			accList.addAll(list);
		}
		return accList;
	}
	
	/**
	 * @Description: 查询请假报备
	 * @param  
	 * @return 
	 * @author: DingDong
	 * @date: 2019年07月4日
	 * @version: V1.0
	 */
	@Override
	public List<LeavePrepareBean> queryLeavePrepareBean(LeavePrepareBean leavePrepareBean) throws MessageException {
		List<LeavePrepareBean> list = wzbgDao.queryLeavePrepare(leavePrepareBean);
		return list;
	}

}
