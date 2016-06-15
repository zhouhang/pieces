<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>珍药材-聚好药商，卖珍药材</title>
	<style type="text/css">
		body{
			text-align:center;
		}
	</style>
	<script type="text/javascript">
		var i=0;
		function time(){
			var _t = document.getElementById("t");
			_t.innerHTML = i;
			i++;
			setTimeout("time()",1000);	
		}
		function init(){
			time();
			document.getElementById("pay_form").submit();;
		}
	</script>
</head>
<body onload="javascript:init();" >
	<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
	<table align="center">
		<tr>
			<td align="center">
				<span>正在连接银行,请稍等片刻···&nbsp;<span id="t" style="color:red;font-weight: 600;"></span>&nbsp;秒</span>
			</td>
		</tr>
	</table>
	${form }
</body>
</html>