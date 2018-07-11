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
						<label class="topjui-form-label">生产单元代码</label>
						<div class="topjui-input-block">
							<input type="text" name="code" data-toggle="topjui-textbox"
								data-options="required:true" id="productionUnitCode">
						</div>
					</div>
				</div>
				<div class="topjui-row">
					<div class="topjui-col-sm12">
						<label class="topjui-form-label">生产单元名称</label>
						<div class="topjui-input-block">
							<input type="text" name="name" data-toggle="topjui-textbox"
								data-options="required:true" id="productionUnitName">
						</div>
					</div>
				</div>
				<div class="topjui-row">
					<div class="topjui-col-sm8">
						<label class="topjui-form-label">目标产量</label>
						<div class="topjui-input-block">
						<input type="number" name="goalOutput" data-options="required:true"
								data-toggle="topjui-textbox" id="goalOutput">
						</div>
					</div>
				</div>
				
				<div class="topjui-row">
					<div class="topjui-col-sm12">
						<label class="topjui-form-label">描述</label>
						<div class="topjui-input-block">
							<input type="text" name="description"
								data-toggle="topjui-textarea" id="productionUnitNote">
						</div>
					</div>
				</div>
			</div>
		</div>
		
		</form>
	</div>
</div>