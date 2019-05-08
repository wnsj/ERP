package com.jiubo.erp.kqgl.controller;



import java.util.ArrayList;


import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiubo.erp.common.MessageException;
import com.jiubo.erp.erpLogin.util.ResponseMessageUtils;
import com.jiubo.erp.kqgl.bean.AttRuleTypeBean;
import com.jiubo.erp.kqgl.bean.PersonalKQBean;
import com.jiubo.erp.kqgl.service.KqParamSetService;
import com.jiubo.erp.kqgl.service.KqService;
import com.jiubo.erp.kqgl.vo.DepartKQ;
import com.jiubo.erp.kqgl.vo.KqInfoResult;
import com.jiubo.erp.kqgl.vo.PunchRecord;
import com.quicksand.push.ToolClass;




@Controller
@RequestMapping("/kqgl")
public class KqController {
	
@Autowired
private KqService service;
 
@Autowired
private KqParamSetService kpService;
 
 
 /**
  * 加载当天的全部的用户考勤信息
  * @param request
  * @param response
  * @return
  */
 @ResponseBody
 @RequestMapping(value="/allKQBaseInfo")
 private List<KqInfoResult> allKQInfo(HttpServletRequest request,HttpServletResponse response) {
	
	 	KqInfoResult kqParam = new KqInfoResult();
		Map<String, String> mapList = ToolClass.mapShiftStr(request);
		
			
		kqParam.setName(mapList.get("name"));
		kqParam.setDepartname(mapList.get("departName"));
		kqParam.setPositionName(mapList.get("positionName"));
		kqParam.setJobNum(mapList.get("jobNum"));
		
		 try {
		    List<KqInfoResult> kqInfoList = this.service.selectKqInfoList(kqParam);
		     System.out.println("kqList:"+kqInfoList.size());
		     return kqInfoList;
		} catch (Exception e) {
			e.printStackTrace();
			ResponseMessageUtils.responseMessage(response, "查询错误,请重试!");
			return null;
		}
}
 /**
  * 根据查询条件进行搜索
  * @param request
  * @param response
  * @return
  */
 @ResponseBody
 @RequestMapping(value="/searchKQInfo")
 private List<KqInfoResult> searchKQInfo(HttpServletRequest request,HttpServletResponse response) {
 
	 	KqInfoResult kqParam = new KqInfoResult();
		Map<String, String> mapList = ToolClass.mapShiftStr(request);
		
			
		kqParam.setName(mapList.get("name"));
		kqParam.setDepartname(mapList.get("departName"));
		kqParam.setPositionName(mapList.get("positionName"));
		kqParam.setJobNum(mapList.get("jobNum"));
		kqParam.setStartDate(mapList.get("beginData"));
		kqParam.setEndDate(mapList.get("endData"));
		
		if (kqParam.getStartDate().equals("")) {
			kqParam.setStartDate(ToolClass.inquirNowDate());
		}
		if (kqParam.getEndDate().equals("")) {
			kqParam.setEndDate(ToolClass.inquirNowDate());
		}
		
		
	 List<KqInfoResult> kqInfoRes = this.service.searchKqInfoList(kqParam);
	 System.out.println("---班型测试kqInfoRes----"+kqInfoRes.size()+kqParam.getEndDate()+kqParam.getStartDate());
	 
	 //查询班次数据为空时执行，返回基本信息
	 if (kqInfoRes.size()<1) {
		 kqInfoRes=this.service.selectKqInfoList(kqParam);
		 System.out.println("kqInfoRes:"+kqInfoRes.size());
		 return kqInfoRes;
	}else {
		kqInfoRes = kquClassTime(kqInfoRes);
		return kqInfoRes;
	}
 
}
 
 /**
  * 将班次信息赋值给所有查询到的考勤人员
  * @param kqInfoRes考勤人员列表
  * @return
  */
 public List<KqInfoResult> kquClassTime(List<KqInfoResult> kqInfoRes) {
	 try {
		    for (int i=0;i < kqInfoRes.size();i++) {
		    	KqInfoResult kqInfoResult = kqInfoRes.get(i);
//		    	System.out.println("getClassTimeType"+kqInfoResult.getClassTimeType());
		    	if (kqInfoResult.getShiftDate() != null && kqInfoResult.getClassTimeType().equals("1")) {
		    		System.out.println("getShiftDate"+kqInfoResult.getShiftDate());
		    		PunchRecord pRecord= new PunchRecord();
		    		pRecord.setYear(kqInfoResult.getShiftDate().substring(0, 4));
			    	pRecord.setMonth(kqInfoResult.getShiftDate().substring(5, 7)); 
			    	pRecord.setDay(kqInfoResult.getShiftDate().substring(8, 10));
			    	pRecord.setAccountId(kqInfoResult.getAccountId());
//					System.out.println("打卡参数"+pRecord.getYear()+"-"+pRecord.getMonth()+"-"+pRecord.getDay());
					
					List<PunchRecord> prList = this.service.selectPunchRecordList(pRecord);
					pRecord = prList.get(0);
					if (prList.size()>0 && pRecord != null) {
						kqInfoResult.setFirstTime(pRecord.getMinAttTime());
						kqInfoResult.setFirstTimeState(completForeKQInfo(pRecord.getMinAttTime(), kqInfoResult.getStartTime(), kqInfoResult.getEndTime()));
						kqInfoResult.setLastTime(pRecord.getMaxAttTime());
						kqInfoResult.setLastTimeState(completAfterKQInfo(pRecord.getMaxAttTime(), kqInfoResult.getStartTime(), kqInfoResult.getEndTime()));
						
					}else {
						kqInfoResult.setFirstTimeState("旷工");
						kqInfoResult.setLastTimeState("旷工");
					}
		    	
				}else {
					kqInfoResult.setFirstTimeState(kqInfoResult.getClassTimeName());
				}
		    }
		     System.out.println("kqInfoRes:"+kqInfoRes.size());
		     return kqInfoRes;
		} catch (Exception e) {
			e.printStackTrace();
			return kqInfoRes;
		}
}
 /**
  * 用来判断打卡时间在那个时间段
  * 上班时间之前---------------返回1-------正常
  * 上班时间之后30分钟内-------返回2-------迟到
  * 上班时间之后30-120分钟内---返回3-------旷工----------
  * 区间----------------------返回4-------上班打卡异常    |
  * 下班时间之前30-180分钟内---返回5-------旷工----------
  * 上班时间之前30分钟内-------返回6-------早退
  * 上班时间------------------返回7--------正常
  * @param mtime 打卡时间
  * @param beginTime 班次的上班时间
  * @param endTime 下班时间
  * @return
  * 上午
  */
 public String completForeKQInfo(String mTime,String beginTime,String endTime) {
	 List<AttRuleTypeBean> aRuleList;
	 Integer lateBegin=0;//迟到时间的起点
	 Integer lateEnd=0;//迟到时间结束点
	 Integer absentBegin=0;//旷工时间的起点
	 Integer absentEnd=0;//旷工时间的结束点
	 Integer punchExceptionBegin=0;//打卡异常时间的起点
	try {
		aRuleList = this.kpService.queryAttRuleType();
		
		 for (AttRuleTypeBean attRuleTypeBean : aRuleList) {
			if (attRuleTypeBean.getName().equals("迟到")) {
				lateBegin = Integer.valueOf(attRuleTypeBean.getEarlyMinutes());
				lateEnd = Integer.valueOf(attRuleTypeBean.getLateMinutes());
			}
			if (attRuleTypeBean.getName().equals("旷工")) {
				absentBegin = Integer.valueOf(attRuleTypeBean.getEarlyMinutes());
				absentEnd = Integer.valueOf(attRuleTypeBean.getLateMinutes());
			}
			if (attRuleTypeBean.getName().equals("打卡异常")) {
				punchExceptionBegin = Integer.valueOf(attRuleTypeBean.getEarlyMinutes());
				
			}
		}
	} catch (MessageException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 if (ToolClass.compare_date(mTime, beginTime)<=0) {
		 return "正常";
	 }else if (ToolClass.compare_date(mTime, beginTime)>0 &&
				ToolClass.compare_date(mTime, ToolClass.strDateTimeShiftStr(beginTime, lateEnd))<=0) {
		 return "迟到";
	}else if (ToolClass.compare_date(mTime, ToolClass.strDateTimeShiftStr(beginTime, lateEnd))>0 &&
			ToolClass.compare_date(mTime, ToolClass.strDateTimeShiftStr(beginTime, absentEnd))<=0) {
		 return "旷工";
	}else {
		return "打卡异常";
	}
}
 /**
  * 下午
  * @param mTime
  * @param beginTime
  * @param endTime
  * @return
  */
 public String completAfterKQInfo(String mTime,String beginTime,String endTime) {
	 List<AttRuleTypeBean> aRuleList;
	 Integer lateBegin=0;//迟到时间的起点
	 Integer lateEnd=0;//迟到时间结束点
	 Integer absentBegin=0;//旷工时间的起点
	 Integer absentEnd=0;//旷工时间的结束点
	 Integer punchExceptionBegin=0;//打卡异常时间的起点
	try {
		aRuleList = this.kpService.queryAttRuleType();
		
		 for (AttRuleTypeBean attRuleTypeBean : aRuleList) {
			if (attRuleTypeBean.getName().equals("迟到")) {
				lateBegin = Integer.valueOf(attRuleTypeBean.getEarlyMinutes());
				lateEnd = Integer.valueOf(attRuleTypeBean.getLateMinutes());
			}
			if (attRuleTypeBean.getName().equals("旷工")) {
				absentBegin = Integer.valueOf(attRuleTypeBean.getEarlyMinutes());
				absentEnd = Integer.valueOf(attRuleTypeBean.getLateMinutes());
			}
			if (attRuleTypeBean.getName().equals("打卡异常")) {
				punchExceptionBegin = Integer.valueOf(attRuleTypeBean.getEarlyMinutes());
				
			}
		}
	} catch (MessageException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 if (ToolClass.compare_date(mTime, ToolClass.strDateTimeShiftStr(endTime, -absentEnd))<=0) {
		return "打卡异常";
	}else if (ToolClass.compare_date(mTime, ToolClass.strDateTimeShiftStr(endTime, -absentEnd))>0 &&
			ToolClass.compare_date(mTime, ToolClass.strDateTimeShiftStr(endTime, -lateEnd))<=0) {
		 return "旷工";
	}else if (ToolClass.compare_date(mTime, ToolClass.strDateTimeShiftStr(endTime, -lateEnd))>0 &&
			ToolClass.compare_date(mTime, endTime)<0) {
		 return "早退";
	}else {
		 return "正常";
	}
}
 
 /**
  * 部门考勤的全部信息,通过searchKQInfo方法搜索全部的考勤，进行部门考勤的计算
  * @param request
  * @param response
  * @return
  */
  @ResponseBody
  @RequestMapping(value="/searchDepartKQList")
  private List<DepartKQ> departKQList(HttpServletRequest request,HttpServletResponse response) {
	  
	  	KqInfoResult kqParam = new KqInfoResult();
		Map<String, String> mapList = ToolClass.mapShiftStr(request);
		List<DepartKQ> dpkqLsit = new ArrayList<>();
		
			
		kqParam.setName(mapList.get("name"));
		kqParam.setDepartname(mapList.get("departName"));
		kqParam.setPositionName(mapList.get("positionName"));
		kqParam.setJobNum(mapList.get("jobNum"));
		kqParam.setStartDate(mapList.get("beginData"));
		kqParam.setEndDate(mapList.get("endData"));
		
		if (kqParam.getStartDate().equals("")) {
			kqParam.setStartDate(ToolClass.inquirNowDate()+" "+"00:00:00.000");
		}
		if (kqParam.getEndDate().equals("")) {
			kqParam.setEndDate(ToolClass.inquirNowDate()+" "+"23:59:59.000");
		}
		
		
		List<KqInfoResult> kqInfoRes = this.service.searchKqInfoList(kqParam);
		System.out.println("---班型测试kqInfoRes----："+kqParam.getDepartname());
		System.out.println("---班型测试kqInfoRes----："+kqInfoRes.size());
		System.out.println("---班型测试kqInfoRes----："+kqParam.getEndDate());
		System.out.println("---班型测试kqInfoRes----："+kqParam.getStartDate());
	 
		
		dpkqLsit = this.service.selectDepartKqInfoList();
		System.out.println("dpkqLsit:"+dpkqLsit.size());
		if (kqParam.getDepartname()!=null && !kqParam.getDepartname().equals("")) {
			DepartKQ departKQ = new DepartKQ();
			dpkqLsit = new ArrayList<>();
			departKQ.setDepartKQName(kqParam.getDepartname());
			departKQ.setDownPA("0");
			departKQ.setLaterTimes("0");
			departKQ.setLeaveEarlyTimes("0");
			departKQ.setMinersTimes("0");
			departKQ.setOnPA("0");
			departKQ.setOverTimesDays("0");
			departKQ.setRestDays("0");
			dpkqLsit.add(departKQ);
		}else {
			for(DepartKQ dpKQ : dpkqLsit) {
				dpKQ.setDownPA("0");
				dpKQ.setLaterTimes("0");
				dpKQ.setLeaveEarlyTimes("0");
				dpKQ.setMinersTimes("0");
				dpKQ.setOnPA("0");
				dpKQ.setOverTimesDays("0");
				dpKQ.setRestDays("0");
			}
		}
		
		
		//查询班次数据为空时执行，返回基本信息
		if (kqInfoRes.size()<1) {
			System.out.println("dpkqLsit1:"+dpkqLsit.size());
			return dpkqLsit;
		}else {
			kqInfoRes = kquClassTime(kqInfoRes);
			dpkqLsit = selectDepartKqInfo(kqInfoRes,dpkqLsit);
			System.out.println("dpkqLsit2:"+kqInfoRes.size());
			return dpkqLsit;
		}

 }
 /**
  * 通过个人考勤信息计算部门考勤信息
  * @param dpkqLsit 部门列表
  * @param kqInfoResults
  * @param hasClassType 存在班型时进行数据统计
  * @return
  */
public List<DepartKQ> selectDepartKqInfo(List<KqInfoResult> kqInfoResults,List<DepartKQ> dpkqLsit) {
	
	
	for (DepartKQ dp : dpkqLsit) {
		for (KqInfoResult ryKQ : kqInfoResults) {
			if (ryKQ.getDepartname().equals(dp.getDepartKQName())) {
				if (ryKQ.getClassTimeType().equals("2")) {
					dp.setRestDays(String.valueOf(Integer.valueOf(dp.getRestDays())+1));
				}else {
					String upStatus=ryKQ.getFirstTimeState();
					String downStatus=ryKQ.getLastTimeState();
					
					if (upStatus.equals("迟到")) {
						dp.setLaterTimes(String.valueOf(Integer.valueOf(dp.getLaterTimes())+1));
					}
					if (upStatus.equals("早退")) {
						dp.setLeaveEarlyTimes(String.valueOf(Integer.valueOf(dp.getLeaveEarlyTimes())+1));				
					}
					if (upStatus.equals("旷工") || downStatus.equals("旷工")) {
						dp.setMinersTimes(String.valueOf(Integer.valueOf(dp.getMinersTimes())+1));
					}
					if (upStatus.equals("打卡异常")) {
						dp.setOnPA(String.valueOf(Integer.valueOf(dp.getOnPA())+1));
					}
					if (downStatus.equals("打卡异常")) {
						dp.setDownPA(String.valueOf(Integer.valueOf(dp.getDownPA())+1));
					}
				}
				
			}
		}
	}
	
	return dpkqLsit;
}
  
/**
 * 个人的出勤统计
 * @param request
 * @param response
 * @return
 */
 @ResponseBody
 @RequestMapping(value="/singleKQList")
 private List<KqInfoResult> singleKQList(HttpServletRequest request,HttpServletResponse response) {
	 KqInfoResult kqParam = new KqInfoResult();
		Map<String, String> mapList = ToolClass.mapShiftStr(request);
		
			
		kqParam.setAccountName(mapList.get("accountName"));
		kqParam.setStartDate(mapList.get("beginData"));
		kqParam.setEndDate(mapList.get("endData"));
		
		if (kqParam.getStartDate().equals("")) {
			kqParam.setStartDate(ToolClass.inquirNowDate());
		}
		if (kqParam.getEndDate().equals("")) {
			kqParam.setEndDate(ToolClass.inquirNowDate());
		}
		
		List<KqInfoResult> kqInfoRes = this.service.searchKqInfoList(kqParam);
		 System.out.println("---班型测试kqInfoRes----"+kqInfoRes.size()+kqParam.getEndDate()+kqParam.getStartDate());
		 
		 //查询班次数据为空时执行，返回基本信息
		 if (kqInfoRes.size()<1) {
			 kqInfoRes=this.service.selectKqInfoList(kqParam);
			 System.out.println("kqInfoRes:"+kqInfoRes.size());
			 return kqInfoRes;
		}else {
			kqInfoRes = kquClassTime(kqInfoRes);
			return kqInfoRes;
		}
}


  
  /**
   * 人员出勤统计
   * @param request
   * @param response
   * @return
   */
   @ResponseBody
   @RequestMapping(value="/ryKQList")
   private List<PersonalKQBean> ryKQList(HttpServletRequest request,HttpServletResponse response) {
  	
	   	KqInfoResult kqParam = new KqInfoResult();
		Map<String, String> mapList = ToolClass.mapShiftStr(request);
		List<PersonalKQBean> rykqLsit = new ArrayList<>();
		
			
		kqParam.setName(mapList.get("name"));
		kqParam.setDepartname(mapList.get("departName"));
		kqParam.setPositionName(mapList.get("positionName"));
		kqParam.setJobNum(mapList.get("jobNum"));
		kqParam.setStartDate(mapList.get("beginData"));
		kqParam.setEndDate(mapList.get("endData"));
		
		if (kqParam.getStartDate().equals("")) {
			kqParam.setStartDate(ToolClass.inquirNowDate()+" "+"00:00:00.000");
		}
		if (kqParam.getEndDate().equals("")) {
			kqParam.setEndDate(ToolClass.inquirNowDate()+" "+"23:59:59.000");
		}
		
		
		List<KqInfoResult> kqInfoRes=this.service.selectKqInfoList(kqParam);
		 System.out.println("kqInfoRes:"+kqInfoRes.size());
		 for (KqInfoResult kqInfoResult : kqInfoRes) {
			PersonalKQBean pKqBean = new PersonalKQBean();
			pKqBean.setRyKQName(kqInfoResult.getName());
			pKqBean.setRyPositionKQName(kqInfoResult.getDepartname());
			pKqBean.setRyJobNum(kqInfoResult.getJobNum());
			pKqBean.setRyPositionKQName(kqInfoResult.getPositionName());
			pKqBean.setRyDepartKQId(kqInfoResult.getDepartId());
			pKqBean.setRyDepartKQName(kqInfoResult.getDepartname());
			pKqBean.setRyLaterTimes("0");
			pKqBean.setRyLeaveEarlyTimes("0");
			pKqBean.setRyMinersTimes("0");
			pKqBean.setRyOnNomalPA("0");
			pKqBean.setRyDownNomalPA("0");
			pKqBean.setRyOnPA("0");
			pKqBean.setRyDownPA("0");
			pKqBean.setRyRestDays("0");
			rykqLsit.add(pKqBean);
		}
		 System.out.println("人员考勤"+rykqLsit.size());
		 
		 kqInfoRes = this.service.searchKqInfoList(kqParam);
			System.out.println("---班型测试kqInfoRes----："+kqParam.getDepartname());
			System.out.println("---班型测试kqInfoRes----："+kqInfoRes.size());
			System.out.println("---班型测试kqInfoRes----："+kqParam.getEndDate());
			System.out.println("---班型测试kqInfoRes----："+kqParam.getStartDate());
		
		 //查询班次数据为空时执行，返回基本信息
		 if (kqInfoRes.size()<1) {
			 return rykqLsit;
		}else {
			kqInfoRes = kquClassTime(kqInfoRes);
			rykqLsit = selectRYKqInfo(kqInfoRes,rykqLsit);
			System.out.println("dpkqLsit2:"+kqInfoRes.size());
			return rykqLsit;
		}
  }
   /**
    * 通过个人打卡排班信息计算个人的考勤情况
    * @param dpkqLsit 部门列表
    * @param kqInfoResults
    * @param hasClassType 存在班型时进行数据统计
    * @return
    */
  public List<PersonalKQBean> selectRYKqInfo(List<KqInfoResult> kqInfoResults,List<PersonalKQBean> rykqLsit) {
  	
  	for (PersonalKQBean ry : rykqLsit) {
  		for (KqInfoResult ryKQ : kqInfoResults) {
  			if (ryKQ.getName().equals(ry.getRyKQName())) {
  				if (ryKQ.getClassTimeType().equals("2")) {
  					ry.setRyRestDays(String.valueOf(Integer.valueOf(ry.getRyRestDays())+1));
  				}else {
  					String upStatus=ryKQ.getFirstTimeState();
  					String downStatus=ryKQ.getLastTimeState();
  					System.out.println("upStatus:"+upStatus+"downStatus:"+downStatus);
  					if (upStatus.equals("迟到")) {
  						ry.setRyLaterTimes(String.valueOf(Integer.valueOf(ry.getRyLaterTimes())+1));
  					}
  					if (upStatus.equals("早退")) {
  						ry.setRyLeaveEarlyTimes(String.valueOf(Integer.valueOf(ry.getRyLaterTimes())+1));				
  					}
  					if (upStatus.equals("旷工") || downStatus.equals("旷工")) {
  						ry.setRyMinersTimes(String.valueOf(Integer.valueOf(ry.getRyMinersTimes())+1));
  					}
  					if (upStatus.equals("打卡异常")) {
  						ry.setRyOnPA(String.valueOf(Integer.valueOf(ry.getRyOnPA())+1));
  					}
  					if (downStatus.equals("打卡异常")) {
  						ry.setRyDownPA(String.valueOf(Integer.valueOf(ry.getRyDownPA())+1));
  					}
  					if (upStatus.equals("正常")) {
  						ry.setRyOnNomalPA(String.valueOf(Integer.valueOf(ry.getRyOnNomalPA())+1));
  					}
  					if (downStatus.equals("正常")) {
  						ry.setRyDownNomalPA(String.valueOf(Integer.valueOf(ry.getRyDownNomalPA())+1));
  					}
  				}
  				
  			}
  		}
  	}
  	
  	return rykqLsit;
  }
//   /**
//    * 部门考勤考勤的全部信息
//    * @param request
//    * @param response
//    * @return
//    */
//    @ResponseBody
//    @RequestMapping(value="/departKQList")
//    private List<KqInfoResult> departKQList(HttpServletRequest request,HttpServletResponse response) {
//   	
//   	 try {
//   	     List<KqInfoResult> kqList = this.service.selectKqInfoList(request);
//   	     return kqList;
//   	} catch (Exception e) {
//   		e.printStackTrace();
//   		ResponseMessageUtils.responseMessage(response, "查询错误,请重试!");
//   		return null;
//   	}
//   }
//    
//    /**
//     * 部门考勤考勤的全部信息
//     * @param request
//     * @param response
//     * @return
//     */
//     @ResponseBody
//     @RequestMapping(value="/departKQList")
//     private List<KqInfoResult> departKQList(HttpServletRequest request,HttpServletResponse response) {
//    	
//    	 try {
//    	     List<KqInfoResult> kqList = this.service.selectKqInfoList(request);
//    	     return kqList;
//    	} catch (Exception e) {
//    		e.printStackTrace();
//    		ResponseMessageUtils.responseMessage(response, "查询错误,请重试!");
//    		return null;
//    	}
//    }
//     
     
     /**
      * 部门考勤考勤的全部信息
      * @param request
      * @param response
      * @return
      */
      @ResponseBody
      @RequestMapping(value="/departKQList")
      private List<KqInfoResult> departKQList1(HttpServletRequest request,HttpServletResponse response) {
     	
     	 try {
//     	     List<KqInfoResult> kqList = this.service.selectKqInfoList(request);
     	     return null;
     	} catch (Exception e) {
     		e.printStackTrace();
     		ResponseMessageUtils.responseMessage(response, "查询错误,请重试!");
     		return null;
     	}
     }
//      
//      
//      /**
//       * 部门考勤考勤的全部信息
//       * @param request
//       * @param response
//       * @return
//       */
//       @ResponseBody
//       @RequestMapping(value="/departKQList")
//       private List<KqInfoResult> departKQList(HttpServletRequest request,HttpServletResponse response) {
//      	
//      	 try {
//      	     List<KqInfoResult> kqList = this.service.selectKqInfoList(request);
//      	     return kqList;
//      	} catch (Exception e) {
//      		e.printStackTrace();
//      		ResponseMessageUtils.responseMessage(response, "查询错误,请重试!");
//      		return null;
//      	}
//      }
//       
//       /**
//        * 部门考勤考勤的全部信息
//        * @param request
//        * @param response
//        * @return
//        */
//        @ResponseBody
//        @RequestMapping(value="/departKQList")
//        private List<KqInfoResult> departKQList(HttpServletRequest request,HttpServletResponse response) {
//       	
//       	 try {
//       	     List<KqInfoResult> kqList = this.service.selectKqInfoList(request);
//       	     return kqList;
//       	} catch (Exception e) {
//       		e.printStackTrace();
//       		ResponseMessageUtils.responseMessage(response, "查询错误,请重试!");
//       		return null;
//       	}
//       }
// 
 
 //加载排班信息列表
 //添加排班
 //加载考勤规则列表
 //添加考勤规则

}
