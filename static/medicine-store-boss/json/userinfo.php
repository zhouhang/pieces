<?php 

	header("Content-Type: text/html; charset=UTF-8");

	sleep(1);//效果演示，该句可移除;

	$arr = array (
		'mobile'=>"18801285391",
		'name'=>"王先生",
		'type'=>"采购经理",
		'company'=>"沪谯药业",
		'notes'=>"用户备注用户备注用户备注用户备注用户备注用户备注用户备注用户备注用户备注用户备注用户备注用户备注用户备注用户备注", 
		"date"=> "2016-10-11 18：21"
	); 


	echo json_encode($arr);
?>