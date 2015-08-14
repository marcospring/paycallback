package com.cyou.paycallback.datesource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;

import com.cy.mrd.security.Secret;
import com.cyou.paycallback.define.Cache;

;

/**
 * 继承BasicDataSource
 * 
 * @author zhanghongchao
 * 
 */
public class BaseDataSource extends BasicDataSource {
	private static final Logger log = Logger.getLogger(BaseDataSource.class);

	/**
	 * 转换password，如果database_encrypt配置为true，则需要进行解密
	 */
	@Override
	public void setPassword(String password) {
		boolean databaseEncrypt = Boolean.parseBoolean(Cache.CONFIG_MAP.get("datasource.encrypt"));
		if (databaseEncrypt) {
			try {
				password = Secret.decrypt(password);// 自己实现密码解密算法
			} catch (Exception e) {
				log.error("BasicServiceDataSource decrypt password<" + password + "> failed," + e.getMessage(), e);
				System.exit(-1);
				return;
			}
		}
		this.password = password;
	}
}
