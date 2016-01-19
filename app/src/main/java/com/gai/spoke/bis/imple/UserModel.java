package com.gai.spoke.bis.imple;

import android.util.Log;

import com.gai.spoke.bean.LoginConfig;
import com.gai.spoke.bean.User;
import com.gai.spoke.bis.callback.OnLoginListener;
import com.gai.spoke.bis.interf.IUserModel;
import com.gai.spoke.bis.task.LoginTask;
import com.gai.spoke.presenter.imple.LoginPresenter;
import com.gai.spoke.utils.SLog;
import com.gai.spoke.utils.XmppConnectionManager;

import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.roster.Roster;
import org.jivesoftware.smack.roster.RosterEntry;
import org.jivesoftware.smack.roster.RosterGroup;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smackx.offline.OfflineMessageManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gai on 15/12/25.
 */
public class UserModel implements IUserModel {
    private static final String TAG = "UserModel";
    private LoginPresenter loginPresenter;
    LoginTask loginTask;
    XmppConnectionManager xmppConnectionManager = null;
    XMPPTCPConnection xmpptcpConnection;

    public UserModel(LoginPresenter loginPresenter) {
        this.loginPresenter = loginPresenter;
        this.xmppConnectionManager = XmppConnectionManager.getInstance();
//        this.loginTask=new L
    }

    @Override
    public void login(User user,OnLoginListener loginListener) {
        LoginConfig loginConfig = new LoginConfig();
        loginConfig.setUsername(user.getName());
        loginConfig.setPassword(user.getPassword());
        this.loginTask = new LoginTask(loginPresenter,loginConfig,loginListener);
        loginTask.execute();
//        loginConfig.setXmppHost("192.168.31.179");
//        loginConfig.setXmppPort(5222);
//        loginConfig.setXmppServiceName("zpfdeMacBook-Pro.loca");
//        xmpptcpConnection = xmppConnectionManager.init(loginConfig);
//
//        try {
//            xmpptcpConnection.connect();
//            xmpptcpConnection.login();
//        } catch (XMPPException e) {
//            SLog.e(TAG, Log.getStackTraceString(e));
//        } catch (SmackException e) {
//            SLog.e(TAG, Log.getStackTraceString(e));
//        } catch (IOException e) {
//            SLog.e(TAG, Log.getStackTraceString(e));
//        }
    }

    @Override
    public void reg(User user) {

    }

    public void changePassword(User user) {

    }

    @Override
    public void setPresence(Presence.Type type) {

    }

    @Override
    public void logout(User user) {
        XmppConnectionManager.getInstance().disconnect();
    }

    /**
     * 获取所有分组
     *
     * @param roster
     * @return
     */
    @Override
    public List<RosterGroup> getGroups(Roster roster) {
        List<RosterGroup> rosterGroups = new ArrayList<>();
        rosterGroups.addAll(roster.getGroups());
        return rosterGroups;
    }

    /**
     * 新建分组
     *
     * @param roster
     * @param groupName
     * @return
     */
    @Override
    public boolean addGroup(Roster roster, String groupName) {
        try {
            roster.createGroup(groupName);
            return true;
        } catch (Exception e) {
            SLog.e(TAG, Log.getStackTraceString(e));
        }
        return false;
    }

    /**
     * 添加到分组
     *
     * @param roster
     * @param userName
     * @param groupName
     */
    @Override
    public void addUserToGroup(Roster roster, String userName, String groupName) {
        RosterGroup rosterGroup = roster.getGroup(groupName);
        if (rosterGroup == null) {
            rosterGroup = roster.createGroup(groupName);
        }

        RosterEntry entry = roster.getEntry(userName);
        try {
            rosterGroup.addEntry(entry);
        } catch (SmackException.NoResponseException e) {
//            e.printStackTrace();
            SLog.e(TAG, Log.getStackTraceString(e));
        } catch (XMPPException.XMPPErrorException e) {
            SLog.e(TAG, Log.getStackTraceString(e));
        } catch (SmackException.NotConnectedException e) {
            SLog.e(TAG, Log.getStackTraceString(e));
        }
    }

    /**
     * 获取所有成员
     *
     * @param roster
     * @return
     */
    @Override
    public List<RosterEntry> getAllEntrys(Roster roster) {
        List<RosterEntry> rosterEntries = new ArrayList<>();
        rosterEntries.addAll(roster.getEntries());
        return rosterEntries;
    }

    /**
     * 获取分组所有成员
     *
     * @param roster
     * @param groupName
     * @return
     */
    @Override
    public List<RosterEntry> getEntrysByGroup(Roster roster, String groupName) {
        List<RosterEntry> list = new ArrayList<RosterEntry>();
        RosterGroup group = roster.getGroup(groupName);
        list.addAll(group.getEntries());
        return list;
    }

    @Override
    public boolean addUser(Roster roster, String userName, String name, String groupName) {
        try {
            roster.createEntry(userName, name, null == groupName ? null
                    : new String[]{groupName});
            return true;
        } catch (XMPPException e) {
            SLog.e(TAG, Log.getStackTraceString(e));
        } catch (SmackException.NotLoggedInException e) {
//            e.printStackTrace();
            SLog.e(TAG, Log.getStackTraceString(e));
        } catch (SmackException.NotConnectedException e) {
//            e.printStackTrace();
            SLog.e(TAG, Log.getStackTraceString(e));
        } catch (SmackException.NoResponseException e) {
//            e.printStackTrace();
            SLog.e(TAG, Log.getStackTraceString(e));
        }
        return false;
    }

    @Override
    public boolean removeUser(Roster roster, String userName) {
        RosterEntry entry = roster.getEntry(userName);

        try {
            if (null != entry)
                roster.removeEntry(entry);
        } catch (SmackException.NotLoggedInException e) {
            SLog.e(TAG, Log.getStackTraceString(e));
        } catch (SmackException.NoResponseException e) {
            SLog.e(TAG, Log.getStackTraceString(e));
        } catch (XMPPException.XMPPErrorException e) {
            SLog.e(TAG, Log.getStackTraceString(e));
        } catch (SmackException.NotConnectedException e) {
            SLog.e(TAG, Log.getStackTraceString(e));
        }
        return true;
    }

    @Override
    public List<User> searchUsers(String serverDomain, String userName) {
        return null;
    }

    @Override
    public List<Message> getOffLineMessages(XMPPTCPConnection connection) {
        List<Message> msgs = new ArrayList<Message>();
        OfflineMessageManager offLineMessageManager = new OfflineMessageManager(
                connection);
        try {
            msgs.addAll(offLineMessageManager.getMessages());
            offLineMessageManager.deleteMessages();
        } catch (XMPPException e) {
            e.printStackTrace();
        } catch (SmackException.NotConnectedException e) {
            e.printStackTrace();
        } catch (SmackException.NoResponseException e) {
            e.printStackTrace();
        }
        return msgs;
    }
}
