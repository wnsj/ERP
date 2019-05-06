package com.jiubo.erp.kqgl.controller;



import java.util.ArrayList;


import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mybatis.generator.codegen.ibatis2.Ibatis2FormattingUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.jiubo.erp.erpLogin.util.ResponseMessageUtils;
import com.jiubo.erp.kqgl.service.KqService;

import com.jiubo.erp.kqgl.vo.DepartKQ;
import com.jiubo.erp.kqgl.vo.KqInfoResult;
import com.jiubo.erp.kqgl.vo.PunchRecord;
import com.jiubo.erp.rygl.bean.DepartmentBean;
import com.quicksand.push.ToolClass;




@Controller
@RequestMapping("/kqgl")
public class KqController {
	
 @Autowired
 private KqService service;
 

 
 
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
		kqParam.setDepartname(mapList.get("dpId"));
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
		kqParam.setDepartname(mapList.get("dpId"));
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
			    	pRecord.setUserId(kqInfoResult.getuId());
//					System.out.println("打卡参数"+pRecord.getYear()+"-"+pRecord.getMonth()+"-"+pRecord.getDay());
					
					List<PunchRecord> prList = this.service.selectPunchRecordList(pRecord);
					pRecord = prList.get(0);
					if (prList.size()>0 && pRecord != null) {
						if ( pRecord.getMaxAttTime()==null && pRecord.getMinAttTime()==null) {
							System.out.println("最大值和最小值为null");
						}else if (pRecord.getMaxAttTime()==pRecord.getMinAttTime()) {
							System.out.println("最大值和最小值相等");
						}else {
							System.out.println("最大值和最小值不等");
							kqInfoResult.setFirstTime(pRecord.getMinAttTime());
							kqInfoResult.setFirstTimeState(completForeKQInfo(pRecord.getMinAttTime(), kqInfoResult.getStartTime(), kqInfoResult.getEndTime()));
							kqInfoResult.setLastTime(pRecord.getMaxAttTime());
							kqInfoResult.setLastTimeState(completAfterKQInfo(pRecord.getMaxAttTime(), kqInfoResult.getStartTime(), kqInfoResult.getEndTime()));
							
						}
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
	
	 if (ToolClass.compare_date(mTime, beginTime)<=0) {
		 return "正常";
	 }else if (ToolClass.compare_date(mTime, beginTime)>0 &&
				ToolClass.compare_date(mTime, ToolClass.strDateTimeShiftStr(beginTime, 30))<=0) {
		 return "迟到";
	}else if (ToolClass.compare_date(mTime, ToolClass.strDateTimeShiftStr(beginTime, 30))>0 &&
			ToolClass.compare_date(mTime, ToolClass.strDateTimeShiftStr(beginTime, 120))<=0) {
		 return "旷工";
	}else if (ToolClass.compare_date(mTime, ToolClass.strDateTimeShiftStr(beginTime, 120))>0 &&
			ToolClass.compare_date(mTime, ToolClass.strDateTimeShiftStr(endTime, -180))<=0) {
		return "打卡异常";
	}else {
		return "上班未打卡";
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
	 if (ToolClass.compare_date(mTime, ToolClass.strDateTimeShiftStr(beginTime, 120))<=0) {
		 return "下班未打卡";
	}else if (ToolClass.compare_date(mTime, ToolClass.strDateTimeShiftStr(beginTime, 120))>0 &&
				ToolClass.compare_date(mTime, ToolClass.strDateTimeShiftStr(endTime, -180))<=0) {
		return "打卡异常";
	}else if (ToolClass.compare_date(mTime, ToolClass.strDateTimeShiftStr(endTime, -180))>0 &&
			ToolClass.compare_date(mTime, ToolClass.strDateTimeShiftStr(endTime, -30))<=0) {
		 return "旷工";
	}else if (ToolClass.compare_date(mTime, ToolClass.strDateTimeShiftStr(endTime, -30))>0 &&
			ToolClass.compare_date(mTime, endTime)<0) {
		 return "早退";
	}else {
		 return "正常";
	}
}
 /**
  * 加载当天的全部的用户考勤信息
  * @param request
  * @param response
  * @return
  */
 @ResponseBody
 @RequestMapping(value="/departBaseKQInfo")
 private List<DepartKQ> departBaseKQInfo(HttpServletRequest request,HttpServletResponse response) {
		
//	 	KqInfoResult kqParam = new KqInfoResult();
//		Map<String, String> mapList = ToolClass.mapShiftStr(request);
//		List<DepartKQ> dpList  = new ArrayList<>();
//		DepartKQ dpKq;
//			
//		kqParam.setName(mapList.get("name"));
//		kqParam.setDepartname(mapList.get("dpId"));
//		kqParam.setPositionName(mapList.get("positionName"));
//		kqParam.setJobNum(mapList.get("jobNum"));
//		
//		 try {
//		    List<KqInfoResult> kqInfoList = this.service.selectKqInfoList(kqParam);
//		     System.out.println("kqList:"+kqInfoList.size());
//		     for (KqInfoResult kqInfoResult : kqInfoList) {
//				if (dpList.size()>0) {
//					for (DepartKQ dpkqLsit : dpList) {
//						if (kqInfoResult.getDepartname()==dpKq.getDepartKQName()) {
//							
//						}
//					}
//				}else {
//					dpKq = new DepartKQ(kqInfoResult.getDepartname());
//					dpList.add(dpKq);
//				}
//			}
//		     
//		     
//		     return dpList;
//		} catch (Exception e) {
//			e.printStackTrace();
//			ResponseMessageUtils.responseMessage(response, "查询错误,请重试!");
			return null;
//		}
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
	 
		//查询班次数据为空时执行，返回基本信息
		if (kqInfoRes.size()<1) {
			kqInfoRes=this.service.selectKqInfoList(kqParam);
			System.out.println("kqInfoRes:"+kqInfoRes.size());
		 
			dpkqLsit = selectDepartKqInfo(kqInfoRes);
			System.out.println("dpkqLsit1:"+kqInfoRes.size());
			return dpkqLsit;
		}else {
			kqInfoRes = kquClassTime(kqInfoRes);
			dpkqLsit = selectDepartKqInfo(kqInfoRes);
			System.out.println("dpkqLsit2:"+kqInfoRes.size());
			return dpkqLsit;
		}

 }
 /**
  * 通过个人考勤信息计算部门考勤信息
  * @param kqInfoResults
  * @return
  */
public List<DepartKQ> selectDepartKqInfo(List<KqInfoResult> kqInfoResults) {
	DepartKQ departKQ = new DepartKQ("","0","0","0","0","0","0","0");
	List<DepartKQ> dpList = new ArrayList<>();
	for (KqInfoResult kqInfoResult : kqInfoResults) {
		if (dpList.size()>0) {
			boolean isSame = false;
			for (DepartKQ departKQ2 : dpList) {
				if (departKQ2.getDepartKQName().equals(kqInfoResult.getDepartname())) {
					isSame = true;
					departKQ2.setRestDays(String.valueOf(Integer.valueOf(departKQ2.getRestDays())+1));
					break;
				}
			}
			if (isSame==false) {
				if (kqInfoResult.getClassTimeType()=="2") {
					departKQ.setRestDays("1");
				}
				dpList.add(departKQ);
			}
		}else {
			if (kqInfoResult.getClassTimeType()=="2") {
				departKQ.setRestDays("1");
			}
			dpList.add(departKQ);
		}
	}
	return dpList;
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
	
	 try {
//	     List<KqInfoResult> kqList = this.service.selectKqInfoList(request);
	     return null;
	} catch (Exception e) {
		e.printStackTrace();
		ResponseMessageUtils.responseMessage(response, "查询错误,请重试!");
		return null;
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
   private List<KqInfoResult> ryKQList(HttpServletRequest request,HttpServletResponse response) {
  	
  	 try {
//  	     List<KqInfoResult> kqList = this.service.selectKqInfoList(request);
  	     return null;
  	} catch (Exception e) {
  		e.printStackTrace();
  		ResponseMessageUtils.responseMessage(response, "查询错误,请重试!");
  		return null;
  	}
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
//     
//     /**
//      * 部门考勤考勤的全部信息
//      * @param request
//      * @param response
//      * @return
//      */
//      @ResponseBody
//      @RequestMapping(value="/departKQList")
//      private List<KqInfoResult> departKQList(HttpServletRequest request,HttpServletResponse response) {
//     	
//     	 try {
//     	     List<KqInfoResult> kqList = this.service.selectKqInfoList(request);
//     	     return kqList;
//     	} catch (Exception e) {
//     		e.printStackTrace();
//     		ResponseMessageUtils.responseMessage(response, "查询错误,请重试!");
//     		return null;
//     	}
//     }
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
