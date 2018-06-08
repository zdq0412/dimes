<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../../common/jsp/head.jsp" %>
</head>
<body>
<div data-toggle="topjui-layout" data-options="fit:true">
    <div data-options="region:'west',title:'',split:true,border:false,width:'15%',iconCls:'fa fa-sitemap',headerCls:'border_right',bodyCls:'border_right'">
        <!-- treegrid表格 -->
        <table data-toggle="topjui-treegrid"
               data-options="id:'orgnizationDatagrid',
			   idField:'id',
			   treeField:'name',
			   fitColumns:true,
			   fit:true,
			   singleSelect:true,
			   url:'department/queryTopDepartments.do',
			   childGrid:{
			   	   param:'pid:id',
                   grid:[
                       {type:'datagrid',id:'userDg'},
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
    <div data-options="region:'center',iconCls:'icon-reload',title:'',split:true,border:true,bodyCls:'border_top_none'">
        <div data-toggle="topjui-layout" data-options="fit:true">
            <div data-options="region:'center',title:'',fit:false,split:true,border:false,bodyCls:'border_bottom'"
                 style="height:60%">
                <!-- datagrid表格 -->
                <table data-toggle="topjui-datagrid"
                       data-options="id:'userDg',
                       url:'department/queryDepartmentsByParentId.do',
                       singleSelect:true,
                       fitColumns:true,
                       parentGrid:{
                           type:'treegrid',
                           id:'codeItemDatagrid',
                       },
			           childTab: [{id:'southTabs'}]">
                    <thead>
                    <tr>
                        <th data-options="field:'id',title:'id',checkbox:true"></th>
                        <th data-options="field:'code',title:'部门代码'"></th>
                        <th data-options="field:'name',title:'部门名称',sortable:false"></th>
                        <th data-options="field:'note',title:'备注',sortable:true"></th>
                        <th data-options="field:'parentCode',title:'上级部门代码',sortable:true,
                        formatter:function(value,row,index){
                            if (row.parent) {
                                return row.parent.code;
                            } else {
                                return '';
                            }
                        }"></th>
                        <th data-options="field:'parentName',title:'上级部门名称',sortable:true, 
                        formatter:function(value,row,index){
                            if (row.parent) {
                                return row.parent.name;
                            } else {
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
            </div>
            <div data-options="region:'south',fit:false,split:true,border:false"
                 style="height:40%">
                <div data-toggle="topjui-tabs"
                     data-options="id:'southTabs',
                     fit:true,
                     border:false,
                     singleSelect:true,
                     parentGrid:{
                         type:'datagrid',
                         id:'userDg',
                         param:'deptId:id'
                     }">
                    <div title="岗位信息" data-options="id:'tab0',iconCls:'fa fa-th'">
                        <!-- datagrid表格 -->
                        <table data-toggle="topjui-datagrid"
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
                    </div>
                    <div title="相关文档" data-options="id:'tab1',iconCls:'fa fa-th'">
                        <!-- datagrid表格 -->
                        <table data-toggle="topjui-datagrid"
                               data-options="id:'relateDoc',
                               initCreate: false,
                               fitColumns:true,
						       url:remoteHost+'/ucenter/user/getPageSet?type=1'">
                            <thead>
                            <tr>
                                <th data-options="field:'uuid',title:'UUID',checkbox:true"></th>
                                <th data-options="field:'userNameId',title:'员工号',sortable:true"></th>
                                <th data-options="field:'userName',title:'姓名',sortable:true"></th>
                                <th data-options="field:'sex',title:'性别',sortable:true,
                                formatter:function(value,row,index){
                                    if (value == '1') {
                                        return '男';
                                    } else if (value == '2') {
                                        return '女';
                                    } else {
                                        return '';
                                    }
                                }"></th>
                                <th data-options="field:'telephone',title:'电话',sortable:true"></th>
                                <th data-options="field:'email',title:'电子邮箱',sortable:true"></th>
                                <th data-options="field:'mobile',title:'手机',sortable:true"></th>
                                <th data-options="field:'orgName',title:'所属机构',sortable:true"></th>
                                <th data-options="field:'post',title:'职务',sortable:true"></th>
                                <th data-options="field:'userGroup',title:'用户组',sortable:true"></th>
                                <th data-options="field:'zone',title:'所属区域',sortable:true,hidden:true,formatter:function(value,row,index){return row.country+' '+row.province+' '+row.city+' '+row.district}"></th>
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
<div id="userDg-toolbar" class="topjui-toolbar"
     data-options="grid:{
           type:'datagrid',
           id:'userDg'
       }">
    <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method:'openDialog',
       extend: '#userDg-toolbar',
       iconCls: 'fa fa-plus',
       dialog:{
           id:'userAddDialog',
           href:_ctx + '/html/complex/dialog_add.html',
           buttonsGroup:[
               {text:'保存',url:_ctx + '/json/response/success.json',iconCls:'fa fa-plus',handler:'ajaxForm',btnCls:'topjui-btn-brown'}
           ]
       }">新增</a>
    <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method: 'openDialog',
            extend: '#userDg-toolbar',
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
    <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method:'doAjax',
       extend: '#userDg-toolbar',
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
    <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method:'search',
       extend: '#userDg-toolbar'">停用</a>
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
       dialog:{
           id:'userAddDialog',
           href:_ctx + '/html/complex/dialog_add.html',
           buttonsGroup:[
               {text:'保存',url:_ctx + '/json/response/success.json',iconCls:'fa fa-plus',handler:'ajaxForm',btnCls:'topjui-btn-brown'}
           ]
       }">新增</a>
    <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method: 'openDialog',
            extend: '#position-toolbar',
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
    <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method:'doAjax',
       extend: '#position-toolbar',
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
    <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method:'search',
       extend: '#position-toolbar'">停用</a>
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
       iconCls: 'fa fa-plus',
       dialog:{
           id:'userAddDialog',
           href:_ctx + '/html/complex/dialog_add.html',
           buttonsGroup:[
               {text:'保存',url:_ctx + '/json/response/success.json',iconCls:'fa fa-plus',handler:'ajaxForm',btnCls:'topjui-btn-brown'}
           ]
       }">新增</a>
    <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
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
    <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
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
    <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method:'search',
       extend: '#relateDoc-toolbar'">停用</a>
    </div>
<!-- 相关文档表格工具栏结束 -->
</body>
</html>