<?php
/*
*	!!! THIS IS JUST AN EXAMPLE !!!, PLEASE USE ImageMagick or some other quality image processing libraries
*/

// sleep(1);

$allowedExts = array("gif", "jpeg", "jpg", "png"); //上传文件类型列表
$max_file_size = 1024*1024; //上传文件大小限制, 单位BYTE
$imagePath = "temp/";

$temp = explode(".", $_FILES["img"]["name"]);
$extension = strtolower(end($temp));

if (!file_exists($imagePath)) {
    mkdir($imagePath);
}

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
	if (!in_array($extension, $allowedExts)) {
		$response = array(
			"status" => 'error', 
			"title" => '不允许的文件类型', 
			"message" => '支持 jpg、jepg、png、gif等格式图片文件'
		);

	} else if ($_FILES["img"]["size"] > $max_file_size) {
		$response = array(
			"status" => 'error', 
			"title" => '图片太大', 
			"message" => '请上传小于1M的图片'
		);

	} else {
		$filename = $_FILES["img"]["tmp_name"];
	    $imgage = $imagePath . "temp_" . md5(uniqid()) . "." . $extension;

	    if (move_uploaded_file ($filename, $imgage)) {
	    	$image_size = getimagesize($imgage);
			$response = array(
		    	"status" => 'success' 
		    	,"title" => '上传成功' 
				,"message" => '图片已上传成功'
		    	,"url" => $imgage
		    	,"width" => $image_size[0] 
		    	,"height" => $image_size[1]
	    	);
	    } else {
	    	$response = array(
		        "status" => 'error', 
				"title" => 'PHP限制图片太大', 
				"message" => '图片不能大于2M'
			);
	    }


	}

} else {
	$response = array(
    	"status" => 'error', 
    	"title" => '上传失败', 
		"message" => '只能POST提交'
    );
}

print json_encode($response);

