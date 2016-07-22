<?php session_start();
$conn = mysql_connect("localhost", "root", "sumeng") or die(mysql_error());
@mysql_select_db("tag", $conn);
mysql_query("set names utf-8");

$query = "SELECT * FROM addr_info";
$result = mysql_query($query, $conn);

$total = mysql_affected_rows();

$list = array();

while($resultline = mysql_fetch_row($result)) {
  $list[] = $resultline;
}

$result_array = array();

foreach($list as $val) {
    $array = array();
    $array['addr1'] = $val[0];
    $array['addr2'] = $val[1];
    $array['areacode'] = $val[2];
    $array['cat1'] = $val[3];
    $array['cat2'] = $val[4];
    $array['cat3'] = $val[5];
    $array['contentid'] = $val[6];
    $array['contenttypeid'] = $val[7];
    $array['createdtime'] = $val[8];
    $array['firstimage'] = $val[9];
    $array['firstimage2'] = $val[10];
    $array['mapx'] = $val[11];
    $array['mapy'] = $val[12];
    $array['mlevel'] = $val[13];
    $array['modifiedtime'] = $val[14];
    $array['readcount'] = $val[15];
    $array['sigungucode'] = $val[16];
    $array['tel'] = $val[17];
    $array['title'] = $val[18];
    $array['zipcode'] = $val[19];

    $result_array[] =  $array;
}

echo json_encode($result_array);
exit;

?>