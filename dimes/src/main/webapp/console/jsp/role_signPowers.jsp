<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script>
		$(function(){
			$('#powers').iDatagrid({
			    url:'role/queryPowers.do',
			    columns:[[
			        {field:'id',title:'id',checkbox:true},
			        {field:'powerCode',title:'权限码',width:100},
			        {field:'powerName',title:'权限名',width:100},
			        {field:'note',title:'说明',width:100}
			    ]],
			    queryParams:{
			    	roleId:$("#departmentDg").iDatagrid("getSelected").id
			    },
			    onLoadSuccess:function(data){
			    	 var rowData = data.rows;
			    	    $.each(rowData, function (idx, val) {
			    	        if (val.checked) {
			    	            $("#powers").datagrid("selectRow", idx);
			    	        }
			    	    });
			    }
			});
		});
	</script>
<div data-toggle="topjui-layout" data-options="fit:true">
	<div
		data-options="region:'center',title:'',fit:true,border:false,bodyCls:'border_right_bottom'">
		<table id="powers"></table>
	</div>
</div>