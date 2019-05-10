package com.jiubo.erp.zpgl.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jiubo.erp.common.MessageException;
import com.jiubo.erp.zpgl.bean.RecruitChannelBean;
import com.jiubo.erp.zpgl.bean.RecruitDataBean;

public interface ZpglDao {
	
	//查询招聘渠道
	public List<RecruitChannelBean> queryRecruitChannel();
	
	//删除招聘渠道
	public void deleteRecruitChannel(String id);
	
	//查询面试信息
	public List<RecruitDataBean> queryRecruitData(RecruitDataBean recruitDataBean);
	
	//添加面试信息
	public void addRecruitData(RecruitDataBean recruitDataBean);
	
	//删除面试信息
	public void updateRecruitDataById(String id);
	
	//修改面试信息
	public void updateRecruitData(RecruitDataBean recruitDataBean);
	

	
	//public List<RecruitDataBean> queryRecruitDataTest(@Param("name")String name);
}
