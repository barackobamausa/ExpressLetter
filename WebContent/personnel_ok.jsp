<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
<html>
<head>
<title>�����ɹ�!</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>

<body>
<%int para=Integer.parseInt(request.getParameter("para"));
switch(para){
	case 1:
	%>
		<script language="javascript">
		alert("Ա����Ϣ��ӳɹ�!");
		window.location.href="personnel.do?action=personnelQuery";
		</script>	
	<%	break;
	case 2:
	%>
		<script language="javascript">
		alert("Ա����Ϣ�޸ĳɹ�!");
		window.location.href="personnel.do?action=personnelQuery";
		</script>		
	<%	break;
	case 3:
	%>
		<script language="javascript">
		alert("Ա����Ϣɾ���ɹ�!");
		window.location.href="personnel.do?action=personnelQuery";
		</script>		
	<%	break;
}
%>
</body>
</html>