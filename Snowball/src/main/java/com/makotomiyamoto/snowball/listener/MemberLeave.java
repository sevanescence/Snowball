package com.makotomiyamoto.snowball.listener;

import com.makotomiyamoto.snowball.manager.FileManager;
import net.dv8tion.jda.api.events.guild.member.GuildMemberLeaveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public final class MemberLeave extends ListenerAdapter {

    @Override
    @SuppressWarnings("all")
    public void onGuildMemberLeave(GuildMemberLeaveEvent event) {

        String dir = "src/main/resources/config.txt";

        String leave_message = FileManager.getKey(dir, "MemberLeaveMessage")
                .replaceAll("%user%", event.getMember().getAsMention());

        event.getGuild().getDefaultChannel().sendMessage(leave_message).complete();

    }

}
