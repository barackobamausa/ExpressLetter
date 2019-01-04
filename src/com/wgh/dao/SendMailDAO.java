package com.wgh.dao;

import java.io.File;
import java.sql.*;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

import com.wgh.actionForm.SendMailForm;
import com.wgh.core.ConnDB;

public class SendMailDAO {

	// �����ʼ�
	public int sendMail(SendMailForm s) {
		int ret = 0;
		String from = s.getAddresser();
		String to = s.getAddressee();
		String subject = s.getTitle();
		String content = s.getContent();
		String password = s.getPwd();
		String path = s.getAdjunct();
		try {
			String mailserver ="smtp."+from.substring(from.indexOf('@')+1,from.length());	//��Internet�Ϸ����ʼ�ʱ�Ĵ���
			//String mailserver = "wanggh";									//�ھ������ڷ����ʼ�ʱ�Ĵ���

			Properties prop = new Properties();
			prop.put("mail.smtp.host", mailserver);
			prop.put("mail.smtp.auth", "true");
			Session sess = Session.getDefaultInstance(prop);
			sess.setDebug(true);
			MimeMessage message = new MimeMessage(sess);
			message.setFrom(new InternetAddress(from));	// ����Ϣ�������÷�����
			//�����ռ���
			String toArr[]=to.split(",");
			InternetAddress[] to_mail=new InternetAddress[toArr.length];
			for(int i=0;i<toArr.length;i++){
				to_mail[i]=new InternetAddress(toArr[i]);
			}
		    message.setRecipients(Message.RecipientType.BCC,to_mail);
			//��������
			message.setSubject(subject);
			Multipart mul = new MimeMultipart(); // �½�һ��MimeMultipart��������Ŷ��BodyPart����
			BodyPart mdp = new MimeBodyPart(); // �½�һ������ż����ݵ�BodyPart����
			mdp.setContent(content, "text/html;charset=gb2312");
			mul.addBodyPart(mdp); // �������ż����ݵ�BodyPart���뵽MimeMulitipart������
			if(!path.equals("") && path!=null){	//�����ڸ���ʱ
				// �����ż��ĸ������ñ����ϵ��ļ���Ϊ������
				mdp = new MimeBodyPart(); // �½�һ����Ÿ�����BodyPart
				String adjunctname = new String(path.getBytes("GBK"), "ISO-8859-1"); // �˴���Ҫת�룬���򸽼��а�������ʱ������������			
				path = (System.getProperty("java.io.tmpdir") + "/" + path).replace(
						"\\", "/");
				System.out.println("·����" + path);
				FileDataSource fds = new FileDataSource(path);
				DataHandler handler = new DataHandler(fds);
				mdp.setFileName(adjunctname);
				mdp.setDataHandler(handler);
				mul.addBodyPart(mdp);
			}
			message.setContent(mul); // ��mul��Ϊ��Ϣ���������
			message.saveChanges();
			Transport transport = sess.getTransport("smtp");
			// ��smtp��ʽ��¼���䣬��1�������Ƿ����ʼ��õ��ʼ�������SMTP��ַ����2������Ϊ�û�������3������Ϊ����
			transport.connect(mailserver, from, password);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			ret = 1;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("�����ʼ������Ĵ���" + e.getMessage());
			ret = 0;
		}
		return ret;
	}

}
