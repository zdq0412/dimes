<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../common/jsp/head.jsp"%>
</head>
<body>

	<div data-toggle="topjui-layout" data-options="fit:true">

		<div
			data-options="region:'west',title:'',split:true,border:false,width:'15%',iconCls:'fa fa-sitemap',headerCls:'border_right',bodyCls:'border_right'">
			<!-- treegrid表格 -->
			<table data-toggle="topjui-treegrid"
				data-options="id:'departmentTg',
			   idField:'id',
			   treeField:'name',
			   fitColumns:true,
			   fit:true,
			   singleSelect:true,
			   url:'workpieceType/queryTopWorkpieceTypes.do',
			   childGrid:{
			   	   param:'typeId:id',
                   grid:[
                       {type:'datagrid',id:'departmentDg'},
                   ]
			   }">
				<thead>
					<tr>
						<!--  <th data-options="field:'id',title:'id',checkbox:true"></th> -->
						<th data-options="field:'name',width:'100%',title:'工件类别'"></th>
					</tr>
				</thead>
			</table>
		</div>
		<div
			data-options="region:'center',iconCls:'icon-reload',title:'',split:true,border:true,bodyCls:'border_top_none'">
			<div data-toggle="topjui-layout" data-options="fit:true">
				<div
					data-options="region:'center',title:'',fit:false,split:true,border:false,bodyCls:'border_bottom'"
					style="height: 60%;">
					<!-- datagrid表格 -->
					<table data-toggle="topjui-datagrid"
						data-options="id:'departmentDg',
                       url:'workpiece/queryWorkpiecesByTypeId.do',
                       singleSelect:true,
                       fitColumns:true,
                       pagination:true,
                       parentGrid:{
                       	type:'treegird',
                       	id:'departmentTg'
                       }, childTab: [{id:'southTabs'}]">
						<thead>
							<tr>
								<th
									data-options="field:'id',title:'id',checkbox:false,width:'80px',hidden:true"></th>
								<th
									data-options="field:'code',title:'工件代码',width:'180px',align:'center'"></th>
								<th
									data-options="field:'name',title:'工件名称',width:'180px',align:'center',sortable:false"></th>
								<th
									data-options="field:'unitType',title:'规格型号',width:'180px',align:'center',sortable:false"></th>
								<th
									data-options="field:'graphNumber',title:'图号',width:'180px',align:'center',sortable:false"></th>
								<th
									data-options="field:'customerGraphNumber',title:'客户图号',width:'180px',align:'center',sortable:false"></th>
								<th
									data-options="field:'version',title:'版本号',width:'180px',align:'center',sortable:false"></th>
								<th
									data-options="field:'measurementUnit',title:'计量单位',width:'180px',align:'center',sortable:false"></th>
								<th
									data-options="field:'note',title:'备注',width:'180px',align:'center',sortable:false"></th>
								<th
									data-options="field:'disabled',width:'180px',title:'停用',sortable:false,
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
					style="height: 40%">
					<div data-toggle="topjui-tabs"
						data-options="id:'southTabs',
                     fit:true,
                     border:false,
                     parentGrid:{
                         type:'datagrid',
                         id:'departmentDg',
                         param:'workpieceId:id'
                     }">
						<div title="工艺路线" data-options="id:'tab0',iconCls:'fa fa-th'">
							<!-- datagrid表格 -->
							<table data-toggle="topjui-datagrid"
								data-options="id:'position',
                               initCreate: false,
                               singleSelect:true,
                               fitColumns:true,
						       url:'processes/queryProcessessByWorkpieceId.do'">
								<thead>
									<tr>
										<th
											data-options="field:'id',title:'id',checkbox:false,hidden:true,width:200,align:'center'"></th>
										<th
											data-options="field:'processTypeName',title:'工序类别',checkbox:false,width:200,align:'center',formatter:function(value,row,index){
			        	if(row.processType){
			        		return row.processType.name;
			        	}else{
			        		return '';
			        	}
			        }"></th>
										<th
											data-options="field:'code',title:'工序代码',width:200,align:'center'"></th>
										<th
											data-options="field:'name',title:'工序名称',width:200,align:'center'"></th>
										<th
											data-options="field:'note',title:'备注',width:200,align:'center'"></th>
									</tr>
								</thead>
							</table>
						</div>
						<div title="工序设备" data-options="id:'tab1',iconCls:'fa fa-th'">
							<!-- datagrid表格 -->
							<table data-toggle="topjui-datagrid"
								data-options="id:'relateDoc',
                               initCreate: false,
                               fitColumns:true,
						       url:'workpiece/queryWorkpieceProcessDeviceSiteMappingsByWorkpieceId.do'">
								<thead>
									<tr>
										<th
											data-options="field:'id',title:'id',checkbox:false,hidden:true,width:80"></th>
										<th
											data-options="field:'processTypeName',title:'工序类别',width:80,formatter:function(value,row,index){
											if(row.workpieceProcess.process){
												return row.workpieceProcess.process.processType.name;
											}else{
												return '';
											}
										}"></th>
										<th
											data-options="field:'processCode',title:'工序代码',width:80,formatter:function(value,row,index){
											if(row.workpieceProcess.process){
												return row.workpieceProcess.process.code;
											}else{
												return '';
											}
										}"></th>
										<th
											data-options="field:'processName',title:'工序名称',width:80,formatter:function(value,row,index){
											if(row.workpieceProcess.process){
												return row.workpieceProcess.process.name;
											}else{
												return '';
											}
										}"></th>
										<th
											data-options="field:'deviceCode',title:'设备代码',width:80,formatter:function(value,row,index){
											if(row.deviceSite){
												return row.deviceSite.device.code;
											}else{
												return '';
											}
										}"></th>
										<th
											data-options="field:'deviceName',title:'设备名称',width:80,formatter:function(value,row,index){
											if(row.deviceSite){
												return row.deviceSite.device.name;
											}else{
												return '';
											}
										}"></th>
										<th
											data-options="field:'deviceUnitType',title:'规格型号',width:80,formatter:function(value,row,index){
											if(row.deviceSite){
												return row.deviceSite.device.unitType;
											}else{
												return '';
											}
										}"></th>
										<th
											data-options="field:'deviceSite.code',title:'站点代码',width:80,formatter:function(value,row,index){
											if(row.deviceSite){
												return row.deviceSite.code;
											}else{
												return '';
											}
										}"></th>
										<th
											data-options="field:'deviceSite.name',title:'站点名称',formatter:function(value,row,index){
											if(row.deviceSite){
												return row.deviceSite.name;
											}else{
												return '';
											}
										}"></th>
										<th
											data-options="field:'processingBeat',title:'加工节拍(s)',width:80,sortable:false"></th>
									</tr>
								</thead>
							</table>
						</div>
						<div title="工序参数" data-options="id:'tab2',iconCls:'fa fa-th'">
							<!-- datagrid表格 -->
							<table data-toggle="topjui-datagrid"
								data-options="id:'statusTroubleCode',
                               initCreate: false,
                               fitColumns:true,
						       url:'workpiece/queryWorkpieceProcessParameterMappingsByWorkpieceId.do'">
								<thead>
									<tr>
										<th
											data-options="field:'id',title:'id',checkbox:false,hidden:true,width:50"></th>
										<th
											data-options="field:'processTypeName',title:'工序类别',width:50,formatter:function(value,row,index){
											if(row.workpieceProcess.process){
												return row.workpieceProcess.process.processType.name;
											}else{
												return '';
											}
										}"></th>
										<th
											data-options="field:'processCode',title:'工序代码',width:50,formatter:function(value,row,index){
											if(row.workpieceProcess.process){
												return row.workpieceProcess.process.code;
											}else{
												return '';
											}
										}"></th>
										<th
											data-options="field:'processName',title:'工序名称',width:50,formatter:function(value,row,index){
											if(row.workpieceProcess.process){
												return row.workpieceProcess.process.name;
											}else{
												return '';
											}
										}"></th>
										<th
											data-options="field:'parameterTypeName',title:'参数类别',width:50,formatter:function(value,row,index){
											if(row.parameter){
												return row.parameter.parameterType.name;
											}else{
												return '';
											}
										}"></th>
										<th
											data-options="field:'parameterCode',title:'参数代码',width:50,formatter:function(value,row,index){
											if(row.parameter){
												return row.parameter.code;
											}else{
												return '';
											}
										}"></th>
										<th
											data-options="field:'parameterName',title:'参数名称',width:50,formatter:function(value,row,index){
											if(row.parameter){
												return row.parameter.name;
											}else{
												return '';
											}
										}"></th>
										<th
											data-options="field:'rule',title:'参数取值规则 ',width:50,formatter:function(value,row,index){
											if(row.parameter){
												return row.parameter.rules;
											}else{
												return '';
											}
										}"></th>
										<th
											data-options="field:'unit',title:'单位',width:50"></th>
										<th data-options="field:'upLine',title:'控制线UL',width:50,sortable:false"></th>
										<th data-options="field:'lowLine',title:'控制线LL',width:50,sortable:false"></th>
										<th data-options="field:'standardValue',title:'标准值',width:50,sortable:false"></th>
										<th data-options="field:'note',title:'备注',width:50,sortable:false"></th>
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
		<a href="javascript:void(0)" data-toggle="topjui-menubutton"
			data-options="method:'openDialog',
       extend: '#departmentDg-toolbar',
       iconCls: 'fa fa-plus',
       parentGrid:{
       	type:'treegrid',
       	id:'departmentTg'
       },
       dialog:{
           id:'classesAddDialog',
           width:600,
           height:700,
           href:'console/jsp/workpiece_add.jsp',
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
           			$.get('workpiece/addWorkpiece.do',{
           			code:code,
           			name:name,
           			unitType:$('#unitType').val(),
           			graphNumber:$('#graphNumber').val(),
           			customerGraphNumber:$('#customerGraphNumber').val(),
           			version:$('#version').val(),
           			measurementUnit:$('#measurementUnit').val(),
           			'workpieceType.id':$('#departmentTg').iTreegrid('getSelected').id,
           			note:$('#note').val()
           			},function(data){
           				if(data.success){
	           				$('#classesAddDialog').iDialog('close');
	           				$('#departmentDg').iDatagrid('reload');
	           				$('#departmentTg').iTreegrid('reload');
           				}else{
           					alert(data.msg);
           				}
           			});
           	},iconCls:'fa fa-plus',btnCls:'topjui-btn-normal'},
           	{text:'关闭',handler:function(){
           		$('#classesAddDialog').iDialog('close');
           	},iconCls:'fa fa-close',btnCls:'topjui-btn-normal'},
           ]}">新增</a>
		<a href="javascript:void(0)" data-toggle="topjui-menubutton"
			data-options="method: 'openDialog',
            extend: '#departmentDg-toolbar',
            iconCls: 'fa fa-pencil',
            dialog: {
            	id:'classEditDialog',
                width: 600,
                height: 700,
                href: 'console/jsp/workpiece_edit.jsp',
                url:'workpiece/queryWorkpieceById.do?id={id}',
                 buttons:[
           	{text:'编辑',handler:function(){
           			var code = $('#code').val();
           			var name = $('#name').val();
           			if(name==null || ''===$.trim(name)){
           				return false;
           			}
           			$.get('workpiece/updateWorkpiece.do',{
           			id:$('#departmentDg').iDatagrid('getSelected').id,
           			code:code,
           			name:name,
           			unitType:$('#unitType').val(),
           			graphNumber:$('#graphNumber').val(),
           			customerGraphNumber:$('#customerGraphNumber').val(),
           			version:$('#version').val(),
           			measurementUnit:$('#measurementUnit').val(),
           			'workpieceType.id':$('#departmentTg').iTreegrid('getSelected').id,
           			note:$('#note').val()
           			},function(data){
           				if(data.success){
	           				$('#classEditDialog').iDialog('close');
	           				$('#departmentDg').iDatagrid('reload');
	           				$('#departmentTg').iTreegrid('reload');
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
		<a href="javascript:void(0)" data-toggle="topjui-menubutton"
			data-options="method:'doAjax',
       extend: '#departmentDg-toolbar',
       iconCls:'fa fa-trash',
       url:'workpiece/deleteWorkpiece.do',
       grid: {uncheckedMsg:'请先勾选要删除的数据',id:'departmentDg',param:'id:id'}">删除</a>
		<a href="javascript:void(0)" data-toggle="topjui-menubutton"
			data-options="method:'doAjax',
       extend: '#departmentDg-toolbar',
       iconCls:'fa fa-stop',
       url:'workpiece/disabledWorkpiece.do',
       grid: {uncheckedMsg:'请选择要停用的数据',id:'departmentDg',param:'id:id'}">停用</a>
	</div>
	<!-- 职位表格工具栏开始 -->
	<div id="position-toolbar" class="topjui-toolbar"
		data-options="grid:{
           type:'datagrid',
           id:'position'
       }">
		<a href="javascript:void(0)" data-toggle="topjui-menubutton"
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
           href:'console/jsp/workpiece_processes_add.jsp',
           buttons:[
           	{text:'保存',handler:function(){
           			var ids = $('#processesTable').iDatagrid('getSelections');
           		var idsArray = new Array();
           		if(ids.length<=0){
           			alert('请选择要添加的工序!');
           		}else{
           			for(var i = 0;i < ids.length;i++){
           				idsArray.push(ids[i].id);
           			}
           			$.get('workpiece/addProcesses4Workpiece.do',{
           				workpieceId:$('#departmentDg').iDatagrid('getSelected').id,
           				processesId:JSON.stringify(idsArray)
           			},function(data){
           				if(data.success){
           					$('#position').iDatagrid('reload');
           					$('#processesTable').iDatagrid('reload');
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
		<a href="javascript:void(0)" data-toggle="topjui-menubutton"
			data-options="method:'doAjax',
       extend: '#position-toolbar',
       iconCls:'fa fa-trash',
       url:'workpiece/deleteProcessesFromWorkpiece.do?workpieceId={parent.id}',
       parentGrid:{
        type:'datagrid',
        id:'departmentDg'
    },
       grid: {uncheckedMsg:'请先勾选要删除的数据',id:'position',param:'processesId:id'}">删除</a>
	</div>
	<!-- 职位表格工具栏结束 -->
	<!-- 相关文档表格工具栏开始 -->
	<div id="relateDoc-toolbar" class="topjui-toolbar"
		data-options="grid:{
           type:'datagrid',
           id:'relateDoc'
       }">
		<a href="javascript:void(0)" data-toggle="topjui-menubutton"
			data-options="method:'openDialog',
      	 extend: '#relateDoc-toolbar',
         parentGrid:{
               type:'datagrid',
               id:'departmentDg'
            },
       iconCls: 'fa fa-plus',
              dialog:{
           id:'parameterAddDialog',
            width:900,
           height:500,
           href:'console/jsp/workpiece_processDeviceSite_add.jsp',
           buttons:[
           	{text:'保存',handler:function(){
           			var ids = $('#processesDeviceSiteTable').iDatagrid('getSelections');
           		var idsArray = new Array();
           		if(ids.length<=0){
           			alert('请选择要添加的数据!');
           		}else{
           			for(var i = 0;i < ids.length;i++){
           				idsArray.push(ids[i].id);
           			}
           			$.get('workpiece/addProcessDeviceSite4Workpiece.do',{
           				workpieceId:$('#departmentDg').iDatagrid('getSelected').id,
           				processDeviceSiteIds:JSON.stringify(idsArray)
           			},function(data){
           				if(data.success){
           					$('#relateDoc').iDatagrid('reload');
           					$('#processesDeviceSiteTable').iDatagrid('reload');
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
		<a href="javascript:void(0)" data-toggle="topjui-menubutton"
			data-options="method:'openDialog',
      	 extend: '#relateDoc-toolbar',
         parentGrid:{
               type:'datagrid',
               id:'departmentDg'
            },
       iconCls: 'fa fa-pencil',
              dialog:{
           id:'parameterUpdateDialog',
            width:600,
           height:300,
           href:'console/jsp/workpiece_processDeviceSite_edit.jsp',
           url:'workpiece/queryWorkpieceProcessDeviceSiteMappingById.do?id={id}',
           buttons:[
           	{text:'修改',handler:function(){
           		
           	
           			$.get('workpiece/updateWorkpieceProcessDeviceSiteMapping.do',{
           				id:$('#relateDoc').iDatagrid('getSelected').id,
           				processingBeat:$('#processingBeat').val()
           			},function(data){
           				if(data.success){
           					$('#relateDoc').iDatagrid('reload');
           					$('#parameterUpdateDialog').iDialog('close');
           				}else{
           					alert(data.msg);
           				}
           			}); 
           	},iconCls:'fa fa-pencil',btnCls:'topjui-btn-normal'},
           	{text:'关闭',handler:function(){
           		$('#parameterUpdateDialog').iDialog('close');
           	},iconCls:'fa fa-close',btnCls:'topjui-btn-normal'}
           ]
       }">修改加工节拍</a>
		<a href="javascript:void(0)" data-toggle="topjui-menubutton"
			data-options="method:'doAjax',
       extend: '#relateDoc-toolbar',
       iconCls:'fa fa-trash',
       url:'workpiece/deleteProcessDeviceSiteFromWorkpiece.do',
        parentGrid:{
        type:'datagrid',
        id:'departmentDg'
    },
       grid: {uncheckedMsg:'请先勾选要删除的数据',id:'relateDoc',param:'id:id'}">删除</a>
	</div>
	<!-- 状态故障代码工具栏 -->
	<div id="statusTroubleCode-toolbar" class="topjui-toolbar"
		data-options="grid:{
           type:'datagrid',
           id:'statusTroubleCode'
       }">
		<a href="javascript:void(0)" data-toggle="topjui-menubutton"
			data-options="method:'openDialog',
       extend: '#statusTroubleCode-toolbar',
       iconCls: 'fa fa-plus',
         parentGrid:{
               type:'datagrid',
               id:'departmentDg'
            },
           dialog:{
           id:'parameterAddDialog',
            width:900,
           height:500,
           href:'console/jsp/workpiece_processParameter_add.jsp',
           buttons:[
           	{text:'保存',handler:function(){
           			var ids = $('#processesDeviceSiteTable').iDatagrid('getSelections');
           		var idsArray = new Array();
           		if(ids.length<=0){
           			alert('请选择要添加的数据!');
           		}else{
           			for(var i = 0;i < ids.length;i++){
           				idsArray.push(ids[i].id);
           			}
           			$.get('workpiece/addProcessParameter4Workpiece.do',{
           				workpieceId:$('#departmentDg').iDatagrid('getSelected').id,
           				processDeviceSiteIds:JSON.stringify(idsArray)
           			},function(data){
           				if(data.success){
           					$('#statusTroubleCode').iDatagrid('reload');
           					$('#processesDeviceSiteTable').iDatagrid('reload');
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
		<a href="javascript:void(0)" data-toggle="topjui-menubutton"
			data-options="method: 'openDialog',
            extend: '#statusTroubleCode-toolbar',
            iconCls: 'fa fa-pencil',
            dialog: {
            	id:'classEditDialog',
                width: 600,
                height: 400,
                href: 'console/jsp/workpiece_processParameter_edit.jsp',
                url:'workpiece/queryWorkpieceProcessParameterMappingById.do?id={id}',
                 buttons:[
           	{text:'编辑',handler:function(){
           			$.get('workpiece/updateWorkpieceProcessParameterMapping.do',{
           				id:$('#statusTroubleCode').iDatagrid('getSelected').id,
           				unit:$('#unit').val(),
           				upLine:$('#upLine').val(),
           				lowLine:$('#lowLine').val(),
           				standardValue:$('#standardValue').val(),
           				note:$('#note').val()
           			},function(data){
           			if(data.success){
           					$('#statusTroubleCode').iDatagrid('reload');
           					$('#classEditDialog').iDialog('close');
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
		<a href="javascript:void(0)" data-toggle="topjui-menubutton"
			data-options="method:'doAjax',
       extend: '#statusTroubleCode-toolbar',
       iconCls:'fa fa-trash',
       url:'workpiece/deleteProcessParameterFromWorkpiece.do',
       grid: {uncheckedMsg:'请先勾选要删除的数据',id:'statusTroubleCode',param:'id:id'}">删除</a>
	</div>
</body>
</html>