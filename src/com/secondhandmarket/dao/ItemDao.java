package com.secondhandmarket.dao;

import java.util.List;

import com.secondhandmarket.model.Item;

/**
 * ��Ʒ Dao��ӿ�
 * @author maqiang
 *
 */
public interface ItemDao {

	/**
	 * ����һ����¼
	 * @param item
	 * @return -1��ʾ���벻�ɹ�,1��ʾ����ɹ�
	 */
	public int insert(Item item);
	
	/**
	 * ͨ����Ʒid����
	 * @param id
	 * @return
	 */
	public Item find(int id);
	
	/**
	 * ͨ���ؼ��ֲ�����Ʒ
	 * @param key ��ѯ�Ĺؼ��� like
	 * @return ����ƥ�����Ʒ����
	 */
	public List find(String key);
	
	/**
	 * ͨ����Ʒ��ƥ����Ʒ
	 * @param itemName
	 * @return
	 */
	public Item findByName(String itemName);
	
	/**
	 * ɾ��һ����¼
	 * @param item
	 * @return -1��ʾɾ��ʧ��,1��ʾɾ���ɹ�
	 */
	public int delete(Item item);
	
	/**
	 * ���¼�¼
	 * @param item
	 */
	public int update(Item item);
	
	/**
	 * ��ȡĳ���û�����������
	 * @param userId
	 * @return
	 */
	public int getPublishCount(int userId);
	
	/**
	 * �����ϴ�ʱ������---���¶�̬
	 * @return
	 */
	public List SortByTime();
	
	/**
	 * �����ղش�������---���ŵ�Ʒ
	 * @return
	 */
	public List SortByCollectCount();
	
	/**
	 * ����ĳ���û���������Ʒ
	 * @param ownerId
	 * @return
	 */
	public List findByOwnerId(int ownerId);
	
	/**
	 * ͨ����Ʒ�������
	 * @param classfication1
	 * @param classfication2
	 * @return
	 */
	public List<Item> findByClassfication(String classfication1,String classfication2);
	
	/**
	 * ��ѯ�û�Ԥ������Ʒ
	 * @param buyId
	 * @return
	 */
	public List<Item> findByBuyerId(int buyId);
}
