package com.secondhandmarket.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 发布者可对预订者留言
 * 用户可对物品留言
 * @author maqiang
 *
 */
public class Message implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8866104913489340105L;
	
	private int id;
	private int userId;			
	private int itemId;			
	private String message;
	private int isSend;			//表示消息是否发送过   0-未发送		1-发送过
	private int isReceiver;	//是否为接收方 0-不是接收方		1-接收方
	private Timestamp time;		//留言时间
	
	public Message() {}
	
	public Message(int userId,int itemId,String message,int isSend,int isReceiver) {
		this.userId=userId;
		this.itemId=itemId;
		this.message=message;
		this.isSend=0;
		this.isReceiver=isReceiver;
		time=new Timestamp(System.currentTimeMillis());
	}
	
	public void setId(int id) {
		this.id=id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setUserId(int userId) {
		this.userId=userId;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setItemId(int itemId) {
		this.itemId=itemId;
	}
	
	public int getItemId() {
		return itemId;
	}
	
	public void setMessage(String message) {
		this.message=message;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setIsSend(int isSend) {
		this.isSend=isSend;
	}
	
	public int getIsSend(){
		return isSend;
	}
	
	public void setIsReceiver(int isReceiver) {
		this.isReceiver=isReceiver;
	}
	
	public int getIsReceiver() {
		return isReceiver;
	}
	
	public void setTime(Timestamp time) {
		this.time=time;
	}
	
	public Timestamp getTime() {
		return time;
	}
	
	@Override
    public int hashCode() {
		
        final int prime = 31;
        int result = 1;
        result = prime * result + ((userId==0) ? 0 : userId);
        result = prime * result + ((itemId==0) ? 0 : itemId);
        return result;
    }
	
	@Override
	public boolean equals(Object obj) {
		if(this==obj)
			return true;
		if(obj==null)
			return false;
		if(getClass()!=obj.getClass())
			return false;
		Message other=(Message)obj;
		if(userId==0) {
			if(other.userId!=0)
				return false;
		} else if(userId!=other.userId)
			return false;
			
		if(itemId==0) {
			if(other.itemId!=0)
				return false;
		} else if(itemId!=other.getItemId())
			return false;
		
		return true;
	}
}
