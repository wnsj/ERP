package com.jiubo.erp.zpgl.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.jiubo.erp.common.Constant;
import com.jiubo.erp.common.MapUtil;
import com.jiubo.erp.common.MessageException;
import com.jiubo.erp.kqgl.controller.KqParamSetController;
import com.jiubo.erp.zpgl.bean.RecruitDataBean;
import com.jiubo.erp.zpgl.service.ZpglService;
import com.quicksand.push.ToolClass;

@Controller
@Scope("prototype")
@RequestMapping("/zpglController")
public class ZpglController {
	
	public static Logger log = LoggerFactory.getLogger(ZpglController.class);
	
	@Autowired
	private ZpglService zpglService;
	
	/**
	 * @desc:查询招聘渠道
	 * @param:
	 * @return: JSONObject
	 * @Create at: 2019-05-09
	 * @author:  dx
	 * @version: 1.0
	 */
	//http://127.0.0.1:8080/Erp/zpglController/queryRecruitChannel
	@ResponseBody
	@RequestMapping(value="/queryRecruitChannel",method = {RequestMethod.POST})
	public JSONObject queryRecruitChannel(HttpServletRequest request,HttpServletResponse response){
		   JSONObject result = new JSONObject();
	       String retCode = Constant.Result.SUCCESS;
	       String retMsg = Constant.Result.SUCCESS_MSG;
		   try {
		  	    result.put(Constant.Result.RETDATA,zpglService.queryRecruitChannel());
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
	 * @desc:删除招聘渠道
	 * @param:
	 * @return: JSONObject
	 * @Create at: 2019-05-09
	 * @author:  dx
	 * @version: 1.0
	 */
	//http://127.0.0.1:8080/Erp/zpglController/deleteRecruitChannel?id=2
	@ResponseBody
	@RequestMapping(value="/deleteRecruitChannel",method = {RequestMethod.POST})
	public JSONObject deleteRecruitChannel(@RequestBody Map<String,Object> requestMap,HttpServletRequest request,HttpServletResponse response){
		   JSONObject result = new JSONObject();
	       String retCode = Constant.Result.SUCCESS;
	       String retMsg = Constant.Result.SUCCESS_MSG;
		   try {
			    String id = MapUtil.getStringIgnoreCase(requestMap, "id", MapUtil.NOT_NULL);
		  	    zpglService.deleteRecruitChannel(id);
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
	 * @desc:查询面试信息
	 * @param:
	 * @return: JSONObject
	 * @Create at: 2019-05-10
	 * @author:  dx
	 * @version: 1.0
	 */
	//
	@ResponseBody
	@RequestMapping(value="/queryRecruitData",method = {RequestMethod.POST})
	public JSONObject queryRecruitData(HttpServletRequest request,HttpServletResponse response){
		   JSONObject result = new JSONObject();
	       String retCode = Constant.Result.SUCCESS;
	       String retMsg = Constant.Result.SUCCESS_MSG;
		   try {
			   String str = ToolClass.getStrFromInputStream(request);
	    	   if(StringUtils.isBlank(str))throw new MessageException("参数接收失败！");
	    	   RecruitDataBean recruitDataBean = MapUtil.transJsonStrToObjectIgnoreCase(str,RecruitDataBean.class);
		  	   result.put(Constant.Result.RETDATA,zpglService.queryRecruitData(recruitDataBean));
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
	 * @desc:添加面试信息
	 * @param:
	 * @return: JSONObject
	 * @Create at: 2019-05-10
	 * @author:  dx
	 * @version: 1.0
	 */
	//
	@ResponseBody
	@RequestMapping(value="/addRecruitData",method = {RequestMethod.POST})
	public JSONObject addRecruitData(HttpServletRequest request,HttpServletResponse response){
		   JSONObject result = new JSONObject();
	       String retCode = Constant.Result.SUCCESS;
	       String retMsg = Constant.Result.SUCCESS_MSG;
		   try {
			   String str = ToolClass.getStrFromInputStream(request);
	    	   if(StringUtils.isBlank(str))throw new MessageException("参数接收失败！");
	    	   RecruitDataBean recruitDataBean = MapUtil.transJsonStrToObjectIgnoreCase(str,RecruitDataBean.class);
		  	   zpglService.addRecruitData(recruitDataBean);
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
	 * @desc:修改面试信息
	 * @param:
	 * @return: JSONObject
	 * @Create at: 2019-05-10
	 * @author:  dx
	 * @version: 1.0
	 */
	@ResponseBody
	@RequestMapping(value="/updateRecruitData",method = {RequestMethod.POST})
	public JSONObject updateRecruitData(HttpServletRequest request,HttpServletResponse response){
		   JSONObject result = new JSONObject();
	       String retCode = Constant.Result.SUCCESS;
	       String retMsg = Constant.Result.SUCCESS_MSG;
		   try {
			   String str = ToolClass.getStrFromInputStream(request);
	    	   if(StringUtils.isBlank(str))throw new MessageException("参数接收失败！");
	    	   RecruitDataBean recruitDataBean = MapUtil.transJsonStrToObjectIgnoreCase(str,RecruitDataBean.class);
		  	   zpglService.updateRecruitData(recruitDataBean);
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
	 * @desc:删除面试信息（逻辑删除）
	 * @param:
	 * @return: JSONObject
	 * @Create at: 2019-05-10
	 * @author:  dx
	 * @version: 1.0
	 */
	@ResponseBody
	@RequestMapping(value="/updateRecruitDataById",method = {RequestMethod.POST})
	public JSONObject updateRecruitDataById(@RequestBody Map<String,Object> requestMap,HttpServletRequest request,HttpServletResponse response){
		   JSONObject result = new JSONObject();
	       String retCode = Constant.Result.SUCCESS;
	       String retMsg = Constant.Result.SUCCESS_MSG;
		   try {
			   String id = MapUtil.getStringIgnoreCase(requestMap, "id", MapUtil.NOT_NULL);
		  	   zpglService.deleteRecruitChannel(id);
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
	
	@ResponseBody
	@RequestMapping(value="/test",method = {RequestMethod.GET,RequestMethod.POST})
	public JSONObject test(HttpServletRequest request,HttpServletResponse response){
		   JSONObject result = new JSONObject();
	       String retCode = Constant.Result.SUCCESS;
	       String retMsg = Constant.Result.SUCCESS_MSG;
		   try {
//			   String str = ToolClass.getStrFromInputStream(request);
//			   log.error("str:{}",str);
	    	   //if(StringUtils.isBlank(str))throw new MessageException("参数接收失败！");
//	    	   RecruitDataBean recruitDataBean = MapUtil.transJsonStrToObjectIgnoreCase(str,RecruitDataBean.class);
//		  	   result.put(Constant.Result.RETDATA,zpglService.queryRecruitData(recruitDataBean));
			   zpglService.test();
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
}
