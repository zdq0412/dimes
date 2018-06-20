<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script>
		$(function(){
			$('#processesDeviceSiteTable').iDatagrid({
			    url:'workpiece/queryOtherProcessParameters.do',
			    columns:[[
			        {field:'id',title:'id',checkbox:true},
			        {field:'processTypeName',title:'工序类别',width:100,
			        	formatter:function(value,row,index){
			        		if(row.processes){
			        			return row.processes.processType.name;
			        		}else{
			        			return '';
			        		}
			        	}
			        } ,
			        {field:'processCode',title:'工序代码',width:100,
			        	formatter:function(value,row,index){
			        		if(row.processes){
			        			return row.processes.code;
			        		}else{
			        			return '';
			        		}
			        	}
			        },
			        {field:'processName',title:'工序名称',width:100,formatter:function(value,row,index){
			        	if(row.processes){
			        		return row.processes.name;
			        	}else{
			        		return '';
			        	}
			        }},
			        {field:'parameterTypeName',title:'参数类别',width:100,formatter:function(value,row,index){
			        	if(row.parameters){
			        		return row.parameters.parameterType.name;
			        	}else{
			        		return '';
			        	}
			        }},
			        {field:'parameterCode',title:'参数代码',width:100,formatter:function(value,row,index){
			        	if(row.parameters){
			        		return row.parameters.code;
			        	}else{
			        		return '';
			        	}
			        }},
			        {field:'parameterName',title:'参数名称',width:100,formatter:function(value,row,index){
			        	if(row.parameters){
			        		return row.parameters.name;
			        	}else{
			        		return '';
			        	}
			        }},
			        {field:'rules',title:'参数取值规则',width:100,formatter:function(value,row,index){
			        	if(row.parameters){
			        		return row.parameters.rules;
			        	}else{
			        		return '';
			        	}
			        }}
			    ]],
			    queryParams:{
			    	workpieceId:$("#departmentDg").iDatagrid("getSelected").id
			    }
			});
		}); 
</script>
<div data-toggle="topjui-layout" data-options="fit:true">
	<div
		data-options="region:'center',title:'',fit:true,border:false,bodyCls:'border_right_bottom'">
		<table id="processesDeviceSiteTable"></table>
	</div>
</div>