package com.secondhandmarket.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.secondhandmarket.model.Advertisement;
import com.secondhandmarket.service.AdvertisementService;

public class AdvertisementServlet extends BaseServlet {

	@Override
	public void doGet(HttpServletRequest req,HttpServletResponse res) {
		
	}
	
	@Override
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException {
		res.setContentType("text/html;charset=UTF-8");
		JSONObject jsonObj=new JSONObject();
		List<Advertisement> list=getAd();
		if(list!=null) {
			jsonObj.put("info", "success");
			JSONArray jsonArray=new JSONArray();
			for(Advertisement ad: list) {
				JSONObject jsonObject=new JSONObject();
				jsonObject.put("url",ad.getUrl());
				jsonObject.put("photoPath", ad.getPhotoPath());
				jsonObject.put("id",ad.getId());
				jsonArray.add(jsonObject);
			}
			jsonObj.put("results", jsonArray);
		}else
			jsonObj.put("info", "fail");
		res.getWriter().print(jsonObj.toString());
	}
	
	private List<Advertisement> getAd() {
		AdvertisementService advertisementService=(AdvertisementService)getContext().getBean("advertisementService");
		return advertisementService.find();
	}
}
