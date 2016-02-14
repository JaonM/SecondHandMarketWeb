package com.secondhandmarket.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.secondhandmarket.model.Item;
import com.secondhandmarket.model.ItemPhoto;
import com.secondhandmarket.service.ItemPhotoService;
import com.secondhandmarket.service.ItemService;

public class SearchServlet extends BaseServlet {

	@Override
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException{
		
	}
	
	@Override
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(req.getInputStream(),"UTF-8"));
		String key=br.readLine();
		System.out.println(key);
		List<Item> list=searchItem(key);
		System.out.println(list);
		JSONObject jsonObj=new JSONObject();
		if(list!=null&&list.size()>0) {
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
			jsonObj.put("results", jsonArray);
		} else
			jsonObj.put("info", "fail");
		res.setContentType("text/html;charset=UTF-8");
		res.getWriter().print(jsonObj.toString());
	}
	
	/**
	 * 根据关键字搜索物品
	 */
	private List<Item> searchItem(String key) {
		ItemService itemService=(ItemService)getContext().getBean("itemService");
		return itemService.find(key);
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
}
