package com.secondhandmarket.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.secondhandmarket.dao.MessageDao;
import com.secondhandmarket.model.Message;

public class MessageDaoImpl implements MessageDao {

	//“¿¿µ◊¢»Î
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	@Override
	public int insert(Message message) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		try {
			session.save(message);
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
	public int update(Message message) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		try {
			session.update(message);
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
	public int delete(Message message) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		try {
			session.delete(message);
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
	public List<Message> find(int userId, int itemId) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		try {
			Query query=session.createQuery("from Message where userId=? and itemId=?");
			query.setParameter(0, userId);
			query.setParameter(1, itemId);
			List list=query.list();
			session.beginTransaction().commit();
			session.close();
			return list;
		} catch(Exception e) {
			e.printStackTrace();
			session.close();
		}
		return null;
	}

	@Override
	public List findByItemId(int itemId) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		try {
			Query query=session.createQuery("from Message where itemId=?");
			query.setParameter(0, itemId);
			List list=query.list();
			session.beginTransaction().commit();
			session.close();
			return list;
		} catch(Exception e) {
			e.printStackTrace();
			session.close();
		}
		return null;
	}

	@Override
	public List<Message> findByUserId(int userId) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		try {
			Query query=session.createQuery("from Message where userId=?");
			query.setParameter(0, userId);
			List list=query.list();
			session.beginTransaction().commit();
			session.close();
			return list;
		}catch(Exception e) {
			e.printStackTrace();
			session.close();
		}
		return null;
	}

}
