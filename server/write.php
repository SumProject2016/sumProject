<?php session_start();

$conn = mysql_connect("localhost", "root", "sumeng") or die(mysql_error());
@mysql_select_db("tag", $conn);
mysql_query("set names utf8");

$contentid = $_GET[contentid];
?>

<FORM ACTION="insert.php" METHOD="POST">
    <TABLE BORDER=1 WIDTH=350 align="center">
        <TR>
            <TD width="33%" nowrap="">닉네임</TD> <TD ><INPUT TYPE="TEXT" NAME="name"></TD>
            <TD width="35%" nowrap="">비밀번호</TD> <TD><INPUT TYPE="PASSWORD" NAME="pass"></TD>
        </TR>
        <TR>
            <TD COLSPAN=4><TEXTAREA NAME="content" COLS=65 ROWS=5></TEXTAREA></TD>
        </TR>
        <TR>
            <TD COLSPAN=4 align=right><INPUT TYPE="SUBMIT" VALUE=" 확인 "></TD>
            <INPUT TYPE = hidden value="<? echo $contentid; ?>" NAME="contentid"></TD>
        </TR>
    </TABLE>
</FORM>
<BR>
