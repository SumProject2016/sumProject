<?php
if ($_GET['mode']!="delete")
{
?>
<HTML>
<FORM METHOD="POST"
      ACTION="<?=$_SERVER['PHP_SELF']?>?id=<?=$_GET[id]?>&mode=delete">
    <TABLE>
        <TR>
            <TD>비밀번호</TD>
            <TD><INPUT TYPE="PASSWORD" NAME="pass"></TD>
            <TD><INPUT TYPE="SUBMIT" VALUE=" 확 인 "></TD>
        </TR>
    </TABLE>
    <?
    exit;
    } //end if

    $conn = mysql_connect("localhost", "root", "sumeng") or die(mysql_error());
    @mysql_select_db("tag", $conn);
    @mysql_query("set names utf8");

    $query = "SELECT pass FROM memo WHERE id='$_GET[id]'";
    $result = mysql_query($query, $conn);
    $row = mysql_fetch_array($result);

    global $site_link;
    $contentid = $_POST[contentid];


    if ($row[pass] == $_POST[pass])
    {
        $query = "DELETE FROM memo WHERE id='$_GET[id]'";
        $result = mysql_query($query, $conn);
    } else
    {
?>
        <script>
            alert("패스워드가 일치하지 않습니다.");
            location.href="/item_detail.php?contentid=<?=$_POST[contentid]?>";
        </script>
<?
    }
    ?>

    <script>
        alert("글이 삭제되었습니다.");
        location.href="/item_detail.php?contentid=<?=$contentid?>";
    </script>