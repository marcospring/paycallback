package com.cyou.paycallback.channel;

import java.util.HashMap;
import java.util.Map;

import com.cyou.paycallback.core.DefaultPayCallBackHandler;
import com.cyou.paycallback.util.StringUtils;

public class AlitvCallbackHandler extends DefaultPayCallBackHandler {

	@Override
	public Map<String, String> passThroughStr(String passThrough) {
		Map<String, String> passThroughParam = new HashMap<String, String>();
		if (StringUtils.isEmpty(passThrough)) {
			throw new IllegalArgumentException("缺少透传参数");
		}
		String[] appkeyValues = passThrough.split("appkey");
		passThroughParam.put(APP_KEY, appkeyValues[1]);
		return passThroughParam;
	}

}
