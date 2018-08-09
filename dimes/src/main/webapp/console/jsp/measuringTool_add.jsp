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
							<label class="topjui-form-label">量具代码</label>
							<div class="topjui-input-block">
								<input type="text" name="code" data-toggle="topjui-textbox"
									data-options="required:true" id="code">
							</div>
						</div>
					</div>
					<div class="topjui-row">
						<div class="topjui-col-sm12">
							<label class="topjui-form-label">量具名称</label>
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
									data-options="required:false" id="unitType">
							</div>
						</div>
					</div>
					<div class="topjui-row">
						<div class="topjui-col-sm12">
							<label class="topjui-form-label">状态</label>
							<div class="topjui-input-block">
								<input type="text" name="status" data-toggle="topjui-textbox"
									data-options="required:false" id="status">
							</div>
						</div>
					</div>
					<div class="topjui-row">
						<div class="topjui-col-sm12">
							<label class="topjui-form-label">生产厂家</label>
							<div class="topjui-input-block">
								<input type="text" name="manufacturer"
									data-toggle="topjui-textbox" data-options="required:false"
									id="manufacturer">
							</div>
						</div>
					</div>
					<div class="topjui-row">
						<div class="topjui-col-sm12">
							<label class="topjui-form-label">经销商</label>
							<div class="topjui-input-block">
								<input type="text" name="trader" data-toggle="topjui-textbox"
									data-options="required:false" id="trader">
							</div>
						</div>
					</div>
					<div class="topjui-row">
						<div class="topjui-col-sm12">
							<label class="topjui-form-label">生产日期</label>
							<div class="topjui-input-block">
								<input type="text" name="outFactoryDate"
									data-toggle="topjui-datebox" data-options="required:false"
									id="outFactoryDate">
							</div>
						</div>
					</div>
					<div class="topjui-row">
						<div class="topjui-col-sm12">
							<label class="topjui-form-label">计量类型</label>
							<div class="topjui-input-block">
								<input data-toggle="topjui-combobox"
									data-options="
valueField: 'text',
textField: 'text',
data: [{
    text: '计时型'
},{
    text: '计数型'
}]"
									name="measurementType" id="measurementType">
							</div>
						</div>
					</div>
					<div class="topjui-row">
						<div class="topjui-col-sm12">
							<label class="topjui-form-label">计量目标</label>
							<div class="topjui-input-block">
								<input type="number" name="measurementObjective"
									data-toggle="topjui-textbox" data-options="required:false"
									id="measurementObjective">
							</div>
						</div>
					</div>
					<div class="topjui-row">
						<div class="topjui-col-sm12">
							<label class="topjui-form-label">计量累计</label>
							<div class="topjui-input-block">
								<input type="number" name="cumulation"
									data-toggle="topjui-textbox" data-options="required:false"
									id="cumulation">
							</div>
						</div>
					</div>
					<div class="topjui-row">
						<div class="topjui-col-sm12">
							<label class="topjui-form-label">计量差异</label>
							<div class="topjui-input-block">
								<input type="number" name="measurementDifference"
									data-toggle="topjui-textbox" data-options="required:false"
									id="measurementDifference">
							</div>
						</div>
					</div>
					<div class="topjui-row">
						<div class="topjui-col-sm12">
							<label class="topjui-form-label">质保期</label>
							<div class="topjui-input-block">
								<input type="number" name="warrantyPeriod"
									data-toggle="topjui-textbox" data-options="required:false"
									id="warrantyPeriod">
							</div>
						</div>
					</div>
					<div class="topjui-row">
						<div class="topjui-col-sm12">
							<label class="topjui-form-label">上传图片</label>
							<div class="topjui-input-block">
								<input name="picName" data-toggle="topjui-uploadbox"
									data-options="editable:false,
   buttonText:'上传图片',
   accept:'images',
   uploadUrl:'equipment/upload.do'"
									type="text" id="picName">
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
</div>