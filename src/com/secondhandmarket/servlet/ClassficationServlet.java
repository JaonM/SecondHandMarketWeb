package com.secondhandmarket.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.secondhandmarket.model.Classfication;
import com.secondhandmarket.service.ClassficationService;

public class ClassficationServlet extends BaseServlet {

	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException {
		String key=req.getParameter("key");
		if(key.equals("classfication")) {
			int classfication1Id=Integer.valueOf(req.getParameter("classfication1Id"));
			List<Classfication> list=getClassficationList(classfication1Id);
			JSONObject jsonObject=new JSONObject();
			if(list!=null) {
				jsonObject.put("info", "success");
				JSONArray jsonArray=new JSONArray ();
				for(Classfication classfication:list) {
					JSONObject jsonObj=new JSONObject();
					jsonObj.put("classfication2Name", classfication.getClassfication2Name());
					jsonObj.put("classfication1Id", classfication.getClassfication1Id());
					jsonArray.add(jsonObj);
				}
				jsonObject.put("results", jsonArray);
			} else
				jsonObject.put("info","fail");
			res.setContentType("text/html;charset=UTF-8");
			res.getWriter().print(jsonObject.toString());
		} 
	}
	
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException {
		
	}
	
	/**
	 * 根据classfication1Id获取二级分类列表
	 */
	private List<Classfication> getClassficationList(int classfication1Id) {
		ClassficationService classficationService=(ClassficationService)getContext().getBean("classficationService");
		return classficationService.findByClassfication1Id(classfication1Id);
	}
	
	
}
