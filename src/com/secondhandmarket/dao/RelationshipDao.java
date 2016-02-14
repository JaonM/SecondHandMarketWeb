package com.secondhandmarket.dao;

import java.util.List;

import com.secondhandmarket.model.Relationship;
import com.secondhandmarket.model.RelationshipId;

/**
 * �û�֮��Ĺ�ע��ϵ Dao��ӿ�
 * @author maqiang
 *
 */
public interface RelationshipDao {

	/**
	 * ����һ����¼
	 * @param relationshipDao
	 * @return -1��ʾ���벻�ɹ�,1��ʾ����ɹ�
	 */
	public int insert(Relationship relationship);
	
	/**
	 * ��������User֮��Ĺ�ϵ
	 * @param relationshipId ͨ����������
	 * @return
	 */
	public Relationship findRelationship(RelationshipId relationshipId);
	
	/**
	 * ��������User֮��Ĺ�ϵ
	 * @param relationshipId
	 * @return
	 */
	public void updateRelationship(Relationship relationship);
	

	/**
	 * ɾ����ϵ
	 * @param relationshipId
	 */
	public int deleteRelationship(Relationship relationship);
	
	/**
	 * ��ȡuser1 ��ע���˵����� relation=1
	 * @param user1Id
	 * @return
	 */
	public int getUserAttentionCount(int user1Id);
	
	/**
	 * ��ȡ��עuser1���˵����� relation=2
	 * @param user1Id
	 * @return
	 */
	public int getUserBeAttentionedCount(int user1Id);
	
	/**
	 * ��ȡuser1��ע���˵�id�б� relation=1;
	 * @param user1Id
	 * @return
	 */
	public List<Integer> getUserAttentionIdList(int user1Id);
	
	/**
	 * ��ȡ��עuser1���˵��б� relation=2
	 * @param user1Id 
	 * @return
	 */
	public List<Integer> getUserBeAttentionedIdList(int user1Id);
}
