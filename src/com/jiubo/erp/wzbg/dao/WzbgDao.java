package com.jiubo.erp.wzbg.dao;

import java.util.List;

import com.jiubo.erp.erpLogin.bean.AccountDataBean;
import com.jiubo.erp.wzbg.bean.LeavePrepareBean;

/**
 * @version: V1.0
 * @author: DingDong
 * @className: WzbgDao
 * @description: 无纸化办公Dao层
 * @data: 2019-06-29
 **/
public interface WzbgDao {
	
	// 添加请假报备
	public void addLeavePrepare(LeavePrepareBean leavePrepare);
	
	// 查询报备审批权限表
	public String queryApprovalLeave();
	
	// 查询请假报备审批权限账户信息
	public List<AccountDataBean> queryApprovalLeaveAccount(String[] args);
	
	// 查询请假报备
	public List<LeavePrepareBean> queryLeavePrepare();

}
