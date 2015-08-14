package com.cyou.paycallback.define;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局缓存 放置各渠道配置文件信息
 */
public class Cache {

	public static Map<String, AppKeyConfig> appConfigMap = new HashMap<String, AppKeyConfig>();

	public static Map<String, String> CONFIG_MAP = new HashMap<String, String>();// config配置文件

}
