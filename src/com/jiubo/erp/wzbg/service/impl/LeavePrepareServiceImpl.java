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
import com.jiubo.erp.wzbg.dao.LeavePrepareDao;
import com.jiubo.erp.wzbg.service.LeavePrepareService;
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
public class LeavePrepareServiceImpl implements LeavePrepareService {

	private final static Logger logger  = LoggerFactory.getLogger(LeavePrepareServiceImpl.class);

	@Autowired
	private LeavePrepareDao leavePrepareDao;

	/**
	 * @Description: 查询部门下的员工姓名以及ERP账户信息
	 * @param  String id(部门id)
	 * @return List<DeptWithEmp>
	 * @author: DingDong
	 * @date: 2019年7月1日
	 * @version: V1.0
	 */
	@Override
	public List<DeptWithEmp> queryEmpInfoByDept(String id) throws MessageException {
		List<DeptWithEmp> list = leavePrepareDao.queryEmpInfoByDept(id);
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
		logger.info("----------开始新增请假报备,方法:addLeavePrepareBean----------");
		try {
			leavePrepareDao.addLeavePrepare(leavePrepareBean);
		} catch (Exception e) {
			throw new MessageException(e.getMessage());
		}
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
		ApprovalBaoBeiBean approval = leavePrepareDao.queryApprovalAuth();
		// 创建Map用于封装集合数据
		List<AccWithApprovalLeaveAuth> accList = new ArrayList<AccWithApprovalLeaveAuth>();
		// 获取报备表的审批职位id并去掉首尾符号
		String str = approval.getCheckPositionID().substring(1, approval.getCheckPositionID().length()-1);
		// 截取字符串
		String[] pidStr = str.split("\\|");
		for(String pid:pidStr) {
			List<AccWithApprovalLeaveAuth> list = leavePrepareDao.queryAuthAccount(pid);
			accList.addAll(list);
		}
		return accList;
	}

	/**
	 * @Description: 查询请假报备
	 * @param  leavePrepareBean(请假报备实体)
	 * @return
	 * @author: DingDong
	 * @date: 2019年07月04日
	 * @version: V1.0
	 */
	@Override
	public List<LeavePrepareBean> queryLeavePrepareBean(LeavePrepareBean leavePrepareBean) throws MessageException {
		List<LeavePrepareBean> list = leavePrepareDao.queryLeavePrepare(leavePrepareBean);
		return list;
	}

	/**
	 * @Description: 查询请假代理人列表
	 * @param  deptWithEmp
	 * @return List<DeptWithEmp>
	 * @author: DingDong
	 * @date: 2019年07月09日
	 * @version: V1.0
	 */
	@Override
	public List<DeptWithEmp> queryAgentList(DeptWithEmp deptWithEmp) throws MessageException {
		List<DeptWithEmp> list = leavePrepareDao.queryAgentList(deptWithEmp);
		return list;
	}

	/**
	 * @Description: 查询所在部门以及子部门的员工信息
	 * @param  deptWithEmp
	 * @return List<DeptWithEmp>
	 * @author: DingDong
	 * @date: 2019年7月10日
	 * @version: V1.0
	 */
	@Override
	public List<DeptWithEmp> queryEmpByDeptOrParentDept(DeptWithEmp deptWithEmp) throws MessageException {
		List<DeptWithEmp> list = leavePrepareDao.queryEmpByDeptOrParentDept(deptWithEmp);
		return list;
	}

	/**
	 * @Description: 更新请假报备信息
	 * @param  leavePrepareBean
	 * @return
	 * @author: DingDong
	 * @date: 2019年7月11日
	 * @version: V1.0
	 */
	@Override
	public void updateLeavePrepare(LeavePrepareBean leavePrepareBean) throws MessageException {
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
		leavePrepareDao.updateLeavePrepare(leavePrepareBean);
	}

}
