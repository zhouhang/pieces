<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>中药材电子商务管理系统</title>
	<#include "home/common.ftl">
</head>
<body>
<#include "home/top.ftl" />  
<#import 'macro.ftl' as tools>
<div class="wapper">
<#include "home/left.ftl" /> 
<!-- pageCenter start -->
    <div class="main">
        <div class="page-main">
            <h1 class="title1">资料审核</h1>
            <form action="/getCertificationManage/searchUcCertifyCondition" id="searchForm" method="POST">
                <ul class="form-search2">
                    <li>
                        提交时间段：<input type="text" name="submitStartDate" value="${(memCertify.submitStartDate)!''}" class="text text-3 mr10" id="datetimepicker1" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'datetimepicker2\')}',startDate:'%y/%M/%d 00:00:00',dateFmt:'yyyy/MM/dd HH:mm:ss'})"/>至<input type="text" value="${(memCertify.submitEndDate)!''}" name="submitEndDate" class="text text-3 ml10" id="datetimepicker2" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'datetimepicker1\')}',startDate:'%y/%M/%d 23:59:59',dateFmt:'yyyy/MM/dd HH:mm:ss'})" />
                    <span>&nbsp;&nbsp;&nbsp;&nbsp;会员名：</span><input class="text text-2 mr10" type="text" name="userName" value="${(memCertify.userName)!''}"/>
                    <span>手机号码：</span><input class="text text-2 mr10" type="text" name="mobileNo" value="${(memCertify.mobileNo)!''}"/>
                    </li>
                    <li>
                    	<span>认证类型：</span>
						<select name="certifyType" id="certifyType" class="text">
							<option value="" selected=selected>请选择</option>
							<option value="0" <#if (memCertify.certifyType==0)>selected</#if> >个人身份认证</option>
							<option value="1" <#if (memCertify.certifyType==1)>selected</#if> >企业身份认证</option>
						</select>
                        <span>&nbsp;&nbsp;会员类型：</span>
                        <select name="userType" id="userType" class="text">
                        	<option value="" selected="selected">请选择</option>
                        	<option value="0" <#if (memCertify.userType==0)>selected=selected</#if> >个人</option>
                        	<option value="1" <#if (memCertify.userType==1)>selected=selected</#if> >公司</option>
                        	<option value="2" <#if (memCertify.userType==2)>selected=selected</#if> >个体工商户</option>
                        </select>
                        <span>&nbsp;&nbsp;业务类型：</span>
                        <select name="dealType" id="dealType" class="text">
                        	<option value="" selected="selected">全部</option>
                        	<option value="1" <#if (memCertify.dealType==1)>selected=selected</#if> >买方</option>
                        	<option value="2" <#if (memCertify.dealType==2)>selected=selected</#if> >卖方</option>
                        	<option value="3" <#if (memCertify.dealType==3)>selected=selected</#if> >买卖双重身份</option>
                        </select>
                        <span>&nbsp;&nbsp;公司/姓名：</span><input class="text text-2 mr10" type="text" name="realName" value="${(memCertify.realName)!''}"/></li>
                    <li style="margin-bottom:15px;"><div align="center"><input type="button" class="btn-blue" id="subBtn" value="查询" /><input type="button" class="btn-hui ml30" id="resetBtn" value="清空" /></div></li>
                </ul>
            </form>
            <div class="use-item1">
                <table class="table-1" style="width:100%; *width:88%;" cellpadding="1" cellspacing="1">
                    <tr>
                        <th width="125">会员名</th>
                        <th width="135">手机号码</th>
                        <th>提交时间</th>
                        <th>公司/姓名</th>
                        <th>业务类型</th>
                        <th>经营身份</th>
                        <th>认证类型</th>
                        <th>会员类型</th>
                        <th>认证状态</th>
                        <th width="216">操作</th>
                    </tr>
                    <#if (page.results?exists)>
                    	<#if (page.results?size=0)>
	                    	<tr>
	                    		<td colspan="10">没有数据！请输入条件查询！</td>
	                    	</tr>
                    	</#if>
                    <#else>
                    	<tr>
	                    	<td colspan="10">没有数据！请输入条件查询！</td>
	                    </tr>		
                    </#if>
                    
                    <#list page.results as certifyUser>
                    <tr>
                        <td width="50" align="center">${certifyUser.userName}</td>
                        <td width="125">${certifyUser.mobile}</td>
                        <td width="135">${(certifyUser.submitTime?string("yyyy-MM-dd HH:mm:ss"))!''}</td>
                        <td>${certifyUser.name}</td>
                        <td>
                        	<#if (certifyUser.dealType=="")>无</#if>
                        	<#if (certifyUser.dealType==1)>买方</#if>
                        	<#if (certifyUser.dealType==2)>卖方</#if>
                        	<#if (certifyUser.dealType==3)>买卖双重身份</#if>
                        </td>
                        <td>
                        	<#if (certifyUser.dealRole=="")>无</#if>
                        	<#if (certifyUser.dealRole==1)>产地经营户</#if>
                        	<#if (certifyUser.dealRole==2)>市场经营大户</#if>
                        	<#if (certifyUser.dealRole==3)>中药饮片厂</#if>
                        	<#if (certifyUser.dealRole==4)>中成药厂</#if>
                        	<#if (certifyUser.dealRole==5)>种植合作社</#if>
                        	<#if (certifyUser.dealRole==6)>药农</#if>
                        	<#if (certifyUser.dealRole==7)>其他</#if>
                        </td>
                        <td>
                        	<#if (certifyUser.type==0)>个人身份认证</#if>
                        	<#if (certifyUser.type==1)>企业身份认证</#if>
                        </td>
                        <td>
                        	<#if (certifyUser.property==0)>个人</#if>
                        	<#if (certifyUser.property==1)>公司</#if>
                        	<#if (certifyUser.property==2)>个体工商户</#if>
                        </td>
                        <td>
                        	<#if (certifyUser.status==0)><i style="color:blue;">待审核</i></#if>
                        	<#if (certifyUser.status==1)><i style="color:green;">审核通过</i></#if>
                        	<#if (certifyUser.status==2)><i style="color:red;">审核不通过</i></#if>
                        </td>
                        <td width="216" class="opr-btn">
                            <span class="operate-5" id="${certifyUser.certifyId}-${certifyUser.type}" title="操作"></span>
                        </td>
                    </tr>
					</#list>
                </table>
            </div>
            <@tools.pages page=page form="searchForm"/>
        </div>
    </div>
<!-- pageCenter over -->
</div>

<script>
    $(function(){
    	/* Date.prototype.Format = function(fmt)
        { //author: meizz
            var o = {
                "M+" : this.getMonth()+1,                 //月份
                "d+" : this.getDate(),                    //日
                "h+" : this.getHours(),                   //小时
                "m+" : this.getMinutes(),                 //分
                "s+" : this.getSeconds(),                 //秒
                "q+" : Math.floor((this.getMonth()+3)/3), //季度
                "S"  : this.getMilliseconds()             //毫秒
            };
            if(/(y+)/.test(fmt))
                fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
            for(var k in o)
                if(new RegExp("("+ k +")").test(fmt))
                    fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
            return fmt;
        };
        //日期控件
        var datetimepicker1 = $('#datetimepicker1');
        var datetimepicker2 = $('#datetimepicker2'); 
       */
       /*  datetimepicker1.datetimepicker();
        datetimepicker2.datetimepicker();
 		//前端查询条件,时间验证
        $("#datetimepicker1").attr("readonly","readonly");
        $("#datetimepicker2").attr("readonly","readonly");
        $('#datetimepicker1').focus(function() {
            if ($(this).val() == myDate1) {
                $(this).val('');
            }
            else {
                $(this).val($(this).val());
            }
        }).blur(function(){
            if($(this).val() == 0){
                $(this).val(myDate1);
            }else{
                $(this).val($(this).val());
            }
        });
        $('#datetimepicker2').focus(function() {
            if ($(this).val() == myDate2) {
                $(this).val('');
            }
            else {
                $(this).val($(this).val());
            }
        }).blur(function(){
            if($(this).val() == 0){
                $(this).val(myDate2);
            }else{
                $(this).val($(this).val());
            }
        }); */
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
        
        //进入审核页面
        $('.operate-5').click(function(){
        	var _param = $(this).attr("id");
        	//获取查询条件参数
        	var _submitStartDate = $("input[name='submitStartDate']").val();
        	var _submitEndDate = $("input[name='submitEndDate']").val();
        	var _userName = $("input[name='userName']").val();
        	var _mobileNo = $("input[name='mobileNo']").val();
        	var _realName = $("input[name='realName']").val();
        	var _certifyType = $("select[name='certifyType']").val();
        	var _userType = $("select[name='userType']").val();
        	
        	
			location.href = "/getCertificationManage/getCertifyUcUserInfo?param="
											+ _param
											+ "&submitStartDate="
											+ _submitStartDate
											+ "&submitEndDate="
											+ _submitEndDate
											+ "&userName="
											+ _userName
											+ "&mobileNo="
											+ _mobileNo
											+ "&realName="
											+ _realName
											+ "&certifyType="
											+ _certifyType + "&userType=" + _userType;
						});
		//清空
		$("#resetBtn").click(function() {
			$("input[name='submitStartDate']").val("");
			$("input[name='submitEndDate']").val("");
			$("input[name='userName']").val("");
			$("input[name='mobileNo']").val("");
			$("input[name='realName']").val("");
			$("select[name='certifyType']").val("");
			$("select[name='userType']").val("");
			$("select[name='dealType']").val("");
		});

	})
</script>
</body>
</html>