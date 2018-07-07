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
			   	   param:'productionUnitId:id',
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
                       url:'workSheet/queryWorkSheetsByProductionUnitId.do',
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
								<th data-options="field:'id',title:'id',checkbox:false"></th>
								<th data-options="field:'no',title:'单号',sortable:false"></th>
								<th
									data-options="field:'manufactureDate',title:'生产日期',sortable:false, formatter:function(value,row,index){
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
                                        
                                        var dateStr = date.getFullYear() + '-' + monthStr + '-' + dayStr ;
                                        return dateStr;
                                    }else{
                                    	return '';
                                    }
                                }"></th>
								<th
									data-options="field:'workPieceCode',title:'工件代码',sortable:false"></th>
								<th
									data-options="field:'workPieceName',title:'工件名称',sortable:false"></th>
								<th data-options="field:'unitType',title:'规格型号',sortable:false"></th>
								<th data-options="field:'graphNumber',title:'图号',sortable:false"></th>
								<th
									data-options="field:'customerGraphNumber',title:'客户图号',sortable:false"></th>
								<th data-options="field:'version',title:'版本号',sortable:false"></th>
								<th
									data-options="field:'productCount',title:'生产数量',sortable:false"></th>
								<th data-options="field:'batchNumber',title:'批号',sortable:false"></th>
								<th data-options="field:'stoveNumber',title:'炉号',sortable:false"></th>
								<th
									data-options="field:'productionUnitName',title:'生产单元',sortable:false"></th>
								<th data-options="field:'status',title:'状态',sortable:false"></th>
								<th data-options="field:'note',title:'备注',sortable:false"></th>
								<th
									data-options="field:'documentMaker',title:'制单人',sortable:false"></th>
								<th
									data-options="field:'makeDocumentDate',title:'制单日期',sortable:false, formatter:function(value,row,index){
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
                                        
                                        var dateStr = date.getFullYear() + '-' + monthStr + '-' + dayStr ;
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
               id:'productionUnitTg',
               param:'productionUnitId:id,productionUnitName:name,productionUnitCode:code'
            },
       dialog:{
           id:'productionUnitAddDialog',
           width:1880,
           height:850,
           href:'console/jsp/workSheet_add.jsp',
           buttons:[
           	{text:'保存',handler:function(){
           	
           		var rows = $('#workSheetDetail').iEdatagrid('getRows');
				$('#workSheetDetail').iEdatagrid('saveRow'); 
				var detailArray = new Array();
				for(var i = 0;i<rows.length;i++){
			 	var row=rows[i];
			    var detail={
						processName:row.processName,
						productionCount:row.productionCount
					};
					detailArray.push(detail);
				}
					//生产总量
			
			var productCount=$('#productCount').val();
					if(!productCount){
						//alert('请填写生产总量');
						return false;
					}
					var  no =$('#no').val();
           			if(no==null || ''===$.trim(no)){
           				//alert('请填写工单号');
						return false;
           			}
           			var workpieceCode = $('#workPieceCode').val();
           			if(workpieceCode==null || ''===$.trim(workpieceCode)){
           					//alert('请选择工件');
			        		return false;
					} 	
					
					var data=[];
						for(var i=0; i<detailArray.length;i++){
						if(!data[detailArray[i].processName]){
							data[detailArray[i].processName] = parseInt(detailArray[i].productionCount);
						}else{
							data[detailArray[i].processName] += parseInt(detailArray[i].productionCount);
						}
					}
           	
           		var flag = false;
           	
           		for(var key in data){
						if(data[key]<productCount){
							flag = true;
							break;
						}
					}
           	
           	if(flag){
				   $.iMessager.confirm('警告','单个工序的生产量和小于生产总量，确认该操作吗？',function(r){
				    if (r){
				    $.get('workSheet/addWorkSheet.do',{
					           			no:no,
					           			manufactureDate:$('#manufactureDate').val(),
					           			workPieceName:$('#workPieceName').val(),
					           			unitType:$('#unitType').val(),
					           			graphNumber:$('#graphNumber').val(),
					           			customerGraphNumber:$('#customerGraphNumber').val(),
					           			version:$('#version').val(),
					           			productCount:$('#productCount').val(),
					           			batchNumber:$('#batchNumber').val(),
					           			stoveNumber:$('#stoveNumber').val(),
					           			productionUnitId:$('#productionUnitId').val(),
					           			productionUnitName:$('#productionUnitName').val(),
					           			productionUnitCode:$('#productionUnitCode').val(),
					           			note:$('#note').val(),
					           			workPieceCode:$('#workPieceCode').val()
					           			},function(data){
					           				if(data.success){
						           				$('#productionUnitAddDialog').iDialog('close');
						           				$('#productionUnitDg').iDatagrid('reload');
					           				}else{
					           					alert(data.msg);
					           				}
					           			});
				    }
				});
           	}else{
           		$.get('workSheet/addWorkSheet.do',{
					           			no:no,
					           			manufactureDate:$('#manufactureDate').val(),
					           			workPieceName:$('#workPieceName').val(),
					           			unitType:$('#unitType').val(),
					           			graphNumber:$('#graphNumber').val(),
					           			customerGraphNumber:$('#customerGraphNumber').val(),
					           			version:$('#version').val(),
					           			productCount:$('#productCount').val(),
					           			batchNumber:$('#batchNumber').val(),
					           			stoveNumber:$('#stoveNumber').val(),
					           			productionUnitId:$('#productionUnitId').val(),
					           			productionUnitName:$('#productionUnitName').val(),
					           			productionUnitCode:$('#productionUnitCode').val(),
					           			note:$('#note').val(),
					           			workPieceCode:$('#workPieceCode').val()
					           			},function(data){
					           				if(data.success){
						           				$('#productionUnitAddDialog').iDialog('close');
						           				$('#productionUnitDg').iDatagrid('reload');
					           				}else{
					           					alert(data.msg);
					           				}
					           			});
           	}
           	
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
                width:1880,
           		height:850,
                href: 'console/jsp/workSheet_edit.jsp',
                url:'workSheet/queryWorkSheetById.do?id={id}',
                 buttons:[
           	{text:'保存',handler:function(){
           		var rows = $('#workSheetDetail').iEdatagrid('getRows');
				$('#workSheetDetail').iEdatagrid('saveRow'); 
				var detailArray = new Array();
				for(var i = 0;i<rows.length;i++){
					var row=rows[i]; var detail={
						id:row.id,
						processId:row.processId,
						processCode:row.processCode,
						processName:row.processName,
						deviceCode:row.deviceCode,
						deviceName:row.deviceName,
						deviceSiteId:row.deviceSiteId,
						deviceSiteCode:row.deviceSiteCode,
						deviceSiteName:row.deviceSiteName,
						completeCount:row.completeCount,
						qualifiedCount:row.qualifiedCount,
						unqualifiedCount:row.unqualifiedCount,
						repairCount:row.repairCount,
						scrapCount:row.scrapCount,
						parameterSource:row.parameterSource,
						firstReport:row.firstReport,
						status:row.status,
						note:row.note,
						reportCount:row.reportCount,
						productionCount:row.productionCount
					};
					
					detailArray.push(detail);
				}
					//生产总量
			var productCount=$('#productCount').val();
					
					var data=[];
			for(var i=0; i<detailArray.length;i++){
						if(!data[detailArray[i].processName]){
							data[detailArray[i].processName] = parseInt(detailArray[i].productionCount);
						}else{
							data[detailArray[i].processName] += parseInt(detailArray[i].productionCount);
						}
					}
					
					var details = JSON.stringify(detailArray);
					var workSheet = JSON.stringify({
           			id:$('#productionUnitDg').iDatagrid('getSelected').id,
           			no:$('#no').val(),
           			manufactureDate:$('#manufactureDate').val(),
           			workPieceName:$('#workPieceName').val(),
           			unitType:$('#unitType').val(),
           			graphNumber:$('#graphNumber').val(),
           			customerGraphNumber:$('#customerGraphNumber').val(),
           			version:$('#version').val(),
           			productCount:$('#productCount').val(),
           			batchNumber:$('#batchNumber').val(),
           			stoveNumber:$('#stoveNumber').val(),
           			productionUnitId:$('#productionUnitId').val(),
           			productionUnitName:$('#productionUnitName').val(),
           			productionUnitCode:$('#productionUnitCode').val(),
           			note:$('#note').val(),
           			workPieceCode:$('#workPieceCode').val()
           			});
					
					var flag = false;
					
					for(var key in data){
						if(data[key]<productCount){
							flag = true;
							break;
						}
					}
					if(flag){
						 $.iMessager.confirm('警告','单个工序生产量和小于生产总量？',function(r){
							    if (r){
							    	$.get('workSheet/updateWorkSheet.do',{details:details,workSheet:workSheet},function(data){
				           				if(data.success){
					           				$('#productionUnitEditDialog').iDialog('close');
					           				$('#productionUnitDg').iDatagrid('reload');
				           				}else{
				           					alert(data.msg);
				           				}
				           			});
							    }
							});
					}else{
						$.get('workSheet/updateWorkSheet.do',{details:details,workSheet:workSheet},function(data){
				           				if(data.success){
					           				$('#productionUnitEditDialog').iDialog('close');
					           				$('#productionUnitDg').iDatagrid('reload');
				           				}else{
				           					alert(data.msg);
				           				}
				           			});
					}
					
				
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
       url:'workSheet/deleteWorkSheet.do',
       grid: {uncheckedMsg:'请先勾选要删除的数据',id:'productionUnitDg',param:'id:id'}">删除</a>
	<!-- 	<a href="javascript:void(0)" data-toggle="topjui-menubutton"
			data-options="method:'doAjax',
       extend: '#productionUnitDg-toolbar',
       iconCls:'fa fa-stop',
       url:'productionUnit/disabledProductionUnit.do',
       grid: {uncheckedMsg:'请选择要停用的生产部门',id:'productionUnitDg',param:'id:id'}">停用</a> -->
	</div>
</body>
</html>