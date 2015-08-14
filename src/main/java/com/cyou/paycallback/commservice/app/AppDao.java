package com.cyou.paycallback.commservice.app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.cyou.paycallback.commservice.order.OrderDao;
import com.cyou.paycallback.datesource.JdbcDao;

public class AppDao extends JdbcDao {

	private static final Logger log = Logger.getLogger(OrderDao.class);

	public static App getAppById(int appId) {
		App app = new App();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from tb_app where ID=? and ISSHOW=?";
		try {
			conn = getConnection("billing");
			conn.setAutoCommit(true);
			ps = conn.prepareStatement(sql);

			ps.setInt(1, appId);
			ps.setBoolean(2, true);
			rs = ps.executeQuery();
			if (rs.next()) { // 价钱 注册id 商品id 商品数量
				app.setId(rs.getInt("ID"));
				app.setAppkey(rs.getString("APPKEY"));
				app.setAppname(rs.getString("APPNAME"));
				app.setAppsecret(rs.getString("APPSECRET"));
				app.setCallbackUrl(rs.getString("CALLBACK_URL"));
				app.setCreateDate(rs.getDate("CREATE_DATE"));
				app.setFlag(rs.getBoolean("FLAG"));
				app.setGameType(rs.getString("GAME_TYPE"));
				app.setIappayId(rs.getString("IAPPAY_ID"));
				app.setIplist(rs.getString("IPLIST"));
				app.setActivate(rs.getBoolean("ISACTIVATE"));
				app.setGM(rs.getBoolean("ISGM"));
				app.setShow(rs.getBoolean("ISSHOW"));
				app.setTestVersion("TEST_VERSION");
				app.setFirstcharge(rs.getInt("firstcharge"));
				return app;
			}
		} catch (SQLException e) {
			log.error("get app by id error,", e);
			e.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					log.error("get app by id error,close connection error", e);
				}
			}
		} finally {
			closeConnection(conn, ps, rs);
		}
		return app;
	}

}
