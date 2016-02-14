package com.secondhandmarket.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.secondhandmarket.model.Relationship;
import com.secondhandmarket.model.RelationshipId;
import com.secondhandmarket.model.User;
import com.secondhandmarket.service.ItemService;
import com.secondhandmarket.service.RelationshipService;
import com.secondhandmarket.service.UserService;

/**
 * 处理关注功能Servlet
 * @author maqiang
 *
 */
public class RelationshipServlet extends BaseServlet {

	@Override
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException {
		
	}
	
	@Override
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException {
		String key=req.getParameter("key");
		int userId=Integer.valueOf(req.getParameter("userId"));
		if(key.equals("attention")) {
			JSONObject jsonObject=new JSONObject();
			List<User> userList=getUserAttentionList(userId);
			JSONArray jsonArray=new JSONArray();
			for(User user:userList) {
				JSONObject jsonObj=new JSONObject();
				jsonObj.put("userId", user.getId());
				jsonObj.put("nickName", user.getNickName());
				jsonObj.put("name",user.getName());
				jsonObj.put("phone",user.getPhone());
				jsonObj.put("sex",user.getSex());
				jsonObj.put("selfIntroduction", user.getSelfIntroduction());
				jsonObj.put("sno", user.getsNo());
				jsonObj.put("photopath", user.getUserPhoto());
				jsonObj.put("attentionCount", getUserAttentionCount(user.getId()));
				jsonObj.put("beAttentionedCount", getUserBeAttentionedCount(user.getId()));
				jsonObj.put("publishCount", getPublishCount(user.getId()));
				jsonArray.add(jsonObj);
			}
			jsonObject.put("info","success");
			jsonObject.put("results", jsonArray);
			
			res.setContentType("text/html;charset=UTF-8");
			res.getWriter().print(jsonObject.toString());

		} else if(key.equals("beAttentioned")) {
			JSONObject jsonObject=new JSONObject();
			List<User> userList=getUserBeAttentionedList(userId);
			JSONArray jsonArray=new JSONArray();
			for(User user:userList) {
				JSONObject jsonObj=new JSONObject();
				jsonObj.put("userId", user.getId());
				jsonObj.put("nickName", user.getNickName());
				jsonObj.put("name",user.getName());
				jsonObj.put("phone",user.getPhone());
				jsonObj.put("sex",user.getSex());
				jsonObj.put("selfIntroduction", user.getSelfIntroduction());
				jsonObj.put("sno", user.getsNo());
				jsonObj.put("photopath", user.getUserPhoto());
				jsonObj.put("attentionCount", getUserAttentionCount(user.getId()));
				jsonObj.put("beAttentionedCount", getUserBeAttentionedCount(user.getId()));
				jsonObj.put("publishCount", getPublishCount(user.getId()));
				jsonArray.add(jsonObj);
			}
			jsonObject.put("info","success");
			jsonObject.put("results", jsonArray);
			res.setContentType("text/html;charset=UTF-8");
			res.getWriter().print(jsonObject.toString());
		} else if(key.equals("relationship")) {
			int otherId=Integer.valueOf(req.getParameter("otherId"));
			Relationship relationship=getRelationship(userId,otherId);
			JSONObject jsonObj=new JSONObject();
			if(relationship!=null) {
				res.setContentType("text/html;charset=UTF-8");
				jsonObj.put("relation", String.valueOf(relationship.getRelation()));
			}else {
				res.setContentType("text/html;charset=UTF-8");
			
				jsonObj.put("relation", String.valueOf(0));
			}
			res.getWriter().print(jsonObj.toString());
		} else if(key.equals("payAttention")) {
			int otherId=Integer.valueOf(req.getParameter("otherId"));
			int result=payAttention(userId,otherId);
			JSONObject jsonObj=new JSONObject();
			res.setContentType("text/html;charset=UTF-8");
			if(result==1) {
				jsonObj.put("info", "success");
			} else
				jsonObj.put("info","fail");
			res.getWriter().print(jsonObj.toString());
		} 
		
	}
	
	//根据userId 获取用户
	private User getUser(int userId) {
		UserService userService=(UserService)getContext().getBean("userService");
		return userService.find(userId);
	}
	
	//获取用户关注的人User列表
	private List<User> getUserAttentionList(int userId) {
		RelationshipService relationshipService=(RelationshipService)getContext().getBean("relationshipService");
		List<User> userList=new ArrayList<User>();
		List<Integer> idList=relationshipService.getUserAttentionIdList(userId);
		for(Integer id:idList) {
			User user=getUser(id);
			userList.add(user);
		}
		return userList;
	}
	
	//获取关注用户的人的User列表
	private List<User> getUserBeAttentionedList(int userId) {
		RelationshipService relationshipService=(RelationshipService)getContext().getBean("relationshipService");
		List<User> userList=new ArrayList<User>();
		List<Integer> idList=relationshipService.getUserBeAttentionedIdList(userId);
		for(Integer id:idList) {
			User user=getUser(id);
			userList.add(user);
		}
		return userList;
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
	
	//得到用户发布数量
	private int getPublishCount(int userId) {
		ItemService itemService=(ItemService)getContext().getBean("itemService");
		return itemService.getPublishCount(userId);
	}
	
	//判断两个User 之间的关系
	private Relationship getRelationship(int userId,int otherId) {
		RelationshipService relationshipService=(RelationshipService)getContext().getBean("relationshipService");
		RelationshipId id=new RelationshipId(userId,otherId);
		return relationshipService.findRelationship(id);
	}
	
	//插入到Relationship表中
	private int payAttention(int userId,int otherId) {
		RelationshipService relationshipService=(RelationshipService)getContext().getBean("relationshipService");
		RelationshipId id=new RelationshipId(userId,otherId);
		Relationship relationship=new Relationship();
		relationship.setRelation((byte)1);
		relationship.setRelationshipId(id);
		return relationshipService.insert(relationship);
	}
}

