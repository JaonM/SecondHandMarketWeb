package com.secondhandmarket.service.impl;

import java.util.List;

import com.secondhandmarket.dao.MessageDao;
import com.secondhandmarket.model.Message;
import com.secondhandmarket.service.MessageService;

public class MessageServiceManage implements MessageService {

	//“¿¿µ◊¢»Î
	private MessageDao messageDao;
	
	public void setMessageDao(MessageDao messageDao) {
		this.messageDao=messageDao;
	}
	
	public MessageDao getMessageDao() {
		return messageDao;
	}
	
	@Override
	public int insert(Message message) {
		// TODO Auto-generated method stub
		return messageDao.insert(message);
	}

	@Override
	public int update(Message message) {
		// TODO Auto-generated method stub
		return messageDao.update(message);
	}

	@Override
	public int delete(Message message) {
		// TODO Auto-generated method stub
		return messageDao.delete(message);
	}

	@Override
	public List<Message> find(int userId, int itemId) {
		// TODO Auto-generated method stub
		return messageDao.find(userId, itemId);
	}

	@Override
	public List<Message> findByItemId(int itemId) {
		// TODO Auto-generated method stub
		return messageDao.findByItemId(itemId);
	}

	@Override
	public List<Message> findByUserId(int userId) {
		// TODO Auto-generated method stub
		return messageDao.findByUserId(userId);
	}

}
