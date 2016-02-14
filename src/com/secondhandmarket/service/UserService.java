package com.secondhandmarket.service;

import java.util.List;

import com.secondhandmarket.model.User;

/**
 * ҵ���߼��� ��Dao����з�װ
 * @author maqiang
 *
 */
public interface UserService {

	/**
	 * ����һ����¼,��Ҫ����û��˺��Ƿ��ظ�
	 * @param user
	 * @return -1��ʾ���벻�ɹ�,1��ʾ����ɹ�
	 */
	public int insert(User user);
	
	/** 
	 * ��ѯ�û���¼,����
	 * ͨ���û�id��ѯ
	 */
	public User find(int id);
	
	/**
	 * ͨ���ǳƲ���
	 */
	public List find(String nickName);

	/**
	 * ͨ���˻���.�������
	 * @param account
	 * @param password
	 * @return
	 */
	public User find(String account,String password);

	
	/**
	 * 
	 * @param user
	 * @return -1��ʾɾ�����ɹ�,1��ʾɾ���ɹ�
	 */
	public int delete(User user);
	
	/**
	 * ������Ӧ�û�����
	 * @param user
	 * @param name
	 * @return -1��ʾ���²��ɹ�,1��ʾ���³ɹ�
	 */
	public int updateName(User user,String name);
	
	/**
	 * ������Ӧ�û�����ǩ��
	 * @param user
	 * @param signature
	 * @return -1��ʾ���²��ɹ�,1��ʾ���³ɹ�
	 */
	public int updateSignature(User user,String signature);
	
	/**
	 * ������Ӧ�û��ǳ�
	 * @param user
	 * @param nickName
	 * @return -1��ʾ���²��ɹ�,1��ʾ���³ɹ�
	 */
	public int updateNickName(User user,String nickName);
	
	/**
	 * ������Ӧ�û���ϵ��ʽ
	 * @param user
	 * @param phone
	 * @return -1��ʾ���²��ɹ�,1��ʾ���³ɹ�
	 */
	public int updatePhone(User user,String phone);
	
	/**
	 * ������Ӧ�û�����
	 * @param user
	 * @param password
	 * @return -1��ʾ���²��ɹ�,1��ʾ���³ɹ�
	 */
	public int updatePassword(User user,String password);
	
	/**
	 * �����û�ͷ��
	 * @param user
	 * @param photo
	 * @return -1��ʾ���²��ɹ�,1��ʾ���³ɹ�
	 */
	public int updateUserPhoto(User user,String photoPath);
	 
	/**
	 * ����User���û�����
	 * @return size
	 */
	public int findSize();
}
