package com.jiubo.erp.wzbg.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.jiubo.erp.common.MapUtil;
import com.jiubo.erp.common.MessageException;
import com.jiubo.erp.common.TimeUtil;
import com.jiubo.erp.wzbg.bean.OfficeNameBean;
import com.jiubo.erp.wzbg.bean.OfficeSuppliesDataBean;
import com.jiubo.erp.wzbg.bean.SpecificationBean;
import com.jiubo.erp.wzbg.dao.OfficeDao;
import com.jiubo.erp.wzbg.service.OfficeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @desc:
 * @date: 2019-07-12 15:23
 * @author: dx
 * @version: 1.0
 */
@Service
@Transactional
public class OfficeServiceImpl implements OfficeService {

    @Autowired
    private OfficeDao wzbgDao;

    @Override
    public List<OfficeSuppliesDataBean> queryOfficeSuppliesData(OfficeSuppliesDataBean officeSuppliesDataBean) throws MessageException {
        if (StringUtils.isBlank(officeSuppliesDataBean.getMonth())) throw new MessageException("查询时间不能为空!");
        try {
            officeSuppliesDataBean.setMonth(TimeUtil.getYearMonthStr(TimeUtil.parseAnyDate(officeSuppliesDataBean.getMonth())));
        } catch (ParseException e) {
            e.printStackTrace();
            throw new MessageException("系统异常!");
        }
        List<OfficeSuppliesDataBean> officeSuppliesDataBeans = wzbgDao.queryOfficeSuppliesData(officeSuppliesDataBean);
        /*for (OfficeSuppliesDataBean suppliesDataBean : officeSuppliesDataBeans) {
			SpecificationBean spec = new SpecificationBean();
			spec.setNameId(suppliesDataBean.getOfficeId());
			//该办公用品的所有规格
			suppliesDataBean.setSpecificationList(wzbgDao.querySpecification(spec));
		}*/
        return officeSuppliesDataBeans;
    }

    @Override
    public List<Map<String, Object>> queryOfficeNames() throws MessageException {
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        List<OfficeNameBean> officeNameBeans = wzbgDao.queryOfficeName(null);
        for (OfficeNameBean officeNameBean : officeNameBeans) {
            Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put("id", officeNameBean.getId());
            dataMap.put("name", officeNameBean.getName());
            SpecificationBean spec = new SpecificationBean();
            spec.setNameId(officeNameBean.getId());
            dataMap.put("specification", wzbgDao.querySpecification(spec));
            dataList.add(dataMap);
        }
        return dataList;
    }

    @Override
    public void addUpdateOfficeSupplies(Map<String, Object> params) throws MessageException {
        try {
            String param = MapUtil.getStringIgnoreCase(params, "officeInfo", MapUtil.NOT_NULL);
            List<OfficeSuppliesDataBean> officeList = JSONObject.parseArray(param, OfficeSuppliesDataBean.class);
            wzbgDao.addUpdateOfficeSupplies(officeList);
        } catch (Exception e) {
            e.printStackTrace();
            throw new MessageException(e.getMessage());
        }
    }
}
