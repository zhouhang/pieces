<!DOCTYPE html>
<html lang="en">
<head>
    <title>分类管理-boss-上工好药</title>
<#include "./common/meta.ftl"/>
</head>
<body class="wrapper">
<#include "./common/header.ftl"/>
<#include "./common/aside.ftl"/>
<div class="content">
    <div class="breadcrumb">
        <ul>
            <li>商品管理</li>
            <li>商品列表</li>
        </ul>
    </div>

    <div class="box">
        <div class="tools">
            <div class="filter">
                <form action="">
                    <ul>
                        <li><label>品种：</label><input type="text" class="ipt" placeholder="请输入"></li>
                        <li><label>商品名称：</label><input type="text" class="ipt" placeholder="商品名称"></li>
                        <li>
                            <label>上/下架：</label>
                            <select name="" class="slt">
                                <option value="">全部</option>
                                <option value="">上架</option>
                                <option value="">下架</option>
                            </select>
                        </li>
                        <li>
                            <button class="ubtn ubtn-blue">搜索</button>
                        </li>
                    </ul>
                </form>
            </div>

            <div class="action-add">
                <button class="ubtn ubtn-blue" id="jaddNewCat">新建商品</button>
            </div>
            <div class="action-length">
                <span>显示</span>
                <select name="" class="slt">
                    <option value="10">10</option>
                    <option value="25">25</option>
                    <option value="50">50</option>
                    <option value="100">100</option>
                </select>
                <span>条结果</span>
            </div>
        </div>

        <div class="table">
            <table>
                <thead>
                <tr>
                    <th><input type="checkbox"></th>
                    <th>商品名称</th>
                    <th>品种</th>
                    <th>标题</th>
                    <th>规格等级</th>
                    <th>产地</th>
                    <th>排序</th>
                    <th width="150">创建时间</th>
                    <th width="230" class="tc">操作</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td><input type="checkbox"></td>
                    <td>三七 <em class="c-red">【量大价优】</em></td>
                    <td>三七</td>
                    <td>三七  120头</td>
                    <td>120头</td>
                    <td>河南</td>
                    <td>100</td>
                    <td>2016-05-18 15:22</td>
                    <td class="tc">
                        <a href="goods_edit.html" class="ubtn ubtn-blue jedit">编辑</a>
                        <a href="javascript:;" class="ubtn ubtn-gray jdel">删除</a>
                    </td>
                </tr>
                <tr>
                    <td><input type="checkbox"></td>
                    <td>三七 <em class="c-red">【量大价优】</em></td>
                    <td>三七</td>
                    <td>三七  100头</td>
                    <td>100头</td>
                    <td>河南</td>
                    <td>20</td>
                    <td>2016-05-18 15:22</td>
                    <td class="tc">
                        <a href="goods_edit.html" class="ubtn ubtn-blue jedit">编辑</a>
                        <a href="javascript:;" class="ubtn ubtn-gray jdel">删除</a>
                    </td>
                </tr>
                <tr>
                    <td><input type="checkbox"></td>
                    <td>白芍</td>
                    <td>白芍</td>
                    <td>亳州白芍 选货 过8-12号筛</td>
                    <td>过8号筛</td>
                    <td>亳州</td>
                    <td>30</td>
                    <td>2016-05-18 15:22</td>
                    <td class="tc">
                        <a href="goods_edit.html" class="ubtn ubtn-blue jedit">编辑</a>
                        <a href="javascript:;" class="ubtn ubtn-gray jdel">删除</a>
                    </td>
                </tr>
                <tr class="gray">
                    <td><input type="checkbox"></td>
                    <td>黄芩</td>
                    <td>黄芩</td>
                    <td>甘肃定西黄芪 杂质少 干度好   长期供应</td>
                    <td>过10号筛</td>
                    <td>甘肃</td>
                    <td>10</td>
                    <td>2016-05-18 15:22</td>
                    <td class="tc">
                        <a href="goods_edit.html" class="ubtn ubtn-blue jedit">编辑</a>
                        <a href="javascript:;" class="ubtn ubtn-gray jdel">删除</a>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>

        <div class="pagination">
            <div class="pages">
                <a href="#" class="text">上页</a>
                <span class="curr">1</span>
                <a href="#">2</a>
                <a href="#">3</a>
                <i>...</i>
                <a href="#">8</a>
                <a href="#">9</a>
                <a href="#" class="text">下页</a>
            </div>
            <div class="info">
                显示第 1 至 10 项结果，共 <em id="pageSize">72</em> 项
            </div>
        </div>
    </div>
</div>

<#include "./common/footer.ftl"/>
<script src="assets/plugins/layer/layer.js"></script>

<script>
    var _global = {
        v: {
            deleteUrl: ''
        },
        fn: {
            init: function() {
                this.bindEvent();
            },
            bindEvent: function() {
                var $table = $('.table'),
                        $cbx = $table.find('td input:checkbox'),
                        $checkAll = $table.find('th input:checkbox'),
                        count = $cbx.length;

                // 删除
                $table.on('click', '.jdel', function() {
                    var url = _global.v.deleteUrl + $(this).attr('href');
                    layer.confirm('确认删除此品种？', {icon: 3, title: '提示'}, function (index) {
                        $.get(url, function (data) {
                            if (data.status == "y") {
                                window.location.reload();
                            }
                        }, "json");
                        layer.close(index);
                    });
                    return false; // 阻止链接跳转
                })

                // 全选
                $checkAll.on('click', function() {
                    var isChecked = this.checked;
                    $cbx.each(function() {
                        this.checked = isChecked;
                    })
                })
                // 单选
                $cbx.on('click', function() {
                    var _count = 0;
                    $cbx.each(function() {
                        _count += this.checked ? 1 : 0;
                    })
                    $checkAll.prop('checked', _count === count);
                })
            }
        }
    }

    $(function() {
        _global.fn.init();
    })
</script>

</body>
</html>