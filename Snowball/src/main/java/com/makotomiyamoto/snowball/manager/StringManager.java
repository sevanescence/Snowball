package com.makotomiyamoto.snowball.manager;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public class StringManager {

    public static String getChannelMentions(String string, JDA jda) {

        List<TextChannel> channels = jda.getTextChannels();
        for (String word : string.split(" ")) {
            if (!word.contains("#")) {
                continue;
            }
            for (TextChannel channel : channels) {
                if (word.replaceFirst("#", "")
                        .replaceAll("[^a-zA-Z0-9]", "")
                            .equalsIgnoreCase(channel.getName())) {
                    string = string.replaceFirst(word, channel.getAsMention());
                    break;
                }
            }
        }

        return string;

    }

    @SuppressWarnings("all")
    public static boolean tryForFilter(Message message, MessageReceivedEvent event) {

        String[] bannedWords = FileManager.getKey("src/main/resources/config.txt", "BannedWords")
                .trim()
                    .split(" ");

        String string = message.toString();
        String newString = string;
        for (String word : bannedWords) {
            if (!string.contains(word)) {
                continue;
            }
            newString = string.replaceAll(word, "****");
        }

        if (!string.equals(newString)) {
            return false;
        }

        return true;
    }

}
