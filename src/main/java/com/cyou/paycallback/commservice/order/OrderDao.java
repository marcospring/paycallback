package com.cyou.paycallback.commservice.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.cyou.paycallback.datesource.JdbcDao;

public class OrderDao extends JdbcDao {
	private static final Logger log = Logger.getLogger(OrderDao.class);

	public static Order getOrderById(String orderId) {
		Order order = null;
		Connection connection = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sql = "select APP_ID,GOODS_ID,ORDER_ID,ORDER_STATUS,GOODS_REGISTER_ID,GOODS_NUMBER,GROUP_ID,GOODS_PRICE,USER_ID,ROLE_ID,ROLE_NAME,CHANNEL_ID,PUSH_NUM,PUSH_INFO from TB_ORDER where ORDER_ID=?";
		try {
			connection = getConnection("order");
			connection.setAutoCommit(true);
			ps = connection.prepareStatement(sql);
			ps.setString(1, orderId);
			rs = ps.executeQuery();
			if (rs.next()) {
				order = new Order();
				order.setAppId(rs.getInt("APP_ID"));
				order.setGoodsId(rs.getInt("GOODS_ID"));
				order.setOrderId(rs.getString("ORDER_ID"));
				order.setOrderStatus(rs.getInt("ORDER_STATUS"));
				order.setGoodsRegisterId(rs.getString("GOODS_REGISTER_ID"));
				order.setGoodsNumber(rs.getInt("GOODS_NUMBER"));
				order.setGoodsPrice(rs.getDouble("GOODS_PRICE"));
				order.setUserid(rs.getString("USER_ID"));
				order.setRoleId(rs.getString("ROLE_ID"));
				order.setRoleName(rs.getString("ROLE_NAME"));
				order.setChannelId(rs.getInt("CHANNEL_ID"));
				order.setPushNum(rs.getInt("PUSH_NUM"));
				order.setPushInfo(rs.getString("PUSH_INFO"));
				order.setGroupId(rs.getString("GROUP_ID"));
				return order;
			}
		} catch (SQLException e) {
			log.error("select Orderorder by APP_ID,ORDER_ID,ORDER_STATUS,GOODS_REGISTER_ID,GOODS_NUMBER,GOODS_PRICE,USER_ID,ROLE_ID,ROLE_NAME,CHANNEL_ID error", e);
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					log.error("select Orderorder by APP_ID,ORDER_ID,ORDER_STATUS,GOODS_REGISTER_ID,GOODS_NUMBER,GOODS_PRICE,USER_ID,ROLE_ID,ROLE_NAME,CHANNEL_ID error,close connection error", e1);
				}
			}
		} finally {
			closeConnection(connection, ps, rs);
		}
		return order;
	}
}
