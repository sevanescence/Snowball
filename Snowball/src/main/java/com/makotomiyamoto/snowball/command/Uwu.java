package com.makotomiyamoto.snowball.command;

import com.makotomiyamoto.snowball.interfaces.CommandExecutor;
import com.makotomiyamoto.snowball.manager.CommandManager;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public final class Uwu implements CommandExecutor {

    private String label;
    private int argCount;
    private Permission permission;

    public Uwu(String label, int argCount, Permission permission) {
        this.label = label;
        this.argCount = argCount;
        this.permission = permission;
    }

    @Override
    @SuppressWarnings("all")
    public void cast(String label, String[] args, MessageReceivedEvent event) {

        if (!this.label.equals(label) || !event.getMember().hasPermission(permission)) {
            return;
        }

        args = CommandManager.merge(args, argCount);

        if (args[0].contains("pls")) {
            event.getChannel().sendMessage("uwu").complete();
            return;
        }

        event.getChannel().sendMessage("only if you say `pls` 3:").complete();

    }

}
