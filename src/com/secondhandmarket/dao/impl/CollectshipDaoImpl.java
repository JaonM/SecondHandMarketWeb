package com.secondhandmarket.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.secondhandmarket.dao.CollectshipDao;
import com.secondhandmarket.model.Collectship;

public class CollectshipDaoImpl implements CollectshipDao{

	//sessionFactory ÒÀÀµ×¢Èë
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	@Override
	public int insert(Collectship collectship) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		try {
			
			session.save(collectship);
			session.beginTransaction().commit();
			session.close();
			return 1;
		} catch(Exception e) {
			session.close();
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public Collectship find(int userId, int itemId) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		try {
			Query query=session.createQuery("from Collectship where userId=? and itemId=?");
			query.setParameter(0, userId);
			query.setParameter(1, itemId);
			List list=query.list();
			session.beginTransaction().commit();
			session.close();
			return (Collectship)list.get(0);
		} catch(Exception e) {
			session.close();
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List find(int userId) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		try {
			Query query=session.createQuery("from Collectship where userId=?");
			query.setParameter(0, userId);
			List list=query.list();
			session.beginTransaction().commit();
			session.close();
			return list;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int delete(Collectship collectship) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		try {
			session.delete(collectship);
			session.beginTransaction().commit();
			session.close();
			return 1;
		} catch(Exception e) {
			session.close();
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public List findByItemId(int itemId) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		try {
			Query query=session.createQuery("from Collectship where itemId=?");
			query.setParameter(0, itemId);
			session.beginTransaction().commit();
			List list=query.list();
			session.close();
			return list;
		} catch(Exception e) {
			e.printStackTrace();
			session.clear();
		}
		return null;
	}

	@Override
	public boolean isCollected(int userId, int itemId) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		try {
			Query query=session.createQuery("from Collectship where userId=? and itemId=?");
			query.setParameter(0, userId);
			query.setParameter(1, itemId);
			session.beginTransaction().commit();
			List list=query.list();
			session.close();
			if(list.size()>0)
				return true;
			else 
				return false;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
