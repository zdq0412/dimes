<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div data-toggle="topjui-layout" data-options="fit:true">
	<div
		data-options="region:'center',title:'',fit:true,border:false,bodyCls:'border_right_bottom'">
		<form id="ff" method="post" >
		<div title="生产单元信息" data-options="iconCls:'fa fa-th'">
			<div class="topjui-fluid">
				<div style="height:30px"></div>
				<div class="topjui-row">
					<div class="topjui-col-sm12">
						<label class="topjui-form-label">生产时间</label>
						<div class="topjui-input-block">
							<input type="text" name="collectionDate" data-toggle="topjui-datebox"
								data-options="required:false" id="collectionDate">
						</div>
					</div>
				</div>
				<div class="topjui-row">
					<div class="topjui-col-sm12">
						<label class="topjui-form-label">生产序号</label>
						<div class="topjui-input-block">
							<input type="text" name="serialNo" data-toggle="topjui-textbox"
								data-options="required:true" id="serialNo">
						</div>
					</div>
				</div>
				<div class="topjui-row">
					<div class="topjui-col-sm12">
						<label class="topjui-form-label">工单单号</label>
						<div class="topjui-input-block">
								<input id="no" data-toggle="topjui-combobox" name="no" data-options="valueField:'no',textField:'no',
								url:'processRecord/queryOtherWorkSheetByDeviceSiteId.do?deviceSiteId={deviceSiteId}', 
								onSelect: function(rec){
								$('#workPieceCode').val(rec.workPieceCode);
								$('#workPieceName').val(rec.workPieceName);
								$('#unitType').val(rec.unitType);
								$('#graphNumber').val(rec.graphNumber);
								$('#version').val(rec.version);
					            var url = 'processRecord/queryWorkSheetDetailsByWorkSheetId?workSheetId='+rec.id;
					            $('#processCode').iCombobox('reload', url);
				      		  }">
								<input type="hidden" name="deviceSiteId">
						</div>
					</div>
				</div>
				<div class="topjui-row">
					<div class="topjui-col-sm12">
						<label class="topjui-form-label">工件代码</label>
						<div class="topjui-input-block">
							<input type="text" name="workPieceCode" data-toggle="topjui-textbox"
								data-options="required:false" id="workPieceCode">
						</div>
					</div>
				</div>
				<div class="topjui-row">
					<div class="topjui-col-sm12">
						<label class="topjui-form-label">工件名称</label>
						<div class="topjui-input-block">
							<input type="text" name="workPieceName" data-toggle="topjui-textbox"
								data-options="required:false" id="workPieceName">
						</div>
					</div>
				</div>
				<div class="topjui-row">
					<div class="topjui-col-sm12">
						<label class="topjui-form-label">规格型号</label>
						<div class="topjui-input-block">
							<input type="text" name="unitType" data-toggle="topjui-textbox"
								data-options="required:false" id="unitType">
						</div>
					</div>
				</div>
				<div class="topjui-row">
					<div class="topjui-col-sm12">
						<label class="topjui-form-label">图号</label>
						<div class="topjui-input-block">
							<input type="text" name="graphNumber" data-toggle="topjui-textbox"
								data-options="required:false" id="graphNumber">
						</div>
					</div>
				</div>
				<div class="topjui-row">
					<div class="topjui-col-sm12">
						<label class="topjui-form-label">版本号</label>
						<div class="topjui-input-block">
							<input type="text" name="version" data-toggle="topjui-textbox"
								data-options="required:false" id="version">
						</div>
					</div>
				</div>
				<div class="topjui-row">
					<div class="topjui-col-sm12">
						<label class="topjui-form-label">工序代码</label>
						<div class="topjui-input-block">
								<input id="processCode" data-toggle="topjui-combobox" name="processCode" data-options="valueField:'processCode',textField:'processCode',onSelect:function(rec){
									$('#processName').val(rec.processName);
								}">
						</div>
					</div>
				</div>
				<div class="topjui-row">
					<div class="topjui-col-sm12">
						<label class="topjui-form-label">工序名称</label>
						<div class="topjui-input-block">
							<input type="text" name="processName" data-toggle="topjui-textbox"
								data-options="required:false" id="processName">
						</div>
					</div>
				</div>
				<div class="topjui-row">
					<div class="topjui-col-sm12">
						<label class="topjui-form-label">批号</label>
						<div class="topjui-input-block">
							<input type="text" name="batchNumber" data-toggle="topjui-textbox"
								data-options="required:false" id="batchNumber">
						</div>
					</div>
				</div>
				<div class="topjui-row">
					<div class="topjui-col-sm12">
						<label class="topjui-form-label">炉号</label>
						<div class="topjui-input-block">
							<input type="text" name="stoveNumber" data-toggle="topjui-textbox"
								data-options="required:false" id="stoveNumber">
						</div>
					</div>
				</div>
			</div>
		</div>
		
		</form>
	</div>
</div>