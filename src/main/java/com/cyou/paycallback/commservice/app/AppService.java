package com.cyou.paycallback.commservice.app;

public class AppService {

	public static App getAppById(int appId) {
		return AppDao.getAppById(appId);
	}

}
