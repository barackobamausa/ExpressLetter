<%@ page contentType="text/html; charset=gb2312" language="java"%>
<jsp:useBean id="upFile" scope="page" class="com.jspsmart.upload.SmartUpload" />
<%
upFile.initialize(pageContext);
upFile.upload();
System.out.println("�ļ���С��"+upFile.getFiles().getSize());
if(upFile.getFiles().getSize()>2000000){
	out.println("<script>alert('���ϴ����ļ�̫�󣬲�������ϴ���');</script>");
}else{
	String getFileName=upFile.getFiles().getFile(0).getFileName();
	out.println("<script>opener.form1.adjunct.value='"+getFileName+"';window.close();</script>");
	try{
		//System.out.println("SSS:"+System.getProperty("java.io.tmpdir") );
		upFile.save(System.getProperty("java.io.tmpdir"));
	}catch(Exception e){
		out.println("�ϴ��ļ����ִ���"+e.getMessage());
	}
}
%>