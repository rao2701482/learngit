package org.mike.jerry.io;

import java.io.IOException;

public interface EndPoint {
	
	void shutdownOutput() throws IOException;

    boolean isOutputShutdown();
    
    void shutdownInput() throws IOException;

    boolean isInputShutdown();
	
    void close() throws IOException;
    
    public String getLocalAddr();

    public String getLocalHost();

    public int getLocalPort();

    public String getRemoteAddr();

    public String getRemoteHost();

    public int getRemotePort();
    
    /** Get the max idle time in ms.
     * <p>The max idle time is the time the endpoint can be idle before
     * extraordinary handling takes place.  This loosely corresponds to
     * the {@link java.net.Socket#getSoTimeout()} for blocking connections,
     * but {@link AsyncEndPoint} implementations must use other mechanisms
     * to implement the max idle time.
     * @return the max idle time in ms or if ms <= 0 implies an infinite timeout
     */
    public int getMaxIdleTime();

    /* ------------------------------------------------------------ */
    /** Set the max idle time.
     * @param timeMs the max idle time in MS. Timeout <= 0 implies an infinite timeout
     * @throws IOException if the timeout cannot be set.
     */
    public void setMaxIdleTime(int timeMs) throws IOException;
    
}
