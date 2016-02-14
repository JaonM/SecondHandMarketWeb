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
 * �����ע����Servlet
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
	
	//����userId ��ȡ�û�
	private User getUser(int userId) {
		UserService userService=(UserService)getContext().getBean("userService");
		return userService.find(userId);
	}
	
	//��ȡ�û���ע����User�б�
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
	
	//��ȡ��ע�û����˵�User�б�
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
	 * ��ȡuser��ע���˵�����
	 */
	private int getUserAttentionCount(int userId) {
		RelationshipService relationshipService=(RelationshipService)getContext().getBean("relationshipService");
		return relationshipService.getUserAttentionCount(userId);
	}
	
	/**
	 * ��ȡ��עuser���˵�����
	 * @param userId
	 * @return
	 */
	private int getUserBeAttentionedCount(int userId) {
		RelationshipService relationshipService=(RelationshipService)getContext().getBean("relationshipService");
		return relationshipService.getUserBeAttentionedCount(userId);
	}
	
	//�õ��û���������
	private int getPublishCount(int userId) {
		ItemService itemService=(ItemService)getContext().getBean("itemService");
		return itemService.getPublishCount(userId);
	}
	
	//�ж�����User ֮��Ĺ�ϵ
	private Relationship getRelationship(int userId,int otherId) {
		RelationshipService relationshipService=(RelationshipService)getContext().getBean("relationshipService");
		RelationshipId id=new RelationshipId(userId,otherId);
		return relationshipService.findRelationship(id);
	}
	
	//���뵽Relationship����
	private int payAttention(int userId,int otherId) {
		RelationshipService relationshipService=(RelationshipService)getContext().getBean("relationshipService");
		RelationshipId id=new RelationshipId(userId,otherId);
		Relationship relationship=new Relationship();
		relationship.setRelation((byte)1);
		relationship.setRelationshipId(id);
		return relationshipService.insert(relationship);
	}
}

