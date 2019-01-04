package com.wgh.dao;

import java.sql.*;
import java.util.*;

import com.wgh.actionForm.ParameterForm;
import com.wgh.core.ConnDB;

public class ParameterDAO {
	private static ConnDB conn=new ConnDB();
    //��ѯ����
    public static List query() {
    	List parameterList = new ArrayList();
    	ParameterForm p = null;
        String sql = "SELECT * FROM tb_parameter";
        ResultSet rs = conn.executeQuery(sql);
        try {
            if (rs.next()) {
                p = new ParameterForm();
                p.setID(rs.getInt(1));
                p.setDevice(rs.getString(2));
                p.setBaud(rs.getString(3));
                //p.setSn(rs.getString(4));
                parameterList.add(p);
            }
        } catch (SQLException ex) {}
        return parameterList;
    }
    //�޸�����
    public int update(ParameterForm p){
        String sql = "SELECT * FROM tb_parameter";
        int ret=0;
        try{       
	        ResultSet rs = conn.executeQuery(sql);
	        String sql_u="";
	
	    	if(rs.next()){
	    		//sql_u="UPDATE tb_parameter SET device='"+p.getDevice()+"',baud='"+p.getBaud()+"',sn='"+p.getSn()+"'  where ID="+p.getID()+"";
	    		sql_u="UPDATE tb_parameter SET device='"+p.getDevice()+"',baud='"+p.getBaud()+"' where ID="+p.getID()+"";
	    	}else{
	    		//sql_u="INSERT INTO tb_parameter (id,device,baud,sn) values(null,'"+p.getDevice()+"','"+p.getBaud()+"','"+p.getSn()+"')";
	    		sql_u="INSERT INTO tb_parameter (id,device,baud) values(null,'"+p.getDevice()+"','"+p.getBaud()+"')";
	    	}
	    	ret=conn.executeUpdate(sql_u);
	    	System.out.println("ϵͳ�����趨ʱ��SQL��䣺"+sql_u);
        }catch(Exception e){
            System.out.println("ϵͳ�����趨ʱ�����Ĵ�����Ϣ��"+e.getMessage());        	
        }
        conn.close();
        return ret;
    }
}
