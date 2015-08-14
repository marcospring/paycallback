package com.cyou.paycallback.datesource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cyou.paycallback.define.Cache;

public class JdbcDao {

	static private BaseDataSource billingBaseDataSource = null;
	static private BaseDataSource orderBaseDataSource = null;

	/**
	 * 根据数据库名称获取数据库连接
	 * 
	 * @param dbname
	 * @return
	 * @return
	 * @throws SQLException
	 */
	public static void initBillingDataSource() {
		if (billingBaseDataSource == null) {
			synchronized (BaseDataSource.class) {
				if (billingBaseDataSource == null) {
					billingBaseDataSource = new BaseDataSource();
					billingBaseDataSource.setDriverClassName("com.mysql.jdbc.Driver");
					billingBaseDataSource.setPassword(Cache.CONFIG_MAP.get("service.billing.password"));
					billingBaseDataSource.setUsername(Cache.CONFIG_MAP.get("service.billing.username"));
					billingBaseDataSource.setUrl(Cache.CONFIG_MAP.get("service.billing.url"));
					billingBaseDataSource.setMaxActive(Integer.parseInt(Cache.CONFIG_MAP.get("service.datasource.maxActive")));
					billingBaseDataSource.setDefaultAutoCommit(Boolean.parseBoolean(Cache.CONFIG_MAP.get("service.datasource.defaultAutoCommit")));
					billingBaseDataSource.setInitialSize(Integer.parseInt(Cache.CONFIG_MAP.get("service.datasource.initialSize")));
					billingBaseDataSource.setMaxIdle(Integer.parseInt(Cache.CONFIG_MAP.get("service.datasource.maxIdle")));
					billingBaseDataSource.setMinIdle(Integer.parseInt(Cache.CONFIG_MAP.get("service.datasource.minIdle")));
					billingBaseDataSource.setMaxWait(Long.parseLong(Cache.CONFIG_MAP.get("service.datasource.maxWait")));
					billingBaseDataSource.setTestWhileIdle(Boolean.parseBoolean(Cache.CONFIG_MAP.get("service.datasource.testWhileIdle")));
					billingBaseDataSource.setTestOnBorrow(Boolean.parseBoolean(Cache.CONFIG_MAP.get("service.datasource.testOnBorrow")));
					billingBaseDataSource.setTestOnReturn(Boolean.parseBoolean(Cache.CONFIG_MAP.get("service.datasource.testOnReturn")));
					billingBaseDataSource.setValidationQuery(Cache.CONFIG_MAP.get("service.datasource.validationQuery"));
					billingBaseDataSource.setTimeBetweenEvictionRunsMillis(Long.parseLong(Cache.CONFIG_MAP.get("service.datasource.timeBetweenEvictionRunsMillis")));
					billingBaseDataSource.setMinEvictableIdleTimeMillis(Long.parseLong(Cache.CONFIG_MAP.get("service.datasource.minEvictableIdleTimeMillis")));
					billingBaseDataSource.setLogAbandoned(Boolean.parseBoolean(Cache.CONFIG_MAP.get("service.datasource.logAbandoned")));
					billingBaseDataSource.setRemoveAbandoned(Boolean.parseBoolean(Cache.CONFIG_MAP.get("service.datasource.removeAbandoned")));
					billingBaseDataSource.setRemoveAbandonedTimeout(Integer.parseInt(Cache.CONFIG_MAP.get("service.datasource.removeAbandonedTimeout")));
				}
			}

		}
	}

	public static void initOrderDataSource() {
		if (orderBaseDataSource == null) {
			synchronized (BaseDataSource.class) {
				if (orderBaseDataSource == null) {
					orderBaseDataSource = new BaseDataSource();
					orderBaseDataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
					orderBaseDataSource.setPassword(Cache.CONFIG_MAP.get("service.order.password"));
					orderBaseDataSource.setUsername(Cache.CONFIG_MAP.get("service.order.username"));
					orderBaseDataSource.setUrl(Cache.CONFIG_MAP.get("service.order.url"));
					orderBaseDataSource.setMaxActive(Integer.parseInt(Cache.CONFIG_MAP.get("service.datasource.maxActive")));
					orderBaseDataSource.setDefaultAutoCommit(Boolean.parseBoolean(Cache.CONFIG_MAP.get("service.datasource.defaultAutoCommit")));
					orderBaseDataSource.setInitialSize(Integer.parseInt(Cache.CONFIG_MAP.get("service.datasource.initialSize")));
					orderBaseDataSource.setMaxIdle(Integer.parseInt(Cache.CONFIG_MAP.get("service.datasource.maxIdle")));
					orderBaseDataSource.setMinIdle(Integer.parseInt(Cache.CONFIG_MAP.get("service.datasource.minIdle")));
					orderBaseDataSource.setMaxWait(Long.parseLong(Cache.CONFIG_MAP.get("service.datasource.maxWait")));
					orderBaseDataSource.setTestWhileIdle(Boolean.parseBoolean(Cache.CONFIG_MAP.get("service.datasource.testWhileIdle")));
					orderBaseDataSource.setTestOnBorrow(Boolean.parseBoolean(Cache.CONFIG_MAP.get("service.datasource.testOnBorrow")));
					orderBaseDataSource.setTestOnReturn(Boolean.parseBoolean(Cache.CONFIG_MAP.get("service.datasource.testOnReturn")));
					orderBaseDataSource.setValidationQuery(Cache.CONFIG_MAP.get("service.datasource.validationQuery"));
					orderBaseDataSource.setTimeBetweenEvictionRunsMillis(Long.parseLong(Cache.CONFIG_MAP.get("service.datasource.timeBetweenEvictionRunsMillis")));
					orderBaseDataSource.setMinEvictableIdleTimeMillis(Long.parseLong(Cache.CONFIG_MAP.get("service.datasource.minEvictableIdleTimeMillis")));
					orderBaseDataSource.setLogAbandoned(Boolean.parseBoolean(Cache.CONFIG_MAP.get("service.datasource.logAbandoned")));
					orderBaseDataSource.setRemoveAbandoned(Boolean.parseBoolean(Cache.CONFIG_MAP.get("service.datasource.removeAbandoned")));
					orderBaseDataSource.setRemoveAbandonedTimeout(Integer.parseInt(Cache.CONFIG_MAP.get("service.datasource.removeAbandonedTimeout")));
				}
			}
		}

	}

	protected static Connection getConnection(String dataSourceName) throws SQLException {
		if (dataSourceName.equals("billing")) {
			initBillingDataSource();
			return billingBaseDataSource.getConnection();
		} else if (dataSourceName.equals("order")) {
			initOrderDataSource();
			return orderBaseDataSource.getConnection();
		}
		return null;
	}

	/**
	 * 关闭数据库连接
	 * 
	 * @param connection
	 *            可为null
	 * @param ps
	 *            可为null
	 * @param rs
	 *            可为null
	 */
	protected static void closeConnection(Connection connection, PreparedStatement ps, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
