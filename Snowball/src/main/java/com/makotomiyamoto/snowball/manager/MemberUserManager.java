package com.makotomiyamoto.snowball.manager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.makotomiyamoto.snowball.object.MemberUser;
import com.makotomiyamoto.snowball.object.MemberUserList;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.io.*;
import java.util.List;

public class MemberUserManager {

    @SuppressWarnings("all")
    public static MemberUserList getMemberUserList(String path) {

        try {

            Gson gson = new Gson();
            BufferedReader reader = new BufferedReader(new FileReader(path));
            MemberUserList memberUserList = gson.fromJson(reader, MemberUserList.class);
            return memberUserList;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }

    @SuppressWarnings("all")
    public static MemberUser getMemberUser(String path, String id) {

        MemberUser[] memberUsers = getMemberUserList(path).getMemberUsers();
        for (MemberUser memberUser : memberUsers) {
            if (!memberUser.getMemberUuid().equals(id)) {
                continue;
            }
            return memberUser;
        }

        return null;

    }

    public static void setMemberUserList(String path, MemberUserList memberUserList) {

        try {

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            writer.write(gson.toJson(memberUserList, MemberUserList.class));
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @SuppressWarnings("all")
    public static void setMemberUser(String path, MemberUser memberUser) {

        MemberUserList memberUserList = getMemberUserList(path);
        MemberUser[] memberUsers = memberUserList.getMemberUsers();
        for (int i = 0; i < memberUsers.length; i++) {
            if (memberUsers[i].getMemberUuid().equals(memberUser.getMemberUuid())) {
                memberUsers[i] = memberUser;
                memberUserList.setMemberUsers(memberUsers);
                setMemberUserList(path, memberUserList);
                return;
            }
        }

        MemberUser[] newMemberUsers = new MemberUser[memberUsers.length + 1];
        System.arraycopy(memberUsers, 0, newMemberUsers, 0, memberUsers.length);
        newMemberUsers[newMemberUsers.length - 1] = memberUser;
        memberUserList.setMemberUsers(newMemberUsers);
        setMemberUserList(path, memberUserList);

    }

    @SuppressWarnings("all")
    public static boolean lookForUser(String path, String id) {

        MemberUserList memberUserList = getMemberUserList(path);
        MemberUser[] memberUsers = memberUserList.getMemberUsers();
        for (MemberUser memberUser : memberUsers) {
            if (memberUser.getMemberUuid().equals(id)) {
                return true;
            }
        }

        return false;

    }

    @SuppressWarnings("all")
    public static void checkRoles(MemberUser memberUser, MessageReceivedEvent event) {

        List<Role> roles = event.getJDA().getRoles();

        for (Role role : roles) {
            if (memberUser.getLevel() >= 5 && role.getName().equalsIgnoreCase("Kitty")) {
                event.getGuild().addRoleToMember(event.getMember(), role).complete();
            }
            if (memberUser.getLevel() >= 10 && role.getName().equalsIgnoreCase("Cat")) {
                event.getGuild().addRoleToMember(event.getMember(), role).complete();
            }
            if (memberUser.getLevel() >= 15 && role.getName().equalsIgnoreCase("Kitty Cat")) {
                event.getGuild().addRoleToMember(event.getMember(), role).complete();
            }
            if (memberUser.getLevel() >= 20 && role.getName().equalsIgnoreCase("Nyaa")) {
                event.getGuild().addRoleToMember(event.getMember(), role).complete();
            }
            if (memberUser.getLevel() >= 25 && role.getName().equalsIgnoreCase("Neko")) {
                event.getGuild().addRoleToMember(event.getMember(), role).complete();
            }
            if (memberUser.getLevel() >= 30 && role.getName().equalsIgnoreCase("Nyanko")) {
                event.getGuild().addRoleToMember(event.getMember(), role).complete();
            }
        }

    }

    @SuppressWarnings("all")
    public static void main(String[] args) {

        MemberUser memberUser = new MemberUser("Snowball", 1, 60);
        MemberUserList memberUserList = new MemberUserList(new MemberUser[]{memberUser});
        setMemberUserList("src/main/resources/userdata.json", memberUserList);

        System.out.println(getMemberUser("src/main/resources/userdata.json", "Snowball").getLevel());

        setMemberUser("src/main/resources/userdata.json", memberUser);

        memberUser = new MemberUser("Kitten", 1, 60);

        setMemberUser("src/main/resources/userdata.json", memberUser);

    }

}
