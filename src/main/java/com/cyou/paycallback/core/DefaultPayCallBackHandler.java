package com.cyou.paycallback.core;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.cyou.paycallback.define.AppKeyConfig;
import com.cyou.paycallback.define.Cache;
import com.cyou.paycallback.define.ChannelEntry;
import com.cyou.paycallback.environment.SDKUtil;
import com.cyou.paycallback.request.RequestModule;
import com.cyou.paycallback.result.ResultModule;
import com.cyou.paycallback.secret.SecretModule;
import com.cyou.paycallback.secretstring.SecretStringModule;
import com.cyou.paycallback.util.StringUtils;

public abstract class DefaultPayCallBackHandler implements PayCallBackTemplate {

	Logger logger = Logger.getLogger(SDKUtil.class);
	protected final String APP_KEY = "appKey";
	private RequestModule requestModule;
	private SecretStringModule secretStringModule;
	private SecretModule secretModule;
	private ResultModule resultModule;
	private ChannelEntry entry;
	private Map<String, PayCallBackHandler> versions = new HashMap<String, PayCallBackHandler>();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		if (entry != null) {
			// 获取请求字符串
			Map<String, String> paramMap = requestModule.getParamsMap(request, entry.getRequestCharset());
			// 检测必传字段是否为空
			if (setMustParam(paramMap)) {
				String passThroughParam = entry.getPassThroughName();
				if (!StringUtils.isEmpty(passThroughParam)) {
					String passThroughStr = paramMap.get(passThroughParam);
					// ///////////////////////////////////////////////////////////////////////
					Map<String, String> passThroughMap = passThroughStr(passThroughStr);
					// //////////////////////////////////////////////////////////////////////
					// 将透传参数放入请求参数，如果有相同参数名则报异常
					for (Iterator<String> iter = passThroughMap.keySet().iterator(); iter.hasNext();) {
						String key = iter.next();
						if (!paramMap.containsKey(key)) {
							paramMap.put(key, passThroughMap.get(key));
						} else {
							logger.error("透传参数中含有参数列表同名参数");
							return;
						}
					}
					String appKey = paramMap.get(APP_KEY);
					AppKeyConfig appConfig = Cache.appConfigMap.get(appKey + "_" + entry.getChnnelID());
					if (appConfig != null) {
						// 构造加密字符串
						// ///////////////////////////////////////////////////////////////////////
						String secretStr = getSecretString(paramMap, appConfig);
						// ///////////////////////////////////////////////////////////////////////
						// 构造签名
						// ///////////////////////////////////////////////////////////////////////
						String sign = getSign(secretStr, null);
						// ///////////////////////////////////////////////////////////////////////
						String requestSign = paramMap.get(entry.getSignParam());
						if (requestSign.equals(sign)) {
							boolean flag = SDKUtil.requestBilling(paramMap.get(entry.getOrderIdName()), paramMap.get(entry.getThirdOrderIdName()),
									paramMap.get(entry.getAmountName()), entry.getChannelPayWayMapper(), entry.getChnnelID());
							PrintWriter out = null;
							if (flag) {
								// ///////////////////////////////////////////////////////////////////////
								String result = setResult(paramMap, true);
								// ///////////////////////////////////////////////////////////////////////
								try {
									out = response.getWriter();
									out.print(result);
								} catch (Exception e) {
									e.printStackTrace();
								} finally {
									out.flush();
									out.close();
								}
							} else {
								String result = setResult(paramMap, false);
								try {
									out = response.getWriter();
									out.print(result);
								} catch (Exception e) {
									e.printStackTrace();
								} finally {
									out.flush();
									out.close();
								}
								logger.error("billing验证错误！");
							}
						} else {
							logger.error("签名验证错误！");
						}
					} else {
						logger.error("缓存APPsecret为空！");
					}
				} else {
					logger.error("透传字段名称为空！");
				}
			} else {
				logger.error("必传字段为空！");
				return;
			}
		} else {
			logger.error("渠道实体设置为空！");
		}

	}

	private boolean setMustParam(Map<String, String> paramMap) {
		boolean flag = true;
		String[] mustParam = entry.getMustParam();
		if (mustParam != null && mustParam.length > 0) {
			for (String param : mustParam) {
				if (StringUtils.isEmpty(paramMap.get(param))) {
					logger.error("必传参数：" + param + "为空！");
					flag = !flag;
					break;
				}
			}
		} else {
			logger.error("未设置必传参数列表!");
		}
		return flag;
	}

	@Override
	public Map<String, String> getRequestParams(HttpServletRequest request, String charset) {
		return requestModule.getParamsMap(request, charset);
	}

	@Override
	public abstract Map<String, String> passThroughStr(String passThrough);

	@Override
	public String getSecretString(Map<String, String> params, AppKeyConfig appConfig) {
		return secretStringModule.getSecretString(params, appConfig);
	}

	@Override
	public String getSign(String secretString, String sign) {
		return secretModule.getSign(secretString, sign);
	}

	@Override
	public String setResult(Map<String, String> paramMap, boolean flag) {
		return resultModule.getResult(paramMap, flag);
	}

	@Override
	public PayCallBackHandler getChannel(String version) {
		return versions.get(version);
	}

	@Override
	public void addVersion(String version, PayCallBackHandler handler) {
		versions.put(version, handler);
	}

	public ChannelEntry getChannel() {
		return entry;
	}

	public void setChannel(ChannelEntry entry) {
		this.entry = entry;
	}

	public RequestModule getRequestModule() {
		return requestModule;
	}

	public void setRequestModule(RequestModule requestModule) {
		this.requestModule = requestModule;
	}

	public SecretStringModule getSecretStringModule() {
		return secretStringModule;
	}

	public void setSecretStringModule(SecretStringModule secretStringModule) {
		this.secretStringModule = secretStringModule;
	}

	public SecretModule getSecretModule() {
		return secretModule;
	}

	public void setSecretModule(SecretModule secretModule) {
		this.secretModule = secretModule;
	}

	public ResultModule getResultModule() {
		return resultModule;
	}

	public void setResultModule(ResultModule resultModule) {
		this.resultModule = resultModule;
	}

}
