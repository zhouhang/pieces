<?php
Header("Content-type: application/json");
// sleep(1);
$base64_string = $_POST['img'];

$savename = uniqid().'.jpg'; // 后缀名没有做自动匹配，直接写死了
$savename = $_POST['fileName'];
$savepath = 'temp/'.$savename; 
$image = base64_to_img( $base64_string, $savepath );
if($image){
	$response = array(
		"status" => '1',
		"msg" => '上传成功',
		"url" => 'php/'.$image
	  );
}else{
	$response = array(
		"status" => '0',
		"msg" => '上传失败'
	  );
} 
function base64_to_img( $base64_string, $output_file ) {
    $ifp = fopen( $output_file, "wb" ); 
    fwrite( $ifp, base64_decode( $base64_string) ); 
    fclose( $ifp ); 
    return( $output_file ); 
} 
print json_encode($response);
?>