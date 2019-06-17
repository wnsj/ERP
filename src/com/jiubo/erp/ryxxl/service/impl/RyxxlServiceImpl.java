package com.jiubo.erp.ryxxl.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.jiubo.erp.common.DoubleUtil;
import com.jiubo.erp.common.MapUtil;
import com.jiubo.erp.common.MessageException;
import com.jiubo.erp.common.TimeUtil;
import com.jiubo.erp.kqgl.service.KqParamSetService;
import com.jiubo.erp.rygl.bean.DepartmentBean;
import com.jiubo.erp.ryxxl.bean.DepartmentAttendanceBean;
import com.jiubo.erp.ryxxl.bean.OutEmpBean;
import com.jiubo.erp.ryxxl.bean.ZpxgpgBean;
import com.jiubo.erp.ryxxl.dao.RyxxlDao;
import com.jiubo.erp.ryxxl.service.RyxxlService;

//人员信息管理类impl
@Service
@Transactional
public class RyxxlServiceImpl implements RyxxlService{
	
	@Autowired
	private RyxxlDao ryxxlDao;
	
	@Autowired
	private KqParamSetService kqParamSetService;
	
	@Override
	public Map<String, Object> queryZzryReport(Map<String, Object> paraMap) throws Exception {
		String date = MapUtil.getString(paraMap, "date", MapUtil.NOT_NULL);
		String flag = MapUtil.getString(paraMap, "flag", MapUtil.ALLOW_NULL);
		date = TimeUtil.getDateYYYY_MM_DD_HH_MM_SS(TimeUtil.dateAdd(TimeUtil.parseAnyDate(date), TimeUtil.UNIT_DAY, 1));
		Map<String,Object> dataMap = new HashMap<String,Object>();
		Map<String,Object> resultMap = ryxxlDao.queryZzryReport(date, flag);
		//在职人数
		double sumCount = MapUtil.getDouble(resultMap, "SUMCOUNT", MapUtil.ALLOW_NULL);
		dataMap.put("sumCount", sumCount);
		//-----------------工龄-----------------
		Map<String,Object> data = null;
		data = new HashMap<String,Object>();
		double m = 0;
		
		//1-3月
		m = MapUtil.getDouble(resultMap, "MM_1", MapUtil.ALLOW_NULL);
		data.put("oneThreeCount",(int)m);
		data.put("oneThreeMix",sumCount == 0 ? 0 : DoubleUtil.roundByScale(m / sumCount * 100, 2) + "%");
		//4-6月
		m = MapUtil.getDouble(resultMap, "MM_2", MapUtil.ALLOW_NULL);
		data.put("fourSixCount",(int)m);
		data.put("fourSixMix",sumCount == 0 ? 0 : DoubleUtil.roundByScale(m / sumCount * 100, 2) + "%");
		//7-1年
		m = MapUtil.getDouble(resultMap, "MM_3", MapUtil.ALLOW_NULL);
		data.put("sevenCount",(int)m);
		data.put("sevenMix",sumCount == 0 ? 0 : DoubleUtil.roundByScale(m / sumCount * 100, 2) + "%");
		//满一年
		m = MapUtil.getDouble(resultMap, "MM_4", MapUtil.ALLOW_NULL);
		data.put("oneYearCount",(int)m);
		data.put("oneYearMix",sumCount == 0 ? 0 : DoubleUtil.roundByScale(m / sumCount * 100, 2) + "%");
		//满两年
		m = MapUtil.getDouble(resultMap, "MM_5", MapUtil.ALLOW_NULL);
		data.put("twoYearCount",(int)m);
		data.put("twoYearMix",sumCount == 0 ? 0 : DoubleUtil.roundByScale(m / sumCount * 100, 2) + "%");
		//3年以上
		m = MapUtil.getDouble(resultMap, "MM_6", MapUtil.ALLOW_NULL);
		data.put("threeYearCount",(int)m);
		data.put("threeYearMix",sumCount == 0 ? 0 : DoubleUtil.roundByScale(m / sumCount * 100, 2) + "%");
		
		//工龄总和
		m = MapUtil.getDouble(resultMap, "MM_15", MapUtil.ALLOW_NULL);
		data.put("avgWorkCount",sumCount == 0 ? 0 : DoubleUtil.roundByScale(m / sumCount, 2));
		dataMap.put("work", data);
		//---------------------性别-------------------
		
		data = new HashMap<String,Object>();
		m = MapUtil.getDouble(resultMap, "MM_7", MapUtil.ALLOW_NULL);
		data.put("man",(int)m);
		data.put("manMix",sumCount == 0 ? 0 : DoubleUtil.roundByScale(m / sumCount * 100, 2) + "%");
		
		m = MapUtil.getDouble(resultMap, "MM_8", MapUtil.ALLOW_NULL);
		data.put("girl",(int)m);
		data.put("girlMix",sumCount == 0 ? 0 : DoubleUtil.roundByScale(m / sumCount * 100, 2) + "%");
		
		m = MapUtil.getDouble(resultMap, "MM_22", MapUtil.ALLOW_NULL);
		data.put("otherSex",(int)m);
		data.put("otherSexMix",sumCount == 0 ? 0 : DoubleUtil.roundByScale(m / sumCount * 100, 2) + "%");
		dataMap.put("sex", data);
		
		//---------------------年龄-------------------
		data = new HashMap<String,Object>();
		//20岁以下
		m = MapUtil.getDouble(resultMap, "MM_9", MapUtil.ALLOW_NULL);
		data.put("twentyAge",(int)m);
		data.put("twentyAgeMix",sumCount == 0 ? 0 : DoubleUtil.roundByScale(m / sumCount * 100, 2) + "%");
		//21-25岁
		m = MapUtil.getDouble(resultMap, "MM_10", MapUtil.ALLOW_NULL);
		data.put("twentyOneAge",(int)m);
		data.put("twentyOneAgeMix",sumCount == 0 ? 0 : DoubleUtil.roundByScale(m / sumCount * 100, 2) + "%");
		//26-30岁
		m = MapUtil.getDouble(resultMap, "MM_11", MapUtil.ALLOW_NULL);
		data.put("twentySixAge",(int)m);
		data.put("twentySixAgeMix",sumCount == 0 ? 0 : DoubleUtil.roundByScale(m / sumCount * 100, 2) + "%");
		//31-35岁
		m = MapUtil.getDouble(resultMap, "MM_12", MapUtil.ALLOW_NULL);
		data.put("thirtyOneAge",(int)m);
		data.put("thirtyOneAgeMix",sumCount == 0 ? 0 : DoubleUtil.roundByScale(m / sumCount * 100, 2) + "%");
		//36-40岁
		m = MapUtil.getDouble(resultMap, "MM_13", MapUtil.ALLOW_NULL);
		data.put("thirtySixAge",(int)m);
		data.put("thirtySixAgeMix",sumCount == 0 ? 0 : DoubleUtil.roundByScale(m / sumCount * 100, 2) + "%");
		//40以上
		m = MapUtil.getDouble(resultMap, "MM_14", MapUtil.ALLOW_NULL);
		data.put("fortyAge",(int)m);
		data.put("fortyAgeMix",sumCount == 0 ? 0 : DoubleUtil.roundByScale(m / sumCount * 100, 2) + "%");
		//总年龄
		m = MapUtil.getDouble(resultMap, "MM_16", MapUtil.ALLOW_NULL);
		data.put("avgAge",sumCount == 0 ? 0 : DoubleUtil.roundByScale(m / sumCount, 2));
		dataMap.put("age", data);
		
		//---------------------学历-------------------
		data = new HashMap<String,Object>();
		resultMap = ryxxlDao.queryEducation(date, flag);
		sumCount = MapUtil.getDouble(resultMap, "SUMCOUNT", MapUtil.ALLOW_NULL);
	
		//初中
		m = MapUtil.getDouble(resultMap, "MM_17", MapUtil.ALLOW_NULL);
		data.put("chuzhong",(int)m);
		data.put("chuzhongMix",sumCount == 0 ? 0 : DoubleUtil.roundByScale(m / sumCount * 100, 2) + "%");
		//高中
		m = MapUtil.getDouble(resultMap, "MM_18", MapUtil.ALLOW_NULL);
		data.put("gaozhong",(int)m);
		data.put("gaozhongMix",sumCount == 0 ? 0 :DoubleUtil.roundByScale(m / sumCount * 100, 2) + "%");
		//专科
		m = MapUtil.getDouble(resultMap, "MM_19", MapUtil.ALLOW_NULL);
		data.put("zhuanke",(int)m);
		data.put("zhuankeMix",sumCount == 0 ? 0 : DoubleUtil.roundByScale(m / sumCount * 100, 2) + "%");
		//本科
		m = MapUtil.getDouble(resultMap, "MM_20", MapUtil.ALLOW_NULL);
		data.put("benke",(int)m);
		data.put("benkeMix",sumCount == 0 ? 0 : DoubleUtil.roundByScale(m / sumCount * 100, 2) + "%");
		//其他
		m = MapUtil.getDouble(resultMap, "MM_21", MapUtil.ALLOW_NULL);
		data.put("qita",(int)m);
		data.put("qitaMix",sumCount == 0 ? 0 : DoubleUtil.roundByScale(m / sumCount * 100, 2) + "%");
		
		data.put("educationCount", sumCount);
		
		dataMap.put("education", data);
		
		//--------------------岗位--------------------------
		List<Map<String,Object>> list = ryxxlDao.queryPositionReport(date,flag);
		//保证json有键值
		String[] positionName = {"teShuPuGang","jiChuPuGang","zuZhangGangWei","zhuGuanGangWei","buZhangZongJian","zhuLiGangWei","ziShenGangWei"};
		dataMap.put("totalCount", 0);
		for(String str : positionName) {
			data = new HashMap<String,Object>();
			data.put("positionTypeId", "");
			data.put("positionTypeName", "");
			data.put("positionTypeCount", 0);
			data.put("totalCount", 0);
			data.put("positionTypeMix", "0%");
			dataMap.put(str, data);
		}
		
		for(Map<String,Object> map : list) {
			data = new HashMap<String,Object>();
			String name = MapUtil.getString(map, "POSITIONTYPE_NAME", MapUtil.ALLOW_NULL);
			String positionTypeId = MapUtil.getString(map, "POSITIONTYPE_ID", MapUtil.ALLOW_NULL);
			//每个岗位的人数
			double count = MapUtil.getDouble(map, "SUMCOUNT", MapUtil.ALLOW_NULL);
			//总人数
			sumCount = MapUtil.getDouble(map, "AllCOUNT", MapUtil.ALLOW_NULL);
			data.put("positionTypeId", positionTypeId);
			data.put("positionTypeName", name);
			data.put("positionTypeCount", (int)count);
			dataMap.put("totalCount", sumCount);
			data.put("positionTypeMix", sumCount == 0 ? 0 : DoubleUtil.roundByScale(count / sumCount * 100, 2) + "%");
			switch (name) {
				case "特殊普岗":
					dataMap.put("teShuPuGang", data);
					break;
				case "基础普岗":
					dataMap.put("jiChuPuGang", data);
					break;
				case "组长岗位":
					dataMap.put("zuZhangGangWei", data);
					break;
				case "主管岗位":
					dataMap.put("zhuGuanGangWei", data);
					break;
				case "部长总监":
					dataMap.put("buZhangZongJian", data);
					break;	
				case "主力岗位":
					dataMap.put("zhuLiGangWei", data);
					break;
				case "资深岗位":
					dataMap.put("ziShenGangWei", data);
					break;
			}
		}
		return dataMap;
	}

	@Override
	public Map<String, Object> queryLzryReport(Map<String, Object> paraMap) throws Exception {
		String begDate = MapUtil.getString(paraMap, "begDate", MapUtil.NOT_NULL);
		String endDate = MapUtil.getString(paraMap, "endDate", MapUtil.NOT_NULL);
		String deptId = MapUtil.getString(paraMap, "deptId", MapUtil.ALLOW_NULL);
		String posId =  MapUtil.getString(paraMap, "posId", MapUtil.ALLOW_NULL);
		endDate = TimeUtil.getDateYYYY_MM_DD_HH_MM_SS(TimeUtil.dateAdd(TimeUtil.parseAnyDate(endDate), TimeUtil.UNIT_DAY, 1));
		Map<String,Object> dataMap = new HashMap<String,Object>();
		String[] psId = {};
		if(StringUtils.isNotBlank(posId))psId = posId.split(",");
		//-------------------------离职员工工龄-------------------------
		Map<String,Object> data = null;
		data = new HashMap<String,Object>();
		double m = 0;
		
		Map<String,Object> resultMap = ryxxlDao.queryOutEmpWork(begDate, endDate,deptId,psId);
		double sumCount = MapUtil.getDouble(resultMap, "SUMCOUNT", MapUtil.ALLOW_NULL);
		//未转正
		m = MapUtil.getDouble(resultMap, "MM_1", MapUtil.ALLOW_NULL);
		data.put("noCorrection",(int)m);
		data.put("noCorrectionMix",sumCount == 0 ? 0 : DoubleUtil.roundByScale(m / sumCount * 100, 2) + "%");
		//1-3月
		m = MapUtil.getDouble(resultMap, "MM_2", MapUtil.ALLOW_NULL);
		data.put("oneThreeCount",(int)m);
		data.put("oneThreeMix",sumCount == 0 ? 0 : DoubleUtil.roundByScale(m / sumCount * 100, 2) + "%");
		//4-6月
		m = MapUtil.getDouble(resultMap, "MM_3", MapUtil.ALLOW_NULL);
		data.put("fourSixCount",(int)m);
		data.put("fourSixMix",sumCount == 0 ? 0 : DoubleUtil.roundByScale(m / sumCount * 100, 2) + "%");
		//7-1年
		m = MapUtil.getDouble(resultMap, "MM_4", MapUtil.ALLOW_NULL);
		data.put("sevenCount",(int)m);
		data.put("sevenMix",sumCount == 0 ? 0 : DoubleUtil.roundByScale(m / sumCount * 100, 2) + "%");
		//满一年
		m = MapUtil.getDouble(resultMap, "MM_5", MapUtil.ALLOW_NULL);
		data.put("oneYearCount",(int)m);
		data.put("oneYearMix",sumCount == 0 ? 0 : DoubleUtil.roundByScale(m / sumCount * 100, 2) + "%");
		//满两年
		m = MapUtil.getDouble(resultMap, "MM_6", MapUtil.ALLOW_NULL);
		data.put("twoYearCount",(int)m);
		data.put("twoYearMix",sumCount == 0 ? 0 : DoubleUtil.roundByScale(m / sumCount * 100, 2) + "%");
		//3年以上
		m = MapUtil.getDouble(resultMap, "MM_7", MapUtil.ALLOW_NULL);
		data.put("threeYearCount",(int)m);
		data.put("threeYearMix",sumCount == 0 ? 0 : DoubleUtil.roundByScale(m / sumCount * 100, 2) + "%");
		//总工龄
		m = MapUtil.getDouble(resultMap, "MM_8", MapUtil.ALLOW_NULL);
		data.put("avgWorkCount",sumCount == 0 ? 0 : DoubleUtil.roundByScale(m / sumCount, 2));
		dataMap.put("work", data);
		
		//--------------------------------------离职员工学历--------------------------------------
		data = new HashMap<String,Object>();
		resultMap = ryxxlDao.queryOutEmpEducation(begDate, endDate,deptId,psId);
		sumCount = MapUtil.getDouble(resultMap, "SUMCOUNT", MapUtil.ALLOW_NULL);
		//初中
		m = MapUtil.getDouble(resultMap, "MM_17", MapUtil.ALLOW_NULL);
		data.put("chuzhong",(int)m);
		data.put("chuzhongMix",sumCount == 0 ? 0 : DoubleUtil.roundByScale(m / sumCount * 100, 2) + "%");
		//高中
		m = MapUtil.getDouble(resultMap, "MM_18", MapUtil.ALLOW_NULL);
		data.put("gaozhong",(int)m);
		data.put("gaozhongMix",sumCount == 0 ? 0 :DoubleUtil.roundByScale(m / sumCount * 100, 2) + "%");
		//专科
		m = MapUtil.getDouble(resultMap, "MM_19", MapUtil.ALLOW_NULL);
		data.put("zhuanke",(int)m);
		data.put("zhuankeMix",sumCount == 0 ? 0 : DoubleUtil.roundByScale(m / sumCount * 100, 2) + "%");
		//本科
		m = MapUtil.getDouble(resultMap, "MM_20", MapUtil.ALLOW_NULL);
		data.put("benke",(int)m);
		data.put("benkeMix",sumCount == 0 ? 0 : DoubleUtil.roundByScale(m / sumCount * 100, 2) + "%");
		//其他
		m = MapUtil.getDouble(resultMap, "MM_21", MapUtil.ALLOW_NULL);
		data.put("qita",(int)m);
		data.put("qitaMix",sumCount == 0 ? 0 : DoubleUtil.roundByScale(m / sumCount * 100, 2) + "%");
		
		data.put("educationCount", sumCount);
		dataMap.put("education", data);
		//---------------------------------------重点岗位离职员工---------------------------------------
		List<Map<String,Object>> list = ryxxlDao.queryPositionDataIsPoint();
		for(Map<String,Object> map : list) {
			List<Map<String,Object>> outList = ryxxlDao.queryOutEmpCount(MapUtil.getString(map, "POSITION_ID", MapUtil.NOT_NULL),begDate,endDate);
			//单个岗位离职人数
			map.put("outEmpCount", outList.size());
			//离职人数占比
			map.put("outEmpMix", "0%");
			//辞职人数 
			int a = 0;
			//辞退人数
			int b = 0;
			for(Map<String,Object> outMap : outList) {
				map.put("outEmpMix", DoubleUtil.roundByScale(outList.size() / MapUtil.getDouble(outMap, "SUMCOUNT", MapUtil.NOT_NULL)* 100,2) + "%");
				String resignType = MapUtil.getString(outMap, "RESIGNTYPE", MapUtil.ALLOW_NULL);
				if("辞职".equals(resignType))a++;
				else if("辞退".equals(resignType))b++;
			}
			
			//离职类型
			String type = "";
			if(a > 0 && b > 0)
				type = "辞职" + a + "人,辞退" + b + "人";
			else if(a > 0 && b == 0)
				type = "辞职" + a + "人";
			else if(b > 0 && a == 0)
				type = "辞退" + b + "人";
			map.put("outPosType", type);
		}
		dataMap.put("outEmp", list);
		
		//---------------------------------------------查询所有岗位离职人数---------------------------------------------
		Map<String,OutEmpBean> outEmpMap = new HashMap<String,OutEmpBean>();
		List<OutEmpBean> outEmpList = ryxxlDao.queryOutEmpAllCount(begDate, endDate);
		
		for(OutEmpBean outEmpBean : outEmpList) {
			OutEmpBean bean = outEmpMap.get(outEmpBean.getPositionId());
			if(bean == null) {
				if(StringUtils.isBlank(outEmpBean.getResignType()))
					outEmpBean.setUnKnow((int) outEmpBean.getCount());
				else if("辞职".equals(outEmpBean.getResignType()))
					outEmpBean.setResignation((int) outEmpBean.getCount());
				else if("辞退".equals(outEmpBean.getResignType()))
					outEmpBean.setDismiss((int) outEmpBean.getCount());
				outEmpMap.put(outEmpBean.getPositionId(), outEmpBean);
			}else {
				bean.setCount(bean.getCount() + outEmpBean.getCount());
				if(StringUtils.isBlank(outEmpBean.getResignType()))
					bean.setUnKnow((int) (bean.getUnKnow() + outEmpBean.getCount()));
				else if("辞职".equals(outEmpBean.getResignType()))
					bean.setResignation((int) (bean.getResignation() + outEmpBean.getCount()));
				else if("辞退".equals(outEmpBean.getResignType()))
					bean.setDismiss((int) (bean.getDismiss() + outEmpBean.getCount()));
			}
		}
		
		//map转list
		Collection<OutEmpBean> valueCollection = outEmpMap.values();
	 
	    List<OutEmpBean> valueList = new ArrayList<OutEmpBean>(valueCollection);
	    
	    //对list从大到小排序
		Collections.sort(valueList,new Comparator<OutEmpBean>() {
			@Override
			public int compare(OutEmpBean o1, OutEmpBean o2) {
				//降序
				return (int) (o2.getCount() - o1.getCount());
			}
		});
		
		//取前5条
		if(valueList.size() > 5)
			valueList = valueList.subList(0, 5);
		for(OutEmpBean outEmpBean : valueList) {
			String str = "";
			if(outEmpBean.getResignation() == 0 && outEmpBean.getDismiss() == 0) {
				
			}else if(outEmpBean.getResignation() > 0 && outEmpBean.getDismiss() > 0) {
				str = "辞职" + outEmpBean.getResignation() + "人,辞退" + outEmpBean.getDismiss() + "人";
			}else if(outEmpBean.getResignation() > 0 && outEmpBean.getDismiss() == 0) {
				str = "辞职" + outEmpBean.getResignation() + "人" ;
			}else if(outEmpBean.getResignation() == 0 && outEmpBean.getDismiss() > 0) {
				str = "辞退" + outEmpBean.getDismiss() + "人";
			}
			outEmpBean.setResignType(str);	
		}
		dataMap.put("resignation", valueList);
		
		//---------------------------------------不同岗位类型离职员工---------------------------------------
		list = ryxxlDao.queryOutEmpPositionType(begDate, endDate);
		//保证json有键值
		String[] positionName = {"teShuPuGang","jiChuPuGang","zuZhangGangWei","zhuGuanGangWei","buZhangZongJian","zhuLiGangWei","ziShenGangWei"};
		dataMap.put("totalCount", 0);
		for(String str : positionName) {
			data = new HashMap<String,Object>();
			data.put("positionTypeId", "");
			data.put("positionTypeName", "");
			data.put("positionTypeCount", 0);
			data.put("totalCount", 0);
			data.put("positionTypeMix", "0%");
			dataMap.put(str, data);
		}
		
		//正式数据
		for(Map<String,Object> map : list) {
			data = new HashMap<String,Object>();
			String name = MapUtil.getString(map, "POSITIONTYPE_NAME", MapUtil.ALLOW_NULL);
			String positionTypeId = MapUtil.getString(map, "POSITIONTYPE_ID", MapUtil.ALLOW_NULL);
			//每个岗位的人数
			double count = MapUtil.getDouble(map, "SUMCOUNT", MapUtil.ALLOW_NULL);
			//总人数
			sumCount = MapUtil.getDouble(map, "AllCOUNT", MapUtil.ALLOW_NULL);
			data.put("positionTypeId", positionTypeId);
			data.put("positionTypeName", name);
			data.put("positionTypeCount", (int)count);
			dataMap.put("totalCount", sumCount);
			data.put("positionTypeMix", sumCount == 0 ? 0 : DoubleUtil.roundByScale(count / sumCount * 100, 2) + "%");
			switch (name) {
				case "特殊普岗":
					dataMap.put("teShuPuGang", data);
					break;
				case "基础普岗":
					dataMap.put("jiChuPuGang", data);
					break;
				case "组长岗位":
					dataMap.put("zuZhangGangWei", data);
					break;
				case "主管岗位":
					dataMap.put("zhuGuanGangWei", data);
					break;
				case "部长总监":
					dataMap.put("buZhangZongJian", data);
					break;	
				case "主力岗位":
					dataMap.put("zhuLiGangWei", data);
					break;
				case "资深岗位":
					dataMap.put("ziShenGangWei", data);
					break;
			}
		}
		return dataMap;
	}

	@Override
	public Map<String, Object> queryChanges(Map<String, Object> paraMap) throws MessageException {
		Map<String,Object> dataMap = new HashMap<String,Object>();
		try {
			String endDate = null;
			int level = MapUtil.getInt(paraMap, "level", MapUtil.NOT_NULL);
			String begDate = MapUtil.getString(paraMap, "begDate", MapUtil.NOT_NULL);
			Date date = TimeUtil.parseAnyDate(begDate);
			begDate = TimeUtil.getDateYYYY_MM_DD_HH_MM_SS(TimeUtil.getFirstDayOfMonth(date));
			endDate = TimeUtil.getDateYYYY_MM_DD_HH_MM_SS(TimeUtil.getFirstDayOfMonth(TimeUtil.dateAdd(date, TimeUtil.UNIT_MONTH, 1)));
			//每个部门离职人数
			Map<String,OutEmpBean> outEmpMap = new HashMap<String,OutEmpBean>();
			//每个部门异动人数(老部门，晋升，降职，换岗，调出)
			Map<String,OutEmpBean> changeMap = new HashMap<String,OutEmpBean>();
			//每个部门异动人数(新部门，调入)
			Map<String,OutEmpBean> newChangeMap = new HashMap<String,OutEmpBean>();
			//每个部门月初人数，入职人数，月末人数
			Map<String,OutEmpBean> empMap = new HashMap<String,OutEmpBean>();
			//查询离职人数
			List<OutEmpBean> outEmpList = ryxxlDao.queryOutEmpByDept(begDate, endDate);
			for(OutEmpBean outEmpBean : outEmpList) {
				OutEmpBean bean = outEmpMap.get(outEmpBean.getDepartmentId());
				if(bean == null) {
					if(StringUtils.isBlank(outEmpBean.getResignType()))
						outEmpBean.setUnKnow((int) outEmpBean.getCount());
					else if("辞职".equals(outEmpBean.getResignType()))
						outEmpBean.setResignation((int) outEmpBean.getCount());
					else if("辞退".equals(outEmpBean.getResignType()))
						outEmpBean.setDismiss((int) outEmpBean.getCount());
					outEmpMap.put(outEmpBean.getDepartmentId(), outEmpBean);
				}else {
					bean.setCount(bean.getCount() + outEmpBean.getCount());
					if(StringUtils.isBlank(outEmpBean.getResignType()))
						bean.setUnKnow((int) (bean.getUnKnow() + outEmpBean.getCount()));
					else if("辞职".equals(outEmpBean.getResignType()))
						bean.setResignation((int) (bean.getResignation() + outEmpBean.getCount()));
					else if("辞退".equals(outEmpBean.getResignType()))
						bean.setDismiss((int) (bean.getDismiss() + outEmpBean.getCount()));
				}
			}
			//查询异动
			outEmpList = ryxxlDao.queryOutEmpChangeByDept(begDate, endDate);
			for(OutEmpBean outEmpBean : outEmpList) {
				OutEmpBean bean = changeMap.get(outEmpBean.getDepartmentId());
				if(bean == null) {
					if("晋升".equals(outEmpBean.getResignType())) {
						outEmpBean.setJinSheng((int) outEmpBean.getCount());
					}else if("降职".equals(outEmpBean.getResignType())) {
						outEmpBean.setJiangZhi((int) outEmpBean.getCount());
					}else if("换岗".equals(outEmpBean.getResignType())) {
						outEmpBean.setChange((int) outEmpBean.getCount());
						if(outEmpBean.getDepartmentId().equals(outEmpBean.getNewDepartmentId())) {
							//同部门，换岗
						}else {
							//不同部门，换岗,调出+count
							outEmpBean.setDiaoChu((int) outEmpBean.getCount());
							//新部门调入
							OutEmpBean newChange = newChangeMap.get(outEmpBean.getNewDepartmentId());
							if(newChange != null) {
								newChange.setDiaoRu((int) (outEmpBean.getDiaoRu() + outEmpBean.getCount()));
							}else{
								newChange = new OutEmpBean();
								newChange.setDepartmentId(outEmpBean.getNewDepartmentId());
								newChange.setDiaoRu((int) outEmpBean.getCount());
								newChangeMap.put(outEmpBean.getNewDepartmentId(), newChange);
							}
						}
					}
					changeMap.put(outEmpBean.getDepartmentId(), outEmpBean);
				}else {
					if("晋升".equals(outEmpBean.getResignType())) {
						bean.setJinSheng((int) (bean.getJinSheng() + outEmpBean.getCount()));
					}else if("降职".equals(outEmpBean.getResignType())) {
						bean.setJiangZhi((int)(bean.getJiangZhi() + outEmpBean.getCount()));
					}else if("换岗".equals(outEmpBean.getResignType())) {
						bean.setChange((int) (bean.getChange() + outEmpBean.getCount()));
						if(outEmpBean.getDepartmentId().equals(outEmpBean.getNewDepartmentId())) {
							//同部门，换岗
						}else {
							//不同部门，换岗,调出+count
							bean.setDiaoChu((int) (bean.getDiaoChu() + outEmpBean.getCount()));
							//新部门调入
							OutEmpBean newChange = newChangeMap.get(outEmpBean.getNewDepartmentId());
							if(newChange != null) {
								newChange.setDiaoRu((int) (outEmpBean.getDiaoRu() + outEmpBean.getCount()));
							}else{
								newChange = new OutEmpBean();
								newChange.setDepartmentId(outEmpBean.getNewDepartmentId());
								newChange.setDiaoRu((int) outEmpBean.getCount());
								newChangeMap.put(outEmpBean.getNewDepartmentId(), newChange);
							}
						}
					}
				}
			}
			
			//查询每个部门月初人数，入职人数，月末人数
			outEmpList = ryxxlDao.queryEmpCount(begDate, endDate);
			for(OutEmpBean outEmpBean : outEmpList) {
				//平均人数 = （月初人数 + 月末人数 ）/ 2
				//outEmpBean.setAvgCount((outEmpBean.getBegMonCount() + outEmpBean.getEndMonCount()) / 2);
				empMap.put(outEmpBean.getDepartmentId(), outEmpBean);
			}
			
			List<DepartmentBean> list = kqParamSetService.queryDepartmentEmployee(false,false);
			//将数据对应部门
			setListPara(list,outEmpMap,changeMap,newChangeMap,empMap);
	
			//统计各部门人数
			for(DepartmentBean departmentBean : list) {
				int count = departmentBean.getResignation() + departmentBean.getDismiss() + departmentBean.getUnKnow();
				int total = departmentBean.getJinSheng() + departmentBean.getJiangZhi() + departmentBean.getChange();
				int begMon = departmentBean.getBegMonCount();
				int endMon = departmentBean.getEndMonCount();
				addList(departmentBean,departmentBean.getChildren());
				departmentBean.setCount(count + departmentBean.getCount());
				departmentBean.setTotal(total + departmentBean.getTotal());
				departmentBean.setBegMonCount(begMon + departmentBean.getBegMonCount());
				departmentBean.setEndMonCount(endMon + departmentBean.getEndMonCount());
			}
			
			//总计
			DepartmentBean totalBean = new DepartmentBean();
		
			//根据level获取不同层次部门
			if(level == 1){
				for (DepartmentBean bean : list) {
					bean.getChildren().clear();
					addBean(totalBean, bean, true);
				}
			}else if(level == 2) {
				for (DepartmentBean departmentBean : list) {
					addBean(totalBean, departmentBean, true);
					for(DepartmentBean bean : departmentBean.getChildren()) {
						bean.getChildren().clear();
						addBean(totalBean, bean, false);
					}
				}
			}else {
				for (DepartmentBean departmentBean : list) {
					addBean(totalBean, departmentBean, true);
					for(DepartmentBean bean : departmentBean.getChildren()) {
						addBean(totalBean, bean, false);
						for(DepartmentBean dept : bean.getChildren()) {
							addBean(totalBean, dept, false);
							for(DepartmentBean dpt : dept.getChildren()) {
								addBean(totalBean, dpt, false);
							}
						}
					}
				}
			}
			
			//平均人数
			double avgCount = (totalBean.getBegMonCount() + totalBean.getEndMonCount()) / 2;
			dataMap.put("avgCount", avgCount);
			dataMap.put("begMonCount", totalBean.getBegMonCount());
			dataMap.put("entryCount", totalBean.getEntryCount());
			dataMap.put("count", totalBean.getCount());
			dataMap.put("resignation", totalBean.getResignation());
			dataMap.put("dismiss", totalBean.getDismiss());
			dataMap.put("unKnow", totalBean.getUnKnow());
			//离职率
			dataMap.put("resignationMix", avgCount == 0 ? 0 : Double.valueOf(DoubleUtil.roundByScale(totalBean.getCount() / avgCount * 100 , 2)));
			dataMap.put("total", totalBean.getTotal());
			dataMap.put("diaoRu", totalBean.getDiaoRu());
			dataMap.put("diaoChu", totalBean.getDiaoChu());
			dataMap.put("jinSheng", totalBean.getJinSheng());
			dataMap.put("jiangZhi", totalBean.getJiangZhi());
			dataMap.put("change", totalBean.getChange());
			//异动率
			dataMap.put("changeMix",  avgCount == 0 ? 0 : Double.valueOf(DoubleUtil.roundByScale(totalBean.getTotal() / avgCount * 100 , 2)));
			dataMap.put("endMonCount", totalBean.getEndMonCount());
			dataMap.put("outEmp",list);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MessageException(e.getMessage());
		}
		return dataMap;
	}
	
	//求bean中的和
	private void addBean(DepartmentBean departmentBean,DepartmentBean bean,boolean flag) {
		if(flag) {
			departmentBean.setBegMonCount(departmentBean.getBegMonCount() + bean.getBegMonCount());
			departmentBean.setEndMonCount(departmentBean.getEndMonCount() + bean.getEndMonCount());
			departmentBean.setEntryCount(departmentBean.getEntryCount() + bean.getEntryCount());
			departmentBean.setCount(departmentBean.getCount() + bean.getCount());
			departmentBean.setResignation(departmentBean.getResignation() + bean.getResignation());
			departmentBean.setDismiss(departmentBean.getDismiss() + bean.getDismiss());
			departmentBean.setUnKnow(departmentBean.getUnKnow() + bean.getUnKnow());
			departmentBean.setTotal(departmentBean.getTotal() + bean.getTotal());
			departmentBean.setDiaoRu(departmentBean.getDiaoRu() + bean.getDiaoRu());
			departmentBean.setDiaoChu(departmentBean.getDiaoChu() + bean.getDiaoChu());
			departmentBean.setJinSheng(departmentBean.getJinSheng() + bean.getJinSheng());
			departmentBean.setJiangZhi(departmentBean.getJiangZhi() + bean.getJiangZhi());
			departmentBean.setChange(departmentBean.getChange() + bean.getChange());
		}

		//平均人数 = （月初人数 + 月末人数 ）/ 2
		bean.setAvgCount((bean.getBegMonCount() + bean.getEndMonCount()) / 2);
		//离职率 = 离职人数 / 平均人数
		bean.setQuitMix(bean.getAvgCount() == 0 ? 0 : Double.valueOf(DoubleUtil.roundByScale(bean.getCount() / bean.getAvgCount() * 100 , 2)));
		//异动率 = 异动人数 / 平均人数
		bean.setChangeMix(bean.getAvgCount() == 0 ? 0 : Double.valueOf(DoubleUtil.roundByScale(bean.getCount() / bean.getAvgCount() * 100, 2)));
	}

	
	private void setListPara(List<DepartmentBean> list,Map<String,OutEmpBean> param1,Map<String,OutEmpBean> param2,Map<String,OutEmpBean> param3,Map<String,OutEmpBean> param4) {
		for(DepartmentBean departmentBean : list) {
			OutEmpBean empBean = param1.get(departmentBean.getID());
			if(empBean != null) {
				departmentBean.setResignation(empBean.getResignation());
				departmentBean.setDismiss(empBean.getDismiss());
				departmentBean.setUnKnow(empBean.getUnKnow());
			}
			empBean = param2.get(departmentBean.getID());
			if(empBean != null) {
				departmentBean.setJinSheng(departmentBean.getJinSheng() + empBean.getJinSheng());
				departmentBean.setJiangZhi(departmentBean.getJiangZhi() + empBean.getJiangZhi());
				departmentBean.setChange(departmentBean.getChange() + empBean.getChange());
				departmentBean.setDiaoChu(departmentBean.getDiaoChu() + empBean.getDiaoChu());
				departmentBean.setDiaoRu(departmentBean.getDiaoRu() + empBean.getDiaoRu());
			}
			empBean = param3.get(departmentBean.getID());
			if(empBean != null) {
				departmentBean.setJinSheng(departmentBean.getJinSheng() + empBean.getJinSheng());
				departmentBean.setJiangZhi(departmentBean.getJiangZhi() + empBean.getJiangZhi());
				departmentBean.setChange(departmentBean.getChange() + empBean.getChange());
				departmentBean.setDiaoChu(departmentBean.getDiaoChu() + empBean.getDiaoChu());
				departmentBean.setDiaoRu(departmentBean.getDiaoRu() + empBean.getDiaoRu());
			}
			empBean = param4.get(departmentBean.getID());
			if(empBean != null) {
				departmentBean.setBegMonCount(empBean.getBegMonCount());
				departmentBean.setAvgCount(empBean.getAvgCount());
				departmentBean.setEndMonCount(empBean.getEndMonCount());
				departmentBean.setEntryCount(empBean.getEntryCount());
			}
			if(departmentBean.getChildren().size() > 0) {
				setListPara(departmentBean.getChildren(),param1,param2,param3,param4);
			}
		}
	}
	
	private void addList(DepartmentBean bean,List<DepartmentBean> list) {
		int count = 0;
		int sum = 0;
		int begMonCount = 0;
		int endMonCount = 0;
		int resignationCount = 0;
		int dismissCount = 0;
		int unKnowCount = 0;
		int allEntryCount = 0;
		int diaoRuCount = 0;
		int diaoChuCount = 0;
		int jinShengCount = 0;
		int jiangZhiCount = 0;
		int changeCount = 0;
		
		for(DepartmentBean departmentBean : list) {
			//离职人数
			int total = departmentBean.getResignation() + departmentBean.getDismiss() + departmentBean.getUnKnow();
			//异动人数
			int total2 = departmentBean.getJinSheng() + departmentBean.getJiangZhi() + departmentBean.getChange();
			int begMon = departmentBean.getBegMonCount();
			int endMon = departmentBean.getEndMonCount();
			int resignation = departmentBean.getResignation();
			int dismiss = departmentBean.getDismiss();
			int unKnow = departmentBean.getUnKnow();
			int entryCount = departmentBean.getEntryCount();
			int diaoRu = departmentBean.getDiaoRu();
			int diaoChu = departmentBean.getDiaoChu();
			int jinSheng = departmentBean.getJinSheng();
			int jiangZhi = departmentBean.getJiangZhi();
			int change = departmentBean.getChange();
			if(departmentBean.getChildren().size() > 0) {
				addList(departmentBean,departmentBean.getChildren());
				//如果还有子部门， 小计 = 自身人数 + 子部门人数
				total += departmentBean.getCount();
				total2 += departmentBean.getTotal();
				begMon += departmentBean.getBegMonCount();
				endMon += departmentBean.getEndMonCount();
				resignation += departmentBean.getResignation();
				dismiss += departmentBean.getDismiss();
				unKnow += departmentBean.getUnKnow();
				entryCount += departmentBean.getEntryCount();
				diaoRu += departmentBean.getDiaoRu();
				diaoChu += departmentBean.getDiaoChu();
				jinSheng += departmentBean.getJinSheng();
				jiangZhi +=  departmentBean.getJiangZhi();
				change += departmentBean.getChange();
			}
			
			departmentBean.setCount(total);
			departmentBean.setTotal(total2);
			departmentBean.setBegMonCount(begMon);
			departmentBean.setEndMonCount(endMon);
			departmentBean.setResignation(resignationCount);
			departmentBean.setDismiss(dismiss);
			departmentBean.setUnKnow(unKnow);
			departmentBean.setEntryCount(entryCount);
			departmentBean.setDiaoRu(diaoRu);
			departmentBean.setDiaoChu(diaoChu);
			departmentBean.setJinSheng(jinSheng);
			departmentBean.setJiangZhi(jiangZhi);
			departmentBean.setChange(change);
			
			count += total;
			sum += total2;
			begMonCount += begMon;
			endMonCount += endMon;
			resignationCount += resignationCount;
			dismissCount += dismiss;
			unKnowCount += unKnow;
			allEntryCount += entryCount;
			diaoRuCount += diaoRu;
			diaoChuCount += diaoChu;
			jinShengCount += jinSheng;
			jiangZhiCount += jiangZhi;
			changeCount += change;
		}
		bean.setCount(count);
		bean.setTotal(sum);
		bean.setBegMonCount(begMonCount);
		bean.setEndMonCount(endMonCount);
		bean.setResignation(resignationCount);
		bean.setDismiss(dismissCount);
		bean.setUnKnow(unKnowCount);
		bean.setEntryCount(allEntryCount);
		bean.setDiaoRu(diaoRuCount);
		bean.setDiaoChu(diaoChuCount);
		bean.setJinSheng(jinShengCount);
		bean.setJiangZhi(jiangZhiCount);
		bean.setChange(changeCount);
	}

	@Override
	public Map<String, Object> queryRecruit(Map<String, Object> paraMap) throws MessageException {
		Map<String,Object> dataMap = new HashMap<String,Object>();
		try {
			String begDate = MapUtil.getString(paraMap, "begDate", MapUtil.NOT_NULL);
			String endDate = MapUtil.getString(paraMap, "endDate", MapUtil.NOT_NULL);
			begDate = TimeUtil.getDateYYYY_MM_DD_HH_MM_SS(TimeUtil.getFirstDayOfMonth(TimeUtil.parseAnyDate(begDate)));
			endDate = TimeUtil.getDateYYYY_MM_DD_HH_MM_SS(TimeUtil.getFirstDayOfMonth(TimeUtil.dateAdd(TimeUtil.parseAnyDate(endDate), TimeUtil.UNIT_MONTH, 1)));
			
			//查询每个部门岗位缺编人数，月初人数，计划招聘数，到面人数，合格人数，入职人数，月末人数
			List<ZpxgpgBean> recruitChannelList = ryxxlDao.queryRecruitByDeptPosId(begDate, endDate);
			for(ZpxgpgBean zpxgpgBean : recruitChannelList) {
				double faceCount = zpxgpgBean.getFaceCount();
				//招聘有效率 = 合格人数 / 到面人数 * 100%
				zpxgpgBean.setEffectiveMix(faceCount == 0 ? "0%" : DoubleUtil.roundByScale(zpxgpgBean.getQualifiedCount() / faceCount * 100, 2) + "%");
				//招聘达成率 = 入职人数 / 到面人数 * 100%
				zpxgpgBean.setReachMix(faceCount == 0 ? "0%" : DoubleUtil.roundByScale(zpxgpgBean.getEntryCount() / faceCount * 100, 2) + "%");
			}
			dataMap.put("candidates", recruitChannelList);
			
			//查询合格人数，面试人数，入职人数
			recruitChannelList = ryxxlDao.queryRecruitChannel(begDate,endDate);
			for(ZpxgpgBean zpxgpgBean : recruitChannelList) {
				double publishNum = zpxgpgBean.getPublishNum() ;
				//渠道反馈率 = 应聘人数 / 发布人数 * 100%
				zpxgpgBean.setFeedbackMix(publishNum == 0 ? "0%" : DoubleUtil.roundByScale(zpxgpgBean.getCandidates() / publishNum * 100, 2) + "%");
				//渠道有效率 = 合格人数 / 发布人数 * 100%
				zpxgpgBean.setEffectiveMix(publishNum == 0 ? "0%" : DoubleUtil.roundByScale(zpxgpgBean.getQualifiedCount() / publishNum * 100, 2) + "%");
				//渠道达成率 = 入职人数 / 发布人数 * 100%
				zpxgpgBean.setReachMix(publishNum == 0 ? "0%" : DoubleUtil.roundByScale(zpxgpgBean.getEntryCount() / publishNum * 100, 2) + "%");
			}
			dataMap.put("recruitChannel", recruitChannelList);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MessageException(e.getMessage());
		}
		return dataMap;
	}

	@Override
	public Map<String, Object> queryRlzylyReport(Map<String, Object> paraMap) throws MessageException {
		Map<String,Object> dataMap = new HashMap<String,Object>();
		try {
			//部门id
			String deptId = MapUtil.getString(paraMap, "deptId", MapUtil.ALLOW_NULL);
			//查询时间
			String begDate = MapUtil.getString(paraMap, "begDate", MapUtil.NOT_NULL);
			Date date = TimeUtil.parseAnyDate(begDate);
			int days = Integer.parseInt(TimeUtil.getDayStr(TimeUtil.getLastDayOfMonth(date)));
			begDate = TimeUtil.getYearMonthStr(date);
			//是否包括当月入职
			String isEntry = MapUtil.getString(paraMap, "isEntry", MapUtil.NOT_NULL);
			//是否包括当月离职
			String isQuit = MapUtil.getString(paraMap, "isQuit", MapUtil.NOT_NULL);
			//查询每个部门员工的打卡次数
			Map<String,OutEmpBean> clockMap = new HashMap<String,OutEmpBean>();
			List<OutEmpBean> clockList = ryxxlDao.queryDeptEmpClock(deptId,begDate, isEntry, isQuit);
			for(OutEmpBean outEmpBean : clockList) {
				clockMap.put(outEmpBean.getDepartmentId(), outEmpBean);
			}
			//查询每个部门未打卡天数
			Map<String,DepartmentAttendanceBean> deptAttendanceMap = new HashMap<String,DepartmentAttendanceBean>();
			List<DepartmentAttendanceBean> departmentAttendanceList = ryxxlDao.queryDepartmentAttendance(deptId,begDate);
			for(DepartmentAttendanceBean departmentAttendanceBean : departmentAttendanceList) {
				deptAttendanceMap.put(departmentAttendanceBean.getDepartmentId(), departmentAttendanceBean);
			}
			
			//查询每个部门正常休假天数
			Map<String,OutEmpBean> vacationMap = new HashMap<String,OutEmpBean>();
			clockList = ryxxlDao.queryDeptVacation(deptId,begDate, isEntry, isQuit);
			for(OutEmpBean outEmpBean : clockList) {
				vacationMap.put(outEmpBean.getDepartmentId(), outEmpBean);
			}
			
			//获取所有部门
			List<DepartmentBean> departmentList = kqParamSetService.queryDepartmentEmployee(false, false);
			
			//计算
			departmentList = calculateBean(departmentList, clockMap, deptAttendanceMap,vacationMap,days,deptId);
			dataMap.put("ziYuan", departmentList);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MessageException(e.getMessage());
		}
		return dataMap;
	}
	
	//计算
	private List<DepartmentBean> calculateBean(List<DepartmentBean> departmentList,Map<String,OutEmpBean> param1,Map<String,DepartmentAttendanceBean> param2,Map<String,OutEmpBean> param3,int days,String deptId) {
		for(DepartmentBean departmentBean : departmentList) {
			OutEmpBean outEmpBean = param1.get(departmentBean.getID());
			double count = 0;
			double clockCount = 0;
			if(outEmpBean != null) {
				count = outEmpBean.getCount();
				clockCount = outEmpBean.getSumCount();
			}
			//总人数
			departmentBean.setDismiss((int) count);
			//系统打卡天数
			departmentBean.setDiaoRu((int) clockCount);
			int unClock = 0;
			DepartmentAttendanceBean deptAttendanceBean = param2.get(departmentBean.getID());
			if(deptAttendanceBean != null) {
				unClock = deptAttendanceBean.getNum();
				departmentBean.setUnKnow(Integer.valueOf(deptAttendanceBean.getId()));
			}
			//未打卡天数
			departmentBean.setJinSheng(unClock);
			//本月天数
			departmentBean.setDiaoChu(days);
			//小计
			int total  = (int) (clockCount + unClock);
			departmentBean.setTotal(total);
			//月均出勤天数 = 总出勤天数 / 本月天数
			double avgCount = Double.valueOf(DoubleUtil.roundByScale(total / (double)days, 2));
			departmentBean.setAvgCount(avgCount);
			int vacation = 0;
			outEmpBean = param3.get(departmentBean.getID());
			if(outEmpBean != null)vacation = (int) outEmpBean.getCount();
			//部门休假天数
			departmentBean.setChange(vacation);
			//合理缺勤人数 = 合理缺勤 / 本月天数
			double queQin = Double.valueOf(DoubleUtil.roundByScale(vacation / (double)days, 2));
			departmentBean.setSumCount(queQin);
			//超出缺勤人数 = 总人数 - 月平均出勤人数
			double chaoChu = Double.valueOf(DoubleUtil.roundByScale(count - avgCount, 2));
			departmentBean.setQuitMix(chaoChu);
			//未利用人力资源 = 超出缺勤人数 - 合理缺勤人数
			double weiLiYong = Double.valueOf(DoubleUtil.roundByScale(chaoChu - queQin, 2));
			departmentBean.setCount(weiLiYong);
			//人力资源利用率 = （总人数 - 未利用人力资源）/ 总人数 * 100%
			double liYongMix = count == 0 ? 0 :  Double.valueOf(DoubleUtil.roundByScale((count - weiLiYong) / count * 100, 2));
			departmentBean.setChangeMix(liYongMix);
			if(StringUtils.isNotBlank(deptId) && deptId.equals(departmentBean.getID())) {
				departmentList = new ArrayList<DepartmentBean>();
				departmentList.add(departmentBean);
				return departmentList;
			}
			if(departmentBean.getChildren().size() > 0)calculateBean(departmentBean.getChildren(),param1,param2,param3,days,deptId);
		}
		return departmentList; 
	}

	@Override
	public void updateAddDeptAttendance(DepartmentAttendanceBean departmentAttendanceBean) throws MessageException {
		ryxxlDao.updateAddDeptAttendance(departmentAttendanceBean);
	}
}
/*
 for(DepartmentBean departmentBean : list) {
				sum += (departmentBean.getResignation() + departmentBean.getDismiss() + departmentBean.getUnKnow());
//				sum2 += departmentBean.getJinSheng() + departmentBean.getJiangZhi() + departmentBean.getChange();
				int a = 0;
				for(DepartmentBean bean : departmentBean.getChildren()) {
					a += (bean.getResignation() + bean.getDismiss() + bean.getUnKnow());
					int b = 0;
					for(DepartmentBean dept : bean.getChildren()) {
						b += (dept.getResignation() + dept.getDismiss() + dept.getUnKnow());
						int c = 0;
						for(DepartmentBean db : dept.getChildren()) {
							c += (db.getResignation() + db.getDismiss() + db.getUnKnow());
						}
						System.out.println("c:"+c);
						dept.setCount(c + dept.getCount());
					}
					System.out.println("b:"+b);
					bean.setCount(b + bean.getCount());
				}
				System.out.println("a:"+a);
				departmentBean.setCount(a + departmentBean.getCount());
			}
 */

/*
JSONArray jsArray = JSONArray.parseArray(JSONObject.toJSONString(list));
for(Object object : jsArray) {
	JSONObject jsonObject = (JSONObject) object;
	if(jsonObject.getIntValue("count") == 0) {
		jsonObject.clear();
		continue;
	}
	JSONArray jsonArray = jsonObject.getJSONArray("children");
	for(Object obj : jsonArray) {
		JSONObject jsonObj = (JSONObject) obj;
		if(jsonObj.getIntValue("count") == 0) {
			jsonObj.clear();
			continue;
		}
		JSONArray jsonArr = jsonObj.getJSONArray("children");
		for(Object ob : jsonArr) {
			JSONObject json = (JSONObject) ob;
			if(json.getIntValue("count") == 0)json.clear();
		}
	}
}
//System.out.println("list:"+JSONObject.toJSONString(list));

System.out.println("list:"+jsArray.toString());*/

/*
 计算部门人数之和
	private void addChildren(DepartmentBean departmentBean,List<DepartmentBean> list) {
		int count = 0;
		for(DepartmentBean bean : list) {
			int total = bean.getResignation() + bean.getDismiss() + bean.getUnKnow();
			if(bean.getChildren().size() > 0) {
				addChildren2(bean,bean.getChildren());
				total += bean.getCount();
			}
			bean.setCount(total);
			count += total;
		}
		departmentBean.setCount(count);
	}
	
	private void addChildren2(DepartmentBean departmentBean,List<DepartmentBean> list) {
		int count = 0;
		for(DepartmentBean bean : list) {
			int total = bean.getResignation() + bean.getDismiss() + bean.getUnKnow();
			if(bean.getChildren().size() > 0) {
				addChildren3(bean,bean.getChildren());
				total += bean.getCount();
			}
			bean.setCount(total);
			count += total;
		}
		departmentBean.setCount(count);
	}
	
	private void addChildren3(DepartmentBean departmentBean,List<DepartmentBean> list) {
		int count = 0;
		for(DepartmentBean bean : list) {
			int total = bean.getResignation() + bean.getDismiss() + bean.getUnKnow();
			bean.setCount(total);
			count += total;
		}
		departmentBean.setCount(count);
	}
*/

//for(Map.Entry<String, OutEmpBean> entry: outEmpMap.entrySet()) {
//System.out.println("##"+entry.getValue().getResignation()+","+entry.getValue().getDismiss()+","+entry.getValue().getUnKnow());
////System.out.println(entry.getKey()+"##"+entry.getValue().getResignation()+","+entry.getValue().getDismiss()+","+entry.getValue().getUnKnow());
//}
/*
 if(level == 1){
				for (DepartmentBean bean : list) {
					bean.getChildren().clear();
					//平均人数 = （月初人数 + 月末人数 ）/ 2
					bean.setAvgCount((bean.getBegMonCount() + bean.getEndMonCount()) / 2);
					//离职率 = 离职人数 / 平均人数
					bean.setQuitMix(bean.getCount() / bean.getAvgCount() == 0 ? 0 : Double.valueOf(DoubleUtil.roundByScale(bean.getCount() / bean.getAvgCount() * 100 , 2)));
					//异动率 = 异动人数 / 平均人数
					bean.setChangeMix(bean.getCount() / bean.getAvgCount() == 0 ? 0 : Double.valueOf(DoubleUtil.roundByScale(bean.getCount() / bean.getAvgCount() * 100, 2)));
				}
			}else if(level == 2) {
				for (DepartmentBean departmentBean : list) {
					departmentBean.setAvgCount((departmentBean.getBegMonCount() + departmentBean.getEndMonCount()) / 2);
					departmentBean.setQuitMix(departmentBean.getCount() / departmentBean.getAvgCount() == 0 ? 0 : Double.valueOf(DoubleUtil.roundByScale(departmentBean.getCount() / departmentBean.getAvgCount() * 100,2)));
					departmentBean.setChangeMix(departmentBean.getCount() / departmentBean.getAvgCount() == 0 ? 0 : Double.valueOf(DoubleUtil.roundByScale(departmentBean.getCount() / departmentBean.getAvgCount(),2)));
					for(DepartmentBean bean : departmentBean.getChildren()) {
						bean.getChildren().clear();
						bean.setAvgCount((bean.getBegMonCount() + bean.getEndMonCount()) / 2);
						bean.setQuitMix(bean.getCount() / bean.getAvgCount() == 0 ? 0 : Double.valueOf(DoubleUtil.roundByScale(bean.getCount() / bean.getAvgCount() * 100,2)));
						bean.setChangeMix(bean.getCount() / bean.getAvgCount() == 0 ? 0 : Double.valueOf(DoubleUtil.roundByScale(bean.getCount() / bean.getAvgCount() * 100,2)));
					}
				}
			}else {
				for (DepartmentBean departmentBean : list) {
					departmentBean.setAvgCount((departmentBean.getBegMonCount() + departmentBean.getEndMonCount()) / 2);
					departmentBean.setQuitMix(departmentBean.getCount() / departmentBean.getAvgCount() == 0 ? 0 : Double.valueOf(DoubleUtil.roundByScale(departmentBean.getCount() / departmentBean.getAvgCount() * 100,2)));
					departmentBean.setChangeMix(departmentBean.getCount() / departmentBean.getAvgCount() == 0 ? 0 : Double.valueOf(DoubleUtil.roundByScale(departmentBean.getCount() / departmentBean.getAvgCount() * 100,2)));
					for(DepartmentBean bean : departmentBean.getChildren()) {
						bean.setAvgCount((bean.getBegMonCount() + bean.getEndMonCount()) / 2);
						bean.setQuitMix(bean.getCount() / bean.getAvgCount() == 0 ? 0 : Double.valueOf(DoubleUtil.roundByScale(bean.getCount() / bean.getAvgCount() * 100,2)));
						bean.setChangeMix(bean.getCount() / bean.getAvgCount() == 0 ? 0 : Double.valueOf(DoubleUtil.roundByScale(bean.getCount() / bean.getAvgCount() * 100,2)));
						for(DepartmentBean dept : bean.getChildren()) {
							dept.setAvgCount((dept.getBegMonCount() + dept.getEndMonCount()) / 2);
							dept.setQuitMix(dept.getCount() / dept.getAvgCount() == 0 ? 0 : Double.valueOf(DoubleUtil.roundByScale(dept.getCount() / dept.getAvgCount() * 100,2)));
							dept.setChangeMix(dept.getCount() / dept.getAvgCount() == 0 ? 0 : Double.valueOf(DoubleUtil.roundByScale(dept.getCount() / dept.getAvgCount() * 100,2)));
							for(DepartmentBean dpt : dept.getChildren()) {
								dpt.setAvgCount((dpt.getBegMonCount() + dpt.getEndMonCount()) / 2);
								dpt.setQuitMix(dpt.getCount() / dpt.getAvgCount() == 0 ? 0 : Double.valueOf(DoubleUtil.roundByScale(dpt.getCount() / dpt.getAvgCount() * 100,2)) );
								dpt.setChangeMix(dpt.getCount() / dpt.getAvgCount() == 0 ? 0 : Double.valueOf(DoubleUtil.roundByScale(dpt.getCount() / dpt.getAvgCount() * 100,2)));
							}
						}
					}
				}
			}
 */
