package com.gai.spoke.bis.interf;

import com.gai.spoke.bean.User;
import com.gai.spoke.bis.callback.OnLoginListener;

import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.roster.Roster;
import org.jivesoftware.smack.roster.RosterEntry;
import org.jivesoftware.smack.roster.RosterGroup;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;

import java.util.List;

/**
 * Created by gai on 15/12/25.
 */
public interface IUserModel {
    void login(User user,final OnLoginListener loginListener);


    void reg(User user);

    void changePassword(User user);

    void setPresence(Presence.Type type);


    void logout(User user);


    List<RosterGroup> getGroups(Roster roster);

    boolean addGroup(Roster roster, String groupName);

    void addUserToGroup(Roster roster, String userName, String groupName);

    List<RosterEntry> getAllEntrys(Roster roster);
    List<RosterEntry> getEntrysByGroup(Roster roster, String groupName) ;
    boolean addUser(Roster roster, String userName, String name,
                    String groupName);
    boolean removeUser(Roster roster, String userName);
    List<User> searchUsers(String serverDomain, String userName);
    List<Message> getOffLineMessages(XMPPTCPConnection connection);
}
