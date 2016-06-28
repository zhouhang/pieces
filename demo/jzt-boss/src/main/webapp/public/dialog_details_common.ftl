<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
</head>
<!-- 订单详情 挂牌详情 公用JS层代码  by Calvin.Wh-->
<body>
	<script>
		$(function() {
			//查看订单详情
			$('td a[name=nub]').on('click',function(){
				getOrderInfoById(this);
			});
			$('#orderDetails .close').on('click',function(){
		        $('#orderDetails').hide();
		        $('#orderDetails .orderDetail-box').empty();
		        $('.bghui').remove();
		    }); 
			
			//挂牌信息
			$('td a[name=sell]').on('click',function(){
			       getListingInfo(this);
			});
			//关闭挂牌信息弹层
			$('#listingDetail .close').on('click',function(){
		        $('#listingDetail').hide();
		        $('#listingDetail .sellDetail-box').empty();
		        $('.bghui').remove();
		    }); 
			
		})
		
		//获取订单详情
		function getOrderInfoById(obj) {
			var _this = $(obj), orderId = _this.data("id"), reclick = _this
					.data("reclick");
			if (reclick == "n") {
				_this.data("reclick", "y");
			} else {
				return false;
			}
			var imgServer = "${RESOURCE_IMG_UPLOAD}";
			$.ajax({
				type : "POST",
				url : "/bossorder/info",
				data : {
					orderId : orderId
				},
				dataType : "json",
				success : function(data) {
					if (data.state == "success") {
						var orderInfo = parseJson(data.result);
						var html = '';
						var stepCount = "";
						if ("0" == orderInfo.orderState) {
							//已摘牌
							stepCount = "1";
						}
						if ("3" == orderInfo.orderState) {
							//已付保证金
							stepCount = "2";
						}
						if ("5" == orderInfo.orderState) {
							//已备货
							stepCount = "2";
						}
						if ("4" == orderInfo.orderState) {
							//已付款
							stepCount = "3";
						}
						if ("1" == orderInfo.orderState) {
							//交易完成
							stepCount = "4";
						}
						if ("2" != orderInfo.orderState
								&& "-1" != orderInfo.orderState) {
							//交易取消,交易关闭,不显示该DIV
							html += '<div class="proses"><span class="step'+ stepCount +'"></span></div>';
							$('#orderDetails .orderDetail-box').removeClass('tempHei');
						} else {
							//弹层显示不同高度
							$('#orderDetails .orderDetail-box').addClass('tempHei');
						}
						html += '<p class="message"><strong>订单信息：</strong>&nbsp;订单编号：'
								+ orderInfo.orderId
								+ '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;摘牌日期：'
								+ orderInfo.orderDate + '</p>';
						html += '<table class="table" cellpadding="0" cellspacing="0">';
						html += '<tr>';
						html += '<th width="40%">药材</th>';
						html += '<th width="15%">单价</th>';
						html += '<th width="15%">订单数量</th>';
						html += '<th width="15%">成交数量</th>';
						html += '<th width="15%">商品总价</th>';
						html += '</tr>';
						html += '<tr>';
						html += '<td class="intro">';
						html += '<span><img src="'+ imgServer + '/' + orderInfo.goodsPic +'" /></span>';
						html += '<h3>' + orderInfo.goodsTitle + '</h3>';
						html += '</td>';
						html += '<td align="center">'
								+ orderInfo.unitPrice + '元/'
								+ orderInfo.wlunit + '</td>';
						html += '<td align="center">'
								+ orderInfo.amount + orderInfo.wlunit
								+ '</td>';
						html += '<td align="center">'
								+ orderInfo.volume + orderInfo.wlunit
								+ '</td>';
						html += '<td align="center">'
								+ orderInfo.totalPrice + '元</td>';
						html += '</tr>';
						html += '</table>';
						html += '<div class="pay-cash" align="right">实际付款：<strong> '
								+ orderInfo.actualPayment
								+ '</strong> 元</div>';
						$('#orderDetails .orderDetail-box').append(html);
						bghui();
	    				$('#orderDetails').show();
					} else {
						customAlert(data.result);
					}
					_this.data("reclick", "n");
				},
				error : function(textStatus) {
					customAlert("操作失败！");
					_this.data("reclick", "n");
				}
			});
		}

		//获取挂牌详情
		function getListingInfo(obj){
		var _this = $(obj),
    		listingId = _this.data("id"),
    		reclick = _this.data("reclick");
    	if(reclick == "n") {
    		_this.data("reclick", "y");
    	} else {
    		return false;
    	}
		var imgServer = "${RESOURCE_IMG_UPLOAD}";
    	$.ajax({
    		type:"POST",
    		url:"/busiListingQuery/listingInfo",
    		data:{listingId:listingId},
    		dataType:"json",
    		success:function(data){
    			if(data.state=="success"){
    				bghui();
    				$('#listingDetail').show();
    				var listingInfo = parseJson(data.result);
    				var html = '';
    				html += '<div class="box-1 fl">';
    				html += '<ul>';
    				html += '<li><label>仓单编号：</label><span>' + listingInfo.wlid + '</span></li>';
    				html += '<li><label>标    题：</label><span>' + listingInfo.title + '</span></li>';
    				html += '<li><label>仓单总量/可挂数量：</label><span>' + listingInfo.wltotal + listingInfo.dictvalue + '/' + listingInfo.wlsurplus + listingInfo.dictvalue + '</span></li>';
    				html += '<li><label>挂牌重量：</label><span>' + listingInfo.listingamount + listingInfo.dictvalue + '</span></li>';
    				html += '<li><label>挂牌价格：</label><span>' + listingInfo.lowunitprice +'元</span></li>';
    				html += '<li><label>最低起订：</label><span>' + listingInfo.minsales + listingInfo.dictvalue + '</span></li>';
    				html += '<li><label>包装规格：</label><span>' + listingInfo.packingway +'</span></li>';
    				html += '<li><label>挂牌期限：</label><span>' + listingInfo.listingtimelimit +'天</span></li>';
    				if(0 == listingInfo.hasbill){
    					html += '<li><label>能否提供发票：</label><span>不提供</span></li>';
    				} else {
    					html += '<li><label>能否提供发票：</label><span>提供</span></li>';
    					html += '<li><label>提供发票单价：</label><span>' + listingInfo.billprice + '元/' + listingInfo.dictvalue + '</span></li>';
    				}
    				html += '</ul>';
    				html += '</div>';
    				html += '<div class="box-2 fl">';
    				html += '<ul>';
    				if(listingInfo.piclist && listingInfo.piclist.length > 0){
	    				html += '<li><span><img src="' + imgServer + '/' + listingInfo.piclist[0].path + '"/> </span><p>散货照片</p></li>';
	    				html += '<li><span><img src="' + imgServer + '/' + listingInfo.piclist[1].path + '"/> </span><p>细节照1</p></li>';
	    				html += '<li><span><img src="' + imgServer + '/' + listingInfo.piclist[2].path + '"/> </span><p>细节照2</p></li>';
	    				html += '<li><span><img src="' + imgServer + '/' + listingInfo.piclist[3].path + '"/> </span><p>细节照3</p></li>';
	    				html += '<li><span><img src="' + imgServer + '/' + listingInfo.piclist[4].path + '"/> </span><p>包装照</p></li>';
	    				html += '<li><span><img src="' + imgServer + '/' + listingInfo.piclist[5].path + '"/> </span><p>堆垛照</p></li>';
    				}
    				html += '</ul>';
    				html += '</div>';
    				html += '<div class="box-3 clearfix">';
    				html += '<h3 class="title">药材详情</h3>';
    				html += '<p>' + listingInfo.content + '</p>';
    				html += '</div>';
    				$('#listingDetail .sellDetail-box').append(html);
    			} else {
    				tips(data.result);
    			}
    			_this.data("reclick", "n");
    		},
    		error:function(textStatus){
    			tips("操作失败！");
    			_this.data("reclick", "n");
    		}
    	});
	}
		

		//自定义的alert框
		function customAlert(str) {
			bghui();
			Alert({
				str : str,
				buttons : [ {
					name : '确定',
					classname : 'btn-blue',
					ev : {
						click : function() {
							disappear();
							$(".bghui").remove();
						}
					}
				} ]
			});
		}

		function parseJson(text) {
			try {
				return JSON.parse(text);//ie 89 ff ch
			} catch (e) {
				return eval('(' + text + ')'); //ie7
			}
		}
	</script>
</body>
</html>