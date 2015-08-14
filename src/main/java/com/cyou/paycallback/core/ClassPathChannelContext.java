package com.cyou.paycallback.core;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.cyou.paycallback.resource.Resource;
import com.cyou.paycallback.resource.impl.DefaultResourceLoader;

public class ClassPathChannelContext extends DefaultResourceLoader implements ChannelContext {

	private Logger logger = Logger.getLogger(ClassPathChannelContext.class);
	private Map<String, PayCallBackHandler> context = null;
	private Resource resource = null;

	public void init(String path) {
		logger.info("[初始化渠道容器开始...]");
		resource = getResource(path);
		context = new HashMap<String, PayCallBackHandler>();
	}

	public Map<String, PayCallBackHandler> readChannel() {
		Map<String, PayCallBackHandler> result = new HashMap<String, PayCallBackHandler>();
		List<File> configFiles = resource.getFile();
		for (File file : configFiles) {

		}
		// File file = resource.getFile();
		// XMLResource xml = XMLResource.getInstance();
		// Document document = xml.getDocument(file);
		// Element root = xml.getRoot(document);
		// NodeList channels = xml.getNodeList(root);
		// for (int i = 0; i < channels.getLength(); i++) {
		// Node channel = channels.item(i);
		// String className = xml.getAttributes(channel, "class");
		// String channelName = xml.getAttributes(channel, "name");
		// }
		return result;
	}

	public PayCallBackHandler getChannel() {
		return null;
	}

	@SuppressWarnings("unchecked")
	private Map<String, Map<String, PayCallBackHandler>> getHandler(File file) {
		SAXReader reader = new SAXReader();
		Map<String, Map<String, PayCallBackHandler>> result = new HashMap<String, Map<String, PayCallBackHandler>>();
		try {
			Document document = reader.read(file);
			Element root = document.getRootElement();
			List<Element> channels = root.elements();
			for (Element channel : channels) {
				Map<String, PayCallBackHandler> versions = new HashMap<String, PayCallBackHandler>();
				String channelName = channel.attributeValue("name");
				getVersions(channel, versions);
				result.put(channelName, versions);
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	private void getVersions(Element channel, Map<String, PayCallBackHandler> result) {
		String versionName = channel.attributeValue("name");
		String className = channel.attributeValue("class");
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		try {
			Class<PayCallBackHandler> handlerClass = (Class<PayCallBackHandler>) loader.loadClass(className);
			PayCallBackHandler handler = initHandler(channel, handlerClass);
			result.put(versionName, handler);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private PayCallBackHandler initHandler(Element channel, Class<PayCallBackHandler> handlerClass) {
		ClassLoader loader = handlerClass.getClassLoader();
		// Method method = handlerClass.getDeclaredMethod(name, parameterTypes)
		handlerClass.getMethods();

		return null;
	}
}
