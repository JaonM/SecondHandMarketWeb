package com.secondhandmarket.servlet;

import java.io.File;
import java.io.FileOutputStream;
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
import com.secondhandmarket.service.ItemPhotoService;
import com.secondhandmarket.service.ItemService;

public class PublishServlet extends BaseServlet {

	@Override
	public void doGet(HttpServletRequest req,HttpServletResponse res) {
		
	}
	
	@Override
	public void doPost(HttpServletRequest req,HttpServletResponse res) {
		Item item=new Item();
		List<ItemPhoto> itemPhotoList=new ArrayList<ItemPhoto>();
		
		DiskFileItemFactory factory=new DiskFileItemFactory();
//		factory.setRepository(new File("F:\\JavaWeb\\SecondHandMarket\\temp"));
		ServletFileUpload upload=new ServletFileUpload(factory);
		System.out.println(req.getServletContext().getRealPath("photos/items/"));
		JSONObject result=new JSONObject();
		
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
					else if(fieldName.equals("photoCount"))
						item.setPhotoCount(Integer.valueOf(fileItem.getString()));
					else if(fieldName.equals("ownerId"))
						item.setOwnerId(Integer.valueOf(fileItem.getString()));
				}
			}
			//插入物品
			int code=insertItem(item);
			System.out.println(code);
			if(code==1&&item.getPhotoCount()!=0) {
				//插入成功,开始处理图片
				for(FileItem fileItem0:itemList) {
					if(!fileItem0.isFormField()) {
						String fileName=fileItem0.getFieldName();
				
						int itemId=findByName(item.getName()).getId();
		
						ItemPhoto itemPhoto=new ItemPhoto();
						itemPhoto.setItemId(itemId);
						itemPhoto.setPhotoPath("photos/items/"+itemId+"/"+fileName+".jpg");
						
						System.out.println(insertItemPhoto(itemPhoto));
						
						//正式部署时路径要注意是否会变!!!
						File fileFolder=new File(req.getServletContext().getRealPath("/photos/items/"+itemId));
						fileFolder.mkdirs();
						File file=new File(fileFolder.getCanonicalPath()+"/"+fileName+".jpg");
						
						FileOutputStream fos=new FileOutputStream(file);
						fos.write(fileItem0.get());
						fos.flush();
						fos.close();
					}
				}
				
				result.put("info", "success");
			} else {
				result.put("info", "fail");
			}
			
			res.setContentType("text/html;charset=UTF-8");
			res.getWriter().print(result.toString());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//插入一个物品
	private int insertItem(Item item) {
		ItemService itemService=(ItemService)getContext().getBean("itemService");
		return itemService.insert(item);
	}
	
	//插入物品图片
	private int insertItemPhoto(ItemPhoto itemPhoto) {
		ItemPhotoService itemPhotoService=(ItemPhotoService)getContext().getBean("itemPhotoService");
		return itemPhotoService.insert(itemPhoto);
	}
	
	//通过物品名称查询物品
	private Item findByName(String itemName) {
		ItemService itemService=(ItemService)getContext().getBean("itemService");
		return itemService.findByName(itemName);
	}
}
