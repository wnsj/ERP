package com.jiubo.erp.ryxxl.bean;

import java.io.Serializable;
import java.util.List;

import com.jiubo.erp.kqgl.bean.PositionDataBean;
//人力资源利用率分析表
public class DepartmentBean implements Serializable{
	private static final long serialVersionUID = 5823334656834540120L;
	private String ID; //编号
	private String Name; //名称
	private String ParentID; //父级编号
	private String OrderNum; //顺序号
	private List<DepartmentBean> children;//子部门
	private int zongRenShu;//
	private int xiaoJi;
	private int clockCount;
	private int unClockCount;
	private int days;
	private double avgChuQin;
	private int vacation;
	private double queQin;
	private double chaoQueQin;
	private double weiLiYong;
	private String liYong;
}
