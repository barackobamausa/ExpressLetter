package com.wgh.actionForm;
import org.apache.struts.action.ActionForm;

public class ShortInfoForm extends ActionForm{
	private int ID;			//ID��	
	private int typeID;		//���ID
	private String typeName;	//�������
	private String content;	//����
	public int getID() {
		return ID;
	}
	public void setID(int ID) {
		this.ID = ID;
	}
	
	public int getTypeID() {
		return typeID;
	}
	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}
	
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}