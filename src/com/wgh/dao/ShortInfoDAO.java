package com.wgh.dao;

import java.sql.*;
import java.util.*;

import com.wgh.actionForm.ShortInfoForm;
import com.wgh.actionForm.InfoTypeForm;
import com.wgh.core.ConnDB;

public class ShortInfoDAO {
	private ConnDB conn=new ConnDB();
     //�������
    public int insert(ShortInfoForm cF) {
        String sql1="SELECT * FROM tb_shortInfo WHERE name='"+cF.getContent()+"'";
        ResultSet rs = conn.executeQuery(sql1);
        String sql = "";
        int falg = 0;
            try {
                if (rs.next()) {
                    falg=2;
                } else {
                    sql = "INSERT INTO tb_shortInfo (id,typeId,content) values(null,'" +
                    cF.getTypeID() + "','" +cF.getContent()+"')";
                    falg = conn.executeUpdate(sql);
                    System.out.println("��ӳ��ö����SQL��" + sql);
                    conn.close();
                }
            } catch (SQLException ex) {
                falg=0;
            }
        return falg;
    }
    //��ѯ���
    public List queryType(InfoTypeForm infoTypeForm) {
    	List shortInfoList = new ArrayList();
    	InfoTypeForm i = null;
        String sql="SELECT * FROM tb_infoType";
        ResultSet rs = conn.executeQuery(sql);
        try {
            while (rs.next()) {
                i = new InfoTypeForm();
                i.setID(rs.getInt(1));
                i.setName(rs.getString(2));
                shortInfoList.add(i);
            }
        } catch (SQLException ex) {}
        return shortInfoList;
    }
    public List query(ShortInfoForm shortInfoForm) {
    	List shortInfoList = new ArrayList();
        ShortInfoForm s = null;
        String sql="";
        if(shortInfoForm==null){
            sql = "SELECT s.ID,s.typeId,t.name typeName,s.content FROM tb_shortInfo s INNER JOIN tb_infoType t ON s.typeId=t.id";
        }else{
        	if(shortInfoForm.getID()!=0){
        		sql = "SELECT s.ID,s.typeId,t.name typeName,s.content FROM tb_shortInfo s INNER JOIN tb_infoType t ON s.typeId=t.id WHERE s.ID=" +shortInfoForm.getID() + "";
        	}else{
        		sql = "SELECT s.ID,s.typeId,t.name typeName,s.content FROM tb_shortInfo s INNER JOIN tb_infoType t ON s.typeId=t.id WHERE t.ID=" +shortInfoForm.getTypeID()+ "";
        	}
        }
        System.out.println("SQL:"+sql);
        ResultSet rs = conn.executeQuery(sql);
        try {
            while (rs.next()) {
                s = new ShortInfoForm();
                s.setID(rs.getInt(1));
                s.setTypeID(rs.getInt(2));
                s.setTypeName(rs.getString(3));
                s.setContent(rs.getString(4));
                shortInfoList.add(s);
            }
        } catch (SQLException ex) {
        	System.out.println("��ѯ���ö���ʱ�����Ĵ���"+ex.getMessage());
        }
        return shortInfoList;
    }    
    //�޸�����
    public int update(ShortInfoForm c){
        String sql="UPDATE tb_shortInfo SET typeId='"+c.getTypeID()+"',content='"+c.getContent()+"' WHERE id="+c.getID()+"";
        int ret=conn.executeUpdate(sql);
        System.out.println("�޸ĳ��ö���ʱ��SQL��"+sql);
        conn.close();
        return ret;
    }

//    ɾ������
        public int delete(ShortInfoForm shortInfoForm) {
            String sql = "DELETE FROM tb_shortInfo where id=" + shortInfoForm.getID() +"";
            int flag = conn.executeUpdate(sql);
            conn.close();
            return flag;
        }
}
