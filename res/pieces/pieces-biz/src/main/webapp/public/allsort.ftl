<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
        <title>全部商品分类-${baseSetting.title!}</title>
        <meta name="description" content="${baseSetting.intro!}" />
        <meta name="Keywords" content="${baseSetting.keyWord!}" />
</head>

<body>

    <#include "./inc/header.ftl"/>

    <div class="wrap">
        <div class="allsort">
            <div class="tab">
                <ul class="cf">
                    <#list categorys as category>
                        <li <#if category_index==0>class="current"</#if>>${category.name}</li>
                    </#list>
                </ul>
            </div>
            <div class="tabcont">
                <#list categorys as category>
                    <div class="group">
                        <#list category.categoryPinyin?keys as key>
                            <dl>
                                <dt>${key}</dt>
                                <dd>
                                    <#assign breeds=category.categoryPinyin>
                                    <#list breeds[key] as breed>
                                        <a href="commodity/index?breedId=${breed.id!}">${breed.name!}</a>
                                    </#list>
                                </dd>
                            </dl>
                            <#if key_index%2==1>
                                <div class="space"></div>
                            </#if>
                        </#list>
                    </div>
                </#list>

            </div>
        </div>
    </div>


    <#include "./inc/helper.ftl"/>

    <#include "./inc/footer.ftl"/>

    <script>
    var _global = {
        fn: {
            init: function() {
                this.tab();
            },
            tab: function() {
                var $tabgroups = $('.tabcont').find('.group'),
                    $tabs = $('.tab').find('li');
                $('.tab').on('click', 'li', function() {
                    var idx = $(this).index();
                    $(this).addClass('current').siblings().removeClass('current');
                    $tabgroups.eq(idx).show().siblings().hide();
                })

                $tabs.eq(0).addClass('current');
                $tabgroups.eq(0).show();
            }
        }
    }
    $(function(){
        _global.fn.init();
    })
    </script>
</body>
</html>