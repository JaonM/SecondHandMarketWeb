package com.secondhandmarket.servlet;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.secondhandmarket.model.User;
import com.secondhandmarket.service.UserService;

/**
 * �����û���Ϣ ��Servlet
 * @author maqiang
 *
 */
public class UpdateUserServlet extends BaseServlet {

	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException {

	}
	
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException {
		String key=req.getParameter("key");
		String value=URLDecoder.decode(req.getParameter("value"), "UTF-8");
		int userId=Integer.valueOf(req.getParameter("userId"));
		JSONObject jsonObj=new JSONObject();
		if(key.equals("image")) {
			try {
				//��ȡԭ�����ļ�,ˢ������
				String filePath=req.getServletContext().getRealPath("/photos/users")+"/"+userId+".jpg";
				FileOutputStream fos=new FileOutputStream(filePath);
				InputStream is=req.getInputStream();
				byte [] buffer=new byte[1024];
				int hasRead=0;
				while((hasRead=is.read(buffer))!=-1) 
					fos.write(buffer,0,hasRead);
				fos.close();
				updateUserPhotoPath(findUser(userId),"photos/users/"+userId+".jpg");
				jsonObj.put("info", "success");
			} catch(Exception e) {
				e.printStackTrace();
				jsonObj.put("info", "fail");
			}
		} else if(key.equals("nickName")) {
			InputStream is=req.getInputStream();
			BufferedReader br=new BufferedReader(new InputStreamReader(is,"UTF-8"));
			String nickName=br.readLine();
			br.close();
			is.close();
			updateUserNickName(findUser(userId),nickName);
			jsonObj.put("info","success");
		} else if(key.equals("selfIntroduction")) {
			InputStream is=req.getInputStream();
			BufferedReader br=new BufferedReader(new InputStreamReader(is,"UTF-8"));
			String selfIntroduction=br.readLine();
			br.close();
			is.close();
			updateUserSelfIntroduction(findUser(userId),selfIntroduction);
			jsonObj.put("info", "success");
		} else if(key.equals("phone")) {
			InputStream is=req.getInputStream();
			BufferedReader br=new BufferedReader(new InputStreamReader(is,"UTF-8"));
			String phone=br.readLine();
			br.close();
			is.close();
			updateUserPhone(findUser(userId),phone);
			jsonObj.put("info", "success");
		} else if(key.equals("name")) {
			InputStream is=req.getInputStream();
			BufferedReader br=new BufferedReader(new InputStreamReader(is,"UTF-8"));
			String name=br.readLine();
			br.close();
			is.close();
			updateUserName(findUser(userId),name);
			jsonObj.put("info","success");
		}
		res.setContentType("text/html;charset=UTF-8");
		res.getWriter().print(jsonObj.toString());
	}
	
	//ͨ��id��user
	public User findUser(int userId) {
		UserService userService=(UserService)getContext().getBean("userService");
		return userService.find(userId);
	}
	
	//�����û�ͷ��·��
	public void updateUserPhotoPath(User user,String photoPath) {
		UserService userService=(UserService)getContext().getBean("userService");
		userService.updateUserPhoto(user, photoPath);
	}
	
	//�����û��ǳ�
	public void updateUserNickName(User user,String nickName) {
		UserService userService=(UserService)getContext().getBean("userService");
		userService.updateNickName(user, nickName);
	}
	
	//�����û�����ǩ��
	public void updateUserSelfIntroduction(User user,String selfIntroduction) {
		UserService userService=(UserService)getContext().getBean("userService");
		userService.updateSignature(user, selfIntroduction);
	}
	
	//�����û��ֻ�
	public void updateUserPhone(User user,String phone) {
		UserService userService=(UserService)getContext().getBean("userService");
		userService.updatePhone(user, phone);
	}
	
	//�����û���ʵ����
	private void updateUserName(User user,String name) {
		UserService userService=(UserService)getContext().getBean("userService");
		userService.updateName(user, name);
	}
}
