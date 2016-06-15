<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>交易异常-申请退款</title>
    <link rel="stylesheet" type="text/css"  href="${RESOURCE_CSS}/css/reset.css" />
    <link rel="stylesheet" type="text/css"  href="${RESOURCE_CSS}/css/common.css" />
    <link rel="stylesheet" type="text/css"  href="${RESOURCE_CSS}/css/jzt-user-center/memberCenter.css" />
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/js/jquery-ui/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/js/Validform/css/style.css" >
	
    <script type="text/javascript" src="${RESOURCE_JS}/js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="${RESOURCE_JS}/js/common.js"></script>
	<script type="text/javascript" src="${RESOURCE_JS}/js/jzt-user-center/public.js"></script>
	<script type="text/javascript" src="${RESOURCE_JS}/js/jquery-ui/jquery-ui.js"></script>
	<script type="text/javascript" src="${RESOURCE_JS}/js/Alert.js"></script>
	<script type="text/javascript" src="${RESOURCE_JS}/js/Validform/js/Validform_v5.3.2.js"></script>
	<script type="text/javascript" src="${RESOURCE_JS}/js/ajaxfileupload.js"></script>
</head>
<body>
<#include 'common/header.ftl'>
<div class="area-1200 mb45 clearfix">
    <!-- 会员左侧 -->
    <div class="hy-left fl">
        <div align="center" class="mt10"><img src="${RESOURCE_IMG}/images/jzt-user-center/baozhang.png" /> </div>
        <div class="border-1 mt10">
            <h2 class="title">订单信息</h2>
            <div class="order-state">
                <div align="center"><img src="${RESOURCE_IMG_UPLOAD}/${busiOrder.path!''}" /> </div>
                <p>${busiOrder.title!''}<br /></p>
                <p>
		                    订单编号：<span class="col_blue">${busiOrder.orderid!''}</span> <br />
		                    单    价：<span class="col_red">${busiOrder.unitprice!''}</span> 元 / ${busiOrder.wlunit!''}<br />
		                    数    量：${busiOrder.amount!''} ${busiOrder.wlunit!''}<br />
		                    总    价：<span class="col_red">${busiOrder.totalprice!''}</span> 元<br />
		                    所在仓库：${wareHouseName!''}
                </p>
            </div>
        </div>
    </div>
    <!-- 会员左侧 over -->
    <div class="hy-right fr">
        <div class="reimburse-box">
            <div class="process">
                <span class="step1"></span>
            </div>

            <h2 class="title1 mt20">申请退款</h2>
            <form action="/order/insertApplyReimburse" name="busiAppeal" id="busiAppeal">
            <input type="hidden" name="orderId" value="${busiOrder.orderid!''}">
            <ul class="reim-form mt20">
                <li>
                    <label>取消原因：</label>
                    <input type="radio" name="appealType" value="1" datatype="*" nullmsg="请选择单位性质！" /> 药材质量问题&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="radio" name="appealType" value="2" datatype="*" nullmsg="请选择单位性质！" /> 其他原因 
                	<span class="Validform_checktip"></span>
                </li>
                <li>
                    <label>具体描述：</label>
                    <textarea class="txt textarea" name="reason" datatype="*1-200" nullmsg="请输入具体描述！" errormsg="图片描述不能多于200个字符！"></textarea> <span class="col_999"> （限200个字）</span>
                </li>
                <li>
                    <label>证据上传：</label>
                    <span class="problem-pic">
                        <a href="#"><img id="pic1" name="pic1Path" src="" /><br/>证据1.jpg <i>删除</i></a>
                        <input type="hidden" id="pic1Path" name="pic1" value=""/>
                        <a href="#"><img id="pic2" name="pic2Path" src="" /><br/>证据2.jpg <i>删除</i></a>
                        <input type="hidden" id="pic2Path" name="pic2" value=""/>
                        <a href="#"><img id="pic3" name="pic3Path" src="" /><br/>证据3.jpg <i>删除</i></a>
                        <input type="hidden" id="pic3Path" name="pic3" value=""/>
                        <input type="hidden" id="uploadFlag" value="0"/>
                    </span>
                </li>
                <li>
                    <label></label>
                    <input type="hidden" id="evidencePic" value="">
                    <span class="btn-file" id="myupload"><input type="file" name="upload" id="picUpload" value="上传本地图片" /></span> <span class="ve-mid">证据上传不是必填项，但可能会影响申诉结果！</span> <br />
                </li>
                <li>
                    <label></label>
                    <span class="col_999">（每张图片大小不超过5M，最多3张，支持GIF、JPG、PNG、BMP格式）</span>
                </li>
                <li class="mt20">
                    <label></label>
                    <input type="button" id="busiAppealSubmit" class="btn-red" value="提交申请" />
                </li>
            </ul>
            </form>
        </div>
    </div>
</div>
<!-- 底部  -->
<#include 'common/footer.ftl'>
<!-- 底部 end  -->

<script type="text/javascript">
	$("#myupload").delegate('#picUpload','change',function(){
		var fileId = $(this).attr('id');
		var pic = $(this).val();
		var picImg;//当前img显示的位置
		var picPath;//当前file存储的位置
		if($("#pic1").attr("src")==""){
			picImg = $("#pic1").attr("id");
			picPath = $("#pic1").attr("name");
		}else if($("#pic2").attr("src")==""){
			picImg = $("#pic2").attr("id");
			picPath = $("#pic2").attr("name");
		}else if($("#pic3").attr("src")==""){
			picImg = $("#pic3").attr("id");
			picPath = $("#pic3").attr("name");
		}else{
			Alert({
				str : '请先删除图片再选择上传',
				buttons : [ {
				name : '确定',
				classname : 'btn-blue',
				ev : {
					click : function(data) {
						disappear();
						$(".bghui").remove();
						}
					}
				}]
			});
			return false;
		}
		if(!/.(gif|jpg|jpeg|png|bmp)$/.test(pic.toLowerCase())){
			tips("请选择图片!");
			return false;
		}
		var uploadFlag= parseInt($("#uploadFlag").val());
		$("#uploadFlag").val(uploadFlag+1);
		$("#"+picImg).attr('src','${RESOURCE_IMG}/images/loading.gif');
		$.ajaxFileUpload(
			{
				url:'/order/uploadpic',
				secureuri:false,
				fileElementId:fileId,
				dataType: 'json',
				type: 'POST',
				success: function (data, status)
				{
					if(data.status.code==0){
						var imgSrc = data.con.path+data.con.dateDir+"/"+data.con.filename;
						$("#"+picPath).val(imgSrc);
						$("#"+picImg).attr('src', imgSrc);
					}else{
						tips("上传失败！");
						return false;
					}
					var uploadFlag=$("#uploadFlag").val();
					$("#uploadFlag").val(uploadFlag-1);
					//$("#"+fileId).value();
				},
				error: function (data, status, e)
				{
					tips('操作失败！');
					var uploadFlag=$("#uploadFlag").val();
					$("#uploadFlag").val(uploadFlag-1);
				}
			});
			//$("#picUpload").remove();
			//var input = '<input type="file" name="upload" id="picUpload" value="上传本地图片"/>';
			//$("#myupload").append(input);
	});
		
	//tips
	var Height = $('.tips').height() + 18;
	$('.opr-btn .tips').css('top', -Height);
	$('.operate-1').hover(function() {
		$(this).children('.tips').show();
	}, function() {
		$(this).children('.tips').hide();
	});
	
	 $('#Close').click (function(){
        $(this).parents('#picBox').hide();
        $('.bghui').remove();
    });
	function tips(str) {
		bghui();
		Alert({
			str : str,
			buttons : [ {
				name : '确定',
				classname : 'btn-blue',
				ev : {
					click : function(data) {
						disappear();
						$(".bghui").remove();
					}
				}
			} ]
		});
	};
		
		var addform= $("#busiAppeal").Validform({
			btnSubmit:"#busiAppeal",
			tiptype:4,
			showAllError:true,
			ajaxPost:true,
			callback:function(res){
				if(res.responseText=='y'){
					Alert({
		            str:'申请成功！',
		            buttons:[{
		                name:'确定',
		                id:'1',
		                classname:'btn-style',
		                ev:{click:function(){
		                        disappear();
		                        $(".bghui").remove();
		                        window.location.href="/order/listinfo";
		                    	}
		                	}
		            	}]
		        	});
				}else{
					Alert({
		            str:res.responseText,
		            buttons:[{
		                name:'确定',
		                id:'1',
		                classname:'btn-style',
		                ev:{click:function(){
		                        disappear();
		                        $(".bghui").remove();
		                        $("#busiAppealSubmit").removeAttr("disabled");
								$("#busiAppealSubmit").val("提交申请");
		                    	}
		                	}
		            	}]
		        	});
				}
			}
		});
		
		//删除
		$("li a i").click(function(){
			var pic = $(this).parent().find("img");
			$(pic).attr("src","");
			var picPath = $(pic).attr("name");
			$("#"+picPath).val("");
		});
		
		//提交
		$("#busiAppealSubmit").click(function(){
			//验证上传是否完成
			if(!($("#uploadFlag").val()==0)){
				Alert({
		            str:'请等待当前图片上传完成！',
		            buttons:[{
		                name:'确定',
		                id:'1',
		                classname:'btn-style',
		                ev:{click:function(data){
		                        disappear();
		                        $(".bghui").remove();
		                    	}
		                	}
		            	}]
		        	});
		        	return;
				}
			
			$("#busiAppealSubmit").attr("disabled","disabled");
			$("#busiAppealSubmit").val("提交中");
			var evidencePic;
			if($("#pic1Path").val()==""&&$("#pic2Path").val()==""&&$("#pic3Path").val()==""){
				evidencePic="";
			}else{
				evidencePic= $("#pic1Path").val()+","+$("#pic2Path").val()+","+$("#pic3Path").val();
			}
			$("#evidencePic").val(evidencePic);
			$("#busiAppeal").submit();
			
			$("#busiAppealSubmit").removeAttr("disabled");
			$("#busiAppealSubmit").val("提交申请");
		});
</script>
</body>
</html>