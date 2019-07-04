package com.jiubo.erp.wzbg.service.imply;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiubo.erp.wzbg.bean.AskForLeaveBean;
import com.jiubo.erp.wzbg.bean.EmployeeOfDepartBean;
import com.jiubo.erp.wzbg.dao.PLODao;
import com.jiubo.erp.wzbg.service.PLOService;
import com.jiubo.erp.wzbg.vo.PLOParam;

@Service
public class PLOServiceImply implements PLOService {

	
	@Autowired
    private PLODao dao;
	
	@Override
	public List<AskForLeaveBean> askOfLeaveList(PLOParam ploParam) {
		// TODO Auto-generated method stub
//		return this.dao.selectAskForLeaveList(ploParam);
		return null;
	}

	@Override
	public List<EmployeeOfDepartBean> selectDepartOfEmpList(PLOParam ploParam) {
		// TODO Auto-generated method stub
//		return this.dao.selectDepartOfEmpList(ploParam);
		return null;
	}

}
