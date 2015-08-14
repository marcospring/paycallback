package com.cyou.paycallback.commservice.app;

import java.io.Serializable;
import java.util.Date;

public class App implements Serializable {
	/**
	 * @Fields serialVersionUID : Description
	 */

	private static final long serialVersionUID = 1L;
	private int id;// 应用ID
	private String appname;// 应用名称
	private String appkey;// 应用标识
	private String iappayId;
	private String appsecret;// 应用秘诀
	private String iplist;// 防火墙，IP列表
	private String testVersion; // 测试版本
	private Date createDate; // 应用创建日期
	private boolean flag; // 是否显示在充值页面 true 是 false否
	private boolean isShow = true;// 是否删除 true 是 false 否
	private String gameType;
	private String callbackUrl; // 网页充值回调的URL
	private boolean isActivate = false;
	private boolean isGM = false; // true 代表GM可以读取 false GM不能读取 默认false
	private int firstcharge = 0;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAppname() {
		return appname;
	}

	public void setAppname(String appname) {
		this.appname = appname;
	}

	public String getAppkey() {
		return appkey;
	}

	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}

	public String getIappayId() {
		return iappayId;
	}

	public void setIappayId(String iappayId) {
		this.iappayId = iappayId;
	}

	public String getAppsecret() {
		return appsecret;
	}

	public void setAppsecret(String appsecret) {
		this.appsecret = appsecret;
	}

	public String getIplist() {
		return iplist;
	}

	public void setIplist(String iplist) {
		this.iplist = iplist;
	}

	public String getTestVersion() {
		return testVersion;
	}

	public void setTestVersion(String testVersion) {
		this.testVersion = testVersion;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public boolean isShow() {
		return isShow;
	}

	public void setShow(boolean isShow) {
		this.isShow = isShow;
	}

	public String getGameType() {
		return gameType;
	}

	public void setGameType(String gameType) {
		this.gameType = gameType;
	}

	public String getCallbackUrl() {
		return callbackUrl;
	}

	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}

	public boolean isActivate() {
		return isActivate;
	}

	public void setActivate(boolean isActivate) {
		this.isActivate = isActivate;
	}

	public boolean isGM() {
		return isGM;
	}

	public void setGM(boolean isGM) {
		this.isGM = isGM;
	}

	public int getFirstcharge() {
		return firstcharge;
	}

	public void setFirstcharge(int firstcharge) {
		this.firstcharge = firstcharge;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
