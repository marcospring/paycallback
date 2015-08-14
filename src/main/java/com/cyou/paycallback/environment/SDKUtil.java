package com.cyou.paycallback.environment;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.cyou.paycallback.commservice.app.App;
import com.cyou.paycallback.commservice.app.AppService;
import com.cyou.paycallback.commservice.order.Order;
import com.cyou.paycallback.commservice.order.OrderService;
import com.cyou.paycallback.define.Cache;
import com.cyou.paycallback.define.ChannelEnum;
import com.cyou.paycallback.util.ConvertUtils;
import com.cyou.paycallback.util.HttpUtil;
import com.cyou.paycallback.util.SignUtil;
import com.google.gson.Gson;

/**
 * 新版SDK工具类
 * 
 * @author shendexuan
 */
public class SDKUtil {
	private static final Logger log = Logger.getLogger(SDKUtil.class);

	/**
	 * @param orderId_cy
	 *            自定义生成订单号 必填
	 * @param orderId_sdk
	 *            第三方平台订单号 非必填
	 * @param goodsPrice
	 *            商品价格单位元 必填
	 * @param logName
	 *            日志分类名称代表各个渠道标识 非必填,易宝，一卡通为必填
	 * @return true 请求成功 false请求失败
	 */
	public static boolean requestBilling(String orderId_cy, String orderId_sdk, String goodsPrice, String logName, String channelId) {
		return requestBilling(orderId_cy, orderId_sdk, goodsPrice, logName, true, channelId);
	}

	public static boolean requestBilling(String orderId_cy, String orderId_sdk, String goodsPrice, String logName, String channelId, String miTicket) {
		return requestBilling(orderId_cy, orderId_sdk, goodsPrice, logName, true, channelId, miTicket);
	}

	public static boolean requestBilling(String orderId_cy, String orderId_sdk, String goodsPrice, String logName, boolean payResult, String channelId) {
		boolean isSuccess = true;

		Map<Object, Object> sendMap = new HashMap<Object, Object>();
		sendMap.put("orderIdCy", orderId_cy); // 自定义订单号
		sendMap.put("orderId_sdk", orderId_sdk); // 第三方渠道订单号
		sendMap.put("amount", goodsPrice); // 商品价格
		sendMap.put("thirdPayChannel", logName); // 易宝、一卡通传递 第三方渠道米名称
		sendMap.put("payResult", payResult); // 易宝支付结果 true标识成功 false失败
		long tag = Math.round(Math.random() * 8999 + 1000); // 事务标识
		String appkey = Cache.CONFIG_MAP.get("appkey"); // 向billing请求时有权限的appkey
		String appsecret = Cache.CONFIG_MAP.get("appsecret"); // 向billing请求时有权限的appsecret
		int opcode = 3002;

		if (StringUtils.isEmpty(orderId_cy) || StringUtils.isEmpty(goodsPrice)) {
			isSuccess = false;
			log.error(MessageFormat.format("[" + logName + "]INFO[REQUESTBILLING]ERROR[{0}]ISSUCCESS[{1}]", "parameter is error have null", isSuccess));
		} else {
			String appkey3022 = "";
			String url = "";
			if (orderId_cy.startsWith("debug")) {
				url = Cache.CONFIG_MAP.get("url_debug");
			} else if (orderId_cy.startsWith("prep")) {
				url = Cache.CONFIG_MAP.get("url_prepare");
			} else {
				url = Cache.CONFIG_MAP.get("url_release");
				Order order = OrderService.getOrderById(orderId_cy);
				if (order != null) {
					App app = AppService.getAppById(order.getAppId());
					if (app != null) {
						appkey3022 = app.getAppkey();
						appkey = app.getAppkey();
						appsecret = app.getAppsecret();
					}
				} else {
					log.error(MessageFormat.format("[" + logName + "]INFO[REQUESTBILLING]ERROR[{0}]", "order is not exist"));
				}
			}

			String param = new Gson().toJson(sendMap);
			log.info(MessageFormat.format("[" + logName
					+ "]INFO[REQUESTBILLING]URL[{0}]APPKEY[{1}]APPSECRET[{2}]OPCODE[{3}]TAG[{4}]CHANNELID[{5}]CYORDERID[{6}]THRIDORDERID[{7}]PARAM[{8}]", url, appkey, appsecret,
					opcode, tag, channelId, orderId_cy, orderId_sdk, param));

			String signBilling = "";
			try {
				signBilling = SignUtil.createSign(opcode + param + appkey + appsecret + tag + channelId, true);
			} catch (Exception e) {
				isSuccess = false;
				log.error(MessageFormat.format("[" + logName + "]INFO[REQUESTBILLING]ERROR[{0}]", "createSign create fail!"));
			}
			log.info(MessageFormat.format("[" + logName + "]INFO[REQUESTBILLING]ORDERID_CY[{0}]ORDERID_SDK[{1}]GOODSPRICE[{2}]URL[{3}]", orderId_cy, orderId_sdk, goodsPrice, url));
			String returnstr = HttpUtil.SdkSend(url, appkey, String.valueOf(opcode), signBilling, String.valueOf(tag), channelId, appkey3022, param);
			isSuccess = check(returnstr, logName);
		}
		return isSuccess;
	}

	private static boolean check(String returnstr, String logName) {
		boolean isSuccess = true;
		if (StringUtils.isEmpty(returnstr)) {// 如果请求billing返回值为空
			isSuccess = false;
		} else {
			JSONObject jsonobjectBilling = JSONObject.fromObject(returnstr);
			if (jsonobjectBilling.has("data")) {
				String data = jsonobjectBilling.getString("data");// 游戏标识
				if ("0".equals(data) || "2".equals(data)) {
					isSuccess = true;
				} else {
					isSuccess = false;
				}
			} else {
				isSuccess = false;
			}
		}
		log.info(MessageFormat.format("[" + logName + "]INFO[REQUESTBILLING]BILLINRETURN[{0}]ISSUCCESS[{1}]", returnstr, isSuccess));
		return isSuccess;
	}

	/*
	 * 小米专用增加米币
	 */
	public static boolean requestBilling(String orderId_cy, String orderId_sdk, String goodsPrice, String logName, boolean payResult, String channelId, String miTicket) {
		boolean isSuccess = true;

		Map<Object, Object> sendMap = new HashMap<Object, Object>();
		sendMap.put("orderIdCy", orderId_cy); // 自定义订单号
		sendMap.put("orderId_sdk", orderId_sdk); // 第三方渠道订单号
		sendMap.put("amount", goodsPrice); // 商品价格
		sendMap.put("thirdPayChannel", logName); // 易宝、一卡通传递 第三方渠道米名称
		sendMap.put("payResult", payResult); // 易宝支付结果 true标识成功 false失败
		sendMap.put("miTicket", miTicket);
		long tag = Math.round(Math.random() * 8999 + 1000); // 事务标识
		String appkey = Cache.CONFIG_MAP.get("appkey"); // 向billing请求时有权限的appkey
		String appsecret = Cache.CONFIG_MAP.get("appsecret"); // 向billing请求时有权限的appsecret
		int opcode = 3002;

		if (StringUtils.isEmpty(orderId_cy) || StringUtils.isEmpty(goodsPrice)) {
			isSuccess = false;
			log.error(MessageFormat.format("[" + logName + "]INFO[REQUESTBILLING]ERROR[{0}]ISSUCCESS[{1}]", "parameter is error have null", isSuccess));
		} else {
			String appkey3022 = "";
			String url = "";
			if (orderId_cy.startsWith("debug")) {
				url = Cache.CONFIG_MAP.get("url_debug");
			} else if (orderId_cy.startsWith("prep")) {
				url = Cache.CONFIG_MAP.get("url_prepare");
			} else {
				url = Cache.CONFIG_MAP.get("url_release");
				Order order = OrderService.getOrderById(orderId_cy);
				if (order != null) {
					App app = AppService.getAppById(order.getAppId());
					if (app != null) {
						appkey3022 = app.getAppkey();
						appkey = app.getAppkey();
						appsecret = app.getAppsecret();
					}
				} else {
					log.error(MessageFormat.format("[" + logName + "]INFO[REQUESTBILLING]ERROR[{0}]", "order is not exist"));
				}
			}

			String param = new Gson().toJson(sendMap);
			log.info(MessageFormat.format("[" + logName
					+ "]INFO[REQUESTBILLING]URL[{0}]APPKEY[{1}]APPSECRET[{2}]OPCODE[{3}]TAG[{4}]CHANNELID[{5}]CYORDERID[{6}]THRIDORDERID[{7}]PARAM[{8}]", url, appkey, appsecret,
					opcode, tag, channelId, orderId_cy, orderId_sdk, param));

			String signBilling = "";
			try {
				signBilling = SignUtil.createSign(opcode + param + appkey + appsecret + tag + channelId, true);
			} catch (Exception e) {
				isSuccess = false;
				log.error(MessageFormat.format("[" + logName + "]INFO[REQUESTBILLING]ERROR[{0}]", "createSign create fail!"));
			}
			log.info(MessageFormat.format("[" + logName + "]INFO[REQUESTBILLING]ORDERID_CY[{0}]ORDERID_SDK[{1}]GOODSPRICE[{2}]URL[{3}]", orderId_cy, orderId_sdk, goodsPrice, url));
			String returnstr = HttpUtil.SdkSend(url, appkey, String.valueOf(opcode), signBilling, String.valueOf(tag), channelId, appkey3022, param);
			isSuccess = check(returnstr, logName);
		}
		return isSuccess;
	}

	/**
	 * 老billing回调
	 * 
	 * @param opcode
	 * @param channelId
	 * @param param
	 * @return
	 */
	public static boolean requestBilling(String opcode, int channelId, String param) {
		String result = "1";
		ChannelEnum logName = null;
		try {
			String url = Cache.CONFIG_MAP.get("url");
			String appkey = Cache.CONFIG_MAP.get("appkey");
			String appsecret = Cache.CONFIG_MAP.get("appsecret");
			String tag = Math.round(Math.random() * 2036 + 1000) + "";
			logName = ChannelEnum.valueOf(channelId);
			log.info(MessageFormat.format("[" + logName + "PayAction]INFO[REQUESTBILLING]URL[{0}]APPKEY[{1}]APPSECRET[{2}]OPCODE[{3}]TAG[{4}]CHANNELID[{5}]", url, appkey,
					appsecret, opcode, tag, channelId));
			String sign = null;
			try {
				sign = SignUtil.createSign(opcode + param + appkey + appsecret + tag + channelId, true);
			} catch (Exception e) {
				log.error(MessageFormat.format("[" + logName + "PayAction]ERROR[{0}]", ConvertUtils.getStackTrace(e)));
			}

			String data = HttpUtil.send(url, appkey, opcode, sign, tag, channelId + "", param);

			if (!StringUtils.isEmpty(data)) {
				result = JSONObject.fromObject(data).getString("data");
			}
		} catch (Exception e) {
			log.error(MessageFormat.format("[" + logName + "PayAction]REQUEST BILLING ERROR[{0}]", ConvertUtils.getStackTrace(e)));
		}
		return "0".equals(result) ? true : false;
	}

	public static boolean requestBillingForTencent(String cyorder, String yyborder, String payitem, String yybofficial, String valueOf) {
		boolean isSuccess = true;

		Map<Object, Object> sendMap = new HashMap<Object, Object>();
		sendMap.put("orderIdCy", cyorder); // 自定义订单号
		sendMap.put("orderId_sdk", yyborder); // 第三方渠道订单号
		sendMap.put("payitem", payitem); // 商品价格
		long tag = Math.round(Math.random() * 8999 + 1000); // 事务标识
		String appkey = Cache.CONFIG_MAP.get("appkey"); // 向billing请求时有权限的appkey
		String appsecret = Cache.CONFIG_MAP.get("appsecret"); // 向billing请求时有权限的appsecret
		int opcode = 8000;

		if (StringUtils.isEmpty(cyorder) || StringUtils.isEmpty(payitem)) {
			isSuccess = false;
			log.error(MessageFormat.format("[" + yybofficial + "]INFO[REQUESTBILLING]ERROR[{0}]ISSUCCESS[{1}]", "parameter is error have null", isSuccess));
		} else {
			String appkey3022 = "";
			String url = "";
			if (cyorder.startsWith("debug")) {
				url = Cache.CONFIG_MAP.get("url_debug");
			} else if (cyorder.startsWith("prep")) {
				url = Cache.CONFIG_MAP.get("url_prepare");
			} else {
				url = Cache.CONFIG_MAP.get("url_release");
				Order order = OrderService.getOrderById(cyorder);
				if (order != null) {
					App app = AppService.getAppById(order.getAppId());
					if (app != null) {
						appkey3022 = app.getAppkey();
						appkey = app.getAppkey();
						appsecret = app.getAppsecret();
					}
				} else {
					log.error(MessageFormat.format("[" + yybofficial + "]INFO[REQUESTBILLING]ERROR[{0}]", "order is not exist"));
				}
			}

			String param = new Gson().toJson(sendMap);
			log.info(MessageFormat.format("[" + yybofficial
					+ "]INFO[REQUESTBILLING]URL[{0}]APPKEY[{1}]APPSECRET[{2}]OPCODE[{3}]TAG[{4}]CHANNELID[{5}]CYORDERID[{6}]THRIDORDERID[{7}]PARAM[{8}]", url, appkey, appsecret,
					opcode, tag, valueOf, cyorder, yyborder, param));

			String signBilling = "";
			try {
				signBilling = SignUtil.createSign(opcode + param + appkey + appsecret + tag + valueOf, true);
			} catch (Exception e) {
				isSuccess = false;
				log.error(MessageFormat.format("[" + yybofficial + "]INFO[REQUESTBILLING]ERROR[{0}]", "createSign create fail!"));
			}
			log.info(MessageFormat.format("[" + yybofficial + "]INFO[REQUESTBILLING]ORDERID_CY[{0}]ORDERID_SDK[{1}]GOODSPRICE[{2}]URL[{3}]", cyorder, yyborder, payitem, url));
			String returnstr = HttpUtil.SdkSend(url, appkey, String.valueOf(opcode), signBilling, String.valueOf(tag), valueOf, appkey3022, param);
			isSuccess = check(returnstr, yybofficial);
		}
		return isSuccess;
	}

}
