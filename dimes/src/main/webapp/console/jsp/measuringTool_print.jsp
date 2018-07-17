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
			 $.get("measuringTool/printQr.do",{ids:ids},function(data){
				 var $center = $("#center");
				 var i = 0;
				while(i<data.length){
					var $row = $("<div class='topjui-row'>");
					var data1 = data[i++];
					if(data1!=undefined){
						var $col1 = $('<div class="topjui-col-sm4 topjui-col-xs4 col">');
						//列里的div
						var $innerDiv1 = $("<div class='innerDiv'>");
						$col1.append($innerDiv1);
						
						var $innerTop = $("<div class='innerTop'>");
						var $innerBottom = $("<div class='innerBottom'>");
						
						$innerDiv1.append($innerTop);
						$innerDiv1.append($innerBottom);
						
						$innerTop.append("<img src='<%=basePath%>/" + data1.qrPath+"' />");
						
						
						var code = content('装备代码',data1.code);
						var name = content('装备名称',data1.name);
						var unitType = content('规格型号',data1.unitType);
						var manufacturer = content('生产厂家',data1.manufacturer);
						$innerBottom.append(code);
						$innerBottom.append(name);
						$innerBottom.append(unitType);
						$innerBottom.append(manufacturer);
						
						$row.append($col1);
					}
					
					var data2 = data[i++];
					if(data2!=undefined){
						var $col2 = $('<div class="topjui-col-sm4 topjui-col-xs4 col">');
						//列里的div
						var $innerDiv2 = $("<div class='innerDiv'>");
						$col2.append($innerDiv1);
						
						$innerTop = $("<div class='innerTop'>");
						$innerBottom = $("<div class='innerBottom'>");
						
						$innerDiv2.append($innerTop);
						$innerDiv2.append($innerBottom);
						
						$innerTop.append("<img src='<%=basePath%>/" + data2.qrPath+"' />");
						
						
						
						var code = content('装备代码',data2.code);
						var name = content('装备名称',data2.name);
						var unitType = content('规格型号',data2.unitType);
						var manufacturer = content('生产厂家',data2.manufacturer);
						$innerBottom.append(code);
						$innerBottom.append(name);
						$innerBottom.append(unitType);
						$innerBottom.append(manufacturer);
						
						$row.append($col2);
					}
						
					var data3 = data[i++];
					if(data3!=undefined){
						var $col3 = $('<div class="topjui-col-sm4 topjui-col-xs4 col">');
						//列里的div
						var $innerDiv3 = $("<div class='innerDiv'>");
						$col3.append($innerDiv1);
						
						$innerTop = $("<div class='innerTop'>");
						$innerBottom = $("<div class='innerBottom'>");
						
						$innerDiv3.append($innerTop);
						$innerDiv3.append($innerBottom);
						
						$innerTop.append("<img src='<%=basePath%>/"
											+ data3.qrPath + "' />");

						var code = content('装备代码',data3.code);
						var name = content('装备名称',data3.name);
						var unitType = content('规格型号',data3.unitType);
						var manufacturer = content('生产厂家',data3.manufacturer);
						$innerBottom.append(code);
						$innerBottom.append(name);
						$innerBottom.append(unitType);
						$innerBottom.append(manufacturer);

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
	.data {
		border-bottom-style: solid;
		width: 130pt;
		margin-left: 70pt;
	}
	.label {
		width: 60pt;
	}
	img {
		height: 100pt;
		width: 100pt;
		vertical-align: middle;
	}
	.innerTop {
		height: 50%;
		line-height:80pt;
		text-align: center;
	}
}

@media screen {
	img {
		height: 100pt;
		width: 100pt;
		vertical-align: middle;
	}
	.label {
		width: 60pt;
	}
	.data {
		border-bottom-style: solid;
		margin-left: 70pt;
	}
	.innerTop {
		height: 50%;
	}
}

.innerBottom {
	height: 50%;
}


.innerDiv {
	height: 300pt;
	width: 100%;
	border: solid 1px gray;
	padding:10pt;
}

.col {
	padding: 10pt;
	text-align: center;
}
</style>
<div data-toggle="topjui-layout" data-options="fit:true">
	<div data-options="region:'north',title:'',split:true"
		style="height: 50pt;">
		<div>
			<input type='button' value='打印' onclick='javascript:print()' />
		</div>
	</div>
	<div
		data-options="region:'center',title:'',fit:true,border:false,bodyCls:'border_right_bottom'">
		<div class=".container" id="center"></div>
	</div>
</div>