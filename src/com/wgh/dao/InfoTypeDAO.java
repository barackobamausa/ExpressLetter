package com.wgh.dao;

import com.wgh.actionForm.InfoTypeForm;
import com.wgh.core.ConnDB;
import java.sql.*;
import java.util.*;

public class InfoTypeDAO {
	private ConnDB conn=new ConnDB();
    //�������
    public int insert(InfoTypeForm infoTypeForm) {
        String sql1="SELECT * FROM tb_infoType WHERE name='"+infoTypeForm.getName()+"'";
        ResultSet rs = conn.executeQuery(sql1);
        String sql = "";
        int falg = 0;
            try {
                if (rs.next()) {
                    falg=2;
                } else {
                    sql = "INSERT INTO tb_infoType (id,name) values(null,'" +
                                 infoTypeForm.getName() + "')";
                    falg = conn.executeUpdate(sql);
                    System.out.println("�����Ϣ����SQL��" + sql);
                    conn.close();
                }
            } catch (SQLException ex) {
                falg=0;
            }
        return falg;
    }
    //��ѯ����
    public List query(int id) {
    	List infoTypeList = new ArrayList();
        InfoTypeForm infoTypeForm1 = null;
        String sql="";
        if(id==0){
            sql = "SELECT * FROM tb_infoType";
        }else{
        	sql = "SELECT * FROM tb_infoType WHERE name='" +id + "'";
        }
        ResultSet rs = conn.executeQuery(sql);
        try {
            while (rs.next()) {
                infoTypeForm1 = new InfoTypeForm();
                infoTypeForm1.setID(rs.getInt(1));
                infoTypeForm1.setName(rs.getString(2));
                infoTypeList.add(infoTypeForm1);
            }
        } catch (SQLException ex) {}
        return infoTypeList;
    }
//    ɾ������
        public int delete(InfoTypeForm infoTypeForm) {
            String sql = "DELETE FROM tb_infoType where id=" + infoTypeForm.getID() +"";
            int flag = conn.executeUpdate(sql);
            conn.close();
            return flag;
        }
}
