package com.secondhandmarket.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.secondhandmarket.model.Item;
import com.secondhandmarket.model.ItemPhoto;
import com.secondhandmarket.model.User;
import com.secondhandmarket.service.UserService;

public class RegistServlet extends BaseServlet {

	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException {
		System.out.println(req.getServletContext().getRealPath("/photos/users/"));
	}
	
	/**
	 * post ���� ���ע������,����ѧ�ţ��������ѧ����֤(δʵ��)
	 */
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException {
	
		User user=new User();
		
		DiskFileItemFactory factory=new DiskFileItemFactory();
//		factory.setRepository(new File("F:\\JavaWeb\\SecondHandMarket\\temp"));
		ServletFileUpload upload=new ServletFileUpload(factory);
		
		JSONObject jsonObj=new JSONObject();
		
		try {
			List<FileItem> itemList=upload.parseRequest(req);

			for(FileItem fileItem:itemList) {
				if(fileItem.isFormField()) {
					//������������
					String fieldName=fileItem.getFieldName();
					String value=fileItem.getString();
					if(fieldName.equals("userName"))
						user.setAccount(value);
					else if(fieldName.equals("password"))
						user.setPassword(value);
					else if(fieldName.equals("name"))
						user.setName(value);
					else if(fieldName.equals("phone"))
						user.setPhone(value);
					else if(fieldName.equals("nickName"))
						user.setNickName(value);
					else if(fieldName.equals("sex"))
						user.setSex(Byte.valueOf(value));
					else if(fieldName.equals("sno"))
						user.setsNo(value);
				}
			}
			user.setSelfIntroduction("");
			//������Ʒ
			int result=registUser(user);

			if(result==1) {
				//����ɹ�,��ʼ����ͼƬ
				for(FileItem fileItem0:itemList) {
					if(!fileItem0.isFormField()) {

						User user0=getUser(user.getAccount(),user.getPassword());
		
						String folderPath=req.getServletContext().getRealPath("/photos/users");
						
						File file=new File(folderPath);
						file.mkdirs();
						File newFile=new File(folderPath+"\\"+user0.getId()+".jpg");
						newFile.createNewFile();
						FileOutputStream fos=new FileOutputStream(newFile);
						fos.write(fileItem0.get());
						fos.flush();
						fos.close();

						updateImagePath(user0);
						
					}
				}
				
				jsonObj.put("info", "success");
			} else {
				jsonObj.put("info", "fail");
			}
			res.setContentType("text/html;charset=UTF-8");
			res.getWriter().print(String.valueOf(result));
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//ע�����û�
	private int registUser(User user) {
		UserService userService=(UserService)getContext().getBean("userService");
		return userService.insert(user);
	}
	
	//�����û��������뷵�� user 
	private User getUser(String account,String password) {
		UserService userService=(UserService)getContext().getBean("userService");
		User user=userService.find(account, password);
		return user;
	}
	
	//�����û�ͷ��Ŀ¼
	private void updateImagePath(User user) {
		UserService userService=(UserService)getContext().getBean("userService");
		userService.updateUserPhoto(user, "photos/users/"+user.getId()+".jpg");
	}
}
