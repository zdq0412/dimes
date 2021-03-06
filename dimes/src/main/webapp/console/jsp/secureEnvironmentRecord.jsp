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
                       url:'secureEnvironmentRecord/querySecureEnvironmentRecords.do',
                       singleSelect:true,
                       fitColumns:true,
                       pagination:true,
			           childTab: [{id:'southTabs'}]">
                    <thead>
                    <tr>
                        <th data-options="field:'id',title:'id',hidden:true"></th>
                        <th data-options="field:'currentDate',title:'日期',width:'180px',align:'center'"></th>
                        <th data-options="field:'typeName',title:'类型',width:'180px',align:'center'"></th>
                        <th data-options="field:'gradeName',title:'等级',width:'180px',align:'center'"></th>
                        <th data-options="field:'description',title:'描述',width:'180px',align:'center'"></th>
                    </tr>
                    </thead>
                </table>
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
           id:'secureEnvironmentRecordAddDialog',
           width:600,
           height:600,
           href:'console/jsp/secureEnvironmentRecord_add.jsp',
           buttons:[
           	{text:'保存',handler:function(){
           			var currentDate = $('#currentDate').val();
           			if(currentDate==null || ''===$.trim(currentDate)){
           				return false;
           			}
           			
           			$.get('secureEnvironmentRecord/addSecureEnvironmentRecord.do',{
           			currentDate:currentDate,
           			typeId:$('#typeId').val(),
           			typeName:$('#typeName').val(),
           			gradeId:$('#gradeId').val(),
           			gradeName:$('#gradeName').val(),
           			description:$('#description').val()
           			},function(data){
           				if(data.success){
	           				$('#secureEnvironmentRecordAddDialog').iDialog('close');
	           				$('#departmentDg').iDatagrid('reload');
           				}else{
           					alert(data.msg);
           				}
           			});
           	},iconCls:'fa fa-plus',btnCls:'topjui-btn-normal'},
           	{text:'关闭',handler:function(){
           		$('#secureEnvironmentRecordAddDialog').iDialog('close');
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
                href: 'console/jsp/secureEnvironmentRecord_edit.jsp',
                url:'secureEnvironmentRecord/querySecureEnvironmentRecordById.do?id={id}',
                 buttons:[
           	{text:'编辑',handler:function(){
           			var currentDate = $('#currentDate').val();
           			if(currentDate==null || ''===$.trim(currentDate)){
           				return false;
           			}
           			$.get('secureEnvironmentRecord/updateSecureEnvironmentRecord.do',{
           			id:$('#departmentDg').iDatagrid('getSelected').id,
           			currentDate:currentDate,
           			typeId:$('#typeId').val(),
           			typeName:$('#typeName').val(),
           			gradeId:$('#gradeId').val(),
           			gradeName:$('#gradeName').val(),
           			description:$('#description').val()
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
       url:'secureEnvironmentRecord/deleteSecureEnvironmentRecord.do',
       grid: {uncheckedMsg:'请先勾选要删除的数据',id:'departmentDg',param:'id:id'}">删除</a>
    </div>
</body>
</html>