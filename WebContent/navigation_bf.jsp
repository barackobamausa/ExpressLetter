<%@ page contentType="text/html; charset=gb2312"%>
<%@ page import="com.wgh.core.ChStr" %>
<%
//��֤�û��Ƿ��¼
String manager=(String)session.getAttribute("manager");
String purview=(String)session.getAttribute("purview");
if (manager==null || "".equals(manager)){
	response.sendRedirect("login.jsp");
	return;
}
ChStr chStr=new ChStr();
%>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<script src="JS/menu.JS"></script>
<script src="JS/function.js"></script>
<div class=menuskin id=popmenu onmouseover="clearhidemenu();highlightmenu(event,'on')"
      onmouseout="highlightmenu(event,'off');dynamichide(event)" style="Z-index:100;position:absolute;"></div>
<table width="778" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="112" valign="top" background="Images/top_bg.jpg"><table width="100%" height="35"  border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="78%" height="6"></td>
        <td colspan="2"></td>
      </tr>
      <tr>
        <td height="22">&nbsp;</td>
        <td width="10%"><a href="#" onClick="window.location.reload();" ><span class="word_white">��ˢ��ҳ��</span></a></td>
        <td width="12%"><a href="#" onClick="myclose()"><span class="word_white">���ر�ϵͳ</span></a></td>
        </tr>
    </table>
      <table width="98%" height="72"  border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="72" align="right" valign="bottom" class="word_white">��ҵ���Ż�ӭ����<b> ��ǰ��¼�û���<%=manager%></b></td>
        </tr>
    </table></td>
  </tr>
  <tr>
    <td height="39" align="right" valign="top" background="Images/navigate_bg.jpg" bgcolor="#EEEEEE"><table width="100%" height="30"  border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="28%">&nbsp;</td>
        <td width="70%" align="center" valign="middle" class="word_grey"><a href="main.jsp">��ҳ</a> |
          <a  onmouseover=showmenu(event,cardClip) onmouseout=delayhidemenu() class='navlink' style="CURSOR:hand" >��Ƭ�й���</a> |
          <a  onmouseover=showmenu(event,infoLibrary) onmouseout=delayhidemenu() class='navlink' style="CURSOR:hand" >��Ϣ�����</a> |
          <a  onmouseover=showmenu(event,shortLetter) onmouseout=delayhidemenu() class='navlink' style="CURSOR:hand" >�շ�����</a> |
          <a  href="sendMail.do?action=addMail">�ʼ�Ⱥ��</a> | 
        <%if(purview.equals("1")){%>
        <a  href="sysParameterSet.do?action=parameterQuery" >ϵͳ�����趨</a> |
        <%}%>
		<a onmouseover=showmenu(event,sysSet) onmouseout=delayhidemenu() class='navlink' style="CURSOR:hand">ϵͳ����</a>
		| <a href="#" onClick="quit()">�˳�ϵͳ</a></td>
        <td width="2%">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
</table>
<script language="javascript">
var cardClip='<table width=56><tr><td id=customer onMouseOver=overbg(customer) onMouseOut=outbg(customer)><a href=customer.do?action=customerQuery>�ͻ�����</a></td></tr>\
<tr><td id=personnel onMouseOver=overbg(personnel) onMouseOut=outbg(personnel)><a href=personnel.do?action=personnelQuery>Ա������</a></td></tr>\
</table>'
var infoLibrary='<table width=86><tr><td id=infoType onMouseOver=overbg(infoType) onMouseOut=outbg(infoType)><a href=infoType.do?action=infoTypeQuery>��Ϣ������</a></td></tr>\
<tr><td id=shortInfo onMouseOver=overbg(shortInfo) onMouseOut=outbg(shortInfo)><a href=shortInfo.do?action=shortInfoQuery>���ö������</a></td></tr>\
</table>'
<%if(purview.equals("1")){%>
	var shortLetter='<table width=86><tr><td id=sendLetter onMouseOver=overbg(sendLetter) onMouseOut=outbg(sendLetter)><a href=sendLetter.do?action=addLetter>���Ͷ���</a></td></tr>\
<tr><td id=getLetter onMouseOver=overbg(getLetter) onMouseOut=outbg(getLetter)><a href=sendLetter.do?action=getLetterQuery>���ն���</a></td></tr>\
<tr><td id=historyQ onMouseOver=overbg(historyQ) onMouseOut=outbg(historyQ)><a href=sendLetter.do?action=historyQuery>�鿴������־</a></td></tr>\
</table>'
<%}else{%>
	var shortLetter='<table width=56><tr><td id=sendLetter onMouseOver=overbg(sendLetter) onMouseOut=outbg(sendLetter)><a href=sendLetter.do?action=addLetter>���Ͷ���</a></td></tr>\
<tr><td id=getLetter onMouseOver=overbg(getLetter) onMouseOut=outbg(getLetter)><a href=sendLetter.do?action=getLetterQuery>���ն���</a></td></tr>\
</table>'
<%}
if(purview.equals("1")){%>
	var sysSet='<table width=70><tr><td id=manager onMouseOver=overbg(manager) onMouseOut=outbg(manager)><a href=manager.do?action=managerQuery>����Ա����</a></td></tr>\
<tr><td id=changePWD onMouseOver=overbg(changePWD) onMouseOut=outbg(changePWD)><a  href="manager.do?action=queryPWD">���Ŀ���</a></td></tr>\
</table>'
<%}else{%>
	var sysSet='<table width=70><tr><td id=changePWD onMouseOver=overbg(changePWD) onMouseOut=outbg(changePWD)><a  					href="manager.do?action=queryPWD">���Ŀ���</a></td></tr></table>'
<%}%>
</script>