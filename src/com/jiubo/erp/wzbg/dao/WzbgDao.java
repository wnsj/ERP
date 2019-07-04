package com.jiubo.erp.wzbg.dao;

import java.util.List;

import com.jiubo.erp.wzbg.bean.ApprovalBaoBeiBean;
import com.jiubo.erp.wzbg.bean.LeavePrepareBean;
import com.jiubo.erp.wzbg.vo.AccWithApprovalLeaveAuth;

/**
 * @version: V1.0
 * @author: DingDong
 * @className: WzbgDao
 * @description: 无纸化办公Dao层
 * @data: 2019-06-29
 **/
public interface WzbgDao {
	
	// 添加请假报备信息
	public void addLeavePrepare(LeavePrepareBean leavePrepare);
	
	// 查询请假报备信息
	public List<LeavePrepareBean> queryLeavePrepare(LeavePrepareBean leavePrepareBean);
	
	// 查询报备审批权限表
	public ApprovalBaoBeiBean queryApprovalAuth();
	
	// 通过职位id查询报备审批权限账户信息
	public List<AccWithApprovalLeaveAuth> queryAuthAccount(String id);
	 
}
