<?php 
	//$_POST["param"] 获取文本框的值;
	//$_POST["name"]  获取文本框的name属性值，通过该值来判断是哪个文本框请求处理，这样当有多个实时验证请求时可以指定同一个文件处理;
	// sleep(1);//效果演示，该句可移除;
	//echo "y" //验证通过输出小写字母"y"，出错则输出相应错误信息;
	$list1 = array(
		"name" => "巴戟肉",
        "spec" => "个",
        "level" => "1",
        "originOf" => "湖北省"
	);
	$list2 = array(
		"name"=> "巴戟肉",
        "spec"=> "碎",
        "level"=> "2",
        "originOf"=> "安徽省"
	);
	$list3 = array(
		"name" => "巴戟肉",
        "spec" => "个",
        "level" => "1",
        "originOf" => "湖北省"
	);
	$list4 = array(
		"name"=> "巴戟肉",
        "spec"=> "碎",
        "level"=> "2",
        "originOf"=> "安徽省"
	);
	$list5 = array(
		"name" => "巴戟肉",
        "spec" => "个",
        "level" => "1",
        "originOf" => "湖北省"
	);
	$list6 = array(
		"name"=> "巴戟肉",
        "spec"=> "碎",
        "level"=> "2",
        "originOf"=> "安徽省"
	);
	$list7 = array(
		"name" => "巴戟肉",
        "spec" => "个",
        "level" => "1",
        "originOf" => "湖北省"
	);
	$list8 = array(
		"name"=> "巴戟肉",
        "spec"=> "碎",
        "level"=> "2",
        "originOf"=> "安徽省"
	);
	$list9 = array(
		"name" => "巴戟肉",
        "spec" => "个",
        "level" => "1",
        "originOf" => "湖北省"
	);
	$list10 = array(
		"name"=> "巴戟肉",
        "spec"=> "碎",
        "level"=> "2",
        "originOf"=> "安徽省"
	);
	$list11 = array(
		"name" => "巴戟肉",
        "spec" => "个",
        "level" => "1",
        "originOf" => "湖北省"
	);
	$list12 = array(
		"name"=> "巴戟肉",
        "spec"=> "碎",
        "level"=> "2",
        "originOf"=> "安徽省"
	);
	$response = array(
		"status" => "success",
		"msg" => "",
		"list" => array(
			$list1,
			$list2,
			$list3,
			$list4,
			$list5,
			$list6,
			$list7,
			$list8,
			$list9,
			$list10,
			$list11,
			$list12
		)
	);
	// echo $response;
	print json_encode($response);
?>