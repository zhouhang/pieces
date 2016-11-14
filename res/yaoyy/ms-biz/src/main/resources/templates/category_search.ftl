<!DOCTYPE html>
<html lang="en">
<head>
    <title>搜索-药优优</title>
    <#include "./common/meta.ftl"/>
</head>
<body class="ui-body-nofoot body-gray">
<header class="ui-header">
    <div class="title">搜索</div>
    <div class="abs-l mid">
        <a href="javascript:history.back();" class="fa fa-back"></a>
    </div>
</header>
<div class="ui-content">
    <div class="ui-search">
        <div class="form">
            <form action="category/list">
                <input type="text" name="variety" id="keyword" class="ipt" placeholder="请输入原药材品种名称" autocomplete="off">
                <button type="submit" id="submit" class="fa fa-search submit mid"></button>
            </form>
            <div class="suggest">
                <div class="suggest-panel"></div>
                <div class="suggest-close">关闭</div>
            </div>
        </div>
    </div>

    <div class="search-his" id="searchHistory"></div>
</div>

<#include "./common/footer.ftl"/>
<script>

    var _global = {
        v: {
            searchHistoryName: 'searchhistory',
            searchCategoryUrl:'category/search'
        },
        fn: {
            init: function() {
                this.showHistory();
                this.searchForm();
                this.autocomplete();
            },
            showHistory: function() {
                var self = this;
                var html = [];
                var his = localStorage.getItem(_global.v.searchHistoryName);
                var h = [];

                if (his) {
                    html.push('<ul>');
                    h = his.split(',');
                    for (var i in h) {
                        html.push('<li><i class="fa fa-clock"></i>');
                        html.push('<span>' + h[i] + "</span>");
                        html.push("</li>");
                    }
                    html.push('<li class="clear">清除搜索历史</li>');
                    html.push("</ul>");
                    self.historyBind();
                }
                $('#searchHistory').html(html.join(''));
            },
            historyBind: function() {
                var self = this,
                        $searchHistory = $('#searchHistory');
                // 清空搜索历史
                $searchHistory.on('click', '.clear', function() {
                    if (confirm('确定要清空吗?')) {
                        self.clearHistory();
                        $searchHistory.html('');
                    }
                    return false;
                })

                // keyword
                $searchHistory.on('click', 'li', function() {
                    $('#keyword').val($(this).find('span').html());
                })
            },
            // 添加一条历史记录
            addHistory: function(val) {
                var his = localStorage.getItem(_global.v.searchHistoryName);
                var h = his ? his.split(',') : [];
                var maxSize = 5; // 历史记录最大限制

                if (his && his.indexOf(val) >= 0) {
                    return;
                }
                h.unshift(val);
                // 最多保存5条历史记录
                h.length > maxSize && h.splice(maxSize - 1, h.length - maxSize);
                _YYY.localstorage.set(_global.v.searchHistoryName, h.join(','));
            },
            clearHistory: function() {
                _YYY.localstorage.remove(_global.v.searchHistoryName);
            },
            searchForm: function() {
                var self = this;
                $('#submit').on('click', function() {
                    var val = $.trim($('#keyword').val());
                    if (val) {
                        self.addHistory(val);
                    } else {

                    }
                })
            },
            autocomplete: function() {
                var t,
                        self = this,
                        $suggestions = $('.suggest');
                var search = function() {
                    $.ajax({
                        url: _global.v.searchCategoryUrl,
                        type:"POST",
                        data:{variety:$('#keyword').val()},
                        success: function(data) {
                            var html = [];
                            $.each(data.data, function(i, item) {
                                html.push('<a href="category/list/?variety=' ,item.variety ,'">', item.variety, '</a>');
                            })
                            $suggestions.show().find('.suggest-panel').html(html.join(''));
                        }
                    })
                }
                var _s = function() {
                    t && clearTimeout(t);
                    t = setTimeout(function() {
                        search();
                    }, 300);
                }
                $('#keyword').on('input', _s);

                // 添加历史记录
                $suggestions.on('click', 'a', function() {
                    self.addHistory($(this).html());
                })

                // 关闭
                $suggestions.on('click', '.suggest-close', function() {
                    $suggestions.hide();
                })
            }





        }
    }

    $(function(){
        _global.fn.init();

    });

</script>
</body>
</html>