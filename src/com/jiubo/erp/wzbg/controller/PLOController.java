package com.jiubo.erp.wzbg.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.jiubo.erp.wzbg.service.PLOService;

/**
 * 
 * @author mwl
 *
 */
@Controller
@RequestMapping("/wzbg")
public class PLOController {
    @Autowired
    private PLOService service;
	
   /**
    * 请假-列表
    * @param response
    * @param request
    * @return
    * JSONObject
    * @author 作者 : mwl
    * @version 创建时间：2019年7月3日 下午2:35:25
    */
	
    @ResponseBody
    @RequestMapping(value = "/askOfLeaveList")
    public JSONObject askOfLeaveList(HttpServletResponse response, HttpServletRequest request) {
       return this.service.askOfLeaveList(response, request); 
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
	@ResponseBody
	@RequestMapping(value = "/insertLeaveApplication")
	public JSONObject insertLeaveApplication(HttpServletResponse response, HttpServletRequest request) {
		
        return this.service.insertLeaveApplication(response, request);
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
    @ResponseBody
    @RequestMapping(value = "/departOfEmpList")
    public JSONObject selectDepartOfEmpList(HttpServletResponse response, HttpServletRequest request) {
        
		return this.service.selectDepartOfEmpList(response, request);
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
    @ResponseBody
    @RequestMapping(value = "/checkOfEmpList")
    public JSONObject checkOfEmpList(HttpServletResponse response, HttpServletRequest request) {
        
		return this.service.selectDepartOfEmpList(response, request);
    }
	
	/**
	 * 倒休 列表
	 * @param response
	 * @param request
	 * @return
	 * JSONObject
	 * @author 作者 : mwl
	 * @version 创建时间：2019年7月8日 上午9:45:48
	 */
	@ResponseBody
	@RequestMapping(value = "/restDownList")
	public JSONObject restDownList(HttpServletResponse response, HttpServletRequest request) {
        
		return this.service.restDownList(response, request);
    }
}
