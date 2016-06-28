<#setting url_escaping_charset='utf8'> 
<script type="text/javascript" src="${RESOURCE_JS}/js/search.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/jzt-user-center/public.js"></script>
<!-- 头部  -->
<div class="topper sty1 clearfix">
  <div class="area-1200">
 	 <div class="logo fl">
        <a href="#">聚好药商，卖真药材</a>
        <span>
	    	<#if optTypeTitle??>
	    		${optTypeTitle!''}
	    	<#else>
	    		<#if optType == 0>修改手机<#elseif optType == 1>设置邮箱<#else>修改邮箱</#if>
	    	</#if>
    	</span>
    </div>
    
  </div>    
</div>

<script type="text/javascript">
	$(function() {
		/**head头搜索按钮对应的搜索功能  by Mr.song 2015.3.31
		$('#searchBtn').click(function(){
			window.location.href='${JOINTOWNURL}/search?keyWords='+$('input[type="text"].search-text').val();
		});
		/**顶部菜单弹层 start by Mr.song 2015.3.31**/
	    function hoverer(id){
	        $(id).hover(
	            function(){
	                $(this).children('span').addClass('cur1');
	                $(this).children().children('i').addClass('cur');
	                $(this).children('.sub').show();
	            },
	            function(){
	                $(this).children('span').removeClass('cur1');
	                $(this).children('span').children('i').removeClass('cur');
	                $(this).children('.sub').hide();
	            })
	    }
	    hoverer('#myZYC,#Service,#QA,#webNav');
		/**顶部菜单弹层 end by Mr.song 2015.3.31 ****/
	});
	
	
	$("div.logo").click(function(){
		location.href="http://www.54315.com";
	});
</script>
<!-- 头部 end  -->