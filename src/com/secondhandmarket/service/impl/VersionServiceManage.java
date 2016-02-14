package com.secondhandmarket.service.impl;

import java.util.List;

import com.secondhandmarket.dao.VersionDao;
import com.secondhandmarket.model.Version;
import com.secondhandmarket.service.VersionService;

public class VersionServiceManage implements VersionService {

	VersionDao versionDao;
	
	public void setVersionDao(VersionDao versionDao) {
		this.versionDao=versionDao;
	}
	
	public VersionDao getVersionDao() {
		return versionDao;
	}
	
	@Override
	public int insert(Version verssion) {
		// TODO Auto-generated method stub
		return versionDao.insert(verssion);
	}

	@Override
	public Version find(String version) {
		// TODO Auto-generated method stub
		return versionDao.find(version);
	}

	@Override
	public int delete(Version version) {
		// TODO Auto-generated method stub
		return versionDao.delete(version);
	}

	@Override
	public int update(Version version) {
		// TODO Auto-generated method stub
		return versionDao.update(version);
	}

	@Override
	public List<Version> getList() {
		return versionDao.getList();
	}
}
