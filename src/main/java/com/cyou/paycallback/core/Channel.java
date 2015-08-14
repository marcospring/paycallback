package com.cyou.paycallback.core;

public interface Channel {
	PayCallBackHandler getChannel(String version);

	void addVersion(String version, PayCallBackHandler handler);
}
