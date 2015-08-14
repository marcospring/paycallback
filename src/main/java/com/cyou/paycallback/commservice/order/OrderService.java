package com.cyou.paycallback.commservice.order;

public class OrderService {

	public static Order getOrderById(String orderId) {
		return OrderDao.getOrderById(orderId);
	}
}
