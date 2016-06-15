<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>中药材电子商务管理系统</title>
	<#include "home/common.ftl">
</head>
<body>
<#include "home/top.ftl" />  
<div class="wapper">
<#include "home/left.ftl" /> 
<!-- pageCenter start -->
    <div class="main">
        <div class="page-main">
            <h1 class="title1">会员管理</h1>
            <form action="/getMemberManage/searchMemberCondition" id="searchForm" method="POST">
                <ul class="form-search">
                    <li>
                        注册时间段：<input type="text" name="regStartDate" value="${(memberSearchParams.regStartDate)!''}" class="text text-3 mr10" id="datetimepicker1" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'datetimepicker2\')}',startDate:'%y/%M/%d 00:00:00',dateFmt:'yyyy/MM/dd HH:mm:ss'})" />至<input type="text" value="${(memberSearchParams.regEndDate)!''}" name="regEndDate" class="text text-3 ml10" id="datetimepicker2" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'datetimepicker1\')}',startDate:'%y/%M/%d 23:59:59',dateFmt:'yyyy/MM/dd HH:mm:ss'})"/>
                        &nbsp; <span>业务人员：</span><input class="text text-2 mr10" type="text" name="salesMan" value="${(memberSearchParams.salesMan)!''}"/>
                    </li>
                    <li><span>会员名：</span><input class="text text-2 mr10" type="text" name="userName" value="${(memberSearchParams.userName)!''}"/>
                        <span>手机号码：</span><input class="text text-2 mr10" type="text" name="mobileNo" value="${(memberSearchParams.mobileNo)!''}"/>
                        <span>公司/姓名：</span><input class="text text-2 mr10" type="text" name="realName" value="${(memberSearchParams.realName)!''}"/>
                        <input type="button" class="btn-blue" id="subBtn" value="查询" /> <input type="button" id="resetBtn" class="btn-hui ml30" onclick="" value="清空"/></li>
                </ul>
            </form>
            <div class="use-item1">
             	  <@shiro.hasPermission name="会员管理-新增会员">
             	  	 <li>&nbsp</li>
					 <li><span class="btn-add mb10"><a href="#">添加会员</a> </span></li>
				  </@shiro.hasPermission>
                
                <table class="table-1" style="width:100%; *width:88%;" cellpadding="1" cellspacing="1">
                    <tr>
                    	<th width="5%">ID</th>
                    	<th width="10%">会员名</th>
                    	<th width="8%">手机号码</th>
                    	<th width="10%">公司/姓名</th>
                    	<th width="8%">业务类型</th>
                    	<th width="8%">经营身份</th>
                    	<th width="10%">注册时间</th>
                    	<th width="10%">修改时间</th>
                    	<th width="10%">业务人员</th>
                    	<th width="20%">操作</th>
                    </tr>
                    <#if (ucUserList?exists)>
                    	<#if (ucUserList?size=0)>
	                    	<tr>
	                    		<td colspan="10">没有数据！请输入条件查询！</td>
	                    	</tr>
                    	</#if>
                    <#else>
                    	<tr>
	                    	<td colspan="10">没有数据！请输入条件查询！</td>
	                    </tr>		
                    </#if>
                    
                    <#list ucUserList as memberUsers>
                    <tr>
                        <td>${memberUsers.userId} </td>
                        <td><a href="/getMemberManage/getMemberByUserName?memberName=${memberUsers.userName}">${memberUsers.userName}</a></td>
                        <td>${memberUsers.mobile}</td>
                        <td><a href="/getMemberManage/getMemberByUserName?memberName=${memberUsers.userName}">${memberUsers.companyName}</a></td>
                        <td>
                        	<#if (memberUsers.dealType=="")>无</#if>
                        	<#if (memberUsers.dealType==1)>买方</#if>
                        	<#if (memberUsers.dealType==2)>卖方</#if>
                        	<#if (memberUsers.dealType==3)>买卖双重身份</#if>
                        </td>
                        <td>
                        	<#if (memberUsers.dealRole=="")>无</#if>
                        	<#if (memberUsers.dealRole==1)>产地经营户</#if>
                        	<#if (memberUsers.dealRole==2)>市场经营大户</#if>
                        	<#if (memberUsers.dealRole==3)>中药饮片厂</#if>
                        	<#if (memberUsers.dealRole==4)>中成药厂</#if>
                        	<#if (memberUsers.dealRole==5)>种植合作社</#if>
                        	<#if (memberUsers.dealRole==6)>药农</#if>
                        	<#if (memberUsers.dealRole==7)>其他</#if>
                        </td>
                        <td>${(memberUsers.createTime?string("yyyy-MM-dd HH:mm:ss"))!''}</td>
                        <td>${(memberUsers.updateTime?string("yyyy-MM-dd HH:mm:ss"))!''}</td>
                        <td>${memberUsers.salesMan}</td>
                        <td class="opr-btn">
							<@shiro.hasPermission name="会员管理-用户状态调整">
	                            <span class="operate-1 <#if (memberUsers.status==0)>cur</#if>" onclick="javascript:islock(${memberUsers.userId},${memberUsers.status});" title="是否锁定">
	                                <div class="tips">
	                                	<span class="sj"></span>
	                                	<#if (memberUsers.status==0)>
	                                		已开通
	                                	<#elseif (memberUsers.status==2)>
	                                		禁用
	                                	<#elseif (memberUsers.status==1)>
	                                		已删除
	                                	</#if>
	                                </div>
	                            </span>
                            </@shiro.hasPermission>
                            <@shiro.hasPermission name="会员管理-修改会员">
                            	<span class="operate-2" id="${memberUsers.userId}" title="修改"></span>
                            </@shiro.hasPermission>
                            <@shiro.hasPermission name="会员管理-重置用户密码">
                            	<span class="operate-3" onclick="javascript:secretReset(${memberUsers.userId});"title="重置密码"></span>
                            </@shiro.hasPermission>
                            <@shiro.hasPermission name="会员管理-删除会员">
                            	<span class="operate-4" onclick="javascript:delMember(${memberUsers.userId},${memberUsers.status});" title="删除"></span>
                            </@shiro.hasPermission>
                            <@shiro.hasPermission name="会员管理-绑定业务员">
                        		<span class="operate-6" onclick="javascript:bindSalesMan(${memberUsers.userId});" title="绑定业务员"></span>
                        	</@shiro.hasPermission>
                            <@shiro.hasPermission name="会员管理-查看用户备注">
                            	<span class="operate-5" id="${memberUsers.userId}" title="查看会员详情"></span>
                            </@shiro.hasPermission>
                        </td>
                    </tr>
					</#list>
					
                </table>
            </div>
        </div>
    </div>
<!-- pageCenter over -->
</div>
<!-- 弹层  -->
<!-- 添加会员  -->
<div class="" id="addMember" title="添加会员">
    <div class="box">
        <form class="addMemberForm" action="/getMemberManage/addMember" method="post">
            <ul class="form-1">
                <li>
                    <label><i class="red">*</i> 会员名：</label>
                    <p><input class="text text-1" type="text" id="userName" name="userName" ajaxurl="/getMemberManage/memberNameIsHaved" datatype="dn5-25" nullmsg="请设置会员名！"  errormsg="会员名5-25个英文字母或数字英文字母的组合！" sucmsg="该会员名未被注册，可以使用"/></p>
                	<p id="nameMsg"></p>
                	<input type="hidden" id="memberId" name="memberId"  />
                </li>
                <li>
                    <label><i class="red">*</i> 登录密码：</label>
                    <p><input class="text text-1" type="text" name="passWord" id="passWord" datatype="*6-16" nullmsg="请设置密码！" errormsg="密码范围在6~16位之间！"/></p>
                	<p id="pwdMsg"></p>
                </li>
                <li>
                	<label></label>
                	<p><input type="button" class="btn-hui" onclick="javascript:getRomSecret();" value="随机生成密码"></p>
                </li>
                <li>
                    <label><i class="red">*</i> 公司/真实姓名：</label>
                    <p><input class="text text-1" type="text" name="realName" datatype="s2-50" nullmsg="真实姓名不能为空！"  errormsg="至少4个字符,最多50个字符！"/></p>
                	<p id="rnMsg"></p>
                </li>
                <li>
                    <label><i class="red">*</i> 手 机：</label>
                    <p><input class="text text-1" type="text" name="mobile" ajaxurl="/getMemberManage/memberMobIsExist" datatype="mo"  nullmsg="请输入手机号码！" errormsg="请输入正确手机号！"/></p>
                	<p id="mobMsg"></p>
                </li>
                <li>
                    <label>备 注：</label>
                    <p><textarea name="remark" class="text textarea-1" draggable="false" datatype="s0-500" errormsg="最多500个字符！" ignore="ignore"></textarea> </p>
                	<p id="reMsg"></p>
                </li>
                <!-- <li>
                	<label></label>
                	<p id="msg"></p>
                </li> -->
                <li class="mt25">
                    <label></label>
                    <p><input type="submit" class="btn-blue" value="添加" /><input type="button" id="addReset" class="btn-hui ml10" value="重置" /> </p>
                </li>
            </ul>
        </form>
    </div>
</div>
<!-- 添加会员 over  -->

<!-- 修改会员  -->
<div class="" id="reMember"  title="修改会员">
    <div class="box">
        <form class="reMemberForm" action="" method="post">
            <ul class="form-1">
                <li>
                    <label>会员名：</label>
                    <p id="userName">&nbsp;</p>
                    <input type="hidden" name="memberId" id="memberId" value=""/>
                </li>
                <li>
                    <label><i class="red">*</i> 公司/真实姓名：</label>
                    <p><input class="text text-1" type="text" name="realName" id="realName" value="" datatype="s2-50" sucmsg=" " nullmsg="真实姓名不能为空！"  errormsg="至少2个字符,最多50个字符！"/></p>
                	<p id="rlMsg"></p>
                </li>
                <li>
                    <label><i class="red">*</i> 手 机：</label>
                    <p><input class="text text-1" type="text" name="mobileNo" id="mobileNo" value="" datatype="mo"  sucmsg=" " nullmsg="请输入手机号码!" errormsg="请输入正确手机号!"/></p>
                	<p id="mobMsg"></p>
                </li>
                <li>
                    <label>邮 件：</label>
                    <p><input class="text text-1" type="text" name="email" id="email" value="" datatype="e"  errormsg="请输入正确的邮箱!" sucmsg=" " ignore="ignore"/></p>
                	<p id="emMsg"></p>
                </li>
                <li>
                    <label>备 注：</label>
                    <p><textarea name="remark" id="remark" class="text textarea-1" draggable="false" datatype="s0-500" errormsg="最多500个字符！" sucmsg=" " ignore="ignore"></textarea> </p>
                	<p id="reMsg"></p>
                </li>
<!--                 <li> -->
<!--                 	<label></label> -->
<!--                 	<p id="remsg"></p> -->
<!--                 </li> -->
            </ul>
        </form>
    </div>
</div>
<!-- 修改会员 over  -->
<!-- ui-dialog -->
<div class="" id="reMark"  title="查看备注">
    <div class="box">
            <ul class="form-1">
                <li>
                    <p>&nbsp;</p>
                </li>
            </ul>
    </div>
</div>

<!-- 弹层 over -->
<script>
    $(function(){
        
        $('#subBtn').click(function(){
            var startTime=$("#datetimepicker1").val();
            var start=new Date(startTime.replace("-", "/").replace("-", "/"));
            var endTime=$("#datetimepicker2").val();
            var end=new Date(endTime.replace("-", "/").replace("-", "/"));
            if(end<start){
            	bghui();
                Alert({str:'结束时间不可小于开始时间！'});
                return;
            }
            $("#searchForm").submit();
        });


        //tips
        $('.operate-1').hover(
                function(){
                    $(this).children('.tips').show();
                },
                function(){
                    $(this).children('.tips').hide();
                }
        );
        //弹层
        $( "#addMember" ).dialog({
            autoOpen: false,
            width: 794
        });
        //提交添加会员的表单
        var addMemberForm = $(".addMemberForm").Validform({
    		btnSubmit:"#addMemberBnt",
    		tiptype:4,
    		ajaxPost:true,
    		callback:function(data){
    			if(data.status == 'y'){
    				$('#addMember #memberId').val(data.userId);
					bghui();                    			
            		Alert({
            			str:'添加成功,是否绑定业务员？',
            			buttons:[{
            				name:'是',
            				id:'1',
            				classname:'btn-style',
            				ev:{click:function(data){
            					var _memberId = $("#addMember input[name='memberId']").val();
            					addMemberForm.resetForm();
            					disappear();
            					$("#addMember").dialog( "close" );
                        		$('.bghui').remove();
                        		//绑定业务员
                        		bindSalesMan(_memberId);
            					}
            				}
            			},

        				{name:'否',
        				id:'2',
        				classname:'btn-style',
        				ev:{click:function(data){
        					addMemberForm.resetForm();
        					disappear();
        					$("#addMember").dialog( "close" );
                    		$('.bghui').remove();
                    		 getSearch();
        					}
        				 }
        				}]
            		});
        		}else if(data.status == 'n'){
        			bghui();
        			Alert({str:"添加失败"});
        		}else{
        			bghui();
        			Alert({str:data.status});
        		}
    		}
    	})
    	//添加会员，重置按钮
    	$("#addReset").click(function(){
    		addMemberForm.resetForm();
    	});

        $( "#reMember" ).dialog({
            autoOpen: false,
            width: 794,
            buttons: [
                {
                    text: "修改",
                    id:"reMemberBtn",
                    click: function() {
                    	//$("#reMember form").submit();
                    	var _memName = $("#reMember input[name='memberId']").val();
                    	var _realName = $("#reMember input[name='realName']").val();
                    	var _mobile = $("#reMember input[name='mobileNo']").val();
                    	var _email = $("#reMember input[name='email']").val();
                    	var _remark = $("#reMember textarea[name='remark']").val();
                    	
                    	
                    	$.post("/getMemberManage/modifyMember",{memberId:_memName,realName:_realName,mobileNo:_mobile,email:_email,remark:_remark},function(data){
                    		if(data.msg == 1){
                    			//alert("修改成功");
                    			bghui();                    			
                        		Alert({
                        			str:'修改成功',
                        			buttons:[{
                        				name:'确定',
                        				id:'1',
                        				classname:'btn-style',
                        				ev:{click:function(data){
                        					disappear();
                        					$("#reMember").dialog( "close" );
                                            $('.bghui').remove();
                                			getSearch();
                        					}
                        				}
                        			}]
                        		});
                    		}else if(data.msg == 0){
                    			bghui();
                    			Alert({str:"修改失败"});
                    		}else {
                    			bghui();
                    			Alert({str:data.msg});
                    		}
                    		
                    	}); 
                        
                        //location.reload(true);
                    }
                },
                {
                    text: "取消",
                    click: function() {
                    	//reMemberForm.resetForm();
                        $( this ).dialog( "close" );
                        $('.bghui').remove(); 
                    }
                }
            ]
        });
        
        $( "#reMark" ).dialog({
            autoOpen: false,
            width: 700,
            buttons: [
                {
                    text: "取消",
                    click: function() {
                        $( this ).dialog( "close" );
                        $('.bghui').remove();
                    }
                }
            ]
        });

		// Link to open the dialog
        //$( ".btn-add" ).click(function( event ) {
        //    var html = '<div class="bghui"></div>';
        //    $('body').append(html);
        //    $( "#addMember" ).dialog( "open" );

        //    event.preventDefault();
        //});
		
		/**
        var ReMember = $('.operate-2');
        ReMember.each(function(){
            $(this).click(function( event ){
                var html = '<div class="bghui"></div>';
                $('body').append(html);
                $( "#reMember" ).dialog( "open" );

                event.preventDefault();
            })
        });
		*/
		
        //添加会员表单验证
         /* var addMemberForm = $(".addMemberForm").Validform({
        	 btnSubmit:"#addMemberBnt",
        	 tiptype:2
        	//btnSubmit:"#addMemberBtn"
        }); */
        
        var reMemberForm = $(".reMemberForm").Validform({
        	btnSubmit:"#reMemberBtn",
        	tiptype:2
        	//btnSubmit:"#reMemberBtn"
        });
        
        var bindSalesManForm = $(".bindSalesManForm").Validform({
        	btnSubmit:"#bindSalesManBtn",
        	tiptype:2
        });
        
    });
    
    //获取随机密码
    function getRomSecret(){
    	$.post("/getMemberManage/romSecret",function(data){
    		$('#addMember #passWord').val(data);
    		$('#addMember #passWord').focus();
    	});
    }
    //获取会员信息
    function getMember(memberId){
    	var html = '<div class="bghui"></div>';
        //delete by biran 20150730 防止重复置灰色
    	//$('body').append(html);
        $( "#reMember" ).dialog( "open" );
        
    	$.post("/getMemberManage/getMember",{userId:memberId},function(data){
    		$('#reMember #memberId').val(data.userId);
    		$('#reMember #userName').html(data.userName);
    		$('#reMember #realName').val(data.companyName);
    		if(data.mobileNo=="null"){
				$('#reMember #mobileNo').val("");
			}else{
				$('#reMember #mobileNo').val(data.mobile);
			}
    		
    		if(data.email=="null"){
				$('#reMember #email').val("");
			}else{
				$('#reMember #email').val(data.email);
			}
    		
    		if(data.remark=="null"){
				$('#reMember #remark').val("");
			}else{
				$('#reMember #remark').val(data.remark);
			}
    		
    	});
    }
    
    //会员锁定解锁
    function islock(memberId,status){
    	if(status==0){
    		/* if(!window.confirm("确定要锁定该会员吗？")){
    			return;
    		} */
    		bghui();
            Alert({str:'确定要锁定该会员吗？',
                buttons:[
                    {
                        name:'确定',
                        id:'1',
                        classname:'btn-style',
                        ev:{click:function(data){
                            disappear();
                            $(".bghui").remove();
                            $.post("/getMemberManage/lock",{userId:memberId,status:status},function(data){
                        		if(data==1){
                        			/* alert("操作成功");
                        			getSearch(); */
                        			bghui();
                        			Alert({
                            			str:'操作成功',
                            			buttons:[{
                            				name:'确定',
                            				id:'1',
                            				classname:'btn-style',
                            				ev:{click:function(data){
                            					disappear();
                                                $('.bghui').remove();
                                    			getSearch();
                            					}
                            				}
                            			}]
                            		});
                        			//location.reload(true);
                        		}else if(data==0){
                        			bghui();
                        			Alert({str:"操作失败"});
                        		}
                        	
                        	});
                        }}
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
                    }
                ]
            });
    	}else if(status==2){
    		/* if(!window.confirm("该会员为锁定状态，确定要开通吗？")){
    			return;
    		} */
    		bghui();
            Alert({str:'是否解锁？',
                buttons:[
                    {
                        name:'确定',
                        id:'1',
                        classname:'btn-style',
                        ev:{click:function(data){
                            disappear();
                            $(".bghui").remove();
                            $.post("/getMemberManage/lock",{userId:memberId,status:status},function(data){
                        		if(data==1){
                        			/* alert("操作成功");
                        			getSearch(); */
                        			bghui();
                        			Alert({
                            			str:'操作成功',
                            			buttons:[{
                            				name:'确定',
                            				id:'1',
                            				classname:'btn-style',
                            				ev:{click:function(data){
                            					disappear();
                                                $('.bghui').remove();
                                    			getSearch();
                            					}
                            				}
                            			}]
                            		});
                        			//location.reload(true);
                        		}else if(data==0){
                        			bghui();
                        			Alert({str:"操作失败"});
                        		}
                        	
                        	});
                        }}
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
                    }
                ]
            });
    	}else if(status==1){
    		bghui();
    		Alert({str:"已删除会员不能进行操作！"});
    		return;
    	}
    	
    	
    }
    
    //删除会员
    function delMember(memberId,status){
    	if(status==1){
    		bghui();
    		Alert({str:"该会员已删除,无法操作已删除会员！"});
    		return;
    	}
    	
    	/* if(!window.confirm("删除是不可恢复的，确认要删除吗？")){
    		return;
    	} */
    	
    	bghui();
        Alert({str:'删除后不可恢复，确认要删除吗？',
            buttons:[
                {
                    name:'确定',
                    id:'1',
                    classname:'btn-style',
                    ev:{click:function(data){
                        disappear();
                        $(".bghui").remove();
                        $.post("/getMemberManage/delMember",{userId:memberId},function(data){
                    		if(data == 1){
                    			/* alert("删除成功！");
                    			getSearch(); */
                    			bghui();
                    			Alert({
                        			str:'删除成功！',
                        			buttons:[{
                        				name:'确定',
                        				id:'1',
                        				ev:{click:function(data){
                        					disappear();
                                            $('.bghui').remove();
                                			getSearch();
                        					}
                        				}
                        			}]
                        		});
                    			//location.reload(true);
                    		}else if(data == 0){
                    			bghui();
                    			Alert({str:"删除失败！"});
                    		}
                    	});
                    }}
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
                }
            ]
        });
    	
    }
    
    //密码重置
    function secretReset(memberId){
    	/* if(!window.confirm("你确定要重置密码吗？")){
    		return;
    	} */
    	
    	bghui();
        Alert({str:'你确定要重置密码吗？',
            buttons:[
                {
                    name:'确定',
                    id:'1',
                    classname:'btn-style',
                    ev:{click:function(data){
                        disappear();
                        $(".bghui").remove();
                        $.post("/getMemberManage/secretReset",{userId:memberId},function(data){
                    		if(data == 1){
                    			bghui();
                    			Alert({str:"重置成功,请查收短信！"});
                    		}else{
                    			bghui();
                    			Alert({str:"重置失败！"});
                    		}
                    	});
                    }}
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
                }
            ]
        });
    	
    }
    
    //绑定业务员
    function bindSalesMan(memberId){

      	var _regStartDate = $("#searchForm input[name='regStartDate']").val();
		var _regEndDate = $("#searchForm input[name='regEndDate']").val();
		var _mName = $("#searchForm input[name='userName']").val();
		var _mobNO = $("#searchForm input[name='mobileNo']").val();
		var _rname = $("#searchForm input[name='realName']").val();
		var _salesMan = $("#searchForm input[name='salesMan']").val();
    	window.location.href="/getMemberManage/selectSalesMan?memberId="+memberId+"&regStartDate="+ _regStartDate + "&regEndDate="+ _regEndDate + "&ucuserName=" + _mName + "&mobileNo=" + _mobNO + "&realName=" + _rname+ "&salesMan=" + _salesMan;
	}


    //查看备注
    function lookRemark(memberId){
    	$( "#reMark" ).dialog( "open" );
    	$.post("/getMemberManage/lookRemark",{userId:memberId},function(data){
    		$("#reMark p").html(data.remark);
    	});
    }
    
    //research查询
    function getSearch(){
    	var _regStartDate = $("#searchForm input[name='regStartDate']").val();
		var _regEndDate = $("#searchForm input[name='regEndDate']").val();
		var _mName = $("#searchForm input[name='userName']").val();
		var _mobNO = $("#searchForm input[name='mobileNo']").val();
		var _rname = $("#searchForm input[name='realName']").val();
		var _salesMan = $("#searchForm input[name='salesMan']").val();
		window.location.href="/getMemberManage/searchMemberCondition?regStartDate="+ _regStartDate + "&regEndDate="+ _regEndDate + "&ucuserName=" + _mName + "&mobileNo=" + _mobNO + "&realName=" + _rname + "&salesMan=" + _salesMan;
    }
    //置空
    $("#resetBtn").click(function(){
    	$("#searchForm input[name='regStartDate']").val("");
		$("#searchForm input[name='regEndDate']").val("");
		$("#searchForm input[name='userName']").val("");
		$("#searchForm input[name='mobileNo']").val("");
		$("#searchForm input[name='realName']").val("");
		$("#searchForm input[name='salesMan']").val("");
    });
    
    //进入会员详情页面
    $('.operate-5').click(function(){
    	var userId = $(this).attr("id");
		location.href = "/getMemberManage/getAllByUserId?userId="+ userId;
	});
    //添加会员
    $('.btn-add').click(function(){
    	var userId = $(this).attr("id");
		location.href = "/getMemberManage/toAddMember";
	});
    //修改会员
    $('.operate-2').click(function(){
    	var userId = $(this).attr("id");
		location.href = "/getMemberManage/toUpdateMember?userId="+ userId;
	});
</script>
</body>
</html>