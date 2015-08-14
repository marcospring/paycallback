package com.cyou.paycallback.define;

public class ChannelEntry {

	private String requestCharset;
	// 必传参数列表
	private String[] mustParam;
	// 参与加密参数列表
	private String[] secretParam;
	// 签名参数名
	private String signParam;
	// 透传字段名称
	private String passThroughName;
	// 订单号参数名
	private String orderIdName;
	// 订单金额参数名
	private String amountName;
	// 第三方订单号参数名
	private String thirdOrderIdName;
	// 渠道号
	private String chnnelID;

	private String channelPayWayMapper;

	public String[] getMustParam() {
		return mustParam;
	}

	public void setMustParam(String[] mustParam) {
		this.mustParam = mustParam;
	}

	public String[] getSecretParam() {
		return secretParam;
	}

	public void setSecretParam(String[] secretParam) {
		this.secretParam = secretParam;
	}

	public String getSignParam() {
		return signParam;
	}

	public void setSignParam(String signParam) {
		this.signParam = signParam;
	}

	public String getRequestCharset() {
		return requestCharset;
	}

	public void setRequestCharset(String requestCharset) {
		this.requestCharset = requestCharset;
	}

	public String getPassThroughName() {
		return passThroughName;
	}

	public void setPassThroughName(String passThroughName) {
		this.passThroughName = passThroughName;
	}

	public String getChnnelID() {
		return chnnelID;
	}

	public void setChnnelID(String chnnelID) {
		this.chnnelID = chnnelID;
	}

	public String getChannelPayWayMapper() {
		return channelPayWayMapper;
	}

	public void setChannelPayWayMapper(String channelPayWayMapper) {
		this.channelPayWayMapper = channelPayWayMapper;
	}

	public String getOrderIdName() {
		return orderIdName;
	}

	public void setOrderIdName(String orderIdName) {
		this.orderIdName = orderIdName;
	}

	public String getAmountName() {
		return amountName;
	}

	public void setAmountName(String amountName) {
		this.amountName = amountName;
	}

	public String getThirdOrderIdName() {
		return thirdOrderIdName;
	}

	public void setThirdOrderIdName(String thirdOrderIdName) {
		this.thirdOrderIdName = thirdOrderIdName;
	}

}
