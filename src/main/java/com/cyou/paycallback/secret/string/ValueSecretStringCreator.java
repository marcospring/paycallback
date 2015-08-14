package com.cyou.paycallback.secret.string;

import java.util.List;
import java.util.Map;

import com.cyou.paycallback.core.SecretStringCreator;

public class ValueSecretStringCreator implements SecretStringCreator {

	@Override
	public String createSecretString(Map<String, Object> secretParam) {
		if (secretParam == null)
			return null;
		StringBuffer buffer = new StringBuffer();
		for (Map.Entry<String, Object> entry : secretParam.entrySet()) {
			buffer.append(entry.getValue());
		}
		return buffer.toString();
	}

	@Override
	public String createSortSecretString(Map<String, Object> secretParam, List<String> sortKeys) {
		if (secretParam == null || sortKeys == null)
			return null;
		StringBuffer buffer = new StringBuffer();
		for (String key : sortKeys) {
			buffer.append(String.valueOf(secretParam.get(key)));
		}
		return buffer.toString();
	}

}
