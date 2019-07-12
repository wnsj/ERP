package com.jiubo.erp.wzbg.service;

import com.jiubo.erp.common.MessageException;
import com.jiubo.erp.wzbg.bean.OfficeSuppliesDataBean;

import java.util.List;
import java.util.Map;

/**
 * @desc:
 * @date: 2019-07-12 15:22
 * @author: dx
 * @version: 1.0
 */
public interface OfficeService {

    /* *
     * @desc:
     * @author: dx
     * @date: 2019-07-09 09:27:09
     * @param requestMap :
     * @return: java.util.List<com.jiubo.erp.wzbg.bean.OfficeSuppliesDataBean>
     * @throws:
     * @version: 1.0
     **/
    public List<OfficeSuppliesDataBean> queryOfficeSuppliesData(OfficeSuppliesDataBean officeSuppliesDataBean) throws MessageException;

    /* *
     * @desc:所有办公用品及规格
     * @author: dx
     * @date: 2019-07-12 09:30:52
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @throws:
     * @version: 1.0
     **/
    public List<Map<String, Object>> queryOfficeNames() throws MessageException;

    public void addUpdateOfficeSupplies(Map<String,Object> params)throws MessageException;
}
