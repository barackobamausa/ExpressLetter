package com.wgh.actionForm;

import org.apache.struts.action.ActionForm;

public class ManagerForm extends ActionForm {
	private String pwd;		//����
	private String name;	//����Ա����
	private byte state;		//״̬������Ƿ�Ϊ��������Ա��
	private int ID;			//ID��
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public byte getState() {
		return state;
	}
	public void setState(byte state) {
		this.state = state;
	}
	public int getID() {
		return ID;
	}
	public void setID(int ID) {
		this.ID = ID;
	}
}