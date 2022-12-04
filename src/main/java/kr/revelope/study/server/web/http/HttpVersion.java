package kr.revelope.study.server.web.http;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum HttpVersion {
	HTTP_1_1("HTTP/1.1");

	private static final Map<String, HttpVersion> NAME_MAP = Arrays.stream(values())
		.collect(Collectors.toUnmodifiableMap(HttpVersion::getVersion, Function.identity()));

	private final String version;

	HttpVersion(String version) {
		this.version = version;
	}

	public static HttpVersion get(String version) {
		return NAME_MAP.get(version);
	}

	public String getVersion() {
		return version;
	}
}
