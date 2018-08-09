<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../common/jsp/head.jsp"%>
<link type="text/css" href="console/js/topjui/css/topjui.timeaxis.css" rel="stylesheet">
 <script>
    	function timeAxis(id){
    		 $.get("workflow/queryWorkflow.do",{businessKey:id},function(data){
    			var $div = $("<div  class='topjui-timeaxis-container' style='height:100%;width:100%;' data-toggle='topjui-timeaxis'>");
    			 var list = JSON.stringify(data.data);
    			//去除key中的单引号
    			list = list.replace(/"(\w+)":/g, "$1:");
    			var reg = new RegExp("\"","g");//g,表示全部替换。
    			var l = list.replace(reg,"'");
    			 $div.attr("data-options","title:'NG流程',list:" + l);
    			 
    			 var $wrapper=$("<div class='wrapper'>");
    			 $div.append($wrapper);
    			 var $light = "<div class='light'><i></i></div>";
    			 $wrapper.append($light);
    			 
    			 var $topjui_timeaxis_main = $("<div class='topjui-timeaxis-main'>");
    			 $wrapper.append($topjui_timeaxis_main);
    			 
    			 var $title = $("<h1 class='title'>");
    			 $topjui_timeaxis_main.append($title);
    			 $title.text("NG流程");
    			 for(var i = 0;i<data.data.length;i++){
    				 var $year = $("<div class='year year" + i +"'>");
    				 $topjui_timeaxis_main.append($year);
    				 
    				 var $yearValue = $("<h2>");
    				 $year.append($yearValue);
    				 
    				 $yearValue.append("<a>" + data.data[i].year + "<i></i></a>");
    				 
    				 var $list = $("<div class='list' style='height:116px;'>");
    				 $year.append($list);
    				 for(var j = 0;j<data.data[i].list.length;j++){
    					 var obj = data.data[i].list[j];
    					 var $ul = $("<ul class='ul"+j+"' style='position:absolute;'>");
    					 $list.append($ul);
    					 
    					 var $li = $("<li class='cls'>");
    					 $ul.append($li);
    					 
    					 var $date = $("<p class='date'>"+obj.date+"</p>");
    					 $li.append($date);
    					 var $intro = $("<p class='intro'>" + obj.intro + "</p>");
    					 $li.append($intro);
    					 var $version = $("<p class='version'>" + obj.version + "</p>");
    					 $li.append($version);
    					 var $more = $("<div class='more more00'>")
    					 $li.append($more);
    					 $more.append("<p>"+obj.more[0]?obj.more:'' + "</p>");
    				 }
    			 }
    			var tab1 = $("#tab1"); 
    			tab1.html($div);
    		}); 
    	}
    	/**动态改变按钮动作*/
    	function handleButtons(row){
    		var $confirmBtn = $("#confirmBtn");
    		var $auditBtn = $("#auditBtn");
    		var $reviewBtn = $("#reviewBtn");
    		
    		if(row.auditDate){
    			$auditBtn.iMenubutton("disable");
    		}else{
    			$auditBtn.iMenubutton("enable");
    			
    		}
    		
    		if(!row.reviewDate && row.auditDate){
    			$reviewBtn.iMenubutton("enable");
    		}else{
    			$reviewBtn.iMenubutton("disable");
    		}
    		
    		if(!row.confirmDate && row.reviewDate){
    			$confirmBtn.iMenubutton("enable");
    		}else{
    			$confirmBtn.iMenubutton("disable");
    		}
    	}
    </script>
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
			           childTab: [{id:'southTabs'}],
			           onClickRow:function(index,row){
			           	 	timeAxis(row.id);
			           	 	handleButtons(row);
			           }">
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
									data-options="field:'processingMethod',title:'处理方法',sortable:false,formatter:function(value,row,index){
		                         if (value) {
		                                       switch(value){
		                                       	case 'scrap':return '报废';
		                                       	case 'repair':return '返修';
		                                       	case 'compromise':return '让步接收';
		                                       }
		                                    }else{
		                                    	return '';
		                                    }
		                        }"></th>
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
                         param:'ngRecordId:id'
                     }">
						<div title="不合格详情" data-options="id:'tab0',iconCls:'fa fa-th'">
                        <table 
                         data-toggle="topjui-datagrid"
                               data-options="id:'position',
                               initCreate: false,
                               fitColumns:true,
						       url:'ngProcessMethod/queryNGProcessMethodsByNGRecordId.do'">
                            <thead>
                            <tr>
                                <th data-options="field:'id',title:'id',hidden:true"></th>
                                <th data-options="field:'processMethod',title:'处理方法',sortable:false,width:'200px',formatter:function(value,row,index){
		                         if (value) {
		                                       switch(value){
		                                       	case 'scrap':return '报废';
		                                       	case 'repair':return '返修';
		                                       	case 'compromise':return '让步接收';
		                                       }
		                                    }else{
		                                    	return '';
		                                    }
		                        }"></th>
                                <th data-options="field:'ngCount',title:'数量',sortable:false,width:'200px'"></th>
                            </tr>
                            </thead>
                        </table>
                    </div>
						<div title="流程时间轴" data-options="id:'tab1',iconCls:'fa fa-th'" id="tab1">
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
			data-options="method: 'openDialog',
            extend: '#departmentDg-toolbar',
            iconCls: 'fa fa-pencil',
            dialog: {
            	id:'auditDialog',
                width:600,
                height: 400,
                href: 'console/jsp/ngRecord_workflow.jsp',
                 buttons:[
           	{text:'审核',handler:function(){
           			$.get('ngRecord/auditNGRecord.do',{
           			id:$('#departmentDg').iDatagrid('getSelected').id,
           			suggestion:$('#suggestion').val()
           			},function(data){
           				if(data.success){
	           				$('#auditDialog').iDialog('close');
	           				$('#departmentDg').iDatagrid('reload');
           				}else{
           					alert(data.msg);
           				}
           			});
           	},iconCls:'fa fa-plus',btnCls:'topjui-btn-normal'},
           	{text:'取消',handler:function(){
           		$('#auditDialog').iDialog('close');
           	},iconCls:'fa fa-close',btnCls:'topjui-btn-normal'},
           ]
            }" id="auditBtn">审核</a>
       <a href="javascript:void(0)" data-toggle="topjui-menubutton"
			data-options="method: 'openDialog',
            extend: '#departmentDg-toolbar',
            iconCls: 'fa fa-pencil',
            dialog: {
            	id:'reviewDialog',
                width:600,
                height: 400,
                href: 'console/jsp/ngRecord_workflow.jsp',
                 buttons:[
           	{text:'复核',handler:function(){
           			$.get('ngRecord/reviewNGRecord.do',{
           			id:$('#departmentDg').iDatagrid('getSelected').id,
           			suggestion:$('#suggestion').val()
           			},function(data){
           				if(data.success){
	           				$('#reviewDialog').iDialog('close');
	           				$('#departmentDg').iDatagrid('reload');
           				}else{
           					alert(data.msg);
           				}
           			});
           	},iconCls:'fa fa-plus',btnCls:'topjui-btn-normal'},
           	{text:'取消',handler:function(){
           		$('#reviewDialog').iDialog('close');
           	},iconCls:'fa fa-close',btnCls:'topjui-btn-normal'},
           ]
            }" id="reviewBtn">复核</a>
       <a href="javascript:void(0)" data-toggle="topjui-menubutton"
			data-options="method: 'openDialog',
            extend: '#departmentDg-toolbar',
            iconCls: 'fa fa-pencil',
            dialog: {
            	id:'confirmDialog',
                width:600,
                height: 400,
                href: 'console/jsp/ngRecord_workflow.jsp',
                 buttons:[
           	{text:'确认',handler:function(){
           			$.get('ngRecord/confirmNGRecord.do',{
           			id:$('#departmentDg').iDatagrid('getSelected').id,
           			suggestion:$('#suggestion').val()
           			},function(data){
           				if(data.success){
	           				$('#confirmDialog').iDialog('close');
	           				$('#departmentDg').iDatagrid('reload');
           				}else{
           					alert(data.msg);
           				}
           			});
           	},iconCls:'fa fa-plus',btnCls:'topjui-btn-normal'},
           	{text:'取消',handler:function(){
           		$('#confirmDialog').iDialog('close');
           	},iconCls:'fa fa-close',btnCls:'topjui-btn-normal'},
           ]
            }" id="confirmBtn">确认</a>
	</div>
	<div id="position-toolbar" class="topjui-toolbar"
		data-options="grid:{
           type:'datagrid',
           id:'position'
       }">
		<a href="javascript:void(0)" data-toggle="topjui-menubutton"
			data-options="method: 'openDialog',
            extend: '#position-toolbar',
            iconCls: 'fa fa-pencil',
            dialog: {
            	id:'positionEditDialog',
                width: 600,
                height: 400,
                href: 'console/jsp/ngProcessMethod_edit.jsp',
                url:'ngProcessMethod/queryNGProcessMethodById.do?id={id}',
                 buttons:[
           	{text:'编辑',handler:function(){
           			var ngCount = $('#ngCount').val();
           			if(ngCount==null || ''===$.trim(ngCount)){
           				return false;
           			}
           			$.get('ngProcessMethod/updateNGProcessMethod.do',{
           			id:$('#position').iDatagrid('getSelected').id,
           			ngCount:ngCount
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
	</div>
	<!-- 相关文档表格工具栏结束 -->
</body>
</html>