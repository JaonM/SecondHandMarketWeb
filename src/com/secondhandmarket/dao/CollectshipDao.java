package com.secondhandmarket.dao;

import java.util.List;

import com.secondhandmarket.model.Collectship;

public interface CollectshipDao {

	/**
	 * 插入一条记录
	 * @param collectship
	 * @return
	 */
	public int insert(Collectship collectship);
	
	/**
	 * 根据主键查找一条记录
	 * @param userId
	 * @param itemId
	 * @return
	 */
	public Collectship find(int userId,int itemId);
	
	/**
	 * 根据userId查找某个用户的收藏关系
	 * @param userId
	 * @return
	 */
	public List find(int userId);
	
	/**
	 * 删除一条记录 
	 * @param collectship
	 * @return
	 */
	public int delete(Collectship collectship);
	
	/**
	 * 根据itemId查找相应物品的收藏关系
	 * @param itemId
	 * @return
	 */
	public List findByItemId(int itemId);
	
	/**
	 * 检查物品是否被收藏
	 */
	public boolean isCollected(int userId,int itemId);
}
