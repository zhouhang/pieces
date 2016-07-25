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
                <#if (commodity??&&commodity.breedName??)>
                <em>&gt;</em>
                <span>${commodity.breedName }</span>
                </#if>
            </div>
			
			<#if (screens?size>0)>
            <div class="fa-filter">
                <dl>
                    <dt>你已筛选：</dt>
                    
                    <dd class="extra">
                        <a id="reset" class="btn btn-gray" href="javascript:;">重置筛选条件</a>
                    </dd>
					<dd class="bd" id="screen">
                    	<#list screens as screen>
                        	<a data-name="${screen }" href="javascript:;">${screen }<i class='fa fa-times'></i></a>
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
                            <th width="240">商品信息</th>
                            <th>
                            <input type="hidden" name="categoryId"  value="${commodity.categoryId }">
                            <input type="hidden" name="breedId"  value="${commodity.breedId }">
                                <div class="drop-dowm">
                                    <div class="hd">
                                        <span>切割规格</span><i class="fa fa-chevron-down"></i>
                                    </div>
                                    <div class="bd">
                                        <dl>
                                            <dt>
                                                <label><input type="checkbox" name="" id="" class="cbx">全选</label>
                                                <button class="btn btn-gray">确定</button>
                                            </dt>
                                            <dd>
                                            	<#list specifications as specifications>
                                                	<label><input type="checkbox" name="specNameStr" data-name="${specifications.name }"  class="cbx" value="${specifications.id }" <#if specifications.checked>checked="checked"</#if>>${specifications.name }</label>
                                                </#list>
                                            </dd>
                                        </dl>
                                    </div>
                                </div>
                            </th>
                            <th>
                                <div class="drop-dowm">
                                    <div class="hd">
                                        <span>等级</span><i class="fa fa-chevron-down"></i>
                                    </div>
                                    <div class="bd">
                                        <dl>
                                            <dt>
                                                <label><input type="checkbox" name="" id="" class="cbx">全选</label>
                                                <button class="btn btn-gray">确定</button>
                                            </dt>
                                            <dd>
                                            	<#list levels as level>
                                                	<label><input type="checkbox" name="levelNameStr" data-name="${level.name }" class="cbx" value="${level.id }" <#if level.checked>checked="checked"</#if>>${level.name }</label>
                                                </#list>
                                            </dd>
                                        </dl>
                                    </div>
                                </div>
                            </th>
                            <th>
                                <div class="drop-dowm">
                                    <div class="hd">
                                        <span>原药产地</span><i class="fa fa-chevron-down"></i>
                                    </div>
                                    <div class="bd">
                                        <dl>
                                            <dt>
                                                <label><input type="checkbox" name="" id="" class="cbx">全选</label>
                                                <button class="btn btn-gray">确定</button>
                                            </dt>
                                            <dd>
                                            	<#list place as place>
                                                	<label><input type="checkbox" name="originOfNameStr" data-name="${place.name }" class="cbx" value="${place.id }" <#if place.checked>checked="checked"</#if>>${place.name }</label>
                                                </#list>
                                            </dd>
                                        </dl>
                                    </div>
                                </div>
                            </th>
                            <th width="190">
                                <div class="drop-dowm">
                                    <div class="hd">
                                        <span>执行标准</span><i class="fa fa-chevron-down"></i>
                                    </div>
                                    <div class="bd">
                                        <dl>
                                            <dt>
                                                <label><input type="checkbox" name="" id="" class="cbx">全选</label>
                                                <button class="btn btn-gray">确定</button>
                                            </dt>
                                            <dd>
                                                <#list standards as standard>
                                                	<label><input type="checkbox" name="executiveStandardNameStr" data-name="${standard.executiveStandard }" class="cbx" value="${standard.executiveStandard }"  <#if standard.checked>checked="checked"</#if>>${standard.executiveStandard }</label>
                                                </#list>
                                            </dd>
                                        </dl>
                                    </div>
                                </div>
                            </th>
                            <th width="180">
                                <div class="drop-dowm">
                                    <div class="hd">
                                        <span>生产厂家</span><i class="fa fa-chevron-down"></i>
                                    </div>
                                    <div class="bd">
                                        <dl>
                                            <dt>
                                                <label><input type="checkbox" name="" id="" class="cbx">全选</label>
                                                <button class="btn btn-gray">确定</button>
                                            </dt>
                                            <dd>
                                                <#list factorys as factory>
                                                	<label><input type="checkbox" name="factoryStr" class="cbx" data-name="${factory.factory }" value="${factory.factory }" <#if factory.checked>checked="checked"</#if>>${factory.factory }</label>
                                                </#list>
                                            </dd>
                                        </dl>
                                    </div>
                                </div>
                            </th>
                            <th width="140">操作</th>
                        </tr>
                    </thead>
                    <tfoot></tfoot>
                    <tbody>
                    <#list pageInfo.list as commodity>
                        <tr>
                            <td><a href="/commodity/${commodity.id }"><img src="${commodity.pictureUrl }" width="130" height="130" alt=""></a></td>
                            <td class="tl">                                
                                <div class="desc">
                                    <h3><a href="/commodity/${commodity.id }">${commodity.name }</a></h3>
                                    <p>${commodity.exterior }</p>
                                </div>
                            </td>
                            <td>${commodity.specName }</td>
                            <td>${commodity.levelName }</td>
                            <td>${commodity.originOfName }</td>
                            <td class="tl">${commodity.executiveStandard }</td>
                            <td>${commodity.factory }</td>
                            <td><a class="btn btn-white btn-quote">立即询价</a></td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
                </form>
            </div>

            <@p.pager inPageNo=pageInfo.pageNum-1 pageSize=pageInfo.pageSize recordCount=pageInfo.total toURL="/commodity/index?${commodityParam}"/>
        </div>
    </div>

    <#include "./inc/helper.ftl"/>
    <#include "./inc/footer.ftl"/>
    <script src="/js/jquery.form.js"></script>
    <script src="/js/layer/layer.js"></script>
    <script>
    var productPage = {
        v: {
			url : '/center/enquiry/index'
        },
        fn: {
        	init: function(){
				this.checkboxEvent();
                this.dorpDown();
                this.quote();
				
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
			},
            // 询价
            quote: function() {
                $('#myform').on('click', '.btn-quote', function() {
                	$.ajax({
			            url: "/pop",
			            type: "POST",
			            dataType : "json",
			            success: function(data){
			            	var status = data.status;
			            	if(status === 'y'){
			            		window.location.href = productPage.v.url;
			            	}else{
			            		layer.open({
			                        type: 2,
			                        title: '账户登录',
			                        area: ['360px', '360px'],
			                        content: ['/popLogin', 'no']
			                    });
			            	}
			            }
			        });
                	return false;
                })
            },
            logined: function() {
                layer.closeAll();
                window.location.href = productPage.v.url;
            }
        }
    }
    $(function() {

        productPage.fn.init();
    })
    </script>
</body>
</html>