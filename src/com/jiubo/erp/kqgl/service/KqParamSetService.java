package com.jiubo.erp.kqgl.service;

import java.util.List;
import java.util.Map;

import com.jiubo.erp.common.MessageException;
import com.jiubo.erp.common.Position;
import com.jiubo.erp.kqgl.bean.AttRuleTypeBean;
import com.jiubo.erp.kqgl.bean.AttShiftGroupBean;
import com.jiubo.erp.kqgl.bean.AttShiftScheduleBean;
import com.jiubo.erp.kqgl.vo.Vacation;
import com.jiubo.erp.rygl.bean.DepartmentBean;

public interface KqParamSetService {
	/**
	 * @desc:查询假期种类
	 * @param:
	 * @return: List<Vacation>
	 * @Create at: 2019-04-30
	 * @author:  dx
	 * @version: 1.0
	 */
	public List<Vacation> queryVacation()throws MessageException;
	
	/**
	 * @desc:修改假期种类
	 * @param:
	 * @return: void
	 * @Create at: 2019-04-30
	 * @author:  dx
	 * @version: 1.0
	 * @throws Exception 
	 */
	public void updateVacation(Vacation vacation) throws MessageException;
	
	/**
	 * @desc:添加假期种类
	 * @param:
	 * @return: void
	 * @Create at: 2019-04-30
	 * @author:  dx
	 * @version: 1.0
	 */
	public void addVacation(Vacation vacation);
	
	/**
	 * @desc:删除假期种类
	 * @param:
	 * @return: void
	 * @Create at: 2019-04-30
	 * @author:  dx
	 * @version: 1.0
	 * @throws Exception 
	 */
	public void deleteVacation(int id) throws MessageException;
	
	/**
	 * @desc:查询考勤规则
	 * @param:
	 * @return: List<AttRuleType>
	 * @Create at: 2019-04-30
	 * @author:  dx
	 * @version: 1.0
	 */
	public List<AttRuleTypeBean> queryAttRuleType()throws MessageException;
	
	/**
	 * @desc:添加考勤规则
	 * @param:
	 * @return: void
	 * @Create at: 2019-04-30
	 * @author:  dx
	 * @version: 1.0
	 */
	public void addAttRuleType(AttRuleTypeBean attRuleType)throws MessageException;
	
	/**
	 * @desc:删除考勤规则
	 * @param:
	 * @return: void
	 * @Create at: 2019-04-30
	 * @author:  dx
	 * @version: 1.0
	 */
	public void deleteAttRuleType(int id)throws MessageException;
	
	/**
	 * @desc:修改考勤规则
	 * @param:
	 * @return: void
	 * @Create at: 2019-04-30
	 * @author:  dx
	 * @version: 1.0
	 */
	public void updateAttRuleType(AttRuleTypeBean attRuleType)throws MessageException;
	
	/**
	 * @desc:查询班次
	 * @param:
	 * @return: List<AttShiftSchedule>
	 * @Create at: 2019-05-01
	 * @author:  dx
	 * @version: 1.0
	 */
	public List<AttShiftScheduleBean> queryAttShiftSchedule()throws MessageException;
	
	/**
	 * @desc:添加班次
	 * @param:
	 * @return: void
	 * @Create at: 2019-05-01
	 * @author:  dx
	 * @version: 1.0
	 */
	public void addAttShiftSchedule(AttShiftScheduleBean attShiftSchedule)throws MessageException;
	
	/**
	 * @desc:删除班次
	 * @param:
	 * @return: void
	 * @Create at: 2019-05-01
	 * @author:  dx
	 * @version: 1.0
	 */
	public void deleteAttShiftSchedule(int id)throws MessageException;
	
	/**
	 * @desc:修改班次
	 * @param:
	 * @return: void
	 * @Create at: 2019-05-01
	 * @author:  dx
	 * @version: 1.0
	 */
	public void updateAttShiftSchedule(AttShiftScheduleBean attShiftSchedule)throws MessageException;
	
	/**
	 * @desc:查询班组
	 * @param:
	 * @return: List<AttShiftGroupBean>
	 * @Create at: 2019-05-01
	 * @author:  dx
	 * @version: 1.0
	 */
	public List<AttShiftGroupBean> queryAttShiftGroup()throws MessageException;
	
	/**
	 * @desc:添加班组
	 * @param:
	 * @return: void
	 * @Create at: 2019-05-01
	 * @author:  dx
	 * @version: 1.0
	 */
	public void addAttShiftGroup(AttShiftGroupBean attShiftGroupBean)throws MessageException;
	
	/**
	 * @desc:删除班组
	 * @param:
	 * @return: void
	 * @Create at: 2019-05-01
	 * @author:  dx
	 * @version: 1.0
	 */
	public void deleteAttShiftGroup(int id)throws MessageException;
	
	/**
	 * @desc:修改班组
	 * @param:
	 * @return: void
	 * @Create at: 2019-05-01
	 * @author:  dx
	 * @version: 1.0
	 */
	public void updateAttShiftGroup(AttShiftGroupBean attShiftGroupBean)throws MessageException;
	
	/**
	 * @desc:查询部门信息
	 * @param:
	 * @return: List<Map<String,Object>>
	 * @Create at: 2019-05-02
	 * @author:  dx
	 * @version: 1.0
	 */
	public List<Map<String,Object>> queryDepartment()throws MessageException;
	
	/**
	 * @desc:增加部门信息
	 * @param:
	 * @return: void
	 * @Create at: 2019-05-02
	 * @author:  dx
	 * @version: 1.0
	 */
	public void addDepartment(DepartmentBean departmentBean)throws MessageException;
	
	/**
	 * @desc:删除部门信息
	 * @param:
	 * @return: void
	 * @Create at: 2019-05-02
	 * @author:  dx
	 * @version: 1.0
	 */
	public void deleteDepartment(int id)throws MessageException;
	
	/**
	 * @desc:修改部门信息
	 * @param:
	 * @return: void
	 * @Create at: 2019-05-02
	 * @author:  dx
	 * @version: 1.0
	 */
	public void updateDepartment(DepartmentBean departmentBean)throws MessageException;
	
	public List<Position> queryPosition()throws MessageException;
	
	public void addPosition(Position position)throws MessageException;
	
	public void updatePosition(Position position)throws MessageException;
	
	
}
//班次类别
//public List<AttShift> queryAttShift();
//添加班次类别
//public void addAttShift();