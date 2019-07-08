package com.jiubo.erp.wzbg.service.imply;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiubo.erp.erpLogin.bean.AccountDataBean;
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
	public void addLeavePrepareBean(LeavePrepareBean leavePrepareBean) {
		try {
			logger.debug("-----------------添加请假报备成功-------------------------");
			wzbgDao.addLeavePrepare(leavePrepareBean);
		} catch (Exception e) {
			logger.error("---------------添加请假报备失败-----------------------------");
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
	public List<AccWithApprovalLeaveAuth> queryApprovalLeaveAccount() {
		List<AccWithApprovalLeaveAuth> accList = new ArrayList<AccWithApprovalLeaveAuth>();
		String str = wzbgDao.queryApprovalLeave();
		String[] args = str.split("|");
		List<AccountDataBean> list = wzbgDao.queryApprovalLeaveAccount(args);
		for(AccountDataBean accData:list) {
			AccWithApprovalLeaveAuth acc = new AccWithApprovalLeaveAuth();
			acc.setAccountID(accData.getAccount_ID());
			acc.setAccountName(accData.getAccount_Name());
			accList.add(acc);
		}
		return accList;
	}

	
	
	

}
