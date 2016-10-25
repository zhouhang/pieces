<?php 

	header("Content-Type: text/html; charset=UTF-8");

	sleep(1);//效果演示，该句可移除;

	$arr = array (
		'username'=>"laowang",
		'role'=>"2",
		'password'=>"******",
		'name'=>"老王",
		'mobile'=>"15012345678", 
		'email'=>"15012345678@qq.com"
	); 
	echo json_encode($arr);
?>