package com.makotomiyamoto.snowball.command;

import com.makotomiyamoto.snowball.interfaces.CommandExecutor;
import com.makotomiyamoto.snowball.manager.FileManager;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public final class Help implements CommandExecutor {

    private String label;

    public Help(String label) {
        this.label = label;
    }

    @Override
    @SuppressWarnings("all")
    public void cast(String label, String[] args, MessageReceivedEvent event) {

        if (!this.label.equals(label)) {
            return;
        }

        event.getChannel().sendMessage(FileManager.getKey("src/main/resources/config.txt", "helpOkay")).complete();

    }

}
