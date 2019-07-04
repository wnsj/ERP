package com.jiubo.erp.wzbg.contoller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.jiubo.erp.common.Constant;
import com.jiubo.erp.common.MapUtil;
import com.jiubo.erp.common.MessageException;
import com.jiubo.erp.rygl.controller.EmpController;
import com.jiubo.erp.wzbg.service.PLOService;
import com.jiubo.erp.wzbg.vo.PLOParam;
import com.quicksand.push.ToolClass;

/**
 * 
 * @author mwl
 *
 */
@Controller
@RequestMapping("/wzbg")
public class PLOController {

	public static Logger log = LoggerFactory.getLogger(EmpController.class);

    @Autowired
    private PLOService service;
	
   /**
    * 查询相关的请假信息
    * @param response
    * @param request
    * @return
    * JSONObject
    * @author 作者 : mwl
    * @version 创建时间：2019年7月3日 下午2:35:25
    */
	@SuppressWarnings("finally")
    @ResponseBody
    @RequestMapping(value = "/askOfLeaveList")
    public JSONObject askOfLeaveList(HttpServletResponse response, HttpServletRequest request) {
        PLOParam plop = new PLOParam();
        JSONObject result = new JSONObject();
        String retCode = Constant.Result.SUCCESS;
        String retMsg = Constant.Result.SUCCESS_MSG;
        System.out.println("getSearchContent：" );
        try {
            String str = ToolClass.getStrFromInputStream(request);
            if (StringUtils.isBlank(str))
                throw new MessageException("参数接收失败！");
            plop = MapUtil.transJsonStrToObjectIgnoreCase(str, PLOParam.class);
            System.out.println("getSearchContent：" + plop.getDepartId()+plop.toString());
            result.put("resData", this.service.askOfLeaveList(plop));
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
	 * 查询相关部门的人员列表
	 * @param response
	 * @param request
	 * @return
	 * JSONObject
	 * @author 作者 : mwl
	 * @version 创建时间：2019年7月3日 下午2:37:15
	 */
	@SuppressWarnings("finally")
    @ResponseBody
    @RequestMapping(value = "/departOfEmpList")
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
            result.put("resData", this.service.askOfLeaveList(plop));
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
