<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script>
		$(function(){
			$('#processesTable').iDatagrid({
			    url:'processes/queryOtherProcesses.do',
			    columns:[[
			        {field:'id',title:'id',checkbox:true},
			        {field:'code',title:'代码',width:100},
			        {field:'name',title:'名称',width:100},
			        {field:'processTypeName',title:'工序类别',width:100,formatter:function(value,row,index){
			        	if(row.processType){
			        		return row.processType.name;
			        	}else{
			        		return '';
			        	}
			        }},
			        {field:'note',title:'备注',width:100}
			    ]]
			});
		}); 
</script>
<div data-toggle="topjui-layout" data-options="fit:true">
	<div
		data-options="region:'center',title:'',fit:true,border:false,bodyCls:'border_right_bottom'">
		<table id="processesTable"></table>
	</div>
</div>