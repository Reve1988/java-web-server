package kr.revelope.study.server.web;

import kr.revelope.study.server.web.http.HttpTranslator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientSocketProcessor implements Runnable {
	private static final Logger LOGGER = LoggerFactory.getLogger(ClientSocketProcessor.class);

	private final Socket socket;

	public ClientSocketProcessor(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		LOGGER.info("Start receiver.");

		try (
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))
		) {
			List<String> requestStringList = new ArrayList<>();

			String line;
			while ((line = in.readLine()) != null) {
				if (line.isBlank()) {
					break;
				}

				requestStringList.add(line);
			}

			LOGGER.info(HttpTranslator.parseHttpTranslator(requestStringList).toString());

			out.write("HTTP/1.1 200 OK\n");
			out.write("Server: java/test\n");
			out.write("Content-Type: text/html; charset=UTF-8\n");
			out.write("Connection: keep-alive\n");
			out.write("Cache-Control: no-cache\n");
			out.write("\n");
			out.write("Hello World name");
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			LOGGER.info("Stop receiver.");
		}
	}
}
