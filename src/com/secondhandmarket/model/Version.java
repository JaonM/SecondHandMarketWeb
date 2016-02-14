package com.secondhandmarket.model;

public class Version {

	private int id;		//id
	private String version;		//°æ±¾ºÅ
	private String apkPath;		//apkÂ·¾¶
	private String description;//°æ±¾ÃèÊö
	
	public Version(){}
	
	public Version(int id,String version,String apkPath,String description) {
		this.id=id;
		this.version=version;
		this.apkPath=apkPath;
		this.description=description;
	}
	
	public void setId(int id) {
		this.id=id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setVersion(String version) {
		this.version=version;
	}
	
	public String getVersion() {
		return version;
	}
	
	public void setApkPath(String apkPath) {
		this.apkPath=apkPath;
	}
	
	public String getApkPath() {
		return apkPath;
	}
	
	public void setDescription(String description) {
		this.description=description;
	}
	
	public String getDescription() {
		return description;
	}
}
