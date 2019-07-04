package com.jiubo.erp.rygl.vo;

/**
 * @version: V1.0
 * @author: DingDong
 * @className: LeavePrepare
 * @description: 部门员工联动信息
 * @data: 2019-07-1
 **/
public class DeptEmp {
	private String id; // 员工id
	private String name; // 员工姓名
	private String deptId; // 部门id
	private String account; // 员工ERP账户
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	
}
