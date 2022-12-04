package kr.revelope.study.server.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private static final Logger LOGGER = LoggerFactory.getLogger(Server.class);

	private volatile boolean running = true;

	public static void main(String[] args) {
		new Server().start();
	}

	public void start() {
		ServerSocket serverSocket = getServerSocket();

		while (running) {
			try {
				Socket clientSocket = serverSocket.accept();

				new Thread(new ClientSocketProcessor(clientSocket)).start();
			} catch (IOException e) {
				LOGGER.error("Read Socket error.", e);
				throw new RuntimeException(e);
			}
		}
	}

	private ServerSocket getServerSocket() {
		try {
			return new ServerSocket(8080);
		} catch (IOException e) {
			LOGGER.error("Create server socket error.", e);
			throw new RuntimeException(e);
		}
	}
}
