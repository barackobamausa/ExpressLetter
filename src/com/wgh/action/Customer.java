package com.wgh.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpException;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wgh.actionForm.CustomerForm;
import com.wgh.core.ChStr;
import com.wgh.dao.CustomerDAO;

public class Customer extends Action{
    private CustomerDAO customerDAO = null;
    private ChStr chStr=new ChStr();
    public Customer() {
        this.customerDAO = new CustomerDAO();
    }	
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws HttpException, IOException{
        String action = request.getParameter("action");
        System.out.println("��ȡ�Ĳ�ѯ�ַ�����" + action);
        if (action == null || "".equals(action)) {
        	request.setAttribute("error","���Ĳ�������");
            return mapping.findForward("error");
        }else if ("customerQuery".equals(action)) {
            return customerQuery(mapping, form, request,response);
		}else if("customerAdd".equals(action)){
			 return customerAdd(mapping, form, request,response);
		}else if("customerDel".equals(action)){
			return customerDel(mapping, form, request,response);
		} else if("customerModifyQ".equals(action)){
                return customerQueryModify(mapping, form, request,response);
        }else if("customerModify".equals(action)){
            return customerModify(mapping, form, request,response);
        }
			request.setAttribute("error", "����ʧ�ܣ�");
			return mapping.findForward("error");
	}
    //��ѯ�ͻ���Ϣ
    private ActionForward customerQuery(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) {
        request.setAttribute("customerQuery", customerDAO.query(0));
        return mapping.findForward("customerQuery");
    }

    //��ӿͻ���Ϣ
    private ActionForward customerAdd(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws HttpException, IOException {
        CustomerForm customerForm = (CustomerForm) form;
        //�˴���Ҫ��������ת��
        customerForm.setName(chStr.toChinese(customerForm.getName()));
        customerForm.setAddress(chStr.toChinese(customerForm.getAddress()));
        customerForm.setArea(chStr.toChinese(customerForm.getArea()));
        customerForm.setBankName(chStr.toChinese(customerForm.getBankName()));
        customerForm.setLinkName(chStr.toChinese(customerForm.getLinkName()));
        int ret = customerDAO.insert(customerForm);
        System.out.println("����ֵret��"+ret);
        if (ret == 1) {
            return mapping.findForward("customerAdd");
        } else if(ret==2){
            request.setAttribute("error","�ÿͻ���Ϣ�Ѿ���ӣ�");
            return mapping.findForward("error");
        }else {
            request.setAttribute("error","��ӿͻ���Ϣʧ�ܣ�");
            return mapping.findForward("error");
        }
    }
    //�޸Ŀͻ���Ϣ�Ĳ�ѯ
    private ActionForward customerQueryModify(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) {
        request.setAttribute("customerQuery",customerDAO.query(Integer.parseInt(request.getParameter("id"))));
        return mapping.findForward("customerQueryModify");
    }
    //�޸Ŀͻ���Ϣ
    private ActionForward customerModify(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response){
        CustomerForm customerForm=(CustomerForm) form;
        //�˴���Ҫ��������ת��
        customerForm.setName(chStr.toChinese(customerForm.getName()));
        customerForm.setAddress(chStr.toChinese(customerForm.getAddress()));
        customerForm.setArea(chStr.toChinese(customerForm.getArea()));
        customerForm.setBankName(chStr.toChinese(customerForm.getBankName()));
        customerForm.setLinkName(chStr.toChinese(customerForm.getLinkName()));
        int ret=customerDAO.update(customerForm);
        if(ret==0){
            request.setAttribute("error","�޸Ŀͻ���Ϣʧ�ܣ�");
            return mapping.findForward("error");
        }else{
            return mapping.findForward("customerModify");
        }
    }
    //ɾ���ͻ���Ϣ
    private ActionForward customerDel(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) {
        CustomerForm customerForm = (CustomerForm) form;
        customerForm.setID(Integer.parseInt(request.getParameter("id")));
        int ret = customerDAO.delete(customerForm);
        if (ret == 0) {
            request.setAttribute("error","ɾ���ͻ���Ϣʧ�ܣ���");
            return mapping.findForward("error");
        } else {
            return mapping.findForward("customerDel");
        }
    }
}
