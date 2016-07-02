<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>客户清单-boss-饮片B2B</title>
    <meta name="renderer" content="webkit" />
    <link rel="stylesheet" href="/css/style.css" />
</head>

<body>

    <!-- header start -->
    <div class="header">
        <div class="wrap">
            <div class="logo">
                <a href="home.html">药优优电子商务管理系统</a>
            </div>
            <div class="user">
                <span>登录用户 hehuan</span>
                <i>|</i>
                <span>2016年6月20日 星期三</span>
                <i>|</i>
                <a href="logout.html">退出</a>
            </div>
        </div>
    </div><!-- header end -->


    <!-- nav start -->
    <div class="nav">
        <div class="wrap">
            <ul>
                <li><a href="#!">首页</a></li>
                <li><a href="#!">销售</a></li>
                <li><a href="#!">目录</a></li>
                <li>
                    <a class="curr" href="#!">客户</a>
                    <div class="subnav">
                        <a href="customers.html">客户管理</a>
                    </div>
                </li>
                <li>
                    <a href="#!">促销</a>
                    <div class="subnav">
                        <a href="customers.html">客户管理</a>
                        <a href="customers.html">客户管理</a>
                        <a href="customers.html">客户管理</a>
                        <a href="customers.html">客户管理</a>
                        <a href="customers.html">客户管理</a>
                    </div>
                </li>
                <li><a href="#!">邮件列表</a></li>
                <li><a href="#!">CMS</a></li>
                <li><a href="#!">报表</a></li>
                <li><a href="#!">系统</a></li>
            </ul>
        </div>
    </div><!-- nav end -->


    <!-- fa-floor start -->
    <div class="fa-floor">
        <div class="wrap">
            <div class="title">
                <h3>客户管理</h3>
                <div class="extra"><a class="btn btn-red" href="/menber/to/add/user"><i class="fa fa-plus"></i>增加新客户</a></div>
            </div>
            <div class="pagin">
                <div class="extra">
                    <a class="btn btn-gray" href="#"><i class="fa fa-export"></i>导出</a>
                </div>
                <div class="skip">
                    <span>第</span>
                    <button type="button" class="fa fa-chevron-left btn btn-gray"></button><input type="text" class="ipt" value="1"><button class="fa fa-chevron-right btn btn-gray"></button>
                    <span>页，共</span><em>6</em><span>页</span>
                    <i>|</i>
                    <span>每页</span>
                    <select name="" id="">
                        <option value="">10</option>
                        <option value="">20</option>
                        <option value="">30</option>
                        <option value="">40</option>
                    </select>
                    <span>个记录，共有 2 个记录</span>
                </div>
            </div>
            <div class="chart">
                <table class="tc">
                    <thead>
                        <tr>
                            <th width="70">编号</th>
                            <th>会员名</th>
                            <th>企业全称</th>
                            <th>企业注册地区</th>
                            <th>联系人</th>
                            <th>手机号</th>
                            <th width="170">注册日期</th>
                            <th>与ERP关联</th>
                            <th>操作</th>
                        </tr>
                        <tr>
                            <td></td>
                            <form action="/menber/get/userlist" id="myform">
	                            <td><input name="userName" id="userName" type="text" class="ipt" value="${user.userName!''}"></td>
	                            <td><input name="companyFullName" id="companyFullName" type="text" class="ipt" value="${user.companyFullName!''}"></td>
	                            <td><input name="areaFull" id="areaFull" type="text" class="ipt" value="${user.areaFull!''}"></td>
	                            <td><input name="contactName" id="contactName" type="text" class="ipt" value="${user.contactName!''}"></td>
	                            <td><input name="contactMobile" id="contactMobile" type="text" class="ipt" value="${user.contactMobile!''}"></td>
	                            <td><input name="startDate" id="start" type="text" class="ipt date" value="${user.startDate!''}"> - <input name="endDate" id="end" type="text" class="ipt date" value="${user.endDate!''}"></td>
                            
	                            <td>
	                                <select name="bindErp" id="bindErp">
	                                    <option value="1" selected="selected">是</option>
	                                    <option value="0">否</option>
	                                </select>
	                            </td>
	                            <td>
	
	                                <button class="button" type="button" id="search"><i class="fa fa-search"></i><span>搜索</span></button>
	                            </td>
                            </form>
                        </tr>
                    </thead>
                    <tfoot></tfoot>
                    <tbody>
	                    <#if (users?size>0)>
		                    <#list users as user>
		                        <tr>
		                            <td>${user.id }</td>
		                            <td>${user.userName }</td>
		                            <td>${user.companyFullName }</td>
		                            <td>${user.areaFull }</td>
		                            <td>${user.contactName }</td>
		                            <td>${user.contactMobile }</td>
		                            <td>${user.createTime?string("yyyy-MM-dd HH:mm:ss") }</td>
		                            <td>
	                            		<#if (user.bindErp==1)>
	                            			是
		                            	<#else>
		                            		否
		                            	</#if>	
		                            </td>
		                            <td><a href="customers-info.html">修改</a></td>
		                        </tr>
		                     </#list>
		                 </#if>
                    </tbody>
                </table>
            </div>
        </div><!-- fa-floor end -->
    </div>


    <!-- footer start -->
    <div class="footer">
        <div class="wrap">            
            <div class="copyright">
                <p>药优优电商管理系统 版本 1.0  版权所有 &copy; 2016 药优优</p>
            </div>
        </div>
    </div><!-- footer end -->

    <script src="/js/jquery.min.js"></script>
    <script src="/js/laydate/laydate.js"></script>
    <script>
    //定义根变量
    !(function($) {
        var page = {
            //定义全局变量区
            v: {
                id: "page"
            },
            //定义方法区
            fn: {
                //初始化方法区
                init: function () {
                    page.fn.dateInit();
                },
                //日期选择
                dateInit: function () {
                    var start = {
                        elem: '#start',
                        format: 'YYYY/MM/DD',
                        min: laydate.now(), //设定最小日期为当前日期
                        max: '2099-06-16 23:59:59', //最大日期
                        istime: true,
                        istoday: false,
                        choose: function(datas){
                             end.min = datas; //开始日选好后，重置结束日的最小日期
                             end.start = datas; //将结束日的初始值设定为开始日
                             $('#start').attr('title', datas);
                        }
                    };
                    var end = {
                        elem: '#end',
                        format: 'YYYY/MM/DD',
                        min: laydate.now(),
                        max: '2099-06-16 23:59:59',
                        istime: true,
                        istoday: false,
                        choose: function(datas){
                            start.max = datas; //结束日选好后，重置开始日的最大日期
                            $('#end').attr('title', datas);
                        }
                    };
                    laydate(start);
                    laydate(end);
                }
            }
        }
        //加载页面js
        $(function() {
        	page.fn.init();

        	var bindErp = "${user.bindErp!'' }";
        	if(bindErp != ''){
        		$('#bindErp').val(bindErp);
        	}
        	else{
        		//1为是
        		$('#bindErp').val('1');
        	}
            $('#search').on('click', function() {
                $('#myform').submit();
            })
        });
        })(jQuery);
    </script>
</body>
</html>