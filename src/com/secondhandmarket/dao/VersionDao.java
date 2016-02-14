package com.secondhandmarket.dao;

import java.util.List;

import com.secondhandmarket.model.Version;

/**
 * �汾����Dao
 * @author maqiang
 *
 */
public interface VersionDao {

	public int insert(Version verssion);

	public Version find(String version);
	
	public int delete(Version version);
	
	public int update(Version version);
	
	/**
	 * �������еİ汾��Ϣ
	 */
	public List<Version> getList();
}
