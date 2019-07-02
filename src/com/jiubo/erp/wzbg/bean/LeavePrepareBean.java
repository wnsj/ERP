package com.jiubo.erp.wzbg.bean;

import java.io.Serializable;

/**
 * @version: V1.0
 * @author: DingDong
 * @className: LeavePrepare
 * @description: 请假报备实体
 * @data: 2019-06-29
 **/
public class LeavePrepareBean implements Serializable {

	private static final long serialVersionUID = -6959115797576537005L;

	private String id; // 请假报备id
	private String type; // 请假类型
	private String fillAccount; // 填表人
	private String fillTime; // 填表时间
	private String leaveAccount; // 请假人
	private String startTime; // 起始时间
	private String endTime; // 结束时间
	private String leaveRemark; // 请假说明
	private String checkAccount; // 审批人
	private String checkTime; // 审批时间
	private String checkResult; // 审批结果
	private String checkRemark; // 审批意见
	private String updateTime; // 数据更新时间
	private String isSee; // 是否查看
	private String state; // 状态
	
	// 非数据库字段
	private String leaveDepartment;
	private String leaveDepartmentName; // 请假人部门
	private String fillDepartment;
	private String fillDepartmentName;  // 填表人部门
	
	public LeavePrepareBean() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFillAccount() {
		return fillAccount;
	}

	public void setFillAccount(String fillAccount) {
		this.fillAccount = fillAccount;
	}

	public String getFillTime() {
		return fillTime;
	}

	public void setFillTime(String fillTime) {
		this.fillTime = fillTime;
	}

	public String getLeaveAccount() {
		return leaveAccount;
	}

	public void setLeaveAccount(String leaveAccount) {
		this.leaveAccount = leaveAccount;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getLeaveRemark() {
		return leaveRemark;
	}

	public void setLeaveRemark(String leaveRemark) {
		this.leaveRemark = leaveRemark;
	}

	public String getCheckAccount() {
		return checkAccount;
	}

	public void setCheckAccount(String checkAccount) {
		this.checkAccount = checkAccount;
	}

	public String getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}

	public String getCheckResult() {
		return checkResult;
	}

	public void setCheckResult(String checkResult) {
		this.checkResult = checkResult;
	}

	public String getCheckRemark() {
		return checkRemark;
	}

	public void setCheckRemark(String checkRemark) {
		this.checkRemark = checkRemark;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getIsSee() {
		return isSee;
	}

	public void setIsSee(String isSee) {
		this.isSee = isSee;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getLeaveDepartment() {
		return leaveDepartment;
	}

	public void setLeaveDepartment(String leaveDepartment) {
		this.leaveDepartment = leaveDepartment;
	}

	public String getLeaveDepartmentName() {
		return leaveDepartmentName;
	}

	public void setLeaveDepartmentName(String leaveDepartmentName) {
		this.leaveDepartmentName = leaveDepartmentName;
	}

	public String getFillDepartment() {
		return fillDepartment;
	}

	public void setFillDepartment(String fillDepartment) {
		this.fillDepartment = fillDepartment;
	}

	public String getFillDepartmentName() {
		return fillDepartmentName;
	}

	public void setFillDepartmentName(String fillDepartmentName) {
		this.fillDepartmentName = fillDepartmentName;
	}
	
	
}
