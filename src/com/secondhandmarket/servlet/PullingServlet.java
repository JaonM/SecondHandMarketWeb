package com.secondhandmarket.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.secondhandmarket.model.Item;
import com.secondhandmarket.model.ItemPhoto;
import com.secondhandmarket.model.Message;
import com.secondhandmarket.model.User;
import com.secondhandmarket.service.ItemPhotoService;
import com.secondhandmarket.service.ItemService;
import com.secondhandmarket.service.MessageService;
import com.secondhandmarket.service.UserService;

/**
 * 处理消息推送的Servlet
 * 终端会每隔一段时间发送请求
 * @author maqiang
 *
 */
public class PullingServlet extends BaseServlet {
	
	@Override
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException {
		
	}
	
	@Override
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException {
		String key=req.getParameter("key");
		if(key.equals("order")) {
			int userId=Integer.valueOf(req.getParameter("userId"));
			List<Item> list=getOrderedItems(userId);
			JSONObject jsonObj=new JSONObject();
			if(list.size()>0) {
				jsonObj.put("info", "success");
				JSONArray jsonArray=new JSONArray();
				for(Item item:list) {
					if(item.getIsSend()==0) {
						JSONObject jsonResult=new JSONObject();
						jsonResult.put("itemId", item.getId());
						jsonResult.put("name", item.getName());
						jsonResult.put("price", item.getPrice());
						jsonResult.put("description",item.getDescription());
						jsonResult.put("photoCount", item.getPhotoCount());
						jsonResult.put("ownerId", item.getOwnerId());
						jsonResult.put("uploadTime", item.getUploadTime());
						jsonResult.put("collectCount",item.getCollectCount());
						jsonResult.put("classfication1", item.getClassfication1());
						jsonResult.put("classfication2", item.getClassfication2());
						jsonResult.put("photoPath", getItemPhotoPath(item.getId()));
						jsonResult.put("status", item.getStatus());
						jsonResult.put("buyerId", item.getBuyerId());
						jsonArray.add(jsonResult);
						
						//将物品设置为已发送
						item.setIsSend(1);
						ItemService itemService=(ItemService)getContext().getBean("itemService");
						itemService.update(item);
					}
				}
				jsonObj.put("results", jsonArray);
			} else
				jsonObj.put("info","fail");
			res.setContentType("text/html;charset=UTF-8");
			res.getWriter().print(jsonObj.toString());
		} else if(key.equals("message")) {
			int userId=Integer.valueOf(req.getParameter("userId"));
			Map<Integer,List<Message>> map=getItemMessages(userId);
			JSONObject jsonObject=new JSONObject();
			if(map.keySet().size()>0) {
				
				jsonObject.put("info","success");
				JSONArray jsonArray=new JSONArray();
				for(int itemId:map.keySet()) {
					JSONObject jsonObj=new JSONObject();
					jsonObj.put("itemName", findItem(itemId).getName());
					JSONArray resultArray=new JSONArray();
					for(Message message:map.get(itemId)) {
						if(message.getIsSend()==0&&message.getIsReceiver()==0) {										//如果消息以前没有发送过才进行推送
							JSONObject jsonResult=new JSONObject();
							jsonResult.put("userId", message.getUserId());
							jsonResult.put("itemId", message.getItemId());
							jsonResult.put("message", message.getMessage());
							jsonResult.put("userNickName", findUser(message.getUserId()).getNickName());
							resultArray.add(jsonResult);
							message.setIsSend(1);
							updateMessage(message);
						}
					}
					jsonObj.put("messageResults", resultArray);
					jsonArray.add(jsonObj);
				}
				jsonObject.put("results", jsonArray);
			} else
				jsonObject.put("info", "fail");
			
			res.setContentType("text/html;charset=UTF-8");
			res.getWriter().print(jsonObject.toString());
		} else if(key.equals("userMessage")) {									//给用户的留言
			int userId=Integer.valueOf(req.getParameter("userId"));
			List<Message> list=getUserMessages(userId);
			JSONObject jsonObj=new JSONObject();
			if(list.size()>0) {
				jsonObj.put("info", "success");
				JSONArray jsonArray=new JSONArray();
				for(Message message:list) {
					if(message.getIsSend()==0&&message.getIsReceiver()==1) {
						JSONObject jsonObject=new JSONObject();
						jsonObject.put("senderName", getOwner(message.getItemId()).getNickName());
						jsonObject.put("senderId", getOwner(message.getItemId()).getId());
						jsonObject.put("itemId", message.getItemId());
						jsonObject.put("message", message.getMessage());
						DateFormat df=new SimpleDateFormat("yyyy/MM/dd");
						jsonObject.put("time",df.format(message.getTime()));
						jsonArray.add(jsonObject);
						message.setIsSend(1);
						updateMessage(message);
					}
				}
				jsonObj.put("results", jsonArray);
			}else {
				jsonObj.put("info", "fail");
			}
			res.setContentType("text/html;charset=UTF-8");
			res.getWriter().println(jsonObj.toString());
			System.out.println(jsonObj.toString());
		}
	}
	
	/**
	 * 查看用户有没有物品已被预订
	 */
	private List<Item> getOrderedItems(int userId) {
		ItemService itemService=(ItemService)getContext().getBean("itemService");
		List<Item> itemList=itemService.findByOwnerId(userId);
		List<Item> orderList=new ArrayList<Item>();
		for(Item item:itemList) {
			if(item.getStatus()==1)
				orderList.add(item);
		}
		return orderList;
	}
	
	/**
	 * 查看某个用户所发布的物品的对应留言
	 */
	private Map<Integer,List<Message>> getItemMessages(int userId) {
		Map<Integer,List<Message>> map=new HashMap<Integer,List<Message>>();
		ItemService itemService=(ItemService)getContext().getBean("itemService");
		List<Item> itemList=itemService.findByOwnerId(userId);
		MessageService messageService=(MessageService)getContext().getBean("messageService");
		for(Item item:itemList) {
			map.put(item.getId(), messageService.findByItemId(item.getId()));
		}
		return map;
	}
	
	/**
	 * 获取对应item的图片路径
	 * @param itemId
	 * @return
	 */
	private String getItemPhotoPath(int itemId) {
		ItemPhotoService itemPhotoService=(ItemPhotoService)getContext().getBean("itemPhotoService");
		StringBuffer sb=new StringBuffer();
		List list=itemPhotoService.findByItemId(itemId);
		if(list==null)
			return null;
		for(int i=0;i<list.size();i++) {
			sb.append(((ItemPhoto)list.get(i)).getPhotoPath());
			sb.append(",");
		}
	
		return sb.substring(0, sb.length()-1);
	}
	
	/**
	 * 根据userId查找user
	 */
	private User findUser(int userId) {
		UserService userService=(UserService)getContext().getBean("userService");
		return userService.find(userId);
	}
	
	/**
	 * 通过itemId获得Item
	 */
	private Item findItem(int itemId) {
		ItemService itemService=(ItemService)getContext().getBean("itemService");
		return itemService.find(itemId);
	}
	
	/**
	 * 更新message	,设为已发送
	 */
	public int updateMessage(Message message) {
		MessageService messageService=(MessageService)getContext().getBean("messageService");
		return messageService.update(message);
	}
	
	/**
	 * 根据itemId查找物主
	 */
	private User getOwner(int itemId) {
		ItemService itemService=(ItemService)getContext().getBean("itemService");
		UserService userService=(UserService)getContext().getBean("userService");
		return userService.find(itemService.find(itemId).getOwnerId());
	}
	
	/**
	 * 根据userId得到用户的留言
	 */
	private List<Message> getUserMessages(int userId) {
		MessageService messageService=(MessageService)getContext().getBean("messageService");
		List<Message> resultList=new ArrayList<Message>();
		try {
			for(Message message:messageService.findByUserId(userId)) {
				if(message.getIsReceiver()==1) {
					resultList.add(message);
				}
			}
		} catch(NullPointerException e) {
			e.printStackTrace();
		}
		return resultList;
	}
	
}
