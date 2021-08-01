package io.github.kamilkapadia.karabast.util;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;

public class JSONPathUtil {

	public static Object getJSONObject(String json) {
		return Configuration.defaultConfiguration().addOptions(Option.SUPPRESS_EXCEPTIONS).jsonProvider().parse(json);
    }
	
	public static String getString(Object jsonObject, String jsonPath) {
	    return JsonPath.read(jsonObject, jsonPath).toString();
	}
	
	public static long getInt(Object jsonObject, String jsonPath) {
	    return Integer.parseInt(JsonPath.read(jsonObject, jsonPath).toString());
	}
	
	public static long getLong(Object jsonObject, String jsonPath) {
	    return Long.parseLong(JsonPath.read(jsonObject, jsonPath).toString());
	}
	
	public static double getDouble(Object jsonObject, String jsonPath) {
	    return Double.parseDouble(JsonPath.read(jsonObject, jsonPath).toString());
	}
	
	public static boolean getBoolean(Object jsonObject, String jsonPath) {
	    return Boolean.parseBoolean(JsonPath.read(jsonObject, jsonPath).toString());
	}
}
