package com.gai.spoke.utils;

import com.gai.spoke.bean.LoginConfig;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.roster.Roster;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;


/**
 * Created by gai on 15/12/29.
 */
public class XmppConnectionManager {
    private XMPPTCPConnectionConfiguration connectionConfig;
    private XMPPTCPConnection connection;
    private static XmppConnectionManager instance;


    private XmppConnectionManager() {
    }


    public static XmppConnectionManager getInstance() {
        if (instance == null) {
            instance = new XmppConnectionManager();
        }
        return instance;
    }


    public XMPPTCPConnection init(LoginConfig loginConfig) {

        XMPPTCPConnectionConfiguration.Builder builder = XMPPTCPConnectionConfiguration.builder();
//        builder.setSecurityMode(ConnectionConfiguration.SecurityMode.disabled);
        connectionConfig = builder.setSendPresence(true)
                .setHost(loginConfig.getXmppHost())
                .setPort(loginConfig.getXmppPort())
                .setServiceName(loginConfig.getXmppServiceName())
                .setSecurityMode(ConnectionConfiguration.SecurityMode.disabled)
                .setCompressionEnabled(false)
                .setDebuggerEnabled(false)
                .build();
        Roster.setDefaultSubscriptionMode(Roster.SubscriptionMode.manual);
        connection = new XMPPTCPConnection(connectionConfig);
        return connection;
    }

    /**
     * 获取有效的xmppConnection 链接
     * @return
     */
    public XMPPConnection getConnection() {
        if (connection == null) {
            throw new RuntimeException("请先初始化XMPPConnection连接");
        }
        return connection;
    }

    /**
     * 销毁xmppConnection 连接
     */
    public void disconnect() {
        if (connection != null) {
            connection.disconnect();
        }
    }
}
