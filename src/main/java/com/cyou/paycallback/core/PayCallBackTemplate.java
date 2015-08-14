package com.cyou.paycallback.core;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.cyou.paycallback.define.AppKeyConfig;

public interface PayCallBackTemplate extends PayCallBackHandler {
	Map<String, String> getRequestParams(HttpServletRequest request, String charset);

	Map<String, String> passThroughStr(String passThrough);

	String getSecretString(Map<String, String> params, AppKeyConfig appConfig);

	String getSign(String secretString, String sign);

	String setResult(Map<String, String> paramMap, boolean flag);

}
