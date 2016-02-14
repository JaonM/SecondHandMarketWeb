package com.secondhandmarket.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.secondhandmarket.model.User;
import com.secondhandmarket.service.ItemService;
import com.secondhandmarket.service.RelationshipService;
import com.secondhandmarket.service.UserService;

/**
 * �����½���ܵ�servlet,�ɹ�����user��Ϣ ��û�д���ͼƬ
 * @author maqiang
 *
 */
public class LoginServlet extends BaseServlet {

	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException {
		//doPost(req,res);
	}
	
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException {
		String account=new String(req.getParameter("account").getBytes("ISO-8859-1"),"UTF-8");
		String password=req.getParameter("password");
		
		User user=validateLogin(account,password);
				 
			JSONObject jsonObject=new JSONObject();
			
			if(user!=null) {
				res.setContentType("text/html;charset=UTF-8");
				try {
					jsonObject.put("info", "success");
					jsonObject.put("userid", user.getId());
					jsonObject.put("nickName", user.getNickName());
					jsonObject.put("name",user.getName());
					jsonObject.put("phone",user.getPhone());
					jsonObject.put("sex",user.getSex());
					jsonObject.put("selfIntroduction", user.getSelfIntroduction());
					jsonObject.put("sno", user.getsNo());
					jsonObject.put("photopath", user.getUserPhoto());
					jsonObject.put("attentionCount", getUserAttentionCount(user.getId()));
					jsonObject.put("beAttentionedCount", getUserBeAttentionedCount(user.getId()));
					jsonObject.put("publishCount", getPublishCount(user.getId()));
				} catch(Exception e) {
					e.printStackTrace();
				}
			} else {
				jsonObject.put("info", "fail");
			}
			
			res.getWriter().println(jsonObject.toString());
		} 
			
		/**
		 * ����ͼƬ,��Ҫѹ����,�ȱ������
		 
			if(!new File(user.getUserPhoto()).exists()) {
				System.out.println("δ�ҵ��û�ͷ��");
				return ;
			}
			res.setContentType("image/jpeg");
			FileInputStream fis=new FileInputStream(user.getUserPhoto());
			ServletOutputStream out=res.getOutputStream();
			int length=0;
			byte [] buffer=new byte[1024];
			while((length=fis.read(buffer))!=-1) {
				res.getOutputStream().write(buffer,0,length);
			}
			fis.close(); 
			out.flush();
			out.close();
		}
		*/

	/**
	 * ��֤��½
	 * @param account
	 * @param password
	 * @return ����User null for��½ʧ��
	 */
	public User validateLogin(String account,String password) {
		UserService userService=(UserService)getContext().getBean("userService");
		User user=userService.find(account,password);
		return user;
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
	
	private int getPublishCount(int userId) {
		ItemService itemService=(ItemService)getContext().getBean("itemService");
		return itemService.getPublishCount(userId);
	}
	
}
