package com.wgh.actionForm;

import java.util.Date;
import org.apache.struts.action.ActionForm;

public class SendLetterForm extends ActionForm {
	private int ID;
	private String toMan;
	private String content;
	private String fromMan;
	private Date sendTime;
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getToMan() {
		return toMan;
	}
	public void setToMan(String toMan) {
		this.toMan = toMan;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFromMan() {
		return fromMan;
	}
	public void setFromMan(String fromMan) {
		this.fromMan = fromMan;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	
}