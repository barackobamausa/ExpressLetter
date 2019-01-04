package com.wgh.action;

import org.apache.struts.action.*;

import com.wgh.actionForm.InfoTypeForm;
import com.wgh.core.ChStr;
import com.wgh.dao.InfoTypeDAO;

import javax.servlet.http.*;

public class InfoType extends Action {
    private InfoTypeDAO infoTypeDAO = null;
    private ChStr chStr=new ChStr();
    public InfoType() {
        this.infoTypeDAO = new InfoTypeDAO();
    }	
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
        String action = request.getParameter("action");
        System.out.println("��ȡ�Ĳ�ѯ�ַ�����" + action);
        if (action == null || "".equals(action)) {
            return mapping.findForward("error");
		}else if ("infoTypeQuery".equals(action)) {
            return infoTypeQuery(mapping, form, request,response);
			
		}else if("infoTypeAdd".equals(action)){
			 return infoTypeAdd(mapping, form, request,response);
		}else if("infoTypeDel".equals(action)){
			return infoTypeDel(mapping, form, request,response);
		}
		request.setAttribute("error", "����ʧ�ܣ�");
		return mapping.findForward("error");
	}

    //��ѯ��Ϣ���
    private ActionForward infoTypeQuery(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) {
        request.setAttribute("infoTypeQuery", infoTypeDAO.query(0));
        return mapping.findForward("infoTypeQuery");
    }

    //�����Ϣ���
    private ActionForward infoTypeAdd(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) {
        InfoTypeForm infoTypeForm = (InfoTypeForm) form;
        infoTypeForm.setName(chStr.toChinese(infoTypeForm.getName()));
        int ret = infoTypeDAO.insert(infoTypeForm);
        System.out.println("����ֵret��"+ret);
        if (ret == 1) {
            return mapping.findForward("infoTypeAdd");
        } else if(ret==2){
            request.setAttribute("error","����Ϣ����Ѿ���ӣ�");
            return mapping.findForward("error");
        }else {
            request.setAttribute("error","�����Ϣ���ʧ�ܣ�");
            return mapping.findForward("error");
        }
    }

    //ɾ����Ϣ���
    private ActionForward infoTypeDel(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) {
        InfoTypeForm infoTypeForm = (InfoTypeForm) form;
        infoTypeForm.setID(Integer.parseInt(request.getParameter("id")));
        int ret = infoTypeDAO.delete(infoTypeForm);
        if (ret != 0) {
            request.setAttribute("error","ɾ����Ϣ���ʧ�ܣ�");
            return mapping.findForward("error");
        } else {
            return mapping.findForward("infoTypeDel");
        }
    }

}
