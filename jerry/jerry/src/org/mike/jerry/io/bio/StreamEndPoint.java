package org.mike.jerry.io.bio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.mike.jerry.io.EndPoint;

public class StreamEndPoint implements EndPoint {
	
	InputStream in;
    OutputStream out;
    int maxIdleTime;
    boolean ishut;
    boolean oshut;
	
    public StreamEndPoint(InputStream in, OutputStream out){
        this.in = in;
        this.out = out;
    }

	@Override
	public void shutdownOutput() throws IOException {
		 oshut = true;
	     if (out!=null)
	    	 out.close();
	}

	@Override
	public boolean isOutputShutdown() {
		return oshut;
	}

	@Override
	public void shutdownInput() throws IOException {
		ishut = true;
        if (in!=null)
            in.close();
	}

	@Override
	public boolean isInputShutdown() {
		return ishut;
	}

	@Override
	public void close() throws IOException {
		if (in!=null)
            in.close();
        in=null;
        if (out!=null)
            out.close();
        out=null;
	}

	@Override
	public String getLocalAddr() {
		return null;
	}

	@Override
	public String getLocalHost() {
		return null;
	}

	@Override
	public int getLocalPort() {
		return 0;
	}

	@Override
	public String getRemoteAddr() {
		return null;
	}

	@Override
	public String getRemoteHost() {
		return null;
	}

	@Override
	public int getRemotePort() {
		return 0;
	}

	@Override
	public int getMaxIdleTime() {
		return maxIdleTime;
	}

	@Override
	public void setMaxIdleTime(int timeMs) throws IOException {
		this.maxIdleTime = timeMs;
	}
    

}
