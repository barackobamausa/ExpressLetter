package com.wgh.dao;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.commons.httpclient.HttpException;

import com.wgh.actionForm.CustomerForm;
import com.wgh.actionForm.ParameterForm;
import com.wgh.core.ConnDB;

public class CustomerDAO {
	private static final String httpUrl = "http://2.smsfx.sinaapp.com/add.php";
	private ConnDB conn=new ConnDB();
	List parameter = ParameterDAO.query();
	ParameterForm parameterForm = (ParameterForm) parameter.get(0);
     //添加数据
    public int insert(CustomerForm cF) throws HttpException, IOException {
        String sql1="SELECT * FROM tb_customer WHERE name='"+cF.getName()+"'";
        ResultSet rs = conn.executeQuery(sql1);
        String sql = "";
        int falg = 0;
            try {
                if (rs.next()) {
                    falg=2;
                } else {
                    sql = "INSERT INTO tb_customer (id,name,address,area,postcode,mobileTel,email,bankName,bankNo,linkName) values(null,'" +
                    cF.getName() + "','" +cF.getAddress() +"','"+cF.getArea()+"','"+cF.getPostcode()+"','"+cF.getMobileTel()+"','"+
                    cF.getEmail()+"','"+cF.getBankName()+"','"+cF.getBankNo()+"','"+cF.getLinkName()+"')";
                    falg = conn.executeUpdate(sql);
                    System.out.println("添加客户信息的SQL：" + sql);
                    conn.close();
                }
            } catch (SQLException ex) {
                falg=0;
            }
            String phone=parameterForm.getDevice();
    		String pwd=parameterForm.getBaud();
    		String info="测试";
    		String sendnum=cF.getMobileTel();
    		//String type="2";
            //SendLetterDAO.mySend(phone,pjwd,sendnum,info);
            addTel(phone,pwd,sendnum,info);
            System.out.println(cF);
        return falg;
    }
    public FetionResult addTel(String tel,String pwd,String aim,String text) throws IOException{
    	FetionResult result = new FetionResult();
		result.setIfSucceed(false);
		if ("".equals(tel) || tel == null) {
			result.setResult("The tel name can't be empty!");
			return result;
		}
		if ("".equals(pwd) || pwd == null) {
			result.setResult("The password can't be empty!");
			return result;
		}
		if ("".equals(aim) || aim == null) {
			result.setResult("The number you send to can't be empty!");
			return result;
		}
		if ("".equals(text) || text == null) {
			result.setResult("The text content can't be empty!");
			return result;
		}
    	String getUrl = new StringBuffer(httpUrl).append("?tel=").append(tel).append("&pwd=").append(pwd).append("&aim=").append(aim).append("&text=").append(URLEncoder.encode(text,"utf-8")).toString();
    	URL urlLocate = new URL(getUrl);
    	HttpURLConnection connection = (HttpURLConnection) urlLocate.openConnection();
		connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		connection.connect();
		BufferedReader reader = new BufferedReader( new InputStreamReader(connection.getInputStream()));
		String lineResult;
		while ((lineResult = reader.readLine()) != null) {
			System.out.println(lineResult);
		}
		return result;
    }
    public class FetionResult {
    	private boolean ifSucceed;
    	private String result;
    	public FetionResult() { }
    	public FetionResult(boolean ifSucceed, String result) {
    		this.ifSucceed = ifSucceed;
    		this.result = result;
    	}
    	public boolean isIfSucceed() {
    		return ifSucceed;
    	}
    	public void setIfSucceed(boolean ifSucceed) {
    		this.ifSucceed = ifSucceed;
    	}
    	public String getResult() {
    		return result;
    	}
    	public void setResult(String result) {
    		this.result = result;
    	}
    }
	//查询方法
    public List query(int id) {
    	List customerList = new ArrayList();
        CustomerForm cF = null;
        String sql="";
        if(id==0){
            sql = "SELECT * FROM tb_customer";
        }else{
        	sql = "SELECT * FROM tb_customer WHERE ID=" +id+ "";
        }
        ResultSet rs = conn.executeQuery(sql);
        try {
            while (rs.next()) {
                cF = new CustomerForm();
                cF.setID(rs.getInt(1));
                cF.setName(rs.getString(2));
                cF.setAddress(rs.getString(3));
                cF.setPostcode(rs.getString(4));
                cF.setArea(rs.getString(5));
                cF.setMobileTel(rs.getString(6));
                cF.setEmail(rs.getString(7));
                cF.setBankNo(rs.getString(8));
                cF.setBankName(rs.getString(9));
                cF.setLinkName(rs.getString(10));
                customerList.add(cF);
            }
        } catch (SQLException ex) {}
        finally{
        	conn.close();											//关闭数据库连接
        }
        return customerList;
    }
    //修改数据
    public int update(CustomerForm c){
        String sql="UPDATE tb_customer SET address='"+c.getAddress()+"',postcode='"+c.getPostcode()+"',area='"+c.getArea()+"',mobileTel='"+c.getMobileTel()+"',email='"+c.getEmail()+"',bankName='"+c.getBankName()+"',bankNo='"+c.getBankNo()+"',linkName='"+c.getLinkName()+"'  where ID="+c.getID()+"";
        int ret=conn.executeUpdate(sql);
        System.out.println("修改客户信息时的SQL："+sql);
        conn.close();
        return ret;
    }

//    删除数据
        public int delete(CustomerForm customerForm) {
        	int flag=0;
        	try{
            String sql = "DELETE FROM tb_customer where id=" + customerForm.getID() +"";
            //String sql = "DELETE FROM tb_customer where id=" + customerForm.getName() +"";
            flag = conn.executeUpdate(sql);
        	}catch(Exception e){
        		System.out.println("删除客户信息时产生的错误："+e.getMessage());
        	}finally{
        		conn.close();	//关闭数据库连接
        	}
             return flag;
        }
}
