package com.secondhandmarket.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.secondhandmarket.dao.ClassficationDao;
import com.secondhandmarket.model.Classfication;

public class ClassficationDaoImpl implements ClassficationDao {

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	@Override
	public int insert(Classfication classfication) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		try {
			session.save(classfication);
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
	public int delete(Classfication classfication) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		try {
			session.delete(classfication);
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
	public List<Classfication> findByClassfication1Id(int id) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		try {
			Query query=session.createQuery("from Classfication where classfication1Id=?");
			query.setParameter(0, id);
			session.beginTransaction().commit();
			List list=query.list();
			session.close();
			return list;
		} catch(Exception e) {
			e.printStackTrace();
			session.close();
		}
		return null;
	}

	@Override
	public int update(Classfication classfication) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		try {
			session.update(classfication);
			session.beginTransaction().commit();
			session.close();
			return 1;
		} catch(Exception e) {
			e.printStackTrace();
			session.close();
		}
		return -1;
	}

}
