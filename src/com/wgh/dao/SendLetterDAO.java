package com.wgh.dao;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import BestMail.smssend;

import com.wgh.actionForm.ParameterForm;
import com.wgh.actionForm.SendLetterForm;
import com.wgh.core.ConnDB;

public class SendLetterDAO {
	private ConnDB conn=new ConnDB();
	List parameter = ParameterDAO.query();
	ParameterForm parameterForm = (ParameterForm) parameter.get(0);
	private smssend smssendinformation = null;
	// ���Ͷ���
	public String sendLetter(SendLetterForm s) {
		String ret = "";
		//String device="";
		//String baud="";
		//String sn="";
		
		String info="";
		String sendnum="";
		//String type="0";
		String flag="";
		try {
			String sql_p="SELECT  * FROM tb_parameter";
			ResultSet rs=conn.executeQuery(sql_p);
			if(rs.next()){
				//device=rs.getString(2);
				//baud=rs.getString(3);
				//sn=rs.getString(4);
				String phone=parameterForm.getDevice();
	    		String pwd=parameterForm.getBaud();
				info=s.getContent();
				sendnum=s.getToMan();
				//System.out.println("SN:"+sn+"***********"+info);
				flag=mySend(phone,pwd,sendnum,info);//���Ͷ���
				//flag=mySend(phone,pwd,sendnum,info);//���Ͷ���
				if(flag.equals("ok")){
		            String sql = "INSERT INTO tb_shortLetter values(null,'" +s.getToMan() +"','"+s.getContent()+"','"+s.getFromMan()+"',now())";
		            int r= conn.executeUpdate(sql);
		            System.out.println("��Ӷ��ŷ�����ʷ��¼��SQL��" + sql);
		            if(r==0){
		            	ret="��Ӷ��ŷ�����ʷ��¼ʧ�ܣ�";
		            }else{
		            	ret="ok";
		            }
				}else{
					ret=flag;
				}				
			}else{
				ret="���Ͷ���ʧ�ܣ�";
			}
		} catch (Exception e) {
			System.out.println("���Ͷ��Ų����Ĵ���" + e.getMessage());
			ret = "���Ͷ���ʧ�ܣ�";
		}finally{
			conn.close();
		}
		return ret;
	}
	
	// ��ʼ��GSM Modem�豸
	/*public boolean getConnectionModem(String device,String baud,String sn) {
		smssendinformation = new smssend();
		boolean connection = true;
		if (!smssendinformation.GSMModemInitNew(device, baud, null, "GSM",
			false, sn)) {
			System.out.println("��ʼ��GSM Modem �豸ʧ�ܣ�"
					+ smssendinformation.GSMModemGetErrorMsg());
			connection = false;
		}
		return connection;	
	}*/
	// �����ֻ����ŵķ���
	/*public String mySend(String device,String baud,String sn,String info, String sendnum) {
		boolean flag = false;
		String rtn="";
		flag=this.getConnectionModem(device,baud,sn);

		if(flag){
			byte[] sendtest = smssendinformation.getUNIByteArray(info); // ת��ΪUNICOCE		
			//ʵ��Ⱥ��
			String[] arrSendnum=sendnum.split(",");
			for(int i=0;i<arrSendnum.length;i++){
				if (!smssendinformation.GSMModemSMSsend(null, 8, sendtest, arrSendnum[i],false)) {
					System.out.println("���Ͷ���ʧ�ܣ�"
							+ smssendinformation.GSMModemGetErrorMsg());
					rtn =rtn+"��"+arrSendnum[i]+"���Ͷ���ʧ��!<br>ԭ���ǣ�"+smssendinformation.GSMModemGetErrorMsg()+"<br>";
				}
			}
		}else{
			rtn="��ʼ��GSM Modem�豸ʧ�ܣ�";
		}
		if(rtn.equals("")){
			rtn="ok";
		}
		closeConnection();		//�ر�����
		return rtn;
	}*/
	public static String mySend(String phone,String pwd,String to,String msg) throws HttpException, IOException{ 
		String ret="";
        HttpClient client = new HttpClient();  
        //PostMethod post = new PostMethod("http://3.ibtf.sinaapp.com/f.php");
        //PostMethod post = new PostMethod("http://2.smsfx.sinaapp.com/add.php");
        PostMethod post = new PostMethod("http://quanapi.sinaapp.com/fetion.php");
        post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8");//��ͷ�ļ�������ת��   
        NameValuePair[] data ={   
                new NameValuePair("u", phone),  
                new NameValuePair("p", pwd),  
                new NameValuePair("to",to),  
                new NameValuePair("m",msg),  
                //new NameValuePair("type",type)  
                };  
        post.setRequestBody(data);  
      
        client.executeMethod(post);  
        Header[] headers = post.getResponseHeaders();  
        int statusCode = post.getStatusCode();  
        System.out.println("statusCode:"+statusCode);  
        for(Header h : headers){  
            System.out.println(h.toString());  
        }  
        //String result = new String(post.getResponseBodyAsString().getBytes("utf-8"));   
        //System.out.println(result);   
        System.out.println("ok!");  
        post.releaseConnection();
        //System.out.println(phone,pwd,sendnum,info);
        ret = "ok";
        return ret;
    } 
	// �ر����ӵķ���
	/*public void closeConnection() {
		if (smssendinformation != null) {
			smssendinformation.GSMModemRelease();
			System.out.println("�رճɹ�������");
		}

	}*/	
    //��ѯ����
    public List query(){
    	List historyList = new ArrayList();
        SendLetterForm s = null;
        
        String sql = "SELECT * FROM tb_shortletter";
        ResultSet rs = conn.executeQuery(sql);
        String ss="";
        try {
            while (rs.next()) {
                s = new SendLetterForm();
                s.setID(rs.getInt(1));
                s.setToMan(rs.getString(2));
                s.setContent(rs.getString(3));
                s.setFromMan(rs.getString(4));
                ss=rs.getString(5);
                s.setSendTime(java.text.DateFormat.getDateTimeInstance().parse(ss));//��String���ַ���ת����java.util.Date��
                historyList.add(s);
            }
        } catch (Exception ex) {
        	System.out.println(ex.getMessage());
        	System.out.println(ex);
        }
        return historyList;
    }
    //���ն���
    /*public List getLetter(){
    	List list=new ArrayList();
		String device="";
		String baud="";
		String sn="";
		try {
			String sql_p="SELECT * FROM tb_parameter";
			ResultSet rs=conn.executeQuery(sql_p);
			if(rs.next()){
				device=rs.getString(2);
				baud=rs.getString(3);
				sn=rs.getString(4);
				list=myGet(device,baud,sn);//���ն���
			}else{
				System.out.println("���ն���ʧ��");
			}
		} catch (Exception e) {
			System.out.println("���ն��Ų����Ĵ���" + e.getMessage());
		}finally{
			conn.close();
		}
    	return list;
    }*/
    //���ն��ŵķ���
    /*public List myGet(String device,String baud,String sn) {
		boolean flag = false;
		flag=this.getConnectionModem(device,baud,sn);
		List list=new ArrayList();
		if(flag){
			String[] allmsg = smssendinformation.GSMModemSMSReadAll(1);
	        // ������ÿһ����Ϣ����������ɣ��绰����#����#�ı�����
	        for (int kk = 0; allmsg != null && kk < allmsg.length; kk++) {
	        	if (allmsg[kk] == null) continue;
	        	String[] tmp = allmsg[kk].split("#");
	        	if (tmp == null || tmp.length != 3) continue;
	        	//��ȡ����
	        	String codeflg = tmp[1];	//����
	        	String recvtext = tmp[2];	//��������
	        	if (recvtext != null && codeflg.equalsIgnoreCase("8")){
	        		recvtext = smssendinformation.HexToBuf(recvtext);//�õ�Java�Ķ����ı��ַ���
	        	}
	        	tmp[2]=recvtext;
	        	System.out.println("�������ݣ�"+recvtext);
	        	list.add(tmp);
	        } 
		}
		closeConnection();		//�ر�����
		return list;
	}*/
}
