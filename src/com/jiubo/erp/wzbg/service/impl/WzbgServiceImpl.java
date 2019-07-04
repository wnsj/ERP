package com.jiubo.erp.wzbg.service.impl;

import java.text.ParseException;
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
	
	private final static Logger logger  = LoggerFactory.getLogger(WzbgService.class);
	
	@Autowired
	private WzbgDao wzbgDao;
	
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
		for(LeavePrepareBean leave:list) {
			if(!StringUtils.isEmpty(leave.getCheckResult())) {
				switch (leave.getCheckResult()) {
					case "0":
						leave.setCheckResult("不同意");
						break;
					case "1":
						leave.setCheckResult("同意");
						break;
				}
			}
		}
		return list;
	}
	

}
