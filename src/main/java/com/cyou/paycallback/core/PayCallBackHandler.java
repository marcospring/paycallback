package com.cyou.paycallback.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface PayCallBackHandler extends Channel {
	void execute(HttpServletRequest request, HttpServletResponse response);
}
