package com.secondhandmarket.dao;

import java.util.List;

import com.secondhandmarket.model.Version;

/**
 * 版本更新Dao
 * @author maqiang
 *
 */
public interface VersionDao {

	public int insert(Version verssion);

	public Version find(String version);
	
	public int delete(Version version);
	
	public int update(Version version);
	
	/**
	 * 返回所有的版本信息
	 */
	public List<Version> getList();
}
