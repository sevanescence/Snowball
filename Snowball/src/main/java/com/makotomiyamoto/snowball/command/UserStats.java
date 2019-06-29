package com.makotomiyamoto.snowball.command;

import com.makotomiyamoto.snowball.interfaces.CommandExecutor;
import com.makotomiyamoto.snowball.manager.CommandManager;
import com.makotomiyamoto.snowball.manager.MemberUserManager;
import com.makotomiyamoto.snowball.object.MemberUser;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class UserStats implements CommandExecutor {

    private String label;
    private int argCount;
    private Permission permission;

    public UserStats(String label, int argCount, Permission permission) {
        this.label = label;
        this.argCount = argCount;
        this.permission = permission;
    }

    @Override
    @SuppressWarnings("all")
    public void cast(String label, String[] args, MessageReceivedEvent event) {

        if (!this.label.equalsIgnoreCase(label) || !event.getMember().hasPermission(permission) || !args[0].contains("@")) {
            return;
        }

        for (Member member : event.getGuild().getMembers()) {

            if (member.getAsMention().equalsIgnoreCase(args[0])) {

                MemberUser memberUser = MemberUserManager
                        .getMemberUser("src/main/resources/userdata.json", member.getId());

                String message =
                        "**" + member.getUser().getName() + "**'s stats:" +
                        "\nLevel: **" + memberUser.getLevel() + "**" +
                        "\nMessages: **" + memberUser.getMessages() + "**" +
                        "\nWarns: **" + memberUser.getWarns() + "**" +
                        "\nStrikes: **" + memberUser.getStrikes() + "**" +
                        "\nKicks: **" + memberUser.getKicks() + "**" +
                        "\nBans: " + memberUser.getBansToString();

                event.getChannel().sendMessage(message).complete();

                return;
            }

        }

        event.getChannel()
                .sendMessage("User `" + args[0].replaceFirst("@", "") + "` not found. 3:")
                .complete();

    }

}
