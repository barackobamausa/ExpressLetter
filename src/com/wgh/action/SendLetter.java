package com.wgh.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.*;
import com.wgh.core.ChStr;
import com.wgh.actionForm.SendLetterForm;
import com.wgh.dao.CustomerDAO;
import com.wgh.dao.PersonnelDAO;
import com.wgh.dao.SendLetterDAO;
import com.wgh.dao.InfoTypeDAO;
import java.io.IOException;  
import org.apache.commons.httpclient.Header;  
import org.apache.commons.httpclient.HttpClient;  
import org.apache.commons.httpclient.HttpException;  
import org.apache.commons.httpclient.NameValuePair;  
import org.apache.commons.httpclient.methods.PostMethod;  

public class SendLetter extends Action{
    private SendLetterDAO sendLetterDAO = null;
    private PersonnelDAO personnelDAO=null;
    private CustomerDAO customerDAO=null;
    private InfoTypeDAO infoTypeDAO=null;
    private ChStr chStr=new ChStr();
    public SendLetter() {
        this.sendLetterDAO = new SendLetterDAO();
        this.personnelDAO=new PersonnelDAO();
        this.customerDAO=new CustomerDAO();
        this.infoTypeDAO=new InfoTypeDAO();
    }	
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
        String action = request.getParameter("action");
        System.out.println("��ȡ�Ĳ�ѯ�ַ�����" + action);
        if (action == null || "".equals(action)) {
        	request.setAttribute("error","���Ĳ�������");
            return mapping.findForward("error");
        }else if ("addLetter".equals(action)) {
            return addLetter(mapping, form, request,response);
        }else if("sendLetter".equals(action)){
            return sendLetter(mapping, form, request,response);
        }else if("historyQuery".equals(action)){
        	return queryHistory(mapping, form, request,response);
        }/*else if("getLetterQuery".equals(action)){
        	return getLetterQuery(mapping,form,request,response);
        }*/
			request.setAttribute("error", "����ʧ�ܣ�");
			return mapping.findForward("error");
	}

    //��д����ҳ��Ӧ�õĲ�ѯ���������ڲ�ѯ�������б���Ϣ���
    private ActionForward addLetter(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) {
        request.setAttribute("personnelQuery",personnelDAO.query(0));
        request.setAttribute("customerQuery",customerDAO.query(0));
        request.setAttribute("shortInfo",infoTypeDAO.query(0));
        return mapping.findForward("addLetter");
    }
    //Ⱥ������
    private ActionForward sendLetter(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response){
    	SendLetterForm sendLetterForm=(SendLetterForm) form;
    	sendLetterForm.setContent(chStr.toChinese(sendLetterForm.getContent()));
    	sendLetterForm.setFromMan(chStr.toChinese(sendLetterForm.getFromMan()));
        String ret=sendLetterDAO.sendLetter(sendLetterForm);
        if(ret.equals("ok")){
        	return mapping.findForward("sendLetter");
        }else{
            request.setAttribute("error",ret);
            return mapping.findForward("error");
        }
    }
    //�鿴��ʷ��¼
    private ActionForward queryHistory(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) {
        request.setAttribute("history",sendLetterDAO.query());
        return mapping.findForward("queryHistory");
    }
    //���ն���Ϣ
    /*private ActionForward getLetterQuery(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) {
        request.setAttribute("shortLetter",sendLetterDAO.getLetter());
        return mapping.findForward("getLetterQuery");
    }*/
}
