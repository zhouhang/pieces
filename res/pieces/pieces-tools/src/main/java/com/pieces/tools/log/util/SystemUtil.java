//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.pieces.tools.log.util;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SystemUtil {
    private static String localHostIP = null;
    private static Logger logger = LoggerFactory.getLogger(SystemUtil.class);

    public SystemUtil() {
    }

    public static String getLocalhostIp() {
        try {
            if(localHostIP == null) {
                localHostIP = getLocalHostAddress();
            }
        } catch (Throwable var1) {
            logger.error("Get host IP failed!!!", var1);
        }

        return localHostIP;
    }

    public static String getJvmPid() {
        String jvmPid = "";

        try {
            jvmPid = ManagementFactory.getRuntimeMXBean().getName();
        } catch (Throwable var2) {
            if(logger.isDebugEnabled()) {
                logger.debug("Get jvm pid failed!!!");
            }
        }

        return jvmPid;
    }

    private static String getLocalHostAddress() throws UnknownHostException {
        try {
            String e1 = getLocalHostLANAddress().getHostAddress();
            return e1;
        } catch (UnknownHostException var1) {
            throw var1;
        }
    }

    private static InetAddress getLocalHostLANAddress() throws UnknownHostException {
        try {
            InetAddress e = null;
            Enumeration unknownHostException1 = NetworkInterface.getNetworkInterfaces();

            while(unknownHostException1.hasMoreElements()) {
                NetworkInterface iface = (NetworkInterface)unknownHostException1.nextElement();
                Enumeration inetAddrs = iface.getInetAddresses();

                while(inetAddrs.hasMoreElements()) {
                    InetAddress inetAddr = (InetAddress)inetAddrs.nextElement();
                    if(!inetAddr.isLoopbackAddress()) {
                        if(inetAddr.isSiteLocalAddress()) {
                            return inetAddr;
                        }

                        if(e == null) {
                            e = inetAddr;
                        }
                    }
                }
            }

            if(e != null) {
                return e;
            } else {
                InetAddress unknownHostException2 = InetAddress.getLocalHost();
                if(unknownHostException2 == null) {
                    throw new UnknownHostException("The JDK InetAddress.getLocalHost() method unexpectedly returned null.");
                } else {
                    return unknownHostException2;
                }
            }
        } catch (Exception var5) {
            UnknownHostException unknownHostException = new UnknownHostException("Failed to determine LAN address: " + var5);
            unknownHostException.initCause(var5);
            throw unknownHostException;
        }
    }
}
