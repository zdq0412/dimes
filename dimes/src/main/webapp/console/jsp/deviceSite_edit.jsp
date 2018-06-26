<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div data-toggle="topjui-layout" data-options="fit:true">
	<div
		data-options="region:'center',title:'',fit:true,border:false,bodyCls:'border_right_bottom'">
		
		<form id="ff" method="post" >
		<div title="站点信息" data-options="iconCls:'fa fa-th'">
			<div class="topjui-fluid">
				<div style="height:30px"></div>
				<div class="topjui-row">
					<div class="topjui-col-sm12">
						<label class="topjui-form-label">设备站点代码</label>
						<div class="topjui-input-block">
							<input type="text" name="code" data-toggle="topjui-textbox"
								data-options="required:true" id="code" readonly="readonly">
						</div>
					</div>
				</div>
				<div class="topjui-row">
					<div class="topjui-col-sm12">
						<label class="topjui-form-label">设备站点名称</label>
						<div class="topjui-input-block">
							<input type="text" name="name" data-toggle="topjui-textbox"
								data-options="required:true" id="name">
						</div>
					</div>
				</div>
				<div class="topjui-row">
					<div class="topjui-col-sm12">
						<label class="topjui-form-label">条码读头地址</label>
						<div class="topjui-input-block">
							<input type="text" name="barCodeAddress" data-toggle="topjui-textbox"
								id="barCodeAddress">
						</div>
					</div>
				</div>
				<div class="topjui-row">
						<div class="topjui-col-sm12">
							<label class="topjui-form-label">参数页面中显示</label>
							<div class="topjui-input-block">
								<input type="checkbox" name="show" value="true" id="show">
							</div>
						</div>
					</div>
				<div class="topjui-row">
						<div class="topjui-col-sm12">
							<label class="topjui-form-label">状态</label>
							<div class="topjui-input-block">
									<input type="text" name="status" id="status" data-toggle="topjui-combobox"  data-options="valueField:'value',textField:'text',data: [{text:'运行', value: '0'},{text: '待机',value: '1'},{text: '停机',value: '2'}]">
							</div>
						</div>
					</div>
				<div class="topjui-row">
					<div class="topjui-col-sm12">
						<label class="topjui-form-label">备注</label>
						<div class="topjui-input-block">
							<input type="text" name="note"
								data-toggle="topjui-textarea" id="note">
						</div>
					</div>
				</div>
			</div>
		</div>
		</form>
	</div>
</div>