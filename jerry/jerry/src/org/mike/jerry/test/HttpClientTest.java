package org.mike.jerry.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClientTest {

	public static void main(String[] args) throws Exception {
		
		HttpURLConnection conn = (HttpURLConnection) 
				new URL("http://www.baidu.com").openConnection();
		conn.setDoOutput(true);
		conn.setDoInput(true);
		
		conn.setRequestMethod("POST");
		
		/*BufferedWriter bw = 
				new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
		
		bw.write("name=admin");
		bw.close();*/
		
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String tmp  =null;
		System.out.println("############ remote resp #################");
		while((tmp = br.readLine())!=null){
			System.out.println(tmp);
		}
	}

}
