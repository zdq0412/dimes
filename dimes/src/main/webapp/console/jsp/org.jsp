<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../common/jsp/head.jsp"%>
<script>
	$(function() {
		$.get("department/queryTopDepartments.do", function(data) {
			layui.use('tree', function() {
				layui.tree({
					elem : '#departmentUl', //传入元素选择器
					nodes : data,
					click : function(node) {
						queryDepts(node.id);
					}
				});
			});

		});
		queryDepts(-1);
	});

	function queryDepts(pid) {
		$('#departmentTable').iDatagrid({
			url : 'department/queryDepartmentsByParentId.do?pid=' + pid,
			idField:'id',
			fitColumns:true,
			pagination:true,
			singleSelect:true,
			pageSize: 10, 
			columns : [ [ {
				field : 'id',
				title : 'id',
				width : 100
			}, {
				field : 'code',
				title : '部门代码',
				width : 100
			}, {
				field : 'name',
				title : '部门名称',
				width : 100,
				align : 'right'
			}, {
				field : 'pcode',
				title : '父部门代码',
				width : 100,
				align : 'right',
				formatter : function(value, row, index) {
					return row.parent.code;
				}
			}, {
				field : 'pname',
				title : '父部门名称',
				width : 100,
				align : 'right',
				formatter : function(value, row, index) {
					return row.parent.name;
				}
			}, {
				field : 'note',
				title : '备注',
				width : 100,
				align : 'center'
			}, {
				field : 'disabled',
				title : '停用',
				width : 100,
				align : 'center',
				formatter : function(value, row, index) {
					if (value) {
						return 'Y';
					} else {
						return 'N';
					}
				}
			} ] ]
		});
	}
</script>
</head>
<body>
	<div data-toggle="topjui-layout" data-options="fit:true">
		<div
			data-options="region:'west',title:'',split:true,border:false,width:'15%',iconCls:'fa fa-sitemap',headerCls:'border_right',bodyCls:'border_right'">
			<ul id="departmentUl">
			</ul>
		</div>
		<div
			data-options="region:'center',iconCls:'icon-reload',title:'',split:true,border:true,bodyCls:'border_top_none'">
			<div data-toggle="topjui-layout" data-options="fit:true">
				<div
					data-options="region:'center',title:'',fit:false,split:true,border:false,bodyCls:'border_bottom'"
					style="height: 60%">
					<table id="departmentTable"></table>
				</div>
				<div data-options="region:'south',fit:false,split:true,border:false"
					style="height: 40%"></div>
			</div>
		</div>
	</div>
</body>
</html>