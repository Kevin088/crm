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
	<div id="div_roleTree" region="west" iconCls="icon-chart_organisation"
		split="true" title="角色" style="width: 200px; padding: 2px"
		collapsible="false">
		<ul id="roleTree"></ul>
	</div>
	<!-- 右半部-员工列表-->
	<div id="div_staffGird" region="center" iconCls="icon-users"
		style="overflow: hidden">
		<!-- 按钮 -->
		<div id="toolbar">
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-reload" plain="true" onclick="reload();">刷新</a> <a
				href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-edit" plain="true" onclick="edit();">编辑</a> <span>用户名:</span><input
				name="search_username" id="search_username" value="" size=10 /> <a
				href="javascript:searchUser()" class="easyui-linkbutton"
				data-options="iconCls:'icon-search'">查询</a>
		</div>
		<!-- 员工列表 -->
		<table id="staffGird" toolbar="#toolbar" class="easyui-datagrid"
			fit="true" url="${path}/user/datagrid" toolbar="#toolbar"
			pagination="true" singleSelect="false" rownumbers="true"
			idField="id"
			striped="true" border="false" nowrap="false">
			<thead>
				<th field="id" width="10" hidden="true" width="135">编号</th>
				<th field="checkbox" width="30" checkbox="true"></th>
				<th field="username" width="135">姓名</th>
				<th field="password" width="135" hidden="true" >密码</th>
				<th field="roleName" width="135">角色</th>
			</thead>
		</table>
		<!-- 添加/修改对话框 -->
		<div id="dlg_user" class="easyui-dialog"
			style="width: 400px; height: 300px; padding: 10px 20px" closed="true"
			buttons="#dlg-buttons">
			<form id="fm_user" method="post" novalidate>
				<div class="fitem">
					<label>角色:</label> <input id="slt_role" name="role_id"
						class="easyui-combobox" required="true" editable="false"
						data-options="
					valueField:'id',
					textField:'roleName',
					panelHeight: 'auto',
					url:'${path}/user/rolelist'" />
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
	</div>
</body>
<script type="text/javascript">
	//请求地址
	var url;
	//提示消息
	var mesTitle;
	$(function() {
		//1.1 加载权限树列表
		$('#roleTree').tree(
				{
					lines : true,
					url : '${path}/role/nodesrole',
					parentField : 'pid',
					onClick : function(node) {
						$('#staffGird').datagrid('reload',
								"${path}/user/datagrid?roleId=" + node.id);
					}
				});
	})

	//刷新
	function reload() {
		$('#staffGird').datagrid('reload');
	}
	var checkedItems = [];
	//提交 添加或编辑
	function saveAddOrUpdate() {
		var roleId=$('#slt_role').combobox('getValue');
		checkedItems.splice(0,checkedItems.length)
        var rows = $('#staffGird').datagrid('getChecked');
        for (var i = 0; i < rows.length; i++) {
            checkedItems.push(rows[i].id);
        }
        
		$.ajax({
			type:"post",
			url:url,
			dataType:"json",
			data:{"ids":checkedItems,"roleId":roleId},
			success:function(result) {
				if (result.success) {
					$('#dlg_user').dialog('close');
					$('#staffGird').datagrid('reload');
				} else {
					mesTitle = '编辑失败';
				}
				$.messager.show({
					title : mesTitle,
					msg : result.msg
				});
			}
		});
	}
	//编辑信息
	function edit() {
		var row = $('#staffGird').datagrid('getChecked');
		if (row) {
			$('#dlg_user').dialog('open').dialog('setTitle', '编辑菜单');
			$('#fm_user').form('load', row);//这句话有问题，第一次加载时正确的，第二次就出错了，还保持第一次的数据
			url = path + "/role/roleEdit?ids=" + checkedItems;
			mesTitle = '编辑成功';
		} else {
			$.messager.alert('提示', '请选择要编辑的记录！', 'error');
		}
	}

	//查询用户信息
	function searchUser() {
		$('#staffGird').datagrid('reload', {
			username : $('#search_username').val(),
		});
	}
</script>
</html>
