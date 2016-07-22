<?php session_start();
$conn = mysql_connect("localhost", "root", "sumeng") or die(mysql_error());
@mysql_select_db("tag", $conn);
mysql_query("set names utf8");

$wdate=date("Y-m-d (H:i)");

$contentid = $_POST[contentid];

$query = "INSERT INTO memo (name, pass, content, wdate, contentid) ";
$query .= " VALUES ('$_POST[name]', '$_POST[pass]', '$_POST[content]', '$wdate', '$_POST[contentid]')";
$result = mysql_query($query, $conn);
?>

<script>
    //alert("리뷰가 등록되었습니다.");
    location.href="/item_detail.php?contentid=<?=$_POST[contentid]?>";
</script>

