package com.cyou.paycallback.result;

import java.util.Map;

public interface ResultModule {
	String getResult(Map<String, String> paramMap, boolean flag);
}
