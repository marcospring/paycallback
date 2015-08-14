package com.cyou.paycallback.core;

import java.util.List;
import java.util.Map;

public interface SecretStringCreator {
	String createSecretString(Map<String, Object> secretParam);

	String createSortSecretString(Map<String, Object> secretParam, List<String> sortKeys);
}
