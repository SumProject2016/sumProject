<?php session_start();

$conn = mysql_connect("localhost", "root", "sumeng") or die(mysql_error());
@mysql_select_db("tag", $conn);
mysql_query("set names utf-8");

$query = "SELECT * FROM memo WHERE memo.contentid = '$_GET[contentid]'";
$contentid = $_GET[contentid];
$result = mysql_query($query, $conn);

$total = mysql_affected_rows();

$pagesize=5;
?>


<?

echo "<center><a href='write.php?contentid=$contentid'>리뷰 작성</a></center>";

for($i=$_GET[no] ; $i < $_GET[no]+$pagesize ; $i++) {

    if ($i < $total)
    {
        mysql_data_seek($result,$i);
        $row = mysql_fetch_array($result);
        ?>

        <style type="text/css">
            .line1{
                border-bottom: 2px solid red;}
           
        </style>

        <TABLE WIDTH=350 BORDER=1 align="center" style="border-collapse:separate;border:1px ;">
            <TR>

                <TD width="38%" nowrap="" ><?=$row[name]?></TD>
                <TD ><?=$row[wdate]?></TD>
                <TD WIDTH="10%" ><a href="delete.php?id=<?=$row[id]?>">del</a></TD>
            </TR>
            <TR>
                <TD class="line1" COLSPAN=4 ><?=$row[content]?></TD>
            </TR>

        </TABLE>
        <?
    } //end if
} //end for
?>

<?
$prev = $_GET[no] - $pagesize ; // 이전 페이지는 시작 글에서 $scale을 뺀 값부터
$next = $_GET[no] + $pagesize ; // 다음 페이지는 시작 글에서 $scale을 더한 값부터

if ($prev >= 0) {
    echo "<p align='center'> <a href='{$_SERVER['PHP_SELF']}?no=$prev'>이전</a></p>";
}

if ($next < $total) {
    echo "<p align='center'><a href='{$_SERVER['PHP_SELF']}?no=$next'>다음</a></center></p>";
}
?>
