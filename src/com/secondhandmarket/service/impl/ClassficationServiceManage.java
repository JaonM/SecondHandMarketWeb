package com.secondhandmarket.service.impl;

import java.util.List;

import com.secondhandmarket.dao.ClassficationDao;
import com.secondhandmarket.model.Classfication;
import com.secondhandmarket.service.ClassficationService;

public class ClassficationServiceManage implements ClassficationService {

	private ClassficationDao classficationDao;
	
	public void setClassficationDao(ClassficationDao classficationDao) {
		this.classficationDao=classficationDao;
	}
	
	public ClassficationDao getClassficationDao() {
		return classficationDao;
	}
	
	@Override
	public int insert(Classfication classfication) {
		// TODO Auto-generated method stub
		return classficationDao.insert(classfication);
	}

	@Override
	public int delete(Classfication classfication) {
		// TODO Auto-generated method stub
		return classficationDao.delete(classfication);
	}

	@Override
	public List<Classfication> findByClassfication1Id(int id) {
		// TODO Auto-generated method stub
		return classficationDao.findByClassfication1Id(id);
	}

	@Override
	public int update(Classfication classfication) {
		// TODO Auto-generated method stub
		return classficationDao.update(classfication);
	}

}
