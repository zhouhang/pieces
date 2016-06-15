<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>珍药材网上支付</title>
</head>
<body>
<!-- 	<table width="600px" align="center"> -->
<!-- 		<tr> -->
<!-- 			<td width="150px">九州通珍药材</td> -->
<!-- 			<td width="250px">交易订单号：123456798</td> -->
<!-- 			<td width="200px">金额：5200036.00</td> -->
<!-- 		</tr> -->
<!-- 	</table> -->
	<table align="center">
		<form action="/test" method="post">
			交易订单号<input type="text" id="txOrder" name="orderId" value="${orderNo!''}"/><br/>
			交易金额<input type="text" name="amount" value=""/>单位(元)<br/>
			付款人<input type="text" name="userId" value=""/><br/>
			收款人<input type="text" name="recieveId" value=""/><br/>
			付款标识<input type="text" name="sourceSys" value="0"><br/>
			商品名称<input type="text" name="orderTitle" value="双汇王中王"><br/>
			款项<select name="amtType">
				<option value="0">保证金</option>
				<option value="1">尾款</option>
				<option value="2">全款</option>
			</select><br/>
<!-- 			签名<input type="text" name="signData" value=""><br/> -->
			<input type="submit" name="sub" value="提交"/><br/>
		</form>
	</table>
</body>
<script type="text/javascript">
	document.getElementById("payOrder").value="100" + Math.floor(Math.random()*100000000);
</script>
</html>