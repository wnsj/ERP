package com.jiubo.erp.wzbg.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.jiubo.erp.common.MessageException;
import com.jiubo.erp.wzbg.bean.LeavePrepareBean;
import com.jiubo.erp.wzbg.bean.OfficeSuppliesDataBean;
import com.jiubo.erp.wzbg.vo.AccWithApprovalLeaveAuth;
import com.jiubo.erp.wzbg.vo.DeptWithEmp;

/**
 * @version: V1.0
 * @author: DingDong
 * @className: WzbgService
 * @description: 无纸化办公业务层
 * @data: 2019-06-29
 **/
public interface WzbgService {

    /**
     * @param String id(部门id)
     * @return List<DeptWithEmp>
     * @Description: 查询部门下的员工姓名以及ERP账户信息
     * @author: DingDong
     * @date: 2019年7月1日
     * @version: V1.0
     */
    public List<DeptWithEmp> queryEmpInfoByDept(String id) throws MessageException;

    /**
     * @param leavePrepareBean(请假报备实体)
     * @return
     * @Description: 添加请假报备
     * @author: DingDong
     * @date: 2019年06月29日
     * @version: V1.0
     */
    public void addLeavePrepareBean(LeavePrepareBean leavePrepareBean) throws MessageException;

    /**
     * @param
     * @return
     * @Description: 查询报备审批权限账户信息
     * @author: DingDong
     * @date: 2019年07月2日
     * @version: V1.0
     */
    public List<AccWithApprovalLeaveAuth> queryApprovalAuthAccount() throws MessageException;

    /**
     * @param leavePrepareBean(请假报备实体)
     * @return
     * @Description: 查询请假报备
     * @author: DingDong
     * @date: 2019年06月29日
     * @version: V1.0
     */
    public List<LeavePrepareBean> queryLeavePrepareBean(LeavePrepareBean leavePrepareBean) throws MessageException;

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
