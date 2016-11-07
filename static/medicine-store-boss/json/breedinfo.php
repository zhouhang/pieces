<?php 

	header("Content-Type: text/html; charset=UTF-8");

	sleep(0);//效果演示，该句可移除;

	$arr = array (
		'catname'=>"三七",
		'title'=>"白芍 安徽白芍 选货 过6-10号筛",
		'price'=>"380-400元/公斤",
		'sort'=>"100",
		'pictureUrl'=>"uploads/Koala.jpg"
	); 

	echo json_encode($arr);
?>