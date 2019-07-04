package com.jiubo.erp.wzbg.service;

import java.util.List;

import com.jiubo.erp.common.MessageException;
import com.jiubo.erp.wzbg.bean.LeavePrepareBean;
import com.jiubo.erp.wzbg.vo.AccWithApprovalLeaveAuth;

/**
 * @version: V1.0
 * @author: DingDong
 * @className: WzbgService
 * @description: 无纸化办公业务层
 * @data: 2019-06-29
 **/
public interface WzbgService {
	
	/**
	 * @Description: 添加请假报备
	 * @param  leavePrepareBean(请假报备实体)
	 * @return 
	 * @author: DingDong
	 * @date: 2019年06月29日
	 * @version: V1.0
	 */
	public void addLeavePrepareBean(LeavePrepareBean leavePrepareBean) throws MessageException;
	
	/**
	 * @Description: 查询报备审批权限账户信息
	 * @param  
	 * @return 
	 * @author: DingDong
	 * @date: 2019年07月2日
	 * @version: V1.0
	 */
	public List<AccWithApprovalLeaveAuth> queryApprovalAuthAccount() throws MessageException;
	
	/**
	 * @Description: 查询请假报备
	 * @param  leavePrepareBean(请假报备实体)
	 * @return 
	 * @author: DingDong
	 * @date: 2019年06月29日
	 * @version: V1.0
	 */
	public List<LeavePrepareBean> queryLeavePrepareBean(LeavePrepareBean leavePrepareBean) throws MessageException;

}
