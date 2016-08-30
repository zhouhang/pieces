<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
    <title>商品列表-饮片B2B</title>
</head>

<body>
	<#include "./inc/header-center.ftl"/>


    <!-- header start -->
    <div class="header header-red">
        <div class="wrap">
            <div class="logo">
                <a href="home.html">饮片B2B首页</a>
            </div>
            <div class="title">
                <h1>我的供应链</h1>
            </div>
            <div class="plus">
                <a class="back" href="home.html"><i class="fa fa-chevron-left"></i> 返回商城首页</a>
            </div>
        </div>
    </div><!-- header end -->

    <!-- member-box start -->
    <div class="member-box">
        <div class="wrap">
            <#include "./inc/side-center.ftl"/>

            <div class="main">
                <div class="title">
                    <h3>我的收藏</h3>
                    <div class="extra"></div>
                </div>
                
                <div class="mycollect">
                    <div class="fa-chart">
                        <table>
                            <thead>
                                <tr>
                                    <th width="150">商品名称</th>
                                    <th width="70">切制规格</th>
                                    <th width="70">等级</th>
                                    <th>外观描述</th>
                                    <th width="120">产地</th>
                                    <th width="120">操作</th>
                                </tr>
                            </thead>
                            
                            <#if (commodityVoList??&&commodityVoList?size>0)>
                            <tbody>
								<#list commodityVoList as commodity>
	                                <tr>
	                                    <td>${commodity.name}</td>
	                                    <td>${commodity.specName}</td>
	                                    <td>${commodity.levelName}</td>
	                                    <td>${commodity.exterior}</td>
	                                    <td>${commodity.originOfName}</td>
	                                    <td>
	                                        <if commodity.status == 1><a href="/center/enquiry/index?commodityId=${commodity.id!}" class="c-blue">询价</a></#if>
	                                        <a href="/center/collect/delete/${commodity.id}" class="c-red jdel">删除</a>
	                                    </td>
	                                </tr>
                                </#list>
                            </tbody>
                            <#else>
								<tr>
                                    <td colspan="6">
                                        <div class="empty">
                                           	 您还没有收藏任何商品！
                                        </div>
                                    </td>
                                </tr>
                           </#if> 
                        </table>
                    </div>

                </div>
            </div>
        </div>
    </div><!-- member-box end -->

    <!-- footer start -->
    <div class="footer">
        <div class="wrap">
            <div class="links">
                <a href="#">关于上工之选</a>
                <i>|</i>
                <a href="#">联系我们</a>
                <i>|</i>
                <a href="#">法律申明</a>
                <i>|</i>
                <a href="#">建议与投诉</a>
                <i>|</i>
                <a href="#">友情链接</a>
                <i>|</i>
                <a href="#">站长统计</a>
            </div>
            <div class="copyright">
                <p> 电信与信息服务业务经营许可证号：皖B20140001  备案号：皖ICP备13006003号  互联网药品交易服务资格证：皖B20130001  互联网药品信息服务资格证：（皖）-经营性-2016-0001</p>
                <p>网站商务合作邮箱：bd@copy;yaoyy.com  客户服务企业邮箱：service@copy;yaoyy.com  Copyright &copy; 2015 – 2020 上工之选 All Rights Reserved</p>
            </div>
        </div>
    </div><!-- footer end -->
	
	<!-- 输入框联想 start -->
    <div class="suggestions" id="suggestions">
		<div class="hd">
			<div class="group">
				<span class="w1">商品名称</span><span class="w2">切制规格</span><span class="w3">等级</span><span class="w4">产地</span>
			</div>
		</div>
		<div class="bd"></div>
	</div><!-- 输入框联想 end -->

    <script src="js/jquery.min.js"></script>
    <script src="js/layer/layer.js"></script>
    <script src="js/laydate/laydate.js"></script>
    <script src="js/common.js"></script>
    <script>
        var _global = {
            v: {
            },
            fn: {
                init: function() {
                    this.delFav();
                },
                // 删除
                delFav: function() {
                    $('.fa-chart').on('click', '.jdel', function() {
                        layer.confirm('确认从收藏夹中删除这个商品吗？', {icon: 3, title:'提示'}, function(index){
                            alert('删除成功')
                            layer.close(index); // 关闭弹层
                        });  
                        return false; // 组织默认事件
                    })
                }
            }
        }
        //加载页面js
        $(function() {
            _global.fn.init();
        });
    </script>
</body>
</html>