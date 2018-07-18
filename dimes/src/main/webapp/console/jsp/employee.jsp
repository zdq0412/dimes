<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../common/jsp/head.jsp"%>
<script type="text/javascript">
 	function openWindow(){
		//获取所有被选中的员工
		var employeesArray = $("#employeeDg").iDatagrid("getChecked");
		if(employeesArray.length>0){
			var ids = "";
			for(var i = 0;i<employeesArray.length;i++){
				var employee = employeesArray[i];
				ids += employee.id +",";
			}
			
			ids = ids.substring(0,ids.length-1);
			$("#ids").val(ids);
 			var newWin = window.open("console/jsp/employee_print.jsp"); 
		}else{
			alert("请选择要打印二维码的记录!");
			return false;
		}
	} 
</script>
</head>
<body>
	<input type="hidden" id="ids" />
	<div data-toggle="topjui-layout" data-options="fit:true">
		<div
			data-options="region:'west',title:'',split:true,border:false,width:'15%',iconCls:'fa fa-sitemap',headerCls:'border_right',bodyCls:'border_right'">
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
                       fitColumns:true,
                       pagination:true,
                       singleSelect:true,
						selectOnCheck:false,
						checkOnSelect:false,
                       parentGrid:{
                           type:'treegrid',
                           id:'departmentTg'
                       },
			           childTab: [{id:'southTabs'}]">
						<thead>
							<tr>
								<th
									data-options="field:'id',title:'id',checkbox:true,width:'80px'"></th>
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
                         param:'employeeId:id'
                     }">
						<div title="技能等级" data-options="id:'tab0',iconCls:'fa fa-th'">
							<!-- datagrid表格 -->
							<table data-toggle="topjui-datagrid"
								data-options="id:'skill',
                               initCreate: false,
                               fitColumns:true,
						       url:'skill/querySkillsByEmployeeId.do'">
								<thead>
									<tr>
										<th data-options="field:'id',title:'id',hidden:true"></th>
										<th data-options="field:'skillTypeName',title:'技能类别',sortable:false,
										formatter:function(value,row,index){
											if(row.skill){
												return row.skill.skillType.name;
											}else{
												return '';
											}
										}"></th>
										<th data-options="field:'skillCode',title:'技能代码',sortable:false,
										formatter:function(value,row,index){
											if(row.skill){
												return row.skill.code;
											}else{
												return '';
											}
										}"></th>
										<th data-options="field:'skillName',title:'技能名称',sortable:false,
										formatter:function(value,row,index){
											if(row.skill){
												return row.skill.name;
											}else{
												return '';
											}
										}"></th>
										<th data-options="field:'levelName',title:'等级代码',sortable:false,
										formatter:function(value,row,index){
											if(row.skillLevel){
												return row.skillLevel.code;
											}else{
												return '';
											}
										}"></th>
										<th data-options="field:'name',title:'等级名称',sortable:false,
										formatter:function(value,row,index){
											if(row.skillLevel){
												return row.skillLevel.name;
											}else{
												return '';
											}
										}"></th>
										<th data-options="field:'skillNote',title:'备注',sortable:false,
										formatter:function(value,row,index){
											if(row.skill){
												return row.skill.note;
											}else{
												return '';
											}
										}"></th>
									</tr>
								</thead>
							</table>
						</div>
						<div title="工序等级" data-options="id:'tab1',iconCls:'fa fa-th'">
							<!-- datagrid表格 -->
							<table data-toggle="topjui-datagrid"
								data-options="id:'process',
                               initCreate: false,
                               fitColumns:true,
						       url:'processes/queryProcessByEmployeeId.do'">
								<thead>
									<tr>
										<th data-options="field:'id',title:'id',hidden:true"></th>
										<th data-options="field:'processTypeName',title:'工序类别',sortable:false,
										formatter:function(value,row,index){
											if(row.process){
												return row.process.processType.name;
											}else{
												return '';
											}
										}"></th>
										<th data-options="field:'processCode',title:'工序代码',sortable:false,
										formatter:function(value,row,index){
											if(row.process){
												return row.process.code;
											}else{
												return '';
											}
										}"></th>
										<th data-options="field:'processName',title:'工序名称',sortable:false,
										formatter:function(value,row,index){
											if(row.process){
												return row.process.name;
											}else{
												return '';
											}
										}"></th>
										<th data-options="field:'levelName',title:'等级代码',sortable:false,
										formatter:function(value,row,index){
											if(row.skillLevel){
												return row.skillLevel.code;
											}else{
												return '';
											}
										}"></th>
										<th data-options="field:'name',title:'等级名称',sortable:false,
										formatter:function(value,row,index){
											if(row.skillLevel){
												return row.skillLevel.name;
											}else{
												return '';
											}
										}"></th>
										<th data-options="field:'processNote',title:'备注',sortable:false,
										formatter:function(value,row,index){
											if(row.process){
												return row.process.note;
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
           			note:$('#employeeNote').val(),
           			'productionUnit.id':$('#productionUnitId').val()
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
           			note:$('#employeeNote_edit').val(),
           			'productionUnit.id':$('#productionUnitId').val()
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
       grid: {uncheckedMsg:'请先勾选要删除的数据',id:'employeeDg',param:'ids:id'}">删除</a>
		<a href="javascript:void(0)" data-toggle="topjui-menubutton"
			data-options="method:'doAjax',
       extend: '#employeeDg-toolbar',
       iconCls:'fa fa-stop',
       url:'employee/disabledEmployee.do',
       grid: {uncheckedMsg:'请选择要停用的员工',id:'employeeDg',param:'id:id'}">停用</a>
      <a href="javascript:void(0)" data-toggle="topjui-menubutton"
			data-options="iconCls: 'fa fa-print',
            modal:true,
            parentGrid:{
		       	type:'treegrid',
		       	id:'departmentTg',
		       	param:'departmentId:id'
		      }" onClick="openWindow()">打印二维码</a>
       <!-- 二维码按钮 -->
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
           href:'console/jsp/employee_skill_add.jsp',
           buttons:[
           	{text:'保存',handler:function(){
           			var skillId = $('#skillId').val();
           			
           			if(!skillId){
           				return false;
           			}
           			var skillLevelId = $('#skillLevelId').val();
           			if(!skillLevelId){
           				return false;
           			}
           			$.get('skill/addSkill4Employee.do',{
           			'employee.id':$('#employeeDg').iDatagrid('getSelected').id,
           			'skill.id':skillId,
           			'skillLevel.id':skillLevelId
           			},function(data){
           				if(data.success){
	           				$('#positionAddDialog').iDialog('close');
	           				$('#skill').iDatagrid('reload');
           				}else{
           					alert(data.msg);
           				}
           			});
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
                href: 'console/jsp/employee_skill_edit.jsp',
                url:'skill/queryEmployeeSkillMappingById.do?employeeSkillMappingId={id}',
                 buttons:[
           	{text:'编辑',handler:function(){
           			
           			$.get('skill/updateSkill4Employee.do',{
           			id:$('#skill').iDatagrid('getSelected').id,
           			'skillLevel.id':$('#skillLevelId').val()
           			},function(data){
           				if(data.success){
	           				$('#positionEditDialog').iDialog('close');
	           				$('#skill').iDatagrid('reload');
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
       url:'skill/deleteEmployeeSkillMapping.do',
       grid: {uncheckedMsg:'请先勾选要删除的数据',id:'skill',param:'id:id'}">删除</a>
	</div>
	<!-- 职位表格工具栏结束 -->
	<!-- 相关文档表格工具栏开始 -->
	<div id="process-toolbar" class="topjui-toolbar"
		data-options="grid:{
           type:'datagrid',
           id:'process'
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
           id:'processAddDialog',
            width:600,
           height:400,
           href:'console/jsp/employee_process_add.jsp',
           buttons:[
           	{text:'保存',handler:function(){
           			var processId = $('#processId').val();
           			
           			if(!processId){
           				return false;
           			}
           			var skillLevelId = $('#skillLevelId').val();
           			if(!skillLevelId){
           				return false;
           			}
           			$.get('skill/addSkill4Employee.do',{
           			'employee.id':$('#employeeDg').iDatagrid('getSelected').id,
           			'process.id':processId,
           			'skillLevel.id':skillLevelId
           			},function(data){
           				if(data.success){
	           				$('#processAddDialog').iDialog('close');
	           				$('#process').iDatagrid('reload');
           				}else{
           					alert(data.msg);
           				}
           			});
           	},iconCls:'fa fa-plus',btnCls:'topjui-btn-normal'},
           	{text:'关闭',handler:function(){
           		$('#processAddDialog').iDialog('close');
           	},iconCls:'fa fa-close',btnCls:'topjui-btn-normal'},
           ]
       }">新增</a>
		<a href="javascript:void(0)" data-toggle="topjui-menubutton"
			data-options="method: 'openDialog',
            extend: '#process-toolbar',
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
       extend: '#process-toolbar',
       iconCls:'fa fa-trash',
       url:_ctx + '/json/response/success.json',
       grid: {uncheckedMsg:'请先勾选要删除的数据',param:'uuid:uuid,code:code'}">删除</a>
	</div>
	<!-- 相关文档表格工具栏结束 -->
</body>
</html>