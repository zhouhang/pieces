<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>意见反馈</title>
    <link rel="stylesheet" type="text/css"  href="${RESOURCE_CSS}/css/reset.css" />
    <link rel="stylesheet" type="text/css"  href="${RESOURCE_CSS}/css/common.css"/>
    <link rel="stylesheet" type="text/css"  href="${RESOURCE_CSS}/css/jzt-user-center/memberCenter.css" />
    <link rel="stylesheet" type="text/css"  href="${RESOURCE_CSS}/css/form.css" />
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/js/Validform/css/style.css" />
</head>
<body>
<div class="topper sty1">
      <div class="area-1100">
        <div class="logo clearfix">
            <a href="http://www.54315.com">聚好药商，卖珍药材！</a>
            <span>意见反馈</span>
        </div>
      </div>
    </div>
<div class="complate-wapper pt20">
    <div class="area-1100 border-1 bgfff">
        <h3 class="title hei-sty1 relative"><strong>如果您在网站使用过程中碰到任何问题，请及时将意见反馈给我们。</strong></h3>
        <div class="content">
        	<form action="/feedBackManage/addIdeaResp" id="ideaResp" method="post">
        	<input type="hidden" name="type" value="5">
            <ul class="form idea-box">
                <li>
                    <label class="lab"><i class="red">*</i> 姓名：</label>
                    <input name="userName" value="" class="text-sty2 col_333" datatype="*1-50" nullmsg="请填写您的姓名" errormsg="不可超过1-50个字符">
                </li>
                <li>
                    <label class="lab"><i class="red">*</i> 分类：</label>
                    <div class="select-bg col_333" style="width: 205px;">
                    <span style="width: 195px;">
                    	<select style="width: 195px;" name="ideaType" class="col_333">
                    		<option selected value="注册/登录">注册/登录</option>
          					<option value="挂牌销售">挂牌销售</option>
          					<option value="货款支付">货款支付</option>
          					<option value="药材采购">药材采购</option>
          					<option value="仓储服务">仓储服务</option>
          					<option value="金融服务">金融服务</option>
          					<option value="行情资讯">行情资讯</option>
          					<option value="其他">其他</option>
                    	</select>
                    </span>
                    </div>
                </li>
                <li>
                    <label class="lab"><i class="red">*</i> 内容：</label>
                    <textarea name="content" class="text-sty2 col_333" datatype="*1-500" nullmsg="请填写您的内容" errormsg="不可超过1-500个字符"></textarea>
                </li>
                <li>
                    <label class="lab">&nbsp;</label>
                    <input type="submit" class="btn-red-grad big bodr-radio" value="提 交" />
                </li>
            </ul>
            </form>
        </div>
    </div>
</div>

<script type="text/javascript" src="${RESOURCE_JS}/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/jzt-user-center/public.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/Validform/js/Validform_v5.3.2.js"></script>
<!-- 底部  -->
	<#include "common/listFooter.ftl"/>
<!-- 底部 end  -->
<script type="text/javascript">
	$(function(){
		$("#ideaResp").Validform({
			tiptype:4,
			dragonfly:true,
			showAllError:true
		});
	})
</script>
</body>
</html>