<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../../common/jsp/head.jsp" %>
</head>
<body>
<div data-toggle="topjui-layout" data-options="fit:true">
    <div data-options="region:'west',title:'',split:true,border:false,width:'15%',iconCls:'fa fa-sitemap',headerCls:'border_right',bodyCls:'border_right'">
        <table
        id="departmentTg"
         data-toggle="topjui-treegrid"
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
    <div data-options="region:'center',iconCls:'icon-reload',title:'',split:true,border:true,bodyCls:'border_top_none'">
        <div data-toggle="topjui-layout" data-options="fit:true">
            <div data-options="region:'center',title:'',fit:false,split:true,border:false,bodyCls:'border_bottom'"
                 style="height:60%;">
                <!-- datagrid表格 -->
                <table  
                data-toggle="topjui-datagrid"
                       data-options="id:'departmentDg',
                       url:'processRecord/queryProcessRecordByDeviceSiteId.do',
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
                        <th data-options="field:'id',title:'id',checkbox:false,width:'80px',hidden:true"></th>
                        <th data-options="field:'collectionDate',title:'生产时间',width:'180px',align:'center',formatter:function(value,row,index){
                        	if(value){
                        				var date = new Date(value);
                                        var month = date.getMonth()+1;
                                        var monthStr = ((month>=10)?month:('0' + month));
                                        
                                        var day = date.getDate();
                                        var dayStr = ((day>=10)?day:('0'+day));
                                        	/*
                                        var hour = date.getHours();
                                        var hourStr = ((hour>=10)?hour:('0' + hour));
                                        
                                        var minute = date.getMinutes();
                                        var minuteStr = ((minute>=10)?minute:('0' +minute));
                                        
                                        var second = date.getSeconds();
                                        var secondStr = ((second>=10)?second:('0' +second));
                                        
                                        var dateStr = date.getFullYear() + '-' + monthStr + '-' + dayStr  + 
                                        				' ' + hourStr + ':' + minuteStr + ':' + secondStr; */
                                        	var dateStr = date.getFullYear() + '-' + monthStr + '-' + dayStr;			
                                        return dateStr;
                        	}else{
                        		return '';
                        	}
                        }"></th>
                        <th data-options="field:'serialNo',title:'生产序号',sortable:false"></th>
                        <th data-options="field:'no',title:'工单单号',sortable:true"></th>
                        <th data-options="field:'workPieceCode',title:'工件代码',sortable:true"></th>
                        <th data-options="field:'workPieceName',title:'工件名称',sortable:true"></th>
                        <th data-options="field:'unitType',title:'规格型号',sortable:true"></th>
                        <th data-options="field:'graphNumber',title:'图号',sortable:true"></th>
                        <th data-options="field:'version',title:'版本号',sortable:true"></th>
                        <th data-options="field:'processCode',title:'工序代码',sortable:true"></th>
                        <th data-options="field:'processName',title:'工序名称',sortable:true"></th>
                        <th data-options="field:'batchNumber',title:'批号',sortable:true"></th>
                        <th data-options="field:'stoveNumber',title:'炉号',sortable:true"></th>
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
                         id:'departmentDg',
                         param:'processRecordId:id'
                     }">
                    <div title="设备参数" data-options="id:'tab0',iconCls:'fa fa-th'">
                        <!-- datagrid表格 -->
                        <table 
                         data-toggle="topjui-datagrid"
                               data-options="id:'position',
                               initCreate: false,
                               fitColumns:true,
						       url:'processParameterRecord/queryProcessParameterRecordByProcessRecordId.do'">
                            <thead>
                            <tr>
                                <th data-options="field:'id',title:'id',checkbox:false,hidden:true"></th>
                                <th data-options="field:'parameterCode',title:'参数代码',sortable:false"></th>
                                <th data-options="field:'parameterName',title:'参数名称',sortable:false"></th>
                                <th data-options="field:'upLine',title:'控制线UL',sortable:false"></th>
                                <th data-options="field:'lowLine',title:'控制线LL',sortable:false"></th>
                                <th data-options="field:'standardValue',title:'标准值',sortable:false"></th>
                                <th data-options="field:'currentDate',title:'时间',sortable:false,formatter:function(value,row,index){
                        	if(value){
                        				var date = new Date(value);
                                        var month = date.getMonth()+1;
                                        var monthStr = ((month>=10)?month:('0' + month));
                                        
                                        var day = date.getDate();
                                        var dayStr = ((day>=10)?day:('0'+day));
                                        	/*
                                        var hour = date.getHours();
                                        var hourStr = ((hour>=10)?hour:('0' + hour));
                                        
                                        var minute = date.getMinutes();
                                        var minuteStr = ((minute>=10)?minute:('0' +minute));
                                        
                                        var second = date.getSeconds();
                                        var secondStr = ((second>=10)?second:('0' +second));
                                        
                                        var dateStr = date.getFullYear() + '-' + monthStr + '-' + dayStr  + 
                                        				' ' + hourStr + ':' + minuteStr + ':' + secondStr; */
                                        	var dateStr = date.getFullYear() + '-' + monthStr + '-' + dayStr;			
                                        return dateStr;
                        	}else{
                        		return '';
                        	}
                        }"></th>
                                <th data-options="field:'parameterValue',title:'参数值',sortable:false"></th>
                                <th data-options="field:'status',title:'状态',sortable:false"></th>
                                <th data-options="field:'statusCode',title:'状态/故障代码',sortable:false"></th>
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
       	id:'departmentTg',
       	param:'deviceSiteId:id'
       },
       dialog:{
           id:'departmentAddDialog',
           width:600,
           height:700,
           href:'console/jsp/processRecord_add.jsp',
           buttons:[
           	{text:'保存',handler:function(){
           			var serialNo = $('#serialNo').val();
           			if(serialNo==null || ''===$.trim(serialNo)){
           				return false;
           			}
           			
           			$.get('processRecord/addProcessRecord.do',{
           			collectionDate:$('#collectionDate').val(),
           			serialNo:$('#serialNo').val(),
           			no:$('#no').iCombogrid('getValue'),
           			workSheetId:$('#workSheetId').val(),
           			workPieceCode:$('#workPieceCode').val(),
           			workPieceName:$('#workPieceName').val(),
           			unitType:$('#unitType').val(),
           			graphNumber:$('#graphNumber').val(),
           			version:$('#version').val(),
           			processId:$('#processId').val(),
           			processCode:$('#processCode').val(),
           			processName:$('#processName').val(),
           			batchNumber:$('#batchNumber').val(),
           			'deviceSiteId':$('#departmentTg').iTreegrid('getSelected').id,
           			deviceSiteCode:$('#departmentTg').iTreegrid('getSelected').code,
           			deviceSiteName:$('#departmentTg').iTreegrid('getSelected').name,
           			stoveNumber:$('#stoveNumber').val(),
           			status:$('#status').val()
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
    <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method: 'openDialog',
            extend: '#departmentDg-toolbar',
            iconCls: 'fa fa-pencil',
             parentGrid:{
       	type:'treegrid',
       	id:'departmentTg',
       	param:'deviceSiteId:id'
       },
            dialog: {
            	id:'departmentEditDialog',
                width: 600,
                height: 700,
                href: 'console/jsp/processRecord_edit.jsp',
                url:'processRecord/queryProcessRecordById.do?id={id}',
                 buttons:[
           	{text:'编辑',handler:function(){
           		var serialNo = $('#serialNo').val();
           			if(serialNo==null || ''===$.trim(serialNo)){
           				return false;
           			}
           			$.get('processRecord/updateProcessRecord.do',{
           			id:$('#departmentDg').iDatagrid('getSelected').id,
           			collectionDate:$('#collectionDate').val(),
           			serialNo:$('#serialNo').val(),
           			no:$('#no').iCombogrid('getValue'),
           			workSheetId:$('#workSheetId').val(),
           			workPieceCode:$('#workPieceCode').val(),
           			workPieceName:$('#workPieceName').val(),
           			unitType:$('#unitType').val(),
           			graphNumber:$('#graphNumber').val(),
           			version:$('#version').val(),
           			processId:$('#processId').val(),
           			processCode:$('#processCode').val(),
           			processName:$('#processName').val(),
           			batchNumber:$('#batchNumber').val(),
           			'deviceSiteId':$('#departmentDg').iDatagrid('getSelected').deviceSiteId,
           			deviceSiteCode:$('#departmentDg').iDatagrid('getSelected').deviceSiteCode,
           			deviceSiteName:$('#departmentDg').iDatagrid('getSelected').deviceSiteName,
           			stoveNumber:$('#stoveNumber').val(),
           			status:$('#status').val()
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
    <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method:'doAjax',
       extend: '#departmentDg-toolbar',
       iconCls:'fa fa-trash',
       url:'processRecord/deleteProcessRecord.do',
       grid: {uncheckedMsg:'请先勾选要删除的数据',id:'departmentDg',param:'id:id'}">删除</a>
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
       	type:'treegrid',
       	id:'departmentTg',
       	param:'workPieceCode:workPieceCode,processCode:processCode'
       },
       dialog:{
           id:'positionAddDialog',
            width:600,
           height:600,
           href:'console/jsp/processParameterRecord_add.jsp',
           buttons:[
           	{text:'保存',handler:function(){
           			/*var parameterCode = $('#parameterCode').val();
           			if(parameterCode==null || ''===$.trim(parameterCode)){
           				return false;
           			}*/
           			$.get('processParameterRecord/addProcessParameterRecord.do',{
           			'processRecord.id':$('#departmentDg').iDatagrid('getSelected').id,
           			parameterCode:$('#parameterCode').val(),
           			parameterName:$('#parameterName').val(),
           			upLine:$('#upLine').val(),
           			lowLine:$('#lowLine').val(),
           			standardValue:$('#standardValue').val(),
           			currentDate:$('#currentDate').val(),
           			parameterValue:$('#parameterValue').val(),
           			status:$('#status').val(),
           			statusCode:$('#statusCode').val()
           			},function(data){
           				if(data.success){
	           				$('#positionAddDialog').iDialog('close');
	           				$('#position').iDatagrid('reload');
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
    <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method: 'openDialog',
            extend: '#position-toolbar',
            iconCls: 'fa fa-pencil',
             parentGrid:{
       	type:'treegrid',
       	id:'departmentTg',
       	param:'workPieceCode:workPieceCode,processCode:processCode'
       },
            dialog: {
            	id:'positionEditDialog',
                width: 600,
                height: 700,
                href: 'console/jsp/processParameterRecord_edit.jsp',
                url:'processParameterRecord/queryProcessParameterRecordById.do?id={id}',
                 buttons:[
           	{text:'编辑',handler:function(){
           			$.get('processParameterRecord/updateProcessParameterRecord.do',{
           			id:$('#position').iDatagrid('getSelected').id,
           			'processRecord.id':$('#departmentDg').iDatagrid('getSelected').id,
           			parameterCode:$('#parameterCode').val(),
           			parameterName:$('#parameterName').val(),
           			upLine:$('#upLine').val(),
           			lowLine:$('#lowLine').val(),
           			standardValue:$('#standardValue').val(),
           			currentDate:$('#currentDate').val(),
           			parameterValue:$('#parameterValue').val(),
           			status:$('#status').val(),
           			statusCode:$('#statusCode').val()
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
    <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method:'doAjax',
       extend: '#position-toolbar',
       iconCls:'fa fa-trash',
       url:'processParameterRecord/deleteProcessParameterRecord.do',
       grid: {uncheckedMsg:'请先勾选要删除的数据',id:'position',param:'id:id'}">删除</a>
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