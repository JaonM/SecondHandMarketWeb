package com.secondhandmarket.service;

import java.util.List;

import com.secondhandmarket.model.ItemPhoto;

/**
 * 业务逻辑层 对Dao层进行封装
 * @author maqiang
 *
 */
public interface ItemPhotoService {

	/**
	 * 插入一条物品图片记录,对应物品photoCount字段
	 * 判断item 的 photo 是否 一超出数量
	 * @param itemPhoto
	 * @return -1表示插入失败,1表示插入成功
	 */
	public int insert(ItemPhoto itemPhoto);
	
	/**
	 * 通过通过物品的id进行查找
	 * @param itemId
	 * @return 返回匹配的图片集合
	 */
	public List findByItemId(int itemId);
	
	/**
	 * 通过物品图片的id查找
	 * @param id
	 * @return 返回特定的图片
	 */
	public ItemPhoto findByPhotoId(int id);
	
	/**
	 * 删除一条记录
	 * @param itemPhoto
	 */
	public void delete(ItemPhoto itemPhoto);
	
	/**
	 * 更新一条记录
	 * @param itemPhoto
	 */
	public void update(ItemPhoto itemPhoto);
	

}
