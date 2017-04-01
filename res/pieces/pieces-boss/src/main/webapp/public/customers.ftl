<!DOCTYPE html>
<html lang="en">
<head>
<#include "./inc/meta.ftl"/>
<title>客户清单-boss-上工好药</title>
</head>

<body>

	<#include "./inc/header.ftl"/>

	<!-- fa-floor start -->
	<div class="fa-floor">
		<div class="wrap">
			<#if (advices??)>
			<div class="message">
				<i class="fa fa-check-circle"></i> <span>${advices}</span>
			</div>
			</#if>

			<div class="title title-btm">
				<h3>客户管理</h3>
				<div class="extra">
				<@shiro.hasPermission name="customer:add">
    				<a class="btn btn-red" href="/user/add"><i class="fa fa-plus"></i>增加新客户</a>
				</@shiro.hasPermission>
				</div>
			</div>
			<div class="pagin">
				<div class="extra">
					<button id="reset" type="reset" class="btn btn-gray">重置条件</button>
					<button class="btn btn-blue" type="button" id="search_btn">
						<i class="fa fa-search"></i><span>搜索</span>
					</button>
				</div>
				<@p.pager pageInfo=userPage pageUrl="/user/index" params=userParams/>
			</div>
			<div class="chart">
				<table class="tc">
					<thead>
						<tr>
							<th width="70">编号</th>
							<th>会员名</th>
							<th>联系人</th>
							<th>手机号</th>
							<th width="200">注册日期</th>
                            <th width="80">是否认证</th>
							<th width="80">客户类型</th>
                            <th width="80">跟单员</th>
							<th width="80">操作</th>
						</tr>
						<tr>
							<form id="search_form">
								<td></td>
								<td><div class="ipt-wrap">
										<input name="userName" type="text" class="ipt"
											value="${userVo.userName!}">
									</div></td>
								<td><div class="ipt-wrap">
										<input name="contactName" type="text" class="ipt"
											value="${userVo.contactName!}">
									</div></td>
								<td><div class="ipt-wrap">
										<input name="contactMobile" type="text" class="ipt"
											value="${userVo.contactMobile!}">
									</div></td>
								<td><input name="startDate" type="text" class="ipt date"
									value="${userVo.startDate}" id="start"> - <input
									name="endDate" type="text" class="ipt date"
									value="${userVo.endDate}" id="end"></td>
                                <td><select name="certifyStatus" id="certifyStatus">
                                    <option <#if (!userVo.certifyStatus??)>selected</#if>
                                            value=""></option>
                                    <option <#if
											(userVo.certifyStatus??&&userVo.certifyStatus==1)>selected</#if>
                                            value="1">已认证</option>
                                    <option <#if
											(userVo.certifyStatus??&&userVo.certifyStatus==0)>selected</#if>
                                            value="0">未认证</option>
                                </select></td>
								<td><select name="type" id="type">
										<option <#if (!userVo.type??)>selected</#if>
                                                value=""></option>
										<option <#if
											(userVo.type??&&userVo.type==1)>selected</#if>
											value="1">终端用户</option>
										<option <#if
											(userVo.type??&&userVo.type==2)>selected</#if>
											value="2">代理商</option>
								</select></td>
                                <td><div class="ipt-wrap">
                                    <input name="serviceName" type="text" class="ipt"
                                           value="${userVo.serviceName!}">
                                </div></td>
								<td></td>
							</form>
						</tr>
					</thead>
					<tfoot></tfoot>
					<tbody>
						<#list userPage.list as user>
						<tr>
							<td>${user.id}</td>
							<td>${user.userName}</td>
							<td>${user.contactName}</td>
							<td>${user.contactMobile}</td>
							<td>${user.createTime?date}</td>
                            <td><#if user.certifyStatus==1>已认证 <#else>未认证</#if></td>
							<td><#if user.type==1>终端用户 <#elseif user.type==2>代理商</#if></td>
							<td>${user.serviceName!}</td>
							<td>
								<@shiro.hasPermission name="customer:edit">
                                    <a href="user/info/${user.id}">修改</a>
									<#if !user.isDel>
                                        <a class="jdisable" data-id="${user.id}" href="javascript:;">禁用</a>
									<#else>
                                        <a class="jenable" data-id="${user.id}" href="javascript:;">启用</a>
									</#if>
								</@shiro.hasPermission>
							</td>
						</tr>
						</#list>
					</tbody>
				</table>
			</div>
		</div>
		<!-- fa-floor end -->
	</div>

	<#include "./inc/footer.ftl"/>
    <script src="${urls.getForLookupPath('/js/layer/layer.js')}"></script>
	<script src="${urls.getForLookupPath('/js/laydate/laydate.js')}"></script>
	<script>
    //定义根变量
    !(function($) {
        var page = {
            //定义全局变量区
            v: {
                id: "page",
                pageNum:${userPage.pageNum},
                pageSize:${userPage.pageSize}
            },
            //定义方法区
            fn: {
                //初始化方法区
                init: function () {
                    page.fn.dateInit();
                    page.fn.filter();
                    $("#search_btn").click(function(){
                        page.fn.filter();
                    });
                    
                    $("#reset").click(function(){
                    	$('.tc :input').not(':button, :submit, :reset, :hidden').val('').removeAttr('checked').removeAttr('selected');
                    })

                    $(".jenable").click(function(){
						var $that = $(this);
                        layer.confirm('确认要启用该用户？', {icon: 3, title: '提示'}, function (index) {
                            var url = "/user/enable?id=" + $that.data("id");
                            $.post(url, function () {
                                window.location.reload();
                            })
                        });
						return false;
                    });

                    $(".jdisable").click(function(){
                        var $that = $(this);
                        layer.confirm('确认要禁用该用户？', {icon: 3, title: '提示'}, function (index) {
                            var url = "/user/disable?id=" + $that.data("id");
                            $.post(url, function () {
                                window.location.reload();
                            })
                        });
                        return false;
                    });

                },
                // 筛选
                filter: function() {
                    var $ipts = $('.chart .ipt, .chart select');
                    var url="/user/index?pageNum="+page.v.pageNum+"&pageSize="+page.v.pageSize;

                    $('#search_btn').on('click', function() {
                        var params = [];
                        $ipts.each(function() {
                            var val = $.trim(this.value);
                            val && params.push($(this).attr('name') + '=' + val);
                        })
                        location.href=url+"&"+params.join('&');
                    })
                },
                //日期选择
                dateInit: function () {
                    var start = {
                        elem: '#start',
                        istoday: false,
                        choose: function(datas){
                            end.min = datas;
                            end.start = datas;
                        }
                    };
                    var end = {
                        elem: '#end',
                        istoday: false,
                        choose: function(datas){
                            start.max = datas;
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
        });
    })(jQuery);


</script>

</body>
</html>