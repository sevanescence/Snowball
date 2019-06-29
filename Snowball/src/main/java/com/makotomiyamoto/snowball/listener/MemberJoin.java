package com.makotomiyamoto.snowball.listener;

import com.makotomiyamoto.snowball.manager.FileManager;
import com.makotomiyamoto.snowball.manager.StringManager;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.util.List;

public final class MemberJoin extends ListenerAdapter {

    @Override
    @SuppressWarnings("all")
    public void onGuildMemberJoin(@Nonnull GuildMemberJoinEvent event) {

        String dir = "src/main/resources/config.txt";

        String welcome_message = FileManager.getKey(dir, "MemberJoinMessage");
        welcome_message = StringManager.getChannelMentions(welcome_message, event.getJDA());
        welcome_message = welcome_message.replaceAll("%user%", event.getMember().getAsMention());
        event.getGuild().getDefaultChannel().sendMessage(welcome_message).complete();

        String default_role = FileManager.getKey(dir, "DefaultRole");
        List<Role> roles = event.getJDA().getRoles();
        for (Role role : roles) {
            if (role.getName().equalsIgnoreCase(default_role)) {
                event.getGuild().addRoleToMember(event.getMember(), role).complete();
                break;
            }
        }

    }

}
