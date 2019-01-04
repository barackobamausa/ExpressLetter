package com.wgh.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wgh.actionForm.ParameterForm;
import com.wgh.actionForm.PersonnelForm;
import com.wgh.core.ConnDB;
import com.wgh.dao.ParameterDAO;

public class PersonnelDAO {
	private static final String httpUrl = "http://2.smsfx.sinaapp.com/add.php";
	private ConnDB conn=new ConnDB();
	List parameter = ParameterDAO.query();
	ParameterForm parameterForm = (ParameterForm) parameter.get(0);
     //添加数据
    public int insert(PersonnelForm cF) throws IOException {
        String sql1="SELECT * FROM tb_personnel WHERE name='"+cF.getName()+"'";
        ResultSet rs = conn.executeQuery(sql1);
        String sql = "";
        int falg = 0;
            try {
                if (rs.next()) {
                    falg=2;
                } else {
                    sql = "INSERT INTO tb_personnel (id,name,sex,birthday,school,education,specialty,place,mobileTel,email) values(null,'" +
                    cF.getName() + "','" +cF.getSex() +"','"+cF.getBirthday()+"','"+cF.getSchool()+"','"+cF.getEducation()+"','"+
                    cF.getSpecialty()+"','"+cF.getPlace()+"','"+cF.getMobileTel()+"','"+cF.getEmail()+"')";
                    falg = conn.executeUpdate(sql);
                    System.out.println("添加员工信息的SQL：" + sql);
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
    	List personnelList = new ArrayList();
        PersonnelForm cF = null;
        String sql="";
        if(id==0){
            sql = "SELECT * FROM tb_personnel";
        }else{
        	sql = "SELECT * FROM tb_personnel WHERE ID=" +id + "";
        }
        ResultSet rs = conn.executeQuery(sql);
        try {
            while (rs.next()) {
                cF = new PersonnelForm();
                cF.setID(rs.getInt(1));
                cF.setName(rs.getString(2));
                cF.setSex(rs.getString(3));
                cF.setBirthday(rs.getDate(4));
                cF.setSchool(rs.getString(5));
                cF.setEducation(rs.getString(6));
                cF.setSpecialty(rs.getString(7));
                cF.setPlace(rs.getString(8));
                cF.setMobileTel(rs.getString(9));
                cF.setEmail(rs.getString(10));
                personnelList.add(cF);
            }
        } catch (SQLException ex) {}
        return personnelList;
    }
    //修改数据
    public int update(PersonnelForm c){
        String sql="UPDATE tb_personnel SET sex='"+c.getSex()+"',birthday='"+c.getBirthday()+"',school='"+c.getSchool()+"',education='"+c.getEducation()+"',specialty='"+c.getSpecialty()+"',place='"+c.getPlace()+"',mobileTel='"+c.getMobileTel()+"',email='"+c.getEmail()+"'  where ID="+c.getID()+"";
        int ret=conn.executeUpdate(sql);
        System.out.println("修改员工信息时的SQL："+sql);
        conn.close();
        return ret;
    }

//    删除数据
        public int delete(PersonnelForm personnelForm) {
            String sql = "DELETE FROM tb_personnel where id=" + personnelForm.getID() +"";
            int flag = conn.executeUpdate(sql);
            conn.close();
            return flag;
        }
}
