package com.jiubo.erp.wzbg.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jiubo.erp.wzbg.bean.AskForLeaveBean;
import com.jiubo.erp.wzbg.bean.EmployeeOfCheck;
import com.jiubo.erp.wzbg.bean.EmployeeOfDepartBean;
import com.jiubo.erp.wzbg.bean.RestDownBean;
import com.jiubo.erp.wzbg.vo.PLOParam;

public interface PLODao {

	//请假列表
	public List<AskForLeaveBean> selectAskForLeaveList(PLOParam ploParam);
	
	//请假--当前部门下的人员列表
	public List<EmployeeOfDepartBean> selectDepartOfEmpList(PLOParam ploParam);
	
	//申请请假
	public int insertLeaveApplication(AskForLeaveBean afl);
	
	//请假修改
	public int updateLeaveApplication(AskForLeaveBean afl);
	
	//请假 --- 人员级别查看审查列表
	public List<EmployeeOfCheck>checkOfEmpList(@Param(value="level") String level,@Param(value="positionId") String positionId
			,@Param(value="departId") String departId,@Param(value="clickTimes") String clickTimes);
	
	//倒休列表
	public List<RestDownBean> selectRestDownList(PLOParam ploParam);
	
	//倒休申请
	public int insertRestDownApplication(RestDownBean rdb);
	
	//倒休修改
	public int updateRestDownApplication(RestDownBean rdb);
	
}





