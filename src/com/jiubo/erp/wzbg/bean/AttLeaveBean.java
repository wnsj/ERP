package com.jiubo.erp.wzbg.bean;

import java.io.Serializable;

//请假信息
public class AttLeaveBean implements Serializable{

	private static final long serialVersionUID = -1632154974077532588L;
	private String id;
	private String userId;//用户ID
	private String jobNum;//工号
	private String leaveTypeId;//请假类别
	private String startTime;//开始时间
	private String endTime;//结束时间
	private String totleTime;//总计时间
	private String totleDate;//总计天数
	private String reason;//请假事由
	private String appliCant;//申请人
	private String appliDate;//申请时间
	private String verifier;//审核人
	private String verifyDate;//审核时间
	private String status;//审核状态
	private String isDelete;//是否有效
	private String createDate;//创建时间
	private String creator;//创建者

	/*非数据库字段*/
	private String empName;//员工姓名
	private String leaveName;//假期名
	private String begDate;
	private String endDate;
	private String deptName;//部门名
	private String creatorName;//创建人姓名
	private String creatorDeptName;//创建人部门
	
	
	public AttLeaveBean() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getJobNum() {
		return jobNum;
	}
	public void setJobNum(String jobNum) {
		this.jobNum = jobNum;
	}
	public String getLeaveTypeId() {
		return leaveTypeId;
	}
	public void setLeaveTypeId(String leaveTypeId) {
		this.leaveTypeId = leaveTypeId;
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
	public String getTotleTime() {
		return totleTime;
	}
	public void setTotleTime(String totleTime) {
		this.totleTime = totleTime;
	}
	public String getTotleDate() {
		return totleDate;
	}
	public void setTotleDate(String totleDate) {
		this.totleDate = totleDate;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getAppliCant() {
		return appliCant;
	}
	public void setAppliCant(String appliCant) {
		this.appliCant = appliCant;
	}
	public String getAppliDate() {
		return appliDate;
	}
	public void setAppliDate(String appliDate) {
		this.appliDate = appliDate;
	}
	public String getVerifier() {
		return verifier;
	}
	public void setVerifier(String verifier) {
		this.verifier = verifier;
	}
	public String getVerifyDate() {
		return verifyDate;
	}
	public void setVerifyDate(String verifyDate) {
		this.verifyDate = verifyDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getLeaveName() {
		return leaveName;
	}
	public void setLeaveName(String leaveName) {
		this.leaveName = leaveName;
	}
	public String getBegDate() {
		return begDate;
	}
	public void setBegDate(String begDate) {
		this.begDate = begDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "AttLeaveBean [id=" + id + ", userId=" + userId + ", jobNum=" + jobNum + ", leaveTypeId=" + leaveTypeId
				+ ", startTime=" + startTime + ", endTime=" + endTime + ", totleTime=" + totleTime + ", totleDate="
				+ totleDate + ", reason=" + reason + ", appliCant=" + appliCant + ", appliDate=" + appliDate
				+ ", verifier=" + verifier + ", verifyDate=" + verifyDate + ", status=" + status + ", isDelete="
				+ isDelete + ", createDate=" + createDate + ", creator=" + creator + ", empName=" + empName
				+ ", leaveName=" + leaveName + ", begDate=" + begDate + ", endDate=" + endDate + "]";
	}
}
/*
 SELECT A.ID, A.USERID, A.JOBNUM,C.NAME EMPNAME, A.LEAVETYPEID,B.NAME LEAVENAME,C.DEPARTMENT_ID,D.NAME DEPTNAME,A.STARTTIME, A.ENDTIME, A.TOTLETIME, 
A.TOTLEDATE, A.REASON, A.APPLICANT, A.APPLIDATE, A.VERIFIER, A.VERIFYDATE, A.STATUS, A.ISDELETE, A.CREATEDATE, A.CREATOR
FROM T_ATT_LEAVE A,T_ATT_LEAVETYPE B,T_EMPLOYEE_BASIC C,T_DEPARTMENT D
WHERE A.LEAVETYPEID = B.ID AND A.ISDELETE = 0 AND A.USERID = C.ACCOUNT AND C.DEPARTMENT_ID = D.ID
AND C.DEPARTMENT_ID = 
AND C.NAME LIKE 
AND C.DEPARTMENT_ID = 
 */
/*
SELECT 
	SUM(B.MM_1) MM_1,SUM(B.MM_2) MM_2,SUM(B.MM_3) MM_3,SUM(B.MM_4) MM_4,
	SUM(B.MM_5) MM_5,SUM(B.MM_6) MM_6,SUM(B.MM_7) MM_7,SUM(B.MM_8) MM_8,
	SUM(B.MM_9) MM_9,SUM(B.MM_10) MM_10,SUM(B.MM_11) MM_11,SUM(B.MM_12) MM_12,
	SUM(B.MM_13) MM_13,SUM(B.MM_14) MM_14,SUM(B.MM_15) MM_15,SUM(B.MM_16) MM_16,
	SUM(B.MM_17) MM_17,SUM(B.MM_18) MM_18,SUM(B.MM_19) MM_19,SUM(B.MM_20) MM_20,
	SUM(B.MM_21) MM_21,
	(SELECT COUNT(1) FROM T_EMPLOYEE_BASIC A WHERE STATE = 1) SUMCOUNT
FROM (
	SELECT 
	   	CASE WHEN (DATEDIFF(MM,A.ENTRYDATE,'2019-01-31') >=1 AND DATEDIFF(MM,A.ENTRYDATE,'2019-01-31') <=3) THEN 1 ELSE 0 END MM_1,
	  	CASE WHEN (DATEDIFF(MM,A.ENTRYDATE,'2019-01-31') >=4 AND DATEDIFF(MM,A.ENTRYDATE,'2019-01-31') <=6) THEN 1 ELSE 0 END MM_2,
	  	CASE WHEN (DATEDIFF(MM,A.ENTRYDATE,'2019-01-31') >=7 AND DATEDIFF(MM,A.ENTRYDATE,'2019-01-31') <=12) THEN 1 ELSE 0 END MM_3,
	  	CASE WHEN (DATEDIFF(MM,A.ENTRYDATE,'2019-01-31') >=13 AND DATEDIFF(MM,A.ENTRYDATE,'2019-01-31') <=23) THEN 1 ELSE 0 END MM_4,
	  	CASE WHEN (DATEDIFF(MM,A.ENTRYDATE,'2019-01-31') >=24 AND DATEDIFF(MM,A.ENTRYDATE,'2019-01-31') <=35) THEN 1 ELSE 0 END MM_5,
	  	CASE WHEN (DATEDIFF(MM,A.ENTRYDATE,'2019-01-31') >=36) THEN 6 ELSE 0 END MM_6,
	  	DATEDIFF(MM,A.ENTRYDATE,'2019-01-31') MM_15,
	  	
	  	CASE WHEN SEX = '男'THEN 1 ELSE 0 END MM_7,
	  	CASE WHEN SEX = '女'THEN 1 ELSE 0 END MM_8,
	  	
	  	CASE WHEN (DATEDIFF(YEAR,A.BIRTH,'2019-01-31') >=1 AND DATEDIFF(YEAR,A.BIRTH,'2019-01-31') <=20) THEN 1 ELSE 0 END MM_9,
	  	CASE WHEN (DATEDIFF(YEAR,A.BIRTH,'2019-01-31') >=21 AND DATEDIFF(YEAR,A.BIRTH,'2019-01-31') <=25) THEN 1 ELSE 0 END MM_10,
	  	CASE WHEN (DATEDIFF(YEAR,A.BIRTH,'2019-01-31') >=26 AND DATEDIFF(YEAR,A.BIRTH,'2019-01-31') <=30) THEN 1 ELSE 0 END MM_11,
	  	CASE WHEN (DATEDIFF(YEAR,A.BIRTH,'2019-01-31') >=31 AND DATEDIFF(YEAR,A.BIRTH,'2019-01-31') <=35) THEN 1 ELSE 0 END MM_12,
	  	CASE WHEN (DATEDIFF(YEAR,A.BIRTH,'2019-01-31') >=36 AND DATEDIFF(YEAR,A.BIRTH,'2019-01-31') <=40) THEN 1 ELSE 0 END MM_13,
	  	CASE WHEN (DATEDIFF(YEAR,A.BIRTH,'2019-01-31') > 40) THEN 1 ELSE 0 END MM_14,
	  	DATEDIFF(YEAR,A.BIRTH,'2019-01-31') MM_16,
	  	
	  	CASE WHEN C.EDUCATION = '初中及以下' THEN 1 ELSE 0 END MM_17,
	  	CASE WHEN C.EDUCATION = '高中/中专' THEN 1 ELSE 0 END MM_18,
	  	CASE WHEN C.EDUCATION = '专科' THEN 1 ELSE 0 END MM_19,
	  	CASE WHEN C.EDUCATION = '本科' OR C.EDUCATION = '硕士研究生'OR C.EDUCATION = '博士' THEN 1 ELSE 0 END MM_20,
	  	CASE WHEN C.EDUCATION IS NULL OR C.EDUCATION = '' THEN 1 ELSE 0 END MM_21,C.EDUCATION
	FROM T_EMPLOYEE_BASIC A,T_EMPLOYEE_DETIAL C
	WHERE A.STATE = 1 AND A.ID = C.EMPLOYEE_BASIC_ID
) B
 */
