<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="com.wgh.actionForm.ShortInfoForm"%>
<%@ page import="com.wgh.actionForm.InfoTypeForm"%>
<%@ page import="java.util.*"%>

<html>
<%
List list_s=(List)request.getAttribute("shortInfoQuery");
List list=(List)request.getAttribute("typeQuery");
int typeId=0;
String typeName="";
%>
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
<body>
<%@include file="navigation.jsp"%>
<table width="778" border="0" align="center" cellspacing="0" cellpadding="0">
  <tr>
    <td valign="top" bgcolor="#FFFFFF"><table width="99%" height="510"  border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" class="tableBorder_gray">
  <tr>
    <td height="510" valign="top" style="padding:5px;"><table width="98%" height="504"  border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td height="39" valign="top" class="word_orange">��ǰλ�ã���Ϣ����� &gt; ���ö������ &gt; �޸ĳ��ö��� &gt;&gt;&gt;</td>
      </tr>
      <tr>
        <td align="center" valign="top"><table width="85%"  border="0" cellpadding="0" cellspacing="0">
		<form name="form1" action="shortInfo.do?action=shortInfoModify" method="post" onSubmit="return check()">
		<%if(list_s.size()>0){
		ShortInfoForm s=(ShortInfoForm)list_s.get(0);
		%>
            <tr>
              <td width="18%" height="41" align="center">��&nbsp;&nbsp;&nbsp;��</td>
              <td width="82%"><select name="typeID" id="typeID">
                  <%for(int i=0;i<list.size();i++){
			  		InfoTypeForm t=(InfoTypeForm)list.get(i);
					typeId=t.getID();
					typeName=t.getName();
			  %>
                  <option value="<%=typeId%>" <%if(s.getTypeID()==t.getID()){%> selected<%}%>><%=typeName%></option>
                  <%}%>
                </select>
                <input name="ID" type="hidden" id="ID" value="<%=s.getID()%>"></td>
            </tr>
            <tr>
              <td height="27" align="center">��&nbsp;&nbsp;&nbsp;�ݣ�</td>
              <td><textarea name="content" cols="60" rows="16" id="content"><%=s.getContent()%></textarea>
                *</td>
            </tr>
            
			<%}else{%>
				<script language="javascript">
				window.location.href="shortInfo.do?action=shortInfoQuery";
				</script>
			<%}%>
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
