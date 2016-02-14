package com.secondhandmarket.dao;

import java.util.List;

import com.secondhandmarket.model.Item;

/**
 * 物品 Dao层接口
 * @author maqiang
 *
 */
public interface ItemDao {

	/**
	 * 插入一条记录
	 * @param item
	 * @return -1表示插入不成功,1表示插入成功
	 */
	public int insert(Item item);
	
	/**
	 * 通过物品id查找
	 * @param id
	 * @return
	 */
	public Item find(int id);
	
	/**
	 * 通过关键字查找物品
	 * @param key 查询的关键字 like
	 * @return 返回匹配的物品集合
	 */
	public List find(String key);
	
	/**
	 * 通过物品名匹配物品
	 * @param itemName
	 * @return
	 */
	public Item findByName(String itemName);
	
	/**
	 * 删除一条记录
	 * @param item
	 * @return -1表示删除失败,1表示删除成功
	 */
	public int delete(Item item);
	
	/**
	 * 更新记录
	 * @param item
	 */
	public int update(Item item);
	
	/**
	 * 获取某个用户发布的数量
	 * @param userId
	 * @return
	 */
	public int getPublishCount(int userId);
	
	/**
	 * 根据上传时间排序---最新动态
	 * @return
	 */
	public List SortByTime();
	
	/**
	 * 根据收藏次数排序---热门单品
	 * @return
	 */
	public List SortByCollectCount();
	
	/**
	 * 查找某个用户发布的物品
	 * @param ownerId
	 * @return
	 */
	public List findByOwnerId(int ownerId);
	
	/**
	 * 通过物品分类查找
	 * @param classfication1
	 * @param classfication2
	 * @return
	 */
	public List<Item> findByClassfication(String classfication1,String classfication2);
	
	/**
	 * 查询用户预定的物品
	 * @param buyId
	 * @return
	 */
	public List<Item> findByBuyerId(int buyId);
}
