package com.cyou.paycallback.core;

import com.cyou.paycallback.resource.ResourceLoader;

public interface ChannelContext extends ResourceLoader {
	public PayCallBackHandler getChannel();
}
