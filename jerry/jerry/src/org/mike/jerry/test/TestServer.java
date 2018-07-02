package org.mike.jerry.test;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TestServer {

	public static void main(String[] args) throws Exception {
		
		ServerSocket ss = new ServerSocket(9000);
		
		String body = "SERVER ERROR!";
		
		while(true){
			Socket socket = ss.accept();
			BufferedWriter br = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			br.write("HTTP/1.1 200 OK\r\n");
			br.write("Content-Type: text/html\r\n");
			br.write("Server: M\r\n");
			br.write("\r\n");
			br.write(body);
			br.close();
		}

	}

}
