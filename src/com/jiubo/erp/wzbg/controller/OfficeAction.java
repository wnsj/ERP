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

    private final static Logger logger = LoggerFactory.getLogger(OfficeAction.class);

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
    //http://127.0.0.1:8080/Erp/officeAction/queryOfficeSuppliesData?month=2019-08&
    @ResponseBody
    @RequestMapping(value = "/queryOfficeSuppliesData", method = RequestMethod.POST)
    public JSONObject queryOfficeSuppliesData(HttpServletRequest request, HttpServletResponse response, @RequestBody String params) {
        JSONObject result = new JSONObject();
        String retCode = Constant.Result.SUCCESS;
        String retMsg = Constant.Result.SUCCESS_MSG;
        try {
            if (StringUtils.isBlank(params)) throw new MessageException("参数接收失败！");
            OfficeSuppliesDataBean officeSuppliesDataBean = JSONObject.parseObject(params, OfficeSuppliesDataBean.class);
            result.put(Constant.Result.RETDATA, wzbgService.queryOfficeSuppliesData(officeSuppliesDataBean));
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
    //http://127.0.0.1:8080/Erp/officeAction/queryOfficeNames
    @ResponseBody
    @RequestMapping(value = "/queryOfficeNames", method = RequestMethod.POST)
    public JSONObject queryOfficeNames(HttpServletRequest request, HttpServletResponse response) {
        JSONObject result = new JSONObject();
        String retCode = Constant.Result.SUCCESS;
        String retMsg = Constant.Result.SUCCESS_MSG;
        try {
            result.put(Constant.Result.RETDATA, wzbgService.queryOfficeNames());
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
    //http://127.0.0.1:8080/Erp/officeAction/addUpdateOfficeSupplies
    @ResponseBody
    @RequestMapping(value = "/addUpdateOfficeSupplies", method = RequestMethod.POST)
    public JSONObject addUpdateOfficeSupplies(HttpServletRequest request, HttpServletResponse response, @RequestBody String params) {
        JSONObject result = new JSONObject();
        String retCode = Constant.Result.SUCCESS;
        String retMsg = Constant.Result.SUCCESS_MSG;
        try {
            if (StringUtils.isBlank(params)) throw new MessageException("参数接收失败!");
            Map<String, Object> requestMap = JSONObject.parseObject(params, Map.class);
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

    /* *
     * @desc:当月是否汇总查询（已经汇总返回9999，没有返回0000）
     * @author: dx
     * @date: 2019-07-16 10:04:52
     * @param request :
     * @param response :
     * @param params :
     * @return: com.alibaba.fastjson.JSONObject
     * @throws:
     * @version: 1.0
     **/
    @ResponseBody
    @RequestMapping(value = "/isHuiZong", method = RequestMethod.POST)
    public JSONObject isHuiZong(HttpServletRequest request, HttpServletResponse response, @RequestBody String params) {
        JSONObject result = new JSONObject();
        String retCode = Constant.Result.SUCCESS;
        String retMsg = Constant.Result.SUCCESS_MSG;
        try {
            if (StringUtils.isBlank(params)) throw new MessageException("参数接收失败!");
            Map<String, Object> requestMap = JSONObject.parseObject(params, Map.class);
            result.put(Constant.Result.RETDATA, wzbgService.isHuiZong(requestMap));
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
     * @desc:数据审核
     * @author: dx
     * @date: 2019-07-16 09:21:36
     * @param request :
     * @param response :
     * @param params :
     * @return: com.alibaba.fastjson.JSONObject
     * @throws:
     * @version: 1.0
     **/
    @ResponseBody
    @RequestMapping(value = "/shenHeOffice", method = RequestMethod.POST)
    public JSONObject shenHeOffice(HttpServletRequest request, HttpServletResponse response, @RequestBody String params) {
        JSONObject result = new JSONObject();
        String retCode = Constant.Result.SUCCESS;
        String retMsg = Constant.Result.SUCCESS_MSG;
        try {
            if (StringUtils.isBlank(params)) throw new MessageException("参数接收失败!");
            Map<String, Object> requestMap = JSONObject.parseObject(params, Map.class);
            wzbgService.shenHeOfficeSupplies(requestMap);
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
     * @desc:办公用品汇总
     * @author: dx
     * @date: 2019-07-17 09:48:12
     * @param request :
     * @param response :
     * @param params :
     * @return: com.alibaba.fastjson.JSONObject
     * @throws:
     * @version: 1.0
     **/
    //http://127.0.0.1:8080/Erp/officeAction/gatherOfficeSupplies?month=2019-08
    @ResponseBody
    @RequestMapping(value = "/gatherOfficeSupplies", method = RequestMethod.POST)
    public JSONObject gatherOfficeSupplies(HttpServletRequest request, HttpServletResponse response, @RequestBody String params) {
        JSONObject result = new JSONObject();
        String retCode = Constant.Result.SUCCESS;
        String retMsg = Constant.Result.SUCCESS_MSG;
        try {
            if (StringUtils.isBlank(params)) throw new MessageException("参数接收失败!");
            Map<String, Object> requestMap = JSONObject.parseObject(params, Map.class);
            result.put(Constant.Result.RETDATA,wzbgService.gatherOfficeSupplies(requestMap));
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
     * @desc:获取意见给出人
     * @author: dx
     * @date: 2019-07-17 10:21:19
     * @param request :
     * @param response :
     * @param params :
     * 参数最外层为一个json对象，里面为一个json数组（均为固定key）
     * 例：{params:[{deptName:"总经办",postName:"副总经理",key:"自定义返回key"}]}
     * @return: com.alibaba.fastjson.JSONObject
     * @throws:
     * @version: 1.0
     **/
    //http://127.0.0.1:8080/Erp/officeAction/queryAdvancePeo
    @ResponseBody
    @RequestMapping(value = "/queryAdvancePeo", method = RequestMethod.POST)
    public JSONObject queryAdvancePeo(HttpServletRequest request, HttpServletResponse response, @RequestBody String params) {
        JSONObject result = new JSONObject();
        String retCode = Constant.Result.SUCCESS;
        String retMsg = Constant.Result.SUCCESS_MSG;
        try {
            if (StringUtils.isBlank(params)) throw new MessageException("参数接收失败!");
            Map<String, Object> requestMap = JSONObject.parseObject(params, Map.class);
            result.put(Constant.Result.RETDATA,wzbgService.queryAdvancePeo(requestMap));
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
      * @desc:查询审核意见
      * @author: dx
      * @date: 2019-07-17 13:59:55
      * @param request :
      * @param response :
      * @param params :
      * @return: com.alibaba.fastjson.JSONObject
      * @throws:
      * @version: 1.0
      **/
    //http://127.0.0.1:8080/Erp/officeAction/queryAdvance?month=2019-08
    @ResponseBody
    @RequestMapping(value = "/queryAdvance", method = RequestMethod.POST)
    public JSONObject queryAdvance(HttpServletRequest request, HttpServletResponse response, @RequestBody String params) {
        JSONObject result = new JSONObject();
        String retCode = Constant.Result.SUCCESS;
        String retMsg = Constant.Result.SUCCESS_MSG;
        try {
            if (StringUtils.isBlank(params)) throw new MessageException("参数接收失败!");
            Map<String, Object> requestMap = JSONObject.parseObject(params, Map.class);
            result.put(Constant.Result.RETDATA,wzbgService.queryAdvance(requestMap));
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
     * @desc:提交，审核
     * @author: dx
     * @date: 2019-07-17 16:34:08
     * @param request :
     * @param response :
     * @param params :
     * @return: com.alibaba.fastjson.JSONObject
     * @throws:
     * @version: 1.0
     **/
    //http://127.0.0.1:8080/Erp/officeAction/commitAndSheHe
    @ResponseBody
    @RequestMapping(value = "/commitAndSheHe", method = RequestMethod.POST)
    public JSONObject commitAndSheHe(HttpServletRequest request, HttpServletResponse response, @RequestBody String params) {
        JSONObject result = new JSONObject();
        String retCode = Constant.Result.SUCCESS;
        String retMsg = Constant.Result.SUCCESS_MSG;
        try {
            if (StringUtils.isBlank(params)) throw new MessageException("参数接收失败!");
            Map<String, Object> requestMap = JSONObject.parseObject(params, Map.class);
            wzbgService.commitAndSheHe(requestMap);
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
     * @desc:查询部门相关负责人
     * @author: dx
     * @date: 2019-07-18 10:00:10
     * @param request :
     * @param response :
     * @param params :
     * @return: com.alibaba.fastjson.JSONObject
     * @throws:
     * @version: 1.0
     **/
    //http://127.0.0.1:8080/Erp/officeAction/queryDeptConscientious
    @ResponseBody
    @RequestMapping(value = "/queryDeptConscientious", method = RequestMethod.POST)
    public JSONObject queryDeptConscientious(HttpServletRequest request, HttpServletResponse response, @RequestBody String params) {
        JSONObject result = new JSONObject();
        String retCode = Constant.Result.SUCCESS;
        String retMsg = Constant.Result.SUCCESS_MSG;
        try {
            if (StringUtils.isBlank(params)) throw new MessageException("参数接收失败!");
            Map<String, Object> requestMap = JSONObject.parseObject(params, Map.class);
            result.put(Constant.Result.RETDATA,wzbgService.queryDeptConscientious(requestMap));
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
