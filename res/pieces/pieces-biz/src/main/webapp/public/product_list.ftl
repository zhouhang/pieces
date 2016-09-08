<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
    <title>商品列表-饮片B2B</title>
</head>

<body>
	<#include "./inc/header.ftl"/>
    <div class="main-body">
        <div class="wrap">
            <div class="sitemap">
                <a href="/commodity/index">商品分类</a>
                <#if (parent??&&parent.name??)>
                <em>&gt;</em>
                <a href="/commodity/index?categoryId=${parent.id }">${parent.name }</a>
                </#if>
                <#if (category??&&category.name??)>
                <em>&gt;</em>
                <span>${category.name }</span>
                </#if>
            </div>
			
			<#if (lxCommodity??&&lxCommodity?size>0)>
            <div class="fa-filter">
                <dl>
                    <dt>商品名称：</dt>

					<dd class="bd" id="screen">
                        <a href="/commodity/index?breedId=${category.id }">全部</a>
                    	<#list lxCommodity as lc>
                        	<a href="javascript:void(0)" data-name="${lc.name }" onclick="productPage.fn.lcClick(this);">${lc.name }</a>
                        </#list>
                    </dd>
                </dl>
            </div>
            </#if>

            <div class="fa-pro-list">
            <form id="myform" action="/commodity/index">
                <table>
                    <thead class="tc">
                        <tr>
                            <th width="150"></th>
                            <th width="260">商品信息</th>
                            <th width="160">规格等级</th>
                            <th width="110">切制规格</th>
                            <th width="120">原药产地</th>
                            <th width="">执行标准</th>
                            <th width="140">操作</th>
                        </tr>
                    </thead>
                    <tfoot></tfoot>
                    <tbody>
                    <#if (pageInfo??&&pageInfo.list?size>0)>
	                    <#list pageInfo.list as commodity>
	                        <tr>
	                            <td><a href="/commodity/${commodity.id }"><img class="lazyload" src="/images/blank.gif" data-original="${commodity.pictureUrl }" width="130" height="130" alt=""></a></td>
	                            <td class="tl">                                
	                                <div class="desc">
	                                    <h3><a href="/commodity/${commodity.id}">${commodity.name }</a></h3>
	                                    <p>${commodity.exterior }</p>
	                                </div>
	                            </td>
	                            <td>${commodity.levelName}</td>
                                <td>${commodity.specName}</td>
	                            <td>${commodity.originOfName}</td>
	                            <td>${commodity.executiveStandard}</td>
	                            <td><a href="/center/enquiry/index?commodityId=${commodity.id!}" class="btn btn-white btn-quote j_pop_login">立即询价</a></td>
	                        </tr>
	                    </#list>
	                </#if>
                    </tbody>
                </table>
                </form>
            </div>
            
            <#if !pageInfo??||(pageInfo??&&pageInfo.list?size == 0)>
	            <div class="fa-pro-empty">
	                <p class="tc">对不起，找不到您需要的商品，建议您：重新选择筛选条件。</p>
	            </div>
			</#if>
			
			<#if pageInfo?? && pageInfo.total &gt; 0>
            	<@p.pager inPageNo=pageInfo.pageNum-1 pageSize=pageInfo.pageSize recordCount=pageInfo.total toURL="/commodity/index?${commodityParam}"/>
        	</#if>
        </div>
    </div>

    <#include "./inc/helper.ftl"/>
    <#include "./inc/footer.ftl"/>
    <script src="/js/jquery.form.js"></script>
    <script src="/js/layer/layer.js"></script>
    <script>
    var productPage = {
        v: {
        	url : "/center/enquiry/index"
        },
        fn: {
        	init: function(){
				this.checkboxEvent();
                this.dorpDown();
				
        		$(".fa-times").click(function(){
        			var name = $(this).parent().data('name');
        			$(".cbx").filter(':checked').each(function() {
        				var checkName = $(this).data('name');
        				if(name === checkName){
        					this.checked = false;
        				}
                    });
        			$("#myform")[0].submit();
                })
        		
        		$("#reset").click(function(){
        			//console.log($('#myfrom th .cbx').length)
                	$('.fa-pro-list th .cbx').prop('checked', false);
        			$('#screen').html("");
        			$("#myform")[0].submit();
                	//return false;
                })
        	},
        	lcClick: function(obj) {
        		var $this = $(obj);
        		window.location.href = "/commodity/index?eqName=" + $this.attr('data-name').replace("%",'%25');
        	},
            // 全选事件
            checkboxEvent: function() {
                var _self = this;
                $('.drop-dowm dt .cbx').each(function() {
                    var $this = $(this),
                        $cbxList = $this.closest('dt').next().find('.cbx')

                    _self.checkAll($this, $cbxList);
                })
                return this;
            },
            // 全选
            checkAll: function($checkAll, $checkList) {
                var amount = $checkList.length, // 总个数
                    total  = 0; // 当前选中的个数
                
                // 单选
                $checkList.on("click", function() {
                    total += this.checked ? 1 : -1;
                    $checkAll.prop('checked', total === amount);
                }).each(function() {
                    // 统计已选个数
                    total += this.checked ? 1 : 0;
                });

                // 全选
                $checkAll.on("click", function() {
                    var icheck = this.checked;
                    $checkList.each(function() {
                        this.checked = icheck;
                    });
                    
                    total = icheck ? amount : 0;
                }).each(function() {
                    this.checked = total !== 0 && total === amount;
                });

                // 确定按钮
                $checkAll.parent().next().on('click', function() {
                    //var val = [];
                    //var modal = [];
                    //$checkList.filter(':checked').each(function() {                        
                    //    var name = $(this).data('name'); 
                    //    modal.push('<a href="javascript:;">', name, '<i class="fa fa-times"></i></a>');
                    //    val.push(name);
                    //});
					var val = [];
                    $checkList.filter(':checked').each(function() {
                        val.push(this.value);
                    });

                    // 关闭下拉
                    $(this).closest('.bd').hide();
                    $("#myform")[0].submit();
                    //val.lenth !== total && $('#screen').append(modal.join()) && this.filer(val);                    
                    return false;
                })
            },
            // 下拉
            dorpDown: function() {
                var $dorpDownList = $('.drop-dowm .bd');
                // 打开当前下拉并关闭其他下拉
                $('.drop-dowm').on('click', '.hd', function() {
                    $dorpDownList.hide();
                    $(this).next().show();
                    return false;
                })
                // 点击页面关闭下拉
                $(document).on('click', function() {
                    $dorpDownList.hide();
                })
                // 点击下拉区域阻止关闭
                $(document).on('click', '.drop-dowm', function(e) {
                    if (e.stopPropagation) {
                        e.stopPropagation();
                    }
                    else {
                        e.cancelBubble = true;
                    }
                })
                return this;
			}
        }
    }
    $(function() {

        productPage.fn.init();
    })
    </script>
</body>
</html>