package com.makotomiyamoto.snowball.listener;

import com.makotomiyamoto.snowball.manager.MemberUserManager;
import com.makotomiyamoto.snowball.manager.StringManager;
import com.makotomiyamoto.snowball.object.MemberUser;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public final class MemberMessage extends ListenerAdapter {

    @Override
    @SuppressWarnings("all")
    public void onMessageReceived(MessageReceivedEvent event) {

        if (!event.getMessage().isFromType(ChannelType.TEXT) ||
                !StringManager.tryForFilter(event.getMessage(), event) ||
                        event.getAuthor().isBot()) {
            return;
        }

        String dir = "src/main/resources/userdata.json";

        if (MemberUserManager.lookForUser(dir, event.getAuthor().getId())) {

            MemberUser memberUser = MemberUserManager.getMemberUser(dir, event.getAuthor().getId());
            memberUser.setMessages(memberUser.getMessages() + 1);
            memberUser.tryLevelUp();
            MemberUserManager.setMemberUser(dir, memberUser);

            MemberUserManager.checkRoles(memberUser, event);

            return;

        }

        System.out.println("User does not exist in database! Writing now. 3:<");

        MemberUser memberUser = new MemberUser(event.getAuthor().getId(), 1, 1);
        MemberUserManager.setMemberUser(dir, memberUser);

    }

}
