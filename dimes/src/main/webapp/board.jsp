<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="common/jsp/head.jsp"%>
<script type="text/javascript" src="front/js/main.js">
	
</script>
<script type="text/javascript"></script>
<style>
body {
	padding: 0px;
	margin: 0px;
}

.chartDiv {
	height: 370px;
	width: 490px;
	margin-bottom: 5px;
	border: 1px solid #0459A2;
}

.directLeft {
	float: left;
}

.directRight {
	float: right;
}

.chartDiv>:first-child {
	height: 50px;
	width: 100%;
}
/*高度可通过父容器高度计算获取*/
.chartBodyDiv {
	height: 340px;
	width: 100%;
}
</style>
</head>
<body>
	<!-- 最外层容器 -->
	<div
		style="padding: 10px; height: 1720px; width: 1000px; margin: 0 auto;">
		<!-- 看板标题 -->
		<div style="height: 150px; width: 100%; margin-bottom: 5px;">
			<img alt="logo" id="logo" style="height: 100%; width: 100%;"
				src="front/imgs/logo.JPG">
		</div>
		<!-- OEE Graph -->
		<div class="chartDiv directLeft" id="oeeGraph">
			<div id="oeeGraphHeader">
				<img id="oeeTitle" style="height: 100%; width: 100%;"
					src="front/imgs/oeeTitle.PNG">
			</div>
			<div id="oeeGraph" class="chartBodyDiv">
				<div style="width: 50%; margin: auto auto;">
					<input data-toggle="topjui-combobox" id="oee"
						data-options="
        valueField: 'id',
        textField: 'name',
        url: 'productionUnit/queryAllProductionUnits.do',
        onLoadSuccess: function () { //加载完成后,val[0]写死设置选中第一项
                var val = $(this).combobox('getData');
                for (var item in val[0]) {
                    if (item == 'id') {
                        $(this).combobox('select', val[0][item]);
                    }
                }
            },
        onSelect: function(rec){
        	requestOee(rec.id);
        }">
				</div>
				<div id="oeeGraphBody" style="height: 310px; width: 100%;"></div>
			</div>
		</div>
		<!-- Output Monitor 产量 -->
		<div class="chartDiv directRight" id="outputMonitor">
			<div id="outputMonitorHeader">
				<img id="outputTitle" style="height: 100%; width: 100%;"
					src="front/imgs/outputTitle.PNG">
			</div>
			<div id="outputMonitorBody" class="chartBodyDiv">
				<div style="width: 50%; margin: auto auto;">
					<input data-toggle="topjui-combobox"
						data-options="
        valueField: 'id',
        textField: 'name',
        url: 'productionUnit/queryAllProductionUnits.do',
        onLoadSuccess: function () { //加载完成后,val[0]写死设置选中第一项
                var val = $(this).combobox('getData');
                for (var item in val[0]) {
                    if (item == 'id') {
                        $(this).combobox('select', val[0][item]);
                    }
                }
            },
        onSelect: function(rec){
        	requestOutput(rec.id);
        }">
				</div>
				<div id="outputGraphBody" style="width: 100%; height: 280px;"></div>
			</div>
		</div>
		<!-- 不合格产品 -->
		<div class="chartDiv directLeft" id="lowQuality">
			<div id="lowQualityHeader">
				<img id="lowQualityTitle" style="height: 100%; width: 100%;"
					src="front/imgs/lowQualityTitle.PNG">
			</div>
			<div id="lowQualityBody" class="chartBodyDiv">
				<div style="width: 50%; margin: auto auto;">
					<input id="cc1" data-toggle="topjui-combobox"
						data-options="
        valueField: 'id',
        textField: 'name',
        url: 'productionUnit/queryAllProductionUnits.do',
        onLoadSuccess: function () { //加载完成后,val[0]写死设置选中第一项
                var val = $(this).combobox('getData');
                for (var item in val[0]) {
                    if (item == 'id') {
                        $(this).combobox('select', val[0][item]);
                    }
                }
            },
        onSelect: function(rec){
        	requestNGRecord(rec.id);
        }">
				</div>
				<div id="ngRecordGraphBody" style="width: 100%; height: 300px;"></div>
			</div>
		</div>
		<!-- 人员技能 -->
		<div class="chartDiv directRight" id="operatorSkill">
			<div id="operatorSkillHeader">
				<img id="empTitle" style="height: 100%; width: 100%;"
					src="front/imgs/empTitle.PNG">
			</div>
			<div id="operatorSkillBody" class="chartBodyDiv">
				<div style="width: 50%; margin: auto auto;">
					<input id="cc1" data-toggle="topjui-combobox"
						data-options="
        valueField: 'id',
        textField: 'name',
        url: 'productionUnit/queryAllProductionUnits.do',
        onLoadSuccess: function () { //加载完成后,val[0]写死设置选中第一项
                var val = $(this).combobox('getData');
                for (var item in val[0]) {
                    if (item == 'id') {
                        $(this).combobox('select', val[0][item]);
                    }
                }
            },
        onSelect: function(rec){
        	requestEmployeeSkill(rec.id);
        }">
				</div>
				<div id="employeeSkillGraphBody" style="width: 100%; height: 310px;"></div>
			</div>
		</div>
		<!-- 安全 -->
		<div class="chartDiv directLeft" id="safetyCross">
			<div id="safetyCrossHeader">
				<img id="safetyCrossTitle" style="height: 100%; width: 100%;"
					src="front/imgs/safetyCross.PNG">
			</div>
			<div id="safetyCrossBody" class="chartBodyDiv">
				<div id="main" style="width: 100%; height: 300px;"></div>
				<div id="names"
					style="margin:0 auto;text-align: center; text-size: 20px;width:300px; height: 20px; line-height: 20px;"></div>
			</div>
		</div>
		<!-- 防错布局 -->
		<div class="chartDiv directRight" id="errorProof">
			<div id="errorProofHeader">
				<img id="errorProofTitle" style="height: 100%; width: 100%;"
					src="front/imgs/errorProofTitle.PNG">
			</div>
			<div id="errorProofBody" class="chartBodyDiv">
				<div style="width: 50%;margin:auto auto;">
		<input id="cc1" data-toggle="topjui-combobox"
			data-options="
        valueField: 'id',
        textField: 'name',
        url: 'productionUnit/queryAllProductionUnits.do',
        onLoadSuccess: function () { //加载完成后,val[0]写死设置选中第一项
                var val = $(this).combobox('getData');
                for (var item in val[0]) {
                    if (item == 'id') {
                        $(this).combobox('select', val[0][item]);
                    }
                }
            },
        onSelect: function(rec){
        	requestLostTime(rec.id);
        }">
	</div>
	<div id="lostTimeReasonGraphBody" style="width: 100%; height: 300px;"></div>
			</div>
		</div>
	</div>
</body>
</html>
