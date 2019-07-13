package com.jiubo.erp.wzbg.vo;

import lombok.Data;

/**
 * @version: V1.0
 * @author: DingDong
 * @className: DeptWithEmp
 * @description: 部门员工信息
 * @data: 2019-07-05
 **/
@Data
public class DeptWithEmp {
	private String departmentId; // 部门id
	private String deptParentId;// 父级id
	private String deptName;
	private String name;// 账户名
	private String account;// 账户id
	private String positionName;// 职位名
}
