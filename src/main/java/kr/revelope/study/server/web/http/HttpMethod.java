package kr.revelope.study.server.web.http;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum HttpMethod {
	GET,
	POST,
	PUT,
	DELETE;

	private static final Map<String, HttpMethod> NAME_MAP = Arrays.stream(values())
		.collect(Collectors.toUnmodifiableMap(HttpMethod::name, Function.identity()));

	public static HttpMethod get(String method) {
		return NAME_MAP.get(method);
	}
}
