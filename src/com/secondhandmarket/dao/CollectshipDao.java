package com.secondhandmarket.dao;

import java.util.List;

import com.secondhandmarket.model.Collectship;

public interface CollectshipDao {

	/**
	 * ����һ����¼
	 * @param collectship
	 * @return
	 */
	public int insert(Collectship collectship);
	
	/**
	 * ������������һ����¼
	 * @param userId
	 * @param itemId
	 * @return
	 */
	public Collectship find(int userId,int itemId);
	
	/**
	 * ����userId����ĳ���û����ղع�ϵ
	 * @param userId
	 * @return
	 */
	public List find(int userId);
	
	/**
	 * ɾ��һ����¼ 
	 * @param collectship
	 * @return
	 */
	public int delete(Collectship collectship);
	
	/**
	 * ����itemId������Ӧ��Ʒ���ղع�ϵ
	 * @param itemId
	 * @return
	 */
	public List findByItemId(int itemId);
	
	/**
	 * �����Ʒ�Ƿ��ղ�
	 */
	public boolean isCollected(int userId,int itemId);
}
