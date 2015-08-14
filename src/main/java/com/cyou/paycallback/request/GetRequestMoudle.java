package com.cyou.paycallback.request;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.cyou.paycallback.util.StringUtils;

public class GetRequestMoudle implements RequestModule {

	@Override
	public Map<String, String> getParamsMap(HttpServletRequest request, String charset) {
		Map<String, String> params = new HashMap<String, String>();
		String requestStr = request.getQueryString();
		if (StringUtils.isEmpty(requestStr)) {
			System.out.println("get请求，请求参数为空！");
		} else {
			try {
				if (!StringUtils.isEmpty(charset)) {
					requestStr = URLDecoder.decode(requestStr, charset);
				}
				String[] parameters = requestStr.split("&");
				for (String p : parameters) {
					if (p.isEmpty()) {
						continue;
					}
					int pos = p.indexOf('=');
					if (pos == -1 || pos == 0 || pos + 1 == p.length()) {
						throw new IllegalArgumentException("找不到键： " + p + "的值， 在 " + requestStr);
					}
					String[] kv = p.split("=");
					if (params.get(kv[0]) != null) {
						throw new IllegalArgumentException("含有相同的参数 " + kv[0] + " 在 " + requestStr);
					}
					params.put(kv[0], kv[1]);
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				System.out.println("get请求，请求参数解码失败！");
			}
		}
		return params;
	}

}
