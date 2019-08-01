package com.jiubo.erp.wzbg.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiubo.erp.common.MessageException;
import com.jiubo.erp.wzbg.bean.ComputerBean;
import com.jiubo.erp.wzbg.dao.ComputerDao;
import com.jiubo.erp.wzbg.service.ComputerService;
import com.jiubo.erp.wzbg.vo.PositionInfo;

/**
 * @version: V1.0
 * @author: DingDong
 * @className: ComputerServiceImpl
 * @description: 电脑业务实现层
 * @data: 2019-07-30
 **/
@Service
@Transactional
public class ComputerServiceImpl implements ComputerService{
	
	private final static Logger logger  = LoggerFactory.getLogger(ComputerServiceImpl.class);
	
	@Autowired
	private ComputerDao computerDao;
	
	/**
	 * @Description: 查询电脑预申请
	 * @param  computerBean
	 * @return List<ComputerBean>
	 * @author: DingDong
	 * @date: 2019年7月30日
	 * @version: V1.0
	 */
	@Override
	public List<ComputerBean> queryPreApplication(ComputerBean computerBean) throws MessageException {
		List<ComputerBean> list;
		logger.info("----------开始查询电脑预申请,方法:queryPreApplication----------");
		try {
			list = computerDao.queryPreApplication(computerBean);
			
		} catch (Exception e) {
			throw new MessageException(e.getMessage());
		}
		return list;
	}
	
	/**
	 * @Description: 添加电脑预申请
	 * @param  computerBean
	 * @return 
	 * @author: DingDong
	 * @date: 2019年7月31日
	 * @version: V1.0
	 */
	@Override
	public void addPreApplication(ComputerBean computerBean) throws MessageException {
		logger.info("----------开始添加电脑预申请,方法:addPreApplication----------");
		try {
			computerDao.addPreApplication(computerBean);
		} catch (Exception e) {
			throw new MessageException(e.getMessage());
		}
	}
	
	/**
	 * @Description: 查询岗位信息
	 * @param  
	 * @return 
	 * @author: DingDong
	 * @date: 2019年7月31日
	 * @version: V1.0
	 */
	@Override
	public List<PositionInfo> queryPositionInfo() throws MessageException {
		logger.info("----------开始查询岗位信息,方法:queryPositionInfo----------");
		List<PositionInfo> list;
		try {
			list = computerDao.queryPositionInfo();
		} catch (Exception e) {
			throw new MessageException(e.getMessage());
		}
		return list;
	}

}
