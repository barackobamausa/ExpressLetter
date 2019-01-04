package com.wgh.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.*;

import com.wgh.actionForm.ParameterForm;
import com.wgh.dao.ParameterDAO;

public class Parameter extends Action{
    private ParameterDAO parameterDAO = null;
    public Parameter() {
        this.parameterDAO = new ParameterDAO();
    }	
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
        String action = request.getParameter("action");
        System.out.println("��ȡ�Ĳ�ѯ�ַ�����" + action);
        if (action == null || "".equals(action)) {
            return mapping.findForward("error");
        }else if ("parameterQuery".equals(action)) {
            return personnelQuery(mapping, form, request,response);
        }else if("parameterModify".equals(action)){
            return personnelModify(mapping, form, request,response);
        }
			request.setAttribute("error", "����ʧ�ܣ�");
			return mapping.findForward("error");
	}

    //�޸�ϵͳ������Ϣ�Ĳ�ѯ
    private ActionForward personnelQuery(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) {
        //ParameterForm personnelForm = (ParameterForm) form;
       // personnelForm.setID(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("parameterQuery",parameterDAO.query());
        return mapping.findForward("parameterQuery");
    }
    //�޸�ϵͳ������Ϣ
    private ActionForward personnelModify(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response){
        ParameterForm parameterForm=(ParameterForm) form;
        int ret=parameterDAO.update(parameterForm);
        if(ret==0){
            request.setAttribute("error","�趨ϵͳ����ʧ�ܣ�");
            return mapping.findForward("error");
        }else{
            return mapping.findForward("parameterModify");
        }
    }
}
