package com.secondhandmarket.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.secondhandmarket.dao.ItemPhotoDao;
import com.secondhandmarket.model.ItemPhoto;

public class ItemPhotoDaoImpl implements ItemPhotoDao {

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	 
	@Override
	public int insert(ItemPhoto itemPhoto) {
		Session session=sessionFactory.openSession();
		try {
//			getHibernateTemplate().save(itemPhoto);
			session.save(itemPhoto);
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
//			List list=getHibernateTemplate().find("from ItemPhoto where itemId=?",itemId);
			Query query=session.createQuery("from ItemPhoto where itemId=?");
			query.setParameter(0, itemId);
			List list=query.list();
			session.beginTransaction().commit();
			if(list.size()==0) {
				session.close();
				return null;
			}
			session.close();
			return list;
		} catch(Exception e) {
			session.close();
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ItemPhoto findByPhotoId(int id) {
		Session session=sessionFactory.openSession();
		// TODO Auto-generated method stub
		try {
			Query query=session.createQuery("from ItemPhoto where id=?");
//			List list=getHibernateTemplate().find("from ItemPhoto where id=?",id);
			query.setParameter(0,id);
			List list=query.list();
			session.beginTransaction().commit();
			session.close();
			if(list.size()==0) {
				session.close();
				return null;
			}
			session.close();
			return (ItemPhoto)list.get(0);
		} catch(Exception e) {
			session.close();
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void delete(ItemPhoto itemPhoto) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		try {
//			getHibernateTemplate().delete(itemPhoto);
			session.delete(itemPhoto);
			session.beginTransaction().commit();
			session.close();
		} catch(Exception e) {
			session.close();
			e.printStackTrace();
		}
	}

	@Override
	public void update(ItemPhoto itemPhoto) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		try {
			session.update(itemPhoto);
			session.beginTransaction().commit();
			session.close();
//			getHibernateTemplate().update(itemPhoto);
		} catch(Exception e) {
			session.close();
			e.printStackTrace();
		}
	}
	
	

}
