package com.cyou.paycallback.secret;

public interface SecretModule {
	String getSign(String secretString, String sign);
}
