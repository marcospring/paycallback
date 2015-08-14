package com.cyou.paycallback.secretstring;

import java.util.Map;

import com.cyou.paycallback.define.AppKeyConfig;

public interface SecretStringModule {
	String getSecretString(Map<String, String> secretMap, AppKeyConfig appConfig);
}
