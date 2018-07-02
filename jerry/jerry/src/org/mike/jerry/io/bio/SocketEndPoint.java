package org.mike.jerry.io.bio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

import org.mike.jerry.util.StringUtil;

public class SocketEndPoint extends StreamEndPoint{

	protected final Socket socket;
	protected final InetSocketAddress local;
	protected final InetSocketAddress remote;
	protected int maxIdleTime;
	
    public SocketEndPoint(Socket socket)
        	throws IOException {
    	
    	super(socket.getInputStream(),socket.getOutputStream());
    	this.socket = socket;
    	local = (InetSocketAddress)socket.getLocalSocketAddress();
    	remote = (InetSocketAddress)socket.getRemoteSocketAddress();
    	super.setMaxIdleTime(socket.getSoTimeout());
	}
    
	@Override
	public void shutdownOutput() throws IOException {
		if (!socket.isClosed()){
            if (!socket.isOutputShutdown())
                socket.shutdownOutput();
            if (socket.isInputShutdown())
                socket.close();
        }
	}

	@Override
	public boolean isOutputShutdown() {
		return socket.isClosed() || socket.isOutputShutdown();
	}

	@Override
	public void shutdownInput() throws IOException {
		if (!socket.isClosed()) {
            if (!socket.isInputShutdown())
                socket.shutdownInput();
            if (socket.isOutputShutdown())
                socket.close();
        }
	}

	@Override
	public boolean isInputShutdown() {
		return socket.isClosed() || socket.isInputShutdown();
	}

	@Override
	public void close() throws IOException {
		socket.close();
        in=null;
        out=null;
	}

	@Override
	public String getLocalAddr() {
		if (local==null || local.getAddress()==null || local.getAddress().isAnyLocalAddress())
			return StringUtil.ALL_INTERFACES;

		return local.getAddress().getHostAddress();
	}

	@Override
	public String getLocalHost() {
		if (local==null || local.getAddress()==null || local.getAddress().isAnyLocalAddress())
			return StringUtil.ALL_INTERFACES;

		return local.getAddress().getCanonicalHostName();
	}

	@Override
	public int getLocalPort() {
		if (local==null)
            return -1;
        return local.getPort();
	}

	@Override
	public String getRemoteAddr() {
		if (remote==null)
            return null;
        InetAddress addr = remote.getAddress();
        return ( addr == null ? null : addr.getHostAddress() );
	}

	@Override
	public String getRemoteHost() {
		if (remote==null)
            return null;
        return remote.getAddress().getCanonicalHostName();
	}

	@Override
	public int getRemotePort() {
		if (remote==null)
            return -1;
        return remote.getPort();
	}
	@Override
    public String toString() {
        return local + " <--> " + remote;
    }
}
