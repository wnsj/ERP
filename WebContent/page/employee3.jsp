<%@page import="com.jiubo.erp.rygl.vo.QueryFamilyResult"%>
<%@page import="com.jiubo.erp.rygl.vo.QueryResult"%>
<%@page import="com.jiubo.erp.rygl.bean.DepartmentBean"%>
<%@page import="com.jiubo.erp.rygl.bean.ProjectDataBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%String path=request.getContextPath(); %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
  <!--   <meta charset="UTF-8"> -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="<%=path%>/css/bootstrap.css">
    <link rel="stylesheet" href="<%=path%>/css/employee.css">
    <script type="text/javascript">
</script>
    <title>员工管理</title>
</head>
<body>
    <div class="container user-container" id="employee-app">
        <div class="row">
            <div class="col-md-12 col-lg-12 main-title">
                <h2>人员管理</h2>
            </div>
        </div>
      <form id="ryForm" action="<%=path%>/search/ryList" method="post">
        <div class="row">
            <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
                <div class="col-md-2 col-lg-2" style="padding: 0; line-height: 34px;">
                    <p>项目：</p>
                </div>
                <div class="col-md-10 col-lg-10">
                    <select class="form-control" name="projectId" onchange="">
                        <option value="" selected="selected">全部</option>
                        <%
                        List<ProjectDataBean> prolist=(List<ProjectDataBean>)request.getAttribute("project");
                          for(ProjectDataBean bean:prolist){	 
                        %>
                           <option value="<%=bean.getProjectID()%>"><%=bean.getProjectName()%></option>
                        <%
                          } %>
                    </select>
                </div>
            </div>
            <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
                <div class="col-md-2 col-lg-2" style="padding: 0; line-height: 34px;">
                    <p>状态：</p>
                </div>
                <div class="col-md-10 col-lg-10">
                    <select class="form-control" name="state">
                        <option value="" selected="selected" onchange="">全部</option>
                        <option value="1">在职</option>
                        <option value="2">离职</option>
                    </select>
                </div>
            </div>
            <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
                <div class="col-md-2 col-lg-2" style="padding: 0; line-height: 34px;">
                    <p>部门：</p>
                </div>
                <div class="col-md-10 col-lg-10">
                    <select class="form-control" name="departName">
                        <option value="" selected="selected" onchange="getEmployee">九博健康管理有限公司</option>
                        <%
                        List<DepartmentBean>  dplist=(List<DepartmentBean>)request.getAttribute("depart");
                          for(DepartmentBean bean:dplist){	 
                        %>
                          <option value="<%=bean.getName()%>"><%=bean.getName()%></option>
                        <%
                           }
                        %>
                    </select>
                </div>
            </div>
            <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="Search for..." name="searchContent">
                    <span class="input-group-btn">
                        <button class="btn btn-default" type="submit">搜索</button>
                    </span>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12 col-lg-12 user-btn-group">
                <button type="submit" class="btn btn-warning" onclick="">查看所有</button>
                <button type="button" class="btn btn-warning" data-toggle="modal" data-target="#myModalQuery">高级查询</button>
                <button type="button" class="btn btn-warning" data-toggle="modal" data-target="#myModalFamily">家庭成员</button>
                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModalJoin">员工入职</button>
                <button type="button" class="btn btn-primary">导出</button>
            </div>
        </div>
       </form> 
        <div class="row">
            <div class="col-md-12 col-lg-12">
                <div class="table-responsive">
                    <table class="table table-bordered table-hover user-table" >
                        <thead>
                            <tr>
                                <th class="text-center">序号</th>
                                <th class="text-center">工号</th>
                                <th class="text-center">姓名</th>
                                <th class="text-center">性别</th>
                                <th class="text-center">部门</th>
                                <th class="text-center">职位</th>
                                <th class="text-center">ERP账号</th>
                                <th class="text-center">出生日期</th>
                                <th class="text-center">入职日期</th>
                                <th class="text-center">转正日期</th>
                                <th class="text-center">离职日期</th>
                                <th class="text-center">状态</th>
                            </tr>
                        </thead>
                        <tbody>
                        <!-- 人员数据 -->
                          <%
                            List<QueryResult> rylist=(List<QueryResult>)request.getAttribute("empData");
	                        for(QueryResult bean:rylist){	 
	                        	String sinf="";
                                if(bean.getState().equals("1")){
                               	 sinf="在职";
                                }else{
                               	 sinf="离职";
                                }
	                      %>
                           <%--  <tr>
                                <td><%=bean.getId() %></td>
                                <td><%=bean.getJobNum() %></td>
                                <td><%=bean.getName() %></td>
                                <td><%=bean.getSex() %></td>
                                <td><%=bean.getDepartName() %></td>
                                <td><%=bean.getPostionName() %></td>
                                <td><%=bean.getERPAaccount() %></td>
                                <td><%=bean.getBirth() %></td>
                                <td><%=bean.getEntryData()%></td>
                                <td><%=bean.getPositiveDate() %></td>
                                <td><%=bean.getResignDate() %></td>                               
                                <td><%=sinf%></td>
                            </tr> --%>
                             <%}%>
                        </tbody>
                        <!-- 分页信息 -->
                        <%
	                        int totalnfo=(int)request.getAttribute("totalnfo");
                        %>
                    </table>
                        共<%=totalnfo%>记录
                </div>
            </div>
        </div>
        </div>
        <form id="ryForm" action="<%=path%>/search/ryList" method="post">
        <div class="row">
            <!-- 高级查询 -->
            <div class="modal fade" id="myModalQuery">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title" id="myModalLabel">高级查询</h4>
                        </div>
                        <div class="modal-body">
                            <div class="row">
                                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                    <div class="col-md-1 col-lg-1 clear-mp" style="line-height: 34px;">
                                        <label>类别：</label>
                                    </div>
                                    <div class="col-md-4 col-lg-4 clear-mp">
                                        <select class="form-control" name="searchType">
                                            <option value="0">全部</option>
                                            <option value="1">入职日期</option>
                                            <option value="2">转正日期</option>
                                            <option value="3">离职日期</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="row add-mt">
                                <div class="form-inline col-xs-6 col-sm-6 col-md-6 col-lg-6">
                                    <div class="form-group">
                                        <label for="firstTime" class="control-label">时间：</label>
                                        <div class="input-group">
                                            <input type="date" class="form-control" id="firstTime" name="enterStartDate">
                                            <div class="input-group-addon">
                                                <span class="glyphicon glyphicon-calendar"></span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-inline col-xs-6 col-sm-6 col-md-6 col-lg-6">
                                    <div class="form-group">
                                        <label for="secondTime" class="control-label">至：</label>
                                        <div class="input-group">
                                            <input type="date" class="form-control" id="secondTime" name="enterEndDate">
                                            <div class="input-group-addon">
                                                <span class="glyphicon glyphicon-calendar"></span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row add-mt">
                                <div class="col-xs-8 col-sm-8 col-md-8 col-lg-8 col-xs-offset-2 col-sm-offset-2 col-md-offset-2 col-lg-offset-2">
                                    <div class="col-md-3 col-lg-3 clear-mp" style="line-height: 34px;"><label>查看生日在：</label></div>
                                    <div class="col-md-7 col-lg-7 clear-mp">
                                        <select class="form-control" name="birthday">
                                            <option value="0">全部</option>
                                            <option value="01">1</option>
                                            <option value="02">2</option>
                                            <option value="03">3</option>
                                            <option value="04">4</option>
                                            <option value="05">5</option>
                                            <option value="06">6</option>
                                            <option value="07">7</option>
                                            <option value="08">8</option>
                                            <option value="09">9</option>
                                            <option value="10">10</option>
                                            <option value="11">11</option>
                                            <option value="12">12</option>
                                        </select>
                                    </div>
                                    <div class="col-md-2 col-lg-2 clear-mp" style="line-height: 34px; text-align: right"><label>月份员工</label></div>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                            <button type="submit" class="btn btn-primary" >查询</button>
                        </div>
                    </div>
                </div>
            </div>
            </form>
            <!-- 家庭成员 -->
            <div class="modal fade" id="myModalFamily" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                aria-hidden="true">
                <div class="modal-dialog modal-family">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title">家庭成员</h4>
                        </div>
                        <div class="modal-body">
                        <!-- 家庭成员检索开始 -->
                        <form action="<%=path%>/search/ryList" method="post">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label for="ygname" class="col-md-3 control-label nopad">员工姓名</label>
                                            <div class="col-md-9">
                                                <input type="text" class="form-control" 
                                                    placeholder="输入工号,姓名查询..." name="empName">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label for="ygname" class="col-md-3 control-label nopad">成员姓名</label>
                                            <div class="col-md-9">
                                                <input type="text" class="form-control" id="ygname"
                                                    placeholder="" name="chName">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label for="date" class="col-md-3 control-label nopad">生日月份</label>
                                            <div class="col-md-9">
                                                <select class="form-control" name="shBirth">
                                                    <option value="0">全部</option>
                                                    <option value="01">1</option>
                                                    <option value="02">2</option>
                                                    <option value="03">3</option>
                                                    <option value="04">4</option>
                                                    <option value="05">5</option>
                                                    <option value="06">6</option>
                                                    <option value="07">7</option>
                                                    <option value="08">8</option>
                                                    <option value="09">9</option>
                                                    <option value="10">10</option>
                                                    <option value="11">11</option>
                                                    <option value="12">12</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-2 col-md-offset-1">
                                        <button type="submit" class=" btn-primary">查询</button>
                                        <button type="button" class="btn btn-primary">导出</button>
                                    </div>
                                </div>
                            </div>
                           </form>
                            <div class="row">
                                <div class="col-md-12 table-responsive text-center martop">
                                    <table class="table table-bordered table-hover">
                                        <thead>
                                            <tr>
                                                <th class="text-center">工号</th>
                                                <th class="text-center">员工姓名</th>
                                                <th class="text-center">称谓</th>
                                                <th class="text-center">成员姓名</th>
                                                <th class="text-center">出生日期</th>
                                                <th class="text-center">工作单位</th>
                                                <th class="text-center">职务</th>
                                                <th class="text-center">电话</th>
                                                <th class="text-center">微信</th>
                                                <th class="text-center">家庭住址</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        <%
                                        List<QueryFamilyResult> fmlist=(List<QueryFamilyResult>)request.getAttribute("fmyData");
					                        for(QueryFamilyResult bean:fmlist){	 
	                                     %>
                                            <tr>
                                                <td><%=bean.getJobnum() %></td>
                                                <td><%=bean.getName()%></td>
                                                <td><%=bean.getAppellation() %></td>
                                                <td><%=bean.getChname() %></td>
                                                <td><%=bean.getBirth() %></td>
                                                <td><%=bean.getWorkadress() %></td>
                                                <td><%=bean.getPosition() %></td>
                                                <td><%=bean.getPhone() %></td>
                                                <td><%=bean.getWechat() %></td>
                                                <td><%=bean.getFamAddress()%></td>
                                            </tr>
                                            <%
					        }
                                            %>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                        </div>
                    </div>
                </div>
            </div>
            <!--员工入职弹出-->
            <div class="modal fade" id="myModalJoin" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                aria-hidden="true">
                <div class="modal-dialog staff_t">
                    <div class="modal-content">
                        <div class="modal-header"><button type="button" data-dismiss="modal" aria-hidden="true" class="close">×</button>
                            <h4 id="myModalLabel" class="modal-title">员工入职</h4>
                        </div>
                        <div class="modal-body">
                            <ul class="nav nav-tabs martop">
                                <li class="active"><a href="#basic" data-toggle="tab">基本信息</a></li>
                                <li><a href="#detailed" data-toggle="tab">详细信息</a></li>
                                <li><a href="#family" data-toggle="tab">家庭成员</a></li>
                                <li><a href="#record" data-toggle="tab">调动记录</a></li>
                            </ul>
                            <div class="tab-content" style=" height:600px; overflow-y:scroll;">
                                <div class="tab-pane fade in active martop" id="basic">
                                    <form action="">
                                        <div class="form-group clearfix">
                                            <label for="cyname" class="col-md-2 control-label text-right nopad">成员姓名：</label>
                                            <div class="col-md-5">
                                                <input type="text" class="form-control" id="cyname" placeholder="">
                                            </div>
                                            <div class="col-md-3 text-danger linhet">已离职</div>
                                        </div>
                                        <div class="form-group clearfix">
                                            <label for="sex" class="col-md-2 control-label text-right nopad">性别：</label>
                                            <div class="col-md-5">
                                                <input type="text" class="form-control" id="sex" placeholder="">
                                            </div>
                                        </div>
                                        <div class="form-group clearfix">
                                            <label class="col-md-2 control-label text-right nopad">出生年月：</label>
                                            <div class="col-md-5 input-group date form_date">
                                                <input type="text" class="form-control" value="">
                                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                            </div>
                                        </div>
                                        <div class="form-group clearfix">
                                            <label for="gh" class="col-md-2 control-label text-right nopad">工号：</label>
                                            <div class="col-md-5">
                                                <input type="text" class="form-control" id="gh" placeholder="">
                                            </div>
                                        </div>
                                        <div class="form-group clearfix">
                                            <label for="erpzh" class="col-md-2 control-label text-right nopad">ERP账号：</label>
                                            <div class="col-md-5">
                                                <input type="text" class="form-control" id="erpzh" placeholder="">
                                            </div>
                                        </div>
                                        <div class="form-group clearfix">
                                            <label class="col-md-2 control-label text-right nopad">部门：</label>
                                            <div class="col-md-5">
                                                <select class="form-control">
                                                    <option value="">运维组</option>
                                                    <option value="">成都运营</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group clearfix">
                                            <label class="col-md-2 control-label text-right nopad">职位：</label>
                                            <div class="col-md-5">
                                                <select class="form-control">
                                                    <option value="">超级管理员</option>
                                                    <option value="">技术</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group clearfix">
                                            <label class="col-md-2 control-label text-right nopad">入职日期：</label>
                                            <div class="col-md-5 input-group date form_date">
                                                <input type="text" class="form-control" value="">
                                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                            </div>
                                        </div>
                                        <div class="form-group clearfix">
                                            <label class="col-md-2 control-label text-right nopad">转正日期：</label>
                                            <div class="col-md-5 input-group date form_date">
                                                <input type="text" class="form-control" value="">
                                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                            </div>
                                            <div class="col-md-3 text-primary linhet">非转正员工</div>
                                        </div>
                                        <div class="form-group clearfix">
                                            <label class="col-md-2 control-label text-right nopad">离职日期：</label>
                                            <div class="col-md-5 input-group date form_date">
                                                <input type="date" class="form-control" value="">
                                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                            </div>
                                        </div>
                                        <div class="form-group clearfix">
                                            <label class="col-md-2 control-label text-right nopad">离职类型：</label>
                                            <div class="col-md-5">
                                                <select class="form-control">
                                                    <option value="">辞退</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group clearfix">
                                            <label class="col-md-2 control-label text-right nopad">状态：</label>
                                            <div class="col-md-5">
                                                <select class="form-control">
                                                    <option value="">离职</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group clearfix">
                                            <label class="col-md-2 control-label text-right nopad">备注：</label>
                                            <div class="col-md-5">
                                                <textarea class="form-control" rows="3"></textarea>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                                <div class="tab-pane fade" id="detailed">
                                    <div class="martop1 clearfix">
                                        <div class="form-group col-md-6 clearfix">
                                            <label class="col-md-4 control-label text-right nopad">身份证号码：</label>
                                            <div class="col-md-8">
                                                <input type="text" class="form-control" placeholder="">
                                            </div>
                                        </div>
                                        <div class="form-group col-md-6 clearfix">
                                            <label class="col-md-4 control-label text-right nopad">政治面貌：</label>
                                            <div class="col-md-8">
                                                <select class="form-control">
                                                    <option value="">团员</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group col-md-6 clearfix">
                                            <label class="col-md-4 control-label text-right nopad">籍贯：</label>
                                            <div class="col-md-8">
                                                <input type="text" class="form-control" placeholder="">
                                            </div>
                                        </div>
                                        <div class="form-group col-md-6 clearfix">
                                            <label class="col-md-4 control-label text-right nopad">民族：</label>
                                            <div class="col-md-8">
                                                <select class="form-control">
                                                    <option value="">汉族</option>
                                                    <option value="">蒙古族</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group col-md-6 clearfix">
                                            <label class="col-md-4 control-label text-right nopad">户口性质：</label>
                                            <div class="col-md-8">
                                                <input type="text" class="form-control" placeholder="">
                                            </div>
                                        </div>
                                        <div class="form-group col-md-6 clearfix">
                                            <label class="col-md-4 control-label text-right nopad">户籍所在地：</label>
                                            <div class="col-md-8">
                                                <input type="text" class="form-control" placeholder="">
                                            </div>
                                        </div>
                                        <div class="form-group col-md-6 clearfix">
                                            <label class="col-md-4 control-label text-right nopad">婚姻状况：</label>
                                            <div class="col-md-8">
                                                <input type="text" class="form-control" placeholder="">
                                            </div>
                                        </div>
                                        <div class="form-group col-md-6 clearfix">
                                            <label class="col-md-4 control-label text-right nopad">家庭住址：</label>
                                            <div class="col-md-8">
                                                <input type="text" class="form-control" placeholder="">
                                            </div>
                                        </div>
                                        <div class="form-group col-md-6 clearfix">
                                            <label class="col-md-4 control-label text-right nopad">现住址：</label>
                                            <div class="col-md-8">
                                                <input type="text" class="form-control" placeholder="">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="martop1 clearfix">
                                        <div class="form-group col-md-6 clearfix">
                                            <label class="col-md-4 control-label text-right nopad">毕业院校：</label>
                                            <div class="col-md-8">
                                                <input type="text" class="form-control" placeholder="">
                                            </div>
                                        </div>
                                        <div class="form-group col-md-6 clearfix">
                                            <label class="col-md-4 control-label text-right nopad">最高学历：</label>
                                            <div class="col-md-8">
                                                <select class="form-control">
                                                    <option value="">博士</option>
                                                    <option value="">硕士</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group col-md-6 clearfix">
                                            <label class="col-md-4 control-label text-right nopad">所学专业：</label>
                                            <div class="col-md-8">
                                                <input type="text" class="form-control" placeholder="">
                                            </div>
                                        </div>
                                        <div class="form-group col-md-6 clearfix">
                                            <label class="col-md-4 control-label text-right nopad">毕业时间：</label>
                                            <div class="col-md-8 input-group date form_date">
                                                <input type="text" class="form-control" value="">
                                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                            </div>
                                        </div>
                                        <div class="form-group col-md-6 clearfix">
                                            <label class="col-md-4 control-label text-right nopad">是否在学：</label>
                                            <div class="col-md-8">
                                                <div class="checkbox">
                                                    <label>
                                                        <input type="checkbox">是
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="martop1 clearfix">
                                        <div class="col12 clearfix">
                                            <div class="form-group col-md-6 clearfix">
                                                <label class="col-md-4 control-label text-right nopad">联系方式：</label>
                                                <div class="col-md-8">
                                                    <input type="text" class="form-control" placeholder="">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group col-md-6 clearfix">
                                            <label class="col-md-4 control-label text-right nopad">紧急联络人：</label>
                                            <div class="col-md-8">
                                                <input type="text" class="form-control" placeholder="">
                                            </div>
                                        </div>
                                        <div class="form-group col-md-6 clearfix">
                                            <label class="col-md-4 control-label text-right nopad">紧急联系方式：</label>
                                            <div class="col-md-8">
                                                <input type="text" class="form-control" placeholder="">
                                            </div>
                                        </div>
                                    </div>

                                    <div class="martop1 clearfix">
                                        <div class="form-group col-md-6 clearfix">
                                            <label class="col-md-4 control-label text-right nopad">驾龄：</label>
                                            <div class="col-md-8">
                                                <input type="text" class="form-control" placeholder="">
                                            </div>
                                        </div>
                                        <div class="form-group col-md-6 clearfix">
                                            <label class="col-md-4 control-label text-right nopad">驾照类型：</label>
                                            <div class="col-md-8">
                                                <select class="form-control">
                                                    <option value="">无</option>
                                                    <option value="">C1</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <!--</div>-->
                                    <!--</div>-->
                                </div>
                                <!--家庭成员-->
                                <div class="tab-pane fade" id="family">
                                    <div class="col-md-2 col-md-offset-10 clearfix martop">
                                        <button type="button" class="btn btn-warning">添加</button>
                                    </div>
                                    <div class="col-md-12 clearfix nopad addbord martop">
                                        <table class="table table-bordered table-responsive">
                                            <thead>
                                                <tr>
                                                    <th>称谓</th>
                                                    <th>姓名</th>
                                                    <th>出生日期</th>
                                                    <th>工作单位</th>
                                                    <th>职务</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td>父亲</td>
                                                    <td>薛小城</td>
                                                    <td>1966-6-6</td>
                                                    <td></td>
                                                    <td>技工</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                        <div class="col-md-8 col-md-offset-2">
                                            <div class="form-group clearfix">
                                                <label class="col-md-3 control-label text-right nopad">称谓：</label>
                                                <div class="col-md-6">
                                                    <input type="text" class="form-control" placeholder="">
                                                </div>
                                            </div>
                                            <div class="form-group clearfix">
                                                <label class="col-md-3 control-label text-right nopad">姓名：</label>
                                                <div class="col-md-6">
                                                    <input type="text" class="form-control" placeholder="">
                                                </div>
                                            </div>
                                            <div class="form-group clearfix">
                                                <label class="col-md-3 control-label text-right nopad">出生日期：</label>
                                                <div class="col-md-6 input-group date form_date">
                                                    <input type="text" class="form-control" value="">
                                                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                                </div>
                                            </div>
                                            <div class="form-group clearfix">
                                                <label class="col-md-3 control-label text-right nopad">工作单位：</label>
                                                <div class="col-md-9">
                                                    <input type="text" class="form-control" placeholder="">
                                                </div>
                                            </div>
                                            <div class="form-group clearfix">
                                                <label class="col-md-3 control-label text-right nopad">职务：</label>
                                                <div class="col-md-6">
                                                    <input type="text" class="form-control" placeholder="">
                                                </div>
                                            </div>
                                            <div class="form-group clearfix">
                                                <label class="col-md-3 control-label text-right nopad">联系电话：</label>
                                                <div class="col-md-6">
                                                    <input type="text" class="form-control" placeholder="">
                                                </div>
                                            </div>
                                            <div class="form-group clearfix">
                                                <label class="col-md-3 control-label text-right nopad">微信：</label>
                                                <div class="col-md-6">
                                                    <input type="text" class="form-control" placeholder="">
                                                </div>
                                            </div>
                                            <div class="form-group clearfix">
                                                <label class="col-md-3 control-label text-right nopad">家庭住址：</label>
                                                <div class="col-md-9">
                                                    <input type="text" class="form-control" placeholder="">
                                                </div>
                                            </div>
                                            <!--确认添加按钮-->
                                            <div class="col-md-2 col-md-offset-8"><button type="button" class="btn btn-warning">确认</button></div>
                                            <div class="col-md-2"><button type="button" class="btn btn-warning">取消</button></div>
                                        </div>
                                    </div>
                                </div>
                                <!--调动记录-->
                                <div class="tab-pane fade" id="record">
                                    <table class="table table-bordered table-responsive">
                                        <thead>
                                            <tr>
                                                <th>原部门</th>
                                                <th>原职位</th>
                                                <th>现部门</th>
                                                <th>现职位</th>
                                                <th>调动类型</th>
                                                <th>调动日期</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td>成都运营</td>
                                                <td>外推</td>
                                                <td>成都运营</td>
                                                <td>竞价</td>
                                                <td></td>
                                                <td>2018-12-1</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <!--</div>-->
                        </div>
                        <div class="modal-footer">
                            <!--按钮-->
                            <div class="col-md-12">
                                <div class="col-md-8">
                                    <button type="button" class="btn btn-info btn-sm">初始化密码</button>
                                    <button type="button" class="btn btn-info btn-sm">转正</button>
                                    <button type="button" class="btn btn-info btn-sm">恢复</button>
                                    <button type="button" class="btn btn-info btn-sm">删除</button>
                                </div>
                                <button type="button" class="btn btn-info">确认</button>
                                <button type="button" data-dismiss="modal" class="btn btn-info">返回</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- 员工信息修改 -->
            <div class="modal fade" id="myModalUpdate" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                aria-hidden="true">
                <div class="modal-dialog staff_t">
                    <div class="modal-content">
                        <div class="modal-header"><button type="button" data-dismiss="modal" aria-hidden="true" class="close">×</button>
                            <h4 id="myModalLabel" class="modal-title">员工信息修改</h4>
                        </div>
                        <div class="modal-body">
                            <ul class="nav nav-tabs martop">
                                <li class="active"><a href="#updatebasic" data-toggle="tab">基本信息</a></li>
                                <li><a href="#updatedetailed" data-toggle="tab">详细信息</a></li>
                                <li><a href="#updatefamily" data-toggle="tab">家庭成员</a></li>
                                <li><a href="#updaterecord" data-toggle="tab">调动记录</a></li>
                            </ul>
                            <div class="tab-content" style=" height:600px; overflow-y:scroll;">
                                <div class="tab-pane fade in active martop" id="updatebasic">
                                    <form action="">
                                        <div class="form-group clearfix">
                                            <label for="cyname" class="col-md-2 control-label text-right nopad">成员姓名：</label>
                                            <div class="col-md-5">
                                                <input type="text" class="form-control" id="cyname" v-model="updateName">
                                            </div>
                                            <template>
                                                <div class="col-md-3 text-danger linhet" v-if="updateState == 2">已离职</div>
                                            </template>
                                        </div>
                                        <div class="form-group clearfix">
                                            <label for="sex" class="col-md-2 control-label text-right nopad">性别：</label>
                                            <div class="col-md-5">
                                                <input type="text" class="form-control" id="sex">
                                            </div>
                                        </div>
                                        <div class="form-group clearfix">
                                            <label class="col-md-2 control-label text-right nopad">出生年月：</label>
                                            <div class="col-md-5 input-group date form_date">
                                                <input type=" date" class="form-control" v-model="updateBirth">
                                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                            </div>
                                        </div>
                                        <div class="form-group clearfix">
                                            <label for="gh" class="col-md-2 control-label text-right nopad">工号：</label>
                                            <div class="col-md-5">
                                                <input type="text" class="form-control" id="gh" v-model="updateJobNum" disabled>
                                            </div>
                                        </div>
                                        <div class="form-group clearfix">
                                            <label for="erpzh" class="col-md-2 control-label text-right nopad">ERP账号：</label>
                                            <div class="col-md-5">
                                                <input type="text" class="form-control" id="erpzh" v-model="updateAccount" disabled>
                                            </div>
                                        </div>
                                        <div class="form-group clearfix">
                                            <label class="col-md-2 control-label text-right nopad">部门：</label>
                                            <div class="col-md-5">
                                                <select class="form-control" v-model="updateDepartment" disabled>
                                                    <template v-for="item in departmentList">
                                                        <option v-bind:value="item.name">{{item.name}}</option>
                                                    </template>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group clearfix">
                                            <label class="col-md-2 control-label text-right nopad">职位：</label>
                                            <div class="col-md-5">
                                                <select class="form-control" v-model="updatePosition" disabled>
                                                    <option v-bind:value="item.position_Name" v-for="(item, index) in positionList">{{item.position_Name}}</option>
                                                </select>
                                            </div>
                                            <button type="button" class="btn btn-warning">岗位调动</button>
                                        </div>
                                        <div class="form-group clearfix">
                                            <label class="col-md-2 control-label text-right nopad">入职日期：</label>
                                            <div class="col-md-5 input-group date form_date">
                                                <input type="date" class="form-control" v-model="updateEntryDate">
                                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                            </div>
                                        </div>
                                        <div class="form-group clearfix">
                                            <label class="col-md-2 control-label text-right nopad">转正日期：</label>
                                            <div class="col-md-5 input-group date form_date">
                                                <input type="date" class="form-control" v-model="updatePositiveDate">
                                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                            </div>
                                            <template>
                                                <div class="col-md-3 text-primary linhet" v-if="updatePositiveDate == ''">非转正员工</div>
                                            </template>
                                        </div>
                                        <div class="form-group clearfix">
                                            <label class="col-md-2 control-label text-right nopad">离职日期：</label>
                                            <div class="col-md-5 input-group date form_date">
                                                <input type="date" class="form-control" v-model="updateResignDate">
                                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                            </div>
                                        </div>
                                        <div class="form-group clearfix">
                                            <label class="col-md-2 control-label text-right nopad">离职类型：</label>
                                            <div class="col-md-5">
                                                <select class="form-control" v-model="updateResignType">
                                                    <option value="辞职">辞职</option>
                                                    <option value="辞退">辞退</option>
                                                    <option value="其他">其他</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group clearfix">
                                            <label class="col-md-2 control-label text-right nopad">状态：</label>
                                            <div class="col-md-5">
                                                <select class="form-control" v-model="updateState">
                                                    <option value="1">在职</option>
                                                    <option value="2">离职</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group clearfix">
                                            <label class="col-md-2 control-label text-right nopad">备注：</label>
                                            <div class="col-md-5">
                                                <textarea class="form-control" rows="3"></textarea>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                                <!-- 详细信息 -->
                                <div class="tab-pane fade" id="updatedetailed">
                                    <div class="martop1 clearfix">
                                        <div class="form-group col-md-6 clearfix">
                                            <label class="col-md-4 control-label text-right nopad">身份证号码：</label>
                                            <div class="col-md-8">
                                                <input type="text" class="form-control" v-model="updateIDNum">
                                            </div>
                                        </div>
                                        <div class="form-group col-md-6 clearfix">
                                            <label class="col-md-4 control-label text-right nopad">政治面貌：</label>
                                            <div class="col-md-8">
                                                <select class="form-control" v-model="updatePloitical">
                                                    <option value="团员">团员</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group col-md-6 clearfix">
                                            <label class="col-md-4 control-label text-right nopad">籍贯：</label>
                                            <div class="col-md-8">
                                                <input type="text" class="form-control" v-model="updateHomeTown">
                                            </div>
                                        </div>
                                        <div class="form-group col-md-6 clearfix">
                                            <label class="col-md-4 control-label text-right nopad">民族：</label>
                                            <div class="col-md-8">
                                                <select class="form-control" v-model="updateNationality">
                                                    <option v-for="item in nationList" v-bind:value="item.nation">{{item.nation}}</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group col-md-6 clearfix">
                                            <label class="col-md-4 control-label text-right nopad">户口性质：</label>
                                            <div class="col-md-8">
                                                <input type="text" class="form-control" v-model="updateAccountProp">
                                            </div>
                                        </div>
                                        <div class="form-group col-md-6 clearfix">
                                            <label class="col-md-4 control-label text-right nopad">户籍所在地：</label>
                                            <div class="col-md-8">
                                                <input type="text" class="form-control" v-model="updateDomicile">
                                            </div>
                                        </div>
                                        <div class="form-group col-md-6 clearfix">
                                            <label class="col-md-4 control-label text-right nopad">婚姻状况：</label>
                                            <div class="col-md-8">
                                                <input type="text" class="form-control" v-model="updateMarital">
                                            </div>
                                        </div>
                                        <div class="form-group col-md-6 clearfix">
                                            <label class="col-md-4 control-label text-right nopad">家庭住址：</label>
                                            <div class="col-md-8">
                                                <input type="text" class="form-control" v-model="updateHomeAddress">
                                            </div>
                                        </div>
                                        <div class="form-group col-md-6 clearfix">
                                            <label class="col-md-4 control-label text-right nopad">现住址：</label>
                                            <div class="col-md-8">
                                                <input type="text" class="form-control" v-model="updateCurrentAddress">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="martop1 clearfix">
                                        <div class="form-group col-md-6 clearfix">
                                            <label class="col-md-4 control-label text-right nopad">毕业院校：</label>
                                            <div class="col-md-8">
                                                <input type="text" class="form-control" v-model="updateSchools">
                                            </div>
                                        </div>
                                        <div class="form-group col-md-6 clearfix">
                                            <label class="col-md-4 control-label text-right nopad">最高学历：</label>
                                            <div class="col-md-8">
                                                <select class="form-control" v-model="updateEducation">
                                                    <option value="学士">学士</option>
                                                    <option value="博士">博士</option>
                                                    <option value="硕士">硕士</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group col-md-6 clearfix">
                                            <label class="col-md-4 control-label text-right nopad">所学专业：</label>
                                            <div class="col-md-8">
                                                <input type="text" class="form-control" v-model="updateProfession">
                                            </div>
                                        </div>
                                        <div class="form-group col-md-6 clearfix">
                                            <label class="col-md-4 control-label text-right nopad">毕业时间：</label>
                                            <div class="col-md-8 input-group date form_date">
                                                <input type="datetime" class="form-control" v-model="updateGraduation">
                                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                            </div>
                                        </div>
                                        <div class="form-group col-md-6 clearfix">
                                            <label class="col-md-4 control-label text-right nopad">是否在学：</label>
                                            <div class="col-md-8">
                                                <div class="checkbox">
                                                    <label>
                                                        <input type="checkbox" v-model="updateAtSchool">是
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="martop1 clearfix">
                                        <div class="col12 clearfix">
                                            <div class="form-group col-md-6 clearfix">
                                                <label class="col-md-4 control-label text-right nopad">联系方式：</label>
                                                <div class="col-md-8">
                                                    <input type="text" class="form-control" v-model="updateContact">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group col-md-6 clearfix">
                                            <label class="col-md-4 control-label text-right nopad">紧急联络人：</label>
                                            <div class="col-md-8">
                                                <input type="text" class="form-control" v-model="updateEmergencyContact">
                                            </div>
                                        </div>
                                        <div class="form-group col-md-6 clearfix">
                                            <label class="col-md-4 control-label text-right nopad">紧急联系方式：</label>
                                            <div class="col-md-8">
                                                <input type="text" class="form-control" v-model="updateEmergencyPhone">
                                            </div>
                                        </div>
                                    </div>

                                    <div class="martop1 clearfix">
                                        <div class="form-group col-md-6 clearfix">
                                            <label class="col-md-4 control-label text-right nopad">驾龄：</label>
                                            <div class="col-md-8">
                                                <input type="text" class="form-control" v-model="updateDrivingExpe">
                                            </div>
                                        </div>
                                        <div class="form-group col-md-6 clearfix">
                                            <label class="col-md-4 control-label text-right nopad">驾照类型：</label>
                                            <div class="col-md-8">
                                                <select class="form-control" v-model="updateLicenseType">
                                                    <option value="">无</option>
                                                    <option value="">C1</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <!--</div>-->
                                    <!--</div>-->
                                </div>
                                <!--家庭成员-->
                                <div class="tab-pane fade" id="updatefamily">
                                    <div class="col-md-2 col-md-offset-10 clearfix martop">
                                        <button type="button" class="btn btn-warning">添加</button>
                                    </div>
                                    <div class="col-md-12 clearfix nopad addbord martop">
                                        <table class="table table-bordered table-responsive">
                                            <thead>
                                                <tr>
                                                    <th>称谓</th>
                                                    <th>姓名</th>
                                                    <th>出生日期</th>
                                                    <th>工作单位</th>
                                                    <th>职务</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr v-for="(item,index) in FamilyNumList" v-on:dblclick="updateEmployeeFamily(item.account_ID)">
                                                    <td>{{item.appellation}}</td>
                                                    <td>{{item.name}}</td>
                                                    <td>{{timeInit(item.birthday)}}</td>
                                                    <td>{{item.workaddress}}</td>
                                                    <td>{{item.position}}</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                        <div class="col-md-8 col-md-offset-2">
                                            <div class="form-group clearfix">
                                                <label class="col-md-3 control-label text-right nopad">称谓：</label>
                                                <div class="col-md-6">
                                                    <input type="text" class="form-control" v-model="updateAppellation">
                                                </div>
                                            </div>
                                            <div class="form-group clearfix">
                                                <label class="col-md-3 control-label text-right nopad">姓名：</label>
                                                <div class="col-md-6">
                                                    <input type="text" class="form-control" v-model="updateFamilyName">
                                                </div>
                                            </div>
                                            <div class="form-group clearfix">
                                                <label class="col-md-3 control-label text-right nopad">出生日期：</label>
                                                <div class="col-md-6 input-group date form_date">
                                                    <input type="datetime" class="form-control" v-model="updateBirthday">
                                                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                                </div>
                                            </div>
                                            <div class="form-group clearfix">
                                                <label class="col-md-3 control-label text-right nopad">工作单位：</label>
                                                <div class="col-md-9">
                                                    <input type="text" class="form-control" v-model="updateWorkaddress">
                                                </div>
                                            </div>
                                            <div class="form-group clearfix">
                                                <label class="col-md-3 control-label text-right nopad">职务：</label>
                                                <div class="col-md-6">
                                                    <input type="text" class="form-control" v-model="updateFamilyPosition">
                                                </div>
                                            </div>
                                            <div class="form-group clearfix">
                                                <label class="col-md-3 control-label text-right nopad">联系电话：</label>
                                                <div class="col-md-6">
                                                    <input type="text" class="form-control" v-model="updatePhone">
                                                </div>
                                            </div>
                                            <div class="form-group clearfix">
                                                <label class="col-md-3 control-label text-right nopad">微信：</label>
                                                <div class="col-md-6">
                                                    <input type="text" class="form-control" v-model="updateWechat">
                                                </div>
                                            </div>
                                            <div class="form-group clearfix">
                                                <label class="col-md-3 control-label text-right nopad">家庭住址：</label>
                                                <div class="col-md-9">
                                                    <input type="text" class="form-control" v-model="updateAddress">
                                                </div>
                                            </div>
                                            <!--确认添加按钮-->
                                            <div class="col-md-2 col-md-offset-8"><button type="button" class="btn btn-warning" v-on:click="">确认</button></div>
                                            <div class="col-md-2"><button type="button" class="btn btn-warning">取消</button></div>
                                        </div>
                                    </div>
                                </div>
                                <!--调动记录-->
                                <div class="tab-pane fade" id="updaterecord">
                                    <table class="table table-bordered table-responsive">
                                        <thead>
                                            <tr>
                                                <th>原部门</th>
                                                <th>原职位</th>
                                                <th>现部门</th>
                                                <th>现职位</th>
                                                <th>调动类型</th>
                                                <th>调动日期</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td>成都运营</td>
                                                <td>外推</td>
                                                <td>成都运营</td>
                                                <td>竞价</td>
                                                <td></td>
                                                <td>2018-12-1</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <!--</div>-->
                        </div>
                        <div class="modal-footer">
                            <!--按钮-->
                            <div class="col-md-12">
                                <div class="col-md-8">
                                    <button type="button" class="btn btn-info btn-sm">初始化密码</button>
                                    <button type="button" class="btn btn-info btn-sm">转正</button>
                                    <button type="button" class="btn btn-info btn-sm">恢复</button>
                                    <button type="button" class="btn btn-info btn-sm">删除</button>
                                </div>
                                <button type="button" class="btn btn-info" v-on:click="updateEmployeeInfo">确认</button>
                                <button type="button" data-dismiss="modal" class="btn btn-info">返回</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
<script src="<%=path%>/js/jquery.min.js"></script>
<script src="<%=path%>/js/bootstrap.js"></script>
<script src="<%=path%>/vueFile/employee.js"></script>


</html>