package com.secondhandmarket.service;

import java.util.List;

import com.secondhandmarket.model.Version;

public interface VersionService {

	public int insert(Version verssion);

	public Version find(String version);
	
	public int delete(Version version);
	
	public int update(Version version);
	
	public List<Version> getList();
}
