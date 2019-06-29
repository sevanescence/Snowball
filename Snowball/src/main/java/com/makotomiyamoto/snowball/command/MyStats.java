package com.makotomiyamoto.snowball.command;

import com.makotomiyamoto.snowball.interfaces.CommandExecutor;
import com.makotomiyamoto.snowball.manager.MemberUserManager;
import com.makotomiyamoto.snowball.object.MemberUser;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public final class MyStats implements CommandExecutor {

    private String label;

    public MyStats(String label) {
        this.label = label;
    }

    @Override
    @SuppressWarnings("all")
    public void cast(String label, String[] args, MessageReceivedEvent event) {

        if (!this.label.equals(label)) {
            return;
        }

        MemberUser user = MemberUserManager
                .getMemberUser("src/main/resources/userdata.json", event.getAuthor().getId());

        int xpToLevelUp = (int) Math.round(100 * (Math.pow(user.getLevel(), 1.5)));

        String nick = event.getMember().getNickname();
        if (nick == null) {
            nick = "no nickname 3:";
        }

        String statsMessage =
                "**" + event.getAuthor().getName() + "'s** stats:" +
                "\nNick: **" + nick +
                "**\nLevel: **" + user.getLevel() +
                "**\nExp: **" + user.getMessages() + "**/**" + xpToLevelUp +
                // empty line
                "**\n\nI'm doing my best 3:";

        event.getChannel().sendMessage(statsMessage).complete();

    }

}
