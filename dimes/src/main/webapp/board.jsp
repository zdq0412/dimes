<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../common/jsp/head.jsp"%>
<style type="text/css" href="front/css/style.css">
</style>
<script type="text/javascript" src="front/js/main.js">
	
</script>
<script type="text/javascript"></script>
</head>
<body>
	<div
		style="padding: 10px; height: 1720px; width: 1000px; margin: 0 auto;">
		<div style="height: 150px; width: 100%; margin-bottom: 5px;">
			<img alt="logo" id="logo" style="height: 100%; width: 100%;">
		</div>
		<!-- OEE Graph -->
		<div class="chartDiv directLeft" id="oeeGraph">
			<div id="oeeGraphHeader">
				<img id="oeeTitle" style="height: 100%; width: 100%;">
			</div>
			<div id="oeeGraphBody" class="chartBodyDiv">
				<div style="width: 50%; margin: auto auto;">
					<input id="cc1" data-toggle="topjui-combobox"
						data-options="
			        valueField: 'id',
			        textField: 'name',
			        url: 'productionUnit/queryAllProductionUnits.do',
			        onSelect: function(rec){
			        	requestOee(rec.id);
			        }">
				</div>
				<div id="oeeGraphContent" style="width: 1800px; height: 800px;"></div>
			</div>
		</div>
		<div class="chartDiv directRight" id="outputMonitor">
			<div id="outputMonitorHeader">
				<img id="outputTitle" style="height: 100%; width: 100%;">
			</div>
			<div id="outputMonitorBody" class="chartBodyDiv"></div>
		</div>
		<div class="chartDiv directLeft" id="lowQuality">
			<div id="lowQualityHeader">
				<img id="lowQualityTitle" style="height: 100%; width: 100%;">
			</div>
			<div id="lowQualityBody" class="chartBodyDiv"></div>
		</div>
		<div class="chartDiv directRight" id="operatorSkill">
			<div id="operatorSkillHeader">
				<img id="safetyCrossTitle" style="height: 100%; width: 100%;">
			</div>
			<div id="safetyCrossBody" class="chartBodyDiv">
				<!-- <table style="widows: 100%;heigth:100%;border-spacing:0;"
				 id="employeeTb">
					<thead></thead>
					<tbody></tbody>
				</table> -->

			</div>
		</div>
		<div class="chartDiv directLeft" id="safetyCross">
			<div id="safetyCrossHeader">
				<img id="empTitle" style="height: 100%; width: 100%;">
			</div>
			<div id="operatorSkillBody" class="chartBodyDiv">
				<img id="empBody" style="height: 95%; width: 100%;">
			</div>
		</div>
		<div class="chartDiv directRight" id="errorProof">
			<div id="errorProofHeader">
				<img id="errorProofTitle" style="height: 100%; width: 100%;">
			</div>
			<div id="errorProofBody" class="chartBodyDiv">
				<img id="errorProofPic" style="height: 95%; width: 100%;">
			</div>
		</div>
	</div>
</body>
</html>
