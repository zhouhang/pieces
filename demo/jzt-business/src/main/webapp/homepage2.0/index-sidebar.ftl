<!-- 侧边固定操作项 start -->
<div class="guide-box">
    <div class="stock" datatype="click">免费采购</div>
    <div class="sells" datatype="click">我要卖货 </div>
    <div class="supply" datatype="click">我要融资</div>
    <div class="help"><a href="http://crm2.qq.com/page/portalpage/wpa.php?uin=4001054315&aty=2&a=2&curl=&ty=1" target="_blank">在线咨询</a></div>
    <div class="advisory">回到顶部</div>
</div>
<!-- 侧边固定操作项 end -->
<!-- 表单弹层 START -->
<div id="stock" class="pop-up">
    <div class="close"></div>
    <form id="addStockForm" action="/feedBackManage/addFeedBack">
    <ul>
    	<input type="hidden" name="type" value="1">
        <li>
            <label>采购需求<span class="col_red">(*必填)</span></label>
            <textarea name='content' placeholder="请详细填写采购需求，包括采购品种、规格等级、数量等。"
            class="text-bg textarea"></textarea>
        </li>
        <li>
            <label>联系电话<span class="col_red">(*必填)</span></label>
            <input name='mobile' class="text-bg"
             placeholder="请留下您的电话，以便我们及时回复您" value="" />
        </li>
        <li class="errow"></li>
        <li><input type="submit" value="提 交" class="btn" id="Certain" /></li>
    </ul>
    </form>
</div>
<div id="sells" class="pop-up">
    <div class="close"></div>
    <form id="addSellsForm" action="/feedBackManage/addFeedBack">
    <ul>
    	<input type="hidden" name="type" value="2">
        <li>
            <label>卖货需求<span class="col_red">(*必填)</span></label>
            <textarea name='content' placeholder="请详细填写卖货需求，包括药材品种、规格等级、数量等。" class="text-bg textarea"></textarea>
        </li>
        <li>
            <label>联系电话<span class="col_red">(*必填)</span></label>
            <input name='mobile' class="text-bg"
             placeholder="请留下您的电话，以便我们及时回复您" value="" />
        </li>
        <li class="errow"></li>
        <li><input type="submit" value="提 交" class="btn" id="Certain" /></li>
    </ul>
    </form>
</div>
<div id="supply" class="pop-up">
    <div class="close"></div>
    <form id="addSupplyForm" action="/feedBackManage/addFeedBack">
    <ul>
    	<input type="hidden" name="type" value="3">
        <li>
            <label>融资需求<span class="col_red">(*必填)</span></label>
            <textarea name='content' placeholder="请写下您的融资需求，包括药材品种、融资金额，融资期限。" 
            class="text-bg textarea"></textarea>
        </li>
        <li class="relative">
            <label>联系电话<span class="col_red">(*必填)</span></label>
            <input name='mobile' class="text-bg"
             placeholder="请留下您的电话，以便我们及时回复您" value="" />
        </li>
        <li class="errow"></li>
        <li><input type="submit" value="提 交" class="btn" id="Certain" /></li>
    </ul>
    </form>
</div>