<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<title>瑜伽馆管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="assets/css/dpl-min.css" rel="stylesheet" type="text/css" />
<link href="assets/css/bui-min.css" rel="stylesheet" type="text/css" />
<link href="assets/css/main-min.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="header">
		<div class="dl-title"><span class="">瑜伽馆管理系统</span></div>
		<div class="dl-log">
			欢迎您，<span class="dl-log-user">root</span><a href="#" title="退出系统"
				class="dl-log-quit">[退出]</a>
		</div>
	</div>
	<div class="content">
		<div class="dl-main-nav">
			<div class="dl-inform">
				<div class="dl-inform-title">
					<s class="dl-inform-icon dl-up"></s>
				</div>
			</div>
			<ul id="J_Nav" class="nav-list ks-clear">
				<li class="nav-item dl-selected"><div
						class="nav-item-inner nav-home">会员管理</div></li>
				<li class="nav-item dl-selected"><div
						class="nav-item-inner nav-order">商品管理</div></li>
				<li class="nav-item dl-selected"><div
						class="nav-item-inner nav-order">人事管理</div></li>
				<li class="nav-item dl-selected"><div
						class="nav-item-inner nav-order">系统管理</div></li>
			</ul>
		</div>
		<ul id="J_NavContent" class="dl-tab-conten">
		</ul>
	</div>
	<script type="text/javascript" src="assets/js/jquery-1.8.1.min.js"></script>
	<script type="text/javascript" src="assets/js/bui-min.js"></script>
	<script type="text/javascript" src="assets/js/common/main-min.js"></script>
	<script type="text/javascript" src="assets/js/config-min.js"></script>
	<script>
		BUI.use('common/main', function() {
			var config = [ {
				id : '1',
				homePage : '11',
				menu : [ {
					text : '会员基础',
					items : [ {
						id : '11',
						text : '会员信息管理',
						href : 'jsp/member/index.jsp'
					} ]
				},{
					text : '会员消费',
					items : [ {
						id : '12',
						text : '会员课程',
						href : 'jsp/memberCourse/index.jsp'
					}, {
						id : '13',
						text : '会员消费',
						href : 'jsp/memberComsume/index.jsp'
					}]
				} ]
			}, {
				id : '2',
				homePage : '21',
				menu : [ {
					text : '商品管理',
					items : [ {
						id : '21',
						text : '课程管理',
						href : 'jsp/course/index.jsp'
					}, {
						id : '22',
						text : '消费品管理',
						href : 'jsp/consume/index.jsp'
					} ]
				} ]
			}, {
				id : '3',
				homePage : '31',
				menu : [ {
					text : '人事管理',
					items : [ {
						id : '31',
						text : '员工管理',
						href : 'jsp/staff/index.jsp'
					}, {
						id : '32',
						text : '课室管理',
						href : 'jsp/classrooms/index.jsp'
					}, {
						id : '33',
						text : '任务管理',
						href : 'jsp/staffCourseClassrooms/index.jsp'
					} ]
				} ]
			}, {
				id : '4',
				homePage : '41',
				menu : [ {
					text : '系统管理',
					items : [ {
						id : '41',
						text : '用户管理',
						href : 'jsp/user/index.jsp'
					}, {
						id : '42',
						text : '角色管理',
						href : 'jsp/role/index.jsp'
					}, {
						id : '43',
						text : '权限管理',
						href : 'jsp/limit/index.jsp'
					} ]
				} ]
			} ];
			new PageUtil.MainPage({
				modulesConfig : config
			});
		});
	</script>
</body>
</html>