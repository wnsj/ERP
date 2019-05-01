package com.jiubo.erp.kqgl.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiubo.erp.erpLogin.util.ResponseMessageUtils;
import com.jiubo.erp.kqgl.service.KqParamSetService;
import com.jiubo.erp.kqgl.vo.AttRuleType;
import com.jiubo.erp.kqgl.vo.Vacation;
import com.quicksand.push.ToolClass;

@Controller
@Scope("prototype")
@RequestMapping("/kqParamSetContr")
public class KqParamSetController {
	
	  @Autowired
	  private KqParamSetService KqParamSetService;
	  
	  /**
	   * @desc:查询假期种类
	   * @param:
	   * @return: List<Vacation>
	   * @Create at: 2019-04-30
	   * @author:  dx
	   * @version: 1.0
	   */
	  //http://127.0.0.1:8080/Erp1.1/kqParamSetContr/queryVacation
	  @ResponseBody
	  @RequestMapping(value="/queryVacation")
      public List<Vacation> queryVacation(HttpServletRequest request,HttpServletResponse response){
		   try {
		  	     return KqParamSetService.queryVacation();
		  	} catch (Exception e) {
		  		e.printStackTrace();
		  		ResponseMessageUtils.responseMessage(response, "查询错误,请重试!");
		  		return null;
		  	}
	  }
	  
	  /**
	   * @desc:修改假期种类
	   * @param:
	   * @return: void
	   * @Create at: 2019-04-30
	   * @author:  dx
	   * @version: 1.0
	   */
	  //http://127.0.0.1:8080/Erp1.1/kqParamSetContr/updateVacation?name=777&id=22
	  @ResponseBody
	  @RequestMapping(value="/updateVacation")
      public void updateVacation(Vacation vacation,HttpServletRequest request,HttpServletResponse response){
		   try {
			   if(StringUtils.isBlank(vacation.getId())  || StringUtils.isBlank(vacation.getName()))
					throw new Exception("假期种类ID为空或假期种类名为空！");
			   KqParamSetService.updateVacation(vacation);
		  	} catch (Exception e) {
		  		e.printStackTrace();
		  		ResponseMessageUtils.responseMessage(response, "修改失败,请重试!");
		  	}
	  }
	  
	  /**
	   * @desc:删除假期种类
	   * @param:
	   * @return: void
	   * @Create at: 2019-04-30
	   * @author:  dx
	   * @version: 1.0
	   */
	  //http://127.0.0.1:8080/Erp1.1/kqParamSetContr/deleteVacation?id=22
	  @ResponseBody
	  @RequestMapping(value="/deleteVacation")
      public void deleteVacation(String id,HttpServletRequest request,HttpServletResponse response){
		   try {
			   if(StringUtils.isBlank(id))throw new Exception("假期种类ID为空！");
			   KqParamSetService.deleteVacation(Integer.parseInt(id));
		  	} catch (Exception e) {
		  		e.printStackTrace();
		  		ResponseMessageUtils.responseMessage(response, "操作失败,请重试!");
		  	}
	  }
	  
	  /**
	   * @desc:添加假期种类
	   * @param:
	   * @return: void
	   * @Create at: 2019-04-30
	   * @author:  dx
	   * @version: 1.0
	   */
	  //http://127.0.0.1:8080/Erp1.1/kqParamSetContr/addVacation?name=666
	  @ResponseBody
	  @RequestMapping(value="/addVacation")
      public void addVacation(Vacation vacation,HttpServletRequest request,HttpServletResponse response){
		   try {
			   if(StringUtils.isBlank(vacation.getName()))throw new Exception("假期种类名不能为空！");
			   vacation.setCreateDate(ToolClass.inquirNowDateTime());
			   KqParamSetService.addVacation(vacation);
		   } catch (Exception e) {
		  		e.printStackTrace();
		  		ResponseMessageUtils.responseMessage(response, "添加失败,请重试!");
		  	}
	  }
	  
	  	/**
	  	 * @desc:查询考勤规则
	  	 * @param:
	  	 * @return: List<AttRuleType>
	  	 * @Create at: 2019-04-30
	  	 * @author:  dx
	  	 * @version: 1.0
	  	 */
	  	//http://127.0.0.1:8080/Erp1.1/kqParamSetContr/queryAttRuleType
		@ResponseBody
		@RequestMapping(value="/queryAttRuleType")
		public List<AttRuleType> queryAttRuleType(HttpServletRequest request,HttpServletResponse response){
			   try {
				   return KqParamSetService.queryAttRuleType();
			   } catch (Exception e) {
			  		e.printStackTrace();
			  		ResponseMessageUtils.responseMessage(response, "查询失败,请重试!");
			  		return null;
			  }
		}
		
		/**
		 * @desc:添加考勤规则
		 * @param:
		 * @return: void
		 * @Create at: 2019-04-30
		 * @author:  dx
		 * @version: 1.0
		 */
		//http://127.0.0.1:8080/Erp1.1/kqParamSetContr/addAttRuleType?name=88&earlyMinutes=10&lateMinutes=20
		@ResponseBody
		@RequestMapping(value="/addAttRuleType")
		public void addAttRuleType(AttRuleType attRuleType,HttpServletRequest request,HttpServletResponse response){
			   try {
				   if(StringUtils.isBlank(attRuleType.getName()) || StringUtils.isBlank(attRuleType.getEarlyMinutes()) || StringUtils.isBlank(attRuleType.getLateMinutes()))
				   throw new Exception("考勤规则名为空、最早时间为空或最晚时间为空！");
				   attRuleType.setCreateDate(ToolClass.inquirNowDateTime());
				   KqParamSetService.addAttRuleType(attRuleType);
			   } catch (Exception e) {
			  		e.printStackTrace();
			  		ResponseMessageUtils.responseMessage(response, "添加失败,请重试!");
			  }
		}
		
		/**
		 * @desc:删除考勤规则
		 * @param:
		 * @return: void
		 * @Create at: 2019-04-30
		 * @author:  dx
		 * @version: 1.0
		 */
		//http://127.0.0.1:8080/Erp1.1/kqParamSetContr/deleteAttRuleType?id=5
		@ResponseBody
		@RequestMapping(value="/deleteAttRuleType")
		public void deleteAttRuleType(int id,HttpServletRequest request,HttpServletResponse response){
			   try {
				   if(id <= 0)throw new Exception("考勤规则id不能为空！");
				   KqParamSetService.deleteAttRuleType(id);
			   } catch (Exception e) {
			  		e.printStackTrace();
			  		ResponseMessageUtils.responseMessage(response, "删除失败,请重试!");
			  }
		}
		
		/**
		 * @desc:修改考勤规则
		 * @param:
		 * @return: void
		 * @Create at: 2019-04-30
		 * @author:  dx
		 * @version: 1.0
		 */
		//http://127.0.0.1:8080/Erp1.1/kqParamSetContr/updateAttRuleType?name=99&earlyMinutes=20&lateMinutes=30&id=5
		@ResponseBody
		@RequestMapping(value="/updateAttRuleType")
		public void updateAttRuleType(AttRuleType attRuleType,HttpServletRequest request,HttpServletResponse response){
			   try {
				   if(StringUtils.isBlank(attRuleType.getName()) || StringUtils.isBlank(attRuleType.getEarlyMinutes())
						   || StringUtils.isBlank(attRuleType.getLateMinutes()) || StringUtils.isBlank(attRuleType.getId()) )
					   throw new Exception("考勤规则名为空、最早时间为空、最晚时间为空或考勤规则id为空！");
				   KqParamSetService.updateAttRuleType(attRuleType);
			   } catch (Exception e) {
			  		e.printStackTrace();
			  		ResponseMessageUtils.responseMessage(response, "修改失败,请重试!");
			  }
		}
}
