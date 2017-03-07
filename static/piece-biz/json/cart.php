<?php
include("conn.php");//引入链接数据库
$list   		= 	array();
if (isset($_REQUEST['ids']) && $_REQUEST['ids'] != '') {
	$id 			= 	$_REQUEST['ids'];
	$q 				= 	"select * from commodity where id in(".$id.")";
	$rs 			= 	mysql_query($q, $conn);
	while($row = mysql_fetch_array($rs, MYSQL_ASSOC)){
		$list[] = array(
			'id' => $row['id'],
			'name' => $row['name'],
			'level' => $row['level'],
			'origin' => $row['origin_of']
		);
	}
	$status = 'y';
} else {
	$status = 0;
}
$data = array('data' => $list, 'status' => $status);
mysql_close(); 
echo json_encode($data);
?>	
