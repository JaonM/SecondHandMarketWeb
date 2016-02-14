package com.secondhandmarket.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.secondhandmarket.dao.UserDao;
import com.secondhandmarket.model.User;

/**
 * User Dao接口实现类
 * @author maqiang
 *
 */
public class UserDaoImpl implements UserDao{
	
	//依赖注入SessionFactory
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	@Override
	public int insert(User user) {
		Session session=sessionFactory.openSession();
		try {
//			getHibernateTemplate().save(user);
//			sessionFactory.getCurrentSession().save(user);		
			
			if(findByAccount(user.getAccount()))
				session.save(user);
			else
				return -1;
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
	public User find(int id) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();

		try {
//			List list=getHibernateTemplate().find("from User where id=?",id);
			Query query=session.createQuery("from User where id=?");
			query.setParameter(0, id);
			List list=query.list();
			session.beginTransaction().commit();
			if(list.size()==0) {
				session.close();
				return null;
			}
			session.close();
			return (User)list.get(0);
		} catch(Exception e) {
			e.printStackTrace();
			session.close();
		}
		return null;
	}

	@Override
	public List find(String nickName) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		try {
//			List list=getHibernateTemplate().find("from User where nickname=?",nickName);
			
			Query query=session.createQuery("from User where nickname=?");
			query.setParameter(0, nickName);
			List list=query.list();
			session.beginTransaction().commit();
			if(list.size()==0) {
				session.close();
				return null;
			}
			session.close();
			return list;
		} catch(Exception e) {
			e.printStackTrace();
			session.close();
		}
		return null;
	}
	
	@Override
	public User find(String account,String password) {
		try {
//			List list=getHibernateTemplate().find("from User user where user.account=? and user.passord=?",new Object [] {account,password});
			Session session=sessionFactory.openSession();
			Query query=session.createQuery("from User where account=? and password=?");
			query.setParameter(0, account);
			query.setParameter(1, password);
			List list=query.list();
			session.beginTransaction().commit();
			if(list.size()==0) {
				session.close();
				return null;
			}
			session.close();
			return (User)list.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	

	@Override
	public int delete(User user) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();

		try {
//			getHibernateTemplate().delete(user);
			session.delete(user);
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
	public int updateName(User user, String name) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		try {
			User user0=find(user.getId());
			if(user0==null) {
				session.close();
				return -1;
			}
			user0.setName(name);
//			getHibernateTemplate().update(user0);
			session.update(user0);
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
	public int updateSignature(User user, String signature) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		try {
			User user0=find(user.getId());
			if(user0==null) {
				session.close();
				return -1;
			}
			user0.setSelfIntroduction(signature);
//			getHibernateTemplate().update(user0);
			session.update(user0);
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
	public int updateNickName(User user, String nickName) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		try {
			User user0=find(user.getId());
			if(user0==null) {
				session.close();
				return -1;
			}
			user0.setNickName(nickName);
//			getHibernateTemplate().update(user0);
			session.update(user0);
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
	public int updatePhone(User user, String phone) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		try {
			User user0=find(user.getId());
			if(user0==null) {
				session.close();
				return -1;
			}
			user0.setPhone(phone);
//			getHibernateTemplate().update(user0);
			session.update(user0);
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
	public int updatePassword(User user, String password) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		try {
			User user0=find(user.getId());
			if(user0==null) {
				session.close();
				return -1;
			}
			user0.setPassword(password);
//			getHibernateTemplate().update(user0);
			session.update(user0);
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
	public int findSize() {
		// TODO Auto-generated method stub
//		return getHibernateTemplate().find("from User").size();
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from User");
		session.beginTransaction().commit();
		List list=query.list();
		session.close();
		return list.size();
	}

	@Override
	public int updateUserPhoto(User user, String photoPath) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		try {
			User user0=find(user.getId());
			if(user0==null) {
				session.close();
				return -1;
			}
			user0.setUserPhoto(photoPath);
//			getHibernateTemplate().update(user0);
			session.update(user0);
			session.beginTransaction().commit();
			session.close();
			return 1;
		} catch(Exception e) {
			e.printStackTrace();
			session.close();
		}
		return -1;
	}
	
	//通过用户账户查找是否有重复
	private boolean findByAccount(String account) {
		Session session=sessionFactory.openSession();
		try {
			Query query=session.createQuery("from User where account=?");
			query.setParameter(0, account);
			List list=query.list();
			session.close();
			if(list.size()>0)
				return false;
		} catch(Exception e) {
			session.close();
			e.printStackTrace();
		}
		return true;
	}

}
