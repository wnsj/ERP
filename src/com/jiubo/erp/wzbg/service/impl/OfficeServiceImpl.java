package com.jiubo.erp.wzbg.service.impl;

import com.alibaba.fastjson.JSONArray;
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
        String param = null;
        String delParam = null;
        try {
            param = MapUtil.getStringIgnoreCase(params, "officeInfo", MapUtil.ALLOW_NULL);
            delParam = MapUtil.getStringIgnoreCase(params, "delOffice", MapUtil.ALLOW_NULL);
        } catch (Exception e) {
            e.printStackTrace();
            throw new MessageException(e.getMessage());
        }
        List<OfficeSuppliesDataBean> officeList = null;
        if (StringUtils.isNotBlank(param)) {
            officeList = JSONObject.parseArray(param, OfficeSuppliesDataBean.class);
            int count = wzbgDao.addUpdateOfficeSupplies(officeList);
        }
        if (StringUtils.isNotBlank(delParam)) {
            officeList = JSONObject.parseArray(delParam, OfficeSuppliesDataBean.class);
            wzbgDao.deleteOfficeSupplies(officeList);
        }
    }

    @Override
    public Map<String, Object> isHuiZong(Map<String, Object> params) throws MessageException {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        OfficeSuppliesDataBean office = new OfficeSuppliesDataBean();
        try {
            dataMap.put("isHuiZong", "0000");
            String month = MapUtil.getStringIgnoreCase(params, "month", MapUtil.NOT_NULL);
            office.setMonth(TimeUtil.getYearMonthStr(TimeUtil.parseAnyDate(month)));
        } catch (Exception e) {
            e.printStackTrace();
            throw new MessageException(e.getMessage());
        }
        List<OfficeSuppliesDataBean> officeSuppliesDataBeanList = wzbgDao.getOfficeSuppliesData(office);
        //if (officeSuppliesDataBeanList.size() <= 0) throw new MessageException("数据异常!");
        for (OfficeSuppliesDataBean officeSuppliesDataBean : officeSuppliesDataBeanList) {
            //判断本月是否已经汇总
            if (StringUtils.isNotBlank(officeSuppliesDataBean.getIsTiJiao())) {
                dataMap.put("isHuiZong", "9999");
                return dataMap;
            }
        }

        return dataMap;
    }

    @Override
    public void shenHeOfficeSupplies(Map<String, Object> params) throws MessageException {
        OfficeSuppliesDataBean office = new OfficeSuppliesDataBean();
        List<OfficeSuppliesDataBean> officeList = null;
        try {
            String month = MapUtil.getStringIgnoreCase(params, "month", MapUtil.NOT_NULL);
            String param = MapUtil.getStringIgnoreCase(params, "officeInfo", MapUtil.NOT_NULL);
            officeList = JSONObject.parseArray(param, OfficeSuppliesDataBean.class);
            if (officeList.size() <= 0) throw new MessageException("数据异常不可审核!");
            office.setMonth(TimeUtil.getYearMonthStr(TimeUtil.parseAnyDate(month)));
        } catch (Exception e) {
            e.printStackTrace();
            throw new MessageException(e.getMessage());
        }
        List<OfficeSuppliesDataBean> officeSuppliesDataBeanList = wzbgDao.getOfficeSuppliesData(office);
        //if (officeSuppliesDataBeanList.size() <= 0) throw new MessageException("数据异常不可审核!");
        for (int i = 0; i < officeSuppliesDataBeanList.size(); i++) {
            OfficeSuppliesDataBean officeSuppliesDataBean = officeSuppliesDataBeanList.get(i);
            //判断本月是否已经汇总
            if (StringUtils.isNotBlank(officeSuppliesDataBean.getIsTiJiao()))
                throw new MessageException("本月已汇总不可审核!");
            //判断审核的数据是否已经审核过
            for (int j = 0; j < officeList.size(); j++) {
                OfficeSuppliesDataBean dataBean = officeList.get(j);
                //同一条数据的审核意见（1：同意,2:不同意,3:未审核）
                if (officeSuppliesDataBean.getId().equals(dataBean.getId()) && !"3".equals(officeSuppliesDataBean.getAdvice2())) {
                    throw new MessageException("该数据已审核，不可重复审核!");
                }
            }
        }
        wzbgDao.addUpdateOfficeSupplies(officeList);
    }

    @Override
    public Map<String, Object> gatherOfficeSupplies(Map<String, Object> params) throws MessageException {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        OfficeSuppliesDataBean office = new OfficeSuppliesDataBean();
        try {
            String month = MapUtil.getStringIgnoreCase(params, "month", MapUtil.NOT_NULL);
            office.setMonth(TimeUtil.getYearMonthStr(TimeUtil.parseAnyDate(month)));
        } catch (Exception e) {
            e.printStackTrace();
            throw new MessageException(e.getMessage());
        }
        dataMap.put("gatherOffice", wzbgDao.gatherOfficeSupplies(office));
        Map<String, Object> advMap = new HashMap<String, Object>();
        List<Map<String, Object>> advList = wzbgDao.queryAdvance(office);
        if (advList.size() > 0) advMap = advList.get(0);
        dataMap.put("advance", advMap);
        return dataMap;
    }

    @Override
    public Map<String, Object> queryAdvancePeo(Map<String, Object> params) throws MessageException {
        Map<String, Object> data = new HashMap<String, Object>();
        String param = null;
        try {
            param = MapUtil.getStringIgnoreCase(params, "params", MapUtil.NOT_NULL);
        } catch (Exception e) {
            e.printStackTrace();
            throw new MessageException(e.getMessage());
        }
        JSONArray jsonArray = JSONObject.parseArray(param);
        if (jsonArray == null || jsonArray.size() == 0) throw new MessageException("参数不能为空!");
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject json = jsonArray.getJSONObject(i);
            data.put(json.getString("key"), wzbgDao.queryAdvancePeo(json));
        }
        return data;
    }

    @Override
    public Map<String, Object> queryAdvance(Map<String, Object> params) throws MessageException {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("advance", "");
        OfficeSuppliesDataBean office = new OfficeSuppliesDataBean();
        try {
            String month = MapUtil.getStringIgnoreCase(params, "month", MapUtil.NOT_NULL);
            office.setMonth(TimeUtil.getYearMonthStr(TimeUtil.parseAnyDate(month)));
        } catch (Exception e) {
            e.printStackTrace();
            throw new MessageException(e.getMessage());
        }
        Map<String, Object> advMap = new HashMap<String, Object>();
        List<Map<String, Object>> advList = wzbgDao.queryAdvance(office);
        if (advList.size() > 0) advMap = advList.get(0);
        dataMap.put("advance", advMap);
        return dataMap;
    }

    @Override
    public void commitAndSheHe(Map<String, Object> params) throws MessageException {
        String param = null;
        List<OfficeSuppliesDataBean> officeList = null;
        try {
            //if (StringUtils.isBlank(officeSuppliesDataBean.getMonth()))throw new MessageException("时间不能为空！");
            param = MapUtil.getStringIgnoreCase(params, "officeInfo", MapUtil.NOT_NULL);
            officeList = JSONObject.parseArray(param, OfficeSuppliesDataBean.class);
            if (officeList.size() <= 0) throw new MessageException("数据异常!");
            for (OfficeSuppliesDataBean officeSuppliesDataBean : officeList) {
                officeSuppliesDataBean.setMonth(TimeUtil.getYearMonthStr(TimeUtil.parseAnyDate(officeSuppliesDataBean.getMonth())));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new MessageException(e.getMessage());
        }
        wzbgDao.commitAndSheHe(officeList);
    }

    @Override
    public List<Map<String, Object>> queryDeptConscientious(Map<String, Object> params) throws MessageException {
        String deptId = null;
        String level = null;
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        try {
            deptId = MapUtil.getStringIgnoreCase(params, "deptId", MapUtil.NOT_NULL);
            level = MapUtil.getStringIgnoreCase(params, "level", MapUtil.ALLOW_NULL);
        } catch (Exception e) {
            e.printStackTrace();
            throw new MessageException(e.getMessage());
        }
        if (StringUtils.isNotBlank(level)) {
            //组长
            mapList = wzbgDao.queryDeptConscientious(3, deptId);
        } else {
            //主管及组长
            for (int i = 0; i < 3; i++) {
                if (wzbgDao.queryDeptLevel(i, deptId) > 0) {
                    mapList = wzbgDao.queryDeptConscientious(i, deptId);
                }
            }
        }
        return mapList;
    }
}
