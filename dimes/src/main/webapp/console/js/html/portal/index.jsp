<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>" />
<meta charset="utf-8" />
<!-- 浏览器标签图片 -->
<link rel="shortcut icon" href="console/js/topjui/images/favicon.ico" />
<!-- TopJUI框架样式 -->
<link type="text/css" href="console/js/topjui/css/topjui.core.min.css"
	rel="stylesheet">
<link type="text/css"
	href="console/js/topjui/themes/default/topjui.black.css"
	rel="stylesheet" id="dynamicTheme" />
<!-- FontAwesome字体图标 -->
<link type="text/css"
	href="console/js/static/plugins/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" />
<link type="text/css"
	href="console/js/static/plugins/layui/css/layui.css" rel="stylesheet" />
	    <link type="text/css" href="console/js/static/public/css/font.css" rel="stylesheet"/>
    <link type="text/css" href="console/js/static/public/css/main.css" rel="stylesheet"/>
<!-- jQuery相关引用 -->
<script type="text/javascript"
	src="console/js/static/plugins/jquery/jquery.min.js"></script>

<script type="text/javascript" src="common/js/echarts.js"></script>
<script type="text/javascript"
	src="console/js/static/plugins/jquery/jquery.cookie.js"></script>
<!-- TopJUI框架配置 -->
<script type="text/javascript"
	src="console/js/static/public/js/topjui.config.js"></script>
<!-- TopJUI框架核心 -->
<script type="text/javascript"
	src="console/js/topjui/js/topjui.core.min.js"></script>
<!-- TopJUI中文支持 -->
<script type="text/javascript"
	src="console/js/topjui/js/locale/topjui.lang.zh_CN.js"></script>
<!-- layui框架js -->
<script type="text/javascript"
	src="console/js/static/plugins/layui/layui.js" charset="utf-8"></script>
<!-- 首页js -->
<script type="text/javascript"
	src="console/js/static/public/js/topjui.index.js" charset="utf-8"></script>
<script>
//点击产线，查询该产线的oee
function requestLostTime(productionUnitId){
	$.get("lostTimeRecord/queryLostTimeReason4ProductionUnit.do",{productionUnitId:productionUnitId},function(result){
		lostTimeReasonGraph(result);
	});
}
//损时分析
function lostTimeReasonGraph(result){
	var series = new Array();
	for(var i = 0;i<result.classes.length;i++){
		var jsonObj =  {
	            name:result.classes[i].name,
	            type:'bar',
	            stack:'班次',
	            data:result.lostTimeList[i]
	        }
		
		series[i] = jsonObj;
	}
	
	series.push({
		name : '目标',
		type : 'line',
		itemStyle : { 
			normal : {
				color:'#008000',
			}
		},
		lineStyle:{
			width:5
		},
		data : result.goalLostTimeList
	});
	
	option = {
		    tooltip : {
		        trigger: 'axis',
		        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
		            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
		        }
		    },
		    legend: {
		        data:result.classes
		    },
		    grid: {
		        left: '3%',
		        right: '4%',
		        bottom: '3%',
		        containLabel: true
		    },
		    xAxis : [
		        {
		            type : 'category',
		            data : result.days
		        }
		    ],
		    yAxis : [
		        {
		            type : 'value',
		            max:24,
		            interval:2
		        }
		    ],
		    series : series
		};
	
	// 基于准备好的dom，初始化echarts实例
	var myChart = echarts.init(document.getElementById('lostTimeReasonGraphBody'));
	// 使用刚指定的配置项和数据显示图表。
	myChart.setOption(option); 
}
	//点击产线，查询该产线的oee
	function requestOee(productionUnitId) {
		$.get("oee/queryOee4ProductionUnit.do", {
			productionUnitId : productionUnitId
		}, function(result) {
			oeeGraph(result);
		});
	}
	//显示oee信息
	function oeeGraph(result) {
		var series = new Array();
		var colorArray = new Array();
		colorArray.push('#8FBC8F');
		colorArray.push('#F00');
		colorArray.push('#00F');
		for (var i = 0; i < result.classes.length; i++) {
			var jsonObj = {
				name : result.classes[i].name,
				type : 'bar',
				itemStyle : {
					normal : {
						color : colorArray[i],
						label : {
							show : false,
							position : 'top',
							formatter : '{c}'
						}
					}
				},
				//data:[50, 75, 100, 150, 200, 250, 150, 100, 95, 160, 50, 45]
				data : result.oeeList[i]
			}

			series[i] = jsonObj;
		}
		series.push({
			name : '平均值',
			type : 'line',
			itemStyle : {
				normal : {
					color : '#888'
				}
			},
			data : result.avgs
		});

		series.push({
			name : '目标',
			type : 'line',
			itemStyle : {
				normal : {
					color : '#008000',
				}
			},
			data : result.goalOeeList
		});
		var jsonStr = JSON.stringify(series);
		var option = {
			title : {
				show : true,
				text : 'oee'
			},
			tooltip : {
				trigger : 'axis'
			},
			legend : {
				data : result.classes
			},
			xAxis : [ {
				type : 'category',
				data : result.days
			} ],
			yAxis : [ {
				type : 'value',
				name : 'oee值/百分比',
				min : 0,
				max : 100,
				interval : 10,
				axisLabel : {
					formatter : '{value}% '
				}
			} ],
			series : series
		};

		// 基于准备好的dom，初始化echarts实例
		var myChart = echarts.init(document.getElementById('oeeGraphBody'));
		// 使用刚指定的配置项和数据显示图表。
		myChart.setOption(option);
	}

	//点击产线，查询该产线的产量
	function requestOutput(productionUnitId) {
		$.get("output/queryOutput4ProductionUnit.do", {
			productionUnitId : productionUnitId
		}, function(result) {
			output(result);
		});
	}
	//产线级
	function output(data) {
		var arr = new Array();
		for (var i = 0; i < data.classNameList.length; i++) {
			var className = data.classNameList[i];
			var ser = {
				name : className,
				type : 'bar',
				stack : data.classNameList[0],
				data : data.outputMap[className]
			};
			arr.push(ser);
		}

		var goalOutput = {
			data : data.goalOutput,
			type : 'line'
		};

		arr.push(goalOutput);

		var myChart = echarts.init(document.getElementById("outputGraphBody"));
		option = {
			tooltip : {
				trigger : 'axis',
				axisPointer : { // 坐标轴指示器，坐标轴触发有效
					type : 'shadow' // 默认为直线，可选为：'line' | 'shadow'
				}
			},
			legend : {
				data : data.classNameList
			},
			grid : {
				left : '3%',
				right : '4%',
				bottom : '3%',
				containLabel : true
			},
			xAxis : [ {
				type : 'category',
				data : data.days

			} ],
			yAxis : [ {
				type : 'value'
			} ],
			series : arr
		};
		myChart.setOption(option);
	}
</script>
<style>
.layui-badge {
	height: initial;
	line-height: 30px;
	text-align: left;
	font-size: 14px;
}
</style>
</head>

<body>
	<div class="layui-container-fluid">
		<div class="panel_box row">
			<div class="panel col">
				<a href="javascript:;"
					data-url="console/js/html/page/message/message.html">
					<div class="panel_icon">
						<i class="layui-icon" data-icon=""></i>
					</div>
					<div class="panel_word newMessage">
						<span>5</span> <cite>新消息</cite>
					</div>
				</a>
			</div>
			<div class="panel col">
				<a href="javascript:;" data-url="page/user/allUsers.html">
					<div class="panel_icon" style="background-color: #FF5722;">
						<i class="iconfont icon-dongtaifensishu"
							data-icon="icon-dongtaifensishu"></i>
					</div>
					<div class="panel_word userAll">
						<span>3</span> <cite>新增人数</cite>
					</div>
				</a>
			</div>
			<div class="panel col">
				<a href="javascript:;" data-url="page/user/allUsers.html">
					<div class="panel_icon" style="background-color: #009688;">
						<i class="layui-icon" data-icon=""></i>
					</div>
					<div class="panel_word userAll">
						<span>3</span> <cite>用户总数</cite>
					</div>
				</a>
			</div>
			<div class="panel col">
				<a href="javascript:;" data-url="page/img/images.html">
					<div class="panel_icon" style="background-color: #5FB878;">
						<i class="layui-icon" data-icon=""></i>
					</div>
					<div class="panel_word imgAll">
						<span>31</span> <cite>图片总数</cite>
					</div>
				</a>
			</div>
			<div class="panel col">
				<a href="javascript:;" data-url="page/news/newsList.html">
					<div class="panel_icon" style="background-color: #F7B824;">
						<i class="iconfont icon-wenben" data-icon="icon-wenben"></i>
					</div>
					<div class="panel_word waitNews">
						<span>13</span> <cite>待审核文章</cite>
					</div>
				</a>
			</div>
			<div class="panel col max_panel">
				<a>
					<div class="panel_icon" style="background-color: #2F4056;">
						<i class="iconfont icon-text" data-icon="icon-text"></i>
					</div>
					<div class="panel_word allNews">
						<span><script>document.write(50);</script></span> <em>今日新增单据</em> <cite>文章列表</cite>
					</div>
				</a>
			</div>
		</div>

		<div class="layui-row layui-col-space10">
			<div class="layui-col-md4">
				<blockquote class="layui-elem-quote title">产线级产量</blockquote>
				<table class="layui-table" lay-skin="line">
					<tbody>
						<tr>
							<td>
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
        	requestOutput(rec.id);
        }">
								</div>
								<div id="outputGraphBody" style="width: 450px; height: 400px;"></div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="layui-col-md4">
				<blockquote class="layui-elem-quote title">产线级OEE</blockquote>
				<table class="layui-table">
					<tbody>
						<tr>
							<td align="center">
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
        	requestOee(rec.id);
        }">
								</div>
								<div id="oeeGraphBody" style="width: 450px; height: 400px;"></div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="layui-col-md4">
				<blockquote class="layui-elem-quote title">损时分析</blockquote>
				<table class="layui-table" lay-skin="line">
					<tbody>
						<tr>
							<td>
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
	<div id="lostTimeReasonGraphBody" style="width: 450px; height: 400px;"></div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>

		<div class="layui-row layui-col-space10">
			<div class="layui-col-md4">
				<blockquote class="layui-elem-quote title">
					最新文章<i class="iconfont icon-new1"></i>
				</blockquote>
				<table class="layui-table" lay-skin="line">
					<colgroup>
						<col>
						<col width="110">
					</colgroup>
					<tbody class="hot_news">
						<tr>
							<td align="left"><a
								href="javascript:window.parent.addParentTab({href:'./html/article/detail.html',title:'强化党内监督是全面从严治党重要保障'})">强化党内监督是全面从严治党重要保障</a>
							</td>
						</tr>
						<tr>
							<td align="left"><a
								href="javascript:window.parent.addParentTab({href:'./html/article/detail.html',title:'李克强：地方和部门主要负责同志要带头...'})">李克强：地方和部门主要负责同志要带头...</a>
							</td>
						</tr>
						<tr>
							<td align="left"><a
								href="javascript:window.parent.addParentTab({href:'./html/article/detail.html',title:'李克强：政务公开是常态不公开是例外'})">李克强：政务公开是常态不公开是例外</a>
							</td>
						</tr>
						<tr>
							<td align="left"><a
								href="javascript:window.parent.addParentTab({href:'./html/article/detail.html',title:'军委机关将总体实行“部—局—处”三级体制'})">军委机关将总体实行“部—局—处”三级体制</a>
							</td>
						</tr>
						<tr>
							<td align="left"><a
								href="javascript:window.parent.addParentTab({href:'./html/article/detail.html',title:'蔡奇就任北京代市长 王安顺因工作调动请辞'})">蔡奇就任北京代市长王安顺因工作调动请辞</a>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="layui-col-md4">
				<blockquote class="layui-elem-quote title">
					热门文章<i class="iconfont icon-new1"></i>
				</blockquote>
				<table class="layui-table" lay-skin="line">
					<colgroup>
						<col>
						<col width="110">
					</colgroup>
					<tbody class="hot_news">
						<tr>
							<td align="left"><a
								href="javascript:window.parent.addParentTab({href:'./html/article/detail.html',title:'强化党内监督是全面从严治党重要保障'})">强化党内监督是全面从严治党重要保障</a>
							</td>
						</tr>
						<tr>
							<td align="left"><a
								href="javascript:window.parent.addParentTab({href:'./html/article/detail.html',title:'李克强：地方和部门主要负责同志要带头...'})">李克强：地方和部门主要负责同志要带头...</a>
							</td>
						</tr>
						<tr>
							<td align="left"><a
								href="javascript:window.parent.addParentTab({href:'./html/article/detail.html',title:'李克强：政务公开是常态不公开是例外'})">李克强：政务公开是常态不公开是例外</a>
							</td>
						</tr>
						<tr>
							<td align="left"><a
								href="javascript:window.parent.addParentTab({href:'./html/article/detail.html',title:'军委机关将总体实行“部—局—处”三级体制'})">军委机关将总体实行“部—局—处”三级体制</a>
							</td>
						</tr>
						<tr>
							<td align="left"><a
								href="javascript:window.parent.addParentTab({href:'./html/article/detail.html',title:'蔡奇就任北京代市长 王安顺因工作调动请辞'})">蔡奇就任北京代市长王安顺因工作调动请辞</a>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="layui-col-md4">
				<blockquote class="layui-elem-quote title">通知公告</blockquote>
				<table class="layui-table" lay-skin="line">
					<colgroup>
						<col>
						<col width="110">
					</colgroup>
					<tbody class="hot_news">
						<tr>
							<td align="left"><a
								href="javascript:window.parent.addParentTab({href:'./html/article/detail.html',title:'强化党内监督是全面从严治党重要保障'})">强化党内监督是全面从严治党重要保障</a>
							</td>
						</tr>
						<tr>
							<td align="left"><a
								href="javascript:window.parent.addParentTab({href:'./html/article/detail.html',title:'李克强：地方和部门主要负责同志要带头...'})">李克强：地方和部门主要负责同志要带头...</a>
							</td>
						</tr>
						<tr>
							<td align="left"><a
								href="javascript:window.parent.addParentTab({href:'./html/article/detail.html',title:'李克强：政务公开是常态不公开是例外'})">李克强：政务公开是常态不公开是例外</a>
							</td>
						</tr>
						<tr>
							<td align="left"><a
								href="javascript:window.parent.addParentTab({href:'./html/article/detail.html',title:'军委机关将总体实行“部—局—处”三级体制'})">军委机关将总体实行“部—局—处”三级体制</a>
							</td>
						</tr>
						<tr>
							<td align="left"><a
								href="javascript:window.parent.addParentTab({href:'./html/article/detail.html',title:'蔡奇就任北京代市长 王安顺因工作调动请辞'})">蔡奇就任北京代市长王安顺因工作调动请辞</a>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>

	</div>
	<script type="text/javascript">
    // 百度统计代码开始
    var _hmt = _hmt || [];
    (function () {
        var hm = document.createElement("script");
        hm.src = "https://hm.baidu.com/hm.js?71559c3bdac3e45bebab67a5a841c70e";
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(hm, s);
    })();
    // 百度统计代码结束
</script>
</body>
</html>