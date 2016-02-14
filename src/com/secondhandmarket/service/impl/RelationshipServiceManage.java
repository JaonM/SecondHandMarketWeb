package com.secondhandmarket.service.impl;

import java.util.List;

import com.secondhandmarket.dao.RelationshipDao;
import com.secondhandmarket.dao.UserDao;
import com.secondhandmarket.model.Relationship;
import com.secondhandmarket.model.RelationshipId;
import com.secondhandmarket.model.User;
import com.secondhandmarket.service.RelationshipService;

public class RelationshipServiceManage implements RelationshipService {

	private UserDao userDao;						//userDao依赖注入
	private RelationshipDao relationshipDao;			//relationshipDao 依赖注入
	
	public void setUserDao(UserDao userDao) {
		this.userDao=userDao;
	}
	
	public void setRelationshipDao(RelationshipDao relationshipDao) {
		this.relationshipDao=relationshipDao;
	}
	
	public RelationshipDao getRelationshipDao() {
		return relationshipDao;
	}
	
	public UserDao getUserDao() {
		return userDao;
	}
	
	@Override
	public int insert(Relationship relationship) {
		// TODO Auto-generated method stub
		if(userDao==null) {
			System.err.println("UserDap 为空");
			return -1;
		}
		User user1=userDao.find(relationship.getRelationshipId().getUser1Id());
		User user2=userDao.find(relationship.getRelationshipId().getUser2Id());
		if(user1==null||user2==null||user1.getId()==user2.getId()) {
			System.err.println("用户不存在.无法进行关注");
			return -1;
		}
		try {
			relationshipDao.insert(relationship);
			return 1;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public Relationship findRelationship(RelationshipId relationshipId) {
		// TODO Auto-generated method stub
		return relationshipDao.findRelationship(relationshipId);
	}

	@Override
	public int updateRelationship(Relationship relationship) {
		// TODO Auto-generated method stub
		Relationship relationship0=relationshipDao.findRelationship(relationship.getRelationshipId());
		if(relationship0==null) {
			System.err.println("更新出错");
			return -1 ;
		}
		relationshipDao.updateRelationship(relationship0);
		return 1;
	}

	@Override
	public int deleteRelationship(Relationship relationship) {
		// TODO Auto-generated method stub
		return relationshipDao.deleteRelationship(relationship);
	}

	@Override
	public int getUserAttentionCount(int user1Id) {
		// TODO Auto-generated method stub
		return relationshipDao.getUserAttentionCount(user1Id);
	}

	@Override
	public int getUserBeAttentionedCount(int user1Id) {
		// TODO Auto-generated method stub
		return relationshipDao.getUserBeAttentionedCount(user1Id);
	}

	@Override
	public List<Integer> getUserAttentionIdList(int user1Id) {
		// TODO Auto-generated method stub
		return relationshipDao.getUserAttentionIdList(user1Id);
	}

	@Override
	public List<Integer> getUserBeAttentionedIdList(int user1Id) {
		// TODO Auto-generated method stub
		return relationshipDao.getUserBeAttentionedIdList(user1Id);
	}


}
