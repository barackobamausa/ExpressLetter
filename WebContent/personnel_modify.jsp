<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="com.wgh.actionForm.PersonnelForm"%>
<%@ page import="java.util.*"%>

<html>
<%
List list=(List)request.getAttribute("personnelQuery");
%>
<head>
<title>��ҵ���š�������+�ʼ�</title>
<link href="CSS/style.css" rel="stylesheet">
<script language="javascript">
function checkform(myform){
	if(myform.name.value==""){
		alert("������Ա��������");myform.name.focus();return false;
	}
	if(myform.birthday.value==""){
		alert("������������ڣ�");myform.birthday.focus();return false;
	}
	if(CheckDate(myform.birthday.value)){
		alert("������ĳ��������������������룡");myform.birthday.focus();return false;
	}		
	if(myform.school.value==""){
		alert("�������ҵѧУ��");myform.school.focus();return false;
	}
	if(myform.place.value==""){
		alert("������ְλ��");myform.place.focus();return false;
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
<table width="778" border="0" align="center" cellspacing="0" cellpadding="0">
  <tr>
    <td valign="top" bgcolor="#FFFFFF"><table width="99%" height="510"  border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" class="tableBorder_gray">
  <tr>
    <td height="510" valign="top" style="padding:5px;"><table width="98%" height="504"  border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td height="39" valign="top" class="word_orange">��ǰλ�ã���Ƭ�й��� &gt; Ա������ &gt; �޸�Ա����Ϣ &gt;&gt;&gt;</td>
      </tr>
      <tr>
        <td align="center" valign="top"><table width="68%"  border="0" cellpadding="0" cellspacing="0">
		<form name="form1" action="personnel.do?action=personnelModify" method="post" onSubmit="return checkform(form1)">
		<%if(list.size()==1){
		PersonnelForm p=(PersonnelForm)list.get(0);
		%>
            <tr>
              <td width="20%" height="27" align="center">Ա��������</td>
              <td width="80%"><input name="name" type="text" id="name" value="<%=p.getName()%>" size="20">
                *
                  <input name="ID" type="hidden" id="ID" value="<%=p.getID()%>"></td>
            </tr>
            <tr>
              <td height="27" align="center">��&nbsp;&nbsp;&nbsp;��</td>
              <td><input name="sex" type="radio" class="noborder" value="��" <%if(p.getSex().equals("��")){%> checked<%}%>>
                ��
                <input name="sex" type="radio" class="noborder" value="Ů" <%if(p.getSex().equals("Ů")){%> checked<%}%>>
                Ů</td>
            </tr>
            <tr>
              <td height="27" align="center">�������ڣ�</td>
              <td><input name="birthday" type="text" id="birthday" value="<%=p.getBirthday()%>" size="20">
                �����ڸ�ʽΪ��YYYY-MM-DD�� </td>
            </tr>
            <tr>
              <td height="27" align="center">��ҵѧУ��</td>
              <td><input name="school" type="text" id="school" value="<%=p.getSchool()%>" size="50">
                *</td>
            </tr>
            <tr>
              <td height="27" align="center">ѧ&nbsp;&nbsp;&nbsp;&nbsp;����</td>
              <td><input name="education" type="text" id="education" value="<%=p.getEducation()%>" size="20"></td>
            </tr>
            <tr>
              <td height="27" align="center">��ѧרҵ��</td>
              <td><input name="specialty" type="text" id="specialty" value="<%=p.getSpecialty()%>" size="30"></td>
            </tr>
            <tr>
              <td height="27" align="center">ְ&nbsp;&nbsp;&nbsp;&nbsp;λ��</td>
              <td><input name="place" type="text" id="place" value="<%=p.getPlace()%>" size="50">
                *</td>
            </tr>
            <tr>
              <td height="27" align="center">�ֻ����룺</td>
              <td><input name="mobileTel" type="text" id="mobileTel" value="<%=p.getMobileTel()%>" size="20">
                *</td>
            </tr>
            <tr>
              <td height="27" align="center">��&nbsp;&nbsp;&nbsp;&nbsp;�䣺</td>
              <td><input name="email" type="text" id="email" value="<%=p.getEmail()%>" size="50">
                *</td>
            </tr>
			<%}else{%>
				<script language="javascript">
				window.location.href="personnel.do?action=personnelQuery";
				</script>
			<%}%>
            <tr>
              <td height="27" colspan="2" align="center"><input name="Submit" type="submit" class="btn_grey" value="�ύ">
                &nbsp;
                <input name="Submit2" type="reset" class="btn_grey" value="����">
                &nbsp;
                <input name="Submit3" type="button" class="btn_grey" value="����" onClick="window.location.href='personnel.do?action=personnelQuery';"></td>
              </tr>
			  </form>
          </table>
          </td>
      </tr>
    </table>
</td>
  </tr>
</table></td>
  </tr>
</table>
<%@ include file="copyright.jsp"%>
</body>
</html>
