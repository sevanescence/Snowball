package throwaway;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.makotomiyamoto.snowball.object.MemberUser;
import com.makotomiyamoto.snowball.object.MemberUserList;

import java.io.*;

@Deprecated
public class UserDataManager {

    @SuppressWarnings("all")
    public static MemberUserList getMemberUserList(String path) {

        try {

            Gson gson = new Gson();
            BufferedReader reader = new BufferedReader(new FileReader(path));
            MemberUserList memberUserList = gson.fromJson(reader, MemberUserList.class);

            return memberUserList;

        } catch (IOException e) {
            return new MemberUserList(new MemberUser[]{new MemberUser("a_mysterious_kitten", 1, 100)});
        }

    }

    public static MemberUser getMemberUser(String path, String id) {

        try {

            for (MemberUser memberUser : getMemberUserList(path).getMemberUsers()) {
                if (memberUser.getMemberUuid().equals(id)) {
                    return memberUser;
                }
            }

        } catch (Exception e) {
            return null;
        }

        return null;

    }

    public static void setMemberUserList(String path, MemberUserList list) {

        try {

            Gson gson = new GsonBuilder().create();
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            writer.write(gson.toJson(list));
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void writeMemberUser(String path, MemberUser memberUser) {

        MemberUserList list = getMemberUserList(path);
        MemberUser[] memberUsers = list.getMemberUsers();
        for (int i = 0; i < memberUsers.length; i++) {
            if (memberUsers[i].getMemberUuid().equals(memberUser.getMemberUuid())) {
                memberUsers[i] = memberUser;
                list.setMemberUsers(memberUsers);
                setMemberUserList(path, list);
                return;
            }
        }

        memberUsers = new MemberUser[list.getMemberUsers().length + 1];
        memberUsers[memberUsers.length - 1] = memberUser;
        list.setMemberUsers(memberUsers);
        setMemberUserList(path, list);

    }

    public static boolean lookForUser(String path, String id) {

        try {

            MemberUserList list = getMemberUserList(path);
            for (MemberUser memberUser : list.getMemberUsers()) {
                if (memberUser.getMemberUuid().equals(id)) {
                    return true;
                }
            }

        } catch (Exception e) {
            return false;
        }

        return false;

    }

    public static void runLevelUp(String path, MemberUser memberUser) {

        memberUser.setLevel(memberUser.getLevel() + 1);

        switch (memberUser.getLevel()) {

            case 5:
                System.out.println("User has hit level 5");
                break;
            case 10:
                System.out.println("User has hit level 10");
                break;
            default:
                System.out.println("User is level " + memberUser.getLevel());
                break;

        }

        UserDataManager.writeMemberUser(path, memberUser);

    }

}
