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
						<label class="topjui-form-label">故障大类</label>
						<div class="topjui-input-block">
								<input id="pressLightTypeName" data-toggle="topjui-combobox" name="pressLightTypeName" data-options="valueField:'no',textField:'no',
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
						<label class="topjui-form-label">原因分类</label>
						<div class="topjui-input-block">
								<input id="smallPressLightTypeName" data-toggle="topjui-combobox" name="smallPressLightTypeName" data-options="valueField:'no',textField:'no',
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
						<label class="topjui-form-label">是否停机</label>
						<div class="topjui-input-block">
							<input type="radio" name="halt" data-toggle="topjui-radio"
								data-options="required:false" id="workPieceCode" value='true'>是
							<input type="radio" name="halt" data-toggle="topjui-radio"
								data-options="required:false" id="workPieceCode" value='false'>否
						</div>
					</div>
				</div>
				<div class="topjui-row">
					<div class="topjui-col-sm12">
						<label class="topjui-form-label">原因小类</label>
						<div class="topjui-input-block">
								<input id="reason" data-toggle="topjui-combobox" name="reason" data-options="valueField:'no',textField:'no',
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
						<label class="topjui-form-label">恢复方法</label>
						<div class="topjui-input-block">
							<input type="text" name="recoverMethod" data-toggle="topjui-textarea"
								data-options="required:false" id="recoverMethod">
						</div>
					</div>
				</div>
			</div>
		</div>
		
		</form>
	</div>
</div>