<%@ page contentType="text/html; charset=gb2312" language="java" import="java.util.*" errorPage="" %>
<%@ page import="com.wgh.actionForm.InfoTypeForm"%>
<html>
<head>
<title>��ҵ���š�������+�ʼ�</title>
<link href="CSS/style.css" rel="stylesheet">
<script language="javascript">
	function check(){
		if(form1.content.value==""){
			alert("����������!");form1.content.focus();return false;
		}
	}
</script>
</head>
<%
List list=(List)request.getAttribute("typeQuery");
int typeId=0;
String typeName="";
%>
<body>
<%@include file="navigation.jsp"%>
<table width="778" border="0" align="center" cellspacing="0" cellpadding="0">
  <tr>
    <td valign="top" bgcolor="#FFFFFF"><table width="99%" height="510"  border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" class="tableBorder_gray">
  <tr>
    <td height="510" valign="top" style="padding:5px;"><table width="98%" height="504"  border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td height="39" valign="top" class="word_orange">��ǰλ�ã���Ϣ����� &gt; ���ö������ &gt; ��ӳ��ö��� &gt;&gt;&gt;</td>
      </tr>
      <tr>
        <td align="center" valign="top"><table width="80%"  border="0" cellpadding="0" cellspacing="0">
		<form action="shortInfo.do?action=shortInfoAdd" method="post" name="form1" onSubmit="return check()">
            <tr>
              <td width="16%" height="41" align="center">��&nbsp;&nbsp;&nbsp;��</td>
              <td width="84%"><select name="typeID" id="typeID">
			  <%for(int i=0;i<list.size();i++){
			  		InfoTypeForm t=(InfoTypeForm)list.get(i);
					typeId=t.getID();
					typeName=t.getName();
			  %>
                <option value="<%=typeId%>"><%=typeName%></option>
				<%}%>
              </select>
               </td>
            </tr>
            <tr>
              <td height="27" align="center">��&nbsp;&nbsp;&nbsp;�ݣ�</td>
              <td><textarea name="content" cols="60" rows="16" id="content"></textarea>              
                *</td>
            </tr>
            <tr>
              <td height="27" colspan="2" align="center"><input name="Submit" type="submit" class="btn_grey" value="�ύ">
                &nbsp;
                <input name="Submit2" type="reset" class="btn_grey" value="����">
&nbsp;
<input name="Submit3" type="button" class="btn_grey" value="����" onClick="window.location.href='shortInfo.do?action=shortInfoQuery';"></td>
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
