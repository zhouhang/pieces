<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>完善经营信息</title>
    <link rel="stylesheet" type="text/css"  href="${RESOURCE_CSS}/css/reset.css" />
    <link rel="stylesheet" type="text/css"  href="${RESOURCE_CSS}/css/common.css" />
	<link rel="stylesheet" type="text/css"  href="${RESOURCE_CSS}/css/jzt-user-center/memberCenter.css" />
    <link rel="stylesheet" type="text/css"  href="${RESOURCE_CSS}/css/form.css" />
    <link rel="stylesheet" type="text/css"  href="${RESOURCE_CSS}/js/Validform/css/style.css" />
    <style type="text/css">
    /** 品种悬浮层 样式 **/
    ul.form li p .breedAdd{
	    display: block;
	    float: left;
	    height: 50px;
	    width: 150px;
	}
	</style>
</head>
<body>
<div class="topper sty1 clearfix">
  <div class="area-1100">
 	 <div class="logo">
        <a class="logo_a" href="http://www.54315.com">聚好药商，卖真药材</a>
        <span>完善会员信息</span>
            
    </div>
    
  </div>    
</div>
<!-- 头部  -->

<!-- 头部 end  -->

<div class="complate-wapper pt20">
    <div class="area-1100 border-1 bgfff">
        <h3 class="title"><strong>欢迎您，<#if user??>${user.userName!''}</#if>：</strong>完善以下信息，能使您获得更多的中药材贸易机会和服务</h3>
        <div class="step">
            <span class="step1"></span>
        </div>
    </div>
    <div class="mt5"></div>
    <div class="area-1100 border-1 bgfff">
    <form id="completeInfoForm" action="/completeInfoGuide/saveBusiInfo" method="POST">
        <ul class="form ie">
            <li>
                <label class="lab"><i class="red">*</i> 业务类型：</label>
                <span class="sty-3 dis-in-bk"><input type="radio" name="dealType" value="1"/> 我买药材</span>
                <span class="sty-3 dis-in-bk"><input type="radio" name="dealType" value="2"/> 我卖药材</span>
                <span class="dis-in-bk"><input type="radio" name="dealType" value="3"/> 我既买药材，也卖药材</span>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="Validform_checktip" id="dealType-msg"></span>
            </li>
            <li>
                <label class="lab"><i class="red">*</i> 经营身份：</label>
                <span class="sty-3 dis-in-bk"><input type="radio" name="dealRole" value="1"/> 产地经营户</span>
                <span class="sty-3 dis-in-bk"><input type="radio" name="dealRole" value="2"/> 市场经营大户</span>
                <span class="sty-3 dis-in-bk"><input type="radio" name="dealRole" value="3"/> 中药饮片厂</span>
                <span class="sty-3 dis-in-bk"><input type="radio" name="dealRole" value="4"/> 中成药厂</span><br/>
                <label class="lab">&nbsp;</label>
                <span class="sty-3 dis-in-bk"><input type="radio" name="dealRole" value="5"/> 种植合作社</span>
                <span class="sty-3 dis-in-bk"><input type="radio" name="dealRole" value="6"/> 药农</span>
                <span class="sty-3 dis-in-bk"><input type="radio" name="dealRole" value="7"/> 其他</span>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="Validform_checktip" id="dealRole-msg"></span>
            </li>
            
            <li>
                <label class="lab breed fl"><i class="red">*</i> 主营品种：</label>
                <p class="sty-4 dis-in-bk fl" id="breed_p_tag">
                
                    <span id="span_0" class="relative breedAdd" name="breedInputList">
	                    <span class="dis-in-bk input-text">
	                        <input type="hidden" name="breeds[0].breedId" value=""/>
	                        <input id="breedText" name="breeds[0].breedName" class="text-sty2 col_333" value="">
	                        <i class="dis-in-bk"><span class="caption">品种名称正确吗？</span></i>
	                    </span>
                    <span class="remove-bg" style="display: none;"><a class="remove" name="remove" href="#">删除</a></span>
                    </span>
					
                    <span id="span_1" class="relative breedAdd" name="breedInputList">
                        <span class="dis-in-bk input-text">
                            <input type="hidden" name="breeds[1].breedId" value=""/>
                            <input id="breedText" name="breeds[1].breedName" class="text-sty2 col_333" value="">
                            <i class="dis-in-bk"><span class="caption">品种名称正确吗？</span></i>
                        </span>
                    <span class="remove-bg style="display: none;"><a class="remove" name="remove" href="#">删除</a></span>
                    </span>

                    <span id="span_2" class="relative breedAdd" name="breedInputList">
                        <span  class="dis-in-bk input-text datatype=">
                            <input type="hidden" name="breeds[2].breedId" value=""/>
                            <input id="breedText" name="breeds[2].breedName" class="text-sty2 col_333" value="">
                            <i class="dis-in-bk"><span class="caption">品种名称正确吗？</span></i>
                        </span>
                    <span class="remove-bg" style="display: none;"><a class="remove" name="remove" href="#">删除</a></span>
                    </span>
                    
                    <span id="span_3" class="relative breedAdd" name="breedInputList">
                        <span  class="dis-in-bk input-text datatype=">
                            <input type="hidden" name="breeds[3].breedId" value=""/>
                            <input id="breedText" name="breeds[3].breedName" class="text-sty2 col_333" value="">
                            <i class="dis-in-bk"><span class="caption">品种名称正确吗？</span></i>
                        </span>
                    <span class="remove-bg" style="display: none;"><a class="remove" name="remove" href="#">删除</a></span>
                    </span>
               </p>
            </li>
            <li class="clearfix">
                <label class="lab"></label>
                <a name="addBreed" href="#" class="add dis-in-bk"> <strong>+</strong> 继续增加品种</a>
                <b class="tips ma"><i></i>请至少输入一个中药材品种，输入多个品种时可以获得更多的贸易机会。</b>
                &nbsp;&nbsp;<span class="Validform_checktip" id="breed-msg"></span>
            </li>
            <li class="mt10">
                 <label class="lab"><i class="red">*</i> 经营规模：</label>
                 <span class="sty-3 dis-in-bk"><input type="radio" name="scale" value="1"/> 100万以下/年</span>
                 <span class="sty-3 dis-in-bk"><input type="radio" name="scale" value="2"/> 100万—500万/年</span>
                 <span class="sty-3 dis-in-bk"><input type="radio" name="scale" value="3"/> 500万—1000万/年</span>
                 <span class="sty-3 dis-in-bk"><input type="radio" name="scale" value="4"/> 1000万—5000万/年</span><br/>
                 <label class="lab">&nbsp;</label>
                 <span class="sty-3 dis-in-bk"><input type="radio" name="scale" value="5"/> 5000万以上/年</span>
                 <span class="dis-in-bk">( 年采购金额或销售金额 ）</span>
                 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="Validform_checktip" id="scale-msg"></span>
            </li>
            
            <!-- 所在地 省市联动  -->
            <li class="mt10">
                <label class="lab"><i class="red">*</i> 所在地址：</label>
                <div class="select-bg mr10">
                	<span>
                		<select id="areaProvince" name="provinceCode" class="col_333 area">
                			<option value="" rel="">请选择省份</option>
                			 <#if areas??>
			            		<#list areas as area>
									<option value="${area.code!'' }" rel="${area.name!'' }">${area.name!'' }</option>
			            		</#list>
			            	</#if>
                		</select>
                	</span>
                </div>
                <div class="select-bg">
                	<span>
	                	<select id="areaCity" name="cityCode" class="col_333 area">
	                		<option value="" rel="">请选择城市</option>
	                	</select>
                	</span>
                </div>
                <span class="dis-in-bk">（ 公司注册所在地 ）</span><span id="provinceCode-msg"></span><br/>
                <label class="lab"></label>
                <input name="address" class="text-sty2 text-wid355 mt10" placeholder="请填写街道地址" type="text" value=""/>
                <span id="address-msg"></span>
            </li>
            
            <li class="mt10">
                <label class="lab">邮编：</label>
                <input name="zipCode" class="text-sty2 text-wid210" type="text"/>
                <span id="zipCode-msg"></span>
            </li>
            <li class="mt5">
                <label class="lab">传真：</label>
                <input name="areaCode" class="text-sty2 text-wid45" placeholder="区号" type="text"/> - 
                <input name="fax" class="text-sty2" placeholder="传真号码" type="text" />
            </li>
            <li class="mt25">
            	<div align="center" class="relative">
            		<input type="submit" value="保 存" id="save" class="btn btn-red-grad big">
            		<a href="javascript:void(0);" class="f14 col_blue text-sty-udline dis-in-bk pl30" id="sayGoodBye">下次再说</a>
            	</div>
            </li>
        </ul>
        </form>
    </div>
</div>
<!-- 底部  -->
<#include "common/smart_footer.ftl" /> 
<!-- 底部 end  -->
<script type="text/javascript" src="${RESOURCE_JS}/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/jzt-user-center/public.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/Validform/js/Validform_v5.3.2.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/Alert.js"></script>
<script>
	$("#sayGoodBye").click(function(){
		location.href="/completeInfoGuide/inFuture?type=busi";
	});

    //主营品种 删除层
    $('body').delegate('span[name=breedInputList]','mouseover',function(){
        var Text = $(this).children().children('input[id=breedText]').val();
        if(!Text == ''){
            $(this).children('.remove-bg').show();
        }
    });
    
    // 删除层 移除
    $('body').delegate('span[name=breedInputList]','mouseout',function(){
        $(this).children('.remove-bg').hide();
    });
    
    //动态添加行索引
    var index = 4;
	//添加按钮 动态新增
    $('a[name=addBreed]').on('click',function(){
        var html = 
        	'<span id="span_'+index+'" class="relative breedAdd" name="breedInputList">'+
        	'<span class="dis-in-bk input-text">'+
        	'<input type="hidden" name="breeds['+index+'].breedId" value=""/>'+
        	'<input id="breedText" name="breeds['+index+'].breedName" class="text-sty2 col_333" value="">'+
        	'<i class="dis-in-bk"><span class="caption">品种名称正确吗？</span></i>'+
        	'</span><span class="remove-bg" style="display: none;">'+
        	'<a class="remove" name="remove" href="#">删除</a></span></span>';
        $(this).parent().prev().children('p').append(html);
        index ++;
        return false;
    });

    //删除
    $('.form li p').delegate('a[name=remove]','click',function(){
        $(this).parents('span[name=breedInputList]').remove();
        return false;
    });
    
    //删除品种
    $('a[name=remove]').on('click',function(){
        var len = $(this).parents('p[id="breed_p_tag"]').children('span').length;
        //至少有一个品种
        if(len > 1){
        	$(this).parents('span[name=breedInputList]').remove();
        }
        return false;
    });

    /////////////////////////////////////////////// 品种匹配层 START ///////////////////////////////////////////////
    $(function(){
        var value = 'amc';
        var isMouseIn = false;
        var breedMain = '';
        //品种输入keyup       
        $('body').delegate('input[id=breedText]','keyup',function(){
            var This = $(this);
            var inputParam = This.val();//获取当前输入参数
            $.ajax({
          		  type: 'POST',
          		  url: "/completeInfoGuide/getBreeds",
          		  data: {'param':inputParam},
				  dataType : "json",
				  async : false,//同步处理
				  success : function(data) {
				    var ok = data.ok;
				    if(ok){
				    	var breeds = data.obj;
				    	var top = '<div class="breed-list">';
				    	var html = "";
				    	var footer = '<span class="close">关 闭</span></div>';
				    	$.each(breeds,function(i,breed){
				    		html += '<a href="#"  id="'+breed.breedId+'">'+breed.breedName+'</a>';
				    	})
				    	breedMain = top + html + footer;
				    }else{
				    	 $('.breed-list').remove();
				    }
				 }
            });
            
             if(value){
                $('body').append(breedMain);
                var x = $(this).offset().left;
                var y = $(this).offset().top;
                $('.breed-list').css('left',x);
                $('.breed-list').css('top',y+25);
                $('.breed-list a').on('click',function(){
                	//主营品种赋值
                	This.val($(this).text());
                	//主营品种隐藏域赋值
                	This.prev("input").val($(this).attr('id'));
                	//品种验证是否存在
                	var inputVal = $(this).text();
                	checkBreed(This,inputVal);
                    $('.breed-list').remove();
                    return false;
                });
                
                $('.breed-list .close').on('click',function(){
                    $('.breed-list').remove();
                })
            }else{
                $('.breed-list').remove();
            } 
        });
        
        //品种弹层关闭
        $('body').delegate('input[id=breedText]','keydown',function(){
        	breedMain = '';
            $('.breed-list').remove();
        });
        
        $('body').delegate('input[id=breedText]','blur',function(){
            if(!isMouseIn){
                $('.breed-list').remove();
            }
        });
		
        $('body').delegate('.breed-list','mouseover',function(){
            isMouseIn=true;
        });
        
        $('body').delegate('.breed-list','mouseout',function(){
            isMouseIn=false;
        });
    });
    /////////////////////////////////////////////// 品种匹配层 END///////////////////////////////////////////////
    
    /////////////////////////////////////////////// 品种验证 START ///////////////////////////////////////////////
    $(function(){
    	//1.将输入参数传递到后台
    	//2.后台根据参数模糊查询返回json数组
    	//3.判断选中div中的品种 ,是否包含于返回的品种数组中
    	//var arr = ["三七","景天三七","三七"];  
    	//alert($.inArray("三七", arr));
    	//如果不包含在数组中,则返回 -1;
        //当前品种  是否存在
        $('body').delegate('input[id=breedText]','blur',function(){ 
        	var This = $(this);//当前的引用
        	var inputVal = $.trim($(this).val());//去空格
        	if(inputVal == ''){
	    		This.next('i').hide();
            }else{
            	checkBreed(This,inputVal);
            }
        });
    });
    
    //品种验证
    //This 当前对象
    //inputVal 当前选中参数
    function checkBreed(This,inputVal){
    	$.ajax({
    		  type: 'POST',
    		  url: "/completeInfoGuide/getBreeds",
    		  data: {'param':inputVal},//inputVal 输入参数
			  dataType : "json",
			  async : false,//同步处理
			  success : function(data) {
			    var ok = data.ok;
			    if(ok){
			    	var breeds = data.obj;
			    	var arr =  new Array();
			    	//将后台返回的品种json数组 循环取值 转换为js数组
			    	$.each(breeds,function(i,breed){
			    		//品种id 赋值到 隐藏域
			    		if(inputVal == breed.breedCname){
			    			//alert(This.attr('id'));
			    			This.prev("input").val(breed.breedId);
			    		}
			    		arr.push('"'+breed.breedCname+'"'); 
			    	})
			    	if($.inArray('"'+inputVal+'"', arr) >= 0){
			    		This.next('i').addClass('ok').css('display','inline-block');
			    		This.next('i').removeClass('wrong');
		            }
			    	if($.inArray('"'+inputVal+'"', arr) < 0){
			    		This.next('i').addClass('wrong').css('display','inline-block');
			    		This.next('i').removeClass('ok');
		            }
			    }else{
			    	This.next('i').addClass('wrong').css('display','inline-block');
			    	This.next('i').removeClass('ok');
			    }
			 }
  	});
    }
    function remove(){
        $('.alert').remove();
        setTimeout('remove()', 5000);
    }
    remove();
/////////////////////////////////////////////// 品种验证 START ///////////////////////////////////////////////

/////////////////////////////////////////////// 表单验证 START ///////////////////////////////////////////////
$(function(){
	//经营信息表单提交
	$("#completeInfoForm").Validform({
		tiptype:4,
		dragonfly:true,
		showAllError:true,
		ajaxPost:true,
		callback:function(data){
			if(data.status==1){
				 var Alert = '<span class="alert"><i></i>保存成功！</span>';
		         $('input[id=save]').parent('div').append(Alert);
		         //跳转
		         location.href="/completeInfoGuide/completeContacterInfo";
			}else if(data.status=='no'){
				var _msg = $.parseJSON(data.errorMsg);
				var len = _msg.length;
				for(var i = 0;i<len;i++){
					var _id = '#' + _msg[i].code + '-msg';
					if(_msg[i].code == 'provinceCode' || _msg[i].code == 'cityCode' ){
						$('#provinceCode-msg').addClass("Validform_wrong");
						$('#provinceCode-msg').text("请选择所在地址");
					}else{
						$(_id).addClass("Validform_wrong");
						$(_id).text(_msg[i].message);
					}
				}
			}else{
				bg();
				Alert({str:'操作失败'});
			}
		}
	});
	//单选按钮选中时清除
	clear('input[name=dealType]','dealType');
	clear('input[name=dealRole]','dealRole');
	clear("input[name=scale]",'scale');
	clear('#breed_p_tag','breed');
	clear('select[name=cityCode]','provinceCode');
	clear('input[name=zipCode]','zipCode');
	clear('input[name=address]','address');
	
	function clear(selector,msgSelector){
		$(selector).click(function(){
			$("#" + msgSelector + "-msg").removeClass('Validform_wrong');
			$('#' + msgSelector + '-msg').text('');
		});
	}
	
})
/////////////////////////////////////////////// 表单验证 END ///////////////////////////////////////////////

/////////////////////////////////////////////// 省市联动START //////////////////////////////////////////////
//省市联动样式
$('input[type=text]').keydown(function(){
    $(this).css('color','#333');
});
//两级联动 可支持三级
$('select.area').change(function(){
	var id = $(this).attr('id');
	var code = $(this).find('option:selected').attr('value');
	if(code==''){
		return;
	}
	switch(id){
		case 'areaProvince':
		  	$.ajax({
				async : true,
				cache : true,
				type : "GET",
				data : {'code':code},
				dataType : "json",
				url : "/completeInfoGuide/getAreasByCode",
				success : function(data) {
					var ok = data.ok;
					if(ok){
						$('#areaCity option:first').nextAll().remove();
						var areas = data.obj;
						$.each(areas,function(index,area){
							$('#areaCity').append('<option value="'+area.code+'" rel="'+area.name+'">'+area.name+'</option>');
						});
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown){
					alert('操作失败！');
				}
			});
			break;
		default:
		 	break;
	}
});


//keydown默认文字样式
$('input[type=text]').keydown(function(){
    $(this).css('color','#333');
});

$('input[type=text]').blur(function(){
	if($(this).val() != ''&& $(this).val()!=null){
		$(this).css('color','#333');
	}
	else{
		$(this).css('color','#c8c8c8');
	}
});
</script>
</body>
</html>