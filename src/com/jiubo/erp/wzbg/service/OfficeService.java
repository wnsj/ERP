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

    /* *
     * @desc:新增，修改办公用品
     * @author: dx
     * @date: 2019-07-12 17:54:34
     * @param params :
     * @return: void
     * @throws:
     * @version: 1.0
     **/
    public void addUpdateOfficeSupplies(Map<String, Object> params) throws MessageException;

    /* *
     * @desc:判断当月是否已经汇总（已经汇总返回9999，没有返回0000）
     * @author: dx
     * @date: 2019-07-16 10:01:54
     * @param params :
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @throws:
     * @version: 1.0
     **/
    public Map<String, Object> isHuiZong(Map<String, Object> params) throws MessageException;

    /* *
     * @desc:审核接口
     * @author: dx
     * @date: 2019-07-15 16:36:30
     * @param params :
     * @return: void
     * @throws:
     * @version: 1.0
     **/
    public void shenHeOfficeSupplies(Map<String, Object> params) throws MessageException;

    /* *
     * @desc:办公用品汇总
     * @author: dx
     * @date: 2019-07-17 09:47:14
     * @param params :
     * @return: java.util.List<com.jiubo.erp.wzbg.bean.OfficeSuppliesDataBean>
     * @throws:
     * @version: 1.0
     **/
    public Map<String,Object> gatherOfficeSupplies(Map<String, Object> params) throws MessageException;

    /* *
     * @desc:获取意见给出人
     * @author: dx
     * @date: 2019-07-17 10:15:34
     * @param params :
     * 参数最外层为一个json对象，里面为一个json数组（均为固定key）
     * 例：{params:[{deptName:"总经办",postName:"副总经理",key:"自定义返回key"}]}
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @throws:
     * @version: 1.0
     **/
    public Map<String,Object> queryAdvancePeo(Map<String, Object> params) throws MessageException;

    /* *
     * @desc:查询审核意见
     * @author: dx
     * @date: 2019-07-17 13:59:20
     * @param params :
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @throws:
     * @version: 1.0
     **/
    public Map<String,Object> queryAdvance(Map<String, Object> params) throws MessageException;

    /* *
     * @desc:提交，审核
     * @author: dx
     * @date: 2019-07-17 16:33:34
     * @param params :
     * @return: void
     * @throws:
     * @version: 1.0
     **/
    public void commitAndSheHe(Map<String, Object> params) throws MessageException;
    
    /* *
     * @desc:
     * @author: dx
     * @date: 2019-07-18 09:59:45
     * @param params :
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @throws:
     * @version: 1.0
     **/
    public List<Map<String, Object>> queryDeptConscientious(Map<String, Object> params) throws MessageException;
}
