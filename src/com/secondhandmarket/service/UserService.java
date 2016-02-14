package com.secondhandmarket.service;

import java.util.List;

import com.secondhandmarket.model.User;

/**
 * 业务逻辑层 对Dao层进行封装
 * @author maqiang
 *
 */
public interface UserService {

	/**
	 * 插入一条记录,需要检查用户账号是否重复
	 * @param user
	 * @return -1表示插入不成功,1表示插入成功
	 */
	public int insert(User user);
	
	/** 
	 * 查询用户记录,重载
	 * 通过用户id查询
	 */
	public User find(int id);
	
	/**
	 * 通过昵称查找
	 */
	public List find(String nickName);

	/**
	 * 通过账户名.密码查找
	 * @param account
	 * @param password
	 * @return
	 */
	public User find(String account,String password);

	
	/**
	 * 
	 * @param user
	 * @return -1表示删除不成功,1表示删除成功
	 */
	public int delete(User user);
	
	/**
	 * 更新相应用户姓名
	 * @param user
	 * @param name
	 * @return -1表示更新不成功,1表示更新成功
	 */
	public int updateName(User user,String name);
	
	/**
	 * 更新相应用户个性签名
	 * @param user
	 * @param signature
	 * @return -1表示更新不成功,1表示更新成功
	 */
	public int updateSignature(User user,String signature);
	
	/**
	 * 更新相应用户昵称
	 * @param user
	 * @param nickName
	 * @return -1表示更新不成功,1表示更新成功
	 */
	public int updateNickName(User user,String nickName);
	
	/**
	 * 更新相应用户联系方式
	 * @param user
	 * @param phone
	 * @return -1表示更新不成功,1表示更新成功
	 */
	public int updatePhone(User user,String phone);
	
	/**
	 * 更新相应用户密码
	 * @param user
	 * @param password
	 * @return -1表示更新不成功,1表示更新成功
	 */
	public int updatePassword(User user,String password);
	
	/**
	 * 更新用户头像
	 * @param user
	 * @param photo
	 * @return -1表示更新不成功,1表示更新成功
	 */
	public int updateUserPhoto(User user,String photoPath);
	 
	/**
	 * 返回User表用户数量
	 * @return size
	 */
	public int findSize();
}
