package com.jiubo.erp.wzbg.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.jiubo.erp.common.Constant;
import com.jiubo.erp.common.MapUtil;
import com.jiubo.erp.common.MessageException;
import com.jiubo.erp.wzbg.bean.LeavePrepareBean;
import com.jiubo.erp.wzbg.service.WzbgService;
import com.quicksand.push.ToolClass;

/**
 * @version: V1.0
 * @author: DingDong
 * @className: WzbgController
 * @description: 无纸化办公业务实现层
 * @data: 2019-06-29
 **/
@Controller
@Scope("prototype")
@RequestMapping("/wzbgController")
public class WzbgController {
	
	private final static Logger logger  = LoggerFactory.getLogger(WzbgController.class);
	
	@Autowired
	private WzbgService wzbgService;
	
	/**
	 * @Description: 添加请假报备
	 * @param  leavePrepareBean(请假报备实体)
	 * @return 
	 * @author: DingDong
	 * @date: 2019年06月29日
	 * @version: V1.0
	 */
	//http://127.0.0.1:8080/Erp/wzbgController/addLeavePrepare
	@ResponseBody
	@RequestMapping(value="/addLeavePrepare", method=RequestMethod.POST)
	public JSONObject addLeavePrepare(HttpServletRequest request,HttpServletResponse response) {
		JSONObject result = new JSONObject();
		String retCode = Constant.Result.SUCCESS;
		String retMsg = Constant.Result.SUCCESS_MSG;
		// 异常处理
		try {
			String str = ToolClass.getStrFromInputStream(request);
			if(StringUtils.isBlank(str)) {
				throw new MessageException("参数接收失败！");
			}
			LeavePrepareBean leavePrepareBean = MapUtil.transJsonStrToObjectIgnoreCase(str,LeavePrepareBean.class);
			wzbgService.addLeavePrepareBean(leavePrepareBean);
			logger.info("------------添加成功----------");
		} catch (MessageException e){
	        retCode = Constant.Result.ERROR;
	        retMsg = e.getMessage();
		} catch (Exception e){
		        retCode = Constant.Result.ERROR;
		        retMsg = Constant.Result.ERROR_MSG;
		        // 日志记录
		        logger.error(Constant.Result.RETMSG,e);
		}finally {
			result.put(Constant.Result.RETCODE, retCode);
	        result.put(Constant.Result.RETMSG, retMsg);
		}
		return result;
	}
	
	/**
	 * @Description: 查询请假报备审批权限账户信息
	 * @param  
	 * @return 
	 * @author: DingDong
	 * @date: 2019年07月02日
	 * @version: V1.0
	 */
	//http://127.0.0.1:8080/Erp/wzbgController/queryApprovalLeaveAccount
	@ResponseBody
	@RequestMapping(value="/queryApprovalLeaveAccount", method=RequestMethod.POST)
	public JSONObject queryApprovalLeaveAccount(HttpServletRequest request,HttpServletResponse response) {
		JSONObject result = new JSONObject();
        String retCode = Constant.Result.SUCCESS;
        String retMsg = Constant.Result.SUCCESS_MSG;
        try {
            result.put(Constant.Result.RETDATA, wzbgService.queryApprovalLeaveAccount());
        } catch (Exception e) {
            retCode = Constant.Result.ERROR;
            retMsg = Constant.Result.ERROR_MSG;
            logger.error(Constant.Result.RETMSG, e);
        } finally {
            result.put(Constant.Result.RETCODE, retCode);
            result.put(Constant.Result.RETMSG, retMsg);
        }
        return result;
	}
}
