package com.secondhandmarket.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.secondhandmarket.dao.CollectshipDao;
import com.secondhandmarket.dao.ItemDao;
import com.secondhandmarket.model.Collectship;
import com.secondhandmarket.model.Item;
import com.secondhandmarket.service.CollectshipService;

public class CollectshipServiceManage implements CollectshipService {

	private CollectshipDao collectshipDao;
	private ItemDao itemDao;
	
	public void setCollectshipDao(CollectshipDao collectshipDao) {
		this.collectshipDao=collectshipDao;
	}
	
	public CollectshipDao getCollectshipDao() {
		return collectshipDao;
	}
	
	public void setItemDao(ItemDao itemDao) {
		this.itemDao=itemDao;
	}
	
	public ItemDao getItemDao() {
		return itemDao;
	}
	
	@Override
	public int insert(Collectship collectship) {
		// TODO Auto-generated method stub
		int result=collectshipDao.insert(collectship);
		if(result==1) {
			Item item=itemDao.find(collectship.getItemId());
			item.setCollectCount(item.getCollectCount()+1);
			int r=itemDao.update(item);
			if(r==1)
				return 1;
			else
				return -1;
		} else
			return -1;
	}

	@Override
	public Collectship find(int userId, int itemId) {
		// TODO Auto-generated method stub
		return collectshipDao.find(userId, itemId);
	}

	@Override
	public List find(int userId) {
		// TODO Auto-generated method stub
		return collectshipDao.find(userId);
	}

	@Override
	public int delete(Collectship collectship) {
		// TODO Auto-generated method stub
		return collectshipDao.delete(collectship);
	}

	@Override
	public List<Item> getUserCollectList(int userId) {
		// TODO Auto-generated method stub
		List<Item> resultList=new ArrayList<Item>();
		List collectList=find(userId);
		for(int i=0;i<collectList.size();i++) {
			int itemId=((Collectship)collectList.get(i)).getItemId();
			 Item item=itemDao.find(itemId);
			 resultList.add(item);
		}
		return resultList;
	}

	@Override
	public List findByItemId(int itemId) {
		// TODO Auto-generated method stub
		return collectshipDao.findByItemId(itemId);
	}

	@Override
	public boolean isCollected(int userId, int itemId) {
		// TODO Auto-generated method stub
		return collectshipDao.isCollected(userId, itemId);
	}

}
