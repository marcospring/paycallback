package com.cyou.paycallback.request;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface RequestModule {
	String GET = "get";
	String POST = "post";
	String STREAM = "stream";

	Map<String, String> getParamsMap(HttpServletRequest request, String charset);

}
