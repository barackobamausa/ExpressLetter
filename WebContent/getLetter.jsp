<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="com.wgh.actionForm.SendLetterForm"%>
<%@ page import="java.util.*"%>

<html>
<%
List list=(List)request.getAttribute("shortLetter");
%>
<head>
<title>��ҵ���š�������+�ʼ�</title>
<link href="CSS/style.css" rel="stylesheet">
</head>
<body>
<%@include file="navigation.jsp"%>
<table  width="778" border="0" align="center" cellspacing="0" cellpadding="0">
  <tr>
    <td valign="top" bgcolor="#FFFFFF"><table width="99%" height="510"  border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" class="tableBorder_gray">
  <tr>
    <td height="510" valign="top" style="padding:5px;"><table width="98%" height="487"  border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td height="22" valign="top" class="word_orange">��ǰλ�ã��շ����� &gt; ���ն��� &gt;&gt;&gt;</td>
      </tr>
      <tr>
        <td align="center" valign="top"><%
if(list==null || list.isEmpty()){
%>
          <table width="100%" height="30"  border="0" cellpadding="0" cellspacing="0">
            <tr>
              <td height="36" align="center">���޶���Ϣ��</td>
            </tr>
          </table>
          <%
}else{
  %>
 <table width="100%"  border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="84%">&nbsp;      </td>
<td width="16%">
      <a href="shortInfo.do?action=type"></a> </td>	  
  </tr>
</table>  
  <table width="91%"  border="1" cellpadding="0" cellspacing="0" bordercolor="#FFFFFF" bordercolordark="#FFFFFF" bordercolorlight="#54ABEF">
  <tr align="center" bgcolor="#e3F4F7">
    <td width="6%" height="26" bgcolor="#A8D8FC">���</td>
    <td width="19%" bgcolor="#A8D8FC">������</td>
    <td width="75%" bgcolor="#A8D8FC">��������</td>
  </tr>
<%
  for(int i=0;i<list.size();i++){
    String[] s=(String[])list.get(i);
	%> 
  <tr>
    <td align="center"> <%=i+1%></td>
    <td align="center"><%=s[0]%></td>
	    <td style="padding:5px;"><%=s[2]%></td>
  </tr>
<%
  }
}
%>  
</table></td>
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
