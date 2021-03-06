<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<script>
	$(function(){
		$('#equipment').iCombogrid({
		    idField:'id',
		    textField:'name',
		    delay: 500,
		    mode: 'remote',
		    url:'equipment/queryAllEquipments.do',
		    columns:[[
		        {field:'id',title:'id',width:60,hidden:true},
		        {field:'code',title:'代码',width:100},
		        {field:'name',title:'名称',width:100},
		        {field:'unitType',title:'规格型号',width:120}
		    ]]
		});

	});
</script>
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
							<label class="topjui-form-label">装备</label>
						 <div class="topjui-input-block">
								<!--	<input id="equipment" data-toggle="topjui-combobox"
									name="equipment"
									data-options="valueField:'id',required:true,textField:'name',
								url:'equipment/queryAllEquipments.do'"> -->
								
								<input id="equipment" name="equipment" data-toggle="topjui-combogrid">
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
									data-options="valueField:'id',textField:'username',
								url:'user/queryNotCurrentUsers.do',onSelect:function(rec){
									$('#helperRealName').val(rec.username);
								}">
								
								<input type="hidden" name="helperRealName" id="helperRealName"/>
							</div>
						</div>
					</div>
				</div>
			</div>

		</form>
	</div>
</div>