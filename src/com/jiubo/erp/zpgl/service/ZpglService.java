package com.jiubo.erp.zpgl.service;

import java.util.List;

import com.jiubo.erp.common.MessageException;
import com.jiubo.erp.zpgl.bean.RecruitChannelBean;
import com.jiubo.erp.zpgl.bean.RecruitDataBean;

public interface ZpglService {
	
	/**
	 * @desc:查询招聘渠道
	 * @param:
	 * @return: List<RecruitChannelBean>
	 * @Create at: 2019-05-09
	 * @author:  dx
	 * @version: 1.0
	 */
	public List<RecruitChannelBean> queryRecruitChannel()throws MessageException;
	
	/**
	 * @desc:删除招聘渠道
	 * @param:
	 * @return: void
	 * @Create at: 2019-05-09
	 * @author:  dx
	 * @version: 1.0
	 */
	public void deleteRecruitChannel(String id)throws MessageException;
	
	/**
	 * @desc:查询面试信息
	 * @param:
	 * @return: List<RecruitDataBean>
	 * @Create at: 2019-05-09
	 * @author:  dx
	 * @version: 1.0
	 */
	public List<RecruitDataBean> queryRecruitData(RecruitDataBean recruitDataBean)throws MessageException;
	
	/**
	 * @desc:添加面试信息
	 * @param:
	 * @return: void
	 * @Create at: 2019-05-10
	 * @author:  dx
	 * @version: 1.0
	 */
	public void addRecruitData(RecruitDataBean recruitDataBean)throws MessageException;
	
	/**
	 * @desc:修改面试信息
	 * @param:
	 * @return: void
	 * @Create at: 2019-05-09
	 * @author:  dx
	 * @version: 1.0
	 */
	public void updateRecruitData(RecruitDataBean recruitDataBean)throws MessageException;
	
	/**
	 * @desc:删除面试信息（逻辑删除）
	 * @param:
	 * @return: void
	 * @Create at: 2019-05-09
	 * @author:  dx
	 * @version: 1.0
	 */
	public void updateRecruitData(String id)throws MessageException;
	
	public void test()throws MessageException;
}
