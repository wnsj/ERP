package com.jiubo.erp.wzbg.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.jiubo.erp.common.Constant;
import com.jiubo.erp.common.MapUtil;
import com.jiubo.erp.common.MessageException;
import com.jiubo.erp.wzbg.bean.LeaveForgetBean;
import com.jiubo.erp.wzbg.bean.LeavePrepareBean;
import com.jiubo.erp.wzbg.service.LeaveForgetService;
import com.quicksand.push.ToolClass;

/**
 * @version: V1.0
 * @author: DingDong
 * @className: LeaveForgetController
 * @description: 忘记打卡证明控制层
 * @data: 2019-07-12
 **/
@RestController
@Scope("prototype")
@RequestMapping("leaveForgetController")
public class LeaveForgetController {
	
	private final static Logger logger  = LoggerFactory.getLogger(LeaveForgetController.class);
	
	@Autowired
	private LeaveForgetService leaveForgetService;
	
	/**
	 * @Description: 查询忘记打卡证明接口
	 * @author: DingDong
	 * @date: 2019年07月12日
	 * @version: V1.0
	 */
	//http://127.0.0.1:8080/Erp/leaveForgetController/queryLeaveForget
	@RequestMapping(value="/queryLeaveForget", method=RequestMethod.POST)
	public JSONObject queryLeaveForget(HttpServletRequest request,HttpServletResponse response) {
		JSONObject result = new JSONObject();
		String retCode = null;
		String retMsg = null;
	    String retData = null;
		try {
			String str = ToolClass.getStrFromInputStream(request);
			if(StringUtils.isBlank(str)) {
				throw new MessageException("参数接收失败！");
			}
			LeaveForgetBean leaveForgetBean = MapUtil.transJsonStrToObjectIgnoreCase(str,LeaveForgetBean.class);
			List<LeaveForgetBean> list = leaveForgetService.queryLeaveForgetBean(leaveForgetBean);
			
			retCode = Constant.Result.SUCCESS;
			retMsg = Constant.Result.SUCCESS_MSG;
			retData = Constant.Result.RETDATA;
			
			result.put(retData, list);
			logger.info("--------------查询忘记打卡证明成功 返回json数据-------------------");
			return result;
		} catch (IOException e) {
			retCode = Constant.Result.ERROR;
            retMsg = Constant.Result.ERROR_MSG;
            logger.error("--------------IOException异常-------------------");
            return result;
		} catch (MessageException e) {
			retCode = Constant.Result.ERROR;
			retMsg = e.getMessage();
			logger.error("--------------MessageException-------------------");
			return result;
		} catch (Exception e) {
			retCode = Constant.Result.ERROR;
            retMsg = Constant.Result.ERROR_MSG;
            logger.error("--------------忘记打卡证明查询失败-------------------");
            return result;
		}finally {
			result.put(Constant.Result.RETCODE, retCode);
			result.put(Constant.Result.RETMSG, retMsg);
		}
	}
	
	/**
	 * @Description: 新增忘记打卡证明接口
	 * @author: DingDong
	 * @date: 2019年07月13日
	 * @version: V1.0
	 */
	//http://127.0.0.1:8080/Erp/leaveForgetController/addLeaveForget
	@RequestMapping(value="/addLeaveForget", method=RequestMethod.POST)
	public JSONObject addLeavePrepare(HttpServletRequest request,HttpServletResponse response) {
		JSONObject result = new JSONObject();
		String retCode = null;
		String retMsg = null;
		try {
			String str = ToolClass.getStrFromInputStream(request);
			if(StringUtils.isBlank(str)) {
				throw new MessageException("参数接收失败！");
			}
			LeaveForgetBean leaveForgetBean = MapUtil.transJsonStrToObjectIgnoreCase(str,LeaveForgetBean.class);
			leaveForgetService.addLeaveForgetBean(leaveForgetBean);
			
			retCode = Constant.Result.SUCCESS;
			retMsg = Constant.Result.SUCCESS_MSG;
			logger.info("--------------新增忘记打卡证明成功-------------------");
			return result;
		} catch (IOException e) {
			retCode = Constant.Result.ERROR;
            retMsg = Constant.Result.ERROR_MSG;
            logger.error("--------------IOException异常-------------------");
            return result;
		} catch (MessageException e) {
			retCode = Constant.Result.ERROR;
			retMsg = e.getMessage();
			logger.error("--------------参数接收失败-------------------");
			return result;
		} catch (Exception e) {
			retCode = Constant.Result.ERROR;
            retMsg = Constant.Result.ERROR_MSG;
            logger.error("--------------新增忘记打卡证明失败-------------------");
            return result;
		}finally {
			result.put(Constant.Result.RETCODE, retCode);
			result.put(Constant.Result.RETMSG, retMsg);
		}
	}

}
