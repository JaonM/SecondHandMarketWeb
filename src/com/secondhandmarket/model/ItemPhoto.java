package com.secondhandmarket.model;

import java.sql.Blob;

/**
 * POJO映射 
 * 物品图片
 * @author maqiang
 *
 */
public class ItemPhoto {

	private int id;										//图片id
	private String photoPath;						//图片资源二进制形式存储
	private int itemId;								//所属物品id
	
	public ItemPhoto() {}
	
	public ItemPhoto(int id,String photoPath,int itemId) {
		this.id=id;
		this.photoPath=photoPath;
		this.itemId=itemId;
	}
	
	//各属性getter和setter
	public void setId(int id) {
		this.id=id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setPhotoPath(String photoPath) {
		this.photoPath=photoPath;
	}
	
	public String getPhotoPath() {
		return photoPath;
	}
	
	public void setItemId(int itemId) {
		this.itemId=itemId;
	}
	
	public int getItemId() {
		return itemId;
	}
	
	public String toString() {
		return "id:"+id+" photoPath:"+photoPath;
	}
}
