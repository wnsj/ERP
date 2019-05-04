package com.jiubo.erp.kqgl.controller;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.jiubo.erp.common.Constant;
import com.jiubo.erp.common.MapUtil;
import com.jiubo.erp.common.MessageException;
import com.jiubo.erp.erpLogin.util.ResponseMessageUtils;
import com.jiubo.erp.kqgl.bean.AttRuleTypeBean;
import com.jiubo.erp.kqgl.bean.AttShiftGroupBean;
import com.jiubo.erp.kqgl.bean.AttShiftScheduleBean;
import com.jiubo.erp.kqgl.service.KqParamSetService;
import com.jiubo.erp.kqgl.vo.Vacation;
import com.jiubo.erp.rygl.bean.DepartmentBean;
import com.quicksand.push.ToolClass;

@Controller
@Scope("prototype")
@RequestMapping("/kqParamSetContr")
public class KqParamSetController {
	
	  public static Logger log = LoggerFactory.getLogger(KqParamSetController.class);
	 
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
	  @RequestMapping(value="/queryVacation",method = {RequestMethod.POST})
      public JSONObject queryVacation(HttpServletRequest request,HttpServletResponse response){
		   JSONObject result = new JSONObject();
	       String retCode = Constant.Result.SUCCESS;
	       String retMsg = Constant.Result.SUCCESS_MSG;
		   try {
		  	    result.put(Constant.Result.RETDATA,KqParamSetService.queryVacation());
		   }catch (MessageException e){
		        retCode = Constant.Result.ERROR;
		        retMsg = e.getMessage();
		   }catch (Exception e){
		        retCode = Constant.Result.ERROR;
		        retMsg = Constant.Result.ERROR_MSG;
		        log.error(Constant.Result.RETMSG,e);
		  }finally {
		        result.put(Constant.Result.RETCODE, retCode);
		        result.put(Constant.Result.RETMSG, retMsg);
		        return result;
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
	  @RequestMapping(value="/updateVacation",method = {RequestMethod.POST})
      public JSONObject updateVacation(HttpServletRequest request,HttpServletResponse response){
		   JSONObject result = new JSONObject();
	       String retCode = Constant.Result.SUCCESS;
	       String retMsg = Constant.Result.SUCCESS_MSG;
	       try {
	    	   String str = ToolClass.getStrFromInputStream(request);
	    	   if(StringUtils.isBlank(str))throw new MessageException("参数接收失败！");
	    	   Vacation vacation = MapUtil.transJsonStrToObjectIgnoreCase(str,Vacation.class);
	    	   if(StringUtils.isBlank(vacation.getId()) || StringUtils.isBlank(vacation.getName()))
					throw new MessageException("假期种类ID为空或假期种类名为空！");
			   KqParamSetService.updateVacation(vacation);
		   }catch (MessageException e){
		        retCode = Constant.Result.ERROR;
		        retMsg = e.getMessage();
		   }catch (Exception e){
		        retCode = Constant.Result.ERROR;
		        retMsg = Constant.Result.ERROR_MSG;
		        log.error(Constant.Result.RETMSG,e);
		  }finally {
		        result.put(Constant.Result.RETCODE, retCode);
		        result.put(Constant.Result.RETMSG, retMsg);
		        return result;
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
	  @RequestMapping(value="/deleteVacation",method = {RequestMethod.POST})
      public JSONObject deleteVacation(@RequestBody Map<String,Object> requestMap,HttpServletRequest request,HttpServletResponse response){
		   JSONObject result = new JSONObject();
	       String retCode = Constant.Result.SUCCESS;
	       String retMsg = Constant.Result.SUCCESS_MSG;
	       try {
	    	   int id = MapUtil.getIntIgnoreCase(requestMap, "id", MapUtil.NOT_NULL);
			   KqParamSetService.deleteVacation(id);
		   }catch (MessageException e){
		        retCode = Constant.Result.ERROR;
		        retMsg = e.getMessage();
		   }catch (Exception e){
		        retCode = Constant.Result.ERROR;
		        retMsg = Constant.Result.ERROR_MSG;
		        log.error(Constant.Result.RETMSG,e);
		  }finally {
		        result.put(Constant.Result.RETCODE, retCode);
		        result.put(Constant.Result.RETMSG, retMsg);
		        return result;
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
	  @RequestMapping(value="/addVacation",method = {RequestMethod.POST})
      public JSONObject addVacation(HttpServletRequest request,HttpServletResponse response){
		   JSONObject result = new JSONObject();
	       String retCode = Constant.Result.SUCCESS;
	       String retMsg = Constant.Result.SUCCESS_MSG;
	       try {
	    	   String str = ToolClass.getStrFromInputStream(request);
	    	   if(StringUtils.isBlank(str))throw new MessageException("参数接收失败！");
	    	   Vacation vacation = MapUtil.transJsonStrToObjectIgnoreCase(str,Vacation.class);
	    	   if(StringUtils.isBlank(vacation.getName()))throw new MessageException("假期种类名不能为空！");
			   vacation.setIsDelete("0");
			   vacation.setCreateDate(ToolClass.inquirNowDateTime());
			   KqParamSetService.addVacation(vacation);
		   }catch (MessageException e){
		        retCode = Constant.Result.ERROR;
		        retMsg = e.getMessage();
		   }catch (Exception e){
		        retCode = Constant.Result.ERROR;
		        retMsg = Constant.Result.ERROR_MSG;
		        log.error(Constant.Result.RETMSG,e);
		  }finally {
		        result.put(Constant.Result.RETCODE, retCode);
		        result.put(Constant.Result.RETMSG, retMsg);
		        return result;
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
		@RequestMapping(value="/queryAttRuleType",method = {RequestMethod.POST})
		public JSONObject queryAttRuleType(HttpServletRequest request,HttpServletResponse response){
			   JSONObject result = new JSONObject();
		       String retCode = Constant.Result.SUCCESS;
		       String retMsg = Constant.Result.SUCCESS_MSG;
		       try {
		    	   result.put(Constant.Result.RETDATA, KqParamSetService.queryAttRuleType());
			   }catch (MessageException e){
			        retCode = Constant.Result.ERROR;
			        retMsg = e.getMessage();
			   }catch (Exception e){
			        retCode = Constant.Result.ERROR;
			        retMsg = Constant.Result.ERROR_MSG;
			        log.error(Constant.Result.RETMSG,e);
			  }finally {
			        result.put(Constant.Result.RETCODE, retCode);
			        result.put(Constant.Result.RETMSG, retMsg);
			        return result;
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
		@RequestMapping(value="/addAttRuleType",method = {RequestMethod.POST})
		public JSONObject addAttRuleType(HttpServletRequest request,HttpServletResponse response){
			   JSONObject result = new JSONObject();
		       String retCode = Constant.Result.SUCCESS;
		       String retMsg = Constant.Result.SUCCESS_MSG;
		       try {
		    	   String str = ToolClass.getStrFromInputStream(request);
		    	   if(StringUtils.isBlank(str))throw new MessageException("参数接收失败！");
		    	   AttRuleTypeBean attRuleType = MapUtil.transJsonStrToObjectIgnoreCase(str,AttRuleTypeBean.class);
		    	   if(StringUtils.isBlank(attRuleType.getName()) || StringUtils.isBlank(attRuleType.getEarlyMinutes()) || StringUtils.isBlank(attRuleType.getLateMinutes()))
					   throw new MessageException("考勤规则名为空、最早时间为空或最晚时间为空！");
				   attRuleType.setIsDelete("0");
				   attRuleType.setCreateDate(ToolClass.inquirNowDateTime());
				   KqParamSetService.addAttRuleType(attRuleType);
			   }catch (MessageException e){
			        retCode = Constant.Result.ERROR;
			        retMsg = e.getMessage();
			   }catch (Exception e){
			        retCode = Constant.Result.ERROR;
			        retMsg = Constant.Result.ERROR_MSG;
			        log.error(Constant.Result.RETMSG,e);
			  }finally {
			        result.put(Constant.Result.RETCODE, retCode);
			        result.put(Constant.Result.RETMSG, retMsg);
			        return result;
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
		@RequestMapping(value="/deleteAttRuleType",method = {RequestMethod.POST})
		public JSONObject deleteAttRuleType(@RequestBody Map<String,Object> requestMap,HttpServletRequest request,HttpServletResponse response){
			   JSONObject result = new JSONObject();
		       String retCode = Constant.Result.SUCCESS;
		       String retMsg = Constant.Result.SUCCESS_MSG;
		       try {
		    	   int id = MapUtil.getIntIgnoreCase(requestMap, "id", MapUtil.NOT_NULL);
				   KqParamSetService.deleteAttRuleType(id);
			   }catch (MessageException e){
			        retCode = Constant.Result.ERROR;
			        retMsg = e.getMessage();
			   }catch (Exception e){
			        retCode = Constant.Result.ERROR;
			        retMsg = Constant.Result.ERROR_MSG;
			        log.error(Constant.Result.RETMSG,e);
			  }finally {
			        result.put(Constant.Result.RETCODE, retCode);
			        result.put(Constant.Result.RETMSG, retMsg);
			        return result;
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
		@RequestMapping(value="/updateAttRuleType",method = {RequestMethod.POST})
		public JSONObject updateAttRuleType(HttpServletRequest request,HttpServletResponse response){
			   JSONObject result = new JSONObject();
		       String retCode = Constant.Result.SUCCESS;
		       String retMsg = Constant.Result.SUCCESS_MSG;
		       try {
		    	   String str = ToolClass.getStrFromInputStream(request);
		    	   if(StringUtils.isBlank(str))throw new MessageException("参数接收失败！");
		    	   AttRuleTypeBean attRuleType = MapUtil.transJsonStrToObjectIgnoreCase(str,AttRuleTypeBean.class);
		    	   if(StringUtils.isBlank(attRuleType.getName()) || StringUtils.isBlank(attRuleType.getEarlyMinutes())
						   || StringUtils.isBlank(attRuleType.getLateMinutes()) || StringUtils.isBlank(attRuleType.getId()) )
					   throw new MessageException("考勤规则名为空、最早时间为空、最晚时间为空或考勤规则id为空！");
				   KqParamSetService.updateAttRuleType(attRuleType);
			   }catch (MessageException e){
			        retCode = Constant.Result.ERROR;
			        retMsg = e.getMessage();
			   }catch (Exception e){
			        retCode = Constant.Result.ERROR;
			        retMsg = Constant.Result.ERROR_MSG;
			        log.error(Constant.Result.RETMSG,e);
			  }finally {
			        result.put(Constant.Result.RETCODE, retCode);
			        result.put(Constant.Result.RETMSG, retMsg);
			        return result;
			  }   
		}
		
		/**
		 * @desc:查询班次
		 * @param:
		 * @return: List<AttShiftSchedule>
		 * @Create at: 2019-05-01
		 * @author:  dx
		 * @version: 1.0
		 */
		//http://127.0.0.1:8080/Erp1.1/kqParamSetContr/queryAttShiftSchedule
		@ResponseBody
		@RequestMapping(value="/queryAttShiftSchedule",method = {RequestMethod.POST})
		public JSONObject queryAttShiftSchedule(HttpServletRequest request,HttpServletResponse response){
			   JSONObject result = new JSONObject();
		       String retCode = Constant.Result.SUCCESS;
		       String retMsg = Constant.Result.SUCCESS_MSG;
		       try {
		    	   result.put(Constant.Result.RETDATA, KqParamSetService.queryAttShiftSchedule());
			   }catch (MessageException e){
			        retCode = Constant.Result.ERROR;
			        retMsg = e.getMessage();
			   }catch (Exception e){
			        retCode = Constant.Result.ERROR;
			        retMsg = Constant.Result.ERROR_MSG;
			        log.error(Constant.Result.RETMSG,e);
			  }finally {
			        result.put(Constant.Result.RETCODE, retCode);
			        result.put(Constant.Result.RETMSG, retMsg);
			        return result;
			  }    
		}
		
		/**
		 * @desc:添加班次
		 * @param:
		 * @return: void
		 * @Create at: 2019-05-01
		 * @author:  dx
		 * @version: 1.0
		 */
		//http://127.0.0.1:8080/Erp1.1/kqParamSetContr/addAttShiftSchedule?name=test&shortName=test&startTime=6:00&endTime=8:00&type=2
		@ResponseBody
		@RequestMapping(value="/addAttShiftSchedule",method = {RequestMethod.POST})
		public JSONObject addAttShiftSchedule(HttpServletRequest request,HttpServletResponse response) {
			   JSONObject result = new JSONObject();
		       String retCode = Constant.Result.SUCCESS;
		       String retMsg = Constant.Result.SUCCESS_MSG;
		       try {
		    	   String str = ToolClass.getStrFromInputStream(request);
		    	   if(StringUtils.isBlank(str))throw new MessageException("参数接收失败！");
		    	   AttShiftScheduleBean attShiftSchedule = MapUtil.transJsonStrToObjectIgnoreCase(str,AttShiftScheduleBean.class);
		    	    // StringUtils.isBlank(attShiftSchedule.getStartTime()) || StringUtils.isBlank(attShiftSchedule.getEndTime()) ||
					if(StringUtils.isBlank(attShiftSchedule.getName()) || StringUtils.isBlank(attShiftSchedule.getShortName()) ||
					   StringUtils.isBlank(attShiftSchedule.getType()))throw new MessageException("班次名、简称或类型为空！");
				    KqParamSetService.addAttShiftSchedule(attShiftSchedule);
			   }catch (MessageException e){
			        retCode = Constant.Result.ERROR;
			        retMsg = e.getMessage();
			   }catch (Exception e){
			        retCode = Constant.Result.ERROR;
			        retMsg = Constant.Result.ERROR_MSG;
			        log.error(Constant.Result.RETMSG,e);
			  }finally {
			        result.put(Constant.Result.RETCODE, retCode);
			        result.put(Constant.Result.RETMSG, retMsg);
			        return result;
			  }    
		}
		
		/**
		 * @desc:删除班次
		 * @param:
		 * @return: void
		 * @Create at: 2019-05-01
		 * @author:  dx
		 * @version: 1.0
		 */
		//http://127.0.0.1:8080/Erp1.1/kqParamSetContr/deleteAttShiftSchedule?id=36
		@ResponseBody
		@RequestMapping(value="/deleteAttShiftSchedule",method = {RequestMethod.POST})
		public JSONObject deleteAttShiftSchedule(@RequestBody Map<String,Object> requestMap,HttpServletRequest request,HttpServletResponse response) {
			   JSONObject result = new JSONObject();
		       String retCode = Constant.Result.SUCCESS;
		       String retMsg = Constant.Result.SUCCESS_MSG;
		       try {
		    	   int id = MapUtil.getIntIgnoreCase(requestMap, "id", MapUtil.NOT_NULL);
				    KqParamSetService.deleteAttShiftSchedule(id);
			   }catch (MessageException e){
			        retCode = Constant.Result.ERROR;
			        retMsg = e.getMessage();
			   }catch (Exception e){
			        retCode = Constant.Result.ERROR;
			        retMsg = Constant.Result.ERROR_MSG;
			        log.error(Constant.Result.RETMSG,e);
			  }finally {
			        result.put(Constant.Result.RETCODE, retCode);
			        result.put(Constant.Result.RETMSG, retMsg);
			        return result;
			  }    
		}
		
		/**
		 * @desc:修改班次
		 * @param:
		 * @return: void
		 * @Create at: 2019-05-01
		 * @author:  dx
		 * @version: 1.0
		 */
		//http://127.0.0.1:8080/Erp1.1/kqParamSetContr/updateAttShiftSchedule?name=test1&shortName=test1&startTime=16:00&endTime=18:00&type=1&remarks=test&id=36
		@ResponseBody
		@RequestMapping(value="/updateAttShiftSchedule",method = {RequestMethod.POST})
		public JSONObject updateAttShiftSchedule(HttpServletRequest request,HttpServletResponse response) {
			   JSONObject result = new JSONObject();
		       String retCode = Constant.Result.SUCCESS;
		       String retMsg = Constant.Result.SUCCESS_MSG;
		       try {
		    	   String str = ToolClass.getStrFromInputStream(request);
		    	   if(StringUtils.isBlank(str))throw new MessageException("参数接收失败！");
		    	   AttShiftScheduleBean attShiftSchedule = MapUtil.transJsonStrToObjectIgnoreCase(str,AttShiftScheduleBean.class);
		    	 //StringUtils.isBlank(attShiftSchedule.getStartTime()) || StringUtils.isBlank(attShiftSchedule.getEndTime()) ||
					if(StringUtils.isBlank(attShiftSchedule.getName()) || StringUtils.isBlank(attShiftSchedule.getShortName()) ||
					   StringUtils.isBlank(attShiftSchedule.getType()) || StringUtils.isBlank(attShiftSchedule.getId()))
						throw new MessageException("班次名、简称、类型或班次id为空！");
				    KqParamSetService.updateAttShiftSchedule(attShiftSchedule);
			   }catch (MessageException e){
			        retCode = Constant.Result.ERROR;
			        retMsg = e.getMessage();
			   }catch (Exception e){
			        retCode = Constant.Result.ERROR;
			        retMsg = Constant.Result.ERROR_MSG;
			        log.error(Constant.Result.RETMSG,e);
			  }finally {
			        result.put(Constant.Result.RETCODE, retCode);
			        result.put(Constant.Result.RETMSG, retMsg);
			        return result;
			  }    
		}
		
		/**
		 * @desc:查询班组
		 * @param:
		 * @return: List<AttShiftGroupBean>
		 * @Create at: 2019-05-01
		 * @author:  dx
		 * @version: 1.0
		 */
		//http://127.0.0.1:8080/Erp1.1/kqParamSetContr/queryAttShiftGroup
		@ResponseBody
		@RequestMapping(value="/queryAttShiftGroup",method = {RequestMethod.POST})
		public JSONObject queryAttShiftGroup(HttpServletRequest request,HttpServletResponse response){
			   JSONObject result = new JSONObject();
		       String retCode = Constant.Result.SUCCESS;
		       String retMsg = Constant.Result.SUCCESS_MSG;
		       try {
		    	   result.put(Constant.Result.RETDATA, KqParamSetService.queryAttShiftGroup());
			   }catch (MessageException e){
			        retCode = Constant.Result.ERROR;
			        retMsg = e.getMessage();
			   }catch (Exception e){
			        retCode = Constant.Result.ERROR;
			        retMsg = Constant.Result.ERROR_MSG;
			        log.error(Constant.Result.RETMSG,e);
			  }finally {
			        result.put(Constant.Result.RETCODE, retCode);
			        result.put(Constant.Result.RETMSG, retMsg);
			        return result;
			  }    
		};
		
		/**
		 * @desc:添加班组
		 * @param:
		 * @return: void
		 * @Create at: 2019-05-01
		 * @author:  dx
		 * @version: 1.0
		 */
		//http://127.0.0.1:8080/Erp1.1/kqParamSetContr/addAttShiftGroup?name=test1&remark=test1
		@ResponseBody
		@RequestMapping(value="/addAttShiftGroup",method = {RequestMethod.POST})
		public JSONObject addAttShiftGroup(HttpServletRequest request,HttpServletResponse response) {
			   JSONObject result = new JSONObject();
		       String retCode = Constant.Result.SUCCESS;
		       String retMsg = Constant.Result.SUCCESS_MSG;
		       try {
		    	   String str = ToolClass.getStrFromInputStream(request);
		    	   if(StringUtils.isBlank(str))throw new MessageException("参数接收失败！");
		    	   AttShiftGroupBean attShiftGroupBean = MapUtil.transJsonStrToObjectIgnoreCase(str,AttShiftGroupBean.class);
		    	   if(StringUtils.isBlank(attShiftGroupBean.getName()))throw new MessageException("班组名不能为空！");
					attShiftGroupBean.setCreateDate(ToolClass.inquirNowDateTime());
					attShiftGroupBean.setIsDelete("0");
					if(StringUtils.isBlank(attShiftGroupBean.getCreator()))attShiftGroupBean.setCreator("0");
				    KqParamSetService.addAttShiftGroup(attShiftGroupBean);
			   }catch (MessageException e){
			        retCode = Constant.Result.ERROR;
			        retMsg = e.getMessage();
			   }catch (Exception e){
			        retCode = Constant.Result.ERROR;
			        retMsg = Constant.Result.ERROR_MSG;
			        log.error(Constant.Result.RETMSG,e);
			  }finally {
			        result.put(Constant.Result.RETCODE, retCode);
			        result.put(Constant.Result.RETMSG, retMsg);
			        return result;
			  }    
		};
		
		/**
		 * @desc:删除班组
		 * @param:
		 * @return: void
		 * @Create at: 2019-05-01
		 * @author:  dx
		 * @version: 1.0
		 */
		//http://127.0.0.1:8080/Erp1.1/kqParamSetContr/deleteAttShiftGroup?id=3
		@ResponseBody
		@RequestMapping(value="/deleteAttShiftGroup",method = {RequestMethod.POST})
		public JSONObject deleteAttShiftGroup(@RequestBody Map<String,Object> requestMap) {
			   JSONObject result = new JSONObject();
		       String retCode = Constant.Result.SUCCESS;
		       String retMsg = Constant.Result.SUCCESS_MSG;
		       try {
		    	    int id = MapUtil.getIntIgnoreCase(requestMap, "id", MapUtil.NOT_NULL);
				    KqParamSetService.deleteAttShiftGroup(id);
			   }catch (MessageException e){
			        retCode = Constant.Result.ERROR;
			        retMsg = e.getMessage();
			   }catch (Exception e){
			        retCode = Constant.Result.ERROR;
			        retMsg = Constant.Result.ERROR_MSG;
			        log.error(Constant.Result.RETMSG,e);
			  }finally {
			        result.put(Constant.Result.RETCODE, retCode);
			        result.put(Constant.Result.RETMSG, retMsg);
			        return result;
			  }    
		};
		
		/**
		 * @desc:修改班组
		 * @param:
		 * @return: void
		 * @Create at: 2019-05-01
		 * @author:  dx
		 * @version: 1.0
		 */
		//http://127.0.0.1:8080/Erp1.1/kqParamSetContr/updateAttShiftGroup?name=test2&remark=test2&id=4
		@ResponseBody
		@RequestMapping(value="/updateAttShiftGroup",method = {RequestMethod.POST})
		public JSONObject updateAttShiftGroup(HttpServletRequest request,HttpServletResponse response) {
			   JSONObject result = new JSONObject();
		       String retCode = Constant.Result.SUCCESS;
		       String retMsg = Constant.Result.SUCCESS_MSG;
		       try {
		    	   String str = ToolClass.getStrFromInputStream(request);
		    	   if(StringUtils.isBlank(str))throw new MessageException("参数接收失败！");
		    	   AttShiftGroupBean attShiftGroupBean = MapUtil.transJsonStrToObjectIgnoreCase(str,AttShiftGroupBean.class);
		    	   //JSONObject.toJSONString(attShiftGroupBean, MapUtil.JSON_NULL_VALUE_FILTER, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteDateUseDateFormat);
		    	   if(StringUtils.isBlank(attShiftGroupBean.getName()) || StringUtils.isBlank(attShiftGroupBean.getId()))throw new MessageException("班组名或班组id为空！");
					KqParamSetService.updateAttShiftGroup(attShiftGroupBean);
			   }catch (MessageException e){
			        retCode = Constant.Result.ERROR;
			        retMsg = e.getMessage();
			   }catch (Exception e){
			        retCode = Constant.Result.ERROR;
			        retMsg = Constant.Result.ERROR_MSG;
			        log.error(Constant.Result.RETMSG,e);
			  }finally {
			        result.put(Constant.Result.RETCODE, retCode);
			        result.put(Constant.Result.RETMSG, retMsg);
			        return result;
			  }    
		};
		
		/**
		 * @desc:查询部门信息
		 * @param:
		 * @return: JSONObject
		 * @Create at: 2019-05-02
		 * @author:  dx
		 * @version: 1.0
		 */
		@ResponseBody
		@RequestMapping(value="/queryDepartment",method = {RequestMethod.POST})
		public JSONObject queryDepartment(HttpServletRequest request,HttpServletResponse response) {
			   JSONObject result = new JSONObject();
		       String retCode = Constant.Result.SUCCESS;
		       String retMsg = Constant.Result.SUCCESS_MSG;
		       try {
		    	   result.put(Constant.Result.RETDATA,JSONObject.parse(JSONObject.toJSONString(KqParamSetService.queryDepartment(), MapUtil.JSON_NULL_VALUE_FILTER, SerializerFeature.WriteMapNullValue)));
			   }catch (MessageException e){
			        retCode = Constant.Result.ERROR;
			        retMsg = e.getMessage();
			   }catch (Exception e){
			        retCode = Constant.Result.ERROR;
			        retMsg = Constant.Result.ERROR_MSG;
			        log.error(Constant.Result.RETMSG,e);
			  }finally {
			        result.put(Constant.Result.RETCODE, retCode);
			        result.put(Constant.Result.RETMSG, retMsg);
			        return result;
			  }    
		};
		
		/**
		 * @desc:增加部门信息
		 * @param:
		 * @return: JSONObject
		 * @Create at: 2019-05-02
		 * @author:  dx
		 * @version: 1.0
		 */
		@ResponseBody
		@RequestMapping(value="/addDepartment",method = {RequestMethod.POST})
		public JSONObject addDepartment(HttpServletRequest request) {
			   JSONObject result = new JSONObject();
		       String retCode = Constant.Result.SUCCESS;
		       String retMsg = Constant.Result.SUCCESS_MSG;
		       try {
		    	   String str = ToolClass.getStrFromInputStream(request);
		    	   if(StringUtils.isBlank(str))throw new MessageException("参数接收失败！");
		    	   DepartmentBean departmentBean = MapUtil.transJsonStrToObjectIgnoreCase(str,DepartmentBean.class);
		    	   KqParamSetService.addDepartment(departmentBean);
		    	   if(false)throw new MessageException("1222");
			   }catch (MessageException e){
			        retCode = Constant.Result.ERROR;
			        retMsg = e.getMessage();
			   }catch (Exception e){
			        retCode = Constant.Result.ERROR;
			        retMsg = Constant.Result.ERROR_MSG;
			        log.error(Constant.Result.RETMSG,e);
			  }finally {
			        result.put(Constant.Result.RETCODE, retCode);
			        result.put(Constant.Result.RETMSG, retMsg);
			        return result;
			  }    
		};
		
		/**
		 * @desc:删除部门信息
		 * @param:
		 * @return: JSONObject
		 * @Create at: 2019-05-02
		 * @author:  dx
		 * @version: 1.0
		 */
		@ResponseBody
		@RequestMapping(value="/deleteDepartment",method = {RequestMethod.POST})
		public JSONObject deleteDepartment(@RequestBody Map<String,Object> requestMap) {
			   JSONObject result = new JSONObject();
		       String retCode = Constant.Result.SUCCESS;
		       String retMsg = Constant.Result.SUCCESS_MSG;
		       try {
		    	   int id = MapUtil.getIntIgnoreCase(requestMap, "id", MapUtil.NOT_NULL);
		    	   KqParamSetService.deleteDepartment(id);
			   }catch (MessageException e){
			        retCode = Constant.Result.ERROR;
			        retMsg = e.getMessage();
			   }catch (Exception e){
			        retCode = Constant.Result.ERROR;
			        retMsg = Constant.Result.ERROR_MSG;
			        log.error(Constant.Result.RETMSG,e);
			  }finally {
			        result.put(Constant.Result.RETCODE, retCode);
			        result.put(Constant.Result.RETMSG, retMsg);
			        return result;
			  }    
		};
		
		/**
		 * @desc:修改部门信息
		 * @param:
		 * @return: JSONObject
		 * @Create at: 2019-05-02
		 * @author:  dx
		 * @version: 1.0
		 */
		@ResponseBody
		@RequestMapping(value="/updateDepartment",method = {RequestMethod.POST})
		public JSONObject updateDepartment(HttpServletRequest request) {
			   JSONObject result = new JSONObject();
		       String retCode = Constant.Result.SUCCESS;
		       String retMsg = Constant.Result.SUCCESS_MSG;
		       try {
		    	   String str = ToolClass.getStrFromInputStream(request);
		    	   if(StringUtils.isBlank(str))throw new MessageException("参数接收失败！");
		    	   DepartmentBean departmentBean = MapUtil.transJsonStrToObjectIgnoreCase(str,DepartmentBean.class);
		    	   KqParamSetService.updateDepartment(departmentBean);
			   }catch (MessageException e){
			        retCode = Constant.Result.ERROR;
			        retMsg = e.getMessage();
			   }catch (Exception e){
			        retCode = Constant.Result.ERROR;
			        retMsg = Constant.Result.ERROR_MSG;
			        log.error(Constant.Result.RETMSG,e);
			  }finally {
			        result.put(Constant.Result.RETCODE, retCode);
			        result.put(Constant.Result.RETMSG, retMsg);
			        return result;
			  }    
		};
}
