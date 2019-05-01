package com.jiubo.erp.kqgl.service;

import java.util.List;

import com.jiubo.erp.kqgl.vo.AttRuleType;
import com.jiubo.erp.kqgl.vo.Vacation;

public interface KqParamSetService {
	/**
	 * @desc:查询假期种类
	 * @param:
	 * @return: List<Vacation>
	 * @Create at: 2019-04-30
	 * @author:  dx
	 * @version: 1.0
	 */
	public List<Vacation> queryVacation();
	
	/**
	 * @desc:修改假期种类
	 * @param:
	 * @return: void
	 * @Create at: 2019-04-30
	 * @author:  dx
	 * @version: 1.0
	 * @throws Exception 
	 */
	public void updateVacation(Vacation vacation) throws Exception;
	
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
	public void deleteVacation(int id) throws Exception;
	
	/**
	 * @desc:查询考勤规则
	 * @param:
	 * @return: List<AttRuleType>
	 * @Create at: 2019-04-30
	 * @author:  dx
	 * @version: 1.0
	 */
	public List<AttRuleType> queryAttRuleType();
	
	/**
	 * @desc:添加考勤规则
	 * @param:
	 * @return: void
	 * @Create at: 2019-04-30
	 * @author:  dx
	 * @version: 1.0
	 */
	public void addAttRuleType(AttRuleType attRuleType);
	
	/**
	 * @desc:删除考勤规则
	 * @param:
	 * @return: void
	 * @Create at: 2019-04-30
	 * @author:  dx
	 * @version: 1.0
	 */
	public void deleteAttRuleType(int id);
	
	/**
	 * @desc:修改考勤规则
	 * @param:
	 * @return: void
	 * @Create at: 2019-04-30
	 * @author:  dx
	 * @version: 1.0
	 */
	public void updateAttRuleType(AttRuleType attRuleType);
}
