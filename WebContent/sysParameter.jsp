<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="com.wgh.actionForm.ParameterForm"%>
<%@ page import="java.util.*"%>

<html>
<%
List list=(List)request.getAttribute("parameterQuery");
int ID=1;
String device="";
String baud="";
String sn="";
%>
<head>
<title>��ҵ���š�������+�ʼ�</title>
<link href="CSS/style.css" rel="stylesheet">
<script language="javascript">
	function check(){
		if(form1.device.value==""){
			alert("�������ʺ�!");form1.device.focus();return false;
		}
		if(form1.baud.value==""){
			alert("����������!");form1.baud.focus();return false;
		}
		/* if(form1.sn.value==""){
			alert("������˿�!");form1.sn.focus();return false;
		} */ 
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
        <td height="39" valign="top" class="word_orange">��ǰλ�ã�ϵͳ�����趨 &gt;&gt;&gt;</td>
      </tr>
      <tr>
        <td align="center" valign="top"><table width="68%"  border="0" cellpadding="0" cellspacing="0">
		<form name="form1" action="sysParameterSet.do?action=parameterModify" method="post" onSubmit="return check()">
		<%if(list.size()==1){
			ParameterForm p=(ParameterForm)list.get(0);
			ID=p.getID();
			device=p.getDevice();
			baud=p.getBaud();
			//sn=p.getSn();
		}
		%>
            <tr>
              <!-- <td width="20%" height="27" align="center">ͨѶ�˿ڣ�</td> -->
              <td width="20%" height="27" align="center">�˻���</td>
              <td width="80%"><input name="device" type="text" id="device" value="<%=device%>" size="20">
                *
                  <input name="ID" type="hidden" id="ID" value="<%=ID%>"></td>
            </tr>
            <tr>
             <!--  <td height="27" align="center">�� �� �� ��</td> -->
              <td height="27" align="center">���룺</td>
              <td><input name="baud" type="password" id="baud" value="<%=baud%>" size="50">
                *</td>
            </tr>
            <%-- <tr>
              <td height="27" align="center">ע �� �룺</td>
              <td><input name="sn" type="text" id="sn" value="<%=sn%>" size="50">
                *</td>
            </tr> --%>
            <tr>
              <td height="27" colspan="2" align="center"><input name="Submit" type="submit" class="btn_grey" value="�ύ">
                &nbsp;
                <input name="Submit2" type="reset" class="btn_grey" value="����">
                </td>
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
