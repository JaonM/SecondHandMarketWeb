package com.secondhandmarket.service.impl;

import java.util.List;

import com.secondhandmarket.dao.ItemDao;
import com.secondhandmarket.dao.ItemPhotoDao;
import com.secondhandmarket.model.ItemPhoto;
import com.secondhandmarket.service.ItemPhotoService;

public class ItemPhotoServiceManage implements ItemPhotoService {

	private ItemPhotoDao itemPhotoDao;							//itemPhotoDao依赖注入 
	
	private ItemDao itemDao;											//itemDao依赖注入
	
	public void setItemPhotoDao(ItemPhotoDao itemPhotoDao) {
		this.itemPhotoDao=itemPhotoDao;
	}
	
	public void setItemDao(ItemDao itemDao) {
		this.itemDao=itemDao;
	}
	
	public ItemDao getItemDao() {
		return itemDao;
	}
	
	public ItemPhotoDao getItemPhotoDao() {
		return itemPhotoDao;
	}
	
	@Override
	public int insert(ItemPhoto itemPhoto) {
		// TODO Auto-generated method stub
		if(itemDao==null) {
			System.err.print("itemDao 为空");
			return -1;
		}
		/**
		try {
			//数据库已经存在的物品题图片数量
			int photoCount=itemPhotoDao.findByItemId(itemPhoto.getItemId()).size();
			//应有图片数量
			int shouldPhotoCount=itemDao.find(itemPhoto.getItemId()).getPhotoCount();
			if(photoCount>shouldPhotoCount) {
				System.err.println("图片数量超出范围,无法插入图片");
				return -1;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		*/
		if(itemDao.find(itemPhoto.getItemId())==null) {
			System.err.println("插入图片出错");
			return -1;
		}
		try {
			itemPhotoDao.insert(itemPhoto);
			return 1;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public List findByItemId(int itemId) {
		// TODO Auto-generated method stub
		return itemPhotoDao.findByItemId(itemId);
	}

	@Override
	public ItemPhoto findByPhotoId(int id) {
		// TODO Auto-generated method stub
		return itemPhotoDao.findByPhotoId(id);
	}

	@Override
	public void delete(ItemPhoto itemPhoto) {
		// TODO Auto-generated method stub
		itemPhotoDao.delete(itemPhoto);
	}

	@Override
	public void update(ItemPhoto itemPhoto) {
		// TODO Auto-generated method stub
		ItemPhoto itemPhoto0=itemPhotoDao.findByPhotoId(itemPhoto.getId());
		if(itemPhoto0!=null)
			itemPhotoDao.update(itemPhoto);
		else
			System.err.println("更新出错");
	}

}
