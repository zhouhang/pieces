<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
    <title>我的珍药材</title>
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/common.css" /> 
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/reset.css" />
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/jzt-user-center/memberCenter.css" />
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/jzt-user-center/store_foreground.css" />
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/js/jquery-ui/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/js/Validform/css/style.css" />
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/js/zoom/zoom.css" />
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/list.css"  />
	<style rel="stylesheet" type="text/css">
		/**分页 td高亮显示css by Mr.song 2015.3.30**/
		tr.over td {  
	    	background: #dedede; /*这个将是鼠标高亮行的背景色*/  
	    	cursor:pointer;
	    } 
	</style>
	<!--数字小数点特效处理，如取小数点后2位等处理 by Mr.song 2015.3.30-->
	<#import 'macro.ftl' as tools>
	<#assign TIPS="对不起，暂无数据!" /> <!--配置常用提示语-->
</head>
<body>
<script type="text/javascript" src="${RESOURCE_JS}/js/jquery-1.11.1.min.js"></script>
<!-- 头部  -->
<#include 'common/header.ftl'>
<div class="area-1200 clearfix">
    <!-- 我的仓单左侧 -->
    <#include 'common/left.ftl'>
    <form action="/listing/updateBusiListing" name="busiwhlistdto" method="post" id="legalizeMedicine">
            <div class="hy-right fr">
                <div class="need-to-know" id="info_1">
            		<h1>
            			<a id="info_link_1" href="#"><img src="${RESOURCE_JS}/images/jzt-user-center/down.png"></a>
            			仓单挂牌流程与须知
            		</h1>
                	<div class="wire_content">
	                	<ul>
	                       <li><span>卖家挂牌</span></li>
	                       <li class="wire"></li> 
	                       <li><img src="${RESOURCE_JS}/images/jzt-user-center/Listing.png"></li>
	                       <li class="intro">1. 填写挂牌信息<br/>
	                            2. 提交后，等待买家摘牌<br/>
	                            3. 买家摘牌后，生成订单，需支付保证金，才可锁定药材数量，如买方违约，将扣除保证金作为违约金
				           </li>
				       </ul>
	                   <ul>
	                    	<li><span>买家摘牌</span></li>
	                        <li class="wire"></li>
	                        <li><img src="${RESOURCE_JS}/images/jzt-user-center/Listing.png"></li>
	                        <li class="intro">1. 买家摘牌后需缴纳保证金<br/>
	                            2. 买家支付保证金后，即锁定相应数量药材，如买方违约，将扣除保证金作为违约金
				            </li>
	                   </ul>
	                   <ul>
	                    	<li><span>仓库备货</span></li>
	                        <li class="wire"></li>
	                        <li><img src="${RESOURCE_JS}/images/jzt-user-center/Listing.png"></li>
	                        <li class="intro">1. 仓库根据买家下单数量，按实际情况进行备货</li>
	                    </ul>
					   <ul>
	                    	<li><span>完成交易</span></li>
	                        <li class="wire"></li><li><img src="${RESOURCE_JS}/images/jzt-user-center/Listing.png"></li>
	                        <li class="intro">1. 买家支付保证金后10个工作日内必须支付剩余货款，否则视为违约，将扣除保证金支付给卖家<br/>
	                            2. 买家支付货款后，药材将生成新的仓单，交易完成</li>
	                    </ul>
                	</div>
               </div>
               <div class="cd-main">
               <div class="flow"><h1>仓单挂牌</h1></div>
               <div class="left_order">
               	<ul>
               		<input type="hidden" id="listingflag" name="listingflag" value="${listingvo.listingflag}"/>
               		<#if listingvo.listingflag==1>
		                	<li><span>仓单编号： ${listingvo.wlid!''}</span></li>
		                	<input id="listingid" name="listingid" type="hidden" value="${listingvo.listingid!''}"/>
		       				<input id="wlid" name="wlid" type="hidden" value="${listingvo.wlid!''}"/>
		       				<input id="breedid" name="breedid" type="hidden" value="${listingvo.breedid!''}"/>
							<li>
			                    <div class="opr-btn" style="float:left;margin-top: -3px; *margin-top:7px;"><span class="operate-1 operate-a"><img src="${RESOURCE_IMG}/images/jzt-user-center/question.png"><div class="tips tip_store" align="left"><span class="sj"></span>举例：60头三七 产地云南省文山壮族苗族自治州文山县 2000公斤可售</div></span></div><span>标题：</span>
			                    <input  id="title" name="title" class="text-store text-7"  value="${listingvo.title!''}" maxlength="30" type="text" nullmsg="请输入标题" dataType="*1-30" errormsg="最大30个字符"/>
		                    </li>
		                    <li><span>可挂牌数量/仓单总量：<@tools.money num=listingvo.wlsurplus format="0.##"/> ${listingvo.dictvalue!''}/<@tools.money num=listingvo.wltotal format="0.##"/> ${listingvo.dictvalue!''}</span><input type="hidden" id="wlsurplus" name="wlsurplus" value="<#if (listingvo.wlsurplus)??>${(listingvo.wlsurplus)?c!0}<#else>0</#if>" />
			                <input type="hidden" id="wltotal" name="wltotal" value="<#if (listingvo.wltotal)??>${(listingvo.wltotal)?c!0}<#else>0</#if>"/></li>
			                <li><span>挂牌数量：</span><input id="listingamount" maxlength="10" name="listingamount" value="<#if (listingvo.listingamount)??>${(listingvo.listingamount)?c!''}<#else></#if>" class="text-store text-8" type="text" dataType="zs" errormsg="挂牌数量不能大于可挂牌数量" gt="wlsurplus" recheck="wlsurplus" onkeyup="clearNoNum(this)" /><span>${listingvo.dictvalue!''}</span></li>
			                <li><span>挂牌价格：</span><input id="lowunitprice" maxlength="10" name="lowunitprice" value="<#if (listingvo.lowunitprice)??>${(listingvo.lowunitprice)?c!''}<#else></#if>" class="text-store text-8" type="text" dataType="zs" errormsg="请输入正数" onkeyup="clearNoNum(this)"/><span>元/${listingvo.dictvalue!''}</span></li>
			                <li><span>最低起订：</span><input id="minsales" maxlength="10" name="minsales" value="<#if (listingvo.minsales)??>${(listingvo.minsales)?c!''}<#else></#if>" class="text-store text-8" type="text" dataType="zs" errormsg="最低起订不能大于挂牌数量" gt="listingamount" recheck="listingamount" onkeyup="clearNoNum(this)" /><span>${listingvo.dictvalue!''}</span></li>
			                <li><span>挂牌期限：</span><div id="select-bg"><span><select name="listingtimelimit">
		                    	<#if limitMap??>
				            		<#list limitMap?keys as key>
				            			<option value="${key!''}" <#if listingvo.listingtimelimit == key>selected</#if>>${limitMap[key]!''}</option>
				            		</#list>
		            			</#if>
		                    </select></span></div>
			                <li><span>能否提供发票：</span><select id="hasbill" name="hasbill" class="text-store text-select1">
			                     <#if billMap??>
					            		<#list billMap?keys as key>
					            			<option value="${key}" <#if listingvo.hasbill==key>selected</#if>>${billMap[key]}</option>
					            		</#list>
				            	 </#if>
			                    </select>
			                </li>
			                <li id="s_billprice"><span>提供发票单价：</span><input id="billprice" maxlength="10" name="billprice" value="<#if (listingvo.billprice)??>${(listingvo.billprice)?c!''}<#else></#if>" class="text-store text-8" type="text" dataType="gs" onkeyup="clearNoNum(this)" /><span>元/${listingvo.dictvalue!''}</span></li>
			            </ul>
		            	</div>
		                  <div class="right_orderpic">
		            	<ul class="gallery">
		            		<#assign i = 0>  
		            		<#if listingvo.piclist ??>
		                   		<#list listingvo.piclist as qualitypic>
		                   			<#assign img = qualitypic.path?substring((qualitypic.path?last_index_of("/")+1),(qualitypic.path?last_index_of(".")))+"_${i320}.jpg"> 
		                   			<#assign img1 = qualitypic.path?substring((qualitypic.path?last_index_of("/")+1),(qualitypic.path?last_index_of(".")))+"_${i640}.jpg"> 
		                   			<#assign tempurl = qualitypic.path?substring(0,qualitypic.path?last_index_of("/")+1)> 
		                   			<#if qualitypic_index==0>
		                   				<li><a href="${RESOURCE_IMG_UPLOAD}/${tempurl}${img1}" name="see"  class=""><img src="${RESOURCE_IMG_UPLOAD}/${tempurl}${img}"/></a><span>散货照片</span></li>
		                   			<#elseif qualitypic_index== listingvo.piclist?size-2>
		                   				<li><a href="${RESOURCE_IMG_UPLOAD}/${tempurl}${img1}" name="see"  class=""><img src="${RESOURCE_IMG_UPLOAD}/${tempurl}${img}"/></a><span>包装照</span></li>
		                   			<#elseif qualitypic_index== listingvo.piclist?size-1>
		                   				<li><a href="${RESOURCE_IMG_UPLOAD}/${tempurl}${img1}" name="see"  class=""><img src="${RESOURCE_IMG_UPLOAD}/${tempurl}${img}"/></a><span>堆垛照</span></li>
		                   			<#else>
		                   				<#assign i = i + 1>
		                   				<li><a href="${RESOURCE_IMG_UPLOAD}/${tempurl}${img1}" name="see"  class=""><img src="${RESOURCE_IMG_UPLOAD}/${tempurl}${img}"/></a><span>细节照${i}</span></li>
		                   			</#if>
		                   		</#list>
				            </#if>  		
		                </ul><div class="clear"></div>
		               </div>
		            <div id="sample" class="editor relative">
			            <h1>药材详情</h1>
				        <textarea id="content" name="content" style="width: 980px; height:200px;">${listingvo.content!''}</textarea><span id="contentCh" class="Validform_checktip"></span>   
				   		<div align="left" style="margin-top:10px;"><input type="submit" class="btn-red" id="btn-blue" value="提交" /></div>     
		           	</div>
		       <#else>
                	<li><span>仓单编号： ${listingvo.wlid!''}</span></li>
		                	<input id="listingid" name="listingid" type="hidden" value="${listingvo.listingid!''}"/>
		       				<input id="wlid" name="wlid" type="hidden" value="${listingvo.wlid!''}"/>
		       				<input id="breedid" name="breedid" type="hidden" value="${listingvo.breedid!''}"/>
							<li>
			                    <div class="opr-btn" style="float:left;margin-top: -3px; *margin-top:7px;"><span class="operate-1 operate-a"><img src="${RESOURCE_IMG}/images/jzt-user-center/question.png"><div class="tips tip_store" align="left"><span class="sj"></span>举例：60头三七 产地云南省文山壮族苗族自治州文山县 2000公斤可售</div></span></div><span>标题：</span>
			                    <input  id="title" name="title" class="text-store text-7"  value="${listingvo.title!''}" maxlength="30" type="text" nullmsg="请输入标题" dataType="*1-30" errormsg="最大30个字符"/>
		                    </li>
		                    <li><span>可挂牌数量/仓单总量：<@tools.money num=listingvo.wlsurplus format="0.##"/> ${listingvo.dictvalue!''}/<@tools.money num=listingvo.wltotal format="0.##"/> ${listingvo.dictvalue!''}</span><input type="hidden" id="wlsurplus" name="wlsurplus" value="<#if (listingvo.wlsurplus)??>${(listingvo.wlsurplus)?c!0}<#else>0</#if>" />
			                <li><span>挂牌数量：</span><#if (listingvo.listingamount)??>${(listingvo.listingamount)?c!''}<#else></#if><span>${listingvo.dictvalue!''}</span></li>
			                <li><span>挂牌价格：</span><input id="lowunitprice" maxlength="10" name="lowunitprice" value="<#if (listingvo.lowunitprice)??>${(listingvo.lowunitprice)?c!''}<#else></#if>" class="text-store text-8" type="text" dataType="zs" errormsg="请输入正数" onkeyup="clearNoNum(this)"/><span>元/${listingvo.dictvalue!''}</span></li>
			                <li><span>最低起订：</span><#if (listingvo.minsales)??>${(listingvo.minsales)?c!''}<#else></#if><span>${listingvo.dictvalue!''}</span></li>
			                <li><span>挂牌期限：</span><div id="select-bg"><span><select name="listingtimelimit">
		                    	<#if limitMap??>
				            		<#list limitMap?keys as key>
				            			<option value="${key!''}" <#if listingvo.listingtimelimit == key>selected</#if>>${limitMap[key]!''}</option>
				            		</#list>
		            			</#if>
		                    </select></span></div>
			                <li><span>能否提供发票：</span>
			                <#if listingvo.hasbill==0>
			                	不提供发票
			                <#else>
			                	提供发票
			                	<li id="s_billprice"><span>提供发票单价：</span><input id="billprice" maxlength="10" name="billprice" value="<#if (listingvo.billprice)??>${(listingvo.billprice)?c!''}<#else></#if>" class="text-store text-8" type="text" dataType="gs" onkeyup="clearNoNum(this)" /><span>元/${listingvo.dictvalue!''}</span></li>
			                </#if>
			                <!--
			                <select id="hasbill" name="hasbill" class="text-store text-select1">
			                     <#if billMap??>
					            		<#list billMap?keys as key>
					            			<option value="${key}" <#if listingvo.hasbill==key>selected</#if>>${billMap[key]}</option>
					            		</#list>
				            	 </#if>
			                    </select>
			                -->    
			                </li>
			            </ul>
		            	</div>
		                  <div class="right_orderpic">
		            	<ul class="gallery">
		            		<#assign i = 0>  
		            		<#if listingvo.piclist ??>
		                   		<#list listingvo.piclist as qualitypic>
		                   			<#assign img = qualitypic.path?substring((qualitypic.path?last_index_of("/")+1),(qualitypic.path?last_index_of(".")))+"_${i320}.jpg"> 
		                   			<#assign img1 = qualitypic.path?substring((qualitypic.path?last_index_of("/")+1),(qualitypic.path?last_index_of(".")))+"_${i640}.jpg"> 
		                   			<#assign tempurl = qualitypic.path?substring(0,qualitypic.path?last_index_of("/")+1)> 
		                   			<#if qualitypic_index==0>
		                   				<li><a href="${RESOURCE_IMG_UPLOAD}/${tempurl}${img1}" name="see"  class=""><img src="${RESOURCE_IMG_UPLOAD}/${tempurl}${img}"/></a><span>散货照片</span></li>
		                   			<#elseif qualitypic_index== listingvo.piclist?size-2>
		                   				<li><a href="${RESOURCE_IMG_UPLOAD}/${tempurl}${img1}" name="see"  class=""><img src="${RESOURCE_IMG_UPLOAD}/${tempurl}${img}"/></a><span>包装照</span></li>
		                   			<#elseif qualitypic_index== listingvo.piclist?size-1>
		                   				<li><a href="${RESOURCE_IMG_UPLOAD}/${tempurl}${img1}" name="see"  class=""><img src="${RESOURCE_IMG_UPLOAD}/${tempurl}${img}"/></a><span>堆垛照</span></li>
		                   			<#else>
		                   				<#assign i = i + 1>
		                   				<li><a href="${RESOURCE_IMG_UPLOAD}/${tempurl}${img1}" name="see"  class=""><img src="${RESOURCE_IMG_UPLOAD}/${tempurl}${img}"/></a><span>细节照${i}</span></li>
		                   			</#if>
		                   		</#list>
				            </#if>  		
		                </ul><div class="clear"></div>
		               </div>
		            <div id="sample" class="editor relative">
			            <h1>药材详情</h1>
				        <textarea id="content" name="content"  style="width: 980px; height:200px;">${listingvo.content!''}</textarea><span id="contentCh" class="Validform_checktip"></span>   
				   		<#if listingvo.listingflag!=4>
				   			<div align="left" style="margin-top:10px;"><input type="submit" class="btn-red" id="btn-blue" value="提交" /></div>  
				   		</#if>
		           	</div>
	           </#if>    	
       </div>
   </div>
   </form>
     </div>
<!-- 详情 end  -->
<!-- 底部  -->
<#include 'common/footer.ftl'>
</body>
<script type="text/javascript" src="${RESOURCE_JS}/js/jquery-ui/jquery-ui.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/jquery.pagination.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/Validform/js/Validform_v5.3.2.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/zoom/zoom.min.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/Alert.js"></script>
<script type="text/javascript">
		//日期控件初始化
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
		//不需要刷新当前界面的tips
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
		};
		//需要刷新当前界面的tips
		function flushtips(str,url){
			bghui();
			Alert({
	            str: str,
	            buttons:[{
	                name:'确定',
	                classname:'btn-style',
	                ev:{click:function(data){
	                	 disappear();
                         $(".bghui").remove();
                         window.location.replace(url);
                     }
	               }
	            }]
		    });
		};
		var alert = tips;
		var hasbill = $('#hasbill');
        var s_billprice= $('#s_billprice');
        var billprice= $('#billprice');
       	if(hasbill.val()=='0'){
       		s_billprice.hide();
       		billprice.val('0');
       	}else{
       		s_billprice.show();
       	}
	    //触发跳转，隐藏按钮
	    $("#hasbill").change( function() {
	    	if(hasbill.val()=='0'){
       			s_billprice.hide();
       			billprice.val('0');
       			$("#billprice").attr({datatype:"gs"});
	       	}else{
	       		s_billprice.show();
	       		//添加dataType属性
       			$("#billprice").attr({datatype:"zs"});
	       	}
	    });
		//ckeditor 初始化
   		CKEDITOR.replace('content', {toolbar:'default',height : 200,width : 980}); 
   		//form 表单数据验证
   		var formObj = $("#legalizeMedicine");
   		$("#legalizeMedicine").Validform({
   			tiptype:4, 
   			ajaxPost:true,
   			datatype:{
   		 			"zs":function(gets,obj,curform,regxp){
   						//参数gets是获取到的表单元素值，obj为当前表单元素，curform为当前验证的表单，regxp为内置的一些正则表达式的引用;
   						var reg=/^[0-9]+(.[0-9]{1,2})?$/;
   						if(!reg.test(gets)||Number(gets)<=0){
   							return "请输入正数";
					}
					$(obj).val(Number(gets));
					//注意return可以返回true 或 false 或 字符串文字，true表示验证通过，返回字符串表示验证失败，字符串作为错误提示显示，返回false则用errmsg或默认的错误提示;
				},
				"gs":function(gets,obj,curform,regxp){
					//参数gets是获取到的表单元素值，obj为当前表单元素，curform为当前验证的表单，regxp为内置的一些正则表达式的引用;
					var reg=/^[0-9]+(.[0-9]{1,2})?$/;
					if(!reg.test(gets)){
						return "请输入正确的数字";
					}
					$(obj).val(Number(gets));
					//注意return可以返回true 或 false 或 字符串文字，true表示验证通过，返回字符串表示验证失败，字符串作为错误提示显示，返回false则用errmsg或默认的错误提示;
				}
			},
			beforeSubmit:function(formObj){
				//富文本框赋值js取值,单独非空判断
				$('#content').val(CKEDITOR.instances.content.getData());
				if($('#content').val()==''){
					$("#contentCh").attr("class","Validform_checktip Validform_wrong");
					$("#contentCh").html("请填写信息！");
					return false;
				}else{
					$("#contentCh").attr("class","Validform_checktip");
					$("#contentCh").html("");
				}
				$("#btn-blue").val("提交中...");
				$("#btn-blue").attr("disabled","disabled");
			},
			callback:function(data){ 
				//alert(JSON.stringify(data));
				//调用完毕恢复按钮原来样式 start
					$("#btn-blue").val("提交");
			        $("#btn-blue").removeAttr("disabled");
		        //调用完毕恢复按钮原来样式 end
		        var ok =data.ok;
		        var msg = data.msg;
		        if(ok){
		        	var url = data.url;
		        	flushtips(msg,url)
		        }else{
		        	tips(msg);
		        }
			}
		});
   		//add by fanyuna 文本框只能输入数字，且小数位数最多为2位
   	    function clearNoNum(obj){
   			  obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符  
   			  obj.value = obj.value.replace(/^\./g,"");  //验证第一个字符是数字而不是.  
   			  obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的  
   			  obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");  
   			  obj.value=obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');//只能输入两个小数  
   		}
   		//tips alert by Mr.song 2015.3.30
   		var Height = $('.tips').height()+18;
   		$('.opr-btn .tips').css('top',-Height);
   	    $('.operate-1').hover(
   	            function(){
   	                $(this).children('.tips').show();
   	            },
   	            function(){
   	                $(this).children('.tips').hide();
   	            }
   	    ); 
	    $('#info_link_1').on('click',function(){
	        $(this).parents('#info_1').toggleClass('toogle');
	        if($(this).parents('#info_1').hasClass('toogle')){
	            $(this).children('img').attr('src','${RESOURCE_JS}/images/jzt-user-center/up.png');
	        }else{
	            $(this).children('img').attr('src','${RESOURCE_JS}/images/jzt-user-center/down.png');
	        }
	    }) 
</script> 
</html>