package com.jiubo.erp.rygl.bean;

import java.io.Serializable;
import java.util.List;

public class DepartmentBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String ID; //编号
	private String Name; //名称
	private String ParentID; //父级编号
	private String OrderNum; //顺序号
	private List<DepartmentBean> children;//子部门
	private List<EmployeeBasicBean> employeeList;//部门员工
	public DepartmentBean() {
	}
	
	public List<DepartmentBean> getChildren() {
		return children;
	}

	public void setChildren(List<DepartmentBean> children) {
		this.children = children;
	}

	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getParentID() {
		return ParentID;
	}
	public void setParentID(String parentID) {
		ParentID = parentID;
	}
	public String getOrderNum() {
		return OrderNum;
	}
	public void setOrderNum(String orderNum) {
		OrderNum = orderNum;
	}

	
	public List<EmployeeBasicBean> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<EmployeeBasicBean> employeeList) {
		this.employeeList = employeeList;
	}

	@Override
	public String toString() {
		return "DepartmentBean [ID=" + ID + ", Name=" + Name + ", ParentID=" + ParentID + ", OrderNum=" + OrderNum
				+ ", children=" + children + ", employeeList=" + employeeList + "]";
	}
}
