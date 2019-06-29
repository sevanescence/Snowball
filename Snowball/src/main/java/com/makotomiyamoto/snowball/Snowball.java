package com.makotomiyamoto.snowball;

import com.makotomiyamoto.snowball.command.Help;
import com.makotomiyamoto.snowball.interfaces.CommandExecutor;
import com.makotomiyamoto.snowball.listener.*;
import com.makotomiyamoto.snowball.command.Uwu;
import com.makotomiyamoto.snowball.manager.FileManager;
import com.makotomiyamoto.snowball.object.Command;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;

public final class Snowball {

    private static ArrayList<CommandExecutor> commands = new ArrayList<>();

    public static void tryCommand(Command cmd, MessageReceivedEvent event) {

        for (CommandExecutor command : commands) {
            command.cast(cmd.getLabel(), cmd.getArgs(), event);
        }

    }

    public static void main(String[] args) throws Exception {

        System.out.println("Compiling the adorable kitty cat...");

        System.out.println("Instantiating commands...");

        commands.add(new Uwu("uwu", 1, Permission.MESSAGE_READ));
        commands.add(new Help("help"));

        System.out.println("Commands initialised! >:3");

        JDABuilder builder = new JDABuilder(AccountType.BOT);
        builder.setToken(FileManager.getKey("src/main/resources/data.txt", "token"));
        builder.addEventListeners(new MemberJoin());
        builder.addEventListeners(new MemberLeave());
        builder.addEventListeners(new MemberMessage());
        builder.addEventListeners(new CommandListener());
        builder.setActivity(Activity.of(Activity.ActivityType.DEFAULT, "with my yarn! >:3"));

        JDA jda = builder.build();
        jda.awaitReady();

        System.out.println("Snowball is online!");

    }

}
