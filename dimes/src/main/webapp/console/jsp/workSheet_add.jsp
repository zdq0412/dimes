<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script>
$(function(){
		//查询所有工件
		$('#workPieceCode').iCombogrid({
		    idField:'id',
		    textField:'name',
		    delay: 500,
		    mode: 'remote',
		    url:'workpiece/queryWorkpieces.do',
		    columns:[[
		        {field:'id',title:'id',width:60,hidden:true},
		        {field:'code',title:'代码',width:100},
		        {field:'name',title:'名称',width:100},
		        {field:'unitType',title:'规格型号',width:100},
		        {field:'customerGraphNumber',title:'客户图号',width:100}
		    ]],
		    onClickRow:function(index,row){
		    	$("#workPieceName").textbox("setValue",row.name);
		    	$("#unitType").textbox("setValue",row.unitType);
		    	$("#customerGraphNumber").textbox("setValue",row.customerGraphNumber);
		    	$("#graphNumber").textbox("setValue",row.graphNumber);
		    	$("#version").textbox("setValue",row.version);
		    }
		});
		
		// 可编辑工单 详情
	 	$('#workSheetDetail').iEdatagrid({
		    url: '',
		    saveUrl:'',
		    updateUrl: '',
		    destroyUrl: ''
		}); 
});
	
</script>
<div data-toggle="topjui-layout" data-options="fit:true">
	<div
		data-options="region:'center',title:'',fit:true,border:false,bodyCls:'border_right_bottom'">
		<form id="addWorkSheetForm" method="post">
			<div title="工单信息" data-options="iconCls:'fa fa-th'">
				<div class="topjui-fluid">
					<div
						style="height: 30px; width: 100%; text-align: center; font-size: 1.5em; font-weight: bold; margin: 20px auto;">
						生产任务单</div>
					<div class="topjui-row">
						<div class="topjui-col-sm3">
							<label class="topjui-form-label">单号</label>
							<div class="topjui-input-block">
								<input type="text" name="no" data-toggle="topjui-textbox"
									data-options="required:true" id="no">
							</div>
						</div>
						<div class="topjui-col-sm3">
							<label class="topjui-form-label">生产日期</label>
							<div class="topjui-input-block">
								<input type="text" name="manufactureDate" style="width:100%;" data-toggle="topjui-datetimebox" 
								data-options="required:true,showSeconds:true" id="manufactureDate">
							</div>
						</div>
						<div class="topjui-col-sm3">
							<label class="topjui-form-label">工件代码</label>
							<div class="topjui-input-block">
								<input type="text" name="workPieceCode" data-toggle="topjui-combogrid"
									data-options="required:false" id="workPieceCode">
							</div>
						</div>
						<div class="topjui-col-sm3">
							<label class="topjui-form-label">工件名称</label>
							<div class="topjui-input-block">
								<input type="text" name="workPieceName" data-toggle="topjui-textbox"
									data-options="required:false" id="workPieceName" readonly="readonly" />
							</div>
						</div>
					</div>
					<div class="topjui-row">
						
						<div class="topjui-col-sm3">
							<label class="topjui-form-label">规格型号</label>
							<div class="topjui-input-block">
								<input type="text" name="unitType" data-toggle="topjui-textbox"
									data-options="required:false" id="unitType" readonly="readonly">
							</div>
						</div>
						<div class="topjui-col-sm3">
							<label class="topjui-form-label">图号</label>
							<div class="topjui-input-block">
								<input type="text" name="graphNumber" data-toggle="topjui-textbox"
									data-options="required:false" id="graphNumber" readonly="readonly">
							</div>
						</div>
						<div class="topjui-col-sm3">
							<label class="topjui-form-label">客户图号</label>
							<div class="topjui-input-block">
								<input type="text" name="customerGraphNumber" data-toggle="topjui-textbox"
									data-options="required:false" id="customerGraphNumber" readonly="readonly">
							</div>
						</div>
						<div class="topjui-col-sm3">
							<label class="topjui-form-label">版本号</label>
							<div class="topjui-input-block">
								<input type="text" name="version" data-toggle="topjui-textbox"
									data-options="required:false" id="version" readonly="readonly">
							</div>
						</div>
						
					</div>
					<div class="topjui-row">
						
						<div class="topjui-col-sm3">
							<label class="topjui-form-label">生产总量</label>
							<div class="topjui-input-block">
								<input type="text" name="productCount" data-toggle="topjui-textbox"
									data-options="required:false" id="productCount">
							</div>
						</div>
						<div class="topjui-col-sm3">
							<label class="topjui-form-label">批号</label>
							<div class="topjui-input-block">
								<input type="text" name="batchNumber" data-toggle="topjui-textbox"
									data-options="required:false" id="batchNumber">
							</div>
						</div>
						<div class="topjui-col-sm3">
							<label class="topjui-form-label">炉号</label>
							<div class="topjui-input-block">
								<input type="text" name="stoveNumber" data-toggle="topjui-textbox"
									data-options="required:false" id="stoveNumber">
							</div>
						</div>
						<div class="topjui-col-sm3">
							<label class="topjui-form-label">生产单元</label>
							<div class="topjui-input-block">
							<!-- 	<input data-toggle="topjui-combotreegrid" data-options="width:'100%',
							        panelWidth:500,
							        url:'',
							        idField:'id',
							        treeField:'name',
							        columns:[[
							            {field:'id',title:'id',hidden:true},
							            {field:'code',title:'编码',width:100},
							            {field:'name',title:'名称',width:100}
							        ]]"  name="productionUnitName" id="productionUnitName">
							        查询具有装备的生产单元 -->
								<input type="hidden" name="productionUnitId"  id="productionUnitId">
								<input type="text" name="productionUnitName"  id="productionUnitName" data-toggle="topjui-textbox" readonly="readonly">
							</div>
						</div>
					</div>
					<div class="topjui-row">
						<div class="topjui-col-sm6">
							<label class="topjui-form-label">备注</label>
							<div class="topjui-input-block">
								<input type="text" name="note" data-toggle="topjui-textbox"
									data-options="required:false" id="note">
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>


	<div data-options="region:'south',fit:false,split:true,border:false"
		style="height: 65%">
		<div data-toggle="topjui-tabs"
			data-options="id:'southTabs',
                     fit:true,
                     border:false
                    ">
			<div title="工序流转" data-options="id:'tab0',iconCls:'fa fa-th'">
				<table  title="" id="workSheetDetail">
					<thead>
						<tr>
							<th
								data-options="field:'id',title:'id',checkbox:false,hidden:true,width:200,align:'center'"></th>
							<th
								data-options="field:'processCode',title:'工序代码',checkbox:false,width:100,align:'center'"></th>
							<th
								data-options="field:'processName',title:'工序名称',align:'center'"></th>
							<th
								data-options="field:'deviceCode',title:'设备代码',width:100,align:'center'"></th>
							<th data-options="field:'deviceName',title:'设备名称',width:100,align:'center'"></th>
							<th data-options="field:'deviceSiteCode',title:'站点代码',width:100,align:'center'"></th>
							<th data-options="field:'deviceSiteName',title:'站点名称',width:100,align:'center'"></th>
							<th data-options="field:'completeCount',title:'完工数量',width:100,align:'center'"></th>
							<th data-options="field:'qualifiedCount',title:'合格数量',width:100,align:'center'"></th>
							<th data-options="field:'unqualifiedCount',title:'不合格数量',width:100,align:'center'"></th>
							<th data-options="field:'repairCount',title:'返修数量',width:100,align:'center'"></th>
							<th data-options="field:'scrapCount',title:'报废数量',width:100,align:'center'"></th>
							<th data-options="field:'parameterSource',title:'参数取值来源',width:100,align:'center'"></th>
							<th data-options="field:'firstReport',title:'首件报告',width:100,align:'center'"></th>
							<th data-options="field:'status',title:'状态',width:100,align:'center'"></th>
							<th data-options="field:'note',title:'备注',width:100,align:'center'"></th>
						</tr>
					</thead>
				</table>
			</div>
			<div title="条码信息" data-options="id:'tab1',iconCls:'fa fa-th'">
				<table data-toggle="topjui-datagrid"
					data-options="id:'relateDoc',
                               initCreate: false,
                               fitColumns:true,
						       url:'workpiece/queryWorkpieceProcessDeviceSiteMappingsByWorkpieceId.do'">
					<thead>
						<tr>
							<th
								data-options="field:'id',title:'id',checkbox:false,hidden:true,width:80"></th>
							<th
								data-options="field:'processTypeName',title:'工序类别',width:80,formatter:function(value,row,index){
											if(row.workpieceProcess.process){
												return row.workpieceProcess.process.processType.name;
											}else{
												return '';
											}
										}"></th>
							<th
								data-options="field:'processCode',title:'工序代码',width:80,formatter:function(value,row,index){
											if(row.workpieceProcess.process){
												return row.workpieceProcess.process.code;
											}else{
												return '';
											}
										}"></th>
							<th
								data-options="field:'processName',title:'工序名称',width:80,formatter:function(value,row,index){
											if(row.workpieceProcess.process){
												return row.workpieceProcess.process.name;
											}else{
												return '';
											}
										}"></th>
							<th
								data-options="field:'deviceCode',title:'设备代码',width:80,formatter:function(value,row,index){
											if(row.deviceSite){
												return row.deviceSite.device.code;
											}else{
												return '';
											}
										}"></th>
							<th
								data-options="field:'deviceName',title:'设备名称',width:80,formatter:function(value,row,index){
											if(row.deviceSite){
												return row.deviceSite.device.name;
											}else{
												return '';
											}
										}"></th>
							<th
								data-options="field:'deviceUnitType',title:'规格型号',width:80,formatter:function(value,row,index){
											if(row.deviceSite){
												return row.deviceSite.device.unitType;
											}else{
												return '';
											}
										}"></th>
							<th
								data-options="field:'deviceSite.code',title:'站点代码',width:80,formatter:function(value,row,index){
											if(row.deviceSite){
												return row.deviceSite.code;
											}else{
												return '';
											}
										}"></th>
							<th
								data-options="field:'deviceSite.name',title:'站点名称',formatter:function(value,row,index){
											if(row.deviceSite){
												return row.deviceSite.name;
											}else{
												return '';
											}
										}"></th>
							<th
								data-options="field:'processingBeat',title:'加工节拍(s)',width:80,sortable:false"></th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
</div>