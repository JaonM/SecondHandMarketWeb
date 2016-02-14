package com.secondhandmarket.service.impl;

import java.util.List;

import com.secondhandmarket.dao.AdvertisementDao;
import com.secondhandmarket.model.Advertisement;
import com.secondhandmarket.service.AdvertisementService;

public class AdvertisementServiceManage implements AdvertisementService {

	AdvertisementDao advertisementDao;
	
	public void setAdvertisementDao(AdvertisementDao advertisementDao) {
		this.advertisementDao=advertisementDao;
	}
	
	public AdvertisementDao getAdvertisementDao() {
		return advertisementDao;
	}
	
	@Override
	public int insert(Advertisement ad) {
		// TODO Auto-generated method stub
		return advertisementDao.insert(ad);
	}

	@Override
	public int delete(Advertisement ad) {
		// TODO Auto-generated method stub
		return advertisementDao.delete(ad);
	}

	@Override
	public int update(Advertisement ad) {
		// TODO Auto-generated method stub
		return advertisementDao.update(ad);
	}

	@Override
	public List<Advertisement> find() {
		// TODO Auto-generated method stub
		return advertisementDao.find();
	}

}
