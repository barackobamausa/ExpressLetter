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
		alert("�ͻ���Ϣ���ӳɹ�!");
		window.location.href="customer.do?action=customerQuery";
		</script>	
	<%	break;
	case 2:
	%>
		<script language="javascript">
		alert("�ͻ���Ϣ�޸ĳɹ�!");
		window.location.href="customer.do?action=customerQuery";
		</script>		
	<%	break;
	case 3:
	%>
		<script language="javascript">
		alert("�ͻ���Ϣɾ���ɹ�!");
		window.location.href="customer.do?action=customerQuery";
		</script>		
	<%	break;
}
%>
</body>
</html>