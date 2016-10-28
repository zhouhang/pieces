<?php 

	header("Content-Type: text/html; charset=UTF-8");

	sleep(0);//效果演示，该句可移除;

	$arr = array (
		'price'=>"100"
	); 

	$arr2 = array (
		'price'=> array(array(1,2,100), array(3,4,70),array(5,6,50))
	); 

	echo json_encode($arr2);
?>

