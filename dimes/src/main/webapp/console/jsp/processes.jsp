<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../../common/jsp/head.jsp" %>
</head>
<body>
<div data-toggle="topjui-layout" data-options="fit:true">
    <div data-options="region:'center',iconCls:'icon-reload',title:'',split:true,border:true,bodyCls:'border_top_none'">
        <div data-toggle="topjui-layout" data-options="fit:true">
            <div data-options="region:'center',title:'',fit:false,split:true,border:false,bodyCls:'border_bottom'"
                 style="height:60%;">
                <!-- datagrid表格 -->
                <table  
                data-toggle="topjui-datagrid"
                       data-options="id:'departmentDg',
                       url:'processes/queryProcessess.do',
                       singleSelect:true,
                       fitColumns:true,
                       pagination:true,
			           childTab: [{id:'southTabs'}]">
                    <thead>
                    <tr>
                        <th data-options="field:'id',title:'id',checkbox:false,width:'80px'"></th>
                        <th data-options="field:'processTypeId',title:'工序类别id',hidden:true,formatter:function(value,row,index){
                            if (row.processType) {
                                return row.processType.id;
                            } else {
                                return '';
                            }
                        }"></th>
                        <th data-options="field:'processTypeName',title:'工序类别',width:'180px',align:'center',formatter:function(value,row,index){
                            if (row.processType) {
                                return row.processType.name;
                            } else {
                                return '';
                            }
                        }"></th>
                        <th data-options="field:'code',title:'工序代码',width:'180px',align:'center'"></th>
                        <th data-options="field:'name',title:'工序名称',sortable:false"></th>
                        <th data-options="field:'note',title:'备注',sortable:false"></th>
                        <th data-options="field:'disabled',title:'停用',sortable:false,
                        formatter:function(value,row,index){
                        	if(value){
                        		return 'Y';
                        	}else{
                        		return 'N';
                        	}
                        }"></th>
                    </tr>
                    </thead>
                </table>
            </div>
            <div data-options="region:'south',fit:false,split:true,border:false"
                 style="height:40%">
                <div data-toggle="topjui-tabs"
                     data-options="id:'southTabs',
                     fit:true,
                     border:false,
                     parentGrid:{
                         type:'datagrid',
                         id:'departmentDg',
                         param:'processId:id'
                     }">
                    <div title="设备站点" data-options="id:'tab0',iconCls:'fa fa-th'">
                        <!-- datagrid表格 -->
                        <table 
                         data-toggle="topjui-datagrid"
                               data-options="id:'position',
                               initCreate: false,
                               singleSelect:true,
                               fitColumns:true,
						       url:'deviceSite/queryDeviceSitesByProcessId.do'">
                            <thead>
                            <tr>
                                <th data-options="field:'id',title:'id',checkbox:false"></th>
                                <th data-options="field:'deviceCode',title:'设备代码',sortable:false,
                                formatter:function(value,row,index){
                                	if(row.device){
                                		return row.device.code;
                                	}else{
                                		return '';
                                	}
                                }"></th>
                                <th data-options="field:'deviceName',title:'设备名称',sortable:false,
                                formatter:function(value,row,index){
                                	if(row.device){
                                		return row.device.name;
                                	}else{
                                		return '';
                                	}
                                }"></th>
                                <th data-options="field:'deviceUnitType',title:'规格型号',sortable:false,
                                formatter:function(value,row,index){
                                	if(row.device){
                                		return row.device.unitType;
                                	}else{
                                		return '';
                                	}
                                }"></th>
                                <th data-options="field:'code',title:'设备站点代码',sortable:false"></th>
                                <th data-options="field:'name',title:'设备站点名称',sortable:false"></th>
                                <th data-options="field:'note',title:'站点说明',sortable:false"></th>
                            </tr>
                            </thead>
                        </table>
                    </div>
                    <div title="工序参数" data-options="id:'tab1',iconCls:'fa fa-th'">
                        <!-- datagrid表格 -->
                        <table data-toggle="topjui-datagrid"
                               data-options="id:'relateDoc',
                               initCreate: false,
                               fitColumns:true,
						       url:'parameter/queryParametersByProcessId.do'">
                            <thead>
                            <tr>
                                <th data-options="field:'id',title:'id',checkbox:false,hidden:true"></th>
                                <th data-options="field:'code',title:'参数代码',sortable:false"></th>
                                <th data-options="field:'name',title:'参数名称',sortable:false"></th>
                                <!-- <th data-options="field:'rules',title:'取值规则',sortable:false"></th> -->
                                <th data-options="field:'kfc',title:'KPC',sortable:false"></th>
                                <th data-options="field:'note',title:'备注',sortable:false"></th>
                                <th data-options="field:'disabled',title:'停用',sortable:false,formatter:function(value,row,index){
                                	if(value){
                                		return 'Y';
                                	}else{
                                		return 'N';
                                	}
                                }"></th>
                            </tr>
                            </thead>
                        </table>
                    </div>
                    <div title="状态/故障代码" data-options="id:'tab2',iconCls:'fa fa-th'">
                        <!-- datagrid表格 -->
                        <table data-toggle="topjui-datagrid"
                               data-options="id:'statusTroubleCode',
                               initCreate: false,
                               fitColumns:true,
						       url:'statusTroubleCode/queryStatusTroubleCodesByProcessId.do'">
                            <thead>
                            <tr>
                                <th data-options="field:'id',title:'id',checkbox:false"></th>
                                <th data-options="field:'code',title:'状态/故障代码',sortable:false"></th>
                                <th data-options="field:'name',title:'状态/故障名称',sortable:false"></th>
                                <th data-options="field:'parameterCode',title:'参数代码',sortable:false"></th>
                                <th data-options="field:'parameterName',title:'参数名称',sortable:false"></th>
                                <th data-options="field:'conditions',title:'条件',sortable:false"></th>
                                <th data-options="field:'note',title:'备注',sortable:false"></th>
                                <th data-options="field:'disabled',title:'停用',sortable:false,formatter:function(value,row,index){
                                	if(value){
                                		return 'Y';
                                	}else{
                                		return 'N';
                                	}
                                }"></th>
                            </tr>
                            </thead>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 部门表格工具栏开始 -->
<div id="departmentDg-toolbar" class="topjui-toolbar"
     data-options="grid:{
           type:'datagrid',
           id:'departmentDg'
       }">
    <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method:'openDialog',
       extend: '#departmentDg-toolbar',
       iconCls: 'fa fa-plus',
       dialog:{
           id:'classesAddDialog',
           width:600,
           height:600,
           href:'console/jsp/processes_add.jsp',
           buttons:[
           	{text:'保存',handler:function(){
           			var code = $('#code').val();
           			if(code==null || ''===$.trim(code)){
           				return false;
           			}
           			
           			var name = $('#name').val();
           			if(name==null || ''===$.trim(name)){
           				return false;
           			}
           			var processTypeName = $('#processTypeName').val();
           			if(processTypeName==null || ''===$.trim(processTypeName)){
           				return false;
           			}
           			$.get('processes/addProcesses.do',{
           			code:code,
           			name:name,
           			'processType.name':processTypeName,
           			note:$('#note').val()
           			},function(data){
           				if(data.success){
	           				$('#classesAddDialog').iDialog('close');
	           				$('#departmentDg').iDatagrid('reload');
           				}else{
           					alert(data.msg);
           				}
           			});
           	},iconCls:'fa fa-plus',btnCls:'topjui-btn-normal'},
           	{text:'关闭',handler:function(){
           		$('#classesAddDialog').iDialog('close');
           	},iconCls:'fa fa-close',btnCls:'topjui-btn-normal'},
           ]}">新增</a>
    <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method: 'openDialog',
            extend: '#departmentDg-toolbar',
            iconCls: 'fa fa-pencil',
            dialog: {
            	id:'classEditDialog',
                width: 600,
                height: 400,
                href: 'console/jsp/processes_edit.jsp',
                url:'processes/queryProcessesById.do?id={id}',
                 buttons:[
           	{text:'编辑',handler:function(){
           			var code = $('#code').val();
           			var name = $('#name').val();
           			if(name==null || ''===$.trim(name)){
           				return false;
           			}
           			var processTypeName = $('#processTypeName').val();
           			if(processTypeName==null || ''===$.trim(processTypeName)){
           				return false;
           			}
           			$.get('processes/updateProcesses.do',{
           			id:$('#departmentDg').iDatagrid('getSelected').id,
           			code:code,
           			name:name,
           			'processType.name':processTypeName,
           			note:$('#note').val()
           			},function(data){
           				if(data.success){
	           				$('#classEditDialog').iDialog('close');
	           				$('#departmentDg').iDatagrid('reload');
           				}else{
           					alert(data.msg);
           				}
           			});
           	},iconCls:'fa fa-plus',btnCls:'topjui-btn-normal'},
           	{text:'关闭',handler:function(){
           		$('#classEditDialog').iDialog('close');
           	},iconCls:'fa fa-close',btnCls:'topjui-btn-normal'},
           ]
            }">编辑</a>
    <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method:'doAjax',
       extend: '#departmentDg-toolbar',
       iconCls:'fa fa-trash',
       url:'processes/deleteProcesses.do',
       grid: {uncheckedMsg:'请先勾选要删除的数据',id:'departmentDg',param:'id:id'}">删除</a>
    <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method:'doAjax',
       extend: '#departmentDg-toolbar',
       iconCls:'fa fa-stop',
       url:'processes/disabledProcesses.do',
       grid: {uncheckedMsg:'请选择要停用的工序',id:'departmentDg',param:'id:id'}">停用</a>
    </div>
<!-- 部门表格工具栏结束 -->
<!-- 职位表格工具栏开始 -->
<div id="position-toolbar" class="topjui-toolbar"
     data-options="grid:{
           type:'datagrid',
           id:'position'
       }">
    <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method:'openDialog',
       extend: '#position-toolbar',
       iconCls: 'fa fa-plus',
        parentGrid:{
               type:'datagrid',
               id:'departmentDg'
            },
       dialog:{
           id:'deviceAddDialog',
            width:620,
           height:500,
           href:'console/jsp/processes_deviceSite_add.jsp',
           buttons:[
           	{text:'保存',handler:function(){
           			var ids = $('#deviceTable').iDatagrid('getSelections');
           		var idsArray = new Array();
           		if(ids.length<=0){
           			alert('请选择要添加的设备!');
           		}else{
           			for(var i = 0;i < ids.length;i++){
           				idsArray.push(ids[i].id);
           			}
           			$.get('processes/addDeviceSite4Processes.do',{
           				processesId:$('#departmentDg').iDatagrid('getSelected').id,
           				deviceSiteIds:JSON.stringify(idsArray)
           			},function(data){
           				if(data.success){
           					$('#position').iDatagrid('reload');
           					$('#deviceTable').iDatagrid('reload');
           				}else{
           					alert(data.msg);
           				}
           			});
           		}
           	},iconCls:'fa fa-plus',btnCls:'topjui-btn-normal'},
           	{text:'关闭',handler:function(){
           		$('#deviceAddDialog').iDialog('close');
           	},iconCls:'fa fa-close',btnCls:'topjui-btn-normal'},
           ]
       }">新增</a>
    <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method:'doAjax',
       extend: '#position-toolbar',
       iconCls:'fa fa-trash',
       url:'processes/deleteDeviceSiteFromProcesses.do?processesId={parent.id}',
        parentGrid:{
        type:'datagrid',
        id:'departmentDg'
    },
       grid: {uncheckedMsg:'请先勾选要删除的数据',id:'position',param:'deviceSiteId:id'}">删除</a>
    </div>
<!-- 职位表格工具栏结束 -->
<!-- 相关文档表格工具栏开始 -->
<div id="relateDoc-toolbar" class="topjui-toolbar"
     data-options="grid:{
           type:'datagrid',
           id:'relateDoc'
       }">
    <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method:'openDialog',
       extend: '#relateDoc-toolbar',
         parentGrid:{
               type:'datagrid',
               id:'departmentDg'
            },
       iconCls: 'fa fa-plus',
              dialog:{
           id:'parameterAddDialog',
            width:700,
           height:500,
           href:'console/jsp/processes_parameter_add.jsp',
           buttons:[
           	{text:'保存',handler:function(){
           			var ids = $('#parameterTable').iDatagrid('getSelections');
           		var idsArray = new Array();
           		if(ids.length<=0){
           			alert('请选择要添加的设备!');
           		}else{
           			for(var i = 0;i < ids.length;i++){
           				idsArray.push(ids[i].id);
           			}
           			$.get('processes/addParameters4Processes.do',{
           				processesId:$('#departmentDg').iDatagrid('getSelected').id,
           				parameterIds:JSON.stringify(idsArray)
           			},function(data){
           				if(data.success){
           					$('#relateDoc').iDatagrid('reload');
           					$('#parameterTable').iDatagrid('reload');
           				}else{
           					alert(data.msg);
           				}
           			});
           		}
           	},iconCls:'fa fa-plus',btnCls:'topjui-btn-normal'},
           	{text:'关闭',handler:function(){
           		$('#parameterAddDialog').iDialog('close');
           	},iconCls:'fa fa-close',btnCls:'topjui-btn-normal'},
           ]
       }">新增</a>
    <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method:'doAjax',
       extend: '#relateDoc-toolbar',
       iconCls:'fa fa-trash',
       url:'processes/deleteParameterFromProcesses.do?processesId={parent.id}',
        parentGrid:{
        type:'datagrid',
        id:'departmentDg'
    },
       grid: {uncheckedMsg:'请先勾选要删除的数据',id:'relateDoc',param:'parametersId:id'}">删除</a>
</div>
<!-- 状态故障代码工具栏 -->
<div id="statusTroubleCode-toolbar" class="topjui-toolbar"
     data-options="grid:{
           type:'datagrid',
           id:'statusTroubleCode'
       }">
    <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method:'openDialog',
       extend: '#statusTroubleCode-toolbar',
       iconCls: 'fa fa-plus',
         parentGrid:{
               type:'datagrid',
               id:'departmentDg'
            },
           dialog:{
           id:'parameterAddDialog',
            width:600,
           height:500,
           href:'console/jsp/processes_statusTroubleCode_add.jsp',
           buttons:[
           	{text:'保存',handler:function(){
           			var code = $('#code').val();
           			if(code==null || ''===$.trim(code)){
           				return false;
           			}
           			
           			var name = $('#name').val();
           			if(name==null || ''===$.trim(name)){
           				return false;
           			}
           			$.get('statusTroubleCode/addStatusTroubleCode.do',{
           			code:code,
           			name:name,
           			parameterCode:$('#parameterCode').val(),
           			parameterName:$('#parameterName').val(),
           			conditions:$('#conditions').val(),
           			'processes.id':$('#departmentDg').iDatagrid('getSelected').id,
           			note:$('#note').val()
           			},function(data){
           				if(data.success){
	           				$('#parameterAddDialog').iDialog('close');
	           				$('#statusTroubleCode').iDatagrid('reload');
           				}else{
           					alert(data.msg);
           				}
           			});
           	},iconCls:'fa fa-plus',btnCls:'topjui-btn-normal'},
           	{text:'关闭',handler:function(){
           		$('#parameterAddDialog').iDialog('close');
           	},iconCls:'fa fa-close',btnCls:'topjui-btn-normal'},
           ]
       }">新增</a>
        <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method: 'openDialog',
            extend: '#statusTroubleCode-toolbar',
            iconCls: 'fa fa-pencil',
            dialog: {
            	id:'classEditDialog',
                width: 600,
                height: 400,
                href: 'console/jsp/processes_statusTroubleCode_edit.jsp',
                url:'statusTroubleCode/queryStatusTroubleCodeById.do?id={id}',
                 buttons:[
           	{text:'编辑',handler:function(){
           			var code = $('#code').val();
           			if(code==null || ''===$.trim(code)){
           				return false;
           			}
           			var name = $('#name').val();
           			if(name==null || ''===$.trim(name)){
           				return false;
           			}
           			$.get('statusTroubleCode/updateStatusTroubleCode.do',{
           			id:$('#id').val(),
           			code:code,
           			name:name,
           			parameterCode:$('#parameterCode').val(),
           			parameterName:$('#parameterName').val(),
           			conditions:$('#conditions').val(),
           			'processes.id':$('#departmentDg').iDatagrid('getSelected').id,
           			note:$('#note').val()
           			},function(data){
           				if(data.success){
	           				$('#classEditDialog').iDialog('close');
	           				$('#statusTroubleCode').iDatagrid('reload');
           				}else{
           					alert(data.msg);
           				}
           			});
           	},iconCls:'fa fa-plus',btnCls:'topjui-btn-normal'},
           	{text:'关闭',handler:function(){
           		$('#classEditDialog').iDialog('close');
           	},iconCls:'fa fa-close',btnCls:'topjui-btn-normal'},
           ]
            }">编辑</a>
    <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method:'doAjax',
       extend: '#statusTroubleCode-toolbar',
       iconCls:'fa fa-trash',
       url:'statusTroubleCode/deleteStatusTroubleCode.do',
       grid: {uncheckedMsg:'请先勾选要删除的数据',id:'statusTroubleCode',param:'id:id'}">删除</a>
</div>
<!-- 相关文档表格工具栏结束 -->
</body>
</html>