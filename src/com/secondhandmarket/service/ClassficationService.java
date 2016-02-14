package com.secondhandmarket.service;

import java.util.List;

import com.secondhandmarket.model.Classfication;

public interface ClassficationService {

	public int insert(Classfication classfication);
	
	public int delete(Classfication classfication);
	
	public List<Classfication> findByClassfication1Id(int id);
	
	public int update(Classfication classfication);
}
