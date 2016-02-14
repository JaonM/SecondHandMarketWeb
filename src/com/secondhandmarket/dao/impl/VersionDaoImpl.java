package com.secondhandmarket.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.secondhandmarket.dao.VersionDao;
import com.secondhandmarket.model.Version;

public class VersionDaoImpl implements VersionDao {

	SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	@Override
	public int insert(Version version) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		try {
			session.save(version);
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
	public Version find(String version) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		try {
			Query query=session.createQuery("fromVersion where version=?");
			query.setParameter(0, version);
			session.beginTransaction().commit();
			List list=query.list();
			session.close();
			return (Version)list.get(0);
		} catch(Exception e) {
			e.printStackTrace();
			session.close();
		}
		return null;
	}

	@Override
	public int delete(Version version) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		try {
			session.delete(version);
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
	public int update(Version version) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		try {
			session.update(version);
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
	public List<Version> getList() {
		Session session=sessionFactory.openSession();
		try {
			Query query=session.createQuery("from Version");
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
	
}
