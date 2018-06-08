<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script>
	$(function(){
		
	});
</script>
<div data-toggle="topjui-layout" data-options="fit:true"">
	<div
		data-options="region:'center',title:'',fit:true,border:false,bodyCls:'border_right_bottom'">
		<div title="部门信息" data-options="iconCls:'fa fa-th'">
			<div class="topjui-fluid">
				<div class="topjui-row">
					<div class="topjui-col-sm12">
						<label class="topjui-form-label">部门代码</label>
						<div class="topjui-input-block">
							<input type="text" name="code" data-toggle="topjui-textbox"
								data-options="required:true">
						</div>
					</div>
				</div>
				<div class="topjui-row">
					<div class="topjui-col-sm12">
						<label class="topjui-form-label">部门名称</label>
						<div class="topjui-input-block">
							<input type="text" name="name" data-toggle="topjui-textbox"
								data-options="required:true">
						</div>
					</div>
				</div>
				<div class="topjui-row">
					<div class="topjui-col-sm8">
						<label class="topjui-form-label">父部门</label>
						<div class="topjui-input-block">
							<input type="text" name="degree" data-toggle="topjui-combobox"
								data-options="required:false,
                       valueField:'id',
                       textField:'name',
                       url:'department/queryAllDepartments.do'">
						</div>
					</div>
				</div>
				<div class="topjui-row">
					<div class="topjui-col-sm12">
						<label class="topjui-form-label">描述</label>
						<div class="topjui-input-block">
							<input type="text" name="description"
								data-toggle="topjui-textarea">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>