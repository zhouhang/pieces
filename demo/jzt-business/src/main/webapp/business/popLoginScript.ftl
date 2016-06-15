<script type="text/javascript" src="${RESOURCE_JS}/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
	//修改用户头
	function load(){
		window.parent.document.getElementById('u').innerHTML='<span id="u"><span class="pr20">您好，${username!""}</span> 欢迎来到珍药材！ <a href="${brokerServer.passport}/logout?service=${JOINTOWNURL}/logout">[退出]</a>'
	}
	window.onload = load();
	
	$(function(){
		//关闭弹窗
		$(window.parent.document).find('.logon-box .close').click();
		//调用父窗口的处理方法
		parent.parent.popCallBack();
	});
</script>