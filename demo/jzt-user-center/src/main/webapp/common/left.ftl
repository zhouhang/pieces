<!-- 我的仓单左侧 -->
<div class="hy-left fl">
      <div class="box-left">
        <ul class="menu">
            <li><a href="javascript:void(0);"><i class="account"></i>账号管理<i class="arrow"></i></a>
                <ul class="sub-menu">
                	<#if (ucUser?? && ucUser.url ?starts_with("/my_material/info"))><li class="cur"><#else><li></#if>
        				<span></span><a href="/my_material/info">用户资料</a></li>
        			<#if (ucUser?? && ucUser.url ?starts_with("/getLegalize"))><li class="cur"><#else><li></#if>
                    	<span></span><a href="/getLegalize">实名认证</a></li>
                </ul>
            </li>
            <!-- 珍药宝START -->
            <#if (ucUser?? && ucUser.certifyStatus > 0)>
	           <li><a href="#"><i class="myZyb"></i>我的珍药宝<i class="arrow"></i></a>
	                <ul class="sub-menu">
	                	<#if (ucUser?? && (ucUser.url+"?type=${type }")  ?starts_with("/ucs/gotoMyPage?type=0"))><li class="cur"><#else><li></#if>
	                		<span></span><a href="/ucs/gotoMyPage?type=0">用户总览</a></li>
	                	<#if (ucUser?? && (ucUser.url+"?type=${type }")  ?starts_with("/ucs/gotoMyPage?type=1"))><li class="cur"><#else><li></#if>
	                    	<span></span><a href="/ucs/gotoMyPage?type=1">我的银行卡</a></li>
	                    <#if (ucUser?? && (ucUser.url+"?type=${type }")  ?starts_with("/ucs/gotoMyPage?type=2"))><li class="cur"><#else><li></#if>
	                    	<span></span><a href="/ucs/gotoMyPage?type=2">充值提现</a></li>
	                    <#if (ucUser?? && (ucUser.url+"?type=${type }")  ?starts_with("/ucs/gotoMyPage?type=3"))><li class="cur"><#else><li></#if>
	                    	<span></span><a href="/ucs/gotoMyPage?type=3">交易明细</a></li>
	                    <#if (ucUser?? && (ucUser.url+"?type=${type }")  ?starts_with("/ucs/gotoMyPage?type=4"))><li class="cur"><#else><li></#if>
	                    	<span></span><a href="/ucs/gotoMyPage?type=4">安全管理</a></li>
	                </ul>
	            </li>
	         </#if>
            <!-- 珍药宝END -->
            <li><a href="javascript:void(0);"><i class="buyer"></i>我是买方<i class="arrow"></i></a>
            <ul class="sub-menu">
           	 		<!-- 未认证，跳到认证的功能-->
           	 		<#if (ucUser?? && ucUser.certifyStatus == 0)>		
           	 				<li><span></span><a href="/getLegalize">买方订单</a></li>	
           	 		<#else>	
           	 			<#if (ucUser?? && ucUser.url?starts_with("/order/listinfo"))><li class="cur"><#else><li></#if>
	        				<span></span><a href="/order/listinfo">买方订单</a></li>	
           	 		</#if>
           	 		<!--add by fanyuna 2015.10.13 我的采购 start-->
           	 		<#if (ucUser?? && ucUser.url ?starts_with("/purchase/pub"))><li class="cur"><#else><li></#if>
	        		<span></span><a href="/purchase/pub">我的采购</a></li>
           	 		<!--add by fanyuna 2015.10.13 我的采购 end-->
           	 		
	           	 	<#if (ucUser?? && ucUser.url ?starts_with("/collect/queryMyCollect"))><li class="cur"><#else><li></#if>
	        		<span></span><a target="_blank" href="/collect/queryMyCollect">我的收藏</a></li>
            </ul>
            </li>
            <li><a href="javascript:void(0);"><i class="sals"></i>我是卖方<i class="arrow"></i></a>
                <ul class="sub-menu">
               	 <!-- 未认证，跳到认证的功能-->
               	 		<#if (ucUser?? && ucUser.certifyStatus == 0)>
								<li><span></span><a href="/getLegalize">我要挂牌</a></li>
                		<#else>	
                			<#if (ucUser?? && ucUser.url ?starts_with("/listing/first"))><li class="cur"><#else><li></#if>
		        				<span></span><a href="/listing/first">我要挂牌</a></li>
		        			<#if (ucUser?? && ucUser.url ?starts_with("/listing/manager"))><li class="cur"><#else><li></#if>
		        				<span></span><a href="/listing/manager">我的挂牌</a></li>	
		        		</#if>	
		        	<#if (ucUser?? && ucUser.url ?starts_with("/order/getSellOrderList"))><li class="cur"><#else><li></#if>
		        	<span></span><a href="/order/getSellOrderList">卖方订单</a></li>
		        	<#if (ucUser?? && ucUser.url ?starts_with("/Quote/QuoteList"))><li class="cur"><#else><li></#if>
		        	<span></span><a href="/Quote/QuoteList">我的报价</a></li>
                </ul>
            </li>
           
<!--             <li><a href="javascript:void(0);"><i class="funds"></i>资金账户<i class="arrow"></i></a> -->
<!--                 <ul class="sub-menu"> -->
<!--                     <li><span></span><a href="javascript:void(0);">资金明细</a></li> -->
<!--                 </ul> -->
<!--             </li> -->
             <li><a href="javascript:void(0);"><i class="serve"></i>仓储<i class="arrow"></i></a>
                <ul class="sub-menu">
                <!-- 未认证，跳到认证的功能-->
               	 		<#if (ucUser?? && ucUser.certifyStatus == 0)>
               	 			<li><span></span><a href="/getLegalize">我的仓单</a></li>	
               	 		<#else>
               	 			 <#if (ucUser?? && ucUser.url ?starts_with("/whlist/manager"))><li class="cur"><#else><li></#if>
		        				<span></span><a href="/whlist/manager">我的仓单</a></li>	
		                </#if>
                </ul>
            </li>
        </ul>
      </div>
      <div class="box-left else-tags">
      			<a target="_blank" href="http://crm2.qq.com/page/portalpage/wpa.php?uin=4001054315&aty=0&a=0&curl=&ty=1">
					<i class="tags1"></i>在线咨询
				</a>
        	<!-- <a href="javascript:void(0);" ><i class="tags2"></i>意见反馈</a>
        	<a href="javascript:void(0);" ><i class="tags3"></i>珍药材手机端</a> -->
      </div>
</div>
<!-- 我的仓单左侧 over -->
