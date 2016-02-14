package com.secondhandmarket.service;

import java.util.List;

import com.secondhandmarket.dao.UserDao;
import com.secondhandmarket.model.Relationship;
import com.secondhandmarket.model.RelationshipId;

/**
 * ҵ���߼���   ��Dao����з�װ
 * @author maqiang
 *
 */
public interface RelationshipService {

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
	 * ����ע��UserDao
	 * @param userDao
	 */
	public void setUserDao(UserDao userDao);
	
	/**
	 * ��������User֮��Ĺ�ϵ
	 * @param relationshipId
	 * @return
	 */
	public int updateRelationship(Relationship relationship);
	

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
