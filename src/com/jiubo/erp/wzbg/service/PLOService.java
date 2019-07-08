package com.jiubo.erp.wzbg.service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

public interface PLOService {
	
	public JSONObject askOfLeaveList(HttpServletResponse response, HttpServletRequest request);
	
	public JSONObject selectDepartOfEmpList(HttpServletResponse response, HttpServletRequest request);
	
	public JSONObject insertLeaveApplication(HttpServletResponse response, HttpServletRequest request);
	
	public JSONObject checkOfEmpList(HttpServletResponse response, HttpServletRequest request);
	
	public JSONObject restDownList(HttpServletResponse response, HttpServletRequest request);
}
