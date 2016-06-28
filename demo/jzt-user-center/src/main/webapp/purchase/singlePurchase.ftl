<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>我的采购-单条发布</title>
    <link rel="stylesheet" type="text/css"  href="${RESOURCE_CSS}/css/reset.css" />
    <link rel="stylesheet" type="text/css"  href="${RESOURCE_CSS}/css/common.css" />
    <link rel="stylesheet" type="text/css"  href="${RESOURCE_CSS}/css/jzt-user-center/memberCenter.css" />
    <link rel="stylesheet" type="text/css"  href="${RESOURCE_CSS}/css/form.css" />
    <link href="${RESOURCE_CSS}/css/popup.css" type="text/css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/js/Validform/css/style.css" />
    <script type="text/javascript" src="${RESOURCE_JS}/js/jquery-1.11.1.min.js"></script>
</head>
<body>

<!-- 头部  -->
    <#include 'common/header.ftl'>
<!-- 头部 end  -->
<div class="area-1200 clearfix">
    <!-- 会员左侧 -->
    <#include 'common/left.ftl'>
    <!-- 会员左侧 over -->
    <div class="hy-right fr">
        <div class="order-box">
            <h2 class="o-title">我的采购</h2>
            <ul class="tabs clearfix">
                <li <#if purchase??><#else>class="cur"</#if>><a href="/purchase/pub">发布采购</a></li>
                <li <#if purchase??>class="cur"<#else></#if>><a href="/purchase/manager">管理采购</a></li>
            </ul>
            
            <div class="stocks-box">
                <p class="caption">您的专属交易员是：${salesmanInfo.name}，电话${salesmanInfo.phone}，您在采购药材时碰到任何问题，可以电话寻求帮助。</p>
               <#if purchase??>
                <p class="caption fail">审核失败原因：${purchase.rejectReason}</p>
                <div class="mt20"></div>
               <#else>
                <div class="chose">
                    <input type="radio" name="choose" checked value="1" /> 单条发布  <input type="radio" class="ml28"  name="choose" value="2" /> 批量发布
                </div>
                </#if>
                <form action="/purchase/singleSave" method="post" id="singlePurchaseForm" name="busiPurchaseDto">
                <dl>
                    <dt>采购单位<span>（必填）</span></dt>
                    <dd>
                        <label><span>*</span> 采购单位</label>
                        <input type="hidden" name="purchaseId" id="purchaseId" value="<#if purchase??>${purchase.purchaseId}</#if>" />
                        <input type="hidden" name="purchaseCode" value="<#if purchase??>${purchase.purchaseCode}</#if>" />
                        <input type="text" name="purchaserOrg" class="text-sty2 text-2 col_333" value="<#if purchase??>${purchase.purchaserOrg}<#else>${orgName}</#if>" datatype="50maxlength" nullmsg="请填写采购单位。"  />
                    </dd>
                    <dd>
                        <label><span>*</span> 联系人</label>
                        <input type="text" name="contact" value="<#if purchase??>${purchase.contact}<#else>${orgName}</#if>" class="text-sty2 text-2 col_333" datatype="50maxlength" nullmsg="请填写联系人。" />
                    </dd>
                    <dd>
                        <label><span>*</span> 联系电话</label>
                        <input type="text" name="contactPhone" class="text-sty2 text-2 col_333" value="<#if purchase??>${purchase.contactPhone}<#else>${contactTel}</#if>" datatype="m" nullmsg="请填写联系电话。" />
                    </dd>
                </dl>
                <dl>
                    <dt>采购品种<span>（必填）</span></dt>
                    <dd class="relative" >
                        <label><span class="col_red">*</span> 品种</label>
                        <input autofocus type="text" name="breedName" class="text-sty2 text-2 <#if purchase??>col_333</#if>" datatype="*" nullmsg="请填写品种名称。" placeholder="请输入或选择药材品种" value="<#if purchase??>${purchase.breedName}</#if>" />
                        
                        
                        <input type="hidden" id="breedId" name="breedId" value="<#if purchase??>${purchase.breedId}</#if>" />
                    </dd>
                    <dd>
                        <label><span class="col_red">*</span> 采购数量</label>
                        <input autofocus type="text" class="text-sty2 text-2 <#if purchase??>col_333</#if>" name="quantity" datatype="ordernum" nullmsg="请填写采购数量。" placeholder="如：100公斤" value="<#if purchase??>${purchase.quantity}</#if>" onkeyup="clearNoNum(this)" />
                        <div class="select-bg"><span><select <#if purchase??>class="col_333"</#if> name="wunitCode">
                        	<#if unitMapList?? && unitMapList?size gt 0>
                        	  <#list unitMapList as unitMap >
                        		<option value="${unitMap.DICT_CODE}" <#if purchase??&&purchase.wunitCode==unitMap.DICT_CODE >selected="selected"</#if>>${unitMap.DICT_VALUE}</option>
                        	  </#list>
                        	</#if>
                        </select></span></div>
                    </dd>
                    <dd>
                        <label><span class="col_red">*</span> 规格等级</label>
                        <input class="text-sty2 text-2 <#if purchase??>col_333</#if>" TYPE="text" name ="standardLevel" datatype="50maxlength" nullmsg="请填写规格。" name="standardLevel" value="<#if purchase??>${purchase.standardLevel}</#if>" />
                    </dd>
                    <dd>
                        <label><span class="col_red">*</span> 产地要求</label>
                        <input class="text-sty2 text-2 <#if purchase??>col_333</#if>" type="text"  name="origin" datatype="50maxlength" nullmsg="请填写产地要求。" value="<#if purchase??>${purchase.origin}</#if>" />
                    </dd>
                    <dd>
                        <label><span class="col_red">*</span> 质量要求</label>
                        <!--
                        <div  class="text-input textarea"  contentEditable id="Quality" onkeydown="edit(this.innerHTML)">
                        <#if purchase??>${purchase.qualityDescription}
                        <#else>
                        	水分：</br>
				                            灰分：</br>
				                            性状：</br>
				                            浸出物：</br>
				                            有效成分含量：</br>
				                            含硫情况：</br>
				                            其他：
				        </#if>
				         </div>
						<input type="hidden" name="qualityDescription" id="qualityDescription" value="<#if purchase??>${purchase.qualityDescription!''}</#if>" />
						-->
						<#if purchase?? && purchase.qualityDescription?? && purchase.qualityDescription!=''>
							<input id="qDes" type="hidden" value="${purchase.qualityDescription}" />
						</#if>
						<textarea name="qualityDescription" id="qualityDescription" class="text-sty2 textarea col_333" datatype="qualityRule" nullmsg="请填写质量要求。" ><#if purchase?? && purchase.qualityDescription?? && purchase.qualityDescription!=''>${purchase.qualityDescription}<#else>水分：
灰分：
性状：
浸出物：
有效成分含量：
含硫情况：
其他：</#if></textarea>
				                    </dd>
				                </dl>
				                <dl>
                    <dt>交收要求<span></span></dt>
                    <dd>
                        <label>交货地点</label>
                        
                        <input type="text" name="deliveryAddress" class="text-sty2 text-2 <#if purchase??>col_333</#if>" datatype="50maxlengthnull" value="<#if purchase??>${purchase.deliveryAddress!''}</#if>" />
                    </dd>
                    <dd>
                        <label>预计交货时间</label>
                        <input type="text" id="Time" name="expectDeliveryTime" class="text-sty2 text-2 Wdate col_333" value="<#if purchase??&&purchase.expectDeliveryTime??>${purchase.expectDeliveryTime?string("yyyy-MM-dd")!''}</#if>" />
                    </dd>
                    <dd>
                        <label>发票要求</label>
                        <input type="radio" name="receiptCode" value="common" <#if purchase??&&purchase.receiptCode??&&purchase.receiptCode=='common'>checked="checked"</#if> /> 普通发票
                        <input type="radio" name="receiptCode" class="ml20" value="addedTax" <#if purchase??&&purchase.receiptCode??&&purchase.receiptCode=='addedTax'>checked="checked"</#if> /> 增值税专用发票
                        <input type="radio" name="receiptCode" class="ml20" value="none" <#if purchase??&&purchase.receiptCode??&&purchase.receiptCode=='none'>checked="checked"</#if> /> 无需发票
                        <input type="hidden" name="receipt" value="<#if purchase??>${purchase.receipt!''}</#if>" />
                    </dd>
                    <dd>
                        <label>其他</label>
                        <textarea name="note" class="text-sty2 textarea <#if purchase??>col_333</#if>" datatype="200maxlengthnull"><#if purchase??>${purchase.note!''}</#if></textarea>
                    </dd>
                </dl>
                <dl>
                    <dt>采购信息有效期<span>（必填）</span></dt>
                    <dd>
                        <label><span class="col_red">*</span> 有效期</label>
                        <input type="radio" name="validPeriod" <#if purchase??><#if purchase.validPeriod?? ><#if purchase.validPeriod=='7'>checked="checked"</#if><#else>checked="checked"</#if><#else>checked="checked"</#if> value="7" /> 7天
                        <input type="radio" class="ml20" name="validPeriod" value="15" <#if purchase??&&purchase.validPeriod??&&purchase.validPeriod=='15'>checked="checked"</#if> /> 15天
                        <input type="radio" class="ml20" name="validPeriod" value="30" <#if purchase??&&purchase.validPeriod??&&purchase.validPeriod=='30'>checked="checked"</#if> /> 30天
                    </dd>
                </dl>
                
                <div class="anniu" id="Confirm" align="center"><input type="submit" class="btn btn-red-grad" value="提 交" id="singlePub" /></div>
                </form>
            </div>
	



        </div>
    </div>

</div>
<!-- 提交成功弹层 有推荐挂牌显示的情况 -->
<div class="popup-box">
    <div class="close"></div>
    <div class="box3" id="boxStyle">
        <div align="center">
            <p class="sty1">提交成功！</p>
        </div>

        <p class="sty2" align="center" id="sucMsg"></p>
        <div class="list" id="listDiv">
            <ul id="listingInfo">
            <!--
                <li><img src="../resources/images/list_pic1.png" /> <p>板蓝根统货 甘肃张掖<br/><span class="red">38</span>元/公斤</p></li>
                <li><img src="../resources/images/list_pic1.png" /> <p>板蓝根统货 甘肃张掖<br/><span class="red">38</span>元/公斤</p></li>
                <li><img src="../resources/images/list_pic1.png" /> <p>板蓝根统货 甘肃张掖<br/><span class="red">38</span>元/公斤</p></li>
                <li><img src="../resources/images/list_pic1.png" /> <p>板蓝根统货 甘肃张掖<br/><span class="red">38</span>元/公斤</p></li>
                -->
            </ul>
        </div>
    </div>
</div>
<!-- 提交成功弹层 over -->
<div class="breed-list" id="breedList">
                        
                        </div>
<!-- 底部  -->
<#include 'common/footer.ftl'>
<!-- 底部 end  -->

<script type="text/javascript" src="${RESOURCE_JS}/js/common.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/jzt-user-center/public.js"></script>
<script src="${RESOURCE_JS}/js/Validform/js/Validform_v5.3.2.js" type="text/javascript"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/Alert.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/placeholder.js"></script>
<script>
//$('input[placeholder]').placeholder();
//add by fanyuna 文本框只能输入数字，且小数位数最多为2位
    function clearNoNum(obj){
	  obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符  
	  obj.value = obj.value.replace(/^\./g,"");  //验证第一个字符是数字而不是.  
	  obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的  
	  obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");  
	  obj.value=obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');//只能输入两个小数  
    };
    
    //截取字符串，多余的部分用...代替  
function setString(str, len) {  
    var strlen = 0;  
    var s = "";  
    for (var i = 0; i < str.length; i++) {  
        if (str.charCodeAt(i) > 128) {  
            strlen += 2;  
        } else {  
            strlen++;  
        }  
        s += str.charAt(i);  
        if (strlen >= len) {  
            return s+"...";  
        }  
    }  
    return s;  
}  
$(function(){
//var qualityDefault = $("#qualityDescription").val(); 
	//发布类型 单选or批量
	$("input[name='choose']").bind("click",function(){
	var selectType = $('input[name="choose"]:checked').val();
		if(selectType=='2'){
			window.location.replace("/purchase/batchPub");
		}
	});
	
	//设置发票要求的值
	$("input[name='receiptCode']").bind("click",function(){
	var receiptCode = $('input[name="receiptCode"]:checked').val();
		if(receiptCode=="common"){
			$("input[name='receipt']").val('普通发票');
		}
		if(receiptCode=="addedTax"){
			$("input[name='receipt']").val('增值税专用发票');
		}
		if(receiptCode=="none"){
			$("input[name='receipt']").val('无需发票');
		}
	});
	
	//JS判断字符串长度（英文占1个字符，中文汉字占2个字符）
	function strlen(str){
	    var len = 0;
	    for (var i=0; i<str.length; i++) { 
	     var c = str.charCodeAt(i); 
	    //单字节加1 
	     if ((c >= 0x0001 && c <= 0x007e) || (0xff60<=c && c<=0xff9f)) { 
	       len++; 
	     } 
	     else { 
	      len+=2; 
	     } 
	    } 
	    return len;
	}
	
    //背景变灰
    function bgHiu(){
        var html = '<div class="bghui"></div>';
        var height = $(document).height();
        $('body').append(html);
        $('.bghui').css('height',height);
    }

    //关闭层
    function close(els,cont){
        els.on('click',function(){
            cont.hide();
            $('.bghui').remove();
        })
    }
   // close($('.close'),$('.popup-box'));
    close($('.close'),$('.breed-list'));

    //显示层
  //  function show(els,cont){
  //      els.on('click',function(){
  //          cont.show();
  //          bgHiu();
  //      })
  //  }
 //   show($('#Confirm'),$('.popup-box'));

    //弹层显示垂直居中位置
    function popUp(par,elm){
        var wid = elm.width();
        var Hei = elm.height();
        par.css({
            'position':'fixed',
            'top':'50%',
            'left':'50%',
            'zIndex':10001,
            'marginLeft':-wid/2,
            'marginTop':-Hei/2,
            'border':'3px solid #cf5445',
            'background':'#ffffff'
        })
    }
    popUp($('.popup-box'),$('.popup-box .box3'));
    
    //输入改变默认色
    $('input[type=text]').keydown(function(){
        $(this).css('color','#333');
    });
    $('select').focus(function(){
        $(this).css('color','#333');
    });
    $('#Quality').keydown(function(){
        $(this).css('color','#333');
    });

    //验证
    var formObj = $("#singlePurchaseForm");
    $("#singlePurchaseForm").Validform({
        tiptype:4,
        ajaxPost:true,
        datatype:{
     			"50maxlength":function(gets,obj,curform,regxp){
     				/*参数gets是获取到的表单元素值，
     				  obj为当前表单元素，
     				  curform为当前验证的表单，
     				  regxp为内置的一些正则表达式的引用。*/
     				if(gets==null || gets =='')
     				   return false;
					if(strlen(gets)>100){
						return "最多可输入50个中文";
					}
     				
     				return true;
     			},
     			"50maxlengthnull":function(gets,obj,curform,regxp){
     				/*参数gets是获取到的表单元素值，
     				  obj为当前表单元素，
     				  curform为当前验证的表单，
     				  regxp为内置的一些正则表达式的引用。*/
     				
					if(strlen(gets)>100){
						return "最多可输入50个中文";
					}
     				
     				return true;
     			},
     			"200maxlength":function(gets,obj,curform,regxp){
     				/*参数gets是获取到的表单元素值，
     				  obj为当前表单元素，
     				  curform为当前验证的表单，
     				  regxp为内置的一些正则表达式的引用。*/
     				if(gets==null || gets =='')
     				   return false;
					if(strlen(gets)>400){
						return "最多可输入200个中文";
					}
     				
     				return true;
     			},
     			"200maxlengthnull":function(gets,obj,curform,regxp){
     				/*参数gets是获取到的表单元素值，
     				  obj为当前表单元素，
     				  curform为当前验证的表单，
     				  regxp为内置的一些正则表达式的引用。*/
     				
					if(strlen(gets)>400){
						return "最多可输入200个中文";
					}
     				
     				return true;
     			},
     			"ordernum":function(gets,obj,curform,regxp){
     				
     				if(gets==null || gets==''){
     					return false;
     				}
     				if(isNaN(gets))
     					return false;
     				return true;
     			},
     			/*"checkbreed":function(gets,obj,curform,regxp){
     				if($("#breedId").val()==null||$("#breedId").val()==""){
     					$('input[name=breedName]').val('');
     					return "请填写品种名称";
     				}
     				alert(11);
     			},*/
     			"qualityRule":function(gets,obj,curform,regxp){
     				
     				if(gets==null || gets =='')
     				   return false;
					if(strlen(gets)>400){
						return "最多可输入200个中文";
					}
			   //添加或修改时质量要求为空的两种情况才验证
			   if($("#purchaseId").val()!=null&&$("#purchaseId").val()!=''&&$("#qDes").val()!=null&&$("#qDes").val()!='')
			   	return true;
				//if($("#purchaseId").val()==null||$("#purchaseId").val()=='')
				else
				{
					var deva = document.getElementById("qualityDescription").defaultValue;
     				if(gets==deva)
     					return false;
     					}
     				return true;
     			}
     		
     		},
        beforeSubmit:function(formObj){
				//质量要求
			//	$('#qualityDescription').val($("#Quality").html());
			//	return true;
			},
        callback:function(data){
			var ok =data.ok;
		    var msg = data.msg;	
		    if(ok){
		    	$('#sucMsg').html(msg);
		    	$('#listingInfo').html(''); //先清空
		    	if(data.listingInfos != null && data.listingInfos !=''){
		    		for(var i=0;i<data.listingInfos.length;i++){
		    			$('#listingInfo').append("<li><a href='${JOINTOWNURL}/detail/getBusiGoodsDetail?listingId="+data.listingInfos[i].LISTINGID+"'  target='_blank'><img src=${RESOURCE_IMG_UPLOAD}/"+data.listingInfos[i].PATH+" /> <p>"+setString(data.listingInfos[i].TITLE,13)+"</a><br/><span class='red'>"+data.listingInfos[i].LOWUNITPRICE+"</span>元/"+data.listingInfos[i].DICT_VALUE+"</p></li>");
		    		}
		    	}else{
		    		$("#boxStyle").removeClass('box3');
		    		$("#boxStyle").addClass('box4');
		    		$("#listDiv").hide();
		    	}
		    	$('.popup-box').show();
            	bgHiu();
            	
            	//关闭层，刷新页面
		    $('.close').on('click',function(){
	            $('.popup-box').hide();
	            $('.bghui').remove();
	            window.location.replace(data.url);
       		 });
		    }
		    else{
		    	tips(msg);
		    }
		    
		    
			}
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
    //日期控件
    $('#Time').click(function(){
        WdatePicker({
            startDate:'%y-%M-%d',
            dateFmt:'yyyy-MM-dd',
            readOnly:true
        });
    });
    //获取焦点隐藏默认值
    function Focus(els){
        var default_value = els.val();
        els.focus(function(){
            if($(this).val() == default_value){
                $(this).val('');
            }
        }).blur(function(){
            if($(this).val() == ''){
                $(this).val(default_value);
            }
        })
    }
   // Focus($('input[name=breedName]'));
   // Focus($('input[name=quantity]'));

    // 品种弹层联想效果
    var isMouseIn = false;
    //$('body').delegate('input[name=breedName]','keyup',function(){
    $('input[name=breedName]').keyup(function(){
    	var keyWord = $(this).val();
    	var y = $(this).offset().top+24,
        x=$(this).offset().left;
    	$('.breed-list').css({'left':x,'top':y});
        //ajax get value        
       if(keyWord!=''){
        //先清空
      	//$('#breedList').html("");
        $.ajax({
            	 url: "/purchase/getBreedByKeyword",
            	 data: {'keyWord':keyWord},
				 type: 'post', 
				 dataType:"json",
				 success:function(data){
				 $('#breedList').html("");
				  if(data.length>0){
				  	$('#breedList').show();
				    for(var i=0;i<data.length;i++){
				 		var breedName = data[i].breedName;
			     		$('#breedList').append("<a href='#' id='"+data[i].breedId+"'>"+breedName.substring(0,breedName.indexOf(keyWord))+"<span class='red'>"+keyWord+"</span>"+breedName.substring(breedName.indexOf(keyWord)+keyWord.length,breedName.length)+"</a>");
				    }				  
			 	  	$('#breedList').append("<span class='close'>关 闭</span>");
			 	  	
				 }
				 else{
					 $("#breedList").css('display','none');
				 	//$('input[name=breedName]').val('');
				    $("#breedId").val('');
				 	//$(this).next('.breed-list').hide();
				 	$('input[name=breedName]').parent('dd').children('.Validform_checktip').removeClass('Validform_right').addClass('Validform_wrong');
    	    		$('input[name=breedName]').parent('dd').children('.Validform_checktip').text('请填写品种名称。');
				 	
				 }
		        
			   }
            });
            }else{
            	 $("#breedList").css('display','none');
				 	$('input[name=breedName]').val('');
				    $("#breedId").val('');
				    $('input[name=breedName]').parent('dd').children('.Validform_checktip').removeClass('Validform_right');
    	    		//$('input[name=breedName]').parent('dd').children('.Validform_checktip').text('请填写品种名称。');
            }

		$('body').delegate('.breed-list a','click',function(){ 
			$('input[name=breedName]').val($(this).text());
			$('input[name=breedName]').parent('dd').children('.Validform_checktip').removeClass('Validform_wrong').addClass('Validform_right');
			$('input[name=breedName]').parent('dd').children('.Validform_checktip').text('');
			$('#breedId').val($(this).attr("id"));
            $(this).parent('.breed-list').hide();
            return false;	
		});
		$('body').delegate('#breedList .close','click',function(){ 
			 	$(this).parent('#breedList').hide();
			 	$('input[name=breedName]').val('');
			 	$('#breedId').val('');
			 	
			$('input[name=breedName]').parent('dd').children('.Validform_checktip').removeClass('Validform_right').addClass('Validform_wrong');
    	    $('input[name=breedName]').parent('dd').children('.Validform_checktip').text('请填写品种名称。');
		});
    });
    $('input[name=breedName]').keydown(function(){
        $(this).next('.breed-list').hide();
        //$('input[name=breedName]').val('');
        
    });
    $('input[name=breedName]').blur(function(){
        //alert($(this).parent('dd').children('.Validform_checktip').val());
    	
        if(!isMouseIn){
          //add by fanyuna 添加时才执行此内容
          if($("#breedId").val()==''){
          $('#breedList').hide();
          $('input[name=breedName]').val('');
          $(this).parent('dd').children('.Validform_checktip').removeClass('Validform_right').addClass('Validform_wrong');
    	$(this).parent('dd').children('.Validform_checktip').text('请填写品种名称。');
            }
        }
    });
    $('.breed-list').hover(
            function(){isMouseIn=true;},
            function(){isMouseIn=false;}
    )

});
</script>
</body>
</html>