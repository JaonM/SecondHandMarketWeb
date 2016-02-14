package com.secondhandmarket.model;

import java.sql.Blob;

/**
 * POJOӳ�� 
 * ��ƷͼƬ
 * @author maqiang
 *
 */
public class ItemPhoto {

	private int id;										//ͼƬid
	private String photoPath;						//ͼƬ��Դ��������ʽ�洢
	private int itemId;								//������Ʒid
	
	public ItemPhoto() {}
	
	public ItemPhoto(int id,String photoPath,int itemId) {
		this.id=id;
		this.photoPath=photoPath;
		this.itemId=itemId;
	}
	
	//������getter��setter
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
