package com.makotomiyamoto.snowball.interfaces;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public interface CommandExecutor {

    void cast(String label, String[] args, MessageReceivedEvent event);

}
