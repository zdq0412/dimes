<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<script>
		$(function(){
			$('#deviceTable').iDatagrid({
			    url:'device/queryIdleDevices.do',
			    columns:[[
			        {field:'id',title:'id',checkbox:true},
			        {field:'code',title:'代码',width:100},
			        {field:'name',title:'名称',width:100},
			        {field:'unitType',title:'规格型号',width:100},
			        {field:'status',title:'设备状态',width:100}
			    ]]
			});
		});
	</script>
<div data-toggle="topjui-layout" data-options="fit:true">
	<div
		data-options="region:'center',title:'',fit:true,border:false,bodyCls:'border_right_bottom'">
		<!-- <table data-toggle="topjui-datagrid"
			data-options="id:'idleDeviceDg',
                               initCreate: false,
                               fitColumns:true,
						       url:'device/queryIdleDevices.do'">
			<thead>
				<tr>
					<th data-options="field:'id',title:'id',checkbox:true"></th>
					<th data-options="field:'code',title:'设备代码',sortable:true"></th>
					<th data-options="field:'name',title:'设备名称',sortable:true"></th>
					<th data-options="field:'unitType',title:'规格型号',sortable:true"></th>
					<th data-options="field:'status',title:'设备状态',sortable:true"></th>
				</tr>
			</thead>
		</table> -->
		<table id="deviceTable"></table>
	</div>
</div>