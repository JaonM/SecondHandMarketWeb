package com.secondhandmarket.model;

import java.io.Serializable;

/**
 * POJOӳ�� �û�֮��Ĺ�ע��ϵ
 * @author maqiang
 *
 */
public class Relationship {


	private RelationshipId relationshipId;				//��ϵ����
	private byte relation;				// 1- user1 ��ע user2
	
	public Relationship() {}
	
	public Relationship(RelationshipId relationshipId,byte i) {
		this.relationshipId=relationshipId;
		this.relation=i;
	}
	
	//������getter��setter
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
