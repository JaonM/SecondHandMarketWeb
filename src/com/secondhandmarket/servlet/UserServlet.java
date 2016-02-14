package com.secondhandmarket.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.secondhandmarket.model.User;
import com.secondhandmarket.service.ItemService;
import com.secondhandmarket.service.RelationshipService;
import com.secondhandmarket.service.UserService;

/**
 * 查找用户Servlet
 * @author maqiang
 *
 */
public class UserServlet extends BaseServlet {

	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException {
		doPost(req,res);
	}
	
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException {
		int userId=Integer.valueOf(req.getParameter("userId"));
		
		User user=getUser(userId);
				 
		JSONObject jsonObject=new JSONObject();
			
		if(user!=null) {
			res.setContentType("text/html;charset=UTF-8");
			try {
				jsonObject.put("info", "success");
				jsonObject.put("userid", user.getId());
				jsonObject.put("nickName", user.getNickName());
				jsonObject.put("name",user.getName());
				jsonObject.put("phone",user.getPhone());
				jsonObject.put("sex",user.getSex());
				jsonObject.put("selfIntroduction", user.getSelfIntroduction());
				jsonObject.put("sno", user.getsNo());
				jsonObject.put("photopath", user.getUserPhoto());
				jsonObject.put("attentionCount", getUserAttentionCount(user.getId()));
				jsonObject.put("beAttentionedCount", getUserBeAttentionedCount(user.getId()));
				jsonObject.put("publishCount", getPublishCount(user.getId()));
				jsonObject.put("orderCount",getUserOrderCount(user.getId()));
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else {
			jsonObject.put("info", "fail");
		}
			
		res.getWriter().println(jsonObject.toString());
	} 
			
		
	/**
	 * 根据userId 查找user
	 * @param userId
	 * @return
	 */
	
	public User getUser(int userId) {
		UserService userService=(UserService)getContext().getBean("userService");
		User user=userService.find(userId);
		return user;
	}
	
	/**
	 * 获取user关注的人的数量
	 */
	private int getUserAttentionCount(int userId) {
		RelationshipService relationshipService=(RelationshipService)getContext().getBean("relationshipService");
		return relationshipService.getUserAttentionCount(userId);
	}
	
	/**
	 * 获取关注user的人的数量
	 * @param userId
	 * @return
	 */
	private int getUserBeAttentionedCount(int userId) {
		RelationshipService relationshipService=(RelationshipService)getContext().getBean("relationshipService");
		return relationshipService.getUserBeAttentionedCount(userId);
	}
	
	private int getPublishCount(int userId) {
		ItemService itemService=(ItemService)getContext().getBean("itemService");
		return itemService.getPublishCount(userId);
	}
	
	/**
	 * 获取用户已购买的数量
	 */
	private int getUserOrderCount(int userId) {
		ItemService itemService=(ItemService)getContext().getBean("itemService");
		return itemService.findByBuyerId(userId).size();
	}
}
