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
	//http://127.0.0.1:8080/Erp/zpglController/addRecruitData
	//参数：{name:'小强',/*姓名（必填）*/sex:'男',birth:'2012-10-01'/*生日*/,idNum:'42187752136255245'/*身份证*/,phone:'110'/*手机*/,mail:'123456@qq.com'/*邮箱*/,qq:'123456'/*qq*/,address:'地球'/*现在地址*/,homeAddress:'地球村'/*家庭地址*/,homeTown:'地球省'/*籍贯*/,accountProp:'农村'/*户口性质*/,ploitical:'团员'/*政治面貌*/,marital:'未婚'/*婚姻*/,nationality:'汉族'/*民族*/,height:'170'/*身高*/,weight:'100'/*体重*/,bloodType:'A'/*血型*/,education:'大专'/*学历*/,school:'地球学校'/*毕业院校*/,graduation:'2016-06-01'/*毕业时间*/,profession:'软件技术'/*专业*/,atSchool:'0'/*是否在校*/,workCompany:'地球公司'/*工作单位*/,workexp:'2'/*相关经验*/,certificate:'OAT'/*技能证书*/,channel:'3'/*应聘渠道（必填）*/,position:'3'/*职位（必填）*/,department:'2'/*部门（必填）*/,wages:'100000'/*期望薪资*/,interviewer:'面试官'/*面试官*/,recruitDate:'2016-01-01'/*面试时间（必填）*/,invitationDate:'2016-01-02'/*邀约时间（必填）*/,score:'60'/*成绩*/,isQualified:'待定'/*是否合格*/,isPay:'0'/*报销路费*/,remark:'备注'/*备注*/,updateAccount:'2020'/*修改人账户id*/}
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
	//http://127.0.0.1:8080/Erp/zpglController/updateRecruitData
	//参数：{id:5/*（必填）*/,name:'小强9',/*姓名（必填）*/sex:'女',birth:'2012-10-09'/*生日*/,idNum:'42187752136255249'/*身份证*/,phone:'1109'/*手机*/,mail:'1234569@qq.com'/*邮箱*/,qq:'1234569'/*qq*/,address:'地球9'/*现在地址*/,homeAddress:'地球村9'/*家庭地址*/,homeTown:'地球省9'/*籍贯*/,accountProp:'农村9'/*户口性质*/,ploitical:'团员9'/*政治面貌*/,marital:'未婚9'/*婚姻*/,nationality:'汉族9'/*民族*/,height:'1709'/*身高*/,weight:'1009'/*体重*/,bloodType:'A9'/*血型*/,education:'大专9'/*学历*/,school:'地球学校9'/*毕业院校*/,graduation:'2016-06-09'/*毕业时间*/,profession:'软件技术9'/*专业*/,atSchool:'1'/*是否在校*/,workCompany:'地球公司9'/*工作单位*/,workexp:'29'/*相关经验*/,certificate:'OAT9'/*技能证书*/,channel:'9'/*应聘渠道（必填）*/,position:'9'/*职位（必填）*/,department:'9'/*部门（必填）*/,wages:'100009'/*期望薪资*/,interviewer:'面试官9'/*面试官*/,recruitDate:'2016-01-09'/*面试时间（必填）*/,invitationDate:'2016-01-09'/*邀约时间（必填）*/,score:'69'/*成绩*/,isQualified:'合格'/*是否合格*/,isPay:'1'/*报销路费*/,remark:'备注9'/*备注*/}
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
		  	   zpglService.updateRecruitData(id);
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
