package com.secondhandmarket.dao;

import java.util.List;

import com.secondhandmarket.model.Message;

/**
 * Message Dao
 * @author maqiang
 *
 */
public interface MessageDao {

	public int insert(Message message);
	
	public int update(Message message);
	
	public int delete(Message message);
	
	public List<Message> find(int userId,int itemId);
	
	public List findByItemId(int itemId);
	
	/**
	 * 根据userId得到留言
	 */
	public List<Message> findByUserId(int userId);
}
