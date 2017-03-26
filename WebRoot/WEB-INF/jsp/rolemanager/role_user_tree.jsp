<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
<%@include file="/WEB-INF/jsp/include/easyui.jsp"%>
<script src="${path}/js/assets/user_list_tree.js" type="text/javascript"></script>
</head>
<body class="easyui-layout" fit="true">
	<!-- 左半部-权限树 -->
	<div id="div_roleTree" region="west"
		iconCls="icon-chart_organisation" split="true" title="县分"
		style="width: 200px; padding: 2px" collapsible="false">
		<ul id="roleTree"></ul>
	</div>
	<!-- 右半部-员工列表-->
	<div id="div_staffGird" region="center" iconCls="icon-users"
		style="overflow: hidden">
		<!-- 按钮 -->
		<div id="toolbar">
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-reload" plain="true" onclick="reload();">刷新</a> 
				<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-add" plain="true" onclick="addUser();">添加</a> 
				<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-edit" plain="true" onclick="edit();">编辑</a> 
				<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-remove" plain="true" onclick="deleteUser();">删除</a> 
				<span>用户名:</span><input name="search_username" id="search_username" value="" size=10 /> 
				<a href="javascript:searchUser()" class="easyui-linkbutton"
				data-options="iconCls:'icon-search'">查询</a> 
		</div>
		<!-- 员工列表 -->
		<table id="staffGird" toolbar="#toolbar"></table>
		<!-- 添加/修改对话框 -->
		<div id="dlg_user" class="easyui-dialog"
			style="width:400px;height:300px;padding:10px 20px" closed="true"
			buttons="#dlg-buttons">
			<form id="fm_user" method="post" novalidate>
				<div class="fitem">
					<label>姓名:</label> <input name="username" class="easyui-textbox" required="true">
					</select>
				</div>
				<div class="fitem">
					<label>密码:</label> <input name="password" class="easyui-textbox" required="true">
				</div>
				<div class="fitem">
					<label>角色:</label>
					<input id="slt_role" name="role_id" class="easyui-combobox" required="true" editable="false"
					data-options="
					valueField:'id',
					textField:'roleName',
					panelHeight: 'auto',
					url:'${path}/user/rolelist'"/>
				</div>
				<div class="fitem">
					<label>县分:</label>
					<input id="slt_district" name="district_id" class="easyui-combobox" required="true" editable="false"
					data-options="
					valueField:'id',
					textField:'dictName',
					panelHeight: 'auto',
					url:'${path}/district/alldistrict'"/>				
				</div>
			</form>
		</div>
		
		<!-- 添加/修改对话框按钮 -->
		<div id="dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton c6"
				iconCls="icon-ok" onclick="saveAddOrUpdate()" style="width:90px">保存</a> 
				<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-cancel" onclick="javascript:$('#dlg_user').dialog('close')"
				style="width:90px">取消</a>
		</div>
		
		<!-- 删除对话框 -->
		<div id="dlg_delete" class="easyui-dialog"
			style="width:300px;height:200px;padding:10px 20px" closed="true"
			buttons="#dlg-del-buttons">
			<div class="ftitle">请谨慎操作</div>
			<form id="fm_del" method="post" novalidate>
					<label>确定删除县分吗？</label>
			</form>
		</div>
		
		<!-- 删除对话框按钮 -->
		<div id="dlg-del-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton c6"
				iconCls="icon-ok" onclick="save_del()" style="width:90px">删除</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-cancel" onclick="javascript:$('#dlg_delete').dialog('close')"
				style="width:90px">取消</a>
		</div>
	</div>
</body>
<script type="text/javascript">
	//请求地址
	var url;
	//提示消息
	var mesTitle;
	$(function() {
		//1.1 加载权限树列表
		$('#organizeTree').tree(
				{
					lines : true,
					url : '${path}/district/nodesDistrict',
					parentField : 'pid',
					onClick : function(node) {
						$('#staffGird').datagrid('reload',
								"${path}/user/datagrid?districtId=" + node.id);
					}
				});

		//1.2、加载所选组织机构下的员工列表
		$('#staffGird').datagrid({
			fit : true,
			url : "${path}/user/datagrid",
			title : "员工（职员）列表",
			loadMsg : "正在加载员工（职员）数据，请稍等...",
			idField : 'id',
			pagination : true,
			border : false,
			nowrap : false,
			fitColumns : true,
			singleSelect : true,
			striped : true,
			rownumbers : true,
			columns : [ [ {
				title : '编号',
				field : 'id',
				hidden : true
			}, {
				title : '姓名',
				field : 'username',
				width : 35,
				align : 'center'
			}, {
				title : '密码',
				field : 'password',
				width : 35,
				align : 'center'
			}, {
				title : '角色',
				field : 'roleName',
				width : 35,
				align : 'center'
			}, ] ],
			onLoadSuccess : function(data) {
				if (data.rows.length > 0) {
					$('#staffGird').datagrid("selectRow", 0);
				}
			}
		});
	})
	//添加信息
	function addUser(){
		$('#dlg_user').dialog('open').dialog('setTitle','添加职工');
		$('#fm_user').form('clear');
		url = path+"/user/addUser";
		mesTitle = '添加职工成功';		
	}
	//刷新
	function reload() {
		$('#staffGird').datagrid('reload'); 
	}
	//提交 添加或编辑
	function saveAddOrUpdate(){
	 	$('#fm_user').form('submit',{
		 	url: url,
		 	onSubmit: function(){
		 		return $(this).form('validate');
		 	},
			success: function(result){
				var result = eval('('+result+')');
				if (result.success){
				 	$('#dlg_user').dialog('close'); 
				 	$('#staffGird').datagrid('reload'); 
				} else {
					 mesTitle = '编辑县分失败';
				}
				$.messager.show({ 
					 title: mesTitle,
					 msg: result.msg
				});
			}
	 	});
	}
	//编辑信息
 	function edit(){
	 	var row = $('#staffGird').datagrid('getSelected');
	 	if (row){
	 		var id = row.id;
		 	$('#dlg_user').dialog('open').dialog('setTitle','编辑菜单');
		 	$('#fm_user').form('load',row);//这句话有问题，第一次加载时正确的，第二次就出错了，还保持第一次的数据
		 	url = path+"/user/editUser?id="+id;
		 	mesTitle = '编辑县分成功';
	 	}else{
	 		$.messager.alert('提示', '请选择要编辑的记录！', 'error');
	 	}
	}
	//删除信息
 	function deleteUser(){
	 	var row = $('#staffGird').datagrid('getSelected');
	 	if (row){
	 		var id = row.id;
		 	$('#dlg_delete').dialog('open').dialog('setTitle','删除菜单');
		 	url = path+"/user/deleteUser?id="+id;
		 	mesTitle = '删除员工成功';
	 	}else{
	 		$.messager.alert('提示', '请选择要删除的记录！', 'error');
	 	}
	}
	//提交删除内容
	function save_del(){
	 	$('#fm_del').form('submit',{
		 	url: url,
			success: function(result){
				var result = eval('('+result+')');
				if (result.success){
				 	$('#dlg_delete').dialog('close'); 
				 	$('#staffGird').datagrid('reload'); 
				} else {
					 mesTitle = '删除员工失败';
				}
				$.messager.show({ 
					 title: mesTitle,
					 msg: result.msg
				});
			}
	 	});
	}
	//查询用户信息
 	function searchUser(){	
 		$('#staffGird').datagrid('reload',{
 			username: $('#search_username').val(),
 		}); 
 	}
</script>
</html>
