<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>手动回调</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
</head>

<body>
	<form id="form1" action="/ucs/payCallBackByManual" method="post">
		<div align="center">
			<table style="border: 1px">
				<tr>
					<th colspan="2">手动回调</th>
				</tr>
				<tr>
					<td>CState:</td>
					<td><input type="text" name="CState" value="1"></td>
				</tr>
				<tr>
					<td>PaymentNo:</td>
					<td><input type="text" name="PaymentNo" value=""></td>
				</tr>
				<tr>
					<td>Amount:</td>
					<td><input type="text" name="Amount" value=""></td>
				</tr>
				<tr>
					<td>OrderNo:</td>
					<td><input type="text" name="OrderNo" value=""></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="提交"></td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>
