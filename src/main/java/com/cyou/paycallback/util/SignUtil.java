package com.cyou.paycallback.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * 生成签名工具类（URL加密传输）
 * @author zhanghl
 *
 */
public class SignUtil {
	
	/**
	 * 创建签名
	 * @param param 参数升序串
	 * @param isSubstr true截取8~24后返回16位子串作为签名，flase返回全部32位作为签名
	 * @return
	 * @throws Exception
	 */
    public static String createSign(String param, boolean isSubstr) throws Exception {
        StringBuffer result = new StringBuffer();
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(param.getBytes("UTF-8"));
        byte[] b = md5.digest();
        for (int i = 0; i < b.length; ++i) {
            int x = b[i] & 0xFF;
            int h = x >>> 4;
            int l = x & 0x0F;
            result.append((char) (h + ((h < 10) ? '0' : 'a' - 10)));
            result.append((char) (l + ((l < 10) ? '0' : 'a' - 10)));
        }
        if (isSubstr) {
            return result.toString().substring(8, 24);
        } else {
            return result.toString();
        }
    }
	
	/**
	 * 创建参数升序串
	 * @param request 请求对象
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static String createParam(HttpServletRequest request) throws UnsupportedEncodingException {
		List<String> keyList = new ArrayList<String>();
		@SuppressWarnings("unchecked")
		Enumeration<String> enumeration = request.getParameterNames();
		while(enumeration.hasMoreElements()) {
			String key = enumeration.nextElement();
			keyList.add(key);
		}
		Collections.sort(keyList, new Comparator<String>() {
			public int compare(String str1, String str2) {
				if(str1.compareTo(str2)>0) {
					return 1;
				}
				if(str1.compareTo(str2)<0) {
					return -1;
				}
				return 0;
			}
		});
		StringBuffer param = new StringBuffer();
		for(String key:keyList) {
			param.append(key);
			param.append("=");
			param.append(request.getParameter(key));
			param.append("&");
		}
		return param.toString().replaceAll("\n", "").replaceAll("\r", "");
	}
	
	
}
