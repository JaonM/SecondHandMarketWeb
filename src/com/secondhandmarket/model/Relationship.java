package com.secondhandmarket.model;

import java.io.Serializable;

/**
 * POJO映射 用户之间的关注关系
 * @author maqiang
 *
 */
public class Relationship {


	private RelationshipId relationshipId;				//关系主键
	private byte relation;				// 1- user1 关注 user2
	
	public Relationship() {}
	
	public Relationship(RelationshipId relationshipId,byte i) {
		this.relationshipId=relationshipId;
		this.relation=i;
	}
	
	//各属性getter和setter
	public void setRelationshipId(RelationshipId relationshipId) {
		this.relationshipId=relationshipId;
	}
	
	public RelationshipId getRelationshipId() {
		return relationshipId;
	}
	
	public void setRelation(byte relation) {
		this.relation=relation;
	}
	
	public byte getRelation() {
		return relation;
	}
	
	@Override
	public String toString() {
		return relationshipId.toString()+" "+relation;
	}
}
