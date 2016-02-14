package com.secondhandmarket.model;

import java.io.Serializable;

/**
 * POJO ӳ��
 * ģ�Ͳ��û�
 * @author maqiang
 *
 */
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6051730374405463774L;
	private int id;								//�û�id   ����
	private String account;					//�û���¼�˺�
	private String password;				//�û���¼����
	private String nickName;				//�û���
	private String name;						//��ʵ����
	private String phone;					//�ֻ�����
	private String sNo;						//�û�ѧ��
	private byte sex;                          //�û��Ա�    0-female  1-male
	private String selfIntroduction; 	//����ǩ���û� 
	private String userPhoto;					//�û�ͷ����·��
	
	public User() {
		
	}
	
	public User(int id,String account,String password,String nickName,String name,String phone,String sNo,byte sex,String selfIntroduction,String userPhoto) {
		this.id=id;
		this.account=account;
		this.password=password;
		this.nickName=nickName;
		this.name=name;
		this.phone=phone;
		this.sNo=sNo;
		this.sex=sex;
		this.selfIntroduction=selfIntroduction;
		this.userPhoto=userPhoto;
	}
	
	//������getter setter
	public void setId(int id) {
		this.id=id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setAccount(String account) {
		this.account=account;
	}
	
	public String getAccount() {
		return account;
	}
	
	public void setPassword(String password) {
		this.password=password;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setNickName(String nickName) {
		this.nickName=nickName;
	}
	
	public String getNickName() {
		return nickName;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setPhone(String phone) {
		this.phone=phone;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setsNo(String sNo) { 
		this.sNo=sNo;
	} 
	
	public String getsNo() {
		return sNo;
	}
	
	public void setSex(byte sex) {
		this.sex=sex;
	}
	
	public byte getSex() {
		return sex;
	}
	
	public void setSelfIntroduction(String selfIntroduction) {
		this.selfIntroduction=selfIntroduction;
	}
	
	public String getSelfIntroduction() {
		return selfIntroduction;
	}
	
	public void setUserPhoto(String userPhoto) {
		this.userPhoto=userPhoto;
	}
	
	public String getUserPhoto() {
		return userPhoto;
	}
	
	public String toString() {
		return "����: "+name+" ���ҽ���: "+selfIntroduction;
	}
	
	@Override
    public int hashCode() {
		
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id==0) ? 0 : id);
        return result;
    }
	
	@Override
	public boolean equals(Object obj) {
		if(this==obj)
			return true;
		if(obj==null)
			return false;
		if(getClass()!=obj.getClass())
			return false;
		User other=(User)obj;
		if(id==0) {
			if(other.id!=0)
				return false;
		} else if(id!=other.id)
			return false;
			
		if(account.equals("")) {
			if(!other.equals(""))
				return false;
		} else if(!account.equals(other.account))
			return false;
		
		return true;
	}
}
