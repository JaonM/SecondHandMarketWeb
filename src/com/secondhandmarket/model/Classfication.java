package com.secondhandmarket.model;

/**
 * �������� model
 * һ������̶�,��������ӷ�������������
 * 
 * 1-ѧϰ��Ʒ 2-������� 3-�������� 4-������Ʒ 5-��Ь���� 6-���� 
 * @author maqiang
 *
 */
public class Classfication {

	private int id;
	private String classfication2Name;
	private int classfication1Id;
	
	public Classfication() {}
	
	public Classfication(int id,String classfication2Name,int classfication1Id) {
		this.id=id;
		this.classfication2Name=classfication2Name;
		this.classfication1Id=classfication1Id;
	}
	
	public void setId(int id) {
		this.id=id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setClassfication2Name(String classfication2Name){
		this.classfication2Name=classfication2Name;
	}
	
	public String getClassfication2Name() {
		return classfication2Name;
	}
	
	public void setClassfication1Id(int classfication1Id) {
		this.classfication1Id=classfication1Id;
	}
	
	public int getClassfication1Id() {
		return classfication1Id;
	}
}

