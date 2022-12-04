package kr.revelope.study.server.web.http;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class HttpTranslator {
	public static HttpRequest parseHttpTranslator(List<String> lines) {
		String status = "request";
		HttpRequest httpRequest = new HttpRequest();
		Map<String, String> header = new HashMap<>();
		StringBuilder bodyBuilder = new StringBuilder();
		for (String line : lines) {
			if (status.equals("request")) {
				String[] elements = lines.get(0).split(" ");
				String method = elements[0];
				String fullPath = elements[1];
				String version = elements[2];

				httpRequest.setMethod(HttpMethod.get(method));
				httpRequest.setPath(fullPath);
				httpRequest.setHttpVersion(HttpVersion.get(version));

				status = "header";
				continue;
			}

			if (status.equals("header")) {
				if (Objects.isNull(line) || line.isBlank()) {
					status = "body";
					continue;
				}

				String[] keyAndValue = line.split(":");
				header.put(keyAndValue[0], keyAndValue[1]);

				httpRequest.setHeader(header);

				continue;
			}

			bodyBuilder.append(line);
		}

		httpRequest.setBody(bodyBuilder.toString());

		return httpRequest;
	}
}
