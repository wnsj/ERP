package com.jiubo.erp.erpLogin.service.impl;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.jiubo.erp.common.CookieTools;
import com.jiubo.erp.common.MessageException;
import com.jiubo.erp.erpLogin.bean.RuleDataBean;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.jiubo.erp.erpLogin.bean.AccountDataBean;
import com.jiubo.erp.erpLogin.dao.LoginDAO;
import com.jiubo.erp.erpLogin.service.UserService;
import com.jiubo.erp.erpLogin.util.Md5Util;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Service
@Transactional
public class UserServiceimpl implements UserService {

    @Autowired
    private LoginDAO dao;

    @Value("${tokenLife}")
    private int tokenLife;

    @Value("${accountLife}")
    private int accountLife;

//    public String getTokenLife() {
//        return tokenLife;
//    }
//
//    public void setTokenLife(String tokenLife) {
//        this.tokenLife = tokenLife;
//    }
//
//    public String getAccountLife() {
//        return accountLife;
//    }
//
//    public void setAccountLife(String accountLife) {
//        this.accountLife = accountLife;
//    }

    @Override
    public JSONObject login(AccountDataBean bean) throws MessageException{
        JSONObject jsonObject = new JSONObject();
        String pwd = Md5Util.md5Encrypt32Lower(bean.getAccount_Pwd());
        System.out.println("pwd:"+pwd);
        bean.setAccount_Pwd(pwd);
        AccountDataBean accountDataBean = dao.userLogin(bean);
        if (accountDataBean == null)throw new MessageException("账号或密码错误!");
        if ("停用".equals(accountDataBean.getAccount_State()))throw new MessageException("该账号已被停用!");
        jsonObject.put("account",accountDataBean);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        //String token = (String) request.getSession(true).getAttribute("Access-Token");
        String token = Md5Util.md5Encrypt32Lower(accountDataBean.getAccount_Name()+accountDataBean.getAccount_Pwd());
        accountDataBean.setAccount_Pwd("");
        request.getSession().setAttribute(token,accountDataBean);
        jsonObject.put("accessToken",token);
        jsonObject.put("permission",queryRuleData(accountDataBean.getAccount_ID()));
//        System.out.println("tokenLife:"+tokenLife+"##accountLife:"+accountLife);
//        CookieTools.addCookie(response,"accessToken",token,StringUtils.isBlank(tokenLife) ? 0 :Integer.parseInt(tokenLife));
//        CookieTools.addCookie(response,"accountData","16515", StringUtils.isBlank(accountLife) ? 0 :Integer.parseInt(accountLife));
        CookieTools.addCookie(response,"accessToken",token,tokenLife);
        CookieTools.addCookie(response,"accountData","16515", accountLife);
        return jsonObject;
    }

    @Override
    public List<RuleDataBean> queryRuleData(String accountId) throws MessageException {
        if (StringUtils.isBlank(accountId))throw new MessageException("账户id不能为空!");
        return dao.queryRuleData(accountId);
    }
}

    /*
     * Erp用户注册
     * 默认测试账号:admin1
     * 密码:a123456
    public Result UserRegister(AccountDataBean input) throws Exception {
        Result result = new Result<>();
        if (dao.selectByUserName(input) == null) {
            this.dao.addAccountData(input);
            result.setMessage("注册成功");
            result.setStatus("1");
        } else {
            result.setMessage("用户名已存在");
            result.setStatus("0");
        }
        return result;
    }
       */

    /*
     * 名称:ERP系统登录
     * 参数::用户名username 密码password
     * 返回值:LoginOutput

    @Override
    @Transactional
    public LoginOutput Erplogin(LoginInput input) throws Exception {
        LoginOutput in = new LoginOutput();
        AccountDataBean account = new AccountDataBean();
        if (StringUtils.isEmpty(input.getUsername()) || StringUtils.isEmpty(input.getPassword())) {
            throw new Exception("must not be null");
        }
        account.setAccount_Name(input.getUsername());
        account.setAccount_Pwd(Md5Util.md5Encrypt32Lower(input.getPassword()));
        in = dao.userLogin(account);
        if (in != null) {
            //判断权限信息
            List<PositionInfoOutPut> posits = dao.selectPositionInfoByAccoutname(account);  //根据用户名查询其权限信息
            if (!posits.isEmpty()) {
                for (PositionInfoOutPut posit : posits) {
                    if (StringUtils.isNotEmpty(posit.getPosition_Name())) {
                        if (posit.getPosition_Name().equals(Position.SUPERADMIN) || posit.getPosition_Name().equals(Position.FUZONG) || posit.getPosition_Name().equals(Position.XINGZHENG)) {
                            in.setStatus("1");
                        }
                        if (posit.getPosition_Name().equals(Position.YHZZH) || posit.getPosition_Name().equals(Position.ZXZG) || posit.getPosition_Name().equals(Position.YYXMZG)) {
                            in.setStatus("0");
                        }
                    }
                }
            }
            in.setStatus("1");
            return in;
        } else {
            return null;
        }
    }
     */
