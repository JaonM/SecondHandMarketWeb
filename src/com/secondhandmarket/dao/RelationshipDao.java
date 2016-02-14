package com.secondhandmarket.dao;

import java.util.List;

import com.secondhandmarket.model.Relationship;
import com.secondhandmarket.model.RelationshipId;

/**
 * 用户之间的关注关系 Dao层接口
 * @author maqiang
 *
 */
public interface RelationshipDao {

	/**
	 * 插入一条记录
	 * @param relationshipDao
	 * @return -1表示插入不成功,1表示插入成功
	 */
	public int insert(Relationship relationship);
	
	/**
	 * 查找两个User之间的关系
	 * @param relationshipId 通过主键查找
	 * @return
	 */
	public Relationship findRelationship(RelationshipId relationshipId);
	
	/**
	 * 更新两个User之间的关系
	 * @param relationshipId
	 * @return
	 */
	public void updateRelationship(Relationship relationship);
	

	/**
	 * 删除关系
	 * @param relationshipId
	 */
	public int deleteRelationship(Relationship relationship);
	
	/**
	 * 获取user1 关注的人的数量 relation=1
	 * @param user1Id
	 * @return
	 */
	public int getUserAttentionCount(int user1Id);
	
	/**
	 * 获取关注user1的人的数量 relation=2
	 * @param user1Id
	 * @return
	 */
	public int getUserBeAttentionedCount(int user1Id);
	
	/**
	 * 获取user1关注的人的id列表 relation=1;
	 * @param user1Id
	 * @return
	 */
	public List<Integer> getUserAttentionIdList(int user1Id);
	
	/**
	 * 获取关注user1的人的列表 relation=2
	 * @param user1Id 
	 * @return
	 */
	public List<Integer> getUserBeAttentionedIdList(int user1Id);
}
