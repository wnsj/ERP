package com.jiubo.erp.wzbg.vo;


/**
 * @version: V1.0
 * @author: DingDong
 * @className: AccWithApprovalLeaveAuth
 * @description: 请假报备审批权限账户信息
 * @data: 2019-07-02
 **/
public class AccWithApprovalLeaveAuth {
	private String accountID; // 账户ID
	private String accountName;// 账户名
	
	public String getAccountID() {
		return accountID;
	}
	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	@Override
	public String toString() {
		return "AccWithApprovalLeaveAuth [accountID=" + accountID + ", accountName=" + accountName + "]";
	}
	
}
