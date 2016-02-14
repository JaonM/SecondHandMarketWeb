package com.secondhandmarket.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.secondhandmarket.dao.RelationshipDao;
import com.secondhandmarket.model.Relationship;
import com.secondhandmarket.model.RelationshipId;

public class RelationshipDaoImpl implements RelationshipDao {
	
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	@Override
	public int insert(Relationship relationship) {
		
		Session session=sessionFactory.openSession();
		try {
			session.save(relationship);
			session.beginTransaction().commit();
			session.close();
//			getHibernateTemplate().save(relationship);
			return 1;
		} catch(Exception e) {
			session.close();
			e.printStackTrace();
		}
		
		return -1;
		
		
	}

	@Override
	public Relationship findRelationship(RelationshipId relationshipId) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		try {
			/**
			Query query=session.createQuery("from Relationship where relationshipId=?");
			query.setParameter(0, relationshipId);
			List list=query.list();
			session.beginTransaction().commit();
			if(list.size()==0) {
				session.close();
				return null;
			}
			*/
			Relationship relationship=(Relationship)session.get(Relationship.class, relationshipId);
			session.close();
//			List list=getHibernateTemplate().find("from Relationship where relationshipId=?",relationshipId);
			return relationship;
		} catch (Exception e) {
			session.close();
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateRelationship(Relationship relationship) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		try {
			session.update(relationship);
			session.beginTransaction().commit();
			session.close();
//			getHibernateTemplate().save(relationshipId);
		} catch(Exception e) {
			session.close();
			e.printStackTrace();
		}
		
	}

	@Override
	public int deleteRelationship(Relationship relationship) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		try {
			session.delete(relationship);
			session.beginTransaction().commit();
			session.close();
			return 1;
//			getHibernateTemplate().delete(relationshipId);
		} catch(Exception e) {
			session.close();
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int getUserAttentionCount(int user1Id) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		try {
			Query query=session.createQuery("from Relationship r where r.relationshipId.user1Id=? and r.relation=1");
			query.setParameter(0, user1Id);
			List list=query.list();
			session.beginTransaction().commit();
			session.close();
			return list.size();
		} catch(Exception e) {
			session.close();
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int getUserBeAttentionedCount(int userId) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		try {
			Query query=session.createQuery("from Relationship r where r.relationshipId.user2Id=? and r.relation=1");
			query.setParameter(0, userId);
			List list=query.list();
			session.beginTransaction().commit();
			session.close();
			return list.size();
		} catch(Exception e) {
			session.close();
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Integer> getUserAttentionIdList(int user1Id) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		try {
			Query query=session.createQuery("from Relationship r where r.relationshipId.user1Id=? and r.relation=1");
			query.setParameter(0, user1Id);
			List list=query.list();
			session.beginTransaction().commit();
			List<Integer> userIdList=new ArrayList<Integer>();
			for(int i=0;i<list.size();i++) {
				Relationship relationship=(Relationship)list.get(i);
				int user2Id=relationship.getRelationshipId().getUser2Id();
				userIdList.add(user2Id);
			}
			session.close();
			return userIdList;
		} catch(Exception e) {
			session.close();
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Integer> getUserBeAttentionedIdList(int userId) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		try {
			Query query=session.createQuery("from Relationship r where r.relationshipId.user2Id=? and r.relation=1");
			query.setParameter(0, userId);
			List list=query.list();
			session.beginTransaction().commit();
			List<Integer> userIdList=new ArrayList<Integer>();
			for(int i=0;i<list.size();i++) {
				Relationship relationship=(Relationship)list.get(i);
				int user1Id=relationship.getRelationshipId().getUser1Id();
				userIdList.add(user1Id);
			}
			session.close();
			return userIdList;
		} catch(Exception e) {
			session.close();
			e.printStackTrace();
		}
		return null;
	}

}
