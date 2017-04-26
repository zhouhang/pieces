<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
    <title>新建询价-boss-上工好药</title>
</head>

<body>
<!-- fa-floor start -->
<div class="create-enquiry2 chart">
    <div class="thead">
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
        </table>
    </div>
    <form action="" id="myform">
        <table>
            <thead class="hide">
            <tr>
                <th width="150">商品名称</th>
                <th width="150">片型</th>
                <th>规格等级</th>
                <th width="120">产地</th>
                <th width="120">单价（元/公斤）</th>
                <th width="120">操作</th>
            </tr>
            </thead>
            <tbody id="tbody"></tbody>
        </table>
        <div class="tfoot">
            <button type="submit" class="btn btn-red" id="submit">报价</button>
            <span>报价有效期至：</span>
            <input type="text" name="date" id="expireDate" value="${expireDate!}" class="ipt" autocomplete="off">
        </div>
    </form>
</div>
<!-- 输入框联想 start -->
<div class="suggestions" id="suggestions" style="width:860px;">
    <div class="hd">
        <div class="group">
            <span class="w1">商品名称</span><span class="w2">片型</span><span class="w3">规格等级</span><span class="w4">产地</span><span class="w5">当前价格</span><span class="w6">上次成交价格</span>
        </div>
    </div>
    <div class="bd"></div>
</div><!-- 输入框联想 end -->

<script type="temp" id="jmodal">
        <tr>
            <td width="150">
                <div class="inner pr">
                    <input type="text" style="display: none" data-rule="required;length[1~20]" name="commodityId<#if commoditys?exists>${commoditys?size +1}</#if>" autocomplete="off">
                    <input type="text"  class="ipt name" name="commodityName" autocomplete="off">
                </div>
            </td>
            <td width="150"><div class="inner pr" name="spec"></div></td>
            <td><div class="inner pr" name="level"></div></td>
            <td width="120"><div class="inner pr" name="originof"></div></td>
            <td width="120"><input type="text" class="ipt price" name="myPrice" value="" autocomplete="off"><span class="error"></span></td>
            <td width="120"><a href="javascript:;" class="remove c-red">删除</a></td>
        </tr>
    </script>


    <!-- footer start -->
<script src="${urls.getForLookupPath('/js/jquery.form.js')}"></script>
<script src="js/smartZoom.js"></script>
<script src="js/laydate/laydate.js"></script>
<script src="js/jquery.pagination.min.js"></script>
<script src="${urls.getForLookupPath('/js/common.js')}"></script>
<script>
    var enquiryPage = {
        v: {},
        fn: {
            init: function() {
                this.myformEvent();
                this.pic();
            },
            pic: function() {
                var imgList = [],
                        templete = '<div class="pic"> \n <div class="img"><img src="images/blank.gif"></div> \n <div class="thumb"></div></div>',
                        pages = [],
                        $pic, img;
            <#if anon.files?exists && anon.files?size gt 0 >
                <#list anon.files as file>
                    imgList.push('${file.attachmentUrl!}');
                </#list>
            </#if>


                // 缩放图片
                var zoomImg = function() {
                    $(img).smartZoom('destroy');
                    $(img).smartZoom();
                }

                if (imgList.length === 0) {
                    return this;
                }

                $('body').append(templete);
                $pic = $('.pic');
                img = $pic.find('.img img')[0];
                // 显示第一张图片
                img.src = imgList[0];
                // 生成分页
                $.each(imgList, function(i, url) {
                    pages.push('<span><img src="', url, '" /></span>');
                })
                $pic.find('.thumb').html(pages.join('')).find('span:eq(0)').addClass('curr');

                // 切换图片
                $pic.on('click', '.thumb img', function() {
                    img.src = this.src;
                    zoomImg();
                    $(this).parent().addClass('curr').siblings().removeClass('curr');
                })

                zoomImg();
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
            myformEvent: function() {
                var $body        = $('body');
                var $suggestions = $('#suggestions');
                var $tbody       = $('#tbody');
                var $ipt         = $tbody.find('.ipt:first');
                var $date        = $('#expireDate');
                var html         = $('#jmodal').html();
                var self         = this;
                var count        = 13;
                var model        = [];

                while (count-- > 0) {
                    model.push(html);
                }
                $tbody.append(model.join(''));

                // 单价
                $tbody.on('keyup', '.price', function(e) {
                    var val = this.value;
                    if (!/^\d+\.?\d*$/.test(val)) {
                        val = Math.abs(parseFloat(val));
                        this.value = isNaN(val) ? '' : val;
                    }
                    if (e.keyCode === 13) {
                        $(this).closest('tr').next().find('.price').focus();
                    }
                })

                var checked = function() {
                    var pass = false;
                    $tbody.find('.name').each(function() {
                        if (this.value != '') {
                            pass = true;
                            return false;
                        }
                    })

                    !pass && $.notify({
                        title: '最少填写一条报价',
                        type: 'error',
                        delay: 2e3
                    })

                    return pass;
                }


                // 提交
                $('#submit').on('click', function() {
                    var val = $date.val(),
                            pass = checked();

                    if (pass && !val) {
                        $.notify({
                            title: '请填写报价有效期',
                            type: 'error',
                            delay: 2e3
                        })
                        $date.focus();
                        pass = false;
                    } else {
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
                    }
                    return false;
                })

                // 商品名联想
                $tbody.on({
                    'click': function(event) {
                        event.stopPropagation();
                    },
                    'focus': function() {
                        // $(this).siblings('.error1').hide();
                        $(this).after($suggestions);
                        $suggestions.find('.group').length > 1 && $suggestions.show();
                    },
                    'input': function() {
                        self.getKeywords(this.value);
                    }
                }, '.name');

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
                    $suggestions.closest('td').next().find('.inner').html(data[2]).trigger('focus').end()
                            .closest('td').next().find('.inner').html(data[3]).trigger('focus').end()
                            .closest('td').next().find('.inner').html(data[4]).trigger('focus').end()
                            .closest('td').next().find("input[name=myPrice]").val(data[5]=="undefined"?0:data[5]).trigger('focus')
                            .closest('tr').find('.remove').show();
                    $suggestions.hide();

                    var length = 0;
                    $('.name').each(function() {
                        length += this.value == '' ? 1 : 0;
                    })
                    if (length < 4) {
                        $tbody.append(model.join(''));
                    }
                })

                // 删除一行
                $tbody.on('click', '.remove', function() {
                    $(this).closest('tr').remove();
                })

                // 有效期
                laydate({
                    elem: '#expireDate',
                    istime: true,
                    choose: function(datas){
                    }
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
                    var val = item[i].id + '|-|' +  item[i].name + '|-|' + item[i].spec + '|-|' + item[i].level + '|-|' + item[i].originOf+ '|-|' + item[i].guidePrice;
                    modal.push('<div class="group" data-val="', val, '">');
                    modal.push(     '<span class="w1">', item[i].name, '</span>');
                    modal.push(     '<span class="w2">', item[i].spec, '</span>');
                    modal.push(     '<span class="w3">', item[i].level, '</span>');
                    modal.push(     '<span class="w4">', item[i].originOf, '</span>');
                    modal.push(     '<span class="w4">', item[i].guidePrice?item[i].guidePrice:"-", '</span>');
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
            }
        }
    }
    $(function() {
        enquiryPage.fn.init();
    })
</script>
</body>
</html>