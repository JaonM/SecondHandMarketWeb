package com.secondhandmarket.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.secondhandmarket.dao.ItemDao;
import com.secondhandmarket.model.Item;

public class ItemDaoImpl  implements ItemDao {

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	@Override
	public int insert(Item item) {
		Session session=sessionFactory.openSession();
		try {
//			getHibernateTemplate().save(item);
			session.save(item);
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
	public Item find(int id) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		try {
//			List list=getHibernateTemplate().find("from Item where id=?",id);
			Query query=session.createQuery("from Item where id=?");
			query.setParameter(0, id);
			List list=query.list();
			session.beginTransaction().commit();
			if(list.size()==0) {
				session.close();
				return null;
			}
			session.close();
			return (Item)list.get(0);
		} catch(Exception e) {
			session.close();
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Item> find(String key) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		try {
//			List list=getHibernateTemplate().find("from Item where name like ?","%"+key+"%");
			Query query=session.createQuery("from Item where name like ?");
			query.setParameter(0, "%"+key+"%");
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
	public int delete(Item item) {
		Session session=sessionFactory.openSession();
		// TODO Auto-generated method stub
		try {
//			getHibernateTemplate().delete(item);
			session.delete(item);
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
	public int update(Item item) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		try {
//			getHibernateTemplate().update(item);
			session.update(item);
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
	public int getPublishCount(int userId) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		try {
			Query query=session.createQuery("from Item where ownerId=?");
			query.setParameter(0, userId);
			session.beginTransaction().commit();
			List list=query.list();
			session.close();
			return list.size();
		} catch(Exception e) {
			e.printStackTrace();
			session.close();
		}
		return 0;
	}

	@Override
	public List SortByTime() {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		try {
			Query query=session.createQuery("from Item order by uploadTime desc");
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
	public List SortByCollectCount() {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		try {
			Query query=session.createQuery("from Item order by collectCount desc");
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
	public Item findByName(String itemName) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		try {
			Query query=session.createQuery("from Item where name=?");
			query.setParameter(0, itemName);
			session.beginTransaction().commit();
			List list=query.list();
			session.close();
			if(list.size()==0)
				return null;
			return (Item)list.get(0);
		} catch(Exception e) {
			e.printStackTrace();
			session.close();
		}
		return null;
	}

	@Override
	public List<Item> findByOwnerId(int ownerId) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		try {
			Query query=session.createQuery("from Item where ownerId=?");
			query.setParameter(0, ownerId);
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
	public List<Item> findByClassfication(String classfication1,
			String classfication2) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		try {
			Query query=session.createQuery("from Item where classfication1=? and classfication2=?");
			query.setParameter(0, classfication1);
			query.setParameter(1, classfication2);
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
	public List<Item> findByBuyerId(int buyId) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		try {
			Query query=session.createQuery("from Item where buyerId=?");
			query.setParameter(0, buyId);
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
