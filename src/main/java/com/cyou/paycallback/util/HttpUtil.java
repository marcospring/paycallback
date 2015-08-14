package com.cyou.paycallback.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.MessageFormat;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 * Http工具类
 * 
 * @author zhanghongliang
 * 
 */
public class HttpUtil {
	private static final Logger log = Logger.getLogger(HttpUtil.class);

	/**
	 * 发送Http请求(POST)
	 * 
	 * @param url
	 *            请求地址
	 * @return
	 */
	public static String send(String url, String param) {
		try {
			URL httpUrl = new URL(url);
			HttpURLConnection http = (HttpURLConnection) httpUrl.openConnection();
			http.setConnectTimeout(5000);
			http.setRequestMethod("POST");
			http.setDoInput(true);
			http.setDoOutput(true);
			PrintWriter out = new PrintWriter(http.getOutputStream());
			out.print(param);
			out.flush();
			out.close();
			StringBuffer result = new StringBuffer();
			InputStream is = http.getInputStream();
			InputStreamReader read = new InputStreamReader(is, "UTF-8");
			BufferedReader reader = new BufferedReader(read);
			String code;
			while ((code = reader.readLine()) != null) {
				result.append(code);
			}
			reader.close();
			read.close();
			is.close();
			return result.toString();
		} catch (Exception e) {
			return e.toString();
		}
	}

	public static String send(String url, String appkey, String opcode, String sign, String tag, String channelId, String param) {
		HttpURLConnection http = null;
		try {
			URL httpUrl = new URL(url);
			http = (HttpURLConnection) httpUrl.openConnection();
			http.setRequestProperty("opcode", String.valueOf(opcode));
			http.setRequestProperty("appkey", appkey);
			http.setRequestProperty("appkey3022", appkey);
			http.setRequestProperty("sign", sign);
			http.setRequestProperty("tag", tag);
			http.setRequestProperty("channelId", channelId);
			http.setConnectTimeout(20000);
			http.setReadTimeout(20000);
			http.setRequestMethod("POST");
			http.setDoInput(true);
			http.setDoOutput(true);
			PrintWriter out = new PrintWriter(http.getOutputStream());
			out.print("data=" + param); // 注意data传递方式
			out.flush();
			out.close();
			StringBuffer result = new StringBuffer();
			InputStream is = http.getInputStream();
			InputStreamReader read = new InputStreamReader(is, "UTF-8");
			BufferedReader reader = new BufferedReader(read);
			String code;
			while ((code = reader.readLine()) != null) {
				result.append(code);
			}
			reader.close();
			read.close();
			is.close();
			return result.toString();
		} catch (Exception e) {
			log.info(MessageFormat.format("[HTTPREQUEST]ERRORINFO[{0}]", e.getMessage()));
			return "-1";
		} finally {
			if (http != null)
				http.disconnect();
		}
	}

	public static String SdkSend(String url, String appkey, String opcode, String sign, String tag, String channelId, String appkey3022, String param) {
		HttpURLConnection http = null;
		try {
			URL httpUrl = new URL(url);
			http = (HttpURLConnection) httpUrl.openConnection();
			http.setRequestProperty("opcode", String.valueOf(opcode));
			http.setRequestProperty("appkey", appkey);
			http.setRequestProperty("sign", sign);
			http.setRequestProperty("tag", tag);
			http.setRequestProperty("channelId", channelId);
			http.setRequestProperty("appkey3022", appkey3022);
			http.setConnectTimeout(20000);
			http.setRequestMethod("POST");
			http.setDoInput(true);
			http.setDoOutput(true);
			PrintWriter out = new PrintWriter(http.getOutputStream());
			out.print("data=" + param); // 注意data传递方式
			out.flush();
			out.close();
			StringBuffer result = new StringBuffer();
			InputStream is = http.getInputStream();
			InputStreamReader read = new InputStreamReader(is, "UTF-8");
			BufferedReader reader = new BufferedReader(read);
			String code;
			while ((code = reader.readLine()) != null) {
				result.append(code);
			}
			reader.close();
			read.close();
			is.close();
			return result.toString();
		} catch (Exception e) {
			log.info(MessageFormat.format("[HTTPREQUEST]ERRORINFO[{0}]", e.getMessage()));
			return "-1";
		} finally {
			if (http != null)
				http.disconnect();
		}
	}

	public static String getNgixIp(HttpServletRequest req) {

		String ip = "";
		try {
			ip = req.getHeader("X-Real-IP");
			if (ip == null || ip.trim().length() == 0 || "unknown".equalsIgnoreCase(ip.trim())) {
				ip = req.getHeader("x-forwarded-for");
			}
			if (ip == null || ip.trim().length() == 0 || "unknown".equalsIgnoreCase(ip.trim())) {
				ip = req.getHeader("Proxy-Client-IP");
			}
			if (ip == null || ip.trim().length() == 0 || "unknown".equalsIgnoreCase(ip.trim())) {
				ip = req.getHeader("WL-Proxy-Client-IP");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = req.getRemoteAddr();
			}
			ip = StringUtils.trimToEmpty(ip);
			String[] arrip = ip.split(",");
			ip = "";
			for (int i = 0; i < arrip.length; i++) {
				if (!"unknown".equalsIgnoreCase(arrip[i].trim())) {
					ip = arrip[i];
					break;
				}
			}
			return ip;
		} catch (Exception e) {
			log.info("error:" + e.getMessage());
			return "";
		}

	}

}