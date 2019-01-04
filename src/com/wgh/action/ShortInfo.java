package com.wgh.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.*;
import com.wgh.actionForm.ShortInfoForm;
import com.wgh.core.ChStr;
import com.wgh.dao.ShortInfoDAO;

public class ShortInfo extends Action{
    private ShortInfoDAO shortInfoDAO = null;
    private ChStr chStr=new ChStr();
    public ShortInfo() {
        this.shortInfoDAO = new ShortInfoDAO();
    }	
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
        String action = request.getParameter("action");
        System.out.println("��ȡ�Ĳ�ѯ�ַ�����" + action);
        if (action == null || "".equals(action)) {
            return mapping.findForward("error");
        }else if ("shortInfoQuery".equals(action)) {
            return shortInfoQuery(mapping, form, request,response);
		}else if("type".equals(action)){
			 return type(mapping, form, request,response);			
		}else if("shortInfoAdd".equals(action)){
			 return shortInfoAdd(mapping, form, request,response);
		}else if("shortInfoDel".equals(action)){
			return shortInfoDel(mapping, form, request,response);
		} else if("shortInfoModifyQ".equals(action)){
                return shortInfoQueryModify(mapping, form, request,response);
        }else if("shortInfoModify".equals(action)){
            return shortInfoModify(mapping, form, request,response);
        }else if("selectShortInfo".equals(action)){
        	return selectShortInfo(mapping,form,request,response);
        }
			request.setAttribute("error", "����ʧ�ܣ�");
			return mapping.findForward("error");
	}
    //��ѯ���ö���
    private ActionForward shortInfoQuery(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) {
        request.setAttribute("shortInfoQuery", shortInfoDAO.query(null));
        return mapping.findForward("shortInfoQuery");
    }
    //���Ͷ���ʱѡ���ö���
    private ActionForward selectShortInfo(ActionMapping mapping,ActionForm form,
    		HttpServletRequest request,HttpServletResponse response){
        ShortInfoForm shortInfoForm = (ShortInfoForm) form;
        shortInfoForm.setTypeID(Integer.parseInt(request.getParameter("id")));
    	request.setAttribute("selectShortInfo",shortInfoDAO.query(shortInfoForm));
    	return mapping.findForward("selectShortInfo");
    }
    //��ѯ�����Ϣ
    private ActionForward type(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) {
        request.setAttribute("typeQuery", shortInfoDAO.queryType(null));
        return mapping.findForward("typeQuery");
    }
    //��ӳ��ö���
    private ActionForward shortInfoAdd(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) {
        ShortInfoForm shortInfoForm = (ShortInfoForm) form;
        //�˴���Ҫ��������ת��
        shortInfoForm.setContent(chStr.toChinese(shortInfoForm.getContent()));
        int ret = shortInfoDAO.insert(shortInfoForm);
        System.out.println("����ֵret��"+ret);
        if (ret == 1) {
            return mapping.findForward("shortInfoAdd");
        } else if(ret==2){
            request.setAttribute("error","�ó��ö����Ѿ���ӣ�");
            return mapping.findForward("error");
        }else {
            request.setAttribute("error","��ӳ��ö���ʧ�ܣ�");
            return mapping.findForward("error");
        }
    }
    //�޸ĳ��ö���Ĳ�ѯ
    private ActionForward shortInfoQueryModify(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) {
        ShortInfoForm shortInfoForm = (ShortInfoForm) form;
        shortInfoForm.setID(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("shortInfoQuery",shortInfoDAO.query(shortInfoForm));
        request.setAttribute("typeQuery", shortInfoDAO.queryType(null));
        return mapping.findForward("shortInfoQueryModify");
    }
    //�޸ĳ��ö���
    private ActionForward shortInfoModify(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response){
        ShortInfoForm shortInfoForm=(ShortInfoForm) form;
        //�˴���Ҫ��������ת��
        shortInfoForm.setContent(chStr.toChinese(shortInfoForm.getContent()));
        int ret=shortInfoDAO.update(shortInfoForm);
        if(ret==0){
            request.setAttribute("error","�޸ĳ��ö���ʧ�ܣ�");
            return mapping.findForward("error");
        }else{
            return mapping.findForward("shortInfoModify");
        }
    }
    //ɾ�����ö���
    private ActionForward shortInfoDel(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) {
        ShortInfoForm shortInfoForm = (ShortInfoForm) form;
        shortInfoForm.setID(Integer.parseInt(request.getParameter("id")));
        int ret = shortInfoDAO.delete(shortInfoForm);
        if (ret != 0) {
            request.setAttribute("error","ɾ�����ö���ʧ�ܣ�");
            return mapping.findForward("error");
        } else {
            return mapping.findForward("shortInfoDel");
        }
    }
}
