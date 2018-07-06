<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script>
$(function(){
	
	var row = $('#workSheetDetail').iEdatagrid("getSelected");
		// 可编辑工单 详情
	 	$('#workSheetDetailParameter').iEdatagrid({
		    url: 'workSheet/queryParametersByParameterRecordId.do',
		    autoSave:true,
		    queryParams:{
		    	processId : row.processId,
		    	deviceSiteId:row.deviceSiteId
		    },
		    updateUrl: 'workSheet/updateParameterRecord.do'
		    	/*  saveUrl:'',
		        destroyUrl: '' */
		}); 
		 if(row.parameterSource=='固定值'){
			 $('#workSheetDetailParameter').iEdatagrid("disableEditing");
		}
});
</script>
<div data-toggle="topjui-layout" data-options="fit:true">
	<div
		data-options="region:'center',title:'',fit:true,border:false,bodyCls:'border_right_bottom'">
		<table title="" id="workSheetDetailParameter">
			<thead>
				<tr>
					<th 
						data-options="field:'id',title:'id',checkbox:false,hidden:true,width:200,align:'center'"></th>
					<th 
						data-options="field:'parameterCode',title:'参数代码',checkbox:false,width:100,align:'center'"></th>
					<th 
						data-options="field:'parameterName',title:'参数名称',align:'center'"></th>
					<th class="editable" editor="{type:'numberbox'}"
						data-options="field:'upLine',title:'控制线UL',width:100,align:'center'"></th>
					<th class="editable" editor="{type:'numberbox'}"
						data-options="field:'lowLine',title:'控制线LL',width:100,align:'center'"></th>
					<th class="editable" editor="{type:'numberbox'}"
						data-options="field:'standardValue',title:'标准值',width:100,align:'center'"></th>
				</tr>
			</thead>
		</table>
	</div>
</div>
