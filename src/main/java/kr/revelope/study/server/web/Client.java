package kr.revelope.study.server.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class Client implements Runnable {
	private final String data;

	public Client(String data) {
		this.data = data;
	}

	public static void main(String[] args) throws InterruptedException {
		new Thread(new Client("aaaa")).start();
		new Thread(new Client("bbbb")).start();
		new Thread(new Client("cccc")).start();

		Thread.sleep(60 * 1000);
	}

	@Override
	public void run() {
		try (
			Socket socket = new Socket("localhost", 8080);
			PrintStream out = new PrintStream(socket.getOutputStream());
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
		) {
			out.println(data);
			out.println();

			String line;
			while ((line = in.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
