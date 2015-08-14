package com.cyou.paycallback.commservice.order;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {
	/**
	 * @Fields serialVersionUID : Description
	 */

	private static final long serialVersionUID = 1L;
	// 订单编号
	private String orderId;
	// 游戏ID
	private int appId;
	// 渠道ID
	private int channelId;
	// 用户唯一标识,用于唯一标识用户
	private String userid;
	// 角色ID
	private String roleId = " "; // oracle 空串插入时会报出插入NULL值失败，所以这里为空时 默认为空格
	// 角色名称
	private String roleName = " ";
	// 商品唯一ID 20150516
	private int goodsId;
	// 商品注册ID
	private String goodsRegisterId;
	// 商品名称
	private String goodsName;
	// 产品数量（购买货币数量）
	private int goodsNumber;
	// 产品价格（RMB）
	private double goodsPrice;
	// 游戏分区ID 20150516
	private String groupId = " ";
	// 游戏分区名称 20150516
	private String groupName = " ";
	// 主显账号 20150516
	private String account;
	// 游戏或第三方自定义域 20150516
	private String pushInfo = " ";
	private int isDelete = 0;
	private int isSandbox = 0;
	private Date updateDate;
	private int payWay = 0;
	private String expand1 = " ";
	private String expand2 = " ";
	private String expand3 = " ";
	private int pushNum = 0;
	private int pushState = 0;
	private String clientVersion = " ";// 客户端版本号20140617by Li Jiuchao
	private String mediaChannelId = " "; // 推广渠道20140811by Li Jiuchao
	// 创建时间 20150516
	private Date createDate;
	// 0：初始；1：等待支付；2：充值成功；3：处理中；4：已付款；5：充值失效 6：易宝支付（付款失败）
	private int orderStatus = 0;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public int getAppId() {
		return appId;
	}

	public void setAppId(int appId) {
		this.appId = appId;
	}

	public int getChannelId() {
		return channelId;
	}

	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public int getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsRegisterId() {
		return goodsRegisterId;
	}

	public void setGoodsRegisterId(String goodsRegisterId) {
		this.goodsRegisterId = goodsRegisterId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public int getGoodsNumber() {
		return goodsNumber;
	}

	public void setGoodsNumber(int goodsNumber) {
		this.goodsNumber = goodsNumber;
	}

	public double getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPushInfo() {
		return pushInfo;
	}

	public void setPushInfo(String pushInfo) {
		this.pushInfo = pushInfo;
	}

	public int getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}

	public int getIsSandbox() {
		return isSandbox;
	}

	public void setIsSandbox(int isSandbox) {
		this.isSandbox = isSandbox;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public int getPayWay() {
		return payWay;
	}

	public void setPayWay(int payWay) {
		this.payWay = payWay;
	}

	public String getExpand1() {
		return expand1;
	}

	public void setExpand1(String expand1) {
		this.expand1 = expand1;
	}

	public String getExpand2() {
		return expand2;
	}

	public void setExpand2(String expand2) {
		this.expand2 = expand2;
	}

	public String getExpand3() {
		return expand3;
	}

	public void setExpand3(String expand3) {
		this.expand3 = expand3;
	}

	public int getPushNum() {
		return pushNum;
	}

	public void setPushNum(int pushNum) {
		this.pushNum = pushNum;
	}

	public int getPushState() {
		return pushState;
	}

	public void setPushState(int pushState) {
		this.pushState = pushState;
	}

	public String getClientVersion() {
		return clientVersion;
	}

	public void setClientVersion(String clientVersion) {
		this.clientVersion = clientVersion;
	}

	public String getMediaChannelId() {
		return mediaChannelId;
	}

	public void setMediaChannelId(String mediaChannelId) {
		this.mediaChannelId = mediaChannelId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
