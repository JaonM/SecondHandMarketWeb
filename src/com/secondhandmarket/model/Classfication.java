package com.secondhandmarket.model;

/**
 * 二级分类 model
 * 一级分类固定,二级分类从服务器接收数据
 * 
 * 1-学习用品 2-数码电器 3-闲置日用 4-体育用品 5-衣鞋配饰 6-其他 
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

