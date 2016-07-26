<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
    <title>询价-饮片B2B</title>
</head>

<body>

    <#include "./inc/header-center.ftl"/>

    <!-- member-box start -->
    <div class="member-box">
        <div class="wrap">
            <#include "./inc/side-center.ftl"/>

            <div class="main">
                <div class="title">
                    <h3>我要询价</h3>
                    <div class="extra"></div>
                </div>

                <div class="fa-table">
                	<div class="caption">
                		<p>请输入要询价的商品名称及对应数量，期望单价可以不输入。一次可以添加多个商品。</p>      
                		<p>您还可以<a class="btn" href="#">下载模板</a>填入内容后，<span class="btn btn-file">上传文档<input type="file"></span>到网站。</p>       
                	</div>
				
                	<div class="fa-chart">
                		<form action="/center/enquiry/submit" method="post" id="enquiryForm">
	                		<table>
	                			<thead>
	                				<tr>
	                					<th width="180">商品名称</th>
	                					<th>切制规格</th>
	                					<th>等级</th>
	                					<th>产地</th>
	                					<th>数量<span>（公斤）</span></th>
	                					<th width="130">期望单价<span>（元/公斤）</span></th>
	                					<th width="110">期望交货日期</th>
	                					<th width="100">操作</th>
	                				</tr>
	                			</thead>
	                			<tfoot>
	                				<tr>
	                            		<td><div class="ipt-wrap"><input type="text" class="ipt ipt-name" value="" name="commodityName" autocomplete="off"></div></td>
	                            		<td><div class="ipt-wrap"><input type="text" class="ipt" value="" name="specs" autocomplete="off"></div></td>
	                            		<td><div class="ipt-wrap"><input type="text" class="ipt" value="" name="level" autocomplete="off"></div></td>
	                            		<td><div class="ipt-wrap"><input type="text" class="ipt" value="" name="origin" autocomplete="off"></div></td>
                                        <td style="display: none"><div class="ipt-wrap"><input type="text" class="ipt ipt-name" value="" name="commodityId" autocomplete="off"></div></td>
                                        <td><div class="ipt-wrap"><input type="text" class="ipt amount" value="" name="amount" autocomplete="off"></div></td>
	                            		<td><div class="ipt-wrap"><input type="text" class="ipt price" value="" name="expectPrice" autocomplete="off"></div></td>
	                            		<td><div class="ipt-wrap"><input type="text" class="ipt date" value="" name="expectDate" autocomplete="off" onclick="laydate({min:laydate.now()})"></div></td>
	                            		<td>
	                            			<a class="add c-blue" href="javascript:;">添加</a>
	                            			<a class="remove c-red" href="javascript:;">删除</a>
	                            		</td>
	                				</tr>
	                			</tfoot>
	                			<tbody>
								<#if (commodityList?size)<2>
									<tr>
                                        <td><div class="ipt-wrap"><input type="text" class="ipt ipt-name" value="<#if commodityList[0]??>${commodityList[0].name!}</#if>" name="commodityName" autocomplete="off"></div></td>
										<td><div class="ipt-wrap"><input type="text" class="ipt" value="<#if commodityList[0]??>${commodityList[0].specName!}</#if>" name="specs" autocomplete="off"></div></td>
										<td><div class="ipt-wrap"><input type="text" class="ipt" value="<#if commodityList[0]??>${commodityList[0].levelName!}</#if>" name="level" autocomplete="off"></div></td>
										<td><div class="ipt-wrap"><input type="text" class="ipt" value="<#if commodityList[0]??>${commodityList[0].factory!}</#if>" name="origin" autocomplete="off"></div></td>
                                        <td style="display: none"><div class="ipt-wrap"><input type="text" class="ipt ipt-name" value="<#if commodityList[0]??>${commodityList[0].id!}</#if>" name="commodityId" autocomplete="off"></div></td>
                                        <td><div class="ipt-wrap"><input type="text" class="ipt amount" value="" name="amount"  autocomplete="off"></div></td>
										<td><div class="ipt-wrap"><input type="text" class="ipt price" value="" name="expectPrice" autocomplete="off"></div></td>
										<td><div class="ipt-wrap"><input type="text" class="ipt date" value="" name="expectDate" autocomplete="off" onclick="laydate({min:laydate.now()})"></div></td>
                                        <td>
											<a class="add c-blue" href="javascript:;">添加</a>
										</td>
									</tr>
									<#else>
										<#list commodityList as commodity>
											<tr>
												<input name="commodityId" type="hidden" value="${commodity.id!}" />
												<td><div class="ipt-wrap"><input type="text" class="ipt ipt-name" value="${commodity.name!}" name="commodityName" autocomplete="off"></div></td>
												<td><div class="ipt-wrap"><input type="text" class="ipt" value="${commodity.specName!}" name="specs" autocomplete="off"></div></td>
												<td><div class="ipt-wrap"><input type="text" class="ipt" value="${commodity.levelName!}" name="level" autocomplete="off"></div></td>
												<td><div class="ipt-wrap"><input type="text" class="ipt" value="${commodity.factory!}" name="origin" autocomplete="off"></div></td>
                                                <td style="display: none"><div class="ipt-wrap"><input type="text" class="ipt ipt-name" value="<#if commodityList[0]??>${commodityList[0].id!}</#if>" name="commodityId" autocomplete="off"></div></td>
                                                <td><div class="ipt-wrap"><input type="text" class="ipt amount" value="" name="amount" autocomplete="off"></div></td>
												<td><div class="ipt-wrap"><input type="text" class="ipt price" value="" name="expectPrice" autocomplete="off"></div></td>
												<td><div class="ipt-wrap"><input type="text" class="ipt date" value="" name="expectDate" autocomplete="off" onclick="laydate({min:laydate.now()})"></div></td>
                                                <td>
													<a class="add c-blue" href="javascript:;">添加</a>
													<a class="remove c-red" href="javascript:;">删除</a>
												</td>
											</tr>
										</#list>
								</#if>

	                			</tbody>
	                		</table>
	                		<div class="submit">
	                			<button class="btn btn-red" type="button" id="submit">提交</button>
	                		</div>
                		</form>
                	</div>
                </div>
            </div>
        </div>
    </div><!-- member-box end -->

    <#include "./inc/footer.ftl"/>

    <!-- 输入框联想 start -->
    <div class="suggestions" id="suggestions">
		<div class="hd">
			<div class="group">
				<span class="w1">商品名称</span><span class="w2">切制规格</span><span class="w3">等级</span><span class="w4">产地</span>
			</div>
		</div>
		<div class="bd"></div>
	</div><!-- 输入框联想 end -->

    <script src="/js/jquery.form.js"></script>
    <script src="/js/member.js"></script>
    <script src="js/layer/layer.js"></script>
    <script src="js/laydate/laydate.js"></script>
    <script src="js/common.js"></script>
    <script>
    	var page = {
    		v: {
    		},
    		fn: {
    			init: function() {
    				this.myformEvent();
                    this.submit();
    			},
    			// input
    			myformEvent: function() {
                    var $body        = $('body');
                    var $suggestions = $('#suggestions');
                    var $myform      = $('#enquiryForm');
                    var $tbody       = $myform.find('tbody');
                    var $ipt         = $tbody.find('.ipt:first');
                    var $tfoot       = $myform.find('tfoot');
                    var modal        = $tfoot.html();
                    var self         = this;
    				$tfoot.empty();

                    // 第一个输入框不为空时自动获取焦点
                    $ipt.val() === '' && $ipt.focus();

                    // 数量
                    $myform.on('keyup', '.amount', function(e) {
                        var val = this.value;
                        if (!/^\d*$/.test(val)) {
                            val = Math.abs(parseInt(val));
                            this.value = isNaN(val) ? '' : val;
                        }
                    });

                    // 单价
                    $myform.on('keyup', '.price', function(e) {
                        var val = this.value;
                        if (!/^\d+\.?\d*$/.test(val)) {
                            val = Math.abs(parseFloat(val));
                            this.value = isNaN(val) ? '' : val;
                        }
                    });

                    // 商品名联想
    				$myform.on({
                        'click': function(event) {
                            $(this).after($suggestions);
                            // self.getKeywords(this.value); // 获取焦点时查询一次
                            event.stopPropagation();
                        },
                        'input': function() {
                            self.getKeywords(this.value);                           
                        }
    				}, '.ipt-name');

                    // 关闭联想层
    				$body.on('click', function() {
    					$suggestions.hide();
    				})
    				$body.on('click', '.suggestions', function(event) {
    					event.stopPropagation();
    				})

                    // 关键字自动填充
                    $body.on('click', '.suggestions .bd .group', function() {
                        var data = $(this).data('val').split('-');
                        $suggestions.prev().val(data[0])
                        .closest('td').next().find('.ipt').val(data[1]).end()
                        .closest('td').next().find('.ipt').val(data[2]).end()
                        .closest('td').next().find('.ipt').val(data[3]).end()
                        .closest('td').next().find('.ipt').val(data[4]);
                        $suggestions.hide();
                    })

    				// 新增一行
    				$myform.on('click', '.add', function() {
    					$tbody.append(modal);
    					// 没有删除按钮时添加删除按钮
    					$(this).siblings().length === 0 && $(this).after(' <a class="remove c-red" href="javascript:;">删除</a>');
    					
    				})

    				// 删除一行
    				$myform.on('click', '.remove', function() {
    					var $tr = $(this).closest('tr'),
    						$btnRemove = $tbody.find('.remove');

    					if ($btnRemove.length < 2) {
    						return false;
    					} 
                        // 弹层确认删除
    					layer.confirm('确认删除行？', {icon: 3, title:'提示'}, function(index){
    						$btnRemove.length === 2 && $btnRemove.remove();
                            var commodityId =  $tr.find("input[name='commodityId']").val();
    						$tr.remove();
						  	layer.close(index);
							//请求服务器删除cookie
							if(commodityId){
                                $.ajax({
                                    url: 'center/enquiry/delete',
                                    dataType: 'json',
                                    data:{commodityId:commodityId}
                                })
							}

						});       
    				})
    			},
    			// ajax 查询关键词
    			getKeywords: function(keywords) {
                    var self = this;
    				var keywords = $.trim(keywords);
                    var $suggestions = $('#suggestions');
					if (keywords === '') {
						$suggestions.hide();
					} else {
						// ajax 查询关键词
                        self.timer && clearTimeout(self.timer);
                        self.timer = setTimeout(function() {
                            self.ajaxSearch(keywords);
                        }, 500);                
					}    			
    			},
                ajaxSearch: function(keywords) {
                    var self = this;
                    $.ajax({
                        url: 'center/enquiry/auto',
                        dataType: 'json',
						data:{commodityName:keywords},
                        success: function(data) {
                            // 显示查询结果
                            if (data.status === 'y') {
                                self.toHtml(data.data);
                            } else {
                                $('#suggestions').hide();
                            }
                        }
                    })
                },
    			// 显示查询结果
    			toHtml: function(json) {
					console.log(json)
					var modal = [];
					$.each(json, function(i, item) {
                        var val = item.name + '-' + item.spec + '-' + item.level + '-' + item.originOf+'-'+item.id;
						modal.push('<div class="group" data-val="', val, '">');
						modal.push('<span class="w1">', item.name, '</span>');
						modal.push('<span class="w2">', item.spec, '</span>');
						modal.push('<span class="w3">', item.level, '</span>');
						modal.push('<span class="w4">', item.originOf, '</span>');
					 	modal.push('</div>');
					})
					$('#suggestions .bd').empty().html(modal.join('')).parent().show();
    			},
                submit: function() {

                    $('#submit').on('click', function() {

                        $('#enquiryForm').ajaxSubmit({
                            success:function(result){
                                if(result.status=="y"){

                                }
                            }
                        })


                    })
                }
    		}
    	}
    	$(function() {
    		page.fn.init();
    	})
    </script>
</body>
</html>