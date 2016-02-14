package com.secondhandmarket.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.secondhandmarket.model.Message;
import com.secondhandmarket.model.User;
import com.secondhandmarket.service.ItemService;
import com.secondhandmarket.service.MessageService;
import com.secondhandmarket.service.UserService;

/**
 * 处理用户对物品留言的Servlet
 * @author maqiang
 *
 */
public class MessageServlet extends BaseServlet {

	@Override
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException {
		
	}
	
	@Override
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException {
		String key=req.getParameter("key");
		if(key.equals("leaveMessage")) {
			int userId=Integer.valueOf(req.getParameter("userId"));
			int itemId=Integer.valueOf(req.getParameter("itemId"));
			
			//读取流中的留言
			BufferedReader br=new BufferedReader(new InputStreamReader(req.getInputStream(),"UTF-8"));
			String message=br.readLine();
			
			System.out.println("userId:"+userId+" itemId:"+itemId+" message:"+message);
			br.close();
			int result=leaveMessage(userId,itemId,message,0);
			JSONObject jsonObj=new JSONObject();
			if(result==1) {
				jsonObj.put("info", "success");
			} else
				jsonObj.put("info", "fail");
			res.setContentType("text/html;charset=UTF-8");
			res.getWriter().print(jsonObj.toString());
		} else if(key.equals("findMessage")) {		//寻找物品的留言
			int itemId=Integer.valueOf(req.getParameter("itemId"));
			List<Message> list=findByItem(itemId);
			JSONObject jsonObj=new JSONObject();
			if(list!=null) {
				jsonObj.put("info", "success");
				JSONArray jsonArray=new JSONArray();
				for(Message message:list) {
					if(message.getIsReceiver()==0) {
						JSONObject jsonObject=new JSONObject();
						jsonObject.put("userId", message.getUserId());
						jsonObject.put("itemId", message.getItemId());
						jsonObject.put("message", message.getMessage());
						jsonObject.put("userNickName", findUser(message.getUserId()).getNickName());
						DateFormat format=new SimpleDateFormat("yyyy-mm-dd");
						jsonObject.put("time", format.format(message.getTime()));
						jsonArray.add(jsonObject);
					}
				}
				jsonObj.put("results", jsonArray);
				
				
			} else
				jsonObj.put("info", "fail");
			
			res.setContentType("text/html;charset=UTF-8");
			res.getWriter().print(jsonObj.toString());
		}else if(key.equals("deleteMessage")) {
//			int userId=Integer.valueOf(req.getParameter("userId"));
//			int itemId=Integer.valueOf(req.getParameter("itemId"));
//			JSONObject jsonObj=new JSONObject();
////			int result=deleteMessage(userId, itemId);
//			if(result==1)
//				jsonObj.put("info", "success");
//			else
//				jsonObj.put("info", "fail");
//			res.setContentType("text/html;charset=UTF-8");
//			res.getWriter().print(jsonObj.toString());
		} else if(key.equals("toBuyer")) {
			int userId=Integer.valueOf(req.getParameter("userId"));
			int itemId=Integer.valueOf(req.getParameter("itemId"));
			
			//读取流中的留言
			BufferedReader br=new BufferedReader(new InputStreamReader(req.getInputStream(),"UTF-8"));
			String message=br.readLine();
			
			br.close();
			int result=leaveMessage(userId,itemId,message,1);
			JSONObject jsonObj=new JSONObject();
			if(result==1) {
				jsonObj.put("info", "success");
			} else
				jsonObj.put("info", "fail");
			res.setContentType("text/html;charset=UTF-8");
			res.getWriter().print(jsonObj.toString());
		} else if(key.equals("ownerUserMessage")) {			//物主和用户的消息
			int userId=Integer.valueOf(req.getParameter("userId"));
			int itemId=Integer.valueOf(req.getParameter("itemId"));
			List<Message> list=ownerBuyerMessage(userId,itemId);
			JSONObject jsonObj=new JSONObject();
			if(list.size()>0) {
				jsonObj.put("info", "success");
				JSONArray jsonArray=new JSONArray();
				for(Message message:list) {
					JSONObject jsonObject=new JSONObject();
					if(message.getIsReceiver()==1) {
						jsonObject.put("senderName", getOwner(message.getItemId()).getNickName());
						jsonObject.put("senderId", getOwner(message.getItemId()).getId());
						jsonObject.put("message", message.getMessage());
						jsonObject.put("itemId", message.getItemId());
						jsonObject.put("itemOwner", true);
						DateFormat df=new SimpleDateFormat("yyyy/MM/dd");
						
						jsonObject.put("time", df.format(message.getTime()));
					}else if(message.getIsReceiver()==0) {
						jsonObject.put("senderId", message.getUserId());
						jsonObject.put("senderName", findUser(message.getUserId()).getNickName());
						jsonObject.put("message", message.getMessage());
						DateFormat df=new SimpleDateFormat("yyyy/MM/dd");
//						df.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
						jsonObject.put("itemId", message.getItemId());
						jsonObject.put("itemOwner", false);
						jsonObject.put("time", df.format(message.getTime()));
						
						System.out.println(df.format(message.getTime()));
					}
					jsonArray.add(jsonObject);
				}
				jsonObj.put("results", jsonArray);
			}else  {
				jsonObj.put("info", "fail");
			}
			res.setContentType("text/html;charset=UTF-8");
			res.getWriter().print(jsonObj.toString());
		}
	}
	
	/**
	 * 给物品留言
	 */
	private int leaveMessage(int userId,int itemId,String message,int isReceiver) {
		MessageService messageService=(MessageService)getContext().getBean("messageService");
		return messageService.insert(new Message(userId,itemId,message,0,isReceiver));
	}
	
	/**
	 * 查询某个物品的留言
	 */
	private List<Message> findByItem(int itemId) {
		MessageService messageService=(MessageService)getContext().getBean("messageService");
		return messageService.findByItemId(itemId);
	}
	
//	/**
//	 * 删除某个用户对物品的留言
//	 */
//	private int deleteMessage(int userId,int itemId) {
//		MessageService messageService=(MessageService)getContext().getBean("messageService");
//		Message message=messageService.find(userId, itemId);
//		return messageService.delete(message);
//	}
	
	/**
	 * 根据userId查找user
	 */
	private User findUser(int userId) {
		UserService userService=(UserService)getContext().getBean("userService");
		return userService.find(userId);
	}
	
	/**
	 * 获取预订者和物主的留言
	 */
	private List<Message> ownerBuyerMessage(int userId,int itemId) {
		MessageService messageService=(MessageService)getContext().getBean("messageService");
		return messageService.find(userId, itemId);
	
	}
	
	/**
	 * 根据itemId查找物主
	 */
	private User getOwner(int itemId) {
		ItemService itemService=(ItemService)getContext().getBean("itemService");
		return findUser(itemService.find(itemId).getOwnerId());
	}

}
