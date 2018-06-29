<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div data-toggle="topjui-layout" data-options="fit:true">
	<div
		data-options="region:'center',title:'',fit:true,border:false,bodyCls:'border_right_bottom'">
		<form id="ff" method="post">
			<div title="装备关联信息" data-options="iconCls:'fa fa-th'">
				<div class="topjui-fluid">
					<div style="height: 30px"></div>

					<div class="topjui-row">
						<div class="topjui-col-sm12">
						<label class="topjui-form-label">关联时间</label>
						<div class="topjui-input-block">
							<input type="text" name="mappingDate" data-toggle="topjui-datetimebox"
								data-options="required:false" id="mappingDate">
						</div>
					</div>
					</div>
					<div class="topjui-row">
						<div class="topjui-col-sm12">
							<label class="topjui-form-label">量具</label>
							<div class="topjui-input-block">
								<input id="equipment" data-toggle="topjui-combobox"
									name="equipmentName"
									data-options="valueField:'id',required:true,textField:'name',
								url:'measuringTool/queryAllMeasuringTools.do'">
							</div>
						</div>
					</div>
					<div class="topjui-row">
						<div class="topjui-col-sm12">
							<label class="topjui-form-label">序列号</label>
							<div class="topjui-input-block">
								<input type="text" name="no" data-toggle="topjui-textbox"
								data-options="required:true" id="no">
							</div>
						</div>
					</div>
					<div class="topjui-row">
						<div class="topjui-col-sm12">
							<label class="topjui-form-label">工单号</label>
							<div class="topjui-input-block">
								<input type="text" name="workSheetCode" data-toggle="topjui-textbox"
								data-options="required:false" id="workSheetCode">
							</div>
						</div>
					</div>
					<div class="topjui-row">
						<div class="topjui-col-sm12">
							<label class="topjui-form-label">使用频次</label>
							<div class="topjui-input-block">
								<input type="number" name="usageRate" data-toggle="topjui-textbox"
								data-options="required:false" id="usageRate">
							</div>
						</div>
					</div>
					<div class="topjui-row">
						<div class="topjui-col-sm12">
							<label class="topjui-form-label">辅助人</label>
							<div class="topjui-input-block">
								<input id="helperName" data-toggle="topjui-combobox"
									name="helperName"
									data-options="valueField:'username',textField:'username',
								url:'user/queryNotCurrentUsers.do',onSelect:function(rec){
									$('#helperId').val(rec.id);
								}">
								
								<input type="hidden" name="helperId" id="helperId"/>
							</div>
						</div>
					</div>
				</div>
			</div>

		</form>
	</div>
</div>