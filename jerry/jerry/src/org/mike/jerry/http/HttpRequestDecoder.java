package org.mike.jerry.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;

import org.mike.jerry.server.HttpInputStream;

/**
 * Http Request Decoder <br/>
 * Reading Http headers and data body
 * 
 * @see Request
 */
public class HttpRequestDecoder {
	
	public static Request decode(Socket socket) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(socket.getInputStream()));
		
		String reqCmd = br.readLine();
		if(reqCmd == null){
			return null;
		}
		String[] cmds = reqCmd.split("\\s");
		
		//Invalid request command
		if(cmds.length != 3) {
			return null;
		}
		//Request method check
		if(!HttpMethod.isAccept(cmds[0])) {
			return null;
		}
		//URL check
		if(cmds[1].length() == 0) {
			return null;
		}
		//Http version ckeck
		if(cmds[2].length() == 0) {
			return null;
		}
		
		Request httpReq = new Request();
		httpReq.method = cmds[0];
		httpReq.version = cmds[2];
		
		//Read headers 
		String line;
		int contentLength = 0;
		HashMap<String,String> headers = new HashMap<String, String>();
		while( (line = br.readLine()) != null 
				&& !line.equals("") ) {
			
			int idx = line.indexOf(": ");
			if(idx == -1) {
				continue;
			}
			if(HttpHeaders.CONTENT_LENGTH.equals(line)) {
				try{
					contentLength = Integer.parseInt(line.substring(idx+2).trim());
				}catch (RuntimeException e) {
					continue;
				}
			}
			headers.put(line.substring(0, idx), line.substring(idx+2));
		}
		httpReq.headers = headers;
		
		//Read url parameters
		String reqUrl = cmds[1];
		int idx = reqUrl.indexOf("?");
		if(idx > 0){ // valid
			HashMap<String,String> paramaterMap = new HashMap<String, String>();
			
			String paramater = reqUrl.substring(idx+1).trim();
			String[] params = paramater.split("&");
			for(String entry : params) {
				String[] group = entry.split("=");
				
				if(group.length != 2) {// invalid param
					continue;
				}
				paramaterMap.put(group[0], group[1]);
			}
			httpReq.parameters = paramaterMap;
			reqUrl = reqUrl.substring(0, idx);
		}
		
		httpReq.requestURI = reqUrl;
		
		
		/*
		 * POST request decoder
		 */
		if( HttpMethod.POST.name().equals( cmds[0] ) ) {
			//Read post parameters
			if(contentLength > 0) {
				char[] buff = new char[contentLength];
				br.read(buff);
				
				//decode paramater
				HashMap<String,String> paramaterMap = httpReq.parameters;
				if(paramaterMap == null){
					paramaterMap = new HashMap<String, String>();
				}
				
				String paramater = new String(buff);
				httpReq.in = new HttpInputStream(paramater.getBytes());
				
				String[] params = paramater.split("&");
				for(String entry : params) {
					String[] group = entry.split("=");
					
					if(group.length != 2) {// invalid param
						continue;
					}
					paramaterMap.put(group[0], group[1]);
				}
				httpReq.parameters = paramaterMap;
			}
		}
		
		socket.shutdownInput();
		return httpReq;
	}
	
	
	
}
