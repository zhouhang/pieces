<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>退款管理信息查看</title>
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/reset.css" />
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/jzt-boss/admin.css" />
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/jquerytab.css">
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/jzt-boss/store.css" />
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/js/jquery-ui/jquery-ui.css" />
	<script type="text/javascript" src="${RESOURCE_JS}/js/jquery.min.js"></script>
    <#import 'macro.ftl' as tools>
    <style type="text/css">
	.loading img {
	    height: 100px;
	    width: 100px;
	}
    </style>
</head>
<body>
<#include "home/top.ftl" />
<div class="wapper">
	<#include "home/left.ftl" />
<!-- pageCenter start -->
    <div class="main">
    	<div class="page-main">
    		<h1 class="title1">退款管理</h1>
    		<!-- 审核状态 -->
    	<table cellpadding="1" cellspacing="1" bgcolor="#dddddd" width="80%" class="table-2">
           <#if type==1>
	           <form action="/refundManager/doRefund" method="POST" id="refundCheckForm" enctype="multipart/form-data">
	           	<ul class="store-search layout1 mt20">
	           		  <input name="flowId" value="${remitFlow.flowId!'' }" type="hidden" />
	           		  <input name="orderId" value="${remitFlow.orderId!''}" type="hidden" />
	           		  <input name="sellerId" value="${remitFlow.sellerId!'' }" type="hidden" />
	           		  <input name="buyerId" value="${remitFlow.buyerId!'' }" type="hidden" />
	           		  <input name="platformId" value="${remitFlow.platformId!'' }" type="hidden" />
	           		  <input name="totalAmount" value="${remitFlow.totalAmount!'' }"  type="hidden" />
	           		  <input name="platformAmount" value="${remitFlow.platformAmount!'' }" type="hidden" />
	           		  <input name="buyerAmount" value="${remitFlow.buyerAmount!'' }"  type="hidden" />
	           		  <input name="sellerAmount" value="${remitFlow.sellerAmount!'' }"  type="hidden" />
	           		  <input name="remitChannel" value="${remitFlow.remitChannel!'' }" type="hidden" />
	           		  <input name="status" value="${remitFlow.status!''}" type="hidden"/>
	           		  <input type="hidden" name="remitType" value="${remitFlow.remitType!'' }">
	           		  <input type="hidden" id="sellerVoucherUrl" name="sellerVoucher" value="">
	           		  <input type="hidden" id="buyerVoucherUrl" name="buyerVoucher" value="">
	           		  
	           		  
	           		  <tr bgcolor="#ffffff">
	                    <th width="15%" height="22">流水号：</th>
	                    <td width="25%">${remitFlow.flowId!'' }</td>
	                    <th width="15%">订单号：</th>
	                    <td width="25%">${remitFlow.orderId!''}</td>
	                  </tr>
	                  <tr bgcolor="#ffffff">
	                    <th height="22">退款金额：</th>
	                    <td>${remitFlow.totalAmount!'' }</td>
	                    <th>平台金额：</th>
	                    <td>${remitFlow.platformAmount!'' }</td>
	                  </tr>
	               	  <tr bgcolor="#ffffff">
	                    <th height="22">买方金额：</th>
	                    <td>${remitFlow.buyerAmount!'' }</td>
	                    <th>卖方金额：</th>
	                    <td>${remitFlow.sellerAmount!'' }</td>
	                  </tr>
		              <tr bgcolor="#ffffff">
		                    <th height="22">支付渠道：</th>
		                    <td><#if (payChannelList)??><#list payChannelList as pcl><#if remitFlow.remitChannel == pcl.key><span>${pcl.name!'' }</span></#if></#list></#if></td>
		                    <th>退款状态：</th>
		                    <td><#if refundRemitStatusMap??><#list refundRemitStatusMap?keys as key><#if remitFlow.status == key><span>${refundRemitStatusMap[key]!'' }</span></#if></#list></#if></td>
		              </tr>
		                <i class="loading" id="loading1">
	                    	<img alt="" src="${RESOURCE_IMG}/images/loading.gif"/>
	                    </i>
		               <#if remitFlow.remitChannel != 0><!-- 根据支付渠道显示 -->
			               <tr bgcolor="#e4f4fa">
		                    <th height="22">买方收款（银行）名称：</th>
		                    <td><input name="buyerBank" value="${remitFlow.buyerBank!'' }" class="text-store text-8" style="width:95%;" type="text" /></td>
		                    <th height="22">卖方收款（银行）名称：</th>
			                <td><input name="sellerBank" value="${remitFlow.sellerBank!'' }" class="text-store text-8" style="width:95%;  type="text" /></td>
	               		   </tr>
			               <tr bgcolor="#e4f4fa">
			                    <th>买方收款账户：</th>
		                    	<td><input name="buyerAccount" value="${remitFlow.buyerAccount!'' }" class="text-store text-8" style="width:95%; type="text" /></td>
			                    <th>卖方收款账户：</th>
			                    <td><input name="sellerAccount" value="${remitFlow.sellerAccount!'' }" class="text-store text-8" style="width:95%; type="text" /></td>
			               </tr>
			               <tr bgcolor="#e4f4fa">
                    			<th height="22">买方付款回单：</th>
                    			<td id="file2_td">
                      				<span class="w-140">买方付款回单：</span>
                      				<input name="file2" id="file2" onchange="uploadFile(this);"  value=""  type="file" style="width:180px;" />
			                    </td>
	                    		<th>卖方付款回单：</th>
			                    <td id="file1_td">
			                    	<span class="w-140">卖方付款回单：</span>
			                    	<input name="file1" id="file1" onchange="uploadFile(this);"  value=""  type="file" style="width:180px;" />
			                    </td>
                		  </tr>
			              <tr bgcolor="#ffffff">
			                    <th height="22">退款完成时间：</th>
			                    <td colspan="3">
			                    	<#if (remitFlow.remitTime)??>
										<input id="wdate1" name="rTime" value="${remitFlow.remitTime?string('yyyy/MM/dd HH:mm:ss')!''}" type="text" class="text-store text-8 mr10 Wdate" onclick="WdatePicker({dateFmt:'yyyy/MM/dd HH:mm:ss'})" />
									<#else>
										<input id="wdate1" name="rTime" value="" type="text" class="text-store text-8 mr10 Wdate" onclick="WdatePicker({dateFmt:'yyyy/MM/dd HH:mm:ss'})" />
									</#if>
			                    </td>
			               </tr>
			               <#else>
			               <tr bgcolor="#e4f4fa">
			                   <th height="22">买方收款（银行）名称：</th>
			                   <td>${remitFlow.buyerBank!'' }</td>
			                   <th height="22">卖方收款（银行）名称：</th>
			                    <td>${remitFlow.sellerBank!'' }</td>
	               		   </tr>
			               <tr bgcolor="#e4f4fa">
			                    <th>买方收款账户：</th>
			                    <td>${remitFlow.buyerAccount!'' }</td>
			                    <th>卖方收款账户：</th>
			                    <td>${remitFlow.sellerAccount!'' }</td>
			               </tr>
		               </#if>
		                <tr bgcolor="#ffffff">
		                    <th height="110">备注：</th>
		                    <td colspan="3"><textarea id="memo" name="memo">${remitFlow.memo!'' }</textarea></td>
		                </tr>
	                    <tr bgcolor="#ffffff">
                    		<th height="22"></th>
                    		<td colspan="3">
                    			<input id="confirm" value="确认" class="btn-blue" type="button" onclick="checkRefundInfo(1);" />
                      	 		<input id="refuse" value="拒绝" class="btn-hui" type="button" onclick="checkRefundInfo(3);"/>
					  	 		<input id="return" value="返回" class="btn-hui" type="button" onclick="javascript:window.history.go(-1);" />
                    		</td>
               		    </tr>
	           </form>
	           <!-- 查看详情 -->
           	<#elseif type==2>
           		<tr bgcolor="#ffffff">
                    <th width="15%" height="22">流水号：</th>
                    <td width="25%">${remitFlow.flowId!'' }</td>
                    <th width="15%">订单号：</th>
                    <td width="25%">${remitFlow.orderId!''}</td>
                </tr>
                <tr bgcolor="#ffffff">
                    <th height="22">退款金额：</th>
                    <td>${remitFlow.totalAmount!'' }</td>
                    <th>平台金额：</th>
                    <td>${remitFlow.platformAmount!'' }</td>
                </tr>
                <tr bgcolor="#ffffff">
                    <th height="22">买方金额：</th>
                    <td>${remitFlow.buyerAmount!'' }</td>
                    <th>卖方金额：</th>
                    <td>${remitFlow.sellerAmount!'' }</td>
                </tr>
                <tr bgcolor="#ffffff">
                    <th height="22">支付渠道：</th>
                    <td><#if (payChannelList)??><#list payChannelList as pcl><#if remitFlow.remitChannel == pcl.key><span>${pcl.name!'' }</span></#if></#list></#if></td>
                    <th>退款状态：</th>
                    <td><#if refundRemitStatusMap??><#list refundRemitStatusMap?keys as key><#if remitFlow.status == key><span>${refundRemitStatusMap[key]!'' }</span></#if></#list></#if></td>
                </tr>
                
                <tr bgcolor="#e4f4fa">
                    <th height="22">买方收款（银行）名称：</th>
                    <td>${remitFlow.buyerBank!'' }</td>
                    <th height="22">卖方收款（银行）名称：</th>
                    <td>${remitFlow.sellerBank!'' }</td>
                </tr>
                <tr bgcolor="#e4f4fa">
                	<th>买方收款账户：</th>
                    <td>${remitFlow.buyerAccount!'' }</td>
                    <th>卖方收款账户：</th>
                    <td>${remitFlow.sellerAccount!'' }</td>
                </tr>
                <tr bgcolor="#e4f4fa">
                    <th height="22">买方付款回单：</th>
                    <td>
                     <#if (remitFlow.buyerVoucher != '')>
                     	<span><a href="javascript:void(0);" id="${RESOURCE_IMG_UPLOAD}/${remitFlow.buyerVoucher!''}">查看</a></span>
                     <#else>	
                      	无
                     </#if>	
                    </td>
                    <th>卖方付款回单：</th>
                    <td>
                    	<#if (remitFlow.sellerVoucher != '')>
	                      <span><a href="javascript:void(0);" id="${RESOURCE_IMG_UPLOAD}/${remitFlow.sellerVoucher!''}">查看</a></span>
	                    <#else>	
	                      	无
	                    </#if>
                    </td>
                </tr>
                <tr bgcolor="#ffffff">
                    <th height="22">退款完成时间：</th>
                    <td colspan="3">
                    	<#if remitFlow.remitTime??>
                      		${remitFlow.remitTime?string('yyyy-MM-dd HH:mm:ss')!''}
                      	<#else>
                      	 	无
                      	</#if>
                    </td>
                </tr>
                <tr bgcolor="#ffffff">
                    <th height="110">备注：</th>
                    <td colspan="3"><textarea readonly="readonly">${remitFlow.memo!'' }</textarea></td>
                </tr>
                <tr bgcolor="#ffffff">
                    <th height="22"></th>
                    <td colspan="3"><input id="return" value="返回" class="btn-hui" type="button" onclick="javascript:window.history.go(-1);" /></td>
                </tr>
           </#if>
         </table>
    	</div>
    </div>
</div>
<!-- 图片弹层 -->
<div class="popup-box" id="picBox">
    <span class="close" id="Close"></span>
    <img src="" id="showImg"   />
</div>
<script type="text/javascript" src="${RESOURCE_JS}/js/jzt-boss/nav.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/jquery-ui/jquery-ui.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/Alert.js"></script>
<script type="text/javascript" charset="utf-8" src="${RESOURCE_JS}/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/imgView/jquery.imageView.js"></script>
<script>
$(function (){
	$("#loading1").hide();
	
	$('#wdate1').click(function(){
		 WdatePicker({
	        maxDate:'#F{$dp.$D(\'wdate2\',{d:-1});}',
	        readOnly:true
	    });
	});
	
	//图片预览	
	$("td span a").click(function(){
		  var path = $(this).attr("id");
		  $('#picBox').show();
	      var html = '<div class="bghui"></div>';
	      var height = $(document).height();
	      $('body').append(html);
	      $('.bghui').css('height',height);
		  $('#showImg').attr('src',path);
		  $('#picBox').imageView({width:600, height:400});
	      return false;
	});

	$('#Close').click (function(){
	    $(this).parents('#picBox').hide();
	    $('.bghui').remove();
	});
})


var isFile;
//异步文件上传
function uploadFile(obj){
	 var file = obj.value;
	 if(!/.(gif|jpeg|bmp|jpg|png)$/.test(file)){
			bghui();
			Alert({str:'请上传图片！'});
			isFile = false;
			return;
	 }else{
		 isFile = true;
		 bghui();
			$("#loading1").show();
			var fileId = obj.id;
			var url = "/refundManager/uploadFile";
			 $.ajaxFileUpload({
			   		url:url,
			   		type: 'POST',
			   		data : {fileId:fileId},
			   		secureuir:false,
			   		fileElementId:fileId,
			   		dataType: 'json',
			   		success: function (data){
			   			var fileStatus = data.fileStatus;
			   			var html;
			   			if(fileStatus){
			   				$("#loading1").hide();
			   				if('file1' == fileId){
			   					bghui();
			   					$("#sellerVoucherUrl").val(data.sellerVoucherUrl);
			   					Alert({str:"卖家回单上传成功!"});
			   					
			   					if($("#file1_td a").length<1){
			   						html = "<span><a href='javascript:void(0);' onclick='showPic(this);' id='${RESOURCE_IMG_UPLOAD}/"+data.sellerVoucherUrl+"'>查看</a><span>";
			   						$("#file1_td").append(html);
			   					}else{
			   						$("#file1_td span a").attr("id","${RESOURCE_IMG_UPLOAD}/"+data.sellerVoucherUrl);
			   					}
			   				}else if('file2' == fileId){
			   					bghui();
			   					$("#buyerVoucherUrl").val(data.buyerVoucherUrl);
			   					Alert({str:"买家回单上传成功!"});
			   					
			   					if($("#file2_td a").length<1){
			   						html = "<span><a href='javascript:void(0);' onclick='showPic(this);' id='${RESOURCE_IMG_UPLOAD}/"+data.buyerVoucherUrl+"'>查看</a><span>";
			   						$("#file2_td").append(html);
			   					}else{
			   						$("#file2_td span a").attr("id","${RESOURCE_IMG_UPLOAD}/"+data.buyerVoucherUrl);
			   					}
			   				}
			   			}else{
			   				bghui();
			   				Alert({str : '回单上传失败！'});
			   			}
			   		}
			  });
	 }
}


function checkRefundInfo(type){
	var url = "/refundManager/doRefund?type="+type;
	var memo = $("#memo").val();
	var wdate1 = $("#wdate1").val();
	var remitChannel = $("input[name='remitChannel']").val();
	var tips = "";
	
	if(type == 1){
		tips = "退款";
		if(remitChannel != 0){
			if(wdate1 == '' || wdate1 == null){
				bghui();
				Alert({str:"请填写退款时间!"});
				return ;
			}
		}
	}else if(type == 3){
		if(memo =='' || memo == null){
			bghui();
			Alert({str:"请填写拒绝原因!"});
			return ;
		}
		tips = "拒绝";
	}
	//线下支付
	/* if(remitChannel!=0){
		if(!isFile){
			bghui();
			Alert({str:"请上传图片!"});
			return ;
		}
	} */
	
	bghui();
	Alert({str:"确定执行"+tips+"操作吗？",
		 buttons:[{
			name:'确定',
			id:'1',
			classname:'btn-style',
			ev:{click:function(data){
				bghui();
				$("#loading1").show();
				//提示框隐藏
				disappear();
				//$(".bghui").remove();
				//ajax start
					$.ajax({
						type : "POST",
						url : url,
						data : $("#refundCheckForm").serialize(),
						dataType : "json",
						success : function(data){
							var code = data.code;
							var msg = data.msg;
							if(code == 1){
								bghui();
								Alert({
					    			str : '操作成功！',
					    			buttons:[{
					    				name:'确定',
					    				id:'1',
					    				classname:'btn-style',
					    				ev:{click:function(data){
					    					disappear();
					    					$(".bghui").remove();
					                        location.href = "/refundManager";
					    				}
					    			}
					    			}]
					    		});
							}else{
								$("#loading1").hide();
								Alert({
					    			str : '操作失败[CODE:'+msg+']',
					    			buttons:[{
					    				name:'确定',
					    				id:'1',
					    				classname:'btn-style',
					    				ev:{click:function(data){
					    					$(".bghui").remove();
					    					disappear();
					                        location.href = "/refundManager";
					    				}
					    			}
					    			}] 
					    		});
							}
						}
					});
				//ajax end
			}
		   }
		},
       {
           name:'取消',
           id:'2',
           classname:'btn-hui',
           ev:{click:function(data){
               disappear();
               $(".bghui").remove();
               return;
           }}
      }]
	});
	
}
//预览上传图片
function showPic(obj){
	var path = obj.id;
	$('#picBox').show();
    var html = '<div class="bghui"></div>';
    var height = $(document).height();
    $('body').append(html);
    $('.bghui').css('height',height);
	$('#showImg').attr('src',path);
	$('#picBox').imageView({width:600, height:400});
    return false;
}
</script>
</body>
</html>