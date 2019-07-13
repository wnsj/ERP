package com.jiubo.erp.wzbg.controller;

import com.alibaba.fastjson.JSONObject;
import com.jiubo.erp.common.Constant;
import com.jiubo.erp.common.MessageException;
import com.jiubo.erp.wzbg.bean.OfficeSuppliesDataBean;
import com.jiubo.erp.wzbg.service.OfficeService;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @desc:
 * @date: 2019-07-12 16:25
 * @author: dx
 * @version: 1.0
 */
@Controller
@Scope("prototype")
@RequestMapping("/officeAction")
public class OfficeAction {

    private final static Logger logger  = LoggerFactory.getLogger(OfficeAction.class);

    @Autowired
    private OfficeService wzbgService;

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
    //http://127.0.0.1:8080/Erp/officeController/queryOfficeSuppliesData?month=2019-08&
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
    //http://127.0.0.1:8080/Erp/officeController/queryOfficeNames
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
    //http://127.0.0.1:8080/Erp/officeController/addUpdateOfficeSupplies
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
