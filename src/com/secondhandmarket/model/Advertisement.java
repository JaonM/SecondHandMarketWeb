package com.secondhandmarket.model;

public class Advertisement {

	private int id;
	private String photoPath;
	private String url;
	
	public Advertisement() {}
	
	public Advertisement(int id,String photoPath,String url) {
		this.id=id;
		this.photoPath=photoPath;
		this.url=url;
	}
	
	public void setId(int id) {
		this.id=id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setPhotoPath(String photoPath) {
		this.photoPath=photoPath;
	}
	
	public String getPhotoPath() {
		return photoPath;
	}
	
	public void setUrl(String url) {
		this.url=url;
	}
	
	public String getUrl() {
		return url;
	}
}
