<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
    <title>选择客户-boss-上工好药</title>
</head>

<body>
    <#include "./inc/header.ftl">

    <!-- fa-floor start -->
    <div class="create-order create-enquiry">
        <div class="wrap">
            <div class="title">
                <h3><i class="fa fa-chevron-right"></i>为${user.userName}报价</h3>
                <div class="extra">
                    <button type="button" class="btn btn-gray" onclick="javascript:history.go(-1);">取消</button>
                    <button type="button" class="btn btn-blue" id="importExcel">导入询价品种</button>
                    <button type="submit" class="btn btn-red" id="submit">报价</button>
                </div>
            </div>

            <div class="main">
                <div class="chart-info">
                    <form action="" id="myform" class="chart tc">
                        <table>
                            <thead>
                            <tr>
                                <th width="150">商品名称</th>
                                <th width="150">片型</th>
                                <th>规格等级</th>
                                <th width="120">产地</th>
                                <th width="120">单价（元/公斤）</th>
                                <th width="120">操作</th>
                            </tr>
                            </thead>
                            <tfoot>
                            <tr>
                                <td colspan="6">
                                    <a href="javascript:;" class="add">+ 添加一行</a>
                                </td>
                            </tr>
                            </tfoot>
                            <tbody>
                            <#if commoditys?exists>
                                <#list commoditys as commodity >
                                <tr>
                                    <td><div class="ipt-wrap pr"><input type="text" style="display: none" data-rule="required;length[1~20]" name="commodityId${commodity_index}" autocomplete="off"><input type="text" class="ipt ipt-name" name="commodityName" value="${commodity.commodityName}" autocomplete="off"></div></td>
                                    <td><div class="ipt-wrap" name="spec"></div></td>
                                    <td><div class="ipt-wrap" name="level"></div></td>
                                    <td><div class="ipt-wrap" name="originof"></div></td>
                                    <td><input type="text" class="ipt price" name="myPrice" value="" autocomplete="off"><span class="error"></span></td>
                                    <td><a href="javascript:;" class="remove c-red">删除</a></td>
                                </tr>
                                </#list>
                            </#if>
                            <tr>
                                <td><div class="ipt-wrap pr"><input type="text" style="display: none" data-rule="required;length[1~20]" name="commodityId<#if commoditys?exists>${commoditys?size +1}</#if>" autocomplete="off"><input type="text" class="ipt ipt-name" name="commodityName" autocomplete="off"></div></td>
                                <td><div class="ipt-wrap" name="spec"></div></td>
                                <td><div class="ipt-wrap" name="level"></div></td>
                                <td><div class="ipt-wrap" name="originof"></div></td>
                                <td><input type="text" class="ipt price" name="myPrice" value="" autocomplete="off"><span class="error"></span></td>
                                <td><a href="javascript:;" class="remove c-red">删除</a></td>
                            </tr>
                            </tbody>
                        </table>
                        <div class="ipt-tr">
                            <span>报价有效期至：</span>
                            <input type="text" class="ipt" id="expireDate" name="expireDate" value="${expireDate!}" autocomplete="off">
                        </div>
                    </form>
                </div>
            </div>
        </div><!-- fa-floor end -->
    </div>


    <!-- footer start -->
    <#include "./inc/footer.ftl"/>
    <!-- footer end -->
    <!-- 输入框联想 start -->
    <div class="suggestions" id="suggestions">
        <div class="hd">
            <div class="group">
                <span class="w1">商品名称</span><span class="w2">片型</span><span class="w3">规格等级</span><span class="w4">产地</span>
            </div>
        </div>
        <div class="bd"></div>
    </div><!-- 输入框联想 end -->

    <script src="${urls.getForLookupPath('/js/laydate/laydate.js')}"></script>
    <script src="/js/layer/layer.js"></script>
    <script src="/js/validator/jquery.validator.min.js?local=zh-CN"></script>
    <script src="/js/jquery.pagination.min.js"></script>
    <script>
        var enquiryPage = {
            v: {
                index:<#if commoditys?exists>${commoditys?size +10}<#else >1</#if>
            },
            fn: {
                init: function() {
                    this.myformEvent();
                    this.batch();
                    this.formValidate();
                    this.dateInit();
                },
                myformEvent: function() {
                    var $body        = $('body');
                    var $suggestions = $('#suggestions');
                    var $myform      = $('#myform');
                    var $tbody       = $myform.find('tbody');
                    var $ipt         = $tbody.find('.ipt:first');
                    var self         = this;

                    // 单价
                    $myform.on({
                        'keyup': function() {
                            var val = this.value;
                            if (!/^\d+\.?\d*$/.test(val)) {
                                val = Math.abs(parseFloat(val));
                                this.value = isNaN(val) ? '' : val;
                            }
                        },
                        'blur': function() {
                        }
                    }, '.price');

                    // 商品名联想
                    $myform.on({
                        'click': function(event) {
                            event.stopPropagation();
                        },
                        'focus': function() {
                            $(this).after($suggestions);
                            $suggestions.find('.group').length > 1 && $suggestions.show();
                            self.getKeywords(this.value);
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
                        var data = $(this).data('val').split('|-|');
                        $suggestions.parent().find("input[name*=commodityId]").val(data[0]);
                        $suggestions.parent().find("input[name=commodityName]").val(data[1]);
                        $suggestions.closest('td').next().find('.ipt-wrap').html(data[2]).trigger('focus').end()
                                .closest('td').next().find('.ipt-wrap').html(data[3]).trigger('focus').end()
                                .closest('td').next().find('.ipt-wrap').html(data[4]).trigger('focus');
                        $suggestions.hide();
                    })

                    // 新增一行
                    $myform.on('click', '.add', function() {
                        enquiryPage.v.index += 1;
                        var tr = '<tr><td><div class="ipt-wrap  pr"><input type="text" style="display: none" name="commodityId'+ enquiryPage.v.index +'" data-rule="required;length[1~20]" autocomplete="off"><input type="text" class="ipt ipt-name" name="commodityName" autocomplete="off"></div></td>'+
                            '<td><div class="ipt-wrap" name="spec"></div></td>'+
                            '<td><div class="ipt-wrap" name="level"></div></td>'+
                            '<td><div class="ipt-wrap" name="originof"></div></td>'+
                            '<td><input type="text" class="ipt price" name="myPrice" value="" autocomplete="off"></td>'+
                            '<td><a href="javascript:;" class="remove c-red">删除</a></td> </tr>';

                        $tbody.append(tr);
                    })

                    // 删除一行
                    $myform.on('click', '.remove', function() {
                        var $tr = $(this).closest('tr');

                        // 弹层确认删除
                        // layer.confirm('确认删除行？', {icon: 3, title:'提示'}, function(index){
                        $tr.remove();
                        // layer.close(index);
                        // });
                    })
                },
                // ajax 查询关键词
                getKeywords: function(keywords) {
                    var self = this;
                    var keywords = $.trim(keywords);
                    if (keywords === '') {
                        $('#suggestions').hide();
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
                        url: '/order/auto',
                        dataType: 'json',
                        data:{commodityName:keywords},
                        success: function(result) {
                            // 显示查询结果
                            if (result.status === 'y') {
                                if (result.data.length === 0) {
                                    $('#suggestions').show().find('.bd').empty().html('暂无此商品:)');
                                } else {
                                    self.toHtml(result.data, 0 ,7);
                                }
                            } else {
                                $('#suggestions').hide();
                            }
                        }
                    })
                },
                // 显示查询结果
                toHtml: function(item, page_index, pageSize) {
                    var modal = [],
                            maxPage = Math.min((page_index + 1) * pageSize, item.length),
                            hasPage = pageSize < item.length;

                    for (var i = page_index * pageSize; i < maxPage; i++) {
                        var val = item[i].id + '|-|' +  item[i].name + '|-|' + item[i].spec + '|-|' + item[i].level + '|-|' + item[i].originOf;
                        modal.push('<div class="group" data-val="', val, '">');
                        modal.push(     '<span class="w1">', item[i].name, '</span>');
                        modal.push(     '<span class="w2">', item[i].spec, '</span>');
                        modal.push(     '<span class="w3">', item[i].level, '</span>');
                        modal.push(     '<span class="w4">', item[i].originOf, '</span>');
                        modal.push('</div>');
                    }
                    hasPage && modal.push('<div class="jq-page"></div>');
                    $('#suggestions').show().find('.bd').empty().html(modal.join(''));
                    hasPage && this.showPage(item, page_index, pageSize);
                },
                showPage: function(item, page_index, pageSize) {
                    var self = this;
                    $('.jq-page').pagination(item.length, {
                        items_per_page: pageSize, //pageSize 每页显示数量
                        current_page: page_index, //默认pageIndex,0(默认),false(不加载)
                        num_edge_entries: 2, //1(任何情况下都显示第一页和最后一页),0(不显示)
                        callback: function(page_index) {
                            self.toHtml(item, page_index, pageSize);
                        }
                    })
                    $('.jq-page').prepend('<div class="p-size">总共' + item.length + '条记录</div>')
                },
                formValidate: function () {
//                    $('#myform').validator({
//                        // 自己指定消息容器位置
//                        target: function(elem){
//                            var $formitem = $(elem).closest('.error');
//                            return $formitem;
//                        }
//                    });

                    $('#myform').validator({
                        fields: {
                            expireDate: 'required'
                        }
                    })

                    $('#submit').on('click', function() {
                        if ($('#myform').isValid()) {
                            var data = enquiryPage.fn.formatTableData()
                            $.ajaxSetup({
                                headers: {
                                    'Content-Type': 'application/json;charset=utf-8'
                                }
                            });
                            $.post("/enquiry/save?userId=${user.id}", JSON.stringify(data), function (result) {
                                if(result.status == "y") {
                                    window.location.href=result.data;
                                }
                            }, "json")
                        } else {
                        }
                    })
                },
                dateInit: function () {
                    laydate({
                        elem: '#expireDate',
                        event: 'focus',
                        istime: true,
                        min: laydate.now(),
                        choose: function() {
                            $('#expireDate').trigger('validate')
                        }
                    })
                    $('#expireDate').on('focus', function() {
                        var posX = $(this).offset().left,
                                w = this.offsetWidth,
                                obj = document.getElementById('laydate_box');
                        obj.style.left = posX + w - obj.offsetWidth + 'px';
                    })
                },
                formatTableData: function () {
                    var tableObj = $('#myform tbody tr').map(function (i) {
                        var row = {};
                        row["commodityId"] = $($(this).find('input')[0]).val();
                        row["commodityName"] = $($(this).find('input')[1]).val();
                        row["myPrice"] = $($(this).find('input')[2]).val();

                        row["expireDate"] = $("#expireDate").val();
                        return row;
                    }).get();

                    return tableObj;
                },
                batch: function() {
                    $("#importExcel").click(function(){
                        layer.open({
                            moveType: 1,
                            area: ['600px'],
                            title: '导入报价',
                            content: '<form action="/enquiry/commodityName?userId=${user.id}" id="excelForm" method="post" enctype="multipart/form-data"><p>上传报价文件</p><label class="btn btn-file enquiry_btn"><span>上传文件</span><input type="file" name="file"></label><label class="filename"></label></form>',
                            btn: ['确定', '取消'],
                            yes: function(index) {
                                $("#excelForm").submit();
                                layer.close(index);
                            },
                            end: function() {
                                $('.enquiry_btn').off();
                            }
                        })

                        $('.enquiry_btn').on('change', 'input', function() {
                            $('.filename').html($(this).val());
                        })
                    })
                }
            }
        }
        $(function() {
            enquiryPage.fn.init();
        })
    </script>
</body>
</html>