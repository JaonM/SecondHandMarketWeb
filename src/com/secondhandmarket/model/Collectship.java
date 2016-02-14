package com.secondhandmarket.model;

import java.io.Serializable;

public class Collectship implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3364695703214729064L;

	private int userId;				//用户id
	private int itemId;				//物品id
	 
	public Collectship() {}
	
	public Collectship(int userId,int itemId) {
		this.userId=userId;
		this.itemId=itemId;
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
		Collectship other=(Collectship)obj;
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
	
	@Override
	public String toString() {
		return userId+" "+itemId;
	}
}
