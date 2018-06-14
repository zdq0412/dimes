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
			   url:'department/queryTopDepartments.do',
			   childGrid:{
			   	   param:'departmentId:id',
                   grid:[
                       {type:'datagrid',id:'employeeDg'},
                   ]
			   }">
				<thead>
					<tr>
						<!--  <th data-options="field:'id',title:'id',checkbox:true"></th> -->
						<th data-options="field:'name',width:'100%',title:'机构名称'"></th>
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
						data-options="id:'employeeDg',
                       url:'employee/queryEmployeesByDepartmentId.do',
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
									data-options="field:'id',title:'id',checkbox:false,width:'80px'"></th>
								<th
									data-options="field:'code',title:'员工代码',width:'180px',align:'center'"></th>
								<th data-options="field:'name',title:'员工名称',sortable:false"></th>
								<th data-options="field:'departmentCode',title:'部门代码'"></th>
								<th data-options="field:'departmentName',title:'部门名称'"></th>
								<th data-options="field:'positionCode',title:'岗位代码'"></th>
								<th data-options="field:'positionName',title:'岗位名称'"></th>
								<th data-options="field:'note',title:'备注'"></th>
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
                         id:'employeeDg',
                         param:'id:id'
                     }">
						<div title="技能信息" data-options="id:'tab0',iconCls:'fa fa-th'">
							<!-- datagrid表格 -->
							<table data-toggle="topjui-datagrid"
								data-options="id:'skill',
                               initCreate: false,
                               fitColumns:true,
						       url:'position/queryPositionsByDepartmentId.do'">
								<thead>
									<tr>
										<th data-options="field:'id',title:'id',checkbox:true"></th>
										<th data-options="field:'code',title:'代码',sortable:true"></th>
										<th data-options="field:'name',title:'岗位名称',sortable:true"></th>
										<th data-options="field:'note',title:'备注',sortable:true"></th>
										<th
											data-options="field:'deptCode',title:'部门代码',sortable:true,
                                formatter:function(value,row,index){
                                    if (row.department) {
                                        return row.department.code;
                                    }else {
                                        return '';
                                    }
                                }"></th>
										<th
											data-options="field:'deptName',title:'部门名称',sortable:true,
                                formatter:function(value,row,index){
                                    if (row.department) {
                                        return row.department.name;
                                    }else {
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
	<!-- 员工表格工具栏开始 -->
	<div id="employeeDg-toolbar" class="topjui-toolbar"
		data-options="grid:{
           type:'datagrid',
           id:'employeeDg'
       }">
		<a href="javascript:void(0)" data-toggle="topjui-menubutton"
			data-options=" method:'openDialog',
       extend: '#employeeDg-toolbar',
       iconCls: 'fa fa-plus',
       parentGrid:{
       	type:'treegrid',
       	id:'departmentTg',
       	param:'departmentId:id'
       },
       dialog:{
           id:'employeeAddDialog',
           width:600,
           height:400,
           href:'console/jsp/employee_add.jsp',
           buttons:[
           	{text:'保存',handler:function(){
           		if($('#departmentTg').iTreegrid('getSelected')){
           			var employeeCode = $('#employeeCode').val();
           			if(employeeCode==null || ''===$.trim(employeeCode)){
           				return false;
           			}
           			var employeeName = $('#employeeName').val();
           			if(employeeName==null || ''===$.trim(employeeName)){
           				return false;
           			}
           			$.get('employee/addEmployee.do',{
           			code:employeeCode,
           			'position.id':$('#employeePosition').val(),
           			name:employeeName,
           			note:$('#employeeNote').val()
           			},function(data){
           				if(data.success){
	           				$('#employeeAddDialog').iDialog('close');
	           				$('#employeeDg').iDatagrid('reload');
           				}else{
           					alert(data.msg);
           				}
           			});
           		}else{
           			alert('请选择部门');
           			$('#employeeAddDialog').iDialog('close');
           		}
           	},iconCls:'fa fa-plus',btnCls:'topjui-btn-normal'},
           	{text:'关闭',handler:function(){
           		$('#employeeAddDialog').iDialog('close');
           	},iconCls:'fa fa-close',btnCls:'topjui-btn-normal'},
           ]}">新增</a>
		<a href="javascript:void(0)" data-toggle="topjui-menubutton"
			data-options="method: 'openDialog',
            extend: '#employeeDg-toolbar',
            iconCls: 'fa fa-pencil',
            parentGrid:{
		       	type:'treegrid',
		       	id:'departmentTg',
		       	param:'departmentId:id'
		      },
            dialog: {
            	id:'employeeEditDialog',
                width: 600,
                height: 400,
                href: 'console/jsp/employee_edit.jsp',
                url:'employee/queryEmployeeById.do?id={id}',
                 buttons:[
           			{text:'编辑',handler:function(){
           		
           			var employeeCode = $('#employeeCode_edit').val();
           			var employeeName = $('#employeeName_edit').val();
           			if(employeeName==null || ''===$.trim(employeeName)){
           				return false;
           			}
           			$.get('employee/updateEmployee.do',{
           			id:$('#employeeDg').iDatagrid('getSelected').id,
           			code:employeeCode,
           			name:employeeName,
           			'position.id':$('#positionId_edit').val(),
           			note:$('#employeeNote_edit').val()
           			},function(data){
           				if(data.success){
	           				$('#employeeEditDialog').iDialog('close');
	           				$('#employeeDg').iDatagrid('reload');
           				}else{
           					alert(data.msg);
           				}
           			});
           	},iconCls:'fa fa-plus',btnCls:'topjui-btn-normal'},
           	{text:'关闭',handler:function(){
           		$('#employeeEditDialog').iDialog('close');
           	},iconCls:'fa fa-close',btnCls:'topjui-btn-normal'},
           ]
            }">编辑</a>
		<a href="javascript:void(0)" data-toggle="topjui-menubutton"
			data-options="method:'doAjax',
       extend: '#employeeDg-toolbar',
       iconCls:'fa fa-trash',
       url:'employee/deleteEmployee.do',
       grid: {uncheckedMsg:'请先勾选要删除的数据',id:'employeeDg',param:'id:id'}">删除</a>
		<a href="javascript:void(0)" data-toggle="topjui-menubutton"
			data-options="method:'doAjax',
       extend: '#employeeDg-toolbar',
       iconCls:'fa fa-stop',
       url:'employee/disabledEmployee.do',
       grid: {uncheckedMsg:'请选择要停用的部门',id:'employeeDg',param:'id:id'}">停用</a>
	</div>
	<!-- 员工表格工具栏结束 -->
	<!-- 职位表格工具栏开始 -->
	<div id="skill-toolbar" class="topjui-toolbar"
		data-options="grid:{
           type:'datagrid',
           id:'skill'
       }">
		<a href="javascript:void(0)" data-toggle="topjui-menubutton"
			data-options="method:'openDialog',
       extend: '#skill-toolbar',
       iconCls: 'fa fa-plus',
        parentGrid:{
		       	type:'datagrid',
		       	id:'employeeDg'
		      },
       dialog:{
           id:'positionAddDialog',
            width:600,
           height:400,
           href:'console/jsp/position_add.jsp',
           buttons:[
           	{text:'保存',handler:function(){
           		if($('#employeeDg').iDatagrid('getSelected')){
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
           			'department.id':$('#employeeDg').iDatagrid('getSelected').id,
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
            extend: '#skill-toolbar',
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
           			'department.id':$('#employeeDg').iDatagrid('getSelected').id,
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
       extend: '#skill-toolbar',
       iconCls:'fa fa-trash',
       url:'position/deletePosition.do',
       grid: {uncheckedMsg:'请先勾选要删除的数据',id:'position',param:'id:id'}">删除</a>
		<a href="javascript:void(0)" data-toggle="topjui-menubutton"
			data-options="method:'doAjax',
       extend: '#skill-toolbar',
       iconCls:'fa fa-stop',
       url:'position/disabledPosition.do',
       grid: {uncheckedMsg:'请选择要停用的职位',id:'position',param:'id:id'}">停用</a>
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