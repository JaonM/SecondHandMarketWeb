package com.secondhandmarket.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.secondhandmarket.dao.AdvertisementDao;
import com.secondhandmarket.model.Advertisement;

public class AdvertisementDaoImpl implements AdvertisementDao{

	SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	@Override
	public int insert(Advertisement ad) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		try {
			session.save(ad);
			session.beginTransaction().commit();;
			session.close();
			return 1;
		} catch(Exception e) {
			e.printStackTrace();
			session.close();
		}
		return -1;
	}

	@Override
	public int delete(Advertisement ad) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		try {
			session.delete(ad);
			session.beginTransaction().commit();
			session.close();
			return 1;
		} catch(Exception e) {
			e.printStackTrace();
			session.close();
		}
		return -1;
	}

	@Override
	public int update(Advertisement ad) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		try {
			session.update(ad);
			session.beginTransaction().commit();
			session.close();
			return 1;
		} catch(Exception e) {
			e.printStackTrace();
			session.close();
		}
		return -1;
	}

	@Override
	public List<Advertisement> find() {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		try {
			Query query=session.createQuery("from Advertisement");
			session.beginTransaction().commit();
			List<Advertisement> list=query.list();
			session.close();
			return list;
		} catch(Exception e) {
			e.printStackTrace();
			session.close();
		}
		return null;
	}

}
