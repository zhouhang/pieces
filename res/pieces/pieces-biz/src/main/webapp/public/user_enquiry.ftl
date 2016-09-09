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
                        <form id="excelForm" action=""/center/enquiry/parseXsl" method="post" enctype="multipart/form-data">
                        <p>请输入要询价的商品名称及对应数量，期望单价可以不输入。一次可以添加多个商品。</p>
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
	                            		<td><div class="ipt-wrap"><input type="text" class="ipt ipt-name" value="" name="commodityName" autocomplete="off"><span class="error"></span></div>
                                            <input name="commodityId" type="hidden" value="" /></td>
	                            		<td><div class="ipt-wrap"><input type="text" class="ipt" value="" name="specs" autocomplete="off"><span class="error"></span></div></td>
	                            		<td><div class="ipt-wrap"><input type="text" class="ipt" value="" name="level" autocomplete="off"><span class="error"></span></div></td>
	                            		<td><div class="ipt-wrap"><input type="text" class="ipt" value="" name="origin" autocomplete="off"><span class="error"></span></div></td>
                                        <td><div class="ipt-wrap"><input type="text" class="ipt amount" value="" name="amount" autocomplete="off"><span class="error"></span></div></td>
	                            		<td><div class="ipt-wrap"><input type="text" class="ipt price" value="" name="expectPrice" autocomplete="off"><span class="error"></span></div></td>
	                            		<td><div class="ipt-wrap"><input type="text" class="ipt date" value="" name="expectDate" autocomplete="off" onclick="laydate({min:laydate.now()})"><span class="error"></span></div></td>
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
                                        <td><div class="ipt-wrap"><input type="text" class="ipt amount" value="${enquiryCommodity.amount!}" name="amount" autocomplete="off"><span class="error"></span></div></td>
                                        <td><div class="ipt-wrap"><input type="text" class="ipt price" value="${enquiryCommodity.expectPrice!}" name="expectPrice" autocomplete="off"><span class="error"></span></div></td>
                                        <td><div class="ipt-wrap"><input type="text" class="ipt date" value="${enquiryCommodity.expectDate?string("yyyy-MM-dd")}" name="expectDate" autocomplete="off" onclick="laydate({min:laydate.now()})"><span class="error"></span></div></td>
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
                                            <td><div class="ipt-wrap"><input type="text" class="ipt amount" value="" name="amount" autocomplete="off"><span class="error"></span></div></td>
                                            <td><div class="ipt-wrap"><input type="text" class="ipt price" value="" name="expectPrice" autocomplete="off"><span class="error"></span></div></td>
                                            <td><div class="ipt-wrap"><input type="text" class="ipt date" value="" name="expectDate" autocomplete="off" onclick="laydate({min:laydate.now()})"><span class="error"></span></div></td>
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
                                                <td><div class="ipt-wrap"><input type="text" class="ipt amount" value="" name="amount" autocomplete="off"><span class="error"></span></div></td>
                                                <td><div class="ipt-wrap"><input type="text" class="ipt price" value="" name="expectPrice" autocomplete="off"><span class="error"></span></div></td>
                                                <td><div class="ipt-wrap"><input type="text" class="ipt date" value="" name="expectDate" autocomplete="off" onclick="laydate({min:laydate.now()})"><span class="error"></span></div></td>
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
				<span class="w1">商品名称</span><span class="w2">切制规格</span><span class="w3">等级</span><span class="w4">产地</span>
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
                        beforeSend: function() {
                        },
                        uploadProgress: function(event, position, total, percentComplete) {
                        },
                        success: function(result) {
                            page.fn.toTable(result)
                        },
                        complete: function(xhr) {

                        }
                    });
                    $("#excel").change(function(){
                        $("#excelForm").submit();
                    })
                },
                toTable:function(result){
                    var getItemVal = function(val){
                        if(val){
                            return val;
                        }else{
                            return "";
                        }
                    }
                    var html = "";
                    $.each(result,function(i,item){
                        html+='<tr>'+
                              '<td>'+
                              '<div class="ipt-wrap"><input type="text" class="ipt ipt-name" value="'+getItemVal(item.commodityName)+'" name="commodityName" autocomplete="off"><span class="error"></span></div><input name="commodityId" type="hidden" value="'+getItemVal(item.commodityId)+'" /></td>'+
                              '<td><div class="ipt-wrap"><input type="text" class="ipt" value="'+getItemVal(item.specs)+'" name="specs" autocomplete="off"><span class="error"></span></div></td>'+
                              '<td><div class="ipt-wrap"><input type="text" class="ipt" value="'+getItemVal(item.level)+'" name="level" autocomplete="off"><span class="error"></span></div></td>'+
                              '<td><div class="ipt-wrap"><input type="text" class="ipt" value="'+getItemVal(item.origin)+'" name="origin" autocomplete="off"><span class="error"></span></div></td>'+
                              '<td><div class="ipt-wrap"><input type="text" class="ipt amount" value="'+getItemVal(item.amount)+'" name="amount" autocomplete="off"></div><span class="error"></span></td>'+
                              '<td><div class="ipt-wrap"><input type="text" class="ipt price" value="'+getItemVal(item.expectPrice)+'" name="expectPrice" autocomplete="off"></div><span class="error"></span></td>'+
                              '<td><div class="ipt-wrap"><input type="text" class="ipt date" value="'+page.fn.formatDate(getItemVal(item.expectDate))+'" name="expectDate" autocomplete="off" onclick="laydate({min:laydate.now()})"><span class="error"></span></div></td>'+
                              '<td>'+
                              '<a class="add c-blue" href="javascript:;">添加</a>'+
                                (result.length >1?'<a class="remove c-red" href="javascript:;">删除</a>':'')+
                              '</td>'+
                              '</tr>'
                    })
                    this.$tbody.empty();
                    this.$tbody.html(html);
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

                    // 第一个输入框不为空时自动获取焦点
                    $ipt.val() === '' && $ipt.focus() && $ipt.after($suggestions);


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
                        .closest('td').next().find('.ipt').val(data[1]).trigger('focus').end()
                        .closest('td').next().find('.ipt').val(data[2]).trigger('focus').end()
                        .closest('td').next().find('.ipt').val(data[3]).trigger('focus').end();

                        // .closest('td').next().find('.ipt').val(data[4]);
                        $suggestions.parent().next().val(data[4]);
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
                            var list = JSON.stringify(page.fn.formatTableData());
                            var billId = $("#billId").val()
                            $.ajax({
                                type : 'post',
                                url : '/center/enquiry/submit?billId='+billId,
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
                        pass: true,
                        serialize: []
                    };
                    this.$tbody.find('tr').each(function() {
                        var
                                $name     = $(this).find('.ipt[name="commodityName"]'),
                                name      = $.trim($name.val()),
                                $standard = $(this).find('.ipt[name="specs"]'),
                                standard  = $.trim($standard.val()),
                                $level    = $(this).find('.ipt[name="level"]'),
                                level     = $.trim($level.val()),
                                $origin   = $(this).find('.ipt[name="origin"]'),
                                origin    = $.trim($origin.val()),
                                $amount   = $(this).find('.ipt[name="amount"]'),
                                amount    = $.trim($amount.val()),
                                $price    = $(this).find('.ipt[name="expectPrice"]'),
                                price     = $.trim($price.val()),
                                $date     = $(this).find('.ipt[name="expectDate"]'),
                                date      = $.trim($date.val());

                        if (name) {
                            $name.nextAll('.error').html('').hide();
                        } else {
                            $name.nextAll('.error').html('此处不可空白').show();
                            result.pass = false;
                        }

                        if (standard) {
                            $standard.nextAll('.error').css('display','none').html('');
                        } else {
                            $standard.nextAll('.error').css('display','block').html('此处不可空白');
                            result.pass = false;
                        }

                        if (level) {
                            $level.nextAll('.error').css('display','none').html('');
                        } else {
                            $level.nextAll('.error').css('display','block').html('此处不可空白');
                            result.pass = false;
                        }

                        if (origin) {
                            $origin.nextAll('.error').css('display','none').html('');
                        } else {
                            $origin.nextAll('.error').css('display','block').html('此处不可空白');
                            result.pass = false;
                        }



                        if (amount) {
                            $amount.nextAll('.error').css('display','none').html('');
                        } else {
                            $amount.nextAll('.error').css('display','block').html('此处不可空白');
                            result.pass = false;
                        }

                        if (date) {
                            $date.nextAll('.error').css('display','none').html('');
                        } else {
                            $date.nextAll('.error').css('display','block').html('此处不可空白');
                            result.pass = false;
                        }

                        if (result.pass) {
                            result.serialize.push({
                                name: name,
                                standard: standard,
                                level: level,
                                origin: origin,
                                amount: amount,
                                price: price,
                                date: date
                            })
                        } else {
                            return false;
                        }
                    })
                    return result;
                },
                formatTableData: function () {
                    var tableObj = $('#enquiryForm tbody tr').map(function (i) {
                        var row = {};
                        $(this).find('input').each(function (i) {
                            row[$(this).attr("name")] = $(this).val();
                        });
                        return row;
                    }).get();

                    return tableObj;
                },
                response:function(result){
                    if(result.status=="y"){
                        $.notify({
                            type: 'success',
                            title: '提交成功',
                            text: result.info
                        })
                        this.$tbody.empty().html(this.modal).find('.remove').remove();
                        $("#billId").val("");
                    }else{
                        $.notify({
                            type: 'error',
                            title: '提交错误',
                            text: result.info
                        })
                    }
                },
                formatDate: function(date) {
                    return date ? date.split(' ')[0] : '';
                }

    		}
    	}
    	$(function() {
    		page.fn.init();
    	})
    </script>
</body>
</html>