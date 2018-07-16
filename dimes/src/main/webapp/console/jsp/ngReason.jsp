<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../../common/jsp/head.jsp" %>
</head>
<body>
<div data-toggle="topjui-layout" data-options="fit:true">
    <div data-options="region:'west',title:'',split:true,border:false,width:'15%',iconCls:'fa fa-sitemap',headerCls:'border_right',bodyCls:'border_right'">
        <!-- treegrid表格 -->
        <table
         data-toggle="topjui-treegrid"
               data-options="id:'parameterTypeTg',
			   idField:'id',
			   treeField:'name',
			   fitColumns:true,
			   fit:true,
			   singleSelect:true,
			   url:'processes/queryProcessTree.do',
			   childGrid:{
			   	   param:'processId:id',
                   grid:[
                       {type:'datagrid',id:'position'}
                   ]
			   }">
            <thead>
            <tr>
               <!--  <th data-options="field:'id',title:'id',checkbox:true"></th> -->
                <th data-options="field:'name',width:'100%',title:'不良原因'"></th>
            </tr>
            </thead>
        </table>
    </div>
    <div data-options="region:'center',iconCls:'icon-reload',title:'',split:true,border:true,bodyCls:'border_top_none'">
        <div data-toggle="topjui-layout" data-options="fit:true">
            <div data-options="region:'center',title:'',fit:false,split:true,border:false,bodyCls:'border_bottom'"
                 style="height:60%;">
                 <table  
                data-toggle="topjui-datagrid"
                       data-options="id:'position',
                       url:'ngReason/queryNGReasonsByProcessId.do',
                       singleSelect:true,
                       fitColumns:true,
                       pagination:true,
                       parentGrid:{
                           type:'treegrid',
                           id:'parameterTypeTg',
                       },
			           childTab: [{id:'southTabs'}]">
                            <thead>
                            <tr>
                                <th data-options="field:'id',title:'id',checkbox:false"></th>
                                <th data-options="field:'ngCode',title:'NG代码',sortable:true"></th>
                                <th data-options="field:'ngReason',title:'NG原因',sortable:true"></th>
                                <th data-options="field:'note',title:'备注',sortable:true"></th>
                                <th data-options="field:'processingMethod',title:'处理方法',sortable:true"></th>
                            </tr>
                            </thead>
                        </table>
            </div>
        </div>
    </div>
</div>
<!-- 部门表格工具栏开始 -->
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
               type:'treegrid',
               id:'parameterTypeTg'
            },
       dialog:{
           id:'parameterAddDialog',
            width:600,
           height:400,
           href:'console/jsp/ngReason_add.jsp',
           buttons:[
           	{text:'保存',handler:function(){
           			var code = $('#ngCode').val();
           			if(code==null || ''===$.trim(code)){
           				return false;
           			}
           			
           			var ngReason = $('#ngReason').val();
           			if(ngReason==null || ''===$.trim(ngReason)){
           				return false;
           			}
           			$.get('ngReason/addNGReason.do',{
           			ngCode:code,
           			ngReason:ngReason,
           			processingMethod:$('#processingMethod').val(),
           			'process.id':$('#parameterTypeTg').iTreegrid('getSelected').id,
           			note:$('#note').val()
           			},function(data){
           				if(data.success){
	           				$('#parameterAddDialog').iDialog('close');
	           				$('#position').iDatagrid('reload');
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
            extend: '#position-toolbar',
            iconCls: 'fa fa-pencil',
            dialog: {
            	id:'parameterEditDialog',
                width: 600,
                height: 400,
                href: 'console/jsp/ngReason_edit.jsp',
                url:'ngReason/queryNGReasonById.do?id={id}',
                 buttons:[
           	{text:'编辑',handler:function(){
           			var code = $('#ngCode').val();
           			var ngReason = $('#ngReason').val();
           			if(ngReason==null || ''===$.trim(ngReason)){
           				return false;
           			}
           			$.get('ngReason/updateNGReason.do',{
           			id:$('#position').iDatagrid('getSelected').id,
           			ngCode:code,
           			ngReason:ngReason,
           			processingMethod:$('#processingMethod').val(),
           			'process.id':$('#position').iDatagrid('getSelected').process.id,
           			note:$('#note').val()
           			},function(data){
           				if(data.success){
	           				$('#parameterEditDialog').iDialog('close');
	           				$('#position').iDatagrid('reload');
           				}else{
           					alert(data.msg);
           				}
           			});
           	},iconCls:'fa fa-plus',btnCls:'topjui-btn-normal'},
           	{text:'关闭',handler:function(){
           		$('#parameterEditDialog').iDialog('close');
           	},iconCls:'fa fa-close',btnCls:'topjui-btn-normal'},
           ]
            }">编辑</a>
    <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method:'doAjax',
       extend: '#position-toolbar',
       iconCls:'fa fa-trash',
       url:'ngReason/deleteNGReason.do',
       grid: {uncheckedMsg:'请先勾选要删除的数据',id:'position',param:'id:id'}">删除</a>
    </div>
</body>
</html>