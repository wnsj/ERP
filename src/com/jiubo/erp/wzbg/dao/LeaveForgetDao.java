package com.jiubo.erp.wzbg.dao;

import java.util.List;

import com.jiubo.erp.wzbg.bean.LeaveForgetBean;

/**
 * @version: V1.0
 * @author: DingDong
 * @className: LeaveForgetDao
 * @description: 忘记打卡证明数据访问层
 * @data: 2019-07-12
 **/
public interface LeaveForgetDao {
	
	// 查询忘记打卡证明
	public List<LeaveForgetBean> queryLeaveForget(LeaveForgetBean leaveForgetBean);
	
	// 添加忘记打卡证明
	public void addLeaveForget(LeaveForgetBean leaveForgetBean);
	
}
