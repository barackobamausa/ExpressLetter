<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
<html>
<head>
<title>��ӹ���Ա��Ϣ</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link href="CSS/style.css" rel="stylesheet">
</head>
<script language="javascript">
function check(form){
	if(form.name.value==""){
		alert("���������Ա����!");form.name.focus();return;
	}
	if(form.pwd.value==""){
		alert("���������Ա����!");form.pwd.focus();return;
	}
	if(form.pwd1.value==""){
		alert("��ȷ�Ϲ���Ա����!");form.pwd1.focus();return;
	}		
	if(form.pwd.value!=form.pwd1.value){
		alert("����������Ĺ���Ա���벻һ�£�����������!");form.pwd.focus();return;
	}
	form.submit();
}
</script>
<body>
<table width="292" height="175" border="0" cellpadding="0" cellspacing="0" background="Images/subBG.jpg">
  <tr>
    <td valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="12%" height="29">&nbsp;</td>
        <td width="85%">&nbsp;</td>
        <td width="3%">&nbsp;</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td><table width="100%" height="122"  border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td height="122" align="center" valign="top">	<form name="form1" method="post" action="manager.do?action=managerAdd">
	<table height="116"  border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="81" height="30" align="center">����Ա���ƣ�</td>
        <td width="167">
          <input name="name" type="text">        </td>
      </tr>
      <tr>
        <td height="28" align="center">����Ա���룺</td>
        <td><input name="pwd" type="password" id="pwd"></td>
      </tr>
      <tr>
        <td height="28" align="center">ȷ�� &nbsp;���룺</td>
        <td><input name="pwd1" type="password" id="pwd1"></td>
      </tr>
      <tr>
        <td height="30" align="center">&nbsp;</td>
        <td><input name="Button" type="button" class="btn_grey" value="����" onClick="check(form1)">
&nbsp;
<input name="Submit2" type="button" class="btn_grey" value="�ر�" onClick="window.close();"></td>
      </tr>
    </table>
	</form></td>
          </tr>
        </table></td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>
