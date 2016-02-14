package com.secondhandmarket.service;

import java.util.List;

import com.secondhandmarket.model.Advertisement;

public interface AdvertisementService {

	public int insert(Advertisement ad);
	
	public int delete(Advertisement ad);
	
	public int update(Advertisement ad);
	
	public List<Advertisement> find();
}
