package com.cyou.paycallback.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.cyou.paycallback.channel.AlitvCallbackHandler;
import com.cyou.paycallback.request.GetRequestMoudle;

public class Test {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		try {
			Class clazz = loader.loadClass("com.cyou.paycallback.channel.AlitvCallbackHandler");
			Object h = clazz.newInstance();
			Method[] methods = clazz.getMethods();
			for (Method method : methods) {
				if ("setRequestModule".equals(method.getName())) {
					method.invoke(h, new GetRequestMoudle());
				}
			}
			AlitvCallbackHandler hh = (AlitvCallbackHandler) h;
			System.out.println(hh.getRequestModule());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// DefaultResourceLoader loader = new DefaultResourceLoader();
		// Resource resource = loader.getResource("classpath:config");
		// List<File> files = resource.getFile();
		// SAXReader reader = new SAXReader();
		// for (File file : files) {
		// try {
		// Document document = reader.read(file);
		// Element root = document.getRootElement();
		// System.out.println("root>>>>>:" + root.getName());
		// List<Element> channels = root.elements();
		// for (Element channel : channels) {
		// System.out.println("name>>>>>>:" + channel.attributeValue("name"));
		// System.out.println("class>>>>>>:" + channel.attributeValue("class"));
		// List<Element> versions = channel.elements();
		// for (Element version : versions) {
		// System.out.println("version name>>>>>>>>>:" +
		// version.attributeValue("name"));
		// System.out.println("RequestModule>>>>>>>>:" +
		// version.elementText("RequestModule"));
		// System.out.println("SecretStringModule>>>>>>>>:" +
		// version.elementText("SecretStringModule"));
		// // System.out.println("SecretModule>>>>>>>>:" +
		// // version.elementText("SecretModule"));
		// System.out.println("ResultModule>>>>>>>>:" +
		// version.elementText("ResultModule"));
		// Element entry = version.element("ChannelEntry");
		// System.out.println("RequestCharset>>>>>>>>:" +
		// entry.elementText("RequestCharset"));
		// System.out.println("MustParam>>>>>>>>:" +
		// entry.elementText("MustParam"));
		// System.out.println("SecretParam>>>>>>>>:" +
		// entry.elementText("SecretParam"));
		// System.out.println("SignParam>>>>>>>>:" +
		// entry.elementText("SignParam"));
		// System.out.println("PassThroughName>>>>>>>>:" +
		// entry.elementText("PassThroughName"));
		// System.out.println("OrderIdName>>>>>>>>:" +
		// entry.elementText("OrderIdName"));
		// System.out.println("AmountName>>>>>>>>:" +
		// entry.elementText("AmountName"));
		// System.out.println("ThirdOrderIdName>>>>>>>>:" +
		// entry.elementText("ThirdOrderIdName"));
		// System.out.println("ChnnelID>>>>>>>>:" +
		// entry.elementText("ChnnelID"));
		// System.out.println("ChannelPayWayMapper>>>>>>>>:" +
		// entry.elementText("ChannelPayWayMapper"));
		// }
		// }
		// } catch (DocumentException e) {
		// e.printStackTrace();
		// }
		// }
		catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
