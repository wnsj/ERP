package com.jiubo.erp.wzbg.bean;   
/** 
 * @author :    mwl
 * @version:    1.0   
 * @since:      JDK 1.8 
 * Create at:   2019年7月4日 上午10:26:16   
 */
public class AskForLeaveBean {
	
	private static final long serialVersionUID = -1632154974077532588L;
	private int id;
	private String leaveType;
	private String addTime;
	private String leaveAccount;
	private String leaveAccountName;
	private String departId;
	private String departName;
	private String positionId;
	private String positionName;
	private String agentAccount;
	private String agentAccountName;
	private String startTime;
	private String endTime; 	
	private String leaveRemark;
	private String photo;
	private String account1;
	private String accountName1;
	private String time1;
	private String result1; 
	private String remark1;
	private String account2;
	private String accountName2;
	private String time2;
	private String result2; 
	private String remark2;
	private String account3;
	private String accountName3;
	private String time3;
	private String result3 ;
	private String remark3;
	private String account4;
	private String accountName4;
	private String time4;
	private String result4; 
	private String remark4;
	private String updateTime;
	private String isSee;
	private String step;
	private String baobeiId;
	
	public AskForLeaveBean() {
		// TODO Auto-generated constructor stub
	}
	
	

	@Override
	public String toString() {
		return "AskForLeaveBean [id=" + id + ", leaveType=" + leaveType + ", addTime=" + addTime + ", leaveAccount="
				+ leaveAccount + ", leaveAccountName=" + leaveAccountName + ", departId=" + departId + ", departName="
				+ departName + ", positionId=" + positionId + ", positionName=" + positionName + ", agentAccount="
				+ agentAccount + ", agentAccountName=" + agentAccountName + ", startTime=" + startTime + ", endTime="
				+ endTime + ", leaveRemark=" + leaveRemark + ", photo=" + photo + ", account1=" + account1
				+ ", accountName1=" + accountName1 + ", time1=" + time1 + ", result1=" + result1 + ", remark1="
				+ remark1 + ", account2=" + account2 + ", accountName2=" + accountName2 + ", time2=" + time2
				+ ", result2=" + result2 + ", remark2=" + remark2 + ", account3=" + account3 + ", accountName3="
				+ accountName3 + ", time3=" + time3 + ", result3=" + result3 + ", remark3=" + remark3 + ", account4="
				+ account4 + ", accountName4=" + accountName4 + ", time4=" + time4 + ", result4=" + result4
				+ ", remark4=" + remark4 + ", updateTime=" + updateTime + ", isSee=" + isSee + ", step=" + step
				+ ", baobeiId=" + baobeiId + "]";
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPositionId() {
		return positionId;
	}

	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getLeaveAccountName() {
		return leaveAccountName;
	}



	public void setLeaveAccountName(String leaveAccountName) {
		this.leaveAccountName = leaveAccountName;
	}



	public String getAgentAccountName() {
		return agentAccountName;
	}



	public void setAgentAccountName(String agentAccountName) {
		this.agentAccountName = agentAccountName;
	}



	public String getAccountName1() {
		return accountName1;
	}



	public void setAccountName1(String accountName1) {
		this.accountName1 = accountName1;
	}



	public String getAccountName2() {
		return accountName2;
	}



	public void setAccountName2(String accountName2) {
		this.accountName2 = accountName2;
	}



	public String getAccountName3() {
		return accountName3;
	}



	public void setAccountName3(String accountName3) {
		this.accountName3 = accountName3;
	}



	public String getAccountName4() {
		return accountName4;
	}



	public void setAccountName4(String accountName4) {
		this.accountName4 = accountName4;
	}



	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getDepartId() {
		return departId;
	}



	public void setDepartId(String departId) {
		this.departId = departId;
	}



	public String getDepartName() {
		return departName;
	}



	public void setDepartName(String departName) {
		this.departName = departName;
	}



	public String getLeaveAccount() {
		return leaveAccount;
	}

	public void setLeaveAccount(String leaveAccount) {
		this.leaveAccount = leaveAccount;
	}

	public String getAgentAccount() {
		return agentAccount;
	}

	public void setAgentAccount(String agentAccount) {
		this.agentAccount = agentAccount;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getLeaveRemark() {
		return leaveRemark;
	}

	public void setLeaveRemark(String leaveRemark) {
		this.leaveRemark = leaveRemark;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getAccount1() {
		return account1;
	}

	public void setAccount1(String account1) {
		this.account1 = account1;
	}

	public String getTime1() {
		return time1;
	}

	public void setTime1(String time1) {
		this.time1 = time1;
	}

	public String getResult1() {
		return result1;
	}

	public void setResult1(String result1) {
		this.result1 = result1;
	}

	public String getRemark1() {
		return remark1;
	}

	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}

	public String getAccount2() {
		return account2;
	}

	public void setAccount2(String account2) {
		this.account2 = account2;
	}

	public String getTime2() {
		return time2;
	}

	public void setTime2(String time2) {
		this.time2 = time2;
	}

	public String getResult2() {
		return result2;
	}

	public void setResult2(String result2) {
		this.result2 = result2;
	}

	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	public String getAccount3() {
		return account3;
	}

	public void setAccount3(String account3) {
		this.account3 = account3;
	}

	public String getTime3() {
		return time3;
	}

	public void setTime3(String time3) {
		this.time3 = time3;
	}

	public String getResult3() {
		return result3;
	}

	public void setResult3(String result3) {
		this.result3 = result3;
	}

	public String getRemark3() {
		return remark3;
	}

	public void setRemark3(String remark3) {
		this.remark3 = remark3;
	}

	public String getAccount4() {
		return account4;
	}

	public void setAccount4(String account4) {
		this.account4 = account4;
	}

	public String getTime4() {
		return time4;
	}

	public void setTime4(String time4) {
		this.time4 = time4;
	}

	public String getResult4() {
		return result4;
	}

	public void setResult4(String result4) {
		this.result4 = result4;
	}

	public String getRemark4() {
		return remark4;
	}

	public void setRemark4(String remark4) {
		this.remark4 = remark4;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getIsSee() {
		return isSee;
	}

	public void setIsSee(String isSee) {
		this.isSee = isSee;
	}

	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
	}

	public String getBaobeiId() {
		return baobeiId;
	}

	public void setBaobeiId(String baobeiId) {
		this.baobeiId = baobeiId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
  
