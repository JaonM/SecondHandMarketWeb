package com.secondhandmarket.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.secondhandmarket.model.Collectship;
import com.secondhandmarket.service.CollectshipService;

/**
 * �����û��ղ�����Servlet
 * @author maqiang
 *
 */
public class CollectServlet extends BaseServlet {

	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException{
		//doPost(req,res);
	}
	
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException{
		int userId=Integer.valueOf(req.getParameter("userId"));
		int itemId=Integer.valueOf(req.getParameter("itemId"));
		String key=req.getParameter("key");
		res.setContentType("text/html;charset=UTF-8");
		if(key.equals("insert")) {
			int result=userCollectItem(userId,itemId);
			JSONObject jsonObj=new JSONObject();
			if(result==1) {
				jsonObj.put("info", "success");
				jsonObj.put("result", "�ղسɹ�");
			}else if(result==-1) {
				jsonObj.put("info", "fail");
				jsonObj.put("result", "���Ѿ��ղع���");
			}
			res.getWriter().print(jsonObj.toString());
		} else if(key.equals("delete")) {
			userDeleteItem(userId,itemId);	
		} else if(key.equals("check")) {
			JSONObject jsonObj=new JSONObject();
			boolean isCollected=isCollected(userId,itemId);
			if(isCollected)
				jsonObj.put("isCollected", true);
			else
				jsonObj.put("isCollected", false);
		}
	}
	
	//�����û��ղض���
	private int userCollectItem(int userId,int itemId) {
		CollectshipService collectshipService=(CollectshipService)getContext().getBean("collectshipService");
		return collectshipService.insert(new Collectship(userId,itemId));
	}
	
	//�����û�ȡ���ղض���
	private int userDeleteItem(int userId,int itemId) {
		CollectshipService collectshipService=(CollectshipService)getContext().getBean("collectshipService");
		return collectshipService.delete(new Collectship(userId,itemId));
	}
	
	//�ж���Ʒ�Ƿ��û��ղع�
	private boolean isCollected(int userId,int itemId) {
		CollectshipService collectshipService=(CollectshipService)getContext().getBean("collectshipService");
		return collectshipService.isCollected(userId, itemId);
	}
}
