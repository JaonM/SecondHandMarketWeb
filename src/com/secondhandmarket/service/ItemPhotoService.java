package com.secondhandmarket.service;

import java.util.List;

import com.secondhandmarket.model.ItemPhoto;

/**
 * ҵ���߼��� ��Dao����з�װ
 * @author maqiang
 *
 */
public interface ItemPhotoService {

	/**
	 * ����һ����ƷͼƬ��¼,��Ӧ��ƷphotoCount�ֶ�
	 * �ж�item �� photo �Ƿ� һ��������
	 * @param itemPhoto
	 * @return -1��ʾ����ʧ��,1��ʾ����ɹ�
	 */
	public int insert(ItemPhoto itemPhoto);
	
	/**
	 * ͨ��ͨ����Ʒ��id���в���
	 * @param itemId
	 * @return ����ƥ���ͼƬ����
	 */
	public List findByItemId(int itemId);
	
	/**
	 * ͨ����ƷͼƬ��id����
	 * @param id
	 * @return �����ض���ͼƬ
	 */
	public ItemPhoto findByPhotoId(int id);
	
	/**
	 * ɾ��һ����¼
	 * @param itemPhoto
	 */
	public void delete(ItemPhoto itemPhoto);
	
	/**
	 * ����һ����¼
	 * @param itemPhoto
	 */
	public void update(ItemPhoto itemPhoto);
	

}
