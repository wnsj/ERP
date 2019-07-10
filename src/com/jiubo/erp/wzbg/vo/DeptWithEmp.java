package com.jiubo.erp.wzbg.vo;

/**
 * @version: V1.0
 * @author: DingDong
 * @className: DeptWithEmp
 * @description: 部门员工信息
 * @data: 2019-07-05
 **/
public class DeptWithEmp {
	private String departmentId; // 部门id
	private String deptParentId;// 父级id
	private String deptName;
	private String name;// 账户名
	private String account;// 账户id
	private String positionName;// 职位名
	
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public String getDeptParentId() {
		return deptParentId;
	}
	public void setDeptParentId(String deptParentId) {
		this.deptParentId = deptParentId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	
}
