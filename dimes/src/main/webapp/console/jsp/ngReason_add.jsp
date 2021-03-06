<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div data-toggle="topjui-layout" data-options="fit:true">
	<div
		data-options="region:'center',title:'',fit:true,border:false,bodyCls:'border_right_bottom'">

		<form id="ff" method="post">
			<div title="参数信息" data-options="iconCls:'fa fa-th'">
				<div class="topjui-fluid">
					<div style="height: 30px"></div>
					<div class="topjui-row">
						<div class="topjui-col-sm12">
							<label class="topjui-form-label">不良原因代码</label>
							<div class="topjui-input-block">
								<input type="text" name="ngCode" data-toggle="topjui-textbox"
									data-options="required:true" id="ngCode">
							</div>
						</div>
					</div>
					<div class="topjui-row">
						<div class="topjui-col-sm12">
							<label class="topjui-form-label">不良原因</label>
							<div class="topjui-input-block">
								<input type="text" name="ngReason" data-toggle="topjui-textbox"
									data-options="required:true" id="ngReason">
							</div>
						</div>
					</div>
					<div class="topjui-row">
						<div class="topjui-col-sm12">
							<label class="topjui-form-label">备注</label>
							<div class="topjui-input-block">
								<input type="text" name="note" data-toggle="topjui-textarea"
									id="note">
							</div>
						</div>
					</div>
					<div class="topjui-row">
						<div class="topjui-col-sm12">
							<label class="topjui-form-label">处理方法</label>
							<div class="topjui-input-block">
								<input id="processingMethod" name="processingMethod"
									data-toggle="topjui-combobox"
									data-options="valueField: 'text',
textField: 'text',
data: [{
    text: '报废',
},{
    text: '返修'
},{
    text: '让步接收'
}]">
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
</div>