<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>我的收藏</title>
    <#import 'macro.ftl' as macro>
    <script type="text/javascript" src="${RESOURCE_JS}/js/jquery-1.11.1.min.js"></script>
    <link href="${RESOURCE_CSS}/css/reset.css" type="text/css" rel="stylesheet" />
    <link href="${RESOURCE_CSS}/css/common.css" type="text/css" rel="stylesheet" />
    <link href="${RESOURCE_CSS}/css/list.css" type="text/css" rel="stylesheet" />
    <style>
		.zoomContainer{
			cursor:pointer;
		}
	</style>
    <link rel="stylesheet" type="text/css" href="${RESOURCE_JS}/js/datetimepicker/jquery.datetimepicker.css" />
</head>
<body>
<!--topper strat-->
<#include "common/detailHeader.ftl">
<!--topper over-->
<#import 'page.ftl' as tools>
<!-- 祥情页主体开始 -->
<div class="area-1200 clearfix">
  <div class="area-998 fl mt10">
      <!--分类展开折叠 strat-->
      <div class="class-list">
            <div class="box-1">
                <dl class="clearfix">
                    <dt>我的收藏：</dt>
                    <dd id="mybreed">
                          	<a  href=javascript:getCollectBy();>全部</a>
                    </dd>
                </dl>
            </div>
      </div>
      <!--分类展开折叠 over-->

  	<div class="box-1 mt10">
        <form id="queryCollectForm" action="/collect/queryMyCollect" method="post" name="queryCollectDto" onsubmit="return check();">
                <div class="price-search">
                      <span>价格范围(单价)：</span>
                      <input class="gray-price text1" type="text"  name="lowPrice" id="lowPrice" value="${(page.params.lowPrice)!'￥最低价格'}"  onfocus="if(this.value=='￥最低价格') this.value='';" onblur="if(this.value=='') this.value='￥最低价格';"/>
                       — <input class="gray-price text1" type="text"  name="highPrice" id="highPrice" value="${(page.params.highPrice)!'￥最高价格'}" onfocus="if(this.value=='￥最高价格') this.value='';" onblur="if(this.value=='') this.value='￥最高价格';"/>
                      <span>产地：</span><input class="text-price text2" type="text" name="origin" id="origin" value="${(page.params.origin)!''}"/>
                      <span>挂牌日期：</span><input type="text" class="text-price text3 mr10  Wdate" name="datetimepicker1" id="datetimepicker1" value="${(page.params.datetimepicker1)!''}"  />至<input type="text" class="text-price text3 ml10  Wdate" id="datetimepicker2" name="datetimepicker2" value="${(page.params.datetimepicker2)!''}" />
                      <input type="submit" class="btn-search ml10" id="btn-blue" value="筛选" type="submit"/>
                      <input type="hidden" name="order" id="order" value=" EXAMINETIME desc ">
                </div>
                <input type="hidden" id="breedId" name="breed" />
         </form>
         <div class="price-inventory clearfix">
             <span class="text_right fr">总共有<b>${(page.totalRecord)!0}</b>件商品，共<b>${(page.totalPage)!0}</b>页</span>
             <ul id="paixu">
                 <li value="1" <#if (page.params.order)?? && page.params.order==" lowunitprice " >sort="asc"<#else>sort="desc"</#if>>价格<i <#if (page.params.order)?? && page.params.order==" lowunitprice ">class="arrows_red cur"<#else><#if page.params.order==" lowunitprice desc ">class="arrows_red"<#else>class="arrows_gray"</#if></#if>></i></li>
                 <li value="2" <#if (page.params.order)?? && page.params.order==" surplus " >sort="asc"<#else>sort="desc"</#if>>库存<i <#if (page.params.order)?? && page.params.order==" surplus ">class="arrows_red cur"<#else><#if page.params.order==" surplus desc ">class="arrows_red"<#else>class="arrows_gray"</#if></#if>></i></li>
                 <li value="3" <#if (page.params.order)?? && page.params.order==" EXAMINETIME " >sort="asc"<#else>sort="desc"</#if>>挂牌日期<i <#if (page.params.order)?? && page.params.order==" EXAMINETIME ">class="arrows_red cur"<#else><#if page.params.order==" EXAMINETIME desc ">class="arrows_red"<#else>class="arrows_gray"</#if></#if>></i></li>
             </ul>
        </div>
    </div>
    
    
    <div class="price_list">
    	<ul>
            <li class="tit-th">
                <div class="wid_1 fl">&nbsp;</div>
                <div class="wid_2 fl" align="center">
                    <p class="tit2">库存量</p>
                </div>
                <div class="wid_2 fl" align="center">
                    <p class="tit2">单价</p>
                </div>
                <div class="wid_2 fl" align="center">
                    &nbsp;
                </div>
            </li>
             <#if page.results??>
                   	<#list page.results as busiCollectionVo >
					  <li>
			                <div class="wid_1 fl">
			                    <a href="${JOINTOWNURL}/detail/getBusiGoodsDetail?listingId=${busiCollectionVo.listingid}" target="_blank"><span class="img"><img <#if busiCollectionVo.path??>src="${RESOURCE_IMG_UPLOAD}/${busiCollectionVo.path}" data-zoom-image="${RESOURCE_IMG_UPLOAD}/${busiCollectionVo.path}"<#else></#if> /></span>
			                    <h3 class="tit">${busiCollectionVo.title!''}</h3></a>
			                    <p class="attr"><span>等级/规格：<i class="col_303030">${(busiCollectionVo.grade)!''}</i></span> <span>产地：<i class="col_303030">${(busiCollectionVo.origin)!''}</i></span></p>
			                    <p class="bz-box" title="现货保证，药材都在库，保证有现货；&#10;质量保障，药材批批质检，客观如实描述。" alt="现货保证，药材都在库，保证有现货；&#10;质量保障，药材批批质检，客观如实描述。"><img style="padding-top:11px;float:left;padding-right:2px;" src="${RESOURCE_IMG}/images/xhc.png"/> <span style="background: none repeat scroll 0 0 #ffffff;color:#000000;width:50px;">现货仓</span></p>
			                </div>
			                <div class="wid_2 fl" align="center">
			                    <p class="tit2"><b class="col_red f16"><@macro.money num=busiCollectionVo.surplus format="0.##"/></b>${(busiCollectionVo.unit)!''}</p>
			                    <p class="attr mt20"><@macro.money num=busiCollectionVo.minsales format="0.##"/>${(busiCollectionVo.unit)!''}起订</p>
			                </div>
			                <div class="wid_2 fl" align="center">
			                    <p class="tit2"><b class="col_red f16"><@macro.money num=busiCollectionVo.lowunitprice format="0.##"/></b>元/${(busiCollectionVo.unit)!''}</p>
			                    <p class="attr mt20" align="center">成交<b class="col_red f14">${(busiCollectionVo.tradeNum)!0}</b>笔</p>
			                </div>
			                <div class="wid_2 fl" align="center">
			                <#if busiCollectionVo.listingflag?? && busiCollectionVo.listingflag==4>
			                    <p class="tit3">
			                    		已下架
			                    </p>
			                  </#if>
			                    <p class="attr mt20" align="center"><a href="cancelMyCollect?cindex=${busiCollectionVo.cindex}"> 取消收藏</a></p>
			                </div>
			          </li>
		            </#list>
		            <#else>
		            <li align="center">暂无记录！</li>
          	</#if>
        </ul>
     </div>
	<div class="clearfix"></div>
    <@tools.pages page=page form="queryCollectForm"/>
  </div>
    <input type="hidden" id="imgBasePath" value="${RESOURCE_IMG_UPLOAD}" />
    <input type="hidden" id="resourceImg" value="${RESOURCE_IMG}" />    
  	<div class="area-192 fr">
  	  <div class="box-1 mt10">
        <h2 class="title-2">热门药材</h2>
          <ul class="img-list1 mt10" id="hotBusiListing">
              
          </ul>
      </div>
  </div>
</div>
<!-- 祥情页主体over -->

<!-- 底部  -->
<!--footer-->
<#include "common/footer.ftl">
<!-- 底部 end  -->
<script type="text/javascript" src="${RESOURCE_JS}/js/common.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/search.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/elevatezoom.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/Alert.js"></script>
<script>
    $(function(){
    	//搜索头部功能
    	$('#searchEngineListingButton').searcher(
    		{
    			onSearch: function(){
    				window.location.href='${JOINTOWNURL}/search?keyWords='+$('input[type="text"].search-text').val()
    			},	//搜索发生时方法
    			onHot: function(){
    				window.location.href='${JOINTOWNURL}/search?keyWords='+$(this).html()
    			},
    			onBreedLink: function(){
    				window.location.href='${JOINTOWNURL}/search/breed/'+$(this).attr('id')+'?value='+$(this).attr('value')
    			},
    			onCategoryLink: function(){
    				window.location.href='${JOINTOWNURL}/search/category/'+$(this).attr('id')+'?value='+$(this).attr('value');
    			},
    			suggestAjaxParams:	function(){
    				return {url:'${JOINTOWNURL}/search/suggest/keyWords',type:'POST',data:{keyWords:$('input[type="text"].search-text').val()}};
    			}	
    		}
    	);
		//日期控件
	    $('#datetimepicker1').click(function(){
			WdatePicker({
				startDate:'%y/%M/%d',
				dateFmt:'yyyy/MM/dd',
				maxDate:'#F{$dp.$D(\'datetimepicker2\',{d:-1});}',
				readOnly:true
			});
		});
		$('#datetimepicker2').click(function(){
			WdatePicker({
				startDate:'%y/%M/%d',
				dateFmt:'yyyy/MM/dd',
				minDate:'#F{$dp.$D(\'datetimepicker1\',{d:1});}',
				readOnly:true
			});
		});
		$('#paixu li').each(function() {
			$(this).click(function(){
				if(this.value==1){ //价格
	              if($(this).attr('sort')=="asc"){
	            	$("#order").val(" lowunitprice desc ");
	            	$(this).attr('sort','desc');
	            	}
	               else{
	                $("#order").val(" lowunitprice ");
	                $(this).attr('sort','asc');
	                }
            }else if(this.value==2){ //库存
            	 if($(this).attr('sort')=="asc"){
	            	$("#order").val(" surplus desc ");
	            	$(this).attr('sort','desc');
	            	}
	               else{
	                $("#order").val(" surplus ");
	                $(this).attr('sort','asc');
	                }
              
            }else if(this.value==3){ //挂牌日期
            	if($(this).attr('sort')=="asc"){
	            	$("#order").val(" EXAMINETIME desc ");
	            	$(this).attr('sort','desc');
	            	}
	               else{
	                $("#order").val(" EXAMINETIME ");
	                $(this).attr('sort','asc');
	                }
            }
            //切换样式
				$(this).children().removeClass("arrows_gray").addClass("arrows_red"); // 追加样式 
				$(this).children().toggleClass('cur');
               $("#queryCollectForm").submit(); 
				});
			});
		//排序
		/*
        $('#paixu li').on('click',function(){
            $(this).children().toggleClass('cur');
            if(this.value==1){ //价格
              if($(this).children().hasClass("cur"))
            	$("#order").val(" lowunitprice ");
               else
                $("#order").val(" lowunitprice desc ");
            }else if(this.value==2){ //库存
               if($(this).children().hasClass("cur"))
            	 $("#order").val(" surplus ");
            	else
            	 $("#order").val(" surplus desc ");
            }else if(this.value==3){ //挂牌日期
            if($(this).children().hasClass("cur"))
               $("#order").val(" EXAMINETIME ");
            else
            	$("#order").val(" EXAMINETIME desc ");
            }
            $("#queryCollectForm").submit();
        });*/
		
        //详情页-放大镜
        $(".price_list li .img img").elevateZoom({
            zoomWindowFadeIn: 300,
            zoomWindowFadeOut: 300,
            lensBorderSize:0,
            zoomWindowWidth: 270,
            zoomWindowHeight: 270
        });
        
        //我的收藏品种
        $.ajax({
        	 url: "/collect/mybreed",
			 type: 'get', 
			 dataType:"json",
			 success:function(data){
			 	for(var i=0;i<data.length;i++){
			 		$("#mybreed").append('<a  href=javascript:getCollectBy("'+data[i].ID+'"); title="'+data[i].NAME+'">'+data[i].NAME+'</a>');
			 	}
			 }
        });
        
        //热门药材
        $.ajax({
        	 url: "/collect/selectHotBusiListing",
			 type: 'get', 
			 dataType:"json",
			 success:function(data){
			 var imgPath = $("#imgBasePath").val();
			 var resourceImgPath = $("#resourceImg").val();
			 	for(var i=0;i<data.length;i++){
			 	var path = imgPath+"/"+data[i].PATH;
			 	if(data[i].PATH==null || data[i].PATH=="")
			 		path = resourceImgPath+"/images/bigpic-1.png";
			 	var listingId = data[i].LISTINGID;
			 	var html="";
			 	    html +="<li>";
                    html +="<a href='${JOINTOWNURL}/detail/getBusiGoodsDetail?listingId="+listingId+"'  target='_blank'><img src='"+path+"' />";
                    html +="<h3>"+data[i].TITLE+"</h3>";
                    html +="<p>价格：<strong>"+data[i].LOWUNITPRICE+"</strong>元/"+data[i].DICT_VALUE+"</p>";
                  html+="</a></li>";
			 		$("#hotBusiListing").append(html);
			 	}
			 }
        });
	});
	
	function getCollectBy(breedId){
		$("#breedId").val(breedId);
		$("#queryCollectForm").submit();
	}
	
	function check(){
		if($("#lowPrice").val()=="￥最低价格")
		  $("#lowPrice").val("");
		if($("#highPrice").val()=="￥最高价格")
			$("#highPrice").val("");
		if($("#lowPrice").val()!="" && $("#lowPrice").val()!=null && !validateNumber($("#lowPrice").val(),'最低价格')){
		    		return false;
		    	}
		if($("#highPrice").val()!="" && $("#highPrice").val()!=null && !validateNumber($("#highPrice").val(),'最高价格')){
		    		return false;
		    	}
		if($("#lowPrice").val()!="" && $("#lowPrice").val()!=null && $("#highPrice").val()!="" && $("#highPrice").val()!=null && Number($("#lowPrice").val())>Number($("#highPrice").val())){
		    	tips('最低价格不能高于最高价格!');
		    		return false;
		    }
	}
	
	function validateNumber(value,name){
			    var reg = new RegExp("^(0|[1-9][0-9]*)(\.[0-9]{1,2})?$");
			    if(reg.test(value)){
			    	return true;
			    }else{
			    	tips(name+'是最多包含2位小数的非负数!');
			    }
			    return false;
			}
  function tips(str){
				bghui();
				Alert({
			            str: str,
			            buttons:[{
			                name:'确定',
			                classname:'btn-style',
			                ev:{click:function(data){
			                	 disappear();
		                         $(".bghui").remove();
		                     }
			               }
			            }]
			    });
			}
</script>
<style>
	.WdateFmtErr{
		font-weight:normal;
		color:#474e57;
	}
</style>
</body>
</html>