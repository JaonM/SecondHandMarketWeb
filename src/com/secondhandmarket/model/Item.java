package com.secondhandmarket.model;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * POJOӳ�� 
 * ��Ʒ
 * @author maqiang
 *
 */
public class Item {

	private int id;											//��Ʒid primary key
	private String name;									//��Ʒ��
	private double price;									//��Ʒ�۸�
	private String description;						//��Ʒ����
	private int photoCount;								//ͼƬ����
	private int ownerId;									//����id
	private Timestamp uploadTime;					//��Ʒ�ϴ�ʱ��
	private int collectCount;							//��Ʒ���ղش���
	private String classfication1;					//��Ʒһ������
	private String classfication2;					//��Ʒ��������
	private int status;										//��Ʒ����״̬ 0-δ�۳� 1-��Ԥ�� 2-���۳�
	private int buyerId;									//�����ߵ�id
	private int isSend;										//����Ʒ����Ϣ�Ƿ��͹� 0-δ���� 1-�ѷ���
	private Date orderTime;							//��Ʒ��Ԥ��ʱ��
	 
	public Item() {}
	
	public Item(int id,String name, double price,String description,int photoCount,int ownerId,Timestamp uploadTime,int collectCount,
			String classfication1,String classfication2,int status,int buyerId,int isSend,Date orderTime) {
		this.id=id;
		this.name=name;
		this.price=price;
		this.description=description;
		this.photoCount=photoCount;
		this.ownerId=ownerId;
		this.uploadTime=uploadTime;
		this.collectCount=collectCount;
		this.classfication1=classfication1;
		this.classfication2=classfication2;
		this.status=status;
		this.buyerId=buyerId;
		this.isSend=isSend;
		this.orderTime=orderTime;
	}
	
	//������getter��setter
	public void setId(int id) {
		this.id=id;
	}
	
	public int getId() {
		return id; 
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setPrice(double price) {
		this.price=price;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setDescription(String description) {
		this.description=description;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setPhotoCount(int photoCount) { 
		this.photoCount=photoCount;
	}
	
	public int getPhotoCount() { 
		return photoCount;
	}
	
	public void setOwnerId(int ownerId) {
		this.ownerId=ownerId;
	}
	
	public int getOwnerId() {
		return ownerId;
	}
	
	public void setUploadTime(Timestamp uploadTime) {
		this.uploadTime=uploadTime;
	}
	
	public Timestamp getUploadTime() {
		
		return uploadTime;
	}
	
	public void setCollectCount(int collectCount) {
		this.collectCount=collectCount;
	}
	
	public int getCollectCount() {
		return collectCount;
	}
	
	public void setClassfication1(String classfication1) {
		this.classfication1=classfication1;
	}
	
	public String getClassfication1() {
		return classfication1;
	}
	
	public void setClassfication2(String classfication2) {
		this.classfication2=classfication2;
	}
	
	public String getClassfication2() {
		return classfication2;
	}
	
	public void setStatus(int status) {
		this.status=status;
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setBuyerId(int buyerId) {
		this.buyerId=buyerId;
	}
	
	public int getBuyerId() {
		return buyerId;
	}
	
	public void setIsSend(int isSend) {
		this.isSend=isSend;
	}
	
	public int getIsSend() {
		return isSend;
	}
	
	public void setOrderTime(Date orderTime) {
		this.orderTime=orderTime;
	}
	
	public Date getOrderTime() {
		return orderTime;
	}
	
	public String toString() {
		return id+" "+name+" "+price+" "+description+" "+photoCount+" "+ownerId+" "+uploadTime+" "+collectCount;
	}
}
