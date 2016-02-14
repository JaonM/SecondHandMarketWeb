package com.secondhandmarket.service.impl;

import java.util.List;

import com.secondhandmarket.dao.UserDao;
import com.secondhandmarket.model.User;
import com.secondhandmarket.service.UserService;

public class UserServiceManage implements UserService{

	private UserDao userDao;									//“¿¿µ◊¢»ÎuserDao
	
	public void setUserDao(UserDao userDao) {
		this.userDao=userDao;
	}
	
	public UserDao getUserDao() {
		return userDao;
	}
	
	@Override
	public int insert(User user) {
		return userDao.insert(user);
	}

	@Override
	public User find(int id) {
		// TODO Auto-generated method stub
		return userDao.find(id);
	}

	@Override
	public List find(String nickName) {
		// TODO Auto-generated method stub
		return userDao.find(nickName);
	}
	
	@Override
	public User find(String account,String password) {
		return userDao.find(account, password);
	}

	@Override
	public int delete(User user) {
		// TODO Auto-generated method stub
		return userDao.delete(user);
	}

	@Override
	public int updateName(User user, String name) {
		// TODO Auto-generated method stub
		return userDao.updateName(user, name);
	}

	@Override
	public int updateSignature(User user, String signature) {
		// TODO Auto-generated method stub
		return userDao.updateSignature(user, signature);
	}

	@Override
	public int updateNickName(User user, String nickName) {
		// TODO Auto-generated method stub
		return userDao.updateNickName(user, nickName);
	}

	@Override
	public int updatePhone(User user, String phone) {
		// TODO Auto-generated method stub
		return userDao.updatePhone(user, phone);
	}

	@Override
	public int updatePassword(User user, String password) {
		// TODO Auto-generated method stub
		return userDao.updatePassword(user, password);
	}

	@Override
	public int updateUserPhoto(User user, String photoPath) {
		// TODO Auto-generated method stub
		return userDao.updateUserPhoto(user, photoPath);
	}

	@Override
	public int findSize() {
		// TODO Auto-generated method stub
		return userDao.findSize();
	}

	
}
