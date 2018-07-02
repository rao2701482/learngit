package org.mike.jerry.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class SocketClientTest {

	public static void main(String[] args) throws Exception {
		
		Socket s = new Socket("www.baidu.com", 80);
		
		
		BufferedWriter bw = 
				new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
		
		bw.write("GET / HTTP/1.1\r\n");
		bw.flush();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		String tmp  =null;
		System.out.println("############ remote resp #################");
		while((tmp = br.readLine())!=null){
			System.out.println(tmp);
		}

	}

}
