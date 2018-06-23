<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div data-toggle="topjui-layout" data-options="fit:true">
	<div
		data-options="region:'center',title:'',fit:true,border:false,bodyCls:'border_right_bottom'">
		<form id="ff" method="post">
			<div title="生产单元信息" data-options="iconCls:'fa fa-th'">
				<div class="topjui-fluid">
					<div style="height: 30px"></div>
					<div class="topjui-row">
						<div class="topjui-col-sm12">
							<label class="topjui-form-label">故障大类</label>
							<div class="topjui-input-block">
								<input id="pressLightTypeName" data-toggle="topjui-combobox"
									name="pressLightTypeName"
									data-options="valueField:'name',textField:'name',
								url:'pressLightType/queryFirstLevelType.do', 
								onSelect: function(rec){
					            var url = 'pressLightType/queryAllPressLightTypesByParentId.do?pid='+rec.id;
					            $('#smallPressLightTypeName').iCombobox('reload', url);
				      		  }">
								<input type="hidden" name="deviceSiteId">
							</div>
						</div>
					</div>
					<div class="topjui-row">
						<div class="topjui-col-sm12">
							<label class="topjui-form-label">原因分类</label>
							<div class="topjui-input-block">
								<input id="smallPressLightTypeName"
									data-toggle="topjui-combobox" name="smallPressLightTypeName"
									data-options="valueField:'name',textField:'name',
								onSelect: function(rec){
					            var url = 'pressLight/queryAllPressLightByTypeId.do?typeId='+rec.id;
					            $('#reason').iCombobox('reload', url);
				      		  }">
								<input type="hidden" name="deviceSiteId">
							</div>
						</div>
					</div>
					<div class="topjui-row">
						<div class="topjui-col-sm12">
							<label class="topjui-form-label">原因小类</label>
							<div class="topjui-input-block">
								<input id="reason" data-toggle="topjui-combobox" name="reason"
									data-options="valueField:'reason',textField:'reason',onSelect:function(rec){
										if(rec.halt){
											$('#halt').attr('checked','checked');
										}else{
											$('#notHalt').attr('checked','checked');
										}
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
									data-options="required:false" id="halt" value='true'>是
								<input type="radio" name="halt" data-toggle="topjui-radio"
									data-options="required:false" id="notHalt" value='false'>否
							</div>
						</div>
					</div>
					<div class="topjui-row">
					<div class="topjui-col-sm12">
						<label class="topjui-form-label">恢复方法</label>
						<div class="topjui-input-block">
							<!-- <input type="textarea" name="recoverMethod"
								data-toggle="topjui-textarea" id="recoverMethod"> -->
								<textarea rows="5" cols="60" id="recoverMethod" name="recoverMethod"></textarea>
						</div>
					</div>
				</div>
				</div>
			</div>

		</form>
	</div>
</div>