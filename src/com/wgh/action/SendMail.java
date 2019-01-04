package com.wgh.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.*;
import com.wgh.core.ChStr;
import com.wgh.actionForm.SendMailForm;
import com.wgh.dao.CustomerDAO;
import com.wgh.dao.PersonnelDAO;
import com.wgh.dao.SendMailDAO;

public class SendMail extends Action{
    private SendMailDAO sendMailDAO = null;
    private PersonnelDAO personnelDAO=null;
    private CustomerDAO customerDAO=null;
    private ChStr chStr=new ChStr();
    public SendMail() {
        this.sendMailDAO = new SendMailDAO();
        this.personnelDAO=new PersonnelDAO();
        this.customerDAO=new CustomerDAO();
    }	
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
        String action = request.getParameter("action");
        System.out.println("��ȡ�Ĳ�ѯ�ַ�����" + action);
        if (action == null || "".equals(action)) {
            request.setAttribute("error","���Ĳ�������");			//��������Ϣ���浽error��
            return mapping.findForward("error");				//ת����ʾ������Ϣ��ҳ��
        }else if ("addMail".equals(action)) {
            return addMail(mapping, form, request,response);
        }else if("sendMail".equals(action)){
            return sendMail(mapping, form, request,response);
        }
			request.setAttribute("error", "����ʧ�ܣ�");
			return mapping.findForward("error");
	}

    //��ѯ�ռ����б�
    private ActionForward addMail(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) {
        request.setAttribute("personnelQuery",personnelDAO.query(0));
        request.setAttribute("customerQuery",customerDAO.query(0));
        return mapping.findForward("addMail");
    }
    //Ⱥ���ʼ�
    private ActionForward sendMail(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response){
        SendMailForm sendMailForm=(SendMailForm) form;
        sendMailForm.setTitle(chStr.toChinese(sendMailForm.getTitle()));
        sendMailForm.setAdjunct(chStr.toChinese(sendMailForm.getAdjunct()));
        sendMailForm.setContent(chStr.toChinese(sendMailForm.getContent()));
        int ret=sendMailDAO.sendMail(sendMailForm);
        if(ret==0){
            request.setAttribute("error","�ʼ�����ʧ�ܣ�");
            return mapping.findForward("error");
        }else{
            return mapping.findForward("sendMail");
        }
    }
}
