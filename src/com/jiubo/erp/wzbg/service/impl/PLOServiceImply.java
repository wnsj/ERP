package com.jiubo.erp.wzbg.service.impl;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.jiubo.erp.common.Constant;
import com.jiubo.erp.common.MapUtil;
import com.jiubo.erp.common.MessageException;
import com.jiubo.erp.rygl.controller.EmpController;
import com.jiubo.erp.wzbg.bean.AskForLeaveBean;
import com.jiubo.erp.wzbg.dao.PLODao;
import com.jiubo.erp.wzbg.service.PLOService;
import com.jiubo.erp.wzbg.vo.PLOParam;
import com.quicksand.push.ToolClass;

@Service
public class PLOServiceImply implements PLOService {

	public static Logger log = LoggerFactory.getLogger(EmpController.class);
	
	@Autowired
    private PLODao dao;
	
	/**
	    * 请假-列表
	    * @param response
	    * @param request
	    * @return
	    * JSONObject
	    * @author 作者 : mwl
	    * @version 创建时间：2019年7月3日 下午2:35:25
	    */
	@SuppressWarnings("finally")
	@Override
	public JSONObject askOfLeaveList(HttpServletResponse response, HttpServletRequest request) {
		PLOParam plop = new PLOParam();
        JSONObject result = new JSONObject();
        String retCode = Constant.Result.SUCCESS;
        String retMsg = Constant.Result.SUCCESS_MSG;
        try {
            String str = ToolClass.getStrFromInputStream(request);
            if (StringUtils.isBlank(str))
                throw new MessageException("参数接收失败！");
            plop = MapUtil.transJsonStrToObjectIgnoreCase(str, PLOParam.class);
            System.out.println("plop:"+plop.toString());
            result.put("resData", this.dao.selectAskForLeaveList(plop));
        } catch (MessageException e) {
            retCode = Constant.Result.ERROR;
            retMsg = e.getMessage();
        } catch (Exception e) {
            retCode = Constant.Result.ERROR;
            retMsg = Constant.Result.ERROR_MSG;
            log.error(Constant.Result.RETMSG, e);
        } finally {
            result.put(Constant.Result.RETCODE, retCode);
            result.put(Constant.Result.RETMSG, retMsg);
            return result;
        }
		
	}
	/**
	 * 请假 -- 代理人员列表
	 * @param response
	 * @param request
	 * @return
	 * JSONObject
	 * @author 作者 : mwl
	 * @version 创建时间：2019年7月3日 下午2:37:15
	 */
	@SuppressWarnings("finally")
	@Override
	public JSONObject selectDepartOfEmpList(HttpServletResponse response, HttpServletRequest request) {
		PLOParam plop = new PLOParam();
        JSONObject result = new JSONObject();
        String retCode = Constant.Result.SUCCESS;
        String retMsg = Constant.Result.SUCCESS_MSG;
        try {
            String str = ToolClass.getStrFromInputStream(request);
            if (StringUtils.isBlank(str))
                throw new MessageException("参数接收失败！");
            plop = MapUtil.transJsonStrToObjectIgnoreCase(str, PLOParam.class);
            System.out.println("getSearchContent：" + plop.getDepartId()+plop.toString());
            result.put("resData", this.dao.selectDepartOfEmpList(plop));
        } catch (MessageException e) {
            retCode = Constant.Result.ERROR;
            retMsg = e.getMessage();
        } catch (Exception e) {
            retCode = Constant.Result.ERROR;
            retMsg = Constant.Result.ERROR_MSG;
            log.error(Constant.Result.RETMSG, e);
        } finally {
            result.put(Constant.Result.RETCODE, retCode);
            result.put(Constant.Result.RETMSG, retMsg);
            return result;
        }
	}
	
	/**
	 * 请假审查 -- 审查人列表 根据请假人的级别查看审查列表
	 * @param response
	 * @param request
	 * @return
	 * JSONObject
	 * @author 作者 : mwl
	 * @version 创建时间：2019年7月8日 上午10:20:08
	 */
	@SuppressWarnings("finally")
	public JSONObject checkOfEmpList(HttpServletResponse response, HttpServletRequest request) {
		Map<String, String> levelMap = new HashMap<>();
        JSONObject result = new JSONObject();
        String retCode = Constant.Result.SUCCESS;
        String retMsg = Constant.Result.SUCCESS_MSG;
        try {
            levelMap = ToolClass.mapShiftStr(request);
            result.put("resData", this.dao.checkOfEmpList(levelMap.get("level"),levelMap.get("positionId"),levelMap.get("departId"),levelMap.get("clickTimes")));
        } catch (Exception e) {
            retCode = Constant.Result.ERROR;
            retMsg = Constant.Result.ERROR_MSG;
            log.error(Constant.Result.RETMSG, e);
        } finally {
            result.put(Constant.Result.RETCODE, retCode);
            result.put(Constant.Result.RETMSG, retMsg);
            return result;
        }
	}
	
	/**
	 * 请假申请
	 * @param response
	 * @param request
	 * @return
	 * JSONObject
	 * @author 作者 : mwl
	 * @version 创建时间：2019年7月8日 上午9:43:42
	 */
	@SuppressWarnings("finally")
	public JSONObject insertLeaveApplication(HttpServletResponse response, HttpServletRequest request) {
		
		Map<String, String> departMap = new HashMap<>();
        JSONObject result = new JSONObject();
        String retCode = Constant.Result.SUCCESS;
        String retMsg = Constant.Result.SUCCESS_MSG;
        try {
        	departMap = ToolClass.mapShiftStr(request);
//            result.put("resData", this.dao.checkOfEmpList(departMap.get("departId")));
        } catch (Exception e) {
            retCode = Constant.Result.ERROR;
            retMsg = Constant.Result.ERROR_MSG;
            log.error(Constant.Result.RETMSG, e);
        } finally {
            result.put(Constant.Result.RETCODE, retCode);
            result.put(Constant.Result.RETMSG, retMsg);
            return result;
        }
	}
	
	@SuppressWarnings("finally")
	public JSONObject restDownList(HttpServletResponse response, HttpServletRequest request) {
        PLOParam plop = new PLOParam();
        JSONObject result = new JSONObject();
        String retCode = Constant.Result.SUCCESS;
        String retMsg = Constant.Result.SUCCESS_MSG;
        try {
            String str = ToolClass.getStrFromInputStream(request);
            if (StringUtils.isBlank(str))
                throw new MessageException("参数接收失败！");
            plop = MapUtil.transJsonStrToObjectIgnoreCase(str, PLOParam.class);
            System.out.println("plop:"+plop.toString());
            result.put("resData", "");
        } catch (MessageException e) {
            retCode = Constant.Result.ERROR;
            retMsg = e.getMessage();
        } catch (Exception e) {
            retCode = Constant.Result.ERROR;
            retMsg = Constant.Result.ERROR_MSG;
            log.error(Constant.Result.RETMSG, e);
        } finally {
            result.put(Constant.Result.RETCODE, retCode);
            result.put(Constant.Result.RETMSG, retMsg);
            return result;
        }
    }
}
