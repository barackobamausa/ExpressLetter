<%@ page contentType="text/html; charset=gb2312" language="java" import="java.util.*" %>
<%@ page import="com.wgh.actionForm.PersonnelForm"%>
<%@ page import="com.wgh.actionForm.CustomerForm"%>
<%@ page import="com.wgh.actionForm.InfoTypeForm"%>
<%
List list_p=(List)request.getAttribute("personnelQuery");
List list_c=(List)request.getAttribute("customerQuery");
List list_i=(List)request.getAttribute("shortInfo");
%>
<html>
<head>
<title>��ҵ���š�������+�ʼ�</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link href="CSS/style.css" rel="stylesheet">
<script language="javascript">
function checkform(myform){
	if(myform.toMan.value==""){
		alert("��������ն��ŵ��ֻ����룡");myform.toMan.focus();return false;
	}
	if(myform.content.value==""){
		alert("������������ݣ�");myform.content.focus();return false;
	}	
}
//����ֻ�����
function add(mobileTel){
	if(checkTel(mobileTel)){
		s=form1.toMan.value;
		if(s.length>=11){
			arrS=s.split(",");
			flag=false;		//����Ƿ��Ѿ����
			for(i=0;i<arrS.length;i++){
				if(arrS[i]==mobileTel){	//�жϸ��ֻ������Ƿ��Ѿ����
					flag=true;
					break;
				}
			}
			if(!flag){
				form1.toMan.value=s+","+mobileTel;
			}
		}else{
			form1.toMan.value=mobileTel;
		}
	}
}
function deal(infoType,text){
	var someValue;
	var str="window.showModalDialog('shortInfo.do?action=selectShortInfo&id="+infoType+"','','dialogWidth=520px;\
	dialogHeight=430px;status=no;help=no;scrollbars=no')";
	someValue=eval(str);
	if(someValue!=undefined){
		text.value=text.value+someValue;
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
    <td height="510" valign="top" style="padding:5px;"><table width="100%" height="504"  border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td height="26" valign="top" class="word_orange">��ǰλ�ã��շ����� &gt; ���Ͷ��� &gt;&gt;&gt;</td>
      </tr>
      <tr>
        <td align="center" valign="top">
<form action="sendLetter.do?action=sendLetter" method="post" name="form1" onSubmit="return checkform(form1)">
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="177" height="469" align="right" valign="top" background="Images/left_bg.gif" class="tableBorder"><img src="Images/left_title.gif" width="210" height="33">
      <table width="92%" border="0" cellpadding="0" cellspacing="0">
	<%if(list_c.size()>0){%>
      <tr>
        <td height="27"><a href="#" onClick="javascript:ShowTR(img_1,OpenRep1)"><img src="Images/jia.gif" alt="չ��" name="img_1" width="10" height="10" border="0"> �ͻ��б�</a></td>
      </tr>
      <tr id="OpenRep1" style="display:none">
        <td height="27">
          <table width="100%" border="0" cellspacing="0" cellpadding="0">
		  <%  for(int i=0;i<list_c.size();i++){
    			CustomerForm c=(CustomerForm)list_c.get(i);%>
            <tr>
              <td>&nbsp;<a href="#" onClick="add('<%=c.getMobileTel()%>')"><%=c.getName()%></a></td>
            </tr>
			<%}%>
          </table>		  </td>
      </tr>
	<%}
	if(list_p.size()>0){%>  
      <tr>
        <td height="27"><a href="#" onClick="javascript:ShowTR(img_2,OpenRep2)"><img src="Images/jia.gif" alt="չ��" name="img_2" width="10" height="10" border="0"> Ա���б�</a></td>
      </tr>
      <tr id="OpenRep2" style="display:none">
        <td height="27">
		 <table width="100%" border="0" cellspacing="0" cellpadding="0">
		  <%  for(int i=0;i<list_p.size();i++){
    			PersonnelForm p=(PersonnelForm)list_p.get(i);%>
            <tr>
              <td>&nbsp;<a href="#" onClick="add('<%=p.getMobileTel()%>')"><%=p.getName()%></a></td>
            </tr>
			<%}%>
          </table>		</td>
      </tr>
	 <%}%>
    </table></td>
	<td background="images/title.jpg">&nbsp;</td>
    <td valign="top" background="images/title.jpg" class="tableBorder">
      <table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
	    <tr>
          <td height="23" align="center">&nbsp;</td>
          <td height="23" align="center">&nbsp;</td>
          <td align="left">&nbsp;</td>
        </tr>	  
        <tr>
          <td width="105" height="52" align="center">���շ��ֻ����룺</td>
          <td colspan="2" align="left">
            <textarea name="toMan" cols="59" rows="3" class="wenbenkuang" id="toMan"></textarea>
            <input name="fromMan" type="hidden" id="fromMan" value="<%=manager%>"></td>
        </tr>
		<tr>
		<td height="32" align="center">��ӳ��ö��</td>
		<td colspan="2">
		  <select name="infoType" class="wenbenkuang">
		  <option value="0">��ѡ���ö������</option>
		  <%for(int i=0;i<list_i.size();i++){
		  		InfoTypeForm infoTypeForm=(InfoTypeForm)list_i.get(i);
		  %>
		    <option value="<%=infoTypeForm.getID()%>"><%=infoTypeForm.getName()%></option>
			<%}%>
		    </select>
		  <input name="Submit3" type="button" class="btn_grey" value="ȷ��" onClick="deal(this.form.infoType.value,this.form.content)">		</td>
		</tr>
        <tr>
          <td align="center">�������ݣ�</td>
          <td colspan="2" align="left"><textarea name="content" cols="59" rows="12" class="wenbenkuang" id="content" title="��������"></textarea></td>
        </tr>
        <tr>
          <td height="32" align="center">&nbsp;</td>
          <td width="325" height="23" align="center"><input name="Submit" type="submit" class="btn_grey" value="����">
            &nbsp;
            <input name="Submit2" type="reset" class="btn_grey" value="����">
            &nbsp;</td>
          <td width="86" align="left">&nbsp;</td>
        </tr>
        </table>
      <table width="100%" height="202" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="202" align="right" valign="bottom"><img src="Images/center_b.gif" width="523" height="100"></td>
        </tr>
      </table>
		</td>
  </tr>
</table>
</form>
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
