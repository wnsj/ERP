package com.jiubo.erp.wzbg.service;

import java.util.List;

import com.jiubo.erp.wzbg.bean.AskForLeaveBean;
import com.jiubo.erp.wzbg.bean.EmployeeOfDepartBean;
import com.jiubo.erp.wzbg.vo.PLOParam;

public interface PLOService {
	
	public List<AskForLeaveBean> askOfLeaveList(PLOParam ploParam);
	
	public List<EmployeeOfDepartBean> selectDepartOfEmpList(PLOParam ploParam);
}
