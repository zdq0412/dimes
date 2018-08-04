<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<script>
	$(function(){
		//不合格类型
		$("#ngTypeName").iCombotreegrid({
			url : 'ngReasonType/queryTopNgReasonTypes.do',
			idField : 'name',
			treeField : 'name',
			columns : [ [ {
				field : 'name',
				title : '名称',
				width : 300
			} ] ],
			onClickRow : function(row) {
				$("#ngTypeId").val(row.id);
				var url = "ngReason/queryNGReasonsByNGReasonTypeIdNoPager.do?ngReasonTypeId=" + row.id;
				$("#ngReason").iCombobox("reload",url);
			} 
		});
		
		//工单选择
		$('#no').iCombogrid({
		    idField:'no',
		    textField:'no',
		    delay: 500,
		    mode: 'remote',
		    url:'workSheet/queryWorkSheetByDeviceSiteId.do',
		    columns:[[
		        {field:'id',title:'id',width:60,hidden:true},
		        {field:'no',title:'工单号',width:100},
		        {field:'workPieceCode',title:'工件代码',width:100},
		        {field:'customerGraphNumber',title:'客户图号',width:100},
		        {field:'batchNumber',title:'批号',width:100},
		        {field:'stoveNumber',title:'炉号',width:100},
		        {field:'productCount',title:'生产数量',width:100}
		    ]],
		    onClickRow : function(index, row) {
		    	$('#workpieceCode').textbox('setValue',row.workPieceCode);
				$('#workpieceName').textbox('setValue',row.workPieceName);
				$('#unitType').textbox('setValue',row.unitType);
				$('#graphNumber').textbox('setValue',row.graphNumber);
				$('#customerGraphNumber').textbox('setValue',row.customerGraphNumber);
				$('#batchNumber').textbox('setValue',row.batchNumber);
				$('#stoveNumber').textbox('setValue',row.stoveNumber);
				
				
				$('#version').textbox('setValue',row.version);
				$('#workSheetId').val(row.id);
	           var url = 'workSheet/queryWorkSheetDetailByWorkSheetIdAndDeviceSiteId.do?workSheetId='+row.id +"&deviceSiteId="+$("#departmentTg").iTreegrid("getSelected").id;
	           $('#processCode').iCombogrid("grid").iDatagrid({idField:'code',
	   		    textField:'code',
			    delay: 500,
			    mode: 'remote',
			    url:url,
			    columns:[[
			        {field:'processId',title:'id',width:60,hidden:true},
			        {field:'processCode',title:'工序代码',width:100},
			        {field:'processName',title:'工序名称',width:100}
			    ]],
			    onClickRow : function(index, row) {
					$('#processName').textbox('setValue',row.name);
					$('#processId').val(row.processId);
			    },
			    onLoadSuccess:function(data){
			    	if(!!data && data.rows.length>0){
			    		$("#processCode").textbox("setValue",data.rows[0].processCode);
			    		$("#processName").textbox("setValue",data.rows[0].processName);
			    		$("#processId").val(data.rows[0].processId);
			    	}
			    }
	           });
		    },
		    queryParams:{
		    	deviceSiteId:$("#departmentTg").iTreegrid("getSelected").id
		    }
		});
		//工件选择
		$('#workpieceCode').iCombogrid({
		    idField:'code',
		    textField:'code',
		    delay: 500,
		    mode: 'remote',
		    url:'workpiece/queryWorkpieces.do',
		    columns:[[
		        {field:'id',title:'id',width:60,hidden:true},
		        {field:'code',title:'工件代码',width:100},
		        {field:'name',title:'工件名称',width:100},
		        {field:'unitType',title:'规格型号',width:100},
		        {field:'customerGraphNumber',title:'客户图号',width:100}
		    ]],
		    onClickRow : function(index, row) {
				$('#workPieceName').textbox('setValue',row.workPieceName);
				$('#unitType').textbox('setValue',row.unitType);
				$('#graphNumber').textbox('setValue',row.graphNumber);
				$('#version').textbox('setValue',row.version);
				$('#workSheetId').val('');
				$("#no").textbox("setValue","");
				//查找工序
				 var url = 'processes/queryProcessByWorkpieceIdAndDeviceSiteId.do?workpieceId='+row.id +"&deviceSiteId="+$("#departmentTg").iTreegrid("getSelected").id;
		          $('#processCode').iCombogrid("grid").iDatagrid({idField:'code',
		  		    textField:'code',
				    delay: 500,
				    mode: 'remote',
				    url:url,
				    columns:[[
				        {field:'id',title:'id',width:60,hidden:true},
				        {field:'code',title:'工序代码',width:100},
				        {field:'name',title:'工序名称',width:100}
				    ]],
				    onClickRow : function(index, row) {
						$('#processName').textbox('setValue',row.name);
						$('#processId').val(row.id);
				    }});
		    },
		    queryParams:{
		    	deviceSiteId:$("#departmentTg").iTreegrid("getSelected").id
		    }
		});
		//工序选择
		$('#processCode').iCombogrid({
		    idField:'code',
		    textField:'code',
		    delay: 500,
		    mode: 'remote',
		    url:'',
		    columns:[[
		        {field:'id',title:'id',width:60,hidden:true},
		        {field:'code',title:'工序代码',width:100},
		        {field:'name',title:'工序名称',width:100}
		    ]],
		    onClickRow : function(index, row) {
				$('#processName').textbox('setValue',row.name);
				$('#processId').val(row.id);
		    }
		});
	});
</script>
<div data-toggle="topjui-layout" data-options="fit:true">
	<div
		data-options="region:'center',title:'',fit:true,border:false,bodyCls:'border_right_bottom'">
		<form id="ff" method="post">
			<div title="不合格记录" data-options="iconCls:'fa fa-th'">
				<div class="topjui-fluid">
					<div style="height: 30px"></div>
					<div class="topjui-row">
					<div class="topjui-col-sm12">
						<label class="topjui-form-label">时间</label>
						<div class="topjui-input-block">
							<input type="text" name="occurDate" data-toggle="topjui-datetimebox"
								data-options="required:false" id="occurDate">
						</div>
					</div>
				</div>
					<div class="topjui-row">
						<div class="topjui-col-sm12">
							<label class="topjui-form-label">工单号</label>
						 	<div class="topjui-input-block">
								<input id="no" data-toggle="topjui-combogrid" name="no">
								<input id="workSheetId" name="workSheetId" type="hidden">
							</div>
						</div>
					</div>
					<div class="topjui-row">
						<div class="topjui-col-sm12">
							<label class="topjui-form-label">工件代码</label>
							<div class="topjui-input-block">
								<input type="text" name="workpieceCode" data-toggle="topjui-combogrid"
								data-options="required:true" id="workpieceCode">
							</div>
						</div>
					</div>
					<div class="topjui-row">
						<div class="topjui-col-sm12">
							<label class="topjui-form-label">工件名称</label>
							<div class="topjui-input-block">
								<input type="text" name="workpieceName" data-toggle="topjui-textbox"
								data-options="required:false" id="workpieceName" readonly="readonly">
							</div>
						</div>
					</div>
					<div class="topjui-row">
						<div class="topjui-col-sm12">
							<label class="topjui-form-label">工序代码</label>
							<div class="topjui-input-block">
								<input type="text" name="processCode" data-toggle="topjui-combogrid"
								data-options="required:false" id="processCode">
								
								<input type="hidden" name="processId" id="processId" />
							</div>
						</div>
					</div>
					<div class="topjui-row">
						<div class="topjui-col-sm12">
							<label class="topjui-form-label">工序名称</label>
							<div class="topjui-input-block">
								<input type="text" name="processName" data-toggle="topjui-textbox"
								data-options="required:false" id="processName" >
							</div>
						</div>
					</div>
					<div class="topjui-row">
						<div class="topjui-col-sm12">
							<label class="topjui-form-label">规格型号</label>
							<div class="topjui-input-block">
								<input type="text" name="unitType" data-toggle="topjui-textbox"
								data-options="required:false" id="unitType" readonly="readonly">
							</div>
						</div>
					</div>
					<div class="topjui-row">
						<div class="topjui-col-sm12">
							<label class="topjui-form-label">客户图号</label>
							<div class="topjui-input-block">
								<input type="text" name="customerGraphNumber" data-toggle="topjui-textbox"
								data-options="required:false" id="customerGraphNumber" readonly="readonly">
							</div>
						</div>
					</div>
					<div class="topjui-row">
						<div class="topjui-col-sm12">
							<label class="topjui-form-label">图号</label>
							<div class="topjui-input-block">
								<input type="text" name="graphNumber" data-toggle="topjui-textbox"
								data-options="required:false" id="graphNumber" readonly="readonly">
							</div>
						</div>
					</div>
					<div class="topjui-row">
						<div class="topjui-col-sm12">
							<label class="topjui-form-label">版本号</label>
							<div class="topjui-input-block">
								<input type="text" name="version" data-toggle="topjui-textbox"
								data-options="required:false" id="version" readonly="readonly">
							</div>
						</div>
					</div>
					<div class="topjui-row">
						<div class="topjui-col-sm12">
							<label class="topjui-form-label">批号</label>
							<div class="topjui-input-block">
								<input type="text" name="batchNumber" data-toggle="topjui-textbox"
								data-options="required:false" id="batchNumber" readonly="readonly">
							</div>
						</div>
					</div>
					<div class="topjui-row">
						<div class="topjui-col-sm12">
							<label class="topjui-form-label">炉号</label>
							<div class="topjui-input-block">
								<input type="text" name="stoveNumber" data-toggle="topjui-textbox"
								data-options="required:false" id="stoveNumber" readonly="readonly">
							</div>
						</div>
					</div>
					<div class="topjui-row">
						<div class="topjui-col-sm12">
							<label class="topjui-form-label">原因类别</label>
							<div class="topjui-input-block">
								<input type="text" name="ngTypeName"  id="ngTypeName">
								
								<input type="hidden" id="ngTypeId" name="ngTypeId" />
							</div>
						</div>
					</div>
					<div class="topjui-row">
						<div class="topjui-col-sm12">
							<label class="topjui-form-label">原因</label>
							<div class="topjui-input-block">
							
							<input id="ngReason" data-toggle="topjui-combobox" name="ngReason" 
								data-options="valueField:'id',textField:'ngReason',onSelect:function(rec){
									$('#ngReasonId').val(rec.id);
								}">
							
								<input type="hidden" id="ngReasonId" name="ngReasonId" />
							</div>
						</div>
					</div>
					<div class="topjui-row">
						<div class="topjui-col-sm12">
							<label class="topjui-form-label">处理方法</label>
							<div class="topjui-input-block">
								<input id="processingMethod" name="processingMethod"
									data-toggle="topjui-combobox"
									data-options="valueField: 'value',
										textField: 'text',
										data: [{
											value:'scrap',
										    text: '报废',
										},{
											value:'repair',
										    text: '返修'
										},{
											value:'compromise',
										    text: '让步接收'
										}]">
							</div>
						</div>
					</div>
					<div class="topjui-row">
						<div class="topjui-col-sm12">
							<label class="topjui-form-label">数量</label>
							<div class="topjui-input-block">
								<input type="number" name="ngCount" data-toggle="topjui-textbox"
								data-options="required:false" id="ngCount">
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
</div>