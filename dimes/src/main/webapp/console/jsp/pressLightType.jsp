<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../../common/jsp/head.jsp" %>
<script type="text/javascript">
 	function openWindow(){
		var checkedArray = $("#position").iDatagrid("getChecked");
		if(checkedArray.length>0){
			var ids = "";
			for(var i = 0;i<checkedArray.length;i++){
				var device = checkedArray[i];
				ids += device.id +",";
			}
			
			ids = ids.substring(0,ids.length-1);
			$("#ids").val(ids);
 			var newWin = window.open("console/jsp/pressLight_print.jsp"); 
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
			   url:'pressLightType/queryTopPressLightTypes.do',
			   childGrid:{
			   	   param:'pid:id,level:level',
                   grid:[
                       {type:'datagrid',id:'departmentDg'},
                   ]
			   }">
            <thead>
            <tr>
               <!--  <th data-options="field:'id',title:'id',checkbox:true"></th> -->
                <th data-options="field:'name',width:'100%',title:'故障类别'"></th>
            </tr>
            </thead>
        </table>
    </div>
    <div data-options="region:'center',iconCls:'icon-reload',title:'',split:true,border:true,bodyCls:'border_top_none'">
        <div data-toggle="topjui-layout" data-options="fit:true">
            <div data-options="region:'center',title:'',fit:false,split:true,border:false,bodyCls:'border_bottom'"
                 style="height:60%;">
                <!-- datagrid表格 -->
                <table  
                data-toggle="topjui-datagrid"
                       data-options="id:'departmentDg',
                       url:'pressLightType/queryPressLightTypesByParentId.do',
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
                        <th data-options="field:'id',title:'id',checkbox:false,width:'80px'"></th>
                        <th data-options="field:'code',title:'类别代码',width:'180px',align:'center'"></th>
                        <th data-options="field:'name',title:'类别名称',sortable:false"></th>
                        <th data-options="field:'note',title:'备注',sortable:false"></th>
                        <th data-options="field:'parentCode',title:'父类别代码',sortable:false,
                        formatter:function(value,row,index){
                            if (row.parent) {
                                return row.parent.code;
                            } else {
                                return '';
                            }
                        }"></th>
                        <th data-options="field:'parentName',title:'父类别名称',sortable:false, 
                        formatter:function(value,row,index){
                            if (row.parent) {
                                return row.parent.name;
                            } else {
                                return '';
                            }
                        }"></th>
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
                         param:'pressLightTypeId:id'
                     }">
                    <div title="按灯信息" data-options="id:'tab0',iconCls:'fa fa-th'">
                        <!-- datagrid表格 -->
                        <table 
                         data-toggle="topjui-datagrid"
                               data-options="id:'position',
                               initCreate: false,
                                 singleSelect:true,
								selectOnCheck:false,
								checkOnSelect:false,
                               fitColumns:true,
						       url:'pressLight/queryPressLightsByPressLightTypeId.do'">
                            <thead>
                            <tr>
                                <th data-options="field:'id',title:'id',checkbox:true"></th>
                                <th data-options="field:'code',title:'故障代码',sortable:false"></th>
                                <th data-options="field:'reason',title:'故障原因',sortable:false"></th>
                                <th data-options="field:'description',title:'详细描述',sortable:false"></th>
                                <th data-options="field:'halt',title:'是否停机',sortable:false,formatter:function(value,rows,index){
                                	if(value){
                                		return 'Y';
                                	}else{
                                		return 'N';
                                	}
                                }"></th>
                                <th data-options="field:'planHalt',title:'计划停机',sortable:false,formatter:function(value,rows,index){
                                	if(value){
                                		return 'Y';
                                	}else{
                                		return 'N';
                                	}
                                }"></th>
                                <th data-options="field:'note',title:'备注',sortable:false"></th>
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
                                <th data-options="field:'documentName',title:'文档名称',sortable:false"></th>
                                <th data-options="field:'note',title:'文档说明',sortable:false"></th>
                                <!-- <th data-options="field:'sex',title:'性别',sortable:false,
                                formatter:function(value,row,index){
                                    if (value == '1') {
                                        return '男';
                                    } else if (value == '2') {
                                        return '女';
                                    } else {
                                        return '';
                                    }
                                }"></th> -->
                                <th data-options="field:'relatedCode',title:'关联代码',sortable:false"></th>
                                <th data-options="field:'relatedName',title:'关联名称',sortable:false"></th>
                                <th data-options="field:'uploadUsername',title:'上传人员',sortable:false"></th>
                                <th data-options="field:'uploadDate',title:'上传时间',sortable:false,
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
    <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method:'openDialog',
       extend: '#departmentDg-toolbar',
       iconCls: 'fa fa-plus',
        parentGrid:{
               type:'treegrid',
               id:'parameterTypeTg',
               params:'level:level'
            },
       dialog:{
           id:'parameterTypeAddDialog',
           width:600,
           height:400,
           href:'console/jsp/pressLightType_add.jsp',
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
           			$.get('pressLightType/addPressLightType.do',{
           			code:code,
           			name:name,
           			level:$('#level').val(),
           			'parent.id':$('#parameterTypeTg').iTreegrid('getSelected').id,
           			note:$('#note').val()
           			},function(data){
           				if(data.success){
	           				$('#parameterTypeAddDialog').iDialog('close');
	           				$('#departmentDg').iDatagrid('reload');
	           				$('#parameterTypeTg').iTreegrid('reload');
           				}else{
           					alert(data.msg);
           				}
           			});
           	},iconCls:'fa fa-plus',btnCls:'topjui-btn-normal'},
           	{text:'关闭',handler:function(){
           		$('#parameterTypeAddDialog').iDialog('close');
           	},iconCls:'fa fa-close',btnCls:'topjui-btn-normal'},
           ]}">新增</a>
    <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method: 'openDialog',
            extend: '#departmentDg-toolbar',
            iconCls: 'fa fa-pencil',
            dialog: {
            	id:'parameterTypeEditDialog',
                width: 600,
                height: 400,
                href: 'console/jsp/pressLightType_edit.jsp',
                url:'pressLightType/queryPressLightTypeById.do?id={id}',
                 buttons:[
           	{text:'编辑',handler:function(){
           			var code = $('#code').val();
           			var name = $('#name').val();
           			if(name==null || ''===$.trim(name)){
           				return false;
           			}
           			$.get('pressLightType/updatePressLightType.do',{
           			id:$('#departmentDg').iDatagrid('getSelected').id,
           			code:code,
           			name:name,
           			'parent.id':$('#parameterTypeTg').iTreegrid('getSelected').id,
           			note:$('#note').val()
           			},function(data){
           				if(data.success){
	           				$('#parameterTypeEditDialog').iDialog('close');
	           				$('#departmentDg').iDatagrid('reload');
	           				$('#departmentTg').iTreegrid('reload');
           				}else{
           					alert(data.msg);
           				}
           			});
           	},iconCls:'fa fa-plus',btnCls:'topjui-btn-normal'},
           	{text:'关闭',handler:function(){
           		$('#parameterTypeEditDialog').iDialog('close');
           	},iconCls:'fa fa-close',btnCls:'topjui-btn-normal'},
           ]
            }">编辑</a>
    <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method:'doAjax',
       extend: '#departmentDg-toolbar',
       iconCls:'fa fa-trash',
       url:'pressLightType/deletePressLightType.do',
       grid: {uncheckedMsg:'请先勾选要删除的数据',id:'departmentDg',param:'id:id'}">删除</a>
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
       data-options="method:'doAjax',
       extend: '#departmentDg-toolbar',
       iconCls:'fa fa-stop',
       url:'pressLightType/disabledPressLightType.do',
       grid: {uncheckedMsg:'请选择要停用的不良原因类型',id:'departmentDg',param:'id:id'}">停用</a>
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
           id:'parameterAddDialog',
            width:620,
           height:500,
           href:'console/jsp/pressLight_add.jsp',
           buttons:[
           	{text:'保存',handler:function(){
           			var code = $('#code').val();
           			if(code==null || ''===$.trim(code)){
           				return false;
           			}
           			
           			var reason = $('#reason').val();
           			if(reason==null || ''===$.trim(reason)){
           				return false;
           			}
           			$.get('pressLight/addPressLight.do',{
           			code:code,
           			reason:reason,
           			description:$('#description').val(),
           			halt:$('input[name=halt]:checked').val(),
           			planHalt:$('input[name=planHalt]:checked').val(),
           			'pressLightType.id':$('#departmentDg').iDatagrid('getSelected').id,
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
                width: 620,
                height: 500,
                href: 'console/jsp/pressLight_edit.jsp',
                url:'pressLight/queryPressLightById.do?id={id}',
                 buttons:[
           	{text:'编辑',handler:function(){
           			var code = $('#code').val();
           			var reason = $('#reason').val();
           			if(reason==null || ''===$.trim(reason)){
           				return false;
           			}
           			$.get('pressLight/updatePressLight.do',{
           			id:$('#position').iDatagrid('getSelected').id,
           			code:code,
           			reason:reason,
           			description:$('#description').val(),
           			halt:$('input[name=halt]:checked').val(),
           			planHalt:$('input[name=planHalt]:checked').val(),
           			'pressLightType.id':$('#departmentDg').iDatagrid('getSelected').id,
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
       url:'pressLight/deletePressLight.do',
       grid: {uncheckedMsg:'请先勾选要删除的数据',id:'position',param:'id:id'}">删除</a>
       <a href="javascript:void(0)" data-toggle="topjui-menubutton"
			data-options="iconCls: 'fa fa-print',
            modal:true,
            parentGrid:{
		       	type:'datagrid',
		       	id:'departmentDg'
		      }" onClick="openWindow()">打印二维码</a>
    <!-- <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
      data-options="method:'doAjax',
       extend: '#departmentDg-toolbar',
       iconCls:'fa fa-stop',
       url:'parameter/disabledParameter.do',
       grid: {uncheckedMsg:'请选择要停用的参数',id:'position',param:'id:id'}">停用</a> -->
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