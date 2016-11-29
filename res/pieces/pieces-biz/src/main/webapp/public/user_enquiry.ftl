<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
    <title>询价-上工好药</title>
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
                        <form id="excelForm" action="/center/enquiry/parseXsl" method="post" enctype="multipart/form-data">
                        <p><strong>输入商品的名称，选择要询价的片型及规格等级。一次可添加多个商品。</strong></p>
                        <p>您还可以<a class="btn" href="/file/批量采购模版.xls">下载模板</a>填入内容后，
                            <span class="btn btn-file">上传文档<input type="file" id="excel" name="excel"></span>
                            到网站。</p>
                        </form>
                    </div>
				
                	<div class="fa-chart">
                		<form action="/center/enquiry/submit" method="post" id="enquiryForm">
                            <input  id="billId" type="hidden" value="${billId!}" name="billId">
	                		<table>
	                			<thead>
	                				<tr>
                                        <th width="110">商品名称</th>
                                        <th width="90">片型</th>
                                        <th>规格等级</th>
                                        <th width="100">产地</th>
                                        <th width="150">操作</th>
	                				</tr>
	                			</thead>
	                			<tfoot>
	                				<tr>
	                            		<td><div class="ipt-wrap"><input type="text" class="ipt ipt-name" value="" name="commodityName" autocomplete="off"><span class="error"></span></div>
                                            <input name="commodityId" type="hidden" value="" /></td>
	                            		<td><div class="ipt-wrap"><input type="text" class="ipt" value="" name="specs" autocomplete="off"><span class="error"></span></div></td>
	                            		<td><div class="ipt-wrap"><input type="text" class="ipt" value="" name="level" autocomplete="off"><span class="error"></span></div></td>
	                            		<td><div class="ipt-wrap"><input type="text" class="ipt" value="" name="origin" autocomplete="off"><span class="error"></span></div></td>
	                            		<td>
	                            			<a class="add c-blue" href="javascript:;">添加</a>
	                            			<a class="remove c-red" href="javascript:;">删除</a>
	                            		</td>
	                				</tr>
	                			</tfoot>
	                			<tbody>

                                <#if enquiryCommoditysList??>
                                    <#list enquiryCommoditysList as enquiryCommodity>
                                    <tr>
                                        <td><div class="ipt-wrap"><input type="text" class="ipt ipt-name" value="${enquiryCommodity.commodityName!}" name="commodityName" autocomplete="off"><span class="error"></span></div><input name="commodityId" type="hidden" value="<#if enquiryCommodity??>${enquiryCommodity.id!}</#if>" /></td>
                                        <td><div class="ipt-wrap"><input type="text" class="ipt" value="${enquiryCommodity.specs!}" name="specs" autocomplete="off"><span class="error"></span></div></td>
                                        <td><div class="ipt-wrap"><input type="text" class="ipt" value="${enquiryCommodity.level!}" name="level" autocomplete="off"><span class="error"></span></div></td>
                                        <td><div class="ipt-wrap"><input type="text" class="ipt" value="${enquiryCommodity.origin!}" name="origin" autocomplete="off"><span class="error"></span></div></td>
                                        <td>
                                            <a class="add c-blue" href="javascript:;">添加</a>
                                            <#if (enquiryCommoditysList?size>1)>
                                                <a class="remove c-red" href="javascript:;">删除</a>
                                            </#if>
                                        </td>
                                    </tr>
                                    </#list>
                                </#if>


                                <#if commodityList??&&commodityList?has_content>
                                    <#list commodityList as commodity>
                                        <tr>
                                            <td><div class="ipt-wrap"><input type="text" class="ipt ipt-name" value="${commodity.name!}" name="commodityName" autocomplete="off"><span class="error"></span></div><input name="commodityId" type="hidden" value="<#if commodityList[0]??>${commodityList[0].id!}</#if>" /></td>
                                            <td><div class="ipt-wrap"><input type="text" class="ipt" value="${commodity.spec!}" name="specs" autocomplete="off"><span class="error"></span></div></td>
                                            <td><div class="ipt-wrap"><input type="text" class="ipt" value="${commodity.level!}" name="level" autocomplete="off"><span class="error"></span></div></td>
                                            <td><div class="ipt-wrap"><input type="text" class="ipt" value="${commodity.originOf!}" name="origin" autocomplete="off"><span class="error"></span></div></td>
                                            <td>
                                                <a class="add c-blue" href="javascript:;">添加</a>
                                                <#if (commodityList?size>1)>
                                                    <a class="remove c-red" href="javascript:;">删除</a>
                                                </#if>
                                            </td>
                                        </tr>
                                    </#list>
                                    <#else>
                                        <#if !enquiryCommoditysList??>
                                            <tr>
                                                <td><div class="ipt-wrap"><input type="text" class="ipt ipt-name" value="" name="commodityName" autocomplete="off"><span class="error"></span></div><input name="commodityId" type="hidden" value="" /></td>
                                                <td><div class="ipt-wrap"><input type="text" class="ipt" value="" name="specs" autocomplete="off"><span class="error"></span></div></td>
                                                <td><div class="ipt-wrap"><input type="text" class="ipt" value="" name="level" autocomplete="off"><span class="error"></span></div></td>
                                                <td><div class="ipt-wrap"><input type="text" class="ipt" value="" name="origin" autocomplete="off"><span class="error"></span></div></td>
                                                <td>
                                                    <a class="add c-blue" href="javascript:;">添加</a>
                                                </td>
                                            </tr>
                                        </#if>
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
				<span class="w1">商品名称</span><span class="w2">片型</span><span class="w3">规格等级</span><span class="w4">产地</span>
			</div>
		</div>
		<div class="bd"></div>
	</div><!-- 输入框联想 end -->

    <script src="js/jquery.form.js"></script>
    <script src="js/layer/layer.js"></script>
    <script src="js/laydate/laydate.js"></script>
    <script>

    	var page = {
    		v: {
    		},
    		fn: {
    			init: function() {
    				this.myformEvent();
                    this.submit();
                    this.uploadExcel();
    			},
                uploadExcel:function(){
                    $("#excelForm").ajaxForm({
                        url:"/center/enquiry/parseXsl",
                        success: function(result) {
                            page.fn.toTable(result)
                        }
                    });
                    $("#excel").change(function(){
                        $("#excelForm").submit();
                    })
                },
                toTable:function(result){
                    var html = [];
                    var row = 0;
                    var getVal = function(val){
                        return val ? val : '';
                    }
                    var formatDate = function(date) {
                        return date ? date.split(' ')[0] : '';
                    }
                    $.each(result,function(i, item){
                        if ($.isEmptyObject(item)) {
                            return true; // break
                        }
                        row ++;
                        html.push('<tr><td><div class="ipt-wrap"><input type="text" class="ipt ipt-name" value="', getVal(item.commodityName), '" name="commodityName" autocomplete="off"><span class="error"></span></div><input name="commodityId" type="hidden" value="', getVal(item.commodityId), '" /></td><td><div class="ipt-wrap"><input type="text" class="ipt" value="', getVal(item.specs), '" name="specs" autocomplete="off"><span class="error"></span></div></td><td><div class="ipt-wrap"><input type="text" class="ipt" value="', getVal(item.level), '" name="level" autocomplete="off"><span class="error"></span></div></td><td><div class="ipt-wrap"><input type="text" class="ipt" value="', getVal(item.origin), '" name="origin" autocomplete="off"><span class="error"></span></div></td><td><a class="add c-blue" href="javascript:;">添加</a><a class="remove c-red" href="javascript:;">删除</a></td></tr>');
                    })
                    this.$tbody.empty().html(html.join(''));
                    row < 2 && this.$tbody.find('.remove').remove();
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
                    self.modal       = modal;
                    self.$myform     = $myform;
                    self.$tbody      = $tbody;
                    self.$suggestions = $suggestions;
    				$tfoot.empty();

                    // 隐藏错误提示
                    $myform.on('focus', '.ipt', function() {
                        $(this).nextAll('.error').html('').hide();
                    })

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
                            event.stopPropagation();
                        },
                        'focus': function() {
                            $(this).after($suggestions);
                            $('#suggestions').find('.group').length > 1 && $suggestions.show();
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
                        .closest('td').next().find('.ipt').val(data[1]).trigger('focus').end()
                        .closest('td').next().find('.ipt').val(data[2]).trigger('focus').end()
                        .closest('td').next().find('.ipt').val(data[3]).trigger('focus').end();
                        $suggestions.hide().parent().next().val(data[4]);
                    })

    				// 新增一行
    				$myform.on('click', '.add', function() {
    					$tbody.append(modal);
    					// 没有删除按钮时添加删除按钮
    					$(this).siblings().length === 0 && $(this).after(' <a class="remove c-red" href="javascript:;">删除</a>');
    					
    				})

    				// 删除一行
    				$myform.on('click', '.remove', function() {
                        var 
                            $tr         = $(this).closest('tr'),
                            $btnRemove  = $tbody.find('.remove'),
                            cid = $tr.find('input[name="commodityId"]').val();

    					if ($btnRemove.length < 2) {
    						return false;
    					} 
                        // 弹层确认删除
    					layer.confirm('确认删除行？', {icon: 3, title:'提示'}, function(index){
                            $btnRemove.length === 2 && $btnRemove.remove();
    						$tr.remove();
						  	layer.close(index);
							//请求服务器删除cookie
							if(cid) {
                                $.ajax({
                                    url: 'center/enquiry/delete',
                                    dataType: 'json',
                                    data:{commodityId: cid}
                                })
                            }
						});       
    				})
    			},
    			// ajax 查询关键词
    			getKeywords: function(keywords) {
                    var self = this;
    				var keywords = $.trim(keywords);
					if (keywords === '') {
                        self.$suggestions.hide();
					} else {
						// ajax 查询关键词
                        self.timer && clearTimeout(self.timer);
                        self.timer = setTimeout(function() {
                            self.ajaxSearch(keywords);
                        }, 300);                
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
                                self.$suggestions.hide();
                            }
                        }
                    })
                },
    			// 显示查询结果
    			toHtml: function(json) {
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
					this.$suggestions.find('.bd').empty().html(modal.join('')).parent().show();
    			},
                submit: function() {
                    var self     = this;
                    var isSubmit = false;
                    $('#submit').on('click', function() {
                        if (isSubmit) {
                            return false;
                        }
                        var result = self.checkForm();
                        if (result.pass) {
                            isSubmit = true;
                            var list = JSON.stringify(result.data);
                            $.ajax({
                                type : 'post',
                                url : '/center/enquiry/submit?billId=' + $('#billId').val(),
                                contentType : 'application/json',
                                data :list,
                                dataType : 'json',
                                success : function(result) {
                                    isSubmit = false;
                                    self.response(result);
                                },
                                complete: function() {
                                    isSubmit = false;
                                }
                            });


                        }else{
                            isSubmit = false;
                        }
                        return false;
                    })
                },
                checkForm: function() {
                    var result = {
                        data: [],
                        pass: true
                    };
                    var $tag;
                    this.$tbody.find('tr').each(function() {
                        var row = {};
                        $(this).find('.ipt').each(function() {
                            $tag = $(this);
                            if ($tag.hasClass('price')) {
                                // nothing
                            } else if (this.value.length === 0) {
                                $tag.nextAll('.error').html('此处不可空白').show();
                                result.pass = false;
                            } else {
                                $tag.nextAll('.error').html('').hide();
                            }
                            row[this.name] = this.value;
                        })
                        result.data.push(row);

                        if (!result.pass) {
                            window.scrollTo(0, $tag.offset().top);
                            return false;
                        }
                    })
                    return result;
                },
                response:function(result){
                    if(result.status=='y'){
                        $.notify({
                            type: 'success',
                            title: '提交成功',
                            text: result.info
                        })
                        this.$tbody.empty().html(this.modal).find('.remove').remove();
                        $('#billId').val('');
                    }else{
                        $.notify({
                            type: 'error',
                            title: '提交错误',
                            text: result.info
                        })
                    }
                }
    		}
    	}
    	$(function() {
    		page.fn.init();
    	})
    </script>
</body>
</html>