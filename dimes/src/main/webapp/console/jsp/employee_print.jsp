<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../common/jsp/head.jsp"%>
<script type="text/javascript" src="common/js/jQuery.print.js">
</script>
<script type="text/javascript">
	function content( label, data){
		var row = '<div class="topjui-row">'+
		'<div class="topjui-col-sm12 topjui-col-xs12">'+
			'<label class="topjui-form-label label">' + label +':</label>' +
			'<div class="topjui-input-block data">' + data +
			'</div>'+
		'</div>'
		'</div>';
		return row;
	}
	
		$(function(){
			var ids = window.opener.document.getElementById("ids").value;
			 $.get("employee/printQr.do",{ids:ids},function(data){
				 var $center = $("#center");
				 var i = 0;
				while(i<data.length){
					var employee1 = data[i++];
					var $row = $("<div class='topjui-row'>");
					if(employee1!=undefined){
						var $col1 = $('<div class="topjui-col-sm4 topjui-col-xs4 col">');
						//列里的div
						var $innerDiv1 = $("<div class='innerDiv'>");
						$col1.append($innerDiv1);
						
						var $innerTop = $("<div class='innerTop'>");
						var $innerBottom = $("<div class='innerBottom'>");
						
						$innerDiv1.append($innerTop);
						$innerDiv1.append($innerBottom);
						
						var $innerBottomLeft = $("<div class='innerBottomLeft'>");
						var $innerBottomRight = $("<div class='innerBottomRight'>");
						
						$innerBottom.append($innerBottomLeft);
						$innerBottom.append($innerBottomRight);
						
						var code = content('工号',employee1.code);
						var name = content('姓名',employee1.name);
						var dept = content('部门',employee1.departmentName);
						$innerBottomLeft.append(code);
						$innerBottomLeft.append(name);
						$innerBottomLeft.append(dept);
						
						$innerBottomRight.append("<img src='<%=basePath%>/" + employee1.qrPath+"' />");
						
						$row.append($col1);
					}
					
					var employee2 = data[i++];
					if(employee2!=undefined){
						 var $col2 = $('<div class="topjui-col-sm4 topjui-col-xs4 col">');
						//列里的div
							var $innerDiv2 = $("<div class='innerDiv'>");
							$col2.append($innerDiv2);
							
							 $innerTop = $("<div class='innerTop'>");
							 $innerBottom = $("<div class='innerBottom'>");
							
							$innerDiv2.append($innerTop);
							$innerDiv2.append($innerBottom);
							
							 $innerBottomLeft = $("<div class='innerBottomLeft'>");
							 $innerBottomRight = $("<div class='innerBottomRight'>");
							
							$innerBottom.append($innerBottomLeft);
							$innerBottom.append($innerBottomRight);
							
							 code = content('工号',employee2.code);
							 name = content('姓名',employee2.name);
							 dept = content('部门',employee2.departmentName);
							$innerBottomLeft.append(code);
							$innerBottomLeft.append(name);
							$innerBottomLeft.append(dept);
							$innerBottomRight.append("<img src='<%=basePath%>/" + employee2.qrPath+"'/>");
							
							$row.append($col2);
					}
						
					var employee3 = data[i++];
					if(employee3!=undefined){
						var $col3 = $('<div class="topjui-col-sm4 topjui-col-xs4 col">');
						 
						var $innerDiv3 = $("<div class='innerDiv'>");
						$col3.append($innerDiv3);
						
						 $innerTop = $("<div class='innerTop'>");
						 $innerBottom = $("<div class='innerBottom'>");
						
						$innerDiv3.append($innerTop);
						$innerDiv3.append($innerBottom);
						
						 $innerBottomLeft = $("<div class='innerBottomLeft'>");
						 $innerBottomRight = $("<div class='innerBottomRight'>");
						
						$innerBottom.append($innerBottomLeft);
						$innerBottom.append($innerBottomRight);
						
						 code = content('工号',employee3.code);
						 name = content('姓名',employee3.name);
						 dept = content('部门',employee3.departmentName);
						$innerBottomLeft.append(code);
						$innerBottomLeft.append(name);
						$innerBottomLeft.append(dept);
						$innerBottomRight.append("<img src='<%=basePath%>/"
													+ employee3.qrPath
													+ "' />");
						$row.append($col3);
					}
							$center.append($row);
							}
						});
	});

	function print() {
		$("#center").print();
	}
</script>
<style>
@media print {
	.data{
		border-bottom-style:solid;width:100pt;margin-left:50pt;
	}
	.label {
		width: 40pt;
	}
	img{
		height:60pt;
		width:60pt;
		vertical-align:middle;
	}
	.innerBottomRight {
	height: 100%;
	float: right;
	width: 40%;
	display: table-cell;
	vertical-align: middle;
	text-align: center;
}
	
	
}
@media screen{
img{
	height:100pt;
	width:100pt;
	vertical-align:middle;
}
.label {
		width: 40pt;
	}
	.data{
		border-bottom-style:solid;margin-left:50pt;
	}
}
.innerBottomRight {
	height: 100%;
	float: right;
	width: 40%;
	display: table-cell;
	vertical-align: middle;
	text-align: center;
	line-height: 100pt;
}

.innerBottom {
	height: 50%;
}

.innerTop {
	height: 50%;
}

.innerDiv {
	height: 250pt;
	width: 100%;
	border: solid 1px gray;
}

.col {
	padding: 10pt;
	text-align: center;
}
.innerBottomLeft {
	height: 100%;
	float: left;
	width: 60%;
	margin-left: 0px;
	vertical-align: bottom;
}
</style>
<div data-toggle="topjui-layout" data-options="fit:true">
	<div data-options="region:'north',title:'',split:true"
		style="height: 50pt;">
		<div>
			<input type='button' value='打印' onclick='javascript:print()' />
			<!-- <input type='button' value='打印' onclick='javascript:window.print()' /> -->
		</div>
	</div>
	<div
		data-options="region:'center',title:'',fit:true,border:false,bodyCls:'border_right_bottom'">
		<div class=".container" id="center"></div>
	</div>
</div>