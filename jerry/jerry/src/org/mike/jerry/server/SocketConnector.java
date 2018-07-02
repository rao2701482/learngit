package org.mike.jerry.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Map;

import org.apache.log4j.Logger;
import org.mike.jerry.http.HttpHeaders;
import org.mike.jerry.http.HttpRequestDecoder;
import org.mike.jerry.http.Response;
import org.mike.jerry.http.Request;
import org.mike.jerry.http.HttpStatus;
import org.mike.jerry.http.MimeTypes;
import org.mike.jerry.io.bio.SocketEndPoint;
import org.mike.jerry.server.handler.ResourceHandler;
import org.mike.jerry.server.util.thread.ThreadPool;
import org.mike.jerry.util.IOUtil;
import org.mike.jerry.util.StringUtil;

/**
 * Blocking Socket Server provider
 */
public class SocketConnector {
	private static final Logger log = Logger.getLogger(SocketConnector.class);
	
	protected ServerSocket serverSocket;
	
	private int acceptQueueSize = 0;
	private int localPort;
	private boolean started = true;
	
	private ThreadPool threadPool;
	
	
	/**
	 * Open server socket
	 * @param port
	 * @throws IOException
	 */
	public void open(int port) throws IOException {
		if (serverSocket==null || serverSocket.isClosed())
	        serverSocket= newServerSocket(null , port, acceptQueueSize);
		//serverSocket.setReuseAddress(true);
		
		this.localPort = port;
	}
	
	protected ServerSocket newServerSocket(String host, int port,int backlog) throws IOException{
	    ServerSocket ss= host==null?
	        new ServerSocket(port,backlog):
	        new ServerSocket(port,backlog,InetAddress.getByName(host));
	
	    return ss;
	}

	public void accept() throws IOException {
		log.info("Server started ...");
		
		while(started){
			Socket socket = serverSocket.accept();
			ConnectorEndPoint connector = new ConnectorEndPoint(socket);
			connector.dispatch();
		}
	}
	
	
	/**
	 * Request Handler, take one thread from thread pool when request coming
	 */
	protected class ConnectorEndPoint extends SocketEndPoint implements Runnable {
		
		public ConnectorEndPoint(Socket socket) throws IOException {
			super(socket);
			socket.setSoTimeout(7000);
		}
		
		public void dispatch() {
			threadPool.dispatch(this);
		}

		@Override
		public void run() {
			/*
			 * Decode requqest
			 */
			Request request;
			try {
				request = HttpRequestDecoder.decode(socket);
				if(request == null)
					return;
			
				/*
				 * handler resource
				 */
				Response response;
				response = ResourceHandler.handle(request);
			
				/*
				 * check error
				 */
				checkResponseError(response);
			
				/*
				 * response to client
				 */
				OutputStream out = socket.getOutputStream();
			
				//config status message
				String respStat = HttpStatus.getMessage(response.getStatus());
			
				StringBuilder headers = new StringBuilder();
				headers.append(response.getHttpVersion() + " " 
						+ response.getStatus() + " " + respStat + StringUtil.CRLF);
				
				//write headers
				for(Map.Entry<String, String> header : response.getHeaders().entrySet()){
					headers.append(header.getKey() + ": " + header.getValue() + StringUtil.CRLF);
				}
				
				headers.append(StringUtil.CRLF);
				out.write(headers.toString().getBytes());
				if(response.getBody() != null){
					String encoding = response.getCharacterEncoding();
					
		            if (encoding != null) {
		            	Charset charset = Charset.forName(encoding);
		            	OutputStreamWriter writer = new OutputStreamWriter(out, charset);
		            	writer.write(new String(response.getBody(), charset));
		            	
		            	writer.close();
		            }else {
		            	out.write(response.getBody());
		            }
				}
				
				out.close();
			} catch (IOException e) {
				log.error("Request error : "+e.getMessage());
			}finally {
				try {
					super.close();
				} catch (IOException e) {
					log.error("Close socket error : " + e.getMessage());
				}
			}
			
		}
		
		/**
		 * config error page when response cause error
		 */
		private void checkResponseError(Response response) {
			switch(response.getStatus())
			{
			case HttpStatus.NOT_FOUND_404:
				//response.getHeaders().put(HttpHeaders.LOCATION, StringUtil.PAGE_404);
				try {
					File file = new File(ResourceHandler.getResourceBase() + StringUtil.PAGE_404);
					FileInputStream in = new FileInputStream(file);
					response.setBody(IOUtil.readBytes(in));
					in.close();
					response.getHeaders().put(HttpHeaders.CONTENT_LENGTH, String.valueOf(response.getBody().length));
					response.getHeaders().put(HttpHeaders.CONTENT_TYPE, MimeTypes.TEXT_HTML.getType());
				} catch (IOException e) {
					log.error("404 page not found : " + e.getMessage());
				}
				
				break;
				
			case HttpStatus.INTERNAL_SERVER_ERROR_500:
				
				break;
			
			}
		}
		
	}
	
	//setter getter
	public void setAcceptQueueSize(int acceptQueueSize) {
		this.acceptQueueSize = acceptQueueSize;
	}

	public int getLocalPort() {
		return localPort;
	}

	public void setThreadPool(ThreadPool threadPool) {
		this.threadPool = threadPool;
	}

	public void setStarted(boolean started) {
		this.started = started;
	}
	
}
