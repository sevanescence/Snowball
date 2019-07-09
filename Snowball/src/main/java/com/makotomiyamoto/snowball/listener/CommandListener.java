package com.makotomiyamoto.snowball.listener;

import com.makotomiyamoto.snowball.Snowball;
import com.makotomiyamoto.snowball.object.Command;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public final class CommandListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        if (!event.getMessage().getContentRaw().startsWith("!")) {
            return;
        }

        String[] msg = event.getMessage().getContentRaw().replaceAll("!", "").trim().split(" ");
        String[] newMsg = new String[msg.length - 1];
        System.arraycopy(msg, 1, newMsg, 0, newMsg.length);

        Command cmd = new Command(msg[0], newMsg);

        Snowball.tryCommand(cmd, event);

    }

}
