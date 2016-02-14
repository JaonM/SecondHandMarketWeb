package com.secondhandmarket.model;

import java.io.Serializable;

/**
 * Relationship主键映射
 * @author maqiang
 *
 */
public class RelationshipId implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3770155961346853367L;
	
	private int user1Id;					//用户1Id
	private int user2Id;					//用户2Id
	
	public RelationshipId() {}
	
	public RelationshipId(int user1Id,int user2Id) {
		this.user1Id=user1Id;
		this.user2Id=user2Id;
	}
	
	//各属性getter和setter
	public void setUser1Id(int user1Id) {
		this.user1Id=user1Id;
	}
	
	public int getUser1Id() {
		return user1Id;
	}
	
	public void setUser2Id(int user2Id) {
		this.user2Id=user2Id;
	}
	
	public int getUser2Id() {
		return user2Id;
	}
	
	@Override
    public int hashCode() {
		
        final int prime = 31;
        int result = 1;
        result = prime * result + ((user1Id==0) ? 0 : user1Id);
        result = prime * result + ((user2Id==0) ? 0 : user2Id);
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
		RelationshipId other=(RelationshipId)obj;
		if(user1Id==0) {
			if(other.user1Id!=0)
				return false;
		} else if(user1Id!=other.user1Id)
			return false;
			
		if(user2Id==0) {
			if(other.user2Id!=0)
				return false;
		} else if(user2Id!=other.getUser2Id())
			return false;
		
		return true;
	}
	
	@Override
	public String toString() {
		return user1Id+" "+user2Id;
	}
}
