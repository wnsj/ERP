package com.jiubo.erp.rygl.controller;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jiubo.erp.common.Constant;
import com.jiubo.erp.common.MapUtil;
import com.jiubo.erp.common.MessageException;
import com.jiubo.erp.common.Position;
import com.jiubo.erp.erpLogin.util.ResponseMessageUtils;
import com.jiubo.erp.kqgl.controller.KqParamSetController;
import com.jiubo.erp.kqgl.vo.Vacation;
import com.jiubo.erp.rygl.bean.DepartmentBean;
import com.jiubo.erp.rygl.bean.ProjectDataBean;
import com.jiubo.erp.rygl.service.EmpService;
import com.jiubo.erp.rygl.vo.Account;
import com.jiubo.erp.rygl.vo.LeaveResign;
import com.jiubo.erp.rygl.vo.Nation;
import com.jiubo.erp.rygl.vo.PositionShift;
import com.jiubo.erp.rygl.vo.QueryFamilyResult;
import com.jiubo.erp.rygl.vo.QueryParam;
import com.jiubo.erp.rygl.vo.QueryResult;
import com.jiubo.erp.rygl.vo.UserFamily;
import com.jiubo.erp.rygl.vo.UserInfo;
import com.quicksand.push.ToolClass;

import lombok.val;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/search")
public class EmpController {

	public static Logger log = LoggerFactory.getLogger(EmpController.class);

	@Autowired
	private EmpService service;

	/**
	 * 全员搜索
	 * 
	 * @param param
	 *            属性包括所有人员信息
	 * @param response
	 * @param request
	 * @return
	 */
	@SuppressWarnings("finally")
	@ResponseBody
	@RequestMapping(value = "/allList")
	public JSONObject allList(HttpServletResponse response, HttpServletRequest request) {
		QueryParam qp = new QueryParam();

		JSONObject result = new JSONObject();
		String retCode = Constant.Result.SUCCESS;
		String retMsg = Constant.Result.SUCCESS_MSG;
		try {
			
			result.put("resData", this.service.initEmpList(qp, request)) ;
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
	 * 高级查询,入职、离职、转正
	 * 
	 * @param param
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("finally")
	@ResponseBody
	@RequestMapping(value = "/advanceAllList")
	public JSONObject advanceQuery(HttpServletResponse response, HttpServletRequest request) throws Exception {
		QueryParam qp = new QueryParam();

		JSONObject result = new JSONObject();
		String retCode = Constant.Result.SUCCESS;
		String retMsg = Constant.Result.SUCCESS_MSG;
		try {
			String str = ToolClass.getStrFromInputStream(request);
			if (StringUtils.isBlank(str))
				throw new MessageException("参数接收失败！");
			qp = MapUtil.transJsonStrToObjectIgnoreCase(str, QueryParam.class);
			if (qp.getSearchType().equals("1")) {
				qp.setEnterStartDate(qp.getStartDate());
				qp.setEnterEndDate(qp.getEndDate());
			}else if (qp.getSearchType().equals("2")) {
				qp.setZzStartDate(qp.getStartDate());
				qp.setZzEndDate(qp.getEndDate());
			}else if (qp.getSearchType().equals("3")) {
				qp.setLeaveStartDate(qp.getStartDate());
				qp.setLeaveEndDate(qp.getEndDate());
			}
			result.put("resData", this.service.initEmpList(qp, request)) ;
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
	 * 生日查询
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 * @return 返回值类型  JSONObject
	 * @author 作者 mwl
	 * @date   时间 2019年5月10日上午9:25:48
	 */
	@SuppressWarnings("finally")
	@ResponseBody
	@RequestMapping(value = "/advanceBirthQuery")
	public JSONObject advanceBirthQuery(HttpServletResponse response, HttpServletRequest request) throws Exception {
		QueryParam qp = new QueryParam();

		JSONObject result = new JSONObject();
		String retCode = Constant.Result.SUCCESS;
		String retMsg = Constant.Result.SUCCESS_MSG;
		try {
			String str = ToolClass.getStrFromInputStream(request);
			if (StringUtils.isBlank(str))
				throw new MessageException("参数接收失败！");
			qp = MapUtil.transJsonStrToObjectIgnoreCase(str, QueryParam.class);
			qp.setState("1");
			result.put("resData", this.service.initEmpList(qp, request)) ;
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
	 * 首页模糊查询
	 * 
	 * @param param
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public List<QueryResult> fuzzyQuery(QueryParam param, HttpServletRequest request) throws Exception {
		List<QueryResult> emplist = this.service.initEmpList(param, request);

		// 首页的模糊搜索
		if (param.getSearchContent() != null & emplist.size() > 0) {
			List<QueryResult> qrListCource = new ArrayList<>();
			List<QueryResult> qrListRes = new ArrayList<>();
			String searchStr = param.getSearchContent();
			qrListCource = this.service.initEmpList(param, request);
			for (QueryResult qrListCour : qrListCource) {
				if (qrListCour.getJobNum().contains(searchStr) || qrListCour.getName().contains(searchStr)
						|| qrListCour.getDepartName().contains(searchStr)
						|| qrListCour.getPositionName().contains(searchStr)) {
					qrListRes.add(qrListCour);
				}
			}
			// System.out.println("-------模糊查询fuzzyQuery-----"+qrListRes.size());

			return qrListRes;
		}
		return emplist;
	}

	/**
	 * 赛选家庭人员列表
	 * 
	 * @param param
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/fmList")
	public List<QueryFamilyResult> fmList(HttpServletResponse response, HttpServletRequest request) throws Exception {

		QueryParam qParam = new QueryParam();
		Map<String, String> mapList = ToolClass.mapShiftStr(request);
		qParam.setChName(mapList.get("chName"));
		qParam.setEmpName(mapList.get("empName"));
		qParam.setShBirth(mapList.get("shBirth"));

		String chName = qParam.getChName();
		String empName = qParam.getEmpName();
		String shBirth = qParam.getShBirth();

		Integer shBirthInt = Integer.valueOf(shBirth);
		List<QueryFamilyResult> fmList = this.service.familyfuzzyQuery(qParam, request);

		List<QueryFamilyResult> fmListCource = this.service.familyfuzzyQuery(qParam, request);
		List<QueryFamilyResult> fmListRes = new ArrayList<>();

		if (fmListCource.size() > 0) {

			if (empName != null & chName != null & shBirthInt > 0) {
				for (QueryFamilyResult fmListCour : fmListCource) {
					String birth = fmListCour.getBirth().substring(6, 7);
					int birthInt = Integer.valueOf(birth);
					if (fmListCour.getJobnum().contains(empName) || fmListCour.getName().contains(empName)) {
						if (fmListCour.getChname().contains(chName)) {
							if (shBirthInt == birthInt) {
								fmListRes.add(fmListCour);
							}
						}
					}
				}
			} else if (empName != null & chName != null & shBirthInt == 0) {
				for (QueryFamilyResult fmListCour : fmListCource) {
					if (fmListCour.getJobnum().contains(empName) || fmListCour.getName().contains(empName)) {
						if (fmListCour.getChname().contains(chName)) {
							// System.out.println("-------2-12个条件----"+fmListRes.size());
							fmListRes.add(fmListCour);
						}
					}
				}
			} else if (shBirthInt > 0 & empName != null) {
				for (QueryFamilyResult fmListCour : fmListCource) {
					String birth = fmListCour.getBirth().substring(6, 7);
					int birthInt = Integer.valueOf(birth);
					if (fmListCour.getChname().contains(chName)) {
						if (shBirthInt == birthInt) {
							// System.out.println("-------2-23个条件----"+fmListRes.size());
							fmListRes.add(fmListCour);
						}
					}
				}
			} else if (chName != null & shBirthInt > 0) {
				for (QueryFamilyResult fmListCour : fmListCource) {
					String birth = fmListCour.getBirth().substring(6, 7);
					int birthInt = Integer.valueOf(birth);
					if (fmListCour.getJobnum().contains(empName) || fmListCour.getName().contains(empName)) {
						if (shBirthInt > 0 & shBirthInt == birthInt) {
							// System.out.println("------2-13个条件----"+fmListRes.size());
							fmListRes.add(fmListCour);
						}
					}
				}
			} else if (empName != null & shBirthInt == 0) {
				for (QueryFamilyResult fmListCour : fmListCource) {
					if (fmListCour.getJobnum().contains(empName) || fmListCour.getName().contains(empName)) {
						// System.out.println("------1-1个条件----"+fmListRes.size());
						fmListRes.add(fmListCour);
					}
				}
			}
		} else if (chName != null & shBirthInt == 0) {
			fmListCource = fmListRes;
			fmListRes = new ArrayList<>();
			for (QueryFamilyResult fmListCour : fmListCource) {
				if (fmListCour.getChname().contains(chName)) {
					// System.out.println("------1-2个条件----"+fmListRes.size());
					fmListRes.add(fmListCour);
				}
			}
		} else if (shBirthInt > 0) {
			fmListCource = fmListRes;
			fmListRes = new ArrayList<>();
			for (QueryFamilyResult fmListCour : fmListCource) {
				String birth = fmListCour.getBirth().substring(6, 7);
				int birthInt = Integer.valueOf(birth);
				if (shBirthInt == birthInt) {
					// System.out.println("------1-3个条件----"+fmListRes.size());
					fmListRes.add(fmListCour);
				}
			}
		}
		// System.out.println("------fmListRes----"+fmListRes.size());
		return fmListRes;
	}

	/**
	 * 查看所有人员列表
	 * 
	 * @param param
	 *            所有人
	 * @param response
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/ryAllList")
	public List<QueryResult> ryAllList(QueryParam param, HttpServletResponse response, HttpServletRequest request) {
		List<QueryResult> qrList = new ArrayList<>();
		try {
			qrList = this.service.initEmpList(param, request);
			// System.out.println("查询到了---------" + qrList.size());
			return qrList;
		} catch (Exception e) {
			e.printStackTrace();
			ResponseMessageUtils.responseMessage(response, "查询失败!");
			return null;
		}
	}

	/**
	 * 项目列表
	 * 
	 * @param response
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/projectList")
	public List<ProjectDataBean> projectList(HttpServletResponse response, HttpServletRequest request) {
		List<ProjectDataBean> qrList = new ArrayList<>();
		try {
			qrList = this.service.initProjectList(request);
			System.out.println("项目列表" + qrList.size());
			return qrList;
		} catch (Exception e) {
			e.printStackTrace();
			ResponseMessageUtils.responseMessage(response, "查询失败!");
			return null;
		}
	}

	/**
	 * 部门列表
	 * 
	 * @param response
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/departList")
	public List<DepartmentBean> departList(HttpServletResponse response, HttpServletRequest request) {

		DepartmentBean dbp = new DepartmentBean();
		try {
			List<DepartmentBean> sorceList = this.service.initDepartList(dbp);
			return sorceList;
		} catch (Exception e) {
			e.printStackTrace();
			ResponseMessageUtils.responseMessage(response, "查询失败!");
			return null;
		}
	}

	/**
	 * 民族列表
	 * 
	 * @param response
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/nationList")
	public List<Nation> positionList(HttpServletResponse response, HttpServletRequest request) {
		List<Nation> nList = new ArrayList<>();
		try {
			nList = this.service.initNationList();
			System.out.println("项目列表" + nList.size());
			return nList;
		} catch (Exception e) {
			e.printStackTrace();
			ResponseMessageUtils.responseMessage(response, "查询失败!");
			return null;
		}
	}

	/**
	 * 职位列表
	 * 
	 * @param response
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/positionList")
	public List<Position> nationList(HttpServletResponse response, HttpServletRequest request) {
		List<Position> pList = new ArrayList<>();
		try {
			pList = this.service.initPositionList();
			System.out.println("positionList" + pList.size());
			return pList;
		} catch (Exception e) {
			e.printStackTrace();
			ResponseMessageUtils.responseMessage(response, "查询失败!");
			return null;
		}
	}

	/**
	 * 离职原因列表
	 * 
	 * @param response
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/leaveReasonList")
	public List<LeaveResign> leaveReasonList(HttpServletResponse response, HttpServletRequest request) {
		List<LeaveResign> lrList = new ArrayList<>();
		try {
			lrList = this.service.initLeaveList(null);
			System.out.println("lrList" + lrList.size());
			return lrList;
		} catch (Exception e) {
			e.printStackTrace();
			ResponseMessageUtils.responseMessage(response, "查询失败!");
			return null;
		}
	}

	/**
	 * 查询部门名称
	 * 
	 * @param dpList
	 *            部门列表 dpId部门id
	 * @return
	 */
	public String departName(List<DepartmentBean> dpList, String dpId) {
		String dpName = new String();
		// System.out.println("-------部门列表和部门id----------"+dpList.size());
		for (DepartmentBean departmentBean : dpList) {
			DepartmentBean dp = departmentBean;
			if (dp.getID().equals(dpId)) {
				dpName = dp.getName();
				return dpName;
			}
		}
		return "";
	}

	/**
	 * 家庭成员列表
	 * 
	 * @param param
	 * @param response
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/familyList")
	public List<QueryFamilyResult> familyList(HttpServletResponse response, HttpServletRequest request) {
		List<QueryFamilyResult> qfrList = new ArrayList<>();
		QueryParam qParam = new QueryParam();
		Map<String, String> mapList = ToolClass.mapShiftStr(request);
		qParam.setChName(mapList.get("chName"));
		qParam.setChName(mapList.get("empName"));
		qParam.setChName(mapList.get("shBirth"));
		try {
			qfrList = this.service.initFamilList(qParam, request);
			System.out.println("家庭人员列表" + qfrList.size());
			return qfrList;
		} catch (Exception e) {
			e.printStackTrace();
			ResponseMessageUtils.responseMessage(response, "查询失败!");
			return null;
		}
	}

	/**
	 * ------------------------------------------------------------------个人信息----------------------------------------------------------------------
	 * 获取单个用户基础信息
	 * 
	 * @param response
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/singleUBInfo")
	public UserInfo requireUserBaseInfo(HttpServletResponse response, HttpServletRequest request) {
		List<UserInfo> uList = new ArrayList<>();
		UserInfo userInfo = new UserInfo();
		Map<String, String> mapList = ToolClass.mapShiftStr(request);

		userInfo.setUserId(Integer.valueOf(mapList.get("userId")));
		uList = this.service.searchUBInfo(userInfo);

		// 通过账号Id找账号的名字和职位的名字
		if (uList.size() > 0) {
			System.out.println("requireUserBaseInfo---" + uList.size());
			userInfo = uList.get(0);
			return userInfo;
		} else {
			ResponseMessageUtils.responseMessage(response, "查询失败!");
			return null;
		}

	}

	/**
	 * 通过账户查找职位名称和ID
	 * 
	 * @param account
	 * @return
	 */
	public Account requireDepartName(Account accountId) {
		List<Account> aList = this.service.selectAccountList(accountId);
		Account account = aList.get(0);
		return account;
	}

	/**
	 * 获取单个用户详细信息
	 * 
	 * @param response
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/singleUDInfo")
	public List<UserInfo> requireUserDetailInfo(HttpServletResponse response, HttpServletRequest request) {
		List<UserInfo> uList = new ArrayList<>();
		UserInfo userInfo = new UserInfo();
		Map<String, String> mapList = ToolClass.mapShiftStr(request);

		userInfo.setuEmployeeBasicID(Integer.valueOf(mapList.get("userId")));
		System.out.println("---requireUserDetailInfo----" + userInfo.getuEmployeeBasicID());
		uList = this.service.searchUDInfo(userInfo);

		return uList;
	}

	/**
	 * 个人的家庭成员信息
	 * 
	 * @param param
	 * @param response
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/singlefamilyList")
	public List<QueryFamilyResult> singlefamilyList(HttpServletResponse response, HttpServletRequest request) {
		List<QueryFamilyResult> qfrList = new ArrayList<>();
		QueryFamilyResult qFResult = new QueryFamilyResult();
		Map<String, String> mapList = ToolClass.mapShiftStr(request);
		qFResult.setuAccountId(mapList.get("accountId"));
		try {
			qfrList = this.service.singleFamilyList(qFResult);
			System.out.println("家庭人员列表" + qfrList.size());
			return qfrList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 更新用户基础信息
	 * 
	 * @param param
	 * @param response
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/uUpdateUserInfo")
	public void updateUserBaseInfo(HttpServletResponse response, HttpServletRequest request) {

		UserInfo userInfo = new UserInfo();
		Account account = new Account();
		QueryFamilyResult qfr = new QueryFamilyResult();
		System.out.println("进来了");
		String string;
		try {
			string = StreamUtils.copyToString(request.getInputStream(), Charset.forName("UTF-8"));

			JSONObject jsonData = JSONObject.fromObject(string);

			System.out.println(jsonData);

			userInfo.setUserId(Integer.valueOf(jsonData.getString("ID")));
			userInfo.setuName(jsonData.getString("Name"));
			userInfo.setuState(jsonData.getString("State"));
			userInfo.setuSex(jsonData.getString("Sex"));
			userInfo.setuBirth(jsonData.getString("Birth"));
			userInfo.setuAccountId(jsonData.getString("Account_Id"));
			userInfo.setuDepartment_id(jsonData.getString("DepartmentId"));
			userInfo.setPositionId(jsonData.getString("PositionId"));
			userInfo.setuEntryDte(jsonData.getString("EntryDate"));
			userInfo.setuPositiveDate(jsonData.getString("PositiveDate"));
			userInfo.setuResignDate(jsonData.getString("ResignDate"));
			userInfo.setuResignType(jsonData.getString("ResignType"));
			userInfo.setuRemark(jsonData.getString("Remark"));

			// 详细信息
			userInfo.setuIdNum(jsonData.getString("IDNum"));
			userInfo.setuPloitical(jsonData.getString("ploitical"));
			userInfo.setuHomeTown(jsonData.getString("homeTown"));
			userInfo.setuNationality(jsonData.getString("nationality"));
			userInfo.setuMarital(jsonData.getString("marital"));
			userInfo.setuHomeAddress(jsonData.getString("homeAddress"));
			userInfo.setuCurrentAddress(jsonData.getString("currentAddress"));
			userInfo.setuDomicile(jsonData.getString("domicile"));
			userInfo.setuAccountProp(jsonData.getString("accountProp"));

			userInfo.setuSchools(jsonData.getString("schools"));
			userInfo.setuEducation(jsonData.getString("education"));
			userInfo.setuProfession(jsonData.getString("profession"));
			userInfo.setuGraduation(jsonData.getString("graduation"));
			userInfo.setuAtSchool(jsonData.getString("atSchool"));

			userInfo.setuContact(jsonData.getString("contact"));
			userInfo.setuEmergencyContact(jsonData.getString("emergencyContact"));
			userInfo.setuEmergencyphone(jsonData.getString("emergencyPhone"));

			userInfo.setuLicenseType(jsonData.getString("licenseType"));
			userInfo.setuDrivingExpe(jsonData.getString("drivingExpe"));

			// 提交家庭信息
			if (jsonData.has("newFamilyNumList")) {
				JSONArray jArr = jsonData.getJSONArray("newFamilyNumList");
				for (int i = 0; i < jArr.size(); i++) {
					qfr = new QueryFamilyResult();
					qfr.setID(jArr.getJSONObject(i).getString("id"));
					qfr.setuAccountId(userInfo.getuAccountId());
					qfr.setAppellation(jArr.getJSONObject(i).getString("appellation"));
					qfr.setChname(jArr.getJSONObject(i).getString("chname"));
					qfr.setBirth(jArr.getJSONObject(i).getString("birth"));
					qfr.setWorkadress(jArr.getJSONObject(i).getString("workadress"));
					qfr.setPosition(jArr.getJSONObject(i).getString("position"));
					qfr.setPhone(jArr.getJSONObject(i).getString("phone"));
					qfr.setWechat(jArr.getJSONObject(i).getString("wechat"));
					qfr.setFamAddress(jArr.getJSONObject(i).getString("famAddress"));
					if (qfr.getID() == "") {
						System.out.println("有一个新的家庭成员");
					}
				}
			}

			Integer baseInfoInt = this.service.updataBaseInfo(userInfo);
			Integer detailInfoInt = this.service.updataDetialInfo(userInfo);
			System.out.println("基础家庭成员" + baseInfoInt + "详细信息" + detailInfoInt);
			if (baseInfoInt == 1 && detailInfoInt == 1) {
				ResponseMessageUtils.responseMessage(response, "修改成功!");
			} else {
				ResponseMessageUtils.responseMessage(response, "修改失败!");
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 插入用户基础信息
	 * 
	 * @param param
	 * @param response
	 * @param request
	 * @return
	 * 
	 * 		注：员工入职的姓名、性别、工号、ERP账号、部门是必填的 1、工号和ERP账号没有规则，只要不和数据库冲突就可以使用
	 *         2、通过ERP账号自动生成账号的相关信息，包括初始密码、id、状态
	 */
	@ResponseBody
	@RequestMapping(value = "/uBaseInfo")
	public Map<String, String> userBaseInfo(HttpServletResponse response, HttpServletRequest request) {

		UserInfo userInfo = new UserInfo();
		Account account = new Account();
		Map<String, String> resultM = new HashMap<>();

		String string;
		try {
			string = StreamUtils.copyToString(request.getInputStream(), Charset.forName("UTF-8"));

			JSONObject jsonData = JSONObject.fromObject(string);

			System.out.println("jsonData" + jsonData);

			userInfo.setuName(jsonData.getString("Name"));
			List<UserInfo> uList = this.service.searchUBInfo(userInfo);

			if (uList.size() > 0) {
				resultM.put("0", "姓名已存在");
				return resultM;
			}

			userInfo = new UserInfo();
			userInfo.setuName(jsonData.getString("JobNum"));
			List<UserInfo> jList = this.service.searchUBInfo(userInfo);
			if (jList.size() > 0) {
				resultM.put("0", "工号已存在");
				return resultM;
			}

			account.setAccountName(jsonData.getString("Account_Name"));
			List<Account> aList = this.service.selectAccountList(account);
			if (aList.size() > 0) {
				resultM.put("0", "ERP账号已存在");
				return resultM;
			} else {
				account.setPositionId(jsonData.getString("PositionId"));
				Integer isSucess = this.service.insertAccountInfo(account);
				if (isSucess == 1) {
					aList = this.service.selectAccountList(account);
					account = aList.get(0);
					userInfo.setuAccount(account.getAccountId());
				}
			}

			userInfo.setuName(jsonData.getString("Name"));
			userInfo.setuName(jsonData.getString("JobNum"));

			// 基本信息
			userInfo.setuSex(jsonData.getString("Sex"));
			userInfo.setuDepartment_id(jsonData.getString("DepartmentId"));
			userInfo.setuBirth(jsonData.getString("Birth"));

			userInfo.setPositionId(jsonData.getString("PositionId"));
			userInfo.setuEntryDte(jsonData.getString("EntryDate"));
			userInfo.setuPositiveDate(jsonData.getString("PositiveDate"));
			userInfo.setuResignDate(jsonData.getString("ResignDate"));
			userInfo.setuRemark(jsonData.getString("Remark"));
			// 详细信息
			userInfo.setuIdNum(jsonData.getString("IDNum"));
			userInfo.setuPloitical(jsonData.getString("ploitical"));
			userInfo.setuHomeTown(jsonData.getString("homeTown"));
			userInfo.setuNationality(jsonData.getString("nationality"));
			userInfo.setuMarital(jsonData.getString("marital"));
			userInfo.setuHomeAddress(jsonData.getString("homeAddress"));
			userInfo.setuCurrentAddress(jsonData.getString("currentAddress"));
			userInfo.setuDomicile(jsonData.getString("domicile"));
			userInfo.setuAccountProp(jsonData.getString("accountProp"));

			userInfo.setuSchools(jsonData.getString("schools"));
			userInfo.setuEducation(jsonData.getString("education"));
			userInfo.setuProfession(jsonData.getString("profession"));
			userInfo.setuGraduation(jsonData.getString("graduation"));
			userInfo.setuAtSchool(jsonData.getString("atSchool"));

			userInfo.setuContact(jsonData.getString("contact"));
			userInfo.setuEmergencyContact(jsonData.getString("emergencyContact"));
			userInfo.setuEmergencyphone(jsonData.getString("emergencyPhone"));

			userInfo.setuLicenseType(jsonData.getString("licenseType"));
			userInfo.setuDrivingExpe(jsonData.getString("drivingExpe"));

			// 提交家庭信息
			if (jsonData.has("newFamilyNumList")) {
				JSONArray jArr = jsonData.getJSONArray("newFamilyNumList");
				for (int i = 0; i < jArr.size(); i++) {
					QueryFamilyResult qfr = new QueryFamilyResult();
					qfr.setuAccountId(userInfo.getuAccountId());
					qfr.setAppellation(jArr.getJSONObject(i).getString("appellation"));
					qfr.setChname(jArr.getJSONObject(i).getString("chname"));
					qfr.setBirth(jArr.getJSONObject(i).getString("birth"));
					qfr.setWorkadress(jArr.getJSONObject(i).getString("workadress"));
					qfr.setPosition(jArr.getJSONObject(i).getString("position"));
					qfr.setPhone(jArr.getJSONObject(i).getString("phone"));
					qfr.setWechat(jArr.getJSONObject(i).getString("wechat"));
					qfr.setFamAddress(jArr.getJSONObject(i).getString("famAddress"));
				}
			}

			String newDate = ToolClass.strDateTimeStr(new Date());
			userInfo.setuCreateDate(newDate);
			userInfo.setuUpdateDate(newDate);
			userInfo.setuAtSchool("0");

			// 插入用户基本信息，在进行查询人员ID，在进行插入详细信息
			this.service.insertUserInfo(userInfo);
			List<UserInfo> userlist = this.service.searchUBInfo(userInfo);
			Integer userId = userlist.get(0).getUserId();
			String uid = String.valueOf(userId);
			// System.out.println("---------人员ID-----------"+uid);

			// 设置上传时间、上传的人
			userInfo.setuEmployeeBasicID(Integer.valueOf(uid));

			// System.out.println("---------人员ID-----------"+param.getuEmployeeBasicID());
			this.service.insertUserDetailInfo(userInfo);
			resultM.put("0", "成员插入成功");
			return resultM;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultM.put("0", "数据插入失败");
			return resultM;
		}

	}

	/**
	 * 插入和更新家庭信息
	 * 
	 * @param param
	 * @param response
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/ufInfo")
	public String userFamilyDetailInfo(HttpServletResponse response, HttpServletRequest request) {
		boolean isBool;
		UserFamily ufInfo = new UserFamily();
		Map<String, String> mapList = ToolClass.mapShiftStr(request);

		// ufInfo.setAccountId(mapList.get("accountId"));
		// ufInfo.setAddress(mapList.get("address"));
		// ufInfo.setAppellation(mapList.get("appellation"));
		// ufInfo.setBirthtay(mapList.get("birthtay"));
		// ufInfo.setName(mapList.get("name"));
		// ufInfo.setPhone(mapList.get("phone"));
		// ufInfo.setPosition(mapList.get("position"));
		// ufInfo.setWechat(mapList.get("wechat"));
		// ufInfo.setWorkAddress(mapList.get("workAddress"));
		try {
			// isBool = this.service.insertUserFmInfo(ufInfo);
			// System.out.println("家庭人员列表" + isBool);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			ResponseMessageUtils.responseMessage(response, "插入失败!");
			return null;
		}
	}

	/**
	 * 查询用户的职位的调动信息
	 * 
	 * @param response
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/positionShifts")
	public List<PositionShift> positionShifts(HttpServletResponse response, HttpServletRequest request) {
		Map<String, String> mapList = ToolClass.mapShiftStr(request);
		PositionShift pShift = new PositionShift();
		pShift.setAccountId(mapList.get("AccountId"));

		List<PositionShift> psList = this.service.selectShiftInfo(pShift);
		return psList;
	}

	/**
	 * 添加离职原因
	 * 
	 * @param response
	 * @param request
	 * @return
	 * @return 返回值类型 JSONObject
	 * @author 作者 mwl
	 * @date 时间 2019年5月9日上午11:57:39
	 */
	@SuppressWarnings("finally")
	@ResponseBody
	@RequestMapping(value = "/addLeaveReason")
	public JSONObject addLeaveReason(HttpServletResponse response, HttpServletRequest request) {
		LeaveResign lr = new LeaveResign();

		JSONObject result = new JSONObject();
		String retCode = Constant.Result.SUCCESS;
		String retMsg = Constant.Result.SUCCESS_MSG;
		try {
			String str = ToolClass.getStrFromInputStream(request);
			if (StringUtils.isBlank(str))
				throw new MessageException("参数接收失败！");
			lr = MapUtil.transJsonStrToObjectIgnoreCase(str, LeaveResign.class);
			if (StringUtils.isBlank(lr.getReasonName()))
				throw new MessageException("离职ID为空或离职原因为空！");
			List<LeaveResign> lrList = this.service.initLeaveList(lr);
			if (lrList.size() > 0) {
				retMsg = "离职原因已存在";
			} else {
				this.service.addLeaveReason(lr);
			}
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
	 * 修改离职原因
	 * 
	 * @param response
	 * @param request
	 * @return
	 * @return 返回值类型 List<LeaveResign>
	 * @author 作者 mwl
	 * @date 时间 2019年5月9日上午8:05:31
	 */
	@SuppressWarnings("finally")
	@ResponseBody
	@RequestMapping(value = "/updateLeaveReason")
	public JSONObject updateLeaveReason(HttpServletResponse response, HttpServletRequest request) {
		LeaveResign lr = new LeaveResign();

		JSONObject result = new JSONObject();
		String retCode = Constant.Result.SUCCESS;
		String retMsg = Constant.Result.SUCCESS_MSG;
		try {
			String str = ToolClass.getStrFromInputStream(request);
			if (StringUtils.isBlank(str))
				throw new MessageException("参数接收失败！");
			lr = MapUtil.transJsonStrToObjectIgnoreCase(str, LeaveResign.class);
			if (StringUtils.isBlank(lr.getReasonId()) || StringUtils.isBlank(lr.getReasonName()))
				throw new MessageException("离职ID为空或离职原因为空！");
			this.service.updateLeaveReason(lr);
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
	 * 删除离职原因
	 * 
	 * @param response
	 * @param request
	 * @return
	 * @return 返回值类型 JSONObject
	 * @author 作者 mwl
	 * @date 时间 2019年5月9日上午10:47:39
	 */
	@SuppressWarnings("finally")
	@ResponseBody
	@RequestMapping(value = "/deleteLeaveReason")
	public JSONObject deleteLeaveReason(HttpServletResponse response, HttpServletRequest request) {
		LeaveResign lr = new LeaveResign();

		JSONObject result = new JSONObject();
		String retCode = Constant.Result.SUCCESS;
		String retMsg = Constant.Result.SUCCESS_MSG;
		try {
			String str = ToolClass.getStrFromInputStream(request);
			if (StringUtils.isBlank(str))
				throw new MessageException("参数接收失败！");
			lr = MapUtil.transJsonStrToObjectIgnoreCase(str, LeaveResign.class);
			if (StringUtils.isBlank(lr.getReasonId()))
				throw new MessageException("离职ID为空");
			this.service.deleteLeaveReason(lr);
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
	 * 测试
	 * 
	 * @param user
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/export")
	@ResponseBody
	public void exportExcel(String index, HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("您来了" + index);
		// 设置响应正文的MIME类型，该类型表示Excel
		request.setCharacterEncoding("gbk");
		response.setContentType("application/vnd.ms-excel");
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		String sex = request.getParameter("sex");
		System.out.println(sex);
		String age = request.getParameter("age");
		String email = request.getParameter("email");

		ServletOutputStream out = response.getOutputStream(); // 响应输出流对象
		HSSFWorkbook wb = new HSSFWorkbook(); // 创建Excel表格
		HSSFSheet sheet = wb.createSheet("用户注册信息"); // 创建工作薄
		sheet.setColumnWidth(4, 5000); // 设置列宽

		HSSFRow titleRow = sheet.createRow(0); // 创建Excel中的标题栏,第1行

		HSSFCell titleCell1 = titleRow.createCell(0); // 在行中创建第1个单元格
		titleCell1.setCellValue("用户姓名"); // 设置第1个单元格的值
		HSSFCell titleCell2 = titleRow.createCell(1); // 在行中创建第2个单元格
		titleCell2.setCellValue("密码"); // 设置第2个单元格的值
		HSSFCell titleCell3 = titleRow.createCell(2); // 在行中创建第3个单元格
		titleCell3.setCellValue("性别"); // 设置第3个单元格的值
		HSSFCell titleCell4 = titleRow.createCell(3); // 在行中创建第4个单元格
		titleCell4.setCellValue("年龄"); // 设置第4个单元格的值
		HSSFCell titleCell5 = titleRow.createCell(4); // 在行中创建第5个单元格
		titleCell5.setCellValue("Email"); // 设置第5个单元格的值

		HSSFRow valueRow = sheet.createRow(1); // 创建第2行

		HSSFCell nameCell = valueRow.createCell(0); // 在第2行中创建单元格
		nameCell.setCellValue(name);
		HSSFCell pwdCell = valueRow.createCell(1);
		pwdCell.setCellValue(pwd);
		HSSFCell sexCell = valueRow.createCell(2);
		sexCell.setCellValue(sex);
		HSSFCell ageCell = valueRow.createCell(3);
		ageCell.setCellValue(age);
		HSSFCell emailCell = valueRow.createCell(4);
		emailCell.setCellValue(email);

		HSSFCellStyle cellStyle = wb.createCellStyle();
		wb.write(out); // 将响应流输入到Excel表格中
		out.flush();
	}

}
