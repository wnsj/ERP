package com.jiubo.erp.wzbg.dao;

import java.util.List;

import com.jiubo.erp.wzbg.bean.AskForLeaveBean;
import com.jiubo.erp.wzbg.bean.EmployeeOfDepartBean;
import com.jiubo.erp.wzbg.vo.PLOParam;

public interface PLODao {

	//查询当前的请假列表
	public List<AskForLeaveBean> selectAskForLeaveList(PLOParam ploParam);
	//查询当前部门下的人员列表
	public List<EmployeeOfDepartBean> selectDepartOfEmpList(PLOParam ploParam);
}





