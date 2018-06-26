<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../../common/jsp/head.jsp" %>
     <script type="text/javascript" src="console/js/static/public/js/topjui.index.js" charset="utf-8"></script>
    <title>DIMES</title>
</head>
<body>
<div id="loading" class="loading-wrap">
    <div class="loading-content">
        <div class="loading-round"></div>
        <div class="loading-dot"></div>
    </div>
</div>
<div id="mm" class="submenubutton" style="width: 140px;">
    <div id="mm-tabclose" name="6" iconCls="fa fa-refresh">刷新</div>
    <div class="menu-sep"></div>
    <div id="Div1" name="1" iconCls="fa fa-close">关闭</div>
    <div id="mm-tabcloseother" name="3">关闭其他</div>
    <div id="mm-tabcloseall" name="2">关闭全部</div>
    <div class="menu-sep"></div>
    <div id="mm-tabcloseright" name="4">关闭右侧标签</div>
    <div id="mm-tabcloseleft" name="5">关闭左侧标签</div>
    <div class="menu-sep"></div>
    <div id="mm-newwindow" name="7">新窗口中打开</div>
</div>
<div data-toggle="topjui-layout" data-options="id:'index_layout',fit:true">
    <div id="north" class="banner" data-options="region:'north',border:false,split:false"
         style="height: 50px; padding:0;margin:0; overflow: hidden;">
        <table style="float:left;border-spacing:0px;">
            <tr>
                <td class="webname">
                    <span class="fa fa-envira" style="font-size:26px; padding-right:8px;"></span>TopJUI前端框架
                </td>
                <td class="collapseMenu" style="text-align: center;cursor: pointer;">
                    <span class="fa fa-chevron-circle-left" style="font-size: 18px;"></span>
                </td>
                <td>
                    <table id="topmenucontent" cellpadding="0" cellspacing="0">
                    <!-- <tr>
                        <td id="1325" title="TopJUI静态演示" class="topmenu selected systemName">
                            <a class="l-btn-text bannerMenu" href="javascript:void(0)">
                                <p>
                                    <lable class="fa fa-tree"></lable>
                                </p>
                                <p><span style="white-space:nowrap;">TopJUI静态演示</span></p>
                            </a>
                        </td>
                        <td id="60" title="TopJUI开发文档" class="topmenu systemName">
                            <a class="l-btn-text bannerMenu" href="javascript:void(0)">
                                <p>
                                    <lable class="fa fa-book"></lable>
                                </p>
                                <p><span style="white-space:nowrap;">TopJUI开发文档</span></p>
                            </a>
                        </td>
                        <td id="9000" title="TopJUI动态演示" class="topmenu systemName">
                            <a class="l-btn-text bannerMenu" href="http://demo.ewsd.cn/login" target="_blank">
                                <p>
                                    <lable class="fa fa-leaf"></lable>
                                </p>
                                <p><span style="white-space:nowrap;">TopJUI动态演示</span></p>
                            </a>
                        </td>
                        <td id="9001" title="TopJUI官方网站" class="topmenu systemName">
                            <a class="l-btn-text bannerMenu" href="https://www.topjui.com" target="_blank">
                                <p>
                                    <lable class="fa fa-home"></lable>
                                </p>
                                <p><span style="white-space:nowrap;">TopJUI官方网站</span></p>
                            </a>
                        </td>
                        <td id="9002" title="TopJUI交流社区" class="topmenu systemName">
                            <a class="l-btn-text bannerMenu" href="https://ask.topjui.com" target="_blank">
                                <p>
                                    <lable class="fa  fa-wechat"></lable>
                                </p>
                                <p><span style="white-space:nowrap;">TopJUI交流社区</span></p>
                            </a>
                        </td>
                        </tr> -->
                    </table>
                </td>
            </tr>
        </table>
        <span style="float: right; padding-right: 10px; height: 50px; line-height: 50px;">
            <a href="javascript:void(0)" data-toggle="topjui-menubutton"
               data-options="iconCls:'fa fa-user',hasDownArrow:false"
               style="color:#fff;">TopJUI前端框架</a>|
            <a href="javascript:void(0)" id="mb3" data-toggle="topjui-menubutton"
               data-options="menu:'#mm3',iconCls:'fa fa-cog',hasDownArrow:true" style="color:#fff;">设置</a>
            <div id="mm3" style="width:74px;">
                <div data-options="iconCls:'fa fa-info-circle'" onclick="javascript:modifyPwd(0)">个人信息</div>
                <div class="menu-sep"></div>
                <div data-options="iconCls:'fa fa-key'" onclick="javascript:modifyPwd(0)">修改密码</div>
            </div>|
            <a href="javascript:void(0)" id="mb2" data-toggle="topjui-menubutton"
               data-options="menu:'#mm2',iconCls:'fa fa-tree',hasDownArrow:true" style="color:#fff;">主题</a>|
            <div id="mm2" style="width:180px;">
                <div data-options="iconCls:'fa fa-tree blue'" onclick="changeTheme('blue')">默认主题</div>
                <div data-options="iconCls:'fa fa-tree'" onclick="changeTheme('black')">黑色主题</div>
                <div data-options="iconCls:'fa fa-tree'" onclick="changeTheme('blacklight')">黑色主题-亮</div>
                <div data-options="iconCls:'fa fa-tree red'" onclick="changeTheme('red')">红色主题</div>
                <div data-options="iconCls:'fa fa-tree red'" onclick="changeTheme('redlight')">红色主题-亮</div>
                <div data-options="iconCls:'fa fa-tree green'" onclick="changeTheme('green')">绿色主题</div>
                <div data-options="iconCls:'fa fa-tree green'" onclick="changeTheme('greenlight')">绿色主题-亮</div>
                <div data-options="iconCls:'fa fa-tree purple'" onclick="changeTheme('purple')">紫色主题</div>
                <div data-options="iconCls:'fa fa-tree purple'" onclick="changeTheme('purplelight')">紫色主题-亮</div>
                <div data-options="iconCls:'fa fa-tree blue'" onclick="changeTheme('blue')">蓝色主题</div>
                <div data-options="iconCls:'fa fa-tree blue'" onclick="changeTheme('bluelight')">蓝色主题-亮</div>
                <div data-options="iconCls:'fa fa-tree orange'" onclick="changeTheme('yellow')">橙色主题</div>
                <div data-options="iconCls:'fa fa-tree orange'" onclick="changeTheme('yellowlight')">橙色主题-亮</div>
            </div>
            <a href="javascript:void(0)" onclick="logout()" data-toggle="topjui-menubutton"
               data-options="iconCls:'fa fa-sign-out',hasDownArrow:false" style="color:#fff;">注销</a>
        </span>
    </div>

    <div id="west"
         data-options="region:'west',split:true,width:230,border:false,headerCls:'border_right',bodyCls:'border_right'"
         title="" iconCls="fa fa-dashboard">
        <div id="RightAccordion"></div>
        <!--<div id="menuTab" class="topjui-tabs" data-options="fit:true,border:false">
            <div title="导航菜单" data-options="iconCls:'fa fa-sitemap'" style="padding:0;">
                <div id="RightAccordion" class="topjui-accordion"></div>
            </div>
            <div title="常用链接" data-options="iconCls:'fa fa-star',closable:true">
                <ul id="channgyongLink"></ul>
            </div>
        </div>-->
    </div>

    <div id="center" data-options="region:'center',border:false" style="overflow:hidden;">
        <div id="index_tabs" style="width:100%;height:100%">
            <div title="系统首页" iconCls="fa fa-home" data-options="border:true,iframe:true,
            content:'<iframe src=\'console/js/html/portal/index.html\' scrolling=\'auto\' frameborder=\'0\' style=\'width:100%;height:100%;\'></iframe>'"></div>
        </div>
    </div>

    <div data-options="region:'south',border:true"
         style="text-align:center;height:30px;line-height:30px;border-bottom:0;overflow:hidden;">
        <span style="float:left;padding-left:5px;width:30%;text-align: left;">当前用户：TopJUI前端框架</span>
        <span style="padding-right:5px;width:40%">
            版权所有 © 2014-2017
            <a href="https://www.ewsd.cn" target="_blank">深圳易网时代信息技术有限公司</a>
            <a href="http://www.miitbeian.gov.cn" target="_blank">粤ICP备16028103号-1</a>
        </span>
        <span style="float:right;padding-right:5px;width:30%;text-align: right;">版本：<script>document.write(topJUI.version)</script></span>
    </div>
</div>

<!--[if lte IE 8]>
<div id="ie6-warning">
    <p>您正在使用低版本浏览器，在本页面可能会导致部分功能无法使用，建议您升级到
        <a href="http://www.microsoft.com/china/windows/internet-explorer/" target="_blank">IE9或以上版本的浏览器</a>
        或使用<a href="http://se.360.cn/" target="_blank">360安全浏览器</a>的极速模式浏览
    </p>
</div>
<![endif]-->

</body>
</html>