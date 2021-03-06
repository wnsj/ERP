package com.jiubo.erp.zpgl.service.impl;

import java.text.ParseException;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiubo.erp.common.MessageException;
import com.jiubo.erp.common.TimeUtil;
import com.jiubo.erp.zpgl.bean.RecruitChannelBean;
import com.jiubo.erp.zpgl.bean.RecruitDataBean;
import com.jiubo.erp.zpgl.bean.ZpPlanBean;
import com.jiubo.erp.zpgl.bean.ZpPublishBean;
import com.jiubo.erp.zpgl.dao.ZpglDao;
import com.jiubo.erp.zpgl.service.ZpglService;
import com.quicksand.push.ToolClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@Transactional
public class ZpglServiceImpl implements ZpglService {

	private final static Logger logger = LoggerFactory.getLogger(ZpglServiceImpl.class);

    @Autowired
    private ZpglDao zpglDao;

    @Override
    public List<RecruitChannelBean> queryRecruitChannel() throws MessageException {
        return zpglDao.queryRecruitChannel();
    }

    @Override
    public void deleteRecruitChannel(String id) throws MessageException {
        zpglDao.deleteRecruitChannel(id);
    }

    @Override
    public List<RecruitDataBean> queryRecruitData(RecruitDataBean recruitDataBean) throws MessageException {
        if (StringUtils.isNotBlank(recruitDataBean.getDateFlag())) {
            if (StringUtils.isBlank(recruitDataBean.getBegDate()) || StringUtils.isBlank(recruitDataBean.getEndDate())) {
                throw new MessageException("选择了日期类型，查询时间不能为空！");
            } else {
                try {
                    String endDate = TimeUtil.getDateYYYY_MM_DD_HH_MM_SS(TimeUtil.dateAdd(TimeUtil.parseAnyDate(recruitDataBean.getEndDate()), TimeUtil.UNIT_DAY, 1));
                    recruitDataBean.setEndDate(endDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }

		return zpglDao.queryRecruitData(recruitDataBean);
		
	}

	@Override
	public void updateRecruitData(RecruitDataBean recruitDataBean) throws MessageException {
		if(StringUtils.isBlank(recruitDataBean.getId()))throw new MessageException("面试信息id不能为空！");
		if(StringUtils.isBlank(recruitDataBean.getName()))throw new MessageException("姓名不能为空！");
		if(StringUtils.isBlank(recruitDataBean.getChannel()))throw new MessageException("应聘渠道不能为空！");
		if(StringUtils.isBlank(recruitDataBean.getInvitationDate()))throw new MessageException("邀约时间不能为空！");
		if(StringUtils.isBlank(recruitDataBean.getDepartment()))throw new MessageException("应聘部门不能为空！");
		if(StringUtils.isBlank(recruitDataBean.getPosition()))throw new MessageException("应聘职位不能为空！");
		if(StringUtils.isBlank(recruitDataBean.getRecruitDate()))throw new MessageException("面试时间不能为空！");
		zpglDao.updateRecruitData(recruitDataBean);
	}

	@Override
	public void updateRecruitData(String id) throws MessageException {
		zpglDao.updateRecruitDataById(id);
	}

	@Override
	public void test() throws MessageException {
		//测试bind待解决
		//System.out.println(zpglDao.queryRecruitDataTest("亮"));
	}

	@Override
	public void addRecruitData(RecruitDataBean recruitDataBean) throws MessageException {
		if(StringUtils.isBlank(recruitDataBean.getName()))throw new MessageException("姓名不能为空！");
		if(StringUtils.isBlank(recruitDataBean.getChannel()))throw new MessageException("应聘渠道不能为空！");
		if(StringUtils.isBlank(recruitDataBean.getInvitationDate()))throw new MessageException("邀约时间不能为空！");
		if(StringUtils.isBlank(recruitDataBean.getDepartment()))throw new MessageException("应聘部门不能为空！");
		if(StringUtils.isBlank(recruitDataBean.getPosition()))throw new MessageException("应聘职位不能为空！");
		if(StringUtils.isBlank(recruitDataBean.getRecruitDate()))throw new MessageException("面试时间不能为空！");
		
		// 基本信息
		
		// 户口性质
		if("0".equals(recruitDataBean.getAccountProp())) {
			recruitDataBean.setAccountProp("农业");
		}
		if("1".equals(recruitDataBean.getAccountProp())) {
			recruitDataBean.setAccountProp("非农业");
		}
		// 血型
		if("0".equals(recruitDataBean.getBloodType())) {
			recruitDataBean.setBloodType("A");
		}
		if("1".equals(recruitDataBean.getBloodType())) {
			recruitDataBean.setBloodType("B");
		}
		if("2".equals(recruitDataBean.getBloodType())) {
			recruitDataBean.setBloodType("O");
		}
		if("3".equals(recruitDataBean.getBloodType())) {
			recruitDataBean.setBloodType("AB");
		}
		if("4".equals(recruitDataBean.getBloodType())) {
			recruitDataBean.setBloodType("特殊");
		}
		// 婚否
		if("0".equals(recruitDataBean.getMarital())) {
			recruitDataBean.setMarital("未婚");
		}
		if("1".equals(recruitDataBean.getMarital())) {
			recruitDataBean.setMarital("已婚");
		}
		// 政治面貌
		if("0".equals(recruitDataBean.getPloitical())) {
			recruitDataBean.setPloitical("群众");
		}
		if("1".equals(recruitDataBean.getPloitical())) {
			recruitDataBean.setPloitical("团员");
		}
		if("2".equals(recruitDataBean.getPloitical())) {
			recruitDataBean.setPloitical("党员");
		}
		// 性别
		if("0".equals(recruitDataBean.getSex())) {
			recruitDataBean.setSex("女");
		}
		if("1".equals(recruitDataBean.getSex())) {
			recruitDataBean.setSex("男");
		}
		
		// 学历经验
		
		// 学历
		if("0".equals(recruitDataBean.getEducation())) {
			recruitDataBean.setEducation("未知");
		}
		if("1".equals(recruitDataBean.getEducation())) {
			recruitDataBean.setEducation("博士");
		}
		if("2".equals(recruitDataBean.getEducation())) {
			recruitDataBean.setEducation("硕士研究生");
		}
		if("3".equals(recruitDataBean.getEducation())) {
			recruitDataBean.setEducation("本科");
		}
		if("4".equals(recruitDataBean.getEducation())) {
			recruitDataBean.setEducation("专科");
		}
		if("5".equals(recruitDataBean.getEducation())) {
			recruitDataBean.setEducation("高中/中专");
		}
		if("6".equals(recruitDataBean.getEducation())) {
			recruitDataBean.setEducation("初中及以下");
		}
		
		// 是否合格
		if("0".equals(recruitDataBean.getIsQualified())) {
			recruitDataBean.setIsQualified("否");
		}
		if("1".equals(recruitDataBean.getIsQualified())) {
			recruitDataBean.setIsQualified("是");
		}
		if("2".equals(recruitDataBean.getIsQualified())) {
			recruitDataBean.setIsQualified("待定");
		}
		
		recruitDataBean.setUpdateAccount("2143");
		//是否有效（ 0：有效，1：失效）
		recruitDataBean.setIsDelete("0");
		//是否入职（0：未入职 1：在职 2：离职）
		recruitDataBean.setIsEntry("0");
		recruitDataBean.setUpdateDate(ToolClass.inquirNowDateTime());
		
		//logger.info(recruitDataBean.toString());
		System.out.println(recruitDataBean.toString());
		
		zpglDao.addRecruitData(recruitDataBean);
	}

	@Override
	public List<ZpPlanBean> queryZpPlan(ZpPlanBean zpPlanBean) throws MessageException {
		if(StringUtils.isBlank(zpPlanBean.getBegDate()) || StringUtils.isBlank(zpPlanBean.getEndDate()))throw new MessageException("查询时间（begDate或endDate）为空！");
		try {
			zpPlanBean.setEndDate(TimeUtil.getDateYYYY_MM_DD_HH_MM_SS(TimeUtil.dateAdd(TimeUtil.parseAnyDate(zpPlanBean.getEndDate()), TimeUtil.UNIT_DAY, 1)));
		} catch (ParseException e) {
			e.printStackTrace();
		}
        return zpglDao.queryZpPlan(zpPlanBean);
    }

    @Override
    public void addZpPlan(ZpPlanBean zpPlanBean) throws MessageException {
        if (StringUtils.isBlank(zpPlanBean.getPosition())) throw new MessageException("职位不能为空！");
        if (StringUtils.isBlank(zpPlanBean.getPlanDate())) throw new MessageException("计划月份不能为空！");
        zpglDao.addZpPlan(zpPlanBean);
    }

    @Override
    public void deleteZpPlan(String id) throws MessageException {
        zpglDao.deleteZpPlan(id);
    }

    @Override
    public void updateZpPlan(ZpPlanBean zpPlanBean) throws MessageException {
        try {
            if (StringUtils.isBlank(zpPlanBean.getPlanId())) throw new MessageException("招聘信息id为空！");
            zpglDao.updateZpPlan(zpPlanBean);
            logger.debug("----------------招聘计划更新成功--------------------");
        } catch (Exception e) {
            logger.debug("----------------招聘计划更新出错--------------------");
        }
    }

    @Override
    public List<ZpPublishBean> queryZpPublish(ZpPublishBean zpPublishBean) throws MessageException {
        if (StringUtils.isBlank(zpPublishBean.getBegDate()) || StringUtils.isBlank(zpPublishBean.getEndDate()))
            throw new MessageException("查询时间（begDate，endDate）为空！");
        try {
            zpPublishBean.setEndDate(TimeUtil.getDateYYYY_MM_DD_HH_MM_SS(TimeUtil.dateAdd(TimeUtil.parseAnyDate(zpPublishBean.getEndDate()), TimeUtil.UNIT_DAY, 1)));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return zpglDao.queryZpPublish(zpPublishBean);
    }

    @Override
    public void addZpPublish(ZpPublishBean zpPublishBean) throws MessageException {
        if (StringUtils.isBlank(zpPublishBean.getChannel())) throw new MessageException("招聘渠道不能为空！");
        if (StringUtils.isBlank(zpPublishBean.getPosition())) throw new MessageException("招聘职位不能为空！");
        if (StringUtils.isBlank(zpPublishBean.getPublishDate())) throw new MessageException("发布日期不能为空！");
        if (StringUtils.isBlank(zpPublishBean.getPublishNum()) || Integer.parseInt(zpPublishBean.getPublishNum()) <= 0)
            throw new MessageException("发布人数为空或小于等于0！");
        zpglDao.addZpPublish(zpPublishBean);
    }

    @Override
    public void deleteZpPublish(String id) throws MessageException {
        zpglDao.deleteZpPublish(id);
    }

    @Override
    public void updateZpPublish(ZpPublishBean zpPublishBean) throws MessageException {
        if (StringUtils.isBlank(zpPublishBean.getPublishId())) throw new MessageException("招聘信息id不能为空！");
        if (StringUtils.isBlank(zpPublishBean.getChannel())) throw new MessageException("招聘渠道不能为空！");
        if (StringUtils.isBlank(zpPublishBean.getPosition())) throw new MessageException("招聘职位不能为空！");
        if (StringUtils.isBlank(zpPublishBean.getPublishDate())) throw new MessageException("发布日期不能为空！");
        if (StringUtils.isBlank(zpPublishBean.getPublishNum()) || Integer.parseInt(zpPublishBean.getPublishNum()) <= 0)
            throw new MessageException("发布人数为空或小于等于0！");
        zpglDao.updateZpPublish(zpPublishBean);
    }

    @Override
    public void addRecruitChannel(RecruitChannelBean recruitChannelBean) throws MessageException {
        if (StringUtils.isBlank(recruitChannelBean.getRecruitChannelName())) throw new MessageException("招聘渠道名不能为空！");
        zpglDao.addRecruitChannel(recruitChannelBean);
    }

    @Override
    public void updateRecruitChannel(RecruitChannelBean recruitChannelBean) throws MessageException {
        if (StringUtils.isBlank(recruitChannelBean.getRecruitChannelId()) || StringUtils.isBlank(recruitChannelBean.getRecruitChannelName()))
            throw new MessageException("招聘渠道id或 招聘渠道名不能为空！");
        zpglDao.updateRecruitChannel(recruitChannelBean);
    }
}
