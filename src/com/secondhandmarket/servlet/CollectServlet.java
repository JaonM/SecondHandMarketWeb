package com.secondhandmarket.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.secondhandmarket.model.Collectship;
import com.secondhandmarket.service.CollectshipService;

/**
 * 处理用户收藏请求Servlet
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
				jsonObj.put("result", "收藏成功");
			}else if(result==-1) {
				jsonObj.put("info", "fail");
				jsonObj.put("result", "你已经收藏过了");
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
	
	//处理用户收藏动作
	private int userCollectItem(int userId,int itemId) {
		CollectshipService collectshipService=(CollectshipService)getContext().getBean("collectshipService");
		return collectshipService.insert(new Collectship(userId,itemId));
	}
	
	//处理用户取消收藏动作
	private int userDeleteItem(int userId,int itemId) {
		CollectshipService collectshipService=(CollectshipService)getContext().getBean("collectshipService");
		return collectshipService.delete(new Collectship(userId,itemId));
	}
	
	//判断物品是否被用户收藏过
	private boolean isCollected(int userId,int itemId) {
		CollectshipService collectshipService=(CollectshipService)getContext().getBean("collectshipService");
		return collectshipService.isCollected(userId, itemId);
	}
}
