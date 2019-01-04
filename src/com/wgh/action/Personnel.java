package com.wgh.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.*;

import com.wgh.actionForm.PersonnelForm;
import com.wgh.core.ChStr;
import com.wgh.dao.PersonnelDAO;

public class Personnel extends Action{
    private PersonnelDAO personnelDAO = null;
    private ChStr chStr=new ChStr();
    public Personnel() {
        this.personnelDAO = new PersonnelDAO();
    }	
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws IOException{
        String action = request.getParameter("action");
        System.out.println("��ȡ�Ĳ�ѯ�ַ�����" + action);
        if (action == null || "".equals(action)) {
            return mapping.findForward("error");
        }else if ("personnelQuery".equals(action)) {
            return personnelQuery(mapping, form, request,response);
			
		}else if("personnelAdd".equals(action)){
			 return personnelAdd(mapping, form, request,response);
		}else if("personnelDel".equals(action)){
			return personnelDel(mapping, form, request,response);
		} else if("personnelModifyQ".equals(action)){
                return personnelQueryModify(mapping, form, request,response);
        }else if("personnelModify".equals(action)){
            return personnelModify(mapping, form, request,response);
        }
			request.setAttribute("error", "����ʧ�ܣ�");
			return mapping.findForward("error");
	}
    //��ѯԱ����Ϣ
    private ActionForward personnelQuery(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) {
        request.setAttribute("personnelQuery", personnelDAO.query(0));
        return mapping.findForward("personnelQuery");
    }

    //���Ա����Ϣ
    private ActionForward personnelAdd(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException {
        PersonnelForm personnelForm = (PersonnelForm) form;
        //�˴���Ҫ��������ת��
        personnelForm.setName(chStr.toChinese(personnelForm.getName()));
        personnelForm.setSex(chStr.toChinese(personnelForm.getSex()));
        personnelForm.setSchool(chStr.toChinese(personnelForm.getSchool()));
        personnelForm.setEducation(chStr.toChinese(personnelForm.getEducation()));
        personnelForm.setSpecialty(chStr.toChinese(personnelForm.getSpecialty()));
        personnelForm.setPlace(chStr.toChinese(personnelForm.getPlace()));   
        int ret = personnelDAO.insert(personnelForm);
        System.out.println("����ֵret��"+ret);
        if (ret == 1) {
            return mapping.findForward("personnelAdd");
        } else if(ret==2){
            request.setAttribute("error","��Ա����Ϣ�Ѿ���ӣ�");
            return mapping.findForward("error");
        }else {
            request.setAttribute("error","���Ա����Ϣʧ�ܣ�");
            return mapping.findForward("error");
        }
    }
    //�޸�Ա����Ϣ�Ĳ�ѯ
    private ActionForward personnelQueryModify(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) {
        PersonnelForm personnelForm = (PersonnelForm) form;
        request.setAttribute("personnelQuery",personnelDAO.query(Integer.parseInt(request.getParameter("id"))));
        return mapping.findForward("personnelQueryModify");
    }
    //�޸�Ա����Ϣ
    private ActionForward personnelModify(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response){
        PersonnelForm personnelForm=(PersonnelForm) form;
        //�˴���Ҫ��������ת��
        personnelForm.setName(chStr.toChinese(personnelForm.getName()));
        personnelForm.setSex(chStr.toChinese(personnelForm.getSex()));
        personnelForm.setSchool(chStr.toChinese(personnelForm.getSchool()));
        personnelForm.setEducation(chStr.toChinese(personnelForm.getEducation()));
        personnelForm.setSpecialty(chStr.toChinese(personnelForm.getSpecialty()));
        personnelForm.setPlace(chStr.toChinese(personnelForm.getPlace())); 
        int ret=personnelDAO.update(personnelForm);
        if(ret==0){
            request.setAttribute("error","�޸�Ա����Ϣʧ�ܣ�");
            return mapping.findForward("error");
        }else{
            return mapping.findForward("personnelModify");
        }
    }
    //ɾ��Ա����Ϣ
    private ActionForward personnelDel(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) {
        PersonnelForm personnelForm = (PersonnelForm) form;
        personnelForm.setID(Integer.parseInt(request.getParameter("id")));
        int ret = personnelDAO.delete(personnelForm);
        if (ret == 0) {
            request.setAttribute("error","ɾ��Ա����Ϣʧ�ܣ�");
            return mapping.findForward("error");
        } else {
            return mapping.findForward("personnelDel");
        }
    }
}
