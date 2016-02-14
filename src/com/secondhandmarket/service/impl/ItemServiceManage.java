package com.secondhandmarket.service.impl;

import java.util.List;

import com.secondhandmarket.dao.ItemDao;
import com.secondhandmarket.dao.UserDao;
import com.secondhandmarket.model.Item;
import com.secondhandmarket.model.ItemPhoto;
import com.secondhandmarket.service.ItemService;

/**
 * 业务逻辑层 对Dao层 封装
 * @author maqiang
 *
 */
public class ItemServiceManage implements ItemService{

	private UserDao userDao;								//userDao依赖注入
	private ItemDao itemDao;								//itemDao依赖注入
	
	public void setUserDao(UserDao userDao) {
		this.userDao=userDao;
	}
	
	public void setItemDao(ItemDao itemDao) {
		this.itemDao=itemDao;
	}
	
	public UserDao getUserDao(UserDao userDao) {
		return userDao;
	}
	
	public ItemDao getItemDao(ItemDao itemDao) {
		return itemDao;
	}
	 
	@Override
	public int insert(Item item) {
		// TODO Auto-generated method stub
		if(itemDao.findByName(item.getName())!=null) {
			return -1;
		}
		if(userDao.find(item.getOwnerId())==null) {
			System.err.println("该用户没有注册,无法发布");
			return -1;
		}
		return itemDao.insert(item);
	}

	@Override
	public Item find(int id) {
		// TODO Auto-generated method stub
		return itemDao.find(id);
	}

	@Override
	public List<Item> find(String key) {
		// TODO Auto-generated method stub
		return itemDao.find(key);
	}
 
	@Override
	public int delete(Item item) {
		// TODO Auto-generated method stub
		return itemDao.delete(item);
	}

	@Override
	public int update(Item item) {
		// TODO Auto-generated method stub
		return itemDao.update(item);
	}

	@Override
	public int getPublishCount(int userId) {
		// TODO Auto-generated method stub
		return itemDao.getPublishCount(userId);
	}

	@Override
	public List SortByTime() {
		// TODO Auto-generated method stub
		return itemDao.SortByTime();
	}

	@Override
	public List SortByCollectCount() {
		// TODO Auto-generated method stub
		return itemDao.SortByCollectCount();
	}

	@Override
	public Item findByName(String itemName) {
		// TODO Auto-generated method stub
		return itemDao.findByName(itemName);
	}

	@Override
	public List<Item> findByOwnerId(int ownerId) {
		// TODO Auto-generated method stub
		return itemDao.findByOwnerId(ownerId);
	}

	@Override
	public List<Item> findByClassfication(String classfication1,
			String classfication2) {
		// TODO Auto-generated method stub
		return itemDao.findByClassfication(classfication1, classfication2);
	}

	@Override
	public List<Item> findByBuyerId(int buyId) {
		// TODO Auto-generated method stub
		return itemDao.findByBuyerId(buyId);
	}


}
