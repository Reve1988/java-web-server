package kr.revelope.study.server.web.http;

import java.util.Map;

public class HttpRequest {
	private HttpMethod method;
	private HttpVersion httpVersion;
	private String path;
	private String queryString;
	private Map<String, String> header;
	private String body;

	public HttpMethod getMethod() {
		return method;
	}

	public void setMethod(HttpMethod method) {
		this.method = method;
	}

	public HttpVersion getHttpVersion() {
		return httpVersion;
	}

	public void setHttpVersion(HttpVersion httpVersion) {
		this.httpVersion = httpVersion;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	public Map<String, String> getHeader() {
		return header;
	}

	public void setHeader(Map<String, String> header) {
		this.header = header;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return "HttpRequest{" +
			"method=" + method +
			", version=" + httpVersion +
			", path='" + path + '\'' +
			", queryString='" + queryString + '\'' +
			", header=" + header +
			", body='" + body + '\'' +
			'}';
	}
}
