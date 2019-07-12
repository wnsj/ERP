package com.jiubo.erp.wzbg.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jiubo.erp.wzbg.bean.OfficeSuppliesDataBean;
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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jiubo.erp.common.Constant;
import com.jiubo.erp.common.CookieTools;
import com.jiubo.erp.common.MapUtil;
import com.jiubo.erp.common.MessageException;
import com.jiubo.erp.erpLogin.bean.AccountDataBean;
import com.jiubo.erp.wzbg.bean.LeavePrepareBean;
import com.jiubo.erp.wzbg.service.WzbgService;
import com.jiubo.erp.wzbg.vo.AccWithApprovalLeaveAuth;
import com.jiubo.erp.wzbg.vo.DeptWithEmp;
import com.quicksand.push.ToolClass;

/**
 * @version: V1.0
 * @author: DingDong
 * @className: WzbgController
 * @description: 无纸化办公业控制层
 * @data: 2019-06-29
 **/
@Controller
@Scope("prototype")
@RequestMapping("/wzbgController")
public class WzbgController {

    private final static Logger logger = LoggerFactory.getLogger(WzbgController.class);

    @Autowired
    private WzbgService wzbgService;

    /**
     * @param
     * @return JSONObject
     * @Description: 查询部门下的员工姓名以及ERP账户信息
     * @author: DingDong
     * @date: 2019年7月1日
     * @version: V1.0
     */
    //http://127.0.0.1:8080/Erp/wzbgController/queryEmpInfoByDept
    @ResponseBody
    @RequestMapping(value = "/queryEmpInfoByDept", method = RequestMethod.POST)
    public JSONObject queryEmpInfoByDept(@RequestBody Map<String, Object> params, HttpServletRequest request, HttpServletResponse response) {
        JSONObject result = new JSONObject();
        String retCode = null;
        String retMsg = null;
        String retData = null;
        try {
            String id = MapUtil.getString(params, "departmentId", MapUtil.ALLOW_NULL);
            List<DeptWithEmp> list = wzbgService.queryEmpInfoByDept(id);

            retCode = Constant.Result.SUCCESS;
            retMsg = Constant.Result.SUCCESS_MSG;
            retData = Constant.Result.RETDATA;

            result.put(retData, list);
            logger.info("--------------查询部门下的员工姓名以及ERP账户信息成功 返回json数据-------------------");
            return result;
        } catch (MessageException e) {
            retCode = Constant.Result.ERROR;
            retMsg = e.getMessage();
            logger.error("--------------MessageException-------------------");
            return result;
        } catch (Exception e) {
            retCode = Constant.Result.ERROR;
            retMsg = Constant.Result.ERROR_MSG;
            logger.error("--------------查询部门下的员工姓名以及ERP账户信息失败-------------------");
            return result;
        } finally {
            result.put(Constant.Result.RETCODE, retCode);
            result.put(Constant.Result.RETMSG, retMsg);
        }
    }

    /**
     * @param
     * @return
     * @Description: 请假报备审批人列表
     * @author: DingDong
     * @date: 2019年07月02日
     * @version: V1.0
     */
    //http://127.0.0.1:8080/Erp/wzbgController/queryApprovalLeaveAccount
    @ResponseBody
    @RequestMapping(value = "/queryApprovalLeaveAccount", method = RequestMethod.POST)
    public JSONObject queryApprovalLeaveAccount(HttpServletRequest request, HttpServletResponse response) {
        JSONObject result = new JSONObject();
        String retCode = Constant.Result.SUCCESS;
        String retMsg = Constant.Result.SUCCESS_MSG;
        // 异常处理
        try {
            List<AccWithApprovalLeaveAuth> list = wzbgService.queryApprovalAuthAccount();
            result.put(Constant.Result.RETDATA, list);
            logger.info("------------查询成功----------");
        } catch (Exception e) {
            retCode = Constant.Result.ERROR;
            retMsg = Constant.Result.ERROR_MSG;
            // 日志记录
            logger.error(Constant.Result.RETMSG, e);
        } finally {
            result.put(Constant.Result.RETCODE, retCode);
            result.put(Constant.Result.RETMSG, retMsg);
        }
        return result;
    }


    /**
     * @param
     * @return
     * @Description: 查询请假报备
     * @author: DingDong
     * @date: 2019年06月29日
     * @version: V1.0
     */
    //http://127.0.0.1:8080/Erp/wzbgController/queryLeavePrepare
    @ResponseBody
    @RequestMapping(value = "/queryLeavePrepare", method = RequestMethod.POST)
    public JSONObject queryLeavePrepare(HttpServletRequest request, HttpServletResponse response) {
        JSONObject result = new JSONObject();
        String retCode = null;
        String retMsg = null;
        String retData = null;
        try {
            String str = ToolClass.getStrFromInputStream(request);
            if (StringUtils.isBlank(str)) {
                throw new MessageException("参数接收失败！");
            }
            LeavePrepareBean leavePrepareBean = MapUtil.transJsonStrToObjectIgnoreCase(str, LeavePrepareBean.class);
            List<LeavePrepareBean> list = wzbgService.queryLeavePrepareBean(leavePrepareBean);

            retCode = Constant.Result.SUCCESS;
            retMsg = Constant.Result.SUCCESS_MSG;
            retData = Constant.Result.RETDATA;

            result.put(retData, list);
            logger.info("--------------查询请假报备成功 返回json数据-------------------");
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
            logger.error("--------------请假报备查询失败-------------------");
            return result;
        } finally {
            result.put(Constant.Result.RETCODE, retCode);
            result.put(Constant.Result.RETMSG, retMsg);
        }
    }

    /**
     * @param
     * @return
     * @Description: 添加请假报备
     * @author: DingDong
     * @date: 2019年06月29日
     * @version: V1.0
     */
    //http://127.0.0.1:8080/Erp/wzbgController/addLeavePrepare
    @ResponseBody
    @RequestMapping(value = "/addLeavePrepare", method = RequestMethod.POST)
    public JSONObject addLeavePrepare(HttpServletRequest request, HttpServletResponse response) {
        JSONObject result = new JSONObject();
        String retCode = null;
        String retMsg = null;
        try {
            String str = ToolClass.getStrFromInputStream(request);
            if (StringUtils.isBlank(str)) {
                throw new MessageException("参数接收失败！");
            }
            LeavePrepareBean leavePrepareBean = MapUtil.transJsonStrToObjectIgnoreCase(str, LeavePrepareBean.class);
            wzbgService.addLeavePrepareBean(leavePrepareBean);

            retCode = Constant.Result.SUCCESS;
            retMsg = Constant.Result.SUCCESS_MSG;
            logger.info("--------------添加请假报备成功-------------------");
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
            logger.error("--------------请假报备添加失败-------------------");
            return result;
        } finally {
            result.put(Constant.Result.RETCODE, retCode);
            result.put(Constant.Result.RETMSG, retMsg);
        }
    }

    /* *
     * @desc:办公用品管理查询
     * @author: dx
     * @date: 2019-07-09 09:13:56
     * @param request :
     * @param response :
     * @param params :
     * @return: com.alibaba.fastjson.JSONObject
     * @throws:
     * @version: 1.0
     **/
    //http://127.0.0.1:8080/Erp/wzbgController/queryOfficeSuppliesData?month=2019-08&
    @ResponseBody
    @RequestMapping(value = "/queryOfficeSuppliesData", method = RequestMethod.POST)
    public JSONObject queryOfficeSuppliesData(HttpServletRequest request, HttpServletResponse response, @RequestBody String params) {
        JSONObject result = new JSONObject();
        String retCode = Constant.Result.SUCCESS;
        String retMsg = Constant.Result.SUCCESS_MSG;
        try {
            if (StringUtils.isBlank(params)) throw new MessageException("参数接收失败！");
            OfficeSuppliesDataBean officeSuppliesDataBean = JSONObject.parseObject(params, OfficeSuppliesDataBean.class);
            result.put(Constant.Result.RETDATA,wzbgService.queryOfficeSuppliesData(officeSuppliesDataBean));
        } catch (MessageException e) {
            retCode = Constant.Result.ERROR;
            retMsg = e.getMessage();
        } catch (Exception e) {
            retCode = Constant.Result.ERROR;
            retMsg = Constant.Result.ERROR_MSG;
            logger.error(Constant.Result.RETMSG, e);
        } finally {
            result.put(Constant.Result.RETCODE, retCode);
            result.put(Constant.Result.RETMSG, retMsg);
            return result;
        }
    }

    /* *
     * @desc:查询办公用品名及规格
     * @author: dx
     * @date: 2019-07-10 13:40:15
     * @param request :
     * @param response :
     * @return: com.alibaba.fastjson.JSONObject
     * @throws:
     * @version: 1.0
     **/
    //http://127.0.0.1:8080/Erp/wzbgController/queryOfficeNames
    @ResponseBody
    @RequestMapping(value = "/queryOfficeNames", method = RequestMethod.POST)
    public JSONObject queryOfficeNames(HttpServletRequest request, HttpServletResponse response) {
        JSONObject result = new JSONObject();
        String retCode = Constant.Result.SUCCESS;
        String retMsg = Constant.Result.SUCCESS_MSG;
        try {
            result.put(Constant.Result.RETDATA,wzbgService.queryOfficeNames());
        } catch (MessageException e) {
            retCode = Constant.Result.ERROR;
            retMsg = e.getMessage();
        } catch (Exception e) {
            retCode = Constant.Result.ERROR;
            retMsg = Constant.Result.ERROR_MSG;
            logger.error(Constant.Result.RETMSG, e);
        } finally {
            result.put(Constant.Result.RETCODE, retCode);
            result.put(Constant.Result.RETMSG, retMsg);
            return result;
        }
    }

    /* *
     * @desc:修改或添加办公用品申请信息
     * @author: dx
     * @date: 2019-07-12 11:13:23
     * @param request :
     * @param response :
     * @param params :
     * @return: com.alibaba.fastjson.JSONObject
     * @throws:
     * @version: 1.0
     **/
    //http://127.0.0.1:8080/Erp/wzbgController/addUpdateOfficeSupplies
    @ResponseBody
    @RequestMapping(value = "/addUpdateOfficeSupplies", method = RequestMethod.POST)
    public JSONObject addUpdateOfficeSupplies(HttpServletRequest request, HttpServletResponse response,@RequestBody String params) {
        JSONObject result = new JSONObject();
        String retCode = Constant.Result.SUCCESS;
        String retMsg = Constant.Result.SUCCESS_MSG;
        try {
            if(StringUtils.isBlank(params))throw new MessageException("参数接收失败!");
            Map<String,Object> requestMap = JSONObject.parseObject(params,Map.class);
            wzbgService.addUpdateOfficeSupplies(requestMap);
        } catch (MessageException e) {
            retCode = Constant.Result.ERROR;
            retMsg = e.getMessage();
        } catch (Exception e) {
            retCode = Constant.Result.ERROR;
            retMsg = Constant.Result.ERROR_MSG;
            logger.error(Constant.Result.RETMSG, e);
        } finally {
            result.put(Constant.Result.RETCODE, retCode);
            result.put(Constant.Result.RETMSG, retMsg);
            return result;
        }
    }
}
