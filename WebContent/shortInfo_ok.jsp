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
		alert("���ö�����ӳɹ�!");
		window.location.href="shortInfo.do?action=shortInfoQuery";
		</script>	
	<%	break;
	case 2:
	%>
		<script language="javascript">
		alert("���ö����޸ĳɹ�!");
		window.location.href="shortInfo.do?action=shortInfoQuery";
		</script>		
	<%	break;
	case 3:
	%>
		<script language="javascript">
		alert("���ö���ɾ���ɹ�!");
		window.location.href="shortInfo.do?action=shortInfoQuery";
		</script>		
	<%	break;
}
%>
</body>
</html>