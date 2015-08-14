package com.cyou.paycallback.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ParamSortUtil {
	public List<String> getSortKeyList(Map<String, Object> secretParam) {
		List<String> keys = new ArrayList<String>();
		for (Map.Entry<String, Object> p : new TreeMap<String, Object>(secretParam).entrySet()) {
			keys.add(p.getKey());
		}
		return keys;
	}
}
