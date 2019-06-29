package throwaway;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.makotomiyamoto.snowball.object.MemberUser;

import java.io.*;

@Deprecated
public class JsonFileManager {

    @SuppressWarnings("all")
    public static MemberUser[] getMemberUsers(String path) {

        try {

            Gson gson = new Gson();
            BufferedReader reader = new BufferedReader(new FileReader(path));
            MemberUser[] memberUsers = gson.fromJson(reader, MemberUser[].class);

            return memberUsers;

        } catch (IOException e) {
            return new MemberUser[0];
        }

    }

    public static MemberUser getMemberUser(String path, String id) {

        MemberUser[] memberUsers = getMemberUsers(path);
        for (MemberUser memberUser : memberUsers) {
            if (memberUser.getMemberUuid().equals(id)) {
                return memberUser;
            }
        }

        return null;

    }

    private static void writeMemberUsers(String path, MemberUser[] memberUsers) {

        try {

            Gson gson = new GsonBuilder().create();
            String json = gson.toJson(memberUsers);
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            writer.write(json);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @SuppressWarnings("all")
    public static void writeMemberUser(String path, MemberUser memberUser) {

        MemberUser[] currentMemberUsers = getMemberUsers(path);
        for (int i = 0; i < currentMemberUsers.length; i++) {

            if (currentMemberUsers[i].getMemberUuid().equals(memberUser.getMemberUuid())) {

                currentMemberUsers[i] = memberUser;
                writeMemberUsers(path, currentMemberUsers);

                return;

            }

        }

        MemberUser[] newMemberUsers = new MemberUser[currentMemberUsers.length + 1];
        System.arraycopy(currentMemberUsers, 0, newMemberUsers, 0, currentMemberUsers.length);
        newMemberUsers[newMemberUsers.length - 1] = memberUser;
        writeMemberUsers(path, newMemberUsers);

    }

}
