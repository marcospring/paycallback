
package com.cyou.paycallback.define;



/**
 * AppKeyConfig entity. @author MyEclipse Persistence Tools
 */

public class AppKeyConfig  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
    private Integer channelId;
    private String channelName;
    private Integer billAppId;
    private String otherAppId;
    private String appName;
    private String otherAppsecret;
    private String expandOne;
    private String expandTwo;
    private String content;


    // Constructors

    /** default constructor */
    public AppKeyConfig() {
    }

	/** minimal constructor */
    public AppKeyConfig(Integer channelId, Integer billAppId, String otherAppId, String otherAppsecret,
            String expandOne, String expandTwo, String content) {
        this.channelId = channelId;
        this.billAppId = billAppId;
        this.otherAppId = otherAppId;
        this.otherAppsecret = otherAppsecret;
        this.expandOne = expandOne;
        this.expandTwo = expandTwo;
        this.content = content;
    }
    
    /** full constructor */
    public AppKeyConfig(Integer channelId, String channelName, Integer billAppId, String otherAppId, String appName,
            String otherAppsecret, String expandOne, String expandTwo, String content) {
        this.channelId = channelId;
        this.channelName = channelName;
        this.billAppId = billAppId;
        this.otherAppId = otherAppId;
        this.appName = appName;
        this.otherAppsecret = otherAppsecret;
        this.expandOne = expandOne;
        this.expandTwo = expandTwo;
        this.content = content;
    }

   
    // Property accessors

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getExpandOne() {
        return expandOne;
    }

    public void setExpandOne(String expandOne) {
        this.expandOne = expandOne;
    }

    public String getExpandTwo() {
        return expandTwo;
    }

    public void setExpandTwo(String expandTwo) {
        this.expandTwo = expandTwo;
    }

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getChannelId() {
        return this.channelId;
    }
    
    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return this.channelName;
    }
    
    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public Integer getBillAppId() {
        return this.billAppId;
    }
    
    public void setBillAppId(Integer billAppId) {
        this.billAppId = billAppId;
    }

    public String getOtherAppId() {
        return this.otherAppId;
    }
    
    public void setOtherAppId(String otherAppId) {
        this.otherAppId = otherAppId;
    }

    public String getAppName() {
        return this.appName;
    }
    
    public void setAppName(String appName) {
        this.appName = appName;
    }


    public String getOtherAppsecret() {
        return this.otherAppsecret;
    }
    
    public void setOtherAppsecret(String otherAppsecret) {
        this.otherAppsecret = otherAppsecret;
    }

	@Override
	public String toString() {
		return "AppKeyConfig [id=" + id + ", channelId=" + channelId + ", channelName=" + channelName + ", billAppId=" + billAppId + ", otherAppId="
				+ otherAppId + ", appName=" + appName + ", otherAppsecret=" + otherAppsecret + ", expandOne=" + expandOne + ", expandTwo="
				+ expandTwo + ", content=" + content + "]";
	}

}