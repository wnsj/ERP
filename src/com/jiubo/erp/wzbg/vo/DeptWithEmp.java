package com.jiubo.erp.wzbg.vo;

/**
 * @version: V1.0
 * @author: DingDong
 * @className: DeptWithEmp
 * @description: 部门员工信息
 * @data: 2019-07-05
 **/
public class DeptWithEmp {
	private String departmentId;
	private String name;
	private String account;
	public String getName() {
		return name;
	}
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
