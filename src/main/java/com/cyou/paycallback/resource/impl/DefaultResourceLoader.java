package com.cyou.paycallback.resource.impl;

import java.net.MalformedURLException;
import java.net.URL;

import com.cyou.paycallback.resource.Resource;
import com.cyou.paycallback.resource.ResourceLoader;
import com.cyou.paycallback.util.StringUtils;

public class DefaultResourceLoader implements ResourceLoader {

	@Override
	public Resource getResource(String path) {
		ResourceEnum kind = getResourceEnum(path);
		switch (kind) {
		case CLASS_PATH:
			return new ClassPathResource(path);
		default:
			return null;
		}
	}

	private ResourceEnum getResourceEnum(String path) {
		if (StringUtils.isEmpty(path))
			throw new NullPointerException("path si null");
		if (path.contains("classpath:")) {
			return ResourceEnum.CLASS_PATH;
		} else {
			try {
				new URL(path);
				return ResourceEnum.URL_PATH;
			} catch (MalformedURLException e) {
				return ResourceEnum.CLASS_PATH;
			}
		}
	}

}
