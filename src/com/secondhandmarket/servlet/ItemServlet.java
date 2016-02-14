package com.secondhandmarket.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.secondhandmarket.model.Collectship;
import com.secondhandmarket.model.Item;
import com.secondhandmarket.model.ItemPhoto;
import com.secondhandmarket.model.Message;
import com.secondhandmarket.service.CollectshipService;
import com.secondhandmarket.service.ItemPhotoService;
import com.secondhandmarket.service.ItemService;
import com.secondhandmarket.service.MessageService;

public class ItemServlet extends BaseServlet {

	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException {
		
		String classfication1=new String(req.getParameter("classfication1").getBytes("ISO-8859-1"),"UTF-8");
		String classfication2=new String(req.getParameter("classfication2").getBytes("ISO-8859-1"),"UTF-8");
		System.out.println(classfication1+" "+classfication2);
		int start=Integer.valueOf(req.getParameter("start"));
		int end=Integer.valueOf(req.getParameter("end"));
		JSONObject jsonObj=new JSONObject();
		List<Item> list=findItem(classfication1, classfication2,start,end);
		if(list!=null) {
			jsonObj.put("info","success");
			JSONArray jsonArray=new JSONArray();
			for(int i=0;i<list.size();i++) {
				JSONObject jsonObject=new JSONObject();
				jsonObject.put("id", list.get(i).getId());
				jsonObject.put("name", list.get(i).getName()); 
				jsonObject.put("price", list.get(i).getPrice());
				jsonObject.put("description", list.get(i).getDescription());
				jsonObject.put("photoCount", list.get(i).getPhotoCount());
				jsonObject.put("ownerId", list.get(i).getOwnerId());
				jsonObject.put("uploadTime", list.get(i).getUploadTime());
				jsonObject.put("collectCount",list.get(i).getCollectCount());
				jsonObject.put("classfication1", list.get(i).getClassfication1());
				jsonObject.put("classfication2", list.get(i).getClassfication2());
				jsonObject.put("photoPath", getItemPhotoPath(list.get(i).getId()));
				jsonObject.put("status", list.get(i).getStatus());
				jsonObject.put("buyerId", list.get(i).getBuyerId());
				jsonArray.add(jsonObject);
			}
			jsonObj.put("results", jsonArray);
		}else
			jsonObj.put("info", "fail");
		res.setContentType("text/html;charset=UTF-8");
		res.getWriter().print(jsonObj.toString());
	}
	
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException {
		String key=req.getParameter("key");
		int start=Integer.valueOf(req.getParameter("start"));
		int end=Integer.valueOf(req.getParameter("end"));
		if(key.equals("newItem")) {				//返回最新动态
			List<Item> list=getNewItem(start,end);
			res.setContentType("text/html;charset=UTF-8");
			JSONObject jsonObj=new JSONObject();
			if(list==null) {
				jsonObj.put("info", "fail");
			} else {
				jsonObj.put("info", "success");
				JSONArray jsonArray=new JSONArray();
				for(int i=0;i<list.size();i++) {
					JSONObject jsonObject=new JSONObject();
					jsonObject.put("id", list.get(i).getId());
					jsonObject.put("name", list.get(i).getName()); 
					jsonObject.put("price", list.get(i).getPrice());
					jsonObject.put("description", list.get(i).getDescription());
					jsonObject.put("photoCount", list.get(i).getPhotoCount());
					jsonObject.put("ownerId", list.get(i).getOwnerId());
					jsonObject.put("uploadTime", list.get(i).getUploadTime());
					jsonObject.put("collectCount",list.get(i).getCollectCount());
					jsonObject.put("classfication1", list.get(i).getClassfication1());
					jsonObject.put("classfication2", list.get(i).getClassfication2());
					jsonObject.put("photoPath", getItemPhotoPath(list.get(i).getId()));
					jsonObject.put("status", list.get(i).getStatus());
					jsonObject.put("buyerId", list.get(i).getBuyerId());
					jsonArray.add(jsonObject);
				}
				jsonObj.put("result", jsonArray);
			}
			res.getWriter().print(jsonObj.toString());
		} else if(key.equals("popItem")) {
			List<Item> list=getPopItem(start,end);
			res.setContentType("text/html;charset=UTF-8");
			JSONObject jsonObj=new JSONObject();
			if(list==null) {
				jsonObj.put("info", "fail");
			} else { 
				jsonObj.put("info", "success");
				JSONArray jsonArray=new JSONArray();
				for(int i=0;i<list.size();i++) {
					JSONObject jsonObject=new JSONObject();
					jsonObject.put("id", list.get(i).getId());
					jsonObject.put("name", list.get(i).getName());
					jsonObject.put("price", list.get(i).getPrice());
					jsonObject.put("description", list.get(i).getDescription());
					jsonObject.put("photoCount", list.get(i).getPhotoCount());
					jsonObject.put("ownerId", list.get(i).getOwnerId());
					jsonObject.put("uploadTime", list.get(i).getUploadTime());
					jsonObject.put("collectCount",list.get(i).getCollectCount());
					jsonObject.put("classfication1", list.get(i).getClassfication1());
					jsonObject.put("classfication2", list.get(i).getClassfication2());
					jsonObject.put("photoPath", getItemPhotoPath(list.get(i).getId()));
					jsonObject.put("status", list.get(i).getStatus());
					jsonObject.put("buyerId", list.get(i).getBuyerId());
					jsonArray.add(jsonObject);
				}
				jsonObj.put("result", jsonArray);
			}
			res.getWriter().print(jsonObj.toString());
		} else if(key.equals("collect")) {
			int userId=Integer.valueOf(req.getParameter("userId"));
			List<Item> list=getUserCollecItem(userId);
			res.setContentType("text/html;charset=UTF-8");
			JSONObject jsonObj=new JSONObject();
			if(list==null) {
				jsonObj.put("info", "fail");
			} else {
				jsonObj.put("info", "success");
				JSONArray jsonArray=new JSONArray();
				for(int i=0;i<list.size();i++) {
					JSONObject jsonObject=new JSONObject();
					jsonObject.put("id", list.get(i).getId());
					jsonObject.put("name", list.get(i).getName());
					jsonObject.put("price", list.get(i).getPrice());
					jsonObject.put("description", list.get(i).getDescription());
					jsonObject.put("photoCount", list.get(i).getPhotoCount());
					jsonObject.put("ownerId", list.get(i).getOwnerId());
					jsonObject.put("uploadTime", list.get(i).getUploadTime());
					jsonObject.put("collectCount",list.get(i).getCollectCount());
					jsonObject.put("classfication1", list.get(i).getClassfication1());
					jsonObject.put("classfication2", list.get(i).getClassfication2());
					jsonObject.put("photoPath", getItemPhotoPath(list.get(i).getId()));
					jsonObject.put("status", list.get(i).getStatus());
					jsonObject.put("buyerId", list.get(i).getBuyerId());
					jsonArray.add(jsonObject);
				}
				jsonObj.put("result", jsonArray);
			}
			res.getWriter().print(jsonObj.toString());
		} else if(key.equals("publish")) {
			int userId=Integer.valueOf(req.getParameter("userId"));
			List<Item> list=getUserPublishItem(userId);
			res.setContentType("text/html;charset=UTF-8");
			JSONObject jsonObj=new JSONObject();
			if(list==null) {
				jsonObj.put("info", "fail");
			} else {
				jsonObj.put("info", "success");
				JSONArray jsonArray=new JSONArray();
				for(int i=0;i<list.size();i++) {
					JSONObject jsonObject=new JSONObject();
					jsonObject.put("id", list.get(i).getId());
					jsonObject.put("name", list.get(i).getName());
					jsonObject.put("price", list.get(i).getPrice());
					jsonObject.put("description", list.get(i).getDescription());
					jsonObject.put("photoCount", list.get(i).getPhotoCount());
					jsonObject.put("ownerId", list.get(i).getOwnerId());
					jsonObject.put("uploadTime", list.get(i).getUploadTime());
					jsonObject.put("collectCount",list.get(i).getCollectCount());
					jsonObject.put("classfication1", list.get(i).getClassfication1());
					jsonObject.put("classfication2", list.get(i).getClassfication2());
					jsonObject.put("photoPath", getItemPhotoPath(list.get(i).getId()));
					jsonObject.put("status", list.get(i).getStatus());
					jsonObject.put("buyerId", list.get(i).getBuyerId());
					jsonArray.add(jsonObject);
				}
				jsonObj.put("result", jsonArray);
			}
			res.getWriter().print(jsonObj.toString());
		} else if(key.equals("spec")) {
			int itemId=Integer.valueOf(req.getParameter("itemId"));
			Item item=getItem(itemId);
			res.setContentType("text/html;charset=UTF-8");
			JSONObject jsonObj=new JSONObject();
			if(item==null) {
				jsonObj.put("info", "fail");
			} else {
				jsonObj.put("info", "success");
				jsonObj.put("id", item.getId());
				jsonObj.put("name", item.getName());
				jsonObj.put("price", item.getPrice());
				jsonObj.put("description",item.getDescription());
				jsonObj.put("photoCount", item.getPhotoCount());
				jsonObj.put("ownerId", item.getOwnerId());
				jsonObj.put("uploadTime", item.getUploadTime());
				jsonObj.put("collectCount",item.getCollectCount());
				jsonObj.put("classfication1", item.getClassfication1());
				jsonObj.put("classfication2", item.getClassfication2());
				jsonObj.put("photoPath", getItemPhotoPath(item.getId()));
				jsonObj.put("status", item.getStatus());
				jsonObj.put("buyerId", item.getBuyerId());
			}
			res.getWriter().print(jsonObj.toString());
		} else if(key.equals("update")) {
			
			int itemId=Integer.valueOf(req.getParameter("itemId"));
			Item item=getItem(itemId);
			DiskFileItemFactory factory=new DiskFileItemFactory();
			factory.setRepository(new File("G:\\JavaWeb\\SecondHandMarket\\temp"));
			ServletFileUpload upload=new ServletFileUpload(factory);
			
			try {
				List<FileItem> itemList=upload.parseRequest(req);

				for(FileItem fileItem:itemList) {
					if(fileItem.isFormField()) {
						//处理文字内容
						String fieldName=fileItem.getFieldName();
						if(fieldName.equals("name"))
							item.setName(fileItem.getString());
						else if(fieldName.equals("classfication1"))
							item.setClassfication1(fileItem.getString());
						else if(fieldName.equals("classfication2"))
							item.setClassfication2(fileItem.getString());
						else if(fieldName.equals("price"))
							item.setPrice(Double.valueOf(fileItem.getString()));
						else if(fieldName.equals("description"))
							item.setDescription(fileItem.getString());
					}
				}
				
				//插入物品
				int result=updateItem(item);
				
				res.setContentType("text/html;charset=UTF-8");
				JSONObject jsonObj=new JSONObject();
				if(result==1)
					jsonObj.put("info", "success");
				else
					jsonObj.put("info", "fail");
				res.getWriter().println(jsonObj.toString());
			} catch(Exception e) {
				e.printStackTrace();
			}
			
		} else if(key.equals("delete")) {
			int itemId=Integer.valueOf(req.getParameter("itemId"));
			Item item=getItem(itemId);
			JSONObject jsonObj=new JSONObject();
			int result=deleteItem(item);
			if(result==1) {
				jsonObj.put("info", "success");
				//删除图片
				File fileFolder=new File(req.getServletContext().getRealPath("/photos/items/"+itemId));
				File [] fileList=fileFolder.listFiles();
				for(int i=0;i<fileList.length;i++) {
					File file=fileList[i];
					if(file.isFile())
						file.delete();
				}
				fileFolder.delete();
			}
			else
				jsonObj.put("info", "fail");
			res.setContentType("text/html;charset=UTF-8");
			res.getWriter().print(jsonObj.toString());
		} else if(key.equals("buy")) {
			int itemId=Integer.valueOf(req.getParameter("itemId"));
			int userId=Integer.valueOf(req.getParameter("userId"));
			System.out.println(userId);
			int result=buyItem(itemId,userId);
			JSONObject jsonObj=new JSONObject();
			if(result==1)
				jsonObj.put("info", "success");
			else
				jsonObj.put("info", "fail");
			res.setContentType("text/html;charset=UTF-8");
			res.getWriter().print(jsonObj.toString());
		} else if(key.equals("deal")) {
			int itemId=Integer.valueOf(req.getParameter("itemId"));
			int status=Integer.valueOf(req.getParameter("status"));
			Item item=getItem(itemId);
			JSONObject jsonObj=new JSONObject();
			int result=changeDealStatus(status,item);
			if(result==1) {
				jsonObj.put("info", "success");
				//删除图片
				File fileFolder=new File(req.getServletContext().getRealPath("/photos/items/"+itemId));
				File [] fileList=fileFolder.listFiles();
				for(int i=0;i<fileList.length;i++) {
					File file=fileList[i];
					if(file.isFile())
						file.delete();
				}
				fileFolder.delete();
			}else {
				jsonObj.put("info", "fail");
			}
			res.setContentType("text/html;charset=UTF-8");
			res.getWriter().print(jsonObj.toString());
		} else if(key.equals("havedOrder")) {
			int userId=Integer.valueOf(req.getParameter("userId"));
			List<Item> list=getUserOrderItemList(userId);
			res.setContentType("text/html;charset=UTF-8");
			JSONObject jsonObj=new JSONObject();
			if(list==null) {
				jsonObj.put("info", "fail");
			} else {
				jsonObj.put("info", "success");
				JSONArray jsonArray=new JSONArray();
				for(int i=0;i<list.size();i++) {
					JSONObject jsonObject=new JSONObject();
					jsonObject.put("id", list.get(i).getId());
					jsonObject.put("name", list.get(i).getName());
					jsonObject.put("price", list.get(i).getPrice());
					jsonObject.put("description", list.get(i).getDescription());
					jsonObject.put("photoCount", list.get(i).getPhotoCount());
					jsonObject.put("ownerId", list.get(i).getOwnerId());
					jsonObject.put("uploadTime", list.get(i).getUploadTime());
					jsonObject.put("collectCount",list.get(i).getCollectCount());
					jsonObject.put("classfication1", list.get(i).getClassfication1());
					jsonObject.put("classfication2", list.get(i).getClassfication2());
					jsonObject.put("photoPath", getItemPhotoPath(list.get(i).getId()));
					jsonObject.put("status", list.get(i).getStatus());
					jsonObject.put("buyerId", list.get(i).getBuyerId());
					jsonArray.add(jsonObject);
				}
				jsonObj.put("result", jsonArray);
			}
			res.getWriter().print(jsonObj.toString());
		} else if(key.equals("cancelOrder")) {
			int userId=Integer.valueOf(req.getParameter("userId"));
			int itemId=Integer.valueOf(req.getParameter("itemId"));
			
			JSONObject jsonObj=new JSONObject();
			int result=cancelOrder(userId,itemId);
			if(result==1&&deleteMessage(userId,itemId)==1) 
				jsonObj.put("info", "success");
			else
				jsonObj.put("info", "fail");
			
			res.setContentType("text/html;charset=UTF-8");
			res.getWriter().print(jsonObj.toString());
		}
	}
	
	/**
	 * 返回最新动态物品
	 * key="newItem"
	 * @return
	 */
	private List<Item> getNewItem(int start,int end) {
		if(start>end)
			return null;
		ItemService itemService=(ItemService)getContext().getBean("itemService");
		List<Item> resultList=new ArrayList<Item>();
		List list=itemService.SortByTime();
		if(end>list.size())
			end=list.size();
		for(int i=start;i<end;i++) {
			resultList.add((Item)list.get(i));
		}
		return resultList;
	}
	
	/**
	 * 返回热门单品物品
	 * key="popItem"
	 * @return
	 */ 
	private List<Item> getPopItem(int start,int end) {
		if(start>end)
			return null;
		ItemService itemService=(ItemService)getContext().getBean("itemService");
		List<Item> resultList=new ArrayList<Item>();
		List list=itemService.SortByCollectCount();
		if(end>list.size())
			end=list.size();
		for(int i=start;i<end;i++) {
			resultList.add((Item)list.get(i));
		}
		return resultList;
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
			return "";
		for(int i=0;i<list.size();i++) {
			sb.append(((ItemPhoto)list.get(i)).getPhotoPath());
			sb.append(",");
		}
	
		return sb.substring(0, sb.length()-1);
	}
	
	/**
	 * 返回某个用户收藏的列表
	 * @param userId
	 * @return
	 */
	private List<Item> getUserCollecItem(int userId) {
		CollectshipService collectshipService=(CollectshipService)getContext().getBean("collectshipService");
		return collectshipService.getUserCollectList(userId);
	}
	
	/**
	 * 返回某个用户已发布列表
	 * @param userId
	 * @return
	 */
	private List<Item> getUserPublishItem(int userId) {
		ItemService itemService=(ItemService)getContext().getBean("itemService");
		return itemService.findByOwnerId(userId);
	}
	
	//根据itemId获取物品
	private Item getItem(int itemId) {
		ItemService itemService=(ItemService)getContext().getBean("itemService");
		return itemService.find(itemId);
	}
	
	//更新item
	private int updateItem(Item item) {
		ItemService itemService=(ItemService)getContext().getBean("itemService");
		return itemService.update(item);
	}
	
	//删除已发布的物品
	private int deleteItem(Item item) {
		ItemService itemService=(ItemService)getContext().getBean("itemService");
		ItemPhotoService itemPhotoService=(ItemPhotoService)getContext().getBean("itemPhotoService");
		CollectshipService collectshipService=(CollectshipService)getContext().getBean("collectshipService");
		List<ItemPhoto> itemPhotoList=itemPhotoService.findByItemId(item.getId());
		for(ItemPhoto ip:itemPhotoList) {
			itemPhotoService.delete(ip);
		}
		List<Collectship> collectshipList=collectshipService.findByItemId(item.getId());
		for(Collectship collectship:collectshipList) {
			collectshipService.delete(collectship);
		}
		//删除该物品的所有留言
		MessageService messageService=(MessageService)getContext().getBean("messageService");
		for(Message message:messageService.findByItemId(item.getId())) {
			messageService.delete(message);
		}
		return itemService.delete(item);
	}
	
	/**
	 * 购买物品,将物品状态改成1-已预订
	 */
	private int buyItem(int itemId,int userId) {
		ItemService itemService=(ItemService)getContext().getBean("itemService");
		Item item=itemService.find(itemId);
		if(item==null||item.getStatus()==1)
			return -1;
		item.setStatus(1);
		item.setBuyerId(userId);
		item.setOrderTime(new Date(System.currentTimeMillis()));
		return itemService.update(item);  
	}
	
	/**
	 * 卖家 
	 * 更改物品的交易状态,删除物品
	 * @param status 更改的交易状态 0-交易失败 1-交易成功
	 */
	private int changeDealStatus(int status,Item item) {
		ItemService itemService=(ItemService)getContext().getBean("itemService");
		if(status==0) {
			item.setBuyerId(0);
			item.setStatus(0);
			itemService.update(item);
			return 2;
		}else if(status==1) {
			deleteItem(item);
			return 1;
		}else
			return 0;
	}
	
	/**
	 * 根据物品分类查找物品
	 */
	private List<Item> findItem(String classfication1,String classfication2,int start,int end) {
		if(start>end)
			return null;
		ItemService itemService=(ItemService)getContext().getBean("itemService");
		List<Item> resultList=new ArrayList<Item>();
		List list=itemService.findByClassfication(classfication1, classfication2);
		if(end>list.size())
			end=list.size();
		for(int i=start;i<end;i++) {
			resultList.add((Item)list.get(i));
		}
		return resultList;
	}
	
	/**
	 * 获取某个用户预定的物品列表
	 */
	private List<Item> getUserOrderItemList(int userId) {
		ItemService itemService=(ItemService)getContext().getBean("itemService");
		return itemService.findByBuyerId(userId);
	}
	
	/**
	 * 
	 * 取消对物品的预定
	 */
	private int cancelOrder(int userId,int itemId) {
		ItemService itemService=(ItemService)getContext().getBean("itemService");
		Item item=itemService.find(itemId);
		if(item==null||item.getStatus()==0)
			return -1;
		item.setStatus(0);
		item.setBuyerId(0);
		item.setOrderTime(null);
		return itemService.update(item);  
	}
	
	/**
	 * 取消预定后删除用户和物主的留言
	 */
	private int deleteMessage(int userId,int itemId) {
		MessageService messageService=(MessageService)getContext().getBean("messageService");
		for(Message message:messageService.find(userId, itemId)) {
			messageService.delete(message);
		}
		return 1;
	}
}
