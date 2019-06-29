package com.makotomiyamoto.snowball.manager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.makotomiyamoto.snowball.object.MemberUser;
import net.dv8tion.jda.api.JDA;

import java.io.*;

public class FileManager {

    public static String[] getFileAsArray(String path) {
        try {
            BufferedReader bufferedReader =
                    new BufferedReader(
                            new FileReader(
                                    new File(path)));
            String line;
            String[] fileContentsArray = new String[1];
            while ((line = bufferedReader.readLine()) != null) {
                String[] temp = new String[fileContentsArray.length + 1];
                System.arraycopy(fileContentsArray, 0, temp, 0, fileContentsArray.length);
                temp[temp.length - 1] = line;
                fileContentsArray = temp;
            }
            System.arraycopy(fileContentsArray, 1, fileContentsArray, 0, fileContentsArray.length - 1);
            String[] temp = new String[fileContentsArray.length - 1];
            System.arraycopy(fileContentsArray, 0, temp, 0, fileContentsArray.length - 1);
            fileContentsArray = temp;
            bufferedReader.close();
            return fileContentsArray;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String[]{};
    }

    public static void writeArrayToFile(String path, Object[] fileContents) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(path)));
            for (Object line : fileContents) {
                if (line != null) {
                    bufferedWriter.write(line.toString());
                    bufferedWriter.newLine();
                }
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getKey(String path, String key) {

        try {

            BufferedReader reader = new BufferedReader(
                    new FileReader(
                            new File(path)));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.length() >= key.length() && line.substring(0, key.length()).equalsIgnoreCase(key)) {
                    reader.close();
                    return line.substring(key.length() + 1);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }

    public static void setKey(String path, String key, String data) {

        String[] file_as_array = getFileAsArray(path);
        for (int i = 0; i < file_as_array.length; i++) {
            if (file_as_array[i].length() >= key.length() &&
                    file_as_array[i].substring(0, key.length()).equalsIgnoreCase(key)) {
                file_as_array[i] = data;
                writeArrayToFile(path, file_as_array);
                break;
            }
        }

    }

    @Deprecated
    public static void setMemberUserData(String path, MemberUser memberUser) {

        Gson gson = new GsonBuilder().create();
        gson.toJson(memberUser);

        String[] file_as_array = getFileAsArray(path);
        for (int i = 0; i < file_as_array.length; i++) {
            if (file_as_array[i].contains(memberUser.getMemberUuid())) {
                file_as_array[i] = gson.toJson(memberUser);
                writeArrayToFile(path, file_as_array);
                return;
            }
        }

        String[] new_file = new String[file_as_array.length + 1];
        System.arraycopy(file_as_array, 0, new_file, 0, file_as_array.length);
        new_file[new_file.length - 1] = gson.toJson(memberUser);

        writeArrayToFile(path, new_file);

    }

}
