package com.jiubo.erp.wzbg.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiubo.erp.common.MessageException;
import com.jiubo.erp.wzbg.bean.LeaveForgetBean;
import com.jiubo.erp.wzbg.dao.LeaveForgetDao;
import com.jiubo.erp.wzbg.service.LeaveForgetService;

/**
 * @version: V1.0
 * @author: DingDong
 * @className: 
 * @description: 忘记打卡证明业务实现层
 * @data: 2019-07-12
 **/
@Service
@Transactional
public class LeaveForgetServiceImpl implements LeaveForgetService {
	
	private final static Logger logger  = LoggerFactory.getLogger(LeaveForgetServiceImpl.class);
	
	@Autowired
	private LeaveForgetDao leaveForgetDao;
	
	/**
	 * @Description: 查询忘记打卡证明
	 * @param  leaveForgetBean
	 * @return List<LeaveForgetBean>
	 * @author: DingDong
	 * @date: 2019年7月12日
	 * @version: V1.0
	 */
	@Override
	public List<LeaveForgetBean> queryLeaveForgetBean(LeaveForgetBean leaveForgetBean) throws MessageException {
		List<LeaveForgetBean> list;
		try {
			logger.debug("---------------开始查询忘记打卡证明-------------------");
			list = leaveForgetDao.queryLeaveForget(leaveForgetBean);
		} catch (Exception e) {
			throw new MessageException(e.getMessage());
		}
		return list;
	}
	
	/**
	 * @Description: 新增忘记打卡证明
	 * @param  leaveForgetBean
	 * @return List<LeaveForgetBean>
	 * @author: DingDong
	 * @date: 2019年7月13日
	 * @version: V1.0
	 */
	@Override
	public void addLeaveForgetBean(LeaveForgetBean leaveForgetBean) throws MessageException {
		leaveForgetDao.addLeaveForget(leaveForgetBean);
	}

}
