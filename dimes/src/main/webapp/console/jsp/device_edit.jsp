<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div data-toggle="topjui-layout" data-options="fit:true">
	<div
		data-options="region:'center',title:'',fit:true,border:false,bodyCls:'border_right_bottom'">

		<form id="ff" method="post">
			<div title="设备信息" data-options="iconCls:'fa fa-th'">
				<div class="topjui-fluid">
					<div style="height: 30px"></div>
					<div class="topjui-row">
						<div class="topjui-col-sm12">
							<label class="topjui-form-label">设备代码</label>
							<div class="topjui-input-block">
								<input type="text" name="code" data-toggle="topjui-textbox"
									data-options="required:true" id="code">
							</div>
						</div>
					</div>
					<div class="topjui-row">
						<div class="topjui-col-sm12">
							<label class="topjui-form-label">设备名称</label>
							<div class="topjui-input-block">
								<input type="text" name="name" data-toggle="topjui-textbox"
									data-options="required:true" id="name">
							</div>
						</div>
					</div>
					<div class="topjui-row">
						<div class="topjui-col-sm12">
							<label class="topjui-form-label">规格型号</label>
							<div class="topjui-input-block">
								<input type="text" name="unitType" data-toggle="topjui-textbox"
									id="unitType">
							</div>
						</div>
					</div>
					<div class="topjui-row">
						<div class="topjui-col-sm12">
							<label class="topjui-form-label">状态</label>
							<div class="topjui-input-block">
								<input type="text" name="status" data-toggle="topjui-textbox"
									id="deviceStatus">
							</div>
						</div>
					</div>
					<div class="topjui-row">
						<div class="topjui-col-sm12">
							<label class="topjui-form-label">制造商</label>
							<div class="topjui-input-block">
								<input type="text" name="manufacturer" data-toggle="topjui-textbox"
									id="manufacturer">
							</div>
						</div>
					</div>
					<div class="topjui-row">
						<div class="topjui-col-sm12">
							<label class="topjui-form-label">经销商</label>
							<div class="topjui-input-block">
								<input type="text" name="trader" data-toggle="topjui-textbox"
									id="trader">
							</div>
						</div>
					</div>
					<div class="topjui-row">
						<div class="topjui-col-sm12">
							<label class="topjui-form-label">安装日期</label>
							<div class="topjui-input-block">
								<input type="text" name="installDate" data-toggle="topjui-datebox"
									id="installDate" editable="false">
							</div>
						</div>
					</div>
					<div class="topjui-row">
						<div class="topjui-col-sm12">
							<label class="topjui-form-label">出厂日期</label>
							<div class="topjui-input-block">
								<input type="text" name="outFactoryDate" data-toggle="topjui-datebox"
									id="outFactoryDate" editable="false">
							</div>
						</div>
					</div>
					<div class="topjui-row">
						<div class="topjui-col-sm12">
							<label class="topjui-form-label">出厂编码</label>
							<div class="topjui-input-block">
								<input type="text" name="outFactoryCode" data-toggle="topjui-textbox"
									id="outFactoryCode">
							</div>
						</div>
					</div>
					<div class="topjui-row">
						<div class="topjui-col-sm12">
							<label class="topjui-form-label">安装位置</label>
							<div class="topjui-input-block">
								<input type="text" name="installPosition" data-toggle="topjui-textbox"
									id="installPosition">
							</div>
						</div>
					</div>
					<div class="topjui-row">
						<div class="topjui-col-sm12">
							<label class="topjui-form-label">瓶颈设备</label>
							<div class="topjui-input-block">
								<input type="radio" name="bottleneck" value="true">是 <input
									type="radio" name="bottleneck" value="false" checked="checked">否
							</div>
						</div>
					</div>
					<div class="topjui-row">
						<div class="topjui-col-sm12">
							<label class="topjui-form-label">OEE目标</label>
							<div class="topjui-input-block">
								<input type="text" name="goalOee" data-toggle="topjui-textbox"
									id="goalOee">
							</div>
						</div>
					</div>
					<div class="topjui-row">
						<div class="topjui-col-sm12">
							<label class="topjui-form-label">参数取值</label>
							<div class="topjui-input-block">
								<input type="text" name="parameterValueType" data-toggle="topjui-textbox"
									id="parameterValueType">
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
</div>