package com.jmx;

import javax.management.MBeanInfo;
import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;

/**
 * Created by infear on 2017/4/16.
 */
public class Main {
    private static ObjectName objectName;
    private static MBeanServer mBeanServer;

    public static void main(String[] args) throws Exception {
        init();
        manage();
    }

    private static void init() throws Exception {
        ServerImpl serverImpl = new ServerImpl();
        ServerMonitor serverMonitor = new ServerMonitor(serverImpl);
        mBeanServer = MBeanServerFactory.createMBeanServer();
        objectName = new ObjectName("objectName:id=ServerMonitor1");
        mBeanServer.registerMBean(serverMonitor, objectName);
    }

    private static void manage() throws Exception {
        MBeanInfo mBeanInfo = mBeanServer.getMBeanInfo(objectName);
        System.out.println(mBeanInfo.getAttributes());
        //UpTime not upTime here!
        System.out.println(mBeanServer.getAttribute(objectName, "UpTime"));
    }
}
