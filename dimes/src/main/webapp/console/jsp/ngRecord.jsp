<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../common/jsp/head.jsp"%>
</head>
<body>
	<div data-toggle="topjui-layout" data-options="fit:true">
		<div
			data-options="region:'west',title:'',split:true,border:false,width:'15%',iconCls:'fa fa-sitemap',headerCls:'border_right',bodyCls:'border_right'">
			<table id="departmentTg" data-toggle="topjui-treegrid"
				data-options="id:'departmentTg',
			   idField:'id',
			   treeField:'name',
			   fitColumns:true,
			   fit:true,
			   singleSelect:true,
			   url:'productionUnit/queryDeviceSiteTree.do',
			   childGrid:{
			   	   param:'deviceSiteId:id',
                   grid:[
                       {type:'datagrid',id:'departmentDg'},
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
						data-options="id:'departmentDg',
                       url:'ngRecord/queryNGRecordByDeviceSiteId.do',
                       singleSelect:true,
                       fitColumns:true,
                       pagination:true,
                       parentGrid:{
                           type:'treegrid',
                           id:'departmentTg',
                       },
			           childTab: [{id:'southTabs'}]">
						<thead>
							<tr>
								<th
									data-options="field:'id',title:'id',checkbox:false,width:'80px',hidden:true"></th>
								<th
									data-options="field:'no',title:'工单号',sortable:false"></th>
								<th
									data-options="field:'workpieceCode',title:'工件代码',sortable:false"></th>
								<th	data-options="field:'workpieceName',title:'工件名称',sortable:false"></th>
								<th
									data-options="field:'processCode',title:'工序代码',sortable:false"></th>
								<th	data-options="field:'processName',title:'工序名称',sortable:false"></th>
								<th
									data-options="field:'unitType',title:'规格型号',sortable:false"></th>
								<th
									data-options="field:'customerGraphNumber',title:'客户 图号',sortable:false"></th>
								<th
									data-options="field:'graphNumber',title:'图号',sortable:false"></th>
								<th data-options="field:'version',title:'版本号',sortable:false"></th>
								<th data-options="field:'batchNumber',title:'批号',sortable:false"></th>
								<th data-options="field:'stoveNumber',title:'炉号',sortable:false"></th>
								<th data-options="field:'ngCount',title:'数量',sortable:false"></th>
								<th
									data-options="field:'ngTypeName',title:'原因类别',sortable:false"></th>
								<th
									data-options="field:'ngReason',title:'原因',sortable:false"></th>
								<th
									data-options="field:'processingMethod',title:'处理方法',sortable:false"></th>
								<th data-options="field:'inputUsername',title:'录入人',sortable:false"></th>
																<th
									data-options="field:'inputDate',title:'录入时间',align:'center',formatter:function(value,row,index){
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
								<th data-options="field:'auditorName',title:'审核人',sortable:false"></th>
								<th	data-options="field:'auditDate',title:'审核时间',align:'center',formatter:function(value,row,index){
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
								<th data-options="field:'reviewerName',title:'复核人',sortable:false"></th>
								<th	data-options="field:'reviewDate',title:'复核时间',align:'center',formatter:function(value,row,index){
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
								<th data-options="field:'confirmUsername',title:'确认人',sortable:false"></th>
								<th	data-options="field:'confirmDate',title:'确认时间',align:'center',formatter:function(value,row,index){
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
				<div data-options="region:'south',fit:false,split:true,border:false"
					style="height: 40%">
					<div data-toggle="topjui-tabs"
						data-options="id:'southTabs',
                     fit:true,
                     border:false,
                     singleSelect:true,
                     parentGrid:{
                         type:'datagrid',
                         id:'departmentDg',
                         param:'id:id'
                     }">
						<!--  <div title="岗位信息" data-options="id:'tab0',iconCls:'fa fa-th'">
                        datagrid表格
                        <table 
                         data-toggle="topjui-datagrid"
                               data-options="id:'position',
                               initCreate: false,
                               fitColumns:true,
						       url:'position/queryPositionsByDepartmentId.do'">
                            <thead>
                            <tr>
                                <th data-options="field:'id',title:'id',checkbox:true"></th>
                                <th data-options="field:'code',title:'岗位代码',sortable:true"></th>
                                <th data-options="field:'name',title:'岗位名称',sortable:true"></th>
                                <th data-options="field:'note',title:'备注',sortable:true"></th>
                                <th data-options="field:'deptCode',title:'部门代码',sortable:true,
                                formatter:function(value,row,index){
                                    if (row.department) {
                                        return row.department.code;
                                    }else {
                                        return '';
                                    }
                                }"></th>
                                <th data-options="field:'deptName',title:'部门名称',sortable:true,
                                formatter:function(value,row,index){
                                    if (row.department) {
                                        return row.department.name;
                                    }else {
                                        return '';
                                    }
                                }"></th>
                                <th data-options="field:'disabled',title:'停用',sortable:true,
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
                    </div> -->
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
       	id:'departmentTg',
       	params:'deviceSiteId:id'
       },
       dialog:{
           id:'departmentAddDialog',
           width:700,
           height:800,
           href:'console/jsp/ngRecord_add.jsp',
           buttons:[
           	{text:'保存',handler:function(){
           			$.get('ngRecord/addNGRecord.do',{
           			occurDate:$('#occurDate').val(),
           			no:$('#no').val(),
           			'deviceSiteId':$('#departmentTg').iTreegrid('getSelected').id,
           			'deviceSiteCode':$('#departmentTg').iTreegrid('getSelected').code,
           			'deviceSiteName':$('#departmentTg').iTreegrid('getSelected').name,
           			workpieceCode:$('#workpieceCode').val(),
           			workpieceName:$('#workpieceName').val(),
           			processId:$('#processId').val(),
           			processCode:$('#processCode').val(),
           			processName:$('#processName').val(),
           			unitType:$('#unitType').val(),
           			customerGraphNumber:$('#customerGraphNumber').val(),
           			graphNumber:$('#graphNumber').val(),
           			version:$('#version').val(),
           			batchNumber:$('#batchNumber').val(),
           			stoveNumber:$('#stoveNumber').val(),
           			ngTypeId:$('#ngTypeId').val(),
           			ngTypeName:$('#ngTypeName').val(),
           			ngReason:$('#ngReason').val(),
           			ngReasonId:$('#ngReasonId').val(),
           			processingMethod:$('#processingMethod').val(),
           			ngCount:$('#ngCount').val()
           			},function(data){
           				if(data.success){
	           				$('#departmentAddDialog').iDialog('close');
	           				$('#departmentDg').iDatagrid('reload');
           				}else{
           					alert(data.msg);
           				}
           			});
           	},iconCls:'fa fa-plus',btnCls:'topjui-btn-normal'},
           	{text:'关闭',handler:function(){
           		$('#departmentAddDialog').iDialog('close');
           	},iconCls:'fa fa-close',btnCls:'topjui-btn-normal'},
           ]}">新增</a>
		<a href="javascript:void(0)" data-toggle="topjui-menubutton"
			data-options="method: 'openDialog',
            extend: '#departmentDg-toolbar',
            iconCls: 'fa fa-pencil',
            dialog: {
            	id:'departmentEditDialog',
                width:700,
                height: 800,
                href: 'console/jsp/ngRecord_edit.jsp',
                url:'ngRecord/queryNGRecordById.do?id={id}',
                 buttons:[
           	{text:'编辑',handler:function(){
           			$.get('ngRecord/updateNGRecord.do',{
           			id:$('#departmentDg').iDatagrid('getSelected').id,
           			occurDate:$('#occurDate').val(),
           			no:$('#no').val(),
           			'deviceSiteId':$('#departmentDg').iDatagrid('getSelected').deviceSiteId,
           			'deviceSiteCode':$('#departmentDg').iDatagrid('getSelected').deviceSiteCode,
           			'deviceSiteName':$('#departmentDg').iDatagrid('getSelected').deviceSiteName,
           			workpieceCode:$('#workpieceCode').val(),
           			workpieceName:$('#workpieceName').val(),
           			processId:$('#processId').val(),
           			processCode:$('#processCode').val(),
           			processName:$('#processName').val(),
           			unitType:$('#unitType').val(),
           			customerGraphNumber:$('#customerGraphNumber').val(),
           			graphNumber:$('#graphNumber').val(),
           			version:$('#version').val(),
           			batchNumber:$('#batchNumber').val(),
           			stoveNumber:$('#stoveNumber').val(),
           			ngTypeId:$('#ngTypeId').val(),
           			ngTypeName:$('#ngTypeName').val(),
           			ngReason:$('#ngReason').val(),
           			ngReasonId:$('#ngReasonId').val(),
           			processingMethod:$('#processingMethod').val(),
           			ngCount:$('#ngCount').val()
           			},function(data){
           				if(data.success){
	           				$('#departmentEditDialog').iDialog('close');
	           				$('#departmentDg').iDatagrid('reload');
           				}else{
           					alert(data.msg);
           				}
           			});
           	},iconCls:'fa fa-plus',btnCls:'topjui-btn-normal'},
           	{text:'关闭',handler:function(){
           		$('#departmentEditDialog').iDialog('close');
           	},iconCls:'fa fa-close',btnCls:'topjui-btn-normal'},
           ]
            }">编辑</a>

		<a href="javascript:void(0)" data-toggle="topjui-menubutton"
			data-options="method:'doAjax',
       extend: '#departmentDg-toolbar',
       iconCls:'fa fa-trash',
       url:'ngRecord/deleteNGRecord.do',
       grid: {uncheckedMsg:'请先勾选要删除的数据',id:'departmentDg',param:'id:id'}">删除</a>
		<a href="javascript:void(0)" data-toggle="topjui-menubutton"
			data-options="method:'doAjax',
       extend: '#departmentDg-toolbar',
       iconCls:'fa fa-trash',
       url:'ngRecord/auditNGRecord.do',
       grid: {uncheckedMsg:'请先勾选要审核的数据',id:'departmentDg',param:'id:id'}">审核</a>
		<a href="javascript:void(0)" data-toggle="topjui-menubutton"
			data-options="method:'doAjax',
       extend: '#departmentDg-toolbar',
       iconCls:'fa fa-trash',
       url:'ngRecord/reviewNGRecord.do',
       grid: {uncheckedMsg:'请先勾选要复核的数据',id:'departmentDg',param:'id:id'}">复核</a>
		<a href="javascript:void(0)" data-toggle="topjui-menubutton"
			data-options="method:'doAjax',
       extend: '#departmentDg-toolbar',
       iconCls:'fa fa-trash',
       url:'ngRecord/confirmNGRecord.do',
       grid: {uncheckedMsg:'请先勾选要确认的数据',id:'departmentDg',param:'id:id'}">确认</a>
	</div>
	
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
           id:'positionAddDialog',
            width:600,
           height:400,
           href:'console/jsp/position_add.jsp',
           buttons:[
           	{text:'保存',handler:function(){
           		if($('#departmentDg').iDatagrid('getSelected')){
           			var positionCode = $('#positionCode').val();
           			if(positionCode==null || ''===$.trim(positionCode)){
           				return false;
           			}
           			
           			var positionName = $('#positionName').val();
           			if(positionName==null || ''===$.trim(positionName)){
           				return false;
           			}
           			$.get('position/addPosition.do',{
           			code:positionCode,
           			name:positionName,
           			'department.id':$('#departmentDg').iDatagrid('getSelected').id,
           			note:$('#positionNote').val()
           			},function(data){
           				if(data.success){
	           				$('#positionAddDialog').iDialog('close');
	           				$('#position').iDatagrid('reload');
           				}else{
           					alert(data.msg);
           				}
           			});
           		}else{
           			alert('请选择部门');
           			$('#positionAddDialog').iDialog('close');
           		}
           	},iconCls:'fa fa-plus',btnCls:'topjui-btn-normal'},
           	{text:'关闭',handler:function(){
           		$('#positionAddDialog').iDialog('close');
           	},iconCls:'fa fa-close',btnCls:'topjui-btn-normal'},
           ]
       }">新增</a>
		<a href="javascript:void(0)" data-toggle="topjui-menubutton"
			data-options="method: 'openDialog',
            extend: '#position-toolbar',
            iconCls: 'fa fa-pencil',
            dialog: {
            	id:'positionEditDialog',
                width: 600,
                height: 400,
                href: 'console/jsp/position_edit.jsp',
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
            }">编辑</a>
		<a href="javascript:void(0)" data-toggle="topjui-menubutton"
			data-options="method:'doAjax',
       extend: '#position-toolbar',
       iconCls:'fa fa-trash',
       url:'position/deletePosition.do',
       grid: {uncheckedMsg:'请先勾选要删除的数据',id:'position',param:'id:id'}">删除</a>
		<a href="javascript:void(0)" data-toggle="topjui-menubutton"
			data-options="method:'doAjax',
       extend: '#departmentDg-toolbar',
       iconCls:'fa fa-stop',
       url:'position/disabledPosition.do',
       grid: {uncheckedMsg:'请选择要停用的职位',id:'position',param:'id:id'}">停用</a>
	</div>
	<!-- 相关文档表格工具栏结束 -->
</body>
</html>