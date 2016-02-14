package com.secondhandmarket.dao;

import java.util.List;

import com.secondhandmarket.model.Advertisement;

public interface AdvertisementDao {

	public int insert(Advertisement ad);
	
	public int delete(Advertisement ad);
	
	public int update(Advertisement ad);
	
	public List<Advertisement> find();
}
