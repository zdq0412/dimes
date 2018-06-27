<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../common/jsp/head.jsp"%>
</head>
<body>
	<div data-toggle="topjui-layout" data-options="fit:true">
		<div
			data-options="region:'west',title:'',split:true,border:false,width:'15%',iconCls:'fa fa-sitemap',headerCls:'border_right',bodyCls:'border_right'">
			<!-- treegrid表格 -->
			<table id="productionUnitTg" data-toggle="topjui-treegrid"
				data-options="id:'productionUnitTg',
			   idField:'id',
			   treeField:'name',
			   fitColumns:true,
			   fit:true,
			   singleSelect:true,
			   url:'productionUnit/queryTopProductionUnits.do',
			   childGrid:{
			   	   param:'pid:id',
                   grid:[
                       {type:'datagrid',id:'productionUnitDg'},
                   ]
			   }">
				<thead>
					<tr>
						<!--  <th data-options="field:'id',title:'id',checkbox:true"></th> -->
						<th data-options="field:'name',width:'100%',title:'生产单元'"></th>
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
						data-options="id:'productionUnitDg',
                       url:'productionUnit/queryProductionUnitsByParentId.do',
                       singleSelect:true,
                       fitColumns:true,
                       pagination:true,
                       parentGrid:{
                           type:'treegrid',
                           id:'productionUnitTg',
                       },
			           childTab: [{id:'southTabs'}]">
						<thead>
							<tr>
								<th
									data-options="field:'id',title:'id',checkbox:false,width:'80px',hidden:true"></th>
								<th
									data-options="field:'code',title:'生产单元代码',width:'180px',align:'center'"></th>
								<th data-options="field:'name',title:'生产单元名称',sortable:false"></th>
								<th data-options="field:'note',title:'备注',sortable:true"></th>
								<th
									data-options="field:'parentCode',title:'上级单元代码',sortable:true,
                        formatter:function(value,row,index){
                            if (row.parent) {
                                return row.parent.code;
                            } else {
                                return '';
                            }
                        }"></th>
								<th
									data-options="field:'parentName',title:'上级单元名称',sortable:true, 
                        formatter:function(value,row,index){
                            if (row.parent) {
                                return row.parent.name;
                            } else {
                                return '';
                            }
                        }"></th>
								<th
									data-options="field:'disabled',title:'停用',sortable:true,
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
                     singleSelect:true,
                     parentGrid:{
                         type:'datagrid',
                         id:'productionUnitDg',
                         param:'productionUnitId:id'
                     }">
						<div title="设备信息" data-options="id:'tab0',iconCls:'fa fa-th'">
							<!-- datagrid表格 -->
							<table data-toggle="topjui-datagrid"
								data-options="id:'position',
                               initCreate: false,
                               fitColumns:true,
                               singleSelect:true,
						       url:'device/queryDevicesByProductionUnitId.do'">
								<thead>
									<tr>
										<th data-options="field:'id',title:'id',checkbox:false"></th>
										<th data-options="field:'code',title:'设备代码',sortable:true"></th>
										<th data-options="field:'name',title:'设备名称',sortable:true"></th>
										<th data-options="field:'unitType',title:'规格型号',sortable:true"></th>
										<th data-options="field:'status',title:'设备状态',sortable:true"></th>
									</tr>
								</thead>
							</table>
						</div>
						<div title="相关文档" data-options="id:'tab1',iconCls:'fa fa-th'">
							<!-- datagrid表格 -->
							<table data-toggle="topjui-datagrid"
								data-options="id:'relateDoc',
                               initCreate: false,
                               fitColumns:true,
						       url:'department/queryRaletedDocuments.do'">
								<thead>
									<tr>
										<th data-options="field:'id',title:'id',checkbox:true"></th>
										<th
											data-options="field:'documentName',title:'文档名称',sortable:false"></th>
										<th data-options="field:'note',title:'文档说明',sortable:false"></th>
										<!-- <th data-options="field:'sex',title:'性别',sortable:true,
                                formatter:function(value,row,index){
                                    if (value == '1') {
                                        return '男';
                                    } else if (value == '2') {
                                        return '女';
                                    } else {
                                        return '';
                                    }
                                }"></th> -->
										<th
											data-options="field:'relatedCode',title:'关联代码',sortable:false"></th>
										<th
											data-options="field:'relatedName',title:'关联名称',sortable:false"></th>
										<th
											data-options="field:'uploadUsername',title:'上传人员',sortable:false"></th>
										<th
											data-options="field:'uploadDate',title:'上传时间',sortable:false,
                                 formatter:function(value,row,index){
                                    if (value) {
                                        var date = new Date(value);
                                        var month = date.getMonth()+1;
                                        var monthStr = ((month>=10)?month:('0' + month));
                                        
                                        var day = date.getDate();
                                        var dayStr = ((day>=10)?day:('0'+day));
                                        
                                        var hour = date.getHours();
                                        var hourStr = ((hour>=10)?hour:('0' + hour));
                                        
                                        var minute = date.getMinutes();
                                        var minuteStr = ((minute>=10)?minute:('0' +minute));
                                        
                                        var second = date.getSeconds();
                                        var secondStr = ((second>=10)?second:('0' +second));
                                        
                                        var dateStr = date.getFullYear() + '-' + monthStr + '-' + dayStr  + 
                                        				' ' + hourStr + ':' + minuteStr + ':' + secondStr;
                                        return dateStr;
                                    }else{
                                    	return '';
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

	<!-- 生产单元表格工具栏开始 -->
	<div id="productionUnitDg-toolbar" class="topjui-toolbar"
		data-options="grid:{
           type:'datagrid',
           id:'productionUnitDg'
       }">
		<a href="javascript:void(0)" data-toggle="topjui-menubutton"
			data-options="method:'openDialog',
       extend: '#productionUnitDg-toolbar',
       iconCls: 'fa fa-plus',
       parentGrid:{
               type:'treegrid',
               id:'productionUnitTg'
            },
       dialog:{
           id:'productionUnitAddDialog',
           width:600,
           height:400,
           href:'console/jsp/productionUnit_add.jsp',
           buttons:[
           	{text:'保存',handler:function(){
           			var productionUnitCode = $('#productionUnitCode').val();
           			if(productionUnitCode==null || ''===$.trim(productionUnitCode)){
           				return false;
           			}
           			
           			var productionUnitName = $('#productionUnitName').val();
           			if(productionUnitName==null || ''===$.trim(productionUnitName)){
           				return false;
           			}
           			$.get('productionUnit/addProductionUnit.do',{
           			code:productionUnitCode,
           			name:productionUnitName,
           			'parent.id':$('#productionUnitTg').iTreegrid('getSelected').id,
           			note:$('#productionUnitNote').val()
           			},function(data){
           				if(data.success){
	           				$('#productionUnitAddDialog').iDialog('close');
	           				$('#productionUnitDg').iDatagrid('reload');
	           				$('#productionUnitTg').iTreegrid('reload');
           				}else{
           					alert(data.msg);
           				}
           			});
           	},iconCls:'fa fa-plus',btnCls:'topjui-btn-normal'},
           	{text:'关闭',handler:function(){
           		$('#productionUnitAddDialog').iDialog('close');
           	},iconCls:'fa fa-close',btnCls:'topjui-btn-normal'},
           ]}">新增</a>
		<a href="javascript:void(0)" data-toggle="topjui-menubutton"
			data-options="method: 'openDialog',
            extend: '#productionUnitDg-toolbar',
            iconCls: 'fa fa-pencil',
            dialog: {
            	id:'productionUnitEditDialog',
                width: 600,
                height: 400,
                href: 'console/jsp/productionUnit_edit.jsp',
                url:'productionUnit/queryProductionUnitById.do?id={id}',
                 buttons:[
           	{text:'编辑',handler:function(){
           			var productionUnitCode = $('#productionUnitCode_edit').val();
           			var productionUnitName = $('#productionUnitName_edit').val();
           			if(productionUnitName==null || ''===$.trim(productionUnitName)){
           				return false;
           			}
           			$.get('productionUnit/updateProductionUnit.do',{
           			id:$('#productionUnitDg').iDatagrid('getSelected').id,
           			code:productionUnitCode,
           			name:productionUnitName,
           			'parent.id':$('#productionUnitTg').iTreegrid('getSelected').id,
           			note:$('#productionUnitNote_edit').val()
           			},function(data){
           				if(data.success){
	           				$('#productionUnitEditDialog').iDialog('close');
	           				$('#productionUnitDg').iDatagrid('reload');
	           				$('#productionUnitTg').iTreegrid('reload');
           				}else{
           					alert(data.msg);
           				}
           			});
           	},iconCls:'fa fa-plus',btnCls:'topjui-btn-normal'},
           	{text:'关闭',handler:function(){
           		$('#productionUnitEditDialog').iDialog('close');
           	},iconCls:'fa fa-close',btnCls:'topjui-btn-normal'},
           ]
            }">编辑</a>
		<a href="javascript:void(0)" data-toggle="topjui-menubutton"
			data-options="method:'doAjax',
       extend: '#productionUnitDg-toolbar',
       iconCls:'fa fa-trash',
       url:'productionUnit/deleteProductionUnit.do',
       grid: {uncheckedMsg:'请先勾选要删除的数据',id:'productionUnitDg',param:'id:id'}">删除</a>
		<a href="javascript:void(0)" data-toggle="topjui-menubutton"
			data-options="method:'doAjax',
       extend: '#productionUnitDg-toolbar',
       iconCls:'fa fa-stop',
       url:'productionUnit/disabledProductionUnit.do',
       grid: {uncheckedMsg:'请选择要停用的生产部门',id:'productionUnitDg',param:'id:id'}">停用</a>
	</div>
	<!-- 生产单元表格工具栏结束 -->
	<!-- 设备表格工具栏开始 -->
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
               id:'productionUnitDg'
            },
       dialog:{
           id:'deviceAddDialog',
            width:600,
           height:400,
           href:'console/jsp/productionUnit_device_add.jsp',
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
           			$.get('device/updateDeviceProductionUnit.do',{
           				productionUnitId:$('#productionUnitDg').iDatagrid('getSelected').id,
           				deviceIds:JSON.stringify(idsArray)
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
		<!--  <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method: 'openDialog',
            extend: '#device-toolbar',
            iconCls: 'fa fa-pencil',
            dialog: {
            	id:'positionEditDialog',
                width: 600,
                height: 400,
                href: 'console/jsp/productionUnit_device_edit.jsp',
                url:'position/queryPositionById.do?id={id}',
                 buttons:[
           	{text:'编辑',handler:function(){
           			var positionCode = $('#positionCode_edit').val();
           			var positionName = $('#positionName_edit').val();
           			if(positionName==null || ''===$.trim(positionName)){
           				return false;
           			}
           			$.get('position/updatePosition.do',{
           			id:$('#position').iDatagrid('getSelected').id,
           			code:positionCode,
           			name:positionName,
           			'department.id':$('#departmentDg').iDatagrid('getSelected').id,
           			note:$('#positionNote_edit').val()
           			},function(data){
           				if(data.success){
	           				$('#positionEditDialog').iDialog('close');
	           				$('#position').iDatagrid('reload');
           				}else{
           					alert(data.msg);
           				}
           			});
           	},iconCls:'fa fa-plus',btnCls:'topjui-btn-normal'},
           	{text:'关闭',handler:function(){
           		$('#positionEditDialog').iDialog('close');
           	},iconCls:'fa fa-close',btnCls:'topjui-btn-normal'},
           ]
            }">编辑</a> -->
		<a href="javascript:void(0)" data-toggle="topjui-menubutton"
			data-options="method:'doAjax',
       extend: '#position-toolbar',
       iconCls:'fa fa-trash',
       url:'device/updateDeviceProductionUnitNull.do',
       grid: {uncheckedMsg:'请先勾选要移除的数据',id:'position',param:'deviceId:id'}">删除</a>
		<!--  <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
      data-options="method:'doAjax',
       extend: '#position-toolbar',
       iconCls:'fa fa-stop',
       url:'device/disabledDevice.do',
       grid: {uncheckedMsg:'请选择要停用的设备',id:'position',param:'id:id'}">停用</a> -->
	</div>
	<!-- 设备表格工具栏结束 -->
	<!-- 相关文档表格工具栏开始 -->
	<div id="relateDoc-toolbar" class="topjui-toolbar"
		data-options="grid:{
           type:'datagrid',
           id:'relateDoc'
       }">
		<a href="javascript:void(0)" data-toggle="topjui-menubutton"
			data-options="method:'openDialog',
       extend: '#relateDoc-toolbar',
       iconCls: 'fa fa-plus',
       dialog:{
           id:'userAddDialog',
           href:_ctx + '/html/complex/dialog_add.html',
           buttonsGroup:[
               {text:'保存',url:_ctx + '/json/response/success.json',iconCls:'fa fa-plus',handler:'ajaxForm',btnCls:'topjui-btn-brown'}
           ]
       }">新增</a>
		<a href="javascript:void(0)" data-toggle="topjui-menubutton"
			data-options="method: 'openDialog',
            extend: '#relateDoc-toolbar',
            iconCls: 'fa fa-pencil',
            grid: {
                type: 'datagrid',
                id: 'userDg'
            },
            dialog: {
                width: 950,
                height: 500,
                href: _ctx + '/html/complex/user_edit.html?uuid={uuid}',
                url: _ctx + '/json/product/detail.json?uuid={uuid}',
                buttonsGroup: [
                    {
                        text: '更新',
                        url: _ctx + '/json/response/success.json',
                        iconCls: 'fa fa-save',
                        handler: 'ajaxForm',
                        btnCls: 'topjui-btn-green'
                    }
                ]
            }">编辑</a>
		<a href="javascript:void(0)" data-toggle="topjui-menubutton"
			data-options="method:'doAjax',
       extend: '#relateDoc-toolbar',
       iconCls:'fa fa-trash',
       url:_ctx + '/json/response/success.json',
       grid: {uncheckedMsg:'请先勾选要删除的数据',param:'uuid:uuid,code:code'}">删除</a>
		<!--     <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method:'filter',
       extend: '#userDg-toolbar'
       ">过滤</a>
    <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method:'search',
       extend: '#userDg-toolbar'">查询</a> -->
		<a href="javascript:void(0)" data-toggle="topjui-menubutton"
			data-options="method:'search',
       extend: '#relateDoc-toolbar'">停用</a>
	</div>
	<!-- 相关文档表格工具栏结束 -->
</body>
</html>