package com.secondhandmarket.service;

import java.util.List;

import com.secondhandmarket.model.Message;

public interface MessageService {

	public int insert(Message message);
	
	public int update(Message message);
	
	public int delete(Message message);
	
	public List<Message> find(int userId,int itemId);
	
	public List<Message> findByItemId(int itemId);
	
	/**
	 * ����userId�õ�����
	 */
	public List<Message> findByUserId(int userId);
}
