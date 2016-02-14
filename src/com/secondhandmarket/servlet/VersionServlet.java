package com.secondhandmarket.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.secondhandmarket.model.Version;
import com.secondhandmarket.service.VersionService;

public class VersionServlet extends BaseServlet{

	@Override
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException {
		
	}
	
	@Override
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException {
		String key=req.getParameter("key");
		res.setContentType("text/html;charset=UTF-8");
		if(key.equals("newVersion")) {
			JSONObject jsonObj=new JSONObject();
			Version version=getNewVersion();
			if(version!=null) {
				jsonObj.put("info", "success");
				jsonObj.put("id", version.getId());
				jsonObj.put("version", version.getVersion());
				jsonObj.put("description", version.getDescription());
				jsonObj.put("apkPath",version.getApkPath());
			} else
				jsonObj.put("info", "fail");
			res.getWriter().print(jsonObj.toString());
		}
	}
	
	/**
	 * 返回 最新的版本信息
	 */
	private Version getNewVersion() {
		VersionService versionService=(VersionService)getContext().getBean("versionService");
		List<Version> versions=versionService.getList();
		if(versions.size()>0)
			return versions.get(versions.size()-1);
		else 
			return null;
	}
}
