package com.jiubo.erp.wzbg.dao;

import com.jiubo.erp.wzbg.bean.OfficeNameBean;
import com.jiubo.erp.wzbg.bean.OfficeSuppliesDataBean;
import com.jiubo.erp.wzbg.bean.SpecificationBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @desc:
 * @date: 2019-07-12 15:25
 * @author: dx
 * @version: 1.0
 */
public interface OfficeDao {
    //办公用品管理查询接口
    public List<OfficeSuppliesDataBean> queryOfficeSuppliesData(OfficeSuppliesDataBean officeSuppliesDataBean);

    //办公用品名
    public List<OfficeNameBean> queryOfficeName(OfficeNameBean officeNameBean);

    //办公用品规格
    public List<SpecificationBean> querySpecification(SpecificationBean specificationBean);

    //添加或修改办公用品信息
    public void addUpdateOfficeSupplies(@Param("officeList")List<OfficeSuppliesDataBean> officeList);
}
