<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="com.wgh.actionForm.CustomerForm"%>
<%@ page import="java.util.*"%>

<html>
<%
List list=(List)request.getAttribute("customerQuery");
%>
<head>
<title>��ҵ���š�������+�ʼ�</title>
<link href="CSS/style.css" rel="stylesheet">
<script language="javascript">
function checkform(myform){
	if(myform.name.value==""){
		alert("������ͻ�ȫ�ƣ�");myform.name.focus();return false;
	}
	if(myform.address.value==""){
		alert("�������ַ��");myform.address.focus();return false;
	}
	if(myform.postcode.value==""){
		alert("�������������룡");myform.postcode.focus();return false;
	}
	if(myform.linkName.value==""){
		alert("��������ϵ�ˣ�");myform.linkName.focus();return false;
	}
	if(myform.mobileTel.value==""){
		alert("�������ֻ����룡");myform.mobileTel.focus();return false;
	}
	if(!checkTel(myform.mobileTel.value)){
		alert("��������ֻ������������������룡");myform.mobileTel.focus();return false;
	} 
	if(myform.email.value==""){
		alert("���������䣡");myform.email.focus();return false;
	}
	if(!checkemail(myform.email.value)){
		alert("������������������������룡");myform.email.focus();return false;
	}
	return true;		
}
</script>
</head>
<body>
<%@include file="navigation.jsp"%>
<table  width="778" border="0" align="center" cellspacing="0" cellpadding="0">
  <tr>
    <td valign="top" bgcolor="#FFFFFF"><table width="99%" height="510"  border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" class="tableBorder_gray">
  <tr>
    <td height="510" valign="top" style="padding:5px;"><table width="98%" height="504"  border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td height="39" valign="top" class="word_orange">��ǰλ�ã���Ƭ�й��� &gt; �ͻ����� &gt; ��ӿͻ���Ϣ &gt;&gt;&gt;</td>
      </tr>
      <tr>
        <td align="center" valign="top"><table width="68%"  border="0" cellpadding="0" cellspacing="0">
		<form name="form1" action="customer.do?action=customerAdd" method="post" onSubmit="return checkform(form1)">
            <tr>
              <td width="20%" height="27" align="center">�ͻ�ȫ�ƣ�</td>
              <td width="80%"><input name="name" type="text" id="name" size="50">
                *</td>
            </tr>
            <tr>
              <td height="27" align="center">��������</td>
              <td><input name="area" type="text" id="area" size="30"></td>
            </tr>
            <tr>
              <td height="27" align="center">��&nbsp;&nbsp;&nbsp;&nbsp;ַ��</td>
              <td><input name="address" type="text" id="address" size="60">
                *</td>
            </tr>
            <tr>
              <td height="27" align="center">������룺</td>
              <td><input name="postcode" type="text" id="postcode" size="6">
                *</td>
            </tr>
            <tr>
              <td height="27" align="center">�� ϵ �ˣ�</td>
              <td><input name="linkName" type="text" id="linkName" size="20">
                *</td>
            </tr>
            <tr>
              <td height="27" align="center">��&nbsp;&nbsp;&nbsp;&nbsp;����</td>
              <td><input name="mobileTel" type="text" id="mobileTel" size="30">
                *</td>
            </tr>
            <tr>
              <td height="27" align="center">��&nbsp;&nbsp;&nbsp;&nbsp;�䣺</td>
              <td><input name="email" type="text" id="email" size="50">
                *</td>
            </tr>
            <tr>
              <td height="27" align="center">�������У�</td>
              <td><input name="bankName" type="text" id="bankName" size="20"></td>
            </tr>
            <tr>
              <td height="27" align="center">�����˺ţ�</td>
              <td><input name="bankNo" type="text" id="bankNo" size="30"></td>
            </tr>
            <tr>
              <td height="27" colspan="2" align="center"><input name="Submit" type="submit" class="btn_grey" value="�ύ">
                &nbsp;
                <input name="Submit2" type="reset" class="btn_grey" value="����">
&nbsp;
<input name="Submit3" type="button" class="btn_grey" value="����" onClick="window.location.href='customer.do?action=customerQuery';"></td>
              </tr>
			  </form>
          </table>
          </td>
      </tr>
    </table>
</td>
  </tr>
</table>
<%@ include file="copyright.jsp"%>
</body>
</html>
